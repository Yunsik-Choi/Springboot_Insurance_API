package com.Insurance.hm.employee.domain;

import com.Insurance.hm.employee.domain.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findEmployeeByLoginId(String loginId);

    List<Employee> findEmployeeByDepartment(Department department);
}
