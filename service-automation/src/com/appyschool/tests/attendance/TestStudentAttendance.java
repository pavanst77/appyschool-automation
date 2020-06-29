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
import com.appyschool.common.SuiteSetupProperties;
import com.appyschool.services.EventServices;
import com.appyschool.services.StaffAttendanceServices;
import com.appyschool.services.StudentAttendanceServices;
import com.jayway.restassured.response.Response;	

public class TestStudentAttendance extends SoftAssertionBase{

	/**
	 * Take Student Attendance(Day)
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/csv/StudentAttendance.csv")
	public void testStudentAttendance(HashMap<String, String> map) throws JSONException {
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.STUDENTATTENDANCE_CREATE_PAYLOAD);
		JSONObject object1 = new JSONObject();
		
		object1.put("id", "6732094942523778916");

		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

		json.put("studentDetails", object1);
		
		JSONArray newjson= new JSONArray();
		newjson.put(json);
		System.out.println(json.toString());
		System.out.println(newjson.toString());
		Response response = StudentAttendanceServices.createStudentAttendance(newjson.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), 200);
		verifyEquals(responseObj.getString("status"), GenericConstants.SUCCESS_OK);
	}
	
	/**
	 * Delete Student Attendance(Day) 
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testDeleteStudentAttendance() throws JSONException {
		List<String> ids= CommonMethods.StudentAttendance();
		if (ids != null) {
			Response response = StudentAttendanceServices.deleteStudentAttendance(ids.get(0));
			response.then().log().all();
		
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			verifyEquals(response.statusCode(), 200);
			verifyEquals(responseObj.getString("message"), "Successfully deleted Student attendance");
			
			response = StudentAttendanceServices.getStudentAttendance(ids.get(0));
			response.then().log().all();
			verifyEquals(response.statusCode(), 204); // No Content		
		} 
		else {
			verifyTrue(false);
		}
	}
	
	/**
	 * Get Student Attendance(Day) 
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testGetStudentDetails() throws JSONException {
		List<String> ids= CommonMethods.StudentAttendance();
		if (ids != null) {
			Response response = StudentAttendanceServices.getStudentAttendance(ids.get(0));
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
	 * Get All Student Attendance(Day) Details
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testGetallStudentAttendanceDetails() throws JSONException {
		
			Response response = StudentAttendanceServices.getAllStudentAttendance();
			response.then().log().all();
			
			verifyEquals(response.statusCode(), 200);
	}
	
}
