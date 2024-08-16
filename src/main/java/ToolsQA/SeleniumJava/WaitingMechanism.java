package ToolsQA.SeleniumJava;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.time.Duration;
import java.util.List;

public class WaitingMechanism {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

//        waitDisplayedOnThePage(driver);
//        waitAlertToBePresent(driver);
        waitLoadingProcess(driver);



    }

    public static void waitDisplayedOnThePage(WebDriver driver){
        // doi den khi element do xuat hien, max time la 10 secs
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // global

        driver.get("https://omayo.blogspot.com/");

        driver.findElement(By.className("dropbtn")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement emailOption =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Gmail")));

        emailOption.click();
    }

    public static void waitElementToBeClickAble(WebDriver driver){
        driver.get("https://verifalia.com/validate-email");

        driver.findElement(By.id("inputData")).sendKeys("test");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement validateButton =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Validate']")));

        validateButton.click();
    }

    public static void waitAlertToBePresent(WebDriver driver){
        driver.get("https://omayo.blogspot.com/");

        driver.findElement(By.id("alert1")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println(alertText);
        alert.accept();

        driver.findElement(By.id("ta1")).sendKeys("test");

    }

    public static void waitLoadingProcess(WebDriver driver) throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/");

        driver.findElement(By.linkText("Dynamic Loading")).click();
        driver.findElement(By.linkText("Example 1: Element on page that is hidden")).click();

        driver.findElement(By.xpath("//div[@id='start']/button")).click();

        // c1: doi h4 xuat hien
        // c2: try 10 lan, nhung chua hieu tai sao dung list o day => xem lai
        List<WebElement> processImage = driver.findElements(By.xpath("//div[@id='loading']/img"));

        int count = 0;
        while(processImage.size() != 0 && count <=10){
            Thread.sleep(1000);
            count++;
        }

        String textOnPage = driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();

        System.out.println(textOnPage);
        driver.quit();

    }

    public static void pageLoadTimeout(WebDriver driver){
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));
        driver.get("https://omayo.blogspot.com/");
    }
}
