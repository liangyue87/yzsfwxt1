package com.ljx.hfgsjt.repository.activity;

import com.ljx.hfgsjt.entity.dto.Activity.WfActivityRoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface WfActivityRoleRepository extends JpaRepository<WfActivityRoleEntity, String>,JpaSpecificationExecutor<WfActivityRoleEntity>, Serializable {

    @Query(value = "SELECT * FROM WF_ACTIVITY_ROLE WHERE activityid like %?1% ",
            countQuery = "SELECT count(*) FROM WF_ACTIVITY_ROLE WHERE activityid like %?1%",
            nativeQuery = true)
    Page<WfActivityRoleEntity> findByActivitynameLike(String activityid, PageRequest request);
}
