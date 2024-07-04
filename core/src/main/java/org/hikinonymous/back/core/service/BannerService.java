package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.hikinonymous.back.core.dto.BannerDto;
import org.hikinonymous.back.core.entity.BannerEntity;
import org.hikinonymous.back.core.repository.banner.BannerRepository;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.EncUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;

    private final FileService fileService;

    private final CodeService codeService;

    public Page<BannerEntity> paging(Pageable pageable) {
        return bannerRepository.findAll(pageable);
    }

    public void proc(BannerDto bannerDto) {
        String refType = "BANNER";
        bannerDto.setPcImage(fileService.proc(bannerDto.getPcImageFile(), refType, "PC"));
        bannerDto.setMoImage(fileService.proc(bannerDto.getMoImageFile(), refType, "MO"));

        bannerDto.setPosition(codeService.findByCodeSeq(bannerDto.getPositionSeq()));
        BannerEntity bannerEntity = bannerDto.bindToEntityForProc();
        bannerRepository.save(bannerEntity);
    }

    public BannerEntity findById(Long bannerSeq) {
        BannerEntity bannerEntity = bannerRepository.findById(bannerSeq).orElseThrow(() ->
                new NoSuchElementException("Banner seq: " + bannerSeq + " not found")
        );
        return bannerEntity;
    }
}
