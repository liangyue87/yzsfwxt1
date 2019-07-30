package com.ljx.hfgsjt.repository.activity;

import com.ljx.hfgsjt.entity.dto.Activity.WfTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface WfTaskRepository extends JpaRepository<WfTaskEntity, String>,JpaSpecificationExecutor<WfTaskEntity>, Serializable {
    @Query(value = "SELECT max(to_number(TASKID)) FROM wf_task",nativeQuery = true)
    Integer findByMaxId();

    List<WfTaskEntity> findByProcInstId(String pdef_id);

    WfTaskEntity findByTaskid(String taskid);

    @Query(value = "SELECT * FROM wf_task where TASKID = ?1",nativeQuery = true)
    WfTaskEntity getWfTaskById(String taskid);

    WfTaskEntity findBycActivityidAndProcInstId(String cActivityid,String procInstId);

    WfTaskEntity findBycActivityidAndRefid(String pdefId, String refid);
}
