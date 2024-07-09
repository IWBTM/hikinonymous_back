package org.hikinonymous.back.core.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.CmsMenuDto;
import org.hikinonymous.back.core.dto.ManagerDto;
import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.hikinonymous.back.core.repository.cmsMenu.CmsMenuRepository;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ServerErrorException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
public class CmsMenuService {

    private final CmsMenuRepository cmsMenuRepository;

    @Transactional
    public Stream<CmsMenuEntity> streamAllByDisplayYnAndDelYnOrderBySortOrder(String displayYn, String delYn) {
        return cmsMenuRepository.streamAllByDisplayYnAndDelYnOrderBySortOrder(displayYn, delYn);
    }

    public List<CmsMenuDto> findAllByManagerSeq(Long managerSeq) {
        return cmsMenuRepository.findAllByManagerSeq(managerSeq);
    }

    @Transactional
    public CmsMenuEntity findByCmsMenuSeq(Long seq) {
        return cmsMenuRepository.findByCmsMenuSeq(seq).orElseThrow(() ->
                new ServerErrorException("Cms Menu Seq: " + seq + " not found", null)
        );
    }

    @Transactional
    public void proc(CmsMenuDto cmsMenuDto) {
        CmsMenuEntity cmsMenuEntity = cmsMenuRepository.findByCmsMenuSeq(cmsMenuDto.getCmsMenuSeq()).orElseGet(() ->
                new CmsMenuEntity()
        );
        cmsMenuDto.setCmsMenuSeq(cmsMenuEntity.getCmsMenuSeq());
        cmsMenuRepository.save((CmsMenuEntity) CommonUtil.bindToObjectFromObject(cmsMenuDto, CmsMenuEntity.class));
    }

    @Transactional
    public void updateOrderSort(HttpServletRequest request, List<CmsMenuDto> cmsMenuDtoList, ManagerDto manager) {
        for (CmsMenuDto cmsMenuDto: cmsMenuDtoList) {
            CommonUtil.setClientInfo(request, cmsMenuDto, manager);
            CmsMenuEntity cmsMenuEntity = cmsMenuRepository.findByCmsMenuSeq(cmsMenuDto.getCmsMenuSeq()).orElseThrow(() ->
                    new NoSuchElementException("Cms Menu Seq: " + cmsMenuDto.getCmsMenuSeq() + " not found")
            );

            cmsMenuEntity.setSortOrder(cmsMenuDto.getSortOrder());
            cmsMenuRepository.save(cmsMenuEntity);
        }
    }

    public Page<CmsMenuEntity> paging(Pageable pageable) {
        return cmsMenuRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()));
    }

    public Page<CmsMenuEntity> pagingByMenuLevel(Pageable pageable, Integer menuLevel) {
        return cmsMenuRepository.findAllByMenuLevel(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()), menuLevel);
    }

    public Page<CmsMenuEntity> pagingByMenuLevelAndAuthDir(Pageable pageable, Integer menuLevel, String authDir) {
        return cmsMenuRepository.findAllByMenuLevelAndAuthDir(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()), menuLevel, authDir);
    }

    public List<CmsMenuEntity> findAllByAuthDirAndMenuLevel(String authDir, Integer menuLevel) {
        return cmsMenuRepository.findAllByAuthDirAndMenuLevel(authDir, menuLevel);
    }

}
