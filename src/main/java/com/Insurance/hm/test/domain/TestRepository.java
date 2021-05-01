package com.Insurance.hm.test.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class TestRepository {

    private final EntityManager em;

    public Long saveTest(Test test){
        em.persist(test);
        return test.getId();
    }

    public Test searchTest(Long testID){
        Test test = em.find(Test.class, testID);
        return test;
    }

    public List<Test> findAllTest(){
        List<Test> testList = em.createQuery("select t from Test t")
                                .getResultList();
        return testList;
    }

    public Test deleteTest(Long testID){
        Test test = searchTest(testID);
        em.remove(test);
        return test;
    }

}
