package com.Insurance.hm.AccidentHistory.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccidentHistoryRepository extends JpaRepository<AccidentHistory, Long> {

    @Override
    @EntityGraph(attributePaths = {"client"})
    List<AccidentHistory> findAll();

    @Override
    @EntityGraph(attributePaths = {"client"})
    Optional<AccidentHistory> findById(Long aLong);
}
