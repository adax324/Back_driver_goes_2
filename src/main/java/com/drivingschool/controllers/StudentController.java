package com.drivingschool.controllers;

import com.drivingschool.dto.StudentDTO;
import com.drivingschool.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/{uuid}")
    public StudentDTO getStudent(@PathVariable String uuid){
     return studentService.get(uuid);
    }
    @GetMapping("/all")
    private List<StudentDTO> getAllStudents(){
        return studentService.list();
    }
    @PostMapping("/save")
    public ResponseEntity<StudentDTO> save(@RequestBody StudentDTO student) {
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }
}
