package org.hikinonymous.back.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hikinonymous.back.core.entity.BannerEntity;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.EncUtil;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Schema(
        name = "배너 DTO"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerDto extends CommonManagerDto {

    private Long bannerSeq;

    private String title;

    private CodeDto position;

    private Long positionSeq;

    private String etc;

    private String url;

    private String useYn;

    private MultipartFile pcImageFile;

    private MultipartFile moImageFile;

    private FileDto pcImage;

    private FileDto moImage;

    public BannerEntity bindToEntityForProc(BannerEntity bannerEntity) {
        bannerEntity.setBannerSeq(this.getBannerSeq());
        bannerEntity.setTitle(this.getTitle());
        bannerEntity.setEtc(this.getEtc());
        bannerEntity.setUrl(this.getUrl());
        bannerEntity.setUseYn(this.getUseYn());
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

    public static BannerDto bindToDtoForView(BannerEntity bannerEntity) {
        BannerDto bannerDto = new BannerDto();
        bannerDto.setBannerSeq(bannerEntity.getBannerSeq());
        bannerDto.setTitle(bannerEntity.getTitle());
        bannerDto.setPosition(CodeDto.bindToDto(bannerEntity.getPosition()));
        bannerDto.setEtc(bannerEntity.getEtc());
        bannerDto.setUrl(bannerEntity.getUrl());
        bannerDto.setUseYn(bannerEntity.getUseYn());
        bannerDto.setPcImage(FileDto.bindToDtoForView(bannerEntity.getPcImage()));
        bannerDto.setMoImage(FileDto.bindToDtoForView(bannerEntity.getMoImage()));
        if (!Objects.isNull(bannerEntity.getRegister())) {
            bannerDto.setRegDate(CommonUtil.getDayByStrDate(bannerEntity.getRegDate()));
            bannerDto.setRegisterNm(EncUtil.decryptAES256(bannerEntity.getRegister().getManagerNm()));
            bannerDto.setRegisterIp(bannerEntity.getRegisterIp());
        }
        if (!Objects.isNull(bannerEntity.getUpdater())) {
            bannerDto.setUpdDate(CommonUtil.getDayByStrDate(bannerEntity.getUpdDate()));
            bannerDto.setUpdaterNm(bannerEntity.getUpdater().getManagerNm());
            bannerDto.setUpdaterNm(EncUtil.decryptAES256(bannerEntity.getUpdater().getManagerNm()));
            bannerDto.setUpdaterIp(bannerEntity.getUpdaterIp());
        }
        return bannerDto;
    }

}
