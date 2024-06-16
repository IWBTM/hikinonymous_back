package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.ManagerDto;
import org.hikinonymous.back.core.entity.ManagerEntity;
import org.hikinonymous.back.core.repository.manager.ManagerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ServerErrorException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    private final CodeService codeService;

    public ManagerEntity findByManagerId(String email) {
        return managerRepository.findByManagerId(email);
    }

    public void updateSuccessLoginStatus(ManagerEntity managerEntity) {
        managerEntity.setLastLoginDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        managerEntity.setLoginCnt(managerEntity.getLoginCnt() + 1);
        managerEntity.setLoginFailCnt(0);
        managerEntity.setManagerStatus(codeService.findByCodeAndCodeMaster("ACTIVE", "MANAGER_STATUS"));
        managerRepository.save(managerEntity);
    }

    public void updateFailLoginStatus(ManagerEntity managerEntity) {
        managerEntity.setLoginFailCnt(managerEntity.getLoginFailCnt() + 1);
        managerRepository.save(managerEntity);
    }

    public ManagerEntity findByManagerSeq(long seq) {
        return managerRepository.findByManagerSeq(seq).orElseThrow(() ->
                new ServerErrorException("Manger Seq: " + seq + " not found", null)
        );
    }

    @Transactional
    public Stream<ManagerEntity> streamAllBySuperYn(String superYn) {
        return managerRepository.streamAllBySuperYn(superYn);
    }
}
