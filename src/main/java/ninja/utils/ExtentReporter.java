package ninja.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ExtentReporter {

    public static ExtentReports generateExtentReport(){
        ExtentReports extentReports = new ExtentReports();
        File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\extentReport.html");
        ExtentSparkReporter extentSparkReporter   = new ExtentSparkReporter(extentReportFile);
        extentReports.attachReporter(extentSparkReporter);

        extentSparkReporter.config().setDocumentTitle("TN Automation Report");
        extentSparkReporter.config().setReportName("TutorialNinja Test Report");
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setTimeStampFormat("dd/MM/yy hh:mm:ss");

        Properties prop = new Properties();
        File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\ninja\\config\\config.properties");

        try {

            FileInputStream input = new FileInputStream(propFile) ;

            prop.load(input);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }

        extentReports.setSystemInfo("Application url", prop.getProperty("url"));
        extentReports.setSystemInfo("Browser name", prop.getProperty("broswername"));
        extentReports.setSystemInfo("System info", System.getProperty("os.name"));
        return extentReports;

    }

}
