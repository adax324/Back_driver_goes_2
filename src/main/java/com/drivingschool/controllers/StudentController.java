package com.drivingschool.controllers;

import com.drivingschool.dto.StudentDto;
import com.drivingschool.entities.Student;
import com.drivingschool.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
//    @GetMapping("/get/{id}")
//    public List<StudentDto> getStudent(@PathVariable Long id){
//     return studentService.readDto(id);
//    }
    @GetMapping("/all")
    private List<StudentDto> getAllStudents(){
        List<StudentDto> a = studentService.readAllDto();
        return a;
    }
    @PostMapping("/save")
    public ResponseEntity<StudentDto> save(@RequestBody StudentDto student){
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }
}
