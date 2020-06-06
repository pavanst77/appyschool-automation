package com.appyschool.common;

import static com.jayway.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;

public class CommonUtils {
	private static String cookie = null;
	
	static {
		RestAssured.baseURI = ConfigProperties.getValue(GenericConstants.BASE_URL);
		cookie = "JSESSIONID=" + ConfigProperties.getValue(GenericConstants.COOKIE);
	}

	/**
	 * 
	 * @param newHeader
	 * @return
	 */
	public static RequestSpecification getRequestSpec() {
		return given().log().all().headers("Cookie", cookie);
	}
	
	/**
	 * Reading JSON File
	 */
	public static String getJsonTemplate(String path) {
		byte[] jsonData = null;
		try {
			jsonData = Files.readAllBytes(Paths.get(path));
		} catch (IOException e) {

			e.printStackTrace();
		}
		String json = new String(jsonData);
		return json;
	}
	
	/**
	 * 
	 * @param jsonTemplate
	 * @param map
	 * @return
	 */
	public static JSONObject getJsonFromTemplate(String jsonTemplate, HashMap<String, String> map) {
		JSONObject json = null;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			if (key.equals("testcaseId")) {
				continue;
			} 
			String strValue = entry.getValue();
			jsonTemplate = jsonTemplate.replace(key , strValue);
		}

		try {
			json = new JSONObject(jsonTemplate);
		} catch (JSONException e) {

			e.printStackTrace();
		}
		return json;
	}
	
	
	/**
	 * 
	 * @param jsonTemplate
	 * @param map
	 * @return
	 */
	public static JSONArray getJsonArrayFromTemplate(String jsonTemplate, HashMap<String, String> map) {
		JSONArray jArray = null;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			if (key.equals("testcaseId")) {
				continue;
			} 
			String strValue = entry.getValue();
			jsonTemplate = jsonTemplate.replace(key , strValue);
		}

		try {
			jArray = new JSONArray(jsonTemplate);
		} catch (JSONException e) {

			e.printStackTrace();
		}
		return jArray;

	}

	/**
	 * 
	 * @param testMethod
	 * @return
	 * @throws Exception
	 */
	@DataProvider(name = "getDataFromFile")
	public static Iterator<Object[]> getDataFromFile(Method testMethod) throws Exception {
		Map<String, String> arguments = resolveDataProviderArguments(testMethod);
		ArrayList<HashMap<String, String>> maplist = (ArrayList<HashMap<String, String>>) getRawLinesFromFile(arguments.get("dataPath"));
		List<Object[]> data = new ArrayList<Object[]>();
		for (HashMap<String, String> map : maplist) {
			data.add(new Object[] { map });
		}
		return data.iterator();
	}
	
	/**
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String, String> getDataFromFile(String filePath) throws Exception {
		ArrayList<HashMap<String, String>> maplist = (ArrayList<HashMap<String, String>>) getRawLinesFromFile(filePath);
		return maplist.get(0);
	}

	/**
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static List<HashMap<String, String>> getRawLinesFromFile(String filePath) throws Exception {
		InputStream is = new FileInputStream(new File(filePath));
		List<String> lines = IOUtils.readLines(is, "UTF-8");
		String header = lines.get(0);
		String[] head = header.split(",");
		ArrayList<HashMap<String, String>> maplist = new ArrayList<HashMap<String, String>>();
		for (int i = 1; i <= lines.size() - 1; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			String line = lines.get(i);
			String[] values = line.split(",");
			System.out.println("num values" + values.length);
			int headpos = 0;
			for (String s : head) {
				String val = values[headpos++];
				map.put(s, val);
			}
			maplist.add(map);
			headpos = 0;
		}
		is.close();
		return maplist;
	}

	/**
	 * 
	 * @param testMethod
	 * @return
	 * @throws Exception
	 */
	protected static Map<String, String> resolveDataProviderArguments(Method testMethod) throws Exception {
		if (testMethod == null)
			throw new IllegalArgumentException("Test Method context cannot be null.");

		DataProviderArguments args = testMethod.getAnnotation(DataProviderArguments.class);
		if (args == null)
			throw new IllegalArgumentException("Test Method context has no DataProviderArguments annotation.");
		if (args.value() == null)
			throw new IllegalArgumentException("Test Method context has a malformed DataProviderArguments annotation.");
		Map<String, String> arguments = new HashMap<String, String>();
		arguments.put("dataPath", args.value());
		return arguments;
	}
}
