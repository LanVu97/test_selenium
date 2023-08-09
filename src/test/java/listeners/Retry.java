package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int counter = 1;
	int retryMaxLimit = 3;

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub

		if (!result.isSuccess()) { // Check if test not succeed
			if (counter < retryMaxLimit) { // Check if maxtry count is reached
				counter++; // Increase the maxTry count by 1
				// Mark test as failed
				return true; // Tells TestNG to re-run the test
			} else {
				result.setStatus(ITestResult.FAILURE); // If maxCount reached,test marked as failed
			}
		} else {
			result.setStatus(ITestResult.SUCCESS); // If test passes, TestNG marks it as passed
		}
		return false;
	}

}