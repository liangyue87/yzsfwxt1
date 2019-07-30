package com.ljx.hfgsjt.htqd.dao;

import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.htqd.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

public interface HtbzMapper {

    @Insert("insert into ht_qdzbfj(id,htqdid,fjlx_yj,fjlx_ej,fjlj,fjhz,fjmc,scr,scsj)" +
            " values(#{id},#{htqdid},#{fjlx_yj},#{fjlx_ej},#{fjlj},#{fjhz},#{fjmc},#{scr},sysdate)")
    void upoadFile(Map<String, Object> pd);

    @Select("<script>" +
            " select t.*,ROWNUM rn from " +
            " (select h.*,z.jfdw from get_xmlb_ht h left join ht_qdzb z on h.ID = z.xmid) t where t.xmfzr=#{xmfzr} " +
            "<when test=\"xmbh != null and xmbh != '' \">" +
            " and t.xmbh LIKE CONCAT(CONCAT('%', #{xmbh}), '%') " +
            "</when>" +
            "<when test=\"xmmc != null and xmmc != '' \">" +
            " and t.xmmc LIKE CONCAT(CONCAT('%', #{xmmc}), '%') " +
            "</when>" +
            "<when test=\"lxr != null and lxr != '' \">" +
            " and t.lxr LIKE CONCAT(CONCAT('%', #{lxr}), '%') " +
            "</when>" +
            "<when test=\"xmdz != null and xmdz != '' \">" +
            " and t.XMDZ LIKE CONCAT(CONCAT('%', #{xmdz}), '%') " +
            "</when>" +
            "<when test=\"azlxname != null and azlxname != '' \">" +
            " and t.VAZLX = #{azlxname}" +
            "</when>" +
            "<when test=\"jsdw != null and jsdw != '' \">" +
            " and t.dwmc LIKE CONCAT(CONCAT('%', #{jsdw}), '%') " +
            "</when>" +
            "<when test=\"jfdw != null and jfdw != '' \">" +
            " and t.jfdw LIKE CONCAT(CONCAT('%', #{jfdw}), '%') " +
            "</when>" +
            "<when test=\"beginTime != null and beginTime != '' \">" +
            " and to_char(t.LXSJ,'yyyy-mm-dd') &gt;= #{beginTime}" +
            "</when>" +
            "<when test=\"endTime != null and endTime != '' \">" +
            " and to_char(t.LXSJ,'yyyy-mm-dd') &lt;= #{endTime}" +
            "</when>" +
            "<when test=\"sfbz == 0 \">" +
            " and t.jfdw is null" +
            "</when>" +
            "<when test=\"sfbz == 1 \">" +
            " and t.jfdw is not null" +
            "</when>" +
            "</script>")
    List<GetXmlbHtEntity> getAllHtList(Map<String, Object> pd);

    @Select("<script>" +
            " select * from (select t.*,ROWNUM rn from (select t.* from " +
            " (select h.*,z.jfdw from get_xmlb_ht h left join ht_qdzb z on h.ID = z.xmid) t where t.xmfzr=#{xmfzr}  " +
            "<when test=\"xmbh != null and xmbh != '' \">" +
            " and t.xmbh LIKE CONCAT(CONCAT('%', #{xmbh}), '%') " +
            "</when>" +
            "<when test=\"xmmc != null and xmmc != '' \">" +
            " and t.xmmc LIKE CONCAT(CONCAT('%', #{xmmc}), '%') " +
            "</when>" +
            "<when test=\"lxr != null and lxr != '' \">" +
            " and t.lxr LIKE CONCAT(CONCAT('%', #{lxr}), '%') " +
            "</when>" +
            "<when test=\"xmdz != null and xmdz != '' \">" +
            " and t.XMDZ LIKE CONCAT(CONCAT('%', #{xmdz}), '%') " +
            "</when>" +
            "<when test=\"azlxname != null and azlxname != '' \">" +
            " and t.VAZLX = #{azlxname}" +
            "</when>" +
            "<when test=\"jsdw != null and jsdw != '' \">" +
            " and t.dwmc LIKE CONCAT(CONCAT('%', #{jsdw}), '%') " +
            "</when>" +
            "<when test=\"jfdw != null and jfdw != '' \">" +
            " and t.jfdw LIKE CONCAT(CONCAT('%', #{jfdw}), '%') " +
            "</when>" +
            "<when test=\"beginTime != null and beginTime != '' \">" +
            " and to_char(t.LXSJ,'yyyy-mm-dd') &gt;= #{beginTime}" +
            "</when>" +
            "<when test=\"endTime != null and endTime != '' \">" +
            " and to_char(t.LXSJ,'yyyy-mm-dd') &lt;= #{endTime}" +
            "</when>" +
            "<when test=\"sfbz == 0 \">" +
            " and t.jfdw is null " +
            "</when>" +
            "<when test=\"sfbz == 1 \">" +
            " and t.jfdw is not null " +
            "</when>" +
            " order by t.lxsj desc) t) " +
            " WHERE rn &gt; #{pagebefor} AND rn &lt;= #{pageafter}" +
            "</script>")
    List<GetXmlbHtEntity> getHtList(Map<String, Object> pd);

