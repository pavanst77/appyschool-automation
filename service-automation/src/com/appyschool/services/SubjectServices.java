package com.appyschool.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class SubjectServices {
	
	private static final Logger LOG = LogManager.getLogger(SubjectServices.class);

	/**
	 * Create Subject service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createSubject(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_SUBJECTS));
	}

	/**
	 * Modify Subject service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response modifySubject(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_SUBJECTS));
	}
	
	/**
	 * Delete Subject service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response deleteSubject(String id) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_SUBJECTS) + id + "/delete");
	}
	
	/**
	 * Get Particular Subject service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response getSubject(String id) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_SUBJECTS) + id);
	}
	
	/**
	 * Get All Subjects service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response getAllSubjects() {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_SUBJECTS));
	}
}
