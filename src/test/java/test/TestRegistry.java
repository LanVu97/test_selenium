package test;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import config.PropertiesFile;
import io.github.bonigarcia.wdm.WebDriverManager;
import model.User;
import pages.AccountSuccessPage;

import pages.RegistrationPage;

public class TestRegistry {

	private WebDriver driver = null;
	private ExtentReports extent;
	private ExtentTest test;
	public static String browserName;
	RegistrationPage registrationPage;
	AccountSuccessPage accountSuccessPage;
	String messageSucess = "Your Account Has Been Created!";
	User user;

	@BeforeSuite
	public void setUp() {
		ExtentSparkReporter spark = new ExtentSparkReporter("ecom.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);

	}

	@BeforeTest
	public void setUpTest() {
		String browserName = PropertiesFile.getProperty("broswer");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		// Mazimize current window
		driver.manage().window().maximize();

		registrationPage = new RegistrationPage(driver);
		accountSuccessPage = new AccountSuccessPage(driver);

	}

	@BeforeMethod
	public void setUpTestCase(Method method) {
		test = extent.createTest(method.getName());

		driver.manage().deleteAllCookies();
		registrationPage.open();

		user = new User();
		user.setEmail(user.createEmail());
		user.setFirstName(PropertiesFile.getProperty("fistName"));
		user.setLastName(PropertiesFile.getProperty("lastName"));
		user.setTelephone(PropertiesFile.getProperty("telephone"));
		user.setPassword(PropertiesFile.getProperty("password"));
		user.setPasswordConfirm(PropertiesFile.getProperty("passwordConfirm"));
		user.setAgreePrivacyPolicy(true);
		user.setShouldSubscribe(false);

	}

	// Verifying elements on Registration page
	@Test
	public void verifyElemntsOnPageTest() throws Exception {

		Assert.assertTrue(registrationPage.mainHeading().isDisplayed());
		Assert.assertTrue(registrationPage.loginPageLink().isDisplayed());
		Assert.assertTrue(registrationPage.fistNameInput().isDisplayed());
		Assert.assertTrue(registrationPage.telephoneInput().isDisplayed());
		Assert.assertTrue(registrationPage.lastNameInput().isDisplayed());
		Assert.assertTrue(registrationPage.emailInput().isDisplayed());
		Assert.assertTrue(registrationPage.passwordInput().isDisplayed());
		Assert.assertTrue(registrationPage.passwordConfirmInput().isDisplayed());
		Assert.assertTrue(registrationPage.newsletterSubscribeNo().isDisplayed());
		Assert.assertTrue(registrationPage.newsletterSubscribeYes().isDisplayed());
		Assert.assertTrue(registrationPage.privacyPolicyCheckbox().isDisplayed());
		Assert.assertTrue(registrationPage.continueButton().isDisplayed());
	}

//	Verifying Privacy policy page redirection
	@Test
	public void termsRedirectionTest() throws Exception {
		registrationPage.clickPolicyLink();

		// to fetch the web elements of the modal content and interact with them
		// code to fetch content of modal body and verify it

		WebElement modalContentBody = registrationPage.privacyPolicyModelBody();

		Assert.assertEquals(modalContentBody.getText(), "Privacy Policy");

		// code to click on close modal button

		WebElement modalClosetButton = registrationPage.privacyPolicyModelButton();
		modalClosetButton.click();
	}

	// Verifying redirection to the Login page from Registration page
	@Test(priority = 3)
	public void loginRedirectionTest() {
		registrationPage.clickLoginLink();

		String expectedURL = "https://tutorialsninja.com/demo/index.php?route=account/login";
		String actualURL = driver.getCurrentUrl();

		Assert.assertEquals(actualURL, expectedURL);

		String expectedTitle = "Account Login";
		String actualTitle = driver.getTitle();

		Assert.assertEquals(actualTitle, expectedTitle);
	}

	// happy path######################
	@Test
	public void userCreatedSuccessfully_when_allRequiredFieldsField_and_clickContinueButton() {

		registrationPage.register(user, false);
		Assert.assertEquals(accountSuccessPage.getMainHeadingText(), messageSucess);

	}

	@Test
	public void userCreatedSuccessfully_when_allRequiredFieldsField_and_pressContinueButtonWithEnter() {

		registrationPage.register(user, true);

		Assert.assertEquals(accountSuccessPage.getMainHeadingText(), messageSucess);
	}

	// boundary values

	@Test
	public void userCreatedSuccessfully_when_firstName1Character() {
		user.setFirstName("A");

		registrationPage.register(user, false);

		Assert.assertEquals(accountSuccessPage.getMainHeadingText(), messageSucess);
	}

	@Test
	public void userCreatedSuccessfully_when_firstName32Characters() {

		user.setFirstName(StringUtils.repeat("A", 32));

		registrationPage.register(user, false);
		Assert.assertEquals(accountSuccessPage.getMainHeadingText(), messageSucess);
	}

	@Test
	public void userCreatedSuccessfully_when_lastName1Character() {

		user.setLastName(StringUtils.repeat("A", 1));

		registrationPage.register(user, false);
		Assert.assertEquals(accountSuccessPage.getMainHeadingText(), messageSucess);
	}

	@Test
	public void userCreatedSuccessfully_when_lastName32Characters() {

		user.setLastName(StringUtils.repeat("A", 32));

		registrationPage.register(user, false);
		Assert.assertEquals(accountSuccessPage.getMainHeadingText(), messageSucess);
	}

	@Test
	public void userCreatedSuccessfully_when_telephone3Character() {

		user.setTelephone("123");

		registrationPage.register(user, false);
		Assert.assertEquals(accountSuccessPage.getMainHeadingText(), messageSucess);
	}

	@Test
	public void userCreatedSuccessfully_when_telephone32Characters() {

		user.setTelephone(StringUtils.repeat("9", 32));
		registrationPage.register(user, false);
		Assert.assertEquals(accountSuccessPage.getMainHeadingText(), messageSucess);
	}

	@Test
	public void userCreatedSuccessfully_when_password4Character() {

		user.setPassword("1234");
		user.setPasswordConfirm("1234");

		registrationPage.register(user, false);

		Assert.assertEquals(accountSuccessPage.getMainHeadingText(), messageSucess);
	}

	@Test
	public void userCreatedSuccessfully_when_password20Characters() {

		user.setPassword(StringUtils.repeat("9", 20));
		user.setPasswordConfirm(StringUtils.repeat("9", 20));

		registrationPage.register(user, false);

		Assert.assertEquals(accountSuccessPage.getMainHeadingText(), messageSucess);
	}

	@Test
	public void userCreatedSuccessfully_when_newsletterSubscribeTrue() {

		user.setShouldSubscribe(true);

		registrationPage.register(user, false);

		Assert.assertEquals(accountSuccessPage.getMainHeadingText(), messageSucess);
	}

	// validations######################################
	@Test
	public void privacyPolicyNotCheckedValidationDisplayed_when_notAgree() {

		user.setAgreePrivacyPolicy(false);

		registrationPage.register(user, false);
		String actualError = registrationPage.mainErrorSummary().getText();
		Assert.assertEquals(actualError, "Warning: You must agree to the Privacy Policy!");

	}

	@Test
	public void firstNameValidationDisplayed_when_emptyFirstName() {

		user.setFirstName("");

		registrationPage.open();
		registrationPage.register(user, false);
		String actualError = registrationPage.getErrorMessage("First Name");
		Assert.assertEquals("First Name must be between 1 and 32 characters!", actualError);

	}

	@Test
	public void firstNameValidationDisplayed_when_firstName33Characters() {

		user.setFirstName(StringUtils.repeat("A", 33));

		registrationPage.register(user, false);

		String actualError = registrationPage.getErrorMessage("First Name");
		Assert.assertEquals(actualError, "First Name must be between 1 and 32 characters!");
	}

	@Test
	public void lastNameValidationDisplayed_when_emptyLastName() {

		user.setLastName("");

		registrationPage.register(user, false);

		String actualError = registrationPage.getErrorMessage("Last Name");
		Assert.assertEquals(actualError, "Last Name must be between 1 and 32 characters!");
	}

	@Test
	public void lastNameValidationDisplayed_when_lastName33Characters() {

		user.setLastName(StringUtils.repeat("A", 33));

		registrationPage.register(user, false);

		String actualError = registrationPage.getErrorMessage("Last Name");
		Assert.assertEquals(actualError, "Last Name must be between 1 and 32 characters!");

	}

	@Test
	public void emailValidationDisplayed_when_emptyEmail() {

		user.setEmail("");
		registrationPage.register(user, false);

		String actualError = registrationPage.getErrorMessage("E-Mail");
		Assert.assertEquals(actualError, "E-Mail Address does not appear to be valid!");

	}

	@Test
	public void emailValidationDisplayed_when_incorrectEmailSet() {

		user.setEmail("aaaa");

		registrationPage.register(user, false);

		String actualError = registrationPage.emailInput().getAttribute("validationMessage");

		Assert.assertEquals(actualError, "Please include an '@' in the email address. 'aaaa' is missing an '@'.");
	}

	@Test
	public void emailValidationDisplayed_when_alreadyemail() {

		user.setEmail("test@test.com");

		registrationPage.register(user, false);

		String actualError = registrationPage.mainErrorSummary().getText();

		Assert.assertEquals(actualError, "Warning: E-Mail Address is already registered!");
	}

	@Test
	public void telephoneValidationDisplayed_when_emptyTelephone() {

		user.setTelephone("");

		registrationPage.register(user, false);

		String actualError = registrationPage.getErrorMessage("Telephone");
		Assert.assertEquals(actualError, "Telephone must be between 3 and 32 characters!");
	}

	@Test
	public void telephoneValidationDisplayed_when_telephone33Characters() {

		user.setTelephone(StringUtils.repeat("3", 33));

		registrationPage.register(user, false);

		String actualError = registrationPage.getErrorMessage("Telephone");
		Assert.assertEquals(actualError, "Telephone must be between 3 and 32 characters!");
	}

	@Test
	public void passwordDisplayedEncrypted_when_typePassword() {

		Assert.assertEquals(registrationPage.passwordInput().getAttribute("type"), "password");
	}

	@Test
	public void passwordConfirmDisplayedEncrypted_when_typePassword() {

	}

	@AfterTest
	public void tearDownTest() {
		driver.close();
		driver.quit();
		System.out.println("Test complete");
	}

	@AfterSuite
	public void tearDown() {
		extent.flush();
	}

}
