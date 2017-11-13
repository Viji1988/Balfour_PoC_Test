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

public class PDP extends LoadableComponent<PDP> {
	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;

	/**********************************************************************************************
	 ********************************* WebElements of PDP - Starts *****************************
	 **********************************************************************************************/

	@FindBy(css = "#content")
	WebElement pdpSection;

	@FindBy(css = "div[id*='Metal Color and Type']")
	WebElement MetalColorToggle;

	@FindBy(css = "div[id*='Stone Selection']")
	WebElement StoneSelection;

	@FindBy(css = "div[id*='Top Options']")
	WebElement topSelection;

	@FindBy(css = "select[tabindex='12']")
	WebElement drpTopSelection;

	@FindBy(css = "select[data-reactid='.0.0.0.1.3.0.$3.1.$1.0.2.0']")
	WebElement drpslide1;

	@FindBy(css = "select[data-reactid='.0.0.0.1.3.0.$4.1.$1.0.2.0']")
	WebElement drpslide2;

	@FindBy(css = "select[data-reactid='.0.0.0.1.3.0.$3.1.$2.1.$0.0.2.0']")
	WebElement drptTypeDigitSlide1;

	@FindBy(css = "select[data-reactid='.0.0.0.1.3.0.$3.1.$2.1.$4.0.2.0']")
	WebElement drpDigitSlide1;

	@FindBy(css = "select[data-reactid='.0.0.0.1.3.0.$4.1.$2.1.$0.0.2.0']")
	WebElement drptTypeDigitSlide2;

	@FindBy(css = "select[data-reactid='.0.0.0.1.3.0.$4.1.$2.1.$5.0.2.0']")
	WebElement drpDigitSlide2;

	@FindBy(css = "div[id*='Side 1 Design']")
	WebElement Slide1Design;

	@FindBy(css = "input[data-reactid='.0.0.0.1.3.0.$3.1.$0.1.$1.0.2.2']")
	WebElement txtSlide1;

	@FindBy(css = "input[data-reactid='.0.0.0.1.3.0.$4.1.$0.1.$1.0.2.2']")
	WebElement txtSlide2;

	@FindBy(css = "input[data-reactid='.0.0.0.1.3.0.$5.1.$1.0.2.2']")
	WebElement lineEngraving;

	@FindBy(css = "select[data-reactid='.0.0.0.1.3.0.$5.1.$2.0.2.0']")
	WebElement drpBandSize;

	@FindBy(css = "div[id*='Side 2 Design']")
	WebElement Slide2Design;

	@FindBy(css = "div[id*='Band Options']")
	WebElement BandOption;

	@FindBy(css = "div[id*='Ring Care Plan Options']")
	WebElement CarePlan;

	@FindBy(css = "div[id*='Student Name and Grade']")
	WebElement NameAndGrade;

	@FindBy(css = "input[data-reactid='.0.0.0.1.3.0.$7.1.$0.0.2.2']")
	WebElement txtStudFirstName;

	@FindBy(css = "input[data-reactid='.0.0.0.1.3.0.$7.1.$1.0.2.2']")
	WebElement txtStudLastName;

	@FindBy(css = "select[data-reactid='.0.0.0.1.3.0.$7.1.$2.0.2.0']")
	WebElement drpClass;

	@FindBy(css = "div[data-reactid='.0.0.0.1.1.0.1']")
	WebElement labelProductNameOfRings;

	@FindBy(css = "h2[itemprop='name']")
	WebElement labelProductNameOfApparels;

	@FindBy(css = "input[id='options_49215_text']")
	WebElement txtCustom;

	@FindBy(css = "button[data-reactid='.0.0.0.1.5.2']")
	WebElement btnBottomAddToCart;

	@FindBy(css = "button[data-reactid='.0.0.0.1.5.3.0.3.$0']")
	WebElement btnProceedToCheckout;

	@FindBy(css = "button[title*='Add to Cart']")
	WebElement btnApparelAddToCart;

