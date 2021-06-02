package com.Insurance.hm.partner.service;

import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.EmployeeRepository;
import com.Insurance.hm.global.exception.business.NonMatchIdException;
import com.Insurance.hm.partner.constants.PartnerErrorConstants;
import com.Insurance.hm.partner.domain.Partner;
import com.Insurance.hm.partner.domain.PartnerRepository;
import com.Insurance.hm.partner.dto.PartnerCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService{

    private final PartnerRepository partnerRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Long create(PartnerCreateRequestDto createRequestDto) {
        Long employeeId = createRequestDto.getEmployeeId();
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NonMatchIdException(PartnerErrorConstants.NON_MATCH_EMPLOYEE));
        Partner partner = createRequestDto.toEntity(employee);
        Partner savePartner = partnerRepository.save(partner);
        return savePartner.getId();
    }

    @Override
    public Partner findById(Long id) {
        Partner partner = partnerRepository.findById(id).orElseThrow(this::getNonMatchPartner);
        return partner;
    }

    @Override
    public Long deleteById(Long id) {
        Partner partner = partnerRepository.findById(id).orElseThrow(this::getNonMatchPartner);
        partnerRepository.delete(partner);
        return id;
    }

    private NonMatchIdException getNonMatchPartner() {
        return new NonMatchIdException(PartnerErrorConstants.NON_MATCH_PARTNER);
    }
}
