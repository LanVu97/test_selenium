package ninja.testcase;

import ninja.base.Base;
import ninja.pages.AccountPage;
import ninja.pages.HomePage;
import ninja.pages.LoginPage;
import ninja.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Login extends Base {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = initializeBroswerAndOpenAppUrl(prop.getProperty("broswername"));
        HomePage homePage = new HomePage(driver);
        homePage.clickOnMyAccount();
        homePage.selectLoginOption();

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

    @Test
    public void verifyLoginWithValidCredential(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAddress("lan123@gmail.com");
        loginPage.enterPassword("123123");
        loginPage.clickOnLoginButton();

        AccountPage accountPage = new AccountPage(driver);
        Assert.assertTrue(accountPage.isAccountLinkDisplayed(), "The account page is not displayed");
    }

    @Test
    public void verifyLoginWithInvalidCredential(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAddress(Utilities.generateEmailwithTimeStamp());
        loginPage.enterPassword("123123333");
        loginPage.clickOnLoginButton();

        String actualWarningMessage = loginPage.getEmailPasswordNotMatchingWarningText();
        String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "The expected message is not displayed");
    }

    @Test
    public void verifyLoginWithInvalidEmailAndValidPassword(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAddress(Utilities.generateEmailwithTimeStamp());
        loginPage.enterPassword("123123");
        loginPage.clickOnLoginButton();

        String actualWarningMessage = loginPage.getEmailPasswordNotMatchingWarningText();
        String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "The expected message is not displayed");
    }

    @Test
    public void verifyLoginWithValidEmailAndInvalidPassword(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAddress("lan1234@gmail.com");
        loginPage.enterPassword("12312300");
        loginPage.clickOnLoginButton();

        String actualWarningMessage = loginPage.getEmailPasswordNotMatchingWarningText();
        String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "The expected message is not displayed");
    }

    @Test
    public void verifyLoginWithoutProvidingCredentals(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAddress("");
        loginPage.enterPassword("");
        loginPage.clickOnLoginButton();

        String actualWarningMessage = loginPage.getEmailPasswordNotMatchingWarningText();
        String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "The expected message is not displayed");
    }

}
