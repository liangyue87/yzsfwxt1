package com.ljx.hfgsjt.gcpd.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljx.hfgsjt.entity.dto.Activity.WfDfActivityEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfHiInstContentEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfHiInstEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfTaskHistoryEntity;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.gcpd.GcpdSqgchgpdEntity;
import com.ljx.hfgsjt.entity.dto.gcpd.YshgFjEntity;
import com.ljx.hfgsjt.entity.dto.gcpd.YshgRwbEntity;
import com.ljx.hfgsjt.entity.dto.gcpd.YshgRwbFjEntity;
import com.ljx.hfgsjt.gcpd.service.GcpdService;
import com.ljx.hfgsjt.repository.CalculateHours;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import com.ljx.hfgsjt.util.utils.StringUtil;
import com.ljx.hfgsjt.xkybz.service.XkylxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.ljx.hfgsjt.util.utils.DateUtils.getDateFormatByGMT;

/**
 * 十、工程评定
 */
@Controller
@RequestMapping(value = "GcpdController")
@Api(description = "工程评定")
public class GcpdController {

    @Autowired
    private GcpdService gcpdService;
    @Autowired
    private XkylxService xkylxService;

    CalculateHours cal = new CalculateHours();

    private static final Logger logger = LoggerFactory.getLogger(GcpdController.class);

