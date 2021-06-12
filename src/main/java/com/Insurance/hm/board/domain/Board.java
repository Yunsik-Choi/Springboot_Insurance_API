package com.Insurance.hm.board.domain;

import com.Insurance.hm.board.dto.BoardCreateDto;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.global.domain.BaseTime;
import com.Insurance.hm.global.domain.file.File;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private File file;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private String content;


    @Builder
    public Board(String title, Employee employee, String content, File file) {
        this.title = title;
        this.employee = employee;
        this.content = content;
        this.file = file;
    }

    public void update(BoardCreateDto boardCreateDto) {
        this.title = boardCreateDto.getTitle();
        this.content = boardCreateDto.getContent();
        this.file = boardCreateDto.getFile();
    }
}
