package com.cjahn.webcrawler.core.service;

import org.openqa.selenium.WebDriver;

import com.cjahn.webcrawler.object.CollectInfo;

public interface NaverCrawlerInterface {
	public void doCollectAsync(WebDriver driver, CollectInfo collectInfo) throws Exception;
	public void doCollect(WebDriver driver, CollectInfo collectInfo) throws Exception;
}
