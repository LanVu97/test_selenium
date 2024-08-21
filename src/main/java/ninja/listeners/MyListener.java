package ninja.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import ninja.utils.ExtentReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;


public class MyListener implements ITestListener {

    private ExtentReports extentReport;
    private ExtentTest test;

    public void onStart(ITestContext context) {
        extentReport = ExtentReporter.generateExtentReport();
    }

    public void onTestStart(ITestResult result) {
        test = extentReport.createTest(result.getName());
        test.log(Status.INFO, result.getName() + "started excuting");
    }

    public void onTestSuccess(ITestResult result) {
        test = extentReport.createTest(result.getName());
        test.log(Status.PASS, result.getName() + "got successfully executed");
    }

    public void onTestFailure(ITestResult result) {
        WebDriver driver= null;
        try {
            driver= (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        File srcScreenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationScreenShotPath = System.getProperty("user.dir") + "\\screenshots\\"+result.getName()+".png";

        try {
            FileHandler.copy(srcScreenShot, new File(destinationScreenShotPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        test.addScreenCaptureFromPath("/screenshots/"+result.getName()+".png");
        test.log(Status.INFO, result.getThrowable());
        test.log(Status.FAIL, result.getName() + "got fail");


    }

    public void onTestSkipped(ITestResult result) {
        test.log(Status.INFO, result.getThrowable());
        test.log(Status.SKIP, result.getName() + "got skipped");
    }

    public void onFinish(ITestContext context) {
            extentReport.flush();
    }
}
