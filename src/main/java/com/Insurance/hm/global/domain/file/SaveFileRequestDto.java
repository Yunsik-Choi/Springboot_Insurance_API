package com.Insurance.hm.global.domain.file;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class SaveFileRequestDto {

    private String originFilename;
    private String filename;
    private String filePath;

    public File toEntity(){
        File file = File.builder()
                .originFilename(originFilename)
                .filename(filename)
                .filePath(filePath)
                .build();
        return file;
    }


}
