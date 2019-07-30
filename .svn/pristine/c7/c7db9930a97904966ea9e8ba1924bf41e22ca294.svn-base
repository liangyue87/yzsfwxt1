package com.ljx.hfgsjt.sfkp.dao;

import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.Sjst.XmSb;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.htqd.GetXmlbJfEntity;
import com.ljx.hfgsjt.entity.sfkp.SfkpSftzdzbEntity;
import com.ljx.hfgsjt.entity.sfkp.SfkpldEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

public interface SfkpMapper {

    @Select("select s.*,x.username zdr_name ,t.username qrr_name,i.xmmc,p.mc pjlxmc from sfkp_sftzdzb s " +
            " left join xtgl_user x on s.zdr = x.userid " +
            " left join xtgl_user t on t.userid = s.jfqrr " +
            " left join xm_instance i on s.xmid = i.id " +
            " left join xtgl_pjlx p on s.pjlx = p.bh" +
            " where s.xmid =  #{xmid} order by s.zdsj desc")
    List<Map<String, Object>> getSftzd(Map<String,Object> pd);

    @Select("select * from xtgl_gskhyh where type = '缴费通知'")
    List<Map<String, Object>> getSkzh();

    @Select("select t.bh value,t.mc label from xtgl_fylx t order by bh")
    List<Map<String, Object>> getGcflm();

    @Insert("insert into sfkp_sftzdcb(id,sftzdzbid,tzdlx,ysje,zdr,zdsj) " +
            " values(#{id},#{sftzdzbid},#{tzdlx},#{ysje},#{zdr},sysdate)")
    void insertSftzdcb(Map<String, Object> cbpd);

    @Insert("insert into sfkp_sftzdzb(id,tzdbh,xmid,tzdzt,zhmc,zhyh,zh,ysje,zdr,zdsj,jfdw,bz,xmqk) " +
            " values(#{id},#{tzdbh},#{xmid},#{tzdzt},#{zhmc},#{zhyh},#{zh},#{ysje},#{zdr},sysdate,#{jfdw},#{bz},#{xmqk})")
    void insertSftzdzb(Map<String, Object> pd);

    @Update("update sfkp_sftzdzb set ysje = #{ysje},zdr=#{zdr},zdsj=sysdate,jfdw=#{jfdw},bz=#{bz},xmqk=#{xmqk}" +
            " where id = #{id}")
    void updateSftzdzb(Map<String, Object> pd);

    @Update("update sfkp_sftzdzb set tzdzt = 1,jfqrr = #{jfqrr},jfqrsj = sysdate,ssje=#{ssje},pjlx=#{pjlx}," +
            " pjh=#{pjh},skbz=#{bz},kprq=#{kprq} where id = #{zbid}")
    void updateSftzd(Map<String, Object> pd);

    @Select("<script>" +
            " select * from (select t.*,ROWNUM rn from (select t.* from get_xmlb_jf t where t.xmfzr=#{xmfzr}  " +
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
            "<when test=\"beginTime != null and beginTime != '' \">" +
            " and to_char(t.LXSJ,'yyyy-mm-dd') &gt;= #{beginTime}" +
            "</when>" +
            "<when test=\"endTime != null and endTime != '' \">" +
            " and to_char(t.LXSJ,'yyyy-mm-dd') &lt;= #{endTime}" +
            "</when>" +
            " order by t.lxsj desc) t) " +
            " WHERE rn &gt; #{pagebefor} AND rn &lt;= #{pageafter}" +
            "</script>")
    List<GetXmlbJfEntity> getJfList(Map<String, Object> pd);

    @Select("<script>" +
            " select t.*,ROWNUM rn from get_xmlb_jf t where t.xmfzr=#{xmfzr} " +
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
            "<when test=\"beginTime != null and beginTime != '' \">" +
            " and to_char(t.LXSJ,'yyyy-mm-dd') &gt;= #{beginTime}" +
            "</when>" +
            "<when test=\"endTime != null and endTime != '' \">" +
            " and to_char(t.LXSJ,'yyyy-mm-dd') &lt;= #{endTime}" +
            "</when>" +
            "</script>")
    List<GetXmlbJfEntity> getAllJfList(Map<String, Object> pd);

    @Select("select t.bh value,t.mc label from xtgl_kj t")
    List<Map<String, Object>> getZbkjList();

    @Select("select x.* from get_jfgsb x where x.xm_id = #{xmid} order by x.hh")
    List<XmSb> queryGsbByXmid(Map<String, Object> pd);

    @Select("select x.* from get_jfgsb x where x.xm_id = #{xmid}")
    List<XmSb> queryAllGsbByXmid(Map<String, Object> pd);

