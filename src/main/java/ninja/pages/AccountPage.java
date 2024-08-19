package ninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

    WebDriver driver;
    @FindBy(linkText = "Account")
    private WebElement accountLink;

    public AccountPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isAccountLinkDisplayed(){
       return this.accountLink.isDisplayed();
    }
}
