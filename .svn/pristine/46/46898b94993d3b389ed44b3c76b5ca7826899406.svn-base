package com.ljx.hfgsjt.repository.activity;

import com.ljx.hfgsjt.entity.dto.Activity.WfRuInstContentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface WfRuInstContentRepository extends JpaRepository<WfRuInstContentEntity, String>,JpaSpecificationExecutor<WfRuInstContentEntity>, Serializable {


    WfRuInstContentEntity findByProcInstIdAndContentname(String procInstId, String contentname);
}
