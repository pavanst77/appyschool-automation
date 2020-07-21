package com.appyschool.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class ConcessionTypesServices {

	private static final Logger LOG = LogManager.getLogger(ConcessionTypesServices.class);

	/**
	 * Create Concession Types Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createConcession(String payload){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONCESSIONTYPES_URI_PATH));
	}
	
	
	/**
	 * Delete Concession Types Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response deleteConcession(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).post(ConfigProperties.getValue(GenericConstants.CONCESSIONTYPES_URI_PATH) + id + "/delete");
	}
	
	/**
	 * Get All Concession Types Service
	 * @param payload
	 * @return
	 */
	public static Response getAllConcession(){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.CONCESSIONTYPES_URI_PATH));
	}
}
