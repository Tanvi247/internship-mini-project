package com.training.test.controller;

import com.training.test.model.RestroDetailsRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/restro")
public class RestaurantController {

    @GetMapping("/new")
    public ResponseEntity<String> getRestos(){
//        System.out.println("This is the first get request");
//        return "This is the first get request";
        return new ResponseEntity<>("This is the first get request",HttpStatus.ACCEPTED);
    }

//    @PostMapping("/post")
//    public ResponseEntity<String> setRestros(@RequestBody String name){
//        return new ResponseEntity<>("This is the first post request" +name,HttpStatus.ACCEPTED);
//    }

//    @PostMapping("/post")
//    public ResponseEntity<String> setRestros(@RequestBody Map<String, String> requestBody){
//        String name = requestBody.get("name");
//        return new ResponseEntity<>("This is the first post request " + name, HttpStatus.ACCEPTED);
//    }

    @PostMapping("/create")
    public ResponseEntity<String> setRestros(@RequestBody RestroDetailsRequest restroDetailsRequest){
        return new ResponseEntity<>("Restaurant name is "+ restroDetailsRequest.getName()+ " and username is "+ restroDetailsRequest.getUsername()+ " with contact number:"+ restroDetailsRequest.getContact(),HttpStatus.OK);
    }
}
