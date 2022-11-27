package com.drivingschool.controller;

import com.drivingschool.dto.CityDTO;
import com.drivingschool.service.CityService;
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

    private final CityService cityService;
    @Autowired
    public CityContoller(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CityDTO>> list(HttpServletRequest request, @RequestHeader HttpHeaders headers) {
        return new ResponseEntity<>(cityService.list(), HttpStatus.OK);
    }
}
