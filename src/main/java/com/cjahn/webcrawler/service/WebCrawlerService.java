package com.cjahn.webcrawler.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjahn.webcrawler.config.OpenAPIConfig;
import com.cjahn.webcrawler.core.service.CrawlerCore;
import com.cjahn.webcrawler.core.service.KakaoCrawler;
import com.cjahn.webcrawler.core.service.NaverCrawler;
import com.cjahn.webcrawler.elasticsearch.service.CollectESService;
import com.cjahn.webcrawler.object.CollectInfo;

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
    
    @PostConstruct
    public void init() {
    	this.crawlerMap = new HashMap<String, CrawlerCore>();
    	this.crawlerMap.put("naver", naverCrawler);
    	this.crawlerMap.put("kakao", kakaoCrawaler);
    }
	
	public void doCollect(CollectInfo reqCollect) {
		CollectInfo collectInfo = reqCollectESService.save(reqCollect);
		
		reqCollect.getWebPortalList().forEach(webportal->{
			try {
				CrawlerCore crawler = this.crawlerMap.get(webportal);
				if(crawler!=null)
				{
					crawler.setReqCollect(collectInfo);
					crawler.doCollectAsync();	
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		//collect는 별도의 thread로 동작시키며, 해당 thread는 수집중, 수집완료 의 상태를 갖는다.
//		reqCollect.getWebPortalList().forEach(v->{			
//			this.crawlerList.forEach(v2->{
//				try {
//					v2.setReqCollect(collectInfo);
//					v2.doCollectAsync();
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			});
//		});
	}
}
