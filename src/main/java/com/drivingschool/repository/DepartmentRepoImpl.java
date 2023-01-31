package com.drivingschool.repository;

import com.drivingschool.entity.City;
import com.drivingschool.entity.Department;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class DepartmentRepoImpl extends AbstractRepo<Department, Long> implements DepartmentRepo {
    public List<Department> listByCityId(Long cityId) {
        Join<Department, City> departmentCityJoin = root.join("city");
        ParameterExpression<Long> cityId1 = cb.parameter(Long.class);
        cq.where(cb.equal(departmentCityJoin.get("id"), cityId1));
        TypedQuery<Department> query = getEntityManager().createQuery(cq);
        query.setParameter(cityId1, cityId);
        return listByCriteria();
    }
}
