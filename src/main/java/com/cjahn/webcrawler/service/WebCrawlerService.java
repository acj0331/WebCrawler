package com.cjahn.webcrawler.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjahn.webcrawler.config.OpenAPIConfig;
import com.cjahn.webcrawler.core.service.CrawlerCore;
import com.cjahn.webcrawler.core.service.NaverCrawler;
import com.cjahn.webcrawler.object.ReqCollect;

@Service
public class WebCrawlerService {
	private static final String NAVER = "naver";
	private static final String KAKAO = "kakao";
	
	
	@Autowired
	OpenAPIConfig apiConfig;

    @Autowired
    NaverCrawler naverCrawler;
	
	ArrayList<CrawlerCore> crawlerList;
	
	
	public WebCrawlerService() {
	}
	
	//@PostConstruct
	public void init() {
	}
	
	public void doCollect(ReqCollect reqCollect) {
		
		
	}
}
