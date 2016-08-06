package com.innoq.schulung.spring.demo.controller;

import com.innoq.schulung.spring.demo.business.UserBusinessService;
import com.innoq.schulung.spring.demo.domain.User;
import com.innoq.schulung.spring.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private UserBusinessService userBusinessService;

    private RestTemplate restTemplate;

    @Autowired
    public UserController(UserBusinessService userBusinessService, RestTemplate restTemplate) {
        this.userBusinessService = userBusinessService;
        this.restTemplate = restTemplate;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        log.info("index called");
        List<User> userList = userBusinessService.findAll();
        List<User> deactivatedUsers = userBusinessService.findByStatus("deactivated");
        List<User> verifiedUsers = userBusinessService.findByStatus("verified");
        model.addAttribute("verifiedUsers", verifiedUsers);
        model.addAttribute("deactivatedUsers", deactivatedUsers);
        model.addAttribute("users", userList);
        model.addAttribute("user", new User());
        return "index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createUser(@ModelAttribute User user, Model model) {
        log.info("user created");
        userBusinessService.saveUser(user);
        return "redirect:/user";
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public String verify(@ModelAttribute User user, Model model) {
        log.info("user verified");
        userBusinessService.changeUserStatus(user.getId(), "verified");
        return "redirect:/user";
    }

    @RequestMapping(value = "/deactivate", method = RequestMethod.POST)
    public String deactivate(@ModelAttribute User user, Model model) {
        log.info("user deactivated");
        userBusinessService.changeUserStatus(user.getId(), "deactivated");
        return "redirect:/user";
    }


}