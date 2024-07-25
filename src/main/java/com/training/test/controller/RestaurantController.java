package com.training.test.controller;

import com.training.test.entity.RestaurantDetails;
import com.training.test.model.RestroDetailsRequest;
import com.training.test.service.LoginService;
import com.training.test.service.RestroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restro")
public class RestaurantController {

    //    @Autowired
    RestroService restroService;

    //    @Autowired
    LoginService loginService;

    RestaurantController(RestroService restroService, LoginService loginService){
        this.restroService = restroService;
        this.loginService = loginService;
    }

    @GetMapping("/new")
    public ResponseEntity<String> getRestros(){
//        System.out.println("This is a first get request");
//        return "This is a first get request";
        return new ResponseEntity<>("This is a first get request", HttpStatus.ACCEPTED);
    }

    @PostMapping("/create")
    public ResponseEntity<String> setRestro(@RequestBody RestroDetailsRequest restroDetailsRequest){
        this.restroService.processNewRestro(restroDetailsRequest);
        return new ResponseEntity<>("Restro name is "+restroDetailsRequest.getName()+" and owner name is "+restroDetailsRequest.getOwner() +" with contact "+restroDetailsRequest.getContact(), HttpStatus.OK);
    }

    @GetMapping("/getDetails")
    public RestaurantDetails getRestro(){
        return this.restroService.getRestro();
//        return new ResponseEntity<>("Restro name is "+restroDetailsRequest.getName()+" and username is "+restroDetailsRequest.getUsername() +"with contact "+restroDetailsRequest.getContact(), HttpStatus.OK);
    }

    @PostMapping("/updateDetails")
    public RestaurantDetails updateRestro(){
        return this.restroService.updateRestro();
//        return new ResponseEntity<>("Restro name is "+restroDetailsRequest.getName()+" and username is "+restroDetailsRequest.getUsername() +"with contact "+restroDetailsRequest.getContact(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public void deleteRestro(@RequestBody String name){
        this.restroService.deleteRestro(name);
    }
}