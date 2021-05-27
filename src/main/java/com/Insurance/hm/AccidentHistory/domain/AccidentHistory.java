package com.Insurance.hm.AccidentHistory.domain;

import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.global.domain.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccidentHistory extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accident_history_id")
    private Long id;

    @Column(name = "accident_date")
    private LocalDateTime accidentDate;
    @Column(name = "accident_rate")
    private Double accidentRate;
    @Column(name = "accident_description")
    private String accidentDescription;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;


    @Builder
    public AccidentHistory(LocalDateTime accidentDate, Double accidentRate, String accidentDescription, Client client) {
        this.accidentDate = accidentDate;
        this.accidentRate = accidentRate;
        this.accidentDescription = accidentDescription;
        changeClient(client);
    }


    private void changeClient(Client client){
        this.client = client;
        List<AccidentHistory> accident_history_list = client.getAccidentHistoryList();
        if(!accident_history_list.contains(this))
            accident_history_list.add(this);
    }
}
