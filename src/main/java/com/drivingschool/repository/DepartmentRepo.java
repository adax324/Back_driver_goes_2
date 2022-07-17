package com.drivingschool.repository;

import com.drivingschool.entities.City;
import com.drivingschool.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
    List<Department> findAllByCity_Id(Long cityId);
}
