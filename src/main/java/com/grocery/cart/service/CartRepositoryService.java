package com.grocery.cart.service;

import com.grocery.cart.data.CartRepository;
import com.grocery.cart.model.Cart;
import com.grocery.cart.model.CartRequest;
import com.grocery.cart.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartRepositoryService {

    @Autowired
    private CartRepository cartRepository;


    public Cart getCartByUserName(String userName) {
        Optional<Cart> cart = cartRepository.findById(userName);
        if(cart.isPresent())
            return cart.get();
        else
            return null;
    }

    public Cart addToCart(CartRequest cartRequest) {
        Optional<Cart> userCart = cartRepository.findById(cartRequest.getUserName());
        if(userCart.isPresent()){
            Cart cart = userCart.get();
            List<Item> existingItem = cart.getCarItem().stream().filter(item -> item.getProductId().equals(cartRequest.getProductId())).collect(Collectors.toList());
            List<Item> updatedCarItem = cart.getCarItem();
            if(existingItem.size()>0){
                int newQuantity =  existingItem.get(0).getQuantity() + cartRequest.getQuantity();
                int index = cart.getCarItem().indexOf(existingItem.get(0));
                updatedCarItem.get(index).setQuantity(newQuantity);
            }else {
                updatedCarItem.add(new Item(cartRequest.getProductId(),cartRequest.getQuantity()));
            }
            cart.setCarItem(updatedCarItem);
            return cartRepository.save(cart);
        }else{
            List<Item> itemList = Collections.singletonList(new Item(cartRequest.getProductId(), cartRequest.getQuantity()));
            return cartRepository.save(new Cart(cartRequest.getUserName(),itemList));
        }
    }

    public String removeFromCart(String userName){
        Optional<Cart> userCart = cartRepository.findById(userName);
        if(userCart.isPresent()){
            cartRepository.delete(userCart.get());
            return "Cart deleted for "+ userName;
        }else{
            return "Cart not found for "+ userName;
        }
    }
}
