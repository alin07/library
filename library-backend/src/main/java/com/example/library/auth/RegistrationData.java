package com.example.library.auth;

import com.example.library.model.Account;


import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class RegistrationData {
    @Getter 
    @Setter
    private Account account;

    @Getter 
    @Setter
    private String password;
}
