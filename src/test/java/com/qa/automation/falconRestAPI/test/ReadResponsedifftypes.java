package com.qa.automation.falconRestAPI.test;

import org.testng.annotations.Test;
import  io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.io.InputStream;

public class ReadResponsedifftypes {

//	To get all response as String
	
//	@Test
	public void  testGetResponseAsString() {
	String responseAsString=get("http://10.10.10.231:8083/dashboard").asString();
	System.out.println("MYResponseres \r\n"+responseAsString);	
	}
//To get all response as Inputstream
//@Test

public void getallResponseInputstream() throws IOException {
	InputStream stream=get("http://10.10.10.231:8083/dashboard").asInputStream();
	System.out.println("stream lenght"+stream.toString().length());
	stream.close();
}
//Extract details using path
//@Test
public void ExctractDetailsUsingPath() {
	String response=
			when().get("http://restapi.demoqa.com/authentication/CheckForAuthentication/").
			then().
			contentType(ContentType.JSON).
			body("products[1].totalFail",equalTo(1)).toString();
	//extract().path("productName")
	System.out.println(response);
}

//Extract details using path  in one line

//@Test
public void ExtractDeatilsUsingoneline() {
	String href1=get("http://10.10.10.231:8083/dashboard").andReturn().jsonPath().getString("products[2]");
	System.out.println("Array\n"+href1);
}
//Extract details as Response for further use
//@Test
public void ExtractDetailsUsingResponse() {
	Response response=
			when().
			get("http://10.10.10.231:8083/dashboard").
			then().
			extract().
			response();
	System.out.println("Content Type:"+response.contentType());
	System.out.println("keys values"+response.jsonPath().getString("products[0].productName"));
	
	
}




}

