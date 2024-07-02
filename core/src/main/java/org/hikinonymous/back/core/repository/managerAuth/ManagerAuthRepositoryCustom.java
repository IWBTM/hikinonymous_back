package org.hikinonymous.back.core.repository.managerAuth;

import org.hikinonymous.back.core.dto.MenuAuthDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ManagerAuthRepositoryCustom {

    List<MenuAuthDto> findAllByManagerSeqAndAuthDir(Long managerSeq, String authDir, boolean isExist);

}
