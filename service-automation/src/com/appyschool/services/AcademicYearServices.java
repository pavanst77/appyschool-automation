package com.appyschool.services;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class AcademicYearServices {

	/**
	 * Create AcademicYear service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createAcademicYear(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_ACADEMICYEAR));
	}

	/**
	 * Modify AcademicYear service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response modifyAcademicYear(String payload, String id) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_ACADEMICYEAR));
	}
	
	/**
	 * Delete AcademicYear service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response deleteAcademicYear(String id) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_ACADEMICYEAR) + id + "/delete");
	}
	
	/**
	 * Get Particular AcademicYear service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response getAcademicYearDetails(String id) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.CONFIGURATION_ACADEMICYEAR) + id);
	}
	
	/**
	 * Get All AcademicYear service
	 * 
	 * @param payload
	 * @param id
	 * @return
	 */
	public static Response getAllAcademicYears() {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_ACADEMICYEAR));
	}
}
