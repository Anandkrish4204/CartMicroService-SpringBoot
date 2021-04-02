package com.grocery.cart.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartRequest {

    private String userName;
    private String productId;
    private int quantity;
}
