package com.ljx.hfgsjt.zlgd.mapper;

import com.ljx.hfgsjt.entity.dto.Sjst.ProjectModel;
import com.ljx.hfgsjt.entity.dto.Sjst.SjstYhzl;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ZlgdMapper {
    int getProjectCount(ProjectModel model);

    List<ProjectModel> getProjectList(ProjectModel model);

    void getZlzl(Map<String, Object> params);

    List<SjstYhzl> getSjcgzl(@Param("xmid") String xmid,@Param("id") String id);

    List<SjstYhzl> getYszl(@Param("xmid")String xmid,@Param("id") String id);

    List<SjstYhzl> getHtzl(@Param("xmid")String xmid,@Param("id") String id);

    @Select("select role.USERID,role.ROLEID,g.ORGID,g.ORGNAME,g.BMFZRID\n" +
            "from XTGL_USERROLE role\n" +
            "join XTGL_ROLE_ORG org on role.ROLEID = org.ROLEID\n" +
            "join XTGL_ORG g on org.ORGID = g.ORGID\n" +
            "where role.USERID = #{userid}")
    Map<String, Object> getRoleidAndOrgidAndBmfzridByUserid(@Param("userid") String userid);
}
