package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hikinonymous.back.core.entity.BannerEntity;
import org.hikinonymous.back.core.entity.CodeEntity;
import org.hikinonymous.back.core.entity.FileInfoEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Schema(
        name = "파일 DTO"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDto extends CommonDto {

    private Long fileInfoSeq;

    private CodeDto deviceType;

    private Integer sort;

    private CodeDto refType;

    private String filePath;

    private String fileApiPath;

    private String fileNm;

    private String fileOriNm;

    private Long fileSize;

    private String fileExt;

    public static FileDto bindToDtoForView(FileInfoEntity fileInfoEntity) {
        FileDto fileDto = new FileDto();
        fileDto.setFileInfoSeq(fileInfoEntity.getFileInfoSeq());
        fileDto.setDeviceType(CodeDto.bindToDto(fileInfoEntity.getDeviceType()));
        fileDto.setRefType(CodeDto.bindToDto(fileInfoEntity.getRefType()));
        fileDto.setSort(fileInfoEntity.getSort());
        fileDto.setFilePath(fileInfoEntity.getFilePath());
        fileDto.setFileNm(fileInfoEntity.getFileNm());
        fileDto.setFileOriNm(fileInfoEntity.getFileOriNm());
        fileDto.setFileSize(fileInfoEntity.getFileSize());
        fileDto.setFileExt(fileInfoEntity.getFileExt());
        fileDto.setFileApiPath("/cms/common/file/view/");
        return fileDto;
    }

    public static FileDto bindToDtoForProc(FileInfoEntity fileInfoEntity) {
        if (Objects.isNull(fileInfoEntity)) return null;
        FileDto fileDto = new FileDto();
        fileDto.setFileInfoSeq(fileInfoEntity.getFileInfoSeq());
        return fileDto;
    }

    public static FileInfoEntity bindToEntityForProc(FileDto fileDto) {
        if (Objects.isNull(fileDto)) return null;
        FileInfoEntity fileInfoEntity = new FileInfoEntity();
        fileInfoEntity.setFileInfoSeq(fileDto.getFileInfoSeq());
        return fileInfoEntity;
    }
}
