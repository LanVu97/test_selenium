package ninja.testcase;

import ninja.base.Base;
import ninja.pages.*;
import ninja.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Register extends Base {

    public WebDriver driver;
    RegisterPage registerPage;
    @BeforeMethod
    public void setUp(){
        driver = initializeBroswerAndOpenAppUrl(prop.getProperty("broswername"));
        HomePage homePage = new HomePage(driver);
        homePage.clickOnMyAccount();
        registerPage = homePage.selectRegisterOption();

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void verifyRegisterWithMandatoryField(){

        AccountSuccessPage accountSuccessPage = registerPage.registerWithMandatoryField("Auro", "Nguyen", Utilities.generateEmailwithTimeStamp(),"1234567890", "123123", "123123");

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isDisplayLogoutOption(), "The logout is not displayed");

        Assert.assertEquals(accountSuccessPage.getAccountSuccessPageHeading(), "Your Account Has Been Created!");
        Assert.assertEquals(accountSuccessPage.getcongradulations(),"Congratulations! Your new account has been successfully created!");
        Assert.assertEquals(accountSuccessPage.getMemberPrivileges(),"You can now take advantage of member privileges to enhance your online shopping experience with us.");
        Assert.assertEquals(accountSuccessPage.getMemberQuestions(),"If you have ANY questions about the operation of this online shop, please e-mail the store owner.");
        Assert.assertEquals(accountSuccessPage.getConfirmationEmail(),"A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please contact us.");
        Assert.assertTrue(accountSuccessPage.isDisplayContactUsLink(), "The contact us link is not displayed");

        accountSuccessPage.clickOnContinue();
        String actualPageTitle = driver.getTitle();
        String expectedPageTitle = "My Account";
        Assert.assertEquals(actualPageTitle,expectedPageTitle);

    }

    @Test(priority = 2)
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
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isDisplayLogoutOption(), "The logout is not displayed");

        Assert.assertEquals(accountSuccessPage.getAccountSuccessPageHeading(), "Your Account Has Been Created!");
        Assert.assertEquals(accountSuccessPage.getcongradulations(),"Congratulations! Your new account has been successfully created!");
        Assert.assertEquals(accountSuccessPage.getMemberPrivileges(),"You can now take advantage of member privileges to enhance your online shopping experience with us.");
        Assert.assertEquals(accountSuccessPage.getMemberQuestions(),"If you have ANY questions about the operation of this online shop, please e-mail the store owner.");
        Assert.assertEquals(accountSuccessPage.getConfirmationEmail(),"A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please contact us.");
        Assert.assertTrue(accountSuccessPage.isDisplayContactUsLink(), "The contact us link is not displayed");
    }


    @Test(priority = 3)
    public void verifyRegisterWithExistingEmail(){

     registerPage.registerWithMandatoryField("Auro", "Nguyen", "lan123@gmail.com","1234567890", "123123", "123123");

        String actualWarningMessage = registerPage.getduplicateEmailAddressWarning();
        String expectedWarningMessage = "Warning: E-Mail Address is already registered!";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "The expected message is not displayed");
    }

    @Test(priority = 4)
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

    @Test(priority = 5)
    public void registerAccountBySelectingNoNewsletterOption(){

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterFirstName("Auro");
        registerPage.enterLastName("Nguyen");
        registerPage.enterEmailAddress(Utilities.generateEmailwithTimeStamp());
        registerPage.enterTelephone("1234567890");
        registerPage.enterPassword("123123");
        registerPage.enterConfirmPassword("123123");
        registerPage.selectNoNewsletter();
        registerPage.selectPrivacyPolicy();
        registerPage.clickOnContinueButton();

        AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isDisplayLogoutOption(), "The logout is not displayed");

        Assert.assertEquals(accountSuccessPage.getAccountSuccessPageHeading(), "Your Account Has Been Created!");
        Assert.assertEquals(accountSuccessPage.getcongradulations(),"Congratulations! Your new account has been successfully created!");
        Assert.assertEquals(accountSuccessPage.getMemberPrivileges(),"You can now take advantage of member privileges to enhance your online shopping experience with us.");
        Assert.assertEquals(accountSuccessPage.getMemberQuestions(),"If you have ANY questions about the operation of this online shop, please e-mail the store owner.");
        Assert.assertEquals(accountSuccessPage.getConfirmationEmail(),"A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please contact us.");
        Assert.assertTrue(accountSuccessPage.isDisplayContactUsLink(), "The contact us link is not displayed");
    }

    @Test(priority = 6)
    public void registerAccountByProvidingMismatchingPasswords(){

        registerPage.registerWithMandatoryField("Auro", "Nguyen", Utilities.generateEmailwithTimeStamp(),"1234567890", "123123", "1231237");

        Assert.assertEquals(registerPage.getNotMatchPasswordWarning(), "Password confirmation does not match password!");

    }

    @Test(dataProvider="invalidEmailSupplier", priority = 7)
    public void registerAccountUsingInvalidEmailAddress(String invalidEmail){

        registerPage.registerWithMandatoryField("Auro", "Nguyen", invalidEmail,"1234567890", "123123", "123123");

        Assert.assertEquals(registerPage.getEmailWarning(), "E-Mail Address does not appear to be valid!");

    }

    @DataProvider(name="invalidEmailSupplier")
    public String[] supplyInvalidEmailData() {

        String[] invalidEmails = {"user.@example.com","amotoori@gmail",".user@example.com", "useruseruseruseruseruseruseruseruseruseruseruseruser1234567890000@example.com"};
        return invalidEmails;
    }
}
