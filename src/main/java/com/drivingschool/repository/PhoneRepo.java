package com.drivingschool.repository;

import com.drivingschool.entities.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepo extends JpaRepository<PhoneNumber, Long> {
}
