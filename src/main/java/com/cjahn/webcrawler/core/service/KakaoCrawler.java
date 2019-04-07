package com.cjahn.webcrawler.core.service;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjahn.webcrawler.config.OpenAPIConfig;
import com.cjahn.webcrawler.elasticsearch.service.WebSummaryESService;
import com.cjahn.webcrawler.object.CollectInfo;

@Service
public class KakaoCrawler extends CrawlerCore  implements KakoCrawlerInterface{
	@Autowired
	OpenAPIConfig apiConfig;	

	@Autowired
    WebSummaryESService svc;
	
	
	@Override
	public void doCollect(WebDriver driver, CollectInfo collectInfo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("kakao crawler not developed!!!!!!!!!!!!!!!!!!!!");
	}
}
