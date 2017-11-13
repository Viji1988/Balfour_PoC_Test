package com.Balfour.reusablecomponents;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Balfour.support.BrowserActions;
import com.Balfour.support.Log;
import com.Balfour.support.StopWatch;
import com.Balfour.support.Utils;

public class BusinessManager {
	
	 // Description: login to Business Manager Web Application.
	// hard coded search box element
	 
	
	@SuppressWarnings("rawtypes")
	final public static void login_businessmanager(LinkedHashMap <String, String> loginDetails,  WebDriver driver) throws Exception {

		Set loginDetailsSet = loginDetails.entrySet();
		Iterator loginDetailsIterator = loginDetailsSet.iterator();

		while (loginDetailsIterator.hasNext()) {

			Map.Entry mapEntry = (Map.Entry) loginDetailsIterator.next();
			String[] keyWithElementTypeAndDescriptionAndTextToType = mapEntry.getKey().toString().split("_");
			String locator = mapEntry.getValue().toString();

			switch (keyWithElementTypeAndDescriptionAndTextToType[0].toLowerCase()) {
				case "type":
					BrowserActions.typeOnTextField(locator,
							keyWithElementTypeAndDescriptionAndTextToType[2],
							driver,
							keyWithElementTypeAndDescriptionAndTextToType[1]);
					break;
				case "click":
					BrowserActions.clickOnElement(locator, driver, keyWithElementTypeAndDescriptionAndTextToType[1]);
					break;
				default:
					Log.trace("Option not matched - please read Method document to pass correct form of parameter. Try: Type/Click/Select");
					break;
			}// Switch
			
			Utils.waitForPageLoad(driver);
		} //while
		
		
	}
	
	
	@SuppressWarnings("rawtypes")
	final public static void simple_search_customer(LinkedHashMap <String, String> loginDetails,LinkedHashMap <String, String> searchDetails, WebElement element, WebDriver driver, String customer_name_no, String site) throws Exception {
		final long startTime = StopWatch.startTime();

		login_businessmanager(loginDetails, driver);
		
		Set searchDetailsSet = searchDetails.entrySet();
		Iterator searchDetailsIterator = searchDetailsSet.iterator();

		while (searchDetailsIterator.hasNext()) {

			Map.Entry mapEntry = (Map.Entry) searchDetailsIterator.next();
			String[] keyWithElementTypeAndDescriptionAndTextToType = mapEntry.getKey().toString().split("_");
			String locator = mapEntry.getValue().toString();
			
			switch (keyWithElementTypeAndDescriptionAndTextToType[0].toLowerCase()) {
				case "type":
					BrowserActions.typeOnTextField(locator, keyWithElementTypeAndDescriptionAndTextToType[2], driver, keyWithElementTypeAndDescriptionAndTextToType[1]);
					break;
				case "click":
					BrowserActions.clickOnElement(locator, driver, keyWithElementTypeAndDescriptionAndTextToType[1]);
					break;
				default:
					Log.trace("Option not matched - please read Method document to pass correct form of parameter. Try: Type/Click/Select", StopWatch.elapsedTime(startTime));
					break;
			}// Switch
		
			Utils.waitForPageLoad(driver);
		}
		get_customer_search_data();
	}
	
	
	
	  /*Description:
	      Get the Searched customer details from BM.
	  Return Class Variables
	       @customer_info: Key [created_date, enabled, last_login, customer_no]
	       @@profile_info: Key [salutation, title, company, job_title, first_name, last_name, name_suffix, gender,
	                            birthday, second_name, email_id, next_birthday, home_phn_no, business_phn_no,
	                            mobile_no, fax_no]
	 
	      @sb_info: Key [id, loyalty_id, membership_tier, enroll_date, enroll_loc, do_not_call, do_not_text,
	                     do_not_mail, total_points_avail, total_lifetime_points_earned, total_lifetime_spend]
	  
	      @address_summary: Key [address_summary]*/
	  

	final public static String get_customer_search_data(){	
		
		return null;
		
	}
	
	
	
	
	
}
