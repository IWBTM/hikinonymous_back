package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.entity.FileInfoEntity;
import org.hikinonymous.back.core.repository.common.FileInfoRepository;
import org.hikinonymous.back.core.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileInfoRepository fileInfoRepository;

    private final CodeService codeService;

    @Value("${file.upload.path}")
    private String uploadPath;

    public FileInfoEntity proc(MultipartFile file, String refType, String deviceType) {
        FileInfoEntity fileInfoEntity = FileUploadUtil.upload(file, uploadPath, refType, deviceType);
        if (Objects.isNull(fileInfoEntity)) return null;
        fileInfoEntity.setRefType(codeService.findByCodeAndCodeMaster(refType, "FILE_REF_TYPE"));
        fileInfoEntity.setDeviceType(codeService.findByCodeAndCodeMaster(deviceType, "DEVICE_TYPE"));

        return fileInfoRepository.save(fileInfoEntity);
    }

}
