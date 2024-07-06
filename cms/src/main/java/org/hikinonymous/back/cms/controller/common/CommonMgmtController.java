package org.hikinonymous.back.cms.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.*;
import org.hikinonymous.back.core.entity.CodeEntity;
import org.hikinonymous.back.core.service.*;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "COMMON MENU", description = "COMMON MENU API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/common/")
@RequiredArgsConstructor
public class CommonMgmtController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CodeService codeService;

    @Operation(
            summary = "공통 코드 리스트 조회",
            description = "코드 마스터로 공통 코드 리스트를 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "codeListByCodeMaster/{codeMaster}")
    public ResponseDto view(
            HttpServletRequest request,
            @PathVariable(name = "codeMaster") @Parameter(
                    name = "codeMaster",
                    description = "코드 마스터"
            ) String codeMaster
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        List<CodeEntity> codeEntities = codeService.findByCodeMaster(codeMaster);
        responseDto.setData(codeEntities.stream().map(codeEntity -> CommonUtil.bindToObjectFromObject(codeEntity, CodeDto.class)));
        return ResponseUtil.success(responseDto);
    }

}
