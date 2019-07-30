package com.ljx.hfgsjt.repository.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UtilMapper {

    @Select("select date1 from HTGL_JJR_NEW t where t.year=#{year} and jjr='1' and t.zm is null order by date1 asc")
    List<String> queryDate(int year);

    @Select("select date1 from HTGL_JJR_NEW t where t.year=#{year} and bb='1' or t.zm='1' and t.zm is null order by date1 asc")
    List<String> queryBb(int year);

    @Select("select to_char(t.day,'mm-dd') date_str from xtgl_wkcb t where work = '0' and to_char(t.day,'yyyy') = #{year} order by day asc")
    List<String> findWorkDate(int year);

    @Select("select to_char(t.day,'mm-dd') date_str from xtgl_wkcb t where work = '1' and to_char(t.day,'yyyy') = #{year} order by day asc")
    List<String> findFreeDate(int year);

    @Select("select to_char(to_date(t.ams,'hh24:mi:ss'),'hh24') amsh," +
                "to_char(to_date(t.amf,'hh24:mi:ss'),'mi') amsm," +
                "to_char(to_date(t.amf,'hh24:mi:ss'),'hh24') amfh," +
                "to_char(to_date(t.ams,'hh24:mi:ss'),'mi') amfm," +
                "to_char(to_date(t.pms,'hh24:mi:ss'),'hh24') pmsh," +
                "to_char(to_date(t.pms,'hh24:mi:ss'),'mi') pmsm," +
                "to_char(to_date(t.pmf,'hh24:mi:ss'),'hh24') pmfh," +
                "to_char(to_date(t.pmf,'hh24:mi:ss'),'mi') pmfm,rownum" +
                " from xtgl_wkzb t where kssj <= sysdate and jssj >= sysdate and rownum = 1")
    Map<String,Object> findWorkTime();
}
