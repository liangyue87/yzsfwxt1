package com.ljx.hfgsjt.wtys.controller;

import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.wtys.YsZjqtcbEntity;
import com.ljx.hfgsjt.entity.wtys.YsZjqtzbEntity;
import com.ljx.hfgsjt.message.service.MessageService;
import com.ljx.hfgsjt.repository.CalculateHours;
import com.ljx.hfgsjt.sgjg.service.SgxdService;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import com.ljx.hfgsjt.wtys.service.YsZjqtFkService;
import com.ljx.hfgsjt.wtys.service.YsZjqtService;
import org.activiti.engine.impl.util.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "YsZjqt")
@CrossOrigin
public class YsZjqtController {

    @Autowired
    private YsZjqtService ysZjqtService;

    @Autowired
    private YsZjqtFkService ysZjqtFkService;

    @Autowired
    private SgxdService sgxdService;

    @Autowired
    private MessageService messageService;

    private Logger logger = LoggerFactory.getLogger(YsZjqtController.class);

    CalculateHours cal = new CalculateHours();

    //查询所有数据
    @RequestMapping(value = "getQtcyry",method = RequestMethod.GET)
    @ResponseBody
    public String getQtcyry(String Xmid, HttpServletResponse response){
        JSONObject jo = new JSONObject();
        return jo.toString();
    }

    //保存发起造价洽谈
    @RequestMapping(value = "saveYszjqt", method = RequestMethod.POST)
    @ResponseBody
    public String saveYszjqt(String bz, String qtsj, String xmid,String sjry,String sjrysjh,String ysry,String ysrysjh,
                             HttpServletRequest request, HttpServletResponse response) throws ParseException {
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        DxDfsEntity dxDfsEntity = new DxDfsEntity();//初始化短信模版
        String gddh = "";//项目经理固定电话
        Date qtsj1 = new Date();
        Map<String, Object> map = new HashMap<String, Object>();
        String ss="ZJQT";
        map.put("v_stop_run",ss);
        sgxdService.texuChange(map);//获取任务编号
        String rwbh = "ZJQT"+map.get("p_result");
        if(!"".equals(qtsj) && qtsj != null){
            qtsj1 = sdf.parse(qtsj);//预约勘查时间
        }
        JSONObject jo = new JSONObject();
        //将洽谈任务保存到洽谈任务主表
        YsZjqtzbEntity ysZjqtzbEntity = new YsZjqtzbEntity();
        String zbId = GuidUtils.getGuid();
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity)session.getAttribute("user");//获取当前登录的用户信息
        String pdefId = "40010";
        String czr = user.getUserid();
        ysZjqtzbEntity.setId(zbId);
        ysZjqtzbEntity.setFqr(czr);
        ysZjqtzbEntity.setFqsj(new Date());
        ysZjqtzbEntity.setQtsj(qtsj1);
        ysZjqtzbEntity.setBz(bz);
        ysZjqtzbEntity.setQtzt(new Long(1));
        ysZjqtzbEntity.setRwbh(rwbh);
        ysZjqtService.saveYszjqt(ysZjqtzbEntity);

        //将洽谈参加部门保存到洽谈任务子表
        YsZjqtcbEntity ysZjqtcbEntity = new YsZjqtcbEntity();
        ysZjqtcbEntity.setId(GuidUtils.getGuid());
        ysZjqtcbEntity.setZjqtid(zbId);
        ysZjqtcbEntity.setCybm("设计部门");
        ysZjqtcbEntity.setZpry(czr);
        ysZjqtcbEntity.setZpsj(new Date());
        ysZjqtcbEntity.setBzpr(sjry);
        ysZjqtcbEntity.setSjhm(sjrysjh);
        String sffsdx_sj = request.getParameter("sjy_value");
        if("true".equals(sffsdx_sj)){
            ysZjqtcbEntity.setSffsdx(1);
        }else{
            ysZjqtcbEntity.setSffsdx(0);
        }
        ysZjqtService.saveYszjqtcb(ysZjqtcbEntity);

        YsZjqtcbEntity ysZjqtcbEntity1 = new YsZjqtcbEntity();
        ysZjqtcbEntity1.setId(GuidUtils.getGuid());
        ysZjqtcbEntity1.setZjqtid(zbId);
        ysZjqtcbEntity1.setCybm("预算部门");
        ysZjqtcbEntity1.setZpry(czr);
        ysZjqtcbEntity1.setZpsj(new Date());
        ysZjqtcbEntity1.setBzpr(ysry);
        ysZjqtcbEntity1.setSjhm(ysrysjh);
        String sffsdx_ys = request.getParameter("sgdw_value");
        if("true".equals(sffsdx_ys)){
            ysZjqtcbEntity1.setSffsdx(1);
        }else{
            ysZjqtcbEntity1.setSffsdx(0);
        }
        ysZjqtService.saveYszjqtcb(ysZjqtcbEntity1);

