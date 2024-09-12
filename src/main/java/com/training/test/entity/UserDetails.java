package com.training.test.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "user_details")
public class UserDetails {

    @Id
    @SequenceGenerator(name="user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private int id;

    @Column(name = "first_name", length= 25)
    private String firstName;

    @Column(name = "last_name", length= 25)
    private String lastName;

    @Column(name = "username", length= 20)
    private String username;

    @Column(name = "contact_no")
    private Long contact;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name="password")
    private String password;

}

