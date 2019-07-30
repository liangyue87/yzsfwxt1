package com.ljx.hfgsjt.yj.dao;

import com.ljx.hfgsjt.entity.yj.XmLd;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Ant on 2019/5/9.
 */
public interface LdMapper {
    int getLdCount(String xmid);

    List<XmLd> getLdData(@Param("xmid") String xmid,@Param("startRow") int startRow,@Param("endRow") int endRow);

    List<XmLd> LddzjData(String id);

    List<XmLd> LdhxData(String id);
}
