package com.drivingschool.repository;

import com.drivingschool.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepo extends JpaRepository<Student, Long> {
}
