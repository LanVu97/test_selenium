package ToolsQA.SeleniumJava;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Set;

public class HandleMultiWindow {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://omayo.blogspot.com/");

        String parentWinId = driver.getWindowHandle();

        driver.findElement(By.linkText("Open a popup window")).click();
        driver.findElement(By.linkText("Blogger")).click();
        Set<String> allWindowsID1 = driver.getWindowHandles();

        for(String window: allWindowsID1){
            System.out.println(window);
            driver.switchTo().window(window);
            String popupTitle =  driver.getTitle();
            if(popupTitle.equalsIgnoreCase("New Window")){
               String text = driver.findElement(By.xpath("//div[@class='example']/h3")).getText();
                System.out.println(text);
//                driver.close();       // close windown is active
            }

        }

        // Get again the window exist
        Set<String> allWindowsID2 = driver.getWindowHandles();
        for(String window: allWindowsID2){

            System.out.println("2" + window);
            driver.switchTo().window(window);
            String popupTitle =  driver.getTitle();
            if(popupTitle.equalsIgnoreCase("Blogger.com - Create a unique and beautiful blog easily.")){
                driver.findElement(By.xpath("//span[text()='Sign in']")).click();
//                driver.close();
            }

        }

        driver.switchTo().window(parentWinId);
        driver.quit();  // close all window


    }
}
