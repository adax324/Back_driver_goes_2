package com.drivingschool.repository;

import com.drivingschool.entity.ERole;
import com.drivingschool.entity.Role;
import com.drivingschool.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepoImpl extends AbstractRepo<Role, Long> implements RoleRepo {
    @Override
    public Role findByName(ERole name) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Role> cq = cb.createQuery(Role.class);
        Root<Role> root = cq.from(Role.class);

        cq.where(cb.equal(root.get("name"), name));
        List<Role> roles = em.createQuery(cq).getResultList();
        return switch (roles.size()) {
            case 0 -> null;
            default -> roles.get(0);
        };
    }
}
