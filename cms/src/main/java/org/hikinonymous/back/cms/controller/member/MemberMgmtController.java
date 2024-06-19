package org.hikinonymous.back.cms.controller.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.BoardDto;
import org.hikinonymous.back.core.dto.BoardSimpleDto;
import org.hikinonymous.back.core.dto.ManagerDto;
import org.hikinonymous.back.core.dto.ResponseDto;
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
            (BoardSimpleDto) CommonUtil.bindToObjectFromObjObject(memberEntity, BoardSimpleDto.class)
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
    @GetMapping(value = "{memberStatus}/view")
    public ResponseDto view(
            HttpServletRequest request,
            @PathVariable @Parameter(
                    name = "seq",
                    description = "회원 SEQ"
            ) Long seq
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        responseDto.setData((BoardDto) CommonUtil.bindToObjectFromObjObject(memberService.findById(seq), BoardDto.class));
        return ResponseUtil.success(responseDto);
    }

}
