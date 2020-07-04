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
import com.jayway.restassured.response.Response;	

public class TestStaffAttendance extends SoftAssertionBase{

	/**
	 * Take Staff Attendance
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/csv/StaffAttendance.csv")
	public void testStaffAttendance(HashMap<String, String> map) throws JSONException {
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.STAFFATTENDANCE_CREATE_PAYLOAD);
		JSONObject object1 = new JSONObject();
		
		object1.put("id", SuiteSetupProperties.getValue("Staff_ID"));

		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

		json.put("staffDetails", object1);
		
		JSONArray newjson= new JSONArray();
		newjson.put(json);
		System.out.println(json.toString());
		System.out.println(newjson.toString());
		Response response = StaffAttendanceServices.createStaffAttendance(newjson.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), 200);
		verifyEquals(responseObj.getString("status"), GenericConstants.SUCCESS_OK);
	}
	
	/**
	 * Delete Staff Attendance
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testDeleteStaffAttendance() throws JSONException {
		List<String> ids= CommonMethods.StaffAttendance();
		if (ids != null) {
			Response response = StaffAttendanceServices.deleteStaffAttendance(ids.get(0));
			response.then().log().all();
		
			verifyEquals(response.statusCode(), 200);
			} 
		else {
			verifyTrue(false);
		}
	}
	
	
	/**
	 * Get All Staff Attendance Details
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testGetallStaffAttendanceDetails() throws JSONException {
		
			Response response = StaffAttendanceServices.getAllStaffAttendance();
			response.then().log().all();
			
			verifyEquals(response.statusCode(), 200);
	}
	
}
