package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.entity.ManagerEntity;
import org.hikinonymous.back.core.repository.codeMaster.CodeMasterRepository;
import org.hikinonymous.back.core.repository.manager.ManagerRepository;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    private final CodeService codeService;

    public ManagerEntity findByManagerId(String email) {
        return managerRepository.findByManagerId(CommonUtil.encryptAES256(email));
    }

    public void updateSuccessLoginStatus(ManagerEntity managerEntity) {
        managerEntity.setLastLoginDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        managerEntity.setLoginCnt(managerEntity.getLoginCnt() + 1);
        managerEntity.setManagerStatus(codeService.findByCodeAndCodeMaster("ACTIVE", "MANAGER_STATUS"));
        managerRepository.save(managerEntity);
    }

    public void updateFailLoginStatus(ManagerEntity managerEntity) {
        managerEntity.setLoginFailCnt(managerEntity.getLoginCnt() + 1);
        managerRepository.save(managerEntity);
    }
}
