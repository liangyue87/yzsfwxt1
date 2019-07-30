package com.ljx.hfgsjt.zhys.controller;

import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.Sjst.XzqyEntity;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.zhys.XmUserEntity;
import com.ljx.hfgsjt.entity.dto.zhys.ZhysRwcbEntity;
import com.ljx.hfgsjt.entity.dto.zhys.ZhysRwcbFjEntity;
import com.ljx.hfgsjt.entity.dto.zhys.ZhysRwzbEntity;
import com.ljx.hfgsjt.repository.CalculateHours;
import com.ljx.hfgsjt.sgjg.service.SgxdService;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import com.ljx.hfgsjt.util.utils.StringUtil;
import com.ljx.hfgsjt.xkybz.service.XkylxService;
import com.ljx.hfgsjt.zhys.service.SqzhysService;
import com.ljx.hfgsjt.zhys.service.XdzhysdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "XdzhysdController")
@Api(description = "下达综合验收单")
public class XdzhysdController {

    @Autowired
    private SqzhysService sqzhysService;

    @Autowired
    private XdzhysdService xdzhysdService;

    @Autowired
    private SgxdService sgxdService;

    @Autowired
    private XkylxService xkylxService;

    private Logger logger = LoggerFactory.getLogger(XdzhysdController.class);

    CalculateHours cal = new CalculateHours();

