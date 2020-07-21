package com.appyschool.tests.food;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.appyschool.common.SuiteSetupProperties;
import com.appyschool.services.EventServices;
import com.appyschool.services.FoodServices;
import com.jayway.restassured.response.Response;	

public class TestFood extends SoftAssertionBase{

	/**
	 * Create a Food
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/csv/food.csv")
	public void testCreateFood(HashMap<String, String> map) throws JSONException {
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.FOOD_CREATE_PAYLOAD);
		
		Map<String, String> branchStandardIds = CommonMethods.getBranchStandardFromMapBranchStandardSections(SuiteSetupProperties.getValue("BRANCH_ID_"+1));
		
		map.put("$branchStandardSectionsid", branchStandardIds.get("mapId"));
		
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
	
		json.put("title", RandomStringUtils.randomAlphabetic(10));
		json.put("description", "Creating from API automation");
		
		
		System.out.println(json.toString());
		Response response = FoodServices.createFood(json.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), 200);
		verifyEquals(responseObj.getString("status"), GenericConstants.SUCCESS_OK);
	}
	
	/**
	 * Modify Food
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/csv/food.csv")
	public void testModifyFood(HashMap<String, String> map) throws JSONException {
		List<String> ids= CommonMethods.createFood();
		if (ids != null) {
					
			String jsonTemplate = ConfigProperties.getValue(GenericConstants.FOOD_MODIFY_PAYLOAD);
			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
			
			json.put("title","Modified "+ RandomStringUtils.randomAlphanumeric(5));
			json.put("description", "Modified from API automation");
			json.put("id", ids.get(0));

			Response response = FoodServices.modifyFood(json.toString());
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			verifyEquals(response.statusCode(), 200);
			verifyEquals(responseObj.getString("message"), "Successfully Modified Food");
			verifyTrue(responseObj.get("id") != null);
		}
		else {
			verifyTrue(false);
		}
	}
	
	/**
	 * Delete Food
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testDeleteFood() throws JSONException {
		List<String> ids= CommonMethods.createFood();
		if (ids != null) {
			Response response = FoodServices.deleteFood(ids.get(0));
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			verifyEquals(response.statusCode(), 200);
			verifyEquals(responseObj.getString("message"), "Successfully deleted Food");
			
			response = FoodServices.getFood(ids.get(0));
			response.then().log().all();
			verifyEquals(response.statusCode(), 204); // No Content		
		} 
		else {
			verifyTrue(false);
		}
	}
	
	/**
	 * Get a Food Details
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testGetFoodDetails() throws JSONException {
		List<String> ids= CommonMethods.createFood();
		if (ids != null) {
			Response response = FoodServices.getFood(ids.get(0));
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
	 * Get All Food Details
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testGetallFoodDetails() throws JSONException {
		
			Response response = FoodServices.getAllFood();
			response.then().log().all();
			
			verifyEquals(response.statusCode(), 200);
	}
	
	/**
	 * Send Mail
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testSendMail() throws JSONException {
		List<String> ids= CommonMethods.createFood();
		if (ids != null) {
			Response response = FoodServices.sendNotification(ids.get(0),"mail");
			response.then().log().all();
			
			verifyEquals(response.statusCode(), 200);
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			verifyEquals(response.statusCode(), 200);
			verifyEquals(responseObj.getString("message"), "Mail sent successfully");
		}
	}
	
	/**
	 * Send App Notification
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testSendApp() throws JSONException {
		List<String> ids= CommonMethods.createFood();
		if (ids != null) {
			Response response = FoodServices.sendNotification(ids.get(0),"app");
			response.then().log().all();
			
			verifyEquals(response.statusCode(), 200);
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			verifyEquals(response.statusCode(), 200);
			verifyEquals(responseObj.getString("message"), "App Notification sent successfully");
		}
	}
}
