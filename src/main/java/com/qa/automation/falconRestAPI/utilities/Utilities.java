/**
 * 
 */
package com.qa.automation.falconRestAPI.utilities;

/**
 * @author venkata.muppidi
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;

import com.atmecs.falcon.automation.db.sql.DBConnector;


import com.atmecs.falcon.automation.util.logging.LogManager;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.atmecs.falcon.automation.verifyresult.VerificationManager;


public class Utilities extends TestSuiteBase {
String csvFile;
Reader reader;
Properties props;
RequestBuilder requestBuilder = new RequestBuilder();
ReportLogService report = new ReportLogServiceImpl(Utilities.class);

static Logger logger = LogManager.getInstance().getLogger(Utilities.class);

public String getURI(String endPoint) {
return props.getProperty("BASE_URI") + endPoint;
}

public String documentURI(String endPoint) {
return props.getProperty("Document_URI") + endPoint;
}

public String getSQLQuery(String tableName, String uniqueKeyForQuery, String uniqueKeyValueForQuery) {

String query = dbProps.getProperty("query") + " " + tableName + " Where " + uniqueKeyForQuery + " = '"
+ uniqueKeyValueForQuery + "' ";
System.out.println("SQL query generated is: " + query);
return query;
}

public String getSQLQuery(String tableName, String uniqueKeyForQuery, String uniqueKeyValueForQuery,
String function, String CRUPDate, String sequence) {
String query = memberAddressProps.getProperty("query") + " " + tableName + " Where " + uniqueKeyForQuery
+ " = '" + uniqueKeyValueForQuery + "' " + function + " " + CRUPDate + " " + sequence;
System.out.println("SQL query generated is: " + query);
return query;
}

public static void testCaseAssertion() {
List<String> steps = Reporter.getOutput(Reporter.getCurrentTestResult());
for (String step : steps) {
if (step.contains("FAIL")) {
Assert.fail();

}
}
}

/**
* Purpose : This method returns headersSet value.
*
* @return headersSet set of String values.
*/
@SuppressWarnings("resource")
public Set<String> getHeadersSet() {
Set<String> headersSet = new HashSet<String>();

try {
reader = new FileReader(this.csvFile);

CSVParser csvParser = new CSVParser(this.reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
headersSet = csvParser.getHeaderMap().keySet();
} catch (FileNotFoundException fileNotFoundException) {
logger.debug("File doesn't exist in the given path {}:, {}", csvFile, fileNotFoundException.getMessage());
Reporter.log("File doesn't exist in the given path: " + csvFile + " " + fileNotFoundException.getMessage());
} catch (IOException ioException) {
Reporter.log("Unable to read the specified file: " + ioException.getMessage());
logger.debug("Unable to read the specified file {}", ioException.getMessage());
}

return headersSet;
}

/**
* Purpose : This method returns boolean by checking data set enable status for
* the given CSVRecord
*
* @param csvRecord  is CSVRecord
* @param columnName is column name to check whether enable or disable.
* @return true if column name is enabled or returns false if column name is
*         disable.
*/
public boolean isColumnEnabled(CSVRecord csvRecord, String columnName) {
if (csvRecord.get(columnName).equalsIgnoreCase("1"))
return true;
else
return false;
}

/**
* Purpose : This method returns boolean by checking data set enable status for
* the given CSVRecord
*
* @param csvRecord is CSVRecord
* @return true if dataset is enable or returns false if dataset is disable.
*/
public boolean isDataSetEnabled(CSVRecord csvRecord) {
String jenkinsSanityFlag = "false";
if (jenkinsSanityFlag.equalsIgnoreCase("true")) {
if (isColumnEnabled(csvRecord, "is_sanity_test")) {
if (csvRecord.get("is_dataset_enabled").equalsIgnoreCase("1"))
return true;
else
return false;
} else
return false;
} else {
if (csvRecord.get("is_dataset_enabled").equalsIgnoreCase("1"))
return true;
else
return false;
}
}

/**
* Purpose : This method returns boolean by validating the response JSON value
* with DB.
*
* @param JSONObject responseJSON - JSON object which you want to compare with
*                   DB. String uniqueKeyForQuer & String uniqueKeyValueForQuery
*                   - unique key & value for sqlQuery String dataSetFilePath -
*                   Path of the CSV file from which column needs to read.
* @return true if values in JSON & DB matched else retrun False.
*/
public boolean prepareDBConnectionAndvalidateResponse(JSONObject responseJSON, String uniqueKeyForQuery,
String uniqueKeyValueForQuery, String dataSetFilePath, String tabelName) {

this.csvFile = dataSetFilePath;
ResultSet rstmt=null;
Set<String> headersSet = getHeadersSet();

boolean isDBColumnAvailable = true;
boolean isRequestKeyAvailable = true;
boolean isvaldate = false;
String sqlQuery, mappingNamewithDB = null;
try {
sqlQuery = getSQLQuery(tabelName, uniqueKeyForQuery, uniqueKeyValueForQuery);

DBConnector dbConnector = JDBCUtils.getDBConnector();

Connection connection = dbConnector.getConnection();
System.out.println("Connection : " + connection);

PreparedStatement ps = connection.prepareCall(Constants.PROCEDUREQUERY);

ps.execute();
Statement stmt = connection.createStatement();
rstmt = stmt.executeQuery(sqlQuery);
System.out.println("FromDB row is: " + rstmt.getRow());
while (rstmt.next()) {
System.out.println("_________________Reached here______________________");
for (String columnName : headersSet) {
if (!columnName.contains("_enabled") && !columnName.contains("dataset")
&& !columnName.contains("TestCaseCode") && !columnName.contains("testDescription")
&& !columnName.contains("responsecode")) {

/*
* This try is use to validate DB column available or not
*/
try {
mappingNamewithDB = dbProps.getProperty(columnName);
System.out.println("ColmnName Value ---------->"+columnName);
System.out.println("Mapping value ------------>"+mappingNamewithDB);
String valueRetrivedFromDB = rstmt.getString(mappingNamewithDB);
isDBColumnAvailable = true;
// System.out.println("Column name from Mapping config file: " + mappingNamewithDB);
report.info("DB column is :"+valueRetrivedFromDB);
} catch (Exception e) {
isDBColumnAvailable = false;
}

/*
* This try is used to validate response key with the given column is available
* or not.
*/
try {
responseJSON.getString(columnName);
isRequestKeyAvailable = true;
report.info("Column name is: " + columnName);
} catch (Exception e) {
isRequestKeyAvailable = false;
}

if (isDBColumnAvailable && isRequestKeyAvailable) {

String valueRetrivedFromDB = rstmt.getString(mappingNamewithDB);
valueRetrivedFromDB = valueRetrivedFromDB.split(Pattern.quote("."))[Constants.ZERO];
valueRetrivedFromDB = valueRetrivedFromDB.split(Pattern.quote("T"))[Constants.ZERO];
System.out.println("DBValue for column is: " + rstmt.getString(mappingNamewithDB));

String requestColumnValue = responseJSON.getString(columnName);
requestColumnValue = requestColumnValue.split(Pattern.quote("."))[Constants.ZERO];
requestColumnValue = requestColumnValue.split(Pattern.quote("T"))[Constants.ZERO];
if (columnName.equalsIgnoreCase("dob")) {
requestColumnValue = requestColumnValue.substring(0, 10);
}
if (columnName.contains("Date")) {
requestColumnValue = requestColumnValue.substring(0, 10);
valueRetrivedFromDB = valueRetrivedFromDB.substring(0, 10);
}

System.out.println(".........." + columnName);

System.out.println("Request body value for column is: " + requestColumnValue);

isvaldate = VerificationManager.verifyString(valueRetrivedFromDB, requestColumnValue,
"String comparison for column is: " + columnName);
if (isvaldate == false)
return false;
System.out.println("DB validation sucess");
}

}
}
}
// connection.close();
// }
} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return isvaldate;

}

/*
* public boolean prepareDBConnectionAndvalidateResponseNew( JSONObject
* responseJSON, String dataSetFilePath, String tableName, String
* uniqueKeyForQuery, String uniqueKeyValueForQuery, String function, String
* CRUPDate, String sequence) {
*
* this.csvFile = dataSetFilePath; ResultSet rstmt = null;
*
* Set<String> headersSet = getHeadersSet();
*
* boolean isDBColumnAvailable = true; boolean isRequestKeyAvailable = true;
* boolean isvaldate = false; String sqlQuery, mappingNamewithDB = null;
*
* try {
*
* sqlQuery = getSQLQuery(tableName, uniqueKeyForQuery, uniqueKeyValueForQuery,
* function, CRUPDate, sequence);
*
* DBConnector dbConnector = JDBCUtils.getDBConnector();
*
* Connection connection = dbConnector.getConnection();
* System.out.println("Connection : " + connection);
*
* PreparedStatement ps = connection .prepareCall(Constants.PROCEDUREQUERY);
*
* ps.execute(); Statement stmt = connection.createStatement(); rstmt =
* stmt.executeQuery(sqlQuery); System.out.println("FromDB row is: " +
* rstmt.getRow()); while (rstmt.next()) {
*
* Constants.MEMBERID = rstmt.getString("memberId"); // Need to // think of //
* taking // the value // from // somewhere // else
* System.out.println("Address id got after POST success is: " +
* Constants.ADDRESSID); for (String columnName : headersSet) { if
* (!columnName.contains("_enabled") && !columnName.contains("dataset") &&
* !columnName.contains("TestCaseCode") &&
* !columnName.contains("testDescription") &&
* !columnName.contains("responsecode")) {
*
* This try is use to validate DB column available or not try {
*
* mappingNamewithDB = memberAddressProps .getProperty(columnName); String
* valueRetrivedFromDB = rstmt .getString(mappingNamewithDB);
* isDBColumnAvailable = true; System.out
* .println("Column name from Mapping config file: " + mappingNamewithDB); }
* catch (Exception e) { isDBColumnAvailable = false; }
*
* This try is used to validate response key with the given column is available
* or not. try { responseJSON.getString(columnName);
*
* isRequestKeyAvailable = true; System.out.println("Column name is: " +
* columnName); } catch (Exception e) { isRequestKeyAvailable = false; }
*
* if (isDBColumnAvailable && isRequestKeyAvailable) {
*
* String valueRetrivedFromDB = rstmt .getString(mappingNamewithDB);
* System.out.println("DBValue for column is: " +
* rstmt.getString(mappingNamewithDB)); valueRetrivedFromDB =
* valueRetrivedFromDB.split(Pattern.quote("."))[Constants.ZERO];
*
* String requestColumnValue = responseJSON.getString(columnName); if
* (columnName.equalsIgnoreCase("dob")) { requestColumnValue =
* requestColumnValue .substring(0, 10); requestColumnValue =
* valueRetrivedFromDB.split(Pattern.quote("."))[Constants.ZERO];
*
* } System.out .println("Request body value for column is: " +
* requestColumnValue);
*
* isvaldate = VerificationManager.verifyString( valueRetrivedFromDB,
* requestColumnValue, "String comparison failed for column is: " + columnName);
* if (isvaldate == false) return false; }
*
* } } }
*
* } catch (Exception e) { // TODO Auto-generated catch block
* e.printStackTrace(); } return isvaldate;
*
* }
*/

public void deleteDirectory(String fileName) {

File file = new File(fileName);
String folderName = file.getName().split(Pattern.quote("."))[Constants.ZERO];
boolean isFileAvailable = false;
String path = null;

Path pathOfRequestTestDataFolder = null;
try {
pathOfRequestTestDataFolder = Paths.get(Constants.TEST_DATA_HOME + Constants.fileSeparator
+ folderName.toLowerCase() + Constants.fileSeparator + Constants.REQUEST);
path = pathOfRequestTestDataFolder.toString();
isFileAvailable = true;
} catch (Exception e1) {
isFileAvailable = false;
}
if (isFileAvailable == true) {

try {
File dir = new File(path).getAbsoluteFile();
if (dir.isDirectory()) {
File[] children = dir.listFiles();
for (int i = 0; i < children.length; i++) {
boolean success = children[i].delete();
System.out.println("Child index: " + i + " deleted " + success);
}
}

Files.delete(pathOfRequestTestDataFolder);
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}

}

public boolean prepareDBConnectionAndvalidateResponse1(JSONObject responseJSON, String dataSetFilePath,
String sqlQuery) throws SQLException {

this.csvFile = dataSetFilePath;
ResultSet rstmt = null;

Set<String> headersSet = getHeadersSet();

boolean isDBColumnAvailable = true;
boolean isRequestKeyAvailable = true;
boolean isvaldate = false;
String mappingNamewithDB = null;
Connection connection = null;

try {

// sqlQuery = getSQLQuery(sqlQuery);
System.out.println("Requested SQL Query is: " + sqlQuery);

DBConnector dbConnector = JDBCUtils.getDBConnector();

connection = dbConnector.getConnection();
System.out.println("Connection : " + connection);

PreparedStatement ps = connection.prepareCall(Constants.PROCEDUREQUERY);

ps.execute();
Statement stmt = connection.createStatement();
rstmt = stmt.executeQuery(sqlQuery);
System.out.println("FromDB row is: " + rstmt.getRow());

while (rstmt.next()) {

for (String columnName : headersSet) {
if (!columnName.contains("_enabled") && !columnName.contains("dataset")
&& !columnName.contains("TestCaseCode") && !columnName.contains("testDescription")
&& !columnName.contains("responsecode")) {

/*
* This try is use to validate DB column available or not
*/
try {

mappingNamewithDB = dbProps.getProperty(columnName);
System.out.println("Mapped value from :"+mappingNamewithDB);
String valueRetrivedFromDB = rstmt.getString(mappingNamewithDB);
isDBColumnAvailable = true;
System.out.println("Column name from Mapping config file: " + valueRetrivedFromDB);
} catch (Exception e) {
isDBColumnAvailable = false;
}

/*
* This try is used to validate response key with the given column is available
* or not.
*/
try {

responseJSON.getString(columnName);

isRequestKeyAvailable = true;
report.info("Column name is: " + columnName);
} catch (Exception e) {
isRequestKeyAvailable = false;
}

if (isDBColumnAvailable && isRequestKeyAvailable) {
String valueRetrivedFromDB = rstmt.getString(mappingNamewithDB).replaceAll("[^\\x00-\\x7F]", "");
System.out.println("DBValue for column is: " + rstmt.getString(mappingNamewithDB));

valueRetrivedFromDB = valueRetrivedFromDB.split(Pattern.quote("."))[Constants.ZERO];
valueRetrivedFromDB = valueRetrivedFromDB.split(Pattern.quote("T"))[Constants.ZERO];

String requestColumnValue = responseJSON.getString(columnName).replaceAll("[^\\x00-\\x7F]", "");
requestColumnValue = requestColumnValue.split(Pattern.quote("."))[Constants.ZERO];
requestColumnValue = requestColumnValue.split(Pattern.quote("T"))[Constants.ZERO];
if (columnName.equalsIgnoreCase("dob")) {
requestColumnValue = requestColumnValue.substring(0, 10);
requestColumnValue = valueRetrivedFromDB.split(Pattern.quote("."))[Constants.ZERO];

}
if (columnName.contains("Date")) {
requestColumnValue = requestColumnValue.substring(0, 10);
valueRetrivedFromDB = valueRetrivedFromDB.substring(0, 10);
}
if (columnName.contains("message-type-created-date")) {
requestColumnValue = requestColumnValue.substring(0, 10);
valueRetrivedFromDB = valueRetrivedFromDB.substring(0, 10);
}
if (columnName.contains("created-date")) {
requestColumnValue = requestColumnValue.substring(0, 10);
valueRetrivedFromDB = valueRetrivedFromDB.substring(0, 10);
}
if (columnName.contains("updated-date")) {
requestColumnValue = requestColumnValue.substring(0, 10);
valueRetrivedFromDB = valueRetrivedFromDB.substring(0, 10);
}

if (columnName.equalsIgnoreCase("EmailConfirmed") || columnName.equalsIgnoreCase("IsActive")
|| columnName.equalsIgnoreCase("TwoFactorEnabled")
|| columnName.equalsIgnoreCase("EmailOptOut")
|| columnName.equalsIgnoreCase("IsMemberActive")
|| columnName.equalsIgnoreCase("isBenefitActive")
|| columnName.equalsIgnoreCase("isTemporary")
|| columnName.equalsIgnoreCase("ignoreTempMember")
|| columnName.equalsIgnoreCase("hasOrders")) {
valueRetrivedFromDB = (valueRetrivedFromDB.equals("1")) ? "true" : "false";

}

System.out.println("Request body value for column is: " + requestColumnValue);
System.out.println("Verify DB VALUE///////////////////////:"+valueRetrivedFromDB);
System.out.println("Verify ColumnValue VALUE///////////////////////:"+requestColumnValue);
isvaldate = VerificationManager.verifyString(valueRetrivedFromDB, requestColumnValue,
"String comparison for column is: " + columnName);
if (isvaldate == false) {
connection.close();
return false;
}

}

}
}
}

// }
}

catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
connection.close();
return isvaldate;
}

public String authentication() throws URISyntaxException {
// To get JWT token in authentication level
String requestbody = "{\"password\":\"Password@123\",\"appId\":\"1\",\"tenantId\":\"44\",\"username\":\"USPGI\\\\VKonda\"}";

System.out.println("Request Body:: " + requestbody);
String url = (utilities.getURI(Constants.POST_AUTH_SSO_ENDPOINT));
ResponseService responseService = requestBuilder.contentType("application/json").body(requestbody).build()
.post(new URI(url));
String body = responseService.getResponseBody().toString();
// String token="Bearer "+"\""+body+"\"";
String token = "Bearer " + body;
System.out.println("Response body --->"+body);
System.out.println("ResponeCode--->"+responseService.getStatusCode());
System.out.println("ResponeLine--->"+responseService.getStatusLine());
System.out.println("Authentication JWT token :" + token);
// .get(url);
// ResponseService responseService = requestBuilder
// .contentType("application/json")
// .headers(map)
// .body(requestJSONobject.toString())
// .build()
// .post(new URI(utilities.getURI(Constants.POST_GRIEVANCE_ENDPOINT)));

return token;

}

public boolean auditValidationAftetSuccessAPI(String uniqueObjectID, String operationName) throws SQLException {

Connection connection = null;
String sqlQuery = "select * from EventAudit where ObjectID='" + uniqueObjectID + "' and Operation='"
+ operationName + "'";
System.out.println("Sql query is: " + sqlQuery);
try {
DBConnector dbConnector = JDBCUtils.getDBConnector();

connection = dbConnector.getConnection();
System.out.println("Connection : " + connection);

PreparedStatement ps = connection.prepareCall(Constants.PROCEDUREQUERY);

ps.execute();
// String sqlQuery =
// "SELECT * FROM Members WHERE ExternalMemberID = 'UL0000007'";
Statement stmt = connection.createStatement();
ResultSet rstmt = stmt.executeQuery(sqlQuery);

while (rstmt.next()) {
System.out.println("Current row number is: " + rstmt.getRow());
if (rstmt.getRow() > 1)
break;

String dbID = rstmt.getString("ObjectID");
String dbOperation = rstmt.getString("Operation");

boolean isAuditIDSuccess = VerificationManager.verifyString(dbID, uniqueObjectID,
"Audit varifaction for Object ID success");
boolean isOperationSuccess = VerificationManager.verifyString(dbOperation, operationName,
"Audit verfication for Operation name success");

if ((isAuditIDSuccess || isOperationSuccess) == false) {
connection.close();
return false;
}

}

} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
connection.close();
return true;

}

}
