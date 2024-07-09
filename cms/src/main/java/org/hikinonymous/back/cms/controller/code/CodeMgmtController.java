package org.hikinonymous.back.cms.controller.code;

import ch.qos.logback.core.util.StringUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.*;
import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.hikinonymous.back.core.entity.CodeEntity;
import org.hikinonymous.back.core.entity.CodeMasterEntity;
import org.hikinonymous.back.core.service.CmsMenuService;
import org.hikinonymous.back.core.service.CodeMasterService;
import org.hikinonymous.back.core.service.CodeService;
import org.hikinonymous.back.core.service.ManagerLogService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Tag(name = "CODE MANAGEMENT", description = "CODE MANAGEMENT API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/code/code/")
@RequiredArgsConstructor
public class CodeMgmtController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CodeService codeService;

    private final CodeMasterService codeMasterService;

    private final ManagerLogService managerLogService;

    private final String MENU_NAME = "코드";

    @Operation(
            summary = MENU_NAME + " 리스트 조회",
            description = MENU_NAME + " 리스트를 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "list")
    public ResponseDto list(
            HttpServletRequest request,
            @PageableDefault Pageable pageable,
            @RequestParam(
                    name = "codeMaster",
                    required = false
            ) @Parameter(
                    name = "codeMaster",
                    description = "코드 MASTER"
            ) String codeMaster
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        managerLogService.proc(request, MENU_NAME + " 리스트", "R",  manager);


        if (StringUtil.isNullOrEmpty(codeMaster)) {
            Page<CodeMasterEntity> codeMasterEntityPages = codeMasterService.paging(pageable);
            if (!Objects.isNull(codeMasterEntityPages) && !codeMasterEntityPages.isEmpty()) {
                responseDto.setData(codeMasterEntityPages.map(CodeMasterDto::bindToDto
                ));
            }
        } else {
            Page<CodeEntity> codeEntityPages = codeService.paging(pageable, codeMaster);
            if (!Objects.isNull(codeEntityPages) && !codeEntityPages.isEmpty()) {
                responseDto.setData(codeEntityPages.map(CodeDto::bindToDto
                ));
            }
        }
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = MENU_NAME + " 저장",
            description = MENU_NAME + " 정보를 저장한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @PostMapping(value = "code/proc")
    public ResponseDto codeProc(
            HttpServletRequest request,
            @RequestBody(required = false) @Valid CodeDto codeDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);

        String behaviorType;
        if (Objects.isNull(codeDto.getCodeSeq())) behaviorType = "C";
        else behaviorType = "U";
        managerLogService.proc(request, MENU_NAME + " 정보", behaviorType,  manager);

        codeService.proc(codeDto);
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = MENU_NAME + " 마스터 저장",
            description = MENU_NAME + " 마스터 정보를 저장한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @PostMapping(value = "codeMaster/proc")
    public ResponseDto codeMasterProc(
            HttpServletRequest request,
            @RequestBody(required = false) @Valid CodeMasterDto codeMasterDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);

        String behaviorType;
        if (Objects.isNull(codeMasterDto.getCodeMaster())) behaviorType = "C";
        else behaviorType = "U";
        managerLogService.proc(request, MENU_NAME + " 마스터 정보", behaviorType,  manager);

        codeMasterService.proc(codeMasterDto);
        return ResponseUtil.success(responseDto);
    }

}
