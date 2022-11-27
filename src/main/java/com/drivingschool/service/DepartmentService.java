package com.drivingschool.service;

import com.drivingschool.dto.DepartmentDTO;
import com.drivingschool.entity.Department;
import com.drivingschool.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService extends AbstractService<Department, DepartmentDTO, Long>{
    @Autowired
    private DepartmentRepo departmentRepo;

    public List<DepartmentDTO> findByCity(Long cityId) {
        return this.map(departmentRepo.findAllByCity_Id(cityId));
    }
}