    @ApiOperation(value = "下达综合验收单列表数据")
    @RequestMapping(value = "getXdzhysdlb", method = RequestMethod.GET)
    public String getXdzhysdlb(HttpServletRequest request,Integer page,Integer size, String xmmc, String xmbh, String lxr, String status, String kssj, String jssj ) {
        JSONObject jo = new JSONObject();
        List<XmInstance> list = new ArrayList<XmInstance>();
        Map<String, Object> map = new HashedMap();
        map.put("page", (page-1)*size);
        map.put("pageSize", (page-1)*size + size);
        map.put("xmmc", xmmc);
        map.put("xmbh", xmbh);
        map.put("lxr", lxr);
        map.put("status", status);
        map.put("kssj", kssj);
        map.put("jssj", jssj);
        int total = 0;
        try {
            total = xdzhysdService.selectXdzhysdCount(map);
            if (total > 0) {
                list = xdzhysdService.selectXdzhysdList(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jo.put("total", total);
        jo.put("list", list);
        return jo.toString();

    }

    @ApiOperation(value = "获取部门信息")
    @RequestMapping(value = "getBmxx", method = RequestMethod.POST)
    public String getBmxx(HttpServletRequest request ,String xmid,String bmbm_qs) {
        JSONObject jo = new JSONObject();
        XzqyEntity xzqyEntity =xdzhysdService.getQsmc(bmbm_qs);//区所名称
        jo.put("qsmc", xzqyEntity.getMc());
        XmUserEntity userEntity=xdzhysdService.getQs(xzqyEntity.getBh());//区所负责人
        jo.put("gsslxr", userEntity.getUsername());
        jo.put("gsslxrsjh", userEntity.getMobilephone());
        jo.put("gssid", userEntity.getUserid());
        XmUserEntity userEntity_jl=xdzhysdService.getUser("监理单位");//监理负责人
        jo.put("jldwlxr", userEntity_jl.getUsername());
        jo.put("jldwlxrsjh", userEntity_jl.getMobilephone());
        jo.put("jldwid", userEntity_jl.getUserid());
        XmUserEntity userEntity_xxgh=xdzhysdService.getUser("信息规划部");//信息规划部
        jo.put("xxghlxr", userEntity_xxgh.getUsername());
        jo.put("xxghlxrsjh", userEntity_xxgh.getMobilephone());
        jo.put("xxghid", userEntity_xxgh.getUserid());
        XmUserEntity userEntity_sgbm=xdzhysdService.getUser("施工单位分派人");//施工部门
        jo.put("sgbmlxr", userEntity_sgbm.getUsername());
        jo.put("sgbmlxrsjh", userEntity_sgbm.getMobilephone());
        jo.put("sgbmid", userEntity_sgbm.getUserid());
        XmUserEntity userEntity_gcgl=xdzhysdService.getUser("工程管理部");//工程管理
        jo.put("gcgllxr", userEntity_gcgl.getUsername());
        jo.put("gcgllxrsjh", userEntity_gcgl.getMobilephone());
        jo.put("gcglid", userEntity_gcgl.getUserid());
        return jo.toString();
    }


    @ApiOperation(value = "下达综合验收单列表数据")
    @RequestMapping(value = "selectRwzbid", method = RequestMethod.GET)
    public String selectRwzbid(HttpServletRequest request,String xmid ) {
        JSONObject jo = new JSONObject();
        XmInstance list= xdzhysdService.selectRwzbid(xmid);
        jo.put("sqdid",list.getSqzbid() );
        jo.put("sqlx",list.getSqlx() );
        jo.put("lxr",list.getLxr() );
        jo.put("lxrsjh",list.getLxrsjh() );
       // jo.put("xzqybh",list.getXzqybh() );
        jo.put("bmbmQs",list.getBmbmQs() );
        jo.put("sqbh",list.getSqbh());
        return jo.toString();
    }


    @ApiOperation(value = "获取其他部门资料")
    @RequestMapping(value = "Bmdownload", method = RequestMethod.POST)
    public HttpServletResponse Bmdownload(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
           // String basePath = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();
            // path是指欲下载的文件的路径。
            String fileDir= StringUtil.getBasePath();
            path = fileDir + path;
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

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


    @ApiOperation(value = "各部门资料列表")
    @RequestMapping(value = "toDetial", method = RequestMethod.GET)
    public String toDetial(HttpServletRequest request,String xmid,String zhyscbid ,Integer page,Integer size) {
        JSONObject jo = new JSONObject();
        List<ZhysRwcbFjEntity> list = new ArrayList<ZhysRwcbFjEntity>();
        Map<String, Object> map = new HashedMap();
        map.put("page", (page-1)*size);
        map.put("pageSize", (page-1)*size + size);
        map.put("zhyscbid", zhyscbid);
        int total = 0;
        try {
            total = xdzhysdService.selectBmzl(zhyscbid);
            if (total > 0) {
                list = xdzhysdService.selectBmzllist(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jo.put("total", total);
        jo.put("list", list);
        return jo.toString();
    }


    @ApiOperation(value = "发起验收")
    @RequestMapping(value = "doFqys", method = RequestMethod.GET)
    @ResponseBody
    public String doFqys(String xmid,String type,String sqdid,String bmmc,HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<String, Object>();
        String ss="ZHYS";
        map.put("v_stop_run",ss);
        sqzhysService.texuChange(map);
        String flag = "false";
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        String czr = user.getUserid();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String,Object> pd = new HashMap<>();
        pd.put("xmid",xmid);
        int x = 0;
        List<Map<String,Object>> bList = new ArrayList<>();
        String end = "0";
        try{
            if("1".equals(type)){
                String id = GuidUtils.getGuid();
                //综合验收任务主表保存
                ZhysRwzbEntity zhysRwzbEntity = new ZhysRwzbEntity();
                zhysRwzbEntity.setId(id);
                zhysRwzbEntity.setXmid(xmid);
                zhysRwzbEntity.setFqr(czr);
                zhysRwzbEntity.setFqrq(new Date());
                zhysRwzbEntity.setZt("0");
                zhysRwzbEntity.setYjyssj(sdf.parse(request.getParameter("yjyssj")));
                zhysRwzbEntity.setZhyssqid(sqdid);
                xdzhysdService.saveZhysRwzb(zhysRwzbEntity);

                //综合验收任务从表保存
                List<String> bmList = new ArrayList<>();
                bmList.add("监理单位");
                bmList.add(request.getParameter("gss"));
                bmList.add("信息规划部");
                bmList.add("施工部门");
                bmList.add("工程管理部");
                for(int i =0;i<bmList.size();i++){
                    String isCheck = "";
                    String ysbm = "";
                    String lxr = "";
                    String lxrsjh = "";
                    ZhysRwcbEntity zhysRwcbEntity = new ZhysRwcbEntity();
                    if("监理单位".equals(bmList.get(i))){
                        isCheck = request.getParameter("c_jldw");
                        ysbm = "监理单位";
                        lxr = request.getParameter("jldwid");
//                        lxrsjh = request.getParameter("jldwlxrsjh");
                    }else if(request.getParameter("gss").equals(bmList.get(i))){//供水所
                        isCheck = "true";
                        ysbm = request.getParameter("gss");
                        lxr = request.getParameter("gssid");
//                        lxrsjh = request.getParameter("gsslxrsjh");
                    }else if("信息规划部".equals(bmList.get(i))){
                        isCheck = request.getParameter("c_xxgh");
                        ysbm = "信息规划部";
                        lxr  = request.getParameter("xxghid");
//                        lxrsjh = request.getParameter("xxghlxrsjh");
                    }else if("施工部门".equals(bmList.get(i))){
                        isCheck = request.getParameter("c_sgb");
                        ysbm = "施工部门";
                        lxr  = request.getParameter("sgbmid");
//                        lxrsjh = request.getParameter("sgbmlxrsjh");
                    }else if("工程管理部".equals(bmList.get(i))){
                        isCheck = request.getParameter("c_gcgl");
                        ysbm = "工程管理部";
                        lxr = request.getParameter("gcglid");
//                        lxrsjh = request.getParameter("gcgllxrsjh");
                    }
                    if("true".equals(isCheck)){
                        x++;
                        Map<String,Object> map2 = new HashMap<>();
                        map2.put("bm",ysbm);
                        map2.put("blr",lxr);
                        bList.add(map2);
                        zhysRwcbEntity.setId(GuidUtils.getGuid());
                        zhysRwcbEntity.setZhysrwzbid(id);
                        zhysRwcbEntity.setYsbm(ysbm);
                        zhysRwcbEntity.setZpr(lxr);
                        zhysRwcbEntity.setZpsj(new Date());
                        xdzhysdService.saveZhysRwcb(zhysRwcbEntity);
                    }
                }
            }else if("2".equals(type)){
                pd.put("sqdid",sqdid);
                pd.put("bmmc",bmmc);
                pd.put("name",request.getParameter("name"));
                xdzhysdService.updateCbBmcyry(pd);
            }else if("3".equals(type)){
                pd.put("sqdid",sqdid);
                pd.put("bmmc",bmmc);
                pd.put("bmyj",request.getParameter("bmyj"));
                String yssj = request.getParameter("yssj");
                if(yssj == null){
                    pd.put("yssj","");
                }else{
                    pd.put("yssj",sdf.parse(yssj));
                }
                xdzhysdService.updateCbBmfkyj(pd);
            }else if("4".equals(type)){
                pd.put("sqdid",sqdid);
                pd.put("xmjlyj",request.getParameter("xmjlyj"));
                pd.put("xmjlyssj",sdf.parse(request.getParameter("xmjlyssj")));
                xdzhysdService.updateZbXmjlfkyj(pd);
            }else if("5".equals(type)){
                pd.put("sqdid",sqdid);
                pd.put("xmjlyj",request.getParameter("xmjlyj"));
                pd.put("xmjlyssj",sdf.parse(request.getParameter("xmjlyssj")));
                xdzhysdService.updateZbXmjlfkyjCh(pd);
            }

            String lcscId = "80001";
            String rwblr = "";
            WfTaskEntity task = new WfTaskEntity();
            WfRuInstEntity instEntity = new WfRuInstEntity();
            WfRuInstContentEntity wfRuInstContentEntity = new WfRuInstContentEntity();
            List<Map<String,Object>> varList = new ArrayList<>();
            if("2".equals(type) || "3".equals(type)){
                varList = sgxdService.queryPdefIdAndBmmc(sqdid+","+bmmc);
            }else{
                varList = sgxdService.queryPdefId(sqdid,lcscId);
            }
            String procInstId = String.valueOf(varList.get(0).get("PROC_INST_ID"));
            String pdefId = String.valueOf(varList.get(0).get("C_ACTIVITYID"));

            if("4".equals(type)){
                //结束流程实例
                instEntity = sgxdService.findByprocInstId(procInstId);
                WfHiInstEntity wfHiInstEntity = new WfHiInstEntity(instEntity);
                wfHiInstEntity.setEndtime(new Date());
                //将当前流程实例存入流程历史表
                sgxdService.savewfHiInst(wfHiInstEntity);
                //删除当前流程实例
                sgxdService.deleteInst(procInstId);

                //将当前流程实例内容表存入流程实例历史内容表
                wfRuInstContentEntity = sgxdService.findByProcInstIdAndContentname(procInstId,sqdid);
                WfHiInstContentEntity wfHiInstContentEntity = new WfHiInstContentEntity(wfRuInstContentEntity);
                sgxdService.saveWfHiInstContent(wfHiInstContentEntity);
                //删除当前流程实例内容
                sgxdService.deleteInstContent(procInstId,sqdid);
            }else{

            }

            //将当前task表里数据存入历史任务表中
            if("2".equals(type) || "3".equals(type)){
                task = sgxdService.findBycActivityidAndRefid(pdefId,sqdid+","+bmmc);
            }else{
                task = sgxdService.findBycActivityidAndProcInstId(pdefId,procInstId);
            }
            task.setIscurrent("0");//已完成
            task.setIscheck("1");//已签收
            task.setJssj(new Date());//结束时间
            WfTaskHistoryEntity historyEntity = new WfTaskHistoryEntity(task);
            sgxdService.saveWfTaskHistory(historyEntity);

            //删除当前task表里数据
            if("2".equals(type) || "3".equals(type)){
                sgxdService.deleteTasks(pdefId,procInstId,sqdid+","+bmmc);
                if("3".equals(type)){
                    List<WfTaskEntity> pTask =  sgxdService.findByProcInstId(procInstId);
                    if(pTask.isEmpty() == false){
                        end = "1";
                    }
                }
            }else{
                sgxdService.deleteTask(pdefId,procInstId);
            }

            //下一节点 创建任务
            String direction = "0";//流程方向 前进/回退
            if("1".equals(type)){
                for(int p=0;p<bList.size();p++){
                    WfTaskEntity newtask = new WfTaskEntity();
                    String nextnode = "";
                    rwblr = String.valueOf(bList.get(p).get("blr"));
                    if("监理单位".equals(bList.get(p).get("bm"))){
                        nextnode = "80004";
                    }else if("信息规划部".equals(bList.get(p).get("bm"))){
                        nextnode = "80006";
                    }else if("施工部门".equals(bList.get(p).get("bm"))){
                        nextnode = "80007";
                    }else if("工程管理部".equals(bList.get(p).get("bm"))){
                        nextnode = "80008";
                    }else{
                        nextnode = "80005";
                    }
//                    List<WfReNodeEntity> list = sgxdService.findByActivityidAndDirection(pdefId,direction);
                    List<WfReNodeEntity> list = sgxdService.findByActivityidAndNextnode(pdefId,nextnode);
                    for(WfReNodeEntity wf : list){
                        WfDfActivityEntity ac = sgxdService.findByActivityid(wf.getActivityid());
                        newtask.setTaskid(GuidUtils.getGuid());
                        newtask.setPtaskid("0");//前置任务为空
                        newtask.setProcInstId(procInstId);//流程实例ID
                        newtask.setCActivityid(wf.getNextnode());//环节编号
                        Date qssj = new Date();
                        newtask.setCheck_sx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(ac.getCheckMinute())));
                        newtask.setJssx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(ac.getYellowFinish())));
                        newtask.setJssx_yl(cal.getEndTime(sdft.format(qssj),Float.parseFloat(ac.getFinishMinute())+Float.parseFloat(ac.getYellowFinish())));
                        newtask.setQssj(qssj);
                        newtask.setIscurrent("1");//状态标志：0已做1待做
                        newtask.setCzr(czr);//操作人
                        newtask.setIscheck("0");//已经签收：0未签收1已签收
                        newtask.setHasnew("2");//无子流程
                        newtask.setRefkey("ZHYS_SQZB");//关联实体Key
                        newtask.setRefid(sqdid+","+bList.get(p).get("bm"));//关联实体ID
                        newtask.setRwblr(rwblr);//任务办理人
                        sgxdService.saveWfTask(newtask);

                        //修改流程实例表中的流程定义ID
                        sgxdService.updateWfruInst(procInstId,wf.getNextnode());
                    }
                }
            }else if("4".equals(type)){

            }else{
                if("1".equals(end)){

                }else{
                    if("3".equals(type)){
                        direction = "3";//结束
                        pdefId = "80010";
                    }else if("5".equals(type)){
                        pdefId = "80010";
                        direction = "1";//退回
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
                        newtask.setCheck_sx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(ac.getCheckMinute())));
                        newtask.setJssx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(ac.getYellowFinish())));
                        newtask.setJssx_yl(cal.getEndTime(sdft.format(qssj),Float.parseFloat(ac.getFinishMinute())+Float.parseFloat(ac.getYellowFinish())));
                        newtask.setQssj(qssj);
                        newtask.setCzr(czr);//操作人
                        newtask.setIscheck("0");//已经签收：0未签收1已签收
                        newtask.setHasnew("2");//无子流程
                        newtask.setRefkey("ZHYS_SQZB");//关联实体Key
                        newtask.setRefid(sqdid+","+bmmc);//关联实体ID
                        if("2".equals(type)){
                            rwblr = request.getParameter("name");
                        }else if("3".equals(type)){
                            XmInstanceEntity xmInstanceEntity = xkylxService.getXmxxById(xmid);
                            rwblr = xmInstanceEntity.getXmfzr();
                        }else if("5".equals(type)){
                            rwblr = czr;
                        }else{
                            rwblr = xkylxService.getUserIdByActivityId(wf.getNextnode());
                        }
                        newtask.setRwblr(rwblr);//任务办理人
                        sgxdService.saveWfTask(newtask);

                        //修改流程实例表中的流程定义ID
                        sgxdService.updateWfruInst(procInstId,wf.getNextnode());
                    }

                    if("5".equals(type)){

                        //将当前task表里数据存入历史任务表中
                        task = sgxdService.findBycActivityidAndProcInstId(thprefId,procInstId);
                        task.setIscurrent("0");//已完成
                        task.setIscheck("1");//已签收
                        task.setJssj(new Date());//结束时间
                        WfTaskHistoryEntity thhistoryEntity = new WfTaskHistoryEntity(task);
                        sgxdService.saveWfTaskHistory(thhistoryEntity);

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
                            thtask.setCheck_sx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(ac.getCheckMinute())));
                            thtask.setJssx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(ac.getYellowFinish())));
                            thtask.setJssx_yl(cal.getEndTime(sdft.format(qssj),Float.parseFloat(ac.getFinishMinute())+Float.parseFloat(ac.getYellowFinish())));
                            thtask.setQssj(qssj);
                            thtask.setCzr(czr);//操作人
                            thtask.setIscheck("0");//已经签收：0未签收1已签收
                            thtask.setHasnew("2");//无子流程
                            thtask.setRefkey("ZHYS_SQZB");//关联实体Key
                            thtask.setRefid(sqdid+","+bmmc);//关联实体ID
                            String actId = "80001";
                            List<String> rList = sgxdService.queryRwblr(actId,sqdid,procInstId);
                            if(rList.isEmpty() == false){
                                thtask.setRwblr(rList.get(0));//任务办理人
                            }
                            sgxdService.saveWfTask(thtask);

