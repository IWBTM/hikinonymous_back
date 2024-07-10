package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.ServiceBoardDelYnDto;
import org.hikinonymous.back.core.dto.ServiceBoardDto;
import org.hikinonymous.back.core.entity.BannerEntity;
import org.hikinonymous.back.core.entity.ServiceBoardEntity;
import org.hikinonymous.back.core.repository.serviceBoard.ServiceBoardRepository;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceBoardService {

    private final ServiceBoardRepository serviceBoardRepository;

    private final FileService fileService;

    private final CodeService codeService;

    public Page<ServiceBoardEntity> findAllByServiceBoardType(String serviceBoardType, Pageable pageable) {
        return serviceBoardRepository.findAllByServiceBoardTypeAndDelYn(codeService.findByCodeAndCodeMaster(serviceBoardType, "SERVICE_BOARD_TYPE"), PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()), "N");
    }

    public ServiceBoardEntity findById(Long seq) {
        return serviceBoardRepository.findById(seq).orElseThrow(() ->
                new NoSuchElementException("Service Board Seq: " + seq + " not found")
        );
    }

    public void proc(ServiceBoardDto serviceBoardDto) {
        ServiceBoardEntity serviceBoardEntity = Optional
                .ofNullable(serviceBoardDto.getServiceBoardSeq())
                .flatMap(serviceBoardRepository::findById)
                .orElseGet(ServiceBoardEntity::new);

        String refType = "FAQ";
        if (!Objects.isNull(serviceBoardDto.getPcThumbImageFile()) && serviceBoardDto.getPcThumbImageFile().getSize() > 0) {
            serviceBoardEntity.setPcThumbImage(fileService.proc(serviceBoardDto.getPcThumbImageFile(), refType, "PC"));
        }
        if (!Objects.isNull(serviceBoardDto.getMoThumbImageFile()) && serviceBoardDto.getMoThumbImageFile().getSize() > 0) {
            serviceBoardEntity.setMoThumbImage(fileService.proc(serviceBoardDto.getMoThumbImageFile(), refType, "MO"));
        }
        if (!Objects.isNull(serviceBoardDto.getPcMainImageFile()) && serviceBoardDto.getPcMainImageFile().getSize() > 0) {
            serviceBoardEntity.setPcMainImage(fileService.proc(serviceBoardDto.getPcMainImageFile(), refType, "PC"));
        }
        if (!Objects.isNull(serviceBoardDto.getMoMainImageFile()) && serviceBoardDto.getMoMainImageFile().getSize() > 0) {
            serviceBoardEntity.setMoMainImage(fileService.proc(serviceBoardDto.getMoMainImageFile(), refType, "MO"));
        }

        serviceBoardEntity = serviceBoardDto.bindToEntityForProc(serviceBoardEntity);
        serviceBoardEntity.setServiceBoardType(codeService.findByCodeAndCodeMaster(serviceBoardDto.getServiceBoardType().getCode(), "SERVICE_BOARD_TYPE"));
        serviceBoardRepository.save(serviceBoardEntity);
    }

    public void updateDelYn(ServiceBoardDelYnDto serviceBoardDelYnDto) {
        ServiceBoardEntity serviceBoardEntity = this.findById(serviceBoardDelYnDto.getServiceBoardSeq());
        serviceBoardEntity.setDelYn(serviceBoardDelYnDto.getDelYn());
        serviceBoardRepository.save(serviceBoardEntity);
    }
}