    @Select("select * from get_htxm where xmbh = #{xmbh}")
    List<GetHtxmEntity> findXmxxByXmbh(String xmbh);

    @Select("select t.*,c.codename azlxname,to_char(t.lxsj,'yyyy-mm-dd') lxsjname from xm_instance t " +
            " left join (select * from xtgl_code where parentid in " +
            " (select id from xtgl_code where parentid = (select id from xtgl_code c where c.type = 'azlx'))) c " +
            " on t.azlx = c.codevalue where t.id = #{xmid}")
    List<XmInstanceEntity> queryxmxxByid(String xmid);

    @Select("select * from xtgl_gskhyh")
    List<Map<String, Object>> queryYjsfzh();

    @Select("select h.*,'' bz from xtgl_htjsfs h")
    List<JsfsEntity> queryJsfs();

    @Select("select t.*,u.username,to_char(t.scsj,'yyyy-MM-dd') cjsj from ht_qdzbfj t " +
            " left join xtgl_user u on t.scr = u.userid where t.htqdid = #{htzbid}")
    List<Map<String, Object>> queryZjqtfjById(Map<String, Object> pd);

    @Delete("delete from ht_qdzbfj where id=#{fjId}")
    void deleteFjByid(Map<String, Object> pd);

    @Select("select f.mc,c.ysfl,c.ysje,c.bz from ys_rwcb c left join ys_rwzb z on c.ysrwzbid = z.id " +
            " left join xtgl_fylx f on c.ysfl = f.bh where z.xmid=#{xmid}")
    List<Map<String, Object>> queryYslist(String xmid);

    @Insert("insert into ht_qdzb(id,xmid,htbh,htqdsj,gqzrlts,jfdw,gcmc,gcdz,gcjsfs,gczje,jfkhh,yhzh,bzrbh,htlx,qrsj,zffs,fqs,htzt,cjsj) " +
            " values (#{id},#{xmid},#{htbh},#{htqdsj},#{gqzrlts},#{jfdw},#{gcmc},#{gcdz},#{gcjsfs},#{gczje},#{jfkhh},#{yhzh},#{bzrbh},1,#{qrsj}," +
            "#{zffs},#{fqs},1,sysdate)")
    void saveHt(Map<String, Object> pd);

    @Update("update ht_qdzb set htqdsj=#{htqdsj},gqzrlts=#{gqzrlts},jfdw=#{jfdw},gcmc=#{gcmc},gcdz=#{gcdz},gcjsfs=#{gcjsfs}," +
            " gczje=#{gczje},jfkhh=#{jfkhh},yhzh=#{yhzh},bzrbh=#{bzrbh},qrsj=#{qrsj},zffs=#{zffs},fqs=#{fqs},cjsj=sysdate " +
            " where id = #{editzbid}")
    void updateHt(Map<String, Object> pd);

    @Insert("insert into HT_FYCB(id,htzbid,htfl,htje,bz) values (#{id},#{htzbid},#{htfl},#{htje},#{bz})")
    void savefycb(Map<String, Object> cbpd);

    @Select("call get_htbh(#{v_stop_run,mode=IN,jdbcType=INTEGER},#{p_result,mode=OUT,jdbcType=VARCHAR})")
    @Options(statementType = StatementType.CALLABLE)
    String texuChange(Map<String, Object> map);

    @Insert("insert into HT_FQB(id,htzbid,qs,zje,perce) values (#{id},#{htzbid},#{qs},#{zje},#{perce})")
    void saveFqb(FqbEntity fqbEntity);

    @Insert("insert into HT_FQMXB(id,fqzbid,fqfl,fqje,fqperce,mc) values (#{id},#{fqzbid},#{fqfl},#{fqje},#{fqperce},#{mc})")
    void saveFqbmx(FqmxbEntity fqmxbEntity);

    @Select("select * from HT_FQB t where t.htzbid = #{htzbid}")
    List<FqbEntity> findFqb(String htzbid);

    @Select("select t.*,t.fqje ysje from HT_FQMXB t where t.fqzbid = #{fqzbid}")
    List<FqmxbEntity> findFqmxb(String fqzbid);

    @Select("select * from HT_FQB t where t.htzbid = #{htzbid}")
    List<FqbEntity> getFqbByHtzbid(String htzbid);

    @Delete("delete from HT_FQB where htzbid = #{htzbid}")
    void deleteFqbByHtzbid(String htzbid);

    @Delete("delete from HT_FQMXB where fqzbid = #{fqzbid}")
    void deleteFqmxbByFqzbid(String fqzbid);

    @Select("select s.*,to_char(s.htqdsj,'yyyy-MM-dd') qdsj,to_char(s.qrsj,'yyyy-MM-dd') tgsj from ht_qdzb s where s.xmid = #{xmid}")
    List<Map<String, Object>> queryHtbz(String xmid);

    @Delete("delete from ht_fycb f where f.htzbid = #{editzbid}")
    void deleteHtfy(Map<String, Object> pd);

    @Update("update xm_instance x set x.xmlb = #{xmlb}")
    void updateXmlbtoXMb(Map<String, Object> pd);

    @Select("select * from xm_instance where id = #{xmid}")
    List<XmInstance> findXmxxByxmid(String xmid);
}
