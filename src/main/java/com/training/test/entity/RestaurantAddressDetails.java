package com.training.test.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "restaurant_address")
public class RestaurantAddressDetails {

    @Id
    @SequenceGenerator(name="restro__address_sequence", sequenceName = "restro__address_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restro__address_sequence")
    @Column(name = "address_id")
    private int id;

    @Column(name = "street")
    private String streetName;

    @Column(name = "city")
    private String city;

    @Column(name = "pin_code")
    private int pinCode;
}