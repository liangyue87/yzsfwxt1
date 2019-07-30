package com.ljx.hfgsjt.xkybz.controller;

import com.alibaba.fastjson.JSON;
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
import com.ljx.hfgsjt.message.controller.MessageController;
import com.ljx.hfgsjt.message.service.MessageService;
import com.ljx.hfgsjt.repository.CalculateHours;
import com.ljx.hfgsjt.util.utils.*;
import com.ljx.hfgsjt.xkybz.service.*;
import com.ljx.hfgsjt.xqsl.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/***
 * 二、现场勘查与立项
 */
@RestController
@RequestMapping(value = "XkylxController")
@Api(description = "现场勘查与立项")
public class XkylxController {

    @Autowired
    private XkylxService service;
    @Autowired
    private XqslService xqslService;
    @Autowired
    private MessageService messageService;
    @Autowired
    MessageController controller;

    CalculateHours cal = new CalculateHours();


    /***
     * 测试
     * @param request
     * @return
     */
    /*@RequestMapping(value = "testEndTime", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "测试")
    public String testEndTime(String beginTime,int min,HttpServletRequest request) {
        Date date = cal.getEndTime(beginTime,min);
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = sdft.format(date);
        return s;
    }*/


    /***
     * 项目或申请单未勘查列表
     * @param kcrwfqView
     * @param request
     * @return
     */
    @RequestMapping(value = "getXckcTask", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取勘查任务")
    public String getXckcTask(KcrwfqView kcrwfqView, HttpServletRequest request) {
        //从项目中查询未进行联合勘查的项目
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
        kcrwfqView.setXmjl(user.getUserid());
        kcrwfqView.setStartRow();
        kcrwfqView.setEndRow();
        List<KcrwfqView> row = service.getKcrwfq(kcrwfqView);
        int total = service.getKcrwfqTotal(kcrwfqView);
        JSONObject jo = new JSONObject();
        jo.put("total", total);
        jo.put("row", row);
        return jo.toString();
    }

    /***
     * 根据id或者项目基本信息
     * @param response
     * @return
     */
    @RequestMapping(value = "getXmxx", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取项目或者申请单的信息")
    public String getXmxx(String xmid, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jo = new JSONObject();
        String type = request.getParameter("type");
        try {
            if (!"".equals(type) && type != null) {
                List<GetXmlbSbyjEntity> getXmlbSbyjEntity = service.getYjXmxx(xmid);
                jo.put("xmxx", getXmlbSbyjEntity);
            } else {
                XmInstanceEntity xmInstanceEntity = service.getXmxxById(xmid);
                if (xmInstanceEntity == null) {
                    GetXckcxxView getXckcxxView = service.getXmxxFromSqd(xmid);
                    jo.put("xmxx", getXckcxxView);
                    jo.put("sflx", 0);
                } else {
                    jo.put("xmxx", xmInstanceEntity);
                    jo.put("sflx", 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 获取现场勘查任务信息
     *
     * @param xmid
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "ShowXckcxx", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取现场勘查任务信息")
    public String ShowXckcxx(String xmid, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jo = new JSONObject();
        XckcRwzbEntity xckcRwzbEntity = service.getXckcxx(xmid);
        if (xckcRwzbEntity == null) {
            XmInstanceEntity xmInstanceEntity = service.getXmxx(xmid);
            try {
                xckcRwzbEntity = service.getXckcxx(xmInstanceEntity.getQbid());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String kcrwzbid = xckcRwzbEntity.getId();
        List<XckcRwcbEntity> list = service.getKcbm(kcrwzbid);
        jo.put("xckc", xckcRwzbEntity);
        jo.put("bm", list);
        return jo.toString();
    }


    /**
     * 修改勘察时间
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "saveKcsj", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改勘察时间")
    public String saveKcsj(String kczbid, String newkcsj) {
        JSONObject jo = new JSONObject();
        try {
            XckcRwzbEntity xckcRwzbEntity = service.getXckcRwzbEntity(kczbid);

            if (xckcRwzbEntity.getKczt().equals("1") || xckcRwzbEntity.getKczt().equals("2")) {
                SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                xckcRwzbEntity.setYykcrq(sdft.parse(newkcsj));
                service.saveXckcRwzb(xckcRwzbEntity);
                jo.put("result", "success");
                jo.put("msg", "修改预约勘察时间成功");
            } else {
                jo.put("result", "warning");
                jo.put("msg", "勘察任务已经进行，无法修改预约勘察时间");
            }

        } catch (Exception e) {
            e.printStackTrace();
            jo.put("result", "error");
            jo.put("msg", "修改预约勘察时间异常");
        }
        return jo.toString();
    }

    /**
     * 终止勘察任务
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "abandonKcrw", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "终止勘察任务")
    public String abandonKcrw(String xmid, String kczbid, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        try {
            XckcRwzbEntity xckcRwzbEntity = service.getXckcRwzbEntity(kczbid);
            xckcRwzbEntity.setKczt("6");//任务终止
            service.saveXckcRwzb(xckcRwzbEntity);
            WfRuInstEntity wfRuInstEntity = service.getWfRuInstByXmidAndPdefid("", xmid);
            HttpSession session = request.getSession();
            String czr = ((UserEntity) session.getAttribute("user")).getUserid();
            wfRuInstEntity.setStopuser(czr);
            wfRuInstEntity.setStopdt(new Date());
            service.saveWfHiRuInst(new WfHiInstEntity(wfRuInstEntity));//保存到历史流程实例
            service.deleteInstById(wfRuInstEntity.getProcInstId());//删除流程实例
            List<WfTaskEntity> list = service.getTaskAllByInstId(wfRuInstEntity.getProcInstId());
            for (WfTaskEntity task : list) {
                task.setJssj(new Date());
                task.setJsUser(czr);
                service.saveWfTaskHistory(new WfTaskHistoryEntity(task));
                service.deleteTaskById(task.getTaskid());
            }
            List<WfTaskHistoryEntity> taskHistorys = service.getAllTaskHistory(wfRuInstEntity.getProcInstId());
            String xmmc = "";
            String xmbh ="";
            String xmjlId = "";
            String xmjl = "";
            String lxfs = "";
            try {
                XmInstanceEntity xm = service.getXmxxById(xmid);
                if(xm != null){
                    xmmc = xm.getXmmc();
                    xmbh = xm.getXmbh();
                    xmjlId = xm.getXmfzr();
                    UserEntity xmjlEntity =  service.getUserByUserId(xm.getXmfzr());
                    xmjl = xmjlEntity.getUsername();
                    lxfs = xmjlEntity.getMobilephone();
                }else {
                    GetXckcxxView sqb = service.getXmxxFromSqd(xmid);
                    xmmc = sqb.getXmmc();
                    xmbh = sqb.getSqbh();
                    xmjlId = sqb.getXmjl();
                    UserEntity xmjlEntity =  service.getUserByUserId(sqb.getXmjl());
                    xmjl = xmjlEntity.getUsername();
                    lxfs = xmjlEntity.getMobilephone();
                }
                for( WfTaskHistoryEntity task : taskHistorys){
                    //UserEntity user =  service.getUserByUserId(task.getRwblr());
                    sendMsgToEnd(xmmc,xmbh,xmjl,lxfs,xmjlId,task.getRwblr());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            jo.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("result", "error");
        }
        return jo.toString();
    }

    //发送勘查终止提醒短信
    public void sendMsgToEnd(String xmmc,String xmbh,String xmjl,String lxfs,String fsr,String jsr){
        DxDfsEntity dxDfsEntity = new DxDfsEntity();
        Map<String, Object> dxmb = messageService.getDxMb("14");
        String content = dxmb.get("DXMB").toString();
        content = content.replace("[项目名称]", xmmc);
        content = content.replace("[项目编号]", xmbh);
        content = content.replace("[项目经理]", xmjl);
        content = content.replace("[联系方式]", lxfs);
        dxDfsEntity.setId(GuidUtils.getGuid());
        dxDfsEntity.setDxmbid("14");
        dxDfsEntity.setFsr(fsr);//发送人
        dxDfsEntity.setFssj(new Date());//发送时间
        dxDfsEntity.setFscs("1");//发送次数
        dxDfsEntity.setFsnr(content);//发送内容
        UserEntity userEntity = service.getUserByUserId(jsr);
        dxDfsEntity.setJsr(userEntity.getUserid());//接收人
        dxDfsEntity.setJshm(userEntity.getMobilephone());//接收号码
        service.saveDxDfsEntity(dxDfsEntity);//保存
    }

    /***
     * 联合勘查任务创建
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "saveXckcRwzb", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "创建勘查任务")
    public String saveXckcRwzb(String xmid, String xckcsj,String kcdd, String bz, HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean sjy_value = request.getParameter("sjy_value").equals("true");//是否需要设计院参与
        boolean sjydc_value = request.getParameter("sjy_value").equals("true");//是否需要到场
        boolean sgdw_value = request.getParameter("sgdw_value").equals("true");//施工单位
        boolean sgdwdc_value = request.getParameter("sgdwdc_value").equals("true");
        boolean qs_value = request.getParameter("qs_value").equals("true");//区所
        boolean qsdc_value = request.getParameter("qsdc_value").equals("true");
        boolean ys_value = request.getParameter("ys_value").equals("true");//区所
        boolean ysdc_value = request.getParameter("ysdc_value").equals("true");
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SimpleDateFormat sdftt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date yykcsj = sdftt.parse(xckcsj);//预约勘查时间
        JSONObject jo = new JSONObject();

        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("v_stop_run", "XCKC");
            service.getRwbh(map);//获取任务编号
            String rwbh = "XCKC" + map.get("p_result");

            //将勘查任务保存到勘查任务主表
            XckcRwzbEntity xckcRwzbEntity = new XckcRwzbEntity();
            String kczbId = GuidUtils.getGuid();
            HttpSession session = request.getSession();
            UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
            String pdefId = "20001";
            String czr = user.getUserid();
            xckcRwzbEntity.setId(kczbId);
            xckcRwzbEntity.setFqr(czr);
            xckcRwzbEntity.setFqrq(new Date());
            xckcRwzbEntity.setYykcrq(yykcsj);
            xckcRwzbEntity.setKcdd(kcdd);
            xckcRwzbEntity.setBz(bz);
            xckcRwzbEntity.setKczt("1");
            xckcRwzbEntity.setRwbh(rwbh);

            //查询项目或者申请单的信息
            GetXckcxxView getXckcxxView = service.getXmxxFromFq(xmid);
            if (getXckcxxView.getLxzt().equals("1")) {//已经立项
                //保存勘查任务项目关联表（已经立项）
                Map<String, Object> xmXckcRela = new HashMap<String, Object>();
                xmXckcRela.put("id", GuidUtils.getGuid());
                xmXckcRela.put("xmid", xmid);
                xmXckcRela.put("xckcid", kczbId);
                service.saveXmXckcRela(xmXckcRela);
            } else {//未立项
                xckcRwzbEntity.setSqlb(getXckcxxView.getType());
                xckcRwzbEntity.setSqid(getXckcxxView.getId());
            }
            service.saveXckcRwzb(xckcRwzbEntity);


            //启动流程实例
            WfRuInstEntity instEntity = new WfRuInstEntity();
            WfTaskEntity task = new WfTaskEntity();
            String procInstId = GuidUtils.getGuid();
            instEntity.setProcInstId(procInstId);
            instEntity.setPdefId("20021");//设计院指派
            instEntity.setStartdt(new Date());
            instEntity.setStartuser(czr);
            instEntity.setProcstatus(1);
            instEntity.setParammap(rwbh);
            service.saveWfRuInst(instEntity);

            //将项目信息存入流程实例使用内容表(WF_RU_INST_CONTENT)
            WfRuInstContentEntity wfRuInstContentEntity = new WfRuInstContentEntity();
            wfRuInstContentEntity.setContentid(GuidUtils.getGuid());
            wfRuInstContentEntity.setProcInstId(procInstId);
            wfRuInstContentEntity.setContentvalue(xmid);
            if (getXckcxxView.getLxzt().equals("1")) {//已经立项
                wfRuInstContentEntity.setTableName("XM_INSTANCE");
            } else {//未立项 申请单
                if (getXckcxxView.getType().equals("10")) {
                    wfRuInstContentEntity.setTableName("YWSL_XJXQSQB");
                } else if (getXckcxxView.getType().equals("20")) {
                    wfRuInstContentEntity.setTableName("YWSL_GSHBSQB");
                } else if (getXckcxxView.getType().equals("30")) {
                    wfRuInstContentEntity.setTableName("YWSL_CHGZSQB");
                }
            }
            service.saveWfRuInstContent(wfRuInstContentEntity);

            //将勘查任务保存到勘查任务从表
            if (sjy_value) {//设计院
                XckcRwcbEntity sjyRwcb = new XckcRwcbEntity();
                String kccbid = GuidUtils.getGuid();
                sjyRwcb.setId(kccbid);//
                sjyRwcb.setKcrwzbid(kczbId);//勘查主表Id
                sjyRwcb.setKcbm("1");//配合勘查的部门
                sjyRwcb.setSffk("0");//是否反馈
                if (sjydc_value) {
                    sjyRwcb.setSfxydc("1");//需要到场
                } else {
                    sjyRwcb.setSfxydc("0");//不需要到场
                }
                service.saveXckcRwcb(sjyRwcb);
                //创建任务
                WfTaskEntity wfTaskEntity = new WfTaskEntity();
                task.setTaskid(GuidUtils.getGuid());
                task.setPtaskid("0");//前置任务为空
                task.setProcInstId(procInstId);//流程实例ID
                task.setCActivityid("20021");//环节编号
                WfDfActivityEntity wfDfActivityEntity = service.findByActivityid("20021");
                Date qssj = new Date();
                try {
                    if (wfDfActivityEntity.getCheckLimited() == null || wfDfActivityEntity.getCheckLimited().equals("")
                            || wfDfActivityEntity.getCheckLimited().equals("0")) {
                        task.setCheck_sx(sdft.parse("2099-12-31 23:59:59"));
                    } else {
                        task.setCheck_sx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
                    }
                    if (wfDfActivityEntity.getFinishLimited() == null || wfDfActivityEntity.getFinishLimited().equals("")
                            || wfDfActivityEntity.getFinishLimited().equals("0")) {
                        task.setJssx(sdft.parse("2099-12-31 23:59:59"));
                        task.setJssx_yl(sdft.parse("2099-12-31 23:59:59"));
                    } else {
                        task.setJssx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                        task.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute()) + Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //task.setCheck_sx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
                //task.setJssx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                //task.setJssx_yl(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getFinishMinute())+Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                task.setQssj(qssj);
                task.setIscurrent("1");//待做
                task.setCzr(czr);//操作人
                task.setIscheck("0");//未签收
                task.setHasnew("2");//无子流程
                task.setRefkey("XCKC_RWCB");
                task.setRefid(kccbid);
                String rwblr = service.getUserIdByActivityId("20021");
                task.setRwblr(rwblr);
                service.saveWfTask(task);

                //给办事人员发送任务提醒短信
                try {
                    saveDxDfs("20021", getXckcxxView.getXmmc(), czr, rwblr, rwbh, task.getTaskid());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (sgdw_value) {//施工单位
                XckcRwcbEntity sgdwRwcb = new XckcRwcbEntity();
                String kccbid = GuidUtils.getGuid();
                sgdwRwcb.setId(kccbid);//
                sgdwRwcb.setKcrwzbid(kczbId);//勘查主表Id
                sgdwRwcb.setSffk("0");//是否反馈
                sgdwRwcb.setKcbm("2");//配合勘查的部门
                if (sgdwdc_value) {
                    sgdwRwcb.setSfxydc("1");//需要到场
                } else {
                    sgdwRwcb.setSfxydc("0");//不需要到场
                }
                service.saveXckcRwcb(sgdwRwcb);
                //创建任务
                WfTaskEntity wfTaskEntity = new WfTaskEntity();
                task.setTaskid(GuidUtils.getGuid());
                task.setPtaskid("0");//前置任务为空
                task.setProcInstId(procInstId);//流程实例ID
                task.setCActivityid("20022");//环节编号
                WfDfActivityEntity wfDfActivityEntity = service.findByActivityid("20022");
                Date qssj = new Date();
                try {
                    if (wfDfActivityEntity.getCheckLimited() == null || wfDfActivityEntity.getCheckLimited().equals("")
                            || wfDfActivityEntity.getCheckLimited().equals("0")) {
                        task.setCheck_sx(sdft.parse("2099-12-31 23:59:59"));
                    } else {
                        task.setCheck_sx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
                    }
                    if (wfDfActivityEntity.getFinishLimited() == null || wfDfActivityEntity.getFinishLimited().equals("")
                            || wfDfActivityEntity.getFinishLimited().equals("0")) {
                        task.setJssx(sdft.parse("2099-12-31 23:59:59"));
                        task.setJssx_yl(sdft.parse("2099-12-31 23:59:59"));
                    } else {
                        task.setJssx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                        task.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute()) + Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //task.setCheck_sx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
                //task.setJssx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                //task.setJssx_yl(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getFinishMinute())+Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                task.setQssj(qssj);
                task.setIscurrent("1");//待做
                task.setCzr(czr);//操作人
                task.setIscheck("0");//未签收
                task.setHasnew("2");//无子流程
                task.setRefkey("XCKC_RWCB");
                task.setRefid(kccbid);
                String rwblr = service.getUserIdByActivityId("20022");
                task.setRwblr(rwblr);
                service.saveWfTask(task);

                //给办事人员发送任务提醒短信
                try {
                    saveDxDfs("20022", getXckcxxView.getXmmc(), czr, rwblr, rwbh, task.getTaskid());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (qs_value) {//区所
                XckcRwcbEntity qsRwcb = new XckcRwcbEntity();
                String kccbid = GuidUtils.getGuid();
                qsRwcb.setId(kccbid);//
                qsRwcb.setKcrwzbid(kczbId);//勘查主表Id
                qsRwcb.setSffk("0");//是否反馈
                qsRwcb.setKcbm("3");//配合勘查的部门
                if (qsdc_value) {
                    qsRwcb.setSfxydc("1");//需要到场
                } else {
                    qsRwcb.setSfxydc("0");//不需要到场
                }
                service.saveXckcRwcb(qsRwcb);
                //创建任务
                WfTaskEntity wfTaskEntity = new WfTaskEntity();
                task.setTaskid(GuidUtils.getGuid());
                task.setPtaskid("0");//前置任务为空
                task.setProcInstId(procInstId);//流程实例ID
                task.setCActivityid("20023");//环节编号
                WfDfActivityEntity wfDfActivityEntity = service.findByActivityid("20023");
                Date qssj = new Date();
                try {
                    if (wfDfActivityEntity.getCheckLimited() == null || wfDfActivityEntity.getCheckLimited().equals("")
                            || wfDfActivityEntity.getCheckLimited().equals("0")) {
                        task.setCheck_sx(sdft.parse("2099-12-31 23:59:59"));
                    } else {
                        task.setCheck_sx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
                    }
                    if (wfDfActivityEntity.getFinishLimited() == null || wfDfActivityEntity.getFinishLimited().equals("")
                            || wfDfActivityEntity.getFinishLimited().equals("0")) {
                        task.setJssx(sdft.parse("2099-12-31 23:59:59"));
                        task.setJssx_yl(sdft.parse("2099-12-31 23:59:59"));
                    } else {
                        task.setJssx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                        task.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute()) + Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //task.setCheck_sx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
                //task.setJssx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                //task.setJssx_yl(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getFinishMinute())+Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                task.setQssj(qssj);
                task.setIscurrent("1");//待做
                task.setCzr(czr);//操作人
                task.setIscheck("0");//未签收
                task.setHasnew("2");//无子流程
                task.setRefkey("XCKC_RWCB");
                task.setRefid(kccbid);
                //根据区域去找相应区所的指派人
                String qybm = getXckcxxView.getXmdz_xzqybh();//获取区域编码
                String userid = service.getQszprByQybm(qybm);
                task.setRwblr(userid);
                service.saveWfTask(task);

                //给办事人员发送任务提醒短信
                try {
                    saveDxDfs("20023", getXckcxxView.getXmmc(), czr, userid, rwbh, task.getTaskid());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ys_value) {//预算科
                XckcRwcbEntity ysRwcb = new XckcRwcbEntity();
                String kccbid = GuidUtils.getGuid();
                ysRwcb.setId(kccbid);//
                ysRwcb.setKcrwzbid(kczbId);//勘查主表Id
                ysRwcb.setSffk("0");//是否反馈
                ysRwcb.setKcbm("4");//配合勘查的部门
                if (ysdc_value) {
                    ysRwcb.setSfxydc("1");//需要到场
                } else {
                    ysRwcb.setSfxydc("0");//不需要到场
                }
                service.saveXckcRwcb(ysRwcb);
                //创建任务
                WfTaskEntity wfTaskEntity = new WfTaskEntity();
                task.setTaskid(GuidUtils.getGuid());
                task.setPtaskid("0");//前置任务为空
                task.setProcInstId(procInstId);//流程实例ID
                task.setCActivityid("20024");//环节编号
                WfDfActivityEntity wfDfActivityEntity = service.findByActivityid("20024");
                Date qssj = new Date();
                try {
                    if (wfDfActivityEntity.getCheckLimited() == null || wfDfActivityEntity.getCheckLimited().equals("")
                            || wfDfActivityEntity.getCheckLimited().equals("0")) {
                        task.setCheck_sx(sdft.parse("2099-12-31 23:59:59"));
                    } else {
                        task.setCheck_sx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
                    }
                    if (wfDfActivityEntity.getFinishLimited() == null || wfDfActivityEntity.getFinishLimited().equals("")
                            || wfDfActivityEntity.getFinishLimited().equals("0")) {
                        task.setJssx(sdft.parse("2099-12-31 23:59:59"));
                        task.setJssx_yl(sdft.parse("2099-12-31 23:59:59"));
                    } else {
                        task.setJssx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                        task.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute()) + Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //task.setCheck_sx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
                //task.setJssx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                //task.setJssx_yl(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getFinishMinute())+Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                task.setQssj(qssj);
                task.setIscurrent("1");//待做
                task.setCzr(czr);//操作人
                task.setIscheck("0");//未签收
                task.setHasnew("2");//无子流程
                task.setRefkey("XCKC_RWCB");
                task.setRefid(kccbid);
                String rwblr = service.getUserIdByActivityId("20024");
                task.setRwblr(rwblr);
                service.saveWfTask(task);

                //给办事人员发送任务提醒短信
                try {
                    saveDxDfs("20024", getXckcxxView.getXmmc(), czr, rwblr, rwbh, task.getTaskid());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //创建任务，存入历史任务表中
            String taskId = GuidUtils.getGuid();
            task.setTaskid(taskId);
            task.setRwblr(user.getUserid());
            task.setPtaskid("0");//前置任务为空
            task.setProcInstId(procInstId);//流程实例ID
            task.setCActivityid(pdefId);//环节编号
            task.setIscurrent("0");//已完成
            task.setCzr(czr);//操作人
            task.setIscheck("1");//已签收
            task.setHasnew("2");//无子流程
            task.setJssj(new Date());
            WfTaskHistoryEntity historyEntity = new WfTaskHistoryEntity(task);
            service.saveWfTaskHistory(historyEntity);
            jo.put("result", "success");
        } catch (Exception e) {
            jo.put("result", "fail");
            e.printStackTrace();
        }

        return jo.toString();
    }

    /***
     * 获取勘查指派任务 设计院/施工单位/区所
     * @param response
     * @return
     */
    @RequestMapping(value = "getXckcZpTask", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取勘查指派任务")
    public String getXckcZpTask(GetXckcxxView getXckcxxView, HttpServletResponse response) {
        JSONObject jo = new JSONObject();
        List<GetXckcxxView> list = null;
        int total = 0;
        try {
            //xmInstanceEntity.setStartRow();
            //xmInstanceEntity.setEndRow();
            list = service.getXckcZpTask(getXckcxxView);
            //total = service.getXckcZpTotal(xmInstanceEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jo.put("row", list);
        jo.put("total", total);
        return jo.toString();
    }

    /***
     * 获取指派人员名单
     * @return
     */
    @RequestMapping(value = "getZpry", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取指派人员信息")
    private String getZpry(String type, String xmid) {
        String roleid = "";
        if (type.equals("1")) {//设计院
            roleid = "369fd3cbf46a46809af4167a75f123b9";
        } else if (type.equals("2")) {//施工单位
            roleid = "369fd3cbf46a46809af4167a75f12310";
        } else if (type.equals("3")) {//区所
            GetXckcxxView getXckcxxView = service.getXckcxxByView(xmid);
            roleid = service.getQsryRoleIdByQybm(getXckcxxView.getXmdz_xzqybh());
        } else if (type.equals("4")) {//预算科
            roleid = "f8621ce59de24e06b6d2f4d55569eda6";
        }
        XckcRwzbEntity xckcRwzbEntity = service.getKczbByXmid(xmid, "");
        JSONObject jo = new JSONObject();
        List<Map<String, Object>> zpry = service.getZpry(roleid);
        jo.put("zpry", zpry);
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jo.put("yykcsj", sdft.format(xckcRwzbEntity.getYykcrq()));
        jo.put("kcdd",xckcRwzbEntity.getKcdd());
        jo.put("kcbz", xckcRwzbEntity.getBz());
        return jo.toString();
    }

    /***
     * 更改任务签收状态
     * @return
     */
    @RequestMapping(value = "updateCheckTask", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更改任务签收状态")
    private void updateCheckTask(String xmid, String type, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            String qsr = ((UserEntity) session.getAttribute("user")).getUserid();
            WfRuInstEntity wfRuInstEntity = service.getWfRuInstByXmidAndPdefid("", xmid);
            WfTaskEntity task = null;
            if (type.equals("")) {
                task = service.getTaskByInstId(wfRuInstEntity.getProcInstId(), "", "");
            } else {
                task = service.getTaskByInstId(wfRuInstEntity.getProcInstId(), "kcbm", type);
            }
            if (task.getIscheck().equals("0")) {
                task.setIscheck("1");
                task.setCheck_time(new Date());
                task.setCheckUser(qsr);//签收人
                service.saveWfTask(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 设计院、施工单位、区所指派人员
     * @param type 设计院、施工单位、区所
     * @param xmid 项目ID
     * @param ryid 指派人员ID
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "saveTaskZp", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "指派人员")
    public String saveTaskZp(String type, String xmid, String ryid, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();
        XckcRwcbEntity xckcRwcbEntity = service.getXckcRwcb(xmid, type, "");
        xckcRwcbEntity.setZpry(ryid);//
        xckcRwcbEntity.setFpsj(new Date());//
        xckcRwcbEntity.setFpr(((UserEntity) session.getAttribute("user")).getUserid());//
        service.saveXckcRwcb(xckcRwcbEntity);
        XckcRwzbEntity xckcRwzbEntity = service.getKczbByXmid(xmid, "");
        xckcRwzbEntity.setKczt("2");
        service.saveXckcRwzb(xckcRwzbEntity);//更改勘查主表状态
        String activitiyid = "";
        if (type.equals("1")) {
            activitiyid = "20021";
        } else if (type.equals("2")) {
            activitiyid = "20022";
        } else if (type.equals("3")) {
            activitiyid = "20023";
        } else if (type.equals("4")) {
            activitiyid = "20024";
        }
        Date yykcsj = xckcRwzbEntity.getYykcrq();
        taskIsFinished_zpry(yykcsj,xmid, "20021", type, "0",
                ((UserEntity) session.getAttribute("user")).getUserid(), ryid, "XCKC_RWCB", xckcRwcbEntity.getId(), "");

        //发送短信给勘查人员
        String content = "";
        String phone = "";
        Map<String, Object> user = null;
        try {
            SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user = messageService.getUserxxById(ryid);
            phone = user.get("MOBILEPHONE").toString();

            String dxmbid = "9";//短信模板ID
            Map<String, Object> dxmb = messageService.getDxMb(dxmbid);
            content = dxmb.get("DXMB").toString();
            content = content.replace("[任务编号]", xckcRwzbEntity.getRwbh());
            content = content.replace("[勘查时间]", sdft.format(xckcRwzbEntity.getYykcrq()));
            content = content.replace("[勘查地点]", xckcRwzbEntity.getKcdd());
            GetXckcxxView getXckcxxView = service.getXckcxxByView(xmid);
            Map<String, Object> xmjl = messageService.getUserxxById(getXckcxxView.getXmjl());
            content = content.replace("[项目经理]", xmjl.get("USERNAME").toString());
            content = content.replace("[联系方式]", xmjl.get("MOBILEPHONE").toString());
            DxDfsEntity dxDfsEntity = new DxDfsEntity();//初始化短信模版
            dxDfsEntity.setId(GuidUtils.getGuid());
            dxDfsEntity.setDxmbid(dxmbid);
            dxDfsEntity.setFsr(((UserEntity) session.getAttribute("user")).getUserid());//发送人
            dxDfsEntity.setFssj(new Date());//发送时间
            dxDfsEntity.setFscs("1");//发送次数
            dxDfsEntity.setFsnr(content);//发送内容
            dxDfsEntity.setJsr(user.get("USERID").toString());//接收人
            dxDfsEntity.setJshm(phone);//接收号码
            service.saveDxDfsEntity(dxDfsEntity);//保存

            XmInstanceEntity xmInstanceEntity = service.getXmxx(xmid);
            if (xmInstanceEntity != null) {
                if (type.equals("1")) {
                    xmInstanceEntity.setSjfzr(ryid);
                } else if (type.equals("2")) {
                    xmInstanceEntity.setSgfzr(ryid);
                } else if (type.equals("3")) {
                    xmInstanceEntity.setQsfzr(ryid);
                } else if (type.equals("4")) {
                    xmInstanceEntity.setYsfzr(ryid);
                }
                service.saveXmInstance(xmInstanceEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jo.put("result", "success");
        return jo.toString();
    }

    /***
     * 获取勘查反馈任务列表
     * @return
     */
    @RequestMapping(value = "getKcrwFk", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取勘查反馈任务列表")
    private String getKcrwFk(String kcbm) {
        JSONObject jo = new JSONObject();
        List<GetXckcxxView> list = service.getKcrwFk(kcbm);
        jo.put("row", list);
        return jo.toString();
    }

    /***
     * 获取区所反馈表单下拉框数据
     * 管线材质 管线口径 正上方覆盖物 相对主路位置
     */
    @RequestMapping(value = "getFkQsCode", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取区所反馈表单下拉框数据")
    private String getFkQsCode() {
        JSONObject jo = new JSONObject();
        List<CodeEntity> gxkj = service.getGxkj();
        List<CodeEntity> gxcz = service.getGxcz();
        List<CodeEntity> zsffgw = service.getZsffgw();
        List<CodeEntity> zlwz = service.getZlwz();
        jo.put("gxkj", gxkj);
        jo.put("gxcz", gxcz);
        jo.put("zsffgw", zsffgw);
        jo.put("zlwz", zlwz);
        return jo.toString();
    }

    /***
     * 获取区所勘查反馈
     * @return
     */
    @RequestMapping(value = "getKcfkRowQs", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取区所勘查反馈")
    private String getKcfkRowQs(String xmid, String type) {
        JSONObject jo = new JSONObject();
        XckcRwcbEntity xckcRwcbEntity = service.getXckcRwcb(xmid, type, "2");
        List<XckcFkQsEntity> list = service.getKcfkQRowQs(xckcRwcbEntity.getId());
        jo.put("row", list);
        jo.put("kcrwcbid", xckcRwcbEntity.getId());
        return jo.toString();
    }

    /***
     * 删除区所勘查反馈
     * @return
     */
    @ApiOperation(value = "删除区所勘查反馈")
    @RequestMapping(value = "deleteKcfkRowQs", method = RequestMethod.POST)
    @ResponseBody
    private String deleteKcfkRowQs(String id) {
        JSONObject jo = new JSONObject();
        try {
            service.deleteKcfkRowQs(id);
            jo.put("result", "success");
        } catch (Exception e) {
            jo.put("result", "fail");
            e.printStackTrace();
        }
        return jo.toString();
    }

    /***
     * 保存区所勘查反馈
     * @return
     */
    @RequestMapping(value = "saveKcfkRowQs", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存区所勘查反馈")
    private String saveKcfkRowQs(String kcrwcbid, XckcFkQsEntity xckcFkQsEntity, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        try {
            if (xckcFkQsEntity.getId() == null || xckcFkQsEntity.getId().equals("")) {
                xckcFkQsEntity.setId(GuidUtils.getGuid());
            }
            xckcFkQsEntity.setKcrwcbid(kcrwcbid);
            HttpSession session = request.getSession();
            UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
            String czr = user.getUserid();
            xckcFkQsEntity.setCjr(czr);//创建人
            xckcFkQsEntity.setCjsj(new Date());
            service.saveXckcFkQs(xckcFkQsEntity);
            jo.put("result", "success");
        } catch (Exception e) {
            jo.put("result", "fail");
            e.printStackTrace();
        }
        return jo.toString();
    }

    @RequestMapping(value = "getKczbxx", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "部门反馈时项目经理勘查备注获取")
    public String getKczbxx(String xmid) {
        JSONObject jo = new JSONObject();
        String kczt = "2";
        XckcRwzbEntity xckcRwzbEntity = service.getKczbByXmid(xmid, kczt);
        jo.put("kcbz", xckcRwzbEntity.getBz());
        jo.put("kcdd", xckcRwzbEntity.getKcdd());
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jo.put("kcsj", sdft.format(xckcRwzbEntity.getYykcrq()));
        return jo.toString();
    }

    @RequestMapping(value = "getFkFjData", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取勘查反馈时上传的附件")
    public String getFkFjData(String xmid, String type) {
        JSONObject jo = new JSONObject();
        XckcRwzbEntity xckcRwzbEntity = service.getKczbByXmid(xmid, "");
        String kcrwzbid = xckcRwzbEntity.getId();
        List<XckcRwcbEntity> list = service.getKcbm(kcrwzbid);
        String cbid = "";
        String tablename = "";
        for (XckcRwcbEntity cb : list) {
            if (cb.getKcbm().equals(type)) {
                cbid = cb.getId();
            }
        }
        List<XckcFkFj> fj = null;
        if (type.equals("1")) {
            tablename = "XCKC_RWCB_FJ";
            fj = service.getFkFjlb(tablename, cbid);
        } else if (type.equals("2")) {
            tablename = "XCKC_FK_SGDW_FJ";
            fj = service.getFkFjlb(tablename, cbid);
        } else if (type.equals("3")) {
            tablename = "XCKC_FK_QS_FJ";
            fj = service.getFkFjlb(tablename, cbid);
        } else if (type.equals("4")) {
            tablename = "XCKC_RWCB_YS_FJ";
            fj = service.getFkFjlb(tablename, cbid);
        }
        jo.put("fj", fj);
        return jo.toString();
    }

    @RequestMapping(value = "getFkFjData_sjy", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取勘查反馈时上传的附件")
    public String getFkFjData_sjy(String xmid, String type) {
        JSONObject jo = new JSONObject();
        XckcRwzbEntity xckcRwzbEntity = service.getKczbByXmid(xmid, "");
        String kcrwzbid = xckcRwzbEntity.getId();
        List<XckcRwcbEntity> list = service.getKcbm(kcrwzbid);
        String cbid = "";
        String tablename = "";
        for (XckcRwcbEntity cb : list) {
            if (cb.getKcbm().equals(type)) {
                cbid = cb.getId();
            }
        }
        List<Map<String, Object>> fj = new ArrayList<>();
        fj = service.getFkFjData_sjy(cbid);
        jo.put("fj", fj);
        return jo.toString();
    }

    @RequestMapping(value = "getFkFjData_sgdw", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取勘查反馈时上传的附件")
    public String getFkFjData_sgdw(String xmid, String type) {
        JSONObject jo = new JSONObject();
        XckcRwzbEntity xckcRwzbEntity = service.getKczbByXmid(xmid, "");
        String kcrwzbid = xckcRwzbEntity.getId();
        List<XckcRwcbEntity> list = service.getKcbm(kcrwzbid);
        String cbid = "";
        String tablename = "";
        for (XckcRwcbEntity cb : list) {
            if (cb.getKcbm().equals(type)) {
                cbid = cb.getId();
            }
        }
        List<Map<String, Object>> fj = new ArrayList<>();
        fj = service.getFkFjData_sgdw(cbid);
        jo.put("fj", fj);
        return jo.toString();
    }

    @RequestMapping(value = "getPjFjData", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取勘查评价时上传的附件")
    public String getPjFjData(String xmid, String type) {
        JSONObject jo = new JSONObject();
        String kcrwzbid = "";
        String tablename = "";
        if (type.equals("4")) {
            XckcRwzbEntity xckcRwzbEntity = service.getKczbByXmid(xmid, "3");
            kcrwzbid = xckcRwzbEntity.getId();
            tablename = "XCKC_PJ_FJ";
        } else if (type.equals("5")) {
            XckcRwzbEntity xckcRwzbEntity = service.getKczbByXmid(xmid, "4");
            kcrwzbid = xckcRwzbEntity.getId();
            tablename = "XCKC_KHYJ_FJ";
        }
        List<XckcFkFj> fj = service.getPjFjlb(tablename, kcrwzbid);
        jo.put("fj", fj);
        return jo.toString();
    }

    @RequestMapping(value = "deleteFkFj", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除勘查反馈时上传的附件")
    public String deleteFkFj(String id, String type) {
        JSONObject jo = new JSONObject();
        try {
            String tablename = "";
            if (type.equals("1")) {
                tablename = "XCKC_RWCB_FJ";
            } else if (type.equals("2")) {
                tablename = "XCKC_FK_SGDW_FJ";
            } else if (type.equals("3")) {
                tablename = "XCKC_FK_QS_FJ";
            } else if (type.equals("4")) {
                tablename = "XCKC_PJ_FJ";
            } else if (type.equals("5")) {
                tablename = "XCKC_KHYJ_FJ";
            } else if (type.equals("6")) {
                tablename = "XCKC_RWCB_YS_FJ";
            }
            service.deleteFkFj(tablename, id);
            jo.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("result", "fail");
        }
        return jo.toString();
    }


    /***
     * 现场勘查意见反馈
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "saveXckcFk", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "现场勘查意见反馈")
    public String saveXckcFk(String xmid, String type, String info,
                             XckcFkSgdwEntity xckcFkSgdwEntity,
                             HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        JSONObject jo = new JSONObject();
        try {
            XckcRwcbEntity xckcRwcbEntity = service.getXckcRwcb(xmid, type, "2");
            SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            if (type.equals("1")) {//设计院
                // Date dcsj = sdft.parse(sjkcsj);
                xckcRwcbEntity.setSffk("1");
                xckcRwcbEntity.setSfdc("1");
                xckcRwcbEntity.setFkyj(info);
                //xckcRwcbEntity.setDcsj(dcsj);
                xckcRwcbEntity.setFksj(new Date());
                service.saveXckcRwcb(xckcRwcbEntity);
                jo.put("result", "success");
            } else if (type.equals("2")) {//施工单位
                //Date dcsj = sdft.parse(sjkcsj);
                xckcFkSgdwEntity.setId(GuidUtils.getGuid());//ID
                xckcFkSgdwEntity.setKcrwcbid(xckcRwcbEntity.getId());//勘查从表ID
                UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
                String czr = user.getUserid();
                xckcFkSgdwEntity.setCjr(czr);//创建人
                xckcFkSgdwEntity.setCjsj(new Date());//创建时间
                service.saveXckcFkSgdw(xckcFkSgdwEntity);
                xckcRwcbEntity.setSffk("1");
                xckcRwcbEntity.setSfdc("1");
                xckcRwcbEntity.setFkyj(info);
                //xckcRwcbEntity.setDcsj(dcsj);
                xckcRwcbEntity.setFksj(new Date());
                service.saveXckcRwcb(xckcRwcbEntity);
                jo.put("result", "success");
            } else if (type.equals("3")) {//区所
                xckcRwcbEntity.setSffk("1");
                xckcRwcbEntity.setSfdc("1");
                xckcRwcbEntity.setFkyj(info);
                //xckcRwcbEntity.setDcsj(dcsj);
                xckcRwcbEntity.setFksj(new Date());
                service.saveXckcRwcb(xckcRwcbEntity);
                jo.put("result", "success");
            } else if (type.equals("4")) {//预算科
                // Date dcsj = sdft.parse(sjkcsj);
                xckcRwcbEntity.setSffk("1");
                xckcRwcbEntity.setSfdc("1");
                xckcRwcbEntity.setFkyj(info);
                //xckcRwcbEntity.setDcsj(dcsj);
                xckcRwcbEntity.setFksj(new Date());
                service.saveXckcRwcb(xckcRwcbEntity);
                jo.put("result", "success");
            }
            String sffk = "0";
            List<XckcRwcbEntity> list = service.findAllXckcRwcbBySffk(xmid, sffk);
            XckcRwzbEntity xckcRwzbEntity = null;
            if (list.size() == 0) {
                xckcRwzbEntity = service.getKczbByXmid(xmid, "2");
                xckcRwzbEntity.setKczt("3");
                service.saveXckcRwzb(xckcRwzbEntity);
            } else {
                xckcRwzbEntity = new XckcRwzbEntity();
                xckcRwzbEntity.setId("");
            }
            String activityid = "";
            if (type.equals("1")) {
                activityid = "20031";
            } else if (type.equals("2")) {
                activityid = "20032";
            } else if (type.equals("3")) {
                activityid = "20033";
            }
            GetXckcxxView getXckcxxView = service.getXckcxxByView(xmid);
            String rwblr = getXckcxxView.getXmjl(); //任务完成 任务办理人为项目经理
            taskIsFinished(xmid, "20031", type, "0",
                    ((UserEntity) session.getAttribute("user")).getUserid(), rwblr, "XCKC_RWCB", xckcRwcbEntity.getId(), xckcRwzbEntity.getId());
        } catch (Exception e) {
            jo.put("result", "fail");
            e.printStackTrace();
        }
        return jo.toString();
    }

    /***
     * 获取勘查反馈评价列表
     * @return
     */
    @RequestMapping(value = "getKcfkPj", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取勘查反馈评价列表")
    private String getKcfkPj() {
        JSONObject jo = new JSONObject();
        List<GetXckcxxView> list = service.getKcrwPj();
        jo.put("row", list);
        return jo.toString();
    }

    /***
     * 获取参与勘查的部门
     * @return
     */
    @RequestMapping(value = "getFkpjKcbm", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取参与勘查的部门")
    private String getFkpjKcbm(String xmid, String kczt) {
        JSONObject jo = new JSONObject();
        XckcRwzbEntity xckcRwzbEntity = service.getKczbByXmid(xmid, kczt);
        String kcrwzbid = xckcRwzbEntity.getId();
        List<XckcRwcbEntity> list = service.getKcbm(kcrwzbid);
        XckcRwcbEntity arrays[] = new XckcRwcbEntity[4];
        for (XckcRwcbEntity cb : list) {
            if (cb.getKcbm().equals("3")) {//区所反馈
                arrays[0] = cb;
                List<XckcFkQsEntity> qsfk = service.showQsFk(cb.getId());
                List<XckcFkFj> qsFj = service.getFkFjlb("xckc_fk_qs_fj", cb.getId());
                jo.put("qsFk", qsfk);
                jo.put("qsFj", qsFj);
                jo.put("qsFkyj", cb.getFkyj());//反馈意见
            } else if (cb.getKcbm().equals("1")) {//设计院反馈
                arrays[2] = cb;
                List<XckcFkFj> sjyFj = service.getFkFjlb("XCKC_RWCB_FJ", cb.getId());
                jo.put("sjyFkyj", cb.getFkyj());
                jo.put("sjyFj", sjyFj);
            } else if (cb.getKcbm().equals("4")) {//预算科
                arrays[3] = cb;
                List<XckcFkFj> sjyFj = service.getFkFjlb("XCKC_RWCB_YS_FJ", cb.getId());
                jo.put("ysFkyj", cb.getFkyj());
                jo.put("ysFj", sjyFj);
            } else if (cb.getKcbm().equals("2")) {//施工单位反馈
                arrays[1] = cb;
                List<XckcFkFj> sgdwFj = service.getSgdwFkFjlb(cb.getId());
                XckcFkSgdwEntity sgdwFk = service.getSgdwKcfk(cb.getId());
                jo.put("sgdwFk", sgdwFk);//反馈信息
                jo.put("sgdwFkyj", cb.getFkyj());//反馈意见
                jo.put("sgdwFj", sgdwFj);//反馈附件
            }
        }
        List<XckcRwcbEntity> row = new ArrayList<XckcRwcbEntity>();//重新排序
        for (XckcRwcbEntity cb : arrays) {
            if (cb != null) {
                row.add(cb);
            }
        }
        jo.put("row", row);
        jo.put("kcrwzbid", kcrwzbid);
        return jo.toString();
    }

    /***
     * 获取参与勘查的部门
     * @return
     */
    @RequestMapping(value = "getFkpjKcbm_sjst", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取参与勘查的部门")
    private String getFkpjKcbm_sjst(String xmid, String kczt) {
        JSONObject jo = new JSONObject();
        String kcrwzbid = "";
        List<XckcRwcbEntity> list = null;
        List<XckcFkFj> fj = null;
        List<XckcFkFj> khtyfj = null;
        String xmjlfkyj = "";
        String sjkcrq = "";
        String khtysj = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        XckcRwzbEntity xckcRwzbEntity = service.getKczbByXmid(xmid, kczt);
        if (xckcRwzbEntity == null) {
            XmInstanceEntity xmInstanceEntity = service.getXmxx(xmid);
            try {
                xckcRwzbEntity = service.getXckcxx(xmInstanceEntity.getQbid());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (xckcRwzbEntity != null) {
            kcrwzbid = xckcRwzbEntity.getId();
            list = service.getKcbm(kcrwzbid);
            for (XckcRwcbEntity cb : list) {
                if (cb.getKcbm().equals("3")) {//区所反馈
                    List<XckcFkQsEntity> qsfk = service.showQsFk(cb.getId());
                    List<XckcFkFj> qsFj = service.getFkFjlb("xckc_fk_qs_fj", cb.getId());
                    jo.put("qsFk", qsfk);
                    jo.put("qsFj", qsFj);
                    jo.put("qsFkyj", cb.getFkyj());//反馈意见
                } else if (cb.getKcbm().equals("1")) {//设计院反馈
                    List<XckcFkFj> sjyFj = service.getFkFjlb("XCKC_RWCB_FJ", cb.getId());
                    jo.put("sjyFkyj", cb.getFkyj());
                    jo.put("sjyFj", sjyFj);
                } else if (cb.getKcbm().equals("4")) {//预算科反馈
                    List<XckcFkFj> sjyFj = service.getFkFjlb("XCKC_RWCB_YS_FJ", cb.getId());
                    jo.put("ysFkyj", cb.getFkyj());
                    jo.put("ysFj", sjyFj);
                } else if (cb.getKcbm().equals("2")) {//施工单位反馈
                    List<XckcFkFj> sgdwFj = service.getSgdwFkFjlb(cb.getId());
                    XckcFkSgdwEntity sgdwFk = service.getSgdwKcfk(cb.getId());
                    jo.put("sgdwFk", sgdwFk);//反馈信息
                    jo.put("sgdwFkyj", cb.getFkyj());//反馈意见
                    jo.put("sgdwFj", sgdwFj);//反馈附件
                }
            }
            try {
                if(xckcRwzbEntity.getKczt().equals("4") || xckcRwzbEntity.getKczt().equals("5")){
                    fj = service.getPjFjlb("XCKC_PJ_FJ", kcrwzbid);
                    xmjlfkyj = xckcRwzbEntity.getXmjlpj();
                }
                if(xckcRwzbEntity.getKczt().equals("5")){
                    khtysj = formatter.format(xckcRwzbEntity.getKhtjsj());
                    khtyfj = service.getPjFjlb("XCKC_KHYJ_FJ" , kcrwzbid);
                    if (xckcRwzbEntity.getSjdcrq() != null) {
                        sjkcrq = formatter.format(xckcRwzbEntity.getSjdcrq());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        jo.put("row", list);
        jo.put("kcrwzbid", kcrwzbid);
        jo.put("fj", fj);
        jo.put("xmjlfkyj", xmjlfkyj);
        jo.put("sjkcrq", sjkcrq);
        jo.put("khtysj", khtysj);
        jo.put("khtyfj", khtyfj);
        return jo.toString();
    }

    /***
     * 获取反馈上传资料列表列表
     * @return
     */
    @RequestMapping(value = "getFkFjlb", method = RequestMethod.GET)
    @ResponseBody
    private String getFkFjlb(String type, String kcrwcbid) {
        JSONObject jo = new JSONObject();
        String tablename = "";
        if (type.equals("1")) {
            tablename = "XCKC_RWCB_FJ";
        }
        List<XckcFkFj> list = service.getFkFjlb(tablename, kcrwcbid);
        jo.put("row", list);
        return jo.toString();
    }

    /***
     * 勘查反馈评价
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "saveXckcPj", method = RequestMethod.POST)
    @ResponseBody
    public String saveXckcPj(String xmid, String info, String sjkcsj, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jo = new JSONObject();
        try {
            SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
            XckcRwzbEntity xckcRwzbEntity = service.getKczbByXmid(xmid, "3");
            xckcRwzbEntity.setKczt("4");//勘查评价完成
            xckcRwzbEntity.setXmjlpj(info);
            xckcRwzbEntity.setPjsj(new Date());
            xckcRwzbEntity.setSjdcrq(sdft.parse(sjkcsj));
            service.saveXckcRwzb(xckcRwzbEntity);
            HttpSession session = request.getSession();
            GetXckcxxView getXckcxxView = service.getXckcxxByView(xmid);
            String rwblr = getXckcxxView.getXmjl();
            taskIsFinished(xmid, "20006", "", "0",
                    ((UserEntity) session.getAttribute("user")).getUserid(), rwblr, "XCKC_RWZB", xckcRwzbEntity.getId(), "");
            jo.put("result", "success");
        } catch (Exception e) {
            jo.put("result", "fail");
            e.printStackTrace();
        }
        return jo.toString();
    }

    /***
     * 获取勘查反馈用户确认列表
     * @return
     */
    @RequestMapping(value = "getKcfkYhqr", method = RequestMethod.GET)
    @ResponseBody
    private String getKcfkYhqr() {
        JSONObject jo = new JSONObject();
        List<GetXckcxxView> list = service.getKcrwYhqr();
        jo.put("row", list);
        return jo.toString();
    }

    /***
     * 保存勘查反馈结果客户确认
     * @return
     */
    @RequestMapping(value = "saveXckcJgqr", method = RequestMethod.POST)
    @ResponseBody
    private String saveXckcJgqr(String xmid, String khsfty, String yhtysj, HttpServletRequest request) {
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
        JSONObject jo = new JSONObject();
        try {
            XckcRwzbEntity xckcRwzbEntity = service.getKczbByXmid(xmid, "4");
            xckcRwzbEntity.setKczt("5");
            xckcRwzbEntity.setKhsfty(khsfty);
            xckcRwzbEntity.setKhtjsj(sdft.parse(yhtysj));
            //xckcRwzbEntity.setKhtjsj(new Date());
            service.saveXckcRwzb(xckcRwzbEntity);

            HttpSession session = request.getSession();

            GetXckcxxView getXckcxxView = service.getXckcxxByView(xmid);
            String rwblr = getXckcxxView.getXmjl();
            taskIsFinished(xmid, "20007", "", "0",
                    ((UserEntity) session.getAttribute("user")).getUserid(), rwblr, "XCKC_RWZB", xckcRwzbEntity.getId(), "");
            jo.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("result", "success");
        }
        return jo.toString();
    }


    /***
     * 获取立项列表
     * @param request
     * @return
     */
    @RequestMapping(value = "getLxlb", method = RequestMethod.GET)
    @ResponseBody
    public String getLxlb(GetSqbxxView getSqbxxView, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
        getSqbxxView.setXmjl(user.getUserid());
        getSqbxxView.setStartRow();
        getSqbxxView.setEndRow();
        List<GetSqbxxView> row = service.getLxlb(getSqbxxView);
        int total = service.getLxlbTotal(getSqbxxView);
        JSONObject jo = new JSONObject();
        jo.put("total", total);
        jo.put("row", row);
        return jo.toString();
    }

    /***
     * 获取申请信息
     * @param response
     * @return
     */
    @RequestMapping(value = "getSsbxx", method = RequestMethod.GET)
    @ResponseBody
    public String getSsbxx(String id, String type, HttpServletResponse response) {
        JSONObject jo = new JSONObject();
        if (type.equals("10")) {//居民科
            YwslXjxqsqbEntity ywslXjxqsqbEntity = service.getSsbxxJmk(id);
            jo.put("ssbxx", ywslXjxqsqbEntity);
        } else if (type.equals("20")) {//工商科
            YwslGshbsqbEntity ywslGshbsqbEntity = service.getSsbxxGsk(id);
            jo.put("ssbxx", ywslGshbsqbEntity);
        } else if (type.equals("30")) {//户改科
            YwslChgzsqbEntity ywslChgzsqbEntity = service.getSsbxxHgk(id);
            jo.put("ssbxx", ywslChgzsqbEntity);
        }
        return jo.toString();
    }

    /***
     * 获取申请信息
     * @param response
     * @return
     */
    @RequestMapping(value = "getSlxx", method = RequestMethod.GET)
    @ResponseBody
    public String getSlxx(String id, String type, HttpServletResponse response) {
        JSONObject jo = new JSONObject();
        GetXqslEntity getXqslEntity = service.getSlxx(id);
        jo.put("ssbxx", getXqslEntity);
        return jo.toString();
    }

    /***
     * 获取下拉框数据
     * @param response
     * @return
     */
    @RequestMapping(value = "initSqbCheckbox", method = RequestMethod.GET)
    @ResponseBody
    public String initSqbCheckbox(String type, HttpServletResponse response, String flag) {
        JSONObject jo = new JSONObject();
        Map<String, Object> pd = new HashMap<>();
        String tp = "";
        if (type.equals("10")) {
            tp = "jmk";
        } else if (type.equals("20")) {
            tp = "gsk";
        } else {
            tp = "hgk";
        }
        pd.put("type", tp);
        List<Map<String, Object>> qyList = xqslService.getQyList(pd);
        List<Map<String, Object>> azList = xqslService.getAzList(pd);
        List<CodeEntity> xmflm = service.getXmCodeCheckbox("项目分类码", type);
        List<CodeEntity> gctzm = service.getXmCodeCheckbox("工程特征码", "");
        List<Map<String, Object>> gsfsOption = xqslService.queryFwxzList(flag);
        List<XmInstanceEntity> xm = service.getZxm();
        jo.put("qyList", qyList);
        jo.put("azList", azList);
        jo.put("xmflm", xmflm);
        jo.put("gctzm", gctzm);
        jo.put("gsfsOption", gsfsOption);
        jo.put("xm", xm);
        return jo.toString();
    }

    /***
     * 获取立项列表
     * @param response
     * @return
     */
    @RequestMapping(value = "getCheckZxm", method = RequestMethod.GET)
    @ResponseBody
    public String getCheckZxm(XmInstance xmInstance, HttpServletResponse response) {
        xmInstance.setStartRow();
        xmInstance.setEndRow();
        List<GetSqbxxView> row = service.getCheckZxm(xmInstance);
        int total = service.getCheckZxmTotal(xmInstance);
        JSONObject jo = new JSONObject();
        jo.put("total", total);
        jo.put("row", row);
        return jo.toString();
    }

    /*获取立项时受理资料*/
    @ApiOperation(value = "资料总览")
    @RequestMapping(value = "getZlzl", method = RequestMethod.GET)
    @ResponseBody
    public String getZlzl(HttpServletRequest request, String sqdid, String type) {
        List<CodeModel> list = new ArrayList<CodeModel>();
        List<CodeModel> listfomat = new ArrayList<CodeModel>();
        String fjlx = "";
        String tablename = "";
        String fieldname = "";
        if (!StringUtil.isEmpty(sqdid)) {
            switch (type) {
                case "10":
                    fjlx = "2a52373c3bad43bb9b5236f6ce95455d";
                    tablename = "YWSL_XJXQSQB_FJ";
                    fieldname = "YWSL_XJXQSQBID";
                    break;
                case "20":
                    fjlx = "86c0fe78eb204bb5a45cfd64cae13839";
                    tablename = "YWSL_GSHBSQB_FJ";
                    fieldname = "YWSL_GSHBSQBID";
                    break;
                case "30":
                    fjlx = "96fd81786e1146b6abfd095cd135c15b";
                    tablename = "YWSL_CHGZSQB_FJ";
                    fieldname = "YWSL_CHGZSQBID";
                    break;
                default:
                    break;
            }
            list = service.getZlzl(tablename, fieldname, sqdid, fjlx);
        }
        if (list != null && list.size() > 0) {
            for (CodeModel codeModel : list
            ) {
                if (StringUtil.isEmpty(codeModel.getParentid())) {
                    listfomat.add(codeModel);
                }
            }
            for (CodeModel codefomatModel : listfomat
            ) {
                List<CodeModel> listChildren = new ArrayList<CodeModel>();
                for (CodeModel codeModels : list
                ) {
                    if (!StringUtil.isEmpty(codeModels.getParentid())) {
                        if (codeModels.getParentid().equals(codefomatModel.getId())) {
                            listChildren.add(codeModels);

                        }
                    }
                }
                codefomatModel.setChildren(listChildren);
            }
        }
        JSONObject jo = new JSONObject();
        jo.put("row", listfomat);
        return jo.toString();
    }

    @ApiOperation(value = "资料详情")
    @RequestMapping(value = "toDetial", method = RequestMethod.GET)
    @ResponseBody
    public String toDetial(HttpServletRequest request, String type, String fjlx, String sqdid) {
        List<SjstYhzl> list = new ArrayList<SjstYhzl>();
        String tablename = "";
        String fieldname = "";
        if (!StringUtil.isEmpty(sqdid)) {
            switch (type) {
                case "10":
                    tablename = "YWSL_XJXQSQB_FJ";
                    fieldname = "YWSL_XJXQSQBID";
                    break;
                case "20":
                    tablename = "YWSL_GSHBSQB_FJ";
                    fieldname = "YWSL_GSHBSQBID";
                    break;
                case "30":
                    tablename = "YWSL_CHGZSQB_FJ";
                    fieldname = "YWSL_CHGZSQBID";
                    break;
                default:
                    break;
            }
            list = service.getYhzl(tablename, fieldname, sqdid, fjlx);
        }
        JSONObject jo = new JSONObject();
        jo.put("row", list);
        return jo.toString();
    }

    /***
     * 立项
     * @param type
     * @return
     */
    @RequestMapping(value = "saveLxxx", method = RequestMethod.POST)
    @ResponseBody
    public String saveLxxx(String id, String type, String xmlb, String zxmmc, String zxmid, String xmmc, String xmfl, String gctz, String gsfs,
                           HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        try {
            String xmbh = "";
            String tablename = "";
            XmInstanceEntity xmInstanceEntity = new XmInstanceEntity();
            List<Map<String, Object>> fjlist = null;
            if (type.equals("10")) {//居民科
                xmbh += "KJ";
                tablename = "YWSL_XJXQSQB";
                YwslXjxqsqbEntity ywslXjxqsqbEntity = service.getSsbxxJmk(id);
                xmInstanceEntity.setAzlx(ywslXjxqsqbEntity.getAzlx());
                xmInstanceEntity.setQbid(ywslXjxqsqbEntity.getId());
                xmInstanceEntity.setSqbh(ywslXjxqsqbEntity.getSqbh());
                xmInstanceEntity.setSlrbh(ywslXjxqsqbEntity.getSlr());
                xmInstanceEntity.setXmfzr(ywslXjxqsqbEntity.getXmjl());
                xmInstanceEntity.setLxr(ywslXjxqsqbEntity.getLxr());
                xmInstanceEntity.setLxrsjh(ywslXjxqsqbEntity.getLxrsjh());
                xmInstanceEntity.setXmdzSheng(ywslXjxqsqbEntity.getXmdz_sheng());
                xmInstanceEntity.setXmdzShi(ywslXjxqsqbEntity.getXmdz_shi());
                xmInstanceEntity.setXmdzXzqybh(ywslXjxqsqbEntity.getXmdz_xzqybh());
                xmInstanceEntity.setXmdz(ywslXjxqsqbEntity.getXmdz());
                xmInstanceEntity.setDwmc(ywslXjxqsqbEntity.getDwmc());
                xmInstanceEntity.setSlsj(ywslXjxqsqbEntity.getSlrq());
                xmInstanceEntity.setGsfs(gsfs);
                xmInstanceEntity.setLxdh(ywslXjxqsqbEntity.getZjlxdh());
                ywslXjxqsqbEntity.setSflx("1");
                fjlist = service.findSqFjLb("YWSL_XJXQSQB_FJ", "YWSL_XJXQSQBID", ywslXjxqsqbEntity.getId());
            } else if (type.equals("20")) {//工商科
                xmbh += "KG";
                tablename = "YWSL_GSHBSQB";
                YwslGshbsqbEntity ywslGshbsqbEntity = service.getSsbxxGsk(id);
                xmInstanceEntity.setAzlx(ywslGshbsqbEntity.getAzlx());
                xmInstanceEntity.setQbid(ywslGshbsqbEntity.getId());
                xmInstanceEntity.setSqbh(ywslGshbsqbEntity.getSqbh());
                xmInstanceEntity.setSlrbh(ywslGshbsqbEntity.getSlr());
                xmInstanceEntity.setXmfzr(ywslGshbsqbEntity.getXmjl());
                xmInstanceEntity.setLxr(ywslGshbsqbEntity.getLxr());
                xmInstanceEntity.setLxrsjh(ywslGshbsqbEntity.getLxrsjh());
                xmInstanceEntity.setXmdzSheng(ywslGshbsqbEntity.getXmdz_sheng());
                xmInstanceEntity.setXmdzShi(ywslGshbsqbEntity.getXmdz_shi());
                xmInstanceEntity.setXmdzXzqybh(ywslGshbsqbEntity.getXmdz_xzqybh());
                xmInstanceEntity.setXmdz(ywslGshbsqbEntity.getXmdz());
                xmInstanceEntity.setDwmc(ywslGshbsqbEntity.getDwmc());
                xmInstanceEntity.setSlsj(ywslGshbsqbEntity.getSlrq());
                xmInstanceEntity.setLxdh(ywslGshbsqbEntity.getZjlxdh());
                fjlist = service.findSqFjLb("YWSL_GSHBSQB_FJ", "YWSL_GSHBSQBID", ywslGshbsqbEntity.getId());
            } else if (type.equals("30")) {//户改科
                xmbh += "KC";
                tablename = "YWSL_CHGZSQB";
                YwslChgzsqbEntity ywslChgzsqbEntity = service.getSsbxxHgk(id);
                xmInstanceEntity.setAzlx(ywslChgzsqbEntity.getAzlx());
                xmInstanceEntity.setQbid(ywslChgzsqbEntity.getId());
                xmInstanceEntity.setSqbh(ywslChgzsqbEntity.getSqbh());
                xmInstanceEntity.setSlrbh(ywslChgzsqbEntity.getSlr());
                xmInstanceEntity.setXmfzr(ywslChgzsqbEntity.getXmjl());
                xmInstanceEntity.setLxr(ywslChgzsqbEntity.getLxr());
                xmInstanceEntity.setLxrsjh(ywslChgzsqbEntity.getLxrsjh());
                xmInstanceEntity.setXmdzSheng(ywslChgzsqbEntity.getXmdz_sheng());
                xmInstanceEntity.setXmdzShi(ywslChgzsqbEntity.getXmdz_shi());
                xmInstanceEntity.setXmdzXzqybh(ywslChgzsqbEntity.getXmdz_xzqybh());
                xmInstanceEntity.setXmdz(ywslChgzsqbEntity.getXmdz());
                xmInstanceEntity.setDwmc(ywslChgzsqbEntity.getDwmc());
                xmInstanceEntity.setSlsj(ywslChgzsqbEntity.getSlrq());
                xmInstanceEntity.setLxdh(ywslChgzsqbEntity.getZjlxdh());
                fjlist = service.findSqFjLb("YWSL_CHGZSQB_FJ", "YWSL_CHGZSQBID", ywslChgzsqbEntity.getId());
            }
            Map gss = service.getGssByXzqybh(xmInstanceEntity.getXmdzXzqybh());
            xmInstanceEntity.setBmbmQs(gss.get("BH").toString());
            String azlxbh = "";
            azlxbh = service.getAzlxbm(xmInstanceEntity.getAzlx());
            xmbh += azlxbh;
            //SimpleDateFormat sdft = new SimpleDateFormat("yyyyMM");
            //xmbh += sdft.format(new Date());
            Map<String, Object> mmp = new HashMap<String, Object>();
            mmp.put("v_stop_run", "XMBH");
            service.getXmbh(mmp);//获取项目编号
            xmbh += mmp.get("p_result");
            //xmbh += gctz.replaceAll(",","");//工程特征码暂时不加
            //String znxmid = "";
            if (xmlb.equals("0")) {//新建项目
                XmInstanceEntity xm = new XmInstanceEntity();
                zxmid = GuidUtils.getGuid();
                xmInstanceEntity.setPxmid(zxmid);
                xm.setId(zxmid);
                xm.setXmmc(zxmmc);
                xm.setXmbh(xmbh);
                xm.setLxsj(new Date());
                xm.setSlsj(new Date());
                service.saveXmInstance(xm);//新建一个主项目
            } else {//选择主项目
                xmInstanceEntity.setPxmid(zxmid);
                XmInstanceEntity xm = service.getXmxx(zxmid);
                xmbh = xm.getXmbh();
            }
            String xmid = GuidUtils.getGuid();
            xmInstanceEntity.setId(xmid);
            xmInstanceEntity.setXmmc(xmmc);
            xmInstanceEntity.setXmbh(xmbh);
            xmInstanceEntity.setSqlx(type);
            xmInstanceEntity.setLxsj(new Date());

            //若已经勘查过，将勘查环节设计\施工等人员放入项目主表
            XckcRwzbEntity xckcRwzbEntity = service.getXckcRwzbIsDoneBySqdId(id);
            if (xckcRwzbEntity != null) {
                List<XckcRwcbEntity> list = service.getKcbm(xckcRwzbEntity.getId());
                for (XckcRwcbEntity cb : list) {
                    if (cb.getKcbm().equals("3")) {//区所
                        xmInstanceEntity.setQsfzr(cb.getZpry());
                    } else if (cb.getKcbm().equals("1")) {//设计院
                        xmInstanceEntity.setSjfzr(cb.getZpry());
                    } else if (cb.getKcbm().equals("2")) {//施工单位
                        xmInstanceEntity.setSgfzr(cb.getZpry());
                    } else if (cb.getKcbm().equals("4")) {//预算人员
                        xmInstanceEntity.setYsfzr(cb.getZpry());
                    }
                }
            }
            //查询主项目下有几个子项目
            int i = service.findXmNumForParentId(zxmid) + 1;

            xmInstanceEntity.setXmbh(xmInstanceEntity.getXmbh() + "F" + i);
            xmInstanceEntity.setStatus("1");
            service.saveXmInstance(xmInstanceEntity);//保存项目

            //将xmid放入YWSL_XJXQSQ_LDXX 居民科/出户改造科
            if (type.equals("10") || type.equals("30")) {
                service.setXmidToLdxx(id, xmid);
            }

            //将受理资料放入xm_slfj
            for (Map mm : fjlist) {
                Map map = mm;
                map.put("XMID", xmid);
                service.saveSlfj(map);
            }

            //修改申请表中是否立项状态
            service.updateSsbSflx(tablename, id);


            //启动流程实例
            String pdefId = "20008";
            WfRuInstEntity instEntity = new WfRuInstEntity();
            WfTaskEntity task = new WfTaskEntity();
            String procInstId = GuidUtils.getGuid();
            instEntity.setProcInstId(procInstId);
            instEntity.setPdefId(pdefId);
            instEntity.setStartdt(new Date());
            instEntity.setProcstatus(1);
            //任务编号
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("v_stop_run", "LX");
            service.getRwbh(map);//获取任务编号
            String rwbh = "LX" + map.get("p_result");
            instEntity.setParammap(rwbh);
            HttpSession session = request.getSession();
            UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
            instEntity.setStartuser(user.getUserid());
            WfHiInstEntity wfHiInstEntity = new WfHiInstEntity(instEntity);
            service.saveWfHiRuInst(wfHiInstEntity);//存入历史流程实例表

            //保存到历史任务
            //创建任务
            WfTaskEntity wfTaskEntity = new WfTaskEntity();
            task.setTaskid(GuidUtils.getGuid());
            task.setPtaskid("0");//前置任务为空
            task.setProcInstId(procInstId);//流程实例ID
            task.setCActivityid("20008");//环节编号
            task.setIscurrent("0");//待做
            task.setCzr(user.getUserid());//操作人
            task.setRwblr(user.getUserid());
            task.setIscheck("1");//未签收
            task.setHasnew("2");//无子流程
            task.setRefid(xmid);
            task.setQssj(new Date());
            task.setJssj(new Date());
            task.setRefkey("XM_INSTANCE");
            WfTaskHistoryEntity wfTaskHistoryEntity = new WfTaskHistoryEntity(task);
            service.saveWfTaskHistory(wfTaskHistoryEntity);

            //将项目信息存入流程实例使用内容表(WF_RU_INST_CONTENT)
            WfRuInstContentEntity wfRuInstContentEntity = new WfRuInstContentEntity();
            wfRuInstContentEntity.setContentid(GuidUtils.getGuid());
            wfRuInstContentEntity.setProcInstId(procInstId);
            wfRuInstContentEntity.setContentvalue(xmid);
            wfRuInstContentEntity.setTableName("XM_INSTANCE");
            WfHiInstContentEntity wfHiInstContentEntity = new WfHiInstContentEntity(wfRuInstContentEntity);
            service.saveWfHiInstContent(wfHiInstContentEntity);//

            jo.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("result", "error");
        }
        return jo.toString();
    }

    /**
     * taskIsFinished 完成当前任务
     *
     * @param xmid       项目ID
     * @param activityid 流程ID
     * @param type       类型
     * @param direction  方向
     */
    public void taskIsFinished(String xmid, String activityid, String type, String direction, String czr, String rwblr, String key, String value, String value2) {
        WfRuInstEntity wfRuInstEntity = service.getWfRuInstByXmidAndPdefid(activityid, xmid);
        WfTaskEntity wfTaskEntity = null;
        if (type.equals("")) {
            wfTaskEntity = service.getTaskByInstId(wfRuInstEntity.getProcInstId(), "", "");
        } else {
            wfTaskEntity = service.getTaskByInstId(wfRuInstEntity.getProcInstId(), "kcbm", type);
        }
        wfTaskEntity.setJssj(new Date());
        wfTaskEntity.setIscurrent("0");
        WfTaskHistoryEntity wfTaskHistoryEntity = new WfTaskHistoryEntity(wfTaskEntity);
        service.saveWfTaskHistory(wfTaskHistoryEntity);//保存历史任务
        service.deleteTaskById(wfTaskEntity.getTaskid());//删除当前任务
        List<WfReNodeEntity> list = service.findByActivityidAndDirection(wfTaskEntity.getCActivityid(), direction);
        if (list.size() == 0) {//最后节点 保存到历史流程实例 并删除流程实例
            WfHiInstEntity wfHiInstEntity = new WfHiInstEntity(wfRuInstEntity);
            wfHiInstEntity.setEndtime(new Date());
            service.saveWfHiRuInst(wfHiInstEntity);
            service.deleteInstById(wfRuInstEntity.getProcInstId());
            List<WfRuInstContentEntity> contents = service.getWfRuInstContentByInstId(wfRuInstEntity.getProcInstId());//获取流程实例内容表数据
            for (WfRuInstContentEntity contentEntity : contents) {
                WfHiInstContentEntity wfHiInstContentEntity = new WfHiInstContentEntity(contentEntity);
                service.saveWfHiInstContent(wfHiInstContentEntity);//保存流程实例内容表数据到历史
                service.deleteWfRuInstContentById(contentEntity.getContentid());//删除流程实例内容表数据
            }
        } else {
            for (WfReNodeEntity wf : list) {
                WfTaskEntity task = new WfTaskEntity();
                String taskId = GuidUtils.getGuid();
                //WfDfActivityEntity ac = service.findByActivityid(wf.getActivityid());
                task.setTaskid(taskId);
                //task.setQssj(new Date());
                task.setPtaskid(wfTaskEntity.getTaskid());//前置任务为空
                task.setProcInstId(wfRuInstEntity.getProcInstId());//流程实例ID
                task.setCActivityid(wf.getNextnode());//环节编号
                WfDfActivityEntity wfDfActivityEntity = service.findByActivityid(wf.getNextnode());
                Date qssj = new Date();
                SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    if (wfDfActivityEntity.getCheckLimited() == null || wfDfActivityEntity.getCheckLimited().equals("")
                            || wfDfActivityEntity.getCheckLimited().equals("0")) {
                        task.setCheck_sx(sdft.parse("2099-12-31 23:59:59"));
                    } else {
                        task.setCheck_sx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
                    }
                    if (wfDfActivityEntity.getFinishLimited() == null || wfDfActivityEntity.getFinishLimited().equals("")
                            || wfDfActivityEntity.getFinishLimited().equals("0")) {
                        task.setJssx(sdft.parse("2099-12-31 23:59:59"));
                        task.setJssx_yl(sdft.parse("2099-12-31 23:59:59"));
                    } else {
                        task.setJssx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                        task.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute()) + Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                task.setQssj(qssj);
                task.setIscurrent("1");//未完成
                task.setCzr(czr);//操作人
                task.setIscheck("0");//未签收
                task.setHasnew("2");//无子流程
                task.setRefkey(key);
                task.setRefid(value);
                if (activityid.equals("30001")) {
                    task.setRwblr(wfTaskHistoryEntity.getRwblr());
                } else {
                    task.setRwblr(rwblr);
                }

                if (activityid.equals("20021") && wfRuInstEntity.getPdefId().equals("20021")) {
                    wfRuInstEntity.setPdefId("20031");
                }
                if (wf.getNextnode().equals("20006")) {//待勘查的几个部门
                    List<XckcRwcbEntity> ll = service.findAllXckcRwcbBySffk(xmid, "0");
                    if (ll.size() == 0) {
                        task.setRefkey("XCKC_RWZB");
                        task.setRefid(value2);
                        service.saveWfTask(task);
                        wfRuInstEntity.setPdefId(wf.getNextnode());
                        service.saveWfRuInst(wfRuInstEntity);

                        //给办事人员发送任务提醒短信
                        try {
                            GetXckcxxView getXckcxxView = service.getXckcxxByView(xmid);
                            saveDxDfs("20024", getXckcxxView.getXmmc(), czr, task.getRwblr(), wfRuInstEntity.getParammap(), task.getTaskid());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    service.saveWfTask(task);
                    service.saveWfRuInst(wfRuInstEntity);
                    if (!activityid.equals("20021")) {//除了20021环节给指派人员进行勘查发送其他短信
                        try {
                            GetXckcxxView getXckcxxView = service.getXckcxxByView(xmid);
                            saveDxDfs(task.getCActivityid(), getXckcxxView.getXmmc(), czr, task.getRwblr(), wfRuInstEntity.getParammap(), task.getTaskid());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    /**
     * taskIsFinished 完成当前任务
     *
     * @param xmid       项目ID
     * @param activityid 流程ID
     * @param type       类型
     * @param direction  方向
     */
    public void taskIsFinished_zpry(Date yykcsj,String xmid, String activityid, String type, String direction, String czr, String rwblr, String key, String value, String value2) {
        WfRuInstEntity wfRuInstEntity = service.getWfRuInstByXmidAndPdefid(activityid, xmid);
        WfTaskEntity wfTaskEntity = null;
        if (type.equals("")) {
            wfTaskEntity = service.getTaskByInstId(wfRuInstEntity.getProcInstId(), "", "");
        } else {
            wfTaskEntity = service.getTaskByInstId(wfRuInstEntity.getProcInstId(), "kcbm", type);
        }
        wfTaskEntity.setJssj(new Date());
        wfTaskEntity.setIscurrent("0");
        WfTaskHistoryEntity wfTaskHistoryEntity = new WfTaskHistoryEntity(wfTaskEntity);
        service.saveWfTaskHistory(wfTaskHistoryEntity);//保存历史任务
        service.deleteTaskById(wfTaskEntity.getTaskid());//删除当前任务
        List<WfReNodeEntity> list = service.findByActivityidAndDirection(wfTaskEntity.getCActivityid(), direction);
        if (list.size() == 0) {//最后节点 保存到历史流程实例 并删除流程实例
            WfHiInstEntity wfHiInstEntity = new WfHiInstEntity(wfRuInstEntity);
            wfHiInstEntity.setEndtime(new Date());
            service.saveWfHiRuInst(wfHiInstEntity);
            service.deleteInstById(wfRuInstEntity.getProcInstId());
            List<WfRuInstContentEntity> contents = service.getWfRuInstContentByInstId(wfRuInstEntity.getProcInstId());//获取流程实例内容表数据
            for (WfRuInstContentEntity contentEntity : contents) {
                WfHiInstContentEntity wfHiInstContentEntity = new WfHiInstContentEntity(contentEntity);
                service.saveWfHiInstContent(wfHiInstContentEntity);//保存流程实例内容表数据到历史
                service.deleteWfRuInstContentById(contentEntity.getContentid());//删除流程实例内容表数据
            }
        } else {
            for (WfReNodeEntity wf : list) {
                WfTaskEntity task = new WfTaskEntity();
                String taskId = GuidUtils.getGuid();
                //WfDfActivityEntity ac = service.findByActivityid(wf.getActivityid());
                task.setTaskid(taskId);
                //task.setQssj(new Date());
                task.setPtaskid(wfTaskEntity.getTaskid());//前置任务为空
                task.setProcInstId(wfRuInstEntity.getProcInstId());//流程实例ID
                task.setCActivityid(wf.getNextnode());//环节编号
                WfDfActivityEntity wfDfActivityEntity = service.findByActivityid(wf.getNextnode());
                Date qssj = new Date();
                SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    if (wfDfActivityEntity.getCheckLimited() == null || wfDfActivityEntity.getCheckLimited().equals("")
                            || wfDfActivityEntity.getCheckLimited().equals("0")) {
                        task.setCheck_sx(sdft.parse("2099-12-31 23:59:59"));
                    } else {
                        task.setCheck_sx(cal.getEndTime(sdft.format(yykcsj), Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
                    }
                    if (wfDfActivityEntity.getFinishLimited() == null || wfDfActivityEntity.getFinishLimited().equals("")
                            || wfDfActivityEntity.getFinishLimited().equals("0")) {
                        task.setJssx(sdft.parse("2099-12-31 23:59:59"));
                        task.setJssx_yl(sdft.parse("2099-12-31 23:59:59"));
                    } else {
                        task.setJssx(cal.getEndTime(sdft.format(yykcsj), Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                        task.setJssx_yl(cal.getEndTime(sdft.format(yykcsj), Float.parseFloat(wfDfActivityEntity.getFinishMinute()) + Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                task.setQssj(qssj);
                task.setIscurrent("1");//未完成
                task.setCzr(czr);//操作人
                task.setIscheck("0");//未签收
                task.setHasnew("2");//无子流程
                task.setRefkey(key);
                task.setRefid(value);
                if (activityid.equals("30001")) {
                    task.setRwblr(wfTaskHistoryEntity.getRwblr());
                } else {
                    task.setRwblr(rwblr);
                }

                if (activityid.equals("20021") && wfRuInstEntity.getPdefId().equals("20021")) {
                    wfRuInstEntity.setPdefId("20031");
                }
                if (wf.getNextnode().equals("20006")) {//待勘查的几个部门
                    List<XckcRwcbEntity> ll = service.findAllXckcRwcbBySffk(xmid, "0");
                    if (ll.size() == 0) {
                        task.setRefkey("XCKC_RWZB");
                        task.setRefid(value2);
                        service.saveWfTask(task);
                        wfRuInstEntity.setPdefId(wf.getNextnode());
                        service.saveWfRuInst(wfRuInstEntity);

                        //给办事人员发送任务提醒短信
                        try {
                            GetXckcxxView getXckcxxView = service.getXckcxxByView(xmid);
                            saveDxDfs("20024", getXckcxxView.getXmmc(), czr, task.getRwblr(), wfRuInstEntity.getParammap(), task.getTaskid());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    service.saveWfTask(task);
                    service.saveWfRuInst(wfRuInstEntity);
                    if (!activityid.equals("20021")) {//除了20021环节给指派人员进行勘查发送其他短信
                        try {
                            GetXckcxxView getXckcxxView = service.getXckcxxByView(xmid);
                            saveDxDfs(task.getCActivityid(), getXckcxxView.getXmmc(), czr, task.getRwblr(), wfRuInstEntity.getParammap(), task.getTaskid());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /***
     * 拍摄上传
     * @param requsetFile
     * @param request
     * @return
     */
    @RequestMapping(value = "uploadCamera", method = RequestMethod.POST)
    public String uploadCream(String date, String fileName, String dirName, MultipartFile requsetFile, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        String name = "";
        MultipartFile multipartFile = null;
        if (Objects.isNull(requsetFile)) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            multipartFile = multipartRequest.getFile("trackdata");
            if (Objects.isNull(multipartFile)) {
//                logger.error("文件为空");
                jo.put("result", "文件为空，请重新上传");
                return "";
            }
        }
        String fileDir = StringUtil.getBasePath() + "upload" + File.separator + dirName + File.separator;
        File dir = new File(fileDir);
        File file = null;
        try {
            name = fileName + date + ".jpg";
            file = new File(fileDir, name);
            if (!dir.exists()) {
                dir.mkdir();
            }
            if (!file.exists()) {
                //判断是否存在目录. 不存在则创建
                isChartPathExist(fileDir);
                file.createNewFile();
            }
            multipartFile.transferTo(file);
            jo.put("fileName", fileName);
            jo.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("result", "error");
        }
        return jo.toString();
    }

    //保存拍摄后的图片路径到数据库
    @RequestMapping(value = "saveImagePath", method = RequestMethod.POST)
    @ResponseBody
    public String saveImagePath(String xmid, String fileName, String dirName, HttpServletRequest request) {
        //String fileDir = StringUtil.getBasePath()+"upload"+File.separator+ dirName +File.separator;

        Map<String, Object> fjMap = new HashMap<>();
        fjMap.put("id", GuidUtils.getGuid());//关联勘查反馈从表id
        fjMap.put("fjlj", "upload/" + dirName + "/" + fileName);//附件路径
        fjMap.put("fjhz", fileName.substring(fileName.indexOf(".") + 1));//附件后缀
        fjMap.put("fjmc", fileName);//附件名称
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
        fjMap.put("scr", user.getUserid());//上传人
        XckcRwzbEntity xckcRwzbEntity = service.getKczbByXmid(xmid, "4");
        String kcrwzbid = xckcRwzbEntity.getId();
        fjMap.put("fjlx_ej", "86c0fe78eb204bb5a45c86c0fe78eb03");
        fjMap.put("kcrwzbid", kcrwzbid);
        service.saveKcjgqrFj(fjMap);

        return "";
    }

    /**
     * @param fileItem
     * @param response
     * @return
     */
    @ApiOperation(value = "预览图片")
    @RequestMapping(value = "showImage", method = RequestMethod.GET)
    public String showImage(String fileItem, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jo = new JSONObject();
        try {
            List<String> responseList = new ArrayList<String>();
            String[] filePath = fileItem.split(",");
            //String basePath = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")+"upload/DocCream/")).replaceAll("file:/", "").replaceAll("%20", " ").trim();
            for (String str : filePath) {
                InputStream inputStream = null;
                byte[] data = null;
                try {
                    inputStream = new FileInputStream(str);
                    data = new byte[inputStream.available()];
                    inputStream.read(data);
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 加密
                BASE64Encoder encoder = new BASE64Encoder();
                responseList.add(encoder.encode(data));
            }
            jo.put("row", responseList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jo.toString();
    }


    /***
     * 通过xmid获取xmbh   用于设计院勘查反馈高拍仪上传文件时路径
     * * @return
     */
    @RequestMapping(value = "getXmbhByXmid", method = RequestMethod.GET)
    @ResponseBody
    public String getXmbhByXmid(String xmid){
        String xmbh ="";
        try {
            XmInstanceEntity xmInstanceEntity = service.getXmxxById(xmid);
            if(xmInstanceEntity != null){
                xmbh = xmInstanceEntity.getXmbh();
            }else {
                xmbh = service.getXmxxFromSqd(xmid).getSqbh();
            }
        } catch (Exception e) {
            xmbh = "error";
            e.printStackTrace();
        }
        return xmbh;
    }

    /***
     * 文件上传
     * @param request
     * @return
     */
    @RequestMapping(value = "camera_sjy", method = RequestMethod.POST)
    @ResponseBody
    public String camera_sjy(String xmid, String fileName, String dirName,String zlType,String yjfjlx, HttpServletRequest request) {
        Map<String, Object> fjMap = new HashMap<>();
        fjMap.put("id", GuidUtils.getGuid());//关联勘查反馈从表id
        XckcRwcbEntity xckcRwcbEntity = null;
        xckcRwcbEntity = service.getXckcRwcb(xmid, "1", "2");
        fjMap.put("kcfkid", xckcRwcbEntity.getId());//主键标识
        fjMap.put("fjlj", "upload/" + dirName + "/" + fileName);//附件路径
        fjMap.put("fjhz", fileName.substring(fileName.indexOf(".") + 1));//附件后缀
        fjMap.put("fjmc", fileName);//附件名称
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
        fjMap.put("scr", user.getUserid());//上传人
        fjMap.put("tablename", "xckc_rwcb_fj");//表名
        fjMap.put("fjlx_ej", zlType);
        fjMap.put("fjlx_yj", yjfjlx);
        service.saveFj(fjMap);//将文件信息保存到附件表
        return "success";
    }

    /***
     * 文件上传
     * @param multipartFiles
     * @param filename
     * @param request
     * @return
     */
    @RequestMapping(value = "importFile_sjy", method = RequestMethod.POST, consumes = {"multipart/mixed", "multipart/form-data"})
    @ResponseBody
    public Map importFile_sjy(
            @RequestParam(value = "multipartFiles", required = false) MultipartFile multipartFiles,
            @RequestParam(value = "filename", required = false) String filename,
            @RequestParam(value = "xmid", required = false) String xmid,
            @RequestParam(value = "zlType", required = false) String zlType,
            @RequestParam(value = "yjfjlx", required = false) String yjfjlx,
            HttpServletRequest request) {
        Map resMap = new HashMap<String, Object>();
        if (Objects.isNull(multipartFiles)) {
            resMap.put("msg", "文件为空，请重新上传");
            return resMap;
        }
        String xmbh ="";
        try {
            XmInstanceEntity xmInstanceEntity = service.getXmxxById(xmid);
            if(xmInstanceEntity != null){
                xmbh = xmInstanceEntity.getXmbh();
            }else {
                xmbh = service.getXmxxFromSqd(xmid).getSqbh();
            }
        } catch (Exception e) {
            xmbh = "error";
            e.printStackTrace();
        }
        String fileDir = StringUtil.getBasePath() + "upload" + File.separator + xmbh + File.separator + "xkylx"+ File.separator;
        //String fileDir = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")+"upload/xkylx/")).replaceAll("file:/", "").replaceAll("%20", " ").trim();
        File dir = new File(fileDir);
        File file = null;
        try {
            file = new File(fileDir, multipartFiles.getOriginalFilename());
            if (!dir.exists()) {
                dir.mkdir();
            }
            if (!file.exists()) {
                //判断是否存在目录. 不存在则创建
                isChartPathExist(fileDir);
                file.createNewFile();
            }
            multipartFiles.transferTo(file);
            String[] filenames = filename.split("\\.");
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String str_date = "_" + df.format(new Date());
            String newFilename = filenames[0] + str_date + "." + filenames[filenames.length - 1];//加时间后缀
            file.renameTo(new File(fileDir + newFilename));//重命名
            Map<String, Object> fjMap = new HashMap<>();
            fjMap.put("id", GuidUtils.getGuid());//关联勘查反馈从表id
            XckcRwcbEntity xckcRwcbEntity = null;
            xckcRwcbEntity = service.getXckcRwcb(xmid, "1", "2");
            fjMap.put("kcfkid", xckcRwcbEntity.getId());//主键标识
            fjMap.put("fjlj", "upload/"+xmbh+"/xkylx/" + newFilename);//附件路径
            fjMap.put("fjhz", filenames[filenames.length - 1]);//附件后缀
            fjMap.put("fjmc", filename);//附件名称
            HttpSession session = request.getSession();
            UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
            fjMap.put("scr", user.getUserid());//上传人
            //String operation = "";
            fjMap.put("tablename", "xckc_rwcb_fj");//表名
            fjMap.put("fjlx_ej", zlType);
            fjMap.put("fjlx_yj", yjfjlx);
            service.saveFj(fjMap);//将文件信息保存到附件表
        } catch (IOException e) {
            e.printStackTrace();
            resMap.put("returncode", "500");
            resMap.put("msg", "程序异常，请联系管理员");
        }
        resMap.put("returncode", "200");
        resMap.put("msg", "上传成功");
        resMap.put("path", fileDir);
        return resMap;
    }

    /***
     * 文件上传
     * @param multipartFiles
     * @param filename
     * @param request
     * @return
     */
    @RequestMapping(value = "importFile_sgdw", method = RequestMethod.POST, consumes = {"multipart/mixed", "multipart/form-data"})
    @ResponseBody
    public Map importFile_sgdw(
            @RequestParam(value = "multipartFiles", required = false) MultipartFile multipartFiles,
            @RequestParam(value = "filename", required = false) String filename,
            @RequestParam(value = "xmid", required = false) String xmid,
            @RequestParam(value = "zlType", required = false) String zlType,
            @RequestParam(value = "yjfjlx", required = false) String yjfjlx,
            HttpServletRequest request) {
        Map resMap = new HashMap<String, Object>();
        if (Objects.isNull(multipartFiles)) {
            resMap.put("msg", "文件为空，请重新上传");
            return resMap;
        }
        String xmbh ="";
        try {
            XmInstanceEntity xmInstanceEntity = service.getXmxxById(xmid);
            if(xmInstanceEntity != null){
                xmbh = xmInstanceEntity.getXmbh();
            }else {
                xmbh = service.getXmxxFromSqd(xmid).getSqbh();
            }
        } catch (Exception e) {
            xmbh = "error";
            e.printStackTrace();
        }
        String fileDir = StringUtil.getBasePath() + "upload" + File.separator + xmbh + File.separator + "xkylx"+ File.separator;
        //String fileDir = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")+"upload/xkylx/")).replaceAll("file:/", "").replaceAll("%20", " ").trim();
        File dir = new File(fileDir);
        File file = null;
        try {
            file = new File(fileDir, multipartFiles.getOriginalFilename());
            if (!dir.exists()) {
                dir.mkdir();
            }
            if (!file.exists()) {
                //判断是否存在目录. 不存在则创建
                isChartPathExist(fileDir);
                file.createNewFile();
            }
            multipartFiles.transferTo(file);
            String[] filenames = filename.split("\\.");
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String str_date = "_" + df.format(new Date());
            String newFilename = filenames[0] + str_date + "." + filenames[filenames.length - 1];//加时间后缀
            file.renameTo(new File(fileDir + newFilename));//重命名
            Map<String, Object> fjMap = new HashMap<>();
            fjMap.put("id", GuidUtils.getGuid());//关联勘查反馈从表id
            XckcRwcbEntity xckcRwcbEntity = null;
            xckcRwcbEntity = service.getXckcRwcb(xmid, "2", "2");
            fjMap.put("kcfkid", xckcRwcbEntity.getId());//主键标识
            fjMap.put("fjlj", "upload/"+xmbh+"/xkylx/" + newFilename);//附件路径
            fjMap.put("fjhz", filenames[filenames.length - 1]);//附件后缀
            fjMap.put("fjmc", filename);//附件名称
            HttpSession session = request.getSession();
            UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
            fjMap.put("scr", user.getUserid());//上传人
            //String operation = "";
            fjMap.put("tablename", "XCKC_FK_SGDW_FJ");//表名
            fjMap.put("fjlx_ej", zlType);
            fjMap.put("fjlx_yj", yjfjlx);
            service.saveFj(fjMap);//将文件信息保存到附件表
        } catch (IOException e) {
            e.printStackTrace();
            resMap.put("returncode", "500");
            resMap.put("msg", "程序异常，请联系管理员");
        }
        resMap.put("returncode", "200");
        resMap.put("msg", "上传成功");
        resMap.put("path", fileDir);
        return resMap;
    }

    @ApiOperation(value = "资料详情")
    @RequestMapping(value = "toDetial_sjy", method = RequestMethod.GET)
    public String toDetial_sjy(HttpServletRequest request, String yjfjlx, String ejfjlx, String xmid) {
        List<SjstYhzl> list = new ArrayList<SjstYhzl>();
        //if(!StringUtil.isEmpty(yjfjlx)){
        XckcRwcbEntity xckcRwcbEntity = null;
        xckcRwcbEntity = service.getXckcRwcb(xmid, "1", "2");
        list = service.getYhzl("XCKC_RWCB_FJ", "KCFKID", xckcRwcbEntity.getId(), ejfjlx);
        //}
        JSONObject jo = new JSONObject();
        jo.put("row", list);
        return jo.toString();
    }

    @ApiOperation(value = "资料详情")
    @RequestMapping(value = "toDetial_sgdw", method = RequestMethod.GET)
    public String toDetial_sgdw(HttpServletRequest request, String yjfjlx, String ejfjlx, String xmid) {
        List<SjstYhzl> list = new ArrayList<SjstYhzl>();
        //if(!StringUtil.isEmpty(yjfjlx)){
        XckcRwcbEntity xckcRwcbEntity = null;
        xckcRwcbEntity = service.getXckcRwcb(xmid, "2", "2");
        list = service.getYhzl("XCKC_FK_SGDW_FJ", "KCFKID", xckcRwcbEntity.getId(), ejfjlx);
        //}
        JSONObject jo = new JSONObject();
        jo.put("row", list);
        return jo.toString();
    }

    /***
     * 文件上传
     * @param multipartFiles
     * @param filename
     * @param type 类型 1设计院 2区所 3施工单位 6预算科   4为勘查评价上传资料 5为勘查结果确认上传资料
     * @param request
     * @return
     */
    @RequestMapping(value = "importFile", method = RequestMethod.POST, consumes = {"multipart/mixed", "multipart/form-data"})
    @ResponseBody
    public Map importEnterPriseQuota(
            @RequestParam(value = "multipartFiles", required = false) MultipartFile multipartFiles,
            @RequestParam(value = "filename", required = false) String filename,
            @RequestParam(value = "xmid", required = false) String xmid,
            @RequestParam(value = "type", required = false) String type,
            HttpServletRequest request) {

        Map resMap = new HashMap<String, Object>();
        if (Objects.isNull(multipartFiles)) {
            resMap.put("msg", "文件为空，请重新上传");
            return resMap;
        }
        String xmbh = "";
        try {
            XmInstanceEntity xmInstanceEntity = service.getXmxxById(xmid);
            if(xmInstanceEntity != null){
                xmbh = xmInstanceEntity.getXmbh();
            }else {
                xmbh = service.getXmxxFromSqd(xmid).getSqbh();
            }
        } catch (Exception e) {
            xmbh = "error";
            e.printStackTrace();
        }
        String fileDir = StringUtil.getBasePath() + "upload" + File.separator + xmbh + File.separator + "xkylx"+ File.separator;
        //String fileDir = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")+"upload/xkylx/")).replaceAll("file:/", "").replaceAll("%20", " ").trim();
        File dir = new File(fileDir);
        File file = null;
        try {
            file = new File(fileDir, multipartFiles.getOriginalFilename());
            if (!dir.exists()) {
                dir.mkdir();
            }
            if (!file.exists()) {
                //判断是否存在目录. 不存在则创建
                isChartPathExist(fileDir);
                file.createNewFile();
            }
            multipartFiles.transferTo(file);

            String[] filenames = filename.split("\\.");

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String str_date = "_" + df.format(new Date());
            String newFilename = filenames[0] + str_date + "." + filenames[filenames.length - 1];//加时间后缀
            file.renameTo(new File(fileDir + newFilename));//重命名

            Map<String, Object> fjMap = new HashMap<>();
            fjMap.put("id", GuidUtils.getGuid());//关联勘查反馈从表id
            XckcRwcbEntity xckcRwcbEntity = null;
            if (type.equals("6")) {
                xckcRwcbEntity = service.getXckcRwcb(xmid, "4", "2");//预算科例外 type为6 但是kcbm为4
            } else {
                xckcRwcbEntity = service.getXckcRwcb(xmid, type, "2");
            }

            if (!type.equals("4") && !type.equals("5")) {
                fjMap.put("kcfkid", xckcRwcbEntity.getId());//主键标识
            }
            fjMap.put("fjlj", "upload/"+xmbh+"/xkylx/" + newFilename);//附件路径
            fjMap.put("fjhz", filenames[filenames.length - 1]);//附件后缀
            fjMap.put("fjmc", filename);//附件名称
            HttpSession session = request.getSession();
            UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
            fjMap.put("scr", user.getUserid());//上传人
            //String operation = "";
            if (type.equals("1")) {
                fjMap.put("tablename", "xckc_rwcb_fj");//表名
                fjMap.put("fjlx_ej", "86c0fe78eb204bb5a45c86c0fe78eb00");
                service.saveFj(fjMap);//将文件信息保存到附件表
            } else if (type.equals("2")) {
                fjMap.put("fjlx_ej", "86c0fe78eb204bb5a45c86c0fe78eb01");
                fjMap.put("tablename", "xckc_fk_sgdw_fj");
                service.saveFj(fjMap);//将文件信息保存到附件表
            } else if (type.equals("6")) {//预算科勘查反馈
                fjMap.put("tablename", "xckc_rwcb_ys_fj");//表名
                fjMap.put("fjlx_ej", "86c0fe78eb204bb5a45c86c0fe78eb04");
                service.saveFj(fjMap);//将文件信息保存到附件表
            } else if (type.equals("3")) {
                fjMap.put("tablename", "xckc_fk_qs_fj");
                fjMap.put("fjlx_ej", "86c0fe78eb204bb5a45c86c0fe78eb05");
                service.saveFj(fjMap);//将文件信息保存到附件表
            } else if (type.equals("4")) {//保存勘查评价资料
                XckcRwzbEntity xckcRwzbEntity = service.getKczbByXmid(xmid, "3");
                String kcrwzbid = xckcRwzbEntity.getId();
                fjMap.put("fjlx_ej", "86c0fe78eb204bb5a45c86c0fe78eb02");
                fjMap.put("kcrwzbid", kcrwzbid);
                service.savePjFj(fjMap);
            }/*else if(type.equals("5")){//保存客户确认附件资料
                XckcRwzbEntity xckcRwzbEntity = service.getKczbByXmid(xmid,"4");
                String kcrwzbid = xckcRwzbEntity.getId();
                fjMap.put("fjlx_ej","86c0fe78eb204bb5a45c86c0fe78eb03");
                fjMap.put("kcrwzbid",kcrwzbid);
                service.saveKcjgqrFj(fjMap);
            }*/
        } catch (IOException e) {
            e.printStackTrace();
            resMap.put("returncode", "500");
            resMap.put("msg", "程序异常，请联系管理员");
        }
        resMap.put("returncode", "200");
        resMap.put("msg", "上传成功");
        resMap.put("path", fileDir);
        return resMap;
    }


    /**
     * 判断文件夹是否存在，如果不存在则新建
     *
     * @param dirPath 文件夹路径
     */
    private static void isChartPathExist(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 下载用户资料
     *
     * @param path
     * @param response
     * @return
     */
    @ApiOperation(value = "下载用户资料")
    @RequestMapping(value = "download", method = RequestMethod.POST)
    public HttpServletResponse download(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            String basePath = StringUtil.getBasePath();
            //String basePath = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();
            // path是指欲下载的文件的路径。
            //path = basePath+path;
            File file = new File(basePath + path);
            System.out.println(path);
            // 取得文件名
            String filename = file.getName();
            // 取得文件的后缀名
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件
            InputStream fis = new BufferedInputStream(new FileInputStream(basePath + path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            //response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            //response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
            //response.setHeader("Access-Control-Allow-Credentials", "true");
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            //response.setCharacterEncoding("utf-8");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    /**
     * 下载Word
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @ApiOperation("下载Word")
    @RequestMapping(value = "downWord", method = RequestMethod.POST)
    @ResponseBody
    public void downWord(String json, String kcrwzbid, String rwbh, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        Map<String, Object> map = new HashMap<String, Object>();
        //把前台传过来的json进行处理
        com.alibaba.fastjson.JSONObject jo = new com.alibaba.fastjson.JSONObject(JSON.parseObject(json));
        Map<String, Object> maps = jo;
        for (Map.Entry<String, Object> entry : maps.entrySet()) {
            entry.getValue().toString();
        }
        //单独处理项目经理的反馈时间和反馈意见
        com.alibaba.fastjson.JSONObject jos = new com.alibaba.fastjson.JSONObject(JSON.parseObject(maps.get("kcpj").toString()));
        Map<String, Object> mapss = jos;
        for (Map.Entry<String, Object> entry : mapss.entrySet()) {
            entry.getValue().toString();
        }
        //单独处理接表工程概括、施工破复面积及简图（三欣）的数据
        com.alibaba.fastjson.JSONObject jbgcJson = new com.alibaba.fastjson.JSONObject(JSON.parseObject(maps.get("sgdwFk").toString()));
        Map<String, Object> jbgcMap = jbgcJson;
        for (Map.Entry<String, Object> entry : jbgcMap.entrySet()) {
            entry.getValue().toString();
        }
/*      Enumeration<String> paramNames = req.getParameterNames();
        // 通过循环将表单参数放入键值对映射中
        while (paramNames.hasMoreElements()) {
            String key = paramNames.nextElement();
            String value = req.getParameter(key);
            map.put(key, value);
        }*/

        map.put("ywh", rwbh);//勘察现场日期
        map.put("kcxcrq", mapss.get("sjkcsj").toString());//勘察现场日期
        map.put("xmmc", maps.get("xmmc").toString());//项目名称
        map.put("yh", maps.get("lxr").toString());//用户
        map.put("azdd", maps.get("xmdz").toString());//安装地址
        map.put("lxfs", maps.get("lxrsjh").toString());//联系方式
        List<XckcRwcbEntity> list = service.getKcbm(kcrwzbid);
        for (XckcRwcbEntity cb : list) {
            if (cb.getKcbm().equals("3")) {//区所反馈
                List<XckcFkQsEntity> qsfk = service.showQsFk(cb.getId());
                System.out.println(qsfk + ":------------------>");
                map.put("qsfk", qsfk);//项目接表周边管线情况(供水所)jbgcMap
            }
        }
        /*---------------接表工程概括、施工破复面积及简图（三欣）-------------------*/
        map.put("cgc", jbgcMap.get("sfycgc").toString());//超高层
        map.put("ldsx", jbgcMap.get("sfyldsx").toString());//楼顶水箱
        map.put("lhpf", jbgcMap.get("sfxydllhpf").toString());//道路绿化破复
        map.put("ypzdb", jbgcMap.get("sfxypzdb").toString());//需要配置地表
        map.put("bfgswz", jbgcMap.get("bfgswz").toString());//泵房供水位置
        map.put("ldsxwz", jbgcMap.get("ldsxwz").toString());//楼顶水箱位置
        map.put("sfxybqg", jbgcMap.get("sfxybqg").toString());//是否有可接表预留支管
        map.put("sfhykjbylzg", jbgcMap.get("sfhykjbylzg").toString());//是否含有可接表预留支管
        map.put("sfxyzglg", jbgcMap.get("sfxyzglg").toString());//是否需要做过路管
        map.put("jbxkwmj", jbgcMap.get("jbxkwmj").toString());//接表前管需要开挖面积㎡
        map.put("bqgcd", jbgcMap.get("bqgcd").toString());//表前管长度
        map.put("tgjl", jbgcMap.get("tgjl").toString());//托管距离
        map.put("dggwcpfmj", jbgcMap.get("dggwcpfmj").toString());//顶管管尾处破复面积
        map.put("glggdpfmj", jbgcMap.get("glggdpfmj").toString());//过路管勾点破复面积
        /*-----------------------------------------------------------------------*/
        try {
            map.put("sjyfk", maps.get("sjyFkyj").toString());//设计院反馈
        } catch (Exception e) {
            map.put("sjyfk", "");//设计院反馈
        }
        try {
            map.put("ysfk", maps.get("ysFkyj").toString());//预算反馈
        } catch (Exception e) {
            map.put("ysfk", "");//预算反馈
        }
        try {
            map.put("xmjlfk", mapss.get("xmjlFkyj").toString());//项目经理反馈
        } catch (Exception e) {
            map.put("xmjlfk", "");//项目经理反馈
        }
        // 提示：在调用工具类生成Word文档之前应当检查所有字段是否完整
        // 否则Freemarker的模板殷勤在处理时可能会因为找不到值而报错 这里暂时忽略这个步骤了
        File file = null;
        InputStream fin = null;
        ServletOutputStream out = null;
        try {
            // 调用工具类WordGenerator的createDoc方法生成Word文档
            file = DownUtil.createDoc(map, "resume");
            fin = new FileInputStream(file);

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/msword");
            // 设置浏览器以下载的方式处理该文件默认名为resume.doc
            response.addHeader("Content-Disposition", "attachment;filename=resume.doc");

            out = response.getOutputStream();
            byte[] buffer = new byte[512]; // 缓冲区
            int bytesToRead = -1;
            // 通过循环将读入的Word文件的内容输出到浏览器中
            while ((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fin != null)
                fin.close();
            if (out != null)
                out.close();
            if (file != null)
                file.delete(); // 删除临时文件
        }
    }


    /**
     * 任务提醒 保存到待发送表中
     *
     * @param activityid
     * @param xmmc
     * @param fsr
     * @param jsr
     * @param taskid
     * @return
     */
    public String saveDxDfs(String activityid, String xmmc, String fsr, String jsr, String rwbh, String taskid) {
        WfDfActivityEntity wfDfActivityEntity = service.findByActivityid(activityid);
        UserEntity userEntity = service.getUserByUserId(jsr);
        DxDfsEntity dxDfsEntity = new DxDfsEntity();//初始化短信模版
        Map<String, Object> dxmb = messageService.getDxMb("4");
        String content = dxmb.get("DXMB").toString();
        content = content.replace("[环节名称类型]", wfDfActivityEntity.getActivityname());
        content = content.replace("[任务单编号]", rwbh);
        content = content.replace("[项目名称]", xmmc);
        dxDfsEntity.setId(GuidUtils.getGuid());
        dxDfsEntity.setDxmbid("4");
        dxDfsEntity.setFsr(fsr);//发送人
        dxDfsEntity.setFssj(new Date());//发送时间
        dxDfsEntity.setFscs("1");//发送次数
        dxDfsEntity.setFsnr(content);//发送内容
        dxDfsEntity.setJsr(userEntity.getUserid());//接收人
        dxDfsEntity.setJshm(userEntity.getMobilephone());//接收号码
        dxDfsEntity.setTaskid(taskid);
        service.saveDxDfsEntity(dxDfsEntity);//保存
        return "";
    }




    /***
     * App 设计院反馈
     * 拍摄上传
     * @param requsetFile
     * @param request
     * @return
     */
    @RequestMapping(value = "uploadimge", method = RequestMethod.POST)
    public String uploadimge(MultipartFile requsetFile, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jo = new JSONObject();
        String type = "1";
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) (request);
        String info = mRequest.getParameter("fkyj");//
        String xmid = mRequest.getParameter("xmid");//
        Iterator<String> files = mRequest.getFileNames();
      /*  if (files != null) {*/
            String xmbh = "";
            try {
                XmInstanceEntity xmInstanceEntity = service.getXmxxById(xmid);
                if(xmInstanceEntity != null){
                    xmbh = xmInstanceEntity.getXmbh();
                }else {
                    xmbh = service.getXmxxFromSqd(xmid).getSqbh();
                }
            } catch (Exception e) {
                xmbh = "error";
                e.printStackTrace();
            }

            String fileDir = StringUtil.getBasePath() + "upload" + File.separator + xmbh + File.separator + "xkylx"+ File.separator;

            while (files.hasNext()) {
                MultipartFile mFile = mRequest.getFile(files.next());
                File dir = new File(fileDir);
                File file = null;
                try {
                    file = new File(fileDir, mFile.getOriginalFilename());
                    if (!dir.exists()) {
                        dir.mkdir();
                    }
                    if (!file.exists()) {
                        //判断是否存在目录. 不存在则创建
                        isChartPathExist(fileDir);
                        file.createNewFile();
                    }
                    mFile.transferTo(file);
                    String filename1 = mFile.getOriginalFilename();
                    String[] filenames = filename1.split("\\.");
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                    String str_date = "_" + df.format(new Date());
                    String newFilename = filenames[0] + str_date + "." + filenames[filenames.length - 1];//加时间后缀
                    file.renameTo(new File(fileDir + newFilename));//重命名

                    Map<String, Object> fjMap = new HashMap<>();
                    fjMap.put("id", GuidUtils.getGuid());//关联勘查反馈从表id
                    XckcRwcbEntity xckcRwcbEntity = null;

                    xckcRwcbEntity = service.getXckcRwcb(xmid, type, "2");//
                    fjMap.put("kcfkid", xckcRwcbEntity.getId());//主键标识
                    fjMap.put("fjlj", "upload/"+xmbh+"/xkylx/" + newFilename);//附件路径
                    fjMap.put("fjhz", filenames[filenames.length - 1]);//附件后缀
                    fjMap.put("fjmc", filename1);//附件名称
                    HttpSession session = request.getSession();
                    UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
                    fjMap.put("scr", user.getUserid());//上传人
                    fjMap.put("tablename", "xckc_rwcb_fj");//表名
                    fjMap.put("fjlx_ej", "86c0fe78eb204bb5a45c86c0fe78eb00");
                    service.saveFj(fjMap);//将文件信息保存到附件表

                } catch (Exception e) {
                    e.printStackTrace();
                    jo.put("returncode", "500");
                    jo.put("msg", "程序异常，请联系管理员");
                }
            }
            //保存数据
            saveXckcFk(xmid, type, info, null, request, response);
            jo.put("status", "200");
      /*  }*/
        return jo.toString();
    }

    /***
     * App 预算科反馈
     * 拍摄上传
     * @param requsetFile
     * @param request
     * @return
     */
    @RequestMapping(value = "uploadimge_1", method = RequestMethod.POST)
    public String uploadimge_1(MultipartFile requsetFile, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jo = new JSONObject();
        String type = "4";
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) (request);
        String info = mRequest.getParameter("fkyj");//
        System.out.println(info + "-----------");
        String xmid = mRequest.getParameter("xmid");//
        Iterator<String> files = mRequest.getFileNames();
       /* if (files != null) {*/
            String xmbh = "";
            try {
                XmInstanceEntity xmInstanceEntity = service.getXmxxById(xmid);
                if(xmInstanceEntity != null){
                    xmbh = xmInstanceEntity.getXmbh();
                }else {
                    xmbh = service.getXmxxFromSqd(xmid).getSqbh();
                }
            } catch (Exception e) {
                xmbh = "error";
                e.printStackTrace();
            }

            String fileDir = StringUtil.getBasePath() + "upload" + File.separator + xmbh + File.separator + "xkylx"+ File.separator;

            while (files.hasNext()) {
                MultipartFile mFile = mRequest.getFile(files.next());
                File dir = new File(fileDir);
                File file = null;
                try {
                    file = new File(fileDir, mFile.getOriginalFilename());
                    if (!dir.exists()) {
                        dir.mkdir();
                    }
                    if (!file.exists()) {
                        //判断是否存在目录. 不存在则创建
                        isChartPathExist(fileDir);
                        file.createNewFile();
                    }
                    mFile.transferTo(file);
                    String filename1 = mFile.getOriginalFilename();
                    String[] filenames = filename1.split("\\.");
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                    String str_date = "_" + df.format(new Date());
                    String newFilename = filenames[0] + str_date + "." + filenames[filenames.length - 1];//加时间后缀
                    file.renameTo(new File(fileDir + newFilename));//重命名

                    Map<String, Object> fjMap = new HashMap<>();
                    fjMap.put("id", GuidUtils.getGuid());//关联勘查反馈从表id
                    XckcRwcbEntity xckcRwcbEntity = null;

                    xckcRwcbEntity = service.getXckcRwcb(xmid, "4", "2");//预算科例外 type为6 但是kcbm为4

                    fjMap.put("kcfkid", xckcRwcbEntity.getId());//主键标识
                    fjMap.put("fjlj", "upload/"+xmbh+"/xkylx/" + newFilename);//附件路径
                    fjMap.put("fjhz", filenames[filenames.length - 1]);//附件后缀
                    fjMap.put("fjmc", filename1);//附件名称
                    HttpSession session = request.getSession();
                    UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
                    fjMap.put("scr", user.getUserid());//上传人
                    fjMap.put("tablename", "xckc_rwcb_ys_fj");//表名
                    fjMap.put("fjlx_ej", "86c0fe78eb204bb5a45c86c0fe78eb04");
                    service.saveFj(fjMap);//将文件信息保存到附件表

                } catch (IOException e) {
                    e.printStackTrace();
                    jo.put("returncode", "500");
                    jo.put("msg", "程序异常，请联系管理员");
                }
            }
            //保存数据
            saveXckcFk(xmid, type, info, null, request, response);
            jo.put("status", "200");
    /*    }*/
        return jo.toString();
    }


    /***
     * 保存区所项目周边管网
     * @return
     */
    @RequestMapping(value = "saveQsXmzbgw", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存区所勘查反馈")
    private String saveQsXmzbgw(String xmid, String gxzl, String gxld, String gxzzl, String gxjzl, String gxzsf,
                                String gxkj, String gxcz, String dcr, String dcrq, String bz,
                                XckcFkQsEntity xckcFkQsEntity, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            XckcRwcbEntity xckcRwcbEntity = service.getXckcRwcb(xmid, "3", "2");
            xckcFkQsEntity.setId(GuidUtils.getGuid());
            xckcFkQsEntity.setKcrwcbid(xckcRwcbEntity.getId());
            HttpSession session = request.getSession();
            UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
            String czr = user.getUserid();
            xckcFkQsEntity.setDcnr("0");
            xckcFkQsEntity.setGxzl(gxzl);
            xckcFkQsEntity.setGxld(gxld);
            xckcFkQsEntity.setGxzzl(gxzzl);
            xckcFkQsEntity.setGxjzl(gxjzl);
            xckcFkQsEntity.setGxzsf(gxzsf);
            xckcFkQsEntity.setGxkj(gxkj);
            xckcFkQsEntity.setGxcz(gxcz);
            xckcFkQsEntity.setDcr(dcr);
            xckcFkQsEntity.setDcrq(format.parse(dcrq));
            xckcFkQsEntity.setBz(bz);
            xckcFkQsEntity.setCjr(czr);//创建人
            xckcFkQsEntity.setCjsj(new Date());
            service.saveXckcFkQs(xckcFkQsEntity);
            jo.put("result", "success");
        } catch (Exception e) {
            jo.put("result", "fail");
            e.printStackTrace();
        }
        return jo.toString();
    }

    /***
     * 保存区所具体位置测压
     * @return
     */
    @RequestMapping(value = "saveQsJtwzcy", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存区所勘查反馈")
    private String saveQsJtwzcy(String xmid, String cydx, String cy_cysj, String dcdwz, String cydyl, String cydbg,
                                String bwts, String gwyj, String cy_dcr, String cy_dcsj, String cy_bz,
                                XckcFkQsEntity xckcFkQsEntity, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            XckcRwcbEntity xckcRwcbEntity = service.getXckcRwcb(xmid, "3", "2");
            xckcFkQsEntity.setId(GuidUtils.getGuid());
            xckcFkQsEntity.setKcrwcbid(xckcRwcbEntity.getId());
            HttpSession session = request.getSession();
            UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
            String czr = user.getUserid();
            xckcFkQsEntity.setDcnr("1");
            xckcFkQsEntity.setCydx(cydx);
            xckcFkQsEntity.setCysj(format.parse(cy_cysj));
            xckcFkQsEntity.setDcdwz(dcdwz);
            xckcFkQsEntity.setCydyl(cydyl);
            xckcFkQsEntity.setCydbg(cydbg);
            xckcFkQsEntity.setBwts(bwts);
            xckcFkQsEntity.setGwyj(gwyj);
            xckcFkQsEntity.setDcr(cy_dcr);
            xckcFkQsEntity.setDcrq(format.parse(cy_dcsj));
            xckcFkQsEntity.setBz(cy_bz);
            xckcFkQsEntity.setCjr(czr);//创建人
            xckcFkQsEntity.setCjsj(new Date());
            service.saveXckcFkQs(xckcFkQsEntity);
            jo.put("result", "success");
        } catch (Exception e) {
            jo.put("result", "fail");
            e.printStackTrace();
        }
        return jo.toString();
    }


    /***
     * 获取区所勘查反馈
     * @return
     */
    @RequestMapping(value = "getQsfk", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取区所勘查反馈")
    private String getQsfk(String xmid, String kczt) {
        JSONObject jo = new JSONObject();
        try {
            XckcRwcbEntity xckcRwcbEntity = service.getXckcRwcb(xmid, "3", kczt);
            List<XckcFkQsEntity> zbgw = service.getQsfk(xckcRwcbEntity.getId(), "0");
            List<XckcFkQsEntity> jtcy = service.getQsfk(xckcRwcbEntity.getId(), "1");

            jo.put("zbgw", zbgw);
            jo.put("jtcy", jtcy);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jo.toString();
    }

    /***
     * 获取区所勘查反馈保存
     * @return
     */
    @RequestMapping(value = "saveQsFk", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取区所勘查反馈保存")
    private String saveQsFk(String xmid, String type, String info, HttpServletResponse response, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        //保存数据
        saveXckcFk(xmid, type, info, null, request, response);
        jo.put("result", "success");
        return jo.toString();
    }

    /***
     * 获取施工部门勘查反馈保存
     * @return
     */
    @RequestMapping(value = "saveSgbmFk", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取施工部门勘查反馈保存")
    public String saveSgbmFk(String bqg_1, String bqg_2, String glg_1, String glg_2, String glg_3,
                             String ldsx_wz, String bfgswz, String ylzg, String cgc, String lhpf, String pzdg,
                             String xmid, String info, String bqg, String glg, String ldsx, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();
        String type = "2";
        try {
            XckcRwcbEntity xckcRwcbEntity = service.getXckcRwcb(xmid, type, "2");
            /*  SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm");*/
            XckcFkSgdwEntity xckcFkSgdwEntity = new XckcFkSgdwEntity();
            xckcFkSgdwEntity.setId(GuidUtils.getGuid());//ID
            xckcFkSgdwEntity.setKcrwcbid(xckcRwcbEntity.getId());//勘查从表ID
            UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
            String czr = user.getUserid();
            xckcFkSgdwEntity.setCjr(czr);//创建人
            xckcFkSgdwEntity.setCjsj(new Date());//创建时间
            xckcFkSgdwEntity.setBqgcd(bqg_1);//表前管
            xckcFkSgdwEntity.setJbxkwmj(bqg_2);//接表前管需要开挖面积㎡
            xckcFkSgdwEntity.setTgjl(glg_1);//托管距离 m
            xckcFkSgdwEntity.setDggwcpfmj(glg_2);//顶管管尾处破复面积㎡
            xckcFkSgdwEntity.setGlggdpfmj(glg_3);//过路管勾点破复面积㎡
            xckcFkSgdwEntity.setLdsxwz(ldsx_wz);//楼顶水箱位置
            xckcFkSgdwEntity.setBfgswz(bfgswz);//泵房供水位置
            xckcFkSgdwEntity.setSfhykjbylzg(ylzg);//是否含有可接表预留支管 0 1
            xckcFkSgdwEntity.setSfycgc(cgc);//是否有超高层 0没有 1有       0 1
            xckcFkSgdwEntity.setSfxydllhpf(lhpf);//是否需要道路绿化破复 0不需要1需要   0 1
            xckcFkSgdwEntity.setSfxypzdb(pzdg);//是否需要需要配置地表 0不需要1需要   0 1
            xckcFkSgdwEntity.setSfyldsx(ldsx);//楼顶水箱
            xckcFkSgdwEntity.setSfxybqg(bqg);//表前管
            xckcFkSgdwEntity.setSfxyzglg(glg);//过路管
            service.saveXckcFkSgdw(xckcFkSgdwEntity);
            xckcRwcbEntity.setSffk("1");
            xckcRwcbEntity.setSfdc("1");
            xckcRwcbEntity.setFkyj(info);
            //xckcRwcbEntity.setDcsj(dcsj);
            xckcRwcbEntity.setFksj(new Date());
            service.saveXckcRwcb(xckcRwcbEntity);
            String sffk = "0";
            List<XckcRwcbEntity> list = service.findAllXckcRwcbBySffk(xmid, sffk);
            XckcRwzbEntity xckcRwzbEntity = null;
            if (list.size() == 0) {
                xckcRwzbEntity = service.getKczbByXmid(xmid, "2");
                xckcRwzbEntity.setKczt("3");
                service.saveXckcRwzb(xckcRwzbEntity);
            } else {
                xckcRwzbEntity = new XckcRwzbEntity();
                xckcRwzbEntity.setId("");
            }
            String activityid = "";
            if (type.equals("1")) {
                activityid = "20031";
            } else if (type.equals("2")) {
                activityid = "20032";
            } else if (type.equals("3")) {
                activityid = "20033";
            }
            GetXckcxxView getXckcxxView = service.getXckcxxByView(xmid);
            String rwblr = getXckcxxView.getXmjl(); //任务完成 任务办理人为项目经理
            taskIsFinished(xmid, "20031", type, "0",
                    ((UserEntity) session.getAttribute("user")).getUserid(), rwblr, "XCKC_RWCB", xckcRwcbEntity.getId(), xckcRwzbEntity.getId());
            jo.put("result", "success");
        } catch (Exception e) {
            jo.put("result", "fail");
            e.printStackTrace();
        }
        return jo.toString();
    }


    /***
     * 项目经理勘查反馈保存+用户签字确认
     * @return
     */
    @RequestMapping(value = "saveXmjlFq", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "项目经理勘查反馈保存+用户签字确认")
    public String saveXmjlFq(String info, HttpServletResponse response, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
        String xmid = request.getParameter("xmid");
        String xmjlyj = request.getParameter("xmjlyj");
        String userid = request.getParameter("userid");
        String kssj = sdft.format(new Date());
        String qztp = request.getParameter("qztp");
        String khsfty = "0";
        if (qztp != null) {
            khsfty = "1";
        }
        try {
            String xmbh="";
            XmInstanceEntity xmInstanceEntity = service.getXmxxById(xmid);
            if(xmInstanceEntity != null){
                xmbh = xmInstanceEntity.getXmbh();
            }else {
                xmbh = service.getXmxxFromSqd(xmid).getSqbh();
            }
            String qztp_str = qztp.substring(qztp.indexOf(",") + 1);
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String str_date = df.format(new Date());
            String fileName = str_date + ".png";//待存储的文件名
            String fileDir = StringUtil.getBasePath() + "upload" + File.separator + xmbh+File.separator+"xkylx"+File.separator;
            String filePath = fileDir + File.separator + fileName;//图片路径
            File dir = new File(fileDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            convertBase64DataToImage(qztp_str, filePath);//转成文件
            Map<String, Object> fjMap = new HashMap<>();
            XckcRwcbEntity xckcRwcbEntity = null;
            // xckcRwcbEntity = service.getXckcRwcb(xmid, "4", "2");
            fjMap.put("id", GuidUtils.getGuid());//主键标识
            fjMap.put("fjlj", "upload/"+xmbh+"/xkylx/" + fileName);//附件路径
            fjMap.put("fjhz", ".png");//附件后缀
            fjMap.put("fjmc", fileName);//附件名
            fjMap.put("scr", userid);//上传人
            XckcRwzbEntity xckcRwzbEntity = service.getKczbByXmid(xmid, "3");
            String kcrwzbid = xckcRwzbEntity.getId();
            fjMap.put("fjlx_ej", "86c0fe78eb204bb5a45c86c0fe78eb02");
            fjMap.put("kcrwzbid", kcrwzbid);
            service.savePjFj(fjMap);
            //调用反馈评价
            xckcRwzbEntity.setKczt("4");//勘查评价完成
            xckcRwzbEntity.setXmjlpj(xmjlyj);
            xckcRwzbEntity.setPjsj(new Date());
            xckcRwzbEntity.setSjdcrq(new Date());
            service.saveXckcRwzb(xckcRwzbEntity);
            GetXckcxxView getXckcxxView = service.getXckcxxByView(xmid);
            String rwblr = getXckcxxView.getXmjl();
            taskIsFinished(xmid, "20006", "", "0",
                    userid, rwblr, "XCKC_RWZB", xckcRwzbEntity.getId(), "");
            //调用客户确认
            XckcRwzbEntity xckcRwzbEntity_1 = service.getKczbByXmid(xmid, "4");
            xckcRwzbEntity_1.setKczt("5");
            xckcRwzbEntity_1.setKhsfty(khsfty);
            xckcRwzbEntity_1.setKhtjsj(new Date());
            //xckcRwzbEntity.setKhtjsj(new Date());
            service.saveXckcRwzb(xckcRwzbEntity_1);
            GetXckcxxView getXckcxxView_1 = service.getXckcxxByView(xmid);
            String rwblr_1 = getXckcxxView_1.getXmjl();
            taskIsFinished(xmid, "20007", "", "0",
                    userid, rwblr_1, "XCKC_RWZB", getXckcxxView_1.getId(), "");
            jo.put("result", "success");
        } catch (IOException e) {
            e.printStackTrace();
            jo.put("result", "fail");
        }
        return jo.toString();
    }


    //获取待办任务
    @RequestMapping(value = "findBacklog", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取我的待办任务")
    public String findBacklog(BaseEntity baseEntity, HttpServletRequest request, HttpServletResponse response) {
        //HttpSession session = request.getSession();
        //baseEntity.setRwblr("");
        baseEntity.setEndRow();
        baseEntity.setStartRow();
        com.alibaba.fastjson.JSONObject jo = new com.alibaba.fastjson.JSONObject();
        List<Map<String, Object>> list = null;
        int total = 0;
        try {
            list = service.getMyBacklog(baseEntity);
            total = service.getMyBacklogTotal(baseEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jo.put("list", list);
        jo.put("total", total);
        return jo.toString();
    }

    /**
     * 打包压缩下载文件
     */
    /*@RequestMapping(value = "/downLoadZipFile")
    public void downLoadZipFile(HttpServletResponse response) throws IOException{
        String zip = GuidUtils.getGuid();
        String zipName = zip + ".zip";
        List<FileBean> fileList = fileService.getFileList();//查询数据库中记录
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition","attachment; filename="+zipName);
        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
        try {
            for(Iterator<FileBean> it = fileList.iterator();it.hasNext();){
                FileBean file = it.next();
                ZipUtils.doCompress(file.getFilePath()+file.getFileName(), out);
                response.flushBuffer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            out.close();
        }
    }*/

    //获取办理过的任务
    @RequestMapping(value = "findDone", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取办理过的任务")
    public String findDone(BaseEntity baseEntity, HttpServletRequest request, HttpServletResponse response) {
        com.alibaba.fastjson.JSONObject jo = new com.alibaba.fastjson.JSONObject();
        baseEntity.setEndRow();
        baseEntity.setStartRow();
        List<Map<String, Object>> list = null;
        int total = 0;
        try {
            list = service.getMyDone(baseEntity);
            total = service.getMyDoneTotal(baseEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jo.put("list", list);
        jo.put("total", total);
        return jo.toString();
    }


    @RequestMapping(value = "gettpck", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "图片查看")
    public String gettpck(String[] tp, HttpServletRequest request, HttpServletResponse response) {
        com.alibaba.fastjson.JSONObject jo = new com.alibaba.fastjson.JSONObject();
        String imgStr = "";
        List imglist = new ArrayList();
        String basePath = StringUtil.getBasePath();
        if (tp != null) {
            try {
                for (int i = 0; i < tp.length; i++) {
                    String[] tupian = tp[i].split("\\.");
                    File file = new File(basePath + tp[i]);
                    FileInputStream fis = new FileInputStream(file);
                    byte[] buffer = new byte[(int) file.length()];
                    int offset = 0;
                    int numRead = 0;
                    while (offset < buffer.length && (numRead = fis.read(buffer, offset, buffer.length - offset)) >= 0) {
                        offset += numRead;
                    }

                    if (offset != buffer.length) {
                        throw new IOException("Could not completely read file "
                                + file.getName());
                    }
                    fis.close();
                    BASE64Encoder encoder = new BASE64Encoder();
                    imgStr = encoder.encode(buffer);
                    imglist.add("data:image/" + tupian[1] + ";base64," + imgStr);
                }
                jo.put("imgStr", imglist);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jo.toString();
    }


    /**
     * 把base64图片数据转为本地图片
     *
     * @param base64ImgData
     * @param filePath
     * @throws IOException
     */
    public static void convertBase64DataToImage(String base64ImgData, String filePath) throws IOException {
        BASE64Decoder d = new BASE64Decoder();
        byte[] bs = d.decodeBuffer(base64ImgData);
        FileOutputStream os = new FileOutputStream(filePath);
        os.write(bs);
        os.close();
    }


    /**
     * app配置文件
     */
    @RequestMapping(value = "appUpdate", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "app更新")
    public String appUpdate(HttpServletRequest request, HttpServletResponse response) {
        FileInputStream inStream = null;
        String apkCode = "";
        String apkUrl = "";
        String apkName = "";
        String apkWzUrl = "";
        try {
            inStream = new FileInputStream("appUpdate");
            Properties prop = new Properties();
            prop.load(inStream);
            apkCode = prop.getProperty("apkCode");
            apkUrl = prop.getProperty("apkUrl");
            apkName = prop.getProperty("apkName");
            String ip = request.getLocalAddr();
            int port = request.getLocalPort();
            //   apkWzUrl="http://"+ip+":"+port+apkUrl;
            apkWzUrl = apkUrl + apkName;

        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("apkCode", apkCode);
        jo.put("apkName", apkName);
        jo.put("apkUrl", apkWzUrl);
        return jo.toString();

    }

    @RequestMapping(value = "xmjlCk", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "项目经理查看")
    public String xmjlCk(String xmid, HttpServletRequest request, HttpServletResponse response) {
        com.alibaba.fastjson.JSONObject jo = new com.alibaba.fastjson.JSONObject();
        try {
            XckcRwzbEntity xckcRwzbEntity = service.getKczbByXmid(xmid, "0");
            String kcfkid = xckcRwzbEntity.getId();
            List<XckcFkFj> xmjlck = service.xmjlCk(kcfkid);
            jo.put("xmjlfq", xckcRwzbEntity.getXmjlpj());
            jo.put("xmjlck", xmjlck);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return jo.toString();
    }

}
