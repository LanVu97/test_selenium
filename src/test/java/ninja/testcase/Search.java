package ninja.testcase;

import ninja.base.Base;
import ninja.pages.HomePage;
import ninja.pages.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Search extends Base {
    public WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = initializeBroswerAndOpenAppUrl(prop.getProperty("broswername"));

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void verifySearchWithValidProduct(){
        HomePage homePage = new HomePage(driver);
        homePage.enterSearchBoxField("HP");
        homePage.clickOnSearch();
//        driver.findElement(By.xpath("//div[@id='search']//button")).click();
        SearchPage searchPage = new SearchPage(driver);
        Assert.assertTrue(searchPage.displayStatusOfvalidHPProduct(), "Valid product is not displayed in the search");

    }

    @Test(priority = 2)
    public void verifySearchWithInvalidProduct(){
        HomePage homePage = new HomePage(driver);
        homePage.enterSearchBoxField("Fitbit");
        homePage.clickOnSearch();

        SearchPage searchPage = new SearchPage(driver);
        String actualSearchMessage = searchPage.getNoProductMessage();
        Assert.assertEquals(actualSearchMessage, "There is no product that matches the search criteria.");

    }

    @Test(priority = 3)
    public void verifySearchWithoutAnyProduct(){
        HomePage homePage = new HomePage(driver);
        homePage.enterSearchBoxField("");
        homePage.clickOnSearch();

        SearchPage searchPage = new SearchPage(driver);
        String actualSearchMessage = searchPage.getNoProductMessage();
        Assert.assertEquals(actualSearchMessage, "There is no product that matches the search criteria.");

    }
}
