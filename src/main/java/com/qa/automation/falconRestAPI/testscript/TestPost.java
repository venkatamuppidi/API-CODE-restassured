/**
 * 
 */
package com.qa.automation.falconRestAPI.testscript;

/**
 * @author venkata.muppidi
 *
 */
import java.io.File;
import java.io.IOException;
import java.net.URI;
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
import com.qa.automation.falconRestAPI.testfunction.Constants;
import com.qa.automation.falconRestAPI.testfunction.Helper;

/**
 * @author VKonda
 */
public class TestPost extends TestSuiteBase implements ITest {

	JSONObject requestJSONobject = new JSONObject();
	JSONObject expectedResponseBody = new JSONObject();
	JSONObject actualResponseBody = new JSONObject();
	TestDataProvider dataProvider = TestDataProvider.getInstance();
	RequestBuilder requestBuilder = new RequestBuilder();
	ReportLogService report = new ReportLogServiceImpl(TestPostGrievance.class);
	UserHelper userHelper = null;
	JSONParserUtility jsonParserUtility;
	Utilities utilities = new Utilities();
	CSVReader csvReader = null;
	String currentTestCaseCode;
	ResultSet resultSet;


	@BeforeMethod
	public void setUp(Object[] args) {
		String separator = "Hydra_API_Reporter";
		if (!isEmpty(args)) {
			File file = (File) args[0];
			if (file.isFile()) {
				String TCC = file.getName().replace(".json", "");
				currentTestCaseCode = TCC+ separator+ getDescription(TCC, Constants.POST_GRIEVANCE_REQUEST_DATASET_CSV);
			}
		}
	}

	public String getDescription(String testCaseCode, String requestCSVFilePath) {
		String testCaseDescription = "";
		try {
			csvReader = new CSVReader(requestCSVFilePath);
			java.util.List<String> TestCaseCodeList = csvReader.getAllDataOfGivenColumnName("TestCaseCode");
			int dataset = TestCaseCodeList.indexOf((Object) testCaseCode) + 1;
			testCaseDescription = csvReader.getElementDataByKey(dataset,"testDescription");

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

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	@BeforeTest
	public void dataSetup() throws Exception {
		report.info("STEP 1: prepare request data for POST new Grievance");

		userHelper = new UserHelper(Constants.POST_GRIEVANCE_REQUEST_DATASET_CSV);

		userHelper.prepareRequest(Constants.POST_GRIEVANCE_REQUEST_DATASET_CSV);

		report.info("STEP 2: prepare expected response data for new grievance creation");
		TestDataProvider.folderPath = Constants.POST_GRIEVANCE_TESTDATA_FILES;
	}

	@SuppressWarnings("unchecked")
	@Test(dataProvider = "DataProvider", dataProviderClass = TestDataProvider.class)
	public void testPOSTMemberService(File testDataFile) throws Exception {

		int expectedResponseCode = 0;
		// Read the request test data file
		requestJSONobject = dataProvider.getJSONObject(testDataFile);

		if (requestJSONobject != null) {
			report.info("<b>STEP 1: </b>Prepared request data for Post new Grievance successfully");
			report.info("..." + requestJSONobject);
		}

		report.info("<b>STEP 2: </b>URL for Post new Grievance");
		report.info("--" + utilities.getURI(Constants.POST_GRIEVANCE_ENDPOINT));
		utilities.authentication();
		String authtoken=utilities.authentication();
		Map<String, String> map = new HashedMap();
		map.put("Authorization",authtoken);

		expectedResponseCode = requestJSONobject.getInt("responsecode");
		System.out.println("Expected response code is: "+expectedResponseCode);

		requestJSONobject.remove("responsecode");
		Constants.ID = requestJSONobject.getString("MemberID");
		System.out.println("After remove of response code: "+requestJSONobject);

		report.info("<b>STEP 3: </b>Make an POST Grievance api call to server");

		ResponseService responseService = requestBuilder
				.contentType("application/json")
				.headers(map)
				.body(requestJSONobject.toString())
				.build()
				.post(new URI(utilities.getURI(Constants.POST_GRIEVANCE_ENDPOINT)));

		report.info("<b>STEP 4: </b>Response body for post grievance request");

		report.info("Response Body \n" +responseService.getBody());

		report.info("<b>STEP 5: </b>Response Status code for post grievance request");

		report.info("Response code \n" +responseService.getStatusCode());

		report.info("<b>STEP 6: </b>Response Status code verification and database validations");
		try{
			if(responseService.getStatusCode()==200){
				JSONObject str=new JSONObject(responseService.getBody());
				String GrievnaceID=str.getString("stringId");

				Helper grievancehelper = new Helper(Constants.POST_GRIEVANCE_REQUEST_DATASET_CSV);
				grievancehelper.VerificationForResponseService(requestJSONobject, expectedResponseCode, responseService, GrievnaceID, Constants.POST_GRIEVANCE_REQUEST_DATASET_CSV, Constants.GETGRIEVANCEBYID+GrievnaceID);
			}
		}catch(Exception e){}
	}

	@Override
	public String getTestName() {
		return currentTestCaseCode;
	}


}