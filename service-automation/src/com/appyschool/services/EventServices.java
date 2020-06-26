package com.appyschool.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class EventServices {

	private static final Logger LOG = LogManager.getLogger(EventServices.class);

	/**
	 * Create Event Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createEvent(String payload){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.EVENT_URI_PATH));
	}
	
	/**
	 * Modify Event service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response modifyEvent(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.EVENT_URI_PATH));
	}
	
	
	/**
	 * Delete Event Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response deleteEvent(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).post(ConfigProperties.getValue(GenericConstants.EVENT_URI_PATH) + id + "/delete");
	}
	
	/**
	 * Get Event Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response getEvent(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.EVENT_URI_PATH) + id);
	}
	
	/**
	 * Get All Event Service
	 * @param payload
	 * @return
	 */
	public static Response getAllEvent(){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.EVENT_URI_PATH));
	}
}
