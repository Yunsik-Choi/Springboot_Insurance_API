package com.Insurance.hm.AccidentHistory.dto;

import com.Insurance.hm.AccidentHistory.domain.AccidentHistory;
import com.Insurance.hm.client.domain.Client;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class AccidentHistoryCreateRequestDto {

    private LocalDateTime accidentDate;
    private Double accidentRate;
    private String accidentDescription;

    private Long clientId;


    public AccidentHistory toEntity(Client client){
        AccidentHistory accidentHistory = AccidentHistory.builder()
                .accidentDate(accidentDate)
                .accidentRate(accidentRate)
                .accidentDescription(accidentDescription)
                .client(client)
                .build();
        return accidentHistory;
    }
}
