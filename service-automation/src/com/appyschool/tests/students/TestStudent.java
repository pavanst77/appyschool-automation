package com.appyschool.tests.students;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
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
import com.appyschool.services.StudentServices;
import com.jayway.restassured.response.Response;

public class TestStudent extends SoftAssertionBase{
	int n=0;
	
	/**
	 * Create a Students
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile" )
	@DataProviderArguments("./testData/configuration/student.csv")
	
	public void testCreateStudents(HashMap<String, String> map) throws JSONException {
		n++;
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.STUDENT_CREATE_PAYLOAD);
		
		
		// Function to get BranchStandardSections ID from SuiteSetupProperties
		Map<String, String> branchStandardIds = CommonMethods.getBranchStandardFromMapBranchStandardSections(SuiteSetupProperties.getValue("BRANCH_ID_"+n));
		
		map.put("$branchId", SuiteSetupProperties.getValue("BRANCH_ID_"+n));
		map.put("$branchStandardSection", branchStandardIds.get("mapId"));
		map.put("$standard", branchStandardIds.get("standardId"));
			
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
		
		json.put("idNumber", "Appy" + RandomStringUtils.randomNumeric(5));
		json.put("rollNumber", RandomStringUtils.randomNumeric(5));
		json.put("primaryPhoneNumber", RandomStringUtils.randomNumeric(10));
		json.put("alternatePhoneNumber", RandomStringUtils.randomNumeric(10));
		json.put("primaryEmailId", map.get("$firstName") + RandomStringUtils.randomNumeric(2) + "@appyschool.com");
		json.put("secondaryEmailId", map.get("$firstName") + RandomStringUtils.randomNumeric(2) + "@gmail.com");
		
		Response response = StudentServices.createStudent(json.toString());
		response.then().log().all();
		
		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), 200);
		verifyEquals(responseObj.getString("status"), GenericConstants.SUCCESS_OK);
	}	
	}