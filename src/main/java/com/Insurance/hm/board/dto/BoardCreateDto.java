package com.Insurance.hm.board.dto;

import com.Insurance.hm.board.domain.Board;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.global.domain.file.File;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Data
public class BoardCreateDto {

    private String title;
    private Long employeeId;
    private String content;
    private List<File> fileList;

    public Board toEntity(Employee employee) {
        Board board = Board.builder()
                .title(title)
                .employee(employee)
                .content(content)
                .fileList(fileList)
                .build();
        return board;
    }
}
