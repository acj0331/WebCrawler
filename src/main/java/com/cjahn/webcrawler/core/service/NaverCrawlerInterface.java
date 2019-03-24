package com.cjahn.webcrawler.core.service;

import com.cjahn.webcrawler.object.ReqCollect;

public interface NaverCrawlerInterface {
	public void setReqCollect(ReqCollect reqCollect);
	public void doCollectAsync() throws Exception;
	public void doCollect() throws Exception;
}
