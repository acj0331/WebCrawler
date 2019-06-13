package com.cjahn.webcrawler.core.service;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cjahn.webcrawler.config.OpenAPIConfig;
import com.cjahn.webcrawler.elasticsearch.service.CollectESService;
import com.cjahn.webcrawler.elasticsearch.service.WebSummaryESService;
import com.cjahn.webcrawler.object.CollectInfo;
import com.cjahn.webcrawler.object.ItemObject;
import com.cjahn.webcrawler.object.NaverObject;
import com.cjahn.webcrawler.utility.CrawlerChecker;
import com.cjahn.webcrawler.utility.CrawlerUtil;


@Service
public class NaverCrawler extends CrawlerCore implements NaverCrawlerInterface  {
	@Autowired
	OpenAPIConfig apiConfig;
	
	@Autowired
    WebSummaryESService webItemEsSvc;
	
	@Autowired
	CollectESService collectEsSvc;

    protected LinkedHashMap<String, Object> config;
    private LinkedHashMap<String, Object> urls;
    private HashMap<String, String> httpHeader;
    private int display;
    private String sort;
    private Jsonb jsonb;
    
	private final String[] searchClasses = {".se-main-container", ".sect_dsc", "#postViewArea"};

    /*
     * TODO : api limit 제어(하루에 25000개)
     * */
    private int limit;
    
    @SuppressWarnings("unchecked")
	@PostConstruct	
	public void init() {
		this.config = (LinkedHashMap<String, Object>) this.apiConfig.getOpenapis().get("naver");
		this.limit = (int) this.config.get("limit");
		this.urls = (LinkedHashMap<String, Object>) this.config.get("urls");
		this.httpHeader = new HashMap<>();
		this.httpHeader.put("X-Naver-Client-Id", (String) this.config.get("client-id"));
		this.httpHeader.put("X-Naver-Client-Secret", (String) this.config.get("client-secret"));
		
		this.display = 10;
		this.sort = "sim";//sim : 유사도 / date : 최근일
		jsonb = JsonbBuilder.create();
		
	}
    
    @Override
    protected void finalize() throws Throwable {
    }
    
    @Override
    @Async("WebCrawlingExecutor")
    public void doCollectAsync(WebDriver driver, CollectInfo collectInfo) {
    	this.doCollect(driver, collectInfo);
    }

