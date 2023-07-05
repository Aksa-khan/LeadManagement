package com.ind.utilities;

import java.io.IOException;
import java.util.Arrays;
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
import com.ind.Base.TestBase;

import net.rcarz.jiraclient.Issue;

/* 
* 
* @Usage: This is a Listeners class used for report customization.
*
*/

public class ExtentListeners implements ITestListener, ISuiteListener{
	
	static Date d = new Date();
	
	public static int totalTestCases;
	public static int passTestCases;
	public static int failedTestCases;
	
	
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

	public static ExtentReports extent = ExtentManager
			.createInstance(".\\reports\\" + fileName);

	public static ExtentTest test;
	

	public void onTestStart(ITestResult result) {

		test = extent
				.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		
		totalTestCases++;
		 passTestCases++;
	//	 failedTestCases;

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		test.pass(m);
		System.out.println("pass");

	}

	public void onTestFailure(ITestResult result) {
		
		totalTestCases++; 
		 failedTestCases++;
		 Issue NewIssue=TestBase.generateJiraTicket(result);
			System.out.println("Issues : "+NewIssue.toString());
			System.out.println("Issues : "+NewIssue.getKey());

		 
		   
		
		try {
			ExtentManager.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.fail(result.getThrowable().getMessage());
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+ " FAILED"+"</b>";	
//		String excepionMessage=Arrays.toString(result.getThrowable().getStackTrace());
//		test.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
//				+ "</font>" + "</b >" + "</summary>" +excepionMessage.replaceAll(",", "<br>")+"</details>"+" \n");
//	
//	

		test.fail("<b><font color=red>" + "Screenshot of failure" + "</font></b><br>",MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.fileName)
				.build());
	
		
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		test.log(Status.FAIL, m);
		
		
	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		test.skip(m);

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
		
		//SendEmail.mail(totalTestCases, passTestCases, failedTestCases);

	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

}
