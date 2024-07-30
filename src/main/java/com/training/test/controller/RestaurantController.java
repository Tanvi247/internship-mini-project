package com.training.test.controller;

import com.training.test.entity.RestaurantDetails;
import com.training.test.model.RestroDetailsRequest;
import com.training.test.service.LoginService;
import com.training.test.service.RestroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/restro")

public class RestaurantController {

    RestroService restroService;

    LoginService loginService;

    RestaurantController(RestroService restroService, LoginService loginService){
        this.restroService = restroService;
        this.loginService = loginService;
    }

    @GetMapping("/new")
    public ResponseEntity<String> getRestros(){
        return new ResponseEntity<>("This is a first get request", HttpStatus.ACCEPTED);
    }

    @PostMapping("/createRestro")
    public ResponseEntity<Map<String, String>> setRestro(@RequestBody RestroDetailsRequest restroDetailsRequest){
        log.info("Request to create new restaurant with details name: {} owner: {} type: {} street: {} city: {} zip code: {} email: {} contact: {}", restroDetailsRequest.getName(),restroDetailsRequest.getOwner(),restroDetailsRequest.getType(),restroDetailsRequest.getStreetName(),restroDetailsRequest.getCity(),restroDetailsRequest.getZipCode(), restroDetailsRequest.getEmailId(),restroDetailsRequest.getContact());
        this.restroService.processNewRestro(restroDetailsRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "restaurant added");
        return new ResponseEntity<>(response, HttpStatus.OK);
//        return new ResponseEntity<>("Restro name is "+restroDetailsRequest.getName()+" and owner name is "+restroDetailsRequest.getOwner() +" with contact "+restroDetailsRequest.getContact(), HttpStatus.OK);
    }

    @GetMapping("/getRestroDetails")
//    public List<RestaurantDetails> getRestro(){
    public RestaurantDetails getRestro(){
        log.info("Request to display all restaurants stored in the restaurant details table received");
        return this.restroService.getRestro();
    }

    @PostMapping("/updateRestroDetails")
    public RestaurantDetails updateRestro(){
        log.info("Request to update owner name by id received");
        return this.restroService.updateRestro();
    }

    @DeleteMapping("/deleteRestro/{name}")
    public ResponseEntity<String> deleteRestro(@PathVariable String name){
        log.info("Request to delete restaurant with name{}", name);
        this.restroService.deleteRestro(name);
        log.info("Restaurant{} deleted", name);
        return new ResponseEntity<>("Deleted restro with name"+ name, HttpStatus.OK);
    }

    @GetMapping("/getByOwner/{owner}")
    public ResponseEntity<List<RestaurantDetails>> getByOwner(@PathVariable String owner){
        log.info("Request to display restaurant by owner{}received", owner);
        return new ResponseEntity<>(this.restroService.getByOwner(owner), HttpStatus.OK);
    }
}