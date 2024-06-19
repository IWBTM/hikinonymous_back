package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.entity.ServiceBoardEntity;
import org.hikinonymous.back.core.repository.serviceBoard.ServiceBoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ServiceBoardService {

    private final ServiceBoardRepository serviceBoardRepository;

    private final CodeService codeService;

    public List<ServiceBoardEntity> findAllByServiceBoardType(String serviceBoardType) {
        return serviceBoardRepository.findAllByServiceBoardType(codeService.findByCodeAndCodeMaster("SERVICE_BOARD_TYPE", serviceBoardType));
    }

    public ServiceBoardEntity findById(Long seq) {
        return serviceBoardRepository.findById(seq).orElseThrow(() ->
                new NoSuchElementException("Service Board Seq: " + seq + " not found")
        );
    }

}
