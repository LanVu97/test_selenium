package ninja.testcase;

import ninja.base.Base;
import ninja.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Login")).click();
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

    @Test
    public void verifyLoginWithValidCredential(){

        driver.findElement(By.id("input-email")).sendKeys("lan123@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("123123");

        driver.findElement(By.xpath("//input[@value='Login']")).click();

        Assert.assertTrue(driver.findElement(By.linkText("Account")).isDisplayed(), "The account page is not displayed");
        driver.quit();
    }

    @Test
    public void verifyLoginWithInvalidCredential(){

        driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailwithTimeStamp());
        driver.findElement(By.id("input-password")).sendKeys("123123333");

        driver.findElement(By.xpath("//input[@value='Login']")).click();

        String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
        String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "The expected message is not displayed");
        driver.quit();
    }



    @Test
    public void verifyLoginWithInvalidEmailAndValidPassword(){

        driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailwithTimeStamp());
        driver.findElement(By.id("input-password")).sendKeys("123123");

        driver.findElement(By.xpath("//input[@value='Login']")).click();

        String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
        String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "The expected message is not displayed");
        driver.quit();
    }

    @Test
    public void verifyLoginWithValidEmailAndInvalidPassword(){

        driver.findElement(By.id("input-email")).sendKeys("lan1234@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("12312300");

        driver.findElement(By.xpath("//input[@value='Login']")).click();

        String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
        String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "The expected message is not displayed");
        driver.quit();
    }

    @Test
    public void verifyLoginWithoutProvidingCredentals(){

        driver.findElement(By.id("input-email")).sendKeys("");
        driver.findElement(By.id("input-password")).sendKeys("");

        driver.findElement(By.xpath("//input[@value='Login']")).click();

        String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
        String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "The expected message is not displayed");
        driver.quit();
    }

}
