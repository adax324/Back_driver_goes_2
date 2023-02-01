package com.drivingschool.service;

import com.drivingschool.dto.StudentDTO;
import com.drivingschool.entity.Student;
import com.drivingschool.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends AbstractService<Student, StudentDTO, Long> implements StudentService {
    private StudentRepo studentRepo;

    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public StudentDTO save(StudentDTO o) {
        if (o.getPhoneNumber() != null)
            o.getPhoneNumber().setStudent(o);
        return super.save(o);
    }
}
