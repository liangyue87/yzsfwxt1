package com.ljx.hfgsjt.repository.yswt;

import com.ljx.hfgsjt.entity.wtys.YsZjqtcbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface YsZjqtcbRepository extends JpaRepository<YsZjqtcbEntity,String> , JpaSpecificationExecutor<YsZjqtcbEntity> {

}
