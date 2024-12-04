package org.hikinonymous.back.cms.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.FileDto;
import org.hikinonymous.back.core.dto.ManagerDto;
import org.hikinonymous.back.core.dto.ResponseDto;
import org.hikinonymous.back.core.entity.FileInfoEntity;
import org.hikinonymous.back.core.exception.BadRequestException;
import org.hikinonymous.back.core.service.FileService;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.hikinonymous.back.core.utils.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "FILE", description = "FILE API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/common/file/")
@RequiredArgsConstructor
public class FileMgmtController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final FileService fileService;

    @Operation(
            summary = "파일 상세 조회",
            description = "파일을 상세 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "{type}/{fileInfoSeq}")
    public ResponseEntity handle(
            HttpServletRequest request,
            @PathVariable(name = "type") @Parameter(
                    name = "type",
                    description = "view / down"
            )
            String type,
            @PathVariable(name = "fileInfoSeq") @Parameter(
                    name = "fileInfoSeq",
                    description = "파일 SEQ"
            ) Long fileInfoSeq
    ) {
        if (type.equals("view")) {
            return fileService.getFileForViewById(fileInfoSeq);
        } else if (type.equals("down")) {
            return fileService.getFileForDownById(fileInfoSeq);
        } else {
            throw new BadRequestException();
        }
    }

}
