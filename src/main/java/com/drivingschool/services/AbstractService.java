package com.drivingschool.services;


import com.drivingschool.configuration.ApplicationContextProvider;
import jakarta.persistence.EntityManager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public abstract class AbstractService<T, TDTO, ID> {
    private EntityManager entityManager;
    private Session session;
    private ModelMapper modelMapper;
    private Class<T> entityClass;
    private Class<TDTO> dtoClass;
    private Class<ID> idClass;
    private ApplicationContext applicationContext;
    private JpaRepository<T, ID> abstractRepo;

    public AbstractService() {
        entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        dtoClass = (Class<TDTO>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        idClass = (Class<ID>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[2];
        applicationContext = ApplicationContextProvider.getApplicationContext();
        this.modelMapper = applicationContext.getBean(ModelMapper.class);
        this.entityManager = applicationContext.getBean(EntityManager.class);
        this.session = entityManager.unwrap(Session.class);
        ResolvableType type = ResolvableType.forClassWithGenerics(JpaRepository.class, entityClass, idClass);
        this.abstractRepo = (JpaRepository<T, ID>) applicationContext.getBeanProvider(type).getObject();
    }

    public TDTO get(Long id) {
        List<T> item = session
                .createCriteria(entityClass)
                .add(Restrictions.eq("id", id))
                .list();
        if (item.size() == 1) {
            return map(item.get(0));
        } else {
            return null;
        }
    }

    public TDTO get(String uuid) {
        List<T> item = session
                .createCriteria(entityClass)
                .add(Restrictions.eq("uuid", uuid))
                .list();
        if (item.size() == 1) {
            return map(item.get(0));
        } else {
            return null;
        }
    }

    public TDTO save(Object o) {
        try {
            Field uuid = o.getClass().getDeclaredField("uuid");
            uuid.setAccessible(true);
            if (uuid.get(o) == null)
                uuid.set(o, UUID.randomUUID().toString());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            T entity = modelMapper.map(o, entityClass);
            T savedEntity = abstractRepo.save(entity);
            return modelMapper.map(savedEntity, dtoClass);
        }
    }

    public List<TDTO> list() {
        return map(session.createCriteria(entityClass).list());
    }


    public List<TDTO> listByCriteria(Criterion... criterion) {
        Criteria criteria = session.createCriteria(entityClass);
        for (Criterion item : criterion) {
            criteria.add(item);
        }
        return map(criteria.list());
    }

    private List<TDTO> map(List<T> entities) {
        List<TDTO> DTOS = new ArrayList<>();
        for (T entity : entities) {
            DTOS.add(modelMapper.map(entity, dtoClass));
        }
        return DTOS;
    }

    private TDTO map(T entity) {
        return modelMapper.map(entity, dtoClass);
    }
}
