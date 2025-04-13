package com.example.testsb.repository;

import com.example.testsb.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByEmail(String email); // Use 'email' if that is the correct field for login
    Optional<MyUser> findByUsername(String username);
}
