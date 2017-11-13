package com.Balfour.testscripts;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Balfour.pages.AffinityPage;
import com.Balfour.pages.CategoryLandingPage;
import com.Balfour.pages.CheckoutPage;
import com.Balfour.pages.HomePage;
import com.Balfour.pages.PDP;
import com.Balfour.pages.ShoppingCart;
import com.Balfour.support.DataProviderUtils;
import com.Balfour.support.EmailReport;
import com.Balfour.support.EnvironmentPropertiesReader;
import com.Balfour.support.Log;
import com.Balfour.support.TestDataExtractor;
import com.Balfour.support.Utils;
import com.Balfour.support.WebDriverFactory;

@Listeners(EmailReport.class)
public class Balfour_PoC_Demo_Scripts {

	EnvironmentPropertiesReader environmentPropertiesReader;
	String ProdwebSite;
	String QAWebsite;
	private String workbookName = "testdata\\data\\Regression.xls";
	private String sheetName = "Balfour";

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {
		ProdwebSite = (System.getProperty("ProdwebSite") != null ? System
				.getProperty("ProdwebSite") : context.getCurrentXmlTest()
				.getParameter("ProdwebSite"));

		QAWebsite = (System.getProperty("QAWebsite") != null ? System
				.getProperty("QAWebsite") : context.getCurrentXmlTest()
				.getParameter("QAWebsite"));
	}

