package com.ljx.hfgsjt.wtys.controller;

import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.wtys.YsZjqtFkEntity;
import com.ljx.hfgsjt.repository.CalculateHours;
import com.ljx.hfgsjt.sgjg.service.SgxdService;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import com.ljx.hfgsjt.util.utils.StringUtil;
import com.ljx.hfgsjt.wtys.service.YsZjqtFkService;
import com.ljx.hfgsjt.wtys.service.YsZjqtService;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.impl.util.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "YsZjqtFk")
@CrossOrigin
public class YsZjqtFkController {

    @Autowired
    private YsZjqtFkService ysZjqtFkService;

    @Autowired
    private YsZjqtService ysZjqtService;

    @Autowired
    private SgxdService sgxdService;

    private Logger logger = LoggerFactory.getLogger(YsZjqtFkController.class);

    CalculateHours cal = new CalculateHours();

    //查询所有数据
    @RequestMapping(value = "ZjqtFkList",method = RequestMethod.GET)
    @ResponseBody
    public String getZjqtFkList(Integer page, Integer size, HttpServletResponse response){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<YsZjqtFkEntity> pagelist = ysZjqtFkService.getYsZjqtFkList(pageRequest);
        JSONObject jo = new JSONObject();
        jo.put("total",pagelist.getTotalElements());
        jo.put("row",pagelist.getContent());
        return jo.toString();
    }

    /**
     * 造价洽谈反馈意见上传
     * @param multipartFiles
     * @param operation
     * @param filename
     * @return
     */
    @RequestMapping(value ="upoadFile",method = RequestMethod.POST, consumes = { "multipart/mixed", "multipart/form-data" })
    @ResponseBody
    public Map upoadFile(
            @RequestParam(value = "multipartFiles", required = false ) MultipartFile multipartFiles,
            @RequestParam(value = "operation", required = false ) String operation,
            @RequestParam(value = "filename", required = false ) String filename,
            @RequestParam(value = "xmid", required = false ) String xmid,HttpServletRequest request) {
        Map resMap = new HashMap<String,Object>();
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity)session.getAttribute("user");//获取当前登录的用户信息
        if (Objects.isNull(multipartFiles)) {
            logger.error("文件为空");
            resMap.put("msg","文件为空，请重新上传");
            return resMap;
        }
        List<XmInstance> xmList = ysZjqtFkService.getXmxxByid(xmid);
        String xmbh = xmList.get(0).getXmbh();
        String fileDir = StringUtil.getBasePath()+"upload"+File.separator+xmbh+File.separator;
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
            Map<String, Object> pd = new HashMap<>();
            pd.put("id", GuidUtils.getGuid());//主键标识
            pd.put("yszjqtid",operation);//造价洽谈标识
            pd.put("fjlx_yj","预算资料");//一级附件类型
            pd.put("fjlx_ej","用户洽谈结果");//二级附件类型
            pd.put("fjlj","upload"+File.separator+xmbh+File.separator+filename.split("\\.")[0]+"."+filename.split("\\.")[filename.split("\\.").length-1]);//附件路径
            pd.put("fjhz",filenames[filenames.length-1]);//附件后缀
            pd.put("fjmc",filename);//附件名称
            pd.put("scr",user.getUserid());//上传人
            ysZjqtFkService.upoadFile(pd);
        }catch (Exception e){
            e.printStackTrace();
            resMap.put("returncode", "999");
            resMap.put("msg", "程序异常，请联系管理员");
        }
        resMap.put("returncode", "000");
        resMap.put("msg", "上传成功");
        resMap.put("path",fileDir);
        return resMap;
    }


    /**
     * 造价洽谈反馈结果录入提交
     * @param request
     * @return
     */
    @RequestMapping(value = "saveYsZjqtFk",method = RequestMethod.POST)
    @ResponseBody
    public String saveYsZjqtFk(HttpServletRequest request){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity)session.getAttribute("user");//获取当前登录的用户信息
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            //更新预算造价洽谈
            String refid = request.getParameter("refid");
            pd.put("qtjg",request.getParameter("qtresult"));
            pd.put("refid",refid);
            ysZjqtFkService.saveYsZjqtFk(pd);

            //流程结束
            WfTaskEntity task = new WfTaskEntity();
            String procInstId = "";
            String pdefId = "";
            String rwblr = "";
            WfRuInstEntity instEntity = new WfRuInstEntity();
            WfRuInstContentEntity wfRuInstContentEntity = new WfRuInstContentEntity();
            String lcscId = "70001";
            List<Map<String,Object>> varList = sgxdService.queryPdefId(refid,lcscId);
            procInstId = String.valueOf(varList.get(0).get("PROC_INST_ID"));
            pdefId = String.valueOf(varList.get(0).get("C_ACTIVITYID"));

            instEntity = sgxdService.findByprocInstId(procInstId);
            WfHiInstEntity wfHiInstEntity = new WfHiInstEntity(instEntity);
            wfHiInstEntity.setEndtime(new Date());
            //将当前流程实例存入流程历史表
            sgxdService.savewfHiInst(wfHiInstEntity);
            //删除当前流程实例
            sgxdService.deleteInst(procInstId);

            //将当前流程实例内容表存入流程实例历史内容表
            wfRuInstContentEntity = sgxdService.findByProcInstIdAndContentname(procInstId,refid);
            WfHiInstContentEntity wfHiInstContentEntity = new WfHiInstContentEntity(wfRuInstContentEntity);
            sgxdService.saveWfHiInstContent(wfHiInstContentEntity);
            //删除当前流程实例内容
            sgxdService.deleteInstContent(procInstId,refid);

            //将当前task表里数据存入历史任务表中
            task = sgxdService.findBycActivityidAndProcInstId(pdefId,procInstId);
            task.setIscurrent("0");//已完成
            task.setIscheck("1");//已签收
            task.setJssj(new Date());//结束时间
            WfTaskHistoryEntity historyEntity = new WfTaskHistoryEntity(task);
            sgxdService.saveWfTaskHistory(historyEntity);

            //删除当前task表里数据
            sgxdService.deleteTask(pdefId,procInstId);

            //下一节点 创建任务
            String direction = "3";//流程方向 前进/回退

            List<WfReNodeEntity> list = ysZjqtService.findByActivityidAndDirection(pdefId,direction);
            WfTaskEntity tasks = new WfTaskEntity();
            for(WfReNodeEntity wf : list){
                WfDfActivityEntity ac = ysZjqtService.findByActivityid(wf.getActivityid());
                tasks.setTaskid(GuidUtils.getGuid());
                tasks.setPtaskid(task.getTaskid());//前置任务为空
                tasks.setProcInstId(procInstId);//流程实例ID
                tasks.setCActivityid(wf.getNextnode());//环节编号
                Date qssj = new Date();
                try {
                    if (ac.getCheckLimited() == null || ac.getCheckLimited().equals("")
                            || ac.getCheckLimited().equals("0")) {
                        tasks.setCheck_sx(sdft.parse("2099-12-31 23:59:59"));
                    } else {
                        tasks.setCheck_sx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getCheckMinute())));
                    }
                    if (ac.getFinishLimited() == null || ac.getFinishLimited().equals("")
                            || ac.getFinishLimited().equals("0")) {
                        tasks.setJssx(sdft.parse("2099-12-31 23:59:59"));
                        tasks.setJssx_yl(sdft.parse("2099-12-31 23:59:59"));
                    } else {
                        tasks.setJssx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getYellowFinish())));
                        tasks.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getFinishMinute()) + Float.parseFloat(ac.getYellowFinish())));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tasks.setQssj(qssj);
                tasks.setIscurrent("1");//已完成
                tasks.setCzr(user.getUserid());//操作人
                tasks.setIscheck("1");//已签收
                tasks.setHasnew("2");//无子流程
                tasks.setRefkey("YS_ZJQTZB");//关联实体Key
