package com.appyschool.services;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class GradesServices {

	/**
	 * Create Grades service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createGrades(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_GRADES));
	}

	/**
	 * Modify Grades service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response modifyGrades(String payload, String id) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_GRADES));
	}
	
	/**
	 * Delete Grades service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response deleteGrades(String id) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_GRADES) + id + "/delete");
	}
	
	/**
	 * Get Particular Grades service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response getGrades(String id) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.CONFIGURATION_GRADES) + id);
	}
	
	/**
	 * Get All Gradess service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response getAllGradess() {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_GRADES));
	}
}
