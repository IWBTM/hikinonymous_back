package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.ManagerAuthDto;
import org.hikinonymous.back.core.repository.managerLog.ManagerLogRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerLogService {

    private final ManagerLogRepository managerLogRepository;

    public void proc(ManagerAuthDto managerAuthDto) {
    }
}

