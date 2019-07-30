package com.ljx.hfgsjt.zhys.service;

import com.ljx.hfgsjt.entity.dto.zhys.*;

import java.util.List;
import java.util.Map;

public interface SqzhysService {      //引用发起官网信息数据列表视图（视图表格字段相同）

    int selectSqzhysCount(Sqzhys sqzhys);//列表数量

    List<Sqzhys> selectSqzhysList(Sqzhys sqzhys);//数据集合

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
    String texuChange(Map<String, Object> map);
    int insertZhysSqzb(ZhysSqzb zhysSqzb);

    List<Map<String, Object>> getWbxxByXmid(String xmid);

    List<Map<String, Object>> getDbxxByXmid(String xmid);

    List<Map<String, Object>> getHbxxByXmid(String xmid);

    //查询默认人
    XmUserEntity getZhysmrr(String pdefid);
}
