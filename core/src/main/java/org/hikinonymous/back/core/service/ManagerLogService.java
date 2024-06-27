package org.hikinonymous.back.core.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.ManagerDto;
import org.hikinonymous.back.core.dto.ManagerLogDto;
import org.hikinonymous.back.core.entity.ManagerLogEntity;
import org.hikinonymous.back.core.repository.managerLog.ManagerLogRepository;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ManagerLogService {

    private final ManagerLogRepository managerLogRepository;

    private final ManagerService managerService;

    @Transactional
    public void proc(HttpServletRequest request, String content, String behaviorType, ManagerDto managerDto) {
        // package 인식 오류
        ManagerLogDto managerLogDto = new ManagerLogDto();
        managerLogDto.setContent(content);
        managerLogDto.setBehaviorType(behaviorType);
        CommonUtil.setClientInfo(request, managerLogDto, managerDto);
        managerLogRepository.save((ManagerLogEntity) CommonUtil.bindToObjectFromObject(managerLogDto, ManagerLogEntity.class));
    }

    public Page<ManagerLogEntity> pagingByRegister(Pageable pageable, Long managerSeq) {
        return managerLogRepository.findAllByRegister(pageable, managerService.findByManagerSeq(managerSeq));
    }

}

