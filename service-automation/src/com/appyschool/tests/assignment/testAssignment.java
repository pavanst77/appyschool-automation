package com.appyschool.tests.assignment;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.appyschool.common.CommonMethods;
import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.DataProviderArguments;
import com.appyschool.common.GenericConstants;
import com.appyschool.common.SoftAssertionBase;
import com.appyschool.services.AssignmentServices;
import com.appyschool.services.EventServices;
import com.jayway.restassured.response.Response;	

public class testAssignment extends SoftAssertionBase{

	/**
	 * Create an Assignment
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/csv/Assignment.csv")
	public void testCreateAssignment(HashMap<String, String> map) throws JSONException {
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.ASSIGNMENT_CREATE_PAYLOAD);
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
		
		json.put("assignmentTitle", RandomStringUtils.randomAlphanumeric(10));
		json.put("assignmentDescription", "Creating from API automation");	
		
		System.out.println(json.toString());
		Response response = AssignmentServices.createAssignment(json.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), 200);
		verifyEquals(responseObj.getString("status"), GenericConstants.SUCCESS_OK);
	}
	
	/**
	 * Modify Assignment
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/csv/Assignment.csv")
	public void testModifyAssignment(HashMap<String, String> map) throws JSONException {
		List<String> ids= CommonMethods.createAssignment();
		if (ids != null) {
					
			String jsonTemplate = ConfigProperties.getValue(GenericConstants.ASSIGNMENT_CREATE_PAYLOAD);
			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
			
			json.put("assignmentTitle", RandomStringUtils.randomAlphanumeric(10));
			json.put("assignmentDescription", "Modified from API automation");	
			json.put("id", ids.get(0));
			json.put("version", 1);

			Response response = AssignmentServices.modifyAssignment(json.toString());
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			verifyEquals(response.statusCode(), 200);
			verifyEquals(responseObj.getString("message"), "Successfully Modified Assignment");
			verifyTrue(responseObj.get("id") != null);
		}
		else {
			verifyTrue(false);
		}
	}
	
	/**
	 * Delete Assignment
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testDeleteAssignment() throws JSONException {
		List<String> ids= CommonMethods.createAssignment();
		if (ids != null) {
			Response response = AssignmentServices.deleteAssignment(ids.get(0));
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			verifyEquals(response.statusCode(), 200);
			verifyEquals(responseObj.getString("message"), "Successfully deleted assignment details");
			
			response = AssignmentServices.getAssignment(ids.get(0));
			response.then().log().all();
			verifyEquals(response.statusCode(), 204); // No Content		
		} 
		else {
			verifyTrue(false);
		}
	}
	
	/**
	 * Get Assignment Details
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testGetAssignmentDetail() throws JSONException {
		List<String> ids= CommonMethods.createAssignment();
		if (ids != null) {
			Response response = AssignmentServices.getAssignment(ids.get(0));
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			verifyEquals(response.statusCode(), 200);
			verifyTrue(responseObj.has("id"));
		} 
		else {
			verifyTrue(false);
		}
	}
	
	/**
	 * Get All Assignment Details
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testGetAllAssignmentDetails() throws JSONException {
		
			Response response = AssignmentServices.getAllAssignment();
			response.then().log().all();
			
			verifyEquals(response.statusCode(), 200);
	}
	
}
