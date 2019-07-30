package com.ljx.hfgsjt.repository;

import com.ljx.hfgsjt.entity.dto.SbcjEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface SbcjRepository extends JpaRepository<SbcjEntity, String>,JpaSpecificationExecutor<SbcjEntity> {


    @Query(value = "SELECT * FROM XTGL_SBCJ WHERE mc like %?1%",
            countQuery = "SELECT count(*) FROM XTGL_SBCJ WHERE mc like %?1%",
            nativeQuery = true)
    Page<SbcjEntity> findUserAll(String sbcjname, PageRequest request);

}
