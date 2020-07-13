package com.appyschool.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class FoodServices {

	private static final Logger LOG = LogManager.getLogger(FoodServices.class);

	/**
	 * Create Food Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createFood(String payload){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.FOOD_URI_PATH));
	}
	
	/**
	 * Modify Food service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response modifyFood(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.FOOD_URI_PATH));
	}
	
	
	/**
	 * Delete Food Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response deleteFood(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).post(ConfigProperties.getValue(GenericConstants.FOOD_URI_PATH) + id + "/delete");
	}
	
	/**
	 * Get Food Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response getFood(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.FOOD_URI_PATH) + id);
	}
	
	/**
	 * Get All Food Service
	 * @param payload
	 * @return
	 */
	public static Response getAllFood(){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.FOOD_URI_PATH));
	}
	
	/**
	 * Send Notification service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response sendNotification(String id,String medium){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).post(ConfigProperties.getValue(GenericConstants.FOOD_URI_PATH) + id + "/"+ medium );
	}
	
}
