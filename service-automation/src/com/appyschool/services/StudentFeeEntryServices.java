package com.appyschool.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class StudentFeeEntryServices {

	private static final Logger LOG = LogManager.getLogger(StudentFeeEntryServices.class);

	/**
	 * Set Status for Student Fee Entry Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createStudentFeeEntry(String payload){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.STUDENTFEEENTRY_URI_PATH));
	}
	
	
	/**
	 * Delete Student Fee Entry Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response deleteStudentFeeEntry(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).post(ConfigProperties.getValue(GenericConstants.FEETYPES_URI_PATH) + id + "/delete");
	}
	
	/**
	 * Get All Student Fee Entry Service
	 * @param payload
	 * @return
	 */
	public static Response getAllStudentFeeEntry(String id,String idd){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.VIEWSTUDENTFEEENTRY_URI_PATH)+id+"/"+idd);
	}
}
