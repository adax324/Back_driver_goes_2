package com.drivingschool.controllers;

import com.drivingschool.dto.CityDTO;
import com.drivingschool.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/city")
public class CityContoller {

    private final CityService cityService;
    @Autowired
    public CityContoller(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CityDTO>> list() {
        return new ResponseEntity<>(cityService.list(), HttpStatus.OK);
    }
}
