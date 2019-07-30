package com.ljx.hfgsjt.repository;

import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface XmInstanceRepository extends JpaRepository<XmInstanceEntity, String>,JpaSpecificationExecutor<XmInstanceEntity> {

    @Query(value = "SELECT t.*,F_GET_USERNAME(t.XMFZR) xmfzr_str FROM XM_INSTANCE t WHERE ID = ?1",nativeQuery = true)
    XmInstanceEntity findByXmid(String xmid);

    List<XmInstanceEntity> findByXmbh(String xmbh);
}
