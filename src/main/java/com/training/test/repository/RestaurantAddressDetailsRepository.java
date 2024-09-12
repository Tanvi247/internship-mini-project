package com.training.test.repository;

import com.training.test.entity.RestaurantAddressDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantAddressDetailsRepository extends JpaRepository<RestaurantAddressDetails, Integer> {
}