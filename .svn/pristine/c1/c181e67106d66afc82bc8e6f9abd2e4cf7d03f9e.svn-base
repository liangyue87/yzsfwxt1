package com.ljx.hfgsjt.sfkp.controller;

import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.Sjst.XmSb;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.htqd.GetXmlbJfEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.sfkp.SfkpSftzdzbEntity;
import com.ljx.hfgsjt.entity.sfkp.SfkpldEntity;
import com.ljx.hfgsjt.message.service.MessageService;
import com.ljx.hfgsjt.repository.CalculateHours;
import com.ljx.hfgsjt.sfkp.service.SfkpService;
import com.ljx.hfgsjt.sgjg.service.SgxdService;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import com.ljx.hfgsjt.util.utils.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.impl.util.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 收费开票控制层
 */
@Controller
@RequestMapping(value = "SfkpController")
public class SfkpController {

    @Autowired
    private SfkpService sfkpService;

    @Autowired
    private SgxdService sgxdService;

    @Autowired
    private MessageService messageService;

    CalculateHours cal = new CalculateHours();

    private Logger logger = LoggerFactory.getLogger(SfkpController.class);

    @ApiOperation(value = "条件查询缴费通知单列表")
    @RequestMapping(value = "getJfList",method = RequestMethod.POST)
    @ResponseBody
    public String getJfList(String xmbh,String xmmc,String lxr,String xmdz,String azlx,String beginTime,String endTime,
                            Integer page,Integer size,String azlxname,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        pd.put("xmfzr",user.getUserid());
        pd.put("xmbh",xmbh);
        pd.put("xmmc",xmmc);
        pd.put("lxr",lxr);
        pd.put("xmdz",xmdz);
        pd.put("azlx",azlx);
        pd.put("beginTime",beginTime);
        pd.put("endTime",endTime);
        pd.put("azlxname",azlxname);
        pd.put("page",page);
        pd.put("size",size);
        try{
            List<GetXmlbJfEntity> list = sfkpService.getJfList(pd);
            int totol = sfkpService.getAllJfList(pd).size();
            jo.put("list",list);
            jo.put("total", totol);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 按用户编号查询缴费通知单列表
     * @param xmid
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "getSftzd",method = RequestMethod.GET)
    @ResponseBody
    public String getSftzd(String xmid, HttpServletResponse response, HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        pd.put("xmid",xmid);
        List<Map<String,Object>> list = sfkpService.getSftzd(pd);
        jo.put("row",list);
        return jo.toString();
    }

    /**
     * 获取收款账号
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "getSkzh",method = RequestMethod.GET)
    @ResponseBody
    public String getSkzh(HttpServletResponse response, HttpServletRequest request){
        JSONObject jo = new JSONObject();
        List<Map<String,Object>> list = sfkpService.getSkzh();
        Map<String, Object> map = new HashMap<String, Object>();
        String ss="JFTZ";
        map.put("v_stop_run",ss);
        sgxdService.texuChange(map);//获取任务编号
        jo.put("tzbh","JFTZ"+map.get("p_result"));
        jo.put("row",list);
        return jo.toString();
    }

    /**
     * 获取工程费栏目下拉框
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "getGcflm",method = RequestMethod.GET)
    @ResponseBody
    public String getGcflm(HttpServletResponse response, HttpServletRequest request){
        JSONObject jo = new JSONObject();
        try{
            List<Map<String,Object>> varList = sfkpService.getGcflm();
            List<Map<String,Object>> zbkjList = sfkpService.getZbkjList();
            jo.put("row",varList);
            jo.put("zbkjList",zbkjList);
            sfkpService.deleteJfhb();
            sfkpService.deleteJfgsb();
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 保存缴费通知单
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "addJftzd",method = RequestMethod.GET)
    @ResponseBody
    public String addJftzd(String tzdid,String sfkpgsbid,String sfkpjmbid,
                           String jtjmbc,String jtgsbc,HttpServletResponse response, HttpServletRequest request){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity)session.getAttribute("user");//获取当前登录的用户信息
        String czr = user.getUserid();
        String id = "";
        if(!"".equals(tzdid) && tzdid != null){
            id =  tzdid;
        }else{
            id = GuidUtils.getGuid();
        }
        String tzdbh = request.getParameter("tzbh");
        String xmid = request.getParameter("xmid");
        try{
            pd.put("id", id);//主表标识
            pd.put("xmid",xmid);//项目标识
            pd.put("tzdbh",tzdbh);//通知单编号
            pd.put("tzdzt","0");//通知单状态 0待确认 1已确认
            String skzh = request.getParameter("skzh");
            String[] arr = skzh.split(" ");
            pd.put("zhmc",arr[0]);//账号名称
            pd.put("zhyh",arr[2]);//账号银行
            pd.put("zh",arr[1]);//账号
            pd.put("zdr",czr);//制单人
            pd.put("jfdw",request.getParameter("jfdw"));//缴费单位
            pd.put("bz",request.getParameter("bz"));//备注
            String xmqk = request.getParameter("xmqk");
            if(xmqk == null){
                xmqk = "";
            }
            pd.put("xmqk",xmqk);//项目情况
            pd.put("ysje",request.getParameter("zje"));//应收金额
            /*==================缴费=====================*/
            Integer count = Integer.valueOf(request.getParameter("fyfls"));
            Map<String,Object> cbpd = new HashMap<>();
            List<String> gssbidList = new ArrayList<>();
            List<String> jmsbidList = new ArrayList<>();
            if(!"".equals(tzdid) && tzdid != null){
                List<String> cbgsidList = sfkpService.queryGsCbid(id);
                List<String> cbjmidList = sfkpService.queryJmCbid(id);
                sfkpService.deleteSftzdcb(pd);
                if(cbgsidList.isEmpty() == false){
                    String cbids = cbgsidList.get(0);
                    if("1".equals(jtgsbc)){
                        String sblx = "DB";
                        sfkpService.deleteSftzdcbsb(cbids,sblx);
                    }else{
                        gssbidList = sfkpService.querygssbid(cbids);
                    }
                }
                if(cbjmidList.isEmpty() == false){
                    String cbidss = cbjmidList.get(0);
                    if("1".equals(jtjmbc)){
                        String sblx = "HB";
                        sfkpService.deleteSftzdcbsb(cbidss,sblx);
                    }else{
                        jmsbidList = sfkpService.queryjmsbid(cbidss);
                    }
                }
            }
            for(int i=0;i<count;i++){
                String label_cs = "label"+(i+1);
                String value_cs = "value"+(i+1);
                String label = request.getParameter(label_cs);
                String value = request.getParameter(value_cs);
                String cbid = GuidUtils.getGuid();
                cbpd.put("id",cbid);//从表标识
                cbpd.put("sftzdzbid",id);//收费通知单主表标识
                cbpd.put("label",label);
                List<String> bhList = sfkpService.querybhBymc(cbpd);
                String tzdlx = "";
                if(bhList.isEmpty() == false){
                    tzdlx = bhList.get(0);
                }
                cbpd.put("tzdlx",tzdlx);//通知单类型 码表
                cbpd.put("ysje",value);//应收金额
                cbpd.put("zdr",user.getUserid());//制单人
                sfkpService.insertSftzdcb(cbpd);//插入收费通知单从表

                if("06".equals(tzdlx)){//工商表
                    if("1".equals(jtgsbc)){
                        pd.put("sfkpgsbid",sfkpgsbid);
                        List<Map<String,Object>> varList = sfkpService.gssbje(pd);
                        Map<String,Object> gspd = new HashMap<>();
                        if(varList.isEmpty() == false){
                            for (int x=0;x<varList.size();x++){
                                gspd.put("sbid",varList.get(x).get("ID"));
                                gspd.put("ysje",varList.get(x).get("GCFYSJE"));
                                gspd.put("sftzdcbid",cbid);
                                gspd.put("id",GuidUtils.getGuid());
                                sfkpService.saveJfgssb(gspd);
                            }
                        }
                    }else{
                        for (int a = 0;a<gssbidList.size();a++){
                            String sbid = gssbidList.get(a);
                            sfkpService.updategssbcbid(sbid,cbid);
                        }
                    }
                }else if("05".equals(tzdlx)){//户表
                    if("1".equals(jtjmbc)){
                        String[] sbarr = sfkpjmbid.split(",");
                        Map<String,Object> jmpd = new HashMap<>();
                        for (int y=0;y<sbarr.length;y++){
                            String[] arr1 = sbarr[y].split(":");
                            jmpd.put("id",arr1[0]);
                            jmpd.put("gcfysje",arr1[1]);
                            jmpd.put("xmid",xmid);
                            jmpd.put("sftzdcbid",cbid);
                            sfkpService.updateSbgcfBySbid(jmpd);
                            sfkpService.updateJfcbgcfBySbid(jmpd);
                        }
                    }else{
                        for (int b = 0;b<jmsbidList.size();b++){
                            String sbid = jmsbidList.get(b);
                            sfkpService.updatejmsbcbid(sbid,cbid);
                        }
                    }
                }else{

                }
            }
            /*==================缴费=====================*/
            if(!"".equals(tzdid) && tzdid != null){
                sfkpService.updateSftzdzb(pd);
            }else{
                sfkpService.insertSftzdzb(pd);//插入收费通知单主表
            }

            WfTaskEntity task = new WfTaskEntity();
            String procInstId = GuidUtils.getGuid();
            String pdefId = "60001";
            WfRuInstEntity instEntity = new WfRuInstEntity();
            WfRuInstContentEntity wfRuInstContentEntity = new WfRuInstContentEntity();

            //启动流程实例并放入历史
            instEntity.setProcInstId(procInstId);
            instEntity.setPdefId(pdefId);
            instEntity.setParammap(tzdbh);
            instEntity.setStartdt(new Date());
            instEntity.setProcstatus(1);
            instEntity.setStartuser(czr);
            WfHiInstEntity hiInstEntity = new WfHiInstEntity(instEntity);
            hiInstEntity.setEndtime(new Date());
            sgxdService.savewfHiInst(hiInstEntity);

            //将项目信息存入流程实例使用内容表(WF_RU_INST_CONTENT)并放入历史
            wfRuInstContentEntity.setContentid(GuidUtils.getGuid());
            wfRuInstContentEntity.setProcInstId(procInstId);
            wfRuInstContentEntity.setTableName("XM_INSTANCE");
            wfRuInstContentEntity.setContentvalue(xmid);
            wfRuInstContentEntity.setContenttype("SFKP_SFTZDZB");
            wfRuInstContentEntity.setContentname(id);
            WfHiInstContentEntity wfHiInstContentEntity = new WfHiInstContentEntity(wfRuInstContentEntity);
            sgxdService.saveWfHiInstContent(wfHiInstContentEntity);

            //创建任务，存入历史任务表中
            String taskId = GuidUtils.getGuid();
            task.setTaskid(taskId);
            task.setQssj(new Date());//起始时间
            task.setJssj(new Date());//结束时间
            task.setPtaskid("0");//前置任务为空
            task.setProcInstId(procInstId);//流程实例ID
            task.setCActivityid(pdefId);//环节编号
            task.setIscurrent("0");//已完成
            task.setCzr(czr);//操作人
            task.setIscheck("1");//已签收
            task.setHasnew("2");//无子流程
            task.setRwblr(czr);//任务办理人
            WfTaskHistoryEntity historyEntity = new WfTaskHistoryEntity(task);
            sgxdService.saveWfTaskHistory(historyEntity);

            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 确认收费通知单
     * @param response
     * @param request
     * @return
     */
    @ApiOperation("确认收费通知单")
    @RequestMapping(value = "updateSftzd",method = RequestMethod.GET)
    @ResponseBody
    public String updateSftzd(String zbid,HttpServletResponse response, HttpServletRequest request){
        String flag = "false";
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity)session.getAttribute("user");//获取当前登录的用户信息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String,Object> pd = new HashMap<>();
        pd.put("zbid",zbid);
        try{
            pd.put("jfqrr",user.getUserid());
            pd.put("pjlx",request.getParameter("pjlx"));
            pd.put("ssje",request.getParameter("ssje"));
            pd.put("pjh",request.getParameter("pjh"));
            pd.put("kprq",sdf.parse(request.getParameter("kprq")));
            pd.put("bz",request.getParameter("bz"));
            sfkpService.updateSftzd(pd);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("v_tzdid",zbid);
            sfkpService.ExecAfterJfqr(map);
            flag = "true";
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 根据项目ID查询未缴费工商表
     * @param xmid
     * @param request
     * @return
     */
    @ApiOperation("根据项目ID查询未缴费工商表")
    @RequestMapping(value = "queryGsbByXmid",method = RequestMethod.GET)
    @ResponseBody
    public String queryGsbByXmid(String xmid,String tzdid,Integer page,Integer size,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        pd.put("xmid",xmid);
        pd.put("page",page);
        pd.put("size",size);
        List<XmSb> gsbList = new ArrayList<>();
        int totol = 0;
        try{
            if(!"".equals(tzdid) && tzdid != null ){
                pd.put("tzdid",tzdid);
                gsbList = sfkpService.queryGsbByXmids(pd);
                totol = sfkpService.queryAllGsbByXmids(pd).size();
            }else{
                gsbList = sfkpService.queryGsbByXmid(pd);
                totol = sfkpService.queryAllGsbByXmid(pd).size();
            }
            jo.put("gsbList",gsbList);
            jo.put("totol",totol);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 工商表内编辑
     * @return
     */
    @ApiOperation(value = "工商表内编辑")
    @RequestMapping(value = "gsbelxedit",method = RequestMethod.POST)
    @ResponseBody
    public String gsbelxedit(HttpServletRequest request){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        try{
            pd.put("id",request.getParameter("id"));
            pd.put("gcfysje",request.getParameter("gcfysje"));
            sfkpService.updateSbgcfBySbid(pd);
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * 工商表批量定价
     * @return
     */
    @ApiOperation(value = "工商表批量定价")
    @RequestMapping(value = "gspldjxg",method = RequestMethod.POST)
    @ResponseBody
    public String gspldjxg(String sbids,String zbkj,String gsysje,HttpServletRequest request){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        pd.put("sbids",sbids);
        pd.put("zbkj",zbkj);
        pd.put("gsysje",gsysje);
        try{
            sfkpService.gspldjxg(pd);
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 工商表缴费勾选确定
     * @return
     */
    @ApiOperation(value = "工商表缴费勾选确定")
    @RequestMapping(value = "gsjezj",method = RequestMethod.POST)
    @ResponseBody
    public String gsjezj(String sbids,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        pd.put("sbids",sbids);
        try{
            List<Map<String,Object>> list = sfkpService.gsjezj(pd);
            jo.put("list",list);
            jo.put("msg","success");
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 根据项目ID查询楼栋信息
     * @param xmid
     * @return
     */
    @ApiOperation("根据项目ID查询楼栋信息")
    @RequestMapping(value = "queryLdByxmid",method = RequestMethod.GET)
    @ResponseBody
    public String queryLdByxmid(String xmid){
        JSONObject jo = new JSONObject();
        try{
            List<SfkpldEntity> list = sfkpService.queryLdByxmid(xmid);
            jo.put("list",list);
            List<Map<String,Object>> varList = sfkpService.querymxByxmid(xmid);
            if(varList.isEmpty() == false){
                jo.put("zhs",varList.get(0).get("ZHS"));
                jo.put("yjs",varList.get(0).get("YJS"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }


    /**
     * 根据楼栋ID查询水表信息
     * @param xmid
     * @return
     */
    @ApiOperation("根据楼栋ID查询水表信息")
    @RequestMapping(value = "querySbByldid",method = RequestMethod.GET)
    @ResponseBody
    public String querySbByldid(String xmid,String ldid){
        JSONObject jo = new JSONObject();
        try{
            List<XmSb> list = sfkpService.querySbByldid(xmid,ldid);
            jo.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("勾选保存户表ID")
    @RequestMapping(value = "savejfhb",method = RequestMethod.POST)
    @ResponseBody
    public String savejfhb(String sbids,String ldid,String type){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        pd.put("ldid",ldid);
        try{
            sfkpService.deleteSfsbByldid(pd);
            if("1".equals(type)){

            }else{
                String[] sbidarr = sbids.split(",");
                for (int i=0;i<sbidarr.length;i++){
                    pd.put("sbid",sbidarr[i]);
                    pd.put("id",GuidUtils.getGuid());
                    sfkpService.insertSftzdcbsb(pd);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询选中户表
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("查询选中户表")
    @RequestMapping(value = "queryHb",method = RequestMethod.GET)
    @ResponseBody
    public String queryHb(String xmid,Integer page,Integer size,String hbld,String hbzbkj,String tzdid){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        pd.put("xmid",xmid);
        pd.put("page",page);
        pd.put("size",size);
        pd.put("hbld",hbld);
        pd.put("hbzbkj",hbzbkj);
        pd.put("tzdid",tzdid);
        try{
            List<Map<String,Object>> ldList = sfkpService.queryLd(pd);
            jo.put("ldList",ldList);
            List<XmSb> list = sfkpService.queryHb(pd);
            List<XmSb> allList = sfkpService.queryallHb(pd);
            int total = sfkpService.queryAllHb(pd).size();
            jo.put("list",list);
            jo.put("allList",allList);
            jo.put("total",total);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("保存户表工程费")
    @RequestMapping(value = "saveHbgcf",method = RequestMethod.POST)
    @ResponseBody
    public String saveHbgcf(String sbids){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        try{
            String[] arr = sbids.split(",");
            for (int i=0;i<arr.length;i++){
                String[] arr1 = arr[i].split(":");
                pd.put("id",arr1[0]);
                pd.put("gcfysje",arr1[1]);
                sfkpService.updateSbgcfBySbid(pd);
                sfkpService.updateJfcbgcfBySbid(pd);
            }
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 户表缴费勾选确定
     * @return
     */
    @ApiOperation(value = "户表缴费勾选确定")
    @RequestMapping(value = "hbjezj",method = RequestMethod.POST)
    @ResponseBody
    public String hbjezj(String sbids,String sfkpjmbid,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        pd.put("sbids",sbids);
        try{
            String[] sbarr = sfkpjmbid.split(",");
            Map<String,Object> jmpd = new HashMap<>();
            for (int y=0;y<sbarr.length;y++){
                String[] arr1 = sbarr[y].split(":");
                jmpd.put("id",arr1[0]);
                jmpd.put("gcfysje",arr1[1]);
                sfkpService.updateSbgcfBySbid(jmpd);
            }
            List<Map<String,Object>> list = sfkpService.hbjezj(pd);
            jo.put("list",list);
            jo.put("msg","success");
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("根据通知单编号查询通知单列表")
    @RequestMapping(value = "queryByTzdid",method = RequestMethod.GET)
    @ResponseBody
    public String queryByTzdid(String tzdid){
        JSONObject jo = new JSONObject();
        try{
            List<SfkpSftzdzbEntity> list = sfkpService.queryByTzdid(tzdid);
            List<Map<String,Object>> cbList = sfkpService.queryCbByTzdid(tzdid);
            jo.put("list",list);
            jo.put("cbList",cbList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("根据名称查询类型")
    @RequestMapping(value = "querylxByname",method = RequestMethod.GET)
    @ResponseBody
    public String querylxByname(String lx,String xmid){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        pd.put("lx",lx);
        pd.put("xmid",xmid);
        try{
            List<Map<String,Object>> list = sfkpService.querylxByname(pd);
            jo.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("根据名称查询类型")
    @RequestMapping(value = "querylxBynames",method = RequestMethod.GET)
    @ResponseBody
    public String querylxBynames(String lx,String xmid){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        pd.put("lx",lx);
        pd.put("xmid",xmid);
        try{
            List<Map<String,Object>> list = sfkpService.querylxBynames(pd);
            jo.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("查询票据类型")
    @RequestMapping(value = "querypjlx",method = RequestMethod.GET)
    @ResponseBody
    public String querypjlx(HttpServletRequest request,String type,String zbid){
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        JSONObject jo = new JSONObject();
        try{
            List<Map<String,Object>> list = sfkpService.querypjlx();
            jo.put("list",list);
            jo.put("qrr",user.getUsername());

            if("1".equals(type)){
                List<Map<String,Object>> sfzbList = sfkpService.querySfzbByid(zbid);
                jo.put("sfzbList",sfzbList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 上传凭证附件
     * @param multipartFiles
     * @param filename
     * @param request
     * @return
     */
    @RequestMapping(value ="importPz",method = RequestMethod.POST, consumes = { "multipart/mixed", "multipart/form-data" })
    @ResponseBody
    public String importPz(
            @RequestParam(value = "multipartFiles", required = false ) MultipartFile multipartFiles,
            @RequestParam(value = "zbid", required = false ) String zbid,
            @RequestParam(value = "filename", required = false ) String filename,
            @RequestParam(value = "xmbh", required = false ) String xmbh,
            HttpServletRequest request) {
        String id = "";
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        JSONObject jo = new JSONObject();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateString = formatter.format(currentTime);
        try {
            if (Objects.isNull(multipartFiles)) {
                logger.error("文件为空");
                jo.put("msg","文件为空，请重新上传");
                return jo.toString();
            }
            String fileDir = StringUtil.getBasePath()+"upload"+ File.separator+xmbh+File.separator;
            File dir = new File(fileDir);
            File file = null;

            file = new File(fileDir, filename.split("\\.")[0]+dateString+"."+filename.split("\\.")[filename.split("\\.").length-1]);
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
            Map<String,Object> pd = new HashMap<>();
            id = GuidUtils.getGuid();
            pd.put("id",id);//主键标识
            pd.put("fjlj","upload"+File.separator+xmbh+File.separator+filename.split("\\.")[0]+dateString+"."+filename.split("\\.")[filename.split("\\.").length-1]);//附件路径
            pd.put("fjhz",filenames[filenames.length-1]);//附件后缀
            pd.put("sftzdzbid",zbid);
            //根据附件标识查询附件类型名称
            pd.put("fjlx_yj","收费开票");//一级附件类型
            pd.put("fjlx_ej","确认凭证");//一级附件类型
            pd.put("fjmc",filename);//附件名称
            pd.put("scr",user.getUserid());//创建人
            sfkpService.importPz(pd);
        } catch (IOException e) {
            e.printStackTrace();
            jo.put("returncode", "999");
            jo.put("msg", "程序异常，请联系管理员");
        }
        jo.put("msg", "上传成功");
        return jo.toString();
    }

    @ApiOperation(value = "拍摄")
    @RequestMapping(value ="saveImageToFj",method = RequestMethod.POST)
    @ResponseBody
    public String saveImageToFj(String dirName,String fileName,String zbid,HttpServletRequest request){
        String id = "";
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        JSONObject jo = new JSONObject();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateString = formatter.format(currentTime);
        Map<String,Object> pd = new HashMap<>();
        try{
            id = GuidUtils.getGuid();
            pd.put("id",id);//主键标识
            pd.put("sftzdzbid",zbid);
            pd.put("fjlj","upload"+File.separator+dirName+File.separator+fileName);//附件路径
            pd.put("fjhz",fileName.substring(fileName.indexOf(".")+1) );//附件后缀
            pd.put("fjlx_yj","收费开票");//一级附件类型
            pd.put("fjlx_ej","确认凭证");//一级附件类型
            pd.put("fjmc",fileName);//附件名称
            pd.put("scr",user.getUserid());//创建人
            sfkpService.importPz(pd);
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("msg", "上传成功");
        return jo.toString();
    }

    @ApiOperation("短信模板")
    @RequestMapping(value = "finddxForm",method = RequestMethod.GET)
    @ResponseBody
    public String finddxForm(String xmid){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        pd.put("xmid",xmid);
        String gddh = "";
        try{
            String content = "";
            List<XmInstanceEntity> xmList = sfkpService.findXmByid(pd);
            Map<String,Object> dxmb = messageService.getDxMb("5");
            content = dxmb.get("DXMB").toString();
            content = content.replace("[项目名称]",xmList.get(0).getXmmc());
            content = content.replace("[单位名称]",xmList.get(0).getDwmc());
            content = content.replace("[业务类型]",xmList.get(0).getSqlxname());
            content = content.replace("[项目编号]",xmList.get(0).getXmbh());
            UserEntity userEntity = sfkpService.getUserByUserId(xmList.get(0).getXmfzr());
            try {
                gddh = userEntity.getGddh();
                if(gddh.equals("null") || gddh == null ){
                    gddh = "";
                }

            } catch (Exception e) {
               gddh = "";
            }
            content = content.replace("[项目经理]",userEntity.getUsername());
            content = content.replace("[项目经理联系方式]","("+gddh+")");
            jo.put("content",content);

            /*String content = "";
        String phone = "";
        Map<String,Object> user=null;
        try {
            SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user = messageService.getUserxxById(ryid);0
            phone = user.get("MOBILPHONE").toString();
            MessageController controller = new MessageController();
            Map<String,Object> dxmb = messageService.getDxMb("9");
            content = dxmb.get("DXMB").toString();
            content.replaceAll("[任务编号]",xckcRwzbEntity.getRwbh());
            content.replaceAll("[勘查时间]",sdft.format(xckcRwzbEntity.getYykcrq()));
            GetXckcxxView getXckcxxView = service.getXckcxxByView(xmid);
            Map<String,Object> xmjl= messageService.getUserxxById(getXckcxxView.getXmjl());
            content.replaceAll("[项目经理]",xmjl.get("USERNAME").toString());
            content.replaceAll("[联系方式]",xmjl.get("MOBILPHONE").toString());
            controller.sendMessage("002","1",null,phone,content);
            DxFsjlEntity dxFsjlEntity = new DxFsjlEntity(GuidUtils.getGuid(),"9",
                    ((UserEntity)session.getAttribute("user")).getUserid(),new Date(),"1","1",content,
                    user.get("username").toString(),phone,"");
            messageService.saveDxFsjl(dxFsjlEntity);
        } catch (Exception e) {//发送失败
            DxFsjlEntity dxFsjlEntity = new DxFsjlEntity(GuidUtils.getGuid(),"9",
                    ((UserEntity)session.getAttribute("user")).getUserid(),new Date(),"1","0",content,
                    user.get("username").toString(),phone,"");
            messageService.saveDxFsjl(dxFsjlEntity);
            e.printStackTrace();
        }*/
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }


    /**
     * 发送短信
     * @param xmid
     * @param jsr
     * @param jsrsjh
     * @param dxnr
     * @param request
     * @return
     */
    @ApiOperation(value = "发送短信")
    @RequestMapping(value = "fsdx", method = RequestMethod.POST)
    @ResponseBody
    public String  fsdx(String xmid,String jsr,String jsrsjh,String dxnr,HttpServletRequest request){
        String flag = "ok";
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");//获取当前登录人
        Map<String,Object> pd = new HashMap<>();
        pd.put("xmid",xmid);

        try {
            DxDfsEntity dxDfsEntity = new DxDfsEntity();
            dxDfsEntity.setId(GuidUtils.getGuid());//标识
            dxDfsEntity.setDxmbid("5");//短信模版标识
            dxDfsEntity.setFsr(userEntity.getUserid());//发送人
            dxDfsEntity.setFssj(new Date());//发送时间
            dxDfsEntity.setFscs("1");//发送次数
            dxDfsEntity.setFsnr(dxnr);//发送内容
            dxDfsEntity.setJsr(jsr);//接收人
            dxDfsEntity.setJshm(jsrsjh);//接收号码
            sfkpService.saveDxDfsEntity(dxDfsEntity);//保存
        } catch (Exception e) {
           flag = "error";
        }


        return  flag;
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
     * 缴费列表
     * 获取基本信息
     * @param xmid
     * @return
     */
    @ApiOperation("缴费列表（获取基本信息）")
    @RequestMapping(value = "getjbxx",method = RequestMethod.POST)
    @ResponseBody
    public String getjbxx(String xmid){
        JSONObject jo = new JSONObject();

        List<XmInstanceEntity> jbxxlist = sfkpService.getjbxx(xmid);

        jo.put("jbxxlist",jbxxlist );

        return jo.toString();
    }

    /**
     * 根据ID查询附件数量
     * @param request
     * @return
     */
    @ApiOperation("根据ID查询附件数量")
    @RequestMapping(value = "queryjfZllist",method = RequestMethod.GET)
    @ResponseBody
    public String queryjfZllist(String zbid,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        try{
            pd.put("zbid",zbid);
            List<Map<String,Object>> fjList = sfkpService.queryZjqtfjById(pd);
            jo.put("fjList",fjList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 根据ID删除附件
     * @param request
     * @return
     */
    @ApiOperation("根据ID删除附件")
    @RequestMapping(value = "deletejfFjByid",method = RequestMethod.GET)
    @ResponseBody
    public String deletejfFjByid(String fjId,HttpServletRequest request){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        try{
            pd.put("fjId",fjId);
            sfkpService.deleteFjByid(pd);
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation("根据项目ID获得项目地址")
    @RequestMapping(value = "getxmdz",method = RequestMethod.GET)
    @ResponseBody
    public String getxmdz(String xmid,String id){
        JSONObject jo = new JSONObject();
        try{
            List<XmInstance> list = sfkpService.getxmdz(xmid);
            List<Map<String,Object>> gs = sfkpService.getGsbTotalByXmid(xmid,id);
            jo.put("gs",gs);
            jo.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("查询收费开票参数")
    @RequestMapping(value = "querysfkp",method = RequestMethod.POST)
    @ResponseBody
    public String querysfkp(String xmid){
        JSONObject jo = new JSONObject();
        try{
            List<XmInstance> xmxxList = sfkpService.findXmByxmid(xmid);
            jo.put("xmxxList",xmxxList);
            List<Map<String,Object>> sftzdList = sfkpService.findSftzd(xmid);
            if(sftzdList.isEmpty() == false){
                jo.put("sftzd",sftzdList.get(0));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }


}
