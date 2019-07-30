package com.ljx.hfgsjt.js.controller;

import com.alibaba.fastjson.JSON;
import com.ljx.hfgsjt.entity.dto.Activity.WfDfActivityEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfReNodeEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfRuInstContentEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfRuInstEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.InstEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.TaskEntity;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.js.*;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.dto.yswt.YsRwcb;
import com.ljx.hfgsjt.entity.dto.yswt.YsRwzb;
import com.ljx.hfgsjt.entity.dto.zhys.XmUserEntity;
import com.ljx.hfgsjt.js.service.JssqCsService;
import com.ljx.hfgsjt.js.service.JssqService;
import com.ljx.hfgsjt.message.controller.MessageController;
import com.ljx.hfgsjt.message.service.MessageService;
import com.ljx.hfgsjt.repository.CalculateHours;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import com.ljx.hfgsjt.util.utils.StringUtil;
import com.ljx.hfgsjt.xkybz.service.XkylxService;
import com.ljx.hfgsjt.zhys.service.SqzhysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "JssqController")
@Api(description = "结算")
public class JssqController {

    @Autowired
    private JssqService jssqService;
    @Autowired
    private JssqCsService jssqCsService;
    @Autowired
    private SqzhysService sqzhysService;
    @Autowired
    private MessageService messageService;
    @Autowired
    MessageController controller;

    @Autowired
    private XkylxService service;

    CalculateHours cal = new CalculateHours();


    /**
     * 用户保存结算编制的汇总表
     * @param json
     * @param request
     * @return
     */
    @ApiOperation(value = "结算编制保存")
    @RequestMapping(value = "saveJsbc", method = RequestMethod.POST)
    @ResponseBody
    public String  saveJsbc(String json,HttpServletRequest request){
        String flag="ok";
        try {
            com.alibaba.fastjson.JSONObject jo = new com.alibaba.fastjson.JSONObject(JSON.parseObject(json));
            HttpSession session = request.getSession();
            UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
            Map<String, Object> map1 = new HashMap<String, Object>();
            DxDfsEntity dxDfsEntity = new DxDfsEntity();
            String ss = "ZHYS";
            map1.put("v_stop_run", ss);
            sqzhysService.texuChange(map1);
            String rwbh = "JS" + map1.get("p_result");
            Map<String, Object> map = jo;
            JsRwCb jsRwCb = new JsRwCb();
            JsRwZb jsRwZb = new JsRwZb();
            JsRwFj jsRwFj = new JsRwFj();
            for(Map.Entry<String, Object> entry : map.entrySet()){
                entry.getValue().toString();
            }
            //保存任务主表
            String xmid=map.get("xmid").toString();
            //先判断是否是退回
            JsRwZb rwzb = jssqService.selectRwzb(xmid);
            if (rwzb != null) {//存在  及是退回
                //跟新任务主表
                rwzb.setBz(map.get("bz").toString());
                rwzb.setFqrq(new Date());
                rwzb.setFqr(user.getUserid());
                jssqService.updataRwzb(rwzb);
                //跟新附件表
                List<JsRwFj> listfj = jssqService.selectJsFjList(xmid);
                for (int i = 0; i < listfj.size(); i++) {
                    //  list.get(i).setYhjsrwid(id);
                    jsRwFj.setId(listfj.get(i).getId());
                    jsRwFj.setYhjsrwid(rwzb.getId());
                    jssqService.updateJsFj(jsRwFj);
                }
                //跟新从表数据
                jssqService.delectRwCb(rwzb.getId());
                //保存从表数据
                for(int i= 1;i<=Integer.parseInt(map.get("i").toString());i++){
                    jsRwCb.setId(GuidUtils.getGuid());
                    jsRwCb.setYhjsrwzbid(rwzb.getId());
                    String ysje ="";
                    String jsje="";
                    try {
                        ysje=map.get("ysje"+i).toString();
                    } catch (NullPointerException e) {
                        ysje = "0";
                    }
                    jsRwCb.setYsje(ysje);
                    try {
                        jsje=map.get("jsje"+i).toString();
                    } catch (NullPointerException e) {
                        jsje = "0";
                    }
                    jsRwCb.setJsje(jsje);
                    jsRwCb.setJsfl(map.get("yslx"+i).toString());
                    jssqService.insertJsRwcb(jsRwCb);
                }

            }else{//不存在
                String id = GuidUtils.getGuid();
                System.out.println(map+"---map---");
                jsRwZb.setId(id);
                jsRwZb.setXmid(map.get("xmid").toString());
                jsRwZb.setBz(map.get("bz").toString());
                jsRwZb.setRwbh(rwbh);
                jsRwZb.setFqr(user.getUserid());
                jsRwZb.setFqrq(new Date());
                jssqService.insertRwzb(jsRwZb);
                //跟新附件表
                List<JsRwFj> listfj = jssqService.selectJsFjList(map.get("xmid").toString());
                for (int i = 0; i < listfj.size(); i++) {
                    //  list.get(i).setYhjsrwid(id);
                    jsRwFj.setId(listfj.get(i).getId());
                    jsRwFj.setYhjsrwid(id);
                    jssqService.updateJsFj(jsRwFj);
                }
                //保存从表数据
                for(int i= 1;i<=Integer.parseInt(map.get("i").toString());i++){
                    jsRwCb.setId(GuidUtils.getGuid());
                    jsRwCb.setYhjsrwzbid(id);
                    String ysje ="";
                    String jsje="";
                    try {
                        ysje=map.get("ysje"+i).toString();
                    } catch (NullPointerException e) {
                        ysje = "0";
                    }
                    jsRwCb.setYsje(ysje);
                    try {
                        jsje=map.get("jsje"+i).toString();
                    } catch (NullPointerException e) {
                        jsje = "0";
                    }
                    jsRwCb.setJsje(jsje);
                    jsRwCb.setJsfl(map.get("yslx"+i).toString());
                    jssqService.insertJsRwcb(jsRwCb);
                }

            }

            //开始跟新信息流程实例 跟新
            InstEntity instEntity = new InstEntity();
            instEntity = jssqService.getRuInst(xmid);
            instEntity.setPdefId("C0019");
            jssqService.updataInst(instEntity);

            //加入到历史任务中
            TaskEntity taskEntity = new TaskEntity();
            String direction = "0";//流程方向 前进/回退s
            String pdefid_id = "C0018";
            List<WfReNodeEntity> list = service.findByActivityidAndDirection(pdefid_id, direction);
            WfDfActivityEntity wfDfActivityEntity = service.findByActivityid(list.get(0).getNextnode());
            List<XmUserEntity> xmUserEntity1 = jssqService.getJsmrr("C0018");//获取任务办理人

            taskEntity = jssqService.getTask(xmid);
            taskEntity.setRwblr(xmUserEntity1.get(0).getUserid());
            taskEntity.setJssj(new Date());//结束时间
            taskEntity.setIscheck("1");//0未签收1已签收
            jssqService.insertHiTask(taskEntity);//添加历史任务
            jssqCsService.delectTask(taskEntity.getTaskid());//删除任务

            //新建任务
            TaskEntity taskEntity1 = new TaskEntity();
            taskEntity1.setTaskid(GuidUtils.getGuid());
            taskEntity1.setPtaskid("0");
            taskEntity1.setProc_inst_id(taskEntity.getProc_inst_id());
            taskEntity1.setC_activityid("C0019");
            taskEntity1.setIscurrent("1");
            taskEntity1.setCzr(user.getUserid());
            taskEntity1.setQssj(new Date());
            taskEntity1.setIscheck("0");
            Date qssj = new Date();
            SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            taskEntity1.setCheck_sx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
            taskEntity1.setJssx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
            taskEntity1.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute())+Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
            taskEntity1.setHasnew("2");
            taskEntity1.setRefkey(taskEntity.getRefkey());
            taskEntity1.setRefid(taskEntity.getRefid());
            List<XmUserEntity> xmUserEntity = jssqService.getJsmrr("C0019");//获取任务办理人
            taskEntity1.setRwblr(xmUserEntity.get(0).getUserid());
            jssqService.insertTask(taskEntity1);

            //发送任务提醒短信
            String content = "";
            Map<String,Object> userMap=null;

            dxDfsEntity.setId(GuidUtils.getGuid());//标识
            dxDfsEntity.setDxmbid("4");//短信模版标识
            dxDfsEntity.setFsr(user.getUserid());//发送人
            dxDfsEntity.setFssj(new Date());//发送时间
            dxDfsEntity.setFscs("1");//发送次数
            Map<String,Object> dxmb = messageService.getDxMb("4");//模版4：任务提醒，替换短信模版内容
            XmInstanceEntity xmInstanceEntity = jssqService.getXmInstanceByXmid(xmid);//获取项目名称
            content = dxmb.get("DXMB").toString();//获取短信模版
            content = content.replace("[环节名称类型]",wfDfActivityEntity.getActivityname());
            content = content.replace("[任务单编号]",rwbh);
            content = content.replace("[项目名称]",xmInstanceEntity.getXmmc());
            dxDfsEntity.setFsnr(content);//发送内容
            // UserEntity userEntity = yswtservice.getUserIdAndUserMobilephone("40008");
            dxDfsEntity.setJsr(xmUserEntity.get(0).getUserid());//接收人
            dxDfsEntity.setJshm(xmUserEntity.get(0).getMobilephone());//接收号码
            dxDfsEntity.setTaskid(taskEntity1.getTaskid());
            jssqService.saveDxDfsEntity(dxDfsEntity);//保存

        } catch (Exception e) {
            e.printStackTrace();
            flag="error";
        }

