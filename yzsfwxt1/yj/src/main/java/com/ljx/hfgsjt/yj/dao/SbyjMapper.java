package com.ljx.hfgsjt.yj.dao;

import com.ljx.hfgsjt.entity.dto.BaseEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.XmSb;
import com.ljx.hfgsjt.entity.yj.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

public interface SbyjMapper {
    @Select("select t.codevalue value,t.codename label from xtgl_code t where " +
            " t.parentid = (select id from xtgl_code  where " +
            " parentid = (select id from xtgl_code  where codename = '安装类型') and codename = #{label})")
    List<azlxEntity> findAzlxBySllx(Map<String, Object> pd);

    @Select("<script>" +
            "select * from (select t.*,ROWNUM rn from (select t.* from get_xmlb_sbyj t where 1=1 " +
            "<when test=\"xmbh != null and xmbh != '' \">" +
            " and t.xmbh LIKE CONCAT(CONCAT('%', #{xmbh}), '%')  " +
            "</when>" +
            "<when test=\"xmmc != null and xmmc != '' \">" +
            " and t.xmmc LIKE CONCAT(CONCAT('%', #{xmmc}), '%')  " +
            "</when>" +
            "<when test=\"gcdz != null and gcdz != '' \">" +
            " and t.xmdz LIKE CONCAT(CONCAT('%', #{gcdz}), '%')  " +
            "</when>" +
            "<when test=\"azlx != null and azlx != '' \">" +
            " and t.azlx = #{azlx} " +
            "</when>" +
//            "<when test=\"kssj != null and kssj != '' \">" +
//            " and to_char(t.lxsj,'yyyy-mm-dd') &gt;= #{kssj} " +
//            "</when>" +
//            "<when test=\"jssj != null and jssj != '' \">" +
//            " and to_char(t.lxsj,'yyyy-mm-dd') &lt;= #{jssj} " +
//            "</when>" +
            " order by t.lxsj) t) " +
            " WHERE rn &gt; #{pagebefor} AND rn &lt;= #{pageafter}" +
            "</script>")
    List<GetXmlbSbyjEntity> getSbyjListBysj(Map<String, Object> pd);

    @Select("<script>" +
            "select t.*,ROWNUM rn from get_xmlb_sbyj t where 1=1 " +
            "<when test=\"xmbh != null and xmbh != '' \">" +
            " and t.xmbh LIKE CONCAT(CONCAT('%', #{xmbh}), '%')  " +
            "</when>" +
            "<when test=\"xmmc != null and xmmc != '' \">" +
            " and t.xmmc LIKE CONCAT(CONCAT('%', #{xmmc}), '%')  " +
            "</when>" +
            "<when test=\"gcdz != null and gcdz != '' \">" +
            " and t.xmdz LIKE CONCAT(CONCAT('%', #{gcdz}), '%')  " +
            "</when>" +
            "<when test=\"azlx != null and azlx != '' \">" +
            " and t.azlx = #{azlx} " +
            "</when>" +
//            "<when test=\"kssj != null and kssj != '' \">" +
//            " and to_char(t.lxsj,'yyyy-mm-dd') &gt;= #{kssj} " +
//            "</when>" +
//            "<when test=\"jssj != null and jssj != '' \">" +
//            " and to_char(t.lxsj,'yyyy-mm-dd') &lt;= #{jssj} " +
//            "</when>" +
            "</script>")
    List<GetXmlbSbyjEntity> queryAllSbyjLists(Map<String, Object> pd);

    @Select("select s.* from sbyj_wb s where s.xm_id = #{xmid} order by s.hh")
//    @Select("select * from (select s.*,ROWNUM ron from sbyj_wb s where s.xm_id = #{xmid} " +
//            " and ROWNUM <= #{pageafter}) t WHERE t.ron > #{pagebefor}")
    List<XmSb> queryWblist(Map<String, Object> pd);

