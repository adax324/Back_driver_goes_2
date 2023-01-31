package com.drivingschool.repository;

import com.drivingschool.entity.Department;

import java.util.List;

public interface DepartmentRepo extends AbstractRepoInterface<Department, Long> {
    public List<Department> listByCityId(Long cityId);
}
