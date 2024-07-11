package org.hikinonymous.back.core.repository.managerAuth;

import ch.qos.logback.core.util.StringUtil;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.MenuAuthDto;
import org.hikinonymous.back.core.entity.QCmsMenuEntity;
import org.hikinonymous.back.core.entity.QManagerAuthEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class ManagerAuthRepositoryImpl implements ManagerAuthRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MenuAuthDto> findAllByManagerSeqAndAuthDir(Long managerSeq, String authDir, boolean isExist) {
        QCmsMenuEntity menuEntity = QCmsMenuEntity.cmsMenuEntity;
        QManagerAuthEntity managerAuthEntity = QManagerAuthEntity.managerAuthEntity;

        if (isExist) {
            List<MenuAuthDto> menuAuthDtoList = queryFactory
                    .select(getMenuAuthDto(managerAuthEntity, menuEntity))
                    .from(managerAuthEntity)
                    .leftJoin(menuEntity)
                    .on(
                            managerAuthEntity.cmsMenu.eq(menuEntity)
                                    .and(managerAuthEntity.manager.managerSeq.eq(managerSeq))
                    )
                    .where(getWhereOfFindAllManagerSeqAndAuthDir(menuEntity, managerAuthEntity, authDir, isExist))
                    .fetch();
            return menuAuthDtoList;
        } else {
            List<MenuAuthDto> menuAuthDtoList = queryFactory
                    .select(getMenuAuthDto(managerAuthEntity, menuEntity))
                    .from(menuEntity)
                    .leftJoin(managerAuthEntity)
                    .on(
                            managerAuthEntity.cmsMenu.eq(menuEntity)
                                    .and(managerAuthEntity.manager.managerSeq.eq(managerSeq))
                    )
                    .where(getWhereOfFindAllManagerSeqAndAuthDir(menuEntity, managerAuthEntity, authDir, isExist))
                    .fetch();
            return menuAuthDtoList;
        }
    }

    @Override
    public Page<MenuAuthDto> pagingByManagerSeqAndAuthDir(Long managerSeq, String authDir, boolean isExist, Pageable pageable
    ) {
        QCmsMenuEntity menuEntity = QCmsMenuEntity.cmsMenuEntity;
        QManagerAuthEntity managerAuthEntity = QManagerAuthEntity.managerAuthEntity;

        if (isExist) {
            List<MenuAuthDto> menuAuthDtoList = queryFactory
                    .select(getMenuAuthDto(managerAuthEntity, menuEntity))
                    .from(managerAuthEntity)
                    .leftJoin(menuEntity)
                    .on(
                            managerAuthEntity.cmsMenu.eq(menuEntity)
                                    .and(managerAuthEntity.manager.managerSeq.eq(managerSeq))
                    )
                    .where(getWhereOfFindAllManagerSeqAndAuthDir(menuEntity, managerAuthEntity, authDir, isExist))
                    .fetch();

            Long totalCnt = queryFactory
                    .select(
                            menuEntity.count()
                    )
                    .from(managerAuthEntity)
                    .leftJoin(menuEntity)
                    .on(
                            managerAuthEntity.cmsMenu.eq(menuEntity)
                                    .and(managerAuthEntity.manager.managerSeq.eq(managerSeq))
                    )
                    .where(getWhereOfFindAllManagerSeqAndAuthDir(menuEntity, managerAuthEntity, authDir, isExist))
                    .fetchOne();

            return new PageImpl<>(menuAuthDtoList, pageable, totalCnt);
        } else {
            List<MenuAuthDto> menuAuthDtoList = queryFactory
                    .select(getMenuAuthDto(managerAuthEntity, menuEntity))
                    .from(menuEntity)
                    .leftJoin(managerAuthEntity)
                    .on(
                            managerAuthEntity.cmsMenu.eq(menuEntity)
                                    .and(managerAuthEntity.manager.managerSeq.eq(managerSeq))
                    )
                    .where(getWhereOfFindAllManagerSeqAndAuthDir(menuEntity, managerAuthEntity, authDir, isExist))
                    .fetch();

            Long totalCnt = queryFactory
                    .select(
                            menuEntity.count()
                    )
                    .from(menuEntity)
                    .leftJoin(managerAuthEntity)
                    .on(
                            managerAuthEntity.cmsMenu.eq(menuEntity)
                                    .and(managerAuthEntity.manager.managerSeq.eq(managerSeq))
                    )
                    .where(getWhereOfFindAllManagerSeqAndAuthDir(menuEntity, managerAuthEntity, authDir, isExist))
                    .fetchOne();

            return new PageImpl<>(menuAuthDtoList, pageable, totalCnt);
        }
    }

    private ConstructorExpression<MenuAuthDto> getMenuAuthDto(QManagerAuthEntity managerAuthEntity, QCmsMenuEntity menuEntity) {
        return Projections.constructor(
                MenuAuthDto.class,
                managerAuthEntity.managerAuthSeq,
                managerAuthEntity.manager.managerSeq,
                menuEntity.cmsMenuSeq,
                menuEntity.menuNm,
                menuEntity.authDir,
                managerAuthEntity.authTypes
        );
    }

    private BooleanExpression getWhereOfFindAllManagerSeqAndAuthDir(QCmsMenuEntity menuEntity, QManagerAuthEntity managerAuthEntity, String authDir, boolean isExist) {
        if (isExist) {
            return menuEntity.delYn.eq("N")
                    .and(
                            StringUtil.isNullOrEmpty(authDir) ?
                                    menuEntity.menuLevel.eq(1) :
                                    (
                                            menuEntity.authDir.eq(authDir)
                                                    .and(menuEntity.menuLevel.eq(2))
                                    )
                    );
        } else {
            return menuEntity.delYn.eq("N")
                    .and(
                            StringUtil.isNullOrEmpty(authDir) ?
                                    menuEntity.menuLevel.eq(1) :
                                    (
                                            menuEntity.authDir.eq(authDir)
                                                    .and(menuEntity.menuLevel.eq(2))
                                    )
                    )
                    .and(
                            managerAuthEntity.authTypes.isNull()
                    );
        }
    }

}
