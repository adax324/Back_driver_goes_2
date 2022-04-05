package com.drivingschool.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = {"city"} ,allowSetters = true)
public class DepartmentDto {
    private Long id;
    private String name;
    private String departmentCode;
    private CityDto city;
}
