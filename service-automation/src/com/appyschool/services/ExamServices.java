package com.appyschool.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class ExamServices {
	
	private static final Logger LOG = LogManager.getLogger(ExamServices.class);

	/**
	 * Create Exams service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createExams(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_EXAMS));
	}

	/**
	 * Modify Exams service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response modifyExams(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_EXAMS));
	}
	
	/**
	 * Delete Exams service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response deleteExams(String id) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_EXAMS) + id + "/delete");
	}
	
	/**
	 * Get Particular Exams service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response getExams(String id) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_EXAMS) + id);
	}
	
	/**
	 * Get All Examss service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response getAllExamss() {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_EXAMS));
	}
}
