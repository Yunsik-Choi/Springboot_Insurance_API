package com.Insurance.hm.board.service;

import com.Insurance.hm.board.constants.BoardErrorConstants;
import com.Insurance.hm.board.domain.Board;
import com.Insurance.hm.board.domain.BoardRepository;
import com.Insurance.hm.board.dto.BoardCreateDto;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.EmployeeRepository;
import com.Insurance.hm.global.constants.GlobalErrorConstants;
import com.Insurance.hm.global.exception.business.NonMatchIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Long create(BoardCreateDto boardCreateDto) {
        Employee employee = employeeRepository.findById(boardCreateDto.getEmployeeId())
                .orElseThrow(() -> new NonMatchIdException(BoardErrorConstants.NON_MATCH_AUTHOR));
        Board board = boardCreateDto.toEntity(employee);
        boardRepository.save(board);
        return board.getId();
    }

    @Override
    public Board findById(Long id) {
        Board board = boardRepository.findById(id).
                orElseThrow(this::getNonMatchBoardId);
        return board;
    }



    @Override
    public Long update(Long id, BoardCreateDto boardCreateDto) {
        Board board = boardRepository.findById(id).orElseThrow(this::getNonMatchBoardId);
        board.update(boardCreateDto);
        return board.getId();
    }

    @Override
    public Long deleteById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(this::getNonMatchBoardId);
        boardRepository.delete(board);
        return id;
    }

    private NonMatchIdException getNonMatchBoardId() {
        return new NonMatchIdException(GlobalErrorConstants.Non_Match_Id.setClassNameMessage("Board"));
    }
}
