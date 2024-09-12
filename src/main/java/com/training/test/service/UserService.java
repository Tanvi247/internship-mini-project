package com.training.test.service;

import com.training.test.entity.RestaurantAddressDetails;
import com.training.test.entity.RestaurantDetails;
import com.training.test.entity.UserDetails;
import com.training.test.model.RestroResponse;
import com.training.test.model.UserDeletionRequest;
import com.training.test.model.UserRegistrationRequest;
import com.training.test.model.UserRegistrationResponse;
import com.training.test.repository.UserDetailsRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Slf4j
@Data
@Service
public class UserService {

    private UserDetailsRepository userDetailsRepository;

    public UserService(UserDetailsRepository userDetailsRepository){
        this.userDetailsRepository = userDetailsRepository;
    }

    public void registerNewUser(UserRegistrationRequest userRegistrationRequest) {

        log.info("Entered service method to register new user received with details Name : {}{} username:{} email id: {} contact: {} dob: {} ", userRegistrationRequest.getFirstName(), userRegistrationRequest.getLastName(),userRegistrationRequest.getUsername(), userRegistrationRequest.getEmailId(),userRegistrationRequest.getContact(), userRegistrationRequest.getDateOfBirth());

        UserDetails userDetails = new UserDetails();

        userDetails.setFirstName(userRegistrationRequest.getFirstName());
        userDetails.setLastName(userRegistrationRequest.getLastName()) ;
        userDetails.setContact(Long.parseLong(userRegistrationRequest.getContact()));
        userDetails.setUsername(userRegistrationRequest.getUsername());
        userDetails.setEmailId(userRegistrationRequest.getEmailId());
        userDetails.setDateOfBirth(String.valueOf(userRegistrationRequest.getDateOfBirth()));
        userDetails.setPassword(userRegistrationRequest.getPassword());

        userDetailsRepository.save(userDetails);

        log.info("Name of the new restaurant created is {}", userRegistrationRequest.getUsername());
    }

    public List<UserRegistrationResponse> getUser() {
        log.info("Entered method to display all restaurants");
        List<UserDetails> userDetailsList = null;
        userDetailsList = userDetailsRepository.findAll();
        log.info("User data retrieved {}", userDetailsList);

        return userDetailsList.stream()
                .map(this::convertToUserResponse)
                .toList();
    }

    private UserRegistrationResponse convertToUserResponse(UserDetails userDetails) {
        return UserRegistrationResponse.builder()
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .username(userDetails.getUsername())
                .contact(userDetails.getContact().toString())
                .emailId(userDetails.getEmailId())
                .dateOfBirth(Date.valueOf((userDetails.getDateOfBirth())))
                .build();
    }

    @Transactional
    public void deleteUser(UserDeletionRequest userDeletionRequest) {
        log.info("Entered method to delete user named{}", userDeletionRequest.getUsername());
        userDetailsRepository.deleteByUsername(userDeletionRequest.getUsername());
    }
}
