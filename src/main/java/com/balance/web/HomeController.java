package com.balance.web;

import com.balance.domain.History;
import com.balance.domain.User;
import com.balance.service.HistoryService;
import com.balance.service.RoleService;
import com.balance.service.UserService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class HomeController {

    final static Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private HistoryService historyService;

    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
    public ModelAndView log() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Custom Login Form");
        model.addObject("message", "This is welcome page!");
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

    @RequestMapping("/register")
    public ModelAndView getRegisterForm(@ModelAttribute("user") User user, BindingResult result) {
        ModelAndView model = new ModelAndView("register");
        return model;
    }

    @RequestMapping("/saveuser")
    public String saveUserData(@ModelAttribute("user") User user,
                               BindingResult result, ModelMap model) {
        userService.addUser(user);
        model.addAttribute("balance", user.getBalance());
        return "balance";
    }

    @RequestMapping("/userlist")
    public ModelAndView getUserList() {
        ModelAndView modelAndView = new ModelAndView("userdetails");
        modelAndView.addObject("user", userService.getUser());
        return modelAndView;
    }

    @RequestMapping("/bal")
    public String getUserBalance(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User user = userService.getUserByName(userName);
        model.addAttribute("balance", user.getBalance());
        return "balance";
    }

    @RequestMapping(value = "/popolnenie", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView addBalance(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        log.info("Add balance");
        log.info("user id = {}", req.getParameter("userid"));
        log.info("amount = {}", req.getParameter("amount"));
        resp.setHeader("Content-Type", "text/xml; charset=UTF-8");
        JSONObject json = new JSONObject();
        if (userService.replenishBalance(Integer.parseInt(req.getParameter("userid")),
                Double.parseDouble(req.getParameter("amount"))).equals("updated")) {
            json.put("balance", String.valueOf(userService.getUserEntityById(Integer.parseInt(req.getParameter("userid"))).getBalance()));
            json.put("id", req.getParameter("userid"));
            resp.getWriter().write(json.toString());
            resp.flushBuffer();
        }

        //вынести добавление истории в отдельный метод
        History history = new History();
        history.setAdmin_name("admin");
        history.setSumm(Double.parseDouble(req.getParameter("amount")));
        history.setUser_name(req.getParameter("userid"));
        history.setUpdate_date(new Date());
        historyService.saveHistory(history);
        return null;
    }

    @RequestMapping("/history")
    public ModelAndView getHistoryList() {
        ModelAndView modelAndView = new ModelAndView("history");
        modelAndView.addObject("history", historyService.getHistory());
        return modelAndView;
    }

}
