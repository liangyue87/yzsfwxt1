package com.ljx.hfgsjt.repository.yswt;

import com.ljx.hfgsjt.entity.dto.yswt.YsRwzb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface YsRwzbRepository extends JpaRepository<YsRwzb, String>, JpaSpecificationExecutor<YsRwzb> {

    @Query(value = "select id from YsRwzb where xmid = ?1 ")
    String  findYszb(String xmid);

    @Modifying(clearAutomatically = true)
    @Query(value = "update  YsRwzb  set yszt=:zt where id = :id ")
    void updateYszt(@Param("zt") String zt,@Param("id")String id);

    @Modifying(clearAutomatically = true)
    @Query(value = "update  YsRwzb  set ysjssj =:sj,yszt=:zt,sfsh=:sh,ysrybz=:bz where id = :id ")
    void updateYszb(@Param("id") String id,
                    @Param("sj")java.util.Date sj,
                    @Param("zt")String zt,
                    @Param("sh")String sh,
                    @Param("bz")String bz);

    @Modifying(clearAutomatically = true)
    @Query(value = "update  YsRwzb  set ysjssj =:sj,yszt=:zt,ysrybz=:bz where id = :id ")
    void updateYszbYsbc(@Param("id") String id,
                    @Param("sj")java.util.Date sj,
                    @Param("zt")String zt,
                    @Param("bz")String bz);

    @Modifying(clearAutomatically = true)
    @Query(value = "update  YsRwzb  set sfsh =:sfsh,shsj=:sj,shr=:shr,shyj=:bz where id = :id ")
    void updateYszb(@Param("id") String id,
                    @Param("sfsh")String sfsh,
                    @Param("sj") Date sj,
                    @Param("shr")String shr,
                    @Param("bz")String bz);

    @Modifying(clearAutomatically = true)
    @Query(value = "update  YsRwzb  set yszt =:yszt where id = :id ")
    void updateYszbShbtg(@Param("id") String id, @Param("yszt") String yszt);


}


