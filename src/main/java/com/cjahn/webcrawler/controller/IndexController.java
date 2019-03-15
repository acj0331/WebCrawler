package com.cjahn.webcrawler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cjahn.webcrawler.service.WebCrawlerService;

@Controller
public class IndexController {
	@Autowired
	WebCrawlerService crawlerService;
	

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String index(Model model){
        
    	crawlerService.doCollect(null);
    	
        model.addAttribute("pagename", "index");
        
        return "index";
    }
}
