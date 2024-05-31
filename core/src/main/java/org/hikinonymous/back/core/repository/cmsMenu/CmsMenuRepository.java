package org.hikinonymous.back.core.repository.cmsMenu;

import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;


@Repository
public interface CmsMenuRepository extends JpaRepository<CmsMenuEntity, Long>, CmsMenuRepositoryCustom {

    Stream<CmsMenuEntity> streamAllByDisplayYn(String displayYn);

}
