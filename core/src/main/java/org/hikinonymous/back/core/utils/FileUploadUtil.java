package org.hikinonymous.back.core.utils;

import ch.qos.logback.core.util.StringUtil;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.impl.FileUploadIOException;
import org.hikinonymous.back.core.entity.FileInfoEntity;
import org.hsqldb.lib.FileUtil;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerErrorException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class FileUploadUtil {

    private static int fileNameSeq;

    public static FileInfoEntity upload(MultipartFile multipartFile, String uploadPath, String refType, String deviceType) {
        if (Objects.isNull(multipartFile)) return null;

        String fileName = getUniqueFileName();
        String fileExt = getFileExt(multipartFile.getOriginalFilename());

        FileInfoEntity fileInfoEntity = new FileInfoEntity();
        fileInfoEntity.setFileOriNm(multipartFile.getOriginalFilename());
        fileInfoEntity.setFilePath(refType + "/" + deviceType + "/");
        fileInfoEntity.setFileNm(fileName + fileExt);
        fileInfoEntity.setFileExt(fileExt);
        fileInfoEntity.setFileSize(multipartFile.getSize());

        String fileFullPath = uploadPath + fileInfoEntity.getFilePath() + fileInfoEntity.getFileNm();
        fileInfoEntity.setFileFullPath(fileFullPath);

        FileUtil.makeDirectories(uploadPath + fileInfoEntity.getFilePath());
        File file = new File(fileFullPath);

        try {
            multipartFile.transferTo(file);

            if (uploadPath.contains("리눅스일 경우 권한 지정")) {
                Runtime.getRuntime().exec("chmod -R 644 " + file);
            }
        } catch (IOException e) {
            throw new ServerErrorException(refType + " => " + deviceType + " 경로에 파일 업로드 중 ", null);
        }

        return fileInfoEntity;
    }

    /**
     * 유니크한 파일명을 만들어 낸다.
     *
     * @return String 파일명
     */
    private static String getUniqueFileName() {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.KOREA);
        return dataFormat.format(new Date()) + String.format("%03d", (++fileNameSeq % 1000));
    }

    /**
     * 파일 확장자 반환
     *
     * @return
     */
    public static String getFileExt(String fileName) {
        if (StringUtil.isNullOrEmpty(fileName)) return "";
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
