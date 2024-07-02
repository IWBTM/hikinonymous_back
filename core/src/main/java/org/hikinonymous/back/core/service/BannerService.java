package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.entity.BannerEntity;
import org.hikinonymous.back.core.repository.banner.BannerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;

    private final CodeService codeService;

    public Page<BannerEntity> paging(Pageable pageable) {
        return bannerRepository.findAll(pageable);
    }
}
