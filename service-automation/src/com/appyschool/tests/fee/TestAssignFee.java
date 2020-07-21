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
import com.appyschool.services.AssignFeeServices;
import com.appyschool.services.EventServices;
import com.appyschool.services.FeeServices;
import com.appyschool.services.StaffAttendanceServices;
import com.jayway.restassured.response.Response;	

public class TestAssignFee extends SoftAssertionBase{
	
	int n = 1 , q = 1;
	
	/**
	 * Create Assign Fee
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/csv/assignfee.csv")
	public void testCreateAssignFee(HashMap<String, String> map) throws JSONException {
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.ASSIGNFEE_CREATE_PAYLOAD);
		
		map.put("$branchid", SuiteSetupProperties.getValue("BRANCH_ID_"+n));
		map.put("$branchversion", SuiteSetupProperties.getValue("BRANCH_VERSION_"+n));
		map.put("$standardid", SuiteSetupProperties.getValue("STANDARDS_ID_"+n));
		map.put("$standardversion", SuiteSetupProperties.getValue("STANDARDS_VERSION_"+n));
		
		n++;
		
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

		System.out.println(json.toString());

		Response response = AssignFeeServices.createFee(json.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), 200);
		verifyEquals(responseObj.getString("status"), GenericConstants.SUCCESS_OK);
	}
	
	/**
	 * Delete Assign Fee
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testDeleteAssignFee() throws JSONException {
		List<String> ids= CommonMethods.createAssignFee();
		
		if (ids != null) {
			Response response = AssignFeeServices.deleteFee(ids.get(0),ids.get(1));
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			verifyEquals(response.statusCode(), 200);
			verifyEquals(responseObj.getString("message"), "Successfully deleted Assign fee details");			
		} 
		else {
			verifyTrue(false);
		}
	}

	
	/**
	 * Get All Assign Fee Details
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testGetallAssignFeeDetails() throws JSONException {
		
			Response response = AssignFeeServices.getFee(SuiteSetupProperties.getValue("BRANCH_ID_"+q),SuiteSetupProperties.getValue("STANDARDS_ID_"+q));
			q++;
			response.then().log().all();
			
			verifyEquals(response.statusCode(), 200);
	}
	
}
