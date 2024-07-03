package org.hikinonymous.back.core.repository.common;

import org.hikinonymous.back.core.entity.FileInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfoEntity, Long>, FileInfoRepositoryCustom {

}
