package com.drivingschool.repository;

import com.drivingschool.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepoImpl extends AbstractRepo<User, Long> implements UserRepo {
    @Override
    public User findByUsername(String username) {
        getCq().where(getCb().equal(getRoot().get("username"), username));
        return null;
    }

    @Override
    public boolean existsByUsername(String username) {
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }


}
