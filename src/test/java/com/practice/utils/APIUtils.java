package com.practice.utils;

import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIUtils {
	    public static Response getUserDetails(String username) {
	        Response res =  RestAssured.given()
	                .baseUri(ConfigReader.getProperty("api.url"))
	                .get("/users/" + username);
	        /*Response res =  RestAssured.given().filter(new AuthFilter())
	                .baseUri(ConfigReader.getProperty("api.url"))
	                .get("/users/" + username);*/
	        return res;
	  
	}
	    // we can write it in test class and in other class
	    public static void validateRes() {
			   Response response = APIUtils.getUserDetails("testUser");
			   SoftAssert st = new SoftAssert();
			   
			   st.assertEquals(response.jsonPath().getString("company"), "AI Corp");
	    }

}