    @Select("select s.* from sbyj_gsb s where s.xm_id = #{xmid} order by s.hh")
//    @Select("select * from (select s.*,ROWNUM ron from sbyj_gsb s where s.xm_id = #{xmid} " +
//            " and ROWNUM <= #{pageafter}) t WHERE t.ron > #{pagebefor}")
    List<XmSb> queryGsblist(Map<String, Object> pd);

    @Select("select s.* from sbyj_jmb s where s.xm_id = #{xmid} order by s.cjsj desc")
//    @Select("select * from (select s.*,ROWNUM ron from sbyj_jmb s where s.xm_id = #{xmid} " +
//            " and ROWNUM <= #{pageafter}) t WHERE t.ron > #{pagebefor}")
    List<Map<String, Object>> queryJMblist(Map<String, Object> pd);

    @Select("select * from sbyj_wb s where s.xm_id = #{xmid}")
    List<XmSb> queryAllWblists(Map<String, Object> pd);

    @Select("select * from sbyj_gsb s where s.xm_id = #{xmid}")
    List<XmSb> queryAllGsblists(Map<String, Object> pd);

    @Select("select s.*,ROWNUM rn from sbyj_jmb s where s.xm_id = #{xmid} ")
    List<Map<String, Object>> queryAllJmblists(Map<String, Object> pd);

    @Select("select x.mc label,x.bh value from xtgl_sbcj x")
    List<Map<String, Object>> getSbcjList(Map<String, Object> pd);

    @Select("select t.bh value,t.mc label from xtgl_kj t")
    List<Map<String, Object>> getZbkjList(Map<String, Object> pd);

    @Select("select t.bh value,t.mc label from xtgl_xzqy t")
    List<Map<String, Object>> getqyList(Map<String, Object> pd);

    @Select("select x.codevalue value,x.codename label from xtgl_code x " +
            " where x.parentid = (select id from xtgl_code where type = 'sblx' )")
    List<Map<String, Object>> getsblxList(Map<String, Object> pd);

    @Select("select x.sqbh,x.xmdz_xzqybh qy,u.username,x.lxrsjh,x.xmbh,x.xmdz from xm_instance x " +
            " left join xtgl_user u on x.lxr = u.userid where x.id = #{xmid}")
    List<Map<String, Object>> getXmxxByXmid(Map<String, Object> pd);

    @Update("update xm_sb s set AZWZXZQ = #{qy},azwz=#{zbdz},yyzx_xcyjrq=#{nxcyjrq},sgdw_sbkj=#{zbkj},bsm=#{bsm}" +
            ",sgsj=#{nazrq},tssj=#{ntsrq},sbcjbh=#{sbcj},SBLXBH=#{sblx}, gps=#{gps},sgry=#{sgry}," +
            "sgryqzsj=#{nsgrq},yhqr=#{yhqr},yhqrqzsj=#{nyhqrrq},pqbh=#{yyzxpq},ds=#{xzsbdd},ybds=#{chjbdd} " +
            " where id = #{sbid}")
    void editWb(WjjlbEntity entity);

    @Select("select p.bh value,p.mc label from xtgl_pq p ")
    List<Map<String, Object>> getpqList(Map<String, Object> pd);

    @Select("select * from sbyj_ldsb s where s.ldid = #{ldid} and s.xm_id = #{xmid} order by s.hh")
    List<XmSb> findJmbByLdid(Map<String, Object> pd);


    void elxedit(Map<String, Object> pd);

