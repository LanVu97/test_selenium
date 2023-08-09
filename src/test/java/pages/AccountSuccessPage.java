package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountSuccessPage extends BasePage {
	public AccountSuccessPage(WebDriver driver) {
		super(driver);

	}

	public String getMainHeadingText() {
		return find(By.xpath("//div[@id='content']/h1")).getText().trim();
	}
}
