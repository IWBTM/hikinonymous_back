package org.hikinonymous.back.core.repository.member;

import org.hikinonymous.back.core.dto.MemberSimpleDto;
import org.hikinonymous.back.core.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepositoryCustom {

    Page<MemberSimpleDto>  findAllByMemberStatus(String memberStatus, Pageable pageable);

}
