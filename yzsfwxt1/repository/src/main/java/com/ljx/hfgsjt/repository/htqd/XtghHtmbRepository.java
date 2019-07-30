package com.ljx.hfgsjt.repository.htqd;

import com.ljx.hfgsjt.entity.dto.htqd.XtglHtmbEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface XtghHtmbRepository extends JpaRepository<XtglHtmbEntity, String>,JpaSpecificationExecutor<XtglHtmbEntity>, Serializable {

    @Query(value = "SELECT * FROM XTGL_HTMB  WHERE mbmc like %?1%",
            countQuery = "SELECT count(*) FROM XTGL_HTMB  WHERE mbmc like %?1%",
            nativeQuery = true)
    Page<XtglHtmbEntity> findXtglHtmb(String mbmc, PageRequest request);

    List<XtglHtmbEntity> findByMbbm(String mbbm);
}
