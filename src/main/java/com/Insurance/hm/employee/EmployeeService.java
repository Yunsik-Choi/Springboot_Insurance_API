package com.Insurance.hm.employee;

import com.Insurance.hm.employee.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EntityManager em;


    public Employee findByLoginId(String loginId, String password){
        Object singleResult = em.createQuery("select e from Employee e where e.login_id =: loginId and e.password =: password")
                .setParameter("loginId",loginId)
                .setParameter("password",password)
                .getSingleResult();
        Employee findEmployee = (Employee) singleResult;
        return findEmployee;
    }

}
