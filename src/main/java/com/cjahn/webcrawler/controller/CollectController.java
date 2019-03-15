package com.cjahn.webcrawler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CollectController {

    @RequestMapping(value="/collect", method=RequestMethod.GET)
    public String mainPage(Model model) {
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
