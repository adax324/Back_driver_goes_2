package com.drivingschool.service;

import com.drivingschool.dto.DepartmentDTO;
import com.drivingschool.entity.Department;

import java.util.List;

public interface DepartmentService extends AbstractServiceInterface<Department, DepartmentDTO, Long> {
    List<DepartmentDTO> findByCity(Long cityId);
}
