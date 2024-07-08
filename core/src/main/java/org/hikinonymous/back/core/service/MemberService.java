package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.MemberDto;
import org.hikinonymous.back.core.dto.MemberSimpleDto;
import org.hikinonymous.back.core.dto.MemberUpdDto;
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

    public Page<MemberSimpleDto> pagingByMemberStatus(String memberStatus, Pageable pageable) {
        return memberRepository.findAllByMemberStatus(memberStatus, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()));
    }

    public MemberDto findDtoById(Long seq) {
        return memberRepository.findDtoById(seq).orElseThrow(() ->
                new NoSuchElementException("Member Seq: " + seq + " not found")
        );
    }

    public MemberEntity findById(Long seq) {
        return memberRepository.findById(seq).orElseThrow(() ->
                new NoSuchElementException("Member Seq: " + seq + " not found")
        );
    }

    public void updateReportCnt(Long seq) {
        MemberEntity memberEntity = this.findById(seq);
        memberEntity.setReportCnt(0);
        memberRepository.save(memberEntity);
    }

    public void updateMemberStatusAndMemo(MemberUpdDto memberUpdDto) {
        MemberEntity memberEntity = this.findById(memberUpdDto.getMemberSeq());
        if (!memberEntity.getMemberStatus().getCode().equals("DROP")) {
            memberEntity.setMemberStatus(codeService.findByCodeSeq(memberUpdDto.getMemberStatusSeq()));
        }
        memberEntity.setMemo(memberUpdDto.getMemo());
        memberRepository.save(memberEntity);
    }
}
