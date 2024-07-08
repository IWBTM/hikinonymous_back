package org.hikinonymous.back.core.repository.member;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.CodeDto;
import org.hikinonymous.back.core.dto.MemberDto;
import org.hikinonymous.back.core.dto.MemberSimpleDto;
import org.hikinonymous.back.core.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.querydsl.core.types.ExpressionUtils.count;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MemberSimpleDto> findAllByMemberStatus(String memberStatus, Pageable pageable) {
        QBoardEntity qBoardEntity = QBoardEntity.boardEntity;
        QReplyEntity qReplyEntity = QReplyEntity.replyEntity;
        QMemberEntity qMemberEntity = QMemberEntity.memberEntity;

        List<MemberSimpleDto> memberSimpleDtoList = queryFactory
                .select(
                        Projections.fields(
                                MemberSimpleDto.class,
                                qMemberEntity.memberSeq,
                                qMemberEntity.memberName,
                                qMemberEntity.memberNickName,
                                qMemberEntity.memberEmail,
                                Projections.fields(
                                        CodeDto.class,
                                        qMemberEntity.memberStatus.codeSeq,
                                        qMemberEntity.memberStatus.code,
                                        qMemberEntity.memberStatus.codeNm
                                ).as("memberStatus"),
                                qMemberEntity.reportCnt,
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(count(qBoardEntity))
                                                .from(qBoardEntity)
                                                .where(
                                                        qBoardEntity.register.eq(qMemberEntity)
                                                        .and(qBoardEntity.delYn.eq("N"))
                                                ),
                                        "totalBoardCnt"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(count(qReplyEntity))
                                                .from(qReplyEntity)
                                                .where(
                                                        qReplyEntity.register.eq(qMemberEntity)
                                                                .and(qReplyEntity.delYn.eq("N"))
                                                ),
                                        "totalReplyCnt"),
                                qMemberEntity.lastLoginDate,
                                qMemberEntity.regDate
                        )
                )
                .from(qMemberEntity)
                .where(qMemberEntity.memberStatus.code.eq(memberStatus)
                        .and(qMemberEntity.memberStatus.codeMasterEntity.codeMaster.eq("MEMBER_STATUS")))
                .orderBy(qMemberEntity.regDate.desc())
                .fetch();

        Long totalMemberCnt = queryFactory
                .select(
                        qMemberEntity.count()
                )
                .from(qMemberEntity)
                .where(qMemberEntity.memberStatus.code.eq(memberStatus)
                        .and(qMemberEntity.memberStatus.codeMasterEntity.codeMaster.eq("MEMBER_STATUS")))
                .orderBy(qMemberEntity.regDate.desc())
                .fetchOne();
        return new PageImpl(memberSimpleDtoList, pageable, totalMemberCnt);
    }

    @Override
    public Optional<MemberDto> findDtoById(Long memberSeq) {
        QBoardEntity qBoardEntity = QBoardEntity.boardEntity;
        QReplyEntity qReplyEntity = QReplyEntity.replyEntity;
        QMemberEntity qMemberEntity = QMemberEntity.memberEntity;
        return Optional.ofNullable(
                queryFactory
                        .select(
                                Projections.fields(
                                        MemberDto.class,
                                        qMemberEntity.memberSeq,
                                        qMemberEntity.memberName,
                                        qMemberEntity.memberNickName,
                                        qMemberEntity.memberEmail,
                                        qMemberEntity.memberHp,
                                        Projections.fields(
                                                CodeDto.class,
                                                qMemberEntity.memberStatus.codeSeq,
                                                qMemberEntity.memberStatus.code,
                                                qMemberEntity.memberStatus.codeNm
                                        ).as("memberStatus"),
                                        Projections.fields(
                                                CodeDto.class,
                                                qMemberEntity.joinType.codeSeq,
                                                qMemberEntity.joinType.code,
                                                qMemberEntity.joinType.codeNm
                                        ).as("joinType"),
                                        qMemberEntity.gender,
                                        qMemberEntity.memo,
                                        qMemberEntity.reportCnt,
                                        qMemberEntity.lastLoginDate,
                                        ExpressionUtils.as(
                                                JPAExpressions
                                                        .select(count(qBoardEntity))
                                                        .from(qBoardEntity)
                                                        .where(
                                                                qBoardEntity.register.eq(qMemberEntity)
                                                                        .and(qBoardEntity.delYn.eq("N"))
                                                        ),
                                                "totalBoardCnt"),
                                        ExpressionUtils.as(
                                                JPAExpressions
                                                        .select(count(qReplyEntity))
                                                        .from(qReplyEntity)
                                                        .where(
                                                                qReplyEntity.register.eq(qMemberEntity)
                                                                        .and(qReplyEntity.delYn.eq("N"))
                                                        ),
                                                "totalReplyCnt"),
                                        qMemberEntity.privacyYn,
                                        qMemberEntity.receiveAdsEmailYn
                                )
                        )
                        .from(qMemberEntity)
                        .where(qMemberEntity.memberSeq.eq(memberSeq))
                        .fetchOne()
        );
    }

}
