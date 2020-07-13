package com.appyschool.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class FeeServices {

	private static final Logger LOG = LogManager.getLogger(FeeServices.class);

	/**
	 * Create Fee Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createFee(String payload){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.FEE_URI_PATH));
	}
	
	
	/**
	 * Delete Fee Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response deleteFee(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).post(ConfigProperties.getValue(GenericConstants.FEE_URI_PATH) + id + "/delete");
	}
	
	
	/**
	 * Get Fee Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response getFee(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.FEE_URI_PATH) + id);
	}
	
	/**
	 * Get All Fee Service
	 * @param payload
	 * @return
	 */
	public static Response getAllFee(){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.FEE_URI_PATH));
	}
}