    @Update("<script>" +
            " update xm_sb " +
            " <set> " +
            "    <if test=\"tzbdz != null and tzbdz != '' \"> azwzxzq = #{zbdz},</if> " +
            "    <if test=\"tgss != null and tgss != '' \"> pqbh = #{gss},</if> " +
            "    <if test=\"tsbkj != null and tsbkj != '' \"> sgdw_sbkj = #{sbkj},</if> " +
            "    <if test=\"tazrq != null and tazrq != '' \"> sgsj = #{azrq},</if> " +
            "    <if test=\"ttsrq != null and ttsrq != '' \"> tssj = #{tsrq},</if> " +
            "    <if test=\"tsbcj != null and tsbcj != '' \"> sbcjbh = #{sbcj},</if> " +
            "    <if test=\"tsblx != null and tsblx != '' \"> sblxbh = #{sblx},</if> " +
            "    <if test=\"tsgry != null and tsgry != '' \"> sgry = #{sgry},</if> " +
            "    <if test=\"tsgrq != null and tsgrq != '' \"> sgryqzsj = #{sgrq},</if> " +
            "    <if test=\"tyhdb != null and tyhdb != '' \"> yhqr = #{yhdb},</if> " +
            "    <if test=\"tyhqrrq != null and tyhqrrq != '' \"> yhqrqzsj = #{yhqrrq}</if> " +
            " </set> " +
            " where id in ${sbids}" +
            "</script>")
    void pledit(Map<String, Object> pd);

    @Update("update xm_sb set bsm = #{bsm},fq = #{fq} where hh = #{bzh}")
    void updateBsmAndFqByBzh(Map<String, Object> pd);

    @Insert("insert into yj_sbyjcb(id,sbyjzbid,sbid,hhlxbh,ldid) " +
            " values(#{id},#{yjzbid},#{sbid},'HB',#{ldid})")
    void sbyjLdSubmit(Map<String, Object> pd);

    @Insert("insert into yj_sbyjzb(id,sbyjdbh,xmid,shr,shsj,cjr,cjsj) " +
            " values (#{id},#{yjdbh},#{xmid},#{name},sysdate,#{cjr},sysdate)")
    void saveSbyjzb(Map<String, Object> pd);

    @Insert("insert into yj_sbyjcb(id,sbyjzbid,sbid,hhlxbh) " +
            " values(#{dbbs},#{id},#{dbid},'DB')")
    void sbyjDbSubmit(Map<String, Object> pd);

    @Delete("delete from yj_sbyjcb where SBYJZBID is null and sbyjdbh ！= #{yjdbh} ")
    void deteleHbByYjdbh(Map<String, Object> pd);

    @Delete("delete from yj_sbyjcb where SBYJZBID is null and LDID = #{ldid}")
    void deleteHbByLdid(Map<String, Object> pd);

    @Select("select * from sbyj_ldsb where xm_id = #{xmid} and ldid = #{ldid} and sbyjdbh is null")
    List<String> queryhbidByLdid(Map<String, Object> pd);

    @Insert("insert into yj_sbyjcb(id,sbyjzbid,sbid,hhlxbh,ldid) " +
            " values(#{ldhbbs},#{id},#{ldhbid},'HB',#{ldid})")
    void ldhbSubmit(Map<String, Object> pd);

    @Update("update yj_sbyjcb set SBYJZBID = #{id},SBYJDBH='' where SBYJZBID is null")
    void updateCbhb(Map<String, Object> pd);

    @Select("select a.*,nvl(b.num,0) num from " +
            "(select userid,username from xtgl_user where userid in (select userid from xtgl_userrole where roleid = '78f4525569c844a8af8c2a1b3b0a5f7e')) a " +
            " left join " +
            "(select shr,count(1) num from yj_sbyjzb group by shr) b on a.userid = b.shr order by num")
    List<Map<String, Object>> getZpry(Map<String, Object> pd);

    @Select("select s.* from sbyjsh_wb s where s.xm_id = #{xmid} and s.sbyjdbh = #{yjdbh} order by s.hh")
    List<Map<String, Object>> shWblist(Map<String, Object> pd);

    @Select("select * from sbyjsh_wb s where s.xm_id = #{xmid} and s.sbyjdbh = #{yjdbh} order by s.hh")
    List<Map<String, Object>> shAllWblists(Map<String, Object> pd);

    @Select("select s.* from sbyjsh_gsb s where s.xm_id = #{xmid} and s.sbyjdbh = #{yjdbh} order by s.hh")
    List<Map<String, Object>> shGsblist(Map<String, Object> pd);

    @Select("select * from sbyjsh_gsb s where s.xm_id = #{xmid} and s.sbyjdbh = #{yjdbh} order by s.hh")
    List<Map<String, Object>> shAllGsblists(Map<String, Object> pd);

