package com.appyschool.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.appyschool.services.AdhocServices;
import com.appyschool.services.AssignFeeServices;
import com.appyschool.services.AssignmentServices;
import com.appyschool.services.BranchServices;
import com.appyschool.services.ConcernsServices;
import com.appyschool.services.ConcessionTypesServices;
import com.appyschool.services.EventServices;
import com.appyschool.services.FeeServices;
import com.appyschool.services.FeeSubTypesServices;
import com.appyschool.services.FeeTypesServices;
import com.appyschool.services.FoodServices;
import com.appyschool.services.MessageTemplatesServices;
import com.appyschool.services.SectionServices;
import com.appyschool.services.StaffAttendanceServices;
import com.appyschool.services.StandardServices;
import com.appyschool.services.StudentAttendanceServices;
import com.appyschool.services.StudentPeriodAttendanceServices;
import com.appyschool.services.SubjectServices;
import com.jayway.restassured.response.Response;

public class CommonMethods {
	private static final Logger LOG = LogManager.getLogger(CommonMethods.class);

	
	/**
	 * Creates branch and returns id of that
	 * 
	 * @return
	 */
	public static List<String> createBranch() {
		List<String> list = null;
		try {
			HashMap<String, String>  map = CommonUtils.getDataFromFile("./testData/configuration/branches/csv/createBranch.csv");
			String jsonTemplate = CommonUtils.getJsonTemplate("./testData/configuration/branches/json/createBranch.json");
			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
			json.put("name", RandomStringUtils.randomAlphanumeric(10));
			
			Response response = BranchServices.createBranch(json.toString());
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			if (response.statusCode() == 200) {
				list = new ArrayList<String>();
				list.add(responseObj.get("id").toString());
				list.add(responseObj.getJSONObject("branchAddressDetails").get("id").toString());
				return list;
			} 
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured while reading");
		}
		return list;
	}
	
