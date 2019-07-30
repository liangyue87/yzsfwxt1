package com.ljx.hfgsjt.repository.activity;

import com.ljx.hfgsjt.entity.dto.Activity.WfRuInstEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface WfRuInstRepository extends JpaRepository<WfRuInstEntity, String>,JpaSpecificationExecutor<WfRuInstEntity>, Serializable {
    @Query(value = "select max(to_number(proc_inst_Id)) from wf_ru_inst",nativeQuery = true)
    Integer findByMaxId();

    List<WfRuInstEntity> findByPdefId(String pdefId);

    WfRuInstEntity findByprocInstId(String procInstId);
}
