package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hikinonymous.back.core.entity.BannerEntity;
import org.hikinonymous.back.core.entity.CodeEntity;
import org.hikinonymous.back.core.entity.FileInfoEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Schema(
        name = "배너 리스트용 DTO"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerDto extends CommonDto {

    private Long bannerSeq;

    private String title;

    private CodeEntity position;

    private Long positionSeq;

    private String etc;

    private String url;

    private String useYn;

    private MultipartFile pcImageFile;

    private MultipartFile moImageFile;

    private FileInfoEntity pcImage;

    private FileInfoEntity moImage;

    public BannerEntity bindEntityForProc() {
        BannerEntity bannerEntity = new BannerEntity();
        bannerEntity.setBannerSeq(this.getBannerSeq());
        bannerEntity.setTitle(this.getTitle());
        bannerEntity.setPosition(this.getPosition());
        bannerEntity.setEtc(this.getEtc());
        bannerEntity.setUrl(this.getUrl());
        bannerEntity.setUseYn(this.getUseYn());
        bannerEntity.setPcImage(this.getPcImage());
        bannerEntity.setMoImage(this.getMoImage());
        if (Objects.isNull(this.getBannerSeq())) {
            bannerEntity.setRegDate(this.getRegDate());
            bannerEntity.setRegister(this.getRegister());
            bannerEntity.setRegisterIp(this.getRegisterIp());
        } else {
            bannerEntity.setUpdDate(this.getUpdDate());
            bannerEntity.setUpdater(this.getUpdater());
            bannerEntity.setUpdaterIp(this.getUpdaterIp());
        }
        return bannerEntity;
    }

}
