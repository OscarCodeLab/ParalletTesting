package com.SauceLab.Listeners;

import java.io.IOException;
import java.util.Date;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;


public class ListenerClass implements ITestListener, ISuiteListener {

	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
    public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

	private static ExtentReports extent;
	static {
	    try {
	        extent = ExtentManager.createInstance(System.getProperty("user.dir") + "/reports/" + fileName);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public static ExtentTest test;
	

	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName() + "@TestCase : " + result.getMethod().getMethodName());
		testReport.set(test);
	}

	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		//test.pass(m);
		 testReport.get().pass(m);

	}

	public void onTestFailure(ITestResult result) {
	    try {
	        String screenshotPath = ExtentManager.captureScreenshot();
	        String methodName = result.getMethod().getMethodName();
	        String logText = "<b>TEST CASE:- " + methodName.toUpperCase() + " FAILED</b>";
	        
	        //test.fail("<b><font color=red>Screenshot of failure</font></b><br>",
	            //MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	        
	        testReport.get().fail("<b><font color=red>Screenshot of failure</font></b><br>",
		            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	            
	        //test.log(Status.FAIL, MarkupHelper.createLabel(logText, ExtentColor.RED));
	        testReport.get().log(Status.FAIL, MarkupHelper.createLabel(logText, ExtentColor.RED));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		//test.skip(m);
		testReport.get().skip(m);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

		if (extent != null) {

			extent.flush();
		}

	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

}
