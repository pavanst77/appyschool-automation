package com.appyschool.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

/**
 * All the services related to Students
 * 
 * @author Hrishi
 *
 */
public class StudentServices {
	
	private static final Logger LOG = LogManager.getLogger(StudentServices.class);

	/**
	 * Create Student Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createStudent(String payload){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.STUDENT_URL));
	}
	
	/**
	 * Modify Student Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response modifyStudent(String payload, String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.STUDENT_URL) + id);
	}
	
	/**
	 * Delete Student Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response deleteStudent(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.post(ConfigProperties.getValue(GenericConstants.STUDENT_URL) + id + "/delete");
	}
	
	/**
	 * Get Student Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response getStudent(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.STUDENT_URL) + id);
	}
	
	/**
	 * Get All Student Service
	 * @param payload
	 * @return
	 */
	public static Response getAllStudent(){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.STUDENT_URL));
	}
}
