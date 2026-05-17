package com.yp.ahrenix.repository;

import com.yp.ahrenix.entities.Role;
import com.yp.ahrenix.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleType name);

}