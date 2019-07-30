package com.ljx.hfgsjt.htqd.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.htqd.DemoHtbzformEntity;
import com.ljx.hfgsjt.entity.dto.htqd.XtglHtmbEntity;
import com.ljx.hfgsjt.htqd.service.HtmbService;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialClob;
import java.util.Date;
import java.util.List;

/**
 * 合同模板控制层
 */
@Controller
@RequestMapping(value = "HtqdController")
public class HtmbController {
    @Autowired
    private HtmbService service;

    /**
     * 两方合同编制提交按钮
     * @param entity
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="do_htbz",method = RequestMethod.POST)
    @ResponseBody
    public String do_htbz(DemoHtbzformEntity entity, HttpServletRequest request, HttpServletResponse response){
        String flag = "ok";
        try {
            if(entity.getWybs() == null || entity.getWybs().equals("")){
                entity.setWybs(GuidUtils.getGuid());
            }
            //合同编制存入数据库
            //service.save(entity);
            //新建流程实例
            String activityid = "50001";
            String direction = "0";//流程方向 前进/回退
            //根据流程节点标示与方向标识查询流程定义ID
            List<WfReNodeEntity> list = service.findByActivityidAndDirection(activityid,direction);
            String pdef_id = list.get(0).getPdefId();//流程定义id
            String startuser = "合同编制经理";//启动用户
            Date startdt = new Date();//启动时间
            Integer proc_inst_id = service.findMaxIdFromRu_inst();
            proc_inst_id = proc_inst_id +1;//流程实例ID
            WfRuInstEntity wfRuInstEntity = new WfRuInstEntity();
            wfRuInstEntity.setPdefId(pdef_id);
            wfRuInstEntity.setProcInstId(String.valueOf(proc_inst_id));
            wfRuInstEntity.setStartdt(startdt);
            wfRuInstEntity.setStartuser(startuser);
            service.saveWfRuInst(wfRuInstEntity);
            //新建流程任务
            Integer taskid = service.findMaxIdFromTask();
            taskid = taskid +1;//任务编号
            //流程任务历史（进入合同编制环节）
            WfTaskEntity wfTaskEntity = new WfTaskEntity();
            WfTaskHistoryEntity wfTaskHistoryEntity = new WfTaskHistoryEntity();
            wfTaskHistoryEntity.setTaskid(String.valueOf(taskid));//任务编号
            wfTaskHistoryEntity.setPtaskid("0");//前置任务编号
            wfTaskHistoryEntity.setProcInstId(String.valueOf(proc_inst_id));//流程实例ID
            wfTaskHistoryEntity.setCActivityid("50001");//环节编号
            wfTaskHistoryEntity.setIscurrent("0");//状态标志：0已做1待做
            wfTaskHistoryEntity.setCzr("合同编制经理");//操作人
            wfTaskHistoryEntity.setQssj(new Date());//起始时间
            wfTaskHistoryEntity.setJssj(new Date());//结束时间
            wfTaskHistoryEntity.setIscheck("1");//已经签收：0未签收1已签收
            wfTaskHistoryEntity.setCheckTime(new Date());//签收时间
            wfTaskHistoryEntity.setHasnew("0");//子流程是否有提交，1有，0无
            service.savewfTaskHistory(wfTaskHistoryEntity);

            //下一流程任务（多方会签环节）
            for(WfReNodeEntity wf : list){
                Integer ptaskid = service.findMaxIdFromTask();
                ptaskid = ptaskid +1;//任务编号
                //根据环节编号查询创建人
                WfDfActivityEntity ac = service.findByActivityid(wf.getActivityid());
                wfTaskEntity.setTaskid(String.valueOf(ptaskid));//任务编号
                wfTaskEntity.setPtaskid(String.valueOf(taskid));//前置任务编号
                wfTaskEntity.setProcInstId(String.valueOf(proc_inst_id));//流程实例ID
                wfTaskEntity.setCActivityid(wf.getNextnode());//环节编号
                wfTaskEntity.setIscurrent("1");//状态标志：0已做1待做
                wfTaskEntity.setCzr(ac.getDefaultCzr());//操作人
                wfTaskEntity.setQssj(new Date());//起始时间
                wfTaskEntity.setIscheck("0");//已经签收：0未签收1已签收
                wfTaskEntity.setHasnew("0");//子流程是否有提交，1有，0无
                service.savewfTask(wfTaskEntity);
            }
        } catch (Exception e) {
            flag = "err";
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查看会签结果
     * @param msg
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="do_htqd",method = RequestMethod.POST)
    @ResponseBody
    public String do_htqd(String msg, HttpServletRequest request, HttpServletResponse response){
        String flag = "ok";
        try{
            String activityid = "50001";//初始环节编号
            String direction = "0";//流程方向 前进/回退
            //根据流程节点标示与方向标识查询流程定义ID
            List<WfReNodeEntity> list = service.findByActivityidAndDirection(activityid,direction);
            String pdef_id = list.get(0).getPdefId();//流程定义id
            //根据流程定义ID查询流程实例ID
            List<WfRuInstEntity> ruinstList = service.findByPdefid(pdef_id);
            String proc_inst_id = ruinstList.get(0).getProcInstId();//流程实例ID
            //根据流程实例ID查询当前环节编号
            List<WfTaskEntity> taskList = service.findByProcInstId(pdef_id);
            //String c_activityid = taskList.get(0).getCActivityid();//当前环节编号
            String c_activityid = "50003";
            if("0".equals(msg) || msg == "0"){//0代表会签通过；1代表会签不通过，需要变更
                direction = "0";//前进
            }else{
                direction = "1";//后退
            }
            List<WfReNodeEntity> list1 = service.findByActivityidAndDirection(c_activityid,direction);
            String nextnode = list1.get(0).getNextnode();//下一节点
            WfTaskEntity wfTaskEntity = taskList.get(0);
            wfTaskEntity.setCActivityid(nextnode);
            service.savewfTask(wfTaskEntity);
            wfTaskEntity.setCActivityid(c_activityid);
            wfTaskEntity.setIscurrent("0");
            wfTaskEntity.setIscheck("1");
            wfTaskEntity.setCheck_time(new Date());
            wfTaskEntity.setJssj(new Date());
            WfTaskHistoryEntity wfTaskHistoryEntity = new WfTaskHistoryEntity(wfTaskEntity);
            service.savewfTaskHistory(wfTaskHistoryEntity);

        }catch(Exception e){
            flag = "err";
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 合同模板界面
     * @return
     */
    @RequestMapping(value ="xtgl_htmb",method = RequestMethod.GET)
    @ResponseBody
    public String xtgl_htmb(String mbmc,Integer page, Integer size,HttpServletResponse response){
        JSONObject jo = new JSONObject();
        PageRequest pageRequest = PageRequest.of(page, size);
        if(mbmc == null){
            mbmc = "";
        }
        Page<XtglHtmbEntity> varList = service.findXtglHtmb(mbmc,pageRequest);
        jo.put("total",varList.getTotalElements());
        jo.put("row",varList.getContent());
        return jo.toString();
    }

