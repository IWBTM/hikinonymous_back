package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
        description = "회원 DTO"
)
@Data
public class MemberDto extends CommonManagerDto {

    @Schema(description = "회원 SEQ")
    private Long memberSeq;

    @Schema(description = "회원 이름")
    private String memberName;

    @Schema(description = "회원 별칭")
    private String memberNickName;

    @Schema(description = "회원 이메일")
    private String memberEmail;

    @Schema(description = "회원 연락처")
    private String memberHp;

    @Schema(description = "회원 상태")
    private CodeDto memberStatus;

    @Schema(description = "성별")
    private String gender;

    @Schema(description = "관리자 메모")
    private String memo;

    @Schema(description = "신고 누적 횟수")
    private Integer reportCnt;

    @Schema(description = "마지막 로그인일")
    private String lastLoginDate;

    @Schema(description = "탈퇴일")
    private String dropDate;

    @Schema(description = "가입 타입")
    private CodeDto joinType;

    @Schema(description = "작성한 게시글 수")
    private Long totalBoardCnt;

    @Schema(description = "작성한 댓글 수")
    private Long totalReplyCnt;

    @Schema(description = "개인정보보호 동의 여부")
    private String privacyYn;

    @Schema(description = "이메일 머시기 동의 여부")
    private String receiveAdsEmailYn;

}
