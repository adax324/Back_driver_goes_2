package com.drivingschool.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

import static com.drivingschool.utility.CustomMapper.map;

public abstract interface AbstractServiceInterface<T, TDTO, K extends Serializable> {
    TDTO get(K id);

    TDTO get(String uuid);

    TDTO save(TDTO o);

    List<TDTO> list();

    void delete(K id);

    void delete(String uuid);
}
