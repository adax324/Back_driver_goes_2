package com.drivingschool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DepartmentDTO {
    private Long id;
    private String name;
    private String departmentCode;
    private CityDTO city;
}
