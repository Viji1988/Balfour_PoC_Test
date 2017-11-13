package com.Balfour.pages;

import java.util.LinkedHashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.Balfour.reusablecomponents.AccountUtils;
import com.Balfour.support.BrowserActions;
import com.Balfour.support.Log;
import com.Balfour.support.Utils;

public class CheckoutPage extends LoadableComponent<CheckoutPage> {
	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;

	public static final String txtFirstname = "input[name='billing[firstname]']";
	public static final String txtLastname = "input[id='billing:lastname']";
	public static final String txtcompany = "input[id='billing:company']";
	public static final String txtEmail = "input[id='billing:email']";
	public static final String txtConfirmEmail = "input[id='billing:cemail']";
	public static final String txtAddress = "input[id='billing:street1']";
	public static final String txtCity = "input[id='billing:city']";
	public static final String txtPostalCode = "input[id='billing:postcode']";
	public static final String txtTelephone = "input[id='billing:telephone']";
	public static final String txtCCNumber = "input[id='verisign_cc_number']";
	public static final String txtCCId = "input[id='verisign_cc_cid']";

	/**********************************************************************************************
	 ********************************* WebElements of checkout Page - Starts *****************************
	 **********************************************************************************************/

	@FindBy(css = ".checkout-title")
	WebElement checkoutTitle;

	@FindBy(css = "label[for='login:guest']")
	WebElement optGuestlogin;

	@FindBy(css = "button[onclick*='checkout']")
	WebElement btnCheckoutContinue;

	@FindBy(css = "button[onclick*='billing.save()']")
	WebElement btnSaveContinue;

	@FindBy(css = "select[id='billing:region_id']")
	WebElement drpState;

	@FindBy(css = "label[for='billing:region_id']")
	WebElement lblState;

	@FindBy(css = "button[onclick*='shipping.save()']")
	WebElement btnShippingSave;

	@FindBy(css = "label[for='p_method_verisign']")
	WebElement optCreditcard;

	@FindBy(css = "select[id='verisign_cc_type']")
	WebElement drpCC;

	@FindBy(css = "select[id='verisign_expiration']")
	WebElement drpDate;

	@FindBy(css = "select[id='verisign_expiration_yr']")
	WebElement drpYear;

	@FindBy(css = "button[onclick*='payment.save']")
	WebElement btnPaymentSave;

	@FindBy(css = txtFirstname)
	WebElement firstName;

	@FindBy(css = "button[id='reviewBtn']")
	WebElement btnPlaceOrder;

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
	public CheckoutPage(WebDriver driver) {
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

		if (isPageLoaded && !(Utils.waitForElement(driver, checkoutTitle))) {
			Log.fail("Affinity Page did not open up. Site might be down.",
					driver);
		}

	}// isLoaded

	@Override
	protected void load() {
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}

	public void navigateToBillingSection() throws Exception {
		BrowserActions
				.clickOnElement(optGuestlogin, driver, "Option GuestUser");
		BrowserActions.clickOnElement(btnCheckoutContinue, driver,
				"Continue button");
	}

	/**
	 * Hashmap to enter the Billing section details
	 * 
	 * @param BillingInfo
	 * @param State
	 * @throws Exception
	 */
	public void fillBillingSectionDetails(
			LinkedHashMap<String, String> BillingInfo, String State)
			throws Exception {
		BillingInfo.put("type_firstname_" + BillingInfo.get("firstname"),
				txtFirstname);
		BillingInfo.put("type_lastname_" + BillingInfo.get("lastname"),
				txtLastname);
		BillingInfo.put("type_Company_" + BillingInfo.get("Company"),
				txtcompany);
		BillingInfo.put("type_email_" + BillingInfo.get("email"), txtEmail);
		BillingInfo.put("type_confirmEmail_" + BillingInfo.get("confirmEmail"),
				txtConfirmEmail);
		BillingInfo.put("type_Address_" + BillingInfo.get("Address"),
				txtAddress);
		BillingInfo.put("type_City_" + BillingInfo.get("City"), txtCity);

		BillingInfo.put("type_Postal_" + BillingInfo.get("Postal"),
				txtPostalCode);
		BillingInfo.put("type_Telephone_" + BillingInfo.get("Telephone"),
				txtTelephone);
		AccountUtils.doAccountOperations(BillingInfo, driver);
		BrowserActions.nap(2);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, -250);");
		BrowserActions.clickOnElement(drpState, driver, "drpState");
		BrowserActions.nap(1);
		BrowserActions.selectDropDownValue(driver, drpState, State);
		BrowserActions.nap(1);
		BrowserActions.clickOnElement(btnSaveContinue, driver,
				"Billingsection Continue Button");

	}

	/**
	 * To Select state from drop down
	 * 
	 * @param state
	 * @throws Exception
	 */
	public void SelectState(String state) throws Exception {
		// BrowserActions.moveToElementJS(driver, drpState);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, -250);");
		BrowserActions.clickOnElement(drpState, driver, "drpState");
		BrowserActions.nap(1);
		BrowserActions.selectDropDownValue(driver, drpState, state);
		BrowserActions.nap(1);
		BrowserActions.clickOnElement(btnSaveContinue, driver,
				"Billingsection Continue Button");
	}

	/**
	 * To Navigate to the Payment section
	 * 
	 * @throws Exception
	 */
	public void navigateToPaymentSection() throws Exception {
		BrowserActions.nap(3);
		BrowserActions.clickOnElement(btnShippingSave, driver,
				"Shipping Save Section ");
		BrowserActions.nap(2);

	}

	/**
	 * To enter the details in the Payment Inputs fields
	 * 
	 * @param paymentDetails
	 * @param cardType
	 * @param date
	 * @param year
	 * @throws Exception
	 */
	public void fillPaymentdetails(
			LinkedHashMap<String, String> paymentDetails, String cardType,
			String date, String year) throws Exception {
		BrowserActions.nap(2);
		BrowserActions.clickOnElement(optCreditcard, driver,
				"Credit card Option");
		BrowserActions.selectDropDownValue(driver, drpCC, cardType);
		BrowserActions.selectDropDownValue(driver, drpDate, date);
		BrowserActions.selectDropDownValue(driver, drpYear, year);
		paymentDetails.put(
				"type_CardNumber_" + paymentDetails.get("CardNumber"),
				txtCCNumber);
		paymentDetails.put("type_CVV_" + paymentDetails.get("CVV"), txtCCId);
		AccountUtils.doAccountOperations(paymentDetails, driver);
		BrowserActions.clickOnElement(btnPaymentSave, driver,
				"Billingsection Continue Button");
		BrowserActions.nap(2);
	}

	/**
	 * button to place an order click
	 * 
	 */
	public void placeOrder() {
		try {
			BrowserActions.moveToElementJS(driver, btnPlaceOrder);
			BrowserActions.clickOnElement(btnPlaceOrder, driver,
					"Place Order Button");
		} catch (Exception e) {
			Log.fail("Place order button is not in state to click, Plz check log event");
		}

	}

}
