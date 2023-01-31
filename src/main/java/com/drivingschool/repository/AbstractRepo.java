package com.drivingschool.repository;

import com.drivingschool.entity.AbstractEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
@Getter
public abstract class AbstractRepo<T extends AbstractEntity<K>, K extends Serializable> {
    private final Class<T> entityClass;
    @PersistenceContext
    private EntityManager entityManager;

    protected CriteriaBuilder cb;
    protected CriteriaQuery<T> cq;
    protected Root<T> root;


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
        refreshCr();
        cq.select(root);

        return entityManager.createQuery(cq).getResultList();
    }

    public T get(K id) {
        return entityManager.find(entityClass, id);
    }

    public T get(String uuid) {
        refreshCr();
        cq.where(cb.equal(root.get("uuid"), uuid));
        return entityManager.createQuery(cq).getSingleResult();
    }

    public List<T> listByCriteria() {
        refreshCr();
        return entityManager.createQuery(cq).getResultList();
    }

    public void delete(K id) {
        entityManager.remove(id);
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }

    private void refreshCr() {
        cb = entityManager.getCriteriaBuilder();
        cq = cb.createQuery(entityClass);
        root = cq.from(entityClass);
    }


}
