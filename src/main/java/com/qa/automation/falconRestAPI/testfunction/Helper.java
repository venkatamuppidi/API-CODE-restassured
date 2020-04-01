/**
 * 
 */
package com.qa.automation.falconRestAPI.testfunction;

/**
 * @author venkata.muppidi
 *
 */
import io.restassured.response.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;


import com.atmecs.falcon.automation.util.logging.LogManager;
import com.atmecs.falcon.automation.util.parser.CSVReader;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.atmecs.falcon.automation.verifyresult.VerificationManager;
import com.google.gson.JsonObject;
import com.hydra.restapi.testfunction.Constants;
import com.hydra.restapi.utilities.JSONParserUtility;
import com.hydra.restapi.utilities.Utilities;

public class Helper{
	JSONParserUtility jsonParserUtility=new JSONParserUtility();
	String csvFile;
	Reader reader;
	Iterable<CSVRecord> recordsIterator;
	ReportLogService report = new ReportLogServiceImpl(GrievanceHelper.class);
	CSVReader csvReader = null;
	Properties configProperties = new Properties();
	Utilities utility = new Utilities();

	static Logger logger = LogManager.getInstance().getLogger(GrievanceHelper.class);

	/**
	 * Purpose : This method used as a Constructor to construct, File, Reader and Iterator Objects.
	 * @param csvFilePath
	 */
	public Helper(String csvFilePath) throws IOException {
		this.csvFile = csvFilePath;
		try {
			reader = new FileReader(this.csvFile);
			csvReader = new CSVReader(this.csvFile);
			recordsIterator = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
			Constants constants = new Constants();
			configProperties = new Properties();
			configProperties.load(constants.getCONFIGURL());


		} catch (FileNotFoundException fileNotFoundException) {
			logger.debug("File doesn't exist in the given path {}:, {}", csvFile, fileNotFoundException.getMessage());
			Reporter.log("File doesn't exist in the given path: " + csvFile + " " + fileNotFoundException.getMessage());
			throw new FileNotFoundException("File doesn't exist in the given path: " + csvFile);
		} catch (IOException ioException) {
			logger.debug("File doesn't close properly: {}", ioException.getMessage());
			Reporter.log("File doesn't close properly: " + ioException.getMessage());
			throw new IOException("File doesn't close properly: " + ioException.getMessage());
		}
	}

	/**
	 * Purpose : This method returns headersSet value.
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
	 * Purpose : This method is used to get file name of csv file.
	 * @return csv file name.
	 */
	public String getFileName() {
		File file = new File(csvFile);
		return file.getName();
	}

	/**
	 * Purpose : This Method returns the simple column value of the given data set
	 * @param csvRecord is CSVRecord
	 * @param dataset is unique test data number.
	 * @param columnName is column name.
	 * @return simpleValue is value of given column name
	 */
	public String getSimpleValue(CSVRecord csvRecord, String dataset, String columnName) {
		String simpleValue = "";

		if (csvRecord.get("dataset").equalsIgnoreCase(dataset))
			simpleValue = csvRecord.get(columnName);

		return simpleValue;
	}

	/**
	 * Purpose : This Method returns the simple column value of the given data set.
	 * @param dataset is unique test data number.
	 * @param columnName is column name.
	 * @return simpleValue is value of given column name
	 */
	public String getSimpleValue(String dataset, String columnName) {
		String simpleValue = "";

		try {
			reader = new FileReader(this.csvFile);
			recordsIterator = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
		} catch (FileNotFoundException fileNotFoundException) {
			logger.debug("File doesn't exist in the given path {}:, {}", csvFile, fileNotFoundException.getMessage());
			Reporter.log("File doesn't exist in the given path: " + csvFile + " " + fileNotFoundException.getMessage());
		} catch (IOException ioException) {
			logger.debug("File doesn't close properly: {}", ioException.getMessage());
			Reporter.log("File doesn't close properly: " + ioException.getMessage());
		}

		for (CSVRecord csvRecord : recordsIterator) {
			if (csvRecord.get("dataset").equalsIgnoreCase(dataset)) {
				simpleValue = csvRecord.get(columnName);
				break;
			}
		}
		return simpleValue;
	}

