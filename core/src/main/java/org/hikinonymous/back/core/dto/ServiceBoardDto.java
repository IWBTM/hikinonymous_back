package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hikinonymous.back.core.entity.FileInfoEntity;
import org.hikinonymous.back.core.entity.ServiceBoardEntity;

import java.util.Objects;

@Schema(
        description = "서비스 게시글 DTO"
)
@Data
public class ServiceBoardDto extends CommonDto {

    @Schema(description = "게시글 SEQ")
    private Long serviceBoardSeq;

    @Schema(description = "제목")
    @NotBlank(message = "제목")
    private String title;

    @Schema(description = "요약")
    private String summary;

    @Schema(description = "내용")
    @NotBlank(message = "내용")
    private String content;

    @Schema(description = "PC 썸네일 이미지")
    private FileInfoEntity pcThumbImage;

    @Schema(description = "PC 썸네일 이미지")
    private FileInfoEntity moThumbImage;

    @Schema(description = "PC 메인 이미지")
    private FileInfoEntity pcMainImage;

    @Schema(description = "MO 메인 이미지")
    private FileInfoEntity moMainImage;

    @Schema(description = "서비스 게시글 타입")
    private CodeDto serviceBoardType;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "사용 여부")
    @NotBlank(message = "사용 여부")
    private String useYn;

    public ServiceBoardEntity bindToEntityForProc(ServiceBoardEntity serviceBoardEntity) {
        serviceBoardEntity.setServiceBoardSeq(serviceBoardSeq);
        serviceBoardEntity.setTitle(title);
        serviceBoardEntity.setSummary(summary);
        serviceBoardEntity.setContent(content);
        serviceBoardEntity.setPcThumbImage(pcThumbImage);
        serviceBoardEntity.setMoThumbImage(moThumbImage);
        serviceBoardEntity.setPcMainImage(pcMainImage);
        serviceBoardEntity.setMoMainImage(moMainImage);
        serviceBoardEntity.setUseYn(useYn);
        serviceBoardEntity.setDelYn("N");
        if (Objects.isNull(serviceBoardSeq)) {
            serviceBoardEntity.setRegDate(getRegDate());
            serviceBoardEntity.setRegister(getRegister());
            serviceBoardEntity.setRegisterIp(getRegisterIp());
        } else {
            serviceBoardEntity.setRegDate(serviceBoardEntity.getRegDate());
            serviceBoardEntity.setRegister(serviceBoardEntity.getRegister());
            serviceBoardEntity.setRegisterIp(serviceBoardEntity.getRegisterIp());
            serviceBoardEntity.setUpdDate(getUpdDate());
            serviceBoardEntity.setUpdater(getUpdater());
            serviceBoardEntity.setUpdaterIp(getUpdaterIp());
        }
        return serviceBoardEntity;
    }
}
