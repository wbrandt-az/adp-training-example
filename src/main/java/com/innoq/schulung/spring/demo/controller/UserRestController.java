package com.innoq.schulung.spring.demo.controller;

import com.innoq.schulung.spring.demo.domain.User;
import com.innoq.schulung.spring.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/rest")
public class UserRestController {

    private UserRepository userRepository;

    @Autowired
    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(path ="/", method = RequestMethod.GET)
    public List<User> index() {

        List<User> userList = userRepository.findAll();
        return userList;
    }
}