	/**
	 * Purpose : This method returns List of JSONObject from CSV
	 * @return
	 */
	public List<JsonObject> getJSONObjectList() {

		List<JsonObject> jsonObjectList = new ArrayList<JsonObject>();

		Set<String> headersSet = getHeadersSet();

		report.info(""+headersSet);

		if (!headersSet.isEmpty()) {
			for (CSVRecord csvRecord : recordsIterator) {

				JsonObject jsonObject = new JsonObject();

				if (utility.isDataSetEnabled(csvRecord)) {
					String datasetValue = csvRecord.get("dataset");

					report.info("dataset : "+datasetValue);

					for (String columnName : headersSet) {
						if (columnName.contains("_enabled") && !columnName.contains("dataset")) {

							if (utility.isColumnEnabled(csvRecord, columnName)) {
								String jsonObjectKey = columnName.replace("_enabled", "").replace("is_", "");
								try {
									jsonObject.addProperty(jsonObjectKey,getSimpleValue(csvRecord, datasetValue, jsonObjectKey));
								} catch (Exception exception) {
									Reporter.log("Unexpected exception occur: {}" + exception.getMessage());
									logger.debug("Unexpected exception occur: {}", exception.getMessage());
								}

								report.info("----json key : "+jsonObjectKey);

								if(jsonObjectKey.equalsIgnoreCase("attributes"))
								{
									JsonObject rvuRequestJson = new JsonObject();
									for (String column : headersSet) {
										if(column.equalsIgnoreCase("is_attributes_enabled"))
											continue;

										if(column.contains("_attributes")) {
											report.info("@   @Colname : " +column);
											int dataSetValue = Integer.parseInt(datasetValue);
											String tval = csvReader.getElementDataByKey(dataSetValue, column);
											rvuRequestJson.addProperty(column.replace("_attributes",""), tval);
											report.info("request"+rvuRequestJson);
										}

									}
									report.info("After for loop"+rvuRequestJson);
									jsonObject.add("attributes", rvuRequestJson);

								}

							}
						}
					}

					jsonObjectList.add(jsonObject);
				}
			}
		}
		return jsonObjectList;
	}

