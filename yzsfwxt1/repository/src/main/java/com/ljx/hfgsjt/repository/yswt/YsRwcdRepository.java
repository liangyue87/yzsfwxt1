package com.ljx.hfgsjt.repository.yswt;

import com.ljx.hfgsjt.entity.dto.yswt.YsRwcb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface YsRwcdRepository extends JpaRepository<YsRwcb , String >, JpaSpecificationExecutor<YsRwcb> {

}
