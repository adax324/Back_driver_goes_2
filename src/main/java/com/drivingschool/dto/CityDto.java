package com.drivingschool.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CityDto {
    private Long id;
    private String name;
    List<DepartmentDto> departments;

}
