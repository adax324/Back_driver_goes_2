package com.drivingschool.service;


import com.drivingschool.configuration.ApplicationContextProvider;
import com.drivingschool.dto.AbstractDTO;
import com.drivingschool.repository.AbstractRepo;
import com.drivingschool.utility.CustomMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.UUID;

import static com.drivingschool.utility.CustomMapper.*;

@Service
public abstract class AbstractService<T, TDTO, K extends Serializable> implements AbstractServiceInterface<T, TDTO, K> {
    private final EntityManager entityManager;
    private final Class<T> entityClass;
    private final Class<TDTO> dtoClass;
    private final Class<K> keyClass;
    private final ApplicationContext applicationContext;
    @Autowired
    private AbstractRepo<T, K> abstractRepo;
    private final Logger logger;

    public AbstractService() {
        this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.dtoClass = (Class<TDTO>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        this.keyClass = (Class<K>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[2];
        this.applicationContext = ApplicationContextProvider.getApplicationContext();
        this.entityManager = applicationContext.getBean(EntityManager.class);

        ResolvableType type = ResolvableType.forClassWithGenerics(JpaRepository.class, entityClass, keyClass);
        this.logger = LoggerFactory.getLogger(AbstractService.class);
    }

    public TDTO get(K id) {
        return map(abstractRepo.get(id), dtoClass);
    }

    public TDTO get(String uuid) {
        return map(abstractRepo.get(uuid), dtoClass);
    }

    public TDTO save(TDTO o) {
        try {
            Field uuid = o.getClass().getDeclaredField("uuid");
            uuid.setAccessible(true);
            if (uuid.get(o) == null)
                uuid.set(o, UUID.randomUUID().toString());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        T entity = map(o, entityClass);
        T savedEntity = abstractRepo.save(entity);
        return map(savedEntity, dtoClass);

    }

    public List<TDTO> list() {
        return map(abstractRepo.list(), dtoClass);
    }

    public void delete(K id) {
        abstractRepo.delete(id);
    }

    public void delete(String uuid) {
        abstractRepo.delete(uuid);
    }

}
