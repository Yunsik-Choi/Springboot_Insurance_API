package com.Insurance.hm.employee.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

    private final EntityManager em;

    public Employee save(Employee employee){
        em.persist(employee);
        Employee findEmployee = em.find(Employee.class,employee.getId());
        return findEmployee;
    }

    public Employee findById(Long id) {
        return em.find(Employee.class,id);
    }

    public Employee update(Employee employee){
        return null;
    }

    public Employee delete(Long id){
        Employee findEmployee = findById(id);
        em.remove(findEmployee);
        return findEmployee;
    }

    public Optional findByLoginId(String loginId) throws RuntimeException {
        Query query = em.createQuery("select e from Employee e where e.login_id =: loginId", Employee.class)
                .setParameter("loginId", loginId);
        Optional findEmployee = query.getResultStream().findFirst();
        return findEmployee;
    }
}
