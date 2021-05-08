package com.Insurance.hm.employee.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class EmployeeRepository {

    private final EntityManager em;

    public Employee save(Employee employee){
        em.persist(employee);
        Employee findEmployee = em.find(Employee.class,employee.getId());
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

    public List<Employee> findByLoginId(String loginId) {
        List<Employee> employeeList = em.createQuery("select e from Employee e where e.login_id =: loginId", Employee.class)
                .setParameter("loginId", loginId)
                .getResultList();
        return employeeList;
    }

    public Employee findByLoginIdAndPassword(String loginId, String password){
        Employee findEmployee = em.createQuery("select e from Employee e where e.login_id =: loginId and e.password =: password", Employee.class)
                .setParameter("loginId", loginId)
                .setParameter("password", password)
                .getSingleResult();
        return findEmployee;
    }
}