	/**
	 * Purpose : prepares the request object from CSV
	 * @param csvFilePath
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void prepareRequest(String csvFilePath) throws FileNotFoundException, IOException {

		GrievanceHelper grievanceutility = new GrievanceHelper(csvFilePath);

		String folderName = grievanceutility.getFileName().split(Pattern.quote("."))[Constants.ZERO];
		report.info(folderName);

		Path pathOfRequestTestDataFolder = Paths.get(Constants.TEST_DATA_HOME + Constants.fileSeparator + folderName.toLowerCase() + Constants.fileSeparator + Constants.REQUEST);
		try {
			Files.createDirectories(pathOfRequestTestDataFolder);
		} catch (IOException ioException) {
			Reporter.log("Unable to read the specified file: " + ioException.getMessage());
			logger.debug("Unable to read the specified file {}", ioException.getMessage());
		}

		try {
			reader = new FileReader(csvFilePath);
			recordsIterator = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
		} catch (FileNotFoundException fileNotFoundException) {
			logger.debug("File doesn't exist in the given path {}:, {}", csvFile, fileNotFoundException.getMessage());
			Reporter.log("File doesn't exist in the given path: " + csvFile + " " + fileNotFoundException.getMessage());
		} catch (IOException ioException) {
			Reporter.log("Unable to read the specified file: " + ioException.getMessage());
			logger.debug("Unable to read the specified file {}", ioException.getMessage());
		}

		List<JsonObject> columnValuesList = grievanceutility.getJSONObjectList();
		int columnCount = 0;
		for (CSVRecord csvRecord : recordsIterator) {
			if (!utility.isDataSetEnabled(csvRecord))
				continue;
			JsonObject obj = columnValuesList.get(columnCount);
			String TestCaseCode = csvRecord.get("TestCaseCode");
			System.out.println("tcc"+TestCaseCode);
			jsonParserUtility.writeAsPrettyJson(obj, pathOfRequestTestDataFolder.toString() + Constants.fileSeparator + TestCaseCode + ".json");

			columnCount++;
		}
	}

	//Verification for the grievance helpers
	public void Verification(int expectedResponseCode,
			org.codehaus.jettison.json.JSONObject jsonObject,
			ResponseService responseService, String UniqueID,
			String columnname, String tableName, String fileName) {
		Utilities utilities = new Utilities();
		boolean isSuccess = VerificationManager.verifyInteger(
				responseService.getStatusCode(), expectedResponseCode,
				"Response Status code validation success");
		if (isSuccess == false) {
			String message = "Response code validation failed. Expected was: "
					+ Constants.RESPONSECODE + " But Found"
					+ responseService.getStatusCode();
			System.err.println("Exception message is: " + message);
			Assert.fail(message);

		}
		System.out.println("response body default: "
				+ responseService.getBody());

		boolean isResponseAvailable = VerificationManager.verifyTrue(
				responseService.getBody() != null,
				"Response data validation success");
		if (isResponseAvailable == false) {
			String message = "Response data displayed as null. Actual Data coming is: "
					+ responseService.getBody();
			System.err.println("Response message is: " + message);
			Assert.fail(message);
		}

		if (expectedResponseCode == 200) {
			String uniqueKey = null;
			try {
				uniqueKey = jsonObject.getString(UniqueID);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("UniqueID got from JSON is: " + uniqueKey);

			boolean isDBValidationSucess = utilities
					.prepareDBConnectionAndvalidateResponse(jsonObject,
							columnname, uniqueKey, fileName, tableName);
			if (isDBValidationSucess == false) {
				Assert.fail("DB validation not sucess, please check");
			}
		}
	}

	//Verification for the Grievance patch helpers
	public void VerificationForRestAssured(int expectedResponseCode,
			org.codehaus.jettison.json.JSONObject JsonObject,
			Response response, String dataSetFilePath, String sqlQuery) throws Exception {
		Utilities utilities = new Utilities();
		boolean isSuccess = VerificationManager.verifyInteger(
				response.getStatusCode(), expectedResponseCode,
				"Response check validation success");
		if (isSuccess == false) {
			String message = "Response code validation failed. Expected was: "
					+ expectedResponseCode + " But Found: "
					+ response.getStatusCode();
			System.err.println("Exception message is: " + message);
			Assert.fail(message);
		}

		System.out.println("Response body is: " + response.getBody());

		boolean isResponseAvailable = VerificationManager.verifyTrue(
				response.getBody() != null, "Response data validation success");
		if (isResponseAvailable == false) {
			String message = "Response data displayed as null. Actual Data coming is: "
					+ response.getBody();
			System.err.println("Response message is: " + message);
			Assert.fail(message);
		}

		if (expectedResponseCode == 200) {

			boolean isDBValidationSucess = utilities
					.prepareDBConnectionAndvalidateResponse1(JsonObject,dataSetFilePath, sqlQuery);
			if (isDBValidationSucess == false) {
				Assert.fail("DB validation not sucess, please check");
			}

		}

	}
	//Verification for the Grievance post helpers
	public void VerificationForResponseService(org.codehaus.jettison.json.JSONObject jsonObject, int expectedResponseCode, ResponseService responseService, String UniqueID, String dataSetFilePath, String sqlQuery) throws Exception
	{
		Utilities utilities = new Utilities();
		boolean isSuccess = VerificationManager.verifyInteger(responseService.getStatusCode(), expectedResponseCode,
				"Response check validation success");
		if (isSuccess == false) {
			String message = "Response code validation failed. Expected was: "+ expectedResponseCode + " But Found"+ responseService.getStatusCode();
			System.err.println("Exception message is: " + message);
			Assert.fail(message);

		}
		System.out.println("response body default: "
				+ responseService.getBody());

		boolean isResponseAvailable = VerificationManager.verifyTrue(responseService.getBody() != null,
				"Response data validation success");
		if(isResponseAvailable == false){
			String message = "Response data displayed as null. Actual Data coming is: "
					+ responseService.getBody();
			System.err.println("Response message is: "+message);
			Assert.fail(message);
		}
		if(expectedResponseCode==200)
		{
			boolean isDBValidationSucess = utilities.prepareDBConnectionAndvalidateResponse1(jsonObject, dataSetFilePath, sqlQuery);
			if(isDBValidationSucess == false){
				Assert.fail("DB validation not sucess, please check");
			}
		}
	}

}

The information in this message may be proprietary and/or confidential, and protected from disclosure. If the reader of this message is not the intended recipient, you are hereby notified that any dissemination, distribution or copying of this communication is strictly prohibited. If you have received this communication in error, please notify ATMECS and delete it from your computer.
