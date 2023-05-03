package com.drivingschool.repository;

import com.drivingschool.entity.Instructor;
import org.springframework.stereotype.Service;

@Service
public class InstructorRepoImpl extends AbstractRepo<Instructor, Long> implements InstructorRepo {
}
