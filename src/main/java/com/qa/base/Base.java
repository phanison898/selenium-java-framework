package com.qa.base;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base extends Config {
	
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public Base() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		if(TEST_CONFIG("Environment").equalsIgnoreCase("local")) {
			SelectBrowser(TEST_CONFIG("Browser"));
		}else if(TEST_CONFIG("Environment").equalsIgnoreCase("server")) {
			SelectBrowser(TEST_CONFIG("Browser"),TEST_CONFIG("Grid-URL"));
		}
	}
	
	@AfterMethod
	public void clean() {
		getDriver().close();
		getDriver().quit();
	}
	
	private void SelectBrowser(String browserName) {
		
		Map<String, Integer> browserMap = new HashMap<String, Integer>();
		browserMap.put(BrowserType.CHROME, 1);
		browserMap.put(BrowserType.FIREFOX, 2);
		browserMap.put(BrowserType.EDGE, 3);
		browserMap.put(BrowserType.IE, 4);
		
		switch(browserMap.get(browserName)) {
		case 1:
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
			break;
		case 2:
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
			break;
		case 3:
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
			break;
		case 4:
			WebDriverManager.iedriver().setup();
			driver.set(new InternetExplorerDriver());
			break;
		default:
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
			break;
		}
		browserInitialization();
	}
	
	private void SelectBrowser(String browserName, String grid_url) {
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
		URL url = null;
		try {
			url = new URL(grid_url);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		driver.set(new RemoteWebDriver(url,capabilities));
		browserInitialization();
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	private void browserInitialization() {
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().pageLoadTimeout(Long.parseLong(TEST_CONFIG("Pageload-wait")), TimeUnit.SECONDS);
		getDriver().manage().timeouts().implicitlyWait(Long.parseLong(TEST_CONFIG("Implicit-wait")), TimeUnit.SECONDS);
		getDriver().manage().timeouts().setScriptTimeout(Long.parseLong(TEST_CONFIG("Scriptload-wait")), TimeUnit.SECONDS);
		getDriver().get(TEST_CONFIG("Application-URL"));
	}
}
