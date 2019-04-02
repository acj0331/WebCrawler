package com.cjahn.webcrawler.core.service;

import java.util.LinkedHashMap;

import com.cjahn.webcrawler.object.CollectInfo;

public class CrawlerCore {
	protected LinkedHashMap<String, Object> config;
	protected CollectInfo collectInfo;
//	protected WebDriver driver;
	
	public void setReqCollect(CollectInfo collectInfo) {
		this.collectInfo = collectInfo;
	}

	public void doCollectAsync() throws Exception{
		throw new Exception("Not supported");
	}
	
	public void doCollect() throws Exception{
		throw new Exception("Not supported");
	}
}
