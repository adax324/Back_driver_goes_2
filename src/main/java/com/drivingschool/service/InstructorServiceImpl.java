package com.drivingschool.service;

import com.drivingschool.dto.InstructorDTO;
import com.drivingschool.entity.Instructor;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceImpl extends AbstractService<Instructor, InstructorDTO, Long> implements InstructorService {

}
