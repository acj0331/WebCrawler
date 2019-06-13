package com.cjahn.webcrawler.core.service;

import java.util.LinkedHashMap;

import org.openqa.selenium.WebDriver;

import com.cjahn.webcrawler.object.CollectInfo;

public class CrawlerCore {
	protected LinkedHashMap<String, Object> config;

	public void doCollectAsync(WebDriver driver, CollectInfo collectInfo) throws Exception{
		throw new Exception("Not supported");
	}
	
	public void doCollect(WebDriver driver, CollectInfo collectInfo) throws Exception{
		throw new Exception("Not supported");
	}
}
