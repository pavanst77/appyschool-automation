package com.appyschool.services;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class WeeklyTrackerServices {

	/**
	 * Create WeeklyTracker service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createWeeklyTracker(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_WEEKLY_TRACKER_SKILLS));
	}

	/**
	 * Modify WeeklyTracker service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response modifyWeeklyTracker(String payload, String id) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_WEEKLY_TRACKER_SKILLS));
	}
	
	/**
	 * Delete WeeklyTracker service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response deleteWeeklyTracker(String id) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_WEEKLY_TRACKER_SKILLS) + id + "/delete");
	}
	
	/**
	 * Get Particular WeeklyTracker service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response getWeeklyTracker(String id) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.CONFIGURATION_WEEKLY_TRACKER_SKILLS) + id);
	}
	
	/**
	 * Get All WeeklyTracker service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response getAllWeeklyTracker() {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_WEEKLY_TRACKER_SKILLS));
	}
}
