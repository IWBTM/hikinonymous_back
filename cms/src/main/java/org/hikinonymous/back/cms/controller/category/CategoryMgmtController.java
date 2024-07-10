package org.hikinonymous.back.cms.controller.category;

import ch.qos.logback.core.util.StringUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hikinonymous.back.core.dto.CategoryDto;
import org.hikinonymous.back.core.dto.CmsMenuDto;
import org.hikinonymous.back.core.dto.ManagerDto;
import org.hikinonymous.back.core.dto.ResponseDto;
import org.hikinonymous.back.core.entity.CategoryEntity;
import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.hikinonymous.back.core.service.CategoryService;
import org.hikinonymous.back.core.service.CmsMenuService;
import org.hikinonymous.back.core.service.ManagerLogService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.EncUtil;
import org.hikinonymous.back.core.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Tag(name = "CATEGORY MENU MANAGEMENT", description = "CATEGORY MENU MANAGEMENT API DOC")
@Slf4j
@RestController
@RequestMapping(value = "/cms/category/category/")
@RequiredArgsConstructor
public class CategoryMgmtController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CategoryService categoryService;

    private final ManagerLogService managerLogService;

    private final String MENU_NAME = "카테고리";

    @Operation(
            summary = MENU_NAME + " 리스트 조회",
            description = MENU_NAME + " 리스트를 조회한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @GetMapping(value = "list/{categoryLevel}")
    public ResponseDto list(
            HttpServletRequest request,
            @PageableDefault(size = 100) Pageable pageable,
            @PathVariable(
                    name = "categoryLevel",
                    required = false
            ) @Parameter(
                    name = "categoryLevel",
                    description = "카테고리 레벨"
            ) Integer categoryLevel
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");

        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);
        managerLogService.proc(request, MENU_NAME + " 리스트", "R",  manager);

        Page<CategoryEntity> categoryEntityPages = categoryService.paging(pageable, categoryLevel);
        responseDto.setData(categoryEntityPages.map(cmsMenuEntity -> {
                CategoryDto categoryDto = (CategoryDto) CommonUtil.bindToObjectFromObject(cmsMenuEntity, CategoryDto.class);
                categoryDto.setRegisterNm(EncUtil.decryptAES256(categoryDto.getRegisterNm()));
                categoryDto.setRegDate(CommonUtil.getDayByStrDate(categoryDto.getRegDate()));
                return categoryDto;
        }));
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = MENU_NAME + " 저장",
            description = MENU_NAME + " 정보를 저장한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @PostMapping(value = "proc")
    public ResponseDto proc(
            HttpServletRequest request,
            @RequestBody @Valid CategoryDto categoryDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);

        String behaviorType;
        if (Objects.isNull(categoryDto.getCategorySeq())) behaviorType = "C";
        else behaviorType = "U";
        managerLogService.proc(request, MENU_NAME + " 정보", behaviorType,  manager);

        CommonUtil.setManagerInfo(request, categoryDto, manager);
        categoryService.proc(categoryDto);
        return ResponseUtil.success(responseDto);
    }

    @Operation(
            summary = MENU_NAME + " 순서 저장",
            description = MENU_NAME + " 순서를 저장한다."
    )
    @ApiResponse(
            description = "응답 에러 코드 DOC 참고"
    )
    @PostMapping(value = "updateOrderSort")
    public ResponseDto updateOrderSort(
            HttpServletRequest request,
            @RequestBody @Valid List<CategoryDto> categoryDtoList
    ) {
        ResponseDto responseDto = new ResponseDto();
        ManagerDto manager = (ManagerDto) request.getAttribute("manager");
        if (Objects.isNull(manager)) return ResponseUtil.canNotFoundManager(responseDto);

        managerLogService.proc(request, MENU_NAME + "정렬 순서", "U",  manager);

        if (Objects.isNull(categoryDtoList) || categoryDtoList.isEmpty()) return ResponseUtil.emptyRequestBody(responseDto);

        categoryService.updateOrderSort(request, categoryDtoList, manager);
        return ResponseUtil.success(responseDto);
    }
}
