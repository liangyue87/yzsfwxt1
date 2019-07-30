package com.ljx.hfgsjt.repository.gcpd;

import com.ljx.hfgsjt.entity.dto.gcpd.YshgRwbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface YshgRwbRepository extends JpaRepository<YshgRwbEntity,String>, JpaSpecificationExecutor<YshgRwbEntity> {
}
