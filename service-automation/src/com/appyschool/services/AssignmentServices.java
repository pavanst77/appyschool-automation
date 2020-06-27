package com.appyschool.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class AssignmentServices {

	private static final Logger LOG = LogManager.getLogger(AssignmentServices.class);

	/**
	 * Create Assignment Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createAssignment(String payload){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.ASSIGNMENT_URI_PATH));
	}
	
	/**
	 * Modify Assignment service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response modifyAssignment(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.ASSIGNMENT_URI_PATH));
	}
	
	
	/**
	 * Delete Assignment Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response deleteAssignment(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).post(ConfigProperties.getValue(GenericConstants.ASSIGNMENT_URI_PATH) + id + "/delete");
	}
	
	/**
	 * Get Assignment Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response getAssignment(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.ASSIGNMENT_URI_PATH) + id);
	}
	
	/**
	 * Get All Assignment Service
	 * @param payload
	 * @return
	 */
	public static Response getAllAssignment(){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.ASSIGNMENT_URI_PATH));
	}
}
