package com.infosys;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

public class Learners {
	
	@Test(priority = 1)
	public void testGetAllCourses() {
		
		System.out.println("*********** Started:Test Case: Get All Learners **********");
		
		given()
			.auth().basic("api_learner", "Lebyy@Api1")
			
		.when()
			.get("https://lebyy.com/practice/api/learners")
		.then() 
			.log().body()
			.statusCode(200);
		System.out.println("*********** Ended:Test Case: Get All Learners **********");
		
	}

}
