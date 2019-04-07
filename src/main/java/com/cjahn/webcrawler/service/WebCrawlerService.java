package com.cjahn.webcrawler.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjahn.webcrawler.config.OpenAPIConfig;
import com.cjahn.webcrawler.core.service.CrawlerCore;
import com.cjahn.webcrawler.core.service.KakaoCrawler;
import com.cjahn.webcrawler.core.service.NaverCrawler;
import com.cjahn.webcrawler.elasticsearch.service.CollectESService;
import com.cjahn.webcrawler.object.CollectInfo;
import com.cjahn.webcrawler.utility.CrawlerUtil;

@Service
public class WebCrawlerService {
	Map<String, CrawlerCore> crawlerMap;
	
	@Autowired
	CollectESService reqCollectESService;
	
	@Autowired
	OpenAPIConfig apiConfig;

    @Autowired
    NaverCrawler naverCrawler;
    @Autowired
    KakaoCrawler kakaoCrawaler;

    List<WebDriver> webDriverList;
    
    @PostConstruct
    public void init() {
    	this.crawlerMap = new HashMap<String, CrawlerCore>();
    	this.crawlerMap.put("naver", naverCrawler);
    	this.crawlerMap.put("kakao", kakaoCrawaler);
    	this.webDriverList = new ArrayList<WebDriver>();
    }
    
    public void clearWebDriver() {
    	for (int i = 0; i < this.webDriverList.size(); i++) {
			this.webDriverList.get(i).close();
		}
    	this.webDriverList.clear();
    }
    
	
	public void doCollect(CollectInfo reqCollect) {
		CollectInfo collectInfo = reqCollectESService.save(reqCollect);
		
		reqCollect.getWebPortalList().forEach(webportal->{
			try {
				CrawlerCore crawler = this.crawlerMap.get(webportal);
				if(crawler!=null)
				{
			    	WebDriver driver = CrawlerUtil.getSeleniumWebDriver();
			    	
					crawler.doCollectAsync(driver, collectInfo);
					this.webDriverList.add(driver);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
