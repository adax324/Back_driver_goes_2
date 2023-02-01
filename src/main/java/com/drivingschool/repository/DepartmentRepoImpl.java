package com.drivingschool.repository;

import com.drivingschool.entity.City;
import com.drivingschool.entity.Department;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class DepartmentRepoImpl extends AbstractRepo<Department, Long> implements DepartmentRepo {


    public List<Department> listByCityId(Long cityId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Department> cq = cb.createQuery(Department.class);
        Root<Department> root = cq.from(Department.class);

        Join<Department, City> departmentCityJoin = root.join("city");

        cq.where(cb.equal(departmentCityJoin.get("id"), cityId));
        TypedQuery<Department> query = getEntityManager().createQuery(cq);
        return getEntityManager().createQuery(cq).getResultList();
    }
}
