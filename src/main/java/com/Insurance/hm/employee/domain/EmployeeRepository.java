package com.Insurance.hm.employee.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
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
        return employee;
    }

    public Employee findById(Long id) {
        return em.find(Employee.class,id);
    }

    public Employee update(Employee employee){
        return null;
    }

    public Employee delete(Employee employee) {
        em.remove(employee);
        return employee;
    }

    public Optional findByLoginId(String loginId) throws RuntimeException {
        Query query = em.createQuery("select e from Employee e where e.login_id =: loginId", Employee.class)
                .setParameter("loginId", loginId);
        Optional findEmployee = query.getResultStream().findFirst();
        return findEmployee;
    }
}
