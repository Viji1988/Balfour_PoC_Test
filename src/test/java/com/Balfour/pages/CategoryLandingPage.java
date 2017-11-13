package com.Balfour.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class CategoryLandingPage extends LoadableComponent<CategoryLandingPage> {
	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;

	/**********************************************************************************************
	 ********************************* WebElements of Brands - Starts *****************************
	 **********************************************************************************************/

	@FindBy(css = ".category-products")
	WebElement categoryLandingPage;

	@FindBy(css = "div[class='productViewGrid selected']")
	WebElement gridView;

	@FindBy(css = ".productViewDetail")
	WebElement listviewIcon;

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
	public CategoryLandingPage(WebDriver driver) {
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
				&& !(Utils.waitForElement(driver, categoryLandingPage))) {
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
	 * Navigate to PDP based up on the Product name as argument
	 * 
	 * @param productName
	 * @return
	 * @throws Exception
	 */
	public PDP navigateToProductPage(String productName) throws Exception {
		BrowserActions.nap(2);
		WebElement productToClick = driver.findElement(By
				.cssSelector("a[title*='" + productName + "']"));
		BrowserActions.clickOnElement(productToClick, driver, "Product Link");
		return new PDP(driver).get();
	}

	/**
	 * Navigate to the product page based up on the product ID given
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public PDP navigateToGraduationProductPage(String productId)
			throws Exception {
		BrowserActions.nap(2);
		WebElement productToClick = driver.findElement(By
				.cssSelector("button[onclick*='" + productId + "']"));
		BrowserActions.clickOnElement(productToClick, driver, "Product Link");
		return new PDP(driver).get();
	}

	/**
	 * To Validate the Grid view page
	 * 
	 * @return
	 */
	public boolean verifyGridView() {
		Boolean isPresent = false;
		if (driver.findElements(
				By.cssSelector("div[class='productViewGrid selected']")).size() > 0) {
			isPresent = true;
		}
		return isPresent;

	}

	/**
	 * To Validate the List view
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean verifyListView() throws Exception {
		BrowserActions.clickOnElement(listviewIcon, driver, "List View");
		BrowserActions.nap(2);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, 310);");
		Boolean isPresent = false;
		if (driver.findElements(
				By.cssSelector("div[class='productViewDetail selected']"))
				.size() > 0) {
			isPresent = true;
		}
		return isPresent;

	}

}