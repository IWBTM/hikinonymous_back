package org.hikinonymous.back.cms.controller.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.*;
import org.hikinonymous.back.core.entity.MemberEntity;
import org.hikinonymous.back.core.service.ManagerLogService;
import org.hikinonymous.back.core.service.MemberService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.EncUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.hikinonymous.back.core.utils.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Tag(name = "MEMBER MANAGEMENT MENU", description = "MEMBER MANAGEMENT MENU API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/member/")
@RequiredArgsConstructor
public class MemberMgmtController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MemberService memberService;

    private final ManagerLogService managerLogService;

    private final String MENU_NAME = "회원";

    @Operation(
            summary = MENU_NAME + " 리스트 조회",
            description = MENU_NAME + " 리스트를 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "{memberStatus}/list")
    public ResponseDto list(
            HttpServletRequest request,
            @PageableDefault Pageable pageable,
            @PathVariable(name = "memberStatus") @Parameter(
                    name = "memberStatus",
                    description = MENU_NAME + " 타입"
            ) String memberStatus
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = SecurityUtil.getCurrentManager(request);

        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        managerLogService.proc(request, MENU_NAME + " 리스트", "R",  manager);

        Page<MemberSimpleDto> pages = memberService.pagingByMemberStatus(memberStatus.toUpperCase(), pageable);
        responseDto.setData(pages.map(memberSimpleDto -> {
            memberSimpleDto.setMemberName(EncUtil.decryptAES256(memberSimpleDto.getMemberName()));
            memberSimpleDto.setMemberEmail(EncUtil.decryptAES256(memberSimpleDto.getMemberEmail()));

            memberSimpleDto.setLastLoginDate(CommonUtil.getDayByStrDate(memberSimpleDto.getLastLoginDate()));
            memberSimpleDto.setDropDate(CommonUtil.getDayByStrDate(memberSimpleDto.getDropDate()));
            memberSimpleDto.setRegDate(CommonUtil.getDayByStrDate(memberSimpleDto.getRegDate()));
            return memberSimpleDto;
        }));
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = MENU_NAME + " 상세 조회",
            description = MENU_NAME + "을 상세 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "{memberStatus}/view/{seq}")
    public ResponseDto view(
            HttpServletRequest request,
            @PathVariable(name = "seq") @Parameter(
                    name = "seq",
                    description = MENU_NAME + " SEQ"
            ) Long seq
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = SecurityUtil.getCurrentManager(request);

        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        managerLogService.proc(request, MENU_NAME + " 상세", "R",  manager);

        MemberDto memberDto = memberService.findDtoById(seq);
        memberDto.setMemberName(EncUtil.decryptAES256(memberDto.getMemberName()));
        memberDto.setMemberEmail(EncUtil.decryptAES256(memberDto.getMemberEmail()));
        memberDto.setMemberHp(EncUtil.decryptAES256(memberDto.getMemberHp()));

        memberDto.setLastLoginDate(CommonUtil.getDayByStrDate(memberDto.getLastLoginDate()));
        memberDto.setDropDate(CommonUtil.getDayByStrDate(memberDto.getDropDate()));
        memberDto.setRegDate(CommonUtil.getDayByStrDate(memberDto.getRegDate()));
        responseDto.setData(memberDto);
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = MENU_NAME + " 신고 횟수 초기화",
            description = MENU_NAME + "의 신고 횟수를 초기화한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @PostMapping(value = "{memberStatus}/updateReportCnt/{seq}")
    public ResponseDto updateReportCnt(
            HttpServletRequest request,
            @PathVariable(name = "seq") @Parameter(
                    name = "seq",
                    description = MENU_NAME + " SEQ"
            ) Long seq
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = SecurityUtil.getCurrentManager(request);
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        managerLogService.proc(request, MENU_NAME + " 신고 횟수", "U",  manager);

        memberService.updateReportCnt(seq);
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = MENU_NAME + " 정보 저장",
            description = MENU_NAME + " 정보를 저장한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @PostMapping(value = "{memberStatus}/proc")
    public ResponseDto proc(
            HttpServletRequest request,
            @RequestBody @Valid MemberUpdDto memberUpdDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = SecurityUtil.getCurrentManager(request);
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        managerLogService.proc(request, MENU_NAME + " 정보", "U",  manager);

        memberService.updateMemberStatusAndMemo(memberUpdDto);
        return ResponseUtil.success(responseDto);
    }

}
