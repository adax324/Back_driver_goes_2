package com.drivingschool.repository;

import com.drivingschool.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepoImpl extends AbstractRepo<User, Long> implements UserRepo {
    @Override
    public User findByUsername(String username) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);

        cq.where(cb.equal(root.get("username"), username));
        List<User> user = em.createQuery(cq).getResultList();
        return switch (user.size()) {
            case 0 -> null;
            default -> user.get(0);
        };
    }

    @Override
    public boolean existsByUsername(String username) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);

        cq.where(cb.equal(root.get("username"), username));
        return em.createQuery(cq).getResultList().size() > 0;
    }

    @Override
    public boolean existsByEmail(String email) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);

        cq.where(cb.equal(root.get("email"), email));
        return em.createQuery(cq).getResultList().size() > 0;
    }


}
