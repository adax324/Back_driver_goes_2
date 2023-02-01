package com.drivingschool.repository;

import java.util.List;

public interface AbstractRepoInterface<T, K> {
    T save(T object);
    T update(T entity);
    List<T> list();
    T get(K id);
    T get(String uuid);
    void delete(K id);
    void delete(String uuid);

}
