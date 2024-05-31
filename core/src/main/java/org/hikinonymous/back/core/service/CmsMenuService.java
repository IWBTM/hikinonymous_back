package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.CmsMenuDto;
import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.hikinonymous.back.core.repository.cmsMenu.CmsMenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

}
