package com.training.test.service;

import com.training.test.entity.UserDetails;
import com.training.test.model.UserDeletionRequest;
import com.training.test.model.UserRegistrationRequest;
import com.training.test.repository.UserDetailsRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class UserService {

    private UserDetailsRepository userDetailsRepository;

    public UserService(UserDetailsRepository userDetailsRepository){
        this.userDetailsRepository = userDetailsRepository;
    }


    public void registerNewUser(UserRegistrationRequest userRegistrationRequest) {

        UserDetails userDetails = new UserDetails();

        userDetails.setFirstName(userRegistrationRequest.getFirstName());
        userDetails.setLastName(userRegistrationRequest.getLastName()) ;
        userDetails.setContact(Long.parseLong(userRegistrationRequest.getContact()));
        userDetails.setUsername(userRegistrationRequest.getUsername());
        userDetails.setEmailId(userRegistrationRequest.getEmailId());
        userDetails.setDateOfBirth(String.valueOf(userRegistrationRequest.getDateOfBirth()));
        userDetails.setPassword(userRegistrationRequest.getPassword());

        userDetailsRepository.save(userDetails);

        System.out.println("New user added successfully with username" + userDetails.getUsername());

    }

    public UserDetails getUser() {
        List<UserDetails> userDetailsList = null;

        userDetailsList = userDetailsRepository.findAll();
        return userDetailsList.getFirst();

    }

    @Transactional
    public void deleteUser(UserDeletionRequest userDeletionRequest) {
        userDetailsRepository.deleteByUsername(userDeletionRequest.getUsername());
    }



}
