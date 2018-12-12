package com.gfi.springit.repository;

import com.gfi.springit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Long> {
    Optional<User> findByEmail(String Email);

    Optional<User> findByEmailAndActivationCode(String email,String activationCode);
}