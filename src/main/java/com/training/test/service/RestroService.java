package com.training.test.service;

import com.training.test.entity.RestaurantAddressDetails;
import com.training.test.entity.RestaurantDetails;
import com.training.test.model.RestroDetailsRequest;
import com.training.test.repository.RestaurantDetailsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class RestroService {

    private LoginService loginService;
    private RestaurantDetailsRepository restaurantDetailsRepository;

    public RestroService(LoginService loginService, RestaurantDetailsRepository restaurantDetailsRepository){
        this.loginService = loginService;
        this.restaurantDetailsRepository = restaurantDetailsRepository;
    }


    public void processNewRestro(RestroDetailsRequest restroDetailsRequest){

        RestaurantAddressDetails addressDetails = new RestaurantAddressDetails();
        addressDetails.setStreetName(restroDetailsRequest.getStreetName());
        addressDetails.setPinCode(Integer.parseInt(restroDetailsRequest.getZipCode()));
        addressDetails.setCity(restroDetailsRequest.getCity());

        RestaurantDetails restaurantDetails = new RestaurantDetails();
        restaurantDetails.setName(restroDetailsRequest.getName());
        restaurantDetails.setOwnerName(restroDetailsRequest.getOwner());
        restaurantDetails.setAddressDetails(addressDetails);
        restaurantDetails.setRestroType(restroDetailsRequest.getType());
        restaurantDetails.setContact(Long.parseLong(restroDetailsRequest.getContact()));

        restaurantDetailsRepository.save(restaurantDetails);


        System.out.print("New Restro name "+restroDetailsRequest.getName());
    }

    public RestaurantDetails getRestro(){
        List<RestaurantDetails> restaurantDetails = null;

        restaurantDetails = restaurantDetailsRepository.findAll();

        return restaurantDetails.getFirst();
    }

    public RestaurantDetails updateRestro(){
        Optional<RestaurantDetails> restaurantDetails = null;

        restaurantDetails = restaurantDetailsRepository.findById(3);

        restaurantDetails.ifPresent(details -> {
            details.setName("Tanvi D");
            restaurantDetailsRepository.save(details);
        });

        return restaurantDetails.get();
    }


    public void deleteRestro(String name){
        System.out.println("Restro Deleted is "+name);
    }

}