package com.Balfour.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Util class consists wait for page load,page load with user defined max time
 * and is used globally in all classes and methods
 * 
 */
public class Utils {
	private static EnvironmentPropertiesReader configProperty = EnvironmentPropertiesReader.getInstance();
	//private static EnvironmentPropertiesReader envProperty = EnvironmentPropertiesReader.getInstance("env.properties");
	
	public static int maxElementWait = 5;

	/**
	 * waitForPageLoad waits for the page load with default page load wait time
	 * 
	 * @param driver
	 *            : Webdriver
	 */
	public static void waitForPageLoad(final WebDriver driver) {
		waitForPageLoad(driver, WebDriverFactory.maxPageLoadWait);
	}

	/**
	 * waitForPageLoad waits for the page load with custom page load wait time
	 * 
	 * @param driver
	 *            : Webdriver
	 * @param maxWait
	 *            : Max wait duration
	 */
	public static void waitForPageLoad(final WebDriver driver, int maxWait) {
		long startTime = StopWatch.startTime();
		FluentWait<WebDriver> wait = new WebDriverWait(driver, maxWait).pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(StaleElementReferenceException.class).withMessage("Page Load Timed Out");
		try {

			if (configProperty.getProperty("documentLoad").equalsIgnoreCase("true"))
				wait.until(WebDriverFactory.documentLoad);

			if (configProperty.getProperty("imageLoad").equalsIgnoreCase("true"))
				wait.until(WebDriverFactory.imagesLoad);

			if (configProperty.getProperty("framesLoad").equalsIgnoreCase("true"))
				wait.until(WebDriverFactory.framesLoad);

			String title = driver.getTitle().toLowerCase();
			String url = driver.getCurrentUrl().toLowerCase();
			Log.event("Page URL:: " + url);

			if ("the page cannot be found".equalsIgnoreCase(title) || title.contains("is not available")
					|| url.contains("/error/") || url.toLowerCase().contains("/errorpage/")) {
				Assert.fail("Site is down. [Title: " + title + ", URL:" + url + "]");
			}
		} catch (TimeoutException e) {
			driver.navigate().refresh();
			wait.until(WebDriverFactory.documentLoad);
			wait.until(WebDriverFactory.imagesLoad);
			wait.until(WebDriverFactory.framesLoad);
		}
		Log.event("Page Load Wait: (Sync)", StopWatch.elapsedTime(startTime));

	} // waitForPageLoad


