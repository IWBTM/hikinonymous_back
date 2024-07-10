package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hikinonymous.back.core.entity.InquiryEntity;
import org.hikinonymous.back.core.entity.InquiryFileEntity;
import org.hikinonymous.back.core.entity.ManagerEntity;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.EncUtil;

import java.util.List;

@Schema(
        description = "문의 DTO"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InquiryDto extends CommonMemberDto {

    @Schema(description = "문의 SEQ")
    private Long inquirySeq;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "내용")
    private String content;

    @Schema(description = "문의 파일 dto")
    private List<InquiryFileDto> inquiryFileDtoList;

    @Schema(description = "문의 타입")
    private CodeDto inquiryType;

    @Schema(description = "확인 여부")
    private String readYn;

    @Schema(description = "답장 여부")
    private String answerYn;

    @Schema(description = "답장")
    private String answer;

    @Schema(description = "답장자")
    private ManagerEntity answerer;
    
    @Schema(description = "답장자 이름")
    private String answererNm;

    @Schema(description = "답장일")
    private String answerDate;

    @Schema(description = "답장자 IP")
    private String answererIp;

    public static InquiryDto bindToDtoForView(InquiryEntity inquiryEntity) {
        InquiryDto inquiryDto = new InquiryDto();
        inquiryDto.setInquirySeq(inquiryEntity.getInquirySeq());
        inquiryDto.setTitle(inquiryEntity.getTitle());
        inquiryDto.setContent(inquiryEntity.getContent());
        inquiryDto.setReadYn(inquiryEntity.getReadYn());
        inquiryDto.setInquiryType(CodeDto.bindToDto(inquiryEntity.getInquiryType()));
        inquiryDto.setReadYn(inquiryEntity.getReadYn());
        inquiryDto.setInquiryFileDtoList(InquiryFileDto.bindToDtoList(inquiryEntity.getInquiryFile()));
        inquiryDto.setRegisterNm(EncUtil.decryptAES256(inquiryEntity.getRegister().getMemberName()));
        inquiryDto.setRegDate(CommonUtil.getDayByStrDate(inquiryEntity.getRegDate()));

        inquiryDto.setAnswerYn(inquiryEntity.getAnswerYn());
        if (inquiryEntity.getAnswerYn().equals("Y")) {
            inquiryDto.setAnswer(inquiryEntity.getAnswer());
            inquiryDto.setAnswererNm(EncUtil.decryptAES256(inquiryEntity.getAnswerer().getManagerNm()));
            inquiryDto.setAnswerDate(CommonUtil.getDayByStrDate(inquiryEntity.getAnswerDate()));
        }
        return inquiryDto;
    }

    public InquiryEntity bindToEntityForProc(InquiryEntity serviceBoardEntity) {
        return serviceBoardEntity;
    }
}
