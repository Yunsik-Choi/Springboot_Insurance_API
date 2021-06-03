package com.Insurance.hm.contract;

import com.Insurance.hm.contract.constants.ContractResponseConstants;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.contract.dto.ContractChangeStatusRequestDto;
import com.Insurance.hm.contract.dto.ContractDetailDto;
import com.Insurance.hm.contract.dto.ContractSignRequestDto;
import com.Insurance.hm.contract.service.ContractService;
import com.Insurance.hm.global.constants.GlobalConstants;
import com.Insurance.hm.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/contract")
public class ContractController {

    private final ContractService contractService;


    @GetMapping
    public ResponseDto showAll(){
        List<Contract> all = contractService.findAll();
        return ResponseDto.builder()
                .message(ContractResponseConstants.FIND_ALL.getMessage())
                .data(all)
                .build();
    }

    @PostMapping("sign")
    public void signContract(@RequestBody ContractSignRequestDto contractSignRequestDto,
                             HttpServletResponse response) throws IOException {
        Long id = contractService.sign(contractSignRequestDto);
        response.sendRedirect(id.toString());
    }

    @GetMapping("{id}")
    public ResponseDto findById(@PathVariable("id") Long id){
        Contract findContract = contractService.findById(id);
        return ResponseDto.builder()
                .data(new ContractDetailDto(findContract))
                .message(ContractResponseConstants.CONTRACT_NO.getMessage()+id+GlobalConstants.FIND_BY_ID.getMessage())
                .build();
    }

    @PostMapping("{id}/status")
    public ResponseDto changeContractStatus(@PathVariable(value = "id") Long id,
                                            @RequestBody ContractChangeStatusRequestDto changeStatusRequestDto){
        Contract findContract = contractService.changeContractStatusById(id,changeStatusRequestDto);
        return ResponseDto.builder()
                .data(new ContractDetailDto(findContract))
                .message(ContractResponseConstants.CONTRACT_NO.getMessage()+findContract.getId()
                        +ContractResponseConstants.CHANGE_CONTRACT_STATUS.getMessage())
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseDto deleteContract(@PathVariable("id") Long id){
        Long deleteId = contractService.deleteById(id);
        return ResponseDto.builder()
                .data(deleteId)
                .message(ContractResponseConstants.CONTRACT_NO.getMessage()+id+GlobalConstants.DELETE.getMessage())
                .build();
    }


}
