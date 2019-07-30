package com.ljx.hfgsjt.repository.sjst;

import com.ljx.hfgsjt.entity.dto.Sjst.GwxxdcCb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface GwxxFkRepository extends JpaRepository<GwxxdcCb, String> , JpaSpecificationExecutor<GwxxdcCb>, Serializable {

}
