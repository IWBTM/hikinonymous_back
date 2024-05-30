package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.LoginDto;
import org.hikinonymous.back.core.entity.ManagerEntity;
import org.hikinonymous.back.core.repository.manager.ManagerRepository;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    public ManagerEntity login(LoginDto loginDto) {
        ManagerEntity managerEntity = managerRepository.findByManagerIdAndManagerPwd(loginDto.getEmail(), CommonUtil.encryptSHA256(loginDto.getPwd()));
        // ... 어떤 로직 넣지
        return managerEntity;
    }
}
