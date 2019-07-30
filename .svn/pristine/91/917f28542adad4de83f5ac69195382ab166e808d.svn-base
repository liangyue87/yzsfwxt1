package com.ljx.hfgsjt.repository.activity;

import com.ljx.hfgsjt.entity.dto.Activity.WfReNodeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface WfReNodeRepository extends JpaRepository<WfReNodeEntity, String>,JpaSpecificationExecutor<WfReNodeEntity>, Serializable {

    @Query(value = "SELECT * FROM WF_RE_NODE WHERE NODENAME like %?1% ",
            countQuery = "SELECT count(*) FROM WF_RE_NODE WHERE NODENAME like %?1%",
            nativeQuery = true)
    Page<WfReNodeEntity> findByNodenameLike(String nodename, PageRequest request);

    List<WfReNodeEntity> findByActivityidAndDirection(String activityid,String direction);

    List<WfReNodeEntity> findByActivityidAndNextnode(String activityid, String nextnode);
}
