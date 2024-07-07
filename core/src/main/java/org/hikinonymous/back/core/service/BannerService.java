package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.BannerDelYnDto;
import org.hikinonymous.back.core.dto.BannerDto;
import org.hikinonymous.back.core.entity.BannerEntity;
import org.hikinonymous.back.core.repository.banner.BannerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;

    private final FileService fileService;

    private final CodeService codeService;

    public Page<BannerEntity> paging(Pageable pageable) {
        return bannerRepository.findAllByDelYn(pageable, "N");
    }

    public void proc(BannerDto bannerDto) {
        BannerEntity bannerEntity = Optional
                .ofNullable(bannerDto.getBannerSeq())
                .flatMap(bannerRepository::findById)
                .orElseGet(BannerEntity::new);

        String refType = "BANNER";
        if (!Objects.isNull(bannerDto.getPcImageFile()) && bannerDto.getPcImageFile().getSize() > 0) {
            bannerEntity.setPcImage(fileService.proc(bannerDto.getPcImageFile(), refType, "PC"));
        }
        if (!Objects.isNull(bannerDto.getMoImageFile()) && bannerDto.getMoImageFile().getSize() > 0) {
            bannerEntity.setMoImage(fileService.proc(bannerDto.getMoImageFile(), refType, "MO"));
        }

        bannerDto.setPosition(codeService.findByCodeSeq(bannerDto.getPositionSeq()));
        bannerEntity = bannerDto.bindToEntityForProc(bannerEntity);
        bannerRepository.save(bannerEntity);
    }

    public BannerEntity findById(Long bannerSeq) {
        BannerEntity bannerEntity = bannerRepository.findById(bannerSeq).orElseThrow(() ->
                new NoSuchElementException("Banner seq: " + bannerSeq + " not found")
        );
        return bannerEntity;
    }

    public void updateDelYn(BannerDelYnDto bannerDelYnDto) {
        BannerEntity bannerEntity = this.findById(bannerDelYnDto.getBannerSeq());
        bannerEntity.setDelYn(bannerDelYnDto.getDelYn());
        bannerRepository.save(bannerEntity);
    }
}
