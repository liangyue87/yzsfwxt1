package com.ljx.hfgsjt.zhys.service;

import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.Sjst.XzqyEntity;
import com.ljx.hfgsjt.entity.dto.zhys.XmUserEntity;
import com.ljx.hfgsjt.entity.dto.zhys.ZhysRwcbEntity;
import com.ljx.hfgsjt.entity.dto.zhys.ZhysRwcbFjEntity;
import com.ljx.hfgsjt.entity.dto.zhys.ZhysRwzbEntity;

import java.util.List;
import java.util.Map;

public interface XdzhysdService {
    XmInstance selectRwzbid(String xmid);
    XmUserEntity getQs(String role);
    XmUserEntity getUser(String role);
    XzqyEntity getQsmc(String qsbh);

    int selectBmzl(String  zhyscbid);//列表数量
    List<ZhysRwcbFjEntity> selectBmzllist(Map<String, Object> map);//数据集合

    int selectXdzhysdCount(Map<String, Object> map);//列表数量

    List<XmInstance> selectXdzhysdList(Map<String, Object> map);//数据集合

    void saveZhysRwzb(ZhysRwzbEntity zhysRwzbEntity);

    void saveZhysRwcb(ZhysRwcbEntity zhysRwcbEntity);

    List<Map<String, Object>> getZpry(Map<String, Object> pd);

    void updateCbBmcyry(Map<String, Object> pd);

    void upoadFile(Map<String, Object> pd);


    List<Map<String, Object>> findZhyscbidByBmAndId(Map<String, Object> pd);

    void updateCbBmfkyj(Map<String, Object> pd);

    List<Map<String, Object>> queryRoleIdByUserId(Map<String, Object> pd);

    List<Map<String, Object>> getFkyj(Map<String, Object> pd);

    void updateZbXmjlfkyj(Map<String, Object> pd);

    void updateZbXmjlfkyjCh(Map<String, Object> pd);

    List<Map<String, Object>> getFkzl(Map<String, Object> pd);

    List<Map<String,Object>> queryRoleIdByActivityId(String flag);

    List<ZhysRwcbFjEntity> getFkFjData(String cbid, String yj, String ej);

    void deleteFkFj(String fjid);
}
