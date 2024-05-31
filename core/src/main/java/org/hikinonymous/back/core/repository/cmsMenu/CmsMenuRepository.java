package org.hikinonymous.back.core.repository.cmsMenu;

import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CmsMenuRepository extends JpaRepository<CmsMenuEntity, Long>, CmsMenuRepositoryCustom {

    List<CmsMenuEntity> findAllByDisplayYn(String displayYn);

}
