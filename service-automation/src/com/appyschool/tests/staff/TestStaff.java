package com.appyschool.tests.staff;

import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.DataProviderArguments;
import com.appyschool.common.GenericConstants;
import com.appyschool.common.SoftAssertionBase;
import com.appyschool.common.SuiteSetupProperties;
import com.appyschool.services.StaffServices;
import com.jayway.restassured.response.Response;

public class TestStaff extends SoftAssertionBase{

	/**
	 * Create a Staff
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/csv/staff1.csv")
	public void testCreateStaff(HashMap<String, String> map) throws JSONException {

		String jsonTemplate = ConfigProperties.getValue(GenericConstants.STAFF_CREATE_PAYLOAD);
		JSONObject object1 = new JSONObject();

		// Function to get Designation ID from SuiteSetupProperties
		object1.put("id", SuiteSetupProperties.getValue("Designation_ID"));

		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

		json.put("staffDesignation", object1);
		json.put("primaryPhoneNumber", RandomStringUtils.randomNumeric(5) + RandomStringUtils.randomNumeric(5));
		json.put("alternatePhoneNumber", RandomStringUtils.randomNumeric(5) + RandomStringUtils.randomNumeric(5));
		json.put("primaryEmailId", map.get("$firstName") + RandomStringUtils.randomNumeric(2) + "@gmail.com");

		System.out.println(json.toString());
		Response response = StaffServices.createStaff(json.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), 200);
		verifyEquals(responseObj.getString("status"), GenericConstants.SUCCESS_OK);
	}

}
