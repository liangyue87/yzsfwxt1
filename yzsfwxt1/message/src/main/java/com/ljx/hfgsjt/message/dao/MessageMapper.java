package com.ljx.hfgsjt.message.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface MessageMapper {

    @Select("select * from DX_MBPZ where id = #{id}")
    Map<String,Object> getDxMb(@Param("id") String id);

    @Select("select * from XTGL_USER where userid = #{userid}")
    Map<String,Object> getUserxxById(String userid);

    @Insert("insert into DX_TIMER(ID,TIME) VALUES (#{id},SYSDATE)")
    void dxTimer(String id);
}
