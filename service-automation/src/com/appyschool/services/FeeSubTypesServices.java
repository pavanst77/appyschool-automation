package com.appyschool.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class FeeSubTypesServices {

	private static final Logger LOG = LogManager.getLogger(FeeSubTypesServices.class);

	/**
	 * Create Fee Sub Types Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createFee(String payload){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.FEESUBTYPES_URI_PATH));
	}
	
	
	/**
	 * Delete Fee Sub Types Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response deleteFee(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).post(ConfigProperties.getValue(GenericConstants.FEESUBTYPES_URI_PATH) + id + "/delete");
	}
	
	/**
	 * Get All Fee Sub Types Service
	 * @param payload
	 * @return
	 */
	public static Response getAllFee(){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.FEESUBTYPES_URI_PATH));
	}
}
