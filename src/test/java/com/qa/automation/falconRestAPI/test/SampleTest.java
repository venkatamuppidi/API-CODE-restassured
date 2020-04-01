package com.qa.automation.falconRestAPI.test;

import org.testng.annotations.Test;
import static  io.restassured.RestAssured.*;

/**
 *
 * SampleTest class
 *
 */
public class SampleTest {
	
@Test
	public void testStatusCode(){
		given().
		get("http://10.10.10.231:8083/dashboard").
		then().
		statusCode(200);
	}
//its will verify code and print complete response in console //
@Test
public void testlogging() {
	given().
	get("http://10.10.10.231:8083/dashboard").
	then().
	statusCode(200).
	log().all();
		
}
//verify the key vlaue in json format//


//  @Test public void testEqualToFunction() { 
//	  given().
//  get("http://10.10.10.231:8083/dashboard/MWB"). 
//  then().
//  
//}  
//  
  
  //verifying the multiple content //
//@Test
//public void testhasItemFunction() {
//	given().
//	get("kasdl").
//	then().
//	body("",hasItem("","",""));
//	
//	
//	
//}
  }
 