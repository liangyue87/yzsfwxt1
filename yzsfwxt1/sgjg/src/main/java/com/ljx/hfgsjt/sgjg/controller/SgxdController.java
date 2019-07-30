package com.ljx.hfgsjt.sgjg.controller;

import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.Sjst.CodeModel;
import com.ljx.hfgsjt.entity.dto.Sjst.GdModel;
import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.Sjst.XmSb;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.htqd.HtQdzbEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.sgjg.GetXmlbSgEntity;
import com.ljx.hfgsjt.entity.sgjg.GetsgxtzbEntity;
import com.ljx.hfgsjt.entity.sgjg.SgxtRwJlfwEntity;
import com.ljx.hfgsjt.entity.sgjg.SgxtRwbEntity;
import com.ljx.hfgsjt.message.service.MessageService;
import com.ljx.hfgsjt.repository.CalculateHours;
import com.ljx.hfgsjt.sgjg.service.SgxdService;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import com.ljx.hfgsjt.util.utils.StringUtil;
import com.ljx.hfgsjt.util.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 施工下单控制层
 */
@Controller
@RequestMapping(value = "SgxdController")
@Api(description = "施工下单")
public class SgxdController {

    @Autowired
    private SgxdService sgxdService;

    @Autowired
    private MessageService messageService;


    CalculateHours cal = new CalculateHours();

    @ApiOperation(value = "条件查询施工下单列表")
    @RequestMapping(value = "getSgxdList",method = RequestMethod.GET)
    @ResponseBody
    public String getSgxdList(String xmbh,String xmmc,Integer page,Integer size,HttpServletRequest request){
        JSONObject jo = new JSONObject();
//        List<GetXmlbSgEntity> list = sgxdService.getSgxdList(xmbh,xmmc,page - 1, size);
//        jo.put("total", sgxdService.getTotal());
        Map<String,Object> pd = new HashMap<>();
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        pd.put("xmfzr",user.getUserid());
        pd.put("xmbh",xmbh);
        pd.put("xmmc",xmmc);
        pd.put("page",page);
        pd.put("size",size);
        List<GetXmlbSgEntity> list = sgxdService.getSgxdsList(pd);
        jo.put("list",list);
        int total = sgxdService.getAllSgxdList(pd).size();
        jo.put("total", total);
        return jo.toString();
    }

    @ApiOperation(value = "条件查询施工工程单列表")
    @RequestMapping(value = "getSggcdList",method = RequestMethod.GET)
    @ResponseBody
    public String getSggcdList(String xmid,Integer page,Integer size){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        try{
            pd.put("xmid",xmid);
            pd.put("page",page);
            pd.put("size",size);
            List<GetsgxtzbEntity> list = sgxdService.getSggcdList(pd);
            int total = sgxdService.getAllSggcdList(pd).size();
            jo.put("list",list);
            jo.put("total", total);
        }catch (Exception e){
            e.printStackTrace();
        }

        return jo.toString();
    }


