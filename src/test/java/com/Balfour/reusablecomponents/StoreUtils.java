package com.Balfour.reusablecomponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Balfour.support.BrowserActions;
import com.Balfour.support.Utils;

/**
 * Re-Usable methods of Wishlist Functionality for Retail Sites
 * 
 * Also created re-usable methods for DemandWare business flows
 * 
 * Some of the DW methods can be optimized and use for other platform based retail site also
 * 
 * 
 */
public class StoreUtils {

	/**
	 * Search Store with City/State/ZIP as input
	 * 
	 * @param txtCityStateZip
	 *            : City/State/ZIP WebElement
	 * 
	 * @param cityStateZip
	 *            : City Name /State Name /ZIP Code
	 * 
	 * @param btnSearch
	 *            : Store Search Button WebElement
	 * 
	 * @param driver
	 *            : WebDriver Instance
	 * @throws Exception 
	 */
	final public static void searchStore(WebElement txtCityStateZip, String cityStateZip, WebElement btnSearch, WebDriver driver) throws Exception {

		//final long startTime = StopWatch.startTime();

		BrowserActions.typeOnTextField(txtCityStateZip, cityStateZip, driver, "Search Store");
		BrowserActions.clickOnElement(btnSearch, driver, "Store Details Link");
		//Log.trace("Search Store.", StopWatch.elapsedTime(startTime));

		Utils.waitForPageLoad(driver);

	}// searchStore

	/**
	 * Select the Store Details link from search suggestion - DW application can use this
	 * 
	 * @param storeDetailsFromSuggestion
	 *            : CSS Locator for Store Detail link from Suggestion . String format - ul[class=''] li:nth-child($) a
	 * 
	 * @param whichSuggstnToClick
	 *            : $ symbol in storeDetailsFromSuggestion param will be replaced with this string. Ex: "1" - ul[class=''] li:nth-child(1) a
	 * 
	 * @param driver
	 *            : WebDriver Instance
	 * @throws Exception 
	 */
	final public static void selectStoreDetailsFromStoreSuggestions(String storeDetailsFromSuggestion, String whichSuggstnToClick, WebDriver driver) throws Exception {

		//final long startTime = StopWatch.startTime();
		String replacedString = storeDetailsFromSuggestion.replace("$", whichSuggstnToClick);

		BrowserActions.clickOnElement(replacedString, driver, "Store Details Link");
		//Log.trace("Clicked '" + whichSuggstnToClick + "' Store Details Link from Suggestion.", StopWatch.elapsedTime(startTime));

		Utils.waitForPageLoad(driver);

	}// selectStoreDetailsFromStoreSuggestions

	/**
	 * Select the Get Directions link from search suggestion - DW application can use this
	 * 
	 * @param storeDirectionsFromSuggestion
	 *            : CSS Locator for Store Direction link from Suggestion . String format - ul[class=''] li:nth-child($) a
	 * 
	 * @param whichSuggstnToClick
	 *            : $ symbol in storeDirectionsFromSuggestion param will be replaced with this string. Ex: "1" - ul[class=''] li:nth-child(1) a
	 * 
	 * @param driver
	 *            : WebDriver Instance
	 * @throws Exception 
	 */
	final public static void selectGetDirectionsFromStoreSuggestions(String storeDirectionsFromSuggestion, String whichSuggstnToClick, WebDriver driver) throws Exception {

		//final long startTime = StopWatch.startTime();
		String replacedString = storeDirectionsFromSuggestion.replace("$", whichSuggstnToClick);

		BrowserActions.clickOnElement(replacedString, driver, "Store Get Direction Link");
		//Log.trace("Clicked '" + whichSuggstnToClick + "' Store Get Direction Link from Suggestion.", StopWatch.elapsedTime(startTime));

		Utils.waitForPageLoad(driver);

	}// selectGetDirectionsFromStoreSuggestions

	/**
	 * Click "Make My Store" link from search suggestion - DW application can use this
	 * 
	 * @param makeMyStoreFromSuggestion
	 *            : CSS Locator for Make My Store link from Suggestion . String format - ul[class=''] li:nth-child($) a
	 * 
	 * @param whichSuggstnToClick
	 *            : $ symbol in srchRsltLoc param will be replaced with this string. Ex: "1" - ul[class=''] li:nth-child(1) a
	 * 
	 * @param driver
	 *            : WebDriver Instance
	 * @throws Exception 
	 */
	final public static void makeMyStoreFromStoreSuggestions(String makeMyStoreFromSuggestion, String whichSuggstnToClick, WebDriver driver) throws Exception {

		//final long startTime = StopWatch.startTime();
		String replacedString = makeMyStoreFromSuggestion.replace("$", whichSuggstnToClick);

		BrowserActions.clickOnElement(replacedString, driver, "Make My Store");
		//Log.trace("Clicked '" + whichSuggstnToClick + "' Make My Store from Suggestion.", StopWatch.elapsedTime(startTime));

		Utils.waitForPageLoad(driver);

	}// makeMyStoreFromStoreSuggestions

	/**
	 * Click STS Radio - DW application can use this
	 * 
	 * @param radioSTS
	 *            : STS Radio Button WebElement
	 * @param driver
	 *            : WebDriver Instance
	 * @throws Exception 
	 */
	final public static void selectSTSOption(WebElement radioSTS, WebDriver driver) throws Exception {

		//final long startTime = StopWatch.startTime();

		BrowserActions.clickOnElement(radioSTS, driver, "STS Option");
		//Log.trace("Clicked STS Radio button.", StopWatch.elapsedTime(startTime));

		Utils.waitForPageLoad(driver);

	}// selectSTSOption

	/**
	 * Click STS Choose Store Button - DW application can use this
	 * 
	 * @param btnChooseStore
	 *            : Choose/Change Store Button WebElement
	 * @param driver
	 *            : WebDriver Instance
	 * @throws Exception 
	 */
	final public static void selectSTSChooseChangeStore(WebElement btnChooseStore, WebDriver driver) throws Exception {

		//final long startTime = StopWatch.startTime();

		BrowserActions.clickOnElement(btnChooseStore, driver, "Choose Store");
		//Log.trace("Clicked STS Radio button.", StopWatch.elapsedTime(startTime));

		Utils.waitForPageLoad(driver);

	}// selectSTSChooseChangeStore

	/**
	 * Click "Select This Store" link from search suggestion - DW application can use this
	 * 
	 * @param selectThisStoreFromSuggestion
	 *            : CSS Locator for Select This Store link from Suggestion . String format - ul[class=''] li:nth-child($) a
	 * 
	 * @param whichSuggstnToClick
	 *            : $ symbol in srchRsltLoc param will be replaced with this string. Ex: "1" - ul[class=''] li:nth-child(1) a
	 * 
	 * @param driver
	 *            : WebDriver Instance
	 * @throws Exception 
	 */
	final public static void selectStoreAsShippingStore(String selectThisStoreFromSuggestion, String whichSuggstnToClick, WebDriver driver) throws Exception {

		//final long startTime = StopWatch.startTime();
		String replacedString = selectThisStoreFromSuggestion.replace("$", whichSuggstnToClick);

		BrowserActions.clickOnElement(replacedString, driver, "Select This Store");
		//Log.trace("Clicked '" + whichSuggstnToClick + "' Select This Store from Suggestion.", StopWatch.elapsedTime(startTime));

		Utils.waitForPageLoad(driver);

	}// selectStoreAsShippingStore

}// Store_Util