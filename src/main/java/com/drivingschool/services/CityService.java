package com.drivingschool.services;

import com.drivingschool.dto.CityDto;
import com.drivingschool.entities.City;
import com.drivingschool.repository.student.CityRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private CityRepository cityRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CityService(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }




    public List<CityDto> readAllDto(){
        return modelMapper.map(cityRepository.findAll(), new TypeToken<List<CityDto>>(){}.getType());
    }
}
