package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hikinonymous.back.core.entity.FileInfoEntity;

import java.util.Objects;

@Schema(
        name = "파일 DTO"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDto extends CommonManagerDto {

    private Long fileInfoSeq;

    private CodeDto deviceType;

    private Integer sort;

    private CodeDto refType;

    private String filePath;

    private String fileApiViewPath;

    private String fileApiDownPath;

    private String fileNm;

    private String fileOriNm;

    private Long fileSize;

    private String fileExt;

    public static FileDto bindToDtoForView(FileInfoEntity fileInfoEntity) {
        if (Objects.isNull(fileInfoEntity)) return null;
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
        fileDto.setFileApiViewPath("/cms/common/file/view/");
        fileDto.setFileApiDownPath("/cms/common/file/down/");
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
