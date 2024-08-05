package com.training.test.controller;

import com.training.test.model.UserDeletionRequest;
import com.training.test.model.UserRegistrationRequest;
import com.training.test.model.UserRegistrationResponse;
import com.training.test.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Register a new user", description = "Send user details and add a new user to the database")
    @PostMapping("/registerUser")
    public ResponseEntity<String> setUser(@RequestBody UserRegistrationRequest userRegistrationRequest){
        this.userService.registerNewUser(userRegistrationRequest);
        return new ResponseEntity<>("Owner Name is "+userRegistrationRequest.getFirstName() + userRegistrationRequest.getLastName()+" username " + userRegistrationRequest.getUsername() +" email id"+ userRegistrationRequest.getEmailId() + " phone no"+ userRegistrationRequest.getContact()+ " dob" + userRegistrationRequest.getDateOfBirth(), HttpStatus.OK );
    }

    @Operation(summary = "Get all user details", description = "Retrieve user entries stored in the database")
    @GetMapping("/getUserDetails")
    public List<UserRegistrationResponse> getUser(){
        log.info("Request to display all users stored in the user table received");
        return this.userService.getUser();
    }

    @Operation(summary = "Delete user", description = "Delete the user by adding the name to the url")
    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody UserDeletionRequest userDeletionRequest){
        log.info("Request to delete user {}", userDeletionRequest.getUsername());
        this.userService.deleteUser(userDeletionRequest);
        return new ResponseEntity<>("Deleted user with username" + userDeletionRequest.getUsername(), HttpStatus.OK);
    }
}
