package com.training.test.repository;

import com.training.test.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
    UserDetails findByUsername(String name);
    void deleteByUsername(String username);
}
