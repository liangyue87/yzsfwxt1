package com.ljx.hfgsjt.repository.activity;

import com.ljx.hfgsjt.entity.dto.Activity.WfDfActivityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface WfDfActivityRepository extends JpaRepository<WfDfActivityEntity, String>,JpaSpecificationExecutor<WfDfActivityEntity>, Serializable {

    @Query(value = "SELECT * FROM WF_DF_ACTIVITY WHERE ACTIVITYNAME like %?1% order by ACTIVITYID",
            countQuery = "SELECT count(*) FROM WF_DF_ACTIVITY WHERE ACTIVITYNAME like %?1%",
            nativeQuery = true)
    Page<WfDfActivityEntity> findByDfActivitynameLike(String activityname, PageRequest request);

    WfDfActivityEntity findByActivityid(String activityid);
}
