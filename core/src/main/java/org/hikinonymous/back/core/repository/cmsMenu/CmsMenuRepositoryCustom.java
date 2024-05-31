package org.hikinonymous.back.core.repository.cmsMenu;

import org.hikinonymous.back.core.dto.CmsMenuDto;

import java.util.List;

public interface CmsMenuRepositoryCustom {

    List<CmsMenuDto> findAllByManagerSeq(long managerSeq);

}
