package com.gfi.springit.repository;

import com.gfi.springit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User,Long> {
}
