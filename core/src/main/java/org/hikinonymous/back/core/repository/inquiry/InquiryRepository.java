package org.hikinonymous.back.core.repository.inquiry;

import org.hikinonymous.back.core.entity.CodeEntity;
import org.hikinonymous.back.core.entity.InquiryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<InquiryEntity, Long>, InquiryRepositoryCustom {

    Page<InquiryEntity> findAllByInquiryTypeAndDelYn(CodeEntity serviceBoardType, PageRequest of, String delYn);

}
