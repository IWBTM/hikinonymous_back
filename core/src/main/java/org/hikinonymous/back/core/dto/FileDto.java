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

    private String fileNm;

    private String fileOriNm;

    private Long fileSize;

    private String fileExt;

}