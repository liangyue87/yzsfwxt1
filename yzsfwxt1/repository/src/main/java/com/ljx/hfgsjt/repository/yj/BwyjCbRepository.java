package com.ljx.hfgsjt.repository.yj;

import com.ljx.hfgsjt.entity.yj.YjBwyjcb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BwyjCbRepository extends JpaRepository<YjBwyjcb,String> , JpaSpecificationExecutor<YjBwyjcb> {

}