    @Select("select x.ldid,x.LDBH,x.XM_ID,x.ZHS,x.wyjsl,nvl(y.yyjs,0) yyjs,nvl(z.bcyjs,0) bcyjs from sbyj_jmb x left join " +
            " (select l.xmid,l.id,l.ldbh,count(1) yyjs from xm_ld l left join yj_sbyjcb c on l.id=c.ldid left join yj_sbyjzb z on c.sbyjzbid = z.id and z.xmid = l.xmid where c.hhlxbh = 'HB' and z.yjdzt = 1 " +
            " group by l.xmid,l.id,l.ldbh) y on x.ldid = y.id and x.XM_ID = y.xmid " +
            " left join (select l.xmid,l.id,l.ldbh,count(1) bcyjs from xm_ld l left join yj_sbyjcb c on l.id=c.ldid left join yj_sbyjzb z on c.sbyjzbid = z.id and z.xmid = l.xmid " +
            " where c.hhlxbh = 'HB' and z.yjdzt = 0 and z.id = #{yjzbid} group by l.xmid,l.id,l.ldbh) z on x.ldid = z.id and x.XM_ID = z.xmid " +
            " where x.xm_id = #{xmid} order by x.cjsj desc")
    List<Map<String, Object>> shJMblist(Map<String, Object> pd);

    @Select("select x.ldid,x.LDBH,x.XM_ID,x.ZHS,x.wyjsl,nvl(y.yyjs,0) yyjs,nvl(z.bcyjs,0) bcyjs from sbyj_jmb x left join " +
            " (select l.xmid,l.id,l.ldbh,count(1) yyjs from xm_ld l left join yj_sbyjcb c on l.id=c.ldid left join yj_sbyjzb z on c.sbyjzbid = z.id and z.xmid = l.xmid where c.hhlxbh = 'HB' and z.yjdzt = 1 " +
            " group by l.xmid,l.id,l.ldbh) y on x.ldid = y.id and x.XM_ID = y.xmid " +
            " left join (select l.xmid,l.id,l.ldbh,count(1) bcyjs from xm_ld l left join yj_sbyjcb c on l.id=c.ldid left join yj_sbyjzb z on c.sbyjzbid = z.id and z.xmid = l.xmid " +
            " where c.hhlxbh = 'HB' and z.yjdzt = 0 and z.id = #{yjzbid} group by l.xmid,l.id,l.ldbh) z on x.ldid = z.id and x.XM_ID = z.xmid " +
            " where x.xm_id = #{xmid} order by x.cjsj desc")
    List<Map<String, Object>> shAllJmblists(Map<String, Object> pd);

    @Select("select d.codename label,d.id value from xtgl_code d " +
            " where d.parentid = (select id from xtgl_code where type = 'ysxz')")
    List<Map<String, Object>> getYsxz(Map<String, Object> pd);

    @Update("update xm_sb set ysxzbh = #{ysxz},hyflbh=#{hyfl},yyzx_xcyjrq=#{xcyjrq},pqbh=#{pq},yyzx_sgry=#{sgdw}," +
            " yyzx_gssry=#{gss},yyzx_yyzxry=#{yyzx},dxfw=#{dxfw},zsljf=#{dsljf},bz=#{bz} " +
            " where id = #{sbid}")
    void sheditsb(Map<String, Object> pd);

    @Update("update xm_sb set ysxzbh = #{ysxz},hyflbh=#{hyfl},yyzx_xcyjrq=#{xcyjrq},pqbh=#{pq},yyzx_sgry=#{sgdw}," +
            " yyzx_gssry=#{gss},yyzx_yyzxry=#{yyzx},dxfw=#{dxfw},zsljf=#{dsljf},dxmc=#{jzmc},dxhm=#{sjhm},bz=#{bz} " +
            " where id = #{sbid}")
    void sheditsbs(Map<String, Object> pd);

    @Select("select c.codevalue value,c.codename label from xtgl_code c where c.parentid = #{ysxz}")
    List<Map<String, Object>> getHyflCodeData(String ysxz);

