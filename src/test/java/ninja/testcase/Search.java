package ninja.testcase;

import ninja.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Search extends Base {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = initializeBroswerAndOpenAppUrl(prop.getProperty("broswername"));

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

    @Test
    public void verifySearchWithValidProduct(){
        driver.findElement(By.name("search")).sendKeys("HP");
        driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
//        driver.findElement(By.xpath("//div[@id='search']//button")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(), 'HP')]")).isDisplayed(), "Valid product is not displayed in the search");

    }

    @Test
    public void verifySearchWithInvalidProduct(){
        driver.findElement(By.name("search")).sendKeys("Fitbit");
        driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();

        String actualSearchMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();

        Assert.assertEquals(actualSearchMessage, "There is no product that matches the search criteria.");

    }

    @Test
    public void verifySearchWithoutAnyProduct(){
        driver.findElement(By.name("search")).sendKeys("");
        driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();

        String actualSearchMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();

        Assert.assertEquals(actualSearchMessage, "There is no product that matches the search criteria.");

    }
}
