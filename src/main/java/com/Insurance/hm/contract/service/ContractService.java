package com.Insurance.hm.contract.service;

import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.contract.dto.ContractChangeStatusRequestDto;
import com.Insurance.hm.contract.dto.ContractSignRequestDto;

import java.util.List;

public interface ContractService {

    Long sign(ContractSignRequestDto contractSignRequestDto);

    Contract findById(Long id);

    Long deleteById(Long id);

    Contract changeContractStatusById(Long id, ContractChangeStatusRequestDto changeStatusRequestDto);

    List<Contract> findAll();
}
