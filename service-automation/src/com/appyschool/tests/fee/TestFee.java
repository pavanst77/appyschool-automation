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
import com.appyschool.services.EventServices;
import com.appyschool.services.FeeServices;
import com.appyschool.services.StaffAttendanceServices;
import com.jayway.restassured.response.Response;	

public class TestFee extends SoftAssertionBase{
	
	int n = 1;
	
	/**
	 * Create Fee
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/csv/fee.csv")
	public void testCreateFee(HashMap<String, String> map) throws JSONException {
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.FEE_CREATE_PAYLOAD);
		
		map.put("$branchid", SuiteSetupProperties.getValue("BRANCH_ID_"+n));
		map.put("$branchversion", SuiteSetupProperties.getValue("BRANCH_VERSION_"+n));
		map.put("$standardid", SuiteSetupProperties.getValue("STANDARDS_ID_"+n));
		map.put("$standardversion", SuiteSetupProperties.getValue("STANDARDS_VERSION_"+n));
		
		n++;
		
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
		json.put("totalAmount", RandomStringUtils.randomNumeric(10));
		JSONArray newjson= new JSONArray();
		newjson.put(json);
		System.out.println(json.toString());
		System.out.println(newjson.toString());
		Response response = FeeServices.createFee(newjson.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), 200);
		verifyEquals(responseObj.getString("status"), GenericConstants.SUCCESS_OK);
	}
	
	/**
	 * Delete Fee
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testDeleteFee() throws JSONException {
		List<Integer> idss= CommonMethods.createFee();
		
		// Converting the List of Integer ids into List of Strings
		List<String> ids = idss.stream()
		         .map(s -> String.valueOf(s))
		         .collect(Collectors.toList());
		
		if (ids != null) {
			Response response = FeeServices.deleteFee(ids.get(0));
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			verifyEquals(response.statusCode(), 200);
			verifyEquals(responseObj.getString("message"), "Successfully deleted fee details");			
		} 
		else {
			verifyTrue(false);
		}
	}

	/**
	 * Get a Fee Details
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testGetFeeDetails() throws JSONException {
		List<Integer> idss=CommonMethods.createFee();
		
		// Converting the List of Integer ids into List of Strings
		List<String> ids = idss.stream()
				         .map(s -> String.valueOf(s))
				         .collect(Collectors.toList());
				
		if (ids != null) {
			Response response = FeeServices.getFee(ids.get(0));
			response.then().log().all();
			
			verifyEquals(response.statusCode(), 200);
		} 
		else {
			verifyTrue(false);
		}
	}
	
	/**
	 * Get All Fee Details
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testGetallFeeDetails() throws JSONException {
		
			Response response = FeeServices.getAllFee();
			response.then().log().all();
			
			verifyEquals(response.statusCode(), 200);
	}
	
}
