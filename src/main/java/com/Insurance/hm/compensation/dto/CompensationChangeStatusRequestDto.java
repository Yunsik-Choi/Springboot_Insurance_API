package com.Insurance.hm.compensation.dto;

import com.Insurance.hm.compensation.domain.entity.CompensationStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompensationChangeStatusRequestDto {

    private CompensationStatus status;

}
