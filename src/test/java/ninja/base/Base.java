package ninja.base;

import ninja.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class Base {

    WebDriver driver;
    public Properties prop;
    public Base(){
        prop = new Properties();
        File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\ninja\\config\\config.properties");
        try {
//                FileInputStream input = new FileInputStream("./src/main/java/ninja/config/config.properties") ;
            FileInputStream input = new FileInputStream(propFile) ;

            prop.load(input);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }

    }
    public WebDriver initializeBroswerAndOpenAppUrl(String browser){

        if(browser.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        }else if (browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
        driver.get(prop.getProperty("url"));

        return driver;
    }
}
