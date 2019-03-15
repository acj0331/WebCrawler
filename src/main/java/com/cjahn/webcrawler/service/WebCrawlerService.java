package com.cjahn.webcrawler.service;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import com.cjahn.webcrawler.config.OpenAPIConfig;
import com.cjahn.webcrawler.core.CrawlerInterface;
import com.cjahn.webcrawler.core.KakaoCrawler;
import com.cjahn.webcrawler.core.NaverCrawler;
import com.cjahn.webcrawler.object.ItemObject;
import com.cjahn.webcrawler.object.ReqCollect;

@Service
public class WebCrawlerService {
	private static final String NAVER = "naver";
	private static final String KAKAO = "kakao";
	
	
	@Autowired
	OpenAPIConfig apiConfig;

    @Autowired
    private ElasticsearchTemplate  esTemplate;
	
	ArrayList<CrawlerInterface> crawlerList;
	
	
	public WebCrawlerService() {
		this.init();
	}
	
	@PostConstruct
	public void init() {
		if(this.apiConfig==null || esTemplate == null)
			return;
		
//		esTemplate.deleteIndex(ItemObject.class);
		
		
		
		crawlerList = new ArrayList<CrawlerInterface>();
		/*
		 * CrawlerFactory
		 * */
//		Map<String, Object> openApiConfigs = apiConfig.getOpenapis();
//		for(Map.Entry<String, Object> entry : openApiConfigs.entrySet()) {
//			if(entry.getKey().equals(NAVER)) {
//				crawlerList.add(new NaverCrawler());
//			}
//			else if(entry.getKey().equals(KAKAO)) {
//				crawlerList.add(new KakaoCrawler());
//			}
//		}
		
		apiConfig.getOpenapis().forEach((k, v) -> {
			if(k.equals(NAVER)) {
				crawlerList.add(new NaverCrawler(v));
			}
			else if(k.equals(KAKAO)) {
				crawlerList.add(new KakaoCrawler(v));
			}	
		});
		
	}
	
	public void doCollect(ReqCollect reqCollect) {
		crawlerList.forEach((v)->{
			v.doCollect(reqCollect);
		});
	}
}
