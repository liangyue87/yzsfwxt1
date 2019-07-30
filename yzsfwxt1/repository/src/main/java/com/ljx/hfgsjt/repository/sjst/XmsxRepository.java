package com.ljx.hfgsjt.repository.sjst;

import com.ljx.hfgsjt.entity.dto.Sjst.XmSx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface XmsxRepository extends JpaRepository<XmSx, String>,JpaSpecificationExecutor<XmSx>, Serializable {
    @Query(value = "SELECT a.*,b.MC bfmc FROM XM_SX a left join XM_BF b on a.BFID=b.ID WHERE a.XMID = ?1 and a.zt='1' order by a.cjsj desc",nativeQuery = true)
    List<XmSx> findAllByXmid(String xmid);
}