        //启动流程实例
        WfRuInstEntity instEntity = new WfRuInstEntity();
        WfTaskEntity task = new WfTaskEntity();
        String procInstId = GuidUtils.getGuid();
//        String procInstId = ysZjqtService.findMaxIdToWfRuInst() + 1 + "";
        instEntity.setProcInstId(procInstId);
        instEntity.setPdefId(pdefId);
        instEntity.setStartdt(new Date());
        instEntity.setParammap(rwbh);
        instEntity.setProcstatus(1);
        instEntity.setStartuser(czr);
        ysZjqtService.saveWfRuInst(instEntity);

        //将项目信息存入流程实例使用内容表(WF_RU_INST_CONTENT)
        WfRuInstContentEntity wfRuInstContentEntity = new WfRuInstContentEntity();
        wfRuInstContentEntity.setContentid(GuidUtils.getGuid());
        wfRuInstContentEntity.setProcInstId(procInstId);
        wfRuInstContentEntity.setTableName("XM_INSTANCE");
        wfRuInstContentEntity.setContentvalue(xmid);
        wfRuInstContentEntity.setContenttype("YS_ZJQTZB");
        wfRuInstContentEntity.setContentname(zbId);
        ysZjqtService.saveWfRuInstContent(wfRuInstContentEntity);

        //创建任务，存入历史任务表中
        String taskId = GuidUtils.getGuid();
//        String taskId = ysZjqtService.findMaxIdToWfTask() + 1 + "";
        task.setTaskid(taskId);
        task.setPtaskid("0");//前置任务为空
        task.setQssj(new Date());//起始时间
        task.setJssj(new Date());//结束时间
        task.setProcInstId(procInstId);//流程实例ID
        task.setCActivityid(pdefId);//环节编号
        task.setIscurrent("0");//已完成
        task.setCzr(czr);//操作人
        task.setIscheck("1");//已签收
        task.setHasnew("2");//无子流程
        task.setRwblr(czr);//任务办理人
        WfTaskHistoryEntity historyEntity = new WfTaskHistoryEntity(task);
        ysZjqtService.saveWfTaskHistory(historyEntity);

        //下一节点 创建任务
        String direction = "0";//流程方向 前进/回退
        List<WfReNodeEntity> list = ysZjqtService.findByActivityidAndDirection(pdefId,direction);
        for(WfReNodeEntity wf : list){
            //taskId = ysZjqtService.findMaxIdToWfTask() + 1 + "";
            taskId = GuidUtils.getGuid();
            WfDfActivityEntity ac = ysZjqtService.findByActivityid(wf.getActivityid());
            task.setTaskid(taskId);
            task.setPtaskid("0");//前置任务为空
            task.setProcInstId(procInstId);//流程实例ID
            task.setCActivityid(wf.getNextnode());//环节编号
            Date qssj = new Date();
            try {
                if (ac.getCheckLimited() == null || ac.getCheckLimited().equals("")
                        || ac.getCheckLimited().equals("0")) {
                    task.setCheck_sx(sdft.parse("2099-12-31 23:59:59"));
                } else {
                    task.setCheck_sx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getCheckMinute())));
                }
                if (ac.getFinishLimited() == null || ac.getFinishLimited().equals("")
                        || ac.getFinishLimited().equals("0")) {
                    task.setJssx(sdft.parse("2099-12-31 23:59:59"));
                    task.setJssx_yl(sdft.parse("2099-12-31 23:59:59"));
                } else {
                    task.setJssx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getYellowFinish())));
                    task.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getFinishMinute()) + Float.parseFloat(ac.getYellowFinish())));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            task.setQssj(qssj);
            task.setIscurrent("1");//已完成
            task.setCzr(czr);//操作人
            task.setIscheck("0");//已签收
            task.setHasnew("2");//无子流程
            task.setRefkey("YS_ZJQTZB");//关联实体Key
            task.setRefid(zbId);//关联实体ID
//            String rwblr = sgxdService.getUserIdByActivityId(wf.getNextnode());
            task.setRwblr(czr);//任务办理人
            ysZjqtService.saveWfTask(task);

            //修改流程实例表中的流程定义ID
            sgxdService.updateWfruInst(procInstId,wf.getNextnode());

