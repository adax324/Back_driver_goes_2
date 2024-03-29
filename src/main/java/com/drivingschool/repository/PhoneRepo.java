package com.drivingschool.repository;

import com.drivingschool.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PhoneRepo extends AbstractRepoInterface<PhoneNumber, Long> {
}
