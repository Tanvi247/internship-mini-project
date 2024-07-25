package com.training.test.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public void authenticate(){
        System.out.println("User Authenticated");
    }
}