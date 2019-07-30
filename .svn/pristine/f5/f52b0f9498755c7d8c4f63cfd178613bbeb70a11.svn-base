package com.ljx.hfgsjt.repository.yswt;

import com.ljx.hfgsjt.entity.dto.yswt.XmYsztEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface YswtRepository extends JpaRepository<XmYsztEntity, String>, JpaSpecificationExecutor<XmYsztEntity> {


    /**
     * 查询全部项目视图
     * @return
     */
    @Query(value = "select * FROM XM_YSZT",nativeQuery=true)
    Page<XmYsztEntity> findYswtAll(PageRequest request);


}


