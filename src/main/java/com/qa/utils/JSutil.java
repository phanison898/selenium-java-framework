package com.qa.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.qa.base.Base;

public class JSutil extends TestUtil{

	
	public void flash(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) getDriver());
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 8; i++) {
			changeColor("rgb(0,200,0)", element);// 1
			changeColor(bgcolor, element);// 2
		}
	}

	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) getDriver());
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}

	public static void drawBorder(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) getDriver());
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public void generateAlert(String message) {
		JavascriptExecutor js = ((JavascriptExecutor) getDriver());
		js.executeScript("alert('" + message + "')");
	}

	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) getDriver());
		js.executeScript("arguments[0].click();", element);

	}

	public void refreshBrowserByJS() {
		JavascriptExecutor js = ((JavascriptExecutor) getDriver());
		js.executeScript("history.go(0)");
	}

	public String getTitleByJS() {
		JavascriptExecutor js = ((JavascriptExecutor) getDriver());
		String title = js.executeScript("return document.title;").toString();
		return title;
	}

	public String getPageInnerText() {
		JavascriptExecutor js = ((JavascriptExecutor) getDriver());
		String pageText = js.executeScript("return document.documentElement.innerText;").toString();
		return pageText;
	}

	public void scrollPageDown() {
		JavascriptExecutor js = ((JavascriptExecutor) getDriver());
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) getDriver());
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public String getBrowserInfo() {
		JavascriptExecutor js = ((JavascriptExecutor) getDriver());
		String uAgent = js.executeScript("return navigator.userAgent;").toString();
		return uAgent;
	}

	public void sendKeysUsingJS(String id, String value) {
		JavascriptExecutor js = ((JavascriptExecutor) getDriver());
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
	}

}