    @Override
    public void doCollect(WebDriver driver, CollectInfo collectInfo) {
//    	WebDriver driver = CrawlerUtil.getSeleniumWebDriver();
    	CrawlerChecker checker = new CrawlerChecker();
    	checker.setCollectEsSvc(collectEsSvc);
    	checker.setCollectInfo(collectInfo);
    	checker.start();
    	
    	try {
    		
	    	for (int i = 0; i < collectInfo.getKeyWordList().size(); i++) {
				String keyword = collectInfo.getKeyWordList().get(i);
				String text = URLEncoder.encode(keyword, "UTF-8");
	            Iterator<String> apiTypes = this.urls.keySet().iterator();
	            while( apiTypes.hasNext() ){
	            	String apiType =apiTypes.next();
	            	String apiURL = (String) this.urls.get(apiType);
	                apiURL = String.format("%s?query=%s", apiURL, text);
	                String restResult = null;
	                for (int start = 1; start <= 1000; start += this.display) {
	                    NaverObject restObj = null;
	                    try {
		                    if (apiType.equals("web")) 
		                    	restResult = collectWeb(String.valueOf(apiURL), start);
		                    else
		                    	restResult = collectBlogAndNews(String.valueOf(apiURL), start);	
						} catch (Exception e) {
							continue;
						}
	                    
	                    
	                    restObj = jsonb.fromJson(restResult, NaverObject.class);  
	                    for (int j = 0; j < restObj.getItems().size(); j++) {
	                		/*
	                		 * 크롤링 중간에 취소할 경우 
	                		 * */
	                    	if(checker.getCrawlerStatus().equals("canceled")) {
	                            checker.setStop(true);
	                            checker.join();
//	                            driver.close();

	                    		return;
	                    	}
	                    	
	                        //save data
	                    	ItemObject item =restObj.getItems().get(j);
	                    	item.setCollectId(collectInfo.getId());
	                    	item.setKeyWord(keyword);
	                    	item.setType(apiType);
	                    	
	                    	
	                    	
	                    	Optional<ItemObject> temp = webItemEsSvc.findByHash(item.getHash());
	                    	if(!temp.isEmpty()) {
	                    		ItemObject duplicateItem = temp.get();
	                    		duplicateItem.setCollectId(item.getCollectId());
	                    		webItemEsSvc.save(duplicateItem);
	                    		continue;
	                    	}
	                    	/*
	                    	 * Crawling core
	                    	 * */
	                    	String htmlText = null;
	                    	try {
	                    		String link = item.getLink();
	                    		if(link.contains("?Redirect=Log&amp;logNo=")) {
	                    			link = link.replace("?Redirect=Log&amp;logNo=", "/");
	                    			item.setLink(link);
	                    		}
		                    	driver.get(link);
		                    	Alert alt = driver.switchTo().alert();
		            			if(alt!=null) 
		            				alt.dismiss();
	                    	} catch (Exception e) {
								//alert 미발견시 exception 발생
							}
		                    
	                    	//naver blog일 경우 mainframe을 찾아야함
							try {
		                    	if(apiType.equals("blog")) {
									WebElement frame = null;
			                    	do {
			                    		frame = null;
			                    		if(CrawlerUtil.elementExist(driver, "mainFrame")) {
			            					frame = driver.findElement(By.id("mainFrame"));
			            				}
			            				else if(CrawlerUtil.elementExist(driver, "screenFrame")) {
			            					frame = driver.findElement(By.id("screenFrame"));
			            				}
			            				if(frame!=null)
			            					driver.switchTo().frame(frame);
									} while (frame!=null);
		                    		
		                    		for (int k = 0; k < searchClasses.length; k++) {
		                    			try {
		                    				try {
					                    		WebDriverWait wait = new WebDriverWait(driver, 1);
				                    			wait.until(ExpectedConditions.presenceOfElementLocated(By.className(searchClasses[k])));
				                    			htmlText = driver.findElement(By.cssSelector(searchClasses[k])).getText();	
				                    		} catch (Exception e) {
				                    			htmlText = driver.findElement(By.cssSelector(searchClasses[k])).getText();
											}
										} catch (NoSuchElementException e) {
											continue;
										}
		                    			
		                    			
		                    			if(htmlText!=null)
		                    				 break;
									}
		                    	}
		                    	
		                    	if(htmlText==null) {
		                    		htmlText = driver.findElement(By.tagName("body")).getText();
		                    	}
							} catch (Exception e) {
								htmlText = "";
							}
                    		
	                    	if(htmlText!=null) {
	                    		//api를 통해 찾은 데이터가 null이 아닌경우 keyword가 포함되어 있어야 한다. 
	                    		if(!htmlText.contains(keyword)) {
	                    			htmlText="";
	                    		}
	                    	}
	                    	
	                    	item.setHtmlText(htmlText);
	                        webItemEsSvc.save(item);
	                    }
	                    
	                    if(restObj.getItems().size() != this.display) {
	                        break;
	                    }
	                }
	            }
			}
	    	collectInfo.setEndDate(System.currentTimeMillis());
	    	collectInfo.setCrawlerStatus("finished");
            collectEsSvc.save(collectInfo);
            checker.setStop(true);
            checker.join();
//            driver.close();

    	 } catch (Exception e) {
             e.printStackTrace();
         }
    }
    
    public String collectBlogAndNews(String apiURL, int index) throws Exception {
        apiURL = String.format("%s&display=%d&start=%d&sort=%s", apiURL, this.display, index, this.sort);

        return CrawlerUtil.RequestAPI(apiURL, RequestMethod.GET, httpHeader);
    }

    public String collectWeb(String apiURL, int index) throws Exception {
        apiURL = String.format("%s&display=%d&start=%d", apiURL, this.display, index);

        return CrawlerUtil.RequestAPI(apiURL, RequestMethod.GET, httpHeader);
    }
}
