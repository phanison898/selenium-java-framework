package com.qa.base;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
	
	final public String $root = System.getProperty("user.dir");
	
	final public String $test_config_path = $root + "/src/main/java/com/qa/config/test-config.properties";
	
	final public String $report_config_path = $root + "/src/main/java/com/qa/config/report-config.properties";
	
	final public String $object_repository_path = $root + "";
	
	final public String $report_path = $root + "/Test-Report/index.html";
	
	public Properties TEST_CONFIG, REPORT_CONFIG;
	
	public Config() {
		try {
			TEST_CONFIG = new Properties();
			REPORT_CONFIG = new Properties();
			TEST_CONFIG.load(new FileInputStream($test_config_path));
			REPORT_CONFIG.load(new FileInputStream($report_config_path));
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String TEST_CONFIG(String key) {
		return TEST_CONFIG.getProperty(key);
	}
	
	public String REPORT_CONFIG(String key) {
		return REPORT_CONFIG.getProperty(key);
	}

}
