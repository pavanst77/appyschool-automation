package com.appyschool.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class ConcernsServices {

	private static final Logger LOG = LogManager.getLogger(ConcernsServices.class);

	/**
	 * Create Concerns Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createConcerns(String payload){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONCERNS_URI_PATH));
	}
	
	/**
	 * Modify Concerns service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response modifyConcerns(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONCERNS_URI_PATH));
	}
	
	/**
	 * Get All Concerns Service
	 * @param payload
	 * @return
	 */
	public static Response getAllConcerns(){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.CONCERNS_URI_PATH));
	}
}
