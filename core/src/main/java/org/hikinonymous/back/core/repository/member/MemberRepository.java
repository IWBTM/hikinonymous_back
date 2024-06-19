package org.hikinonymous.back.core.repository.member;

import org.hikinonymous.back.core.entity.CodeEntity;
import org.hikinonymous.back.core.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long>, MemberRepositoryCustom {

    List<MemberEntity> findAllByMemberStatus(CodeEntity memberStatus);

}
