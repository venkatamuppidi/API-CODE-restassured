/**
 * 
 */
package com.qa.automation.falconRestAPI.testscript;

/**
 * @author venkata.muppidi
 *
 */
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.map.HashedMap;
import org.codehaus.jettison.json.JSONObject;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atmecs.falcon.automation.dataprovider.TestDataProvider;
import com.atmecs.falcon.automation.rest.endpoint.RequestBuilder;
import com.atmecs.falcon.automation.util.parser.CSVReader;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.atmecs.falcon.automation.verifyresult.VerificationManager;
import com.qa.automation.falconRestAPI.testfunction.Constants;
import com.qa.automation.falconRestAPI.testfunction.Helper;
import com.qa.automation.falconRestAPI.testsuite.TestSuiteBase;
import com.qa.automation.falconRestAPI.utilities.JSONParserUtility;
import com.qa.automation.falconRestAPI.utilities.Utilities;

/**
 * @author VKonda
 *
 */
public class TestGet extends TestSuiteBase implements ITest {
	JSONObject requestJSONobject = new JSONObject();
	JSONObject expectedResponseBody = new JSONObject();
	JSONObject actualResponseBody = new JSONObject();
	TestDataProvider dataProvider = TestDataProvider.getInstance();
	RequestBuilder requestBuilder = new RequestBuilder();
	ReportLogService report = new ReportLogServiceImpl(TestGet.class);
	Helper helper = null;
	JSONParserUtility jsonParserUtility;
	Utilities utilities = new Utilities();
	CSVReader csvReader = null;
	RequestSpecification request = RestAssured.given();
	String currentTestCaseCodeBenefit = "TestGetUserService";
	Properties databaseProperties;

	@BeforeMethod
	public void setUp(Object[] args) {
		String separator = "Hydra_API_Reporter";
		if (!isEmpty(args)) {
			File file = (File) args[0];
			if (file.isFile()) {
				String TCC = file.getName().replace(".json", "");
				currentTestCaseCodeBenefit = TCC
						+ separator
						+ getDescription(TCC,
								Constants.GET_GRIEVANCE_REQUEST_DATASET_CSV);
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
		report.info("STEP 1: Prepare response data for Get Grievance");

		grievancehelper = new GrievanceHelper(Constants.GET_GRIEVANCE_REQUEST_DATASET_CSV);

		GrievanceHelper userhelper = new GrievanceHelper(
				Constants.GET_GRIEVANCE_REQUEST_DATASET_CSV);
		userhelper.prepareRequest(Constants.GET_GRIEVANCE_REQUEST_DATASET_CSV);

		report.info("STEP 2: Prepare expected response data for AppSec user");
		TestDataProvider.folderPath = Constants.GET_GRIEVANCE_TESTDATA_FILES;
	}

	@SuppressWarnings("unused")
	@Test(dataProvider = "DataProvider", dataProviderClass = TestDataProvider.class)
	public void testGETGrievanceService(File testDataFile) throws Exception {
		requestJSONobject = dataProvider.getJSONObject(testDataFile);
		int expectedResponseCode = 0;
		String authtoken = utilities.authentication();
		System.out.println("Auth Token is::::"+authtoken);
		@SuppressWarnings("unchecked")
		Map<String, String> map = new HashedMap();
		map.put("Authorization", authtoken);
		if (requestJSONobject != null) {
			report.info("<b>STEP 1: </b>Prepared request data for get grievance successfully");
			report.info("Response body::" + requestJSONobject);
		}

		expectedResponseCode = requestJSONobject.getInt("responsecode");
		System.out.println("Expected response code is: " + expectedResponseCode);

		requestJSONobject.remove("responsecode");

		Constants.ID = requestJSONobject.getString("GrievanceID");
		System.out.println("Requested JSON object is: " + requestJSONobject);
		String url = (utilities.getURI(Constants.GET_GRIEVANCE_ENDPOINT) + Constants.ID);

		report.info("<b>STEP 2: </b>Make an GET Grievance API call to server");
		report.info("URL for get user api service is: " + url);

		ResponseService responseService = requestBuilder
				.headers(map)
				.build().get(new URI(url));


		report.info("<b>STEP 3: </b>Response body for get grievance request");

		report.info("Response Body \n" + responseService.getBody());

		report.info("<b>STEP 4: </b>Response status code for get grievance request");

		report.info("Response code \n" + responseService.getStatusCode());

		System.out.println("Response code: " + responseService.getStatusCode());

		System.out.println("response body default: "+ responseService.getBody());
		boolean isSuccess = VerificationManager.verifyInteger(
				responseService.getStatusCode(), expectedResponseCode,
				"Response check validation success");
		if (isSuccess == false) {
			String message = "Response code validation failed. Expected was: "
					+ expectedResponseCode + " But Found: "
					+ responseService.getStatusCode();
			System.err.println("Exception message is: " + message);
			Assert.fail(message);

		}
		try{
			if(responseService.getStatusCode()==200){

				JSONObject actualJSON = new JSONObject(responseService.getBody());
				System.out.println("ACTUAL JSON IS :"+actualJSON);
				JSONObject data = actualJSON.getJSONObject("data");
				System.out.println("Actual Data is :"+data);
				JSONObject responseJSONobject = data.getJSONObject("attributes");
				String uniqueKey = responseJSONobject.getString("MemberID");
				report.info("<b>STEP 5: </b>Response status and Database validations for get grievance request");
				GrievanceHelper grievanceHelper = new GrievanceHelper(Constants.GET_GRIEVANCE_REQUEST_DATASET_CSV);
				grievanceHelper.Verification(expectedResponseCode, responseJSONobject,responseService, "MemberID", "MemberID",
						"Grievances", Constants.GET_GRIEVANCE_REQUEST_DATASET_CSV);
			}
		}catch(Exception e){}
	}

	@Override
	public String getTestName() {
		return currentTestCaseCodeBenefit;

	}

}
