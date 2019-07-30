package com.ljx.hfgsjt.repository.htqd;

import com.ljx.hfgsjt.entity.dto.htqd.DemoHtqdformEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface HtqdRepository extends JpaRepository<DemoHtqdformEntity, String>,JpaSpecificationExecutor<DemoHtqdformEntity>, Serializable {

}
