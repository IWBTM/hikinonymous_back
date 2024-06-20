package org.hikinonymous.back.cms.controller.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.*;
import org.hikinonymous.back.core.entity.BoardEntity;
import org.hikinonymous.back.core.entity.MemberEntity;
import org.hikinonymous.back.core.service.MemberService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Tag(name = "MEMBER MANAGEMENT MENU", description = "MEMBER MANAGEMENT MENU API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/member/")
@RequiredArgsConstructor
public class MemberMgmtController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MemberService memberService;

    @Operation(
            summary = "회원 리스트 조회",
            description = "회원 리스트를 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "{memberStatus}/list")
    public ResponseDto list(
            HttpServletRequest request,
            @PathVariable @Parameter(
                    name = "memberStatus",
                    description = "회원 타입"
            ) String memberStatus
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        List<MemberEntity> memberEntities = memberService.findAllByMemberStatus(memberStatus);
        responseDto.setData(memberEntities.stream().map(memberEntity ->
            CommonUtil.bindToObjectFromObject(memberEntity, MemberSimpleDto.class)
        ));
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = "회원 상세 조회",
            description = "회원을 상세 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "{memberStatus}/view/{seq}")
    public ResponseDto view(
            HttpServletRequest request,
            @PathVariable @Parameter(
                    name = "seq",
                    description = "회원 SEQ"
            ) Long seq
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        responseDto.setData(CommonUtil.bindToObjectFromObject(memberService.findById(seq), MemberDto.class));
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = "회원 신고 횟수 초기화",
            description = "회원의 신고 횟수를 초기화한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @PostMapping(value = "{memberStatus}/updateReportCnt")
    public ResponseDto updateReportCnt(
            HttpServletRequest request,
            @RequestBody @Valid MemberDto memberDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        if (Objects.isNull(memberDto)) return ResponseUtil.emptyRequestBody(responseDto);

        CommonUtil.setClientInfo(request, memberDto, manager);
        memberService.updateReportCnt(memberDto);
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = "회원 상태 저장",
            description = "회원 상태를 저장한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @PostMapping(value = "{memberStatus}/updateMemberStatus")
    public ResponseDto updateMemberStatus(
            HttpServletRequest request,
            @RequestBody @Valid MemberDto memberDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        if (Objects.isNull(memberDto)) return ResponseUtil.emptyRequestBody(responseDto);

        CommonUtil.setClientInfo(request, memberDto, manager);
        memberService.updateMemberStatus(memberDto);
        return ResponseUtil.success(responseDto);
    }

}
