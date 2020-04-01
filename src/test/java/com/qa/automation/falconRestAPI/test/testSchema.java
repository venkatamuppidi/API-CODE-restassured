package com.qa.automation.falconRestAPI.test;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class testSchema {
	// verify content type
	@Test
	
	public void testContentType() {
		given().
		get("http://10.10.10.231:8083/dashboard").
		then().
		contentType(ContentType.JSON);
		//contentType(ContentType.HTML);
		
		
	}
	// verify 
	//@Test
	

}
