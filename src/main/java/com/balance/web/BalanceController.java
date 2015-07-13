package com.balance.web;

import com.balance.domain.History;
import com.balance.domain.User;
import com.balance.service.HistoryService;
import com.balance.service.UserService;
import org.apache.log4j.MDC;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class BalanceController {

    final static Logger log = LoggerFactory.getLogger(BalanceController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private HistoryService historyService;

    @RequestMapping("/bal")
    public String getUserBalance(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User user = userService.getUserByName(userName);
        MDC.put("userName", user.getUsername());
        log.info("user - {} balance = {}", user.getUsername(), user.getBalance());
        model.addAttribute("balance", user.getBalance());
        return "balance";
    }

    @RequestMapping(value = "/addbalance", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView addBalance(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        log.info("Add balance");
        log.info("user id = {}", req.getParameter("userid"));
        log.info("amount = {}", req.getParameter("amount"));
        resp.setHeader("Content-Type", "text/xml; charset=UTF-8");
        JSONObject json = new JSONObject();

        userService.addBalance(Integer.parseInt(req.getParameter("userid")),
                Double.parseDouble(req.getParameter("amount")));
        json.put("balance", String.valueOf(userService.getUserEntityById(Integer.parseInt(req.getParameter("userid"))).getBalance()));
        json.put("id", req.getParameter("userid"));
        resp.getWriter().write(json.toString());
        resp.flushBuffer();

        //вынести добавление истории в отдельный метод
        History history = new History();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        history.setAdmin_name(auth.getName());
        history.setSumm(Double.parseDouble(req.getParameter("amount")));
        history.setUser_name(req.getParameter("userName"));
        history.setUpdate_date(new Date());
        historyService.saveHistory(history);
        return null;
    }
}
