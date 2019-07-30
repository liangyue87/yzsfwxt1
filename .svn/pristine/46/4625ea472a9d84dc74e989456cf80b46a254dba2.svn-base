package com.ljx.hfgsjt.main.dao;

import com.ljx.hfgsjt.entity.dto.DepartmentEntity;
import org.apache.ibatis.annotations.Select;

public interface DepartmentDao {

    @Select("select x.*,u.USERNAME bmfzr\n" +
            "from XTGL_ORG x\n" +
            "left join XTGL_USER u on x.BMFZRID = u.USERID\n" +
            "where x.ORGID = #{id}")
    DepartmentEntity getDepartmentById(String id);
}
