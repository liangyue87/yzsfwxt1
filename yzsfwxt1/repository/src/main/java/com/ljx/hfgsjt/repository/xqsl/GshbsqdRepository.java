package com.ljx.hfgsjt.repository.xqsl;

import com.ljx.hfgsjt.entity.xqsl.YwslGshbsqdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface GshbsqdRepository extends JpaRepository<YwslGshbsqdEntity, String>, JpaSpecificationExecutor<YwslGshbsqdEntity>, Serializable {

}
