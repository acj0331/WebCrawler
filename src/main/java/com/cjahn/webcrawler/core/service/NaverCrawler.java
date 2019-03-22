package com.cjahn.webcrawler.core.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.annotation.PostConstruct;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cjahn.webcrawler.config.OpenAPIConfig;
import com.cjahn.webcrawler.elasticsearch.service.WebSummaryService;
import com.cjahn.webcrawler.object.NaverBlog;
import com.cjahn.webcrawler.object.NaverNews;
import com.cjahn.webcrawler.object.NaverObject;
import com.cjahn.webcrawler.object.NaverWeb;
import com.cjahn.webcrawler.object.ReqCollect;
import com.cjahn.webcrawler.utility.CrawlerUtil;

@Service
public class NaverCrawler extends CrawlerCore implements NaverCrawlerInterface  {
	@Autowired
	OpenAPIConfig apiConfig;
	
	@Autowired
    WebSummaryService svc;

    protected LinkedHashMap<String, Object> config;
    private int limit;
    private LinkedHashMap<String, Object> urls;
    private HashMap<String, String> httpHeader;
    private int display;
    private String sort;
    private Jsonb jsonb;


    
    @PostConstruct	
	public void init() {
		this.config = (LinkedHashMap<String, Object>) this.apiConfig.getOpenapis().get("naver");
		this.limit = (int) this.config.get("limit");
		this.urls = (LinkedHashMap<String, Object>) this.config.get("urls");
		this.httpHeader = new HashMap<>();
		this.httpHeader.put("X-Naver-Client-Id", (String) this.config.get("client-id"));
		this.httpHeader.put("X-Naver-Client-Secret", (String) this.config.get("client-secret"));
		
		this.display = 10;
		this.sort = "date";
		
		jsonb = JsonbBuilder.create();
	}

	@Override
	public void doCollect(ReqCollect reqCollect) {      
		System.out.println("naver :: doCollect");

        reqCollect.getKeyWordList().forEach(keyword -> {
            try {
                String text = URLEncoder.encode(keyword, "UTF-8");
                this.urls.forEach((apiType, apiURL) -> {
                    apiURL = String.format("%s?query=%s", apiURL, text);

                    String restResult = null;
                    try {
                        for (int start = 1; start <= 1000; start += this.display) {
                            NaverObject restObj = null;
                            if (apiType.equals("blog")) {
                                restResult = collectBlogAndNews(String.valueOf(apiURL), start);
                                restObj = jsonb.fromJson(restResult, NaverBlog.class);
                                for (int i = 0; i < ((NaverBlog)restObj).getItems().size(); i++) {
                                    //send data
//                                    svc.save(((NaverBlog)restObj).getItems().get(i));
                                    System.out.println(((NaverBlog)restObj).getItems().get(i).toString());
                                    
                                    svc.save(((NaverBlog)restObj).getItems().get(i));
                                }
                                
                            } else if (apiType.equals("news")) {
                                restResult = collectBlogAndNews(String.valueOf(apiURL), start);
                                restObj = jsonb.fromJson(restResult, NaverNews.class);
                            } else if (apiType.equals("web")) {
                                restResult = collectWeb(String.valueOf(apiURL), start);
                                restObj = jsonb.fromJson(restResult, NaverWeb.class);
                            }

                            /*
                             * TODO send elastic search & Duplicate check
                             */
                            System.out.println(apiType + "\t"+restResult);
                            if(restObj.getItemSize() != this.display) {
                                break;
                            }
                            
                            
                            

                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                });
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

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
