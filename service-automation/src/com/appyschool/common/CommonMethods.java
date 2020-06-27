package com.appyschool.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.appyschool.services.AdhocServices;
import com.appyschool.services.AssignmentServices;
import com.appyschool.services.BranchServices;
import com.appyschool.services.EventServices;
import com.appyschool.services.SectionServices;
import com.appyschool.services.StandardServices;
import com.appyschool.services.SubjectServices;
import com.jayway.restassured.response.Response;

public class CommonMethods {
	private static final Logger LOG = LogManager.getLogger(CommonMethods.class);

	
	/**
	 * Creates branch and returns id of that
	 * 
	 * @return
	 */
	public static List<String> createBranch() {
		List<String> list = null;
		try {
			HashMap<String, String>  map = CommonUtils.getDataFromFile("./testData/configuration/branches/csv/createBranch.csv");
			String jsonTemplate = CommonUtils.getJsonTemplate("./testData/configuration/branches/json/createBranch.json");
			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
			json.put("name", RandomStringUtils.randomAlphanumeric(10));
			
			Response response = BranchServices.createBranch(json.toString());
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			if (response.statusCode() == 200) {
				list = new ArrayList<String>();
				list.add(responseObj.get("id").toString());
				list.add(responseObj.getJSONObject("branchAddressDetails").get("id").toString());
				return list;
			} 
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured while reading");
		}
		return list;
	}
	
	/**
	 * Creates Events and returns id of that
	 * 
	 * @return
	 */
	public static List<String> createEvent() {
		List<String> list = null;
		try {
			HashMap<String, String>  map = CommonUtils.getDataFromFile("./testData/csv/event.csv");
			String jsonTemplate = ConfigProperties.getValue(GenericConstants.EVENT_CREATE_PAYLOAD);
			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

			json.put("eventName", RandomStringUtils.randomAlphanumeric(10));
			json.put("description", "Creating from API automation");

			Response response = EventServices.createEvent(json.toString());
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			if (response.statusCode() == 200) {
				list = new ArrayList<String>();
				list.add(responseObj.get("id").toString());
				return list;
			} 
		} 
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured while reading");
		}
		return list;
	}
	
	/**
	 * Creates Assignment and returns id of that
	 * 
	 * @return
	 */
	public static List<String> createAssignment() {
		List<String> list = null;
		try {
			HashMap<String, String>  map = CommonUtils.getDataFromFile("./testData/csv/Assignment.csv");
			String jsonTemplate = ConfigProperties.getValue(GenericConstants.ASSIGNMENT_CREATE_PAYLOAD);
			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

			json.put("assignmentTitle", RandomStringUtils.randomAlphanumeric(10));
			json.put("assignmentDescription", "Creating from Common Methods");

			Response response = AssignmentServices.createAssignment(json.toString());
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			if (response.statusCode() == 200) {
				list = new ArrayList<String>();
				list.add(responseObj.get("id").toString());
				return list;
			} 
		} 
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured while reading");
		}
		return list;
	}
	
	/**
	 * Creates Subject and returns Json payload
	 * 
	 * @return
	 */
	public static JSONObject createSubject() {
		JSONObject responseObj = null;
		try {
			HashMap<String, String> map = CommonUtils
					.getDataFromFile("./testData/configuration/subjects/csv/createSubject.csv");
			String jsonTemplate = CommonUtils
					.getJsonTemplate("./testData/configuration/subjects/json/createSubject.json");
			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
			json.put("name", RandomStringUtils.randomAlphanumeric(10));

			Response response = SubjectServices.createSubject(json.toString());
			response.then().log().all();

			if (response.statusCode() == 200) {
				responseObj = new JSONObject(response.getBody().asString());
				return responseObj;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured while reading");
		}
		return responseObj;
	}
	
	/**
	 * Get Branch current version by using Id
	 * 
	 * @param id
	 * @return
	 */
	public static int getBranchVersion(String id) {
		Response response = BranchServices.getBranch(id);
		response.then().log().all();
		JSONObject responseObj = new JSONObject(response.getBody().asString());
		return responseObj.getInt("version");
	}
	
	/**
	 * Get Standard current version by using id
	 * 
	 * @param id
	 * @return
	 */
	public static int getStandardVersion(String id) {
		Response response = StandardServices.getStandard(id);
		response.then().log().all();
		JSONObject responseObj = new JSONObject(response.getBody().asString());
		return responseObj.getInt("version");	
	}
	
	/**
	 * Get Section current version by using Id
	 * 
	 * @param id
	 * @return
	 */
	public static int getSectionVersion(String id) {
		Response response = SectionServices.getSection(id);
		response.then().log().all();
		JSONObject responseObj = new JSONObject(response.getBody().asString());
		return responseObj.getInt("version");
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static Map<String, String> getBranchStandardFromMapBranchStandardSections(String id) {
		Response response = AdhocServices.getBranchStandardSections(id);
		response.then().log().all();

		JSONArray responseObj = new JSONArray(response.getBody().asString());
		Map<String, String> map = new HashMap<>();
		map.put("mapId", String.valueOf(responseObj.getJSONObject(0).get("id")));
		map.put("standardId", String.valueOf(responseObj.getJSONObject(0).getJSONObject("standard").get("id")));
		return map;
	}
}