    /**
     * 1：根据项目ID查询下单范围分类及占比
     * 2：根据项目ID查询下单范围类别具体信息
     * 3：根据项目ID查询物料信息
     * 4: 根据项目ID查询下单水表信息
     * @param xmid
     * @param type
     * @param request
     * @return
     */
    @RequestMapping(value = "queryGcxdByXmid",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("施工下单信息查询")
    public String queryGcxdByXmid(String xmid,String lctype,String sgdid,String type,Integer hbpage, Integer hbsize,
                                  Integer wbsize, Integer wbpage,Integer dbpage, Integer dbsize,
                                  Integer wlpage, Integer wlsize,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        pd.put("wbpage",wbpage);
        pd.put("wbsize",wbsize);
        pd.put("hbpage",hbpage);
        pd.put("hbsize",hbsize);
        pd.put("dbpage",dbpage);
        pd.put("dbsize",dbsize);
        pd.put("wlpage",wlpage);
        pd.put("wlsize",wlsize);
        try{
            pd.put("xmid",xmid);
            if("1".equals(type)){
                List<String> tableList = new ArrayList<>();
                tableList.add("xm_ty");
                tableList.add("xm_bf");
                tableList.add("xm_dxs");
                tableList.add("xm_ld");
                for (int i = 0;i<tableList.size();i++){
                    //项目范围
                    pd.put("tablename",tableList.get(i));
                    if("xm_ty".equals(tableList.get(i))){
                        List<Map<String,Object>> xmfwList = sgxdService.queryXdfwJtByXmid(pd);
                        jo.put("tyList",xmfwList);
                    }else if("xm_bf".equals(tableList.get(i))){
                        List<Map<String,Object>> xmfwList = sgxdService.queryXdfwJtByXmid(pd);
                        jo.put("bfList",xmfwList);
                    }else if("xm_dxs".equals(tableList.get(i))){
                        List<Map<String,Object>> xmfwList = sgxdService.queryXdfwJtByXmid(pd);
                        jo.put("dxsList",xmfwList);
                    }else if("xm_ld".equals(tableList.get(i))){
                        List<Map<String,Object>> ldList = sgxdService.queryLdJtByXmid(pd);
                        jo.put("ldList",ldList);
                    }
                }
            }else if("2".equals(type)){
                //物料
                List<Map<String,Object>> wlxxList = sgxdService.queryWlxxByXmid(pd);
                int wltotal = sgxdService.queryAllWlxxByXmid(pd).size();
                jo.put("wlxxList",wlxxList);
                jo.put("wltotal",wltotal);
            }else if("3".equals(type)){
                //五级计量表
                List<Map<String,Object>> wjjlbList = new ArrayList<>();
                //地表
                List<Map<String,Object>> dbList = new ArrayList<>();
                //户表
                List<Map<String,Object>> hbList = new ArrayList<>();
                pd.put("sgdid",sgdid);
                if("1".equals(lctype)){

                    wjjlbList = sgxdService.queryWjjlbByXmids(pd);
                    dbList = sgxdService.queryDbByXmids(pd);
                    hbList = sgxdService.queryHbByXmids(pd);
                }else{
                    wjjlbList = sgxdService.queryWjjlbByXmid(pd);
                    dbList = sgxdService.queryDbByXmid(pd);
                    hbList = sgxdService.queryHbByXmid(pd);
                }
                int wbtotal = sgxdService.queryAllWjjlbByXmid(pd).size();
                int dbtotal = sgxdService.queryAllDbByXmid(pd).size();
                int hbtotal = sgxdService.queryAllHbByXmid(pd).size();
                jo.put("wjjlbList",wjjlbList);
                jo.put("dbList",dbList);
                jo.put("hbList",hbList);
                jo.put("wbtotal",wbtotal);
                jo.put("dbtotal",dbtotal);
                jo.put("hbtotal",hbtotal);
            }else if("4".equals(type)){
                //五级计量表
                List<Map<String,Object>> wjjlbList = sgxdService.queryWjjlbByXmid(pd);
                int wbtotal = sgxdService.queryAllWjjlbByXmid(pd).size();
                jo.put("wjjlbList",wjjlbList);
                jo.put("wbtotal",wbtotal);
            }else if("5".equals(type)){
                //地表
                List<Map<String,Object>> dbList = sgxdService.queryDbByXmid(pd);
                int dbtotal = sgxdService.queryAllDbByXmid(pd).size();
                jo.put("dbtotal",dbtotal);
                jo.put("dbList",dbList);
            }else if("6".equals(type)){
                //户表
                List<Map<String,Object>> hbList = sgxdService.queryHbByXmid(pd);
                int hbtotal = sgxdService.queryAllHbByXmid(pd).size();
                jo.put("hbList",hbList);
                jo.put("hbtotal",hbtotal);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @RequestMapping(value = "HbSumit",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("下单户表提交")
    public String HbSumit(String[] hbids,String sgdid,String ldid,String hbid){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        pd.put("sgdid",sgdid);
        pd.put("ldid",ldid);
        try{
            if("1".equals(hbid)){
                sgxdService.hbEditdelete(pd);
            }else{
                sgxdService.hbEditdelete(pd);
                if(hbids.length != 0){//户表
                    for(int x=0;x<hbids.length;x++){
                        String bhhs = GuidUtils.getGuid();
                        pd.put("bhhs", bhhs);
                        pd.put("ldid", ldid);
                        pd.put("sbid",hbids[x]);
                        sgxdService.hbEditSubmit(pd);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 水表提交
     * @return
     */
    @RequestMapping(value = "Sbsubmit",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("下单水表提交")
    public String Sbsubmit(String[] hbids, String[] dbids,String[] wbids,String sgdid,
                           HttpServletResponse response){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        pd.put("sgdid",sgdid);
        try{
            if("".equals(sgdid) || sgdid == null){
                sgxdService.dbdelete(pd);
                sgxdService.wbdelete(pd);
            }else{
                sgxdService.wbEditdelete(pd);
                sgxdService.dbEditdelete(pd);
            }
            if(dbids.length != 0){//地表
                for(int y=0;y<dbids.length;y++){
                    String dbbs = GuidUtils.getGuid();
                    pd.put("dbbs", dbbs);
                    pd.put("dbid",dbids[y]);
                    if("".equals(sgdid) || sgdid == null){
                        sgxdService.dbSubmit(pd);
                    }else{
                        sgxdService.dbEditSubmit(pd);
                    }
                }
            }
            if(wbids.length != 0){//五级计量表
                for(int z=0;z<wbids.length;z++){
                    String wbhs = GuidUtils.getGuid();
                    pd.put("wbhs", wbhs);
                    pd.put("wbid",wbids[z]);
                    if("".equals(sgdid) || sgdid == null){
                        sgxdService.wbSubmit(pd);
                    }else{
                        sgxdService.wbEditSubmit(pd);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 下单水表关闭触发方法
     * @return
     */
    @RequestMapping(value = "xdsbClose",method = RequestMethod.GET)
    @ResponseBody
    public String xdsbClose(String sgdid,HttpServletResponse response,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        try{
            pd.put("sgdid",sgdid);
            List<Map<String,Object>> xmfwList = sgxdService.findXmfw(pd);
            jo.put("xmfwList",xmfwList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 施工下单保存
     * @param xmid
     * @param sgdid
     * @param xmjl
     * @param rwbh
     * @param type 1:新增施工下单;2:施工工程单提交给领导审核;3:领导审核通过;4:施工单领导退回;5:项目推进科分派人分派;6:推进科专人下单;
     *             7:退回施工单重新编辑提交
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "saveSgxd",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("施工下单保存")
    public String saveSgxd(String xmid,String type,String sgdid,String xmjl,String rwbh,String thyy,
                           HttpServletResponse response,HttpServletRequest request){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        UserEntity user = (UserEntity)request.getSession().getAttribute("user");
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdft1 = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> map = new HashMap<String, Object>();
        String ss="SGXD";
        map.put("v_stop_run",ss);
        sgxdService.texuChange(map);//获取任务编号
        String qRwbh = "SGXD"+map.get("p_result");
        if("".equals(rwbh) || rwbh == null){
            rwbh = qRwbh;
        }
        try{
            String id = "";
            String czr = user.getUserid();
            if("".equals(sgdid) || sgdid == null){
                id = GuidUtils.getGuid();
            }else{
                id = sgdid;
            }
            pd.put("id",id);
            pd.put("xmid",xmid);
            //根据项目ID查询所属科室
            String sqlx = "";
            List<Map<String,Object>> xmList = sgxdService.querySqlxByXmid(pd);
            if(xmList.isEmpty() == false){
                sqlx = String.valueOf(xmList.get(0).get("SQLX"));
            }

            //施工下单主表保存
            SgxtRwbEntity sgxtRwbEntity = new SgxtRwbEntity();
            sgxtRwbEntity.setId(id);
            sgxtRwbEntity.setXmid(xmid);
            sgxtRwbEntity.setRwbh(rwbh);
            sgxtRwbEntity.setFqr(czr);
            sgxtRwbEntity.setFqrq(new Date());
            sgxtRwbEntity.setGcnrTy(request.getParameter("srty"));
            sgxtRwbEntity.setGcnrBf(request.getParameter("srbf"));
            sgxtRwbEntity.setGcnrLg(request.getParameter("srlg"));
            sgxtRwbEntity.setGcnrDg(request.getParameter("srdg"));
            sgxtRwbEntity.setGcnrQt(request.getParameter("srqt"));
            sgxtRwbEntity.setBz(request.getParameter("srbz") == null ? "" : request.getParameter("srbz"));
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
            String date = request.getParameter("jhjgsj");
            if("".equals(date) || date == null){

            }else {
                sgxtRwbEntity.setJhjgsj(s.parse(date));
            }
            sgxtRwbEntity.setZt("0");
            sgxtRwbEntity.setShzt("0");

            if("1".equals(type) || "2".equals(type) || "7".equals(type) || "8".equals(type)){
                sgxdService.saveSgxtRwb(sgxtRwbEntity);
            }else if("3".equals(type)){
                pd.put("shzt","1");
                pd.put("shr",user.getUserid());
                sgxdService.updateShzt(pd);
            }else if("4".equals(type)){
                pd.put("shzt","2");
                pd.put("thyy",thyy);
                pd.put("shr",user.getUserid());
                sgxdService.updateShth(pd);
            }else if("5".equals(type)){
                pd.put("xmgzry",request.getParameter("name"));
                sgxdService.updateZmgzry(pd);
            }else if("6".equals(type)){
                pd.put("zt","1");
                sgxdService.updateRwzt(pd);
            }


            //施工下单监理范围表
            SgxtRwJlfwEntity sgxtRwJlfwEntity = new SgxtRwJlfwEntity();
            List<Map<String,Object>> jlfwList = sgxdService.queryJlfwByXmid(pd);
            if(jlfwList.isEmpty() == false){
                sgxtRwJlfwEntity.setId(String.valueOf(jlfwList.get(0).get("ID")));
            }else{
                sgxtRwJlfwEntity.setId(GuidUtils.getGuid());
            }
            sgxtRwJlfwEntity.setSgxtrwid(id);
            String tyjl = request.getParameter("ty");
            if(!"".equals(tyjl) && !"false".equals(tyjl)){
                sgxtRwJlfwEntity.setTyjl("1");
            }
            String bfjl = request.getParameter("bf");
            if(!"".equals(bfjl) && !"false".equals(bfjl)){
                sgxtRwJlfwEntity.setBfjl("1");
            }
            String lgjl = request.getParameter("lg");
            if(!"".equals(lgjl) && !"false".equals(lgjl)){
                sgxtRwJlfwEntity.setLgjl("1");
            }
            String dgjl = request.getParameter("dg");
            if(!"".equals(dgjl) && !"false".equals(dgjl)){
                sgxtRwJlfwEntity.setDgjl("1");
            }
            String szgwjl = request.getParameter("szgw");
            if(!"".equals(szgwjl) && !"false".equals(szgwjl)){
                sgxtRwJlfwEntity.setSzgwjl("1");
            }
            String jlsbjl = request.getParameter("jlsb");
            if(!"".equals(jlsbjl) && !"false".equals(jlsbjl)){
                sgxtRwJlfwEntity.setJlsbjl("1");
            }
            sgxtRwJlfwEntity.setQt(request.getParameter("desc"));
            sgxdService.saveSgxtRwjl(sgxtRwJlfwEntity);

            //施工下单水表保存
//            sgxdService.saveSgxtRwsb(pd);

            WfTaskEntity task = new WfTaskEntity();
            String procInstId = "";
            String pdefId = "";
            String rwblr = "";
            WfRuInstEntity instEntity = new WfRuInstEntity();
            WfRuInstContentEntity wfRuInstContentEntity = new WfRuInstContentEntity();
            if("1".equals(type) || "8".equals(type)){

            }else{
                if("2".equals(type)) {//流程起点

                    procInstId = GuidUtils.getGuid();
                    pdefId = "70001";

                    //启动流程实例
                    instEntity.setProcInstId(procInstId);
                    instEntity.setPdefId(pdefId);
                    instEntity.setParammap(rwbh);
                    instEntity.setStartdt(new Date());
                    instEntity.setProcstatus(1);
                    instEntity.setStartuser(czr);
                    sgxdService.saveWfRuInst(instEntity);

                    //将项目信息存入流程实例使用内容表(WF_RU_INST_CONTENT)
                    wfRuInstContentEntity.setContentid(GuidUtils.getGuid());
                    wfRuInstContentEntity.setProcInstId(procInstId);
                    wfRuInstContentEntity.setTableName("XM_INSTANCE");
                    wfRuInstContentEntity.setContentvalue(xmid);
                    wfRuInstContentEntity.setContenttype("SGXT_RWB");
                    wfRuInstContentEntity.setContentname(id);
                    sgxdService.saveWfRuInstContent(wfRuInstContentEntity);

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

                }else{
                    String lcscId = "70001";
                    List<Map<String,Object>> varList = sgxdService.queryPdefId(id,lcscId);
                    procInstId = String.valueOf(varList.get(0).get("PROC_INST_ID"));
                    pdefId = String.valueOf(varList.get(0).get("C_ACTIVITYID"));
                    if("6".equals(type)){
                        //结束流程实例
                        instEntity = sgxdService.findByprocInstId(procInstId);
                        WfHiInstEntity wfHiInstEntity = new WfHiInstEntity(instEntity);
                        wfHiInstEntity.setEndtime(new Date());
                        //将当前流程实例存入流程历史表
                        sgxdService.savewfHiInst(wfHiInstEntity);
                        //删除当前流程实例
                        sgxdService.deleteInst(procInstId);

                        //将当前流程实例内容表存入流程实例历史内容表
                        wfRuInstContentEntity = sgxdService.findByProcInstIdAndContentname(procInstId,id);
                        WfHiInstContentEntity wfHiInstContentEntity = new WfHiInstContentEntity(wfRuInstContentEntity);
                        sgxdService.saveWfHiInstContent(wfHiInstContentEntity);
                        //删除当前流程实例内容
                        sgxdService.deleteInstContent(procInstId,id);

                    }else{

                    }
                    //将当前task表里数据存入历史任务表中
                    task = sgxdService.findBycActivityidAndProcInstId(pdefId,procInstId);
                    task.setIscurrent("0");//已完成
                    task.setIscheck("1");//已签收
                    task.setJssj(new Date());//结束时间
                    WfTaskHistoryEntity historyEntity = new WfTaskHistoryEntity(task);
                    sgxdService.saveWfTaskHistory(historyEntity);

                    //删除当前task表里数据
                    sgxdService.deleteTask(pdefId,procInstId);
                }
                if("6".equals(type)){

                }else{
                    //下一节点 创建任务
                    String direction = "0";//流程方向 前进/回退
                    if("4".equals(type)){
                        direction = "1";//退回
                    }else if("5".equals(type)){
                        direction = "3";//结束
                    }
                    WfTaskEntity newtask = new WfTaskEntity();
                    String thprefId = "";
                    List<WfReNodeEntity> list = sgxdService.findByActivityidAndDirection(pdefId,direction);
                    for(WfReNodeEntity wf : list){
                        WfDfActivityEntity ac = sgxdService.findByActivityid(wf.getActivityid());
                        newtask.setTaskid(GuidUtils.getGuid());
                        newtask.setPtaskid("0");//前置任务为空
                        newtask.setProcInstId(procInstId);//流程实例ID
                        newtask.setCActivityid(wf.getNextnode());//环节编号
                        thprefId = wf.getNextnode();
                        if("1".equals(direction)){
                            newtask.setIscurrent("2");//状态标志：0已做1待做2退回
                        }else{
                            newtask.setIscurrent("1");//状态标志：0已做1待做2退回
                        }
                        Date qssj = new Date();
//                        newtask.setCheck_sx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(ac.getCheckMinute())));
//                        newtask.setJssx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(ac.getYellowFinish())));
//                        newtask.setJssx_yl(cal.getEndTime(sdft.format(qssj),Float.parseFloat(ac.getFinishMinute())+Float.parseFloat(ac.getYellowFinish())));
                        try {
                            if (ac.getCheckLimited() == null || ac.getCheckLimited().equals("")
                                    || ac.getCheckLimited().equals("0")) {
                                newtask.setCheck_sx(sdft.parse("2099-12-31 23:59:59"));
                            } else {
                                newtask.setCheck_sx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getCheckMinute())));
                            }
                            if (ac.getFinishLimited() == null || ac.getFinishLimited().equals("")
                                    || ac.getFinishLimited().equals("0")) {
                                newtask.setJssx(sdft.parse("2099-12-31 23:59:59"));
                                newtask.setJssx_yl(sdft.parse("2099-12-31 23:59:59"));
                            } else {
                                newtask.setJssx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getYellowFinish())));
                                newtask.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getFinishMinute()) + Float.parseFloat(ac.getYellowFinish())));
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        newtask.setQssj(qssj);
                        newtask.setCzr(czr);//操作人
                        newtask.setIscheck("0");//已经签收：0未签收1已签收
                        newtask.setHasnew("2");//无子流程
                        newtask.setRefkey("SGXT_RWB");//关联实体Key
                        newtask.setRefid(id);//关联实体ID
                        String roleId = "";
                        if("5".equals(type)){
                            rwblr = request.getParameter("name");
                        }else if("2".equals(type)){
                            if("10".equals(sqlx)){
                                roleId = "3c46edde25904493a3d6dbc0e3563619";
                            }else if("20".equals(sqlx)){
                                roleId = "08357a8b7dd24a2d8ffb369bd3fffa6e";
                            }else{
                                roleId = "29c66fe853df4b0f9b7ab7fbd92b2108";
                            }
                            rwblr = sgxdService.getUserIdByroleId(roleId);
                        }else if("4".equals(type)){
                            rwblr = czr;
                        }else if("7".equals(type)){
                            if("10".equals(sqlx)){
                                roleId = "3c46edde25904493a3d6dbc0e3563619";
                            }else if("20".equals(sqlx)){
                                roleId = "08357a8b7dd24a2d8ffb369bd3fffa6e";
                            }else{
                                roleId = "29c66fe853df4b0f9b7ab7fbd92b2108";
                            }
                            rwblr = sgxdService.getUserIdByroleId(roleId);
                        }else{
                            rwblr = sgxdService.getUserIdByActivityId(wf.getNextnode());
                        }
                        newtask.setRwblr(rwblr);//任务办理人
                        sgxdService.saveWfTask(newtask);

                        //修改流程实例表中的流程定义ID
                        sgxdService.updateWfruInst(procInstId,wf.getNextnode());

                        if("4".equals(type)){

                        }else{
                            saveDxDfs(newtask.getCActivityid(),xmid,czr,newtask.getRwblr(),rwbh,newtask.getTaskid());
                        }
                    }
                    if("4".equals(type)){

                        //将当前task表里数据存入历史任务表中
                        task = sgxdService.findBycActivityidAndProcInstId(thprefId,procInstId);
                        task.setIscurrent("0");//已完成
                        task.setIscheck("1");//已签收
                        task.setJssj(new Date());//结束时间
                        WfTaskHistoryEntity historyEntity = new WfTaskHistoryEntity(task);
                        sgxdService.saveWfTaskHistory(historyEntity);

                        //删除当前task表里数据
                        sgxdService.deleteTask(thprefId,procInstId);

                        String thdirection = "0";//流程方向 前进/回退
                        WfTaskEntity thtask = new WfTaskEntity();
                        List<WfReNodeEntity> thlist = sgxdService.findByActivityidAndDirection(thprefId,thdirection);
                        for(WfReNodeEntity thwf : thlist){
                            WfDfActivityEntity ac = sgxdService.findByActivityid(thwf.getActivityid());
                            thtask.setTaskid(GuidUtils.getGuid());
                            thtask.setPtaskid("0");//前置任务为空
                            thtask.setProcInstId(procInstId);//流程实例ID
                            thtask.setCActivityid(thwf.getNextnode());//环节编号
                            if("1".equals(thdirection)){
                                thtask.setIscurrent("2");//状态标志：0已做1待做2退回
                            }else{
                                thtask.setIscurrent("1");//状态标志：0已做1待做2退回
                            }
                            Date qssj = new Date();
                            try {
                                if (ac.getCheckLimited() == null || ac.getCheckLimited().equals("")
                                        || ac.getCheckLimited().equals("0")) {
                                    thtask.setCheck_sx(sdft.parse("2099-12-31 23:59:59"));
                                } else {
                                    thtask.setCheck_sx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getCheckMinute())));
                                }
                                if (ac.getFinishLimited() == null || ac.getFinishLimited().equals("")
                                        || ac.getFinishLimited().equals("0")) {
                                    thtask.setJssx(sdft.parse("2099-12-31 23:59:59"));
                                    thtask.setJssx_yl(sdft.parse("2099-12-31 23:59:59"));
                                } else {
                                    thtask.setJssx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getYellowFinish())));
                                    thtask.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getFinishMinute()) + Float.parseFloat(ac.getYellowFinish())));
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            thtask.setQssj(qssj);
                            thtask.setCzr(czr);//操作人
                            thtask.setIscheck("0");//已经签收：0未签收1已签收
                            thtask.setHasnew("2");//无子流程
                            thtask.setRefkey("SGXT_RWB");//关联实体Key
                            thtask.setRefid(id);//关联实体ID
                            String actId = "70001";
                            List<String> rList = sgxdService.queryRwblr(actId,id,procInstId);
                            if(rList.isEmpty() == false){
                                thtask.setRwblr(rList.get(0));//任务办理人
                            }
                            sgxdService.saveWfTask(thtask);

                            //修改流程实例表中的流程定义ID
                            sgxdService.updateWfruInst(procInstId,thwf.getNextnode());

                            saveDxDfs(thtask.getCActivityid(),xmid,czr,thtask.getRwblr(),rwbh,thtask.getTaskid());
                        }
                    }
                }
            }
            if("1".equals(type)){
                flag = id;
            }else{
                flag = "true";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 1、根据项目ID查询下单范围
     * 2、根据项目ID查询监理范围
     * @param xmid
     * @param type
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "showEditForm",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("查询编辑数据")
    public String showEditForm(String xmid,String sgdid,String type,HttpServletRequest request,HttpServletResponse response){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        pd.put("xmid",xmid);
        pd.put("sgdid",sgdid);
        try{
            if("1".equals(type)){//施工下单
                List<Map<String,Object>> sgxdList =  sgxdService.querySgxdByXmid(pd);
                jo.put("sgxdList",sgxdList);
                pd.put("sgdid",sgdid);
                List<String> sbbs  = sgxdService.queryGxsbidBySgdId(pd);
                jo.put("sbbs", StringUtils.join(sbbs.toArray(), ","));
            }else if("2".equals(type)){//监理范围
                pd.put("id",sgdid);
                List<Map<String,Object>> jlfwList =  sgxdService.queryJlfwByXmid(pd);
                jo.put("jlfwList",jlfwList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /***
     * 获取指派人员名单
     * @return
     */
    @RequestMapping(value = "getZpry",method = RequestMethod.GET)
    @ResponseBody
    private String getZpry(String activityId,HttpServletResponse response,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        activityId = "70005";
        pd.put("activityId",activityId);
        List<Map<String,Object>> zpry = sgxdService.getZpry(pd);
        jo.put("zpry",zpry);
        return jo.toString();
    }

    @ApiOperation("根据ID查询施工单信息")
    @RequestMapping(value = "getSgrwdById",method = RequestMethod.GET)
    @ResponseBody
    public String getSgrwdById(String sgdid){
        JSONObject jo = new JSONObject();
        try{
            List<Map<String,Object>> list = sgxdService.getSgrwdById(sgdid);
            jo.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("根据楼栋ID查询水表信息")
    @RequestMapping(value = "querySbByLdid",method = RequestMethod.POST)
    @ResponseBody
    public String querySbByLdid(String ldid){
        JSONObject jo = new JSONObject();
        try{
            List<Map<String,Object>> list = sgxdService.querySbByLdid(ldid);
            jo.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("根据项目ID查询项目基本信息")
    @RequestMapping(value = "queryxmxxByid",method = RequestMethod.GET)
    @ResponseBody
    public String queryxmxxByid(String xmid){
        JSONObject jo = new JSONObject();
        try{
            List<Map<String,Object>> list = sgxdService.queryxmxxByid(xmid);
            jo.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("获取施工打印信息")
    @RequestMapping(value = "getDycontent",method = RequestMethod.GET)
    @ResponseBody
    public String getDycontent(String xmid,String xmjl){
        JSONObject jo = new JSONObject();
        try{
            List<XmInstance> list = sgxdService.getXmxxByid(xmid);
            jo.put("list",list);
            List<HtQdzbEntity> htList = sgxdService.getHtxx(xmid);
            jo.put("htList",htList);
            List<UserEntity> userList = sgxdService.getUserxx(xmjl);
            jo.put("userList",userList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation(value = "初始化楼栋户数")
    @RequestMapping(value = "initLdhb",method = RequestMethod.GET)
    @ResponseBody
    public String  initLdhb(String xmid){
        List<CodeModel> list = new ArrayList<CodeModel>();
        JSONObject jo = new JSONObject();
        try {
            list = sgxdService.initLdhb(xmid);
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        return jo.toString();
    }

    @ApiOperation(value = "初始化地表信息")
    @RequestMapping(value = "initDbpzTable",method = RequestMethod.GET)
    @ResponseBody
    public String  initDbpzTable(String xmId,String sgdid,String ldid,String type){
        JSONObject jo = new JSONObject();
        List<XmSb>  list = new ArrayList<XmSb>();
        try {
            if(ldid == null || "".equals(ldid)){

            }else{
                if("1".equals(type)){
                    list = sgxdService.initDbpzTables(xmId,sgdid,ldid);
                }else{
                    list = sgxdService.initDbpzTable(xmId,sgdid,ldid);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        jo.put("ldid",ldid);
        return jo.toString();
    }

    @ApiOperation(value = "获取施工单ID")
    @RequestMapping(value = "findsgdid",method = RequestMethod.GET)
    @ResponseBody
    public String findsgdid(){
        JSONObject jo = new JSONObject();
        jo.put("sgdid",GuidUtils.getGuid());
        return jo.toString();
    }

    @ApiOperation(value = "删除施工单ID")
    @RequestMapping(value = "delSgdid",method = RequestMethod.POST)
    @ResponseBody
    public String delSgdid(String xmid){
        JSONObject jo = new JSONObject();
        List<String> sgdidList = sgxdService.querySgdidByxmid(xmid);
        if(sgdidList.isEmpty() == false){
            for (int i=0;i<sgdidList.size();i++){
                String sgdid = sgdidList.get(i);
                sgxdService.deleteSgsb(sgdid);
            }
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

    @RequestMapping(value = "updateCheckTask", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更改任务签收状态")
    public String updateCheckTask(String taskId, HttpServletRequest request){
        HttpSession session = request.getSession();
        String qsr = ((UserEntity) session.getAttribute("user")).getUserid();
        String flag = "false";
        try{
            WfTaskEntity task = sgxdService.findByTaskid(taskId);
            if (task.getIscheck().equals("0")) {
                task.setIscheck("1");
                task.setCheck_time(new Date());
                task.setCheckUser(qsr);//签收人
                sgxdService.saveWfTask(task);
            }
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation(value = "管网查询")
    @RequestMapping(value = "initGWTable_sgxd",method = RequestMethod.GET)
    @ResponseBody
    public String  initGWTable_sgxd(String xmid,String sgdid){
        List<GdModel> gdList = new ArrayList<GdModel>();
        gdList = sgxdService.getGdList(xmid,sgdid);
        JSONObject jo = new JSONObject();
        jo.put("row",gdList);
        return jo.toString();
    }

    /**
     * 管网提交
     * @return
     */
    @RequestMapping(value = "gwsubmit",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("管网提交")
    public boolean gwsubmit(String[] gwids, String sgdid,HttpServletResponse response){
        boolean flag=false;
        Map<String,Object> pd = new HashMap<>();
        pd.put("sgdid",sgdid);
        try{
            if(!StringUtil.isEmpty(sgdid)){
                sgxdService.gwdelete(pd);
                if(gwids.length > 0){
                    for(int i = 0;i<gwids.length;i++){
                        String id = GuidUtils.getGuid();
                        pd.put("id", id);
                        pd.put("gwid",gwids[i]);
                        sgxdService.insertGw(pd);
                    }
                    flag=true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

}
