package com.Insurance.hm.insurance.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<Insurance,Long> {

}