	@Test(description = "To Verify School finder by School name ", enabled = true, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC__Verify_AffinityWithSchoolName(String browser)
			throws Exception {

		// ** Loading the test data from excel using the test case id */
		HashMap<String, String> testData = TestDataExtractor.initTestData(
				workbookName, sheetName);

		String executionApp = Utils.getExecutionApp();

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		String schoolName = testData.get("SchoolName");

		HomePage homePage = null;

		try {
			if (executionApp == "ProdSite") {
				homePage = new HomePage(driver, ProdwebSite).get();
				Log.message(
						"1. Navigated to 'Production - Balfour' Home Page!",
						driver);
			}
			if (executionApp == "QAsites") {
				homePage = new HomePage(driver, QAWebsite).get();
				Log.message(
						"1. Navigated to 'Development - Balfour' Home Page!",
						driver);
			}
				AffinityPage affinityPage = homePage
						.navigateToAffinityPageWithSchoolName(schoolName);
				Log.assertThat(
						affinityPage.verifySchoolname(schoolName),
						"2. Searching by School Name displayes the proper result as expected",
						"2. Searching by School Name didn't displayes the proper result as expected",
						driver);
				
				Log.message("\n");
				Log.message("<b><font face='Comic Sans MS' color='06014F'>Actual Result:</font></b>");
				Log.message("<font face='Calibri' color='black'>School finder displayes the proper School based up on the School Name</font>");
				Log.message("<b><font face='Comic Sans MS' color='06014F'>Expected Result:</font></b>");
				Log.message("<font face='Calibri' color='black'>School Finder should display result based upon the School Name</font>");

			
		} // try
		catch (Exception e) {
			Log.exception(e, driver);
		} // catch
		finally {
			Log.endTestCase();
			driver.quit();
		} // finally

	}// TC__Verify_AffinityWithSchoolName

	@Test(description = "To Verify School finder by Zip code", enabled = true, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC__Verify_AffinityWithZipCode(String browser) throws Exception {

		// ** Loading the test data from excel using the test case id */
		HashMap<String, String> testData = TestDataExtractor.initTestData(
				workbookName, sheetName);

		String executionApp = Utils.getExecutionApp();

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		String schoolName = testData.get("SchoolName");

		HomePage homePage = null;

		try {
			if (executionApp == "ProdSite") {
				homePage = new HomePage(driver, ProdwebSite).get();
				Log.message(
						"1. Navigated to 'Production - Balfour' Home Page!",
						driver);
			}
			if (executionApp == "QAsites") {
				homePage = new HomePage(driver, QAWebsite).get();
				Log.message(
						"1. Navigated to 'Development - Balfour' Home Page!",
						driver);
			}
				AffinityPage affinityPage = homePage
						.clickandFetchSchoolNameWithZip(schoolName);
				String SchoolName = HomePage.ZipSchoolName;

				Log.message(
						"2. Selected SchoolName " + SchoolName
								+ " From the list disaplyed for zip code "
								+ schoolName, driver);
				Log.assertThat(
						affinityPage.verifySchoolname(SchoolName),
						"3. Searching by ZipCode displayes the proper result as expected",
						"3. Searching by ZipCode didn't displayes the proper result as expected",
						driver);
				String state = HomePage.location[1];
				Log.assertThat(
						affinityPage.verifyState(state),
						"4. State value has been successfully validated in Affinity Page",
						"4. State code is getting mis-matched, plz check the log event!!",
						driver);

				Log.message("\n");
				Log.message("<b><font face='Comic Sans MS' color='06014F'>Actual Result:</font></b>");
				Log.message("<font face='Calibri' color='black'>School finder displayes the proper School based up on the ZipCode</font>");
				Log.message("<b><font face='Comic Sans MS' color='06014F'>Expected Result:</font></b>");
				Log.message("<font face='Calibri' color='black'>School Finder should display result based upon the ZipCode</font>");

			
		} // try
		catch (Exception e) {
			Log.exception(e, driver);
		} // catch
		finally {
			Log.endTestCase();
			driver.quit();
		} // finally

	}// TC__Verify_AffinityWithZipCode

	@Test(description = "To Verify School finder by Zip code", enabled = true, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC__Verify_AffinityWithStateName(String browser)
			throws Exception {

		// ** Loading the test data from excel using the test case id */
		HashMap<String, String> testData = TestDataExtractor.initTestData(
				workbookName, sheetName);

		String executionApp = Utils.getExecutionApp();

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		String schoolName = testData.get("SchoolName");

		HomePage homePage = null;

		try {
			if (executionApp == "ProdSite") {
				homePage = new HomePage(driver, ProdwebSite).get();
				Log.message(
						"1. Navigated to 'Production - Balfour' Home Page!",
						driver);
			}
			if (executionApp == "QAsites") {
				homePage = new HomePage(driver, QAWebsite).get();
				Log.message(
						"1. Navigated to 'Development - Balfour' Home Page!",
						driver);
			}
				AffinityPage affinityPage = homePage
						.clickandFetchSchoolNamewithState(schoolName);
				String SchoolName = HomePage.stateSchoolName;
				Log.message(
						"2. Selected SchoolName " + SchoolName
								+ " From the list disaplyed for zip code "
								+ schoolName, driver);
				Log.assertThat(
						affinityPage.verifySchoolname(SchoolName),
						"3. Searching by ZipCode displayes the proper result as expected",
						"3. Searching by ZipCode didn't displayes the proper result as expected",
						driver);

				String state = HomePage.location[1];
				Log.assertThat(
						affinityPage.verifyState(state),
						"4. State value has been successfully validated in Affinity Page",
						"4. State code is getting mis-matched, plz check the log event!!",
						driver);
				
				Log.message("\n");
				Log.message("<b><font face='Comic Sans MS' color='06014F'>Actual Result:</font></b>");
				Log.message("<font face='Calibri' color='black'>School finder displayes the proper School based up on the State</font>");
				Log.message("<b><font face='Comic Sans MS' color='06014F'>Expected Result:</font></b>");
				Log.message("<font face='Calibri' color='black'>School Finder should display result based upon the State</font>");

			
		} // try
		catch (Exception e) {
			Log.exception(e, driver);
		} // catch
		finally {
			Log.endTestCase();
			driver.quit();
		} // finally

	}// TC__Verify_AffinityWithStateName

	@Test(description = "To Verify School finder by City Name", enabled = true, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC__Verify_AffinityWithCityName(String browser)
			throws Exception {

		// ** Loading the test data from excel using the test case id */
		HashMap<String, String> testData = TestDataExtractor.initTestData(
				workbookName, sheetName);

		String executionApp = Utils.getExecutionApp();

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		String schoolName = testData.get("SchoolName");

		HomePage homePage = null;

		try {
			if (executionApp == "ProdSite") {
				homePage = new HomePage(driver, ProdwebSite).get();
				Log.message(
						"1. Navigated to 'Production - Balfour' Home Page!",
						driver);
			}
			if (executionApp == "QAsites") {
				homePage = new HomePage(driver, QAWebsite).get();
				Log.message(
						"1. Navigated to 'Development - Balfour' Home Page!",
						driver);
			}
				AffinityPage affinityPage = homePage
						.clickandFetchSchoolNamewithCity(schoolName);
				String SchoolName = HomePage.citySchoolName;
				Log.message(
						"2. Selected SchoolName " + SchoolName
								+ " From the list disaplyed for zip code "
								+ schoolName, driver);
				Log.assertThat(
						affinityPage.verifySchoolname(SchoolName),
						"3. Searching by ZipCode displayes the proper result as expected",
						"3. Searching by ZipCode didn't displayes the proper result as expected",
						driver);

				String state = HomePage.location[1];
				Log.assertThat(
						affinityPage.verifyState(state),
						"4. State value has been successfully validated in Affinity Page",
						"4. State code is getting mis-matched, plz check the log event!!",
						driver);
				
				Log.message("\n");
				Log.message("<b><font face='Comic Sans MS' color='06014F'>Actual Result:</font></b>");
				Log.message("<font face='Calibri' color='black'>School finder displayes the proper School based up on the City</font>");
				Log.message("<b><font face='Comic Sans MS' color='06014F'>Expected Result:</font></b>");
				Log.message("<font face='Calibri' color='black'>School Finder should display result based upon the City</font>");

			
		} // try
		catch (Exception e) {
			Log.exception(e, driver);
		} // catch
		finally {
			Log.endTestCase();
			driver.quit();
		} // finally

	}// TC__Verify_AffinityWithCityName

	@Test(description = "Place an Order with the product from Class Ring", enabled = true, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC__Verify_Catalog_Page_Option(String browser) throws Exception {

		// ** Loading the test data from excel using the test case id */
		HashMap<String, String> testData = TestDataExtractor.initTestData(
				workbookName, sheetName);

		String executionApp = Utils.getExecutionApp();

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		String schoolName = testData.get("SchoolName");
		String categorylink = testData.get("categorylink");

		HomePage homePage = null;

		try {
			if (executionApp == "ProdSite") {
				homePage = new HomePage(driver, ProdwebSite).get();
				Log.message(
						"1. Navigated to 'Production - Balfour' Home Page!",
						driver);
			}
			if (executionApp == "QAsites") {
				homePage = new HomePage(driver, QAWebsite).get();
				Log.message(
						"1. Navigated to 'Development - Balfour' Home Page!",
						driver);
			}

			AffinityPage affinityPage = homePage
					.navigateToAffinityPage(schoolName);
			Log.message("2. Landed on Affinity Page successfully!!", driver);

			CategoryLandingPage clpPage = affinityPage
					.navigateToCLP(categorylink);
			Log.message("3. Navigates to the category landing page!!", driver);

			Log.assertThat(clpPage.verifyGridView(),
					"4. products are displayed in grid view as expected",
					"4. products are not getting displayed as like grid view",
					driver);

			Log.assertThat(clpPage.verifyListView(),
					"5. products are displayed in list view as expected",
					"5. products are not getting displayed as like list view",
					driver);

			Log.message("\n");
			Log.message("<b><font face='Comic Sans MS' color='06014F'>Actual Result:</font></b>");
			Log.message("<font face='Calibri' color='black'>Grid/List View should be displayed properly in the CLP</font>");
			Log.message("<b><font face='Comic Sans MS' color='06014F'>Expected Result:</font></b>");
			Log.message("<font face='Calibri' color='black'>Grid/List View section are getting displayed properly in the CLP</font>");

			Log.testCaseResult();

		} // try
		catch (Exception e) {
			Log.exception(e, driver);
		} // catch
		finally {
			Log.endTestCase();
			driver.quit();
		} // finally

	}// TC_OrderPlacement

	@Test(description = "Place an Order with the product from Class Ring", enabled = true, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_ClassRings_OrderPlacement(String browser) throws Exception {

		// ** Loading the test data from excel using the test case id */
		HashMap<String, String> testData = TestDataExtractor.initTestData(
				workbookName, sheetName);

		String executionApp = Utils.getExecutionApp();

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		String schoolName = testData.get("SchoolName");
		String categorylink = testData.get("categorylink");
		String productName = testData.get("productName");
		String metalcolor = testData.get("MetalColor");
		String metalFinish = testData.get("MetalFinish");
		String Stonecolor = testData.get("Stonecolor");
		String slide1 = testData.get("slide1");
		String Stonecut = testData.get("StoneCut");
		String linenum = testData.get("LineNo");
		String Slide1Bottom = testData.get("Slide1Bottom");
		String palmSide = testData.get("palmSide");
		String RingCare = testData.get("RingCare");
		String State = testData.get("State");
		HomePage homePage = null;

		try {
			if (executionApp == "ProdSite") {
				homePage = new HomePage(driver, ProdwebSite).get();
				Log.message(
						"1. Navigated to 'Production - Balfour' Home Page!",
						driver);
			}
			if (executionApp == "QAsites") {
				homePage = new HomePage(driver, QAWebsite).get();
				Log.message(
						"1. Navigated to 'Development - Balfour' Home Page!",
						driver);
			}

			AffinityPage affinityPage = homePage
					.navigateToAffinityPage(schoolName);
			Log.message("2. Landed on Affinity Page successfully!!", driver);

			CategoryLandingPage clpPage = affinityPage
					.navigateToCLP(categorylink);
			Log.message("3. Navigates to the category landing page!!", driver);

			PDP PdpPage = clpPage.navigateToProductPage(productName);
			Log.assertThat(
					PdpPage.fetchProductNameOfClassRing().contains(productName),
					"4. Navigated to the exact PDP which we selected from CLP",
					"4. Expected PDP din't displayed, Plz check log event",
					driver);

			Log.message("5. Start configuring the product from PDP based up on User needs");
			PdpPage.configureProduct(metalcolor, metalFinish, Stonecolor,
					slide1, Stonecut, linenum, Slide1Bottom, palmSide, RingCare);
			Log.message(
					"6. Successfully Configured the product based upon the user given data",
					driver);

			ShoppingCart shoppingcart = PdpPage.addToCart();
			Log.message("7. Product added to the cart successfully", driver);

			CheckoutPage checkoutPage = shoppingcart.navigateToCheckoutpage();
			Log.message(
					"8. Navigates to the checkoutPage with the added products",
					driver);

			checkoutPage.navigateToBillingSection();
			Log.message("9. Navigate to the Billing section of checkout page",
					driver);

			// ** loading test data to fill Billing details form page
			LinkedHashMap<String, String> BillingDetails = new LinkedHashMap<String, String>(
					testData);
			final String emailAddress = "qaAutomation"
					+ org.apache.commons.lang3.RandomStringUtils.randomNumeric(
							7).toString() + "@gmail.com";
			BillingDetails.put("firstname", testData.get("Firstname"));
			BillingDetails.put("lastname", testData.get("Lastname"));
			BillingDetails.put("Company", testData.get("Company"));
			BillingDetails.put("email", emailAddress);
			BillingDetails.put("confirmEmail", emailAddress);
			BillingDetails.put("Address", testData.get("Address"));
			BillingDetails.put("City", testData.get("City"));
			BillingDetails.put("Postal", testData.get("Postal"));
			BillingDetails.put("Telephone", testData.get("Telephone"));

			checkoutPage.fillBillingSectionDetails(BillingDetails, State);
			Log.message(
					"10. Successfully entered the billig section details and navigates to the Shipping section",
					driver);

			checkoutPage.navigateToPaymentSection();
			Log.message(
					"11. Successfully entered the shipping section details and navigates to the Payment section",
					driver);

			// ** loading test data to fill Billing details form page
			LinkedHashMap<String, String> PaymentDetails = new LinkedHashMap<String, String>(
					testData);
			PaymentDetails.put("CardNumber", testData.get("CardNumber"));
			PaymentDetails.put("CVV", testData.get("CVV"));
			String cardType = testData.get("cardType");
			String date = testData.get("Date");
			String year = testData.get("Year");
			checkoutPage.fillPaymentdetails(PaymentDetails, cardType, date,
					year);
			Log.message(
					"12. Successfully entered the Payment section details and navigates to the Order Review section",
					driver);
			if (executionApp == "QAsites") {
				checkoutPage.placeOrder();
				Log.message(
						"13. Clicked on the Place order button from checkout page",
						driver);
			}
			Log.message("\n");
			Log.message("<b><font face='Comic Sans MS' color='06014F'>Actual Result:</font></b>");
			Log.message("<font face='Calibri' color='black'>Order is placed successfully</font>");
			Log.message("<b><font face='Comic Sans MS' color='06014F'>Expected Result:</font></b>");
			Log.message("<font face='Calibri' color='black'>Customer should able place an order successfully after adding a configured product to cart</font>");

			Log.testCaseResult();

		} // try
		catch (Exception e) {
			Log.exception(e, driver);
		} // catch
		finally {
			Log.endTestCase();
			driver.quit();
		} // finally

	}// TC_OrderPlacement

	@Test(description = "Place an Order with the product from Class Apparels", enabled = true, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_ClassApparels_OrderPlacement(String browser)
			throws Exception {

		// ** Loading the test data from excel using the test case id */
		HashMap<String, String> testData = TestDataExtractor.initTestData(
				workbookName, sheetName);

		String executionApp = Utils.getExecutionApp();

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		String schoolName = testData.get("SchoolName");
		String categorylink = testData.get("categorylink");
		String productName = testData.get("productName");
		String State = testData.get("State");
		HomePage homePage = null;

		try {
			if (executionApp == "ProdSite") {
				homePage = new HomePage(driver, ProdwebSite).get();
				Log.message(
						"1. Navigated to 'Production - Balfour' Home Page!",
						driver);
			}
			if (executionApp == "QAsites") {
				homePage = new HomePage(driver, QAWebsite).get();
				Log.message(
						"1. Navigated to 'Development - Balfour' Home Page!",
						driver);
			}

			AffinityPage affinityPage = homePage
					.navigateToAffinityPage(schoolName);
			Log.message("2. Landed on Affinity Page successfully!!", driver);

			CategoryLandingPage clpPage = affinityPage
					.navigateToCLP(categorylink);
			Log.message("3. Navigates to the category landing page!!", driver);

			PDP PdpPage = clpPage.navigateToProductPage(productName);
			Log.assertThat(
					PdpPage.fetchProductNameOfClassApparels().contains(
							productName),
					"4. Navigated to the exact PDP which we selected from CLP",
					"4. Expected PDP din't displayed, Plz check log event",
					driver);

			ShoppingCart shoppingcart = PdpPage.ApparelsAddToCart();
			Log.message("5. Product added to the cart successfully", driver);

			CheckoutPage checkoutPage = shoppingcart.navigateToCheckoutpage();
			Log.message(
					"6. Navigates to the checkoutPage with the added products",
					driver);

			checkoutPage.navigateToBillingSection();
			Log.message("7. Navigate to the Billing section of checkout page",
					driver);

			// ** loading test data to fill Billing details form page
			LinkedHashMap<String, String> BillingDetails = new LinkedHashMap<String, String>(
					testData);
			final String emailAddress = "qaAutomation"
					+ org.apache.commons.lang3.RandomStringUtils.randomNumeric(
							7).toString() + "@gmail.com";
			BillingDetails.put("firstname", testData.get("Firstname"));
			BillingDetails.put("lastname", testData.get("Lastname"));
			BillingDetails.put("Company", testData.get("Company"));
			BillingDetails.put("email", emailAddress);
			BillingDetails.put("confirmEmail", emailAddress);
			BillingDetails.put("Address", testData.get("Address"));
			BillingDetails.put("City", testData.get("City"));
			BillingDetails.put("Postal", testData.get("Postal"));
			BillingDetails.put("Telephone", testData.get("Telephone"));

			checkoutPage.fillBillingSectionDetails(BillingDetails, State);
			Log.message(
					"8. Successfully entered the billig section details and navigates to the Shipping section",
					driver);

			checkoutPage.navigateToPaymentSection();
			Log.message(
					"9. Successfully entered the shipping section details and navigates to the Payment section",
					driver);

			// ** loading test data to fill Billing details form page
			LinkedHashMap<String, String> PaymentDetails = new LinkedHashMap<String, String>(
					testData);
			PaymentDetails.put("CardNumber", testData.get("CardNumber"));
			PaymentDetails.put("CVV", testData.get("CVV"));
			String cardType = testData.get("cardType");
			String date = testData.get("Date");
			String year = testData.get("Year");
			checkoutPage.fillPaymentdetails(PaymentDetails, cardType, date,
					year);
			Log.message(
					"10. Successfully entered the Payment section details and navigates to the Order Review section",
					driver);
			if (executionApp == "QAsites") {
				checkoutPage.placeOrder();
				Log.message(
						"11. Clicked on the Place order button from checkout page",
						driver);
			}
			Log.message("\n");
			Log.message("<b><font face='Comic Sans MS' color='06014F'>Actual Result:</font></b>");
			Log.message("<font face='Calibri' color='black'>Order is placed successfully</font>");
			Log.message("<b><font face='Comic Sans MS' color='06014F'>Expected Result:</font></b>");
			Log.message("<font face='Calibri' color='black'>Customer should able place an order successfully after adding a configured product to cart</font>");

			Log.testCaseResult();

		} // try
		catch (Exception e) {
			Log.exception(e, driver);
		} // catch
		finally {
			Log.endTestCase();
			driver.quit();
		} // finally

	}// TC_OrderPlacement

	@Test(description = "Place an Order with the product from New Product", enabled = true, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_NewProducts_OrderPlacement(String browser) throws Exception {

		// ** Loading the test data from excel using the test case id */
		HashMap<String, String> testData = TestDataExtractor.initTestData(
				workbookName, sheetName);

		String executionApp = Utils.getExecutionApp();

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		String schoolName = testData.get("SchoolName");
		String categorylink = testData.get("categorylink");
		String productName = testData.get("productName");
		String State = testData.get("State");
		HomePage homePage = null;

		try {
			if (executionApp == "ProdSite") {
				homePage = new HomePage(driver, ProdwebSite).get();
				Log.message(
						"1. Navigated to 'Production - Balfour' Home Page!",
						driver);
			}
			if (executionApp == "QAsites") {
				homePage = new HomePage(driver, QAWebsite).get();
				Log.message(
						"1. Navigated to 'Development - Balfour' Home Page!",
						driver);
			}

			AffinityPage affinityPage = homePage
					.navigateToAffinityPage(schoolName);
			Log.message("2. Landed on Affinity Page successfully!!", driver);

			CategoryLandingPage clpPage = affinityPage
					.navigateToCLP(categorylink);
			Log.message("3. Navigates to the category landing page!!", driver);

			PDP PdpPage = clpPage.navigateToProductPage(productName);
			Log.assertThat(
					PdpPage.fetchProductNameOfClassApparels().contains(
							productName),
					"4. Navigated to the exact PDP which we selected from CLP",
					"4. Expected PDP din't displayed, Plz check log event",
					driver);

			ShoppingCart shoppingcart = PdpPage.ApparelsAddToCart();
			Log.message("5. Product added to the cart successfully", driver);

			CheckoutPage checkoutPage = shoppingcart.navigateToCheckoutpage();
			Log.message(
					"6. Navigates to the checkoutPage with the added products",
					driver);

			checkoutPage.navigateToBillingSection();
			Log.message("7. Navigate to the Billing section of checkout page",
					driver);

			// ** loading test data to fill Billing details form page
			LinkedHashMap<String, String> BillingDetails = new LinkedHashMap<String, String>(
					testData);
			final String emailAddress = "qaAutomation"
					+ org.apache.commons.lang3.RandomStringUtils.randomNumeric(
							7).toString() + "@gmail.com";
			BillingDetails.put("firstname", testData.get("Firstname"));
			BillingDetails.put("lastname", testData.get("Lastname"));
			BillingDetails.put("Company", testData.get("Company"));
			BillingDetails.put("email", emailAddress);
			BillingDetails.put("confirmEmail", emailAddress);
			BillingDetails.put("Address", testData.get("Address"));
			BillingDetails.put("City", testData.get("City"));
			BillingDetails.put("Postal", testData.get("Postal"));
			BillingDetails.put("Telephone", testData.get("Telephone"));

			checkoutPage.fillBillingSectionDetails(BillingDetails, State);
			Log.message(
					"8. Successfully entered the billig section details and navigates to the Shipping section",
					driver);

			checkoutPage.navigateToPaymentSection();
			Log.message(
					"9. Successfully entered the shipping section details and navigates to the Payment section",
					driver);

			// ** loading test data to fill Billing details form page
			LinkedHashMap<String, String> PaymentDetails = new LinkedHashMap<String, String>(
					testData);
			PaymentDetails.put("CardNumber", testData.get("CardNumber"));
			PaymentDetails.put("CVV", testData.get("CVV"));
			String cardType = testData.get("cardType");
			String date = testData.get("Date");
			String year = testData.get("Year");
			checkoutPage.fillPaymentdetails(PaymentDetails, cardType, date,
					year);
			Log.message(
					"10. Successfully entered the Payment section details and navigates to the Order Review section",
					driver);
			if (executionApp == "QAsites") {
				checkoutPage.placeOrder();
				Log.message(
						"11. Clicked on the Place order button from checkout page",
						driver);
			}
			Log.message("\n");
			Log.message("<b><font face='Comic Sans MS' color='06014F'>Actual Result:</font></b>");
			Log.message("<font face='Calibri' color='black'>Order is placed successfully</font>");
			Log.message("<b><font face='Comic Sans MS' color='06014F'>Expected Result:</font></b>");
			Log.message("<font face='Calibri' color='black'>Customer should able place an order successfully after adding a configured product to cart</font>");

			Log.testCaseResult();

		} // try
		catch (Exception e) {
			Log.exception(e, driver);
		} // catch
		finally {
			Log.endTestCase();
			driver.quit();
		} // finally

	}// TC_NewProducts_OrderPlacement

	@Test(description = "Place an Order with the product from Letterman", enabled = true, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_LetterMan_OrderPlacement(String browser) throws Exception {

		// ** Loading the test data from excel using the test case id */
		HashMap<String, String> testData = TestDataExtractor.initTestData(
				workbookName, sheetName);

		String executionApp = Utils.getExecutionApp();

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		String schoolName = testData.get("SchoolName");
		String categorylink = testData.get("categorylink");
		String productName = testData.get("productName");
		String State = testData.get("State");
		HomePage homePage = null;

		try {
			if (executionApp == "ProdSite") {
				homePage = new HomePage(driver, ProdwebSite).get();
				Log.message(
						"1. Navigated to 'Production - Balfour' Home Page!",
						driver);
			}
			if (executionApp == "QAsites") {
				homePage = new HomePage(driver, QAWebsite).get();
				Log.message(
						"1. Navigated to 'Development - Balfour' Home Page!",
						driver);
			}

			AffinityPage affinityPage = homePage
					.navigateToAffinityPage(schoolName);
			Log.message("2. Landed on Affinity Page successfully!!", driver);

			CategoryLandingPage clpPage = affinityPage
					.navigateToCLP(categorylink);
			Log.message("3. Navigates to the category landing page!!", driver);

			PDP PdpPage = clpPage.navigateToProductPage(productName);
			Log.assertThat(
					PdpPage.fetchProductNameOfClassApparels().contains(
							productName),
					"4. Navigated to the exact PDP which we selected from CLP",
					"4. Expected PDP din't displayed, Plz check log event",
					driver);

			Log.message("5. Customizing the product From PDP !!...", driver);
			PdpPage.enterCustomAttributeInPDP();
			Log.message("6. Successfully the Product is customized", driver);

			ShoppingCart shoppingcart = PdpPage.ApparelsAddToCart();
			Log.message("5. Product added to the cart successfully", driver);

			CheckoutPage checkoutPage = shoppingcart.navigateToCheckoutpage();
			Log.message(
					"6. Navigates to the checkoutPage with the added products",
					driver);

			checkoutPage.navigateToBillingSection();
			Log.message("7. Navigate to the Billing section of checkout page",
					driver);

			// ** loading test data to fill Billing details form page
			LinkedHashMap<String, String> BillingInfo = new LinkedHashMap<String, String>(
					testData);
			final String emailAddress = "qaAutomation"
					+ org.apache.commons.lang3.RandomStringUtils.randomNumeric(
							7).toString() + "@gmail.com";
			BillingInfo.put("firstname", testData.get("Firstname"));
			BillingInfo.put("lastname", testData.get("Lastname"));
			BillingInfo.put("Company", testData.get("Company"));
			BillingInfo.put("email", emailAddress);
			BillingInfo.put("confirmEmail", emailAddress);
			BillingInfo.put("Address", testData.get("Address"));
			BillingInfo.put("City", testData.get("City"));
			BillingInfo.put("Postal", testData.get("Postal"));
			BillingInfo.put("Telephone", testData.get("Telephone"));

			checkoutPage.fillBillingSectionDetails(BillingInfo, State);
			Log.message(
					"8. Successfully entered the billig section details and navigates to the Shipping section",
					driver);

			checkoutPage.navigateToPaymentSection();
			Log.message(
					"9. Successfully entered the shipping section details and navigates to the Payment section",
					driver);

			// ** loading test data to fill Billing details form page
			LinkedHashMap<String, String> PaymentDetails = new LinkedHashMap<String, String>(
					testData);
			PaymentDetails.put("CardNumber", testData.get("CardNumber"));
			PaymentDetails.put("CVV", testData.get("CVV"));
			String cardType = testData.get("cardType");
			String date = testData.get("Date");
			String year = testData.get("Year");
			checkoutPage.fillPaymentdetails(PaymentDetails, cardType, date,
					year);
			Log.message(
					"10. Successfully entered the Payment section details and navigates to the Order Review section",
					driver);
			if (executionApp == "QAsites") {
				checkoutPage.placeOrder();
				Log.message(
						"11. Clicked on the Place order button from checkout page",
						driver);
			}
			Log.message("\n");
			Log.message("<b><font face='Comic Sans MS' color='06014F'>Actual Result:</font></b>");
			Log.message("<font face='Calibri' color='black'>Order is placed successfully</font>");
			Log.message("<b><font face='Comic Sans MS' color='06014F'>Expected Result:</font></b>");
			Log.message("<font face='Calibri' color='black'>Customer should able place an order successfully after adding a configured product to cart</font>");

			Log.testCaseResult();

		} // try
		catch (Exception e) {
			Log.exception(e, driver);
		} // catch
		finally {
			Log.endTestCase();
			driver.quit();
		} // finally

	}// TC_LetterMan_OrderPlacement

	@Test(description = "Place an Order with the product from Graduation", enabled = true, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Graduation_OrderPlacement(String browser) throws Exception {

		// ** Loading the test data from excel using the test case id */
		HashMap<String, String> testData = TestDataExtractor.initTestData(
				workbookName, sheetName);

		String executionApp = Utils.getExecutionApp();

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		String schoolName = testData.get("SchoolName");
		String categorylink = testData.get("categorylink");
		String productName = testData.get("productName");
		String productId = testData.get("productId");
		String State = testData.get("State");
		String name = testData.get("Firstname");
		String streeAddress = testData.get("Address");
		String city = testData.get("City");
		String statecode = testData.get("statecode");
		String zipcode = testData.get("Postal");
		String weight = testData.get("weight");
		String height = testData.get("height");

		HomePage homePage = null;

		try {
			if (executionApp == "ProdSite") {
				homePage = new HomePage(driver, ProdwebSite).get();
				Log.message(
						"1. Navigated to 'Production - Balfour' Home Page!",
						driver);
			}
			if (executionApp == "QAsites") {
				homePage = new HomePage(driver, QAWebsite).get();
				Log.message(
						"1. Navigated to 'Development - Balfour' Home Page!",
						driver);
			}

			AffinityPage affinityPage = homePage
					.navigateToAffinityPage(schoolName);
			Log.message("2. Landed on Affinity Page successfully!!", driver);

			CategoryLandingPage clpPage = affinityPage
					.navigateToCLP(categorylink);
			Log.message("3. Navigates to the category landing page!!", driver);

			PDP PdpPage = clpPage.navigateToGraduationProductPage(productId);
			Log.assertThat(
					PdpPage.fetchProductNameOfClassApparels().contains(
							productName),
					"4. Navigated to the exact PDP which we selected from CLP",
					"4. Expected PDP din't displayed, Plz check log event",
					driver);

			Log.message("5. Customizing the product From PDP !!...", driver);
			PdpPage.enterCustomAttributeInGraduationPDP(name, name,
					streeAddress, city, statecode, zipcode, weight, height);
			Log.message("6. Product is successfully customized", driver);

			ShoppingCart shoppingcart = PdpPage.ApparelsAddToCart();
			Log.message("7. Product added to the cart successfully", driver);

			CheckoutPage checkoutPage = shoppingcart.navigateToCheckoutpage();
			Log.message(
					"8. Navigates to the checkoutPage with the added products",
					driver);

			checkoutPage.navigateToBillingSection();
			Log.message("9. Navigate to the Billing section of checkout page",
					driver);

			// ** loading test data to fill Billing details form page
			LinkedHashMap<String, String> BillingInfo = new LinkedHashMap<String, String>(
					testData);
			final String emailAddress = "qaAutomation"
					+ org.apache.commons.lang3.RandomStringUtils.randomNumeric(
							7).toString() + "@gmail.com";
			BillingInfo.put("firstname", testData.get("Firstname"));
			BillingInfo.put("lastname", testData.get("Lastname"));
			BillingInfo.put("Company", testData.get("Company"));
			BillingInfo.put("email", emailAddress);
			BillingInfo.put("confirmEmail", emailAddress);
			BillingInfo.put("Address", testData.get("Address"));
			BillingInfo.put("City", testData.get("City"));
			BillingInfo.put("Postal", testData.get("Postal"));
			BillingInfo.put("Telephone", testData.get("Telephone"));

			checkoutPage.fillBillingSectionDetails(BillingInfo, State);
			Log.message(
					"10. Successfully entered the billig section details and navigates to the Shipping section",
					driver);

			checkoutPage.navigateToPaymentSection();
			Log.message(
					"11. Successfully entered the shipping section details and navigates to the Payment section",
					driver);

			// ** loading test data to fill Billing details form page
			LinkedHashMap<String, String> PaymentDetails = new LinkedHashMap<String, String>(
					testData);
			PaymentDetails.put("CardNumber", testData.get("CardNumber"));
			PaymentDetails.put("CVV", testData.get("CVV"));
			String cardType = testData.get("cardType");
			String date = testData.get("Date");
			String year = testData.get("Year");
			checkoutPage.fillPaymentdetails(PaymentDetails, cardType, date,
					year);
			Log.message(
					"12. Successfully entered the Payment section details and navigates to the Order Review section",
					driver);
			if (executionApp == "QAsites") {
				checkoutPage.placeOrder();
				Log.message(
						"13. Clicked on the Place order button from checkout page",
						driver);
			}
			Log.message("\n");
			Log.message("<b><font face='Comic Sans MS' color='06014F'>Actual Result:</font></b>");
			Log.message("<font face='Calibri' color='black'>Order is placed successfully</font>");
			Log.message("<b><font face='Comic Sans MS' color='06014F'>Expected Result:</font></b>");
			Log.message("<font face='Calibri' color='black'>Customer should able place an order successfully after adding a configured product to cart</font>");

			Log.testCaseResult();

		} // try
		catch (Exception e) {
			Log.exception(e, driver);
		} // catch
		finally {
			Log.endTestCase();
			driver.quit();
		} // finally

	}// TC_LetterMan_OrderPlacement

}