        return flag;
    }


    @ApiOperation(value = "查询某任务的结算数据")
    @RequestMapping(value = "jszbid", method = RequestMethod.GET)
    @ResponseBody
    public String  jszbid(String xmid,HttpServletRequest request){
        // yswtservice.getysbg(zbid);
        com.alibaba.fastjson.JSONObject jo = new com.alibaba.fastjson.JSONObject();
        JsRwZb jsRwZb= jssqService.selectRwzb(xmid);
        List<JsRwCb> jsRwCbs = jssqService.GetJsmc(jsRwZb.getId());
        jo.put("ysrwcb",jsRwCbs);
        return jo.toString();
    }

    @ApiOperation(value = "查询某任务的预算数据")
    @RequestMapping(value = "yszbid", method = RequestMethod.GET)
    @ResponseBody
    public String  yszbid(String xmid,HttpServletRequest request){
        // yswtservice.getysbg(zbid);
        com.alibaba.fastjson.JSONObject jo = new com.alibaba.fastjson.JSONObject();
        try{
            YsRwzb ysRwzb= jssqService.selectYszbid(xmid);
            List<YsRwcb> ysRwcb = jssqService.getYsRwcb(ysRwzb.getId());
            if(ysRwcb!=null){
                jo.put("ysrwcb",ysRwcb);
            }
        }catch (Exception e){
            jo.put("result", "error");
            e.printStackTrace();
        }


        return jo.toString();
    }

    @ApiOperation(value = "结算列表数据")
    @RequestMapping(value = "getJssqLsit", method = RequestMethod.GET)
    public String getJssqLsit(HttpServletRequest request, Jssq jssq) {
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");//获取当前登录人
        JSONObject jo = new JSONObject();

        List<Jssq> list = new ArrayList<Jssq>();
        jssq.setStartRow();
        jssq.setEndRow();
        jssq.setXmfzr(userEntity.getUserid());
        int total = 0;
        try {
            total = jssqService.selectJssqCount(jssq);
            if (total > 0) {
                list = jssqService.selectJssqList(jssq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jo.put("total", total);
        jo.put("list", list);
        System.out.println("------" + jo.toString());
        return jo.toString();

    }

    @ApiOperation(value = "预算汇总表数据")
    @RequestMapping(value = "SelectYshz", method = RequestMethod.GET)
    public String SelectYshz(HttpServletRequest request, String xmid) {
        JSONObject jo = new JSONObject();
        String flbh_ty = "01";
        ys_rucb rucb_ty = jssqService.selectYshz(flbh_ty, xmid);//庭院管网
        if (rucb_ty != null) {
            jo.put("ty_je", rucb_ty.getYsje());
            jo.put("ty_bz", rucb_ty.getBz());
        } else {
            jo.put("ty_je", 0);
            jo.put("ty_bz", "");
        }
        String flbh_bf = "02";
        ys_rucb rucb_bf = jssqService.selectYshz(flbh_bf, xmid);//泵房
        if (rucb_bf != null) {
            jo.put("bf_je", rucb_bf.getYsje());
            jo.put("bf_bz", rucb_bf.getBz());
        } else {
            jo.put("bf_je", 0);
            jo.put("bf_bz", "");
        }
        String flbh_hb = "03";
        ys_rucb rucb_hb = jssqService.selectYshz(flbh_hb, xmid);//户表
        if (rucb_hb != null) {
            jo.put("hb_je", rucb_hb.getYsje());
            jo.put("hb_bz", rucb_hb.getBz());
        } else {
            jo.put("hb_je", 0);
            jo.put("hb_bz", "");
        }
        String flbh_dg = "04";
        ys_rucb rucb_dg = jssqService.selectYshz(flbh_dg, xmid);//吊管
        if (rucb_dg != null) {
            jo.put("dg_je", rucb_dg.getYsje());
            jo.put("dg_bz", rucb_dg.getBz());
        } else {
            jo.put("dg_je", 0);
            jo.put("dg_bz", "");
        }
        String flbh_gsb = "05";
        ys_rucb rucb_gsb = jssqService.selectYshz(flbh_gsb, xmid);//工商表
        if (rucb_gsb != null) {
            jo.put("gsb_je", rucb_gsb.getYsje());
            jo.put("gsb_bz", rucb_gsb.getBz());
        } else {
            jo.put("gsb_je", 0);
            jo.put("gsb_bz", "");
        }
        String flbh_gcsjf = "06";
        ys_rucb rucb_gcsjf = jssqService.selectYshz(flbh_gcsjf, xmid);//工程设计费
        if (rucb_gcsjf != null) {
            jo.put("gcsjf_je", rucb_gcsjf.getYsje());
            jo.put("gcsjf_bz", rucb_gcsjf.getBz());
        } else {
            jo.put("gcsjf_je", 0);
            jo.put("gcsjf_bz", "");
        }
        String flbh_gcjlf = "07";
        ys_rucb rucb_gcjlf = jssqService.selectYshz(flbh_gcjlf, xmid);//工程监理费
        if (rucb_gcjlf != null) {
            jo.put("gcjlf_je", rucb_gcjlf.getYsje());
            jo.put("gcjlf_bz", rucb_gcjlf.getBz());
        } else {
            jo.put("gcjlf_je", 0);
            jo.put("gcjlf_bz", "");
        }
        String flbh_qtfy = "08";
        ys_rucb rucb_qtfy = jssqService.selectYshz(flbh_qtfy, xmid);//工程监理费
        if (rucb_qtfy != null) {
            jo.put("qtfy_je", rucb_qtfy.getYsje());
            jo.put("qtfy_bz", rucb_qtfy.getBz());
        } else {
            jo.put("qtfy_je", 0);
            jo.put("qtfy_bz", "");
        }
        JsRwZb rwzb = jssqService.selectRwzb(xmid);
        if(rwzb!=null){
            JsRwCb RWcb_ty = jssqService.selectRwcb(rwzb.getId(), "01");
            if(RWcb_ty!=null){
                jo.put("ty_js", RWcb_ty.getJsje());
            }else{
                jo.put("ty_js", 0);
            }
            JsRwCb RWcb_bf = jssqService.selectRwcb(rwzb.getId(), "02");
            if(RWcb_bf!=null){
                jo.put("bf_js", RWcb_bf.getJsje());
            }else{
                jo.put("bf_js", 0);
            }
            JsRwCb RWcb_hb = jssqService.selectRwcb(rwzb.getId(), "03");
            if(RWcb_hb!=null){
                jo.put("hb_js", RWcb_hb.getJsje());
            }else{
                jo.put("hb_js", 0);
            }
            JsRwCb RWcb_dg = jssqService.selectRwcb(rwzb.getId(), "04");
            if(RWcb_dg!=null){
                jo.put("dg_js", RWcb_dg.getJsje());
            }else{
                jo.put("dg_js", 0);
            }
            JsRwCb RWcb_gsb = jssqService.selectRwcb(rwzb.getId(), "05");
            if(RWcb_gsb!=null){
                jo.put("gsb_js", RWcb_gsb.getJsje());
            }else{
                jo.put("gsb_js", 0);
            }
            JsRwCb RWcb_gcsjf = jssqService.selectRwcb(rwzb.getId(), "06");
            if(RWcb_gcsjf!=null){
                jo.put("gcsjf_js", RWcb_gcsjf.getJsje());
            }else{
                jo.put("gcsjf_js", 0);
            }
            JsRwCb RWcb_gcjlf = jssqService.selectRwcb(rwzb.getId(), "07");
            if(RWcb_gcjlf!=null){
                jo.put("gcjlf_js", RWcb_gcjlf.getJsje());
            }else{
                jo.put("gcjlf_js", 0);
            }
            JsRwCb RWcb_qtfy = jssqService.selectRwcb(rwzb.getId(), "08");
            if(RWcb_qtfy!=null){
                jo.put("qtfy_js", RWcb_qtfy.getJsje());
            }else{
                jo.put("qtfy_js", 0);
            }
            if(rwzb!=null){
                jo.put("bz", rwzb.getBz());
            }
        }
        //   System.out.println(rucb_ty.getYsje()+"-----"+rucb_bf.getYsje()+"------");
        return jo.toString();

    }


    @ApiOperation(value = "保存发起用户结算")
    @RequestMapping(value = "SaveYhjssq", method = RequestMethod.GET)
    public String SaveYhjssq(HttpServletRequest request, String xmid) {
        JSONObject jo = new JSONObject();
        Map<String, Object> map = new HashMap<String, Object>();
        DxDfsEntity dxDfsEntity = new DxDfsEntity();
        String ss = "ZHYS";
        map.put("v_stop_run", ss);
        sqzhysService.texuChange(map);
        String rwbh = "JS" + map.get("p_result");
        String id = GuidUtils.getGuid();
        String pdefId = "C0018";
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
        String czr = user.getUserid();
        //开始信息流程实例
        WfRuInstEntity instEntity = new WfRuInstEntity();
        String procInstId = GuidUtils.getGuid();
        instEntity.setProcInstId(procInstId);
        instEntity.setParammap(rwbh);
        instEntity.setPdefId(pdefId);
        instEntity.setProcstatus(1);
        instEntity.setStartdt(new Date());
        instEntity.setStartuser(czr);
        service.saveWfRuInst(instEntity);
        //将项目信息存入流程实例使用内容表(WF_RU_INST_CONTENT)
        WfRuInstContentEntity wfRuInstContentEntity = new WfRuInstContentEntity();
        wfRuInstContentEntity.setContentid(GuidUtils.getGuid());
        wfRuInstContentEntity.setProcInstId(procInstId);
        wfRuInstContentEntity.setTableName("XM_INSTANCE");
        wfRuInstContentEntity.setContentvalue(xmid);
        // wfRuInstContentEntity.setContenttype("ZHYS_SQZB");
        wfRuInstContentEntity.setContentname(id);
        service.saveWfRuInstContent(wfRuInstContentEntity);

        //创建任务，存入历史任务表中
        TaskEntity task = new TaskEntity();
        String taskId = GuidUtils.getGuid();
        task.setTaskid(taskId);
        task.setPtaskid("0");//前置任务为空
        task.setProc_inst_id(procInstId);//流程实例ID
        task.setC_activityid("C0180");//环节编号
        task.setQssj(new Date());
        task.setJssj(new Date());
        task.setIscurrent("0");//已完成
        task.setCzr(czr);//操作人
        task.setIscheck("1");//已签收
        task.setHasnew("2");//无子流程
        task.setRwblr(user.getUserid());//任务办理人为当前登录人
        jssqService.insertHiTask(task);

        //下一节点 创建任务  发起用户结算
        TaskEntity newTask = new TaskEntity();//创建新的task
        String direction = "0";//流程方向 前进/回退s
        String pdefid_id = "C0180";
        List<WfReNodeEntity> list = service.findByActivityidAndDirection(pdefid_id, direction);
        WfDfActivityEntity wfDfActivityEntity = service.findByActivityid(list.get(0).getNextnode());
        taskId = GuidUtils.getGuid();
        newTask.setTaskid(taskId);
        newTask.setPtaskid("0");//前置任务为空
        newTask.setProc_inst_id(procInstId);//流程实例ID
        newTask.setC_activityid(list.get(0).getNextnode());//环节编号
        newTask.setIscurrent("1");//已完成
        newTask.setCzr(czr);//操作人
        newTask.setQssj(new Date());//起始时间
        List<XmUserEntity> xmUserEntity=jssqService.getJsmrr("C0018");//根据activityid查找用户信息
        newTask.setRwblr(xmUserEntity.get(0).getUserid());
        newTask.setIscheck("0");//已签收
        newTask.setHasnew("1");//有流程
        newTask.setRefkey("ZHYS_SQZB");//关联实体key
        newTask.setRefid(id);//关联实体ID
        Date qssj = new Date();
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        newTask.setCheck_time(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
        newTask.setJssx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
        newTask.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute())+Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
        jssqService.insertTask(newTask);

        //发送任务提醒短信
        String content = "";
        Map<String,Object> userMap=null;

        dxDfsEntity.setId(GuidUtils.getGuid());//标识
        dxDfsEntity.setDxmbid("4");//短信模版标识
        dxDfsEntity.setFsr(user.getUserid());//发送人
        dxDfsEntity.setFssj(new Date());//发送时间
        dxDfsEntity.setFscs("1");//发送次数
        Map<String,Object> dxmb = messageService.getDxMb("4");//模版4：任务提醒，替换短信模版内容
        XmInstanceEntity xmInstanceEntity = jssqService.getXmInstanceByXmid(xmid);//获取项目名称
        content = dxmb.get("DXMB").toString();//获取短信模版
        content = content.replace("[环节名称类型]",wfDfActivityEntity.getActivityname());
        content = content.replace("[任务单编号]",rwbh);
        content = content.replace("[项目名称]",xmInstanceEntity.getXmmc());
        dxDfsEntity.setFsnr(content);//发送内容
        // UserEntity userEntity = yswtservice.getUserIdAndUserMobilephone("40008");
        dxDfsEntity.setJsr(xmUserEntity.get(0).getUserid());//接收人
        dxDfsEntity.setJshm(xmUserEntity.get(0).getMobilephone());//接收号码
        dxDfsEntity.setTaskid(taskId);
        jssqService.saveDxDfsEntity(dxDfsEntity);//保存

        jo.put("result", "success");
        return jo.toString();
    }


    @ApiOperation(value = "编制用户结算书")
    @RequestMapping(value = "selectBzyhjssList", method = RequestMethod.GET)
    public String selectBzyhjssList(HttpServletRequest request, Jssq jssq) {
        JSONObject jo = new JSONObject();
        List<Jssq> list = new ArrayList<Jssq>();
        jssq.setStartRow();
        jssq.setEndRow();
        int total = 0;
        try {
            total = jssqService.selectBzyhjssCount(jssq);
            if (total > 0) {
                list = jssqService.selectBzyhjssList(jssq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jo.put("total", total);
        jo.put("list", list);
        System.out.println("------" + jo.toString());
        return jo.toString();
    }

    @ApiOperation(value = "查询预算资料上传")
    @RequestMapping(value = "getYszlscLsit", method = RequestMethod.GET)
    public String getYszlscLsit(HttpServletRequest request, String xmid, Integer page, Integer size) {
        JSONObject jo = new JSONObject();
        Map<String, Object> map = new HashedMap();
        map.put("page", (page - 1) * size);
        map.put("pageSize", (page - 1) * size + size);
        map.put("xmid", xmid);
        int total = 0;
        List<JsRwFj> list = new ArrayList<JsRwFj>();
        try {
            total = jssqService.selectJszlCount(xmid);
            if (total > 0) {
                list = jssqService.selectJszlList(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jo.put("total", total);
        jo.put("list", list);
        return jo.toString();

    }

    @ApiOperation(value = "删除单个资料上传")
    @RequestMapping(value = "deleteJszl", method = RequestMethod.GET)
    public String deleteJszl(HttpServletRequest request, String id, String xmid) {
        JSONObject jo = new JSONObject();
        Map<String, Object> map = new HashedMap();
        map.put("page", "1");
        map.put("pageSize", "5");
        map.put("xmid", xmid);
        try {
            List<JsRwFj> list = jssqService.selectJszlList(map);//
            jssqService.delectRwfj(id);
            System.out.println(list.size() + "---------------");
            if (list.size() > 0) {

            } else {
                jssqService.delectRwZb(xmid);
            }
            jo.put("msg", "删除成功");
        } catch (Exception e) {
            jo.put("msg", "程序异常，请联系管理员");
            e.printStackTrace();
        }
        return jo.toString();
    }


    //暂时不用
    @ApiOperation(value = "保存")
    @RequestMapping(value = "SaveJsje", method = RequestMethod.GET)
    public String SaveJsje(HttpServletRequest request, String xmid, String ty_js, String bf_js,
                           String hb_js, String dg_js, String gsb_js, String gcsjf_js, String gcjlf_js, String qtfy_js,
                           String ty_je, String bf_je, String hb_je, String dg_je, String gsb_je, String gcsjf_je, String gcjlf_je,
                           String qtfy_je, String bz) {
        JSONObject jo = new JSONObject();
        String flag = "success";
        DxDfsEntity dxDfsEntity = new DxDfsEntity();
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
        Map<String, Object> map = new HashMap<String, Object>();
        String ss = "ZHYS";
        map.put("v_stop_run", ss);
        sqzhysService.texuChange(map);
        String rwbh = "JS" + map.get("p_result");
        JsRwCb jsRwCb = new JsRwCb();
        JsRwZb jsRwZb = new JsRwZb();
        JsRwFj jsRwFj = new JsRwFj();
        //先判断是否是退回
        JsRwZb rwzb = jssqService.selectRwzb(xmid);
        if (rwzb != null) {//存在  及是退回
            //跟新任务主表
            rwzb.setBz(bz);
            rwzb.setFqrq(new Date());
            rwzb.setFqr(user.getUserid());
            jssqService.updataRwzb(rwzb);
            JsRwCb rwcb_ty = jssqService.selectRwcb(rwzb.getId(), "01");
            if (ty_js != "") {
              //  System.out.println(ty_js+"-------------退回");
                if (rwcb_ty != null) {
                    rwcb_ty.setJsje(ty_js);
                    rwcb_ty.setYsje(ty_je);
                    jssqService.updatRwcb(rwcb_ty);
                } else {
                    jsRwCb.setId(GuidUtils.getGuid());
                    jsRwCb.setYhjsrwzbid(rwzb.getId());//从表关联id
                    jsRwCb.setJsfl("01");
                    jsRwCb.setJsje(ty_js);
                    jsRwCb.setYsje(ty_je);
                    jssqService.insertJsRwcb(jsRwCb);
                }
            }
            JsRwCb rwcb_bf = jssqService.selectRwcb(rwzb.getId(), "02");
            if (bf_js != "") {
                if (rwcb_bf != null) {
                    rwcb_bf.setJsje(bf_js);
                    rwcb_bf.setYsje(bf_je);
                    jssqService.updatRwcb(rwcb_bf);
                } else {
                    jsRwCb.setId(GuidUtils.getGuid());
                    jsRwCb.setYhjsrwzbid(rwzb.getId());//从表关联id
                    jsRwCb.setJsfl("02");
                    jsRwCb.setJsje(bf_js);
                    jsRwCb.setYsje(bf_je);
                    jssqService.insertJsRwcb(jsRwCb);
                }
            }
            JsRwCb rwcb_hb = jssqService.selectRwcb(rwzb.getId(), "03");
            if (hb_js != "") {
                if (rwcb_hb != null) {
                    rwcb_hb.setJsje(hb_js);
                    rwcb_hb.setYsje(hb_je);
                    jssqService.updatRwcb(rwcb_hb);
                } else {
                    jsRwCb.setId(GuidUtils.getGuid());
                    jsRwCb.setYhjsrwzbid(rwzb.getId());//从表关联id
                    jsRwCb.setJsfl("03");
                    jsRwCb.setJsje(hb_js);
                    jsRwCb.setYsje(hb_je);
                    jssqService.insertJsRwcb(jsRwCb);
                }
            }
            JsRwCb rwcb_dg = jssqService.selectRwcb(rwzb.getId(), "04");
            if (dg_js != "") {
                if (rwcb_dg != null) {
                    rwcb_dg.setJsje(dg_js);
                    rwcb_dg.setYsje(dg_je);
                    jssqService.updatRwcb(rwcb_dg);
                } else {
                    jsRwCb.setId(GuidUtils.getGuid());
                    jsRwCb.setYhjsrwzbid(rwzb.getId());//从表关联id
                    jsRwCb.setJsfl("04");
                    jsRwCb.setJsje(dg_js);
                    jsRwCb.setYsje(dg_je);
                    jssqService.insertJsRwcb(jsRwCb);
                }
            }
            JsRwCb rwcb_gsb = jssqService.selectRwcb(rwzb.getId(), "05");
            if (gsb_js != "") {
                if (rwcb_gsb != null) {
                    rwcb_gsb.setJsje(gsb_js);
                    rwcb_gsb.setYsje(gsb_je);
                    jssqService.updatRwcb(rwcb_gsb);
                } else {
                    jsRwCb.setId(GuidUtils.getGuid());
                    jsRwCb.setYhjsrwzbid(rwzb.getId());//从表关联id
                    jsRwCb.setJsfl("05");
                    jsRwCb.setJsje(gsb_js);
                    jsRwCb.setYsje(gsb_je);
                    jssqService.insertJsRwcb(jsRwCb);
                }
            }
            JsRwCb rwcb_gcsjf = jssqService.selectRwcb(rwzb.getId(), "06");
            if (gcsjf_js != "") {
                if (rwcb_gcsjf != null) {
                    rwcb_gcsjf.setJsje(gcsjf_js);
                    rwcb_gcsjf.setYsje(gcsjf_je);
                    jssqService.updatRwcb(rwcb_gcsjf);
                } else {
                    jsRwCb.setId(GuidUtils.getGuid());
                    jsRwCb.setYhjsrwzbid(rwzb.getId());//从表关联id
                    jsRwCb.setJsfl("06");
                    jsRwCb.setJsje(gcsjf_js);
                    jsRwCb.setYsje(gcsjf_je);
                    jssqService.insertJsRwcb(jsRwCb);
                }
            }
            JsRwCb rwcb_gcjlf = jssqService.selectRwcb(rwzb.getId(), "07");
            if (gcjlf_js != "") {
                if (rwcb_gcjlf != null) {
                    rwcb_gcjlf.setJsje(gcjlf_js);
                    rwcb_gcjlf.setYsje(gcjlf_je);
                    jssqService.updatRwcb(rwcb_gcjlf);
                } else {
                    jsRwCb.setId(GuidUtils.getGuid());
                    jsRwCb.setYhjsrwzbid(rwzb.getId());//从表关联id
                    jsRwCb.setJsfl("07");
                    jsRwCb.setJsje(gcjlf_js);
                    jsRwCb.setYsje(gcjlf_je);
                    jssqService.insertJsRwcb(jsRwCb);
                }
            }
            JsRwCb rwcb_qtfy = jssqService.selectRwcb(rwzb.getId(), "08");
            if (qtfy_js != "") {
                if (rwcb_qtfy != null) {
                    rwcb_qtfy.setJsje(qtfy_js);
                    rwcb_qtfy.setYsje(qtfy_je);
                    jssqService.updatRwcb(rwcb_qtfy);
                } else {
                    jsRwCb.setId(GuidUtils.getGuid());
                    jsRwCb.setYhjsrwzbid(rwzb.getId());//从表关联id
                    jsRwCb.setJsfl("08");
                    jsRwCb.setJsje(qtfy_js);
                    jsRwCb.setYsje(qtfy_je);
                    jssqService.insertJsRwcb(jsRwCb);
                }
            }
            //跟新附件表
            List<JsRwFj> listfj = jssqService.selectJsFjList(xmid);
            for (int i = 0; i < listfj.size(); i++) {
                //  list.get(i).setYhjsrwid(id);
                jsRwFj.setId(listfj.get(i).getId());
                jsRwFj.setYhjsrwid(rwzb.getId());
                jssqService.updateJsFj(jsRwFj);
            }

        } else {
            //保存任务主表
            String id = GuidUtils.getGuid();
            jsRwZb.setId(id);
            jsRwZb.setXmid(xmid);
            jsRwZb.setBz(bz);
            jsRwZb.setRwbh(rwbh);
            jsRwZb.setFqr(user.getUserid());
            jsRwZb.setFqrq(new Date());
            jssqService.insertRwzb(jsRwZb);
            //跟新附件表
            List<JsRwFj> listfj = jssqService.selectJsFjList(xmid);
            for (int i = 0; i < listfj.size(); i++) {
                //  list.get(i).setYhjsrwid(id);
                jsRwFj.setId(listfj.get(i).getId());
                jsRwFj.setYhjsrwid(id);
                jssqService.updateJsFj(jsRwFj);
            }
            //保存结算金额至从表
            if (ty_js != "") {
                jsRwCb.setId(GuidUtils.getGuid());
                jsRwCb.setYhjsrwzbid(id);//从表关联id
                jsRwCb.setJsfl("01");
                jsRwCb.setJsje(ty_js);
                jsRwCb.setYsje(ty_je);
                jssqService.insertJsRwcb(jsRwCb);
            }
            if (bf_js != "") {
                jsRwCb.setId(GuidUtils.getGuid());
                jsRwCb.setYhjsrwzbid(id);//从表关联id
                jsRwCb.setJsfl("02");
                jsRwCb.setJsje(bf_js);
                jsRwCb.setYsje(bf_je);
                jssqService.insertJsRwcb(jsRwCb);
            }
            if (hb_js != "") {
                jsRwCb.setId(GuidUtils.getGuid());
                jsRwCb.setYhjsrwzbid(id);//从表关联id
                jsRwCb.setJsfl("03");
                jsRwCb.setJsje(hb_js);
                jsRwCb.setYsje(hb_je);
                jssqService.insertJsRwcb(jsRwCb);
            }
            if (dg_js != "") {
                jsRwCb.setId(GuidUtils.getGuid());
                jsRwCb.setYhjsrwzbid(id);//从表关联id
                jsRwCb.setJsfl("04");
                jsRwCb.setJsje(dg_js);
                jsRwCb.setYsje(dg_je);
                jssqService.insertJsRwcb(jsRwCb);
            }
            if (gsb_js != "") {
                jsRwCb.setId(GuidUtils.getGuid());
                jsRwCb.setYhjsrwzbid(id);//从表关联id
                jsRwCb.setJsfl("05");
                jsRwCb.setJsje(gsb_js);
                jsRwCb.setYsje(gsb_je);
                jssqService.insertJsRwcb(jsRwCb);
            }
            if (gcsjf_js != "") {
                jsRwCb.setId(GuidUtils.getGuid());
                jsRwCb.setYhjsrwzbid(id);//从表关联id
                jsRwCb.setJsfl("06");
                jsRwCb.setJsje(gcsjf_js);
                jsRwCb.setYsje(gcsjf_je);
                jssqService.insertJsRwcb(jsRwCb);
            }
            if (gcjlf_js != "") {
                jsRwCb.setId(GuidUtils.getGuid());
                jsRwCb.setYhjsrwzbid(id);//从表关联id
                jsRwCb.setJsfl("07");
                jsRwCb.setJsje(gcjlf_js);
                jsRwCb.setYsje(gcjlf_je);
                jssqService.insertJsRwcb(jsRwCb);
            }
            if (qtfy_js != "") {
                jsRwCb.setId(GuidUtils.getGuid());
                jsRwCb.setYhjsrwzbid(id);//从表关联id
                jsRwCb.setJsfl("08");
                jsRwCb.setJsje(qtfy_js);
                jsRwCb.setYsje(qtfy_je);
                jssqService.insertJsRwcb(jsRwCb);
            }
        }
        try {
            //开始跟新信息流程实例 跟新
            InstEntity instEntity = new InstEntity();
            instEntity = jssqService.getRuInst(xmid);
            instEntity.setPdefId("C0019");
            jssqService.updataInst(instEntity);

            //加入到历史任务中
            TaskEntity taskEntity = new TaskEntity();
            String direction = "0";//流程方向 前进/回退s
            String pdefid_id = "C0018";
            List<WfReNodeEntity> list = service.findByActivityidAndDirection(pdefid_id, direction);
            WfDfActivityEntity wfDfActivityEntity = service.findByActivityid(list.get(0).getNextnode());
            taskEntity = jssqService.getTask(xmid);
            taskEntity.setJssj(new Date());//结束时间
            taskEntity.setIscheck("1");//0未签收1已签收
            jssqService.insertHiTask(taskEntity);//添加历史任务
            jssqCsService.delectTask(taskEntity.getTaskid());//删除任务

            //新建任务
            TaskEntity taskEntity1 = new TaskEntity();
            taskEntity1.setTaskid(GuidUtils.getGuid());
            taskEntity1.setPtaskid("0");
            taskEntity1.setProc_inst_id(taskEntity.getProc_inst_id());
            taskEntity1.setC_activityid("C0019");
            taskEntity1.setIscurrent("1");
            taskEntity1.setCzr(user.getUserid());
            taskEntity1.setQssj(new Date());
            taskEntity1.setIscheck("0");
            Date qssj = new Date();
            SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            taskEntity1.setCheck_sx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
            taskEntity1.setJssx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
            taskEntity1.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute())+Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
            taskEntity1.setHasnew("2");
            taskEntity1.setRefkey(taskEntity.getRefkey());
            taskEntity1.setRefid(taskEntity.getRefid());
            List<XmUserEntity> xmUserEntity = jssqService.getJsmrr("C0019");//获取任务办理人
            taskEntity1.setRwblr(xmUserEntity.get(0).getUserid());
            jssqService.insertTask(taskEntity1);

            //发送任务提醒短信
            String content = "";
            Map<String,Object> userMap=null;

            dxDfsEntity.setId(GuidUtils.getGuid());//标识
            dxDfsEntity.setDxmbid("4");//短信模版标识
            dxDfsEntity.setFsr(user.getUserid());//发送人
            dxDfsEntity.setFssj(new Date());//发送时间
            dxDfsEntity.setFscs("1");//发送次数
            Map<String,Object> dxmb = messageService.getDxMb("4");//模版4：任务提醒，替换短信模版内容
            XmInstanceEntity xmInstanceEntity = jssqService.getXmInstanceByXmid(xmid);//获取项目名称
            content = dxmb.get("DXMB").toString();//获取短信模版
            content = content.replace("[环节名称类型]",wfDfActivityEntity.getActivityname());
            content = content.replace("[任务单编号]",rwbh);
            content = content.replace("[项目名称]",xmInstanceEntity.getXmmc());
            dxDfsEntity.setFsnr(content);//发送内容
            // UserEntity userEntity = yswtservice.getUserIdAndUserMobilephone("40008");
            dxDfsEntity.setJsr(xmUserEntity.get(0).getUserid());//接收人
            dxDfsEntity.setJshm(xmUserEntity.get(0).getMobilephone());//接收号码
            dxDfsEntity.setTaskid(taskEntity1.getTaskid());
            jssqService.saveDxDfsEntity(dxDfsEntity);//保存
        } catch (NumberFormatException e) {
           flag = "error";
        }


        //taskEntity.setC_activityid(list.get(0).getNextnode());
       /* Date qssj = new Date();
        taskEntity.setJssj(new Date());
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        taskEntity.setCheck_time(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
        taskEntity.setJssx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
        taskEntity.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute())+Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
*/


        /*TaskEntity taskEntity = new TaskEntity();
        String direction = "0";//流程方向 前进/回退s
        String pdefid_id = "C0018";
        List<WfReNodeEntity> list = service.findByActivityidAndDirection(pdefid_id, direction);
        WfDfActivityEntity wfDfActivityEntity = service.findByActivityid(list.get(0).getNextnode());
        taskEntity = jssqService.getTask(xmid);
        taskEntity.setC_activityid(list.get(0).getNextnode());
        Date qssj = new Date();
        taskEntity.setQssj(new Date());
        taskEntity.setJssj(new Date());
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        taskEntity.setCheck_time(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
        taskEntity.setJssx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
        taskEntity.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute())+Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
        jssqService.updataTask(taskEntity);//跟新在线任务

        taskEntity.setTaskid(GuidUtils.getGuid());
        taskEntity.setC_activityid("C0018");
        taskEntity.setJssj(new Date());
        jssqService.insertHiTask(taskEntity);//添加历史任务*/

        jo.put("result", flag);

        return jo.toString();
    }

    /***
     *上传
     * @param request
     * @param
     * @return
     */
    @RequestMapping(value = "importFile", method = RequestMethod.POST, consumes = {"multipart/mixed", "multipart/form-data"})
    @ResponseBody
    public String importEnterPriseQuota(
            @RequestParam(value = "multipartFiles", required = false) MultipartFile multipartFiles,
            @RequestParam(value = "operation", required = false) String operation,
            @RequestParam(value = "filename", required = false) String filename,
            @RequestParam(value = "xmid", required = false) String xmid,
            @RequestParam(value = "zbid", required = false) String zbid,
            HttpServletRequest request) {
        JSONObject jo = new JSONObject();

        String id = GuidUtils.getGuid();
        // String id = zbid;
        if (Objects.isNull(multipartFiles)) {
            jo.put("msg", "文件为空，请重新上传");
            return jo.toString();
        }
        XmInstanceEntity xmInstanceEntity=jssqService.getXmInstanceByXmid(xmid);
        String fileDir= StringUtil.getBasePath()+"upload"+File.separator+xmInstanceEntity.getXmbh()+File.separator+"js"+File.separator;
        String  fiLj="upload"+File.separator+xmInstanceEntity.getXmbh()+File.separator+"js"+File.separator;
        File dir = new File(fileDir);
        File file = null;
        try {
            file = new File(fileDir, multipartFiles.getName());
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
            String str_date="_"+df.format(new Date());
            file.renameTo(new File(fileDir+filenames[0]+str_date+"."+filenames[filenames.length-1]));//重命名
            HttpSession session = request.getSession();
            UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
            Map<String, Object> map = new HashedMap();
            map.put("page", "0");
            map.put("pageSize", "5");
            map.put("xmid", xmid);
            System.out.println(xmid + "-------xmid");
            List<JsRwFj> list = jssqService.selectJszlList(map);
            if (list.size() > 0) {
                String yhjsid = list.get(0).getYhjsrwid();//id
                JsRwFj jsRwFj = new JsRwFj();
                jsRwFj.setId(GuidUtils.getGuid());
                jsRwFj.setYhjsrwid(yhjsid);
                jsRwFj.setFjhz(filenames[filenames.length - 1]);//附件后缀
                jsRwFj.setFjmc(filename);//附件名称
                jsRwFj.setFjlj(fiLj+filenames[0]+str_date+"."+filenames[filenames.length-1]);//附件路径
                jsRwFj.setScr(user.getUserid());//上传人
                jsRwFj.setScsj(new Date());
                jsRwFj.setXmid(xmid);
                jssqService.insertJszl(jsRwFj);
            } else {
                JsRwFj jsRwFj = new JsRwFj();
                jsRwFj.setId(GuidUtils.getGuid());
                jsRwFj.setFjhz(filenames[filenames.length - 1]);//附件后缀
                jsRwFj.setFjmc(filename);//附件名称
                jsRwFj.setFjlj(fiLj+filenames[0]+str_date+"."+filenames[filenames.length-1]);//附件路径
                jsRwFj.setScr(user.getUserid());//上传人
                jsRwFj.setScsj(new Date());
                jsRwFj.setXmid(xmid);
                jssqService.insertJszl(jsRwFj);
            }
            jo.put("msg", "上传成功");
        } catch (Exception e) {
            jo.put("msg", "程序异常，请联系管理员");
            e.printStackTrace();
        }
        return jo.toString();

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

}
