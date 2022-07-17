package com.drivingschool.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = {"departments"} , allowSetters = true)
public class CityDTO {
    private Long id;
    private String name;
    List<DepartmentDTO> departments;

}
