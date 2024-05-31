package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.hikinonymous.back.core.repository.cmsMenu.CmsMenuRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
public class CmsMenuService {

    private final CmsMenuRepository cmsMenuRepository;

    public Stream<CmsMenuEntity> findAllByDisplayYnIsY(String displayYn) {
        return cmsMenuRepository.streamAllByDisplayYn(displayYn);
    }
}
