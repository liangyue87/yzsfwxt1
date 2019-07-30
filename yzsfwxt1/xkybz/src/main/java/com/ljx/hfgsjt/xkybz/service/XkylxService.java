package com.ljx.hfgsjt.xkybz.service;

import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.BaseEntity;
import com.ljx.hfgsjt.entity.dto.CodeEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.CodeModel;
import com.ljx.hfgsjt.entity.dto.Sjst.SjstYhzl;
import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.dto.xkybz.*;
import com.ljx.hfgsjt.entity.xqsl.GetXqslEntity;
import com.ljx.hfgsjt.entity.xqsl.YwslChgzsqbEntity;
import com.ljx.hfgsjt.entity.xqsl.YwslGshbsqbEntity;
import com.ljx.hfgsjt.entity.xqsl.YwslXjxqsqbEntity;
import com.ljx.hfgsjt.entity.yj.GetXmlbSbyjEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

public interface XkylxService {

    void saveWfRuInst(WfRuInstEntity e);//流程实例
    void saveWfHiRuInst(WfHiInstEntity e);//流程实例
    void saveWfTaskHistory(WfTaskHistoryEntity e);//保存流程历史任务
    List<WfTaskHistoryEntity> getAllTaskHistory(String procInstId);
    void saveWfTask(WfTaskEntity e);//保存流程任务
    void saveWfRuInstContent(WfRuInstContentEntity e);//保存流程需要的信息到 流程实例使用的内容表
    WfDfActivityEntity findByActivityid(String activityid);//获取当前节点信息
    List<WfReNodeEntity> findByActivityidAndDirection(String activityid, String direction);//获取当前流程节点下一次节点信息
    WfRuInstEntity getWfRuInstByXmidAndPdefid(String activityid, String xmid);//通过项目ID和流程环节定义ID查找对应的流程实例
    List<WfTaskEntity> getTaskAllByInstId(String instid);
    WfTaskEntity getTaskByInstId(String instid,String nodekey,String attrvalue);//通过流程实例ID和类型查找到单一任务
    WfTaskEntity getWfTaskById(String taskid);
    void deleteTaskById(String taskid);
    void deleteInstById(String instid);
    List<WfRuInstContentEntity> getWfRuInstContentByInstId(String id);
    void deleteWfRuInstContentById(String id);
    void saveWfHiInstContent(WfHiInstContentEntity wfHiInstContentEntity);

    void saveXmXckcRela(Map<String,Object> xmXckcRela);//保存项目现场勘查关联表

    void saveFj(Map map);
    GetXckcxxView getXmxxFromFq(@Param("id")String id);
    GetXckcxxView getXmxxFromSqd(@Param("id")String id);
    GetXckcxxView getXckcxxByView(@Param("id")String id);
    String getQszprByQybm(@Param("qybm")String qybm);//通过区域编码获取区所指派人
    String getQsryRoleIdByQybm(@Param("qybm")String qybm);//通过区域编码获取区所人员角色id
    List<KcrwfqView> getKcrwfq(KcrwfqView kcrwfqView);//获取发起勘察的任务
    Integer getKcrwfqTotal(KcrwfqView kcrwfqView);//获取发起勘察的任务数量
    Page<WfReNodeEntity> finAllWfReNode(Specification<WfReNodeEntity> example, Pageable pageable);//
    XmInstanceEntity getXmxx(String xmid);//获取项目基本信息
    XmInstanceEntity getXmxxById(String xmid);//获取项目基本信息
    void saveXmInstance(XmInstanceEntity m);//保存项目信息
    void saveXckcRwzb(XckcRwzbEntity xckcRwzbEntity);//保存勘查任务主表
    void saveXckcRwcb(XckcRwcbEntity xckcRwcbEntity);//保存勘查任务从表
    List<Map<String,Object>> getZpry(@Param("roleid") String roleid);//获取指派人员以及在建项目 设计院、施工单位、区所
    List<GetXckcxxView> getXckcZpTask(GetXckcxxView getXckcxxView);//获取指派勘查任务 设计院、施工单位、区所
    Integer getXckcZpTotal(GetXckcxxView getXckcxxView);//获取指派勘查任务数量 设计院、施工单位、区所
    XckcRwcbEntity getXckcRwcb(String xmid,String type,String kczt);//根据项目id和勘查主表中勘察部门 去获取勘查任务未完成的勘查任务(有且只会有一条)
    List<XckcRwcbEntity> findAllXckcRwcbBySffk(@Param("xmid") String xmid, @Param("sffk") String sffk);//查询是否反馈的勘查任务从表数据
    List<GetXckcxxView> getKcrwFk(@Param("kcbm") String kcbm);//设计院 施工单位 区所 获取勘查反馈任务
    void saveXckcFkQs(XckcFkQsEntity xckcFkQsEntity);//保存区所反馈信息
    void saveXckcFkSgdw( XckcFkSgdwEntity xckcFkSgdwEntity);//保存施工单位反馈信息

    List<XckcFkQsEntity> getKcfkQRowQs(String kcrwcbid);//获取区所勘查反馈记录
    List<XckcFkQsEntity> getQsfk(String kcrwcbid,String dcnr);//app  获取区所勘查反馈记录

