package com.Insurance.hm.board.dto;

import com.Insurance.hm.board.domain.Board;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.entity.Department;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Clob;
import java.time.LocalDateTime;

@Data
public class BoardDetailDto{

    private Long id;
    private String title;
    private String author;
    private Department department;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String content;

    public BoardDetailDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.author = board.getEmployee().getName();
        this.department = board.getEmployee().getDepartment();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
        this.content = board.getContent();
    }
}
