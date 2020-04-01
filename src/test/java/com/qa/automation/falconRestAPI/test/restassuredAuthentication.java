package com.qa.automation.falconRestAPI.test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

import org.apache.tools.ant.taskdefs.Length.When;
import org.testng.annotations.Test;
import  io.restassured.RestAssured;

import groovy.util.logging.Log;
import  io.restassured.RestAssured;
import io.restassured.response.Response;


public class restassuredAuthentication extends baseclass {
	@Test
	public void testAuthencationPreemptive() {
		RestAssured.given().
		
		then().
		body("FaultId", equalTo("OPERATION_SUCCESS")).log();
		//RestAssured.given().then().body("FaultId",equalTo("OPERATION_SUCCESS")).log().all().statusCode(200);

				
					
					
			//System.out.println("response code "+);

		
		
	}
//@Test
public void extract() {
//String response=
		//	when().
//			get("http://restapi.demoqa.com/authentication/CheckForAuthentication").
//			then().statusCode(200).
//		
//			extract().path("Authentication Type");
//			
	//System.out.println("Content Type:"+response.contentType());
	//System.out.println("keys values"+response.jsonPath().getJsonObject("Authentication Type"));
			
			
	
	
	

	
			
	
}

}


