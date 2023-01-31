package com.drivingschool.repository;

import com.drivingschool.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepoImpl extends AbstractRepo<Student, Long> implements StudentRepo {
}
