package com.ljx.hfgsjt.yj.service;

import com.ljx.hfgsjt.entity.dto.Sjst.CodeModel;
import com.ljx.hfgsjt.entity.dto.Sjst.ProjectModel;
import com.ljx.hfgsjt.entity.dto.Sjst.XmSb;
import com.ljx.hfgsjt.entity.yj.*;

import java.util.List;
import java.util.Map;

public interface BwyjService {
    int getProjectCount(ProjectModel model);

    List<ProjectModel> getProjectList(ProjectModel model);


    int getFmCount(String xmid);

    List<XmFm> getFmData(String xmid, int startRow, int endRow);

    void insertFm(XmFm model);

    int getSbCount(String xmid);

    List<XmSb> getSbData(String xmid, int startRow, int endRow);

    List<CodeModel> getFmkj();

    List<CodeModel> getFmlx();

    List<CodeModel> getFmzy();

    List<CodeModel> getZdfs();

    List<CodeModel> getZdfx();

    List<CodeModel> getfjgcz();

    List<CodeModel> getFjgxs();

    List<XmFm> fmpzHandle(String id);

    List<CodeModel> getFgcz();

    void fmpzDelete(XmFm model);

    int getDzjlbCount(String xmid);

    List<YjDzjzb> getDzjlbData(String xmid, int startRow, int endRow);

    List<YjDzjzb> getfmdzjData(String id);

    void insertDzjcb(YjDzjcb cbModel);

    void insertDzj(YjDzjzb model);

    void dzjHandleDelete(String userid, String id, String zt);

    String getNextNode(String activityId, int direction);

    List<YjDzjcb> getCbid(String id);

    List<Map<String,Object>> getrwblr(String xmid);

    void saveBwyjzbModel(YjBwyjzb bwyjzbModel);

    List<String> getDzjid(String xmid);

    void saveBwyjcbModel(YjBwyjcb bwyjcbModel);

    int submitShow(String xmid);

    int getQsBwyjlbCount(ProjectModel model);

    List<ProjectModel> getQsBwyjlbList(ProjectModel model);

    String getxmyjCount(String xmid);
}
