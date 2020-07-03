package com.appyschool.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class MessageTemplatesServices {

	private static final Logger LOG = LogManager.getLogger(MessageTemplatesServices.class);

	/**
	 * Create Message Templates Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createMessageTemplates(String payload){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.MESSAGETEMPLATES_URI_PATH));
	}
	
	/**
	 * Modify Message Templates service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response modifyMessageTemplates(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.MESSAGETEMPLATES_URI_PATH));
	}
	
	
	/**
	 * Delete Message Templates Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response deleteMessageTemplates(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).post(ConfigProperties.getValue(GenericConstants.MESSAGETEMPLATES_URI_PATH) + id + "/delete");
	}
	
	/**
	 * Get Message Templates Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response getMessageTemplates(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.MESSAGETEMPLATES_URI_PATH) + id);
	}
	
	/**
	 * Get All Message Templates Service
	 * @param payload
	 * @return
	 */
	public static Response getAllMessageTemplates(){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.MESSAGETEMPLATES_URI_PATH));
	}
}
