package com.ljx.hfgsjt.repository.activity;

import com.ljx.hfgsjt.entity.dto.Activity.WfHiInstEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfRuInstEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface WfHiInstRepository extends JpaRepository<WfHiInstEntity, String>,JpaSpecificationExecutor<WfHiInstEntity>, Serializable {


    List<WfHiInstEntity> findByPdefId(String pdefId);
}
