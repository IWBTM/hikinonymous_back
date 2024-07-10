package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hikinonymous.back.core.entity.ReplyEntity;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.EncUtil;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Schema(
        description = "댓글 DTO"
)
@Data
public class ReplyDto extends CommonMemberDto {

    @Schema(description = "댓글 SEQ")
    private Long replySeq;

    @Schema(description = "댓글 내용")
    private String content;

    public static Page<ReplyDto> bindToDtoForPage(Page<ReplyEntity> replyEntityPages) {
        Page<ReplyDto> replyDtoPages = replyEntityPages.map(replyEntity -> {
            ReplyDto replyDto = new ReplyDto();
            replyDto.setReplySeq(replyEntity.getReplySeq());
            replyDto.setContent(replyEntity.getContent());
            replyDto.setRegisterNm(EncUtil.decryptAES256(replyEntity.getRegister().getMemberName()));
            replyDto.setRegisterNickName(replyEntity.getRegister().getMemberNickName());
            replyDto.setRegDate(CommonUtil.getDayByStrDate(replyEntity.getRegDate()));
            return replyDto;
        });
        return replyDtoPages;
    }
}
