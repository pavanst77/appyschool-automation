package com.appyschool.tests.fee;

import java.util.HashMap;
import java.util.List;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
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
import com.appyschool.services.ConcessionTypesServices;
import com.appyschool.services.EventServices;
import com.appyschool.services.FeeServices;
import com.appyschool.services.FeeTypesServices;
import com.appyschool.services.StaffAttendanceServices;
import com.jayway.restassured.response.Response;	

public class TestConcessionTypes extends SoftAssertionBase{
	
	/**
	 * Create Concession Type
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/csv/fee.csv")
	public void testCreateConcessionTypes(HashMap<String, String> map) throws JSONException {
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.CONCESSIONTYPES_CREATE_PAYLOAD);
		
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
		json.put("concessionName", RandomStringUtils.randomAlphabetic(10));
		System.out.println(json.toString());
		Response response = ConcessionTypesServices.createConcession(json.toString());
		response.then().log().all();

		verifyEquals(response.statusCode(), 200);
	}
	
	/**
	 * Delete Concession Types
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testDeleteConcessionTypes() throws JSONException {
		List<String> ids= CommonMethods.createConcessionTypes();
		if (ids != null) {
			Response response = ConcessionTypesServices.deleteConcession(ids.get(0));
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			verifyEquals(response.statusCode(), 200);
			verifyEquals(responseObj.getString("message"), "Successfully deleted Concession Type details");			
		} 
		else {
			verifyTrue(false);
		}
	}
	
	
	/**
	 * Modify Concession Types
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testModifyConcessionTypes() throws JSONException {
		List<String> ids= CommonMethods.createConcessionTypes();
		if (ids != null) {
			HashMap<String, String> map=new HashMap<String, String>();
			String jsonTemplate = ConfigProperties.getValue(GenericConstants.CONCESSIONTYPES_CREATE_PAYLOAD);
			
			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
			json.put("concessionName", RandomStringUtils.randomAlphabetic(10));
			json.put("id", ids.get(0));
			System.out.println(json.toString());
			Response response = ConcessionTypesServices.createConcession(json.toString());
			response.then().log().all();

			verifyEquals(response.statusCode(), 200);		
		} 
		else {
			verifyTrue(false);
		}
	}
	
	/**
	 * Get All Concession Types Details
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testGetallConcessionTypesDetails() throws JSONException {
		
			Response response = ConcessionTypesServices.getAllConcession();
			response.then().log().all();
			
			verifyEquals(response.statusCode(), 200);
	}
	
}
