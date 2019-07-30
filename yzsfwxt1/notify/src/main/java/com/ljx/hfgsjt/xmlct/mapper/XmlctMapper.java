package com.ljx.hfgsjt.xmlct.mapper;

import com.ljx.hfgsjt.entity.dto.Sjst.TaskEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.sfkp.SfkpSftzdzbEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface XmlctMapper {

    List<TaskEntity> getXmlctDqjd(String xmid);
    List<TaskEntity> getXmlctLsjd(String xmid);

    List<TaskEntity> selectXmlctDq(@Param("xmid") String xmid, @Param("pdefid") String pdefid);
    List<TaskEntity> selectXmlctLs(@Param("xmid") String xmid, @Param("pdefid") String pdefid);

    int getXmlbCount(XmInstance xmInstance);
    List<XmInstance> getXmlbList(XmInstance xmInstance);

    List<SfkpSftzdzbEntity>getSfkpList(String xmid);

    @Select("select role.USERID,role.ROLEID,g.ORGID,g.ORGNAME,g.BMFZRID\n" +
            "from XTGL_USERROLE role\n" +
            "join XTGL_ROLE_ORG org on role.ROLEID = org.ROLEID\n" +
            "join XTGL_ORG g on org.ORGID = g.ORGID\n" +
            "where role.USERID = #{userid}")
    Map<String, Object> getRoleidAndOrgidAndBmfzridByUserid(@Param("userid") String userid);

    @Select("select * from V_XMLCT where ID = #{xmid}")
    Map<String, Object> getXmmcByid(@Param("xmid") String xmid);
}
