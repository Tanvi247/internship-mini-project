package com.training.test.service;

import com.training.test.entity.RestaurantAddressDetails;
import com.training.test.entity.RestaurantDetails;
import com.training.test.model.RestroDetailsRequest;
import com.training.test.model.RestroResponse;
import com.training.test.repository.RestaurantDetailsRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Data
@Service
public class RestroService {

    private LoginService loginService;
    private RestaurantDetailsRepository restaurantDetailsRepository;

    public RestroService(LoginService loginService, RestaurantDetailsRepository restaurantDetailsRepository){
        this.loginService = loginService;
        this.restaurantDetailsRepository = restaurantDetailsRepository;
    }

    public RestaurantDetails processNewRestro(RestroDetailsRequest restroDetailsRequest){

        log.info("Method to create new restaurant with details name: {} owner: {} type: {} street: {} city: {} zip code: {} email: {} contact: {} ", restroDetailsRequest.getName(),restroDetailsRequest.getOwner(),restroDetailsRequest.getType(),restroDetailsRequest.getStreetName(),restroDetailsRequest.getCity(),restroDetailsRequest.getZipCode(), restroDetailsRequest.getEmail(),restroDetailsRequest.getContact());

        RestaurantAddressDetails addressDetails = new RestaurantAddressDetails();
        addressDetails.setStreetName(restroDetailsRequest.getStreetName());
        addressDetails.setZipCode(Integer.parseInt(restroDetailsRequest.getZipCode()));
        addressDetails.setCity(restroDetailsRequest.getCity());

        RestaurantDetails restaurantDetails = new RestaurantDetails();
        restaurantDetails.setName(restroDetailsRequest.getName());
        restaurantDetails.setOwnerName(restroDetailsRequest.getOwner());
        restaurantDetails.setAddressDetails(addressDetails);
        restaurantDetails.setRestroType(restroDetailsRequest.getType());
        restaurantDetails.setContact(Long.parseLong(restroDetailsRequest.getContact()));
        restaurantDetails.setEmail(restroDetailsRequest.getEmail());

        restaurantDetailsRepository.save(restaurantDetails);

        log.info("Name of the new restaurant created is {}", restroDetailsRequest.getName());
        return restaurantDetails;
    }

    public List<RestroResponse> getRestro(){
        log.info("Entered method to display all restaurants");
        List<RestaurantDetails> restaurantDetails = restaurantDetailsRepository.findAll();
        log.info("Restaurant data retrieved {}", restaurantDetails);

        return restaurantDetails.stream()
                .map(this::convertToRestroResponse)
                .toList();
    }

    private RestroResponse convertToRestroResponse(RestaurantDetails restaurantDetails) {
        RestaurantAddressDetails address = restaurantDetails.getAddressDetails();
        return RestroResponse.builder()
                .name(restaurantDetails.getName())
                .owner(restaurantDetails.getOwnerName())
                .city(address.getCity())
                .type(restaurantDetails.getRestroType())
                .contact(restaurantDetails.getContact().toString())
                .zipCode(String.valueOf(address.getZipCode()))
                .streetName(address.getStreetName())
                .email(restaurantDetails.getEmail())
                .build();
    }

    public RestroResponse updateRestro(){
        log.info("Entered method to update owner name by id received");

        RestaurantDetails restaurantDetails = restaurantDetailsRepository.findById(3)
                .orElseThrow(() -> new RuntimeException("Restaurant with id 3 not found"));

        restaurantDetails.setName("Tanvi D");
        restaurantDetailsRepository.save(restaurantDetails);
        log.info("Updated details saved");

        return convertToRestroResponse(restaurantDetails);
    }

    @Transactional
    public void deleteRestro(String name){
        log.info("Entered method to delete restaurant named{}received", name);
        restaurantDetailsRepository.deleteByName(name);
        log.info("{} record deleted successfully", name);
    }

    public List<RestroResponse> getByOwner(String ownerName){
        log.info("Entered method to display restaurants owned by {}", ownerName);
        List<RestaurantDetails> restaurantDetails = restaurantDetailsRepository.getByOwnerName(ownerName);
        return restaurantDetails.stream()
                .map(this::convertToRestroResponse)
                .toList() ;
    }

}