    @Select("select m.*,case when n.id is null then 'wgx' else 'ygx' end gxzt,n.sftzdzbid" +
            "   from get_jfgsbs m left join (select s.*,c.sftzdzbid from sfkp_sftzdcbsb s left join sfkp_sftzdcb c " +
            "  on s.sftzdcbid = c.id " +
            "  where s.SFTZDCBID is not null and s.ZZAZ = 0 and s.sblxbh = 'DB') n " +
            "  on m.id = n.sbid where m.XM_ID = #{xmid} order by m.hh")
    List<XmSb> queryGsbByXmids(Map<String, Object> pd);

    @Select("select m.*,case when n.id is null then 'wgx' else 'ygx' end gxzt " +
            " from get_jfgsbs m left join (select s.* from sfkp_sftzdcbsb s left join sfkp_sftzdcb c " +
            " on s.sftzdcbid = c.id " +
            " where s.SFTZDCBID is not null and s.ZZAZ = 0 and s.sblxbh = 'DB' and c.sftzdzbid = #{tzdid} ) n " +
            " on m.id = n.sbid where m.XM_ID = #{xmid}")
    List<XmSb> queryAllGsbByXmids(Map<String, Object> pd);

    @Update("update xm_sb set gcfysje = #{gcfysje} where id = #{id}")
    void updateSbgcfBySbid(Map<String, Object> pd);

    @Update("update xm_sb set gcfysje = #{gsysje} where id in ${sbids} and kjbh = #{zbkj}")
    void gspldjxg(Map<String, Object> pd);

    @Select("select s.hhlxbh,sum(s.gcfysje) gsje from xm_sb s where s.id in ${sbids} group by s.hhlxbh ")
    List<Map<String, Object>> gsjezj(Map<String, Object> pd);

    @Select("select s.id,s.gcfysje from xm_sb s where s.id in ${sfkpgsbid}")
    List<Map<String, Object>> gssbje(Map<String, Object> pd);

    @Select("select s.hhlxbh,sum(s.gcfysje) hbje from xm_sb s where s.id in ${sbids} group by s.hhlxbh ")
    List<Map<String, Object>> hbjezj(Map<String, Object> pd);

    @Select("select * from get_jfld where xmid = #{xmid}")
    List<SfkpldEntity> queryLdByxmid(String xmid);

    @Select("select sum(t.ZHS) zhs,sum(t.yjsl) yjs from get_jfld t where t.XMID = #{xmid} group by t.XMID")
    List<Map<String, Object>> querymxByxmid(String xmid);

    @Select("select * from get_jfldsb where ldid = #{ldid} and xm_id = #{xmid} order by hh")
    List<XmSb> querySbByldid(@Param("xmid") String xmid,@Param("ldid") String ldid);

    @Insert("insert into sfkp_sftzdcbsb(id,sblxbh,ldid,zzaz,sbid) " +
            " values(#{id},'HB',#{ldid},0,#{sbid})")
    void insertSftzdcbsb(Map<String, Object> pd);

    @Delete("delete from sfkp_sftzdcbsb where LDID = #{ldid} and SBLXBH = 'HB' and SFTZDCBID is null")
    void deleteSfsbByldid(Map<String, Object> pd);

    @Select("<script>" +
//            " select * from (" +
            " select g.*,ROWNUM rn from(select g.* from get_jfgxsb g where 1=1 " +
            "<when test=\"hbld != null and hbld != '' \">" +
            " and g.ldid = #{hbld} " +
            "</when>" +
            "<when test=\"hbzbkj != null and hbzbkj != '' \">" +
            " and g.kjbh = #{hbzbkj} " +
            "</when>" +
            "<when test=\"tzdid != null and tzdid != '' \">" +
            " union all " +
            " select s.* from get_jfgxsbs s left join sfkp_sftzdcbsb z on s.ID = z.sbid left join sfkp_sftzdcb c " +
            " on z.sftzdcbid = c.id where c.sftzdzbid = #{tzdid}" +
            "</when>" +
            " ) g where g.XM_ID = #{xmid} order by g.hh" +
//            " )" +
//            " WHERE rn &gt; #{pagebefor} AND rn &lt;= #{pageafter}" +
            "</script>")
    List<XmSb> queryHb(Map<String, Object> pd);

    @Select("<script>" +
            " select * from (select * from get_jfgxsb g" +
            "<when test=\"tzdid != null and tzdid != '' \">" +
            " union all " +
            " select s.* from get_jfgxsbs s left join sfkp_sftzdcbsb z on s.ID = z.sbid left join sfkp_sftzdcb c " +
            " on z.sftzdcbid = c.id where c.sftzdzbid = #{tzdid}" +
            "</when>" +
            ") g where g.XM_ID = #{xmid}" +
            "</script>")
    List<XmSb> queryallHb(Map<String, Object> pd);

