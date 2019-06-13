package com.cjahn.webcrawler.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/*
 * http://seleniumautomationhelper.blogspot.com/2014/02/initializing-webdriver-object-as-thread.html
 * */
public class DriverFactory {
	private DriverFactory() {
		// Do-nothing..Do not allow to initialize this class from outside
	}

	private static DriverFactory instance = new DriverFactory();

	public static DriverFactory getInstance() {
		return instance;
	}

	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() // thread local driver object for webdriver
	{
		@Override
		protected WebDriver initialValue() {
			System.setProperty("java.net.preferIPv4Stack", "true");
			System.setProperty("webdriver.gecko.driver", "src/main/resources/web-driver/geckodriver");
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			return new FirefoxDriver(options); // can be replaced with other browser drivers
		}
	};

	public WebDriver getDriver() // call this method to get the driver object and launch the browser
	{
		return driver.get();
	}

	public void removeDriver() // Quits the driver and closes the browser
	{
		driver.get().quit();
		driver.remove();
	}
}
