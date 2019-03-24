package com.cjahn.webcrawler.core.service;

import java.util.LinkedHashMap;

import com.cjahn.webcrawler.object.ReqCollect;

public class CrawlerCore extends Thread {
	protected LinkedHashMap<String, Object> config;
	protected ReqCollect reqCollect;
	
	public void setReqCollect(ReqCollect reqCollect) {
		this.reqCollect = reqCollect;
	}

	public void doCollectAsync() throws Exception{
		throw new Exception("Not supported");
	}
	
	public void doCollect() throws Exception{
		throw new Exception("Not supported");
	}
}
