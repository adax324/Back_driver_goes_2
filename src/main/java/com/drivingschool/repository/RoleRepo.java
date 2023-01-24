package com.drivingschool.repository;

import com.drivingschool.entity.ERole;
import com.drivingschool.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(ERole name);
}
