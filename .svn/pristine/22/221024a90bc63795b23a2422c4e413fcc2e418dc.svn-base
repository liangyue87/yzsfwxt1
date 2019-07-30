package com.ljx.hfgsjt.yj.dao;

import com.ljx.hfgsjt.entity.dto.Sjst.CodeModel;
import com.ljx.hfgsjt.entity.dto.Sjst.ProjectModel;
import com.ljx.hfgsjt.entity.dto.Sjst.XmSb;
import com.ljx.hfgsjt.entity.yj.XmFm;
import com.ljx.hfgsjt.entity.yj.YjDzjcb;
import com.ljx.hfgsjt.entity.yj.YjDzjzb;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BwyjMapper {
    int getProjectCount(ProjectModel model);

    List<ProjectModel> getProjectList(ProjectModel model);

    int getFmCount(String xmid);

    List<XmFm> getFmData(@Param("xmid") String xmid,@Param("startRow") int startRow,@Param("endRow") int endRow);

    int getSbCount(String xmid);

    List<XmSb> getSbData(@Param("xmid") String xmid, @Param("startRow") int startRow, @Param("endRow") int endRow);

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

    List<YjDzjzb> getDzjlbData(@Param("xmid") String xmid, @Param("startRow") int startRow, @Param("endRow") int endRow);

    List<YjDzjzb> getfmdzjData(String id);

    void dzjHandleDelete(@Param("userid") String userid,@Param("id")  String id, @Param("zt") String zt);

    String getNextNode(@Param("activityId") String activityId,@Param("direction") int direction);

    List<YjDzjcb> getCbid(String id);

    List<Map<String,Object>> getrwblr(String xmid);

    List<String> getDzjid(String xmid);

    int submitShow(String xmid);

    int getQsBwyjlbCount(ProjectModel model);

    List<ProjectModel> getQsBwyjlbList(ProjectModel model);

    String getxmyjCount(String xmid);
}
