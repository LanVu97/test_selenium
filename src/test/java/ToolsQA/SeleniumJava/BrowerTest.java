package ToolsQA.SeleniumJava;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowerTest {

	public static void main(String[] args)  {

//		System.setProperty("webdriver.firefox.driver",".\\drivers\\geckodriver\\geckodriver.exe");
//        WebDriver driver = new FirefoxDriver();
		
//		System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//        driver.get("https://selenium.dev");
//        
//        driver.quit();
		googleSearch();
	}
	
	public static void googleSearch() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        
        driver.findElement(By.name("q")).sendKeys("Automation");
               
        driver.findElement(By.name("btnK")).click();
        driver.quit();
	}
	
	
}
