package com.ljx.hfgsjt.gcpd.dao;

import com.ljx.hfgsjt.entity.dto.Activity.WfHiInstContentEntity;
import com.ljx.hfgsjt.entity.dto.gcpd.YshgFjEntity;
import com.ljx.hfgsjt.entity.dto.gcpd.YshgRwbEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GcpdMapper {

    @Select("select t.* from YSHG_RWB t where t.xmid=#{xmid}")
    YshgRwbEntity getYshgRwbByXmid(@Param("xmid") String xmid);

    @Update("update YSHG_RWB set ysfw = #{ysfw},xmlx=#{xmlx},ysnr=#{ysnr},cbyj=#{cbyj},ysjl=#{ysjl} where id = #{id} ")
    void updateYshgRwbEntity(@Param("id") String id, @Param("ysfw") String ysfw, @Param("xmlx") String xmlx, @Param("ysnr") String ysnr,
                             @Param("cbyj") String cbyj, @Param("ysjl") String ysjl);

    @Select("select count(*) from YSHG_RWB_FJ t where t.yshgrwbid= #{zbid} and t.fjhz = '.jpg'")
    int getPsTotals( @Param("zbid") String zbid);

    @Select("select t.fjlj from YSHG_RWB_FJ t where t.yshgrwbid= #{zbid} and t.fjhz = '.jpg'")
    List<YshgFjEntity> getAllYshgFjFjlj(@Param("zbid") String zbid);

    @Delete("delete from YSHG_RWB_FJ b where b.yshgrwbid = #{zbid} and b.fjlj= #{fjlj}")
    void deleteImage(@Param("zbid") String zbid, @Param("fjlj") String fjlj);

    @Delete("delete from YSHG_RWB_FJ b where b.id = #{fjid}")
    void deleteFkFj(@Param("fjid")String fjid);

    @Select("select *\n" +
            "from WF_HI_INST_CONTENT n\n" +
            "left join WF_HI_INST s on n.PROC_INST_ID = s.PROC_INST_ID\n" +
            "where CONTENTVALUE = #{xmid} and n.CONTENTNAME = 'id' and n.CONTENTTYPE = 'gcpd'")
    WfHiInstContentEntity getwfHiInstContent(@Param("xmid") String xmid);
}
