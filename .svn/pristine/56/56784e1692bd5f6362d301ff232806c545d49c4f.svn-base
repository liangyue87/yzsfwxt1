package com.ljx.hfgsjt.js.mapper;

import com.ljx.hfgsjt.entity.dto.Sjst.InstEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.TaskEntity;
import com.ljx.hfgsjt.entity.dto.js.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface JsspCsMapper {

    int selectJssqCsCount(Jssq jssq);//列表数量

    List<Jssq> selectJssqCsList(Jssq jssq);//数据集合

    ys_rucb selectYshz(@Param("flbh") String flbh, @Param("xmid") String xmid);//
    JsRwCb selectJshz(@Param("flbh") String flbh, @Param("xmid") String xmid);//

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
    void deleteGwxxFj_sh(@Param("xmid") String xmid, @Param("fjlx") String fjlx);//
    void deleteGwxxZb(String xmid);

    InstEntity selectRuInst(@Param("pdef_id") String flbh, @Param("xmid") String xmid);//

    @Select("delete from WF_TASK k where k.TASKID = #{taskid}")
    void delectTask(@Param("taskid") String taskid);
}
