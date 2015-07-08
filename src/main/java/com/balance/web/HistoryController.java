package com.balance.web;

import com.balance.domain.History;
import com.balance.service.HistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class HistoryController {

    final static Logger log = LoggerFactory.getLogger(HistoryController.class);

    @Autowired
    private HistoryService historyService;

    @RequestMapping("/history")
    public ModelAndView getHistoryList() {
        ModelAndView modelAndView = new ModelAndView("history");
        List<History> historyList = historyService.getHistory();
        modelAndView.addObject("history", historyList);
        log.info("history list size {}", historyList.size());
        return modelAndView;
    }

    @RequestMapping(value = "/gethistorybydate", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getHistoryByDate(HttpServletRequest req, HttpServletResponse resp) {
        log.info("get history by date");
        ModelAndView modelAndView = new ModelAndView("history");
        String fromDate = req.getParameter("dateFrom");
        String toDate = req.getParameter("dateTo");
        modelAndView.addObject("history", historyService.getHistoryByDate(fromDate, toDate));
        return modelAndView;
    }
}
