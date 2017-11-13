package com.Balfour.pages;

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

public class HomePage extends LoadableComponent<HomePage> {
	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	public static String SchoolName = null;
	public static String citySchoolName = null;
	public static String stateSchoolName = null;
	public static String ZipSchoolName = null;
	public static String[] location = null;
	/**********************************************************************************************
	 ********************************* WebElements of Brands - Starts *****************************
	 **********************************************************************************************/

	@FindBy(css = ".search")
	private WebElement SearchBox;

	@FindBy(css = "input[class='searchbox']")
	private WebElement txtSearch;

	@FindBy(css = "span.show-for-medium-up")
	private WebElement btnSearch;

	@FindBy(css = "img[src*='close_circle_transparent']")
	private WebElement btnClose;

	@FindBy(css = "div[class='autocomplete-suggestions'] div[data-index='0']")
	private WebElement autosuggestion1;

	@FindBy(css = "div[class='autocomplete-suggestions'] div[data-index='0'] div[class='location']")
	private WebElement autoSuggestion1Location;

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
	public HomePage(WebDriver driver, String url) {
		appURL = url;
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
				Utils.maxElementWait);
		PageFactory.initElements(finder, this);
	}// HomePage

	/**
	 * constructor of the class
	 * 
	 * @param driver
	 *            : Webdriver
	 * 
	 * @param url
	 *            : UAT URL
	 */
	public HomePage(WebDriver driver) {
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

		if (isPageLoaded && !(Utils.waitForElement(driver, SearchBox))) {
			Log.fail("Brands Page did not open up. Site might be down.", driver);
		}

	}// isLoaded

	@Override
	protected void load() {
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
		driver.get(appURL);
	}

	/**
	 * Navigate to the Affinity page based upon the SchoolName
	 * 
	 * @param schoolName
	 * @return
	 */
	public AffinityPage navigateToAffinityPage(String schoolName) {
		try {
			/*
			 * BrowserActions.nap(3); BrowserActions.clickOnElement(btnClose,
			 * driver, "close button");
			 */
			BrowserActions.typeOnTextField(txtSearch, schoolName, driver,
					"SearchBox");
			BrowserActions.nap(5);
			BrowserActions.clickOnElement(btnClose, driver, "close button");
			BrowserActions.clickOnElement(btnSearch, driver, "Search Button");
		} catch (Exception e) {
			Log.fail("Affinity page is not navigated properly!!!", driver);
		}
		return new AffinityPage(driver).get();
	}

	/**
	 * Navigate to the Affinity page with School name as given argument
	 * 
	 * @param schoolName
	 * @return
	 */
	public AffinityPage navigateToAffinityPageWithSchoolName(String schoolName) {
		try {
			BrowserActions.typeOnTextField(txtSearch, schoolName, driver,
					"SearchBox");
			BrowserActions.nap(5);
			BrowserActions.clickOnElement(btnClose, driver, "close button");
			BrowserActions.clickOnElement(btnSearch, driver, "Search Button");
		} catch (Exception e) {
			Log.fail("Affinity page is not navigated properly!!!", driver);
		}
		return new AffinityPage(driver).get();
	}

	/**
	 * To Fetch and click on the SchoolName from suggestion
	 * 
	 * @param schoolName
	 * @return
	 * @throws Exception
	 */
	public AffinityPage clickandFetchSchoolName(String schoolName)
			throws Exception {
		BrowserActions.typeOnTextField(txtSearch, schoolName, driver,
				"SearchBox");
		BrowserActions.nap(5);
		BrowserActions.clickOnElement(btnClose, driver, "close button");
		BrowserActions.typeOnTextField(txtSearch, schoolName, driver,
				"SearchBox");
		BrowserActions.nap(2);
		String locate = BrowserActions.getText(driver, autoSuggestion1Location,
				"Location");
		location = locate.split("\\, ");
		SchoolName = BrowserActions.getText(driver, autosuggestion1,
				"Auto suggestion");
		BrowserActions.clickOnElement(autosuggestion1, driver,
				"Suggestion selected");
		return new AffinityPage(driver).get();
	}
	
	/**
	 * To Fetch and click on the SchoolName from suggestion
	 * 
	 * @param schoolName
	 * @return
	 * @throws Exception
	 */
	public AffinityPage clickandFetchSchoolNamewithCity(String schoolName)
			throws Exception {
		BrowserActions.typeOnTextField(txtSearch, schoolName, driver,
				"SearchBox");
		BrowserActions.nap(5);
		BrowserActions.clickOnElement(btnClose, driver, "close button");
		BrowserActions.typeOnTextField(txtSearch, schoolName, driver,
				"SearchBox");
		BrowserActions.nap(2);
		String locate = BrowserActions.getText(driver, autoSuggestion1Location,
				"Location");
		location = locate.split("\\, ");
		citySchoolName = BrowserActions.getText(driver, autosuggestion1,
				"Auto suggestion");
		BrowserActions.clickOnElement(autosuggestion1, driver,
				"Suggestion selected");
		return new AffinityPage(driver).get();
	}
	
	/**
	 * To Fetch and click on the SchoolName from suggestion
	 * 
	 * @param schoolName
	 * @return
	 * @throws Exception
	 */
	public AffinityPage clickandFetchSchoolNamewithState(String schoolName)
			throws Exception {
		BrowserActions.typeOnTextField(txtSearch, schoolName, driver,
				"SearchBox");
		BrowserActions.nap(5);
		BrowserActions.clickOnElement(btnClose, driver, "close button");
		BrowserActions.typeOnTextField(txtSearch, schoolName, driver,
				"SearchBox");
		BrowserActions.nap(2);
		String locate = BrowserActions.getText(driver, autoSuggestion1Location,
				"Location");
		location = locate.split("\\, ");
		stateSchoolName = BrowserActions.getText(driver, autosuggestion1,
				"Auto suggestion");
		BrowserActions.clickOnElement(autosuggestion1, driver,
				"Suggestion selected");
		return new AffinityPage(driver).get();
	}
	
	/**
	 * To Fetch and click on the SchoolName from suggestion
	 * 
	 * @param schoolName
	 * @return
	 * @throws Exception
	 */
	public AffinityPage clickandFetchSchoolNameWithZip(String schoolName)
			throws Exception {
		BrowserActions.typeOnTextField(txtSearch, schoolName, driver,
				"SearchBox");
		BrowserActions.nap(5);
		BrowserActions.clickOnElement(btnClose, driver, "close button");
		BrowserActions.typeOnTextField(txtSearch, schoolName, driver,
				"SearchBox");
		BrowserActions.nap(2);
		String locate = BrowserActions.getText(driver, autoSuggestion1Location,
				"Location");
		location = locate.split("\\, ");
		ZipSchoolName = BrowserActions.getText(driver, autosuggestion1,
				"Auto suggestion");
		BrowserActions.clickOnElement(autosuggestion1, driver,
				"Suggestion selected");
		return new AffinityPage(driver).get();
	}

	/**
	 * To Rrturn the fetched school information
	 * 
	 * @return
	 */
	public String fetchSchoolName() {
		// TODO Auto-generated method stub
		return SchoolName;
	}
}