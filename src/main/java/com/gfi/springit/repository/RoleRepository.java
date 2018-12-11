package com.gfi.springit.repository;

import com.gfi.springit.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository <Role,Long> {
}
