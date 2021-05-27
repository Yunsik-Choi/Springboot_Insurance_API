package com.Insurance.hm.insurance.domain;

import com.Insurance.hm.insurance.domain.entity.InsuranceTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.annotation.Target;
import java.util.List;
import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<Insurance,Long> {

    @Query("select i from Insurance i where i.id =:id AND i.target.startAge >:startage")
    Optional<Insurance> findStartage(@Param("id") Long id, @Param("startage") int age);
}
