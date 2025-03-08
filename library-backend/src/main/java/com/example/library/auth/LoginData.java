package com.example.library.auth;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class LoginData {
    @Getter 
    @Setter
    private String email;
    @Getter 
    @Setter
    private String password;
}
