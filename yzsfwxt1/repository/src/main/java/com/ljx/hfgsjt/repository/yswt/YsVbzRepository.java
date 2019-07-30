package com.ljx.hfgsjt.repository.yswt;

import com.ljx.hfgsjt.entity.dto.yswt.YsVbzNewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface YsVbzRepository extends JpaRepository<YsVbzNewEntity,String > , JpaSpecificationExecutor<YsVbzNewEntity> {
}
