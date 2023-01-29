package com.drivingschool.controller;

import com.drivingschool.dto.InstructorDTO;
import com.drivingschool.dto.StudentDTO;
import com.drivingschool.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorControler {
    private InstructorService instructorService;
    @Autowired

    public InstructorControler(InstructorService instructorService) {
        this.instructorService = instructorService;
    }
    @GetMapping("/{uuid}")
    public InstructorDTO getInstructor(@PathVariable String uuid){ return instructorService.get(uuid);}
    @GetMapping("/all")
    private List<InstructorDTO> getAllInstructors(Principal principal, HttpServletRequest request, @RequestHeader HttpHeaders headers){
        return instructorService.list();
    }
    @PostMapping("/save")
    public ResponseEntity<InstructorDTO> save(@RequestBody InstructorDTO instructorDTO) {
        return new ResponseEntity<>(instructorService.save(instructorDTO), HttpStatus.CREATED);
    }
    @CrossOrigin
    @DeleteMapping("/delete")
    public void delete (@RequestParam String uuid) {instructorService.delete(uuid);}
}
