package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.CmsMenuDto;
import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.hikinonymous.back.core.repository.cmsMenu.CmsMenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ServerErrorException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
public class CmsMenuService {

    private final CmsMenuRepository cmsMenuRepository;

    @Transactional
    public Stream<CmsMenuEntity> streamAllByDisplayYn(String displayYn) {
        return cmsMenuRepository.streamAllByDisplayYn(displayYn);
    }

    public List<CmsMenuDto> findAllByManagerSeq(Long managerSeq) {
        return cmsMenuRepository.findAllByManagerSeq(managerSeq);
    }

    @Transactional
    public Stream<CmsMenuEntity> streamAllByDelYn(String delYn) {
        return cmsMenuRepository.streamAllByDelYn(delYn);
    }

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

        cmsMenuEntity.setMenuNm(cmsMenuDto.getMenuNm());
        cmsMenuEntity.setMenuCode(cmsMenuDto.getMenuCode());
        cmsMenuEntity.setMenuLevel(cmsMenuDto.getMenuLevel());
        cmsMenuEntity.setFilePath(cmsMenuDto.getFilePath());
        cmsMenuEntity.setAuthDir(cmsMenuDto.getAuthDir());
        cmsMenuEntity.setDisplayYn(cmsMenuDto.getDisplayYn());
        cmsMenuEntity.setEtc(cmsMenuDto.getEtc());
        cmsMenuEntity.setSortOrder(cmsMenuDto.getSortOrder());

        if (Objects.isNull(cmsMenuDto.getCmsMenuSeq())) cmsMenuEntity.setRegisterIp(cmsMenuDto.getRegisterIp());
        else cmsMenuEntity.setUpdaterIp(cmsMenuDto.getUpdaterIp());
    }
}
