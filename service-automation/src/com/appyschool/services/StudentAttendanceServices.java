package com.appyschool.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class StudentAttendanceServices {

	private static final Logger LOG = LogManager.getLogger(StudentAttendanceServices.class);

	/**
	 * Take Student Attendance(Day)
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createStudentAttendance(String payload){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.STUDENTATTENDANCE_URI_PATH));
	}
	
	/**
	 * Modify Student Attendance(Day) service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response modifyStudentAttendance(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.STUDENTATTENDANCE_URI_PATH));
	}
	
	
	/**
	 * Delete Student Attendance(Day) Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response deleteStudentAttendance(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).post(ConfigProperties.getValue(GenericConstants.STUDENTATTENDANCEDELETE_URI_PATH) + id);
	}
	
	/**
	 * Get Student Attendance(Day) Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response getStudentAttendance(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.STUDENTATTENDANCE_URI_PATH) + id);
	}
	
	/**
	 * Get All Student Attendance(Day)  Service
	 * @param payload
	 * @return
	 */
	public static Response getAllStudentAttendance(){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.STUDENTATTENDANCE_URI_PATH));
	}
}
