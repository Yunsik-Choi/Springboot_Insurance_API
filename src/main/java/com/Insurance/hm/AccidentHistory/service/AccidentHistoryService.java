package com.Insurance.hm.AccidentHistory.service;

import com.Insurance.hm.AccidentHistory.domain.AccidentHistory;
import com.Insurance.hm.AccidentHistory.dto.AccidentHistoryCreateRequestDto;

import java.util.List;

public interface AccidentHistoryService {

    Long create(AccidentHistoryCreateRequestDto accidentHistoryCreateRequestDto);

    AccidentHistory findById(Long id);

    Long deleteById(Long id);

    List<AccidentHistory> findAll();
}