    @ApiOperation(value = "申请工程合格评定(模糊查询)")
    @RequestMapping(value = "getSqgcHgpdList", method = RequestMethod.GET)
    @ResponseBody
    public String getSqgcHgpdList(String xmid,String date1,String date2,String xmbh,String xmmc,String lxr,String xmdz,String azlx,String pdzt,Integer page,Integer size,HttpServletRequest request){
       UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");//获取当前登录人
        if(!"".equals(date1) && date1 != null){
            try {
                date1 = getDateFormatByGMT(date1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(!"".equals(date2) && date2 != null){
            try {
                date2 = getDateFormatByGMT(date2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        JSONObject jo = new JSONObject();
        List<GcpdSqgchgpdEntity> gcHgpd = gcpdService.geYszptall(xmid,userEntity.getUserid(),date1,date2,xmbh,xmmc, lxr, xmdz, azlx,pdzt, page - 1, size);
        jo.put("gcHgpd", gcHgpd);
        jo.put("total", gcpdService.getTotal());
        return jo.toString();
    }


    @ApiOperation(value = "保存供水工程竣工验收合格证明")
    @RequestMapping(value = "bc", method = RequestMethod.GET)
    @ResponseBody
    public String bc(String xmid, String xmmc, String ysfw, String jsdw, String xmlx, String ysnr, String cbyj, String ysjl, HttpServletRequest request){
        String flag = "ok";
        YshgRwbEntity yshgRwbEntity1=gcpdService.getYshgRwbByXmid(xmid);
        String id=null;
        String sfcz=null;

        UserEntity user= (UserEntity) request.getSession().getAttribute("user");//获取当前登录的用户信息
        String guid=GuidUtils.getGuid();

        //调用存储过程获取任务编号
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("v_stop_run", "YSHG");
        xkylxService.getRwbh(map);//获取任务编号
        String rwbh = "YSHG" + map.get("p_result");

        try {
            id=yshgRwbEntity1.getId();
        } catch (Exception e) {
            id="";
        }
        YshgRwbEntity yshgRwbEntity=new YshgRwbEntity();
        yshgRwbEntity.setId(guid);
        yshgRwbEntity.setXmid(xmid);
        yshgRwbEntity.setRwbh(rwbh);
        yshgRwbEntity.setYsfw(ysfw);
        yshgRwbEntity.setXmlx(xmlx);
        yshgRwbEntity.setYsnr(ysnr);
        yshgRwbEntity.setCbyj(cbyj);
        if (!ysjl.equals("") || ysjl!=null){
            yshgRwbEntity.setYsjl(ysjl);
        }
        yshgRwbEntity.setCjr(user.getUserid());
        yshgRwbEntity.setCjsj(new Date());


        if (id.equals("") || id == null){
            gcpdService.saveYshgRwbEntity(yshgRwbEntity);

        }else {
            gcpdService.updateYshgRwbEntity(id,ysfw,xmlx,ysnr,cbyj,ysjl);
        }

        WfHiInstContentEntity wfHiInstContentEntity = gcpdService.getwfHiInstContent(xmid);
        try {//第一次执行，该项目没有创建实例，获取ProcInstId时会报错，届时置为空
            sfcz = wfHiInstContentEntity.getContentid();
        } catch (Exception e) {
            sfcz = "";
        }

        if (sfcz.equals("") || sfcz == null){
            try {
                //流程实例加入历史流程中A0001(wf_hi_inst)
                WfHiInstEntity wfHiInst = new WfHiInstEntity();
                String procInstId = GuidUtils.getGuid();//PROC_INST_ID历史流程实例id
                wfHiInst.setProcInstId(procInstId);
                wfHiInst.setPdefId("A0001");//历史流程实例id
                wfHiInst.setParammap(rwbh);
                wfHiInst.setStartdt(new Date());//启动时间
                wfHiInst.setStartuser(user.getUserid());//启动时间
                wfHiInst.setEndtime(new Date());//结束时间
                wfHiInst.setProcstatus("1");//实例状态
                wfHiInst.setStopuser(user.getUserid());//终止用户
                wfHiInst.setStopdt(new Date());//终止时间
                //保存到历史流程实例表中
                xkylxService.saveWfHiRuInst(wfHiInst);

                //将项目信息存入历史流程实例使用内容表(WF_HI_INST_CONTENT)
                WfHiInstContentEntity wfHiInstContent = new WfHiInstContentEntity();
                wfHiInstContent.setContentid(GuidUtils.getGuid());
                wfHiInstContent.setProcInstId(procInstId);
                wfHiInstContent.setTableName("XM_INSTANCE");
                wfHiInstContent.setContentname("id");
                wfHiInstContent.setContenttype("gcpd");
                wfHiInstContent.setContentvalue(xmid);
                wfHiInstContent.setReadonly("0");
                //保存到历史流程实例内容表中
                xkylxService.saveWfHiInstContent(wfHiInstContent);

                //创建任务存入历史任务表中 A0001
                WfTaskHistoryEntity wfTaskHistory = new WfTaskHistoryEntity();
                String taskId = GuidUtils.getGuid();//任务编号
                wfTaskHistory.setTaskid(taskId);
                wfTaskHistory.setPtaskid("0");//前置任务为空
                wfTaskHistory.setProcInstId(procInstId);//流程实例ID
                wfTaskHistory.setcActivityid("A0001");//环节编号
                wfTaskHistory.setIscurrent("0");//已完成
                wfTaskHistory.setCzr(user.getUserid());//操作人
                wfTaskHistory.setQssj(new Date());//起始时间
                wfTaskHistory.setJssj(new Date());//结束时间
                wfTaskHistory.setIscheck("1");//已签收
                wfTaskHistory.setCheckTime(new Date());//签收时间
                //-----------------绿灯办结限制时间----------------------
                Date qssj = new Date();
                SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                WfDfActivityEntity wfDfActivityEntity = xkylxService.findByActivityid("A0001");
                wfTaskHistory.setJssx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getYellowFinish())));//绿灯办结限制时间
                wfTaskHistory.setCheckUser(user.getUserid());//签收人
                wfTaskHistory.setJsUser(user.getUserid());//结束人
                wfTaskHistory.setCheckSx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getCheckMinute())));//签收时限
                //-----------------------------------------------------
                wfTaskHistory.setHasnew("1");//子流程是否有提交，1有，0无
                //黄灯办结限制时间
                wfTaskHistory.setJssxYl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute())+Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                wfTaskHistory.setRefkey("YSHG_RWB");//关联实体Key
                wfTaskHistory.setRefid(guid);//关联实体ID
                //wfTaskHistory.setRwblr(xkylxService.getUserIdByActivityId("A0001"));//任务办理人
                wfTaskHistory.setRwblr(user.getUserid());//任务办理人为前登录人
                xkylxService.saveWfTaskHistory(wfTaskHistory);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                flag = "error";
            }
        }

