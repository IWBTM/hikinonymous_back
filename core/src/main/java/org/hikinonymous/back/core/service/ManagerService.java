package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.LoginDto;
import org.hikinonymous.back.core.entity.ManagerEntity;
import org.hikinonymous.back.core.repository.manager.ManagerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final BCryptPasswordEncoder encoder;

    private final ManagerRepository managerRepository;

    public ManagerEntity login(LoginDto loginDto) {
        ManagerEntity managerEntity = managerRepository.findByManagerIdAndManagerPwd(loginDto.getEmail(), encoder.encode(loginDto.getPwd()));
        // ... 어떤 로직 넣지
        return managerEntity;
    }
}
