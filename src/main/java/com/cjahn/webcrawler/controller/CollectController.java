package com.cjahn.webcrawler.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjahn.webcrawler.elasticsearch.service.CollectESService;
import com.cjahn.webcrawler.object.CollectInfo;

@Controller
public class CollectController {
	@Autowired
	CollectESService collectSvc;
	
    @RequestMapping(value="/collect", method=RequestMethod.GET)
    public String mainPage(
    		@RequestParam(value="startDate", required=false) String startDate, 
    		@RequestParam(value="endDate", required=false) String endDate, 
    		Model model
    	) {
    	List<CollectInfo> collectList = new ArrayList<>();
    	Iterable<CollectInfo> temp = collectSvc.findAll();
    	temp.forEach(action->{
    		collectList.add(action);
    	});
    	
    	model.addAttribute("collectList", collectList);
        model.addAttribute("pagename", "collect");
        /*
         * page name
         * */
        return "collect";
    }
    
    @RequestMapping(value="/doCollect", method=RequestMethod.GET)
    public String doCollect(Model model) {
        model.addAttribute("pagename", "doCollect");

        /*
         * page name
         * */
        return "doCollect";
    }
}
