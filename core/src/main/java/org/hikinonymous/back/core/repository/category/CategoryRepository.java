package org.hikinonymous.back.core.repository.category;

import org.hikinonymous.back.core.entity.CategoryEntity;
import org.hikinonymous.back.core.entity.CmsMenuEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>, CategoryRepositoryCustom {

    Page<CategoryEntity> findAllByCategoryLevelAndDelYn(PageRequest page, Integer categoryLevel, String delYn);

}
