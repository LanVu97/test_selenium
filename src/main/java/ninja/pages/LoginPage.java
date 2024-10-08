package ninja.pages;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;
    @FindBy(id = "input-email")
    private WebElement emailAddressField;

    @FindBy(id = "input-password")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
    private WebElement emailPasswordNotMatchingWarning;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterEmailAddress(String emailText){
        this.emailAddressField.sendKeys(emailText);
    }

    public void enterPassword(String passwordText){
        this.passwordField.sendKeys(passwordText);
    }

    public void clickOnLoginButton(){
        this.loginButton.click();
    }

    public String getEmailPasswordNotMatchingWarningText(){
        return this.emailPasswordNotMatchingWarning.getText();
    }

    public AccountPage login(String email, String password){
        this.enterEmailAddress(email);
        this.enterPassword(password);
        this.clickOnLoginButton();
        return new AccountPage(driver);
    }
}
