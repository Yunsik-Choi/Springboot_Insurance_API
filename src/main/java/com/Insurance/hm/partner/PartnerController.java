package com.Insurance.hm.partner;

import com.Insurance.hm.global.constants.GlobalConstants;
import com.Insurance.hm.global.dto.ResponseDto;
import com.Insurance.hm.partner.constants.PartnerResponseConstants;
import com.Insurance.hm.partner.domain.Partner;
import com.Insurance.hm.partner.dto.PartnerCreateRequestDto;
import com.Insurance.hm.partner.dto.PartnerDetailDto;
import com.Insurance.hm.partner.service.PartnerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/partner")
public class PartnerController {

    private final PartnerService partnerService;

    @PostMapping("create")
    private void createPartner(@RequestBody PartnerCreateRequestDto createRequestDto,
                               HttpServletResponse response) throws IOException {
        Long id = partnerService.create(createRequestDto);
        response.sendRedirect(id.toString());
    }

    @GetMapping("{id}")
    private ResponseDto findById(@PathVariable(value = "id") Long id){
        Partner findPartner = partnerService.findById(id);
        return ResponseDto.builder()
                .message(PartnerResponseConstants.PARTNER_NO.getMessage()+id+ GlobalConstants.FIND_BY_ID.getMessage())
                .data(new PartnerDetailDto(findPartner))
                .build();
    }

    @DeleteMapping("{id}")
    private ResponseDto deleteById(@PathVariable(value = "id") Long id){
        Long deleteById = partnerService.deleteById(id);
        return ResponseDto.builder()
                .message(PartnerResponseConstants.PARTNER_NO.getMessage()+id+GlobalConstants.DELETE)
                .data(deleteById)
                .build();
    }


}
