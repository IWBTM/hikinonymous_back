package org.hikinonymous.back.core.repository.managerAuth;

import org.hikinonymous.back.core.dto.MenuAuthDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ManagerAuthRepositoryCustom {

    Page<MenuAuthDto> pagingByManagerSeqAndAuthDir(Long managerSeq, String authDir, boolean isExist, Pageable pageable);

    List<MenuAuthDto> findAllByManagerSeqAndAuthDir(Long managerSeq, String authDir, boolean isExist);

}
