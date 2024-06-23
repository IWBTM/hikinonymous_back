package org.hikinonymous.back.core.repository.member;

import org.hikinonymous.back.core.entity.CodeEntity;
import org.hikinonymous.back.core.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long>, MemberRepositoryCustom {

    Page<MemberEntity> findAllByMemberStatus(CodeEntity memberStatus, Pageable pageable);

}
