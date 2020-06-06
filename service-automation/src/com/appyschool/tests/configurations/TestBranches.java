package com.appyschool.tests.configurations;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.appyschool.common.CommonMethods;
import com.appyschool.common.CommonUtils;
import com.appyschool.common.DataProviderArguments;
import com.appyschool.common.SoftAssertionBase;
import com.appyschool.services.BranchServices;
import com.jayway.restassured.response.Response;

public class TestBranches extends SoftAssertionBase {

	/**
	 * Create Branches
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/configuration/branches/csv/createBranch.csv")
	public void testCreateBranch(HashMap<String, String> map) throws JSONException {
		String jsonTemplate = CommonUtils.getJsonTemplate("./testData/configuration/branches/json/createBranch.json");
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
		json.put("name", RandomStringUtils.randomAlphanumeric(10));
		
		Response response = BranchServices.createBranch(json.toString());
		response.then().log().all();
		
		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), Integer.parseInt(map.get("statusCode")));
		verifyTrue(responseObj.get("id") != null);
	}
	
	/**
	 * Modify Branches
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/configuration/branches/csv/modifyBranch.csv")
	public void testModifyBranch(HashMap<String, String> map) throws JSONException {
		List<String> ids= CommonMethods.createBranch();
		if (ids != null) {
			map.put("$id", ids.get(0));
			map.put("$addressId", ids.get(1));
			String jsonTemplate = CommonUtils.getJsonTemplate("./testData/configuration/branches/json/modifyBranch.json");
			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
			json.put("name", RandomStringUtils.randomAlphanumeric(10));
			json.put("version", 0);
			
			Response response = BranchServices.modifyBranch(json.toString());
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			verifyEquals(response.statusCode(), Integer.parseInt(map.get("statusCode")));
			verifyTrue(responseObj.get("id") != null);
		} else {
			verifyTrue(false);
		}
	}
	
	/**
	 * Delete Branches
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testDeleteBranch() throws JSONException {
		List<String> ids= CommonMethods.createBranch();
		if (ids != null) {
			Response response = BranchServices.deleteBranch(ids.get(0));
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			verifyEquals(response.statusCode(), 200);
			verifyEquals(responseObj.getString("message"), "Successfully deleted branch");
			
			response = BranchServices.getBranch(ids.get(0));
			response.then().log().all();
			verifyEquals(response.statusCode(), 204); // No Content
		} else {
			verifyTrue(false);
		}
	}
	
	/**
	 * Get Branch Details
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test()
	public void testGetBranchDetails() throws JSONException {
		List<String> ids= CommonMethods.createBranch();
		if (ids != null) {
			Response response = BranchServices.getBranch(ids.get(0));
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			verifyEquals(response.statusCode(), 200);
			verifyTrue(responseObj.has("id"));
			verifyTrue(responseObj.has("branchAddressDetails"));
		} else {
			verifyTrue(false);
		}
	}
}
