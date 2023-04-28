package com.drivingschool.service;

import com.drivingschool.dto.InstructorDTO;
import com.drivingschool.entity.Instructor;
import com.drivingschool.repository.InstructorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorService extends AbstractService<Instructor, InstructorDTO, Long> {
    private InstructorRepo instructorRepo;

    @Autowired
    public InstructorService(InstructorRepo instructorRepo){this.instructorRepo = instructorRepo;}

    public InstructorDTO save(InstructorDTO instructorDTO) {
//        if (instructorDTO.getPhoneNumber() != null) instructorDTO.getPhoneNumber().set
    return super.save(instructorDTO);
    }
}
