package com.Insurance.hm.employee;

import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.EmployeeRepository;
import com.Insurance.hm.employee.dto.DetailEmployeeDto;
import com.Insurance.hm.employee.dto.LoginEmployeeDto;
import com.Insurance.hm.employee.dto.LoginInfoDto;
import com.Insurance.hm.exception.SimpleMessageException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee join(Employee employee){
        List<Employee> employeeList = employeeRepository.findByLoginId(employee.getLogin_id());
        if(employeeList.size() != 0)
            throw new SimpleMessageException(HttpStatus.INTERNAL_SERVER_ERROR,
                    employeeList.get(0).getLogin_id()+"는 이미 존재하는 아이디입니다.");
        Employee save = employeeRepository.save(employee);
        return save;
    }

    public LoginEmployeeDto login(LoginInfoDto loginInfoDto){
        List<Employee> employeeList = employeeRepository.findByLoginId(loginInfoDto.getId());
        if(employeeList.size()==0)
            throw new SimpleMessageException(HttpStatus.INTERNAL_SERVER_ERROR,
                    loginInfoDto.getId()+"와 일치하는 아이디가 존재하지 않습니다.");
        if(!employeeList.get(0).getPassword().equals(loginInfoDto.getPassword()))
            throw new SimpleMessageException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
        LoginEmployeeDto loginEmployeeDto = new LoginEmployeeDto(employeeList.get(0));
        return loginEmployeeDto;
    }

    public DetailEmployeeDto deleteEmployee(Long id){
        Employee findEmployee = employeeRepository.findById(id);
        if(findEmployee==null)
            throw new SimpleMessageException(HttpStatus.BAD_REQUEST,"ID:"+id+" 와 일치하는 직원이 존재하지 않습니다.");
        return null;
    }

}
