package ninja.testcase;

import com.beust.ah.A;
import ninja.base.Base;
import ninja.pages.*;
import ninja.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Register extends Base {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = initializeBroswerAndOpenAppUrl(prop.getProperty("broswername"));
        HomePage homePage = new HomePage(driver);
        homePage.clickOnMyAccount();
        homePage.selectRegisterOption();

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

    @Test
    public void verifyRegisterWithMandatoryField(){

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterFirstName("Auro");
        registerPage.enterLastName("Nguyen");
        registerPage.enterEmailAddress(Utilities.generateEmailwithTimeStamp());
        registerPage.enterTelephone("1234567890");
        registerPage.enterPassword("123123");
        registerPage.enterConfirmPassword("123123");
        registerPage.selectPrivacyPolicy();
        registerPage.clickOnContinueButton();

        AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
        Assert.assertEquals(accountSuccessPage.getAccountSuccessPageHeading(), "Your Account Has Been Created!");
    }

    @Test
    public void verifyRegisterWithAllField(){

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterFirstName("Auro");
        registerPage.enterLastName("Nguyen");
        registerPage.enterEmailAddress(Utilities.generateEmailwithTimeStamp());
        registerPage.enterTelephone("1234567890");
        registerPage.enterPassword("123123");
        registerPage.enterConfirmPassword("123123");
        registerPage.selectYesNewsletter();
        registerPage.selectPrivacyPolicy();
        registerPage.clickOnContinueButton();

        AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
        Assert.assertEquals(accountSuccessPage.getAccountSuccessPageHeading(), "Your Account Has Been Created!");
    }

    @Test
    public void verifyRegisterWithExistingEmail(){

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterFirstName("Auro");
        registerPage.enterLastName("Nguyen");
        registerPage.enterEmailAddress("lan123@gmail.com");
        registerPage.enterTelephone("1234567890");
        registerPage.enterPassword("123123");
        registerPage.enterConfirmPassword("123123");
        registerPage.selectYesNewsletter();
        registerPage.selectPrivacyPolicy();
        registerPage.clickOnContinueButton();

        String actualWarningMessage = registerPage.getduplicateEmailAddressWarning();
        String expectedWarningMessage = "Warning: E-Mail Address is already registered!";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "The expected message is not displayed");
    }

    @Test
    public void verifyRegisterWithoutFillingAnyFields(){

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickOnContinueButton();

        String actualPolicyWarning = registerPage.getPrivacyPolicyWarning();
        String expectedPolicyWarning = "Warning: You must agree to the Privacy Policy!";
        Assert.assertTrue(actualPolicyWarning.contains(expectedPolicyWarning), "The privacy policy warning is not displayed");

        String actualFirstNameWarning = registerPage.getFirstNameWarning();
        String expectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
        Assert.assertTrue(actualFirstNameWarning.contains(expectedFirstNameWarning), "The firstname warning is not displayed");

        String actualLastNameWarning = registerPage.getLastNameWarning();
        String expectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
        Assert.assertTrue(actualLastNameWarning.contains(expectedLastNameWarning), "The lastname warning is not displayed");

        String actualEmailWarning = registerPage.getEmailWarning();
        String expectedEmailWarning = "E-Mail Address does not appear to be valid!";
        Assert.assertTrue(actualEmailWarning.contains(expectedEmailWarning), "The email warning is not displayed");

        String actualTelephoneWarning = registerPage.getTelephoneWarning();
        String expectedTelephoneWarning = "Telephone must be between 3 and 32 characters!";
        Assert.assertTrue(actualTelephoneWarning.contains(expectedTelephoneWarning), "The telephone warning is not displayed");

        String actualPasswordWarning = registerPage.getPasswordWarning();
        String expectedPasswordWarning = "Password must be between 4 and 20 characters!";
        Assert.assertTrue(actualPasswordWarning.contains(expectedPasswordWarning), "The password warning is not displayed");

    }
}
