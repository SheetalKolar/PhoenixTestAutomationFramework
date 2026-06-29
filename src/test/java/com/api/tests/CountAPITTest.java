package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.utils.SpecUtils;

import static com.api.constant.Role.*;
import static com.api.utils.AuthTokenProvider.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static com.api.utils.configManager.*;

import static io.restassured.RestAssured.*;

public class CountAPITTest {

	@Test
	public void verifyCountAPIResponse() {
		given()
		.spec(SpecUtils.requestSpecWithAuth(FD))
		.when()
		.get("/dashboard/count")
		.then()
		.spec(SpecUtils.responseSpec_OK())
		.body("message",equalTo("Success"))
		.body("data", notNullValue())
		.body("data.size()",equalTo(3))
		.body("data.count",everyItem(greaterThanOrEqualTo(0)))
		.body("data.label",everyItem(not(blankOrNullString())))
		.body("data.key", containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
		.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json")); 
		
	}
	
	@Test
	public void countAPITest_MissingAuthToken() {
		given()
		.spec(SpecUtils.requestSpec())
		.when()
		.get("/dashboard/count")
		.then()
		.spec(SpecUtils.responseSpec_TEXT(401));
	}
}