    @Select("<script>" +
            "select g.*,ROWNUM rn from(select g.* from get_jfgxsb g where 1=1 " +
            "<when test=\"hbld != null and hbld != '' \">" +
            " and g.ldid = #{hbld} " +
            "</when>" +
            "<when test=\"hbzbkj != null and hbzbkj != '' \">" +
            " and g.kjbh = #{hbzbkj} " +
            "</when>" +
            "<when test=\"tzdid != null and tzdid != '' \">" +
            " union all " +
            " select s.* from get_jfgxsbs s left join sfkp_sftzdcbsb z on s.ID = z.sbid left join sfkp_sftzdcb c " +
            " on z.sftzdcbid = c.id where c.sftzdzbid = #{tzdid}" +
            "</when>" +
            " ) g where g.XM_ID = #{xmid}" +
            "</script>")
    List<XmSb> queryAllHb(Map<String, Object> pd);

    @Select("<script>" +
            "select s.ldid value,x.ldbh label from sfkp_sftzdcbsb s left join xm_ld x on s.ldid = x.id " +
            " where s.sftzdcbid is null and s.sblxbh = 'HB' group by x.ldbh,s.ldid " +
            "<when test=\"tzdid != null and tzdid != '' \">" +
            " union all " +
            " select s.ldid value,x.ldbh label from sfkp_sftzdcbsb s left join xm_ld x on s.ldid = x.id " +
            " left join sfkp_sftzdcb c on c.id = s.sftzdcbid left join sfkp_sftzdzb z on z.id = c.sftzdzbid " +
            " where c.sftzdzbid = #{tzdid} and s.sblxbh = 'HB' and z.xmid = #{xmid} " +
            " group by x.ldbh,s.ldid" +
            "</when>" +
            "</script>")
    List<Map<String, Object>> queryLd(Map<String, Object> pd);

    @Update("update sfkp_sftzdcbsb set YSJE = #{gcfysje},sftzdcbid = #{sftzdcbid} " +
            " where sbid = #{id} and sblxbh = 'HB' and sftzdcbid is null")
    void updateJfcbgcfBySbid(Map<String, Object> pd);

    @Delete("delete from sfkp_sftzdcbsb where sblxbh = 'HB' and sftzdcbid is null")
    void deleteJfhb();

    @Insert("insert into sfkp_sftzdcbsb(id,sftzdcbid,sblxbh,ysje,zzaz,sbid) " +
            " values(#{id},#{sftzdcbid},'DB',#{ysje},0,#{sbid})")
    void saveJfgssb(Map<String, Object> pd);

    @Select("select bh from xtgl_fylx where mc = #{label}")
    List<String> querybhBymc(Map<String, Object> cbpd);

    @Update("update sfkp_sftzdcbsb set sftzdcbid = #{id},tzdlx = #{tzdlx} where sftzdcbid is null and sblxbh='DB'")
    void updateJfcbgsb(Map<String, Object> cbpd);

    @Update("update sfkp_sftzdcbsb set sftzdcbid = #{id},tzdlx = #{tzdlx} where sftzdcbid is null and sblxbh='HB'")
    void updateJfcbhb(Map<String, Object> cbpd);

    @Delete("delete from sfkp_sftzdcbsb where sblxbh = 'DB' and sftzdcbid is null")
    void deleteJfgsb();

    @Select("select * from sfkp_sftzdzb where id = #{tzdid}")
    List<SfkpSftzdzbEntity> queryByTzdid(String tzdid);

    @Select("select s.ysje value,f.mc label from sfkp_sftzdcb s left join xtgl_fylx f on s.tzdlx = f.bh " +
            " where s.sftzdzbid = #{tzdid}")
    List<Map<String, Object>> queryCbByTzdid(String tzdid);

    @Delete("delete from sfkp_sftzdcb where SFTZDZBID = #{id}")
    void deleteSftzdcb(Map<String, Object> pd);

    @Select("select x.bh,x.mc,nvl(y.tag,'暂无预算金额') tag from xtgl_fylx x left join (" +
            " select f.YSFL,f.mc,'预算总金额'||f.YSJE||'元，已开单据'||f.ykdj||'元，已到账'||f.ydz||'元' tag " +
            " from get_jffylx f where f.mc in ${lx} and f.xmid = #{xmid}) y " +
            " on x.bh = y.ysfl where x.mc in ${lx} order by x.bh")
    List<Map<String, Object>> querylxByname(Map<String, Object> pd);

    @Select("select x.bh,x.mc,nvl(y.tag,'暂无预算金额') tag from xtgl_fylx x left join (" +
            " select f.YSFL,f.mc,'预算总金额'||f.YSJE||'元，已开单据'||f.ykdj||'元，已到账'||f.ydz||'元' tag " +
            " from get_jffylx f where f.mc = #{lx} and f.xmid = #{xmid}) y " +
            " on x.bh = y.ysfl where x.mc = #{lx} order by x.bh")
    List<Map<String, Object>> querylxBynames(Map<String, Object> pd);

