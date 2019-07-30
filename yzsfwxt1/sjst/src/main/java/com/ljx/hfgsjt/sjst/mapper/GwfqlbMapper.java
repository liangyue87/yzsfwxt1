package com.ljx.hfgsjt.sjst.mapper;

import com.ljx.hfgsjt.entity.dto.Sjst.GwfqlbModel;
import com.ljx.hfgsjt.entity.dto.Sjst.InstContentEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.InstEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GwfqlbMapper {

    int getGwfqlbCount(GwfqlbModel gwfq);

    List<GwfqlbModel> getGwfqlbList(GwfqlbModel gwfq);

    InstContentEntity getRuInstContent(String xmid);

    void insetHiinstContent(InstContentEntity instContentEntity);

    InstEntity getRuInst(String xmid);

    void insertHiInst(InstEntity instEntity);

    TaskEntity getTask(String xmid);

    void insertTask(TaskEntity taskEntity);


    void deleteRuInstContent(String prodid);
    void deleteRuInst(String prodid);
    void deleteTask(String prodid);

}
