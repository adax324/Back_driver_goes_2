package com.drivingschool.repository;

import com.drivingschool.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepo extends JpaRepository<Student, Long> {
}
