package com.ljx.hfgsjt.zhcx.mapper;

import com.ljx.hfgsjt.entity.dto.BaseEntity;
import com.ljx.hfgsjt.entity.zhcx.zhyslb;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by lkh on 2019/5/7.
 */
public interface ZhysMapper {
    List<zhyslb> selectZhysList(@Param("xm") String xm, @Param("start") Integer start, @Param("end") Integer end, @Param("yszt") Integer yszt);

    List<Map<String,Object>> getSbxxList(BaseEntity baseEntity);
    int getSbxxTotal(BaseEntity baseEntity);
}