//            saveDxDfs(task.getCActivityid(),xmid,czr,task.getRwblr(),rwbh,task.getTaskid());
        }

        //保存造价洽谈时，发送短信通知洽谈部门的人
        String content = "";//短信内容
        //如果预算人不为空，发送短信
        if (!ysrysjh.equals("") || !ysrysjh.equals("null") || ysrysjh!=null){
            dxDfsEntity.setId(GuidUtils.getGuid());//标识
            dxDfsEntity.setDxmbid("13");//短信模版标识
            dxDfsEntity.setFsr(user.getUserid());//发送人
            dxDfsEntity.setFssj(new Date());//发送时间
            dxDfsEntity.setFscs("1");//发送次数
            Map<String,Object> dxmb = messageService.getDxMb("13");//模版13：洽谈会议通知，替换短信模版内容
            content = dxmb.get("DXMB").toString();//获取短信模版
            content = content.replace("[任务编号]",rwbh);
            content = content.replace("[洽谈时间]",qtsj);
            content = content.replace("[项目经理]",user.getUsername());
            try {
                gddh = user.getGddh();
                if (gddh.equals("") || gddh.equals("null") || gddh == null){
                    gddh = "";
                }
            } catch (Exception e) {
                gddh = "";
            }
            content = content.replace("[联系方式]",gddh);
            dxDfsEntity.setFsnr(content);//发送内容
            // UserEntity userEntity = yswtservice.getUserByXmfzr(xmInstanceEntity.getYsfzr());
            dxDfsEntity.setJsr(ysry);//接收人
            dxDfsEntity.setJshm(ysrysjh);//接收号码
            ysZjqtService.saveDxDfsEntity(dxDfsEntity);//保存
        }

        //如果设计人不为空，发送短信
        if (!sjrysjh.equals("") || !sjrysjh.equals("null") || sjrysjh!=null){
            dxDfsEntity.setId(GuidUtils.getGuid());//标识
            dxDfsEntity.setDxmbid("13");//短信模版标识
            dxDfsEntity.setFsr(user.getUserid());//发送人
            dxDfsEntity.setFssj(new Date());//发送时间
            dxDfsEntity.setFscs("1");//发送次数
            Map<String,Object> dxmb = messageService.getDxMb("13");//模版13：洽谈会议通知，替换短信模版内容
            content = dxmb.get("DXMB").toString();//获取短信模版
            content = content.replace("[任务编号]",rwbh);
            content = content.replace("[洽谈时间]",qtsj);
            content = content.replace("[项目经理]",user.getUsername());
            try {
                gddh = user.getGddh();
                if (gddh.equals("") || gddh.equals("null") || gddh == null){
                    gddh = "";
                }
            } catch (Exception e) {
                gddh = "";
            }
            content = content.replace("[联系方式]",gddh);
            dxDfsEntity.setFsnr(content);//发送内容
            dxDfsEntity.setJsr(sjry);//接收人
            dxDfsEntity.setJshm(sjrysjh);//接收号码
            ysZjqtService.saveDxDfsEntity(dxDfsEntity);//保存
        }







        jo.put("flag",true);
        return jo.toString();
    }

    /**
     * 根据项目标识查询洽谈部门
     * @param request
     * @return
     */
    @RequestMapping(value = "queryBmById",method = RequestMethod.GET)
    @ResponseBody
    public String queryBmById(HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        try{
            pd.put("id",request.getParameter("xmid"));
            List<Map<String,Object>> list = ysZjqtFkService.queryBmById(pd);
            jo.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 任务提醒 保存到待发送表中
     * @param activityid
     * @param xmid
     * @param fsr
     * @param jsr
     * @param taskid
     * @return
     */
    public String saveDxDfs(String activityid,String xmid,String fsr,String jsr,String rwbh,String taskid) {
        WfDfActivityEntity wfDfActivityEntity = sgxdService.findByActivityid(activityid);
        List<XmInstance> xmInstances = sgxdService.getXmxxByid(xmid);
        String xmmc = xmInstances.get(0).getXmmc();
        UserEntity userEntity = sgxdService.getUserByUserId(jsr);
        DxDfsEntity dxDfsEntity = new DxDfsEntity();//初始化短信模版
        Map<String,Object> dxmb = messageService.getDxMb("4");
        String content = dxmb.get("DXMB").toString();
        content = content.replace("[环节名称类型]", wfDfActivityEntity.getActivityname());
        content =   content.replace("[任务单编号]", rwbh);
        content =  content.replace("[项目名称]", xmmc);
        dxDfsEntity.setId(GuidUtils.getGuid());
        dxDfsEntity.setDxmbid("4");
        dxDfsEntity.setFsr(fsr);//发送人
        dxDfsEntity.setFssj(new Date());//发送时间
        dxDfsEntity.setFscs("1");//发送次数
        dxDfsEntity.setFsnr(content);//发送内容
        dxDfsEntity.setJsr(userEntity.getUserid());//接收人
        dxDfsEntity.setJshm(userEntity.getMobilephone());//接收号码
        dxDfsEntity.setTaskid(taskid);
        sgxdService.saveDxDfsEntity(dxDfsEntity);//保存
        return "";
    }
}
