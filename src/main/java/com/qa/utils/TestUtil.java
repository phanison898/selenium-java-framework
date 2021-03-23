package com.qa.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.Base;

public class TestUtil extends Base {

	public static void generateHtmlFile(String src, String dest) {
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new FileReader(src));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String str = "";
		String s;
		try {
			while ((s = bf.readLine()) != null) {
				str += s;
				str += "<br />";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String html = "<!DOCTYPE html><html><head></head><body><center>" + str + "</center></body></html>";
		FileWriter writter = null;
		try {
			writter = new FileWriter(new File(dest));
			writter.write(html);
			writter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String readFile(String path) {
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String str = "";
		String s;
		try {
			while ((s = bf.readLine()) != null) {
				str += s;
				str += "<br />";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static void generateModifiedHtmlReport() {
		StringBuilder str = new StringBuilder();
		String path = "./TestResults/Report/UnitTestReport.html";
		String data = readFile(path);
		String middle = "<ul id=" + "'" + "nav-mobile" + "'";
		String start = data.split(middle)[0];
		String end = data.split(middle)[1];
		String target = "<ul id=" + "'" + "Logger_section" + "'" + " class=" + "'"
				+ "right hide-on-med-and-down nav-right" + "'" + ">" + "<h6>Click this logger</h6></ul>";
		StringBuilder builder = new StringBuilder();
		builder.append(start);
		builder.append(target);
		builder.append(middle);
		builder.append(end);
		FileWriter writter = null;
		try {
			writter = new FileWriter(new File("./TestResults/Report/UnitTestReport.html"));
			writter.write(builder.toString());
			writter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public WebElement getElement(By selector) {
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(selector));
			element = getDriver().findElement(selector);
		} catch (Exception e) {
			System.out.println("element could not be created");
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return element;
	}

	public void sendKeysElement(By selector, String value) {
		try {
			getElement(selector).sendKeys(value);
		} catch (Exception e) {
			System.out.println("some issue with sendKeys");
		}
	}

	public void clickOnElement(By selector) {
		getElement(selector).click();
	}

	public String getTextElement(By selector) {
		return getElement(selector).getText();
	}

	public String getPageTitle() {
		return getDriver().getTitle();
	}

}
