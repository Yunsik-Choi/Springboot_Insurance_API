package com.Insurance.hm.compensation.domain;

import com.Insurance.hm.employee.domain.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompensationRepository extends JpaRepository<Compensation, Long> {

    @Override
    @EntityGraph(value = "compensation-entity-graph", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Compensation> findById(Long aLong);


    @Override
    @EntityGraph(value = "compensation-entity-graph", type = EntityGraph.EntityGraphType.LOAD)
    List<Compensation> findAll();


    @EntityGraph(value = "compensation-entity-graph", type = EntityGraph.EntityGraphType.LOAD)
    List<Compensation> findByEmployee(Employee employee);
}
