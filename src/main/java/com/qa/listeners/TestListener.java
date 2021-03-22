package com.qa.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.report.ExtentManager;
import com.qa.report.ExtentTestManager;

public class TestListener implements ITestListener {
	
	ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	ExtentReports extent = new ExtentManager().getExtent();
	String category;
	String testcase;
	
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	public void onStart(ITestContext context) {
		
	}
	
	public void onTestFailure(ITestResult result) {
		System.out.println("The test-case [ "+result.getMethod().getMethodName()+" ] --> Failed");
		test.get().log(Status.FAIL, "Test case failed");
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("The test-case [ "+result.getMethod().getMethodName()+" ] --> Skipped");
		test.get().log(Status.SKIP, "Test case skipped");
	}

	public void onTestStart(ITestResult result) {
		System.out.println("The test-case [ "+result.getMethod().getMethodName()+" ] --> Started");
		
		category = result.getInstance().getClass().getSimpleName();
		testcase = result.getMethod().getMethodName();
		
		ExtentTestManager testManager = new ExtentTestManager(extent);
		
		test.set(testManager.startTest(testcase));
		test.get().assignCategory(category);
		test.get().log(Status.INFO, "Test case started");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("The test-case [ "+result.getMethod().getMethodName()+" ] --> Passed");
		test.get().log(Status.PASS, "Test case passed");
	}
}
