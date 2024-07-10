package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.CodeDto;
import org.hikinonymous.back.core.dto.InquiryDelYnDto;
import org.hikinonymous.back.core.dto.InquiryDto;
import org.hikinonymous.back.core.entity.InquiryEntity;
import org.hikinonymous.back.core.repository.inquiry.InquiryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final InquiryRepository inquiryRepository;

    private final CodeService codeService;

    public Page<InquiryEntity> findAllByInquiryType(String inquiryType, Pageable pageable) {
        inquiryType = inquiryType.toUpperCase();
        return inquiryRepository.findAllByInquiryTypeAndDelYn(codeService.findByCodeAndCodeMaster(inquiryType, "INQUIRY_TYPE"), PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()), "N");
    }

    public InquiryEntity findById(Long seq) {
        return inquiryRepository.findById(seq).orElseThrow(() ->
                new NoSuchElementException("Inquiry Seq: " + seq + " not found")
        );
    }

    public void proc(InquiryDto inquiryDto) {
        InquiryEntity inquiryEntity = Optional
                .ofNullable(inquiryDto.getInquirySeq())
                .flatMap(inquiryRepository::findById)
                .orElseGet(InquiryEntity::new);

        inquiryEntity = inquiryDto.bindToEntityForProc(inquiryEntity);
        inquiryEntity.setInquiryType(codeService.findByCodeAndCodeMaster(inquiryDto.getInquiryType().getCode(), "SERVICE_BOARD_TYPE"));
        inquiryRepository.save(inquiryEntity);
    }

    public void updateDelYn(InquiryDelYnDto inquiryDelYnDto) {
        InquiryEntity inquiryEntity = this.findById(inquiryDelYnDto.getInquirySeq());
        inquiryEntity.setDelYn(inquiryDelYnDto.getDelYn());
        inquiryRepository.save(inquiryEntity);
    }

    public void updateReadYn(Long seq) {
        InquiryEntity inquiryEntity = this.findById(seq);
        inquiryEntity.setReadYn("Y");
        inquiryRepository.save(inquiryEntity);
    }

    public void updateAnswer(InquiryDto inquiryDto) {
        InquiryEntity inquiryEntity = this.findById(inquiryDto.getInquirySeq());
        inquiryEntity.setAnswer(inquiryDto.getAnswer());
        inquiryEntity.setAnswerer(inquiryDto.getAnswerer());
        inquiryEntity.setAnswerDate(inquiryDto.getAnswerDate());
        inquiryEntity.setAnswererIp(inquiryDto.getAnswererIp());
        inquiryEntity.setAnswerYn("Y");
        inquiryRepository.save(inquiryEntity);
    }
}
