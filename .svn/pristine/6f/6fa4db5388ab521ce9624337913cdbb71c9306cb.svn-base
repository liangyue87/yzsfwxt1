package com.ljx.hfgsjt.xqsl.dao;

import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.xqsl.GetXqslEntity;
import com.ljx.hfgsjt.entity.xqsl.XmGsybxxEntity;
import com.ljx.hfgsjt.entity.xqsl.XmYzbEntity;
import com.ljx.hfgsjt.entity.xqsl.YwslXjxqsqLdxxEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface XqslMapper {
    @Update("update ywsl_gshbsqb set xmjl = #{name},fpzt = '1' where id = #{rwid}")
    void updateGsSlrById(Map<String, Object> pd);

    @Update("update ywsl_xjxqsqb set xmjl = #{name},fpzt = '1' where id = #{rwid}")
    void updateJmSlrById(Map<String, Object> pd);

    @Update("update ywsl_chgzsqb set xmjl = #{name},fpzt = '1' where id = #{rwid}")
    void updateHgSlrById(Map<String, Object> pd);

    @Insert("insert into ${tablename}(id,ywsl_xjxqsqbid,fjlx_yj,fjlx_ej,fjlj,fjhz,fjmc,scr,scsj) " +
            " values (#{id},#{sldid},#{fjlx_yj},#{fjlx_ej},#{fjlj},#{fjhz},#{fjmc},#{scr},sysdate)")
    void insertImportFh(Map<String, Object> pd);

   @Insert("insert into ${tablename}(id,ywsl_gshbsqbid,fjlx_yj,fjlx_ej,fjlj,fjhz,fjmc,scr,scsj) " +
           " values (#{id},#{sldid},#{fjlx_yj},#{fjlx_ej},#{fjlj},#{fjhz},#{fjmc},#{scr},sysdate)")
   void insertImportgsFh(Map<String, Object> pd);

   @Insert("insert into ${tablename}(id,ywsl_chgzsqbid,fjlx_yj,fjlx_ej,fjlj,fjhz,fjmc,scr,scsj) " +
           " values (#{id},#{sldid},#{fjlx_yj},#{fjlx_ej},#{fjlj},#{fjhz},#{fjmc},#{scr},sysdate)")
   void insertImporthgFh(Map<String, Object> pd);

    @Insert("insert into ${tablename}(id,sqbh,xmmc,dwmc,xysfs,xyszk,ldsx,lxr,lxrsjh,zjlxdh,xmdz_xzqybh,xmdz,lfhs," +
            "pfhs,lfds,zgcs,ckyss,cpyss,mmyss,gcyss,wyyss,qtys,slr,slrq,fpr,fpsj,azlx,nbxf,xqmc) " +
            " values(#{id},#{sqbh},#{xmmc},#{dwmc},#{xysfs},#{xyszk},#{ldsx},#{lxr},#{lxrsjh},#{zjlxdh},#{xmdz_xzqybh},#{xmdz}," +
            "#{lfhs},#{pfhs},#{lfds},#{zgcs},#{ckyss},#{cpyss},#{mmyss},#{gcyss},#{wyyss},#{qtys},#{slr},sysdate," +
            "#{slr},sysdate,#{azlx},#{nbxf},#{xqmc})")
 void insertChgz(Map<String, Object> pd);

    @Update("update ${tablename} set xmmc=#{xmmc},dwmc=#{dwmc},xysfs=#{xysfs},xyszk=#{xyszk},ldsx=#{ldsx}," +
            "lxr=#{lxr},lxrsjh=#{lxrsjh},xmdz_xzqybh=#{xmdz_xzqybh},xmdz=#{xmdz},lfhs=#{lfhs},pfhs=#{pfhs},lfds=#{lfds}," +
            "zgcs=#{zgcs},ckyss=#{ckyss},cpyss=#{cpyss},mmyss=#{mmyss},gcyss=#{gcyss},wyyss=#{wyyss},qtys=#{qtys}," +
            "slr=#{slr},slrq=sysdate,fpr=#{slr},fpsj=sysdate,azlx=#{azlx},nbxf=#{nbxf},xqmc=#{xqmc},zjlxdh=#{zjlxdh} " +
            "where id=#{update_id}")
    void updateChgz(Map<String, Object> pd);

    @Insert("insert into ${tablename}(id,sqbh,xmmc,lxr,lxrsjh,zjlxdh,slr,slrq,fpr,fpsj,xmdz_xzqybh,xmdz,azlx,zjzmj," +
            "zzjzmj,dwmc,yhxz,dkjc,hs,sjfw,dylh,ghbfs,jfsj,fqqk,fwxz) " +
            " values (#{id},#{sqbh},#{xmmc},#{lxr},#{lxrsjh},#{zjlxdh},#{slr},#{slrq},#{slr},sysdate," +
            " #{xmdz_xzqybh},#{xmdz},#{azlx},#{zjzmj},#{zzjzmj},#{dwmc},#{yhxz}," +
            " #{dkjc},#{hs},#{sjfw},#{dylh},#{ghbfs},#{jfsj},#{fqqk},#{fwxz})")
    void insertXjxq(Map<String, Object> pd);

    @Update("update ${tablename} set sqbh=#{sqbh},xmmc=#{xmmc},lxr=#{lxr},lxrsjh=#{lxrsjh},zjlxdh=#{zjlxdh},slr=#{slr},slrq=#{slrq},fpr=#{slr}," +
            " fpsj = sysdate,xmdz_xzqybh=#{xmdz_xzqybh},xmdz=#{xmdz},azlx=#{azlx},zjzmj=#{zjzmj},zzjzmj=#{zzjzmj}," +
            " dwmc=#{dwmc},yhxz=#{yhxz},dkjc=#{dkjc},hs=#{hs},sjfw=#{sjfw},dylh=#{dylh},ghbfs=#{ghbfs}," +
            " jfsj=#{jfsj},fqqk=#{fqqk},fwxz=#{fwxz} where id=#{update_id}")
    void updateXjxq(Map<String, Object> pd);

    @Insert("insert into ${tablename}(id,sqbh,xmmc,lxr,lxrsjh,zjlxdh,jhkgsj,yxazsl,jsyt,yhxz,yhh,ybkj,slr,slrq," +
            "fpr,fpsj,xmdz_xzqybh,xmdz,azlx,dwmc) " +
            " values (#{id},#{sqbh},#{xmmc},#{lxr},#{lxrsjh},#{zjlxdh},#{jhkgsj},#{yxazsl},#{jsyt},#{yhxz},#{yhh}," +
            "#{ybkj},#{slr},#{slrq},#{slr},sysdate,#{xmdz_xzqybh},#{xmdz},#{azlx},#{dwmc})")
    void insertGshb(Map<String, Object> pd);

    @Update("update ${tablename} set sqbh=#{sqbh},xmmc=#{xmmc},lxr=#{lxr},lxrsjh=#{lxrsjh},zjlxdh=#{zjlxdh},yxazsl=#{yxazsl}," +
            " jsyt=#{jsyt},yhxz=#{yhxz},yhh=#{yhh},ybkj=#{ybkj},slr=#{slr},slrq=#{slrq},fpr=#{slr}," +
            " fpsj=sysdate,xmdz_xzqybh=#{xmdz_xzqybh},xmdz=#{xmdz},azlx=#{azlx},dwmc=#{dwmc},jhkgsj=#{jhkgsj} " +
            " where id=#{update_id}")
    void updateGshb(Map<String, Object> pd);

    @Update("update ${tnamefj} set ywsl_chgzsqbid = #{id} where ywsl_chgzsqbid is null")
    void updateChgzFj(Map<String, Object> pd);

    @Update("update ${tnamefj} set ywsl_xjxqsqbid = #{id} where ywsl_xjxqsqbid is null")
    void updateXjxqFj(Map<String, Object> pd);

    @Update("update ${tnamefj} set ywsl_gshbsqbid = #{id} where ywsl_gshbsqbid is null")
    void updateGshbFj(Map<String, Object> pd);

    @Update("update ${tnamefj} set ywsl_chgzsqbid = #{update_id} where ywsl_chgzsqbid is null")
    void updateChgzFjs(Map<String, Object> pd);

    @Update("update ${tnamefj} set ywsl_xjxqsqbid = #{update_id} where ywsl_xjxqsqbid is null")
    void updateXjxqFjs(Map<String, Object> pd);

    @Update("update ${tnamefj} set ywsl_gshbsqbid = #{update_id} where ywsl_gshbsqbid is null")
    void updateGshbFjs(Map<String, Object> pd);

    @Insert("insert into wf_task(taskid,ptaskid,proc_inst_id,c_activityid,iscurrent," +
            " czr,qssj,ischeck) " +
            " values(#{taskid},#{ls_taskid},#{proc_inst_id},#{c_activityid},#{iscurrent},#{czr},sysdate,#{ischeck})")
    void insertTask(Map<String, Object> pd);

    @Select("select * from (select t.*,ROWNUM rn from GET_XQSL t where t.xmjl = #{czr} and t.fpzt = 1) " +
            " WHERE rn > #{pagebefor} AND rn <= #{pageafter}")
    List<GetXqslEntity> getXqqsListByXmjl(@Param("pagebefor") Integer pagebefor, @Param("pageafter") Integer pageafter,@Param("czr") String czr);

    @Select("select t.* from GET_XQSL t where t.xmjl = #{czr} and t.fpzt = 1")
    List<GetXqslEntity> findAll(String czr);

    @Update("update ${tablename} set fpzt = 2 where id = #{id}")
    void updateFpzt(Map<String, Object> pd);

    @Select("select a.*,nvl(b.num,0) num from " +
            "(select userid,username from xtgl_user where userid in (select userid from xtgl_userrole where roleid = #{roleid})) a " +
            " left join " +
            "(select xmjl,count(1) num from get_xqsl group by xmjl) b on a.userid = b.xmjl order by num")
    List<Map<String, Object>> getZpry(Map<String, Object> pd);

    @Select("select * from xtgl_fjzlgl where parentid = #{fjlxid} order by id ")
    List<Map<String, Object>> getSclx(Map<String, Object> pd);

    @Select("select id,parentid from xtgl_fjzlgl where id = #{operation}")
    List<Map<String,Object>> findFjlxById(Map<String, Object> pd);

    @Select("<script>" +
            " select * from (select t.*,ROWNUM rn from (select t.*,to_char(t.jfsj,'yyyy-MM-dd') xjfsj,c.codename azlxname from get_xqsl t " +
            " left join (select * from xtgl_code c where c.parentid in (select id from xtgl_code where type in ('gsk','jmk','hgk'))) c on c.codevalue = t.AZLX " +
            " where t.FPZT = 0 and t.slr = #{xmfzr} " +
            "<when test=\"sqlx != null and sqlx != '' \">" +
            " and t.typeid = #{sqlx}" +
            "</when>" +
            "<when test=\"status != null and status != '' \">" +
            " and t.fpzt = #{status} " +
            "</when>" +
            "<when test=\"gcmc != null and gcmc != '' \">" +
            " and t.xmmc LIKE CONCAT(CONCAT('%', #{gcmc}), '%') " +
            "</when>" +
            "<when test=\"gcdz != null and gcdz != '' \">" +
            " and t.xmdz LIKE CONCAT(CONCAT('%', #{gcdz}), '%') " +
            "</when>" +
            "<when test=\"lxr != null and lxr != '' \">" +
            " and t.lxr  LIKE CONCAT(CONCAT('%', #{lxr}), '%') " +
            "</when>" +
            "<when test=\"beforeDate != null and beforeDate != '' \">" +
            " and to_char(t.slrq,'yyyy-mm-dd') &gt;= #{beforeDate} " +
            "</when>" +
            "<when test=\"afterDate != null and afterDate != '' \">" +
            " and to_char(t.slrq,'yyyy-mm-dd') &lt;= #{afterDate} " +
            "</when>" +
            " order by t.SQBH desc) t) " +
            " WHERE rn &gt; #{pagebefor} AND rn &lt;= #{pageafter}" +
            "</script>")
    List<GetXqslEntity> queryXqsl(Map<String, Object> pd);

    @Select("<script>" +
            "select t.*,to_char(t.jfsj,'yyyy-MM-dd') xjfsj,c.codename azlxname from get_xqsl t " +
            " left join (select * from xtgl_code c where c.parentid in (select id from xtgl_code where type in ('gsk','jmk','hgk'))) c on c.codevalue = t.AZLX " +
            " where t.FPZT = 0 and t.slr = #{xmfzr} " +
            "<when test=\"sqlx != null and sqlx != '' \">" +
            " and t.type = #{sqlx}" +
            "</when>" +
            "<when test=\"status != null and status != '' \">" +
            " and t.fpzt = #{status} " +
            "</when>" +
            "<when test=\"gcmc != null and gcmc != '' \">" +
            " and t.xmmc = #{gcmc} " +
            "</when>" +
            "<when test=\"gcdz != null and gcdz != '' \">" +
            " and t.xmdz = #{gcdz} " +
            "</when>" +
            "<when test=\"lxr != null and lxr != '' \">" +
            " and t.lxr = #{lxr} " +
            "</when>" +
            "<when test=\"beforeDate != null \">" +
            " and to_char(t.slrq,'yyyy-mm-dd') &gt;= #{beforeDate} " +
            "</when>" +
            "<when test=\"afterDate != null \">" +
            " and to_char(t.slrq,'yyyy-mm-dd') &lt;= #{afterDate} " +
            "</when>" +
            "</script>")
    List<GetXqslEntity> queryAllXqqsLists(Map<String, Object> pd);

    @Select("select t.bh value,t.mc label from xtgl_xzqy t ")
    List<Map<String, Object>> getQyList(Map<String, Object> pd);

    @Select("select t.name type,count(1) value from ( select x.name,x.id from ${tablename} t  " +
            " left join xtgl_fjzlgl x on t.fjlx_yj = x.parentid and t.fjlx_ej = x.id " +
            " where t.id in ${ids}) t group by t.name,t.id order by t.id")
    List<Map<String, Object>> queryZlcount(Map<String, Object> pd);

    @Delete("delete from ${tnamefj} where id not in ${ids} and ywsl_chgzsqbid is null")
    void deleteChgzFj(Map<String, Object> pd2);

    @Delete("delete from ${tnamefj} where id not in ${ids} and ywsl_xjxqsqbid is null")
    void deleteXjxqFj(Map<String, Object> pd);

    @Delete("delete from ${tnamefj} where id not in ${ids} and ywsl_gshbsqbid is null")
    void deleteGshbFj(Map<String, Object> pd1);

    @Select("select t.*,x.name type from ${tablename} t left join " +
            " (select * from xtgl_fjzlgl f where f.parentid = #{fjlxid}) x on t.fjlx_ej = x.id" +
            " where ywsl_xjxqsqbid = #{id}")
    List<Map<String, Object>> getScwjJm(Map<String, Object> pd);

    @Select("select t.*,x.name type from ${tablename} t left join " +
            " (select * from xtgl_fjzlgl f where f.parentid = #{fjlxid}) x on t.fjlx_ej = x.id" +
            " where ywsl_gshbsqbid = #{id}")
    List<Map<String, Object>> getScwjGs(Map<String, Object> pd);

    @Select("select t.*,x.name type from ${tablename} t left join " +
            " (select * from xtgl_fjzlgl f where f.parentid = #{fjlxid}) x on t.fjlx_ej = x.id" +
            " where ywsl_chgzsqbid = #{id}")
    List<Map<String, Object>> getScwjHg(Map<String, Object> pd);

    @Select("select t.codevalue value,t.codename label from xtgl_code t where " +
            " t.parentid = (select id from xtgl_code  where " +
            " parentid = (select id from xtgl_code where type = 'azlx') and type = #{type})")
    List<Map<String, Object>> getAzList(Map<String, Object> pd);

    @Insert("insert into ywsl_xjxqsq_ldxx(id,sqid,ldh,dys,lc,mchs,zhs,bz,sqlx)" +
            " values(#{id},#{sqid},#{ldh},#{dys},#{lc},#{mchs},#{zhs},#{bz},#{sqlx})")
    void addLdxx(YwslXjxqsqLdxxEntity entity);

    @Insert("insert into ywsl_xjxqsq_ldxx(id,ldh,dys,lc,mchs,zhs,bz,sqlx)" +
            " values(#{id},#{ldh},#{dys},#{lc},#{mchs},#{zhs},#{bz},#{sqlx})")
    void addLdxxs(YwslXjxqsqLdxxEntity entity);

    @Update("update ywsl_xjxqsq_ldxx set ldh=#{ldh},dys=#{dys},lc=#{lc},mchs=#{mchs},zhs=#{zhs},bz=#{bz} where id=#{id}")
    void editLdxx(YwslXjxqsqLdxxEntity entity);

    @Select("select * from ywsl_xjxqsq_ldxx where sqid is null")
    List<YwslXjxqsqLdxxEntity> getLdxxList();

    @Delete("delete from ywsl_xjxqsq_ldxx where id = #{id}")
    void deleteLdxxById(String id);

    @Select("select * from ywsl_xjxqsq_ldxx where sqid = #{id}")
    List<YwslXjxqsqLdxxEntity> getLdxxList1(Map<String, Object> pd);

    @Delete("delete from ywsl_xjxqsq_ldxx where id not in ${ldxxids} and sqid is null and sqlx = #{sqlx}")
    void deleteXjxqSqld(Map<String, Object> pd);

    @Update("update ywsl_xjxqsq_ldxx set sqid = #{id} where sqid is null and sqlx = #{sqlx}")
    void updateXjxqSqld(Map<String, Object> pd);

    @Update("update ywsl_xjxqsq_ldxx set sqid = #{update_id} where sqid is null and sqlx = #{sqlx}")
    void updateXjxqSqlds(Map<String, Object> pd);

    @Select("select t.*,to_char(t.slrq,'yyyy-MM-dd') vslrq from get_xqsl t where t.id = #{rwid}")
    List<Map<String, Object>> getXqslById(String rwid);

    @Select("select x.codevalue value,x.codename label from xtgl_code x where " +
            " x.parentid in (select id from xtgl_code where type = 'sqlx') order by sort ")
    List<Map<String, Object>> querySqList();

    @Select("select * from xqsl_yzb where sqid is null")
    List<XmYzbEntity> getYzbList();

    @Select("select * from xqsl_yzb where sqid =  #{id}")
    List<XmYzbEntity> getYzbList1(Map<String, Object> pd);

    @Delete("delete from xm_yzb where id = #{id}")
    void deleteYzbById(String id);

    @Insert("insert into xm_yzb(id,hh,hm,kjbh,ysxzbh,hyflbh,kchs,bz,sqid)" +
            " values(#{id},#{hh},#{hm},#{kjbh},#{ysxzbh},#{hyflbh},#{kchs},#{bz},#{sqid})")
    void addYzb(XmYzbEntity entity);

    @Insert("insert into xm_yzb(id,hh,hm,kjbh,ysxzbh,hyflbh,kchs,bz)" +
            " values(#{id},#{hh},#{hm},#{kjbh},#{ysxzbh},#{hyflbh},#{kchs},#{bz})")
    void addYzbs(XmYzbEntity entity);

    @Update("update xm_yzb set hh=#{hh},hm=#{hm},kjbh=#{kjbh},ysxzbh=#{ysxzbh},hyflbh=#{hyflbh},kchs=#{kchs},bz=#{bz} " +
            " where id=#{id}")
    void editYzb(XmYzbEntity entity);

    @Select("select x.bh value,x.mc label from xtgl_kj x")
    List<Map<String, Object>> queryKjList();

    @Select("select c.codename label,c.id value from xtgl_code c where " +
            " c.parentid = (select id from xtgl_code where type = 'ysxz')")
    List<Map<String, Object>> queryYsxzList();

    @Delete("delete from xm_yzb where id not in ${yzbids} and sqid is null")
    void deleteYzb(Map<String, Object> pd);

    @Update("update xm_yzb set sqid = #{id} where sqid is null")
    void updateYzb(Map<String, Object> pd);

    @Update("update xm_yzb set sqid = #{update_id} where sqid is null")
    void updateYzbs(Map<String, Object> pd);

    @Select("select m.name,m.id,m.parentid,nvl(n.nums,0) count from (" +
            "  select x.name,x.id,x.parentid from xtgl_fjzlgl x where x.parentid = #{fjlxid} group by x.name,x.id,x.parentid) m " +
            " left join (select x.name,count(1) nums from ${tablename} f left join xtgl_fjzlgl x " +
            "  on f.fjlx_ej = x.id where f.fjlx_yj = #{fjlxid} and f.ywsl_xjxqsqbid is null group by x.name) n " +
            " on m.name = n.name order by m.id")
    List<Map<String, Object>> getZlrow(Map<String, Object> pd);

    @Select("select m.name,m.id,m.parentid,nvl(n.nums,0) count from (" +
            "  select x.name,x.id,x.parentid from xtgl_fjzlgl x where x.parentid = #{fjlxid} group by x.name,x.id,x.parentid) m " +
            " left join (select x.name,count(1) nums from ${tablename} f left join xtgl_fjzlgl x " +
            "  on f.fjlx_ej = x.id where f.fjlx_yj = #{fjlxid} and f.ywsl_gshbsqbid is null group by x.name) n " +
            " on m.name = n.name order by m.id")
    List<Map<String, Object>> getGsZlrow(Map<String, Object> pd);

    @Select("select m.name,m.id,m.parentid,nvl(n.nums,0) count from (" +
            "  select x.name,x.id,x.parentid from xtgl_fjzlgl x where x.parentid = #{fjlxid} group by x.name,x.id,x.parentid) m " +
            " left join (select x.name,count(1) nums from ${tablename} f left join xtgl_fjzlgl x " +
            "  on f.fjlx_ej = x.id where f.fjlx_yj = #{fjlxid} and f.ywsl_chgzsqbid is null group by x.name) n " +
            " on m.name = n.name order by m.id")
    List<Map<String, Object>> getHgZlrow(Map<String, Object> pd);

   @Select("select m.name,m.id,m.parentid,nvl(n.nums,0) count from (" +
           "  select x.name,x.id,x.parentid from xtgl_fjzlgl x where x.parentid = #{fjlxid} group by x.name,x.id,x.parentid) m " +
           " left join (select x.name,count(1) nums from ${tablename} f left join xtgl_fjzlgl x " +
           "  on f.fjlx_ej = x.id where f.fjlx_yj = #{fjlxid} and f.ywsl_xjxqsqbid = #{id} group by x.name) n " +
           " on m.name = n.name order by m.id")
    List<Map<String, Object>> getEditZlrow(Map<String, Object> pd);

    @Select("select m.name,m.id,m.parentid,nvl(n.nums,0) count from (" +
            "  select x.name,x.id,x.parentid from xtgl_fjzlgl x where x.parentid = #{fjlxid} group by x.name,x.id,x.parentid) m " +
            " left join (select x.name,count(1) nums from ${tablename} f left join xtgl_fjzlgl x " +
            "  on f.fjlx_ej = x.id where f.fjlx_yj = #{fjlxid} and f.ywsl_gshbsqbid = #{id} group by x.name) n " +
            " on m.name = n.name order by m.id")
    List<Map<String, Object>> getGsEditZlrow(Map<String, Object> pd);

    @Select("select m.name,m.id,m.parentid,nvl(n.nums,0) count from (" +
            "  select x.name,x.id,x.parentid from xtgl_fjzlgl x where x.parentid = #{fjlxid} group by x.name,x.id,x.parentid) m " +
            " left join (select x.name,count(1) nums from ${tablename} f left join xtgl_fjzlgl x " +
            "  on f.fjlx_ej = x.id where f.fjlx_yj = #{fjlxid} and f.ywsl_chgzsqbid = #{id} group by x.name) n " +
            " on m.name = n.name order by m.id")
    List<Map<String, Object>> getHgEditZlrow(Map<String, Object> pd);

    @Select("select * from (select t.*,rownum rn from (select t.id,t.fjlx_yj,t.fjlx_ej,t.fjmc,t.fjlj,u.username," +
            " to_char(t.scsj,'yyyy-MM-dd HH24:mi') scsj from ${tablename} t left join xtgl_user u on t.scr = u.userid " +
            "  where t.fjlx_yj = #{yjfjlx} and t.fjlx_ej = #{ejfjlx} and t.ywsl_xjxqsqbid = #{sqdid} order by t.scsj desc) t)" +
            " WHERE rn > #{pagebefor} AND rn <= #{pageafter} ")
    List<Map<String, Object>> toDetial(Map<String, Object> pd);

    @Select("select t.id,t.fjlx_yj,t.fjlx_ej,t.fjmc,t.fjlj,u.username,to_char(t.scsj,'yyyy-MM-dd HH24:mi') scsj " +
            "  from ${tablename} t left join xtgl_user u on t.scr = u.userid " +
            "  where t.fjlx_yj = #{yjfjlx} and t.fjlx_ej = #{ejfjlx} and t.ywsl_xjxqsqbid = #{sqdid}")
    List<Map<String, Object>> toAllDetial(Map<String, Object> pd);

    @Delete("delete from ${tablename} where id = #{id}")
    void deleteFj(Map<String, Object> pd);

    @Delete("delete from ywsl_xjxqsq_ldxx where sqid is null")
    void deleteLdxx();

    @Delete("delete from xm_yzb where sqid is null")
    void deleteYzbxx();

    @Delete("delete from ywsl_xjxqsqb_fj where ywsl_xjxqsqbid is null")
    void deleteJmFjxx();

    @Delete("delete from ywsl_gshbsqb_fj where ywsl_gshbsqbid is null")
    void deleteGsFjxx();

    @Delete("delete from ywsl_chgzsqb_fj where ywsl_chgzsqbid is null")
    void deleteHgFjxx();

    @Select("select * from (select t.*,rownum rn from (select t.id,t.fjlx_yj,t.fjlx_ej,t.fjmc,t.fjlj,u.username," +
            " to_char(t.scsj,'yyyy-MM-dd HH24:mi') scsj from ${tablename} t left join xtgl_user u on t.scr = u.userid " +
            "  where t.fjlx_yj = #{yjfjlx} and t.fjlx_ej = #{ejfjlx} and t.ywsl_gshbsqbid = #{sqdid} order by t.scsj desc) t )" +
            " WHERE rn > #{pagebefor} AND rn <= #{pageafter} ")
    List<Map<String, Object>> toDetialGs(Map<String, Object> pd);

    @Select("select t.id,t.fjlx_yj,t.fjlx_ej,t.fjmc,t.fjlj,u.username,to_char(t.scsj,'yyyy-MM-dd HH24:mi') scsj " +
            "  from ${tablename} t left join xtgl_user u on t.scr = u.userid " +
            "  where t.fjlx_yj = #{yjfjlx} and t.fjlx_ej = #{ejfjlx} and t.ywsl_gshbsqbid = #{sqdid} ")
    List<Map<String, Object>> toAllDetialGs(Map<String, Object> pd);

    @Select("select * from (select t.*,rownum rn from (select t.id,t.fjlx_yj,t.fjlx_ej,t.fjmc,t.fjlj,u.username," +
            " to_char(t.scsj,'yyyy-MM-dd HH24:mi') scsj from ${tablename} t left join xtgl_user u on t.scr = u.userid " +
            "  where t.fjlx_yj = #{yjfjlx} and t.fjlx_ej = #{ejfjlx} and t.ywsl_chgzsqbid = #{sqdid} order by t.scsj desc) t)" +
            " WHERE rn > #{pagebefor} AND rn <= #{pageafter} ")
    List<Map<String, Object>> toDetialHg(Map<String, Object> pd);

    @Select("select t.id,t.fjlx_yj,t.fjlx_ej,t.fjmc,t.fjlj,u.username,to_char(t.scsj,'yyyy-MM-dd HH24:mi') scsj " +
            "  from ${tablename} t left join xtgl_user u on t.scr = u.userid " +
            "  where t.fjlx_yj = #{yjfjlx} and t.fjlx_ej = #{ejfjlx} and t.ywsl_chgzsqbid = #{sqdid} ")
    List<Map<String, Object>> toAllDetialHg(Map<String, Object> pd);

    @Select("select c.codename label,c.codevalue value from xtgl_code c where " +
            " c.parentid = (select id from xtgl_code where type = #{type})")
    List<Map<String,Object>> queryFwxzList(String type);

    @Select("select b.xmjl,b.sqbh,b.xmmc,u.USERNAME,u.mobilephone,u.GDDH from ${tablename} b left join xtgl_user u on b.xmjl=u.userid where b.id = #{id} ")
    Map<String, Object> getxmjl(Map<String, Object> pd);

    @Select("select t.*,m.codename azlxname from get_xqsl t left join " +
            " (select * from xtgl_code where parentid in " +
            " (select id from xtgl_code where parentid = " +
            "  (select id from xtgl_code c where c.type = 'azlx'))) m on t.AZLX = m.codevalue " +
            " where t.id = #{rwid}")
    List<GetXqslEntity> queryXqByid(String rwid);

    @Select("select sqbh,xmmc from get_xqsl where id = #{rwid}")
    List<Map<String, Object>> querySqxxById(Map<String, Object> pd);

    @Select("select codename from xtgl_code where parentid in " +
            " (select id from xtgl_code where parentid = (select id from xtgl_code c where c.type = 'azlx')) " +
            " and codevalue = #{azlxbh}")
    String findAzlxBybh(String azlxbh);

    @Select("select * from xqsl_gsybxx")
    List<XmGsybxxEntity> getGsbjxxList();

    @Select("select * from xqsl_gsybxx where sqid = #{id}")
    List<XmGsybxxEntity> getGsbjxxLists(Map<String, Object> pd);

    @Insert("insert into XM_GSYBXX(id,yhh,ybkj,yxkj,bz,sqid) " +
            " values (#{id},#{yhh},#{ybkj},#{yxkj},#{bz},#{sqid})")
    void addGsbjxx(XmGsybxxEntity entity);

    @Update("update xm_gsybxx set yhh = #{yhh},ybkj = #{ybkj},yxkj=#{yxkj},bz=#{bz} where id=#{id}")
    void editGsbjxx(XmGsybxxEntity entity);

    @Delete("delete from xm_gsybxx where id=#{id}")
    void deleteGsbjxxById(String id);

    @Delete("delete from ${tablename} where id = #{id}")
    void deleteXq(Map<String, Object> pd);

    @Select("select a.*,nvl(b.num,0) num from " +
            " (select u.userid,u.username from xtgl_user u join xtgl_userrole x on x.userid = u.userid where x.roleid = " +
            " (select x.roleid from xtgl_user u join xtgl_userrole x on u.userid = x.userid  where u.userid = #{userid}) " +
            " and x.userid != #{userid}) a left join " +
            " (select xmjl,count(1) num from get_xqsl group by xmjl) b on a.userid = b.xmjl order by num ")
    List<Map<String, Object>> queryzdry(String userid);

    @Update("update wf_task set rwblr = #{zdr} where taskid = #{taskId}")
    void updateRwblr(Map<String, Object> pd);

    @Update("update ${tablename} set xmjl = #{zdr} where id =#{rwid}")
    void updateXmjl(Map<String, Object> pd);

    @Select("select * from xtgl_user where userid = #{zdr}")
    List<UserEntity> queryUserByid(String zdr);

    @Select("select e.codevalue value,e.codename label\n" +
            "from XTGL_USERROLE r\n" +
            "left join XTGL_ROLE_ORG o on r.ROLEID = o.ROLEID\n" +
            "left join XTGL_ORG g on o.ORGID = g.ORGID\n" +
            "left join XTGL_CODE e on g.ORGNAME = e.CODENAME\n" +
            "where r.USERID = #{userid}")
    Map<String, Object> defaultsqlx(@Param("userid") String userid);

    @Select("select * from xm_instance where id = #{rwid}")
    List<Map<String, Object>> getXmxxByid(String rwid);

    @Select("select t.*,to_char(t.slrq,'yyyy-MM-dd') vslrq from get_xqsl t where t.sqbh = #{sqbh}")
    List<Map<String, Object>> getXqslByBh(String sqbh);
}
