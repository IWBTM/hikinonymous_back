package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hikinonymous.back.core.entity.ServiceBoardEntity;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.EncUtil;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Schema(
        description = "서비스 게시글 DTO"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceBoardDto extends CommonManagerDto {

    @Schema(description = "게시글 SEQ")
    private Long serviceBoardSeq;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "요약")
    private String summary;

    @Schema(description = "내용")
    private String content;

    @Schema(description = "PC 썸네일 이미지")
    private FileDto pcThumbImage;

    @Schema(description = "PC 썸네일 이미지")
    private FileDto moThumbImage;

    @Schema(description = "PC 메인 이미지")
    private FileDto pcMainImage;

    @Schema(description = "MO 메인 이미지")
    private FileDto moMainImage;

    @Schema(description = "업로드 시 PC 썸네일 이미지")
    private MultipartFile pcThumbImageFile;

    @Schema(description = "업로드 시 MO 썸네일 이미지")
    private MultipartFile moThumbImageFile;

    @Schema(description = "업로드 시 PC 메인 이미지")
    private MultipartFile pcMainImageFile;

    @Schema(description = "업로드 시 MO 메인 이미지")
    private MultipartFile moMainImageFile;

    @Schema(description = "서비스 게시글 타입")
    private CodeDto serviceBoardType;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "사용 여부")
    private String useYn;

    public ServiceBoardEntity bindToEntityForProc(ServiceBoardEntity serviceBoardEntity) {
        serviceBoardEntity.setServiceBoardSeq(serviceBoardSeq);
        serviceBoardEntity.setTitle(title);
        serviceBoardEntity.setSummary(summary);
        serviceBoardEntity.setContent(content);
        serviceBoardEntity.setUseYn(useYn);
        serviceBoardEntity.setDelYn("N");
        if (Objects.isNull(serviceBoardSeq)) {
            serviceBoardEntity.setRegDate(getRegDate());
            serviceBoardEntity.setRegister(getRegister());
            serviceBoardEntity.setRegisterIp(getRegisterIp());
        } else {
            serviceBoardEntity.setUpdDate(getUpdDate());
            serviceBoardEntity.setUpdater(getUpdater());
            serviceBoardEntity.setUpdaterIp(getUpdaterIp());
        }
        return serviceBoardEntity;
    }

    public static ServiceBoardDto bindToDtoForView(ServiceBoardEntity serviceBoardEntity) {
        ServiceBoardDto serviceBoardDto = new ServiceBoardDto();
        serviceBoardDto.setServiceBoardSeq(serviceBoardEntity.getServiceBoardSeq());
        serviceBoardDto.setTitle(serviceBoardEntity.getTitle());
        serviceBoardDto.setSummary(serviceBoardEntity.getSummary());
        serviceBoardDto.setContent(serviceBoardEntity.getContent());
        serviceBoardDto.setUseYn(serviceBoardEntity.getUseYn());
        serviceBoardDto.setPcThumbImage(FileDto.bindToDtoForView(serviceBoardEntity.getPcThumbImage()));
        serviceBoardDto.setMoThumbImage(FileDto.bindToDtoForView(serviceBoardEntity.getMoThumbImage()));
        serviceBoardDto.setPcMainImage(FileDto.bindToDtoForView(serviceBoardEntity.getPcMainImage()));
        serviceBoardDto.setMoMainImage(FileDto.bindToDtoForView(serviceBoardEntity.getMoMainImage()));
        serviceBoardDto.setRegDate(CommonUtil.getDayByStrDate(serviceBoardEntity.getRegDate()));
        serviceBoardDto.setRegisterNm(EncUtil.decryptAES256(serviceBoardEntity.getRegister().getManagerNm()));
        serviceBoardDto.setRegisterIp(serviceBoardEntity.getRegisterIp());
        if (!Objects.isNull(serviceBoardEntity.getUpdater())) {
            serviceBoardDto.setUpdDate(CommonUtil.getDayByStrDate(serviceBoardEntity.getUpdDate()));
            serviceBoardDto.setUpdaterNm(EncUtil.decryptAES256(serviceBoardEntity.getUpdater().getManagerNm()));
            serviceBoardDto.setUpdaterIp(serviceBoardEntity.getUpdaterIp());
        }
        return serviceBoardDto;
    }
}
