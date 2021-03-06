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
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id")
    private List<File> fileList;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private String content;


    @Builder
    public Board(String title, Employee employee, String content, List<File> fileList) {
        this.title = title;
        this.employee = employee;
        this.content = content;
        this.fileList = fileList;
    }

    public void update(BoardCreateDto boardCreateDto) {
        this.title = boardCreateDto.getTitle();
        this.content = boardCreateDto.getContent();
        this.fileList = boardCreateDto.getFileList();
    }

    public void clearFileList(){
        this.fileList.clear();
    }
}