    @Select("select * from sbyjsh_ldsb s where s.ldid = #{ldid} and s.xm_id = #{xmid} and s.sbyjdbh = #{yjdbh} " +
            " order by s.hh ")
    List<Map<String, Object>> shJmbByLdid(Map<String, Object> pd);

    @Update("<script>" +
            " update xm_sb " +
            " <set> " +
            "    <if test=\"tdsljf != null and tdsljf != '' \"> zsljf = #{dsljf},</if> " +
            "    <if test=\"tgssry != null and tgssry != '' \"> yyzx_gssry = #{gssry},</if> " +
            "    <if test=\"thyfl != null and thyfl != '' \"> hyflbh = #{hyfl},</if> " +
            "    <if test=\"tsgdwry != null and tsgdwry != '' \"> yyzx_sgry = #{sgdwry},</if> " +
            "    <if test=\"txcyjrq != null and txcyjrq != '' \"> yyzx_xcyjrq = #{xcyjrq},</if> " +
            "    <if test=\"tysxz != null and tysxz != '' \"> ysxzbh = #{ysxz},</if> " +
            "    <if test=\"tyyzxry != null and tyyzxry != '' \"> yyzx_yyzxry = #{yyzxry},</if> " +
            "    <if test=\"tbz != null and tbz != '' \"> bz = #{bz},</if> " +
            "    <if test=\"tdxfw != null and tdxfw != '' \"> dxfw = #{dxfw},</if> " +
            "    <if test=\"tjzmc != null and tjzmc != '' \"> dxmc = #{jzmc},</if> " +
            "    <if test=\"tsjhm != null and tsjhm != '' \"> dxhm = #{sjhm},</if> " +
            "    <if test=\"tpq != null and tpq != '' \"> pqbh = #{pq}</if> " +
            " </set> " +
            " where id in ${sbids}" +
            "</script>")
    void shpledit(Map<String, Object> pd);

    @Select("select a.*,nvl(b.num,0) num from " +
            "(select userid,username from xtgl_user where userid in (select userid from xtgl_userrole where roleid = '917b118e6e664bd18fc2865d0094618b')) a " +
            " left join " +
            "(select cby,count(1) num from yj_sbyjzb group by cby) b on a.userid = b.cby order by num")
    List<Map<String, Object>> getZpcby(Map<String, Object> pd);

    @Update("update yj_sbyjzb set cby = #{name},cbsj = sysdate where id=#{refId}")
    void updateCby(Map<String, Object> pd);

    @Update("update xm_sb set bch=#{bch} where id = #{sbid}")
    void cbyeditsb(Map<String, Object> pd);

    @Update("update xm_sb set bch=#{bch} where id in ${ids}")
    void cbypledit(Map<String, Object> pd);

    @Update("update yj_sbyjzb set yjdzt = #{zt} where id=#{refId}")
    void updateSbztByid(Map<String, Object> pd);

    @Delete("delete from yj_sbyjcb y where y.hhlxbh = 'DB' " +
            " and y.sbyjzbid = #{yjzbid} ")
    void deleteDb(Map<String, Object> pd);

    @Delete("delete from yj_sbyjcb y where y.hhlxbh = 'HB' and y.ldid = #{ldid} and y.sbyjzbid=#{yjzbid}")
    void deleteLdsb(Map<String, Object> pd);

    @Select("select id from yj_sbyjzb where sbyjdbh = #{yjdbh}")
    String findZbidBybh(Map<String, Object> pd);


    @Select("select rwblr from wf_task_history where c_activityid = #{activityId} and refid is null " +
            " and proc_inst_id = #{procInstId}")
    List<String> queryRwblr(@Param("activityId")String activityId,@Param("refId") String refId,
                            @Param("procInstId") String procInstId);

    @Select("select rwblr from wf_task_history where c_activityid = #{activityId} and refid = #{refId} " +
            " and proc_inst_id = #{procInstId} order by qssj desc")
    List<String> querycbyThRwblr(@Param("activityId")String activityId,@Param("refId") String refId,
                                 @Param("procInstId") String procInstId);

