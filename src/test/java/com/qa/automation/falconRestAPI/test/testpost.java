package com.qa.automation.falconRestAPI.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class testpost {
@Test
public void testPost() {
	RequestSpecification request= RestAssured.given();
	request.header("Content-Type","application/json");
	JSONObject json =new JSONObject();
	json.put("productName", "testing");
		/*
		 * json.put("totalTestcases", "24"); json.put("totalFail", "8");
		 * json.put("totalPass", "9");
		 */
	request.body(json.toJSONString());
	Response response=request.post("http://10.10.10.231:8083/dashboard");
	int code=response.getStatusCode();
	Assert.assertEquals(code,201);
	
}


}
