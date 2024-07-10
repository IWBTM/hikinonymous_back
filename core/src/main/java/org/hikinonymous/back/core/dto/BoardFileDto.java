package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hikinonymous.back.core.entity.BoardFileEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Schema(
        description = "게시글 파일 DTO"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardFileDto {

    @Schema(description = "게시글 파일 SEQ")
    private Long boardFileSeq;

    @Schema(description = "파일 dto")
    private FileDto fileDto;

    public static List<BoardFileDto> bindToDtoList(List<BoardFileEntity> boardFileEntities) {
        List<BoardFileDto> boardFileDtoList = new ArrayList<>();
        if (!Objects.isNull(boardFileEntities) && !boardFileEntities.isEmpty()) {
            boardFileEntities.forEach(boardFileEntity -> {
                BoardFileDto boardFileDto = new BoardFileDto();
                boardFileDto.setBoardFileSeq(boardFileEntity.getBoardFileSeq());
                boardFileDto.setFileDto(FileDto.bindToDtoForView(boardFileEntity.getFileInfo()));
                boardFileDtoList.add(boardFileDto);
            });
        }
        return boardFileDtoList;
    }
}
