package com.drivingschool.repository;

public interface AbstractRepoInterface<T, K> {
    T save(T object);
}
