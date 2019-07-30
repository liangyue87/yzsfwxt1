package com.ljx.hfgsjt.repository.yj;

import com.ljx.hfgsjt.entity.yj.YjBwyjzb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BwyjZbRepository extends JpaRepository<YjBwyjzb,String> , JpaSpecificationExecutor<YjBwyjzb> {

}
