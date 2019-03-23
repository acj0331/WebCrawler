package com.cjahn.webcrawler.core.service;

import com.cjahn.webcrawler.object.ReqCollect;

public interface NaverCrawlerInterface {
//	public void init(OpenAPIConfig config);

//    @Async("WebCrawlingExecutor")
	public void doCollect(ReqCollect reqCollect);
}
