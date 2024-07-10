package org.hikinonymous.back.core.repository.board;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.BoardSimpleDto;
import org.hikinonymous.back.core.dto.CategoryDto;
import org.hikinonymous.back.core.dto.CodeDto;
import org.hikinonymous.back.core.entity.CodeEntity;
import org.hikinonymous.back.core.entity.QBoardEntity;
import org.hikinonymous.back.core.entity.QCategoryEntity;
import org.hikinonymous.back.core.entity.QCodeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<BoardSimpleDto> findAllByBoardType(CodeEntity codeEntity, Pageable pageable) {
        QBoardEntity qBoardEntity = QBoardEntity.boardEntity;
        List<BoardSimpleDto> boardSimpleDtoList = queryFactory
                .select(
                        Projections.fields(
                                BoardSimpleDto.class,
                                qBoardEntity.boardSeq,
                                qBoardEntity.title,
                                Projections.fields(
                                    CategoryDto.class,
                                    qBoardEntity.category.categorySeq,
                                    qBoardEntity.category.categoryName
                                ).as("category"),
                                qBoardEntity.viewCnt,
                                Expressions.numberTemplate(Long.class, "{0}", qBoardEntity.replies.size()).as("replyCnt"),
                                qBoardEntity.delYn,
                                qBoardEntity.regDate,
                                qBoardEntity.register.memberName.as("registerNm"),
                                qBoardEntity.register.memberNickName.as("registerNickName")

                        )
                )
                .from(qBoardEntity)
                .where(getBoardListWhere(qBoardEntity))
                .orderBy(qBoardEntity.regDate.desc())
                .fetch();

        Long totalBoardCnt = queryFactory
                .select(
                        qBoardEntity.count()
                )
                .from(qBoardEntity)
                .where(getBoardListWhere(qBoardEntity))
                .fetchOne();
        return new PageImpl(boardSimpleDtoList, pageable, totalBoardCnt);
    }

    private BooleanExpression getBoardListWhere(QBoardEntity qBoardEntity) {
        return (
                qBoardEntity.register.dropDate.isNull()
                        .or(qBoardEntity.register.dropDate.isEmpty())
                )
                .and(qBoardEntity.delYn.eq("N"));
    }

}
