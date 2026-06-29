package com.api.tests;

import static io.restassured.RestAssured.*;


import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.SpecUtils;

import static com.api.utils.configManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {

	@Test
	public void loginAPITest() throws IOException {
		//Rest Assured Code
		
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		given()
		.spec(SpecUtils.requestSpec(userCredentials))
		.when()
		.post("login")
		.then().spec(SpecUtils.responseSpec_OK())
		.body("message", equalTo("Success"))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));

	}
}
