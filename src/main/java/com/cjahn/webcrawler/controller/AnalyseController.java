package com.cjahn.webcrawler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AnalyseController {

    @RequestMapping(value="/analyse", method=RequestMethod.GET)
    public String mainPage(Model model) {
        model.addAttribute("pagename", "analyse");
        
        /*
         * page name
         * */
        return "analyse";
    }
}
