package com.appyschool.services;

import static com.jayway.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class DesignationServices {

	private static final Logger LOG = LogManager.getLogger(DesignationServices.class);
	
	
	/**
	 * Create Designation
	 * 
	 * @param payload
	 * @return
	 */
	
	public static Response createDesignation(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_DESIGNATION));
	}
	
	/**
	 * Get Designation Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response getdesignation(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.CONFIGURATION_DESIGNATION) + id);
	}
}
