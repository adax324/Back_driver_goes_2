package com.drivingschool.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.Getter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
@Getter
@Transactional
public abstract class AbstractRepo<T, K extends Serializable> implements AbstractRepoInterface<T, K> {
    private final Class<T> entityClass;
    @PersistenceContext
    private EntityManager entityManager;

    public AbstractRepo() {
        entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T save(T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    public T update(T entity) {
        entityManager.merge(entity);
        entityManager.flush();
        return entity;
    }

    public List<T> list() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
    }

    public T get(K id) {
        return entityManager.find(entityClass, id);
    }

    public T get(String uuid) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.where(cb.equal(root.get("uuid"), uuid));
        return entityManager.createQuery(cq).getSingleResult();
    }

    public void delete(K id) {
        entityManager.remove(id);
    }
    public void delete(String uuid) {
        entityManager.createQuery("FROM " + entityClass.getSimpleName() + " WHERE uuid = :uuid")
                .setParameter("uuid", uuid)
                .getResultStream()
                .findFirst()
                .ifPresent(entityManager::remove);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
