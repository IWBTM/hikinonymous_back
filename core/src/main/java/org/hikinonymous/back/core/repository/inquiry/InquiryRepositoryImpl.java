package org.hikinonymous.back.core.repository.inquiry;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.CodeDto;
import org.hikinonymous.back.core.dto.InquiryDto;
import org.hikinonymous.back.core.dto.InquiryFileDto;
import org.hikinonymous.back.core.entity.QInquiryEntity;
import org.hikinonymous.back.core.entity.QInquiryFileEntity;

@RequiredArgsConstructor
public class InquiryRepositoryImpl implements InquiryRepositoryCustom {
    
    private final JPAQueryFactory queryFactory;
    
    @Override
    public CodeDto findByIdWithFiles(Long seq) {
        QInquiryEntity qInquiryEntity = QInquiryEntity.inquiryEntity;
        QInquiryFileEntity qInquiryFileEntity = QInquiryFileEntity.inquiryFileEntity;

//        queryFactory
//                .select(
//                        Projections.fields(
//                                InquiryDto.class,
//                                qInquiryEntity.inquirySeq,
//                                qInquiryEntity.title,
//                                qInquiryEntity.content,
//                                qInquiryEntity.inquiryType,
//                                Projections.array(
//                                )
//                        )
//                )
        return null;
    }
    
}
