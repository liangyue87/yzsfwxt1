package com.ljx.hfgsjt.repository.yswt;

import com.ljx.hfgsjt.entity.dto.yswt.YsYszy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface YsYszyRepository extends JpaRepository<YsYszy,String> , JpaSpecificationExecutor<YsYszy> {

    @Query(value = "select count(*) from  YS_RWZB b where  b.YSR = ?1 and (b.yszt = '0' or b.yszt = '1' or b.yszt = '2') ",
            nativeQuery = true
            )
    Long countYsZpry(String UserId);
}
