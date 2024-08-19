package ninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    WebDriver driver;
    @FindBy(id = "input-firstname")
    private WebElement firstNameField;

    @FindBy(id = "input-lastname")
    private WebElement lastNameField;

    @FindBy(id= "input-email")
    private WebElement emailField;

    @FindBy(id= "input-telephone")
    private WebElement telephoneField;

    @FindBy(id= "input-password")
    private WebElement passwordField;

    @FindBy(id= "input-confirm")
    private WebElement passwordConfirmField;

    @FindBy(xpath= "//input[@name='newsletter'][@value='1']")
    private WebElement yesNewsletterOption;

    @FindBy(name = "agree")
    private WebElement privacyPolicyField;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
    private WebElement duplicateEmailAddressWarning;

    @FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
    private WebElement privacyPolicyWarning;

    @FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
    private WebElement firstNameWarning;

    @FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
    private WebElement lastNameWarning;

    @FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
    private WebElement emailWarning;

    @FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
    private WebElement telephoneWarning;

    @FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
    private WebElement passwordWarning;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterFirstName(String firstNameText){
        this.firstNameField.sendKeys(firstNameText);
    }

    public void enterLastName(String lastNameText){
        this.lastNameField.sendKeys(lastNameText);
    }

    public void enterEmailAddress(String emailAddressText){
        this.emailField.sendKeys(emailAddressText);
    }

    public void enterTelephone(String telephoneText){
        this.telephoneField.sendKeys(telephoneText);
    }

    public void enterPassword(String passwordText){
        this.passwordField.sendKeys(passwordText);
    }

    public void enterConfirmPassword(String confirmPasswordText){
        this.passwordConfirmField.sendKeys(confirmPasswordText);
    }

    public void selectYesNewsletter(){
        this.yesNewsletterOption.click();
    }

    public void selectPrivacyPolicy(){
        this.privacyPolicyField.click();
    }

    public void clickOnContinueButton(){
        this.continueButton.click();
    }

    public String getduplicateEmailAddressWarning(){
        return this.duplicateEmailAddressWarning.getText();
    }

    public String getPrivacyPolicyWarning(){
        return this.privacyPolicyWarning.getText();
    }

    public String getFirstNameWarning(){
        return this.firstNameWarning.getText();
    }

    public String getLastNameWarning(){
        return this.lastNameWarning.getText();
    }

    public String getTelephoneWarning(){
        return this.telephoneWarning.getText();
    }

    public String getEmailWarning(){
        return this.emailWarning.getText();
    }

    public String getPasswordWarning(){
        return this.passwordWarning.getText();
    }

    public AccountSuccessPage registerWithMandatoryField(String firstName, String lastName, String email, String telephone, String password, String confirmPassword){

        this.enterFirstName(firstName);
        this.enterLastName(lastName);
        this.enterEmailAddress(email);
        this.enterTelephone(telephone);
        this.enterPassword(password);
        this.enterConfirmPassword(confirmPassword);
        this.selectPrivacyPolicy();
        this.clickOnContinueButton();
        return new AccountSuccessPage(driver);
    }
}
