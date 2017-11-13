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

public class ShoppingCart extends LoadableComponent<ShoppingCart> {
	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;

	/**********************************************************************************************
	 ********************************* WebElements of checkout Page - Starts *****************************
	 **********************************************************************************************/

	@FindBy(css = "div[class*='checkout-types']")
	WebElement checkoutSection;

	@FindBy(css = "div[class*='checkout-types'] button[onclick*='Proceed to Checkout']")
	WebElement btnProceedToCheckout;

	/**********************************************************************************************
	 ********************************* WebElements of checkout Page - Ends **************************
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
	public ShoppingCart(WebDriver driver) {
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

		if (isPageLoaded && !(Utils.waitForElement(driver, checkoutSection))) {
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
	 * Method to navigate to the checkout page
	 * 
	 * @return
	 */
	public CheckoutPage navigateToCheckoutpage() {
		try {
			BrowserActions.clickOnElement(btnProceedToCheckout, driver,
					"Cart Page button");
		} catch (Exception e) {
			Log.fail("Proceed to checkout button is not in state to click");
		}
		return new CheckoutPage(driver).get();

	}
}