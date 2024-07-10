package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hikinonymous.back.core.entity.BoardEntity;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.EncUtil;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Objects;

@Schema(
        description = "게시글 DTO"
)
@Data
public class BoardDto extends CommonMemberDto {

    @Schema(description = "게시글 SEQ")
    private Long boardSeq;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "내용")
    private String content;

    @Schema(description = "게시글 파일 리스트 dto")
    private List<BoardFileDto> boardFileDtoList;

    @Schema(description = "카테고리 dto")
    private CategoryDto category;

    @Schema(description = "조회수")
    private Long viewCnt;

    @Schema(description = "삭제 여부")
    private String delYn;

    public static BoardDto bindToDtoForView(BoardEntity boardEntity) {
        BoardDto boardDto = new BoardDto();
        boardDto.setBoardSeq(boardEntity.getBoardSeq());
        boardDto.setTitle(boardEntity.getTitle());
        boardDto.setContent(boardEntity.getContent());
        boardDto.setBoardFileDtoList(BoardFileDto.bindToDtoList(boardEntity.getBoardFiles()));
        boardDto.setViewCnt(boardEntity.getViewCnt());
        boardDto.setCategory((CategoryDto) CommonUtil.bindToObjectFromObject(boardEntity.getCategory(), CategoryDto.class));
        boardDto.setRegDate(CommonUtil.getDayByStrDate(boardEntity.getRegDate()));
        boardDto.setRegister(boardEntity.getRegister());
        boardDto.setRegisterNm(EncUtil.decryptAES256(boardEntity.getRegister().getMemberName()));
        boardDto.setRegisterNickName(boardEntity.getRegister().getMemberNickName());
        boardDto.setRegisterIp(boardEntity.getRegisterIp());
        if (!Objects.isNull(boardEntity.getUpdater())) {
            boardDto.setUpdDate(boardEntity.getUpdDate());
            boardDto.setUpdaterIp(boardEntity.getRegisterIp());
        }

        return boardDto;
    }
}
