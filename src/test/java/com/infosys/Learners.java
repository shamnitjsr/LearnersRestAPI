package com.infosys;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class Learners {
	
	String id = "";
	
	@Test(priority = 1)
	public void testGetAllLearners() {
		
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
	
	@Test(priority = 2)
	public void testGetLearnersById() {
		
		System.out.println("*********** Started:Test Case: Get Learners By ID **********");
		
		given()
			.auth().basic("api_learner", "Lebyy@Api1")
			.pathParam("id", 1)
			
		.when()
			.get("https://lebyy.com/practice/api/learners/{id}")
		.then() 
			.log().body()
			.statusCode(200);
		System.out.println("*********** Ended:Test Case: Get Learners By ID **********");
		
	}
	
	@Test(priority = 3)
	public void testCreateLearnerssUsingExternalFile() throws FileNotFoundException {
		
		System.out.println("*********** Started:Test Case: Create Learners Using External File **********");
		
		File file = new File("D:\\ProjectWorkspace\\LearnerRestAPI\\src\\test\\java\\com\\infosys\\learners.json");
		FileReader fileReader = new FileReader(file);
		JSONTokener jsonTokener = new JSONTokener(fileReader);
		JSONObject requestBody = new JSONObject(jsonTokener);
		
		id = given()
			.auth().basic("api_learner", "Lebyy@Api1")
			.header("Content-Type", "application/json")
			.body(requestBody.toString())
		.when()
			.post("https://lebyy.com/practice/api/learners")
		.then() 
			.statusCode(201)
			.log().body()
			.extract().jsonPath().getString("id");
			System.out.println(id);
			
			System.out.println("*********** Started:Test Case: Create Learners Using External File **********");
		
		
	}

}
