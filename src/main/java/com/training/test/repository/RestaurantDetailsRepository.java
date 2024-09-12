package com.training.test.repository;

import com.training.test.entity.RestaurantDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantDetailsRepository extends JpaRepository<RestaurantDetails, Integer> {
    void deleteByName(String name);

    @Query (value = "select * from restaurant_details where owner_name = ?", nativeQuery = true)
    public List<RestaurantDetails> getByOwnerName(String ownerName);
}