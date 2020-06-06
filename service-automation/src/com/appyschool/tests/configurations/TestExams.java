package com.appyschool.tests.configurations;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.appyschool.common.CommonMethods;
import com.appyschool.common.CommonUtils;
import com.appyschool.common.DataProviderArguments;
import com.appyschool.common.SoftAssertionBase;
import com.appyschool.services.BranchServices;
import com.jayway.restassured.response.Response;

public class TestExams extends SoftAssertionBase {}
