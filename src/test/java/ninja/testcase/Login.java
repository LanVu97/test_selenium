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

    public WebDriver driver;
    LoginPage loginPage;
    @BeforeMethod
    public void setUp(){
        driver = initializeBroswerAndOpenAppUrl(prop.getProperty("broswername"));
        HomePage homePage = new HomePage(driver);
        homePage.clickOnMyAccount();
        loginPage = homePage.selectLoginOption();

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void verifyLoginWithValidCredential(){
        AccountPage accountPage = loginPage.login("lan123@gmail.com", "123123");
        Assert.assertTrue(accountPage.isAccountLinkDisplayed(), "The account page is not displayed");
    }

    @Test(priority = 2)
    public void verifyLoginWithInvalidCredential(){
        loginPage.login(Utilities.generateEmailwithTimeStamp(), "123123333");

        String actualWarningMessage = loginPage.getEmailPasswordNotMatchingWarningText();
        String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "The expected message is not displayed");
    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailAndValidPassword(){
        loginPage.login(Utilities.generateEmailwithTimeStamp(), "123123");

        String actualWarningMessage = loginPage.getEmailPasswordNotMatchingWarningText();
        String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "The expected message is not displayed");
    }

    @Test(priority = 4)
    public void verifyLoginWithValidEmailAndInvalidPassword(){
        loginPage.login("lan123@gmail.com", "12312300");

        String actualWarningMessage = loginPage.getEmailPasswordNotMatchingWarningText();
        String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "The expected message is not displayed");
    }

    @Test(priority = 5)
    public void verifyLoginWithoutProvidingCredentals(){
        loginPage.login("", "");

        String actualWarningMessage = loginPage.getEmailPasswordNotMatchingWarningText();
        String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "The expected message is not displayed");
    }

}
