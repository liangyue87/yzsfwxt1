package com.ljx.hfgsjt.zhys.mapper;

import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.Sjst.XzqyEntity;
import com.ljx.hfgsjt.entity.dto.zhys.XmUserEntity;
import com.ljx.hfgsjt.entity.dto.zhys.ZhysRwcbFjEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface XdzhysdMapper {

    XmInstance selectRwzbid(String xmid);
    XzqyEntity getQsmc(String qsbh);
    XmUserEntity getQs(String role);
    XmUserEntity getUser(String role);
    int selectXdzhysdCount(Map<String, Object> map);//列表数量

    int selectBmzl(String  zhyscbid);//列表数量
    List<ZhysRwcbFjEntity> selectBmzllist(Map<String, Object> map);//数据集合

    List<XmInstance> selectXdzhysdList(Map<String, Object> map);//数据集合

    @Select("select a.*,nvl(b.num,0) num from " +
            "(select userid,username from xtgl_user where userid in (select userid from xtgl_userrole where roleid = #{roleId})) a " +
            " left join " +
            "(select bmcyry,count(1) num from zhys_rwcb where ysbm = #{bmmc} group by bmcyry) b on a.userid = b.bmcyry " +
            " order by num")
    List<Map<String, Object>> getZpry(Map<String, Object> pd);

    @Update("update zhys_rwcb set bmcyry = #{name} where id = " +
            " (select c.id from zhys_rwcb c left join zhys_rwzb z on c.zhysrwzbid = z.id " +
            "  where z.zhyssqid = #{sqdid} and c.ysbm = #{bmmc} and z.xmid = #{xmid})")
    void updateCbBmcyry(Map<String, Object> pd);

    @Select("select * from zhys_rwcb c left join zhys_rwzb z on c.zhysrwzbid = z.id " +
            " where z.zhyssqid = #{sqdid} and c.ysbm = #{bmmc} and z.xmid = #{xmid}")
    List<Map<String, Object>> findZhyscbidByBmAndId(Map<String, Object> pd);

    @Insert("insert into zhys_rwcb_fj(id,zhyscbid,fjlx_yj,fjlx_ej,fjlj,fjhz,fjmc,scr,scsj ) " +
            " values (#{id},#{zhyscbid},#{fjlx_yj},#{fjlx_ej},#{fjlj},#{fjhz},#{fjmc},#{scr},sysdate)")
    void upoadFile(Map<String, Object> pd);

    @Update("update zhys_rwcb set sffk = '1',fksj = #{yssj},fkyj = #{bmyj} where id = " +
            " (select c.id from zhys_rwcb c left join zhys_rwzb z on c.zhysrwzbid = z.id " +
            "  where z.zhyssqid = #{sqdid} and c.ysbm = #{bmmc} and z.xmid = #{xmid})")
    void updateCbBmfkyj(Map<String, Object> pd);

    @Select("select * from xtgl_userrole where userid = #{userId}")
    List<Map<String, Object>> queryRoleIdByUserId(Map<String, Object> pd);

    @Select("select c.ysbm,to_char(c.fksj,'yyyy-MM-dd') fksj,c.fkyj from zhys_rwcb c " +
            " left join zhys_rwzb z on z.id = c.zhysrwzbid " +
            " where z.zhyssqid = #{sqdid} and z.xmid = #{xmid}")
    List<Map<String, Object>> getFkyj(Map<String, Object> pd);

    @Update("update zhys_rwzb set SJYSSJ = #{xmjlyssj},XMJLFKYJ=#{xmjlyj},FKJG='1' " +
            " where XMID=#{xmid} and ZHYSSQID=#{sqdid}")
    void updateZbXmjlfkyj(Map<String, Object> pd);

    @Update("update zhys_rwzb set SJYSSJ = #{xmjlyssj},XMJLFKYJ=#{xmjlyj},FKJG='0' " +
            " where XMID=#{xmid} and ZHYSSQID=#{sqdid}")
    void updateZbXmjlfkyjCh(Map<String, Object> pd);

    @Select("select f.fjlx_ej type,z.xmid,f.zhyscbid,count(1) num from zhys_rwcb_fj f left join zhys_rwcb c " +
            " on f.zhyscbid = c.id left join zhys_rwzb z on z.id = c.zhysrwzbid " +
            " where z.xmid = #{xmid} and z.zhyssqid = #{sqdid} group by f.fjlx_ej,z.xmid,f.zhyscbid")
    List<Map<String, Object>> getFkzl(Map<String, Object> pd);

    @Select("select a.roleid,r.roletype from wf_activity_role a left join xtgl_role r on a.roleid = r.roleid " +
            " where activityid = #{flag}")
    List<Map<String,Object>> queryRoleIdByActivityId(String flag);

    @Select("select a.ID,a.ZHYSCBID,a.FJLX_YJ,a.FJLX_EJ,a.FJLJ,a.FJHZ,a.FJMC,a.SCR,to_char(a.SCSJ,'yyyy-mm-dd hh24:mi:ss')SCSJ1,a.SCSJ \n" +
            "from zhys_rwcb_fj a\n" +
            "where a.ZHYSCBID = #{cbid}\n" +
            "  and a.FJLX_YJ = #{yj}\n" +
            "  and a.FJLX_EJ = #{ej}")
    List<ZhysRwcbFjEntity> getFkFjData(@Param("cbid")String cbid, @Param("yj")String yj, @Param("ej")String ej);

    @Delete("delete from ZHYS_RWCB_FJ j where j.ID = #{fjid}")
    void deleteFkFj(@Param("fjid") String fjid);

}
