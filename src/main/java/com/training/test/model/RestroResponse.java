package com.training.test.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestroResponse {
    private String name;
    private String owner;
    private String city;
    private String type;
    private String contact;
    private String zipCode;
    private String streetName;
    private String email;
}