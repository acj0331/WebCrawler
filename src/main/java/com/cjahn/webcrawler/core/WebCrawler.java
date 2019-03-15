package com.cjahn.webcrawler.core;

import java.util.LinkedHashMap;

import com.cjahn.webcrawler.config.OpenAPIConfig;
import com.cjahn.webcrawler.object.ReqCollect;

public class WebCrawler implements CrawlerInterface{
    protected LinkedHashMap<String, Object> config;

	@SuppressWarnings("unchecked")
    public WebCrawler(Object v) {
	    this.config = (LinkedHashMap<String, Object>)v;
    }

    @Override
	public void init(OpenAPIConfig config) {
	}

	@Override
	public void doCollect(ReqCollect reqCollect) {
	}
}
