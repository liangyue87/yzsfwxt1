package com.ljx.hfgsjt.repository.yj;

import com.ljx.hfgsjt.entity.yj.XmFm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FmRepository extends JpaRepository<XmFm,String> , JpaSpecificationExecutor<XmFm> {

}
