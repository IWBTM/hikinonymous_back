package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hikinonymous.back.core.entity.InquiryFileEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Schema(
        description = "문의 파일 DTO"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InquiryFileDto {

    @Schema(description = "문의 파일 SEQ")
    private Long inquiryFileSeq;

    @Schema(description = "파일 dto")
    private FileDto fileDto;

    public static List<InquiryFileDto> bindToDtoList(List<InquiryFileEntity> inquiryFileEntities) {
        List<InquiryFileDto> inquiryFileDtoList = new ArrayList<>();
        if (!Objects.isNull(inquiryFileEntities) && !inquiryFileEntities.isEmpty()) {
            inquiryFileEntities.forEach(inquiryFileEntity -> {
                InquiryFileDto inquiryFileDto = new InquiryFileDto();
                inquiryFileDto.setInquiryFileSeq(inquiryFileEntity.getInquiryFileSeq());
                inquiryFileDto.setFileDto(FileDto.bindToDtoForView(inquiryFileEntity.getFileInfo()));
                inquiryFileDtoList.add(inquiryFileDto);
            });
        }
        return inquiryFileDtoList;
    }
}
