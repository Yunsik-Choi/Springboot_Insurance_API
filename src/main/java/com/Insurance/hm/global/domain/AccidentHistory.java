package com.Insurance.hm.global.domain;

import com.Insurance.hm.client.domain.Client;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccidentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime accident_date;

    private Double accident_rate;

    private String accident_description;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;


    @Builder
    public AccidentHistory(LocalDateTime accident_date, Double accident_rate, String accident_description, Client client) {
        this.accident_date = accident_date;
        this.accident_rate = accident_rate;
        this.accident_description = accident_description;
        changeClient(client);
    }


    private void changeClient(Client client){
        this.client = client;
        List<AccidentHistory> accident_history_list = client.getAccident_history_list();
        if(!accident_history_list.contains(this))
            accident_history_list.add(this);
    }
}
