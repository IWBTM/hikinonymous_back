package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.ManagerAuthDto;
import org.hikinonymous.back.core.entity.ManagerAuthEntity;
import org.hikinonymous.back.core.repository.managerAuth.ManagerAuthEntityRepository;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ManagerAuthService {

    private final ManagerAuthEntityRepository managerAuthEntityRepository;

    private final ManagerService managerService;

    public Page<ManagerAuthEntity> findAll(Pageable pageable) {
        return managerAuthEntityRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()));
    }

    public void proc(ManagerAuthDto managerAuthDto) {
        ManagerAuthEntity managerAuthEntity = managerAuthEntityRepository.findById(managerAuthDto.getManagerAuthSeq()).orElseGet(() ->
                new ManagerAuthEntity()
        );

        managerAuthEntity = (ManagerAuthEntity) CommonUtil.bindToObjectFromObject(managerAuthDto, ManagerAuthEntity.class);
        managerAuthEntityRepository.save(managerAuthEntity);
    }
}

