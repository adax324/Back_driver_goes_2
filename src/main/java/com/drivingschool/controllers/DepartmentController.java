package com.drivingschool.controllers;

import com.drivingschool.dto.CityDTO;
import com.drivingschool.dto.DepartmentDTO;
import com.drivingschool.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @GetMapping("/findByCity")
    public List<DepartmentDTO> findByCity(@RequestParam Long cityId) {
            return departmentService.findByCity(cityId);
    }
}
