package com.grocery.cart.controller;


import com.grocery.cart.model.User;
import com.grocery.cart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUserList(){
        return userService.getUserList();
    }
}
