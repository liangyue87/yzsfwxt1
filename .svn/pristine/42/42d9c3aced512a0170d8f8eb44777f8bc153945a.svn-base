package com.ljx.hfgsjt.repository;

import com.ljx.hfgsjt.entity.dto.HtglJjrNew;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JjrdyRepository extends JpaRepository<HtglJjrNew, String>, JpaSpecificationExecutor<HtglJjrNew> {
    @Query(value = "SELECT * FROM htgl_jjr_new t WHERE year like %?1% order by t.date1 asc",
            countQuery = "SELECT count(*) FROM htgl_jjr_new WHERE year like %?1%",
            nativeQuery = true)
    Page<HtglJjrNew> findByNodenameLike(String year, PageRequest pageRequest);

    @Query(value = "select * from HTGL_JJR_NEW where lx=3 order by date1 asc",
            nativeQuery = true)
    List<HtglJjrNew> findByZm();
}
