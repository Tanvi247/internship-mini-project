package com.training.test.repository;

import com.training.test.entity.RestaurantDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantDetailsRepository extends JpaRepository<RestaurantDetails, Integer> {
}