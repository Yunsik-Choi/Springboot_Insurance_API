package com.Insurance.hm.claim.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Transactional
public class ClaimRepository {

    private final EntityManager em;

    public Claim add(Claim claim){

        return null;
    }

    public Claim findByID(Long id){

        return null;
    }

    public Claim update(Claim claim){

        return null;
    }

    public Claim delete(Long id){

        return null;
    }

}
