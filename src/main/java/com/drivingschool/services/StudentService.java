package com.drivingschool.services;

import com.drivingschool.dto.StudentDto;
import com.drivingschool.entities.Student;
import com.drivingschool.repository.student.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }


    public List<StudentDto> readAllDto() {
        return studentRepository.findAll().stream().map(student -> modelMapper.map(student, StudentDto.class)).collect(Collectors.toList());
    }

    public StudentDto save(StudentDto student) {
        student.setUuid(UUID.randomUUID().toString());
        return modelMapper.map(studentRepository.save(modelMapper.map(student, Student.class)), StudentDto.class);

    }
}
