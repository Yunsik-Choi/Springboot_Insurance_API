package com.Insurance.hm.board.dto;

import com.Insurance.hm.board.domain.Board;
import com.Insurance.hm.employee.domain.Employee;
import lombok.Data;


@Data
public class BoardCreateDto {

    private String title;
    private Long employeeId;
    private String content;

    public Board toEntity(Employee employee) {
        Board board = Board.builder()
                .title(title)
                .employee(employee)
                .content(content)
                .build();
        return board;
    }
}
