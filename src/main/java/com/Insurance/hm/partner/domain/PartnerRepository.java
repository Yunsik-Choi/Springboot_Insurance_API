package com.Insurance.hm.partner.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PartnerRepository extends JpaRepository<Partner, Long> {

    @Override
    @EntityGraph(attributePaths = {"employee","claimpartnerList"})
    Optional<Partner> findById(Long aLong);


    @Override
    @EntityGraph(attributePaths = {"employee","claimpartnerList"})
    List<Partner> findAll();
}
