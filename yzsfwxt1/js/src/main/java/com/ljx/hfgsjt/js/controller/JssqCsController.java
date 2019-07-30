package com.ljx.hfgsjt.js.controller;

import com.ljx.hfgsjt.entity.dto.Activity.WfDfActivityEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfReNodeEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.InstContentEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.InstEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.TaskEntity;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.js.*;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "JssqCsController")
@Api(description = "结算初审")
public class JssqCsController {

    @Autowired
    private JssqCsService jssqCsService;
    @Autowired
    private JssqService jssqService;
    @Autowired
    private SqzhysService sqzhysService;
    @Autowired
    private MessageService messageService;
    @Autowired
    MessageController controller;
    @Autowired
    private XkylxService service;
    CalculateHours cal = new CalculateHours();

    @ApiOperation(value = "结算初审列表数据")
    @RequestMapping(value = "getJssqCsLsit", method = RequestMethod.GET)
    public String getJssqCsLsit(HttpServletRequest request, Jssq jssq) {
        JSONObject jo = new JSONObject();
        List<Jssq> list = new ArrayList<Jssq>();
        jssq.setStartRow();
        jssq.setEndRow();
        int total = 0;
        try {
            total = jssqCsService.selectJssqCsCount(jssq);
            if (total > 0) {
                list = jssqCsService.selectJssqCsList(jssq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jo.put("total", total);
        jo.put("list", list);
        System.out.println("------" + jo.toString());
        return jo.toString();

    }

    @ApiOperation(value = "预算 结算汇总表数据")
    @RequestMapping(value = "SelectJshz", method = RequestMethod.GET)
    public String SelectJshz(HttpServletRequest request, String xmid) {
        JSONObject jo = new JSONObject();
        JsRwZb jsRwZb =jssqCsService.selectRwzb(xmid);
        jo.put("jsbz", jsRwZb.getBz());
        String flbh_ty = "01";
        ys_rucb rucb_ty = jssqCsService.selectYshz(flbh_ty, xmid);//庭院管网
        JsRwCb jsRwCb_ty = jssqCsService.selectJshz(flbh_ty, xmid);
        if(rucb_ty!=null){
            jo.put("ty_je", rucb_ty.getYsje());
        }
       if(jsRwCb_ty!=null){
          jo.put("ty_js", jsRwCb_ty.getJsje());
       }
        String flbh_bf = "02";
        ys_rucb rucb_bf = jssqCsService.selectYshz(flbh_bf, xmid);//泵房
        JsRwCb jsRwCb_bf = jssqCsService.selectJshz(flbh_bf, xmid);
        if(rucb_bf!=null){
            jo.put("bf_je", rucb_bf.getYsje());
        }
        if(jsRwCb_bf!=null){
            jo.put("bf_js", jsRwCb_bf.getJsje());
        }

        String flbh_hb = "03";
        ys_rucb rucb_hb = jssqCsService.selectYshz(flbh_hb, xmid);//户表
        JsRwCb jsRwCb_hb  = jssqCsService.selectJshz(flbh_hb, xmid);
        if(rucb_hb!=null){
            jo.put("hb_je", rucb_hb.getYsje());
        }
        if(jsRwCb_hb!=null){
            jo.put("hb_js", jsRwCb_hb.getJsje());
        }

        String flbh_dg = "04";
        ys_rucb rucb_dg = jssqCsService.selectYshz(flbh_dg, xmid);//吊管
        JsRwCb jsRwCb_dg = jssqCsService.selectJshz(flbh_dg, xmid);
        if(rucb_dg!=null){
            jo.put("dg_je", rucb_dg.getYsje());
        }
        if(jsRwCb_dg!=null){
            jo.put("dg_js", jsRwCb_dg.getJsje());
        }
        String flbh_gsb = "05";
        ys_rucb rucb_gsb = jssqCsService.selectYshz(flbh_gsb, xmid);//工商表
        JsRwCb jsRwCb_gsb = jssqCsService.selectJshz(flbh_gsb, xmid);
        if(rucb_gsb!=null){
            jo.put("gsb_je", rucb_gsb.getYsje());
        }
        if(jsRwCb_gsb!=null){
            jo.put("gsb_js", jsRwCb_gsb.getJsje());
        }
        String flbh_gcsjf = "06";
        ys_rucb rucb_gcsjf = jssqCsService.selectYshz(flbh_gcsjf, xmid);//工程设计费
        JsRwCb jsRwCb_gcsjf = jssqCsService.selectJshz(flbh_gcsjf, xmid);
        if(rucb_gcsjf!=null){
            jo.put("gcsjf_je", rucb_gcsjf.getYsje());
        }
        if(jsRwCb_gcsjf!=null){
            jo.put("gcsjf_js", jsRwCb_gcsjf.getJsje());
        }
        String flbh_gcjlf = "07";
        ys_rucb rucb_gcjlf = jssqCsService.selectYshz(flbh_gcjlf, xmid);//工程监理费
        JsRwCb jsRwCb_gcjlf  = jssqCsService.selectJshz(flbh_gcjlf, xmid);
        if(rucb_gcjlf!=null){
            jo.put("gcjlf_je", rucb_gcjlf.getYsje());
        }
        if(jsRwCb_gcjlf!=null){
            jo.put("gcjlf_js", jsRwCb_gcjlf.getJsje());
        }
        String flbh_qtfy = "08";
        ys_rucb rucb_qtfy = jssqCsService.selectYshz(flbh_qtfy, xmid);//其他
        JsRwCb jsRwCb_qtfy = jssqCsService.selectJshz(flbh_qtfy, xmid);
        if(rucb_qtfy!=null){
            jo.put("qtfy_je", rucb_qtfy.getYsje());
        }
        if(jsRwCb_qtfy!=null){
            jo.put("qtfy_js", jsRwCb_qtfy.getJsje());
        }
        //   System.out.println(rucb_ty.getYsje()+"-----"+rucb_bf.getYsje()+"------");
        return jo.toString();

    }


    @ApiOperation(value = "获取其他用户资料")
    @RequestMapping(value = "download",method = RequestMethod.POST)
    public HttpServletResponse download(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            //String basePath = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();
            // path是指欲下载的文件的路径。
            String basePath = StringUtil.getBasePath();
            path = basePath+path;
            File file = new File(path);
            // 取得文件名。
           // String fileDir= StringUtil.getBasePath()+"upload"+File.separator+"js"+File.separator+"jszl"+File.separator;
            String filename = file.getName();
            // 取得文件的后缀名。
         //   String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
//            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
//            response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
//            response.setHeader("Access-Control-Allow-Credentials", "true");
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


    @ApiOperation(value = "查询预算资料上传")
    @RequestMapping(value = "getJssqJczl", method = RequestMethod.GET)
    public String getJssqJczl(HttpServletRequest request, String xmid,Integer page,Integer size) {
        JSONObject jo = new JSONObject();
        Map<String, Object> map = new HashedMap();
        map.put("page", (page-1)*size);
        map.put("pageSize", (page-1)*size + size);
        map.put("xmid", xmid);
        int total=0;
        List<JsRwFj> list=new ArrayList<JsRwFj>();
        try {
            total = jssqCsService.selectJszlJcCount(xmid);
            if (total > 0) {
                list=jssqCsService.selectJszlJcList(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        jo.put("total", total);
        jo.put("list", list);
        return jo.toString();

    }

    @ApiOperation(value = "保存")
    @RequestMapping(value = "SaveJsSh", method = RequestMethod.GET)
    public String SaveJsSh(HttpServletRequest request, String xmid,String bz) {
        JSONObject jo = new JSONObject();
        try{
            HttpSession session = request.getSession();
            UserEntity user = (UserEntity)session.getAttribute("user");//获取当前登录的用户信息
            Map<String, Object> map = new HashMap<String, Object>();
            String ss="ZHYS";
            map.put("v_stop_run",ss);
            sqzhysService.texuChange(map);
            String rwbh="JS"+map.get("p_result");
            String id = GuidUtils.getGuid();
            JsRwZb jsRwZb=new JsRwZb();
            JsRwFj jsRwFj=new JsRwFj();
            //跟新任务主表
             jsRwZb= jssqCsService.selectRwzb(xmid);
            if(jsRwZb!=null){
                jsRwZb.setShyj(bz);
                jsRwZb.setCsry(user.getUserid());
                jsRwZb.setCssj(new Date());
                jssqCsService.updataRwzb(jsRwZb);
            }
            List<JsRwFj> listfj=jssqCsService.selectJsFjList(xmid);
            for(int i=0;i<listfj.size();i++){
                //  list.get(i).setYhjsrwid(id);
                jsRwFj.setId(listfj.get(i).getId());
                jsRwFj.setYhjsrwid(id);
                jssqService.updateJsFj(jsRwFj);
            }
            //开始跟新信息流程实例 跟新
      /*      InstEntity instEntity=new InstEntity();
            instEntity=jssqCsService.getRuInst(xmid);
            System.out.println(xmid+"---------xmid"+instEntity.getProcInstId());
            instEntity.setPdefId("C0020");
            jssqService.updataInst(instEntity);*/

         /*   List<XmUserEntity> xmUserEntity1 = jssqService.getJsmrr("C0019");//获取任务办理人
            String taskid="";
            TaskEntity taskEntity=new TaskEntity();
            taskEntity=jssqCsService.getTask(xmid);
            System.out.println("----------taskEntity-----------"+taskEntity.getRwblr());
            taskid=taskEntity.getTaskid();
            taskEntity.setTaskid(GuidUtils.getGuid());
            taskEntity.setJssj(new Date());
            taskEntity.setRwblr(xmUserEntity1.get(0).getUserid());
            taskEntity.setC_activityid("C0019");
            jssqService.insertHiTask(taskEntity);//添加历史任务*/

            //结束流程
            String InstId = "";//唯一ProcInstId
            InstContentEntity incentity = new InstContentEntity();
            incentity = jssqService.getRuInstContent("C0019",xmid);//查
            InstId = incentity.getProcInstId();//唯一ProcInstId
            incentity.setContentid(GuidUtils.getGuid());
            jssqService.insetHiinstContent(incentity);//添加

            //WF_RU_INST     WF_HI_INST
            InstEntity instEntity = new InstEntity();
            instEntity = jssqCsService.selectRuInst("C0019",xmid);//查
            jssqService.insertHiInst(instEntity);//添加

            //WF_TASK   WF_TASK_HISTORY
       /*     List<XmUserEntity> xmUserEntity1 = jssqService.getJsmrr("C0019");//获取任务办理人*/
            TaskEntity taskEntity = new TaskEntity();
            taskEntity = jssqCsService.getTask(xmid);//查
            String taskId = GuidUtils.getGuid();
            taskEntity.setTaskid(taskId);
            taskEntity.setJssj(new Date());
            jssqCsService.insertHiTask(taskEntity);//添加
            //删除WF_RU_INST_CONTENT    WF_RU_INST     WF_TASK
            jssqService.deleteRuInst(InstId);
            jssqService.deleteRuInstContent(InstId);
            jssqService.deleteTask(InstId);

         /*   String direction = "0";//流程方向 前进/回退s
            String pdefid_id = "C0019";
            List<WfReNodeEntity> list = service.findByActivityidAndDirection(pdefid_id, direction);
            WfDfActivityEntity wfDfActivityEntity = service.findByActivityid(list.get(0).getNextnode());
            taskEntity.setTaskid(taskid);
            taskEntity.setC_activityid(list.get(0).getNextnode());
            Date qssj = new Date();
            taskEntity.setQssj(qssj);
            SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            taskEntity.setCheck_time(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
            taskEntity.setJssx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
            taskEntity.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute())+Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
            jssqService.updataTask(taskEntity);//跟新在线任务*/
            jo.put("result", "success");
        }catch (Exception e){
            e.printStackTrace();
            jo.put("result", "result");
        }

        return jo.toString();
    }


    @ApiOperation(value = "退回")
    @RequestMapping(value = "Tuihui", method = RequestMethod.GET)
    public String Tuihui(HttpServletRequest request, String xmid, String thyy) {
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");//获取当前登录人
        DxDfsEntity dxDfsEntity = new DxDfsEntity();
        JSONObject jo = new JSONObject();
            //开始跟新信息流程实例 跟新
            InstEntity instEntity=new InstEntity();
            instEntity=jssqCsService.getRuInst(xmid);
           // System.out.println(xmid+"---------xmid"+instEntity.getProcInstId());
            instEntity.setPdefId("C0018");
            jssqService.updataInst(instEntity);

            TaskEntity taskEntity=new TaskEntity();
            String direction = "1";//流程方向 前进/回退s
             String pdefid_id = "C0019";
             List<WfReNodeEntity> list = service.findByActivityidAndDirection(pdefid_id, direction);
             WfDfActivityEntity wfDfActivityEntity = service.findByActivityid(list.get(0).getNextnode());
            taskEntity=jssqCsService.getTask(xmid);
            taskEntity.setHtyy(thyy);//回退时间
            taskEntity.setJssj(new Date());//结束时间
            taskEntity.setIscheck("1");//0未签收1已签收
            jssqService.insertHiTask(taskEntity);//添加历史任务
            jssqCsService.delectTask(taskEntity.getTaskid());//删除当前任务

            //新建任务
            TaskEntity taskEntity1 = new TaskEntity();
            taskEntity1.setTaskid(GuidUtils.getGuid());
            taskEntity1.setPtaskid("0");
            taskEntity1.setProc_inst_id(taskEntity.getProc_inst_id());
            taskEntity1.setC_activityid(list.get(0).getNextnode());
            taskEntity1.setIscurrent("1");
            taskEntity1.setCzr(userEntity.getUserid());
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
             List<XmUserEntity> xmUserEntity = jssqService.getJsmrr("C0018");//获取任务办理人
            taskEntity1.setRwblr(xmUserEntity.get(0).getUserid());
            taskEntity1.setHtyy(thyy);//回退原因
            jssqService.insertTask(taskEntity1);

        //发送任务提醒短信
        String content = "";
        Map<String,Object> userMap=null;

        dxDfsEntity.setId(GuidUtils.getGuid());//标识
        dxDfsEntity.setDxmbid("4");//短信模版标识
        dxDfsEntity.setFsr(userEntity.getUserid());//发送人
        dxDfsEntity.setFssj(new Date());//发送时间
        dxDfsEntity.setFscs("1");//发送次数
        Map<String,Object> dxmb = messageService.getDxMb("4");//模版4：任务提醒，替换短信模版内容
        XmInstanceEntity xmInstanceEntity = jssqService.getXmInstanceByXmid(xmid);//获取项目名称
        JsRwZb jsRwZb1=jssqCsService.selectRwzb(xmid);
        content = dxmb.get("DXMB").toString();//获取短信模版
        content = content.replace("[环节名称类型]",wfDfActivityEntity.getActivityname());
        content = content.replace("[任务单编号]",jsRwZb1.getRwbh());
        content = content.replace("[项目名称]",xmInstanceEntity.getXmmc());
        dxDfsEntity.setFsnr(content);//发送内容
        // UserEntity userEntity = yswtservice.getUserIdAndUserMobilephone("40008");
        dxDfsEntity.setJsr(xmUserEntity.get(0).getUserid());//接收人
        dxDfsEntity.setJshm(xmUserEntity.get(0).getMobilephone());//接收号码
        dxDfsEntity.setTaskid(taskEntity1.getTaskid());
        jssqService.saveDxDfsEntity(dxDfsEntity);//保存



           /* taskEntity.setC_activityid(list.get(0).getNextnode());
             Date qssj = new Date();
             taskEntity.setQssj(qssj);
             SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             taskEntity.setCheck_time(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
             taskEntity.setJssx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
             taskEntity.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute())+Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
            jssqService.updataTask(taskEntity);//跟新在线任务
            taskEntity.setTaskid(GuidUtils.getGuid());
            taskEntity.setC_activityid("C0019");
            taskEntity.setHtyy(thyy);*/
            //jssqService.insertHiTask(taskEntity);//添加历史任务
           // JsRwZb jsRwZb1=jssqCsService.selectRwzb(xmid);
          //  jssqCsService.deleteGwxxCb(jsRwZb1.getId()); //删除从表数据
           // jssqCsService.deleteGwxxFj(jsRwZb1.getId()); //删除附件表数据 预算资料
            jssqCsService.deleteGwxxFj_sh(xmid,"1");//删除审核附件数据
          //  jssqCsService.deleteGwxxZb(xmid);//删除主表数据
        jo.put("result", "success");
        return jo.toString();
    }

    /***
     * 会审附件上传
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
            HttpServletRequest request) {
        JSONObject jo = new JSONObject();

        String id = GuidUtils.getGuid();
        // String id = zbid;
        if (Objects.isNull(multipartFiles)) {
            jo.put("msg","文件为空，请重新上传");
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
            String str_date=df.format(new Date());
            file.renameTo(new File(fileDir+str_date+filenames[0]+"."+filenames[filenames.length-1]));//重命名
            HttpSession session = request.getSession();
            UserEntity user = (UserEntity)session.getAttribute("user");//获取当前登录的用户信息
            Map<String, Object> map = new HashedMap();
            map.put("page","0");
            map.put("pageSize","5");
            map.put("xmid", xmid);
            System.out.println(xmid+"-------xmid");
            List<JsRwFj> list=jssqCsService.selectJszlJcList(map);
            if(list.size()>0){
                String yhjsid=list.get(0).getYhjsrwid();//id
                JsRwFj jsRwFj=new JsRwFj();
                jsRwFj.setId(GuidUtils.getGuid());
                jsRwFj.setYhjsrwid(yhjsid);
                jsRwFj.setFjlx_Yj("1");
                jsRwFj.setFjhz(filenames[filenames.length-1]);//附件后缀
                jsRwFj.setFjmc(filename);//附件名称
                jsRwFj.setFjlj(fiLj+filename.split("\\.")[0]+str_date+"."+filename.split("\\.")[filename.split("\\.").length-1]);//附件路径
                jsRwFj.setScr(user.getUserid());//上传人
                jsRwFj.setScsj(new Date());
                jsRwFj.setXmid(xmid);
                jssqService.insertJszl(jsRwFj);
            }else{
                JsRwFj jsRwFj=new JsRwFj();
                jsRwFj.setId(GuidUtils.getGuid());
                jsRwFj.setFjlx_Yj("1");
                jsRwFj.setFjhz(filenames[filenames.length-1]);//附件后缀
                jsRwFj.setFjmc(filename);//附件名称
                jsRwFj.setFjlj(fiLj+filename.split("\\.")[0]+str_date+"."+filename.split("\\.")[filename.split("\\.").length-1]);//附件路径
                jsRwFj.setScr(user.getUserid());//上传人
                jsRwFj.setScsj(new Date());
                jsRwFj.setXmid(xmid);
                jssqService.insertJszl(jsRwFj);
            }
            jo.put("msg", "上传成功");
        }catch (Exception e){
            jo.put("msg", "程序异常，请联系管理员");
            e.printStackTrace();
        }
        return jo.toString();

    }



    @ApiOperation(value = "全景图回显审核意见")
    @RequestMapping(value = "getjsshyi", method = RequestMethod.GET)
    public String getjsshyi(HttpServletRequest request, String xmid) {
        JSONObject jo = new JSONObject();
        JsRwZb jsRwZb=jssqCsService.selectRwzb(xmid);
        if(jsRwZb!=null){
            jo.put("shyj",jsRwZb.getShyj() );
        }else{
            jo.put("shyj","" );
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
