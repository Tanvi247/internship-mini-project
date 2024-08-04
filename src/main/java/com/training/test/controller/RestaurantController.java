package com.training.test.controller;

import com.training.test.entity.RestaurantDetails;
import com.training.test.model.RestroDetailsRequest;
import com.training.test.service.LoginService;
import com.training.test.service.RestroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Testing connection", description = "Check whether the server is responding")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Connection successful"),
            @ApiResponse(responseCode = "400", description = "Connection unsuccessful"),
            @ApiResponse(responseCode = "500", description = "Error with the server. Contact backend team")
    })
    @GetMapping("/new")
    public ResponseEntity<String> getRestros(){
        return new ResponseEntity<>("This is a first get request", HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Creating a new restaurant", description = "Send restaurant details and add a new restaurant to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant is created successfully"),
            @ApiResponse(responseCode = "400", description = "Error while creating the restaurant."),
            @ApiResponse(responseCode = "500", description = "Error with the server. Contact backend team")
    })
    @PostMapping("/createRestro")
    public ResponseEntity<Map<String, String>> setRestro(@RequestBody RestroDetailsRequest restroDetailsRequest){
        log.info("Request to create new restaurant with details name: {} owner: {} type: {} street: {} city: {} zip code: {} email: {} contact: {}", restroDetailsRequest.getName(),restroDetailsRequest.getOwner(),restroDetailsRequest.getType(),restroDetailsRequest.getStreetName(),restroDetailsRequest.getCity(),restroDetailsRequest.getZipCode(), restroDetailsRequest.getEmail(),restroDetailsRequest.getContact());
        this.restroService.processNewRestro(restroDetailsRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "restaurant added");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get all restaurant details", description = "Retrieve entries stored in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant details returned"),
            @ApiResponse(responseCode = "400", description = "Error while retrieving the restaurant."),
            @ApiResponse(responseCode = "500", description = "Error with the server. Contact backend team")
    })
    @GetMapping(value= "/getRestroDetails", produces = "application/json")
    public List<RestaurantDetails> getRestro(){
        log.info("Request to display all restaurants stored in the restaurant details table received");
        return this.restroService.getRestro();
    }

    @Operation(summary = "Update restaurant details", description = "Update owner name with id 3")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant details updated"),
            @ApiResponse(responseCode = "400", description = "Error while updating the restaurant."),
            @ApiResponse(responseCode = "500", description = "Error with the server. Contact backend team")
    })
    @PostMapping("/updateRestroDetails")
    public RestaurantDetails updateRestro(){
        log.info("Request to update owner name by id received");
        return this.restroService.updateRestro();
    }

    @Operation(summary = "Delete restaurant", description = "Delete the restaurant by adding the name to the url")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant deleted"),
            @ApiResponse(responseCode = "400", description = "Error while deleting the restaurant."),
            @ApiResponse(responseCode = "500", description = "Error with the server. Contact backend team")
    })
    @DeleteMapping("/deleteRestro/{name}")
    public ResponseEntity<String> deleteRestro(@PathVariable String name){
        log.info("Request to delete restaurant with name{}", name);
        this.restroService.deleteRestro(name);
        log.info("Restaurant {} deleted", name);
        return new ResponseEntity<>("Deleted restro with name"+ name, HttpStatus.OK);
    }

    @Operation(summary = "Get by owner", description = "Retrieve restaurant info by owner name")
    @GetMapping("/getByOwner/{owner}")
    public ResponseEntity<List<RestaurantDetails>> getByOwner(@PathVariable String owner){
        log.info("Request to display restaurant by owner{}received", owner);
        return new ResponseEntity<>(this.restroService.getByOwner(owner), HttpStatus.OK);
    }
}