package com.balance.web;

import com.balance.domain.User;
import com.balance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

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

}
