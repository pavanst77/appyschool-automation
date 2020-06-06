package com.appyschool.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SoftAssertionBase {

	private static Map verificationFailuresMap = new HashMap();
	
	public enum Status {
		PASSED(1),
		FAILED(2);
		int status;

		private Status(int status) {
			this.status = status;
		}

		public int getStatus() {
			return status;
		}
		
		public static Status getResult(int key) {
				for (Status status : values()) {
					if (status.getStatus() == key) {
						return status;
					}
				}
			return null;
		}
	}
	
	@BeforeMethod
	public void beforeMethod(ITestResult result) {
		System.out.println("============== Test Case -- Begin ==============");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		String testCaseName = result.getMethod().getMethodName();
		System.out.println("============== Test Case - " + testCaseName + "- End ============== : " + Status.getResult(result.getStatus()));
	}

	public void verifyTrue(Boolean b, String msg) {
		try {
			Assert.assertTrue(b.booleanValue());
		} catch (Error e) {
		    System.out.println("Actual: " + b.booleanValue() + "Expected: " + Boolean.TRUE);
			addVerificationFailure(e);
			Reporter.log(msg + "");
			System.out.println(msg);
		}
	}

	public void verifyTrue(Boolean b) {
		try {
			Assert.assertTrue(b.booleanValue());
		} catch (Error e) {
		    System.out.println("Actual: " + b.booleanValue() + "\n" + "Expected: " + Boolean.TRUE);
			addVerificationFailure(e);
			Reporter.log(e.getMessage());
		}
	}

	public void verifyFalse(Boolean b) {
		try {
			Assert.assertFalse(b.booleanValue());
		} catch (Error e) {
		    System.out.println("Actual: " + b.booleanValue() + "\n" + "Expected: " + Boolean.FALSE);
			addVerificationFailure(e);
			Reporter.log(e.getMessage());
		}
	}

	public void verifyTrue(String elementDefinition, String elementLocator,
			Boolean b, String pageURL) {
		try {
			Assert.assertTrue(b.booleanValue());
		} catch (Error e) {
			addVerificationFailure(e);
			Reporter.log("Verification failed for element: "
					+ elementDefinition + ", Element locator: "
					+ elementLocator + "On page: " + pageURL);
		}
	}

	public void verifyTrue(String elementDefinition, String elementLocator,
			boolean isElementPresent, int actualElementCount,
			int expectedElementCount, String pageURL) {
		try {
			Assert.assertTrue(isElementPresent);
		} catch (Error e) {
			addVerificationFailure(e);
			Reporter.log("Verification failed for element: "
					+ elementDefinition + ", Element locator: "
					+ elementLocator + "On page: " + pageURL);
		}
		try {
			Assert.assertTrue(actualElementCount == expectedElementCount);
		} catch (Error e) {
			addVerificationFailure(e);
			Reporter.log("Verification count failed for element: "
					+ elementDefinition + ", Element locator: "
					+ elementLocator + ", Expected Element Count: "
					+ expectedElementCount + ", while Actual Element Count: "
					+ actualElementCount + " On page: " + pageURL);
		}
	}

	public void verifyEquals(String actual, String expected, String msg) {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Error e) {
		    System.out.println("Actual: " + actual + "\n" + "Expected: " + expected);
			addVerificationFailure(e);
			Reporter.log(msg + "");
		}
	}

    public void verifyEquals(String actual, String expected) {
        try {
            Assert.assertEquals(actual, expected);
        } catch (Error e) {
            System.out.println("Actual: " + actual + "\n" + "Expected: " + expected);
            addVerificationFailure(e);
            Reporter.log(e.getMessage());
        }
    }
	
	public void verifyEqualsIgnoreCase(String actual, String expected) {
		String actualInLower = (actual!=null)?actual.toLowerCase():null;
		String expectedInLower = (expected!=null)?actual.toLowerCase():null;
		try {
			Assert.assertEquals(actualInLower, expectedInLower);
		} catch (Error e) {
		    System.out.println("Actual: " + actualInLower + "\n" + "Expected: " + expectedInLower);
			addVerificationFailure(e);
			Reporter.log(e.getMessage());
		}
	}
	public void verifyEquals(List<String> actual, List<String> expected) {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Error e) {
		    System.out.println("Actual: " + actual + "\n" + "Expected: " + expected);
			addVerificationFailure(e);
			Reporter.log(e.getMessage());
		}
	}

	public void verifyEquals(int actual, int expected) {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Error e) {
		    System.out.println("Actual: " + actual + "\n" + "Expected: " + expected);
			addVerificationFailure(e);
			Reporter.log(e.getMessage());
		}
	}
	
	public void verifyEquals(Boolean actual, Boolean expected) {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Error e) {
		    System.out.println("Actual: " + actual + "\n" + "Expected: " + expected);
			addVerificationFailure(e);
			Reporter.log(e.getMessage());
		}
	}

	public void verifyEquals(Object actual, Object expected, String msg) {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Error e) {
		    System.out.println("Actual: " + actual + "\n" + "Expected: " + expected);
			addVerificationFailure(e);
			Reporter.log(msg + "");
		}
	}

	public void verifyNotEquals(Object actual, Object expected, String msg) {
		try {
			Assert.assertNotEquals(actual, expected);
		} catch (Error e) {
		    System.out.println("Actual: " + actual + "\n" + "Expected: " + expected);
			addVerificationFailure(e);
			Reporter.log(msg + "");
		}
	}

	public void verifyEquals(String actual[], String expected[], String msg) {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Error e) {
		    System.out.println("Actual: " + actual + "\n" + "Expected: " + expected);
			addVerificationFailure(e);
			Reporter.log(msg + "");
		}
	}

	public void verifyEquals(String actual[], String expected[]) {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Error e) {
		    System.out.println("Actual: " + actual + "\n" + "Expected: " + expected);
			addVerificationFailure(e);
			Reporter.log(e + "");
		}
	}

	public void verifyEquals(Object actual[], Object expected[], String msg) {
		try {
			Assert.assertEquals(((Object) (actual)), ((Object) (expected)));
		} catch (Error e) {
		    System.out.println("Actual: " + actual + "\n" + "Expected: " + expected);
			addVerificationFailure(e);
			Reporter.log(msg + "");
		}
	}

	public static List getVerificationFailures() {
		List verificationFailures = (List) verificationFailuresMap.get(Reporter.getCurrentTestResult());
		return verificationFailures == null ? new ArrayList() : verificationFailures;
	}

	private static void addVerificationFailure(Throwable e) {
		List verificationFailures = getVerificationFailures();
		verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
		verificationFailures.add(e);
	}

}