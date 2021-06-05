package com.Insurance.hm.client.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {

    @Override
    @EntityGraph(attributePaths = {"contractList","accidentHistoryList"})
    Optional<Client> findById(Long aLong);


    @Override
    @EntityGraph(attributePaths = {"contractList","accidentHistoryList"})
    List<Client> findAll();
}
