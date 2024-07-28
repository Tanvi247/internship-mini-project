package com.training.test.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "restaurant_details")
public class RestaurantDetails {

    @Id
    @SequenceGenerator(name="restro_sequence", sequenceName = "restro_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restro_sequence")
    private int id;

    @Column(name = "restro_name",length = 50)
    private String name;

    @Column(name = "owner_name",length = 50)
    private String ownerName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address", referencedColumnName = "address_id")
    private RestaurantAddressDetails addressDetails;

    @Column(name = "restro_type")
    private String restroType;

    @Column(name = "contact")
    private Long contact;

    @Column(name = "email_id")
    private String emailId;
}