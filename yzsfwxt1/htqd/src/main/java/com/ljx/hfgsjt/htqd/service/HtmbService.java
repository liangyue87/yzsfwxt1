package com.ljx.hfgsjt.htqd.service;

import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.htqd.DemoHtbzformEntity;
import com.ljx.hfgsjt.entity.dto.htqd.XtglHtmbEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface HtmbService {

    void save(DemoHtbzformEntity entity);

    Integer findMaxIdFromRu_inst();

    void saveWfRuInst(WfRuInstEntity wfRuInstEntity);

    Integer findMaxIdFromTask();

    void savewfTaskHistory(WfTaskHistoryEntity wfTaskHistoryEntity);

    void savewfTask(WfTaskEntity wfTaskEntity);

    List<WfReNodeEntity> findByActivityidAndDirection(String activityid, String direction);

    WfDfActivityEntity findByActivityid(String activityid);

    List<WfRuInstEntity> findByPdefid(String pdef_id);

    List<WfTaskEntity> findByProcInstId(String pdef_id);

    Page<XtglHtmbEntity> findXtglHtmb(String mbmc, PageRequest request);

    void saveHtmb(XtglHtmbEntity xtglHtmbEntity);

    void deleteHtmbById(String wybs);
}
