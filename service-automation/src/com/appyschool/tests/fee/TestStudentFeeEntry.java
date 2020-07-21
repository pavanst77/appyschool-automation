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
import com.appyschool.services.FeeSubTypesServices;
import com.appyschool.services.FeeTypesServices;
import com.appyschool.services.StaffAttendanceServices;
import com.appyschool.services.StudentFeeEntryServices;
import com.jayway.restassured.response.Response;	

public class TestStudentFeeEntry extends SoftAssertionBase{
	
	
	/**
	 * Set Student Fee Status (Paid, Not Paid, Partially Paid, Pending)
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/csv/studentfeeentrystatus.csv")
	public void testSetStudentFeeStatus(HashMap<String, String> map) throws JSONException {
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.STUDENTFEEENTRYSTATUS_CREATE_PAYLOAD);
		
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

		System.out.println(json.toString());
		Response response = StudentFeeEntryServices.createStudentFeeEntry(json.toString());
		response.then().log().all();

		verifyEquals(response.statusCode(), 200);
	}
	
	
	/**
	 * Modify Existing Student Fee Status
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/csv/studentfeeentrystatus.csv")
	public void testModifyStudentFeeStatus(HashMap<String, String> map) throws JSONException {
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.STUDENTFEEENTRYSTATUS_CREATE_PAYLOAD);
		map.put("$assignfeeid","46");
		map.put("$assignfeeversion","32");
		map.put("$status","PAID");
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

		System.out.println(json.toString());
		Response response = StudentFeeEntryServices.createStudentFeeEntry(json.toString());
		response.then().log().all();

		verifyEquals(response.statusCode(), 200);
	}
	
	
	/**
	 * Get All Student Fee Entry Details
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testGetallStudentFeeEntryDetails() throws JSONException {
		
			Response response = StudentFeeEntryServices.getAllStudentFeeEntry("1", "1");
			response.then().log().all();
			
			verifyEquals(response.statusCode(), 200);
	}
	
}
