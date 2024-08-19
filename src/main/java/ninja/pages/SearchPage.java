package ninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
    WebDriver driver;

    @FindBy(xpath = "//a[contains(text(), 'HP')]")
    private WebElement validHPProduct;

    @FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
    private WebElement noProductMessage;

    public SearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean displayStatusOfvalidHPProduct(){
        return this.validHPProduct.isDisplayed();
    }


    public String  getNoProductMessage(){
        return this.noProductMessage.getText();
    }



}
