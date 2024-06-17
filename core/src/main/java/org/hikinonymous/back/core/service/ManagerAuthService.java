package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.ManagerAuthDto;
import org.hikinonymous.back.core.entity.ManagerAuthEntity;
import org.hikinonymous.back.core.repository.managerAuth.ManagerAuthEntityRepository;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ManagerAuthService {

    private final ManagerAuthEntityRepository managerAuthEntityRepository;

    private final ManagerService managerService;

    public List<ManagerAuthEntity> findAll() {
        return managerAuthEntityRepository.findAll();
    }

    public void proc(ManagerAuthDto managerAuthDto) {
        ManagerAuthEntity managerAuthEntity = managerAuthEntityRepository.findById(managerAuthDto.getManagerAuthSeq()).orElseGet(() ->
                new ManagerAuthEntity()
        );

        managerAuthEntity = (ManagerAuthEntity) CommonUtil.bindToObjectFromObjObject(managerAuthDto, ManagerAuthEntity.class);
        managerAuthEntityRepository.save(managerAuthEntity);
    }
}

