package com.Insurance.hm.insurance.domain;

import com.Insurance.hm.insurance.domain.entity.InsuranceTarget;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<Insurance,Long> {

    @Override
    @EntityGraph(value = "insurance-entity-graph", type = EntityGraph.EntityGraphType.LOAD)
    List<Insurance> findAll();

    @Override
    @EntityGraph(value = "insurance-entity-graph", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Insurance> findById(Long aLong);


}
