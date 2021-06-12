package com.Insurance.hm.claim.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    @Override
    @EntityGraph(value = "claim-entity-graph", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Claim> findById(Long aLong);

    @Override
    @EntityGraph(value = "claim-entity-graph", type = EntityGraph.EntityGraphType.LOAD)
    List<Claim> findAll();

}
