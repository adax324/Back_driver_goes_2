package com.drivingschool.controller;

import com.drivingschool.dto.StudentDTO;
import com.drivingschool.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
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
    private List<StudentDTO> getAllStudents(Principal principal, HttpServletRequest request, @RequestHeader HttpHeaders headers) {
        return studentService.list();
    }
    @PostMapping("/save")
    public ResponseEntity<StudentDTO> save(@RequestBody StudentDTO student) {
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }
    @CrossOrigin
    @DeleteMapping("/delete")
    public void delete(@RequestParam String uuid) {
        studentService.delete(uuid);
    }
}
