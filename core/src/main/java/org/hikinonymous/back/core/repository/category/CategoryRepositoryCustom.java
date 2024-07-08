package org.hikinonymous.back.core.repository.category;

import org.hikinonymous.back.core.dto.CmsMenuDto;

import java.util.List;

public interface CategoryRepositoryCustom {

    List<CmsMenuDto> findAllByManagerSeq(long managerSeq);

}
