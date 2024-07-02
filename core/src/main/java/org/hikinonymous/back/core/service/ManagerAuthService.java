package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.MenuAuthDto;
import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.hikinonymous.back.core.entity.ManagerAuthEntity;
import org.hikinonymous.back.core.entity.ManagerEntity;
import org.hikinonymous.back.core.repository.managerAuth.ManagerAuthRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ManagerAuthService {

    private final ManagerAuthRepository managerAuthRepository;

    private final ManagerService managerService;

    private final CmsMenuService cmsMenuService;

    public List<MenuAuthDto> findAllByManagerSeqAndAuthDir(Long managerSeq, String authDir, boolean isExist) {
        return managerAuthRepository.findAllByManagerSeqAndAuthDir(managerSeq, authDir, isExist);
    }

    @Transactional
    public void proc(MenuAuthDto menuAuthDto) {
        ManagerAuthEntity managerAuthEntity = Optional
                .ofNullable(menuAuthDto.getManagerAuthSeq())
                .flatMap(managerAuthRepository::findById)
                .orElseGet(ManagerAuthEntity::new);

        ManagerEntity managerEntity = managerService.findByManagerSeq(menuAuthDto.getManagerSeq());
        CmsMenuEntity cmsMenuEntity = cmsMenuService.findByCmsMenuSeq(menuAuthDto.getCmsMenuSeq());

        if (cmsMenuEntity.getMenuLevel().equals(1)) {
            List<ManagerAuthEntity> managerAuthEntities = new LinkedList<>();
            managerAuthEntity.setManager(managerEntity);
            managerAuthEntity.setCmsMenu(cmsMenuEntity);
            managerAuthEntity.setAuthTypes(menuAuthDto.getAuthTypes());
            managerAuthEntities.add(managerAuthEntity);

            List<MenuAuthDto> existMenuAuthDtoList = this.findAllByManagerSeqAndAuthDir(menuAuthDto.getManagerSeq(), menuAuthDto.getAuthDir(), true);
            if (!existMenuAuthDtoList.isEmpty()) {
                managerAuthEntities.addAll(
                    existMenuAuthDtoList.stream().map(existMenuAuthDto ->
                         ManagerAuthEntity
                                .builder()
                                .managerAuthSeq(existMenuAuthDto.getManagerAuthSeq())
                                .manager(managerEntity)
                                .cmsMenu(cmsMenuService.findByCmsMenuSeq(existMenuAuthDto.getCmsMenuSeq()))
                                .authTypes(menuAuthDto.getAuthTypes())
                                .build()
                    ).toList()
                );
            }

            List<MenuAuthDto> unExistMenuAuthDtoList = this.findAllByManagerSeqAndAuthDir(menuAuthDto.getManagerSeq(), menuAuthDto.getAuthDir(), false);
            if (!unExistMenuAuthDtoList.isEmpty()) {
                managerAuthEntities.addAll(
                    unExistMenuAuthDtoList.stream().map(unExistMenuAuthDto ->
                            ManagerAuthEntity
                                    .builder()
                                    .managerAuthSeq(unExistMenuAuthDto.getManagerAuthSeq())
                                    .manager(managerEntity)
                                    .cmsMenu(cmsMenuService.findByCmsMenuSeq(unExistMenuAuthDto.getCmsMenuSeq()))
                                    .authTypes(menuAuthDto.getAuthTypes())
                                    .build()
                    ).toList()
                );
            }

            managerAuthRepository.saveAll(managerAuthEntities);
        }

        if (cmsMenuEntity.getMenuLevel().equals(2)) {
            managerAuthEntity.setAuthTypes(menuAuthDto.getAuthTypes());
            managerAuthEntity.setManager(managerEntity);
            managerAuthEntity.setCmsMenu(cmsMenuService.findByCmsMenuSeq(menuAuthDto.getCmsMenuSeq()));
            managerAuthRepository.save(managerAuthEntity);
        }
    }

    public void del(MenuAuthDto menuAuthDto) {
        ManagerAuthEntity managerAuthEntity = this.findById(menuAuthDto.getManagerAuthSeq());
        CmsMenuEntity cmsMenuEntity = managerAuthEntity.getCmsMenu();

        List<ManagerAuthEntity> managerAuthEntities = new LinkedList<>();
        if (cmsMenuEntity.getMenuLevel().equals(1)) {
            List<MenuAuthDto> menuAuthDtoList = this.findAllByManagerSeqAndAuthDir(menuAuthDto.getManagerSeq(), cmsMenuEntity.getAuthDir(), true);
            if (!menuAuthDtoList.isEmpty()) {
                managerAuthEntities.addAll(
                        menuAuthDtoList.stream().map(existMenuAuthDto ->
                            ManagerAuthEntity
                                    .builder()
                                    .managerAuthSeq(existMenuAuthDto.getManagerAuthSeq())
                                    .build()
                        ).toList()
                );
            }
        }
        managerAuthEntities.add(managerAuthEntity);
        managerAuthRepository.deleteAll(managerAuthEntities);
    }

    public ManagerAuthEntity findById(Long managerAuthSeq) {
        return managerAuthRepository.findById(managerAuthSeq).orElseThrow(() ->
            new NoSuchElementException("ManagerAuth Seq:" + managerAuthSeq + "not found")
        );
    }
}

