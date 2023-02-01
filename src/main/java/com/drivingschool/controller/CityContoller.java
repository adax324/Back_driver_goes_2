package com.drivingschool.controller;

import com.drivingschool.dto.CityDTO;
import com.drivingschool.service.CityService;
import com.drivingschool.service.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController()
@RequestMapping("/city")
public class CityContoller {
    @Autowired
    private CityService cityService;


    @GetMapping("/list")
    public ResponseEntity<List<CityDTO>> list() {
        return new ResponseEntity<>(cityService.list(), HttpStatus.OK);
    }
}
