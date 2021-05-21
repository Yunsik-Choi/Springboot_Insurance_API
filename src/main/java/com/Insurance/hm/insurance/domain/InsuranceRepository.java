package com.Insurance.hm.insurance.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class InsuranceRepository {

    private final EntityManager em;

    public Insurance create(Insurance insurance){
        em.persist(insurance);
        return insurance;
    }

    public Insurance findById(Long id){
        return em.find(Insurance.class, id);
    }

    public Insurance deleteById(Long id){
        Insurance findInsurance = findById(id);
        em.remove(findInsurance);
        return findInsurance;
    }


}
