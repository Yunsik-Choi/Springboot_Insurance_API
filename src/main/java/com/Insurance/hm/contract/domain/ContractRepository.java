package com.Insurance.hm.contract.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Override
    @EntityGraph(value = "contract-entity-graph", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Contract> findById(Long aLong);


    @Override
    @EntityGraph(value = "contract-entity-graph", type = EntityGraph.EntityGraphType.LOAD)
    List<Contract> findAll();
}
