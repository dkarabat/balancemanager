package com.balance.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    final static Logger log = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
    public ModelAndView log() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Custom Login Form");
        model.addObject("message", "This is welcome page!");
        model.setViewName("login");
        return model;
    }

}
