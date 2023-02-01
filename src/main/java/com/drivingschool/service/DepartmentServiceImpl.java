package com.drivingschool.service;

import com.drivingschool.dto.DepartmentDTO;
import com.drivingschool.entity.Department;
import com.drivingschool.repository.DepartmentRepoImpl;
import com.drivingschool.utility.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl extends AbstractService<Department, DepartmentDTO, Long> implements DepartmentService {
    @Autowired
    private DepartmentRepoImpl departmentRepo;

    public List<DepartmentDTO> findByCity(Long cityId) {
        return CustomMapper.map(departmentRepo.listByCityId(cityId), DepartmentDTO.class);
    }
}