    //新增 or 保存
    @RequestMapping(value="save_Htmb",method = RequestMethod.POST)
    @ResponseBody
    public String save_Htmb(HttpServletRequest request, HttpServletResponse response) {
        String flag = "ok";
        try {
            String mbnr = request.getParameter("mbnr");
            XtglHtmbEntity xtglHtmbEntity = new XtglHtmbEntity();
            xtglHtmbEntity.setMbnr(new SerialClob(mbnr.toCharArray()));
            xtglHtmbEntity.setWybs(request.getParameter("wybs"));
            xtglHtmbEntity.setMbbh(request.getParameter("mbbh"));
            xtglHtmbEntity.setMbbm(request.getParameter("mbbm"));
            xtglHtmbEntity.setMblx(request.getParameter("mblx"));
            xtglHtmbEntity.setMbmc(request.getParameter("mbmc"));
            xtglHtmbEntity.setCjr(request.getParameter("cjr"));
            xtglHtmbEntity.setCjsj(new Date());
            xtglHtmbEntity.setScFlag("0");
            if(xtglHtmbEntity.getWybs() == null || xtglHtmbEntity.getWybs().equals("")){
                xtglHtmbEntity.setWybs(GuidUtils.getGuid());
            }else{
                xtglHtmbEntity.setXgr(request.getParameter("xgr"));
                xtglHtmbEntity.setXgsj(new Date());
            }
            service.saveHtmb(xtglHtmbEntity);
        } catch (Exception e) {
            flag = "err";
            e.printStackTrace();
        }
        return flag;
    }

    //删除
    @RequestMapping(value = "deleteHtmbById", method = RequestMethod.POST)
    @ResponseBody
    public String deleteHtmbById(String wybs, HttpServletResponse response) {
        String flag = "err";
        try {
            service.deleteHtmbById(wybs);
        } catch (Exception e) {
            flag = "ok";
            e.printStackTrace();
        }
        return flag;
    }

}
