package com.drivingschool.repository;

import com.drivingschool.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City, Long> {
}