    @Select("select c.sbid from yj_sbyjzb z left join yj_sbyjcb c on z.id = c.sbyjzbid " +
            " where z.xmid = #{xmid} and z.id = #{refId} and c.HHLXBH != 'WJJLB'")
    List<String> querySbids(@Param("xmid") String xmid, @Param("refId") String refId);

    @Select("call get_sbyj_dr(#{v_sbid,mode=IN,jdbcType=VARCHAR},#{yjdbh,mode=IN,jdbcType=VARCHAR})")
    @Options(statementType = StatementType.CALLABLE)
    void cbysbdr(Map<String, Object> map);

    @Select("select * from (select c.*,ROWNUM rn from sbyj_dc c where c.yjdbh = #{yjdbh}) where " +
            "  rn > #{pagebefor} AND rn <= #{pageafter}")
    List<Map<String, Object>> querydcsb(Map<String, Object> pd);

    @Select("select * from sbyj_dc c where c.yjdbh = #{yjdbh}")
    List<Map<String, Object>> queryAlldcsb(Map<String, Object> pd);

    @Select("select jc from xtgl_sbcj where bh = #{sbcj}")
    String querysbcjjcBybh(String sbcj);

    @Select("select mc from xtgl_kj where bh = #{kjbh}")
    String querysbkjmcBybh(String kjbh);

    @Select("<script>" +
            "select * from(" +
            " select s.*,rownum rn from (select * from zhcx_sfkp " +
            "where 1=1 " +
            "<when test=\"xmmc != null and xmmc != ''\">"+
            "and xmmc like concat(concat('%',#{xmmc}),'%')"+
            "</when>"+
            "<when test=\"xmbh != null and xmbh != ''\">"+
            "and xmbh like concat(concat('%',#{xmbh}),'%')"+
            "</when>"+
            "<when test=\"xmfzr != null and xmfzr != ''\">"+
            "and xmfzr like concat(concat('%',#{xmfzr}),'%')"+
            "</when>"+
            "<when test=\"ksmc != null and ksmc != ''\">"+
            "and sqlx = #{ksmc}"+
            "</when>"+
            "<when test=\"azlx != null and azlx != ''\">"+
            "and azlx = #{azlx}"+
            "</when>"+
            "<when test=\"kssj != null and kssj != ''\">"+
            "and to_date(zdsj,'yyyy-mm-dd hh24:mi:ss') &gt;= to_date(#{kssj},'yyyy-mm-dd')"+
            "</when>"+
            "<when test=\"jssj != null and jssj != ''\">"+
            "and to_date(zdsj,'yyyy-mm-dd hh24:mi:ss') &lt; to_date(#{jssj},'yyyy-mm-dd') +1"+
            "</when>"+
            "order by xmbh) s " +
            "where rownum &lt;= #{endRow}" +
            ") where rn &gt;= #{startRow}" +
            "</script>")
    List<Map<String,Object>> querysfkp(BaseEntity e);

    @Select("<script>" +
            "select count(1) from zhcx_sfkp where 1=1" +
            "<when test=\"xmmc != null and xmmc != ''\">"+
            "and xmmc like concat(concat('%',#{xmmc}),'%')"+
            "</when>"+
            "<when test=\"xmbh != null and xmbh != ''\">"+
            "and xmbh like concat(concat('%',#{xmbh}),'%')"+
            "</when>"+
            "<when test=\"xmfzr != null and xmfzr != ''\">"+
            "and xmfzr like concat(concat('%',#{xmfzr}),'%')"+
            "</when>"+
            "<when test=\"ksmc != null and ksmc != ''\">"+
            "and sqlx = #{ksmc}"+
            "</when>"+
            "<when test=\"azlx != null and azlx != ''\">"+
            "and azlx = #{azlx}"+
            "</when>"+
            "<when test=\"kssj != null and kssj != ''\">"+
            "and to_date(zdsj,'yyyy-mm-dd hh24:mi:ss') &gt;= to_date(#{kssj},'yyyy-mm-dd')"+
            "</when>"+
            "<when test=\"jssj != null and jssj != ''\">"+
            "and to_date(zdsj,'yyyy-mm-dd hh24:mi:ss') &lt; to_date(#{jssj},'yyyy-mm-dd') +1"+
            "</when>"+
            "</script>")
    int querysfkpTotal(BaseEntity e);

