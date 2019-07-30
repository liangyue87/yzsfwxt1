package com.ljx.hfgsjt.js.service;

import com.ljx.hfgsjt.entity.dto.Sjst.InstEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.TaskEntity;
import com.ljx.hfgsjt.entity.dto.js.*;

import java.util.List;
import java.util.Map;

public interface JssqCsService {

    int selectJssqCsCount(Jssq jssq);//列表数量

    List<Jssq> selectJssqCsList(Jssq jssq);//数据集合

    ys_rucb selectYshz( String flbh, String xmid);//
    JsRwCb selectJshz( String flbh, String xmid);//


    int selectJszlJcCount(String xmid);//列表数量
    List<JsRwFj> selectJszlJcList(Map<String, Object> map);//数据集合

    void updataRwzb(JsRwZb jsRwZb);
    JsRwZb selectRwzb(String xmid);

    InstEntity getRuInst(String xmid);
    TaskEntity getTask(String xmid);

    void insertTask(TaskEntity taskEntity);
    void insertHiTask(TaskEntity taskEntity);
    List<JsRwFj> selectJsFjList(String xmid);//列表数量

    void deleteGwxxCb(String zbid);
    void deleteGwxxFj(String zbid);
    void deleteGwxxFj_sh( String xmid, String fjlx);//
    void deleteGwxxZb(String xmid);

    InstEntity selectRuInst( String flbh, String xmid);//

    /**
     * 根据任务id删除任务
     * @param taskid
     */
    void delectTask(String taskid);
}
