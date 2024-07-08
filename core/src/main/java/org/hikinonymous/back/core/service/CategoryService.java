package org.hikinonymous.back.core.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.CategoryDto;
import org.hikinonymous.back.core.dto.CmsMenuDto;
import org.hikinonymous.back.core.dto.ManagerDto;
import org.hikinonymous.back.core.entity.BannerEntity;
import org.hikinonymous.back.core.entity.CategoryEntity;
import org.hikinonymous.back.core.repository.category.CategoryRepository;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public void proc(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = Optional
                .ofNullable(categoryDto.getCategorySeq())
                .flatMap(categoryRepository::findById)
                .orElseGet(CategoryEntity::new);
        categoryDto.setCategorySeq(categoryEntity.getCategorySeq());
        categoryDto.setDelYn("N");
        categoryRepository.save((CategoryEntity) CommonUtil.bindToObjectFromObject(categoryDto, CategoryEntity.class));
    }

    @Transactional
    public void updateOrderSort(HttpServletRequest request, List<CategoryDto> categoryDtoList, ManagerDto manager) {
        for (CategoryDto categoryDto : categoryDtoList) {
            CommonUtil.setClientInfo(request, categoryDto, manager);
            CategoryEntity categoryEntity = this.findById(categoryDto.getCategorySeq());
            categoryEntity.setSortOrder(categoryDto.getSortOrder());
            categoryRepository.save(categoryEntity);
        }
    }

    public CategoryEntity findById(Long seq) {
        return categoryRepository.findById(seq).orElseThrow(() ->
                new NoSuchElementException("Cms Menu Seq: " + seq + " not found")
        );
    }

    public Page<CategoryEntity> paging(Pageable pageable, Integer categoryLevel) {
        return categoryRepository.findAllByCategoryLevelAndDelYn(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()), categoryLevel, "N");
    }

}