//                    String rwblr = sgxdService.getUserIdByActivityId(wf.getNextnode());
                tasks.setRwblr(user.getUserid());//任务办理人
                tasks.setRefid(refid);//关联实体ID
            }
            WfTaskHistoryEntity historyEntitys = new WfTaskHistoryEntity(tasks);
            ysZjqtService.saveWfTaskHistory(historyEntitys);
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 根据ID获得预算造价洽谈主表数据
     * @param request
     * @return
     */
    @ApiOperation("根据ID获得预算造价洽谈主表数据")
    @RequestMapping(value = "queryZjqtzbById",method = RequestMethod.GET)
    @ResponseBody
    public String queryZjqtzbById(HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        try{
            pd.put("id",request.getParameter("zbid"));
            List<Map<String,Object>> list = ysZjqtFkService.queryZjqtzbById(pd);
            List<Map<String,Object>> cbList = ysZjqtFkService.queryZjqtcbById(pd);
            jo.put("list",list);
            jo.put("cbList",cbList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 根据ID查询附件数量
     * @param request
     * @return
     */
    @ApiOperation("根据ID查询附件数量")
    @RequestMapping(value = "queryZllist",method = RequestMethod.GET)
    @ResponseBody
    public String queryZllist(HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        try{
            pd.put("id",request.getParameter("zbid"));
            List<Map<String,Object>> fjList = ysZjqtFkService.queryZjqtfjById(pd);
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
    @RequestMapping(value = "deleteFjByid",method = RequestMethod.GET)
    @ResponseBody
    public String deleteFjByid(String fjId,HttpServletRequest request){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        try{
            pd.put("fjId",fjId);
            ysZjqtFkService.deleteFjByid(pd);
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation("洽谈反馈结果参数查询")
    @RequestMapping(value = "queryPara",method = RequestMethod.GET)
    @ResponseBody
    public String queryPara(String xmid){
        JSONObject jo = new JSONObject();
        try{
            List<String> list = ysZjqtFkService.queryPara(xmid);
            if(list.isEmpty() == false){
                String zbid = list.get(0);
                List<String> qtjgList = ysZjqtFkService.queryFkByzbid(zbid);
                jo.put("qtjgList",qtjgList);
            }
            jo.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("发起造价洽谈参数查询")
    @RequestMapping(value = "queryParas",method = RequestMethod.GET)
    @ResponseBody
    public String queryParas(String xmid){
        JSONObject jo = new JSONObject();
        try{
            List<String> list = ysZjqtFkService.queryParas(xmid);
            if(list.isEmpty() == false){
                String zbid = list.get(0);
                List<Map<String,Object>> zjqtList = ysZjqtFkService.queryFkByzbids(zbid);
                jo.put("zjqtList",zjqtList);
            }
            jo.put("list",list);
        }catch (Exception e){
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
