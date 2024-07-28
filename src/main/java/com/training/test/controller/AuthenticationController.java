package com.training.test.controller;

import com.training.test.entity.UserDetails;
import com.training.test.model.UserDeletionRequest;
import com.training.test.model.UserRegistrationRequest;
import com.training.test.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class AuthenticationController {

    UserService userService;

    AuthenticationController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> setUser(@RequestBody UserRegistrationRequest userRegistrationRequest){
        this.userService.registerNewUser(userRegistrationRequest);
        return new ResponseEntity<>("Owner Name is "+userRegistrationRequest.getFirstName() + userRegistrationRequest.getLastName()+" username " + userRegistrationRequest.getUsername() +" email id"+ userRegistrationRequest.getEmailId() + " phone no"+ userRegistrationRequest.getContact()+ " dob" + userRegistrationRequest.getDateOfBirth(), HttpStatus.OK );
    }

    @GetMapping("/getDetails")
    public UserDetails getUser(){
        return this.userService.getUser();
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody UserDeletionRequest userDeletionRequest){
        this.userService.deleteUser(userDeletionRequest);
        return new ResponseEntity<>("Deleted user with username" + userDeletionRequest.getUsername(), HttpStatus.OK);
    }

//    @PostMapping("/updateUser")
//    public ResponseEntity<String> updateUser(@RequestBody UserRegistrationRequest userRegistrationRequest){
//        this.userService.updateUser(userRegistrationRequest.getUsername());
//        return new ResponseEntity<>("Owner Name is "+userRegistrationRequest.getFirstName() + userRegistrationRequest.getLastName()+" username " + userRegistrationRequest.getUsername() +" email id"+ userRegistrationRequest.getEmailId() + " phone no"+ userRegistrationRequest.getContact()+ " dob" + userRegistrationRequest.getDateOfBirth(), HttpStatus.OK );
//    }
}
