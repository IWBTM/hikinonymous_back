package org.hikinonymous.back.core.repository.inquiry;

import org.hikinonymous.back.core.dto.CodeDto;

public interface InquiryRepositoryCustom {

    CodeDto findByIdWithFiles(Long seq);
}
