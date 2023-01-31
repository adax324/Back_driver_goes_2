package com.drivingschool.repository;

import com.drivingschool.entity.ERole;
import com.drivingschool.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepoImpl extends AbstractRepo<Role, Long> implements RoleRepo {
    @Override
    public Role findByName(ERole name) {
        return null;
    }
}
