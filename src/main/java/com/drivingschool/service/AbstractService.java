package com.drivingschool.service;


import com.drivingschool.configuration.ApplicationContextProvider;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
public abstract class AbstractService<T, TDTO, ID extends Number> {
    private final EntityManager entityManager;
//    private final Session session;
    private final ModelMapper modelMapper;
    private final Class<T> entityClass;
    private final Class<TDTO> dtoClass;
    private final Class<ID> idClass;
    private final ApplicationContext applicationContext;
    private final JpaRepository<T, ID> abstractRepo;
    private final Logger logger;

    public AbstractService() {
        this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.dtoClass = (Class<TDTO>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        this.idClass = (Class<ID>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[2];
        this.applicationContext = ApplicationContextProvider.getApplicationContext();
        this.modelMapper = applicationContext.getBean(ModelMapper.class);
        this.entityManager = applicationContext.getBean(EntityManager.class);

        ResolvableType type = ResolvableType.forClassWithGenerics(JpaRepository.class, entityClass, idClass);
        this.abstractRepo = (JpaRepository<T, ID>) applicationContext.getBeanProvider(type).getObject();
        this.logger = LoggerFactory.getLogger(AbstractService.class);
    }

    public TDTO get(Long id) {
        return abstractRepo.findById((ID) id).map(this::map).orElse(null);
    }

    public TDTO get(String uuid) {
        String query = String.format("FROM %s", entityClass.getSimpleName());
        List<T> resultList = entityManager.createQuery(query + " WHERE uuid = :uuid").setParameter("uuid", uuid).getResultList();
        if (resultList.size() == 1)
            return map(resultList.get(0));
        else
            return null;
    }

    public TDTO save(Object o) {
        try {
            Field uuid = o.getClass().getDeclaredField("uuid");
            uuid.setAccessible(true);
            if (uuid.get(o) == null)
                uuid.set(o, UUID.randomUUID().toString());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        T entity = modelMapper.map(o, entityClass);
        T savedEntity = abstractRepo.save(entity);
        return modelMapper.map(savedEntity, dtoClass);

    }

    public List<TDTO> list() {
        return map(abstractRepo.findAll());
    }

    public void delete(String uuid) {
        entityManager.createQuery("FROM " + entityClass.getSimpleName() + " WHERE uuid = :uuid")
                .setParameter("uuid", uuid)
                .getResultStream()
                .findFirst()
                .ifPresent(entityManager::remove);
    }


    protected List<TDTO> map(List<T> entities) {
        List<TDTO> DTOS = new ArrayList<>();
        for (T entity : entities) {
            DTOS.add(modelMapper.map(entity, dtoClass));
        }
        return DTOS;
    }

    protected TDTO map(T entity) {
        return modelMapper.map(entity, dtoClass);
    }
}
