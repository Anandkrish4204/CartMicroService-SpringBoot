package com.grocery.cart.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String username;
    private String password;
    private String fullName;
    private String contactNumber;

}