	/**
	 * Wait until element disappears in the page
	 * 
	 * @param driver
	 *            - driver instance
	 * @param element
	 *            - webelement to wait to have disaapear
	 * @return true if element is not appearing in the page
	 */
	public static boolean waitUntilElementDisappear(WebDriver driver, final WebElement element) {
		final boolean isNotDisplayed;

		WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, WebDriverFactory.maxPageLoadWait);
		isNotDisplayed = wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver webDriver) {
				boolean isPresent = false;
				try {
					if (element.isDisplayed()) {
						isPresent = false;
						Log.event("Element " + element.toString() + ", is still visible in page");
					}
				} catch (Exception ex) {
					isPresent = true;
					Log.event("Element " + element.toString() + ", is not displayed in page ");
					return isPresent;
				}
				return isPresent;
			}
		});
		return isNotDisplayed;
	}


	/**
	 * To get the test orientation
	 * 
	 * <p>
	 * if test run on sauce lab device return landscape or portrait or valid
	 * message, otherwise check local device execution and return landscape or
	 * portrait or valid message
	 * 
	 * @return dataToBeReturned - portrait or landscape or valid message
	 */
	public static String getTestOrientation() {
		String dataToBeReturned = null;
		boolean checkExecutionOnSauce = false;
		boolean checkDeviceExecution = false;
		checkExecutionOnSauce = (System.getProperty("SELENIUM_DRIVER") != null
				|| System.getenv("SELENIUM_DRIVER") != null) ? true : false;

		if (checkExecutionOnSauce) {
			checkDeviceExecution = ((System.getProperty("runUserAgentDeviceTest") != null)
					&& (System.getProperty("runUserAgentDeviceTest").equalsIgnoreCase("true"))) ? true : false;
			if (checkDeviceExecution) {
				dataToBeReturned = (System.getProperty("deviceOrientation") != null)
						? System.getProperty("deviceOrientation") : "no sauce run system variable: deviceOrientation ";
			} else {
				dataToBeReturned = "sauce browser test: no orientation";
			}
		} else {
			checkDeviceExecution = (configProperty.hasProperty("runUserAgentDeviceTest")
					&& (configProperty.getProperty("runUserAgentDeviceTest").equalsIgnoreCase("true"))) ? true : false;
			if (checkDeviceExecution) {
				dataToBeReturned = configProperty.hasProperty("deviceOrientation")
						? configProperty.getProperty("deviceOrientation")
								: "no local run config variable: deviceOrientation ";
			} else {
				dataToBeReturned = "local browser test: no orientation";
			}
		}
		return dataToBeReturned;
	}

	/**
	 * To wait for the specific element on the page
	 * 
	 * @param driver
	 *            : Webdriver
	 * @param element
	 *            : Webelement to wait for
	 * @return boolean - return true if element is present else return false
	 */
	public static boolean waitForElement(WebDriver driver, WebElement element) {
		return waitForElement(driver, element, maxElementWait);
	}

	/**
	 * To wait for the specific element on the page
	 * 
	 * @param driver
	 *            : Webdriver
	 * @param element
	 *            : Webelement to wait for
	 * @param maxWait
	 *            : Max wait duration
	 * @return boolean - return true if element is present else return false
	 */
	public static boolean waitForElement(WebDriver driver, WebElement element, int maxWait) {
		boolean statusOfElementToBeReturned = false;
		long startTime = StopWatch.startTime();
		WebDriverWait wait = new WebDriverWait(driver, maxWait);
		//Log.message("---------------------------------------------"+element);
		try {
			WebElement waitElement = wait.until(ExpectedConditions.visibilityOf(element));
			if (waitElement.isDisplayed() && waitElement.isEnabled()) {
				statusOfElementToBeReturned = true;
				Log.event("Element is displayed:: " + element.toString());
			}
		} catch (Exception e) {
			statusOfElementToBeReturned = false;
			Log.event("Unable to find a element after " + StopWatch.elapsedTime(startTime) + " sec ==> "
					+ element.toString());
		}
		return statusOfElementToBeReturned;
	}

	public static WebDriver switchWindows(WebDriver driver, String windowToSwitch, String opt,
			String closeCurrentDriver) throws Exception {

		WebDriver currentWebDriver = driver;
		WebDriver assingedWebDriver = driver;
		boolean windowFound = false;
		ArrayList<String> multipleWindows = new ArrayList<String>(assingedWebDriver.getWindowHandles());

		for (int i = 0; i < multipleWindows.size(); i++) {

			assingedWebDriver.switchTo().window(multipleWindows.get(i));

			if (opt.equals("title")) {
				if (assingedWebDriver.getTitle().trim().equals(windowToSwitch)) {
					windowFound = true;
					break;
				}
			} else if (opt.equals("url")) {
				if (assingedWebDriver.getCurrentUrl().contains(windowToSwitch)) {
					windowFound = true;
					break;
				}
			} // if

		} // for

		if (!windowFound)
			throw new Exception("Window: " + windowToSwitch + ", not found!!");
		else {
			if (closeCurrentDriver.equals("true"))
				currentWebDriver.close();
		}

		return assingedWebDriver;

	}// switchWindows

	/**
	 * Switching between tabs or windows in a browser
	 * 
	 * @param driver -
	 */
	public static void switchToNewWindow(WebDriver driver) {
		String winHandle = driver.getWindowHandle();
		for (String index : driver.getWindowHandles()) {
			if (!index.equals(winHandle)) {
				driver.switchTo().window(index);
				break;
			}
		}
		if (!((RemoteWebDriver) driver).getCapabilities().getBrowserName().matches(".*safari.*")) {
			((JavascriptExecutor) driver).executeScript("if(window.screen){window.moveTo(0, 0); window.resizeTo(window.screen.availWidth, window.screen.availHeight);};");
		}
	}

	/**
	 * To compare two HashMap values,then print unique list value and print
	 * missed list value
	 * 
	 * @param expectedList
	 *            - expected element list
	 * @param actualList
	 *            - actual element list
	 * @return statusToBeReturned - returns true if both the lists are equal,
	 *         else returns false
	 */
	public static boolean compareTwoHashMap(Map<String, String> expectedList, Map<String, String> actualList) {
		List<String> missedkey = new ArrayList<String>();
		HashMap<String, String> missedvalue = new HashMap<String, String>();
		try {
			for (String k : expectedList.keySet()) {
				if (!(actualList.get(k).equalsIgnoreCase(expectedList.get(k)))) {
					missedvalue.put(k, actualList.get(k));
					Log.event("Missed Values:: " + missedvalue);
					return false;
				}
			}
			for (String y : actualList.keySet()) {
				if (!expectedList.containsKey(y)) {
					missedkey.add(y);
					Log.event("Missed keys:: " + missedkey);
					return false;
				}
			}
		} catch (NullPointerException np) {
			return false;
		}
		return true;
	}

	/**
	 * To compare two array list values,then print unique list value and print
	 * missed list value
	 * 
	 * @param expectedElements - expected element list
	 * @param actualElements - actual element list
	 * @return statusToBeReturned - returns true if both the lists are equal,
	 *         else returns false
	 */
	public static boolean compareTwoList(List<String> expectedElements, List<String> actualElements) {
		boolean statusToBeReturned = false;
		List<String> uniqueList = new ArrayList<String>();
		List<String> missedList = new ArrayList<String>();
		for (String item : expectedElements) {
			if (actualElements.contains(item)) {
				uniqueList.add(item);
			} else {
				missedList.add(item);
			}
		}
		Collections.sort(expectedElements);
		Collections.sort(actualElements);
		if (expectedElements.equals(actualElements)) {
			Log.event("All elements checked on this page:: " + uniqueList);
			statusToBeReturned = true;
		} else {
			Log.event("Missing element on this page:: " + missedList);
			statusToBeReturned = false;
		}
		return statusToBeReturned;
	}

	/**
	 * Verify the css property for an element
	 * 
	 * @param element
	 *            - WebElement for which to verify the css property
	 * @param cssProperty
	 *            - the css property name to verify
	 * @param actualValue
	 *            - the actual css value of the element
	 * @return boolean
	 */
	public static boolean verifyCssPropertyForElement(WebElement element, String cssProperty, String actualValue) {
		boolean result = false;

		String actualClassProperty = element.getCssValue(cssProperty);

		if (actualClassProperty.contains(actualValue)) {
			result = true;
		}
		return result;
	}

	/**
	 * To get the value of an input field.
	 * 
	 * @param element
	 *            - the input field you need the value/text of
	 * @param driver
	 *            -
	 * @return text of the input's value
	 */
	public static String getValueOfInputField(WebElement element, WebDriver driver) {
		String sDataToBeReturned = null;
		if (Utils.waitForElement(driver, element)) {
			sDataToBeReturned = element.getAttribute("value");
		}
		return sDataToBeReturned;
	}	


	/**
	 * To wait for the specific element which is in disabled state on the page
	 * 
	 * @param driver - current driver object
	 * @param element - disabled webelement
	 * @param maxWait - duration of wait in seconds
	 * @return boolean - return true if disabled element is present else return false
	 */
	public static boolean waitForDisabledElement(WebDriver driver, WebElement element, int maxWait) {
		boolean statusOfElementToBeReturned = false;
		long startTime = StopWatch.startTime();
		WebDriverWait wait = new WebDriverWait(driver, maxWait);
		try {
			WebElement waitElement = wait.until(ExpectedConditions.visibilityOf(element));
			if (!waitElement.isEnabled()) {
				statusOfElementToBeReturned = true;
				Log.event("Element is displayed and disabled:: " + element.toString());
			}
		} catch (Exception ex) {
			statusOfElementToBeReturned = false;
			Log.event("Unable to find disabled element after " + StopWatch.elapsedTime(startTime) + " sec ==> " + element.toString());
		}
		return statusOfElementToBeReturned;
	}
	 
	/** To know which environment should run against the script
	 * 
	 * @return String - return prod site/Qa site value 
	 */
	public static String getExecutionApp() { 
			String dataToBeReturned = null; 
			if (configProperty.getProperty("ExecuteOnProductionServer").equalsIgnoreCase("true")) {
				dataToBeReturned = "ProdSite"; 
			} else { 
				dataToBeReturned = "QAsites"; 
			} 
			Log.event("Running platform type:: " + dataToBeReturned); 
			return dataToBeReturned; 
		}// getRunPlatForm 


	/** 
	 * To get run platform from the config.Property files 
	 * 
	 * @return String - return mobile/desktop value 
	 */
	public static String getRunPlatForm() { 
	configProperty.hasProperty("testlinkUrl");
	
		String dataToBeReturned = null; 
		if ((configProperty.hasProperty("runMobile") 
				&& configProperty.getProperty("runMobile").equalsIgnoreCase("true")) 
				|| (configProperty.hasProperty("runUserAgentDeviceTest") 
						&& configProperty.getProperty("runUserAgentDeviceTest").equalsIgnoreCase("true"))) {
			dataToBeReturned = "mobile"; 
		} else { 
			dataToBeReturned = "desktop"; 
		} 
		Log.event("Running platform type:: " + dataToBeReturned); 
		return dataToBeReturned; 
	}// getRunPlatForm 


}
