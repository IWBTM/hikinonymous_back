package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hikinonymous.back.core.entity.InquiryEntity;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.EncUtil;

@Schema(
        description = "문의 DTO"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InquirySimpleDto extends CommonMemberDto {

    @Schema(description = "문의 SEQ")
    private Long inquirySeq;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "문의 타입")
    private CodeDto inquiryType;

    @Schema(description = "확인 여부")
    private String readYn;

    @Schema(description = "답장 여부")
    private String answerYn;

    public static InquirySimpleDto bindToDto(InquiryEntity inquiryEntity) {
        InquirySimpleDto inquirySimpleDto = new InquirySimpleDto();
        inquirySimpleDto.setInquirySeq(inquiryEntity.getInquirySeq());
        inquirySimpleDto.setTitle(inquiryEntity.getTitle());
        inquirySimpleDto.setInquiryType(CodeDto.bindToDto(inquiryEntity.getInquiryType()));
        inquirySimpleDto.setReadYn(inquiryEntity.getReadYn());
        inquirySimpleDto.setAnswerYn(inquiryEntity.getAnswerYn());
        inquirySimpleDto.setRegisterNm(EncUtil.decryptAES256(inquiryEntity.getRegister().getMemberName()));
        inquirySimpleDto.setRegDate(CommonUtil.getDayByStrDate(inquiryEntity.getRegDate()));
        return inquirySimpleDto;
    }
}
