package com.ljx.hfgsjt.zhys.controller;

import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.zhys.*;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "SqzhysController")
@Api(description = "申请综合验收")
public class SqzhysController {

    @Autowired
    private SqzhysService sqzhysService;

    @Autowired
    private XkylxService service;

    CalculateHours cal = new CalculateHours();

    @ApiOperation(value = "申请综合验收列表数据")
    @RequestMapping(value = "getSqzhysLsit", method = RequestMethod.GET)
    public String getSqzhysLsit(HttpServletRequest request, Sqzhys sqzhys) {
       UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");//获取当前登录人
        JSONObject jo = new JSONObject();
        // System.out.println("---1---" + sqzhys.getStatus() + "-2--" + sqzhys.getLxr() + "--3--" + sqzhys.getXmmc() + "-4---" + sqzhys.getXmdz() + "--5--" + sqzhys.getKssj() + "---6--" + sqzhys.getJssj());
        List<Sqzhys> list = new ArrayList<Sqzhys>();
        int total = 0;
        try {
            sqzhys.setStartRow();
            sqzhys.setEndRow();
            sqzhys.setXmfzr(userEntity.getUserid());
            total = sqzhysService.selectSqzhysCount(sqzhys);
            if (total > 0) {
                list = sqzhysService.selectSqzhysList(sqzhys);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        jo.put("total", total);
        jo.put("list", list);
        System.out.println("------" + jo.toString());
        return jo.toString();

    }


    @ApiOperation(value = "查询资料分类数量")
    @RequestMapping(value = "selectZlflsl", method = RequestMethod.GET)
    public String selectZlflsl(HttpServletRequest request, String xmid, String sqlx, String sqbh, Integer page, Integer size) {

        JSONObject jo = new JSONObject();
        Map<String, Object> map = new HashedMap();
        System.out.println(xmid + "------xmid------" + sqlx + "--------sqlx-------" + sqbh + "-------sqbh------");
        map.put("page", (page - 1) * size);
        map.put("pageSize", (page - 1) * size + size);
        map.put("sqbh", sqbh);
        map.put("xmid", xmid);
        int total = 0;//居民 工商 户外
        int sjcgtotal = 0;
        int sgtotal = 0;
        if ("1".equals(sqlx)) {//居民
            total = sqzhysService.selectJmCount(sqbh);
            if (total > 0) {
                List<YwslXjxqsqbFjEntity> jmlist = sqzhysService.selectJmlist(map);
                jo.put("khtglist", jmlist);
            }
            jo.put("total", total);
        } else if ("2".equals(sqlx)) {//工商
            total = sqzhysService.selectGsCount(sqbh);
            if (total > 0) {
                List<YwslGshbsqbFjEntity> gslist = sqzhysService.selectGslist(map);
                jo.put("khtglist", gslist);
            }
            jo.put("total", total);
        } else if ("3".equals(sqlx)) {//户外
            total = sqzhysService.selectHwCount(sqbh);
            if (total > 0) {
                List<YwslChgzsqbFjEntity> hwlist = sqzhysService.selectHwlist(map);
                jo.put("khtglist", hwlist);
            }
            jo.put("total", total);
        } else {
            jo.put("total", 0);
        }
        sjcgtotal = sqzhysService.selectSjcgCount(xmid);
        if (sjcgtotal > 0) {
            List<SjstRwSjstcgEntity> sjcglist = sqzhysService.selectSjcglist(map);
            jo.put("sjcglist", sjcglist);
        }
        sgtotal = sqzhysService.selectSgCount(xmid);
        if (sgtotal > 0) {
            List<SgxtBgxxFjEntity> sglist = sqzhysService.selectSglist(map);
            jo.put("sglist", sglist);
        }
        jo.put("sjcgtotal", sjcgtotal);
        jo.put("sgtotal", sgtotal);
        return jo.toString();
    }


    @ApiOperation(value = "查询综合验收范围数量和对象")
    @RequestMapping(value = "selectZhysfwxx", method = RequestMethod.GET)
    public String selectZhysfwxx(HttpServletRequest request, String xmid, Integer page, Integer size) {
        JSONObject jo = new JSONObject();
        Map<String, Object> map = new HashedMap();
        map.put("page", (page - 1) * size);
        map.put("pageSize", (page - 1) * size + size);
        map.put("xmid", xmid);
        int tysl = sqzhysService.selectTysl(xmid);//庭院
        if (tysl > 0) {
            List<XmTyEntity> xmTyList = sqzhysService.selectTyList(map);
            jo.put("tylist", xmTyList);
        }
        int ldsl = sqzhysService.selectLdsl(xmid);
        if (ldsl > 0) {
            List<XmLdEntity> xmLdList = sqzhysService.selectLdList(map);
            jo.put("ldlist", xmLdList);
        }
        int dxssl = sqzhysService.selectDxssl(xmid);
        if (dxssl > 0) {
            List<XmDxsEntity> xmDxsList = sqzhysService.selectDxsList(map);
            jo.put("dxslist", xmDxsList);
        }
        int bfsl = sqzhysService.selectBfsl(xmid);
        if (bfsl > 0) {
            List<XmBfEntity> xmBfList = sqzhysService.selectBfList(map);
            jo.put("bflist", xmBfList);
        }
        int sxsl = sqzhysService.selectSxsl(xmid);
        if (sxsl > 0) {
            List<XmSxEntity> xmSxList = sqzhysService.selectSxList(map);
            jo.put("sxlist", xmSxList);
        }
        jo.put("tysl", tysl);
        jo.put("ldsl", ldsl);
        jo.put("dxssl", dxssl);
        jo.put("bfsl", bfsl);
        jo.put("sxsl", sxsl);
        return jo.toString();

    }


    @ApiOperation(value = "保存施工部发起的申请")
    @RequestMapping(value = "saveZhysSqzb", method = RequestMethod.POST)
    public String saveZhysSqzb(HttpServletRequest request, String xmid, String bz) {
        JSONObject jo = new JSONObject();
        Map<String, Object> map = new HashMap<String, Object>();
        String ss = "ZHYS";
        map.put("v_stop_run", ss);
        sqzhysService.texuChange(map);
        String rwbh = "ZHYS" + map.get("p_result");
        String id = GuidUtils.getGuid();
        String pdefId = "80002";
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
        String czr = user.getUserid();
        //存入综合验收申请主表
        ZhysSqzb zhysSqzb = new ZhysSqzb();
        zhysSqzb.setId(id);
        zhysSqzb.setRwbh(rwbh);
        zhysSqzb.setXmid(xmid);
        zhysSqzb.setFqr(czr);
        zhysSqzb.setFqrq(new Date());
        zhysSqzb.setBz(bz);
        int sqzb_size = sqzhysService.insertZhysSqzb(zhysSqzb);
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
        wfRuInstContentEntity.setContenttype("ZHYS_SQZB");
        wfRuInstContentEntity.setContentname(id);
        service.saveWfRuInstContent(wfRuInstContentEntity);
        //创建任务，存入历史任务表中
        String pdef_id = "80001";
        WfTaskEntity task = new WfTaskEntity();
        String taskId = GuidUtils.getGuid();
        task.setTaskid(taskId);
        task.setPtaskid("0");//前置任务为空
        task.setProcInstId(procInstId);//流程实例ID
        task.setCActivityid(pdef_id);//环节编号
        task.setQssj(new Date());
        task.setJssj(new Date());
        task.setIscurrent("0");//已完成
        task.setCzr(czr);//操作人
        task.setIscheck("1");//已签收
        task.setHasnew("2");//无子流程
        task.setRwblr(czr);//任务办理人
        WfTaskHistoryEntity historyEntity = new WfTaskHistoryEntity(task);
        service.saveWfTaskHistory(historyEntity);
        //下一节点 创建任务  综合验收申请
        String direction = "0";//流程方向 前进/回退
        List<WfReNodeEntity> list = service.findByActivityidAndDirection(pdef_id, direction);
        XmUserEntity xmUserEntity=sqzhysService.getZhysmrr(pdef_id);
        WfDfActivityEntity wfDfActivityEntity = service.findByActivityid(list.get(0).getNextnode());
        taskId = GuidUtils.getGuid();
        System.out.println(list.size() + "----");
        task.setTaskid(taskId);
        task.setPtaskid("0");//前置任务为空
        task.setProcInstId(procInstId);//流程实例ID
        task.setQssj(new Date());
        task.setCActivityid(list.get(0).getNextnode());//环节编号
        task.setIscurrent("1");//已完成
        task.setCzr(czr);//操作人
        task.setRwblr(xmUserEntity.getUserid());
        task.setIscheck("0");//签收
        task.setHasnew("2");//无子流程
        task.setRefkey("ZHYS_SQZB");//关联实体key
        task.setRefid(id);//关联实体ID
        String rwblr = service.getUserIdByActivityId(list.get(0).getNextnode());
        task.setRwblr(rwblr);
        Date qssj = new Date();
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        task.setCheck_time(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
        task.setJssx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
        task.setJssx_yl(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getFinishMinute())));

        service.saveWfTask(task);

        if (sqzb_size > 0) {
            jo.put("result", "success");
        } else {
            jo.put("result", "fail");
        }
        return jo.toString();
    }


    @ApiOperation(value = "获取其他用户资料")
    @RequestMapping(value = "download", method = RequestMethod.POST)
    public HttpServletResponse download(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
           String basePath = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();
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


    /**
     * 根据项目ID查询水表信息
     *
     * @return
     */
    @RequestMapping(value = "getSbxx", method = RequestMethod.GET)
    @ResponseBody
    public String getSbxx(String xmid, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jo = new JSONObject();
        try {
            List<Map<String, Object>> wblist = sqzhysService.getWbxxByXmid(xmid);
            List<Map<String, Object>> dblist = sqzhysService.getDbxxByXmid(xmid);
            List<Map<String, Object>> hblist = sqzhysService.getHbxxByXmid(xmid);
            jo.put("wblist", wblist);
            jo.put("dblist", dblist);
            jo.put("hblist", hblist);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jo.toString();
    }

}
