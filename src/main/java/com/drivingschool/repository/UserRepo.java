package com.drivingschool.repository;

import com.drivingschool.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends AbstractRepoInterface<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
