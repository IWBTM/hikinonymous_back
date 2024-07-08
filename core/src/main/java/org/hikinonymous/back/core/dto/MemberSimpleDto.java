package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
        description = "회원 리스트용 DTO"
)
@Data
public class MemberSimpleDto {

    @Schema(description = "회원 SEQ")
    private Long memberSeq;

    @Schema(description = "회원 이름")
    private String memberName;

    @Schema(description = "회원 별칭")
    private String memberNickName;

    @Schema(description = "회원 이메일")
    private String memberEmail;

    @Schema(description = "회원 상태")
    private CodeDto memberStatus;

    @Schema(description = "신고 누적 횟수")
    private Integer reportCnt;

    @Schema(description = "작성한 게시글 수")
    private Long totalBoardCnt;

    @Schema(description = "작성한 댓글 수")
    private Long totalReplyCnt;

    @Schema(description = "마지막 로그인일")
    private String lastLoginDate;

    @Schema(description = "탈퇴일")
    private String dropDate;

    @Schema(description = "가입일")
    private String regDate;

}
