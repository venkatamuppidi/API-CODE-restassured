/**
 * 
 */
package com.qa.automation.falconRestAPI.testfunction;

/**
 * @author venkata.muppidi
 *
 */
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

import com.qa.automation.falconRestAPI.testsuite.TestSuiteBase;



public class Constants extends TestSuiteBase {
public static final String fileSeparator = System.getProperty("file.separator");
public static final String userDir = System.getProperty("user.dir") + fileSeparator;
public static final String TEST_DATA_HOME = "src" + fileSeparator + "main" + fileSeparator + "java" + fileSeparator
+ "com" + fileSeparator + "hydra" + fileSeparator + "restapi" + fileSeparator + "testdata";
public static final String TEST_DATA_RESOURCES = "src" + fileSeparator + "main" + fileSeparator + "resources";

public static final String REQUEST = "request";
public static final String RESPONSE = "response";
public static final int ZERO = 0;

public static int RESPONSECODE = 0;
public static String MEMBERID = null;
public static String LoginName = null;
public static String ADDRESSID = null;
public static String memberid = null;
public static String reasontypeid = null;
public static String statustypeid = null;

public static String documentname = null;
public static String documenttype = null;
public static String documentstatus = null;

public static String id = null;
public static String ID = null;

public static String NOTEID = null;
public static String Dt_Ini = null;
public static String Dt_End = null;

public static String SORT = null;
public static String FILTER = null;

public static final String GET_USER_ENDPOINT = "/api/users/";
public static final String POST_USER_ENDPOINT = "/api/users";
public static final String POST_USERFORGOTPASSWORD_ENDPOINT = "/api/users/forgotpassword";
public static final String POST_UPDATEPASSWORD_ENDPOINT = "/api/users/updateuserpassword";

public static final String POST_AUDIT_ENDPOINT = "/api/event-audit";
public static final String GET_AUDIT_ENDOINT = "/api/event-audit/GetallAsync?";
public static final String GET_AUDITBYID_ENDPOINT = "/api/event-audit/GetEventAuditByMemberIDAsync?memberId=";

public static final String MEMBERADDRESS_ENDPOINT = "/api/member-addresses/";

public static final String POSTMEMBERADDRESS_ENDPOINT = "/api/member-addresses";

public static final String GET_CATEGORY_ENDPOINT = "/api/categories/";

public static final String POST_CATEGORY_ENDPOINT = "/api/categories";

public static final String PATCH_CATEGORY_ENDPOINT = "/api/categories/";

public static final String MEMBER = "/api/members/";

public static final String GET_PRODUCT_ENDPOINT = "/api/products/";

public static final String POST_PRODUCT_ENDPOINT = "/api/products";

public static final String PATCH_PRODUCT_ENDPOINT = "/api/products/";

public static final String POST_MEMBERNOTES_ENDPOINT = "/api/member-notes";

public static final String PATCH_MEMBERNOTES_ENDPOINT = "/api/member-notes/";

public static final String POST_LOGIN_ENDPOINT = "/Auth/Login";

public static final String POST_MEMBER_ENDPOINT = "/api/members";

public static final String PATCH_MEMBER_ENDPOINT = "/api/members/updatemember";

public static final String GET_MEMBERNOTE_ENDPOINT = "/api/member-notes/";

public static final String GET_WEB_REG_DETAILS_ENDPOINT = "/webregistrationdetails";

public static final String DELETE_MEMBER_ENDPOINT = "/api/members/";

public static final String PUT_ALTERNATE_NUMBER_ENDPOINT = "/api/members/";

public static final String POST_GRIEVANCE_ENDPOINT = "/api/Grievance";

public static final String GET_GRIEVANCE_ENDPOINT = "/api/Grievance/";

public static final String PATCH_GRIEVANCE_ENDPOINT = "/api/Grievance/";
public static final String POST_AUTH_SSO_ENDPOINT = "/Auth/Login";

public static final String GET_REASONBYREASONTYPEID_ENDPOINT = "/api/common/GetReason?reasonTypeid=";

public static final String GET_REASONTYPES_ENDPOINT = "/api/common/GetReasonTypes";

public static final String GET_STATUSTYPES_ENDPOINT = "/api/common/GetStatusTypes";

public static final String GET_STATUSBYSTATUSTYPEID_ENDPOINT = "/api/common/GetStatus?statusTypeId=";

public static final String GET_MESSAGETYPESCATEGORIES_ENDPOINT = "/api/message-type-categories";

public static final String POST_MESSAGETYPE_ENDPOINT = "/api/message-types";

public static final String GET_MEMBERADDRESSANDCURRENTBENEFITS_ENDPOINT = "/api/members?include=member-addresses,currentbenefits";

public static final String PATCH_WEBPORTALUSERBYID_ENDPOINT = "/api/web-portal-user/";

public static final String POST_WEBPORTALUSER__ENDPOINT = "/api/web-portal-user";

public static final String POST_WEBPORTALUSER_FORGOTPASSWORD_ENDPOINT = "/api/web-portal-user/forgotpassword";

public static final String POST_UPDATEPASSWORD_WEBPORTALUSER_ENDPOINT = "/api/web-portal-user/updatepassword";

public static final String POST_WEBPORTALUSER_FORGOTUSERNAME_ENDPOINT = "/api/web-portal-user/forgotusername";

public static final String Get_WEBPORTALUSER_ENDPOINT = "/api/web-portal-user/";

public static final String GET_COMMONS_FACILITIES_ENDPOINT = "/api/common/GetFacilities";

public static final String GET_COMMONS_LANGUAGE_ENDPOINT = "/api/common/GetLanguages";

public static final String GET_COMMONS_STATES_ENDPOINT = "/api/common/GetStates";

public static final String GET_COMMONS_CITY_ENDPOINT = "/api/common/GetCityByStateId?stateCode=";

public static final String GET_MESSAGE_TYPE_CATEGORY_ID_ENDPOINT = "/api/message-type-categories/";

public static final String GET_MESSAGE_TYPE_ENDPOINT = "/api/message-types";

public static final String GET_MESSAGE_TYPE_ID_ENDPOINT = "/api/message-types?filter[msg-type-category-id]=";

public static final String GET_MEMBER_BENEFIT_PLAN_ENDPOINT = "/api/member-benefits?filter[isTemporary]=";

public static final String GET_ROLES_ENDPOINT="/api/roles/";

public static final String PROCEDUREQUERY = "exec sp_set_session_context 'tenant_id', "
+ props.getProperty("TenantID");

// Post User Service
public static final String POST_USER_TEST_DATA_RESOURCES = "src" + fileSeparator + "main" + fileSeparator
+ "resources" + fileSeparator + "userservice";
public final static String POST_USER_REQUEST_DATASET_CSV = userDir + POST_USER_TEST_DATA_RESOURCES + fileSeparator
+ "postuser.csv";
public final static String POST_USER_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "postuser" + fileSeparator
+ "request";
public final static String POST_USER_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "postuser" + fileSeparator
+ "response";

// Post User Forgot Password
public static final String POST_USERFORGOTPASSWORD_TEST_DATA_RESOURCES = "src" + fileSeparator + "main"
+ fileSeparator + "resources" + fileSeparator + "userservice";
public final static String POST_USERFORGOTPASSWORD_DATASET_CSV = userDir + POST_USER_TEST_DATA_RESOURCES
+ fileSeparator + "postuserforgotpassword.csv";
public final static String POST_USERFORGOTPASSWORD_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator
+ "postuserforgotpassword" + fileSeparator + "request";
public final static String POST_USERFORGOTPASSWORD_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator
+ "postuserforgotpassword" + fileSeparator + "response";

// Post User Forgot Password
public static final String POST_USERUPDATEPASSWORD_TEST_DATA_RESOURCES = "src" + fileSeparator + "main"
+ fileSeparator + "resources" + fileSeparator + "userservice";
public final static String POST_USERUPDATEPASSWORD_DATASET_CSV = userDir
+ POST_USERUPDATEPASSWORD_TEST_DATA_RESOURCES + fileSeparator + "postuserupdatepassword.csv";
public final static String POST_USERUPDATEPASSWORD_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator
+ "postuserupdatepassword" + fileSeparator + "request";
public final static String POST_USERUPDATEPASSWORD_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator
+ "postuserupdatepassword" + fileSeparator + "response";

// Get User
public static final String GET_USER_TEST_DATA_RESOURCES = "src" + fileSeparator + "main" + fileSeparator
+ "resources" + fileSeparator + "userservice";
public final static String GET_USER_REQUEST_DATASET_CSV = userDir + POST_USER_TEST_DATA_RESOURCES + fileSeparator
+ "getuser.csv";
public final static String GET_USER_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "getuser" + fileSeparator
+ "request";
public final static String GET_USER_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "getuser" + fileSeparator
+ "response";
// Post Audit Service
public static final String POST_AUDIT_TEST_DATA_RESOURCES = "src" + fileSeparator + "main" + fileSeparator
+ "resources" + fileSeparator + "auditservice";
public final static String POST_AUDIT_REQUEST_DATASET_CSV = userDir + POST_AUDIT_TEST_DATA_RESOURCES + fileSeparator
+ "postaudit.csv";
public final static String POST_AUDIT_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "postaudit" + fileSeparator
+ "request";
public final static String POST_AUDIT_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "postaudit" + fileSeparator
+ "response";
// Get Audit Service
public static final String GET_AUDIT_TEST_DATA_RESOURCES = "src" + fileSeparator + "main" + fileSeparator
+ "resources" + fileSeparator + "auditservice";
public final static String GET_AUDIT_REQUEST_DATASET_CSV = userDir + GET_AUDIT_TEST_DATA_RESOURCES + fileSeparator
+ "getaudit.csv";
public final static String GET_AUDIT_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "getaudit" + fileSeparator
+ "request";
public final static String GET_AUDIT_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "getaudit" + fileSeparator
+ "response";
// Get Audit Service By ID
public static final String GET_AUDITBYID_TEST_DATA_RESOURCES = "src" + fileSeparator + "main" + fileSeparator
+ "resources" + fileSeparator + "auditservice";
public final static String GET_AUDITBYID_REQUEST_DATASET_CSV = userDir + GET_AUDITBYID_TEST_DATA_RESOURCES
+ fileSeparator + "getauditbyid.csv";
public final static String GET_AUDITBYID_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "getauditbyid"
+ fileSeparator + "request";
public final static String GET_AUDITBYID_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "getauditbyid"
+ fileSeparator + "response";

// Post Grievance Service
public static final String POST_GRIEVANCE_TEST_DATA_RESOURCES = "src" + fileSeparator + "main" + fileSeparator
+ "resources" + fileSeparator + "grievance";
public final static String POST_GRIEVANCE_REQUEST_DATASET_CSV = userDir + POST_GRIEVANCE_TEST_DATA_RESOURCES
+ fileSeparator + "postgrievance.csv";
public final static String POST_GRIEVANCE_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "postgrievance"
+ fileSeparator + "request";
public final static String POST_GRIEVANCE_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "postgrievance"
+ fileSeparator + "response";

// Patch Grievance Service
public static final String PATCH_GRIEVANCE_TEST_DATA_RESOURCES = "src" + fileSeparator + "main" + fileSeparator
+ "resources" + fileSeparator + "grievance";
public final static String PATCH_GRIEVANCE_REQUEST_DATASET_CSV = userDir + PATCH_GRIEVANCE_TEST_DATA_RESOURCES
+ fileSeparator + "patchgrievance.csv";
public final static String PATCH_GRIEVANCE_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "patchgrievance"
+ fileSeparator + "request";
public final static String PATCH_GRIEVANCE_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "patchgrievance"
+ fileSeparator + "response";

// Get Grievance Service
public static final String GET_GRIEVANCE_TEST_DATA_RESOURCES = "src" + fileSeparator + "main" + fileSeparator
+ "resources" + fileSeparator + "grievance";
public final static String GET_GRIEVANCE_REQUEST_DATASET_CSV = userDir + GET_GRIEVANCE_TEST_DATA_RESOURCES
+ fileSeparator + "getgrievance.csv";
public final static String GET_GRIEVANCE_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "getgrievance"
+ fileSeparator + "request";
public final static String GET_GRIEVANCE_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "getgrievance"
+ fileSeparator + "response";

// Document
public final static String Document_REQUEST_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator + "document"
+ fileSeparator + "postdocumenttestdata.csv";
public static final String Document_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "postdocumenttestdata"
+ fileSeparator + "request";
public static final String Document_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "postdocumenttestdata"
+ fileSeparator + "response";

public static final String GetDocument_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "getdocumentbyid"
+ fileSeparator + "request";
public static final String GetDocumentName_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "getdocumentName"
+ fileSeparator + "request";
public final static String GETDocumentID_PREPARE_Request_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "document" + fileSeparator + "GetdocumentbyID.csv";
public final static String GETDocumentNAME_PREPARE_REQ_REPONSE_DATASET_CSV = userDir + TEST_DATA_RESOURCES
+ fileSeparator + "document" + fileSeparator + "GetdocumentName.csv";

public final static String Document_RELATIONSHIP = userDir + TEST_DATA_RESOURCES + fileSeparator + "document"
+ fileSeparator + "database.properties";

// Post member
public final static String POST_MEMBER_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator + "member"
+ fileSeparator + "POSTMember.csv";
public static final String POST_MEMBER_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "POSTMember"
+ fileSeparator + "request";
public static final String POST_MEMBER_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "POSTMember" + fileSeparator
+ "response";
// Patch member
public final static String PATCH_MEMBER_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator + "member"
+ fileSeparator + "PATCHMember.csv";
public static final String PATCH_MEMBER_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "PATCHMember"
+ fileSeparator + "request";
public static final String PATCH_MEMBER_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "PATCHMember"
+ fileSeparator + "response";
// Delete member
public final static String DELETE_MEMBER_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator + "member"
+ fileSeparator + "DeleteMember.csv";
public static final String DELETE_MEMBER_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "DELETEMember"
+ fileSeparator + "request";
public static final String DELETE_MEMBER_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "DELETEMember"
+ fileSeparator + "response";
// Put alternative number
public final static String PUT_ALTERNATE_NEMBER_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "member" + fileSeparator + "PutMember.csv";
public static final String PUT_ALTERNATE_NEMBER_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "PutMember"
+ fileSeparator + "request";
public static final String PUT_ALTERNATE_NEMBER_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "PutMember"
+ fileSeparator + "response";

// Post member notes
public final static String POST_MEMBERNOTES_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "membernotes" + fileSeparator + "postmembernotes.csv";
public static final String POST_MEMBERNOTE_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "POSTMemberNotes"
+ fileSeparator + "request";
public static final String POST_MEMBERNOTE_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "POSTMemberNotes"
+ fileSeparator + "response";
// Patch member notes
public final static String PATCH_MEMBERNOTES_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "membernotes" + fileSeparator + "patchmembernotes.csv";
public static final String PATCH_MEMBERNOTE_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "PatchMemberNotes"
+ fileSeparator + "request";
public static final String PATCH_MEMBERNOTE_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "PatchMemberNotes"
+ fileSeparator + "response";

// login
public final static String POST_LOGIN_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator + "login"
+ fileSeparator + "login.csv";
public static final String POST_LOGIN_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "login" + fileSeparator
+ "request";
public static final String POST_LOGIN_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "POSTLogin" + fileSeparator
+ "response";

// Get Web registration details
public final static String GET_WEB_REG_DETAILS_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "webregdetails" + fileSeparator + "getwebregdetails.csv";
public static final String GET_WEB_REG_DETAILS_FILES = TEST_DATA_HOME + fileSeparator + "getwebregdetails"
+ fileSeparator + "request";
public static final String GET_WEB_REG_DETAILS_DATA = TEST_DATA_HOME + fileSeparator + "getwebregdetails"
+ fileSeparator + "response";

// Get member
public final static String GET_MEMBER_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator + "member"
+ fileSeparator + "getmember.csv";
public static final String GET_MEMBER_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "getmember" + fileSeparator
+ "request";
public static final String GET_MEMBER_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "getmember" + fileSeparator
+ "response";

// Get Member notes
public final static String GET_MEMBERNOTE_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "membernotes" + fileSeparator + "getmembernote.csv";
public final static String GET_NOTES_DATASET_CSV=userDir+TEST_DATA_RESOURCES+fileSeparator+"membernotes"+fileSeparator+"GetNotes.csv";
public static final String GET_MEMBERNOTE_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "getmembernote"
+ fileSeparator + "request";
public static final String GET_MEMBERNOTE_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "getmembernote"
+ fileSeparator + "response";

// Response Member Address
public final static String ADDRESS_RESPONSE_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "memberaddress" + fileSeparator + "getMemberAddress.csv";

// Patch Member Address
public final static String ADDRESS_PATCHREQUEST_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "memberaddress" + fileSeparator + "patchMemberAddress.csv";

// Post Member Address
public final static String ADDRESS_POSTREQUEST_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "memberaddress" + fileSeparator + "postMemberAddress.csv";
public static final String ADDRESS_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "postmemberaddress"
+ fileSeparator + "request";
public static final String ADDRESS_PATCH_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "patchmemberaddress"
+ fileSeparator + "request";
public static final String ADDRESS_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "getmemberaddress"
+ fileSeparator + "response";

// Get Product
public final static String GET_PRODUCT_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator + "product"
+ fileSeparator + "getProduct.csv";
public static final String GET_PRODUCT_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "GETProduct"
+ fileSeparator + "request";
public static final String GET_PRODUCT_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "GETProduct" + fileSeparator
+ "response";
// Post Product
public final static String POST_PRODUCT_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator + "product"
+ fileSeparator + "postProduct.csv";
public static final String POST_PRODUCT_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "POSTProduct"
+ fileSeparator + "request";
public static final String POST_PRODUCT_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "POSTProduct"
+ fileSeparator + "response";
// Patch Product
public final static String PATCH_PRODUCT_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator + "product"
+ fileSeparator + "patchProduct.csv";
public static final String PATCH_PRODUCT_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "PATCHProduct"
+ fileSeparator + "request";
public static final String PATCH_PRODUCT_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "PATCHProduct"
+ fileSeparator + "response";
// Get Category
public final static String GET_CATEGORY_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator + "category"
+ fileSeparator + "getcategory.csv";
public static final String GET_CATEGORY_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "GETCategory"
+ fileSeparator + "request";
public static final String GET_CATEGORY_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "GETCategory"
+ fileSeparator + "response";
// Post Category
public final static String POST_CATEGORY_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator + "category"
+ fileSeparator + "postCategory.csv";
public static final String POST_CATEGORY_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "POSTCategory"
+ fileSeparator + "request";
public static final String POST_CATEGORY_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "POSTCategory"
+ fileSeparator + "response";
// Patch Category
public final static String PATCH_CATEGORY_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator + "category"
+ fileSeparator + "patchCategory.csv";
public static final String PATCH_CATEGORY_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "PATCHCategory"
+ fileSeparator + "request";
public static final String PATCH_CATEGORY_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "PATCHCategory"
+ fileSeparator + "response";

// Benefit patch
public static final String Benefit_TEST_DATA_RESOURCES = "src" + fileSeparator + "main" + fileSeparator
+ "resources";
public final static String Benefit_REQUEST_DATASET_CSV = userDir + Benefit_TEST_DATA_RESOURCES + fileSeparator
+ "benefitplan" + fileSeparator + "Patch_Benefit.csv";
public static final String Benefit_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "patch_benefit" + fileSeparator
+ "request";
public static final String Benefit_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "patch_benefit" + fileSeparator
+ "response";
public static final String GetBenefitID_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "getbenefitplans"
+ fileSeparator + "request";
public final static String GETGenefitID_PREPARE_Request_DATASET_CSV = userDir + Benefit_TEST_DATA_RESOURCES
+ fileSeparator + "benefitplan" + fileSeparator + "GetBenefitPlans.csv";

public static final String MEMBERADDRESSFILENAME = TEST_DATA_RESOURCES + "memberaddress"
+ "MemberAddressDBMapping.properties";

// WebPortalUser Post FORGOTPASSWORD
public final static String POST_WEBPORTALUSER_FORGOTPASSWORD_DATASET_CSV = userDir + TEST_DATA_RESOURCES
+ fileSeparator + "WebUserPortal" + fileSeparator + "postWebPortalUserForgotPassword.csv";
public static final String POST_WEBPORTALUSER_FORGOTPASSWORD_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator
+ "postWebPortalUserForgotPassword" + fileSeparator + "request";

// WebPortalUser Post UpdatePassword

public final static String POST_WEBPORTALUSER_UPDATEPASSWORD_DATASET_CSV = userDir + TEST_DATA_RESOURCES
+ fileSeparator + "WebUserPortal" + fileSeparator + "postWebPortalUserUpdatePassword.csv";
public static final String POST_WEBPORTALUSER_UPDATEPASSWORD_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator
+ "postWebPortalUserUpdatePassword" + fileSeparator + "request";

// web portal user get

public final static String GET_WEBPORTALUSER_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "WebUserPortal" + fileSeparator + "getWebPortalUser.csv";
public static final String GET_WEBPORTALUSER_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "getWebPortalUser"
+ fileSeparator + "request";
public static final String GET_WEBPORTALUSER_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "getWebPortalUser"
+ fileSeparator + "response";

// Post web Portal User
public final static String WEBPORTALUSER_POSTREQUEST_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "webportaluser" + fileSeparator + "postWebPortalUser.csv";
public static final String WEBPORTALUSER_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "postwebportaluser"
+ fileSeparator + "request";

// Patch web portal user by id
public final static String PATCH_WEBPORTALUSERBYID_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "webportaluser" + fileSeparator + "patchWebPortalUserById.csv";
public static final String PATCH_WEBPORTALUSERBYID_FILES = TEST_DATA_HOME + fileSeparator + "patchwebportaluserbyid"
+ fileSeparator + "request";

// Get reason types
public final static String GET_REASONTYPES_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator + "common"
+ fileSeparator + "getReasonTypes.csv";
public static final String GET_REASONTYPES_FILES = TEST_DATA_HOME + fileSeparator + "getreasontypes" + fileSeparator
+ "request";

// Get reason
public final static String GET_REASONBYREASONTYPEID_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "common" + fileSeparator + "getReasonByReasonTypeId.csv";
public static final String GET_REASONBYREASONTYPEID_FILES = TEST_DATA_HOME + fileSeparator
+ "getreasonbyreasontypeid" + fileSeparator + "request";

// Get status types
public final static String GET_STATUSTYPES_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator + "common"
+ fileSeparator + "getStatusTypes.csv";
public static final String GET_STATUSTYPES_FILES = TEST_DATA_HOME + fileSeparator + "getstatustypes" + fileSeparator
+ "request";

// Get status by status type id
public final static String GET_STATUSBYSTATUSTYPEID_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "common" + fileSeparator + "getStatusByStatusTypeId.csv";
public static final String GET_STATUSBYSTATUSTYPEID_FILES = TEST_DATA_HOME + fileSeparator
+ "getstatusbystatustypeid" + fileSeparator + "request";

// Get Message Type Categories
public final static String GET_MESSAGETYPECATEGORIES_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "messagetypecategories" + fileSeparator + "getMessageTypeCategories.csv";
public static final String GET_MESSAGETYPECATEGORIES_FILES = TEST_DATA_HOME + fileSeparator
+ "getmessagetypecategories" + fileSeparator + "request";

// Post Message Types
public final static String POST_MESSAGETYPES_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "messagetypes" + fileSeparator + "postMessageTypes.csv";
public static final String POST_MESSAGETYPES_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "postmessagetypes"
+ fileSeparator + "request";

// Get MemberById
public final static String GET_MEMBERBYID_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator + "memberbyid"
+ fileSeparator + "getMemberById.csv";
public static final String GET_MEMBERBYID_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "getmemberbyid"
+ fileSeparator + "request";

// Get Member Address and Current Benefits
public final static String GET_MEMBERADDRESSANDCURRENTBENEFITS_DATASET_CSV = userDir + TEST_DATA_RESOURCES
+ fileSeparator + "memberaddressandcurrentbenefits" + fileSeparator
+ "getMemberAddressAndCurrentBenefits.csv";
public static final String GET_MEMBERADDRESSANDCURRENTBENEFITS_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator
+ "getmemberaddressandcurrentbenefits" + fileSeparator + "request";

// Get Common facilities

public final static String GET_COMMONS_FACILITIES_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "commons" + fileSeparator + "getCommonsfacilities.csv";
public static final String GET_COMMONS_FACILITIES_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator
+ "getCommonsfacilities" + fileSeparator + "request";
public static final String GET_COMMONS_FACILITIES_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator
+ "getCommonsfacilities" + fileSeparator + "response";

// Get Common Language

public final static String GET_COMMONS_LANGUAGE_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "commons" + fileSeparator + "getCommonsLanguage.csv";
public static final String GET_COMMONS_LANGUAGE_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator
+ "getCommonsLanguage" + fileSeparator + "request";
public static final String GET_COMMONS_LANGUAGE_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator
+ "getCommonsLanguage" + fileSeparator + "response";

// Get Common States

public final static String GET_COMMONS_STATES_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "commons" + fileSeparator + "getCommonsStates.csv";
public static final String GET_COMMONS_STATES_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "getCommonsStates"
+ fileSeparator + "request";
public static final String GET_COMMONS_STATES_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "getCommonsStates"
+ fileSeparator + "response";

// Get Common City

public final static String GET_COMMONS_CITY_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator + "commons"
+ fileSeparator + "getCityByStateCode.csv";
public static final String GET_COMMONS_CITY_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "getCityByStateCode"
+ fileSeparator + "request";
public static final String GET_COMMONS_CITY_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "getCityByStateCode"
+ fileSeparator + "response";

// Get MessageTypeCategoryByID

public final static String GET_MESSAGE_TYPE_CATEGORY_ID__DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "messageTypeCategory" + fileSeparator + "getMessageTypeCategoryByID.csv";
public static final String GET_MESSAGE_TYPE_CATEGORY_ID_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator
+ "getMessageTypeCategoryByID" + fileSeparator + "request";
public static final String GET_MESSAGE_TYPE_CATEGORY_ID_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator
+ "getMessageTypeCategoryByID" + fileSeparator + "response";

// Get MessageType

public final static String GET_MESSAGE_TYPE_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "messageTypes" + fileSeparator + "getMessageTypes.csv";
public static final String GET_MESSAGE_TYPE_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator + "getMessageTypes"
+ fileSeparator + "request";
public static final String GET_MESSAGE_TYPE_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator + "getMessageTypes"
+ fileSeparator + "response";

// Get MessageType By CategoryId

public final static String GET_MESSAGE_TYPE_ID_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "messageTypes" + fileSeparator + "getMessageTypesByCategoryID.csv";
public static final String GET_MESSAGE_TYPE_ID_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator
+ "getMessageTypesByCategoryID" + fileSeparator + "request";
public static final String GET_MESSAGE_TYPE_ID_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator
+ "getMessageTypesByCategoryID" + fileSeparator + "response";

// Get MEMBER_BENEFIT_PLAN

public final static String GET_MEMBER_BENEFIT_PLAN_DATASET_CSV = userDir + TEST_DATA_RESOURCES + fileSeparator
+ "memberBenefitPlan" + fileSeparator + "memberBenefitPlan.csv";
public static final String GET_MEMBER_BENEFIT_PLAN_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator
+ "memberBenefitPlan" + fileSeparator + "request";
public static final String GET_MEMBER_BENEFIT_PLAN_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator
+ "memberBenefitPlan" + fileSeparator + "response";
//Get Roles
public final static String GET_ROLES_REQUEST_DATASET_CSV=userDir+ TEST_DATA_RESOURCES + fileSeparator
+ "roles" + fileSeparator + "getRoles.csv";
public static final String GET_ROLES_TESTDATA_FILES = TEST_DATA_HOME + fileSeparator
+ "roles" + fileSeparator + "request";
public static final String GET_ROLES_RESPONSE_DATA = TEST_DATA_HOME + fileSeparator
+ "roles" + fileSeparator + "response";
// Queries
public static final String SELECTMEMBERADDRESSDETAILS = "select * from MemberAddresses where AddressID = ";

public static final String GETADDRESSBYMEMBERID = "select * from MemberAddresses where MemberID = ";

public static final String ORDERCREATEDDATEBYDESC = " order by CreatedDate desc";

public static final String ORDERUPDATEDDATEBYDESC = " order by UpdatedDate desc" + props.getProperty("TenantID");

public static final String GETGRIEVANCEBYID = "Select * from Grievances where GrievanceID=";

public static final String GETGRIEVANCEBYMEMBERID = "Select * from Grievances where MemberID=";

public static final String SELECTWEBPORTALUSERDETAILSBYMEMBERID = "select * from WebPortal_MemberRegistration where MemberID = ";

public static final String SELECTWEBPORTALUSERDETAILSBYID = "select * from WebPortal_MemberRegistration where Id = ";

public static final String WEBPORTALUSERORDERUPDATEDDATEBYDESC = " order by UpdatedDate desc";

public static final String SELECTSTATUS = "select * from Status where StatusID = ";

public static final String SELECTREASON = "select * from Reasons where ReasonTypeID = ";

public static final String SELECTREASONTYPES = "select * from ReasonTypes where ReasonTypeID = ";

public static final String SELECTSTATUSTYPES = "select * from StatusTypes where StatusTypeID = ";

public static final String SELECTMESSAGETYPECATEGORIES = "select * from MessageTypeCategories where MsgTypeCategoryID = ";

public static final String SELECTMESSAGETYPES = "select * from MessageTypes where MsgTypeID = ";

public InputStream getRequestPropertiesURL() {
return getClass().getResourceAsStream("/data.properties");
}

public InputStream getResponseSchemaURL() {
return getClass().getResourceAsStream("/responseSchema.json");
}

public InputStream getDBPropertiesURL() {
return getClass().getResourceAsStream("/database.properties");
}

public InputStream getCONFIGURL() {
return getClass().getResourceAsStream("/config.properties");
}

public InputStream memberAddressDBPropertiesURL() {
System.out.println("Get member address databae props: "
+ getClass().getResourceAsStream("/memberaddress/database.properties"));

return getClass().getResourceAsStream("/memberaddress/database.properties");
}

public InputStream getMemberAddressResponseSchemaURL() {
return getClass().getResourceAsStream("/memberaddress/responseSchema.json");
}

/* --------------------- Audit operation name hard code ------------------ */
public static String POSTMEMBER_AUDIT_OPERATIONNAME = "CreateMember";
public static String PATCHMEMBER_AUDIT_OPERATIONNAME = "UpdateAsync";
public static String PUTALTERNATEPHONE_AUDIT_OPERATIONNAME = "UpdateAltPhoneNumberAsync";
public static String DELETEMEMBER_AUDIT_OPERATIONNAME = "DeactivateTempMemberAsync";

public static String PATCHMEMBERNOTE_AUDIT_OPERATIONNAME = "UpdateMemberNoteAsync";
public static String POSTMEMBERNOTE_AUDIT_OPERATIONNAME = "CreateMemberNoteAsync";

public static String PATCHMEMBERADDRESS_AUDIT_OPERATIONNAME = "UpdateMemberAddressAsync";
public static String POSTMEMBERADDRESS_AUDIT_OPERATIONNAME = "CreateMemberAddressAsync";

}

