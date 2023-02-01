package com.drivingschool.controller;

import com.drivingschool.dto.StudentDTO;
import com.drivingschool.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/get/{uuid}")
    public StudentDTO get(@PathVariable String uuid) {
        return studentService.get(uuid);
    }

    @GetMapping("/list")
    private List<StudentDTO> list() {
        return studentService.list();
    }

    @PostMapping("/save")
    public ResponseEntity<StudentDTO> save(@RequestBody StudentDTO student) {
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam String uuid) {
        studentService.delete(uuid);
    }
}
