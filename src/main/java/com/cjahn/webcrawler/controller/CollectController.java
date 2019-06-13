package com.cjahn.webcrawler.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjahn.webcrawler.config.OpenAPIConfig;
import com.cjahn.webcrawler.elasticsearch.service.CollectESService;
import com.cjahn.webcrawler.elasticsearch.service.WebSummaryESService;
import com.cjahn.webcrawler.object.CollectInfo;
import com.cjahn.webcrawler.service.WebCrawlerService;

@Controller
public class CollectController {
	@Autowired
	WebCrawlerService crawlerService;
	@Autowired
	OpenAPIConfig apiConfig;
	@Autowired
	CollectESService collectSvc;
	@Autowired
	WebSummaryESService webitemSvc;
	
    @RequestMapping(value="/collect", method=RequestMethod.GET)
    public String mainPage(
    		@RequestParam(value="page", required=false) String page,
    		@RequestParam(value="startDate", required=false) String startDate, 
    		@RequestParam(value="endDate", required=false) String endDate, 
    		Model model
    	) {
    	List<CollectInfo> collectList = new ArrayList<>();
    	int row = 10;
    	int pg = 1;
    	try {
			pg = page==null?1:Integer.parseInt(page);
		} catch (Exception e) {
			pg = 1;
		}
    	pg--;
    	
    	Long total = collectSvc.getTotalCount();
    	if(total!=0) {
    		collectList = collectSvc.findAllSortById(pg, row);
    		collectList.forEach(collect->{
    			collect.setCollectCount(webitemSvc.getTotalCount(collect.getId()));
    		});
    	}
    	model.addAttribute("row", row);
    	model.addAttribute("total", collectSvc.getTotalCount());
    	model.addAttribute("collectList", collectList);
        model.addAttribute("pagename", "collect");
        /*
         * page name
         * */
        return "collect";
    }
    
    @RequestMapping(value="/clear",method=RequestMethod.GET)
    public String clear(Model model) {
    	crawlerService.clearWebDriver();
    	return "collect";
    }
    
    @RequestMapping(value="/cancel", method=RequestMethod.GET)
    public String cancel(
    		@RequestParam(value="id", required=false) String id,
    		Model model) {
    	Optional<CollectInfo> collect = collectSvc.findById(Long.parseLong(id));
    	if(collect.isEmpty())
    		return "collect";
    	
    	CollectInfo info = collect.get();
    	info.setCrawlerStatus("canceled");
    	info.setEndDate(System.currentTimeMillis());
    	collectSvc.save(info);
    	
    	
        return "collect";
    }

    @RequestMapping(value="/collect_info", method=RequestMethod.POST)
    public String startCollect(@RequestBody CollectInfo collect) {
    	crawlerService.doCollect(collect);
    	return "collect_info";	
	}

    @RequestMapping(value="/collect_info", method=RequestMethod.GET)
    public String collect_info(
    		@RequestParam(value="id", required=false) String id,
    		Model model) {
    	List<String> webPortalList = new ArrayList<String>();
    	for(String webPortal : apiConfig.getOpenapis().keySet()) {
    		webPortalList.add(webPortal);
    	}
    	
    	CollectInfo info = null;
        if(id!=null) {
        	Optional<CollectInfo> collect = collectSvc.findById(Long.parseLong(id));
        	if(collect.isEmpty())
        		return "collect";
        	
        	info = collect.get();
        	info.setCollectCount(webitemSvc.getTotalCount(info.getId()));
        }
        model.addAttribute("pagename", "collect_info");
        model.addAttribute("apiConfig", apiConfig.getOpenapis());
        model.addAttribute("collect", info);
        
        return "collect_info";
    }
}
