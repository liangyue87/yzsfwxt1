package com.ljx.hfgsjt.zhcx.mapper;


import com.ljx.hfgsjt.entity.zhcx.Wtysqk;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lkh on 2019/5/7.
 */
public interface WtysMapper {
    List<Wtysqk> selectWtysList(@Param("xm") String xm, @Param("start") Integer start, @Param("end") Integer end, @Param("wtyszt") Integer wtyszt);
}
