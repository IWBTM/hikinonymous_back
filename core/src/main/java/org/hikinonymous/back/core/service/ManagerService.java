package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.ManagerDelYnDto;
import org.hikinonymous.back.core.dto.ManagerDto;
import org.hikinonymous.back.core.dto.ManagerPwdDto;
import org.hikinonymous.back.core.entity.ManagerEntity;
import org.hikinonymous.back.core.repository.manager.ManagerRepository;
import org.hikinonymous.back.core.utils.EncUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ServerErrorException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    private final CodeService codeService;

    public ManagerEntity findByManagerId(String email) {
        return managerRepository.findByManagerId(email);
    }

    public void updateSuccessLoginStatus(ManagerEntity managerEntity) {
        managerEntity.setLastLoginDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        managerEntity.setLoginCnt(managerEntity.getLoginCnt() + 1);
        managerEntity.setLoginFailCnt(0);
        managerEntity.setManagerStatus(codeService.findByCodeAndCodeMaster("ACTIVE", "MANAGER_STATUS"));
        managerRepository.save(managerEntity);
    }

    public void updateFailLoginStatus(ManagerEntity managerEntity) {
        managerEntity.setLoginFailCnt(managerEntity.getLoginFailCnt() + 1);
        managerRepository.save(managerEntity);
    }

    public ManagerEntity findByManagerSeq(long seq) {
        return managerRepository.findByManagerSeq(seq).orElseThrow(() ->
                new ServerErrorException("Manger Seq: " + seq + " not found", null)
        );
    }

    @Transactional
    public Stream<ManagerEntity> streamAllBySuperYn(String superYn) {
        return managerRepository.streamAllBySuperYn(superYn);
    }

    @Transactional
    public void proc(ManagerDto managerDto) {
        ManagerEntity managerEntity = managerRepository.findByManagerSeq(managerDto.getManagerSeq()).orElseGet(() ->
            new ManagerEntity()
        );

        managerEntity.setManagerId(EncUtil.encryptAES256(managerDto.getManagerId()));
        managerEntity.setManagerNm(EncUtil.encryptAES256(managerDto.getManagerNm()));
        managerEntity.setManagerHp(EncUtil.encryptAES256(managerDto.getManagerHp()));

        managerEntity.setUseYn(managerDto.getUseYn());

        if (!Objects.isNull(managerDto.getManagerStatusSeq())) {
            managerEntity.setManagerStatus(codeService.findByCodeSeq(managerDto.getManagerStatusSeq()));
        }
        managerRepository.save(managerEntity);
    }

    @Transactional
    public void updatePwd(ManagerPwdDto managerDto) {
        ManagerEntity managerEntity = managerRepository.findByManagerSeq(managerDto.getManagerSeq()).orElseThrow(() ->
                new NoSuchElementException("Manager Seq: " + managerDto.getManagerSeq() + " not found")
        );

        managerEntity.setManagerPwd(EncUtil.encryptSHA256(managerDto.getManagerPwd()));
        managerRepository.save(managerEntity);
    }

    @Transactional
    public void updateDelYn(ManagerDelYnDto managerDto) {
        ManagerEntity managerEntity = managerRepository.findByManagerSeq(managerDto.getManagerSeq()).orElseThrow(() ->
                new NoSuchElementException("Manager Seq: " + managerDto.getManagerSeq() + " not found")
        );

        managerEntity.setDelYn(EncUtil.encryptSHA256(managerDto.getDelYn()));
        managerRepository.save(managerEntity);
    }

    public Page<ManagerEntity> paging(Pageable pageable) {
        return managerRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()));
    }
}