                            //修改流程实例表中的流程定义ID
                            sgxdService.updateWfruInst(procInstId,thwf.getNextnode());
                        }
                    }
                }
            }
            flag ="true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /***
     * 获取指派人员名单
     * @return
     */
    @RequestMapping(value = "getZpry",method = RequestMethod.GET)
    @ResponseBody
    public String getZpry(String flag,String bmmc,HttpServletResponse response,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        String userId = user.getUserid();
//        List<Map<String,Object>> roleList = xdzhysdService.queryRoleIdByActivityId(flag);
//        pd.put("roleId",roleList.get(0).get("ROLETYPE"));
        String type = "";
        if("监理单位".equals(bmmc)){
            pd.put("roleId","d9335d9cd0e14f34b11f5ce60414bd1b");
        }else if("信息规划部".equals(bmmc)){
            pd.put("roleId","3614a8f2be8645cfb949fd4a0ed636d6");
        }else if("施工部门".equals(bmmc)){
            pd.put("roleId","369fd3cbf46a46809af4167a75f12310");
        }else if("工程管理部".equals(bmmc)){
            pd.put("roleId","33e128f9b2b9485e8367b110f4db099c");
        }else if("庐阳所".equals(bmmc)){
            pd.put("roleId","0c629a53ac694fcf8b80c629a53ac601");
        }else if("瑶海所".equals(bmmc)){
            pd.put("roleId","0c629a53ac694fcf8b80c629a53ac602");
        }else if("蜀山所".equals(bmmc)){
            pd.put("roleId","0c629a53ac694fcf8b80c629a53ac603");
        }else if("包河所".equals(bmmc)){
            pd.put("roleId","0c629a53ac694fcf8b80c629a53ac604");
        }else if("经开所".equals(bmmc)){
            pd.put("roleId","0c629a53ac694fcf8b80c629a53ac605");
        }else if("北城所".equals(bmmc)){
            pd.put("roleId","0c629a53ac694fcf8b80c629a53ac606");
        }else if("滨湖所".equals(bmmc)){
            pd.put("roleId","0c629a53ac694fcf8b80c629a53ac607");
        }
        pd.put("bmmc",bmmc);
        List<Map<String,Object>> zpry = xdzhysdService.getZpry(pd);
        jo.put("zpry",zpry);
        return jo.toString();
    }

    /**
     * 获得当前登录人所在参与部门
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "getCybm",method = RequestMethod.GET)
    @ResponseBody
    public String getCybm(HttpServletResponse response,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        String userId = user.getUserid();
        pd.put("userId",userId);
        String roleId = "";
        List<Map<String,Object>> list = xdzhysdService.queryRoleIdByUserId(pd);
        if(list.isEmpty() == false){
            roleId = String.valueOf(list.get(0).get("ROLEID"));
        }
        if("ed212d777b304531b80bccf8c3a886d5".equals(roleId)){
            jo.put("bmmc","监理单位");
        }
        return jo.toString();
    }

    /**
     * 1、根据项目ID申请单ID获得反馈意见
     * 2、根据项目ID申请单ID获得反馈资料
     * @param sqdid
     * @param xmid
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "getFkyj",method = RequestMethod.GET)
    @ResponseBody
    public String getFkyj(String sqdid,String xmid,HttpServletResponse response,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        pd.put("sqdid",sqdid);
        pd.put("xmid",xmid);
        List<Map<String,Object>> fkyj = xdzhysdService.getFkyj(pd);
        List<Map<String,Object>> fkzl = xdzhysdService.getFkzl(pd);
        jo.put("fkyj",fkyj);
        jo.put("fkzl",fkzl);
        return jo.toString();
    }

    /**
     * 综合验收附件上传
     * @param multipartFiles
     * @param filename
     * @return
     */
    @ApiOperation("综合验收附件上传")
    @RequestMapping(value ="upoadFile",method = RequestMethod.POST, consumes = { "multipart/mixed", "multipart/form-data" })
    @ResponseBody
    public String upoadFile(
            @RequestParam(value = "multipartFiles", required = false ) MultipartFile multipartFiles,
            @RequestParam(value = "sqdid", required = false ) String sqdid,
            @RequestParam(value = "bmmc", required = false ) String bmmc,
            @RequestParam(value = "xmid", required = false ) String xmid,
            @RequestParam(value = "filename", required = false ) String filename, HttpServletRequest request) {
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
            String fileDir = StringUtil.getBasePath()+"upload"+File.separator+"zhys"+File.separator;
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
            pd.put("sqdid",sqdid);
            pd.put("bmmc",bmmc);
            pd.put("xmid",xmid);
            List<Map<String,Object>> list = xdzhysdService.findZhyscbidByBmAndId(pd);
            if(list.isEmpty() == false){
                pd.put("zhyscbid",list.get(0).get("ID"));
            }else{
                pd.put("zhyscbid","0");
            }
            pd.put("id", id);//主键标识
            pd.put("fjlx_yj","综合验收");//一级附件类型
            pd.put("fjlx_ej",bmmc+"资料");//一级附件类型
            pd.put("fjlj","upload"+File.separator+"zhys"+File.separator+filename.split("\\.")[0]+dateString+"."+filename.split("\\.")[filename.split("\\.").length-1]);//附件路径
            pd.put("fjhz",filenames[filenames.length-1]);//附件后缀
            pd.put("fjmc",filename);//附件名称
            pd.put("scr",user.getUserid());//上传人
            xdzhysdService.upoadFile(pd);
        }catch (Exception e){
            e.printStackTrace();
            jo.put("returncode", "999");
            jo.put("msg", "程序异常，请联系管理员");
        }
        jo.put("msg", "上传成功");
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

    /**
     * 获取附件列表（上传）
     * @param xmid
     * @param sqdid
     * @param bmmc
     * @return
     */
    @ApiOperation("获取附件列表（上传）")
    @RequestMapping(value ="getFkFjData",method = RequestMethod.POST)
    @ResponseBody
    public String getFkFjData(String xmid,String sqdid,String bmmc){
        JSONObject jo = new JSONObject();
        String cbid = "0";
        String yj = "综合验收";
        String ej = bmmc+"资料";
        Map<String,Object> pd = new HashMap<>();
        pd.put("sqdid",sqdid);
        pd.put("bmmc",bmmc);
        pd.put("xmid",xmid);
        List<Map<String,Object>> list = xdzhysdService.findZhyscbidByBmAndId(pd);
        cbid = list.get(0).get("ID").toString();
        System.out.println("----------------------------------------------------------------->>>>>>"+cbid);
        //zhys_rwcb_fj
       List<ZhysRwcbFjEntity> ZhysRwcbFj= xdzhysdService.getFkFjData(cbid,yj,ej);



        for(int i = 0;i < ZhysRwcbFj.size();i++){
            ZhysRwcbFjEntity a = ZhysRwcbFj.get(i);//直接拿这个a去点get或者set就行了
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.format(a.getScsj());
        }


        jo.put("ZhysRwcbFj",ZhysRwcbFj);

        return jo.toString();
    }

    /**
     * 下载用户资料
     * @param path
     * @param response
     * @return
     */
    @ApiOperation(value = "下载用户资料")
    @RequestMapping(value = "downloads",method = RequestMethod.POST)
    public HttpServletResponse downloads(String path, HttpServletRequest request,HttpServletResponse response) {
        try {
            String basePath = StringUtil.getBasePath();
            //String basePath = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();
            // path是指欲下载的文件的路径。
            //path = basePath+path;
            File file = new File(basePath+path);
            // 取得文件名
            String filename = file.getName();
            // 取得文件的后缀名
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件
            InputStream fis = new BufferedInputStream(new FileInputStream(basePath+path));
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
     * 删除综合验收记录附件
     * @param fjid
     * @return
     */
    @ApiOperation(value = "删除综合验收记录附件")
    @RequestMapping(value = "deleteFkFj", method = RequestMethod.POST)
    @ResponseBody
    public String deleteFkFj(String fjid){
        String flag="ok";

        try {
            xdzhysdService.deleteFkFj(fjid);
        } catch (Exception e) {
            flag="error";
        }
        return flag;
    }


}
