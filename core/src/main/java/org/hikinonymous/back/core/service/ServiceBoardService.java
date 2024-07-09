package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceBoardService {

    private final ServiceBoardRepository serviceBoardRepository;

    private final CodeService codeService;

    public Page<ServiceBoardEntity> findAllByServiceBoardType(String serviceBoardType, Pageable pageable) {
        serviceBoardType = serviceBoardType.toUpperCase();
        return serviceBoardRepository.findAllByServiceBoardType(codeService.findByCodeAndCodeMaster(serviceBoardType, "SERVICE_BOARD_TYPE"), PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()));
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
        serviceBoardEntity = serviceBoardDto.bindToEntityForProc(serviceBoardEntity);
        serviceBoardEntity.setServiceBoardType(codeService.findByCodeAndCodeMaster(serviceBoardDto.getServiceBoardType().getCode(), "SERVICE_BOARD_TYPE"));
        serviceBoardRepository.save(serviceBoardEntity);
    }
}
