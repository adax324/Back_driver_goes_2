package com.drivingschool.services;

import com.drivingschool.dto.PhoneNumberDTO;
import com.drivingschool.dto.StudentDTO;
import com.drivingschool.entities.Student;
import com.drivingschool.repository.StudentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService extends AbstractService<Student, StudentDTO, Long> {
    private StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public StudentDTO save(StudentDTO o) {
        if (o.getPhoneNumber() != null)
            o.getPhoneNumber().setStudent(o);
        return super.save(o);
    }
}
