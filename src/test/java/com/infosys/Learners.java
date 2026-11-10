package com.infosys;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class Learners {
	//Shambhu Kumar-10112026-1st
	//Shambhu Kumar-10112026-2st
	
	
	
	String id = "";
	
	@Test(priority = 1 , groups={"TC001_GetAllLearners","regression","sanity"})
	public void testGetAllLearners() {
		
		System.out.println("*********** Welcome In Testing World**********");
		System.out.println("*********** Welcome In Automation Testing World**********");
		
		System.out.println("*********** Started:Test Case: Get All Learners **********");
		
		given()
			.log().body()
			.auth().basic("api_learner", "Lebyy@Api1")
			
		.when()
			.get("https://lebyy.com/practice/api/learners")
		.then() 
			.log().body()
			.statusCode(200);
		System.out.println("*********** Ended:Test Case: Get All Learners **********");
		
	}
	
	
	
	@Test(priority = 2)
	public void testCreateLearnerssUsingExternalFile() throws FileNotFoundException {
		
		System.out.println("*********** Started:Test Case: Create Learners Using External File **********");
		
		//File file = new File("D:\\ProjectWorkspace\\LearnerRestAPI\\src\\test\\java\\com\\infosys\\learners.json");
		//FileReader fileReader = new FileReader("C:\\Users\\SHAMBHU KUMAR\\git\\LearnersRestAPI\\src\\test\\java\\com\\infosys\\learners.json");
		
		FileReader fileReader = new FileReader("src/test/resources/learners.json");
		
		//FileReader fileReader = new FileReader(file);
		
		JSONTokener jsonTokener = new JSONTokener(fileReader);
		JSONObject requestBody = new JSONObject(jsonTokener);
		
		id = given()
			.log().all()
			.auth().basic("api_learner", "Lebyy@Api1")
			.header("Content-Type", "application/json")
			.body(requestBody.toString())
			
		.when()
			.post("https://lebyy.com/practice/api/learners")
		.then() 
		    .log().all()
			.statusCode(201)
			.log().body()
			.extract().jsonPath().getString("id");
			System.out.println(id);
			
			System.out.println("*********** Ended:Test Case: Create Learners Using External File **********");
		
		
	}
	
	
	@Test(priority = 3)
	public void testGetAllLearnersAfterNewCreation() {
		
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
	
	@Test(priority = 4)
	public void testGetLearnersById() {
		
		System.out.println("*********** Started:Test Case: Get Learners By ID **********");
		
		given()
			.auth().basic("api_learner", "Lebyy@Api1")
			.pathParam("id", id)
			
		.when()
			.get("https://lebyy.com/practice/api/learners/{id}")
		.then() 
			.log().body()
			.statusCode(200);
		System.out.println("*********** Ended:Test Case: Get Learners By ID **********");
		
	}
	
	@Test(priority = 5)
	public void testPutLearnersUsingExternalFile() throws FileNotFoundException {
		
        System.out.println("*********** Started:Test Case: Put Learners Using External File **********");
		
		//File file = new File("C:\\Users\\SHAMBHU KUMAR\\git\\LearnersRestAPI\\src\\test\\java\\com\\infosys\\Updatelearners.json");
        
        FileReader file = new FileReader("src/test/resources/Updatelearners.json");
		
		
		//FileReader fileReader = new FileReader(file);
		JSONTokener jsonTokener = new JSONTokener(file);
		JSONObject requestBody = new JSONObject(jsonTokener);
		
		given()
			.log().all()
			.auth().basic("api_learner", "Lebyy@Api1")
			.header("Content-Type", "application/json")
			.pathParam("id", id)
			
			.body(requestBody.toString())
		.when()
			.put("https://lebyy.com/practice/api/learners/{id}")
		.then() 
			.log().all()
			.statusCode(200)
			.log().body()
			.extract().jsonPath().getString("id");
			System.out.println(id);
			
			System.out.println("*********** Ended:Test Case: Put Learners Using External File **********");
		
		
	}
	
	@Test(priority = 6)
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
	
	@Test(priority = 7)
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
	
	@Test(priority = 8)
	public void testGetAllLearnersWithoutAuth() {
		
		System.out.println("*********** Started:Test Case: Get All Learners Without Auth **********");
		
		given()
			.log().body()
			//.auth().basic("api_learner", "Lebyy@Api1")
			
		.when()
			.get("https://lebyy.com/practice/api/learners")
		.then() 
			.log().body()
			.statusCode(401);
		System.out.println("*********** Ended:Test Get All Learners Without Auth **********");
		
	}

}
