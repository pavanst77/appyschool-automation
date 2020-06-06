package com.appyschool.tests.staff;

import java.util.HashMap;


import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.DataProviderArguments;
import com.appyschool.services.BranchServices;
import com.jayway.restassured.response.Response;

public class TestStaff {
	
	/**
	 * Create a Staff
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/csv/staff.csv")
	public void testCreateStaff(HashMap<String, String> map)
			throws JSONException {
		String jsonTemplate = CommonUtils
				.getJsonTemplate("./testData/json/straff1.json");
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
		json.put("primaryPhoneNumber", RandomStringUtils.randomNumeric(5)
				+ RandomStringUtils.randomNumeric(5));
		json.put("alternatePhoneNumber", RandomStringUtils.randomNumeric(5)
				+ RandomStringUtils.randomNumeric(5));
		json.put("primaryEmailId",
				map.get("$firstName") + RandomStringUtils.randomNumeric(2)
						+ "@eims.com");
		json.put("secondaryEmailId",
				map.get("$firstName") + RandomStringUtils.randomNumeric(2)
						+ "@eims.com");
		
		// Assert.assertEquals(response.statusCode(), 200);
	}
}
