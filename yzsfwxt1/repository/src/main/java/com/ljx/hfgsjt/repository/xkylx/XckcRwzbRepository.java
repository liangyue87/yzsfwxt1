package com.ljx.hfgsjt.repository.xkylx;

import com.ljx.hfgsjt.entity.dto.xkybz.XckcRwzbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface XckcRwzbRepository extends JpaRepository<XckcRwzbEntity, String>,JpaSpecificationExecutor<XckcRwzbEntity>, Serializable {

}
