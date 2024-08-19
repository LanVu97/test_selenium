package ninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;
    @FindBy(xpath = "//span[text()='My Account']")
    private WebElement myAccountDropdownMenu;

    @FindBy(linkText = "Login")
    private WebElement loginOption;

    @FindBy(linkText = "Register")
    private WebElement registerOption;

    @FindBy(name = "search")
    private WebElement searchBoxField;

    @FindBy(xpath = "//div[@id='search']/descendant::button")
    private WebElement searchButton;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnMyAccount(){
        this.myAccountDropdownMenu.click();
    }

    public void selectLoginOption(){
        this.loginOption.click();
    }

    public void selectRegisterOption(){
        this.registerOption.click();
    }

    public void enterSearchBoxField(String searchText){
        this.searchBoxField.sendKeys(searchText);
    }

    public void clickOnSearch(){
        this.searchButton.click();
    }
}
