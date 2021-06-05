package com.Insurance.hm.board;

import com.Insurance.hm.board.constants.BoardResponseConstants;
import com.Insurance.hm.board.domain.Board;
import com.Insurance.hm.board.dto.BoardCreateDto;
import com.Insurance.hm.board.dto.BoardDetailDto;
import com.Insurance.hm.board.service.BoardService;
import com.Insurance.hm.global.constants.GlobalConstants;
import com.Insurance.hm.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public ResponseDto showAll(){
        List<Board> all = boardService.findAll();
        List<BoardDetailDto> responseAll = all.stream().map(i -> new BoardDetailDto(i)).collect(Collectors.toList());
        return ResponseDto.builder()
                .message(BoardResponseConstants.FIND_ALL.getMessage())
                .data(responseAll)
                .build();
    }

    @PostMapping("write")
    public void writeBoard(@RequestBody BoardCreateDto boardCreateDto, HttpServletResponse response) throws IOException {
        Long id = boardService.create(boardCreateDto);
        response.sendRedirect(id.toString());
    }


    @GetMapping("{id}")
    public ResponseDto findById(@PathVariable Long id){
        Board findBoard = boardService.findById(id);
        BoardDetailDto boardDetailDto = new BoardDetailDto(findBoard);
        return ResponseDto.builder()
                .message(BoardResponseConstants.BOARD_NO.getMessage()+id+ GlobalConstants.FIND_BY_ID.getMessage())
                .data(boardDetailDto)
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseDto deleteById(@PathVariable Long id){
        boardService.deleteById(id);
        return ResponseDto.builder()
                .message(BoardResponseConstants.BOARD_NO.getMessage()+id+GlobalConstants.DELETE.getMessage())
                .data(id)
                .build();
    }

    @PostMapping("{id}")
    public void updateById(@PathVariable Long id, @RequestBody BoardCreateDto boardCreateDto,
                           HttpServletResponse response) throws IOException {
        boardService.update(id,boardCreateDto);
        response.sendRedirect(id.toString());
    }

}
