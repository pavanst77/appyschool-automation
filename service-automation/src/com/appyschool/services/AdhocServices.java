package com.appyschool.services;

import java.io.File;

import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.GenericConstants;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class AdhocServices {
	
	/**
	 * Map Branch Standard Section service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response mapBranchStandardSections(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_BRANCH_STANDARD_SECTION));
	}
	
	/**
	 * Get Branch Standard Section service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response getAllBranchStandardSections() {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.CONFIGURATION_BRANCH_STANDARD_SECTION));
	}
	
	/**
	 * Get Branch Standard Section service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response getBranchStandardSections(String id) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.get(ConfigProperties.getValue(GenericConstants.CONFIGURATION_BRANCH_STANDARD_SECTION) + id);
	}
	
	/**
	 * Map Exam Subjects service
	 * 
	 * @param payload
	 * @return
	 */
	public static Response mapExamSubjects(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_EXAMS_SUBJECTS));
	}
	
	/**
	 * Create Co-Scholastic Areas
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createCoSholasticAreas(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_CO_SCHOLASTICAREAS));
	}
	
	/**
	 * Create Co-Curricular Areas
	 * 
	 * @param payload
	 * @return
	 */
	public static Response createCoCurricularAreas(String payload) {
		RequestSpecification request = CommonUtils.getRequestSpec();
		return request.contentType(GenericConstants.CONTENT_TYPE_JSON).body(payload).post(ConfigProperties.getValue(GenericConstants.CONFIGURATION_CO_CURRICULARAREAS));
	}
	
	/**
	 * Upload image
	 * 
	 * @param fileLoc
	 * @param delim
	 * @param head
	 * @return
	 */
    public static Response uploadBannerImage(String imgLoc) {
		RequestSpecification request = CommonUtils.getRequestSpec();
    	return request.multiPart("file", new File(imgLoc)).post(ConfigProperties.getValue(GenericConstants.BANNER_IMAGER_URL));
        }
        
}
