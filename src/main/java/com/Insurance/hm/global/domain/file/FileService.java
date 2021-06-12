package com.Insurance.hm.global.domain.file;

import com.Insurance.hm.global.constants.GlobalConstants;
import com.Insurance.hm.global.constants.GlobalErrorConstants;
import com.Insurance.hm.global.exception.business.NonMatchIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public Long saveFile(SaveFileRequestDto saveFileRequestDto){
        File file = saveFileRequestDto.toEntity();
        File save = fileRepository.save(file);
        return save.getId();
    }

    public File getFile(Long id){
        File file = fileRepository.findById(id).orElseThrow(this::getNonMatchFile);
        return file;
    }

    private NonMatchIdException getNonMatchFile() {
        return new NonMatchIdException(GlobalErrorConstants.Non_Match_Id.setClassNameMessage("file"));

    }
}
