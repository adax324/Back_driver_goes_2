package com.drivingschool.services;

import com.drivingschool.dto.DepartmentDTO;
import com.drivingschool.entities.Department;
import com.drivingschool.repository.DepartmentRepo;
import org.hibernate.criterion.Restrictions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService extends AbstractService<Department, DepartmentDTO, Long>{
    @Autowired
    private DepartmentRepo departmentRepo;

    public List<DepartmentDTO> findByCity(Long cityId) {
        return listByCriteria(Restrictions.eq("city.id", cityId));
    }
}
