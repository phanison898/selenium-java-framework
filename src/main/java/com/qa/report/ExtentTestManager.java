package com.qa.report;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
	
	Map<Integer,ExtentTest> testMap = new HashMap<Integer, ExtentTest>();
	ExtentReports extent;
	
	public ExtentTestManager(ExtentReports extent) {
		this.extent = extent;
	}
	
	public synchronized ExtentTest startTest(String testName) {
		ExtentTest test = extent.createTest(testName);
		testMap.put((int)(long)(Thread.currentThread().getId()), test);
		return test;
	}
	
	public synchronized ExtentTest getTest() {
		return testMap.get((int)(long)(Thread.currentThread().getId()));
	}
	
	public synchronized void endTest() {
		extent.removeTest(testMap.get((int)(long)(Thread.currentThread().getId())));
	}

}
