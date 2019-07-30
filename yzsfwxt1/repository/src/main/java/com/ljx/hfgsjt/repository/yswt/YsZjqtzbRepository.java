package com.ljx.hfgsjt.repository.yswt;

import com.ljx.hfgsjt.entity.wtys.YsZjqtzbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface YsZjqtzbRepository extends JpaRepository<YsZjqtzbEntity,String> , JpaSpecificationExecutor<YsZjqtzbEntity> {

}
