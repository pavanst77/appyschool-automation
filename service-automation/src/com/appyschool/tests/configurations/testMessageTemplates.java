package com.appyschool.tests.configurations;

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
import com.appyschool.services.MessageTemplatesServices;
import com.jayway.restassured.response.Response;	

public class testMessageTemplates extends SoftAssertionBase{

	/**
	 * Create Message Templates
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/csv/MessageTemplates.csv")
	public void testMessageTemplate(HashMap<String, String> map) throws JSONException {
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.MESSAGETEMPLATES_CREATE_PAYLOAD);
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
		
		json.put("templateMessage","Creating from API automation");
		json.put("templateTitle","Creation Testing "+ RandomStringUtils.randomAlphanumeric(2));

		System.out.println(json.toString());
		Response response = MessageTemplatesServices.createMessageTemplates(json.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), 200);
		verifyEquals(responseObj.getString("status"), GenericConstants.SUCCESS_OK);
	}
	
	/**
	 * Modify Message Templates
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/csv/MessageTemplates.csv")
	public void testmodifyMessageTemplates(HashMap<String, String> map) throws JSONException {
		List<String> ids= CommonMethods.createMessageTemplates();
		if (ids != null) {
					
			String jsonTemplate = ConfigProperties.getValue(GenericConstants.MESSAGETEMPLATES_CREATE_PAYLOAD);
			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
			
			json.put("templateMessage","Modified from API automation");
			json.put("messageTemplateType","FEEREMINDER");
			json.put("id", ids.get(0));

			Response response = MessageTemplatesServices.modifyMessageTemplates(json.toString());
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
	 * Delete Message Templates
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testDeleteMessageTemplates() throws JSONException {
		List<String> ids= CommonMethods.createMessageTemplates();
		if (ids != null) {
			Response response = MessageTemplatesServices.deleteMessageTemplates(ids.get(0));
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			verifyEquals(response.statusCode(), 200);
			verifyEquals(responseObj.getString("message"), "Successfully deleted Message Templates details");
			
			response = MessageTemplatesServices.getMessageTemplates(ids.get(0));
			response.then().log().all();
			verifyEquals(response.statusCode(), 204); // No Content		
		} 
		else {
			verifyTrue(false);
		}
	}
	
		
	/**
	 * Get All Message Templates Details
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testGetAllMessageTemplatesDetails() throws JSONException {
		
			Response response = MessageTemplatesServices.getAllMessageTemplates();
			response.then().log().all();
			
			verifyEquals(response.statusCode(), 200);
	}
	
}
