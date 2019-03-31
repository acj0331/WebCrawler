package com.cjahn.webcrawler.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.web.bind.annotation.RequestMethod;

public class CrawlerUtil {
	private static String OS = System.getProperty("os.name").toLowerCase();

	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	public static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

	public static boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
	}

	public static boolean isSolaris() {
		return (OS.indexOf("sunos") >= 0);
	}

	public static WebDriver getSeleniumWebDriver() {
		WebDriver driver = null;
		if (isWindows())
			System.setProperty("webdriver.gecko.driver", "src/main/resources/web-driver/geckodriver.exe");	//chrome : webdriver.chrome.driver
		else
			System.setProperty("webdriver.gecko.driver", "src/main/resources/web-driver/geckodriver");
		
		FirefoxOptions options = new FirefoxOptions();
//			options.setHeadless(true);
		driver = new FirefoxDriver(options);
		
		return driver;
	}
	
	public static boolean elementExist(WebDriver driver, String id) {
		try {
			driver.findElement(By.id(id));
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		return true;
	}
	
	
	public static void testWeb() {}

	public static String RequestAPI(String apiURL, RequestMethod method, Map<String, String> header) throws Exception {
		HttpURLConnection con;
		URL url = new URL((String) apiURL);

		con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod(method.toString());

		Set<Entry<String, String>> set = header.entrySet();
		Iterator<Entry<String, String>> itr = set.iterator();
		while (itr.hasNext()) {
			Map.Entry<String, String> e = (Map.Entry<String, String>) itr.next();
			con.setRequestProperty(e.getKey(), e.getValue());
		}
		int responseCode = con.getResponseCode();
		BufferedReader br;
		if (responseCode == 200) { // 정상 호출
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else { // 에러 발생
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = br.readLine()) != null) {
			response.append(inputLine);
		}
		br.close();

		return response.toString();
	}
}
