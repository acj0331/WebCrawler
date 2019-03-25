package com.cjahn.webcrawler.core.service;

import com.cjahn.webcrawler.object.CollectInfo;

public interface NaverCrawlerInterface {
	public void setReqCollect(CollectInfo reqCollect);
	public void doCollectAsync() throws Exception;
	public void doCollect() throws Exception;
}
