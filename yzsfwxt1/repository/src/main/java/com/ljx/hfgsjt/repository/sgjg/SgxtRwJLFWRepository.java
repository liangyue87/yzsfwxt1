package com.ljx.hfgsjt.repository.sgjg;

import com.ljx.hfgsjt.entity.sgjg.SgxtRwJlfwEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SgxtRwJLFWRepository extends JpaRepository<SgxtRwJlfwEntity,String>, JpaSpecificationExecutor<SgxtRwJlfwEntity> {
}
