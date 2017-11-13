package com.Balfour.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.Balfour.support.BrowserActions;
import com.Balfour.support.Log;
import com.Balfour.support.Utils;

public class AffinityPage extends LoadableComponent<AffinityPage> {
	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;

	/**********************************************************************************************
	 ********************************* WebElements of Brands - Starts *****************************
	 **********************************************************************************************/

	@FindBy(css = "#affinity-page")
	WebElement affinityPageValidation;

	@FindBy(css = "span[itemprop='name']")
	WebElement header;

	@FindBy(css = "p[class*='show-for-large-up affinity-schoolName']")
	WebElement headerwithStateCode;

	/**********************************************************************************************
	 ********************************* WebElements of HomePage Page - Ends **************************
	 **********************************************************************************************/
	/**
	 * constructor of the class
	 * 
	 * @param driver
	 *            : Webdriver
	 * 
	 * @param url
	 *            : UAT URL
	 */
	public AffinityPage(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
				Utils.maxElementWait);
		PageFactory.initElements(finder, this);
		elementLayer = new ElementLayer(driver);
	}

	@Override
	protected void isLoaded() {

		Utils.waitForPageLoad(driver);

		if (!isPageLoaded) {
			Assert.fail();
		}

		if (isPageLoaded
				&& !(Utils.waitForElement(driver, affinityPageValidation))) {
			Log.fail("Affinity Page did not open up. Site might be down.",
					driver);
		}

	}// isLoaded

	@Override
	protected void load() {
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);

	}

	/**
	 * To Navigate to the CLP based up on the argument
	 * 
	 * @param categorylink
	 * @return
	 * @throws Exception
	 */
	public CategoryLandingPage navigateToCLP(String categorylink)
			throws Exception {
		WebElement lnkToNavigate = driver
				.findElement(By
						.cssSelector("div[class*='medium-4 columns affinity-product'] img[src*='"
								+ categorylink + "']"));
		BrowserActions.clickOnElement(lnkToNavigate, driver, "Category link");
		return new CategoryLandingPage(driver).get();
		/*
		 * WebElement lnkToNavigate = driver .findElement(By .cssSelector(
		 * "div[class*='medium-6 columns affinity-product end affinity-border-bottom'] img[src*='"
		 * +categorylink+"']")); BrowserActions.clickOnElement(lnkToNavigate,
		 * driver, "Category link"); return new
		 * CategoryLandingPage(driver).get();
		 */

	}

	/**
	 * Verify the school based up on the argument
	 * 
	 * @param schoolName
	 * @return
	 * @throws Exception
	 */
	public boolean verifySchoolname(String schoolName) throws Exception {
		boolean producNamedisplayed = false;
		String ProductNamefromSite = null;
		ProductNamefromSite = BrowserActions.getText(driver, header,
				"School Name in Header");
		if (schoolName.contains(ProductNamefromSite)) {
			producNamedisplayed = true;
		}
		return producNamedisplayed;
	}

	/**
	 * Verify state based upon the school is mentioned
	 * 
	 * @param State
	 * @return
	 * @throws Exception
	 */
	public boolean verifyState(String State) throws Exception {
		boolean stateValue = false;
		String StateNamefromSite = null;
		StateNamefromSite = BrowserActions.getText(driver, headerwithStateCode,
				"School Name in Header");
		if (State.equals("California")) {
			if (StateNamefromSite.contains("CA")) {
				stateValue = true;
			}
		}
		return stateValue;
	}

}