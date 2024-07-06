package org.hikinonymous.back.core.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.hikinonymous.back.core.dto.ResponseDto;
import org.hikinonymous.back.core.entity.FileInfoEntity;
import org.hikinonymous.back.core.repository.common.FileInfoRepository;
import org.hikinonymous.back.core.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.util.Objects;
import java.util.Optional;

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

    public FileInfoEntity findById(Long fileInfoSeq) {
        return Optional
                .ofNullable(fileInfoSeq)
                .flatMap(fileInfoRepository::findById)
                .orElseGet(FileInfoEntity::new);
    }


    public ResponseEntity getFileByteById(Long fileInfoSeq) {
        FileInfoEntity fileInfoEntity = this.findById(fileInfoSeq);

        try {
            InputStream imageStream = new FileInputStream(fileInfoEntity.getFileFullPath());
            byte[] imageByteArray = IOUtils.toByteArray(imageStream);
            imageStream.close();
            return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
