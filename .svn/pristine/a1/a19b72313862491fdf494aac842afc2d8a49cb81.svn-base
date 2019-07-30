package com.ljx.hfgsjt.repository.activity;

import com.ljx.hfgsjt.entity.dto.Activity.WfReNodeattrEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface WfReNodeattrRepository extends JpaRepository<WfReNodeattrEntity, String>,JpaSpecificationExecutor<WfReNodeattrEntity>, Serializable {

    /*@Query(value = "SELECT * FROM WF_RE_NODEATTR WHERE NODENAME like %?1% ",
            countQuery = "SELECT count(*) FROM WF_RE_NODEATTR WHERE NODENAME like %?1%",
            nativeQuery = true)
    Page<WfReNodeattrEntity> findByNodenameLike(String nodename, PageRequest request);*/
}
