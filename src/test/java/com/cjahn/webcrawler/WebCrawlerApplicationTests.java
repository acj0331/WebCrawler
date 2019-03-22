package com.cjahn.webcrawler;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cjahn.webcrawler.core.service.NaverCrawlerInterface;
import com.cjahn.webcrawler.object.ReqCollect;
import com.cjahn.webcrawler.service.WebCrawlerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebCrawlerApplicationTests {
    @Autowired
    WebCrawlerService crawlerService;
    
    

//	@Test
	public void contextLoads() {
	    ReqCollect req = new ReqCollect();
	    
	    ArrayList<String> keyword = new ArrayList<>();
	    keyword.add("gtec");
	    keyword.add("경기과학기술대학교");
	    req.setKeyWordList(keyword);
	    
	    
	    crawlerService.init();
	    crawlerService.doCollect(req);
	    
	}
	
	@Autowired
	NaverCrawlerInterface naverCrawler;
	
	@Test
	public void naverCrawlerTest() {
	    ReqCollect req = new ReqCollect();
	    
	    ArrayList<String> keyword = new ArrayList<>();
	    keyword.add("gtec");
	    keyword.add("경기과학기술대학교");
	    req.setKeyWordList(keyword);
	    
		naverCrawler.doCollect(req);
		
	}

}
