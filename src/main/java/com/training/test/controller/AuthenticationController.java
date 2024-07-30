package com.training.test.controller;

import com.training.test.entity.UserDetails;
import com.training.test.model.UserDeletionRequest;
import com.training.test.model.UserRegistrationRequest;
import com.training.test.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("/user")
public class AuthenticationController {

    UserService userService;

    AuthenticationController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/registerUser")
    public ResponseEntity<String> setUser(@RequestBody UserRegistrationRequest userRegistrationRequest){
        log.info("Request to register new user received with details Name : {}{} username:{} email id: {} contact: {} dob: {} ", userRegistrationRequest.getFirstName(), userRegistrationRequest.getLastName(),userRegistrationRequest.getUsername(), userRegistrationRequest.getEmailId(),userRegistrationRequest.getContact(), userRegistrationRequest.getDateOfBirth());
        this.userService.registerNewUser(userRegistrationRequest);
        return new ResponseEntity<>("Owner Name is "+userRegistrationRequest.getFirstName() + userRegistrationRequest.getLastName()+" username " + userRegistrationRequest.getUsername() +" email id"+ userRegistrationRequest.getEmailId() + " phone no"+ userRegistrationRequest.getContact()+ " dob" + userRegistrationRequest.getDateOfBirth(), HttpStatus.OK );
    }

    @GetMapping("/getUserDetails")
    public List<UserDetails> getUser(){
        log.info("Request to display all users stored in the user table received");
        return this.userService.getUser();
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody UserDeletionRequest userDeletionRequest){
        log.info("Request to delete user {}", userDeletionRequest.getUsername());
        this.userService.deleteUser(userDeletionRequest);
        return new ResponseEntity<>("Deleted user with username" + userDeletionRequest.getUsername(), HttpStatus.OK);
    }

}
