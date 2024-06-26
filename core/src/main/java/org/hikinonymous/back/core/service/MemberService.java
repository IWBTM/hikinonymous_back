package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.MemberDto;
import org.hikinonymous.back.core.entity.MemberEntity;
import org.hikinonymous.back.core.repository.member.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final CodeService codeService;

    public Page<MemberEntity> findAllByMemberStatus(String memberStatus, Pageable pageable) {
        return memberRepository.findAllByMemberStatus(codeService.findByCodeAndCodeMaster("MEMBER_STATUS", memberStatus), PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()));
    }

    public MemberEntity findById(Long seq) {
        return memberRepository.findById(seq).orElseThrow(() ->
                new NoSuchElementException("Member Seq: " + seq + " not found")
        );
    }

    public void updateMemberStatus(MemberDto memberDto) {
        MemberEntity memberEntity = this.findById(memberDto.getMemberSeq());
        memberEntity.setMemberStatus(codeService.findByCodeSeq(memberDto.getMemberSeq()));
    }

    public void updateReportCnt(MemberDto memberDto) {
        MemberEntity memberEntity = this.findById(memberDto.getMemberSeq());
        memberEntity.setReportCnt(memberDto.getReportCnt());
    }
}
