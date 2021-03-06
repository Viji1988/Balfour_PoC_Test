package com.Balfour.reusablecomponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Balfour.support.BrowserActions;
import com.Balfour.support.Log;
import com.Balfour.support.Utils;

/**
 * Re-Usable methods of Add/Remove Bag Functionality for Retail Sites
 * 
 * Also created re-usable methods for DemandWare business flows
 * 
 * Some of the DW methods can be optimized and use for other platform based retail site also
 * 
 */
public class AddRemoveBagUtils {

	/**
	 * Click Add To Bag button
	 * 
	 * @param btnAddToBag
	 *            : WebElement for Add To Bag
	 * 
	 * @param driver
	 *            : WebDriver instance
	 * @throws Exception 
	 */
	final public static void addToBag(WebElement btnAddToBag, WebDriver driver) throws Exception {

		//final long startTime = StopWatch.startTime();

		BrowserActions.clickOnElement(btnAddToBag, driver, "Add To Bag");
		//Log.trace("Clicked Search button.", StopWatch.elapsedTime(startTime));

		Utils.waitForPageLoad(driver);

	}// addToBag

	/**
	 * Delete Product/Item from Bag
	 * 
	 * @param btnItemPrdDel
	 *            : Item/Product Delete WebElement
	 * 
	 * @param driver
	 *            : WebDriver Instance
	 * @throws Exception 
	 */
	final public static void deleteItemFromBag(WebElement btnItemPrdDel, WebDriver driver) throws Exception {

		//final long startTime = StopWatch.startTime();

		BrowserActions.clickOnElement(btnItemPrdDel, driver, "Product/Item Delete");
		//Log.trace("Clicked Delete 'X' Product/Item button.", StopWatch.elapsedTime(startTime));

		Utils.waitForPageLoad(driver);

	}// deleteItemFromBag

	/**
	 * Delete desired Product/Item from Bag
	 * 
	 * @param btnItemPrdDel
	 *            : Product/Item Delete String [CSS Selector]
	 * 
	 * @param itemPrdPosition
	 *            : $ symbol in btnItemPrdDel param will be replaced with this string. Ex: "1" - ul[class=''] li:nth-child(1) a
	 * 
	 * @param driver
	 *            : WebDriver Instance
	 * @throws Exception 
	 */
	final public static void deleteItemFromBagAtPosition(String btnItemPrdDel, String itemPrdPosition, WebDriver driver) throws Exception {

		//final long startTime = StopWatch.startTime();
		String replacedString = btnItemPrdDel.replace("$", itemPrdPosition);

		BrowserActions.clickOnElement(replacedString, driver, "Product/Item Delete");
		//Log.trace("Clicked Delete 'X' Product/Item button.", StopWatch.elapsedTime(startTime));
		Log.trace("Clicked Delete 'X' Product/Item button.");

		Utils.waitForPageLoad(driver);

	}// deleteItemFromBagAtPosition

	/**
	 * Delete desired number of Product/Item
	 * 
	 * @param btnItemPrdDel
	 *            : Product/Item Delete String
	 * 
	 * @param itemPrdCountToDelete
	 *            : Number of Product/Item to delete
	 * 
	 * @param driver
	 *            : WebDriver Instance
	 * @throws Exception 
	 */
	final public static void deleteMulitpleItemFromBag(WebElement btnItemPrdDel, int itemPrdCountToDelete, WebDriver driver) throws Exception {

		//final long startTime = StopWatch.startTime();

		for (int i = 0; i < itemPrdCountToDelete; i++) {

			BrowserActions.clickOnElement(btnItemPrdDel, driver, "Product/Item Delete");
			//Log.trace("Clicked Delete 'X' Product/Item button.", StopWatch.elapsedTime(startTime));
			Log.trace("Clicked Delete 'X' Product/Item button.");

		}

		//Log.trace("Deleted '" + itemPrdCountToDelete + "' Product/Item.", StopWatch.elapsedTime(startTime));
		Log.trace("Deleted '" + itemPrdCountToDelete + "' Product/Item.");

		Utils.waitForPageLoad(driver);

	}// deleteMulitpleItemFromBag

}// Add_Remove_Bag_Util