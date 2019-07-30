package com.ljx.hfgsjt.repository.gcpd;

import com.ljx.hfgsjt.entity.dto.gcpd.GcpdSqgchgpdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GcpdRepository extends JpaRepository<GcpdSqgchgpdEntity,String>, JpaSpecificationExecutor<GcpdSqgchgpdEntity> {

}
