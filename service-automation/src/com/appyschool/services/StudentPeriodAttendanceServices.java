package com.appyschool.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class StudentPeriodAttendanceServices {

	private static final Logger LOG = LogManager.getLogger(StudentPeriodAttendanceServices.class);

	/**
	 * Take Student Attendance(Periodic)
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createStudentAttendance(String payload){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.STUDENTPERIODATTENDANCE_URI_PATH));
	}
	
	/**
	 * Modify Student Attendance(Periodic) service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response modifyStudentAttendance(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.STUDENTPERIODATTENDANCE_URI_PATH));
	}
	
	
	/**
	 * Delete Student Attendance(Periodic) Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response deleteStudentAttendance(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).post(ConfigProperties.getValue(GenericConstants.STUDENTPERIODATTENDANCE_URI_PATH) + id);
	}
	
	/**
	 * Get Student Attendance(Periodic) Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response getStudentAttendance(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.VIEWSTUDENTPERIODATTENDANCE_URI_PATH) + id);
	}
	
	/**
	 * Get All Student Attendance(Periodic)  Service
	 * @param payload
	 * @return
	 */
	public static Response getAllStudentAttendance(){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.VIEWSTUDENTPERIODATTENDANCE_URI_PATH));
	}
}
