package com.ljx.hfgsjt.wtys.service;


import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.wtys.YsZjqtFkEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface YsZjqtFkService {


    public Page<YsZjqtFkEntity> getYsZjqtFkList(Pageable pageable);

    void upoadFile(Map<String, Object> pd);

    void saveYsZjqtFk(Map<String, Object> pd);

    List<Map<String,Object>> findTaskByRefid(Map<String, Object> pd);

    List<Map<String,Object>> findRuInstByPid(Map<String, Object> pd);

    void insertWfHiInst(Map<String,Object> map);

    void insertWfHiInstContent(Map<String, Object> pd);

    void deleteWfRuInst(Map<String, Object> pd);

    void deleteWfRuInstContent(Map<String, Object> pd);

    void deleteTask(Map<String, Object> pd);

    List<Map<String,Object>> queryZjqtzbById(Map<String, Object> pd);

    List<Map<String,Object>> queryBmById(Map<String, Object> pd);

    List<Map<String, Object>> queryZjqtcbById(Map<String, Object> pd);

    List<Map<String, Object>> queryZjqtfjById(Map<String, Object> pd);

    void deleteFjByid(Map<String, Object> pd);

    List<XmInstance> getXmxxByid(String xmid);

    List<String> queryPara(String xmid);

    List<String> queryFkByzbid(String zbid);

    List<String> queryParas(String xmid);

    List<Map<String,Object>> queryFkByzbids(String zbid);
}
