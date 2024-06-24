package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.ManagerAuthDto;
import org.hikinonymous.back.core.entity.ManagerAuthEntity;
import org.hikinonymous.back.core.repository.managerAuth.ManagerAuthRepository;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerAuthService {

    private final ManagerAuthRepository managerAuthRepository;

    private final ManagerService managerService;

    public Page<ManagerAuthEntity> findAll(Pageable pageable) {
        return managerAuthRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()));
    }

    public void proc(ManagerAuthDto managerAuthDto) {
        ManagerAuthEntity managerAuthEntity = managerAuthRepository.findById(managerAuthDto.getManagerAuthSeq()).orElseGet(() ->
                new ManagerAuthEntity()
        );

        managerAuthEntity = (ManagerAuthEntity) CommonUtil.bindToObjectFromObject(managerAuthDto, ManagerAuthEntity.class);
        managerAuthRepository.save(managerAuthEntity);
    }
}

