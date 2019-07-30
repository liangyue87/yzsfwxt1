package com.ljx.hfgsjt.main.dao;


import com.ljx.hfgsjt.entity.dto.XtglWkzbEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface WorkdayMapper {

    @Select("select id,to_char(day,'yyyy-mm-dd') day,work,week from XTGL_WKCB order by day desc")
    List<Map<String,Object>> getDaysList();

    @Select("select id,ams," +
            "amf," +
            "pms," +
            "pmf," +
            "to_char(kssj,'yyyy-mm-dd') kssj,to_char(jssj,'yyyy-mm-dd') jssj from XTGL_WKZB jssj order by kssj")
    List<XtglWkzbEntity> getWorkTime();

    @Select("insert into XTGL_WKCB(id,day,work,week) values (#{id},to_date(#{day},'yyyy-mm-dd'),#{work},#{week})")
    void saveDays(Map map);

    @Update("update XTGL_WKCB set day = to_date(#{day},'yyyy-mm-dd'),week = #{week},work = #{work} where id = #{id}")
    void updateDays(Map map);

    @Delete("delete XTGL_WKCB where id =#{id}")
    void deleteDays(String id);

    @Insert("insert into XTGL_WKZB(id,ams,amf,pms,pmf,kssj,jssj,czr,czsj)" +
            "values(#{id},#{ams},#{amf},#{pms},#{pmf},to_date(#{kssj},'yyyy-mm-dd'),to_date(#{jssj},'yyyy-mm-dd'),#{czr},sysdate)")
    void saveTime(XtglWkzbEntity xtglWkzbEntity);

    @Update("update XTGL_WKZB set ams = #{ams},amf = #{amf},pms = #{pms},pmf = #{pmf}," +
            "kssj = to_date(#{kssj},'yyyy-mm-dd'),jssj = to_date(#{jssj},'yyyy-mm-dd') where id = #{id}")
    void updateTime(XtglWkzbEntity xtglWkzbEntity);

    @Delete("delete from XTGL_WKZB where id = #{id}")
    void deleteTime(String id);


}
