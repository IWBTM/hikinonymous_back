package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.repository.managerAuth.ManagerAuthEntityRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerAuthService {

    private final ManagerAuthEntityRepository managerAuthEntityRepository;

}