    void deleteKcfkRowQs(String id);//删除区所勘查反馈记录
    void saveKcfkRowQs(XckcFkQsEntity e);//保存区所勘查反馈记录

    List<CodeEntity> getGxkj();//管线口径
    List<CodeEntity> getGxcz();//管线材质
    List<CodeEntity> getZsffgw();//正上方覆盖物
    List<CodeEntity> getZlwz();//主路位置

    List<GetXckcxxView> getKcrwPj();//获取待评价的任务列表
    List<GetXckcxxView> getKcrwYhqr();//获取待用户确认的任务列表


    XckcRwzbEntity getXckcRwzbEntity(@Param("id") String id);//通过id获取勘查的任务主表信息
    XckcRwzbEntity getXckcxx(@Param("xmid") String xmid);//获取正在进行勘查的任务主表信息
    XckcRwzbEntity getKczbByXmid(String xmid,String kczt);//通过项目id查找已经完成勘查的勘查主表
    List<XckcRwcbEntity> getKcbm(@Param("kcrwzbid") String kcrwzbid);//通过勘查主表id查找参与勘查的部门
    XckcRwzbEntity getXckcRwzbIsDoneBySqdId(String id);//通过申请单ID查找已经完成勘查的勘查主表信息
    List<XckcFkQsEntity> showQsFk(@Param("id") String id);//显示区所反馈记录
    List<XckcFkFj> getFkFjlb(@Param("tablename") String tablename, @Param("kcrwcbid") String kcrwcbid);//获取勘查反馈设计院上传资料列表
    List<XckcFkFj> getPjFjlb(@Param("tablename") String tablename, @Param("kcrwzbid") String kcrwzbid);//获取勘查反馈评价资料列表
    List<XckcFkFj> getSgdwFkFjlb(@Param("kcrwcbid") String kcrwcbid);//获取勘查反馈施工单位上传资料列表
    XckcFkSgdwEntity getSgdwKcfk(@Param("kcrwcbid") String kcrwcbid);//获取施工单位反馈信息
    void savePjFj(Map map);//保存勘查评价附件
    void saveKcjgqrFj(Map map);//保存勘查结果确认附件

    List<GetSqbxxView> getLxlb(GetSqbxxView e);//获取立项列表
    int getLxlbTotal(GetSqbxxView e);//数目

    List<GetSqbxxView> getCheckZxm(XmInstance e);//获取主项目列表
    int getCheckZxmTotal(XmInstance e);//数目

    GetXqslEntity getSlxx(@Param("id")String id);
    YwslXjxqsqbEntity getSsbxxJmk(@Param("id")String id);//获取新建小区申请表的信息
    YwslGshbsqbEntity getSsbxxGsk(@Param("id")String id);//获取新建小区申请表的信息
    YwslChgzsqbEntity getSsbxxHgk(@Param("id")String id);//获取新建小区申请表的信息
    int updateSsbSflx(String tablename,String id);//修改申请表是否立项状态
    Map<String ,String> getGssByXzqybh(@Param("qybh") String qybh);//通过区域编码查找对应供水所信息
    List<CodeEntity> getXmCodeCheckbox(@Param("codename") String codename,@Param("type") String type);
    List<XmInstanceEntity> getZxm();

    int setXmidToLdxx(String sqid,String xmid);
    int findXmNumForParentId(@Param("id")String id);

    Map<String,Object> getRwbh(Map map);//调用存储过程获取任务编号
    Map<String,Object> getXmbh(Map map);//调用存储过程获取项目编号

    String getUserIdByActivityId(String activityId);//通过流程定义ID获取到UserId


    void deleteFkFj(@Param("tablename")String tablename,@Param("id") String id);//删除反馈附件

    void saveSlfj(Map map);

    List<Map<String,Object>> findSqFjLb(@Param("tablename") String tablename,@Param("name") String name,@Param("id") String id);

    List<GetXmlbSbyjEntity> getYjXmxx(String xmid);

    List<CodeModel> getZlzl(String tablename, String fieldname, String sqdid, String fjlx);//获取受理资料类型数量列表

    List<SjstYhzl> getYhzl(String tablename, String fieldname, String sqdid, String fjlx);//获取受理资料某一类的附件

    List<Map<String,Object>> getFkFjData_sjy(String cbid);

    List<Map<String,Object>> getFkFjData_sgdw(String cbid);

    List<SjstYhzl> toDetial_sjy(String id, String ejfjlx, String yjfjlx);

    void saveDxDfsEntity(DxDfsEntity dxDfsEntity);//保存到短信待发送

    UserEntity getUserByUserId(String userid);


    List<Map<String,Object>> getMyBacklog(BaseEntity baseEntity);//获取我的待办任务
    int getMyBacklogTotal(BaseEntity baseEntity);//获取我的待办任务数量
    List<Map<String,Object>> getMyDone(BaseEntity baseEntity);//获取我办理的
    int getMyDoneTotal(BaseEntity baseEntity);//获取我办理的

    List<XckcFkFj> xmjlCk(String kcfkid);

    String getAzlxbm(String azlx);//项目编码 安装类型编码
}
