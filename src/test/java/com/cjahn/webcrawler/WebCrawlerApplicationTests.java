package com.cjahn.webcrawler;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;

import com.cjahn.webcrawler.core.service.NaverCrawlerInterface;
import com.cjahn.webcrawler.elasticsearch.service.CollectESService;
import com.cjahn.webcrawler.elasticsearch.service.WebSummaryESService;
import com.cjahn.webcrawler.object.CollectInfo;
import com.cjahn.webcrawler.object.ItemObject;
import com.cjahn.webcrawler.service.WebCrawlerService;
import com.cjahn.webcrawler.utility.CrawlerUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebCrawlerApplicationTests {
	
	
    @Autowired
    WebCrawlerService crawlerService;
	//@Test
	public void contextLoads() {
	    CollectInfo collectInfo = new CollectInfo();
	    
	    ArrayList<String> webPortal = new ArrayList<>();
	    ArrayList<String> keyword = new ArrayList<>();
	    
	    webPortal.add("naver");
	    
	    keyword.add("gtec");
	    keyword.add("경기과학기술대학교");
	    collectInfo.setKeyWordList(keyword);
	    collectInfo.setWebPortalList(webPortal);
	    
	    crawlerService.doCollect(collectInfo);
	    
	}

	@Autowired
	CollectESService collectEsSvc;
	@Autowired
	NaverCrawlerInterface naverCrawler;
	
	@Test
	public void naverCrawlerTest() throws Exception {
	    CollectInfo collectInfo = new CollectInfo();
	    
	    ArrayList<String> keyword = new ArrayList<>();
	    keyword.add("gtec");
	    keyword.add("경기과학기술대학교");
	    collectInfo.setKeyWordList(keyword);
	    
	    //req.setId(System.currentTimeMillis());
	    //req.setCrawlerStatus("crawling");
	    collectEsSvc.save(collectInfo);
		naverCrawler.setReqCollect(collectInfo);
		naverCrawler.doCollect();
		
	}
	
	

	@Autowired
    WebSummaryESService svc;
	
	//@Test
	public void esTest() throws UnsupportedEncodingException {
		ItemObject item = new ItemObject();
		
		item.setLink("https://usa.studyuhak.net/tag/%EC%8A%A4%ED%84%B0%EB%94%94%EC%9C%A0%ED%95%99");
		item.setTitle("'스터디유학' 태그의 글 목록");
		item.setDescription("Nacel International School System- NISS 1964년 미국 뉴욕주에 설립된 비영리교육재단인 Nacel재단에 의해 개설된 Nacel International School System 9개국 15개 도시 19개 학교를 운영하고 있답니다. NISS... ");
		
		PageImpl<ItemObject> tes = (PageImpl<ItemObject>) svc.findAll();
		System.out.println(tes.getTotalElements());
		List<ItemObject> itemList = svc.findByUrl(item.getLink());
		svc.save(item);
		List<ItemObject> itemList2 = svc.findByTitle(item.getTitle());
//		System.out.println(itemList.size());
		
		
		System.out.println();
		
	}
	
	
//	@Test
	public void testSelenium() {
		WebDriver driver = CrawlerUtil.getSeleniumWebDriver();		
		//naver blog
		//TODO -> logic 간소화
		driver.get("https://dimcobiz.blog.me/221358848111");
		System.out.println(driver.getPageSource());
		WebElement frame = null;
		try {
			frame = driver.findElement(By.xpath("//frame"));
			driver.switchTo().frame(frame);

			System.out.println(driver.getPageSource());	
			frame = driver.findElement(By.xpath("//iframe"));
			driver.switchTo().frame(frame);

			System.out.println(driver.findElement(By.xpath("//body")).getText());	
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			
		}
		
		
		driver.get("https://dimcobiz.blog.me/221358848111");
	}
	

}
