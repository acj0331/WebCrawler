package com.cjahn.webcrawler;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;

import com.cjahn.webcrawler.core.service.NaverCrawlerInterface;
import com.cjahn.webcrawler.elasticsearch.service.CollectESService;
import com.cjahn.webcrawler.elasticsearch.service.WebSummaryESService;
import com.cjahn.webcrawler.object.CollectInfo;
import com.cjahn.webcrawler.object.ItemObject;
import com.cjahn.webcrawler.utility.CrawlerUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebCrawlerApplicationTests {
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
	
	
	//@Test
	public void testSelenium() {
		WebDriver driver = CrawlerUtil.getSeleniumWebDriver();		
		//naver blog
		//TODO -> logic 간소화
		
		try {
			//네이버 블로그 대응
			//driver.get("https://blog.naver.com/aaausa1234?Redirect=Log&amp;logNo=221222426904");
			//driver.get("https://cho2626.blog.me/221437938552");
			//driver.get("http://news.unn.net/news/articleView.html?idxno=209130");
			driver.get("https://blog.naver.com/jyara?Redirect=Log&amp;logNo=221488051279");
			//삭제되었거나 존재하지 않는 게시물입니다 대응
			Alert alt = driver.switchTo().alert();
			if(alt!=null) 
				alt.dismiss();
		} catch (Exception e) {
			/*UnhandledAlertException  | NoAlertPresentException */ 
			//alert 미발생 
		}
		WebElement frame = null;
		//System.out.println(driver.getPageSource());
		try {
			do {
				frame = null;
				//frame or iframe 추출
				if(CrawlerUtil.elementExist(driver, "mainFrame")) {
					frame = driver.findElement(By.id("mainFrame"));
				}
				else if(CrawlerUtil.elementExist(driver, "screenFrame")) {
					frame = driver.findElement(By.id("screenFrame"));
				}
				if(frame!=null)
					driver.switchTo().frame(frame);	
			} while(frame!=null);

        	WebDriverWait wait = new WebDriverWait(driver, 10);
        	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("postListBody")));
			System.out.println(driver.findElement(By.id("postListBody")).getText());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		driver.close();
	}
	

}
