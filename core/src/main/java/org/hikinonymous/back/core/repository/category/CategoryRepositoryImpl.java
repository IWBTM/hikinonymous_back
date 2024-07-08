package org.hikinonymous.back.core.repository.category;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.CmsMenuDto;
import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.hikinonymous.back.core.entity.ManagerEntity;
import org.hikinonymous.back.core.entity.QCmsMenuEntity;
import org.hikinonymous.back.core.entity.QManagerAuthEntity;
import org.hikinonymous.back.core.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CmsMenuDto> findAllByManagerSeq(long managerSeq) {
        QCmsMenuEntity qCmsMenuEntity = QCmsMenuEntity.cmsMenuEntity;
        QManagerAuthEntity qManagerAuthEntity = QManagerAuthEntity.managerAuthEntity;

        List<Tuple> tuples = jpaQueryFactory
                .select(qCmsMenuEntity, qManagerAuthEntity.authTypes)
                .from(qCmsMenuEntity)
                .join(qManagerAuthEntity).on(qManagerAuthEntity.cmsMenu.eq(qCmsMenuEntity))
                .where(qManagerAuthEntity.manager.eq(ManagerEntity.builder().managerSeq(managerSeq).build()))
                .orderBy(qCmsMenuEntity.menuLevel.asc(), qCmsMenuEntity.sortOrder.asc())
                .fetch();

        List<CmsMenuDto> cmsMenuDtoList = new ArrayList<>();
        for (Tuple tuple : tuples) {
            CmsMenuEntity cmsMenuEntity = tuple.get(qCmsMenuEntity);
            String authTypes = tuple.get(qManagerAuthEntity.authTypes);

            CmsMenuDto cmsMenuDto = (CmsMenuDto) CommonUtil.bindToObjectFromObject(cmsMenuEntity, CmsMenuDto.class);
            cmsMenuDto.setAuthTypes(authTypes);
            cmsMenuDtoList.add(cmsMenuDto);
        }
        return cmsMenuDtoList;
    }

}
