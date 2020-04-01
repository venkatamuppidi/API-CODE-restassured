package com.qa.automation.falconRestAPI.test;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class baseclass {
	@BeforeClass
	public  void testbaseclass() {
		RestAssured.authentication=RestAssured.preemptive().basic("ToolsQA","TestPassword");
		RestAssured.baseURI="http://restapi.demoqa.com/authentication/CheckForAuthentication/";
		
	
	}

}
