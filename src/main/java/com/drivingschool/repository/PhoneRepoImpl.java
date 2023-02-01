package com.drivingschool.repository;

import com.drivingschool.entity.PhoneNumber;
import org.springframework.stereotype.Repository;

@Repository
public class PhoneRepoImpl extends AbstractRepo<PhoneNumber, Long> implements PhoneRepo {
}
