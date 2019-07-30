package com.ljx.hfgsjt.repository;

import com.ljx.hfgsjt.entity.dto.xkybz.KcrwfqView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface kcrwfqViewRepository extends JpaRepository<KcrwfqView, String>,JpaSpecificationExecutor<KcrwfqView> {

   @Query(value = "SELECT * FROM GET_XCKC_FQ WHERE xmjl = ?1",
           countQuery = "SELECT count(*) FROM GET_XCKC_FQ WHERE xmjl = ?1",
           nativeQuery = true)
   Page<KcrwfqView> findByXmjl(String xmjl, PageRequest pageRequest);
}
