package com.ljx.hfgsjt.repository.activity;

import com.ljx.hfgsjt.entity.dto.Activity.WfHiInstContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface WfHiInstContentRepository extends JpaRepository<WfHiInstContentEntity, String>,JpaSpecificationExecutor<WfHiInstContentEntity>, Serializable {

}
