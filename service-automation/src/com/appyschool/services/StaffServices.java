package com.appyschool.services;

import static com.jayway.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class StaffServices {

	private static final Logger LOG = LogManager.getLogger(StaffServices.class);

	/**
	 * Create Staff Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createStaff(String payload){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.STAFF_URI_PATH));
	}
	
	/**
	 * Modify Staff Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response modifyStaff(String payload, String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.STAFF_URI_PATH) + id);
	}
	
	/**
	 * Delete Staff Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response deleteStaff(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).post(ConfigProperties.getValue(GenericConstants.STAFF_URI_PATH) + id + "/delete");
	}
	
	/**
	 * Get Staff Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response getStaff(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.STAFF_URI_PATH) + id);
	}
	
	/**
	 * Get All Staff Service
	 * @param payload
	 * @return
	 */
	public static Response getAllStaff(){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.STAFF_URI_PATH));
	}
}
