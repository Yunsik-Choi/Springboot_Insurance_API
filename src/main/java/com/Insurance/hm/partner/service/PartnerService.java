package com.Insurance.hm.partner.service;

import com.Insurance.hm.partner.domain.Partner;
import com.Insurance.hm.partner.dto.PartnerCreateRequestDto;

public interface PartnerService {

    Long create(PartnerCreateRequestDto createRequestDto);

    Partner findById(Long id);

    Long deleteById(Long id);


}
