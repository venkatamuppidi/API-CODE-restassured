/**
 * 
 */
package com.qa.automation.falconRestAPI.testscript;

/**
 * @author venkata.muppidi
 *
 */
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;




import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.codehaus.jettison.json.JSONObject;
import org.testng.ITest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atmecs.falcon.automation.dataprovider.TestDataProvider;

import com.atmecs.falcon.automation.util.parser.CSVReader;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;



public class TestPatch extends TestSuiteBase implements ITest {

	JSONObject requestJSONobject = new JSONObject();
	JSONObject expectedResponseBody = new JSONObject();
	JSONObject actualResponseBody = new JSONObject();
	TestDataProvider dataProvider = TestDataProvider.getInstance();
	RequestBuilder requestBuilder = new RequestBuilder();
	ReportLogService report = new ReportLogServiceImpl(TestPatchGrievance.class);
	GrievanceHelper grievanceHelper = null;
	JSONParserUtility jsonParserUtility;
	Utilities utilities = new Utilities();
	CSVReader csvReader = null;
	String currentTestCaseCode;
	SampleDBConnectivity dbconnectivity = new SampleDBConnectivity();
	RequestSpecification request = RestAssured.given();
	ResultSet resultSet;


	@BeforeMethod
	public void setUp(Object[] args) {
		String separator = "Hydra_API_Reporter";
		if (!isEmpty(args)) {
			File file = (File) args[0];
			if (file.isFile()) {
				String TCC = file.getName().replace(".json", "");
				currentTestCaseCode = TCC
						+ separator
						+ getDescription(TCC, Constants.PATCH_GRIEVANCE_REQUEST_DATASET_CSV);
			}
		}
	}

	public String getDescription(String testCaseCode, String requestCSVFilePath) {
		String testCaseDescription = "";
		try {
			csvReader = new CSVReader(requestCSVFilePath);
			java.util.List<String> TestCaseCodeList = csvReader
					.getAllDataOfGivenColumnName("TestCaseCode");
			int dataset = TestCaseCodeList.indexOf((Object) testCaseCode) + 1;
			testCaseDescription = csvReader.getElementDataByKey(dataset,
					"testDescription");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return testCaseDescription;
	}

	/**
	 * This method returns true if the input array is null or its length is
	 * zero.
	 *
	 * @param array
	 * @return true | false
	 */
	public static boolean isEmpty(Object[] array) {
		if (array == null || array.length == 0) {
			return true;
		}
		return false;
	}

	@BeforeTest
	public void dataSetup() throws Exception {
		report.info("STEP 1: prepare request data for Patch Grievance");

		grievanceHelper = new GrievanceHelper(Constants.PATCH_GRIEVANCE_REQUEST_DATASET_CSV);

		grievanceHelper.prepareRequest(Constants.PATCH_GRIEVANCE_REQUEST_DATASET_CSV);

		TestDataProvider.folderPath = Constants.PATCH_GRIEVANCE_TESTDATA_FILES;
	}

	@Test(dataProvider = "DataProvider", dataProviderClass = TestDataProvider.class)
	public void TestPATCHGrievanceService(File testDataFile) throws Exception {

		// Read the request test data file
		requestJSONobject = dataProvider.getJSONObject(testDataFile);
		int expectedResponseCode = 0;

		if (requestJSONobject != null) {
			report.info("<b>STEP 1: </b>Prepared request data for PATCH grievance successfully");
			report.info("..." + requestJSONobject);
		}
		expectedResponseCode = requestJSONobject.getInt("responsecode");
		Constants.ID = requestJSONobject.getString("GrievanceId");
		requestJSONobject.remove("GrievanceId");
		requestJSONobject.remove("responsecode");

		String patchURL = utilities.getURI(Constants.PATCH_GRIEVANCE_ENDPOINT+Constants.ID);

		report.info("<b>STEP 2: </b>URL for PATCH member ");
		report.info("--" + patchURL);
		utilities.authentication();
		String authtoken=utilities.authentication();
		@SuppressWarnings("unchecked")
		Map<String, String> map = new HashedMap();
		map.put("Authorization",authtoken);


		report.info("<b>STEP 3: </b>Make an PATCH member API call to server");

		request.body(requestJSONobject.toString());
		request.contentType(ContentType.JSON);

		Response response = request
				.headers(map)
				.patch(patchURL);
		report.info("<b>STEP 4: </b>Response status code for Patch Grievance API Service request ");
		report.info("Response code \n" +response.getStatusCode());
		report.info("<b>STEP 5: </b>Response body for Patch Grievance API Service request ");
		report.info("Response body \n" +response.toString());
		report.info("<b>STEP 6: </b> Resonse status code and DB validations ");
		try{
			if(response.getStatusCode()==200){
				GrievanceHelper grievancehelper = new GrievanceHelper(Constants.PATCH_GRIEVANCE_REQUEST_DATASET_CSV);
				grievancehelper.VerificationForRestAssured(expectedResponseCode, requestJSONobject, response, Constants.PATCH_GRIEVANCE_REQUEST_DATASET_CSV, Constants.GETGRIEVANCEBYID+Constants.ID);
			}
		}catch(Exception e){}
	}

	@Override
	public String getTestName() {
		return currentTestCaseCode;
	}
}
