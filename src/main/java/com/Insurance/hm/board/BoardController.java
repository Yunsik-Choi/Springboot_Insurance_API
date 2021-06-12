package com.Insurance.hm.board;

import com.Insurance.hm.board.constants.BoardErrorConstants;
import com.Insurance.hm.board.constants.BoardResponseConstants;
import com.Insurance.hm.board.domain.Board;
import com.Insurance.hm.board.dto.BoardCreateDto;
import com.Insurance.hm.board.dto.BoardDetailDto;
import com.Insurance.hm.board.exception.OverFileSizeFileIndexError;
import com.Insurance.hm.board.service.BoardService;
import com.Insurance.hm.global.constants.GlobalConstants;
import com.Insurance.hm.global.domain.file.FileService;
import com.Insurance.hm.global.domain.file.MD5Generator;
import com.Insurance.hm.global.domain.file.SaveFileRequestDto;
import com.Insurance.hm.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/board")
public class BoardController {

    private final BoardService boardService;
    private final FileService fileService;

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
    public void writeBoard(MultipartHttpServletRequest request, BoardCreateDto boardCreateDto,
                           HttpServletResponse response) throws IOException {
        try{
            List<com.Insurance.hm.global.domain.file.File> newFileList = new ArrayList<>();
            Path path = Paths.get("");
            String projectUrl = path.toAbsolutePath().toString();
            String url = projectUrl + "/src/main/resources/static/file";
            String savePath = url + "/" + boardCreateDto.getEmployeeId().toString();
            if (!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdir();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            for(MultipartFile file : request.getFiles("file")) {
                String originFilename = file.getOriginalFilename();
                String filename = new MD5Generator(originFilename).toString();
                String filePath = savePath + "/" + filename;
                file.transferTo(new File(filePath));
                SaveFileRequestDto saveFileRequestDto = SaveFileRequestDto.builder()
                        .originFilename(originFilename)
                        .filename(filename)
                        .filePath(filePath)
                        .build();
                Long fileId = fileService.saveFile(saveFileRequestDto);
                com.Insurance.hm.global.domain.file.File findFile = fileService.getFile(fileId);
                newFileList.add(findFile);
            }
            boardCreateDto.setFileList(newFileList);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
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
    public void updateBoard(@PathVariable Long id,MultipartHttpServletRequest request,
                            BoardCreateDto boardCreateDto, HttpServletResponse response) throws IOException {
        try{
            List<com.Insurance.hm.global.domain.file.File> newFileList = new ArrayList<>();
            Path path = Paths.get("");
            String projectUrl = path.toAbsolutePath().toString();
            String url = projectUrl + "/src/main/resources/static/file";
            String savePath = url + "/" + boardCreateDto.getEmployeeId().toString();
            if (!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdir();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            for(MultipartFile file : request.getFiles("file")) {
                String originFilename = file.getOriginalFilename();
                String filename = new MD5Generator(originFilename).toString();
                String filePath = savePath + "/" + filename;
                file.transferTo(new File(filePath));
                SaveFileRequestDto saveFileRequestDto = SaveFileRequestDto.builder()
                        .originFilename(originFilename)
                        .filename(filename)
                        .filePath(filePath)
                        .build();
                Long fileId = fileService.saveFile(saveFileRequestDto);
                com.Insurance.hm.global.domain.file.File findFile = fileService.getFile(fileId);
                newFileList.add(findFile);
            }
            boardService.findById(id).clearFileList();
            List<com.Insurance.hm.global.domain.file.File> fileList = boardService.findById(id).getFileList();
            fileList.stream().forEach(i -> fileService.deleteFile(i.getId()));
            boardCreateDto.setFileList(newFileList);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Long updateId = boardService.update(id, boardCreateDto);
        response.sendRedirect(updateId.toString());
    }

    @GetMapping("{id}/download/{fileIndex}")
    public ResponseEntity<Resource> fileDownload(@PathVariable(value = "id") Long id,
                                                 @PathVariable(value = "fileIndex") int fileIndex) throws IOException{
        Board findBoard = boardService.findById(id);
        System.out.println(findBoard.getFileList().size());
        if(fileIndex>=findBoard.getFileList().size())
            throw new OverFileSizeFileIndexError(BoardErrorConstants.OVER_FILE_INDEX);
        com.Insurance.hm.global.domain.file.File file = findBoard.getFileList().get(fileIndex);
        Path path = Paths.get(file.getFilePath());
        Resource resource = new InputStreamResource(Files.newInputStream(path),"UTF-8");
        String originFileName = new String(file.getOriginFilename().getBytes("UTF-8"), "ISO-8859-1");
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+originFileName+"\"")
                .body(resource);
    }

}