    @Select("select x.bh value,x.mc label from xtgl_pjlx x ")
    List<Map<String, Object>> querypjlx();

    @Insert("insert into sfkp_sftzdzbfj(id,sftzdzbid,fjlx_yj,fjlx_ej,fjlj,fjhz,fjmc,scr,scsj,sc_flag)" +
            " values(#{id},#{sftzdzbid},#{fjlx_yj},#{fjlx_ej},#{fjlj},#{fjhz},#{fjmc},#{scr},sysdate,0)")
    void importPz(Map<String, Object> pd);

    @Select("select x.*,t.codename sqlxname from xm_instance x " +
            " left join (select * from xtgl_code where " +
            " parentid  = (select id from xtgl_code t where t.type = 'sqlx')) t on t.codevalue = x.sqlx " +
            " where x.id = #{xmid}")
    List<XmInstanceEntity> findXmByid(Map<String, Object> pd);

    @Select("select * from xm_instance e where e.ID=#{xmid}")
    List<XmInstanceEntity> getjbxx(@Param("xmid") String xmid);

    @Select("select t.*,u.username,to_char(t.scsj,'yyyy-MM-dd') cjsj from sfkp_sftzdzbfj t " +
            " left join xtgl_user u on t.scr = u.userid where t.sftzdzbid = #{zbid}")
    List<Map<String, Object>> queryZjqtfjById(Map<String, Object> pd);

    @Delete("delete from sfkp_sftzdzbfj where id=#{fjId}")
    void deleteFjByid(Map<String, Object> pd);

    @Select("select * from XTGL_USER u where u.USERID = #{userid}")
    UserEntity getUserByUserId(@Param("userid") String userid);

    @Select("call exec_after_jfqr(#{v_tzdid,mode=IN,jdbcType=VARCHAR})")
    @Options(statementType = StatementType.CALLABLE)
    void ExecAfterJfqr(Map<String, Object> map);

    @Select("select s.*,u.username from sfkp_sftzdzb s left join xtgl_user u on s.jfqrr = u.userid where id = #{zbid}")
    List<Map<String, Object>> querySfzbByid(String zbid);

    @Select("select * from xm_instance where id =#{xmid}")
    List<XmInstance> getxmdz(String xmid);

    @Select("select s.id from sfkp_sftzdcb s where s.sftzdzbid = #{id} and s.tzdlx = '06'")
    List<String> queryGsCbid(String id);

    @Select("select s.id from sfkp_sftzdcb s where s.sftzdzbid = #{id} and s.tzdlx = '05'")
    List<String> queryJmCbid(String id);

    @Delete("delete from sfkp_sftzdcbsb where sftzdcbid = #{cbid} and sblxbh = #{sblx}")
    void deleteSftzdcbsb(@Param("cbid") String cbid,@Param("sblx") String sblx);

    @Select("select sbid from sfkp_sftzdcbsb where sftzdcbid = #{cbid} and sblxbh = 'DB'")
    List<String> querygssbid(String cbid);

    @Select("select sbid from sfkp_sftzdcbsb where sftzdcbid = #{cbid} and sblxbh = 'HB'")
    List<String> queryjmsbid(String cbid);

    @Update("update sfkp_sftzdcbsb set sftzdcbid = #{cbid} where sbid = #{sbid} and sblxbh = 'DB'")
    void updategssbcbid(String sbid, String cbid);

    @Update("update sfkp_sftzdcbsb set sftzdcbid = #{cbid} where sbid = #{sbid} and sblxbh = 'HB'")
    void updatejmsbcbid(String sbid, String cbid);

    @Select("select * from xm_instance where id = #{xmid}")
    List<XmInstance> findXmByxmid(String xmid);

    @Select("select s.* from sfkp_sftzdzb s where s.xmid = #{xmid} order by s.zdsj desc")
    List<Map<String, Object>> findSftzd(String xmid);

    @Select("select to_char(wmsys.wm_concat(decode(sb.hhlxbh,'HB','居民表Φ','DB','工商表Φ','五级计量表Φ')||kj.MC||'×'||count(1))) a from SFKP_SFTZDZB zb\n" +
            "join SFKP_SFTZDCB cb on zb.id=cb.sftzdzbid\n" +
            "join SFKP_SFTZDCBSB sb1 on cb.id=sb1.sftzdcbid\n" +
            "join XM_SB sb on sb1.SBID = sb.ID\n" +
            "join XTGL_KJ kj on sb.KJBH = kj.BH\n" +
            "where zb.ID = #{id}\n" +
            "group by sb.hhlxbh,kj.MC")
    List<Map<String, Object>> getGsbTotalByXmid(@Param("xmid") String xmid, @Param("id") String id);
}
