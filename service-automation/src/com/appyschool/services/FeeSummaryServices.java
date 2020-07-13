package com.appyschool.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class FeeSummaryServices {

	private static final Logger LOG = LogManager.getLogger(FeeSummaryServices.class);

	/**
	 * Get Fee Summary Service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response getFee(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.FEE_URI_PATH) + id);
	}
	
	/**
	 * Get All Fee Summary Service (Standard Wise)
	 * @param payload
	 * @return
	 */
	public static Response getAllFeeStandard(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.FEESUMMARY_URI_PATH)+id+"?viewType=STANDARD");
	}
	
	/**
	 * Get All Fee Summary Service (Fee Detail Wise)
	 * @param payload
	 * @return
	 */
	public static Response getAllFee(String id){
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.FEESUMMARY_URI_PATH)+id+"?viewType=FEETYPE");
	}
}
