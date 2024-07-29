package com.training.test.controller;

import com.training.test.entity.RestaurantDetails;
import com.training.test.model.RestroDetailsRequest;
import com.training.test.service.LoginService;
import com.training.test.service.RestroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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
    public ResponseEntity<String> setRestro(@RequestBody RestroDetailsRequest restroDetailsRequest){
        this.restroService.processNewRestro(restroDetailsRequest);
        return new ResponseEntity<>("Restro name is "+restroDetailsRequest.getName()+" and owner name is "+restroDetailsRequest.getOwner() +" with contact "+restroDetailsRequest.getContact(), HttpStatus.OK);
    }

    @GetMapping("/getRestroDetails")
    public RestaurantDetails getRestro(){
        return this.restroService.getRestro();
    }

    @PostMapping("/updateRestroDetails")
    public RestaurantDetails updateRestro(){
        return this.restroService.updateRestro();
    }

    @DeleteMapping("/deleteRestro/{name}")
    public ResponseEntity<String> deleteRestro(@PathVariable String name){
        this.restroService.deleteRestro(name);
        return new ResponseEntity<>("Deleted restro with name"+ name, HttpStatus.OK);
    }
}