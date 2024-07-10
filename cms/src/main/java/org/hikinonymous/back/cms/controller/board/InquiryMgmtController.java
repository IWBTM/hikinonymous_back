package org.hikinonymous.back.cms.controller.board;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.*;
import org.hikinonymous.back.core.service.ManagerLogService;
import org.hikinonymous.back.core.service.InquiryService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Tag(name = "INQUIRY MANAGEMENT MENU", description = "INQUIRY MANAGEMENT MENU API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/inquiry/")
@RequiredArgsConstructor
public class InquiryMgmtController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final InquiryService inquiryService;

    private final ManagerLogService managerLogService;

    private final String MENU_NAME = "문의";

    @Operation(
            summary = MENU_NAME + " 리스트 조회",
            description = MENU_NAME + " 리스트를 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "{type}/list")
    public ResponseDto list(
            HttpServletRequest request,
            @PageableDefault Pageable pageable,
            @PathVariable(
                    name = "type",
                    required = false
            )
            @Parameter(
                    name = "type",
                    required = true,
                    description = "문의 타입"
            ) String type
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        managerLogService.proc(request, MENU_NAME + " 리스트", "R", manager);

        responseDto.setData(inquiryService.findAllByInquiryType(type, pageable).map(InquirySimpleDto::bindToDto));
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = MENU_NAME + " 상세 조회",
            description = MENU_NAME + "을 상세 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "{type}/view/{seq}")
    public ResponseDto view(
            HttpServletRequest request,
            @PathVariable(
                    name = "type",
                    required = false
            )
            @Parameter(
                    name = "type",
                    required = true,
                    description = "문의 타입"
            ) String type,
            @PathVariable(name = "seq") @Parameter(
                    name = "seq",
                    description = MENU_NAME + " SEQ"
            ) Long seq
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        managerLogService.proc(request, MENU_NAME + " 상세", "R", manager);

        inquiryService.updateReadYn(seq);
        responseDto.setData(InquiryDto.bindToDtoForView(inquiryService.findById(seq)));
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = MENU_NAME + " 답장 저장",
            description = MENU_NAME + " 답장을 저장한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @PostMapping(value = "{type}/updateAnswer")
    public ResponseDto updateAnswer(
            HttpServletRequest request,
            @RequestBody @Valid InquiryDto inquiryDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);

        managerLogService.proc(request, MENU_NAME + " 답장", "U", manager);

        CommonUtil.setInquiryAnswererInfo(request, inquiryDto, manager);
        inquiryService.updateAnswer(inquiryDto);
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = MENU_NAME + " 삭제 여부 수정",
            description = MENU_NAME + " 삭제 여부를 수정한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @PostMapping(value = "{type}/updateDelYn")
    public ResponseDto updateDelYn(
            HttpServletRequest request,
            @RequestBody @Valid InquiryDelYnDto inquiryDelYnDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);

        managerLogService.proc(request, MENU_NAME + " 삭제 여부", "U", manager);

        inquiryService.updateDelYn(inquiryDelYnDto);
        return ResponseUtil.success(responseDto);
    }
}