	@FindBy(css = "select[id='ljpOrgSelect']")
	WebElement drpOrganisation;

	@FindBy(css = "input[type='checkbox']")
	WebElement chckman;

	@FindBy(css = "input[id='options_287140_51130_text']")
	WebElement nameOnCard;

	@FindBy(css = "input[id='options_287149_51135_text']")
	WebElement nameFormal;

	@FindBy(css = "input[id='options_287148_51135_text']")
	WebElement txtStreetAddress;

	@FindBy(css = "input[id='options_287147_51135_text']")
	WebElement txtCityconf;

	@FindBy(css = "input[id='options_287146_51135_text']")
	WebElement txtStateCode;

	@FindBy(css = "input[id='options_287145_51135_text']")
	WebElement txtZipCode;

	@FindBy(css = "select#select_287157_51139")
	WebElement drpColor;

	@FindBy(css = "input[id='options_298868_52946_text']")
	WebElement txtWeight;

	@FindBy(css = "input[id='options_298869_52946_text']")
	WebElement txtHeight;

	@FindBy(css = "select#select_298870_52946")
	WebElement drpGender;

	/**********************************************************************************************
	 ********************************* WebElements of PDP Page - Ends **************************
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
	public PDP(WebDriver driver) {
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

		if (isPageLoaded && !(Utils.waitForElement(driver, pdpSection))) {
			Log.fail("PDP Page did not open up. Site might be down.", driver);
		}

	}// isLoaded

	@Override
	protected void load() {
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);

	}

	/**
	 * To Fetch product name for class ring PDP
	 * 
	 * @return
	 */
	public String fetchProductNameOfClassRing() {
		String productName = null;

		try {
			productName = BrowserActions.getText(driver,
					labelProductNameOfRings, "Product Name");
		} catch (Exception e) {
			Log.failsoft("Not able to fetch the product name!!, Plz check the log event");
		}
		return productName;
	}

	/**
	 * To Fetch product name for class apparels and rest of the Categories
	 * 
	 * @return
	 */
	public String fetchProductNameOfClassApparels() {
		String productName = null;

		try {
			productName = BrowserActions.getText(driver,
					labelProductNameOfApparels, "Product Name");
		} catch (Exception e) {
			Log.failsoft("Not able to fetch the product name!!, Plz check the log event");
		}
		return productName;
	}

