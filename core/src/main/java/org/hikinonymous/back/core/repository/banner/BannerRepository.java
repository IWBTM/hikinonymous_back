package org.hikinonymous.back.core.repository.banner;

import org.hikinonymous.back.core.entity.BannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<BannerEntity, Long>, BannerRepositoryCustom {
}
