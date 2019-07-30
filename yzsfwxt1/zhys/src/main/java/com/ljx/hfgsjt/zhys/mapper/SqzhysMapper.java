package com.ljx.hfgsjt.zhys.mapper;

import com.ljx.hfgsjt.entity.dto.zhys.*;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


public interface SqzhysMapper {     //引用发起官网信息数据列表视图（视图表格字段相同）

    int selectSqzhysCount(Sqzhys sqzhys);//列表数量

    List<Sqzhys> selectSqzhysList(Sqzhys sqzhys);//数据集合

 //   List<YwslXjxqsqbFjEntity> selectJmlist(String sqbh);//居民
    int  selectJmCount(String sqbh);//居民
    List<YwslXjxqsqbFjEntity> selectJmlist(Map<String, Object> map);//居民

    int  selectGsCount(String sqbh);//工商
    List<YwslGshbsqbFjEntity> selectGslist(Map<String, Object> map);//工商

    int  selectHwCount(String sqbh);//户外
    List<YwslChgzsqbFjEntity> selectHwlist(Map<String, Object> map);//户外

    int  selectSjcgCount(String xmid);//设计成果数量
    List<SjstRwSjstcgEntity> selectSjcglist(Map<String, Object> map);//设计成果数量

    int  selectSgCount(String xmid);//设计成果数量
    List<SgxtBgxxFjEntity> selectSglist(Map<String, Object> map);//施工资料数量

    int selectTysl(String xmid);//庭院数量
    int selectLdsl(String xmid);//楼栋数量
    int selectDxssl(String xmid);//地下室数量
    int selectBfsl(String xmid);//泵房数量
    int selectSxsl(String xmid);//水箱数量

    List<XmTyEntity> selectTyList(Map<String, Object> map);//庭院集合
    List<XmLdEntity> selectLdList(Map<String, Object> map);//楼栋集合
    List<XmDxsEntity> selectDxsList(Map<String, Object> map);//地下室集合
    List<XmBfEntity> selectBfList(Map<String, Object> map);//地下室集合
    List<XmSxEntity> selectSxList(Map<String, Object> map);//水箱集合

    //查询默认人
    XmUserEntity getZhysmrr(String pdefid);

   //查询存储过程的方法
    String texuChange(Map<String, Object> map);

    int insertZhysSqzb(ZhysSqzb zhysSqzb);

    @Select("select x.id,x.hh,x.hm,x.dz,x.kj,x.mph,x.ysxz,x.jffsbh from xm_sb x " +
            " where x.xm_id = #{xmid} and x.hhlxbh = 'WJJLB'")
    List<Map<String, Object>> getWbxxByXmid(String xmid);

    @Select("select x.id,x.hh,x.hm,x.dz,x.kj,x.mph,x.ysxz,x.jffsbh from xm_sb x " +
            " where x.xm_id = #{xmid} and x.hhlxbh = 'DB'")
    List<Map<String, Object>> getDbxxByXmid(String xmid);

    @Select(" select t.mc tymc,l.id ldbs,l.ldmc, " +
            " case when l.lfsx = 0 then '多层' when l.lfsx = 1 then '高层' else '' end lfsx,count(1) zhs " +
            " from xm_ld l " +
            " left join xm_ty t on l.tyid = t.id and l.xmid = t.xmid " +
            " left join xm_sb s on l.id = s.ldid and l.xmid = s.xm_id " +
            " where s.id is not null and s.xm_id =#{xmid} and s.hhlxbh = 'HB' " +
            " group by l.ldmc,l.lfsx,t.mc,l.id ")
    List<Map<String, Object>> getHbxxByXmid(String xmid);
}
