package com.Insurance.hm.compensation;

import com.Insurance.hm.compensation.constants.CompensationResponseConstants;
import com.Insurance.hm.compensation.domain.Compensation;
import com.Insurance.hm.compensation.dto.CompensationChangeStatusRequestDto;
import com.Insurance.hm.compensation.dto.CompensationDetailDto;
import com.Insurance.hm.compensation.service.CompensationService;
import com.Insurance.hm.global.constants.GlobalConstants;
import com.Insurance.hm.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/compensation")
public class CompensationController {

    private final CompensationService compensationService;

    @GetMapping("{id}")
    public ResponseDto findCompensationById(@PathVariable(value = "id") Long id){
        Compensation compensation = compensationService.findById(id);
        return ResponseDto.builder()
                .message(CompensationResponseConstants.COMPENSATION_NO.getMessage()+id+ GlobalConstants.FIND_BY_ID)
                .data(new CompensationDetailDto(compensation))
                .build();
    }

    @PostMapping("{id}/status")
    public void changeCompensationStatus(@PathVariable(value = "id") Long id,
                                         @RequestBody CompensationChangeStatusRequestDto changeStatusRequestDto,
                                         HttpServletResponse response) throws IOException {
        Long compensationId = compensationService.changeStatus(id, changeStatusRequestDto);
        response.sendRedirect(compensationId.toString());
    }



}
