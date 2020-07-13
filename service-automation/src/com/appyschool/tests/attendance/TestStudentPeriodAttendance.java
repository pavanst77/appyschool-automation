package com.appyschool.tests.attendance;

import java.util.HashMap;
import java.util.List;

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
import com.appyschool.services.StudentPeriodAttendanceServices;
import com.jayway.restassured.response.Response;	

public class TestStudentPeriodAttendance extends SoftAssertionBase{

	/**
	 * Take Student Attendance(Period)
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/csv/PeriodicStudentAttendance.csv")
	public void testStudentAttendance(HashMap<String, String> map) throws JSONException {
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.STUDENTPERIODATTENDANCE_CREATE_PAYLOAD);
	
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

		json.put("reason", RandomStringUtils.randomAlphabetic(3)+" "+RandomStringUtils.randomAlphabetic(3)+" "+RandomStringUtils.randomAlphabetic(3));
		
		JSONArray newjson= new JSONArray();
		newjson.put(json);
		System.out.println(json.toString());
		System.out.println(newjson.toString());
		Response response = StudentPeriodAttendanceServices.createStudentAttendance(newjson.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), 200);
		verifyEquals(responseObj.getString("status"), GenericConstants.SUCCESS_OK);
	}
	
	/**
	 * Delete Student Attendance(Period) 
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testDeleteStudentAttendance() throws JSONException {
		List<String> ids= CommonMethods.StudentPeriodAttendance();
		if (ids != null) {
			Response response = StudentPeriodAttendanceServices.deleteStudentAttendance(ids.get(0));
			response.then().log().all();
		
			verifyEquals(response.statusCode(), 200);

		} 
		else {
			verifyTrue(false);
		}
	}

	
	/**
	 * Get All Student Attendance(Period) Details
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testGetallStudentAttendanceDetails() throws JSONException {
		
			Response response = StudentPeriodAttendanceServices.getAllStudentAttendance();
			response.then().log().all();
			
			verifyEquals(response.statusCode(), 200);
	}
	
}
