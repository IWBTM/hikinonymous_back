package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.ManagerLogDto;
import org.hikinonymous.back.core.entity.ManagerLogEntity;
import org.hikinonymous.back.core.repository.managerLog.ManagerLogRepository;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ManagerLogService {

    private final ManagerLogRepository managerLogRepository;

    @Transactional
    public void proc(ManagerLogDto managerLogDto) {
        managerLogRepository.save((ManagerLogEntity) CommonUtil.bindToObjectFromObject(managerLogDto, ManagerLogEntity.class));
    }

}
