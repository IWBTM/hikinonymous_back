package org.hikinonymous.back.core.repository.cmsMenu;

import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@Repository
public interface CmsMenuRepository extends JpaRepository<CmsMenuEntity, Long>, CmsMenuRepositoryCustom {

    Stream<CmsMenuEntity> streamAllByDisplayYnAndDelYn(String displayYn, String delYn);

    Stream<CmsMenuEntity> streamAllByDelYn(String delYn);

    Optional<CmsMenuEntity> findByCmsMenuSeq(Long seq);

    Page<CmsMenuEntity> findAllByMenuLevel(PageRequest of, Integer menuLevel);

    Page<CmsMenuEntity> findAllByMenuLevelAndAuthDir(PageRequest of, Integer menuLevel, String authDir);

    List<CmsMenuEntity> findAllByAuthDirAndMenuLevel(String authDir, Integer menuLevel);

}
