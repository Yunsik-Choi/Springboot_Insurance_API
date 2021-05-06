package com.Insurance.hm.employee.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Transactional
public class EmployeeRepository {

    private final EntityManager em;

    public Employee save(Employee employee){
        em.persist(employee);
        Employee findEmployee = em.find(Employee.class, employee.getId());
        return findEmployee;
    }

    public Employee findById(Long id){
        return em.find(Employee.class,id);
    }

    public Employee update(Employee employee){
        return null;
    }

    public Employee delete(Long id){
        return null;
    }
}
