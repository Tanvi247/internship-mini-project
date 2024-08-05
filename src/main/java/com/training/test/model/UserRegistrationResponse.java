package com.training.test.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class UserRegistrationResponse {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String contact;
    private String emailId;
    private Date dateOfBirth;
}
