package ToolsQA.SeleniumJava;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        WebDriver drive;
        String brower = "chrome";

        if(brower.equalsIgnoreCase("firefox")){
            drive = new FirefoxDriver();
        }else if(brower.equalsIgnoreCase("chrome")){
            drive  = new ChromeDriver();

        }else if(brower.equalsIgnoreCase("Edge")){
            drive = new EdgeDriver();
        }else{
            throw new Exception("Incorrect Browser");
        }

        drive.manage().window().maximize(); 

    }
}
