package com.cjahn.webcrawler.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjahn.webcrawler.config.OpenAPIConfig;
import com.cjahn.webcrawler.core.service.CrawlerCore;
import com.cjahn.webcrawler.core.service.KakaoCrawler;
import com.cjahn.webcrawler.core.service.NaverCrawler;
import com.cjahn.webcrawler.elasticsearch.service.ReqCollectESService;
import com.cjahn.webcrawler.object.ReqCollect;

@Service
public class WebCrawlerService {
	List<CrawlerCore> crawlerList;
	
	@Autowired
	ReqCollectESService reqCollectESService;
	
	@Autowired
	OpenAPIConfig apiConfig;

    @Autowired
    NaverCrawler naverCrawler;
    @Autowired
    KakaoCrawler kakaoCrawaler;
    
    @PostConstruct
    public void init() {
    	this.crawlerList = new ArrayList<CrawlerCore>();
    	this.crawlerList.add(naverCrawler);
    	//this.crawlerList.add(kakaoCrawaler);
    }
	
	public void doCollect(ReqCollect reqCollect) {
		ReqCollect collectInfo = reqCollectESService.save(reqCollect);
		
		//collect는 별도의 thread로 동작시키며, 해당 thread는 수집중, 수집완료 의 상태를 갖는다.
		reqCollect.getWebPortalList().forEach(v->{			
			this.crawlerList.forEach(v2->{
				try {
					v2.setReqCollect(collectInfo);
					v2.doCollectAsync();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		});
	}
}
