package com.ljx.hfgsjt.repository.xkylx;

import com.ljx.hfgsjt.entity.dto.xkybz.XckcFkQsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface XckcFkQsRepository extends JpaRepository<XckcFkQsEntity, String>,JpaSpecificationExecutor<XckcFkQsEntity>, Serializable {

    @Query(value = "SELECT * FROM XCKC_FK_QS a WHERE kcrwcbid = ?1 order by dcrq",nativeQuery = true)
    List<XckcFkQsEntity> findByKcrwcbid(String kcrwcbid);

}
