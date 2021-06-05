package com.Insurance.hm.insurance;

import com.Insurance.hm.global.constants.GlobalConstants;
import com.Insurance.hm.global.dto.ResponseDto;
import com.Insurance.hm.insurance.constants.InsuranceResponseConstants;
import com.Insurance.hm.insurance.domain.Insurance;
import com.Insurance.hm.insurance.dto.InsuranceCreateRequestDto;
import com.Insurance.hm.insurance.dto.InsuranceDetailDto;
import com.Insurance.hm.insurance.dto.InsuranceChangeStatusRequestDto;
import com.Insurance.hm.insurance.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/insurance")
public class InsuranceController {

    private final InsuranceService insuranceService;

    @GetMapping
    public ResponseDto showAll(){
        List<Insurance> all = insuranceService.findAll();
        List<InsuranceDetailDto> responseAll = all.stream()
                .map(i -> new InsuranceDetailDto(i)).collect(Collectors.toList());
        return ResponseDto.builder()
                .message(InsuranceResponseConstants.FIND_ALL.getMessage())
                .data(responseAll)
                .build();
    }

    @PostMapping("create")
    public void createInsurance(@RequestBody InsuranceCreateRequestDto requestDto,
                                               HttpServletResponse response) throws IOException {
        Long id = insuranceService.create(requestDto);
        response.sendRedirect(id.toString());
    }

    @GetMapping("{id}")
    public ResponseDto findInsuranceById(@PathVariable Long id){
        Insurance findEmployee = insuranceService.findById(id);
        InsuranceDetailDto insuranceDetailDto = new InsuranceDetailDto(findEmployee);
        return ResponseDto.builder()
                .message(InsuranceResponseConstants.INSURANCE_NO.getMessage()+id+GlobalConstants.FIND_BY_ID.getMessage())
                .data(insuranceDetailDto)
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseDto deleteInsuranceById(@PathVariable Long id){
        Long deleteId = insuranceService.deleteById(id);
        return ResponseDto.builder()
                .message(InsuranceResponseConstants.INSURANCE_NO.getMessage()+deleteId+GlobalConstants.DELETE.getMessage())
                .data(deleteId)
                .build();
    }

    @PostMapping("{id}/status")
    public ResponseDto changeInsuranceStatus(@PathVariable(value = "id") Long id,
                                             @RequestBody InsuranceChangeStatusRequestDto changeRequestDto){
        Insurance insurance = insuranceService.changeStatus(id, changeRequestDto);
        return ResponseDto.builder()
                .message(InsuranceResponseConstants.INSURANCE_NO.getMessage()+id
                        +InsuranceResponseConstants.CHANGE_INSURANCE_STATUS.getMessage())
                .data(new InsuranceDetailDto(insurance))
                .build();
    }

}
