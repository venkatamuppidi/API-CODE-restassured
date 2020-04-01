package com.qa.automation.falconRestAPI.test;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SettingRoot {
//calling the single value 
@Test
public void testWithoutRoot() {
	given().
	get("http://10.10.10.231:8083/dashboard").
	then().
	body("products[10].category", equalTo("Mobile")).log();
	
}
//calling multiple values with rootSetting
//@Test
public void testWithRoot() {
	given().
	get("http://10.10.10.231:8083/dashboard").
	then().
	root("products[0]").
	body("productName",equalTo("UI WEB TEST RESULT")).

	body("totalTestcases",equalTo(4)).and().
	body("totalFail",equalTo(1)).and().
	body("totalPass",equalTo(3)).
	log().body();
	
	
}
//detaching the root 
//@Test
public void testDetachRoot() {
	given().
	get("http://10.10.10.231:8083/dashboard").
	then().
	root("products[0]").
	detachRoot("products[0]").
	body("products[1].totalPass",equalTo(9));
}
//checking the header in post api
//@Test
public void testPostReq() {
	given().
	header("Appkey", "key-value").
	param("fgsd","dasd").
	when().
	post("http://10.10.10.231:8083/dashboard").then().statusCode(200).log().all();
}


}
