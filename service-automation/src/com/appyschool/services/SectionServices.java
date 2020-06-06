package com.appyschool.services;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class SectionServices {

	/**
	 * Create Section service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createSection(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_SECTIONS));
	}

	/**
	 * Modify Section service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response modifySection(String payload, String id) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_SECTIONS));
	}
	
	/**
	 * Delete Section service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response deleteSection(String id) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_SECTIONS) + id + "/delete");
	}
	
	/**
	 * Get Particular Section service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response getSection(String id) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.CONFIGURATION_SECTIONS) + id);
	}
	
	/**
	 * Get All Sections service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response getAllSections() {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_SECTIONS));
	}
}
