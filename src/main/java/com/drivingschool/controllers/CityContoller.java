package com.drivingschool.controllers;

import com.drivingschool.configuration.SpringConfiguration;
import com.drivingschool.dto.CityDto;
import com.drivingschool.entities.City;
import com.drivingschool.repository.student.CityRepository;
import com.drivingschool.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/cities")
public class CityContoller {
    private CityService cityService;
    private CityRepository cityRepository;
    @Autowired
    SpringConfiguration springConfiguration;
    @Autowired
    public CityContoller(CityService cityService, CityRepository cityRepository) {
        this.cityService = cityService;
        this.cityRepository = cityRepository;
    }

    @GetMapping("/all")
    public String getAll(){
      return springConfiguration.getAppUrl();
    }
}