        return flag;
    }



    @ApiOperation(value = "查看当前数据在数据库是否存在")
    @RequestMapping(value = "getSfbc", method = RequestMethod.GET)
    @ResponseBody
    public String getSfbc(String xmid){
        JSONObject jo = new JSONObject();
        String flag="";
        YshgRwbEntity yshgRwb = gcpdService.getYshgRwbByXmid(xmid);

        try {
            flag=yshgRwb.getId();
        } catch (Exception e) {
            flag="0";
        }
        if (!flag.equals("0")){
            jo.put("yshgRwb", yshgRwb);
            return jo.toString();
        }else {
            jo.put("yshgRwb",flag);
            return jo.toString();
        }


    }

    /**
     * 查询高拍仪拍摄数量和拍摄路径
     * @param zbid
     * @return
     */
    @ApiOperation(value = "查询某任务个数")
    @RequestMapping(value = "getPsTotals", method = RequestMethod.GET)
    @ResponseBody
    public String getPsTotals(String zbid){
        JSONObject jo = new JSONObject();
        int  ysfjTotal=gcpdService.getPsTotals(zbid);
        List<YshgFjEntity> fjlj = gcpdService.getAllYshgFjFjlj(zbid);

        jo.put("ysfjFjlj",fjlj);
        jo.put("ysfjTotal",ysfjTotal);
        return jo.toString();
    }


    /**
     * 上传
     * @return
     */
    @ApiOperation(value = " 上传")
    @RequestMapping(value ="scwj",method = RequestMethod.POST)
    @ResponseBody
    public Map multiUpload(
            @RequestParam(value = "multipartFiles", required = false ) MultipartFile multipartFiles,
            @RequestParam(value = "filename", required = false ) String  filename,
            @RequestParam(value = "zbid", required = false ) String  zbid,
            @RequestParam(value = "xmid", required = false ) String  xmid,
            @RequestParam(value = "xmbh", required = false ) String  xmbh,
            HttpServletRequest request) {
        Map resMap = new HashMap<String,Object>();

        if (Objects.isNull(multipartFiles)) {
            resMap.put("msg","文件为空，请重新上传");
            return resMap;
        }
        String fileDir = StringUtil.getBasePath()+"upload"+File.separator+xmbh+File.separator+"Yshgrwbfj"+File.separator;
        //String fileDir = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")+"upload/Yshgrwbfj/")).replaceAll("file:/", "").replaceAll("%20", " ").trim();
        File dir = new File(fileDir);
        File file = null;
        try {
            file = new File(fileDir, multipartFiles.getOriginalFilename());
            if (!dir.exists()) {
                dir.mkdir();//创建目录
            }
            if (!file.exists()) {
                //判断是否存在目录. 不存在则创建
                isChartPathExist(fileDir);
                file.createNewFile();
            }
            String[] filenames = filename.split("\\.");//拆分
            Map<String,Object> fjMap = new HashMap<>();
            multipartFiles.transferTo(file);//方法将上传文件写到服务器上指定的文件。
            HttpSession session = request.getSession();
            UserEntity user = (UserEntity)session.getAttribute("user");//获取当前登录的用户信息
           YshgRwbFjEntity yshgRwbFj = new YshgRwbFjEntity();

            yshgRwbFj.setId(GuidUtils.getGuid());//唯一uuid
            yshgRwbFj.setYshgrwbid(zbid);
            yshgRwbFj.setFjlxYj("gchgpdb");
            yshgRwbFj.setFjlj("upload/"+xmbh+"/Yshgrwbfj/"+filename);//附件路径
            yshgRwbFj.setFjhz(filenames[filenames.length-1]);//后缀
            yshgRwbFj.setFjmc(filename);//附件名称
            yshgRwbFj.setScr(user.getUserid());//上传人
            yshgRwbFj.setScsj(new java.sql.Date(new java.util.Date().getTime()));
            yshgRwbFj.setScFlag("0");//不删除
            yshgRwbFj.setXmid(xmid);//项目id
            gcpdService.setyshgRwbFj(yshgRwbFj);

        }catch (IOException e) {
            e.printStackTrace();
            resMap.put("returncode", "500");
            resMap.put("msg", "程序异常，请联系管理员");
        }

        resMap.put("returncode", "200");
        resMap.put("msg", "上传成功");
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

    @ApiOperation(value = " 上传")
    @RequestMapping(value ="saveImageToDb",method = RequestMethod.POST)
    @ResponseBody
    public String saveImageToDb(String dirName,String fileName,String zbid,HttpServletRequest request){
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity)session.getAttribute("user");//获取当前登录的用户信息
        JSONObject jo = new JSONObject();
        YshgRwbFjEntity yshgRwbFj = new YshgRwbFjEntity();
        String fileDir = StringUtil.getBasePath()+"upload"+File.separator;
       // String fileDir = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")+"upload/")).replaceAll("file:/", "").replaceAll("%20", " ").trim();
        yshgRwbFj.setId(GuidUtils.getGuid());//唯一uuid
        yshgRwbFj.setYshgrwbid(zbid);
        yshgRwbFj.setFjlj(fileDir+dirName+File.separator+fileName);//附件路径
        yshgRwbFj.setFjhz(fileName.substring(fileName.indexOf(".")));//后缀
        yshgRwbFj.setFjmc(fileName);//附件名称
        yshgRwbFj.setScr(user.getUserid());//上传人
        yshgRwbFj.setScsj(new java.sql.Date(new java.util.Date().getTime()));
        yshgRwbFj.setScFlag("0");//不删除

        gcpdService.setyshgRwbFj(yshgRwbFj);
        jo.put("path",fileDir+dirName+File.separator+fileName);
        return jo.toString();
    }


    /**
     * 获取预算资料信息表
     * @return
     */
    @RequestMapping(value = "getYshg" ,method = RequestMethod.GET)
    @ResponseBody
    public String getYshg(String id){
        YshgFjEntity fj=new YshgFjEntity();
        fj.setXmid(id);
        List<YshgFjEntity> fjxx = gcpdService.getAllYshgFj(fj);

        JSONObject jo = new JSONObject();
        jo.put("fjxx",fjxx);


        return jo.toString();

    }



    /**
     * 下载用户资料
     * @param path
     * @param response
     * @return
     */
    @ApiOperation(value = "下载预算资料")
    @RequestMapping(value = "download",method = RequestMethod.POST)
    public HttpServletResponse download(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            String basePath = StringUtil.getBasePath();
            // path是指欲下载的文件的路径。
            path = basePath+path;
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
     * 删除高拍仪拍摄照片
     * @param zbid
     * @return
     */
    @ApiOperation(value = "删除高拍仪拍摄照片")
    @RequestMapping(value = "deleteImage", method = RequestMethod.GET)
    @ResponseBody
    public String deleteImage(String zbid,String fjlj){
       String flag="ok";

        try {
            gcpdService.deleteImage(zbid,fjlj);
        } catch (Exception e) {
            flag="error";
        }

        return flag;
    }


    /**
     * 删除工程评定附件
     * @param fjid
     * @return
     */
    @ApiOperation(value = "删除工程评定附件")
    @RequestMapping(value = "deleteFkFj", method = RequestMethod.POST)
    @ResponseBody
    public String deleteFkFj(String fjid){
        String flag="ok";

        try {
            gcpdService.deleteFkFj(fjid);
        } catch (Exception e) {
            flag="error";
        }

        return flag;
    }





}
