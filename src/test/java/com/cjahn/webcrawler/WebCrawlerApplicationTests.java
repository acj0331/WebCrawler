package com.cjahn.webcrawler;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;

import com.cjahn.webcrawler.core.service.NaverCrawlerInterface;
import com.cjahn.webcrawler.elasticsearch.service.WebSummaryService;
import com.cjahn.webcrawler.object.ItemObject;
import com.cjahn.webcrawler.object.NaverBlogItem;
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
	
	//@Test
	public void naverCrawlerTest() {
	    ReqCollect req = new ReqCollect();
	    
	    ArrayList<String> keyword = new ArrayList<>();
	    keyword.add("gtec");
	    keyword.add("경기과학기술대학교");
	    req.setKeyWordList(keyword);
	    
		naverCrawler.doCollect(req);
		
	}
	
	

	@Autowired
    WebSummaryService svc;
	
	@Test
	public void esTest() {
		NaverBlogItem item = new NaverBlogItem();
		
		item.setLink("https://usa.studyuhak.net/tag/%EC%8A%A4%ED%84%B0%EB%94%94%EC%9C%A0%ED%95%99");
		item.setTitle("'스터디유학' 태그의 글 목록");
		item.setDescription("Nacel International School System- NISS 1964년 미국 뉴욕주에 설립된 비영리교육재단인 Nacel재단에 의해 개설된 Nacel International School System 9개국 15개 도시 19개 학교를 운영하고 있답니다. NISS... ");
		
		PageImpl<ItemObject> tes = (PageImpl<ItemObject>) svc.findAll();
		System.out.println(tes.getTotalElements());
		List<ItemObject> itemList = svc.findByUrl(item.getUrl());
		svc.save(item);
		List<ItemObject> itemList2 = svc.findByTitle(item.getTitle());
//		System.out.println(itemList.size());
		
		
		System.out.println();
		
	}

}
