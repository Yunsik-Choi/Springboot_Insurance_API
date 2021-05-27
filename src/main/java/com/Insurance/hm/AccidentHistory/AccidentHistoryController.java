package com.Insurance.hm.AccidentHistory;

import com.Insurance.hm.AccidentHistory.constants.AccidentHistoryResponseConstants;
import com.Insurance.hm.AccidentHistory.domain.AccidentHistory;
import com.Insurance.hm.AccidentHistory.dto.AccidentHistoryCreateRequestDto;
import com.Insurance.hm.AccidentHistory.dto.AccidentHistoryDetailDto;
import com.Insurance.hm.AccidentHistory.service.AccidentHistoryService;
import com.Insurance.hm.global.constants.GlobalConstants;
import com.Insurance.hm.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
public class AccidentHistoryController {

    private final AccidentHistoryService accidentHistoryService;


    @PostMapping("create")
    public void createAccidentHistory(@RequestBody AccidentHistoryCreateRequestDto createRequestDto,
                                             HttpServletResponse response) throws IOException {
        Long id = accidentHistoryService.create(createRequestDto);
        response.sendRedirect(id.toString());
    }

    @GetMapping("{id}")
    public ResponseDto findAccidentHistoryById(@PathVariable Long id){
        AccidentHistory findAccidentHistory = accidentHistoryService.findById(id);
        return ResponseDto.builder()
                .message(AccidentHistoryResponseConstants.ACCIDENT_HISTORY_NO.getMessage()+id+GlobalConstants.FIND_BY_ID.getMessage())
                .data(new AccidentHistoryDetailDto(findAccidentHistory))
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseDto createAccidentHistory(@PathVariable Long id){
        Long deleteId = accidentHistoryService.deleteById(id);
        return ResponseDto.builder()
                .message(AccidentHistoryResponseConstants.ACCIDENT_HISTORY_NO.getMessage()+id+GlobalConstants.DELETE.getMessage())
                .data(deleteId)
                .build();
    }



}
