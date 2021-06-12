package com.Insurance.hm.global.domain.file;

import com.Insurance.hm.board.domain.Board;
import com.Insurance.hm.global.domain.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @Column(nullable = false, name = "origin_filename")
    private String originFilename;

    @Column(nullable = false, name = "filename")
    private String filename;

    @Column(nullable = false, name = "file_path")
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "board_id",insertable = false, updatable = false)
    private Board board;

    @Builder
    public File(String originFilename, String filename, String filePath) {
        this.originFilename = originFilename;
        this.filename = filename;
        this.filePath = filePath;
    }
}
