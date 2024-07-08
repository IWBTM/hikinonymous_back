package org.hikinonymous.back.core.repository.member;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.CodeDto;
import org.hikinonymous.back.core.dto.MemberSimpleDto;
import org.hikinonymous.back.core.entity.QBoardEntity;
import org.hikinonymous.back.core.entity.QMemberEntity;
import org.hikinonymous.back.core.entity.QReplyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
                .where(qMemberEntity.memberStatus.codeNm.eq(memberStatus)
                        .and(qMemberEntity.memberStatus.codeMasterEntity.codeMasterNm.eq("MEMBER_STATUS")))
                .orderBy(qMemberEntity.regDate.desc())
                .fetch();

        Long totalMemberCnt = queryFactory
                .select(
                        qMemberEntity.count()
                )
                .from(qMemberEntity)
                .where(qMemberEntity.memberStatus.codeNm.eq(memberStatus)
                        .and(qMemberEntity.memberStatus.codeMasterEntity.codeMasterNm.eq("MEMBER_STATUS")))
                .orderBy(qMemberEntity.regDate.desc())
                .fetchOne();
        return new PageImpl(memberSimpleDtoList, pageable, totalMemberCnt);
    }

}
