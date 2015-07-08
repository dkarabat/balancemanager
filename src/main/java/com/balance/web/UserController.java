package com.balance.web;

import com.balance.domain.User;
import com.balance.service.UserService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView loginModel = new ModelAndView("login");
        log.info("login form");
        return loginModel;
    }

    @RequestMapping("/register")
    public ModelAndView getRegisterForm(@ModelAttribute("user") User user, BindingResult result) {
        ModelAndView model = new ModelAndView("register");
        log.info("register user {}", user.getUsername());
        return model;
    }

    @RequestMapping("/saveuser")
    public ModelAndView saveUserData(@ModelAttribute("user") User user,
                               BindingResult result, ModelMap model) {
        User newUser = userService.getUserByName(user.getUsername());
        if(newUser == null) {
            log.info("save user {}", user.getUsername());
            userService.addUser(user);
            model.addAttribute("balance", user.getBalance());
            ModelAndView balanceModel = new ModelAndView("balance");
            return balanceModel;
        } else {
            log.info("save user {}", user.getUsername());
            model.addAttribute("info", "Пользователь с таким именем уже зарегистрирован в системе");
            ModelAndView registerModel = new ModelAndView("register");
            return registerModel;
        }
    }

    @RequestMapping("/userlist")
    public ModelAndView getUserList() {
        ModelAndView modelAndView = new ModelAndView("userdetails");
        List<User> userList = userService.getUsers();
        modelAndView.addObject("user", userList);
        log.info("user list size = {}", userList.size());
        return modelAndView;
    }

}
