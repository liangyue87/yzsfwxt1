package com.ljx.hfgsjt.repository.yswt;

import com.ljx.hfgsjt.entity.dto.yswt.YsQsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface YsQsRepository extends JpaRepository<YsQsEntity,String >, JpaSpecificationExecutor<YsQsEntity> {

        }
