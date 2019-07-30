package com.ljx.hfgsjt.repository.yswt;

import com.ljx.hfgsjt.entity.dto.yswt.XtglFylx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XtglFylxRepository extends JpaRepository<XtglFylx, String>, JpaSpecificationExecutor< XtglFylx> {

    @Query(value = "SELECT x.* FROM XTGL_FYLX x ORDER BY x.BH",
            nativeQuery = true)
    List<XtglFylx> findAllByAsc();
}
