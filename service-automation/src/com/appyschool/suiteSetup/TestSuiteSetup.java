package com.appyschool.suiteSetup;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.appyschool.common.CommonMethods;
import com.appyschool.common.CommonUtils;
import com.appyschool.common.ConfigProperties;
import com.appyschool.common.DataProviderArguments;
import com.appyschool.common.GenericConstants;
import com.appyschool.common.SoftAssertionBase;
import com.appyschool.common.SuiteSetupProperties;
import com.appyschool.services.AcademicYearServices;
import com.appyschool.services.AdhocServices;
import com.appyschool.services.BranchServices;
import com.appyschool.services.DesignationServices;
import com.appyschool.services.ExamServices;
import com.appyschool.services.GradesServices;
import com.appyschool.services.SectionServices;
import com.appyschool.services.StaffServices;
import com.appyschool.services.StandardServices;
import com.appyschool.services.StudentServices;
import com.appyschool.services.SubjectServices;
import com.appyschool.services.WeeklyTrackerServices;
import com.jayway.restassured.response.Response;

public class TestSuiteSetup extends SoftAssertionBase {

	int i = 0, j = 0, k = 0, l = 0, m = 0, n = 0, o = 0, p = 0, q = 0, r = 0;

	/**
	 * Create Branches
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/configuration/createBranch.csv")
	public void testCreateBranch(HashMap<String, String> map) throws JSONException {
		i++;
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.BRANCH_CREATE_PAYLOAD);
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
		json.put("name", map.get("$name") + RandomStringUtils.randomAlphanumeric(2));
		json.put("contactNumber1", RandomStringUtils.randomNumeric(10));
		json.put("contactNumber2", RandomStringUtils.randomNumeric(10));

		Response response = BranchServices.createBranch(json.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), Integer.parseInt(map.get("statusCode")));
		verifyTrue(responseObj.get("id") != null);
		SuiteSetupProperties.setValue("BRANCH_ID_" + i, responseObj.get("id"));
		SuiteSetupProperties.setValue("BRANCH_VERSION_" + i, responseObj.get("version"));
	}

	/**
	 * Create Subjects
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/configuration/createSubject.csv")
	public void testCreateSubjects(HashMap<String, String> map) throws JSONException {
		j++;
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.SUBJECT_CREATE_PAYLOAD);
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
		json.put("name", map.get("$name") + RandomStringUtils.randomAlphanumeric(2));

		Response response = SubjectServices.createSubject(json.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), Integer.parseInt(map.get("statusCode")));
		verifyTrue(responseObj.get("id") != null);
		SuiteSetupProperties.setValue("SUBJECT_ID_" + j, responseObj.get("id"));
	}

	/**
	 * Create Standards
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile", dependsOnMethods = "testCreateSubjects")
	@DataProviderArguments("./testData/configuration/createStandard.csv")
	public void testCreateStandards(HashMap<String, String> map) throws JSONException {
		k++;
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.STANDARDS_CREATE_PAYLOAD);
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
		json.put("name", map.get("$name") + " - " + RandomStringUtils.randomAlphanumeric(2));

		JSONArray subjects = json.getJSONArray("subjectDetails");

		subjects.put(0, new JSONObject().put("id", SuiteSetupProperties.getValue("SUBJECT_ID_1")));
		subjects.put(1, new JSONObject().put("id", SuiteSetupProperties.getValue("SUBJECT_ID_2")));
		subjects.put(2, new JSONObject().put("id", SuiteSetupProperties.getValue("SUBJECT_ID_3")));
		subjects.put(3, new JSONObject().put("id", SuiteSetupProperties.getValue("SUBJECT_ID_4")));
		subjects.put(4, new JSONObject().put("id", SuiteSetupProperties.getValue("SUBJECT_ID_5")));
		subjects.put(5, new JSONObject().put("id", SuiteSetupProperties.getValue("SUBJECT_ID_6")));

		Response response = StandardServices.createStandard(json.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), Integer.parseInt(map.get("statusCode")));
		verifyTrue(responseObj.get("id") != null);
		SuiteSetupProperties.setValue("STANDARDS_ID_" + k, responseObj.get("id"));
		SuiteSetupProperties.setValue("STANDARDS_VERSION_" + k, responseObj.get("version"));

	}

	/**
	 * Create Sections
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/configuration/createSection.csv")
	public void testCreateSections(HashMap<String, String> map) throws JSONException {
		l++;
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.SECTION_CREATE_PAYLOAD);
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
		json.put("name", map.get("$name") + " - " + RandomStringUtils.randomAlphanumeric(2));

		Response response = SectionServices.createSection(json.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), Integer.parseInt(map.get("statusCode")));
		verifyTrue(responseObj.get("id") != null);
		SuiteSetupProperties.setValue("SECTION_ID_" + l, responseObj.get("id"));
	}

	/**
	 * Map Branch Standards & Sections
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile", dependsOnMethods = {
			"testCreateBranch", "testCreateStandards", "testCreateSections" })
	@DataProviderArguments("./testData/configuration/mapBranchStandardSections.csv")
	public void testMapBranchStandardSection(HashMap<String, String> map) throws JSONException {
		JSONArray mapBranchStandSections = new JSONArray();
		JSONObject record1 = new JSONObject();
		JSONObject branch = new JSONObject();
		branch.put("id", SuiteSetupProperties.getValue("BRANCH_ID_1"));
		branch.put("version", CommonMethods.getBranchVersion(SuiteSetupProperties.getValue("BRANCH_ID_1")));

		JSONObject standard = new JSONObject();
		standard.put("id", SuiteSetupProperties.getValue("STANDARDS_ID_1"));
		standard.put("version", CommonMethods.getStandardVersion(SuiteSetupProperties.getValue("STANDARDS_ID_1")));

		JSONObject section1 = new JSONObject();
		section1.put("id", SuiteSetupProperties.getValue("SECTION_ID_1"));
		section1.put("version", CommonMethods.getSectionVersion(SuiteSetupProperties.getValue("SECTION_ID_1")));

		record1.put("branch", branch);
		record1.put("standard", standard);
		record1.put("section", section1);

		JSONObject record2 = new JSONObject();

		JSONObject section2 = new JSONObject();
		section2.put("id", SuiteSetupProperties.getValue("SECTION_ID_2"));
		section2.put("version", CommonMethods.getSectionVersion(SuiteSetupProperties.getValue("SECTION_ID_2")));

		record2.put("branch", branch);
		record2.put("standard", standard);
		record2.put("section", section2);

		mapBranchStandSections.put(0, record1);
		mapBranchStandSections.put(1, record2);

		Response response = AdhocServices.mapBranchStandardSections(mapBranchStandSections.toString());
		response.then().log().all();

		verifyEquals(response.statusCode(), Integer.parseInt(map.get("statusCode")));

		Response response1 = AdhocServices.getAllBranchStandardSections();
		response1.then().log().all();

		JSONArray responseObj = new JSONArray(response1.getBody().asString());
		for (int kk = 0; kk < responseObj.length(); kk++) {
			SuiteSetupProperties.setValue("MAP_BRANCH_STANDARD_SECTION_" + (kk + 1),
					responseObj.getJSONObject(kk).get("id"));
		}
	}

	/**
	 * Create Exams
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/configuration/createExams.csv")
	public void testCreateExams(HashMap<String, String> map) throws JSONException {
		m++;
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.EXAMS_CREATE_PAYLOAD);
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

		json.put("name", map.get("$name") + RandomStringUtils.randomAlphanumeric(2));

		Response response = ExamServices.createExams(json.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), Integer.parseInt(map.get("statusCode")));
		verifyTrue(responseObj.get("id") != null);
		SuiteSetupProperties.setValue("EXAMS_ID_" + m, responseObj.get("id"));
	}

	/**
	 * Map Exams and Subjects
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dependsOnMethods = { "testCreateSubjects", "testCreateExams" })
	public void testMapExamSubjects(HashMap<String, String> map) throws JSONException {
		JSONArray mapExamSubjects = new JSONArray();

		JSONObject examDetails = new JSONObject();
		examDetails.put("id", SuiteSetupProperties.getValue("EXAMS_ID_1"));

		JSONObject examSubject1 = new JSONObject();
		JSONObject subjectDetails1 = new JSONObject();
		subjectDetails1.put("id", SuiteSetupProperties.getValue("SUBJECT_ID_1"));

		examSubject1.put("examDetails", examDetails);
		examSubject1.put("subjectDetails", subjectDetails1);
		examSubject1.put("minMark", "30");
		examSubject1.put("maxMark", "100");
		examSubject1.put("subjectTitle", "Subject 1");

		JSONObject examSubject2 = new JSONObject();
		JSONObject subjectDetails2 = new JSONObject();
		subjectDetails2.put("id", SuiteSetupProperties.getValue("SUBJECT_ID_2"));
		examSubject2.put("examDetails", examDetails);
		examSubject2.put("subjectDetails", subjectDetails2);
		examSubject2.put("minMark", "30");
		examSubject2.put("maxMark", "100");
		examSubject2.put("subjectTitle", "Subject 2");

		JSONObject examSubject3 = new JSONObject();
		JSONObject subjectDetails3 = new JSONObject();
		subjectDetails3.put("id", SuiteSetupProperties.getValue("SUBJECT_ID_3"));
		examSubject3.put("examDetails", examDetails);
		examSubject3.put("subjectDetails", subjectDetails3);
		examSubject3.put("minMark", "30");
		examSubject3.put("maxMark", "100");
		examSubject3.put("subjectTitle", "Subject 3");

		JSONObject examSubject4 = new JSONObject();
		JSONObject subjectDetails4 = new JSONObject();
		subjectDetails4.put("id", SuiteSetupProperties.getValue("SUBJECT_ID_4"));
		examSubject4.put("examDetails", examDetails);
		examSubject4.put("subjectDetails", subjectDetails4);
		examSubject4.put("minMark", "30");
		examSubject4.put("maxMark", "100");
		examSubject4.put("subjectTitle", "Subject 4");

		JSONObject examSubject5 = new JSONObject();
		JSONObject subjectDetails5 = new JSONObject();
		subjectDetails5.put("id", SuiteSetupProperties.getValue("SUBJECT_ID_5"));
		examSubject5.put("examDetails", examDetails);
		examSubject5.put("subjectDetails", subjectDetails5);
		examSubject5.put("minMark", "30");
		examSubject5.put("maxMark", "100");
		examSubject5.put("subjectTitle", "Subject 3");

		JSONObject examSubject6 = new JSONObject();
		JSONObject subjectDetails6 = new JSONObject();
		subjectDetails6.put("id", SuiteSetupProperties.getValue("SUBJECT_ID_6"));
		examSubject6.put("examDetails", examDetails);
		examSubject6.put("subjectDetails", subjectDetails6);
		examSubject6.put("minMark", "30");
		examSubject6.put("maxMark", "100");
		examSubject6.put("subjectTitle", "Subject 6");

		mapExamSubjects.put(0, examSubject1);
		mapExamSubjects.put(1, examSubject2);
		mapExamSubjects.put(2, examSubject3);
		mapExamSubjects.put(3, examSubject4);
		mapExamSubjects.put(4, examSubject5);
		mapExamSubjects.put(5, examSubject6);

		Response response = AdhocServices.mapExamSubjects(mapExamSubjects.toString());
		response.then().log().all();
		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), Integer.parseInt(map.get("statusCode")));
		verifyEquals(responseObj.getString("status"), "OK");
	}

	/**
	 * Create Grades
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/configuration/createGrades.csv")
	public void testCreateGrades(HashMap<String, String> map) throws JSONException {
		o++;
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.GRADES_CREATE_PAYLOAD);
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

		json.put("grade", map.get("$grade") + RandomStringUtils.randomAlphanumeric(2));

		Response response = GradesServices.createGrades(json.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), Integer.parseInt(map.get("statusCode")));
		verifyTrue(responseObj.get("id") != null);
		SuiteSetupProperties.setValue("GRADES_ID_" + o, responseObj.get("id"));
	}

	/**
	 * Create Weekly Tracker Skills
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/configuration/createWeeklyTracker.csv")
	public void testCreateWeeklyTrackerSkills(HashMap<String, String> map) throws JSONException {
		p++;
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.WEEKLY_TRACKER_PAYLOAD);
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

		json.put("skillName", map.get("$skillName") + RandomStringUtils.randomAlphanumeric(2));

		Response response = WeeklyTrackerServices.createWeeklyTracker(json.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), Integer.parseInt(map.get("statusCode")));
		verifyTrue(responseObj.get("id") != null);
		SuiteSetupProperties.setValue("WEEKLY_TRACKER_ID_" + p, responseObj.get("id"));
	}

	/**
	 * Create Academic Years
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/configuration/createAcademicYears.csv")
	public void testCreateAcademicYears(HashMap<String, String> map) throws JSONException {
		q++;
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.ACADEMIC_YEAR_PAYLOAD);
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

		json.put("academicYearName", map.get("$academicYearName") + RandomStringUtils.randomAlphanumeric(2));

		Response response = AcademicYearServices.createAcademicYear(json.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), Integer.parseInt(map.get("statusCode")));
		verifyTrue(responseObj.get("id") != null);
		SuiteSetupProperties.setValue("ACADEMIC_YEAR_ID_" + q, responseObj.get("id"));
		System.out.println("Academic Year id is: "+ SuiteSetupProperties.getValue("ACADEMIC_YEAR_ID_" + q));
	}

	/**
	 * Create COScholasticAreas
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/configuration/createScholasticAreas.csv")
	public void testCreateCoScholasticAreas(HashMap<String, String> map) throws JSONException {
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.CO_SCHOLASTICAREAS_PAYLOAD);
		JSONArray json = CommonUtils.getJsonArrayFromTemplate(jsonTemplate, map);

		Response response = AdhocServices.createCoSholasticAreas(json.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), Integer.parseInt(map.get("statusCode")));
		verifyEquals(responseObj.getString("status"), "OK");
	}

	/**
	 * Create CoCurricularAreas
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/configuration/createCoCurricularAreas.csv")
	public void testCreateCoCurricularAreas(HashMap<String, String> map) throws JSONException {
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.CO_CURRICULARAREAS_PAYLOAD);
		JSONArray json = CommonUtils.getJsonArrayFromTemplate(jsonTemplate, map);

		Response response = AdhocServices.createCoCurricularAreas(json.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), Integer.parseInt(map.get("statusCode")));
		verifyEquals(responseObj.getString("status"), "OK");
	}

	/**
	 * Create a Student
	 * 
	 * @param map
	 * @throws JSONException
	 */

	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile", dependsOnMethods = "testMapBranchStandardSection")
	@DataProviderArguments("./testData/configuration/student.csv")
	public void testCreateStudents(HashMap<String, String> map) throws JSONException {
		n++;
		String jsonTemplate = ConfigProperties.getValue(GenericConstants.STUDENT_CREATE_PAYLOAD);

		Map<String, String> branchStandardIds = CommonMethods
				.getBranchStandardFromMapBranchStandardSections(SuiteSetupProperties.getValue("BRANCH_ID_" + 1));

		map.put("$branchId", SuiteSetupProperties.getValue("BRANCH_ID_" + 1));
		map.put("$branchStandardSection", branchStandardIds.get("mapId"));
		map.put("$standard", branchStandardIds.get("standardId"));

		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

		json.put("idNumber", "Appy" + RandomStringUtils.randomNumeric(5));
		json.put("rollNumber", RandomStringUtils.randomNumeric(5));
		json.put("primaryPhoneNumber", RandomStringUtils.randomNumeric(5) + RandomStringUtils.randomNumeric(5));
		json.put("alternatePhoneNumber", RandomStringUtils.randomNumeric(5) + RandomStringUtils.randomNumeric(5));
		json.put("primaryEmailId", map.get("$firstName") + RandomStringUtils.randomNumeric(2) + "@appyschool.com");
		json.put("secondaryEmailId", map.get("$firstName") + RandomStringUtils.randomNumeric(2) + "@appyschool.com");
		Response response = StudentServices.createStudent(json.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		verifyEquals(response.statusCode(), 200);
		verifyEquals(responseObj.getString("status"), GenericConstants.SUCCESS_OK);
		SuiteSetupProperties.setValue("STUDENT_ID_" + n, responseObj.get("id"));
	}

	/*
	 * Create Designation
	 * 
	 * @param map
	 * 
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile")
	@DataProviderArguments("./testData/configuration/createDesignation.csv")
	public void testCreateDesignation(HashMap<String, String> map) throws JSONException {

		String jsonTemplate = CommonUtils
				.getJsonTemplate("./testData/configuration/branches/json/createDesignation.json");
		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

		Response response = DesignationServices.createDesignation(json.toString());
		response.then().log().all();
		System.out.print(response);

		JSONObject responseObj = new JSONObject(response.getBody().asString());

		// Setting the Designation Id so that it can be used whenevevr required
		SuiteSetupProperties.setValue("Designation_ID", responseObj.get("id"));
	}
	
	/**
	 * Create a Staff
	 * 
	 * @param map
	 * @throws JSONException
	 */
	@Test(dataProviderClass = com.appyschool.common.CommonUtils.class, dataProvider = "getDataFromFile",dependsOnMethods = "testCreateDesignation")
	@DataProviderArguments("./testData/csv/staff1.csv")
	public void testCreateStaff(HashMap<String, String> map) throws JSONException {

		String jsonTemplate = ConfigProperties.getValue(GenericConstants.STAFF_CREATE_PAYLOAD);
		JSONObject object1 = new JSONObject();

		// Function to get Designation ID from SuiteSetupProperties
		object1.put("id", SuiteSetupProperties.getValue("Designation_ID"));

		JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

		json.put("staffDesignation", object1);
		json.put("primaryPhoneNumber", RandomStringUtils.randomNumeric(5) + RandomStringUtils.randomNumeric(5));
		json.put("alternatePhoneNumber", RandomStringUtils.randomNumeric(5) + RandomStringUtils.randomNumeric(5));
		json.put("primaryEmailId", map.get("$firstName") + RandomStringUtils.randomNumeric(2) + "@gmail.com");

		System.out.println(json.toString());
		Response response = StaffServices.createStaff(json.toString());
		response.then().log().all();

		JSONObject responseObj = new JSONObject(response.getBody().asString());
		
		// Setting the Staff ID and Version so that it can be used whenevevr required
		SuiteSetupProperties.setValue("Staff_ID", responseObj.get("id"));
	}

}