	/**
	 * Method to configure the PDP of Class Rings based up on the Arguments
	 * passed
	 * 
	 * @param metalcolor
	 * @param metalFinish
	 * @param Stonecolor
	 * @param slide1
	 * @param Stonecut
	 * @param linenum
	 * @param Slide1Bottom
	 * @param palmSide
	 * @param RingCare
	 * @throws Exception
	 */
	public void configureProduct(String metalcolor, String metalFinish,
			String Stonecolor, String slide1, String Stonecut, String linenum,
			String Slide1Bottom, String palmSide, String RingCare)
			throws Exception {
		WebElement Metalcolor = driver.findElement(By.cssSelector("img[src*='"
				+ metalcolor + "']"));
		WebElement MetalFinish = driver.findElement(By.cssSelector("img[src*='"
				+ metalFinish + "']"));
		WebElement StoneColor = driver.findElement(By.cssSelector("img[src*='"
				+ Stonecolor + "']"));
		WebElement StoneCut = driver.findElement(By.cssSelector("img[src*='"
				+ Stonecut + "']"));
		WebElement SlidePersonalisation1 = driver.findElement(By
				.cssSelector("label[for='uc-c-418-" + slide1 + "']"));
		WebElement SlidePersonalisation2 = driver.findElement(By
				.cssSelector("label[for='uc-c-419-" + slide1 + "']"));
		WebElement Slide1numberOfLines = driver.findElement(By
				.cssSelector("label[for='uc-c-527-" + linenum + "']"));
		WebElement Slide2numberOfLines = driver.findElement(By
				.cssSelector("label[for='uc-c-530-" + linenum + "']"));
		WebElement palmSidevalue = driver.findElement(By
				.cssSelector("label[for='uc-c-484-" + palmSide + "']"));
		WebElement slide1Bottom = driver
				.findElement(By
						.cssSelector("img[data-reactid='.0.0.0.1.3.0.$3.1.$2.1.$11.0.2.1.$"
								+ Slide1Bottom + ".1']"));
		WebElement slide2Bottom = driver
				.findElement(By
						.cssSelector("img[data-reactid='.0.0.0.1.3.0.$4.1.$2.1.$11.0.2.1.$"
								+ Slide1Bottom + ".1']"));
		WebElement ringCareValue = driver.findElement(By
				.cssSelector("label[for='uc-c-483-" + RingCare + "']"));
		// Entering Metal color and Type
		BrowserActions.clickOnElement(Metalcolor, driver, "Metal color");
		BrowserActions.clickOnElement(MetalFinish, driver, "Metal Finish");
		Log.message("	5.1 Metal color and type has been properly selected",
				driver);
		// Entering Details of Stone Selection
		BrowserActions.clickOnElement(StoneSelection, driver,
				"Expanded StoneSelection");
		BrowserActions.nap(2);
		BrowserActions.clickOnElement(StoneColor, driver, "Stone Color");
		BrowserActions.clickOnElement(StoneCut, driver, "Stone Cut");
		Log.message("	5.2 Stone color and cut has been properly selected",
				driver);
		// Entering Top options
		BrowserActions.clickOnElement(topSelection, driver,
				"Expanded topSelection");
		BrowserActions.nap(2);
		BrowserActions.selectDropDownValue(driver, drpTopSelection, "4");
		Log.message("	5.3 Top selection value has been properly selected",
				driver);
		// Entering Slide 1 Design fields
		BrowserActions.clickOnElement(Slide1Design, driver, "Slide1Design");
		BrowserActions.nap(2);
		BrowserActions.clickOnElement(SlidePersonalisation1, driver,
				"SlidePersonalisation1");
		BrowserActions.clickOnElement(Slide1numberOfLines, driver,
				"Slide1numberOfLines");
		BrowserActions.typeOnTextField(txtSlide1, "TEST", driver, "txtSlide1");
		BrowserActions.selectDropDownValue(driver, drpslide1, "3");
		BrowserActions.selectDropDownValue(driver, drptTypeDigitSlide1, "3");
		BrowserActions.selectDropDownValue(driver, drpDigitSlide1, "3");
		BrowserActions.clickOnElement(slide1Bottom, driver, "slide1Bottom");
		Log.message("	5.4 Slide 1 Design value has been properly Entered",
				driver);
		// Entering Slide 2 Design fields
		BrowserActions.clickOnElement(Slide2Design, driver, "Slide2Design");
		BrowserActions.nap(2);
		BrowserActions.clickOnElement(SlidePersonalisation2, driver,
				"SlidePersonalisation2");
		BrowserActions.clickOnElement(Slide2numberOfLines, driver,
				"Slide2numberOfLines");
		BrowserActions.typeOnTextField(txtSlide2, "TEST", driver, "txtSlide2");
		BrowserActions.selectDropDownValue(driver, drpslide2, "3");
		BrowserActions.selectDropDownValue(driver, drptTypeDigitSlide2, "3");
		BrowserActions.selectDropDownValue(driver, drpDigitSlide2, "3");
		BrowserActions.clickOnElement(slide2Bottom, driver, "slide2Bottom");
		Log.message("	5.5 Slide 2 Design value has been properly Entered",
				driver);
		// Entering band options fields
		BrowserActions.clickOnElement(BandOption, driver, "BandOption");
		BrowserActions.nap(2);
		BrowserActions.clickOnElement(palmSidevalue, driver, "palmSidevalue");
		BrowserActions.typeOnTextField(lineEngraving, "TEST", driver,
				"lineEngraving");
		BrowserActions.selectDropDownValue(driver, drpBandSize, "3");
		Log.message("	5.6 Band Option value has been properly Entered", driver);
		// Entering Ring Care Plan fields
		BrowserActions.clickOnElement(CarePlan, driver, "CarePlan");
		BrowserActions.nap(2);
		BrowserActions.clickOnElement(ringCareValue, driver, "ringCareValue");
		Log.message("	5.7 Ring Care Value value has been properly Entered",
				driver);
		// Entering Student name and grade fields
		BrowserActions.clickOnElement(NameAndGrade, driver, "NameAndGrade");
		BrowserActions.nap(2);
		BrowserActions.typeOnTextField(txtStudFirstName, "Aspire", driver,
				"txtStudFirstName");
		BrowserActions.typeOnTextField(txtStudLastName, "QA", driver,
				"txtStudLastName");
		BrowserActions.selectDropDownValue(driver, drpClass, "3");
		Log.message("	5.8 Student details has been properly Entered", driver);
	}