    @Select("<script>" +
            "select * from(" +
            " select s.*,rownum rn from (select * from ZHCX_SGXD " +
            "where 1=1 " +
            "<when test=\"xmmc != null and xmmc != ''\">"+
            "and xmmc like concat(concat('%',#{xmmc}),'%')"+
            "</when>"+
            "<when test=\"xmbh != null and xmbh != ''\">"+
            "and xmbh like concat(concat('%',#{xmbh}),'%')"+
            "</when>"+
            "<when test=\"xmfzr != null and xmfzr != ''\">"+
            "and xmfzr like concat(concat('%',#{xmfzr}),'%')"+
            "</when>"+
            "<when test=\"ksmc != null and ksmc != ''\">"+
            "and sqlx = #{ksmc}"+
            "</when>"+
            "<when test=\"azlx != null and azlx != ''\">"+
            "and azlx = #{azlx}"+
            "</when>"+
            "<when test=\"kssj != null and kssj != ''\">"+
            "and to_date(xdsj,'yyyy-mm-dd hh24:mi:ss') &gt;= to_date(#{kssj},'yyyy-mm-dd')"+
            "</when>"+
            "<when test=\"jssj != null and jssj != ''\">"+
            "and to_date(xdsj,'yyyy-mm-dd hh24:mi:ss') &lt; to_date(#{jssj},'yyyy-mm-dd') +1"+
            "</when>"+
            "order by xmbh) s " +
            "where rownum &lt;= #{endRow}" +
            ") where rn &gt;= #{startRow}" +
            "</script>")
    List<Map<String,Object>> querysgxd(BaseEntity e);

    @Select("<script>" +
            "select count(1) from zhcx_sgxd where 1=1" +
            "<when test=\"xmmc != null and xmmc != ''\">"+
            "and xmmc like concat(concat('%',#{xmmc}),'%')"+
            "</when>"+
            "<when test=\"xmbh != null and xmbh != ''\">"+
            "and xmbh like concat(concat('%',#{xmbh}),'%')"+
            "</when>"+
            "<when test=\"xmfzr != null and xmfzr != ''\">"+
            "and xmfzr like concat(concat('%',#{xmfzr}),'%')"+
            "</when>"+
            "<when test=\"ksmc != null and ksmc != ''\">"+
            "and sqlx = #{ksmc}"+
            "</when>"+
            "<when test=\"azlx != null and azlx != ''\">"+
            "and azlx = #{azlx}"+
            "</when>"+
            "<when test=\"kssj != null and kssj != ''\">"+
            "and to_date(xdsj,'yyyy-mm-dd hh24:mi:ss') &gt;= to_date(#{kssj},'yyyy-mm-dd')"+
            "</when>"+
            "<when test=\"jssj != null and jssj != ''\">"+
            "and to_date(xdsj,'yyyy-mm-dd hh24:mi:ss') &lt; to_date(#{jssj},'yyyy-mm-dd') +1"+
            "</when>"+
            "</script>")
    int querysgxdTotal(BaseEntity e);

    @Update("update xm_sb s set s.bch = #{bch} where s.id in ${sbids}")
    void gsplbj(Map<String, Object> pd);

    @Delete("delete from yj_sbyjcb y where y.hhlxbh = 'WJJLB' " +
            " and y.sbyjzbid = #{yjzbid} ")
    void deleteWb(Map<String, Object> pd);

    @Insert("insert into yj_sbyjcb(id,sbyjzbid,sbid,hhlxbh) " +
            " values(#{wbbs},#{id},#{wbid},'WJJLB')")
    void sbyjWbSubmit(Map<String, Object> pd);
}
