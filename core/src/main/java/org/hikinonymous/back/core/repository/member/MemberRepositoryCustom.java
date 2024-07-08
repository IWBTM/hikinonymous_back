package org.hikinonymous.back.core.repository.member;

import org.hikinonymous.back.core.dto.MemberDto;
import org.hikinonymous.back.core.dto.MemberSimpleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MemberRepositoryCustom {

    Page<MemberSimpleDto>  findAllByMemberStatus(String memberStatus, Pageable pageable);

    Optional<MemberDto> findDtoById(Long memberSeq);

}
