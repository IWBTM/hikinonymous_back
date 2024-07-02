package org.hikinonymous.back.core.repository.managerAuth;

import ch.qos.logback.core.util.StringUtil;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.MenuAuthDto;
import org.hikinonymous.back.core.entity.QCmsMenuEntity;
import org.hikinonymous.back.core.entity.QManagerAuthEntity;

import java.util.List;

@RequiredArgsConstructor
public class ManagerAuthRepositoryImpl implements ManagerAuthRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MenuAuthDto> findAllByManagerSeqAndAuthDir(Long managerSeq, String authDir, boolean isExist) {
        QCmsMenuEntity menuEntity = QCmsMenuEntity.cmsMenuEntity;
        QManagerAuthEntity managerAuthEntity = QManagerAuthEntity.managerAuthEntity;

        if (isExist) {
            return queryFactory
                    .select(
                            Projections.constructor(
                                    MenuAuthDto.class,
                                    menuEntity.menuNm,
                                    managerAuthEntity.authTypes
                            )
                    )
                    .from(managerAuthEntity)
                    .leftJoin(menuEntity)
                    .on(
                            managerAuthEntity.cmsMenu.eq(menuEntity)
                                    .and(managerAuthEntity.manager.managerSeq.eq(managerSeq))
                    )
                    .where(
                            menuEntity.delYn.eq("N")
                                    .and(
                                            StringUtil.isNullOrEmpty(authDir) ?
                                                    menuEntity.menuLevel.eq(1) :
                                                    menuEntity.authDir.eq(authDir)
                                    )
                    )
                    .fetch();
        } else {
            return queryFactory
                    .select(
                            Projections.constructor(
                                    MenuAuthDto.class,
                                    menuEntity.menuNm,
                                    managerAuthEntity.authTypes
                            )
                    )
                    .from(menuEntity)
                    .leftJoin(managerAuthEntity)
                    .on(
                            managerAuthEntity.cmsMenu.eq(menuEntity)
                                    .and(managerAuthEntity.manager.managerSeq.eq(managerSeq))
                    )
                    .where(
                            menuEntity.delYn.eq("N")
                                    .and(
                                            StringUtil.isNullOrEmpty(authDir) ?
                                                    menuEntity.menuLevel.eq(1) :
                                                    menuEntity.authDir.eq(authDir)
                                    )
                                    .and(
                                            managerAuthEntity.authTypes.isNull()
                                    )

                    )
                    .fetch();
        }
    }

}
