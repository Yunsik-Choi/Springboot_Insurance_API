package com.Insurance.hm.AccidentHistory.service;

import com.Insurance.hm.AccidentHistory.constants.AccidentHistoryErrorConstants;
import com.Insurance.hm.AccidentHistory.domain.AccidentHistory;
import com.Insurance.hm.AccidentHistory.domain.AccidentHistoryRepository;
import com.Insurance.hm.AccidentHistory.dto.AccidentHistoryCreateRequestDto;
import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.client.domain.ClientRepository;
import com.Insurance.hm.global.constants.GlobalErrorConstants;
import com.Insurance.hm.global.exception.business.NonMatchIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccidentHistoryServiceImpl implements AccidentHistoryService{

    private final AccidentHistoryRepository accidentHistoryRepository;
    private final ClientRepository clientRepository;

    @Override
    public Long create(AccidentHistoryCreateRequestDto accidentHistoryCreateRequestDto) {
        Long clientId = accidentHistoryCreateRequestDto.getClientId();
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NonMatchIdException(AccidentHistoryErrorConstants.NON_MATCH_CLIENT));
        AccidentHistory accidentHistory = accidentHistoryCreateRequestDto.toEntity(client);
        accidentHistoryRepository.save(accidentHistory);
        return accidentHistory.getId();
    }

    @Override
    public AccidentHistory findById(Long id) {
        AccidentHistory accidentHistory = accidentHistoryRepository.findById(id).
                orElseThrow(() -> getNonMatchAccidentHistoryById());
        return accidentHistory;
    }


    @Override
    public Long deleteById(Long id) {
        AccidentHistory accidentHistory = accidentHistoryRepository.findById(id)
                .orElseThrow(() -> getNonMatchAccidentHistoryById());
        accidentHistoryRepository.deleteById(id);
        return id;
    }


    private NonMatchIdException getNonMatchAccidentHistoryById() {
        return new NonMatchIdException(GlobalErrorConstants.Non_Match_Id.setClassNameMessage("AccidentHistory"));
    }

}