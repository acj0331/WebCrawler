package com.cjahn.webcrawler.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cjahn.webcrawler.object.CollectInfo;
import com.cjahn.webcrawler.service.WebCrawlerService;

@Controller
public class IndexController {
	@Autowired
	WebCrawlerService crawlerService;
	

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String index(Model model){
        
    	 CollectInfo collectInfo = new CollectInfo();
 	    
 	    ArrayList<String> webPortal = new ArrayList<>();
 	    ArrayList<String> keyword = new ArrayList<>();
 	    /*
 	     * TEST
 	     * */
// 	    webPortal.add("naver");
// 	    
// 	    keyword.add("gtec");
// 	    keyword.add("경기과학기술대학교");
// 	    collectInfo.setKeyWordList(keyword);
// 	    collectInfo.setWebPortalList(webPortal);
// 	    
// 	    crawlerService.doCollect(collectInfo);
 	    
 	    
 	    
        model.addAttribute("pagename", "index");
        
        return "index";
    }
}
