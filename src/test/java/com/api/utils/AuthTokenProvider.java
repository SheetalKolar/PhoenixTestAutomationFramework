package com.api.utils;

import static io.restassured.RestAssured.*;

import static com.api.constant.Role.*;

import com.api.constant.Role;
import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {

	private AuthTokenProvider() {

	}

	public static String getToken(Role role) {
		// I want to make the request for Login API and we want to extract
		// the token and print it on the console
		UserCredentials userCredentials=null;
		if(role == FD) 
		{
			userCredentials = new UserCredentials("iamfd", "password");
		}
		else if(role == SUP) 
		{
			userCredentials = new UserCredentials("iamsup", "password");
		}
		else if(role == ENG)
		{
			userCredentials = new UserCredentials("iameng", "password");
		}
		else if(role == QC)
		{
			userCredentials = new UserCredentials("iamqc", "password");
		}

		String token = given().baseUri(configManager.getProperty("BASE_URI")).contentType(ContentType.JSON)
				.body(userCredentials).when().post("login").then().log().ifValidationFails()
				.statusCode(200)
				.extract().body().jsonPath().getString("data.token");
		return token;
	}

}
