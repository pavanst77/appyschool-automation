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
import com.appyschool.services.FeeSummaryServices;
import com.appyschool.services.FeeTypesServices;
import com.appyschool.services.StaffAttendanceServices;
import com.jayway.restassured.response.Response;	

public class TestFeeSummary extends SoftAssertionBase{
	

	/**
	 * Get All Fee Summary (Standard Wise)
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testGetallFeeDetailsStandard() throws JSONException {
		
			Response response = FeeSummaryServices.getAllFeeStandard("1");
			response.then().log().all();
			
			verifyEquals(response.statusCode(), 200);
	}
	
	/**
	 * Get All Fee Summary (Fee Detail Wise)
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testGetallFeeDetails() throws JSONException {
		
			Response response = FeeSummaryServices.getAllFee("1");
			response.then().log().all();
			
			verifyEquals(response.statusCode(), 200);
	}
	
}
