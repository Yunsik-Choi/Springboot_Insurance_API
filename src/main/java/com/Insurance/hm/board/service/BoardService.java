package com.Insurance.hm.board.service;

import com.Insurance.hm.board.domain.Board;
import com.Insurance.hm.board.dto.BoardCreateDto;

import java.util.List;

public interface BoardService {

    Long create(BoardCreateDto boardCreateDto);

    Board findById(Long id);

    Long update(Long id, BoardCreateDto boardUpdateDto);

    Long deleteById(Long id);


    List<Board> findAll();
}
