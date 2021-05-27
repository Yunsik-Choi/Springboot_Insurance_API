package com.Insurance.hm.AccidentHistory.dto;

import com.Insurance.hm.AccidentHistory.domain.AccidentHistory;
import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.client.dto.ClientSimpleDto;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class AccidentHistoryDetailDto {

    private Long id;

    private LocalDateTime accidentDate;
    private Double accidentRate;
    private String accidentDescription;

    private ClientSimpleDto client;


    public AccidentHistoryDetailDto(AccidentHistory accidentHistory) {
        this.id = accidentHistory.getId();
        this.accidentDate = accidentHistory.getAccidentDate();
        this.accidentRate = accidentHistory.getAccidentRate();
        this.accidentDescription = accidentHistory.getAccidentDescription();
        this.client = new ClientSimpleDto(accidentHistory.getClient());
    }
}