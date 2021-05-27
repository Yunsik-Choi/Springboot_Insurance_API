package com.Insurance.hm.AccidentHistory.dto;

import com.Insurance.hm.AccidentHistory.domain.AccidentHistory;
import com.Insurance.hm.client.domain.Client;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccidentHistoryInfoDto {

    private Long id;

    private LocalDateTime accidentDate;
    private Double accidentRate;
    private String accidentDescription;
    private Long clientId;

    public AccidentHistoryInfoDto(AccidentHistory accidentHistory) {
        this.id = accidentHistory.getId();
        this.accidentDate = accidentHistory.getAccidentDate();
        this.accidentRate = accidentHistory.getAccidentRate();
        this.accidentDescription = accidentHistory.getAccidentDescription();
        this.clientId = accidentHistory.getClient().getId();
    }
}
