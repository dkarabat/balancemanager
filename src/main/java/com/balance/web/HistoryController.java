package com.balance.web;

import com.balance.service.HistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HistoryController {

    final static Logger log = LoggerFactory.getLogger(HistoryController.class);

    @Autowired
    private HistoryService historyService;

    @RequestMapping("/history")
    public ModelAndView getHistoryList() {
        ModelAndView modelAndView = new ModelAndView("history");
        modelAndView.addObject("history", historyService.getHistory());
        return modelAndView;
    }
}