	/**
	 * Method to enter custom attribute from PDP
	 * 
	 * @throws Exception
	 */
	public void enterCustomAttributeInPDP() throws Exception {
		BrowserActions.typeOnTextField(txtCustom, "ASPIRE", driver,
				"Custom Text");
		BrowserActions.selectDropDownValue(driver, drpOrganisation, "4");
		BrowserActions.clickOnElement(chckman, driver, "Mandatory checkbox");

	}

	/**
	 * To Add a product to cart of Apparels and rest of the categories
	 * 
	 * @return
	 */
	public ShoppingCart ApparelsAddToCart() {
		try {
			BrowserActions.clickOnElement(btnApparelAddToCart, driver,
					"Bottom Add to cartButton");
		} catch (Exception e) {
			Log.fail("Add to cart button is disabled, its due to the product is not configured properly");
		}
		return new ShoppingCart(driver).get();

	}

	/**
	 * Method to add the product to the cart
	 * 
	 * @return
	 */
	public ShoppingCart addToCart() {
		try {
			BrowserActions.clickOnElement(btnBottomAddToCart, driver,
					"Bottom Add to cartButton");
			BrowserActions.nap(1);
			BrowserActions.clickOnElement(btnProceedToCheckout, driver,
					"Proceed To Checkout");
		} catch (Exception e) {
			Log.fail("Add to cart button is disabled, its due to the product is not configured properly");
		}
		return new ShoppingCart(driver).get();

	}

	/**
	 * Method to enter the custom attribute from graduation PDP
	 * 
	 * @param name
	 * @param nameformal
	 * @param streeAddress
	 * @param city
	 * @param statecode
	 * @param zipcode
	 * @param weight
	 * @param height
	 * @throws Exception
	 */
	public void enterCustomAttributeInGraduationPDP(String name,
			String nameformal, String streeAddress, String city,
			String statecode, String zipcode, String weight, String height)
			throws Exception {
		BrowserActions
				.typeOnTextField(nameOnCard, name, driver, "Name On Card");
		BrowserActions.typeOnTextField(nameFormal, nameformal, driver,
				"Name Formal");
		BrowserActions.typeOnTextField(txtStreetAddress, streeAddress, driver,
				"Street Address");
		BrowserActions.typeOnTextField(txtCityconf, city, driver, "City");
		Log.message("	5.1  Name, Address and City has entered properly !!...",
				driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, 150);");
		BrowserActions.typeOnTextField(txtStateCode, statecode, driver,
				"State Code");
		BrowserActions.typeOnTextField(txtZipCode, zipcode, driver, "ZipCode");
		Log.message("	5.2  State code and Zipcode has entered properly !!...",
				driver);
		jse.executeScript("scroll(0, 150);");
		BrowserActions.selectDropDownValue(driver, drpColor, "3");
		BrowserActions.typeOnTextField(txtWeight, weight, driver, "Weight");
		BrowserActions.selectDropDownValue(driver, drpGender, "1");
		BrowserActions.typeOnTextField(txtHeight, height, driver, "Height");
		Log.message(
				"	5.3  Height, weight and Gender has entered properly !!...",
				driver);

	}
}