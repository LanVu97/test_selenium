package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.User;

public class RegistrationPage extends BasePage {

	public RegistrationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

// ####################################################
	public WebElement mainHeading() {
		return find(By.tagName("h1"));
	}

	public WebElement mainErrorSummary() {
		return find(By.className("alert-dismissible"));
	}

	public WebElement loginPageLink() {
		return find(By.linkText("login page"));
	}

	public WebElement fistNameInput() {
		return find(By.id("input-firstname"));
	}

	public WebElement fistNameLabel() {
		return find(By.xpath("//label[@for='input-firstname']"));
	}

	public WebElement lastNameInput() {
		return find(By.id("input-lastname"));
	}

	public WebElement lastNameLabel() {
		return find(By.xpath("//label[@for='input-lastname']"));
	}

	public WebElement emailInput() {
		return find(By.id("input-email"));
	}

	public WebElement emailLabel() {
		return find(By.xpath("//label[@for='input-email']"));
	}

	public WebElement telephoneInput() {
		return find(By.id("input-telephone"));
	}

	public WebElement telephoneLabel() {
		return find(By.xpath("//label[@for='input-telephone']"));
	}

	public WebElement passwordInput() {
		return find(By.id("input-password"));
	}

	public WebElement passwordLabel() {
		return find(By.xpath("//label[@for='input-password']"));
	}

	public WebElement passwordConfirmInput() {
		return find(By.id("input-confirm"));
	}

	public WebElement passwordConfirmLabel() {
		return find(By.xpath("//label[@for='input-confirm']"));
	}

	public WebElement newsletterSubscribeYes() {
		return find(By.xpath("//label[@class=\"radio-inline\"]/input[@value='1']"));
	}

	public WebElement newsletterSubscribeNo() {
		return find(By.xpath("//input[@value='0']"));
	}

	public WebElement subscribeLabel() {
		return find(By.xpath("//label[contains(text(),'Subscribe')]"));
	}

	public WebElement privacyPolicyCheckbox() {
		return find(By.xpath("//input[@type='checkbox']"));
	}

	public WebElement privacyPolicyLink() {
		return find(By.xpath("//a[@class='agree']"));
	}

	public WebElement privacyPolicyModelBody() {
		By locator = By.xpath(".//div[@class='modal-body']");
		wait_for(locator);
		return find(locator);
	}

	public WebElement privacyPolicyModelButton() {
		return find(By.xpath("//button[contains(text(),'×')]"));
	}

	public WebElement continueButton() {
		return find(By.xpath("//input[@value='Continue']"));
	}

//	####################
	public void clickPolicyLink() {

		privacyPolicyLink().click();
	}

	public void clickLoginLink() {

		loginPageLink().click();
	}

	public void open() {
		driver.navigate().to("https://tutorialsninja.com/demo/index.php?route=account/register");
	}

	public void openPrivacyPolicy() {
		privacyPolicyLink().click();
	}

	public String getErrorMessage(String inputLabel) {

		String xpathLocator = String.format("//label[text()='%s']//following-sibling::div/div", inputLabel);
		wait_for(By.xpath(xpathLocator));
		return driver.findElement(By.xpath(xpathLocator)).getText();
	}

	public String getPlaceholder(WebElement element) {
		return element.getAttribute("placeholder");
	}

	public void register(User user, Boolean useEnter) {
		if (!user.getFirstName().isEmpty()) {
			fistNameInput().sendKeys(user.getFirstName());
		}

		if (!user.getLastName().isEmpty()) {
			lastNameInput().sendKeys(user.getLastName());
		}

		if (!user.getEmail().isEmpty()) {
			emailInput().sendKeys(user.getEmail());
		}

		if (!user.getTelephone().isEmpty()) {
			telephoneInput().sendKeys(user.getTelephone());
		}

		if (!user.getPassword().isEmpty()) {
			passwordInput().sendKeys(user.getPassword());
		}

		if (!user.getPasswordConfirm().isEmpty()) {
			passwordConfirmInput().sendKeys(user.getPasswordConfirm());
		}

		if (user.getShouldSubscribe() && !newsletterSubscribeYes().isSelected()) {
			newsletterSubscribeYes().click();
		} else if (!user.getShouldSubscribe() && !newsletterSubscribeNo().isSelected()) {
			newsletterSubscribeNo().click();
		}

		if (user.getAgreePrivacyPolicy()) {
			privacyPolicyCheckbox().click();
		}

		if (useEnter) {
			continueButton().sendKeys(Keys.ENTER);
		} else {
			continueButton().click();
		}
	}

}
