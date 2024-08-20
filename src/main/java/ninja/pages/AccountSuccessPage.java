package ninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {

    WebDriver driver;
    @FindBy(xpath = "//div[@id='content']/h1")
    private WebElement accountSuccessPageHeading;

    @FindBy(xpath = "//div[@id='content']/h1/following-sibling::p")
    private WebElement congradulationsElement;

    @FindBy(xpath = "//div[@id='content']/h1/following-sibling::p[2]")
    private WebElement memberPrivilegesElement;

    @FindBy(xpath = "//div[@id='content']/h1/following-sibling::p[3]")
    private WebElement memberQuestionsElement;

    @FindBy(xpath = "//div[@id='content']/h1/following-sibling::p[4]")
    private WebElement confirmationEmailElement;

    @FindBy(linkText = "contact us")
    private WebElement contactUsLink;

    @FindBy(xpath = "//a[text()='Continue']")
    private WebElement continueButton;


    public AccountSuccessPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getAccountSuccessPageHeading(){
        return this.accountSuccessPageHeading.getText();
    }

    public String getcongradulations(){
        return this.congradulationsElement.getText();
    }

    public String getMemberPrivileges(){
        return this.memberPrivilegesElement.getText();
    }

    public String getMemberQuestions(){
        return this.memberQuestionsElement.getText();
    }

    public String getConfirmationEmail(){
        return this.confirmationEmailElement.getText();
    }

    public boolean isDisplayContactUsLink(){
        return this.contactUsLink.isDisplayed();
    }

    public void clickOnContinue(){
         this.continueButton.click();

    }
}
