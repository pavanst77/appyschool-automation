package com.appyschool.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class StandardServices {
	
	private static final Logger LOG = LogManager.getLogger(StandardServices.class);

	/**
	 * Create Standard service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createStandard(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_STANDARDS));
	}

	/**
	 * Modify Standard service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response modifyStandard(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_STANDARDS));
	}
	
	/**
	 * Delete Standard service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response deleteStandard(String id) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_STANDARDS) + id + "/delete");
	}
	
	/**
	 * Get Particular Standard service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response getStandard(String id) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.CONFIGURATION_STANDARDS) + id);
	}
	
	/**
	 * Get All Standardes service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response getAllStandardes() {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.CONFIGURATION_STANDARDS));
	}
}
