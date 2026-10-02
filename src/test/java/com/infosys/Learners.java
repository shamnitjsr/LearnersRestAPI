package com.infosys;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class Learners {
	
	
	
	String id = "";
	
	@Test(priority = 1)
	public void testGetAllLearners() {
		
		System.out.println("*********** Welcome In Testing World**********");
		System.out.println("*********** Welcome In Automation Testing World**********");
		
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
			
			System.out.println("*********** Ended:Test Case: Create Learners Using External File **********");
		
		
	}
	
	@Test(priority = 4)
	public void testPutLearnersUsingExternalFile() throws FileNotFoundException {
		
        System.out.println("*********** Started:Test Case: Put Learners Using External File **********");
		
		File file = new File("D:\\ProjectWorkspace\\LearnerRestAPI\\src\\test\\java\\com\\infosys\\Updatelearners.json");
		FileReader fileReader = new FileReader(file);
		JSONTokener jsonTokener = new JSONTokener(fileReader);
		JSONObject requestBody = new JSONObject(jsonTokener);
		
		given()
			.auth().basic("api_learner", "Lebyy@Api1")
			.header("Content-Type", "application/json")
			.pathParam("id", id)
			
			.body(requestBody.toString())
		.when()
			.put("https://lebyy.com/practice/api/learners/{id}")
		.then() 
			.statusCode(200)
			.log().body()
			.extract().jsonPath().getString("id");
			System.out.println(id);
			
			System.out.println("*********** Ended:Test Case: Put Learners Using External File **********");
		
		
	}
	
	@Test(priority = 5)
	public void testPatchCoursesUsingHashMap() {
		
		HashMap<String, Object> requestBody = new HashMap<>();
		
		requestBody.put("name", "Rohan");
		requestBody.put("email", "Rohan@gmail.com");
		
		System.out.println("*********** Started:Test Case: Patch Learners Using HashMap**********");
		
		given()
		    .auth().basic("api_learner", "Lebyy@Api1")
			.header("Content-Type", "application/json")
			.pathParam("id", id)
			.body(requestBody)
		.when()
			.patch("https://lebyy.com/practice/api/learners/{id}")
		.then() 
			.log().body()
			.statusCode(200);
		
		System.out.println("*********** Ended:Test Case: Patch Learners Using HashMap **********");
		
	}
	
	@Test(priority = 6)
	public void testDeletelearnersById() {
		
		System.out.println("*********** Started:Test Case: Delete Learners Using HashMap **********");
		
		given()
			.auth().basic("api_learner", "Lebyy@Api1")
			.pathParam("id", id)
		.when()
			.delete("https://lebyy.com/practice/api/learners/{id}")
		.then() 
			.log().body()
			.statusCode(200);
		System.out.println("*********** Ended:Test Case: Delete Learners Using HashMap **********");
		
	}
	
	@Test(priority = 7)
	public void testGetAllLearnersWithoutAuth() {
		
		System.out.println("*********** Started:Test Case: Get All Learners Without Auth **********");
		
		given()
			//.auth().basic("api_learner", "Lebyy@Api1")
			
		.when()
			.get("https://lebyy.com/practice/api/learners")
		.then() 
			.log().body()
			.statusCode(401);
		System.out.println("*********** Ended:Test Get All Learners Without Auth **********");
		
	}

}
