package com.ljx.hfgsjt.repository.yj;

import com.ljx.hfgsjt.entity.yj.YjSbyjzbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YjSbyjzbRepository extends JpaRepository<YjSbyjzbEntity,String> , JpaSpecificationExecutor<YjSbyjzbEntity> {

    List<YjSbyjzbEntity> findByXmid(String xmid);
}