	/**
	 * Creates Events and returns id of that
	 * 
	 * @return
	 */
	public static List<String> createEvent() {
		List<String> list = null;
		try {
			HashMap<String, String>  map = CommonUtils.getDataFromFile("./testData/csv/event.csv");
			String jsonTemplate = ConfigProperties.getValue(GenericConstants.EVENT_CREATE_PAYLOAD);
			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

			json.put("eventName", RandomStringUtils.randomAlphanumeric(10));
			json.put("description", "Creating from API automation");

			Response response = EventServices.createEvent(json.toString());
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			if (response.statusCode() == 200) {
				list = new ArrayList<String>();
				list.add(responseObj.get("id").toString());
				return list;
			} 
		} 
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured while reading");
		}
		return list;
	}
	
	
	/**
	 * Create Fee Types and returns id of that
	 * 
	 * @return
	 */
	public static List<String> createFeeTypes() {
		List<String> list = null;
		try {
			HashMap<String, String>  map = CommonUtils.getDataFromFile("./testData/csv/fee.csv");
			String jsonTemplate = ConfigProperties.getValue(GenericConstants.FEETYPES_CREATE_PAYLOAD);
			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

			json.put("feeName", RandomStringUtils.randomAlphabetic(10));

			Response response = FeeTypesServices.createFee(json.toString());
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			if (response.statusCode() == 200) {
				list = new ArrayList<String>();
				list.add(responseObj.get("id").toString());
				return list;
			} 
		} 
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured while reading");
		}
		return list;
	}
	
	
	/**
	 * Create Fee Sub Types and returns id of that
	 * 
	 * @return
	 */
	public static List<String> createFeeSubTypes() {
		List<String> list = null;
		try {
			HashMap<String, String>  map = CommonUtils.getDataFromFile("./testData/csv/fee.csv");
			String jsonTemplate = ConfigProperties.getValue(GenericConstants.FEETYPES_CREATE_PAYLOAD);
			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

			json.put("feeName", RandomStringUtils.randomAlphabetic(10));

			Response response = FeeSubTypesServices.createFee(json.toString());
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			if (response.statusCode() == 200) {
				list = new ArrayList<String>();
				list.add(responseObj.get("id").toString());
				return list;
			} 
		} 
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured while reading");
		}
		return list;
	}
	
	
	
	/**
	 * Creates Assignment and returns id of that
	 * 
	 * @return
	 */
	public static List<String> createAssignment() {
		List<String> list = null;
		try {
			HashMap<String, String>  map = CommonUtils.getDataFromFile("./testData/csv/Assignment.csv");
			String jsonTemplate = ConfigProperties.getValue(GenericConstants.ASSIGNMENT_CREATE_PAYLOAD);
			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

			json.put("assignmentTitle", RandomStringUtils.randomAlphanumeric(10));
			json.put("assignmentDescription", "Creating from Common Methods");
			json.put("version",0);
			
			Response response = AssignmentServices.createAssignment(json.toString());
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			if (response.statusCode() == 200) {
				list = new ArrayList<String>();
				list.add(responseObj.get("id").toString());
				return list;
			} 
		} 
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured while reading");
		}
		return list;
	}
	
	
	/**
	 * Creates Message Templates and returns id of that
	 * 
	 * @return
	 */
	public static List<String> createMessageTemplates() {
		List<String> list = null;
		try {
			HashMap<String, String>  map = CommonUtils.getDataFromFile("./testData/csv/MessageTemplates.csv");
			String jsonTemplate = ConfigProperties.getValue(GenericConstants.MESSAGETEMPLATES_CREATE_PAYLOAD);
			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

			json.put("templateMessage","Creating from Common Methods");
			json.put("templateTitle","Modification Testing "+ RandomStringUtils.randomAlphanumeric(2));
			
			Response response = MessageTemplatesServices.createMessageTemplates(json.toString());
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			if (response.statusCode() == 200) {
				list = new ArrayList<String>();
				list.add(responseObj.get("id").toString());
				return list;
			} 
		} 
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured while reading");
		}
		return list;
	}
	
	/**
	 * Creates Concerns and returns id of that
	 * 
	 * @return
	 */
	public static List<String> createConcerns() {
		List<String> list = null;
		try {
			HashMap<String, String>  map = CommonUtils.getDataFromFile("./testData/csv/Concerns.csv");
			String jsonTemplate = ConfigProperties.getValue(GenericConstants.CONCERNS_CREATE_PAYLOAD);
			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

			json.put("title","Creating from common methods"+RandomStringUtils.randomAlphanumeric(10));
			json.put("actionTaken","YES");
			json.put("complaintState","ACTIONTAKEN");
			json.put("version",1);
			ConcernsServices.createConcerns(json.toString());	
			Response response =ConcernsServices.getAllConcerns();
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());

			//extracting data array from json string
			JSONArray ja_data = responseObj.getJSONArray("rows");
			int length = responseObj .length(); 
			if (response.statusCode() == 200) {
			list = new ArrayList<String>();
			//loop to get all json objects from data json array
			for(int i=0;i<length;i++){
			    JSONObject jsonn = ja_data.getJSONObject(i);
			    String id = jsonn.getString("id");
			    System.out.println("Concern ID is: "+id);
			    list.add(id);
			}
			
			return list;
			}
	} 
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured while reading");
		}
		return list;
	}
	/**
	 * Take Staff Attendance and returns id of that
	 * 
	 * @return
	 */
	public static List<String> StaffAttendance() {
		List<String> list = null;
		try {
			HashMap<String, String>  map = CommonUtils.getDataFromFile("./testData/csv/StaffAttendance.csv");
			String jsonTemplate = ConfigProperties.getValue(GenericConstants.STAFFATTENDANCE_CREATE_PAYLOAD);
			JSONObject object1 = new JSONObject();
			
			object1.put("id", "6261547495754199570");

			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

			json.put("staffDetails", object1);
			
			JSONArray newjson= new JSONArray();
			newjson.put(json);
			System.out.println(json.toString());
			System.out.println(newjson.toString());
			StaffAttendanceServices.createStaffAttendance(newjson.toString());
			 Response response =StaffAttendanceServices.getAllStaffAttendance();
			 response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());

			//extracting data array from json string
			JSONArray ja_data = responseObj.getJSONArray("rows");
			int length = responseObj .length(); 
			if (response.statusCode() == 200) {
			list = new ArrayList<String>();
			//loop to get all json objects from data json array
			for(int i=0;i<length;){
			    JSONObject jsonn = ja_data.getJSONObject(i);
			    String id = jsonn.getString("id");
			    System.out.println("Staff ID is: "+id);
			    list.add(id);
			    i++;
			}
			
			return list;
			}
	} 
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured while reading");
		}
		return list;
	}
	
	
	/**
	 * Create Concession Types and returns id of that
	 * 
	 * @return
	 */
	public static List<String> createConcessionTypes() {
		List<String> list = null;
		try {
			HashMap<String, String>  map = CommonUtils.getDataFromFile("./testData/csv/fee.csv");
			String jsonTemplate = ConfigProperties.getValue(GenericConstants.CONCESSIONTYPES_CREATE_PAYLOAD);
			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

			json.put("concessionName", RandomStringUtils.randomAlphabetic(10));

			Response response = ConcessionTypesServices.createConcession(json.toString());
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			if (response.statusCode() == 200) {
				list = new ArrayList<String>();
				list.add(responseObj.get("id").toString());
				return list;
			} 
		} 
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured while reading");
		}
		return list;
	}
	
	/**
	 * Take Student Attendance(Day) and returns id of that
	 * 
	 * @return
	 */
	public static List<String> StudentAttendance() {
		List<String> list = null;
		try {
			HashMap<String, String>  map = CommonUtils.getDataFromFile("./testData/csv/StudentAttendance.csv");
			String jsonTemplate = ConfigProperties.getValue(GenericConstants.STUDENTATTENDANCE_CREATE_PAYLOAD);
			JSONObject object1 = new JSONObject();
			
			object1.put("id", "6732094942523778916");

			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

			json.put("studentDetails", object1);
			JSONArray newjson= new JSONArray();
			newjson.put(json);
			System.out.println(newjson.toString());
			 StudentAttendanceServices.createStudentAttendance(newjson.toString());
			 Response response =StudentAttendanceServices.getAllStudentAttendance();
			 response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());

			//extracting data array from json string
			JSONArray ja_data = responseObj.getJSONArray("rows");
			int length = responseObj .length(); 
			if (response.statusCode() == 200) {
			list = new ArrayList<String>();
			//loop to get all json objects from data json array
			for(int i=0;i<length;i++){
			    JSONObject jsonn = ja_data.getJSONObject(i);
			    String id = jsonn.getString("id");
			    System.out.println("id is: "+id);
			    list.add(id);
			}
			
			return list;
			}
	} 
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured while reading");
		}
		return list;
	}
	

	/**
	 * Take Student Attendance(Periodic) and returns id of that
	 * 
	 * @return
	 */
	public static List<String> StudentPeriodAttendance() {
		List<String> list = null;
		try {
			HashMap<String, String>  map = CommonUtils.getDataFromFile("./testData/csv/PeriodicStudentAttendance.csv");
			String jsonTemplate = ConfigProperties.getValue(GenericConstants.STUDENTPERIODATTENDANCE_CREATE_PAYLOAD);

			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

			json.put("reason", RandomStringUtils.randomAlphabetic(3)+" "+RandomStringUtils.randomAlphabetic(3)+" "+RandomStringUtils.randomAlphabetic(3));

			
			JSONArray newjson= new JSONArray();
			newjson.put(json);
			System.out.println(json.toString());
			System.out.println(newjson.toString());
			StudentPeriodAttendanceServices.createStudentAttendance(newjson.toString());
			Response response =StudentPeriodAttendanceServices.getAllStudentAttendance();
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());

			//extracting data array from json string
			JSONArray ja_data = responseObj.getJSONArray("rows");
			int length = responseObj .length(); 
			if (response.statusCode() == 200) {
			list = new ArrayList<String>();
			//loop to get all json objects from data json array
			for(int i=0;i<length;i++){
			    JSONObject jsonn = ja_data.getJSONObject(i);
			    String id = jsonn.getString("id");
			    System.out.println("ID is: "+id);
			    list.add(id);
			}
			
			return list;
			}
	} 
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured while reading");
		}
		return list;
	}

	
	/**
	 * Creates Food and returns id of that
	 * 
	 * @return
	 */
	public static List<String> createFood() {
		List<String> list = null;
		try {
			HashMap<String, String>  map = CommonUtils.getDataFromFile("./testData/csv/food.csv");
			String jsonTemplate = ConfigProperties.getValue(GenericConstants.FOOD_CREATE_PAYLOAD);
			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);

			json.put("title", RandomStringUtils.randomAlphanumeric(10));
			json.put("description", "Creating from API automation");

			Response response = FoodServices.createFood(json.toString());
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());
			if (response.statusCode() == 200) {
				list = new ArrayList<String>();
				list.add(responseObj.get("id").toString());
				return list;
			} 
		} 
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured while reading");
		}
		return list;
	}
	
	
	/**
	 * Creates Subject and returns Json payload
	 * 
	 * @return
	 */
	public static JSONObject createSubject() {
		JSONObject responseObj = null;
		try {
			HashMap<String, String> map = CommonUtils
					.getDataFromFile("./testData/configuration/subjects/csv/createSubject.csv");
			String jsonTemplate = CommonUtils
					.getJsonTemplate("./testData/configuration/subjects/json/createSubject.json");
			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
			json.put("name", RandomStringUtils.randomAlphanumeric(10));

			Response response = SubjectServices.createSubject(json.toString());
			response.then().log().all();

			if (response.statusCode() == 200) {
				responseObj = new JSONObject(response.getBody().asString());
				return responseObj;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured while reading");
		}
		return responseObj;
	}
	
	/**
	 * Create Fee and returns id of that
	 * 
	 * @return
	 */
	public static List<Integer> createFee() {
		List<Integer> list = null;
		try {
			HashMap<String, String>  map = CommonUtils.getDataFromFile("./testData/csv/fee.csv");
			String jsonTemplate = ConfigProperties.getValue(GenericConstants.FEE_CREATE_PAYLOAD);

			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
			json.put("totalAmount", RandomStringUtils.randomNumeric(10));

			JSONArray newjson= new JSONArray();

			newjson.put(json);
			
			System.out.println(newjson.toString());
			FeeServices.createFee(newjson.toString());
			Response response =FeeServices.getAllFee();
			response.then().log().all();
			
			JSONObject responseObj = new JSONObject(response.getBody().asString());

			//extracting data array from json string
			JSONArray ja_data = responseObj.getJSONArray("rows");
			int length = responseObj .length(); 
			if (response.statusCode() == 200) {
			list = new ArrayList<Integer>();
			//loop to get all json objects from data json array
			for(int i=0;i<length;i++){
			    JSONObject jsonn = ja_data.getJSONObject(i);
			    Integer id = jsonn.getInt("id");
			    System.out.println("FEE ID ISS: "+id);
			    list.add(id);
			}
			
			return list;
			}
	} 
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured while reading");
		}
		return list;
	}
	
	/**
	 * Create AssignFee and returns id of that
	 * 
	 * @return
	 */
	public static List<String> createAssignFee() {
		List<String> list = null;
		try {
			HashMap<String, String>  map = CommonUtils.getDataFromFile("./testData/csv/assignfee.csv");
			String jsonTemplate = ConfigProperties.getValue(GenericConstants.ASSIGNFEE_CREATE_PAYLOAD);
			
			JSONObject json = CommonUtils.getJsonFromTemplate(jsonTemplate, map);
			
			System.out.println(json.toString());
			AssignFeeServices.createFee(json.toString());

			Response response =AssignFeeServices.getFee("1","1");
			response.then().log().all();
			

			 JSONArray values = new JSONArray(response.getBody().asString());
			 if (response.statusCode() == 200) {
			
				 list = new ArrayList<String>();
				 for (int i = 0; i < values.length(); i++) {
										JSONObject obj = values.getJSONObject(i); 
										
										if((obj.getInt("feeDetailId"))==(int)obj.getInt("feeDetailId"))
										{
										int feeid = obj.getInt("feeDetailId");
										String feid=Integer.toString(feeid); 
										list.add(feid.toString());
										String id = obj.getString("id");
										list.add(id.toString());		
										System.out.println("ID AND FEEID ARE:  " + id + ", " + feeid);
										}
				 											}
				 return list;
			 									}
			} 
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured while reading");
		}
		return list;
	}
	
	
	/**
	 * Get Branch current version by using Id
	 * 
	 * @param id
	 * @return
	 */
	public static int getBranchVersion(String id) {
		Response response = BranchServices.getBranch(id);
		response.then().log().all();
		JSONObject responseObj = new JSONObject(response.getBody().asString());
		return responseObj.getInt("version");
	}
	
	/**
	 * Get Standard current version by using id
	 * 
	 * @param id
	 * @return
	 */
	public static int getStandardVersion(String id) {
		Response response = StandardServices.getStandard(id);
		response.then().log().all();
		JSONObject responseObj = new JSONObject(response.getBody().asString());
		return responseObj.getInt("version");	
	}
	
	/**
	 * Get Section current version by using Id
	 * 
	 * @param id
	 * @return
	 */
	public static int getSectionVersion(String id) {
		Response response = SectionServices.getSection(id);
		response.then().log().all();
		JSONObject responseObj = new JSONObject(response.getBody().asString());
		return responseObj.getInt("version");
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static Map<String, String> getBranchStandardFromMapBranchStandardSections(String id) {
		Response response = AdhocServices.getBranchStandardSections(id);
		response.then().log().all();

		JSONArray responseObj = new JSONArray(response.getBody().asString());
		Map<String, String> map = new HashMap<>();
		map.put("mapId", String.valueOf(responseObj.getJSONObject(0).get("id")));
		map.put("standardId", String.valueOf(responseObj.getJSONObject(0).getJSONObject("standard").get("id")));
		return map;
	}
}
