package com.qa.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.base.Config;

public class ExtentManager extends Config{
	
	private ExtentReports extent;
	private ExtentSparkReporter spark;
	
	public ExtentReports getExtent() {
		if(extent==null) {
			extent = new ExtentReports();
			spark = new ExtentSparkReporter($report_path);
			extentConfig();
			sparkConfig();
			extent.attachReporter(spark);
		}
		return extent;
	}
	
	private void extentConfig() {
		extent.setSystemInfo("Platform", System.getProperty("os.name"));
		extent.setSystemInfo("Java -version", System.getProperty("java.version"));
		extent.setSystemInfo("Author", REPORT_CONFIG("Report-Author"));
		extent.setSystemInfo("Email", REPORT_CONFIG("Report-Author-Email"));
	}
	
	private void sparkConfig() {
		
		if(REPORT_CONFIG("Report-Theme").equalsIgnoreCase("DARK")) {
			spark.config().setTheme(Theme.DARK);
		}else {
			spark.config().setTheme(Theme.STANDARD);
		}
		
		spark.config().setDocumentTitle(REPORT_CONFIG("Report-Title"));
		spark.config().setReportName(REPORT_CONFIG("Report-Name"));
		spark.config().setTimelineEnabled(true);
	}

}
