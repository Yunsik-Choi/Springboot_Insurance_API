package com.Insurance.hm.insurance;

import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.entity.Department;
import com.Insurance.hm.employee.domain.entity.Role;
import com.Insurance.hm.global.constants.GlobalConstants;
import com.Insurance.hm.global.dto.ResponseDto;
import com.Insurance.hm.insurance.constants.InsuranceResponseConstants;
import com.Insurance.hm.insurance.domain.Insurance;
import com.Insurance.hm.insurance.dto.InsuranceCreateRequestDto;
import com.Insurance.hm.insurance.dto.InsuranceCreateResponseDto;
import com.Insurance.hm.insurance.dto.InsuranceDetailDto;
import com.Insurance.hm.insurance.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/insurance")
public class InsuranceController {

    private final InsuranceService insuranceService;

    @PostMapping("create")
    public void createInsurance(@RequestBody InsuranceCreateRequestDto requestDto,
                                               HttpServletResponse response) throws IOException {
        response.sendRedirect("/api/insurance/"+insuranceService.create(requestDto));
    }

    @GetMapping("{id}")
    public ResponseDto findInsuranceById(@PathVariable Long id){
        Insurance findEmployee = insuranceService.findById(id);
        InsuranceDetailDto insuranceDetailDto = new InsuranceDetailDto(findEmployee);
        return ResponseDto.builder()
                .message(InsuranceResponseConstants.INSURANCE_NO.getMessage()+id
                        +" " +GlobalConstants.FIND_BY_ID.getMessage())
                .data(insuranceDetailDto)
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseDto deleteInsuranceById(@PathVariable Long id){
        Long deleteId = insuranceService.deleteById(id);
        return ResponseDto.builder()
                .message(InsuranceResponseConstants.INSURANCE_NO.getMessage()+deleteId
                        +" "+ GlobalConstants.DELETE.getMessage())
                .data(deleteId)
                .build();
    }


}
