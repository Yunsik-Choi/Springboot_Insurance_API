package com.Insurance.hm.board.domain;

import com.Insurance.hm.board.dto.BoardCreateDto;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.global.domain.BaseTime;
import com.Insurance.hm.global.util.ClobHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.sql.Clob;
import java.sql.SQLException;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Lob
    private String content;


    @Builder
    public Board(String title, Employee employee, String content) {
        this.title = title;
        this.employee = employee;
        this.content = content;
    }

    public void update(BoardCreateDto boardCreateDto) {
        this.title = boardCreateDto.getTitle();
        this.content = boardCreateDto.getContent();
    }
}
