package com.drivingschool.repository;

import com.drivingschool.entity.City;
import org.springframework.stereotype.Repository;

@Repository
public class CityRepoImpl extends AbstractRepo<City, Long> implements CityRepo {
}
