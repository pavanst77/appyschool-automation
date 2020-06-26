package com.appyschool.tests.event;

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
import com.appyschool.services.EventServices;
import com.jayway.restassured.response.Response;	

public class TestEvent extends SoftAssertionBase{
	
	int o=0;
	/**
	 * Create an Event
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/csv/event.csv")
	public void testCreateEvent(HashMap<String, String> map) throws JSONException {
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.EVENT_CREATE_PAYLOAD);
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
		
		json.put("eventName", RandomStringUtils.randomAlphanumeric(10));
		json.put("description", "Creating from API automation");
		
		
		System.out.println(json.toString());
		Response response = EventServices.createEvent(json.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), 200);
		verifyEquals(responseObj.getString("status"), GenericConstants.SUCCESS_OK);
	}
	
	/**
	 * Modify Event
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/csv/event.csv")
	public void testModifyEvent(HashMap<String, String> map) throws JSONException {
		List<String> ids= CommonMethods.createEvent();
		if (ids != null) {
					
			String jsonTemplate = ConfigProperties.getValue(GenericConstants.EVENT_CREATE_PAYLOAD);
			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
			
			json.put("eventName","dEMO"+ RandomStringUtils.randomAlphanumeric(10));
			json.put("description", "Modified from API automation");
			json.put("id", ids.get(0));

			Response response = EventServices.modifyEvent(json.toString());
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			verifyEquals(response.statusCode(), 200);
			verifyEquals(response.statusCode(), Integer.parseInt(map.get("statusCode")));
			verifyEquals(responseObj.getString("message"), "Successfully Modified Event");
			verifyTrue(responseObj.get("id") != null);
		}
		else {
			verifyTrue(false);
		}
	}
	
	/**
	 * Delete Event
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testDeleteEvent() throws JSONException {
		List<String> ids= CommonMethods.createEvent();
		if (ids != null) {
			Response response = EventServices.deleteEvent(ids.get(0));
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			verifyEquals(response.statusCode(), 200);
			verifyEquals(responseObj.getString("message"), "Successfully deleted Event");
			
			response = EventServices.getEvent(ids.get(0));
			response.then().log().all();
			verifyEquals(response.statusCode(), 204); // No Content		
		} 
		else {
			verifyTrue(false);
		}
	}
	
	/**
	 * Get Event Details
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testGetEventDetails() throws JSONException {
		List<String> ids= CommonMethods.createEvent();
		if (ids != null) {
			Response response = EventServices.getEvent(ids.get(0));
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
	 * Get All Event Details
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testGetallEventDetails() throws JSONException {
		
			Response response = EventServices.getAllEvent();
			response.then().log().all();
			
			verifyEquals(response.statusCode(), 200);
	}
	
}
