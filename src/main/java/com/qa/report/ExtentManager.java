package com.qa.report;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
		
		InetAddress ip = null;
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		String host_name = ip.getHostName();
		String host_address = ip.getHostAddress();
		String app_url = String.format("<a href=%s target=_blank>%<s</a>", TEST_CONFIG("Application-URL"));
		String email = String.format("<a href=mailto:%s target=_blank>%<s</a>", REPORT_CONFIG("Report-Author-Email"));
		
		extent.setSystemInfo("Application", app_url);
		extent.setSystemInfo("Browser", TEST_CONFIG("Browser").toUpperCase());
		extent.setSystemInfo("Host-Name", host_name);
		extent.setSystemInfo("Host-Address", host_address);
		extent.setSystemInfo("Host-Platform", System.getProperty("os.name"));
		extent.setSystemInfo("Host Java -v", System.getProperty("java.version"));
		extent.setSystemInfo("Author", REPORT_CONFIG("Report-Author"));
		extent.setSystemInfo("Email", email);
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
