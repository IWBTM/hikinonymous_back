package org.hikinonymous.back.core.repository.cmsMenu;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.CmsMenuDto;
import org.hikinonymous.back.core.dto.CodeDto;
import org.hikinonymous.back.core.entity.*;
import org.hikinonymous.back.core.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CmsMenuRepositoryImpl implements CmsMenuRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CmsMenuDto> findAllByManagerSeq(long managerSeq) {
        QCmsMenuEntity qCmsMenuEntity = QCmsMenuEntity.cmsMenuEntity;
        QManagerAuthEntity qManagerAuthEntity = QManagerAuthEntity.managerAuthEntity;

        List<Tuple> tuples = jpaQueryFactory
                .select(qCmsMenuEntity, qManagerAuthEntity)
                .from(qCmsMenuEntity)
                .join(qManagerAuthEntity).on(qManagerAuthEntity.cmsMenu.eq(qCmsMenuEntity))
                .where(qManagerAuthEntity.manager.eq(ManagerEntity.builder().managerSeq(managerSeq).build()))
                .orderBy(qCmsMenuEntity.menuLevel.asc(), qCmsMenuEntity.sortOrder.asc())
                .fetch();

        List<CmsMenuDto> cmsMenuDtoList = new ArrayList<>();
        for (Tuple tuple : tuples) {
            CmsMenuEntity cmsMenuEntity = tuple.get(qCmsMenuEntity);
            ManagerAuthEntity managerAuthEntity = tuple.get(qManagerAuthEntity);

            CmsMenuDto cmsMenuDto = (CmsMenuDto) CommonUtil.bindToObjectFromObjObject(cmsMenuEntity, CmsMenuDto.class);
            cmsMenuDto.setCrudType((CodeDto) CommonUtil.bindToObjectFromObjObject(managerAuthEntity.getCode(), CodeDto.class));
            cmsMenuDtoList.add(cmsMenuDto);
        }
        return cmsMenuDtoList;
    }

}
