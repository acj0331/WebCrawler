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
import org.openqa.selenium.firefox.FirefoxProfile;
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
		if (isWindows()) {
			System.setProperty("webdriver.gecko.driver", "src/main/resources/web-driver/geckodriver.exe"); //firefox
			System.setProperty("webdriver.chrome.driver", "src/main/resources/web-driver/chromedriver.73.0.3683.68_win.exe"); //chrome 
		}
		else {
			System.setProperty("webdriver.gecko.driver", "src/main/resources/web-driver/geckodriver");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/web-driver/chromedriver.73.0.3683.68_linux");
		}
		FirefoxProfile profile = new FirefoxProfile();
////		FirefoxProfile profile = new FirefoxProfile(new File("C:\\Users\\changju.ahn\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\8twi9i1b.default"));
////		profile.setPreference("permissions.default.stylesheet", 2);
////		profile.setPreference("permissions.default.image", 2);
////		profile.setPreference("dom.ipc.plugins.enabled.libflashplayer.so", false);
//		// profile.setPreference("dom.disable_open_during_load", true);
		//profile.setPreference("dom.disable_beforeunload", true);
		//profile.setPreference("dom.popup_maximum", 0);
		//profile.setPreference("privacy.popups.showBrowserMessage", false);
//
		//FirefoxOptions options = new FirefoxOptions();
		//options.setProfile(profile);
		// options.setHeadless(true);
		//driver = new FirefoxDriver(options);
		
		
		/*
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		//	prefs.put("profile.default_content_settings.popups", 0);
//		prefs.put("profile.default_content_settiong_values.notifications", 2);
		//prefs.put("profile.default_content_setting_values.popups", 2);
		
		prefs.put("profile.managed_default_content_settings.images",2);
		prefs.put("profile.default_content_setting_values.notifications",2);
		prefs.put("profile.managed_default_content_settings.stylesheets",2);
		prefs.put("profile.managed_default_content_settings.cookies",2);
		prefs.put("profile.managed_default_content_settings.javascript",1);
		prefs.put("profile.managed_default_content_settings.plugins",1);
		prefs.put("profile.managed_default_content_settings.popups",2);
		prefs.put("profile.managed_default_content_settings.geolocation",2);
		prefs.put("profile.managed_default_content_settings.media_stream",2);

		options.setExperimentalOption("prefs", prefs);
//		options.addArguments("-incognito");
		
		//options.addArguments("window-size=1920x1080");
		//options.addArguments("headless");
		//options.addArguments("disable-gpu");
//		
//		driver =new ChromeDriver(options);
//		Map<String, Object> prefs = new HashMap<String, Object>();
//		
//		options.setExperimentalOption("prefs", prefs);
		options.addArguments("test-type");
		options.addArguments("disable-popup-blocking");
		driver = new ChromeDriver(options);*/
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

	public static void testWeb() {
	}

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
