package com.appyschool.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class StaffAttendanceServices {

	private static final Logger LOG = LogManager.getLogger(StaffAttendanceServices.class);

	/**
	 * Take Staff Attendance
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createStaffAttendance(String payload){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.STAFFATTENDANCE_URI_PATH));
	}
	
	/**
	 * Delete Staff Attendance Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response deleteStaffAttendance(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).post(ConfigProperties.getValue(GenericConstants.STAFFATTENDANCE_URI_PATH) + id);
	}
	
	/**
	 * Get Staff Attendance Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response getStaffAttendance(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.STAFFATTENDANCE_URI_PATH) + id);
	}
	
	/**
	 * Get All Staff Attendance Service
	 * @param payload
	 * @return
	 */
	public static Response getAllStaffAttendance(){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.STAFFATTENDANCE_URI_PATH));
	}
}
