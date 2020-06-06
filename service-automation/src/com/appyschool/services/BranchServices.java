package com.appyschool.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class BranchServices {
	
	private static final Logger LOG = LogManager.getLogger(BranchServices.class);

	/**
	 * Create Branch service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createBranch(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_BRANCH));
	}

	/**
	 * Modify Branch service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response modifyBranch(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_BRANCH));
	}
	
	/**
	 * Delete Branch service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response deleteBranch(String id) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_BRANCH) + id + "/delete");
	}
	
	/**
	 * Get Particular Branch service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response getBranch(String id) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.CONFIGURATION_BRANCH) + id);
	}
	
	/**
	 * Get All Branches service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response getAllBranches() {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.CONFIGURATION_BRANCH));
	}
}
