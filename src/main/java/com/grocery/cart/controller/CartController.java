package com.grocery.cart.controller;


import com.grocery.cart.model.Cart;
import com.grocery.cart.model.CartRequest;
import com.grocery.cart.model.User;
import com.grocery.cart.service.CartRepositoryService;
import com.grocery.cart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartRepositoryService cartRepositoryService;

    @GetMapping("/users")
    public List<User> getUserList(){
        return userService.getUserList();
    }

    @GetMapping("/user/{userName}")
    public Cart getCartByUserName(@PathVariable String userName){
        return cartRepositoryService.getCartByUserName(userName);
    }

    @PostMapping("/user")
    public Cart addToCart(@RequestBody CartRequest cartRequest){
        return cartRepositoryService.addToCart(cartRequest);
    }

    @DeleteMapping("/user/{userName}")
    public String delteCart(@PathVariable String userName){
        return cartRepositoryService.removeFromCart(userName);
    }
}
