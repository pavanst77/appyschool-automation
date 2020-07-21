package com.appyschool.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class AssignFeeServices {

	private static final Logger LOG = LogManager.getLogger(AssignFeeServices.class);

	/**
	 * Create Assign Fee Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createFee(String payload){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.ASSIGNFEE_URI_PATH));
	}
	
	
	/**
	 * Delete Assign Fee Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response deleteFee(String id,String idd){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).post(ConfigProperties.getValue(GenericConstants.ASSIGNFEE_URI_PATH) + "delete/"+idd+"/"+id);
	}
	
	
	/**
	 * Get Assign Fee Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response getFee(String id,String idd){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.VIEWASSIGNFEE_URI_PATH) + id+"/"+idd);
	}
	
}
