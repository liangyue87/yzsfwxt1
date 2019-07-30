package com.ljx.hfgsjt.sjst.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.Sjst.*;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.xqsl.GetXqslEntity;
import com.ljx.hfgsjt.message.service.MessageService;
import com.ljx.hfgsjt.repository.CalculateHours;
import com.ljx.hfgsjt.sjst.service.SjstService;
import com.ljx.hfgsjt.util.utils.DateUtils;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import com.ljx.hfgsjt.util.utils.StringUtil;
import com.ljx.hfgsjt.xkybz.service.XkylxService;
import com.ljx.hfgsjt.xqsl.service.XqslService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "sjstController")
@Api(description = "设计审图")
public class SjstController {
    @Value("${prop.upload-folder}")
    private String uploadFolder;
    @Autowired
    private SjstService service;
    @Autowired
    private XkylxService xkylxService;
    @Autowired
    private XqslService xqslService;
    @Autowired
    private MessageService messageService;

    private Logger logger = LoggerFactory.getLogger(SjstController.class);

    @ApiOperation(value = "项目统筹")
    @RequestMapping(value = "getTcxm",method = RequestMethod.GET)
    public String  getTcxm(HttpServletRequest request,ProjectModel model){
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        model.setUserid(user.getUserid());
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        int total = 0;
        try {
            model.setStartRow();
            model.setEndRow();
            total = service.getTcxmCount(model);
            if(total>0){
                list = service.getTcxmtList(model);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("total",total);
        jo.put("row",list);
        return jo.toString();
    }

    @ApiOperation(value = "项目终止")
    @RequestMapping(value = "stopProject",method = RequestMethod.GET)
    public boolean  stopProject(HttpServletRequest request,String xmid,String zzyy){
        boolean flag = false;
        try {
            service.stopProject(xmid,zzyy);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }


    @ApiOperation(value = "获取项目集合")
    @RequestMapping(value = "getProjectList",method = RequestMethod.GET)
    public String  getProjectList(HttpServletRequest request,ProjectModel model){
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        model.setXmfzr(user.getUserid());
        List<ProjectModel> list = new ArrayList<ProjectModel>();
        int total = 0;
        try {
            model.setStartRow();
            model.setEndRow();
            total = service.getProjectCount(model);
            if(total>0){
                list = service.getProjectList(model);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("total",total);
        jo.put("row",list);
        return jo.toString();
    }

    @ApiOperation(value = "活动签收")
    @RequestMapping(value = "handleEdit",method = RequestMethod.GET)
    public boolean  handleEdit(HttpServletRequest request,String xmid,String activityId,String type){
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        List<WfRuInstEntity> instList = new ArrayList<WfRuInstEntity>();
        boolean flag = false;
//        String pdefId = "";
        Date date = new Date();
        int direction = 0;
        try{
            //查看是否已存在实例
            instList = service.getInstList(xmid,activityId,direction,type);
            if(instList==null||instList.size()<=0){
                //查询流程定义id
//                pdefId = service.getPdefId(activityId);
                //开启实例
                WfRuInstEntity instEntity = new WfRuInstEntity();
                instEntity.setProcInstId(GuidUtils.getGuid());
                instEntity.setPdefId(activityId);
                instEntity.setStartuser(user.getUserid());
                instEntity.setProcstatus(1);
                instEntity.setStartdt(date);
                xkylxService.saveWfRuInst(instEntity);
                WfRuInstContentEntity instContentEntity = new WfRuInstContentEntity();
                instContentEntity.setContentid(GuidUtils.getGuid());
                instContentEntity.setProcInstId(instEntity.getProcInstId());
                instContentEntity.setTableName("xm_instance");
                instContentEntity.setContentname("id");
                instContentEntity.setContentvalue(xmid);
                instContentEntity.setContenttype(type);
                xkylxService.saveWfRuInstContent(instContentEntity);
                //新增当前任务
                WfTaskEntity task = new WfTaskEntity();
                task.setTaskid(GuidUtils.getGuid());
                task.setPtaskid("0");//前置任务为空
                task.setProcInstId(instEntity.getProcInstId());//流程实例ID
                task.setCActivityid(activityId);//环节编号
                task.setIscurrent("1");//已完成
                task.setCzr(user.getUserid());//操作人
                task.setQssj(date);
                task.setCheck_time(date);
                task.setCheckUser(user.getUserid());
                task.setIscheck("1");//已签收
                task.setHasnew("2");//无子流程
                xkylxService.saveWfTask(task);
            }
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    @ApiOperation(value = "获取资料类型")
    @RequestMapping(value = "getzlTypeOptions",method = RequestMethod.GET)
    public String  getzlTypeOptions(HttpServletRequest request){
        List<CodeModel> list = new ArrayList<CodeModel>();
        list = service.getzlTypeOptions();
        JSONObject jo = new JSONObject();
        jo.put("row",list);
        return jo.toString();
    }

    @ApiOperation(value = "获取用户资料")
    @RequestMapping(value = "initTble",method = RequestMethod.GET)
    public String  initTble(HttpServletRequest request){
        List<CodeModel> list = new ArrayList<CodeModel>();
        List<ProjectModel> projectList = new ArrayList<ProjectModel>();
        String yjfjlx = "";
        String xmid = request.getParameter("xmid");
        if(!StringUtil.isEmpty(xmid)){
            projectList = service.getProjectLists(xmid);
            switch (projectList.get(0).getSqlx()){
                case "10":
                    yjfjlx = "2a52373c3bad43bb9b5236f6ce95455d";
                    break;
                case "20":
                    yjfjlx = "86c0fe78eb204bb5a45cfd64cae13839";
                    break;
                case "30":
                    yjfjlx = "96fd81786e1146b6abfd095cd135c15b";
                    break;
                default:
                    break;
            }
            list = service.getTable(xmid,yjfjlx);
        }
        JSONObject jo = new JSONObject();
        jo.put("row",list);
        return jo.toString();
    }

    @ApiOperation(value = "获取其他用户资料")
    @RequestMapping(value = "getQtzl",method = RequestMethod.GET)
    public String  getQtzl(HttpServletRequest request){
        List<SjstYhzl> list = new ArrayList<SjstYhzl>();
        String xmid = request.getParameter("xmid");
        String fjlx="yhtgzl";
        if(!StringUtil.isEmpty(xmid)){
            list = service.getQtzl(xmid,fjlx);
        }
        JSONObject jo = new JSONObject();
        jo.put("row",list);
        return jo.toString();
    }

    @ApiOperation(value = "删除其他用户资料")
    @RequestMapping(value = "deleteFj",method = RequestMethod.GET)
    public boolean  deleteFj(HttpServletRequest request){
        boolean flag = false;
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        String id = request.getParameter("id");
        try{
            if(!StringUtil.isEmpty(id)){
                service.deleteFj(id,user.getUserid());
                flag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation(value = "删除其他用户资料")
    @RequestMapping(value = "deleteFj_xqsl",method = RequestMethod.GET)
    public boolean  deleteFj_xqsl(HttpServletRequest request){
        boolean flag = false;
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String id = request.getParameter("id");
        try{
            if(!StringUtil.isEmpty(id)){
                service.deleteFj_xqsl(id,user.getUserid());
                logger.info("附件删除人id："+user.getUserid());
                logger.info("附件id："+id);
                logger.info("附件删除时间："+sdf.format(date));
                flag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * 上传用户资料
     * @param multipartFiles
     * @param filename
     * @param request
     * @return
     */
    @ApiOperation(value = "上传用户资料")
    @RequestMapping(value ="importFile",method = RequestMethod.POST, consumes = { "multipart/mixed", "multipart/form-data" })
    public String importEnterPriseQuota(
            @RequestParam(value = "multipartFiles", required = false ) MultipartFile multipartFiles,
            @RequestParam(value = "filename", required = false ) String filename,
            @RequestParam(value = "xmid", required = false ) String xmid,
            @RequestParam(value = "zlType", required = false ) String zlType,
            HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        JSONObject jo = new JSONObject();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateString = formatter.format(currentTime);
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            if (Objects.isNull(multipartFiles)) {
                logger.error("文件为空");
                jo.put("msg","文件为空，请重新上传");
                return jo.toString();
            }
            list = service.getxmList(xmid);
            String fileDir = StringUtil.getBasePath()+"upload"+File.separator+list.get(0).get("XMBH")+File.separator+"sjst"+File.separator;
            System.out.println(fileDir);
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
            SjstYhzl model = new SjstYhzl();
            model.setId(GuidUtils.getGuid());
            model.setXmid(xmid);
            model.setFjlj("upload"+File.separator+list.get(0).get("XMBH")+File.separator+"sjst"+File.separator+filename.split("\\.")[0]+dateString+"."+filename.split("\\.")[filename.split("\\.").length-1]);
            model.setFjhz(filenames[filenames.length-1]);
            model.setFjmc(filename);
            model.setFjlxEj(zlType);
            model.setFjlxYj("yhtgzl");
            model.setScr(user.getUserid());
            service.insertImportFh(model);//保存到上传附件表
        } catch (IOException e) {
            e.printStackTrace();
            jo.put("returncode", "999");
            jo.put("msg", "程序异常，请联系管理员");
            return jo.toString();
        }
        jo.put("returncode", "000");
        jo.put("msg", "上传成功");
        return jo.toString();
    }
    /**
     * 上传用户资料
     * @param request
     * @return
     */
    @ApiOperation(value = "上传用户资料")
    @RequestMapping(value ="sjqzl",method = RequestMethod.GET)
    public String sjqzl(String name,String path,String xmid,String zlType,
            HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        JSONObject jo = new JSONObject();
        Date currentTime = new Date();
        List<Map<String,Object>> list = new ArrayList<>();
        try {

            SjstYhzl model = new SjstYhzl();
            model.setId(GuidUtils.getGuid());
            model.setXmid(xmid);
            model.setFjlj(path);
            String[] names = name.split("\\.");
            model.setFjhz(names[names.length-1]);
            model.setFjmc(name);
            model.setFjlxEj(zlType);
            model.setFjlxYj("yhtgzl");
            model.setScr(user.getUserid());
            service.insertImportFh(model);//保存到上传附件表
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("returncode", "999");
            jo.put("msg", "程序异常，请联系管理员");
            return jo.toString();
        }
        jo.put("returncode", "000");
        jo.put("msg", "上传成功");
        return jo.toString();
    }
    /**
     * 上传用户资料
     * @param multipartFiles
     * @param filename
     * @param request
     * @return
     */
    @ApiOperation(value = "上传用户资料")
    @RequestMapping(value ="importFile_xqsl",method = RequestMethod.POST, consumes = { "multipart/mixed", "multipart/form-data" })
    public String importFile_xqsl(
            @RequestParam(value = "multipartFiles", required = false ) MultipartFile multipartFiles,
            @RequestParam(value = "filename", required = false ) String filename,
            @RequestParam(value = "xmid", required = false ) String xmid,
            @RequestParam(value = "zlType", required = false ) String zlType,
            HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        JSONObject jo = new JSONObject();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            if (Objects.isNull(multipartFiles)) {
                logger.error("文件为空");
                jo.put("msg","文件为空，请重新上传");
                return jo.toString();
            }
            list = service.getxmList(xmid);
            String fileDir = StringUtil.getBasePath()+"upload"+File.separator+list.get(0).get("XMBH")+File.separator+"xqsl"+File.separator;
            System.out.println(fileDir);
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
            String id = GuidUtils.getGuid();
            pd.put("id",id);//主键标识
            pd.put("fjlj","upload"+File.separator+list.get(0).get("XMBH")+File.separator+"xqsl"+File.separator+filename.split("\\.")[0]+dateString+"."+filename.split("\\.")[filename.split("\\.").length-1]);//附件路径
            pd.put("fjhz",filenames[filenames.length-1]);//附件后缀
            pd.put("operation",zlType);
            pd.put("xmid",xmid);
            //根据附件标识查询附件类型名称
            List<Map<String,Object>> fjlx = xqslService.findFjlxById(pd);
            String fjlx_yj = String.valueOf(fjlx.get(0).get("PARENTID"));
            String fjlx_ej = String.valueOf(fjlx.get(0).get("ID"));
            pd.put("fjlx_yj",fjlx_yj);//一级附件类型
            pd.put("fjlx_ej",fjlx_ej);//一级附件类型
            pd.put("fjmc",filename);//附件名称
            pd.put("scr",user.getUserid());//创建人
            service.insertImportFh_xqsl(pd);//保存到上传附件表
            logger.info("附件上传人id："+user.getUserid());
            logger.info("修改项目id："+xmid);
            logger.info("一级附件类型："+fjlx_yj);
            logger.info("二级附件类型："+fjlx_ej);
            logger.info("附件名称："+filename);
            logger.info("附件上传时间："+sdf.format(currentTime));
        } catch (IOException e) {
            e.printStackTrace();
            jo.put("returncode", "999");
            jo.put("msg", "程序异常，请联系管理员");
            return jo.toString();
        }
        jo.put("returncode", "000");
        jo.put("msg", "上传成功");
        return jo.toString();
    }

    @ApiOperation(value = " 上传")
    @RequestMapping(value ="saveImageToFj_xqsl",method = RequestMethod.GET)
    @ResponseBody
    public String saveImageToFj_xqsl(String dirName,String fileName,String operation,String xmid,
                                HttpServletRequest request){
        String id = "";
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        org.activiti.engine.impl.util.json.JSONObject jo = new org.activiti.engine.impl.util.json.JSONObject();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        Map<String,Object> pd = new HashMap<>();
        String fileDir = StringUtil.getBasePath()+"upload"+File.separator+"xqsl"+File.separator;
        try{
            id = GuidUtils.getGuid();
            pd.put("id",id);//主键标识
            pd.put("operation",operation);
            pd.put("xmid",xmid);
            //根据附件标识查询附件类型名称
            List<Map<String,Object>> fjlx = xqslService.findFjlxById(pd);
            String fjlx_yj = String.valueOf(fjlx.get(0).get("PARENTID"));
            String fjlx_ej = String.valueOf(fjlx.get(0).get("ID"));
            pd.put("fjlx_yj",fjlx_yj);//一级附件类型
            pd.put("fjlx_ej",fjlx_ej);//一级附件类型
            pd.put("fjlj","upload"+File.separator+dirName+File.separator+fileName);//附件路径
            pd.put( "fjhz",fileName.substring(fileName.indexOf(".")+1) );//附件后缀
            pd.put("fjmc",fileName);//附件名称
            pd.put("scr",user.getUserid());//创建人
            service.insertImportFh_xqsl(pd);//保存到上传附件表
            logger.info("附件上传人id："+user.getUserid());
            logger.info("修改项目id："+xmid);
            logger.info("一级附件类型："+fjlx_yj);
            logger.info("二级附件类型："+fjlx_ej);
            logger.info("附件名称："+fileName);
            logger.info("附件上传时间："+sdf.format(currentTime));
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("msg", "上传成功");
        jo.put("fileId",id);
        jo.put("path",fileDir+dirName+"/"+fileName);
        return jo.toString();
    }

    /**
     * 下载用户资料
     * @param path
     * @param response
     * @return
     */
    @ApiOperation(value = "获取其他用户资料")
    @RequestMapping(value = "download",method = RequestMethod.POST)
    public HttpServletResponse download(String path,String type, HttpServletRequest request,HttpServletResponse response) {
        try {
            String basePath = StringUtil.getBasePath();
            String params = request.getParameter("params");
            String paths = request.getParameter("path");
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
     * 委托设计
     * @param xmid
     * @param activitiId
     * @return
     */
    @ApiOperation(value = "委托设计")
    @RequestMapping(value = "wtsj",method = RequestMethod.GET)
    public int wtsj(String xmid, String activitiId,String bz ,HttpServletRequest request,String type,int sjsx) {
        UserEntity user = (UserEntity)request.getSession().getAttribute("user");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //这里的格式可以自己设置
        int flag = 0;//0：其他异常；1：正常；-1：已发起委托设计；-2：缺失任务办理人
        CalculateHours ch = new CalculateHours();
        List<WfRuInstEntity> instList = new ArrayList<WfRuInstEntity>();
        List<Map<String,Object>> rwblrList = new ArrayList<>();
        int direction = 0;
        try{
            instList = service.getInstList(xmid,activitiId,direction,type);
            rwblrList = service.getRwblr(activitiId,direction);
            if(instList==null||instList.size()<=0){
                if(rwblrList!=null&&rwblrList.size()>0){

                    WfRuInstEntity instEntity = new WfRuInstEntity();
                    instEntity.setProcInstId(GuidUtils.getGuid());
                    instEntity.setPdefId(activitiId);
                    instEntity.setStartuser(user.getUserid());
                    instEntity.setProcstatus(1);
                    instEntity.setStartdt(date);
                    xkylxService.saveWfRuInst(instEntity);
                    WfRuInstContentEntity instContentEntity = new WfRuInstContentEntity();
                    instContentEntity.setContentid(GuidUtils.getGuid());
                    instContentEntity.setProcInstId(instEntity.getProcInstId());
                    instContentEntity.setTableName("xm_instance");
                    instContentEntity.setContentname("id");
                    instContentEntity.setContentvalue(xmid);
                    instContentEntity.setContenttype(type);
                    xkylxService.saveWfRuInstContent(instContentEntity);
                    //新增当前任务
                    WfTaskEntity task = new WfTaskEntity();
                    task.setTaskid(GuidUtils.getGuid());
                    task.setPtaskid("0");//前置任务为空
                    task.setProcInstId(instEntity.getProcInstId());//流程实例ID
                    task.setCActivityid(activitiId);//环节编号
                    task.setIscurrent("1");//已完成
                    task.setCzr(user.getUserid());//操作人
                    task.setQssj(date);
                    task.setCheck_time(date);
                    task.setCheckUser(user.getUserid());
                    task.setIscheck("1");//已签收
                    task.setHasnew("2");//无子流程
                    task.setRwblr(user.getUserid());
                    xkylxService.saveWfTask(task);
                    //创建设计审图任务
                    SjstRwModel sjstRwModel = new SjstRwModel();
                    sjstRwModel.setRwbh(getKey("SJST",0));
                    sjstRwModel.setId(GuidUtils.getGuid());
                    sjstRwModel.setFqr(user.getUserid());
                    sjstRwModel.setBz(bz);
                    sjstRwModel.setSjstzt("1");
                    sjstRwModel.setSjsx(sjsx);
                    service.saveSjstRw(sjstRwModel);
                    //创建项目与设计审图关系表
                    XmSjstRela xmSjstRela = new XmSjstRela();
                    xmSjstRela.setId(GuidUtils.getGuid());
                    xmSjstRela.setXmid(xmid);
                    xmSjstRela.setSjstid(sjstRwModel.getId());
                    service.saveXmSjstRela(xmSjstRela);

                    //查询当前任务
                    WfTaskEntity nowTask = service.getNowTask(xmid, activitiId);
                    //将当前任务存入历史
                    WfTaskHistoryEntity historyTask = new WfTaskHistoryEntity();
                    historyTask.setTaskid(nowTask.getTaskid());
                    historyTask.setPtaskid(nowTask.getPtaskid());//前置任务为空
                    historyTask.setProcInstId(nowTask.getProcInstId());//流程实例ID
                    historyTask.setCActivityid(nowTask.getCActivityid());//环节编号
                    historyTask.setIscurrent("0");//已完成
                    historyTask.setCzr(user.getUserid());//操作人
                    historyTask.setQssj(nowTask.getQssj());
                    historyTask.setJssj(date);
                    historyTask.setCheckTime(nowTask.getCheck_time());
                    historyTask.setCheckUser(nowTask.getCheckUser());
                    historyTask.setIscheck(nowTask.getIscheck());//已签收
                    historyTask.setHasnew(nowTask.getHasnew());//无子流程
                    historyTask.setHtyy(nowTask.getHtyy());
                    historyTask.setRwblr(nowTask.getRwblr());
                    xkylxService.saveWfTaskHistory(historyTask);

                    //新增下一任务
                    WfTaskEntity nexttask = getNextTaskEntity(xmid,activitiId,direction);
                    //根据环节编号查询环节定义数据
                    WfDfActivityEntity activityEntity = service.getActivityEntity(nexttask.getCActivityid());
                    nexttask.setTaskid(GuidUtils.getGuid());
                    nexttask.setIscurrent("1");//未完成
                    nexttask.setCzr(user.getUserid());//操作人
                    nexttask.setQssj(date);
                    nexttask.setCheckUser(user.getUserid());
                    nexttask.setIscheck("0");//未签收
                    nexttask.setRefkey("sjst_rwb");
                    nexttask.setRefid(sjstRwModel.getId());
                    if(("1").equals(activityEntity.getCheckLimited())){
                        nexttask.setCheck_sx(ch.getEndTime(format.format(date), Float.parseFloat(activityEntity.getCheckMinute())));
                    }else{
                        nexttask.setCheck_sx(DateUtils.stringToDate("2099-12-31 23:59:59"));
                    }
                    if(("1").equals(activityEntity.getFinishLimited())){
                        nexttask.setJssx(ch.getEndTime(format.format(date), Float.parseFloat(activityEntity.getYellowFinish())));
                        nexttask.setJssx_yl(ch.getEndTime(format.format(date), Float.parseFloat(activityEntity.getFinishMinute())+Float.parseFloat(activityEntity.getYellowFinish())));
                    }else{
                        nexttask.setJssx(DateUtils.stringToDate("2099-12-31 23:59:59"));
                        nexttask.setJssx_yl(DateUtils.stringToDate("2099-12-31 23:59:59"));
                    }
                    nexttask.setHasnew("2");//无子流程
                    xkylxService.saveWfTask(nexttask);
                    //修改实例中流程定义id
                    service.updateInst(xmid, activitiId, direction,sjstRwModel.getRwbh());
                    //删除之前任务
                    service.deleteTask(nowTask.getTaskid());
                    flag = 1;
                }else{
                    flag=-2;
                }
            }else{
                flag = -1;
            }
        }catch (Exception ec){
            ec.printStackTrace();
        }
        return flag;
    }

    @ApiOperation(value = "任务提醒发送短信")
    @RequestMapping(value = "rwtxfsdx",method = RequestMethod.GET)
    public void  rwtxfsdx(HttpServletRequest request,String xmid,String activitiId,int direction){
        DxDfsEntity dxDfsEntity = new DxDfsEntity();//初始化短信模版
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        List<Map<String,Object>> list = new ArrayList<>();
        List<Map<String,Object>> dxList = new ArrayList<>();
        String content = "";
        String[] contentList;
        try {
            if(direction!=3){
                list = service.getRwtxfsdx(xmid,activitiId,direction);
            }else{
                list = service.getTaskRwtxfsdx(xmid,activitiId,direction);
            }
            dxList = service.getdxList(xmid,activitiId,direction,(String)list.get(0).get("REFKEY"),(String)list.get(0).get("REFID"));
            Map<String, Object> dxmb = messageService.getDxMb("4");
            content = dxmb.get("DXMB").toString();
            content = content.replace("[环节名称类型]", dxList.get(0).get("ACTIVITYNAME").toString());
            content =   content.replace("[任务单编号]", dxList.get(0).get("RWBH").toString());
            content =  content.replace("[项目名称]", dxList.get(0).get("XMMC").toString());
            dxDfsEntity.setId(GuidUtils.getGuid());//标识
            dxDfsEntity.setDxmbid("4");//短信模版标识
            if(direction!=3){
                dxDfsEntity.setTaskid((String)list.get(0).get("REFID"));
            }else{
                contentList = content.split("，");
                content = contentList[0]+"已完成，"+contentList[1];
            }
            dxDfsEntity.setFsr(user.getUserid());//发送人
            dxDfsEntity.setFssj(new Date());//发送时间
            dxDfsEntity.setFscs("1");//发送次数
            dxDfsEntity.setFsnr(content);//发送内容
            dxDfsEntity.setJsr((String)list.get(0).get("RWBLR"));//接收人
            dxDfsEntity.setJshm((String)list.get(0).get("MOBILEPHONE"));//接收号码
            service.saveDxDfsEntity(dxDfsEntity);//保存
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "延期申请发送短信")
    @RequestMapping(value = "fsdx",method = RequestMethod.GET)
    public void  fsdx(HttpServletRequest request,String id){
        DxDfsEntity dxDfsEntity = new DxDfsEntity();//初始化短信模版
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        List<Map<String,Object>> list = new ArrayList<>();
        List<Map<String,Object>> dxList = new ArrayList<>();
        String content = "";
        try {
            list = service.getdelayList(id);
            Map<String, Object> dxmb = messageService.getDxMb("8");
            content = dxmb.get("DXMB").toString();
            content = content.replace("[项目名称]", list.get(0).get("XMMC").toString());
            content =   content.replace("[项目编号]", list.get(0).get("XMBH").toString());
            content =  content.replace("[环节名称类型]", list.get(0).get("ACTIVITYNAME").toString());
            content =  content.replace("[申请人]", list.get(0).get("SQRNAME").toString());
            content =  content.replace("[申请时间]", list.get(0).get("SQSJ").toString());
            dxDfsEntity.setId(GuidUtils.getGuid());//标识
            dxDfsEntity.setDxmbid("8");//短信模版标识
            dxDfsEntity.setTaskid((String)list.get(0).get("TASKID"));
            dxDfsEntity.setFsr(user.getUserid());//发送人
            dxDfsEntity.setFssj(new Date());//发送时间
            dxDfsEntity.setFscs("1");//发送次数
            dxDfsEntity.setFsnr(content);//发送内容
            dxDfsEntity.setJsr((String)list.get(0).get("SPR"));//接收人
            dxDfsEntity.setJshm((String)list.get(0).get("SPRDH"));//接收号码
            service.saveDxDfsEntity(dxDfsEntity);//保存
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @ApiOperation(value = "延期审批发送短信")
    @RequestMapping(value = "spfsdx",method = RequestMethod.GET)
    public void  spfsdx(HttpServletRequest request,String id){
        DxDfsEntity dxDfsEntity = new DxDfsEntity();//初始化短信模版
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        List<Map<String,Object>> list = new ArrayList<>();
        List<Map<String,Object>> dxList = new ArrayList<>();
        String content = "";
        try {
            list = service.getdelayList(id);
            Map<String, Object> dxmb = messageService.getDxMb("12");
            content = dxmb.get("DXMB").toString();
            content = content.replace("[项目名称]", list.get(0).get("XMMC").toString());
            content =   content.replace("[项目编号]", list.get(0).get("XMBH").toString());
            content =  content.replace("[环节名称类型]", list.get(0).get("ACTIVITYNAME").toString());
            content =  content.replace("[是否通过]", ("1").equals(list.get(0).get("SPSFTY").toString())?"同意":"不同意");
            content =  content.replace("[审批人]", list.get(0).get("SPRNAME").toString());
            content =  content.replace("[审批时间]", list.get(0).get("SPSJ_STR").toString());
            dxDfsEntity.setId(GuidUtils.getGuid());//标识
            dxDfsEntity.setDxmbid("12");//短信模版标识
            dxDfsEntity.setTaskid((String)list.get(0).get("TASKID"));
            dxDfsEntity.setFsr(user.getUserid());//发送人
            dxDfsEntity.setFssj(new Date());//发送时间
            dxDfsEntity.setFscs("1");//发送次数
            dxDfsEntity.setFsnr(content);//发送内容
            dxDfsEntity.setJsr((String)list.get(0).get("SQR"));//接收人
            dxDfsEntity.setJshm((String)list.get(0).get("SQRDH"));//接收号码
            service.saveDxDfsEntity(dxDfsEntity);//保存
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 查询下一环节编号，流程实例，前置任务id
     * @param activitiId
     * @return
     */
    public WfTaskEntity getNextTaskEntity(String xmid,String activitiId,int direction){
        List<WfTaskEntity> list = new ArrayList<WfTaskEntity>();
        list = service.getNextTaskEntity(xmid,activitiId,direction);
        return list.get(0);
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

    /*---------------------------------------------------------------*/
    //
    @ApiOperation(value = "获取设计任务分派列表")
    @RequestMapping(value = "getSjrwfpXmlb",method = RequestMethod.GET)
    public String  getSjrwfpXmlb(HttpServletRequest request,ProjectModel model){
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        boolean flag = false;
        int total = 0;
        List<WfActivityRoleEntity> roleList = new ArrayList<WfActivityRoleEntity>();
        List<ProjectModel> list = new ArrayList<ProjectModel>();
        roleList = service.getRoleList(model.getActivityId(),user.getUserid());
        if(roleList!=null&&roleList.size()>0){
            try {
                model.setStartRow();
                model.setEndRow();
                total = service.getSjrwfpXmlbCount(model);
                if(total>0){
                    list = service.getSjrwfpXmlbList(model);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        JSONObject jo = new JSONObject();
        jo.put("total",total);
        jo.put("row",list);
        return jo.toString();
    }

    @ApiOperation(value = "设计院签收")
    @RequestMapping(value = "goSjrwfp",method = RequestMethod.GET)
    public boolean  goSjrwfp(HttpServletRequest request,String xmid,String activityId){
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        boolean flag = false;
        Date date = new Date();
        try{
            //查询当前任务
            WfTaskEntity nowTask = service.getNowTask(xmid, activityId);
            nowTask.setIscheck("1");
            nowTask.setCheck_time(date);
            nowTask.setCheckUser(user.getUserid());
            service.updateTask(nowTask);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    @ApiOperation(value = "资料分类查询")
    @RequestMapping(value = "getZlfl",method = RequestMethod.GET)
    public String  getZlfl(HttpServletRequest request,String xmid){
        List<CodeModel> list = service.getZlfl(xmid);
        JSONObject jo = new JSONObject();
        jo.put("row",list);
        return jo.toString();
    }

    @ApiOperation(value = "资料详情")
    @RequestMapping(value = "goDetail",method = RequestMethod.GET)
    public String  goDetail(HttpServletRequest request,String id,String xmid){
        List<SjstYhzl> list = new ArrayList<SjstYhzl>();
        if(!StringUtil.isEmpty(id)){
            if(("qtzl").equals(id)){
               list =  service.getQtzl(xmid,id);
            }else{
                list = service.getYhzl(xmid,id);
            }
        }
        JSONObject jo = new JSONObject();
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "资料详情")
    @RequestMapping(value = "toDetial",method = RequestMethod.GET)
    public String  toDetial(HttpServletRequest request,String yjfjlx,String ejfjlx,String xmid){
        List<SjstYhzl> list = new ArrayList<SjstYhzl>();
        if(!StringUtil.isEmpty(yjfjlx)){
            if(("yhtgzl").equals(yjfjlx)){
                list =  service.getXmjlQtzl(xmid,ejfjlx);
            }else if("htqd".equals(yjfjlx)){
                list = service.getHtqdzl(xmid,ejfjlx);
            }else{
                list = service.getYhzl(xmid,ejfjlx);
            }
        }
        JSONObject jo = new JSONObject();
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "资料详情")
    @RequestMapping(value = "toDetial_xqsl",method = RequestMethod.GET)
    public String  toDetial_xqsl(HttpServletRequest request,String yjfjlx,String ejfjlx,String xmid,
                                 Integer page,Integer size){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        int startRow=(page - 1) * size + 1;
        int endRow=page * size;
        pd.put("yjfjlx",yjfjlx);
        pd.put("ejfjlx",ejfjlx);
        pd.put("startRow",startRow);
        pd.put("endRow",endRow);
        pd.put("xmid",xmid);
        List<Map<String,Object>> list = new ArrayList<>();
        int total = 0;
        try {
            total = service.gettoDetial_xqslCount(pd);
            if(total>0){
                list = service.gettoDetial_xqslList(pd);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        jo.put("total",total);
        return jo.toString();
    }

    /***
     * 获取指派人员名单
     * @return
     */
    @RequestMapping(value = "getZpry",method = RequestMethod.GET)
    @ResponseBody
    private String getZpry(String roleid,String xmid){
        JSONObject jo = new JSONObject();
        List<Map<String,Object>> zpry = service.getZpry(roleid,xmid);
        List<Map<String,Object>> sjstrwList = service.getsjstrwList(xmid);
        //获取默认指派人员
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("v_XMID",xmid);
        map.put("v_Type","1");
        map.put("i_result","");
        service.getMrzpryid(map);
        jo.put("mrzpry",map.get("i_result"));
        jo.put("zpry",zpry);
        jo.put("bz",sjstrwList.get(0).get("BZ"));
        return jo.toString();
    }

    /**
     * 设计任务分派
     * @param xmid
     * @param activityId
     * @return
     */
    @ApiOperation(value = "设计任务分派")
    @RequestMapping(value = "sjrwfpSubmit",method = RequestMethod.GET)
    public boolean sjrwfpSubmit(String xmid, String activityId,String sjzr ,HttpServletRequest request) {
        UserEntity user = (UserEntity)request.getSession().getAttribute("user");
        Date date = new Date();
        boolean flag = false;
        List<SjstRwModel> list = new ArrayList<>();
        CalculateHours ch = new CalculateHours();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int direction = 0;
        try{
            //修改实例中流程定义id
            service.updateInst(xmid, activityId,direction,"");
            //查询当前任务
            WfTaskEntity nowTask = service.getNowTask(xmid, activityId);
            //将当前任务存入历史
            WfTaskHistoryEntity historyTask = new WfTaskHistoryEntity();
            historyTask.setTaskid(nowTask.getTaskid());
            historyTask.setPtaskid(nowTask.getPtaskid());//前置任务为空
            historyTask.setProcInstId(nowTask.getProcInstId());//流程实例ID
            historyTask.setCActivityid(nowTask.getCActivityid());//环节编号
            historyTask.setIscurrent("0");//已完成
            historyTask.setCzr(user.getUserid());//操作人
            historyTask.setQssj(nowTask.getQssj());
            historyTask.setJssj(date);
            historyTask.setJssx(nowTask.getJssx());
            historyTask.setJssxYl(nowTask.getJssx_yl());
            historyTask.setCheckTime(nowTask.getCheck_time());
            historyTask.setCheckUser(nowTask.getCheckUser());
            historyTask.setIscheck(nowTask.getIscheck());//已签收
            historyTask.setHasnew(nowTask.getHasnew());//无子流程
            historyTask.setRefkey(nowTask.getRefkey());
            historyTask.setRefid(nowTask.getRefid());
            historyTask.setRwblr(nowTask.getRwblr());
            historyTask.setHtyy(nowTask.getHtyy());
            xkylxService.saveWfTaskHistory(historyTask);
//获取设计审图任务
            list = service.getSjstrw(xmid);
            //新增下一任务
            WfTaskEntity task = getNextTaskEntity(xmid,activityId,direction);
            //根据环节编号查询环节定义数据
            WfDfActivityEntity activityEntity = service.getActivityEntity(task.getCActivityid());
            task.setPtaskid(nowTask.getTaskid());
            task.setTaskid(GuidUtils.getGuid());
            task.setIscurrent("1");//未完成
            task.setCzr(user.getUserid());//操作人
            task.setQssj(date);

            task.setCheckUser(user.getUserid());
            task.setIscheck("0");//未签收
            if(("1").equals(activityEntity.getCheckLimited())){
                task.setCheck_sx(ch.getEndTime(format.format(date), Float.parseFloat(activityEntity.getCheckMinute())));
            }
            task.setJssx(ch.getEndTime(format.format(date), list.get(0).getSjsx()*7*60*2/3));
//            task.setJssx_yl(ch.getEndTime(format.format(date), Float.parseFloat(activityEntity.getFinishMinute())+Float.parseFloat(activityEntity.getYellowFinish())));
            task.setJssx_yl(ch.getEndTime(format.format(date),  list.get(0).getSjsx()*7*60));
            task.setHasnew("2");//无子流程
            task.setRefkey(nowTask.getRefkey());
            task.setRefid(nowTask.getRefid());
            task.setRwblr(sjzr);
            xkylxService.saveWfTask(task);
            //删除之前任务
            service.deleteTask(nowTask.getTaskid());
            //修改设计审图任务
            SjstRwModel sjstRwModel = new SjstRwModel();
            sjstRwModel.setFpr(user.getUserid());
            sjstRwModel.setSjr(sjzr);
            sjstRwModel.setXmid(xmid);
            service.updateSjstRw(sjstRwModel);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    @ApiOperation(value = "获取设计任务列表（设计人员）")
    @RequestMapping(value = "getSjrySjrwXmlb",method = RequestMethod.GET)
    public String  getSjrySjrwXmlb(HttpServletRequest request,ProjectModel model){
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        boolean flag = false;
        int total = 0;
        List<ProjectModel> list = new ArrayList<ProjectModel>();
            try {
                model.setStartRow();
                model.setEndRow();
                model.setUserid(user.getUserid());
                total = service.getSjrySjrwXmlbCount(model);
                if(total>0){
                    list = service.getSjrySjrwXmlbList(model);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        JSONObject jo = new JSONObject();
        jo.put("total",total);
        jo.put("row",list);
        return jo.toString();
    }


    @ApiOperation(value = "转单按钮是否显示")
    @RequestMapping(value = "buttonshow",method = RequestMethod.GET)
    public boolean  buttonshow(HttpServletRequest request,String activityId,int direction){
        boolean flag = false;
        List<Map<String,Object>> list = new ArrayList<>();
        list = service.buttonshow(activityId,direction);
        if(list.size()>0){
            flag = true;
        }
        return flag;
    }

    /***
     * 获取指派人员名单
     * @return
     */
    @RequestMapping(value = "getzdry",method = RequestMethod.GET)
    @ResponseBody
    private String getzdry(String roleid,HttpServletRequest request,String xmid){
        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        List<Map<String,Object>> zpry = service.getZpry(roleid,xmid);
        for(int i=0;i<zpry.size();i++){
            if(zpry.get(i).get("USERID").equals(user.getUserid())){
                zpry.get(i).put("DISABLED",true);
            }else{
                zpry.get(i).put("DISABLED",false);
            }
        }
        jo.put("zpry",zpry);
        return jo.toString();
    }


    /***
     * 获取指派人员名单
     * @return
     */
    @RequestMapping(value = "zdsubmit",method = RequestMethod.GET)
    @ResponseBody
    private boolean zdsubmit(String xmid,String activityId,String radio,HttpServletRequest request){
        boolean flag = false;
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try{
            service.updatezdTask(xmid,activityId,radio);
            flag=true;
            logger.info("指派人id："+user.getUserid());
            logger.info("被指派人id："+radio);
            logger.info("转单时间："+sdf.format(date));
            logger.info("转单项目id："+xmid);
            logger.info("转单活动id："+activityId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }


    @ApiOperation(value = "资料总览")
    @RequestMapping(value = "getZlzl",method = RequestMethod.GET)
    public String  getZlzl(HttpServletRequest request,String xmid,String type){
        List<CodeModel> list = new ArrayList<CodeModel>();
        List<CodeModel> listfomat = new ArrayList<CodeModel>();
        List<ProjectModel> projectList = new ArrayList<ProjectModel>();
        String yjfjlx="";
        if(!StringUtil.isEmpty(xmid)){
            projectList = service.getProjectLists(xmid);
            switch (projectList.get(0).getSqlx()){
                case "10":
                    yjfjlx = "2a52373c3bad43bb9b5236f6ce95455d";
                    break;
                case "20":
                    yjfjlx = "86c0fe78eb204bb5a45cfd64cae13839";
                    break;
                case "30":
                    yjfjlx = "96fd81786e1146b6abfd095cd135c15b";
                    break;
                default:
                    break;
            }
            list = service.getZlzl(xmid,type,yjfjlx);
        }
        if(list!=null&&list.size()>0){
            for (CodeModel codeModel:list
                 ) {
                if(StringUtil.isEmpty(codeModel.getParentid())){
                    listfomat.add(codeModel);
                }
            }
            for (CodeModel codefomatModel:listfomat
                    ) {
                List<CodeModel> listChildren = new ArrayList<CodeModel>();
                for (CodeModel codeModels:list
                        ) {
                    if(!StringUtil.isEmpty(codeModels.getParentid())){
                        if(codeModels.getParentid().equals(codefomatModel.getId())){
                            listChildren.add(codeModels);

                        }
                    }
                }
                codefomatModel.setChildren(listChildren);
            }
        }
        JSONObject jo = new JSONObject();
        jo.put("row",listfomat);
        return jo.toString();
    }

    @ApiOperation(value = "初始化设计时限")
    @RequestMapping(value = "initSjsx",method = RequestMethod.GET)
    public String  initSjsx(String xmid){
        JSONObject jo = new JSONObject();
        List<SjstRwModel> sjstRwModelList = new ArrayList<>();
        List<SjstSxpzb> list = new ArrayList<>();
        List<SjstSxpzb> bqgList = new ArrayList<>();
        List<SjstSxpzb> szdlList = new ArrayList<>();
        List<SjstSxpzb> xjxqList = new ArrayList<>();
        List<SjstSxpzb> chgzList = new ArrayList<>();
        List<SjstSxpzb> glList = new ArrayList<>();
        float sjsx= 0.0f;
        String bz="";
        list = service.initSjsx();
        //获取设计审图任务
        sjstRwModelList = service.getSjstrw(xmid);
        if(sjstRwModelList!=null&&sjstRwModelList.size()>0){
            sjsx = sjstRwModelList.get(0).getSjsx();
            bz=sjstRwModelList.get(0).getBz();
        }
        for (SjstSxpzb model:list
             ) {
            if(("1").equals(model.getPz_type())){
                szdlList.add(model);
            }
            if(("2").equals(model.getPz_type())){
                xjxqList.add(model);
            }
            if(("3").equals(model.getPz_type())){
                bqgList.add(model);
            }
            if(("4").equals(model.getPz_type())){
                chgzList.add(model);
            }
            if(("5").equals(model.getPz_type())){
                glList.add(model);
            }
        }
        jo.put("sjsx",sjsx);
        jo.put("bqgList",bqgList);
        jo.put("xjxqList",xjxqList);
        jo.put("szdlList",szdlList);
        jo.put("chgzList",chgzList);
        jo.put("glList",glList);
        jo.put("bz",bz);
        return jo.toString();
    }


    /**
     * 获取编号
     * @param mc
     * @return
     */
    public String getKey(String mc,int type){
        Map<String,Object> map = new HashMap<String,Object>();
        String key = "";
        map.put("mc",mc);
        map.put("key","");
        service.getKey(map);
        if(type==1){
            key= (String) map.get("key");
        }else {
            key = mc+map.get("key");
        }
        return key;
    }

    @ApiOperation(value = "庭院信息维护")
    @RequestMapping(value = "tyxxwh",method = RequestMethod.GET)
    public boolean  tyxxwh(HttpServletRequest request,TyModel model){
        boolean flag = false;
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        try{
            if(StringUtil.isEmpty(model.getBh())){
                model.setBh(getKey("TY",0));
                model.setCjr(user.getUserid());
                model.setId(GuidUtils.getGuid());
                service.addTY(model);
                flag=true;
            }else{
                service.updateTY(model);
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;

    }

    @ApiOperation(value = "初始化庭院")
    @RequestMapping(value = "initTYTable",method = RequestMethod.GET)
    public String  initTYTable(HttpServletRequest request,String xmid){
        List<TyModel> list = new ArrayList<TyModel>();
        if(!StringUtil.isEmpty(xmid)){
            list = service.initTYTable(xmid);
        }
        JSONObject jo = new JSONObject();
        jo.put("row",list);
        return jo.toString();
    }

    @ApiOperation(value = "初始化地下室")
    @RequestMapping(value = "initDXSTable",method = RequestMethod.GET)
    public String  initDXSTable(HttpServletRequest request,String xmid){
        List<DxsModel> list = new ArrayList<DxsModel>();
        if(!StringUtil.isEmpty(xmid)){
            list = service.initDXSTable(xmid);
        }
        JSONObject jo = new JSONObject();
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "地下室信息维护")
    @RequestMapping(value = "dxsxxwh",method = RequestMethod.GET)
    public boolean  dxsxxwh(HttpServletRequest request,DxsModel model){
        boolean flag = false;
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        try{
            if(StringUtil.isEmpty(model.getBh())){
                model.setBh(getKey("DXS",0));
                model.setCjr(user.getUserid());
                model.setId(GuidUtils.getGuid());
                service.addDXS(model);
                flag=true;
            }else{
                service.updateDXS(model);
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;

    }

    @ApiOperation(value = "初始化泵房")
    @RequestMapping(value = "initBFTable",method = RequestMethod.GET)
    public String  initBFTable(HttpServletRequest request,String xmid){
        List<BfModel> list = new ArrayList<BfModel>();
        if(!StringUtil.isEmpty(xmid)){
            list = service.initBFTable(xmid);
        }
        JSONObject jo = new JSONObject();
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "泵房信息维护")
    @RequestMapping(value = "bfxxwh",method = RequestMethod.GET)
    public boolean  bfxxwh(HttpServletRequest request,BfModel model){
        boolean flag = false;
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        try{
            if(StringUtil.isEmpty(model.getBh())){
                model.setBh(getKey("BF",0));
                model.setCjr(user.getUserid());
                model.setId(GuidUtils.getGuid());
                service.addBF(model);
                flag=true;
            }else{
                service.updateBF(model);
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;

    }

    @ApiOperation(value = "初始化泵房选择器")
    @RequestMapping(value = "initBFOption",method = RequestMethod.GET)
    public String  initBFOption(HttpServletRequest request,String xmid){
       List<CodeModel> list = new ArrayList<>();
       list = service.initBFOption(xmid);
        JSONObject jo = new JSONObject();
        jo.put("row",list);
        return jo.toString();
    }

    @ApiOperation(value = "初始化水箱")
    @RequestMapping(value = "initSXTable",method = RequestMethod.GET)
    public String  initSXTable(HttpServletRequest request,String xmid){
        List<XmSx> list = new ArrayList<XmSx>();
        if(!StringUtil.isEmpty(xmid)){
            list = service.initSXTable(xmid);
        }
        JSONObject jo = new JSONObject();
        jo.put("row",list);
        return jo.toString();
    }

    @ApiOperation(value = "水箱信息维护")
    @RequestMapping(value = "sxxxwh",method = RequestMethod.GET)
    public boolean  sxxxwh(HttpServletRequest request,XmSx model){
        boolean flag = false;
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        try{
            if(StringUtil.isEmpty(model.getSxbh())){
                model.setSxbh(getKey("SX",0));
                model.setCjr(user.getUserid());
                model.setId(GuidUtils.getGuid());
                model.setCjsj(new Date());
                service.addSX(model);
                flag=true;
            }else{
                service.updateSX(model);
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation(value = "官网信息维护")
    @RequestMapping(value = "gwxxwh",method = RequestMethod.POST)
    public boolean  gwxxwh(HttpServletRequest request,GdModel model){
        boolean flag = false;
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        String glgModelListString = model.getGlgModelList();
        JSONArray jsonArray = JSONArray.parseArray(glgModelListString);
        List<GlgModel> glgModelList = new ArrayList<GlgModel>();
        for (int i = 0; i < jsonArray.size(); i++) {
            GlgModel glgmodel = new GlgModel();
            glgmodel.setId(jsonArray.getJSONObject(i).getString("id"));
            glgmodel.setXmgdid(jsonArray.getJSONObject(i).getString("xmgdid"));
            glgmodel.setGlgbh(jsonArray.getJSONObject(i).getString("glgbh"));
            glgmodel.setGlgkj(jsonArray.getJSONObject(i).getString("glgkj"));
            glgmodel.setGlgmc(jsonArray.getJSONObject(i).getString("glgmc"));
            glgmodel.setSl(jsonArray.getJSONObject(i).getInteger("sl"));
            glgModelList.add(glgmodel);
        }
        try{
            if(StringUtil.isEmpty(model.getGdbh())){
                model.setGdbh(getKey("GW",0));
                model.setCjr(user.getUserid());
                model.setId(GuidUtils.getGuid());
                model.setCjsj(new Date());
                for (GlgModel glgModel: glgModelList
                     ) {
                    glgModel.setId(GuidUtils.getGuid());
                    glgModel.setGlgbh(getKey("GLG",0));
                    glgModel.setXmgdid(model.getId());
                    service.addGlg(glgModel);
                }
                service.addGW(model);
                flag=true;
            }else{
                for (GlgModel glgModel: glgModelList
                        ) {
                    if(StringUtil.isEmpty(glgModel.getGlgbh())){
                        glgModel.setId(GuidUtils.getGuid());
                        glgModel.setGlgbh(getKey("GLG",0));
                        glgModel.setXmgdid(model.getId());
                    }
                    service.addGlg(glgModel);
                }
                model.setCjsj(DateUtils.stringToDate(model.getCjsj_vue()));
                service.updateGW(model);
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation(value = "管网字段查询")
    @RequestMapping(value = "initGWTable",method = RequestMethod.GET)
    public String  initGWTable(String xmid){
        List<GdModel> gdList = new ArrayList<GdModel>();
        gdList = service.getGdList(xmid);
        JSONObject jo = new JSONObject();
        jo.put("row",gdList);
        return jo.toString();
    }


    @ApiOperation(value = "根据id查询管道")
    @RequestMapping(value = "GWHandleEdit",method = RequestMethod.GET)
    public String  GWHandleEdit(String id){
        List<GdModel> gdList = new ArrayList<GdModel>();
        List<GlgModel> glgList = new ArrayList<GlgModel>();
        gdList = service.getGdListByid(id);
        glgList = service.getGlgList(id);
        JSONObject jo = new JSONObject();
        jo.put("gdList",gdList);
        jo.put("glgList",glgList);
        return jo.toString();
    }

    @ApiOperation(value = "删除管道")
    @RequestMapping(value = "GWHandleDelete",method = RequestMethod.GET)
    public boolean  GWHandleDelete(String id){
        boolean flag = false;
        try {
            service.GWHandleDelete(id);
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    @ApiOperation(value = "删除过路管")
    @RequestMapping(value = "GLGHandleDelete",method = RequestMethod.GET)
    public boolean  GLGHandleDelete(String id){
        boolean flag = false;
        try {
            service.GLGHandleDelete(id);
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }


    @ApiOperation(value = "管网字段查询")
    @RequestMapping(value = "initParams",method = RequestMethod.GET)
    public String  initParams(String type){
        List<Map<String,Object>> gwztList = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> gdczList = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> kjList = new ArrayList<Map<String,Object>>();
        gwztList = xqslService.queryFwxzList(type);
        gdczList = service.queryGdczList();
        kjList = service.queryKjList();
        JSONObject jo = new JSONObject();
        jo.put("gwztList",gwztList);
        jo.put("gdczList",gdczList);
        jo.put("kjList",kjList);
        return jo.toString();
    }

    @ApiOperation(value = "初始化庭院选择器")
    @RequestMapping(value = "initLYOption",method = RequestMethod.GET)
    public String  initLYOption(HttpServletRequest request,String xmid){
        List<CodeModel> list = new ArrayList<>();
        list = service.initLYOption(xmid);
        JSONObject jo = new JSONObject();
        jo.put("row",list);
        return jo.toString();
    }

    @ApiOperation(value = "初始化楼栋")
    @RequestMapping(value = "initLDTable",method = RequestMethod.GET)
    public String  initLDTable(HttpServletRequest request,String xmid){
        List<LdModel> list = new ArrayList<LdModel>();
        if(!StringUtil.isEmpty(xmid)){
            list = service.initLDTable(xmid);
        }
        JSONObject jo = new JSONObject();
        jo.put("row",list);
        return jo.toString();
    }

    @ApiOperation(value = "楼栋信息维护")
    @RequestMapping(value = "ldxxwh",method = RequestMethod.GET)
    public String  ldxxwh(HttpServletRequest request,LdModel model){
        String flag = "0";
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        List<LdModel> list = service.checkLdbh(model);
        if(list!=null&&list.size()>0){
            flag = "-1";
        }else{
            try{
                if(StringUtil.isEmpty(model.getId())){
                    model.setCjr(user.getUserid());
                    model.setId(GuidUtils.getGuid());
                    service.addLD(model);
                    flag="1";
                }else{
                    service.updateLD(model);
                    flag="1";
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return flag;
    }

    @ApiOperation(value = "初始化分区")
    @RequestMapping(value = "getFQData",method = RequestMethod.GET)
    public String  getFQData(HttpServletRequest request,String xmid,String id){
        List<Fqmodel> list = new ArrayList<Fqmodel>();
        if(!StringUtil.isEmpty(xmid)){
            list = service.getFQData(xmid,id);
        }
        JSONObject jo = new JSONObject();
        jo.put("row",list);
        return jo.toString();
    }

    @ApiOperation(value = "分区信息维护")
    @RequestMapping(value = "fqxxwh",method = RequestMethod.POST)
    public boolean  fqxxwh(HttpServletRequest request,String data){
        boolean flag = false;
        JSONArray jsonArr = JSONArray.parseArray(data);//转换成JSONArray 格式
        try{
            service.deleteFQ((String)jsonArr.getJSONObject(0).get("ldid"),(String)jsonArr.getJSONObject(0).get("xmid"));
            for(int i = 0;i<jsonArr.size();i++){
                Fqmodel model = new Fqmodel();
                model.setFqmc(jsonArr.getJSONObject(i).get("fqmc").toString());
                model.setLdid(jsonArr.getJSONObject(i).get("ldid").toString());
                model.setQslc((Integer)jsonArr.getJSONObject(i).get("qslc"));
                model.setZdlc((Integer)jsonArr.getJSONObject(i).get("zdlc"));
                model.setSybz(Double.parseDouble(jsonArr.getJSONObject(i).get("sybz").toString()));
                model.setXmid((String)jsonArr.getJSONObject(i).get("xmid"));
                model.setFqbh(jsonArr.getJSONObject(i).get("fqbh").toString());
                model.setId(GuidUtils.getGuid());
                service.addFQ(model);
            }
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation(value = "分区信息维护")
    @RequestMapping(value = "xmsqlx",method = RequestMethod.GET)
    public boolean  xmsqlx(HttpServletRequest request,String xmid){
        boolean flag = false;
        try{
           CodeModel model = service.xmsqlx(xmid);
           if(model!=null&&!StringUtil.isEmpty(model.getCodevalue())){
               if(("jmkslzl").equals(model.getCodevalue())){
                   flag = true;
               }
           }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation(value = "初始化新建小区")
    @RequestMapping(value = "xjxqld",method = RequestMethod.GET)
    public String  xjxqld(HttpServletRequest request,String xmid){
        List<XjxqldModel> list = new ArrayList<XjxqldModel>();
        if(!StringUtil.isEmpty(xmid)){
            list = service.xjxqld(xmid);
        }
        JSONObject jo = new JSONObject();
        jo.put("row",list);
        return jo.toString();
    }

    @ApiOperation(value = "初始化物料库")
    @RequestMapping(value = "getWlData",method = RequestMethod.GET)
    public String  getWlData(HttpServletRequest request,XtglwlkModel model){
        List<XtglwlkModel> list = new ArrayList<XtglwlkModel>();
        int total = 0;
        try {
            model.setStartRow();
            model.setEndRow();
            total = service.getWlDataCount(model);
            if(total>0){
                list = service.getWlData(model);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("row",list);
        jo.put("total",total);
        return jo.toString();
    }
    @ApiOperation(value = "初始化项目物料清单")
    @RequestMapping(value = "pickHandle",method = RequestMethod.GET)
    public String  pickHandle(HttpServletRequest request,XtglwlkModel model){
        List<XtglwlkModel> list = new ArrayList<XtglwlkModel>();
        int total = 0;
        try {
            model.setStartRow();
            model.setEndRow();
            total = service.pickHandleCount(model);
            if(total>0){
                list = service.pickHandle(model);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("row",list);
        jo.put("total",total);
        return jo.toString();
    }
    @ApiOperation(value = "添加项目物料清单")
    @RequestMapping(value = "wlHandleAdd",method = RequestMethod.GET)
    public String  wlHandleAdd(HttpServletRequest request,XmwlqdModel model){
        String flag = "0";
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        model.setCjr(user.getUserid());
        List<XmwlqdModel>  list = new ArrayList<XmwlqdModel>();

        try {
            list = service.getWlByWlkid(model.getWlkid(),model.getXmid());
            if(list.size()>0&&list!=null){
                flag = "-1";
            }else{
                model.setId(GuidUtils.getGuid());
                service.wlHandleAdd(model);
                flag="1";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    @ApiOperation(value = "编辑项目物料清单")
    @RequestMapping(value = "wlqdHandleEdit",method = RequestMethod.GET)
    public boolean  wlqdHandleEdit(HttpServletRequest request,XmwlqdModel model){
        boolean flag = false;
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        model.setBgr(user.getUserid());
        try {
            service.wlqdHandleEdit(model);
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    @ApiOperation(value = "删除项目物料清单")
    @RequestMapping(value = "wlqdHandleDelete",method = RequestMethod.GET)
    public boolean  wlqdHandleDelete(HttpServletRequest request,String id){
        boolean flag = false;
        try {
            service.wlqdHandleDelete(id);
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation(value = "初始化审图成果列表")
    @RequestMapping(value = "initStcgTable",method = RequestMethod.GET)
    public String  initStcgTable(HttpServletRequest request,String xmid,String type,String sjstrzwid,String flag){
        List<FjzlglModel> list = new ArrayList<FjzlglModel>();
        List<SjstRwModel> sjstrwList = new ArrayList<>();
        List<Map<String,Object>> xmlist = new ArrayList<>();
        String bz="";
        String xmbh="";
        try {
            if(StringUtil.isNotEmpty(sjstrzwid)){
                list = service.initStcgTable(sjstrzwid,xmid,type,flag);
                sjstrwList = service.getSjstrw(xmid);
            }
            xmlist = service.getxmList(xmid);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(sjstrwList!=null&&sjstrwList.size()>0){
            bz=sjstrwList.get(0).getSjrybz();
        }
        if(xmlist!=null&&xmlist.size()>0){
            xmbh=xmlist.get(0).get("XMBH").toString();
        }
        JSONObject jo = new JSONObject();
        jo.put("row",list);
        jo.put("bz",bz);
        jo.put("xmbh",xmbh);
        return jo.toString();
    }

    @ApiOperation(value = "初始化审图成果详情列表")
    @RequestMapping(value = "stManage",method = RequestMethod.GET)
    public String  stManage(HttpServletRequest request,SjstcgModel query){
        List<SjstcgModel> list = new ArrayList<SjstcgModel>();
        try {
            if(("1").equals(query.getType())){
                list = service.ksjsfaManage(query);
            }else{
                list = service.stManage(query);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "删除审图附件")
    @RequestMapping(value = "stDelete",method = RequestMethod.GET)
    public boolean  stDelete(HttpServletRequest request,String id,int flag){
        boolean result=false;
        if(!StringUtil.isEmpty(id)){
            try {
                service.stDelete(id,flag);
                result=true;
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return result;
    }
    /**
     * 审图成果上传
     * @return
     */
    @ApiOperation(value = "审图成果上传")
    @RequestMapping(value ="stcgsc",method = RequestMethod.GET)
    public String stcgsc(String path,String name,String xmid,String sjstrzwid,String fjlxYj,String fjlxEj,HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        JSONObject jo = new JSONObject();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateString = formatter.format(currentTime);
        try {
            SjstcgModel model = new SjstcgModel();
            model.setId(GuidUtils.getGuid());
            model.setSjstrzwid(sjstrzwid);
            model.setXmid(xmid);
            model.setFjlj(path);
            String[] names = name.split("\\.");
            model.setFjhz(names[names.length-1]);
            model.setFjmc(name);
            model.setFjlxEj(fjlxEj);
            model.setFjlxYj(fjlxYj);
            model.setScr(user.getUserid());
            service.stcgsc(model);//保存到上传附件表
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("returncode", "999");
            jo.put("msg", "程序异常，请联系管理员");
        }
        jo.put("returncode", "000");
        jo.put("msg", "上传成功");
        return jo.toString();
    }

    /**
     * 延期拍摄上传
     * @return
     */
    @ApiOperation(value = "延期拍摄上传")
    @RequestMapping(value ="stcgsc_delay",method = RequestMethod.GET)
    public String stcgsc_delay(String path,String name,String delayid,HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        JSONObject jo = new JSONObject();
        try {
            Map<String,Object> pd = new HashMap<>();
            if(StringUtil.isEmpty(delayid)){
                delayid = GuidUtils.getGuid();
            }
            String id = GuidUtils.getGuid();
            pd.put("id",id);//主键标识
            pd.put("fjlj",path);//附件路径
            String[] names = name.split("\\.");
            pd.put("fjhz",names[names.length-1]);//附件后缀
            pd.put("delayid",delayid);
            pd.put("fjmc",name);//附件名称
            pd.put("scr",user.getUserid());//创建人
            service.insertImportFh_delay(pd);//保存到上传附件表
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("returncode", "999");
            jo.put("msg", "程序异常，请联系管理员");
        }
        jo.put("returncode", "000");
        jo.put("msg", "上传成功");
        jo.put("delayid", delayid);
        return jo.toString();
    }


    /**
     * 下载图片
     * @return
     */
    @ApiOperation(value = "获取预览图片流")
    @RequestMapping(value = "fjzlPreview",method = RequestMethod.GET)
    public String  fjzlPreview(String xmid,String yjfjlx,String ejfjlx) {
        JSONObject jo = new JSONObject();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        List<String> responseList = new ArrayList<String>();
        String path="";
        try {
            list = service.fjzlPreview(xmid,yjfjlx,ejfjlx);
            for (Map<String,Object> map: list
                 ) {
                InputStream inputStream = null;
                byte[] data = null;
                if(!StringUtil.isEmpty(map.get("FJLJ"))){
                    inputStream = new FileInputStream(StringUtil.getBasePath()+map.get("FJLJ"));
                    data = new byte[inputStream.available()];
                    inputStream.read(data);
                    inputStream.close();
                    // 加密
                    BASE64Encoder encoder = new BASE64Encoder();
                    responseList.add(encoder.encode(data));
                }
            }
            jo.put("row",responseList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation(value = "是否显示提交按钮")
    @RequestMapping(value = "submitShow",method = RequestMethod.GET)
    public boolean  submitShow(HttpServletRequest request,String xmid){
        boolean flag = false;
        int total = 0;
        try {
            total = service.submitShow(xmid);
            if(total>0){
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    /**
     * 下载图片
     * @return
     */
    @ApiOperation(value = "获取预览图片流")
    @RequestMapping(value = "stPreview",method = RequestMethod.GET)
    public String  stPreview(SjstcgModel query) {
        JSONObject jo = new JSONObject();
        try {
            String path="";
            List<SjstcgModel> list = new ArrayList<SjstcgModel>();
            List<String> responseList = new ArrayList<String>();
            if(("1").equals(query.getType())){
                list = service.ksjsfaManage(query);
            }else{
                list = service.stManage(query);
            }
            String basePath = StringUtil.getBasePath();
            for (SjstcgModel model:list
                 ) {
                if(model.getFjhz().equalsIgnoreCase("png")||model.getFjhz().equalsIgnoreCase("jpg")){
                    InputStream inputStream = null;
                    byte[] data = null;
                    try {
                        inputStream = new FileInputStream(basePath+model.getFjlj());
                        data = new byte[inputStream.available()];
                        inputStream.read(data);
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // 加密
                    BASE64Encoder encoder = new BASE64Encoder();
                    responseList.add(encoder.encode(data));
                }
            }
            jo.put("row",responseList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jo.toString();
    }

    /**
     *
     * @return
     */
    @ApiOperation(value = "获取水表口径")
    @RequestMapping(value = "getKjData",method = RequestMethod.GET)
    public String  getKjData() {
        JSONObject jo = new JSONObject();
        List<CodeModel> list = new ArrayList<CodeModel>();
        try {
            list=service.getKjData();
            jo.put("row",list);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jo.toString();
    }
    /**
     *
     * @return
     */
    @ApiOperation(value = "根据类型获取XTGL_CODE表中数据")
    @RequestMapping(value = "getCodeData",method = RequestMethod.GET)
    public String  getCodeData(String type) {
        JSONObject jo = new JSONObject();
        List<CodeModel> list = new ArrayList<CodeModel>();
        try {
            list=service.getCodeData(type);
            jo.put("row",list);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jo.toString();
    }

    /**
     *
     * @return
     */
    @ApiOperation(value = "根据类型获取XTGL_CODE表中数据")
    @RequestMapping(value = "getHyflCodeData",method = RequestMethod.GET)
    public String  getHyflCodeData(String parentid) {
        JSONObject jo = new JSONObject();
        List<CodeModel> list = new ArrayList<CodeModel>();
        try {
            list=service.getHyflCodeData(parentid);
            jo.put("row",list);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jo.toString();
    }
    /**
     *用水性质
     * @return
     */
    @ApiOperation(value = "用水性质")
    @RequestMapping(value = "getYsxzData",method = RequestMethod.GET)
    public String  getYsxzData() {
        JSONObject jo = new JSONObject();
        List<CodeModel> list = new ArrayList<CodeModel>();
        try {
            list=service.getYsxzData();
            jo.put("row",list);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jo.toString();
    }
    /**
     *行业分类
     * @return
     */
    @ApiOperation(value = "行业分类")
    @RequestMapping(value = "getHyflData",method = RequestMethod.GET)
    public String  getHyflData() {
        JSONObject jo = new JSONObject();
        List<CodeModel> list = new ArrayList<CodeModel>();
        try {
            list=service.getHyflData();
            jo.put("row",list);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jo.toString();
    }
    /**
     *
     * @return
     */
    @ApiOperation(value = "开户总行")
    @RequestMapping(value = "getKhzhData",method = RequestMethod.GET)
    public String  getKhzhData() {
        JSONObject jo = new JSONObject();
        List<CodeModel> list = new ArrayList<CodeModel>();
        try {
            list=service.getKhzhData();
            jo.put("row",list);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jo.toString();
    }
    /**
     *
     * @return
     */
    @ApiOperation(value = "开户银行")
    @RequestMapping(value = "getKhyhData",method = RequestMethod.GET)
    public String  getKhyhData(String type) {
        JSONObject jo = new JSONObject();
        List<CodeModel> list = new ArrayList<CodeModel>();
        try {
            list=service.getKhyhData(type);
            jo.put("row",list);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation(value = "地表信息维护")
    @RequestMapping(value = "dbxq",method = RequestMethod.POST)
    public boolean  dbxq(HttpServletRequest request,XmSb model){
        boolean flag = false;
        List<XmSb>  list = new ArrayList<XmSb>();
        try {
            if(!StringUtil.isEmpty(model.getLdid())){
                service.deleteLdhb(model.getXmId(),model.getLdid());
                model.setMph(model.getMph().replace("，",",").replace("\n",","));
                String[] mphs = model.getMph().split(",");
                for(int i=0;i<mphs.length;i++){
                    model.setBzlb(1);
                    model.setId(GuidUtils.getGuid());
                    model.setHh(getKey("SB",1));
                    model.setMph(mphs[i]);
                    service.addDb(model);
                }
            }else{
                if(StringUtil.isEmpty(model.getId())){
                    if(StringUtil.isEmpty(model.getCounts())){
                        model.setCounts("1");
                    }
                    int count = Integer.parseInt(model.getCounts());
                    if(StringUtil.isEmpty(model.getHh())){
                        for (int j=0;j<count;j++){
                            model.setHh(getKey("SB",1));
                            model.setId(GuidUtils.getGuid());
                            service.addDb(model);
                        }
                    }else{
                        model.setId(GuidUtils.getGuid());
                        service.addDb(model);
                    }
                }else{
                    service.updateDb(model);
                }
            }
           flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation(value = "出户改造户表保存")
    @RequestMapping(value = "drxq",method = RequestMethod.POST)
    public boolean  drxq(HttpServletRequest request,XmSb model){
        boolean flag = false;
        List<XmSb>  list = new ArrayList<XmSb>();
        try {
            if(StringUtil.isEmpty(model.getId())){
                service.deleteLdhb(model.getXmId(),model.getLdid());
                int count = Integer.parseInt(model.getCounts());
                for (int i=0;i<count;i++){
                    model.setHh(getKey("SB",1));
                    model.setId(GuidUtils.getGuid());
                    service.addChgzHb(model);
                }
            }else{
                service.editChgzHb(model);
            }
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation(value = "查询所有楼栋ID下的水表")
    @RequestMapping(value = "queryAllLdsb",method = RequestMethod.GET)
    public String queryAllLdsb(String xmid,String ldids){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        try{
            pd.put("xmid",xmid);
            pd.put("ldids",ldids);
            List<XmSb> list = service.queryAllLdsb(pd);
            jo.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("出户改造Excel批量编辑")
    @RequestMapping(value ="importExcel",method = RequestMethod.POST, consumes = { "multipart/mixed", "multipart/form-data" })
    @ResponseBody
    public Map importExcel(@RequestParam(value = "multipartFiles", required = false ) MultipartFile multipartFiles,
                           @RequestParam(value = "comId", required = false ) String comId,
                           HttpServletRequest request){
        Map resMap = new HashMap<String,Object>();
        if (Objects.isNull(multipartFiles)) {
            logger.error("文件为空");
            resMap.put("msg","文件为空，请重新上传");
            return resMap;
        }
        try{
            File excel = null;
            if(multipartFiles.equals("")||multipartFiles.getSize()<=0){
                multipartFiles = null;
            }else {
                InputStream ins = null;
                ins = multipartFiles.getInputStream();
                excel = new File(multipartFiles.getOriginalFilename());
                inputStreamToFile(ins, excel);
                ins.close();
            }
            String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
            Workbook wb;
            //根据文件后缀（xls/xlsx）进行判断
            if ( "xls".equals(split[1])){
                FileInputStream fis = new FileInputStream(excel);   //文件流对象
                wb = new HSSFWorkbook(fis);
            }else if ("xlsx".equals(split[1])){
                wb = new XSSFWorkbook(excel);
            }else {
                resMap.put("msg","文件类型错误");
                return resMap;
            }
            //开始解析
            Sheet sheet = wb.getSheetAt(0);     //读取sheet 0
            int firstRowIndex = sheet.getFirstRowNum()+1;   //第一行是列名，所以不读
            int lastRowIndex = sheet.getLastRowNum();
            int count = 0;
            for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                Row row = sheet.getRow(rIndex);
                if (row != null) {
                    int firstCellIndex = row.getFirstCellNum();
                    int lastCellIndex = row.getLastCellNum();
                    Map<String,Object> pd = new HashMap<>();
                    for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
                        Cell cell = row.getCell(cIndex);
                        if(cIndex == 0){
                            pd.put("hh",readCell(cell));
                        }else if(cIndex == 2){
                            pd.put("hm",readCell(cell));
                        }else if(cIndex == 3){
//                                mph = (int) cell.getNumericCellValue();
                            pd.put("mph",readCell(cell));
                        }else if(cIndex == 4){
                            pd.put("sfzh",readCell(cell));
                        }else if(cIndex == 5){
                            pd.put("lxfs",readCell(cell));
                        }
                    }
                    try{
                        service.updateMphAndSfzhByhh(pd);
                        count++;
                    }catch (Exception e1){
                        e1.printStackTrace();
                    }
                }
            }
            resMap.put("msg","上传成功");
            resMap.put("count",count);
        }catch (Exception e){
            e.printStackTrace();
            resMap.put("msg","上传失败");
        }
        return resMap;
    }

    public String readCell(Cell cell){
        String cellValue = "";
        if (null != cell) {
            // 以下是判断数据的类型
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                    DecimalFormat df = new DecimalFormat("0");
                    cellValue = df.format(cell.getNumericCellValue());
                    break;
                case HSSFCell.CELL_TYPE_STRING: // 字符串
                    cellValue = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    cellValue = cell.getBooleanCellValue() + "";
                    break;
                case HSSFCell.CELL_TYPE_FORMULA: // 公式
                    cellValue = cell.getCellFormula() + "";
                    break;
                case HSSFCell.CELL_TYPE_BLANK: // 空值
                    cellValue = "";
                    break;
                case HSSFCell.CELL_TYPE_ERROR: // 故障
                    cellValue = "非法字符";
                    break;
                default:
                    cellValue = "未知类型";
                    break;
            }
        }
        return cellValue;
    }

    @ApiOperation(value = "初始化地表信息")
    @RequestMapping(value = "initDbpzTable",method = RequestMethod.GET)
    public String  initDbpzTable(String xmId,int page,int size,String hhlxbh,String ldid){
        JSONObject jo = new JSONObject();
        List<XmSb>  list = new ArrayList<XmSb>();
        int startRow=0;
        int endRow=0;
        int total = 0;
        try {
            total=service.initDbpzTableCount(xmId,hhlxbh,ldid);
            if(total>0){
                startRow=(page - 1) * size + 1;
                endRow=page * size;
                list = service.initDbpzTable(xmId,startRow,endRow,hhlxbh,ldid);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        jo.put("total",total);
        jo.put("ldid",ldid);
        return jo.toString();
    }

    @ApiOperation(value = "删除地表信息")
    @RequestMapping(value = "dbpzDelete",method = RequestMethod.GET)
    public boolean  dbpzDelete(String id,int zt){
        boolean flag = false;
        try {
            service.dbpzDelete(id,zt);
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation(value = "初始化楼栋户数")
    @RequestMapping(value = "initLdhb",method = RequestMethod.GET)
    public String  initLdhb(String xmid){
        List<CodeModel> list = new ArrayList<CodeModel>();
        JSONObject jo = new JSONObject();
        try {
            list = service.initLdhb(xmid);
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "回显批量编辑")
    @RequestMapping(value = "dbpzAdd",method = RequestMethod.GET)
    public String  dbpzAdd(String xmid,String ldid){
        List<XmSb> list = new ArrayList<XmSb>();
        List<Map<String,Object>> xmList = new ArrayList<>();
        List<LdModel> ldList = new ArrayList<>();
        JSONObject jo = new JSONObject();
        try {
            ldList = service.getLdList(ldid);
            list = service.dbpzAdd(xmid,ldid);
            xmList = service.getxmList(xmid);
            String mph = service.getMph(xmid,ldid);
            if(list.size()>0&&list!=null){
                if(!StringUtil.isEmpty(mph)){
                    list.get(0).setMph(mph);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        jo.put("ldRow",ldList);
        jo.put("dz",xmList.get(0).get("XMDZ"));
        return jo.toString();
    }
    @ApiOperation(value = "修改门牌号")
    @RequestMapping(value = "mphHandel",method = RequestMethod.GET)
    public boolean  mphHandel(String id,String mph){
        boolean flag = false;
        try {
            service.mphHandel(id,mph);
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 上传设计成果退回
     * @param xmid
     * @param activityId
     * @return
     */
    @ApiOperation(value = "上传设计成果退回")
    @RequestMapping(value = "sjcgscBack",method = RequestMethod.GET)
    public boolean sjcgscBack(String xmid, String activityId,String htyy,HttpServletRequest request) {
        UserEntity user = (UserEntity)request.getSession().getAttribute("user");
        Date date = new Date();
        boolean flag = false;
        int direction = 1;
        CalculateHours ch = new CalculateHours();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //这里的格式可以自己设置
        try{
            //修改实例中流程定义id
            service.updateInst(xmid, activityId,direction,"");
            //查询当前任务
            WfTaskEntity nowTask = service.getNowTask(xmid, activityId);
            //新增下一任务
            WfTaskEntity task = getNextTaskEntity(xmid,activityId,direction);
            //根据环节编号查询环节定义数据
            WfDfActivityEntity activityEntity = service.getActivityEntity(task.getCActivityid());
            task.setPtaskid(nowTask.getTaskid());
            task.setTaskid(GuidUtils.getGuid());
            task.setIscurrent("2");//退回
            task.setCzr(user.getUserid());//操作人
            task.setQssj(date);
            task.setIscheck("0");//未签收
            task.setHtyy(htyy);
            if(("1").equals(activityEntity.getCheckLimited())){
                task.setCheck_sx(ch.getEndTime(format.format(date), Float.parseFloat(activityEntity.getCheckMinute())));
            }else{
                task.setCheck_sx(DateUtils.stringToDate("2099-12-31 23:59:59"));
            }
            if(("1").equals(activityEntity.getFinishLimited())){
                task.setJssx(ch.getEndTime(format.format(date), Float.parseFloat(activityEntity.getYellowFinish())));
                task.setJssx_yl(ch.getEndTime(format.format(date), Float.parseFloat(activityEntity.getFinishMinute())+Float.parseFloat(activityEntity.getYellowFinish())));
            }else{
                task.setJssx(DateUtils.stringToDate("2099-12-31 23:59:59"));
                task.setJssx_yl(DateUtils.stringToDate("2099-12-31 23:59:59"));
            }
            task.setHasnew("2");//无子流程
            task.setRefkey(nowTask.getRefkey());
            task.setRefid(nowTask.getRefid());
            xkylxService.saveWfTask(task);
            //将当前任务存入历史
            WfTaskHistoryEntity historyTask = new WfTaskHistoryEntity();
            historyTask.setTaskid(nowTask.getTaskid());
            historyTask.setPtaskid(nowTask.getPtaskid());//前置任务为空
            historyTask.setProcInstId(nowTask.getProcInstId());//流程实例ID
            historyTask.setCActivityid(nowTask.getCActivityid());//环节编号
            historyTask.setIscurrent("0");//已完成
            historyTask.setCzr(user.getUserid());//操作人
            historyTask.setQssj(nowTask.getQssj());
            historyTask.setJssj(date);
            historyTask.setJssx(nowTask.getJssx());
            historyTask.setJssxYl(nowTask.getJssx_yl());
            historyTask.setCheckTime(nowTask.getCheck_time());
            historyTask.setCheckUser(nowTask.getCheckUser());
            historyTask.setIscheck(nowTask.getIscheck());//已签收
            historyTask.setHasnew(nowTask.getHasnew());//无子流程
            historyTask.setRefkey(nowTask.getRefkey());
            historyTask.setRefid(nowTask.getRefid());
            historyTask.setRwblr(nowTask.getRwblr());
            xkylxService.saveWfTaskHistory(historyTask);
            //删除之前任务
            service.deleteTask(nowTask.getTaskid());
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 上传设计成果提交
     * @param xmid
     * @param activityId
     * @return
     */
    @ApiOperation(value = "上传设计成果提交")
    @RequestMapping(value = "scsjcgSubmit",method = RequestMethod.GET)
    public boolean scsjcgSubmit(String xmid,String type, String activityId,String sjrybz,HttpServletRequest request) {
        UserEntity user = (UserEntity)request.getSession().getAttribute("user");
        Date date = new Date();
        boolean flag = false;
        int direction=0;
        try{
            //修改实例中流程定义id
//            service.updateInst(xmid, activityId,direction);
//            查询当前实例
            List<WfRuInstEntity> instList = service.getInstList(xmid,activityId,direction,type);
            List<WfRuInstContentEntity> listWfRuInstContentEntity = service.queryWfRuInstContentEntity(instList.get(0).getProcInstId());
            //将当前实例存入历史
            //service.pushWfRuInstToHis(xmid,activityId);
            WfHiInstEntity instEntity = new WfHiInstEntity();
            instEntity.setProcInstId(instList.get(0).getProcInstId());
            instEntity.setPdefId(instList.get(0).getPdefId());
            instEntity.setEndtime(date);
            instEntity.setParammap(instList.get(0).getParammap());
            instEntity.setStartuser(instList.get(0).getStartuser());
            instEntity.setStartdt(instList.get(0).getStartdt());
            instEntity.setStopdt(instList.get(0).getStopdt());
            instEntity.setProcstatus(instList.get(0).getProcstatus()+"");
            instEntity.setStopuser(instList.get(0).getStopuser());
            xkylxService.saveWfHiRuInst(instEntity);
            WfHiInstContentEntity instContentEntity = new WfHiInstContentEntity();
            instContentEntity.setContentid(listWfRuInstContentEntity.get(0).getContentid());
            instContentEntity.setProcInstId(listWfRuInstContentEntity.get(0).getProcInstId());
            instContentEntity.setTableName(listWfRuInstContentEntity.get(0).getTableName());
            instContentEntity.setContentname(listWfRuInstContentEntity.get(0).getContentname());
            instContentEntity.setContentvalue(listWfRuInstContentEntity.get(0).getContentvalue());
            instContentEntity.setContentremark(listWfRuInstContentEntity.get(0).getContentremark());
            instContentEntity.setContenttype(listWfRuInstContentEntity.get(0).getContenttype());
            instContentEntity.setReadonly(listWfRuInstContentEntity.get(0).getReadonly());
            xkylxService.saveWfHiInstContent(instContentEntity);
            //查询当前任务
            WfTaskEntity nowTask = service.getNowTask(xmid, activityId);
            //将当前任务存入历史
            WfTaskHistoryEntity historyTask = new WfTaskHistoryEntity();
            historyTask.setTaskid(nowTask.getTaskid());
            historyTask.setPtaskid(nowTask.getPtaskid());//前置任务为空
            historyTask.setProcInstId(nowTask.getProcInstId());//流程实例ID
            historyTask.setCActivityid(nowTask.getCActivityid());//环节编号
            historyTask.setIscurrent("0");//已完成
            historyTask.setCzr(user.getUserid());//操作人
            historyTask.setQssj(nowTask.getQssj());
            historyTask.setJssj(date);
            historyTask.setJssx(nowTask.getJssx());
            historyTask.setJssxYl(nowTask.getJssx_yl());
            historyTask.setCheckTime(nowTask.getCheck_time());
            historyTask.setCheckUser(nowTask.getCheckUser());
            historyTask.setIscheck(nowTask.getIscheck());//已签收
            historyTask.setHasnew(nowTask.getHasnew());//无子流程
            historyTask.setRefkey(nowTask.getRefkey());
            historyTask.setRefid(nowTask.getRefid());
            historyTask.setRwblr(nowTask.getRwblr());
            xkylxService.saveWfTaskHistory(historyTask);
            //修改设计审图任务
            SjstRwModel sjstRwModel = new SjstRwModel();
            sjstRwModel.setSjrybz(sjrybz);
            sjstRwModel.setXmid(xmid);
            sjstRwModel.setSjstzt("0");
            service.updateSjstRwBz(sjstRwModel);
            //删除之前任务
            service.deleteTask(nowTask.getTaskid());
//            删除运行实例
            service.deleteInst(instList.get(0).getProcInstId());
            service.deleteInstContent(instList.get(0).getProcInstId());
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    @ApiOperation(value = "红黄绿灯")
    @RequestMapping(value = "initHhld",method = RequestMethod.GET)
    public String  initHhld(String xmid,String activityid){
        JSONObject jo = new JSONObject();
        List<WfTaskEntity> list = new ArrayList<WfTaskEntity>();
        boolean isGreen = false;
        boolean isYellow = false;
        boolean isRed = false;
        Date date = new Date();
        String sjc;
        try {
            list = service.initHhld(xmid,activityid);
            if(list.size()>0){
                isGreen = list.get(0).getJssx().after(date);
                if(isGreen){
                    sjc = DateUtils.calculateTimeDifferenceBySimpleDateFormat(date,list.get(0).getJssx());
                    jo.put("isGreen",isGreen);
                    jo.put("isYellow",isYellow);
                    jo.put("isRed",isRed);
                    jo.put("sjc",sjc);
                    jo.put("row",list);
                    return jo.toString();
                }else{
                    isYellow = list.get(0).getJssx_yl().after(date);
                    if(isYellow){
                        sjc = DateUtils.calculateTimeDifferenceBySimpleDateFormat(date,list.get(0).getJssx_yl());
                        jo.put("isGreen",isGreen);
                        jo.put("isYellow",isYellow);
                        jo.put("isRed",isRed);
                        jo.put("sjc",sjc);
                        jo.put("row",list);
                        return jo.toString();
                    }else{
                        isRed=true;
                        sjc = DateUtils.calculateTimeDifferenceBySimpleDateFormat(list.get(0).getJssx_yl(),date);
                        jo.put("isGreen",isGreen);
                        jo.put("isYellow",isYellow);
                        jo.put("isRed",isRed);
                        jo.put("sjc",sjc);
                        jo.put("row",list);
                        return jo.toString();
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @ApiOperation(value = "设计补充")
    @RequestMapping(value = "saveXmjbxx",method = RequestMethod.GET)
    public boolean  saveXmjbxx(HttpServletRequest request){
        boolean flag = false;
        String xmid = request.getParameter("xmid");
        String xmmc = request.getParameter("xmmc");
        String xmdz = request.getParameter("xmdz");
        String lxr = request.getParameter("lxr");
        String lxrsjh = request.getParameter("lxrsjh");
        String xmfzr_sjh = request.getParameter("xmfzr_sjh");
        String xmfzr = request.getParameter("xmfzr");
        String bz = request.getParameter("bz");
        try {
            //修改设计审图任务
            SjstRwModel sjstRwModel = new SjstRwModel();
            sjstRwModel.setSjrybz(bz);
            sjstRwModel.setXmid(xmid);
            sjstRwModel.setSjstzt("1");
            service.updateSjstRwBz(sjstRwModel);
            //修改项目经理手机号
            service.updateXmjlsjh(xmfzr,xmfzr_sjh);
            //修改项目基本信息
            service.updateXmjbxx(xmid,xmmc,xmdz,lxr,lxrsjh);
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation(value = "申请延期信息")
    @RequestMapping(value = "sqyqxx",method = RequestMethod.GET)
    public String  sqyqxx(HttpServletRequest request,String xmid,String activityId){
        JSONObject jo = new JSONObject();
        List<Map<String,Object>> list = new ArrayList<>();
        UserEntity user = (UserEntity)request.getSession().getAttribute("user");
        jo.put("sqr",user.getUsername());
        String spr = "";
        String sprid = "";
        String yqwcsj = "";
        try {
           list = service.getsqyqxx(xmid,activityId);
           spr = list.get(0).get("USERNAME")+"("+list.get(0).get("MOBILEPHONE")+")";
           sprid = (String)list.get(0).get("XMFZR");
            yqwcsj = list.get(0).get("YQWCSJ")+"";
            jo.put("spr",spr);
            jo.put("sprid",sprid);
            jo.put("yqwcsj",yqwcsj);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation(value = "申请延期")
    @RequestMapping(value = "sqyq",method = RequestMethod.GET)
    public String  sqyq(HttpServletRequest request,String xmid,String activityId,String jhyqjz_vue,String csyy,String yqwcsj,String sprid,String delayid){
        boolean flag = false;
        List<Map<String,Object>> list = new ArrayList<>();
        UserEntity user = (UserEntity)request.getSession().getAttribute("user");
        String id = "";
        JSONObject jo = new JSONObject();
        try {
            list = service.getTask(xmid,activityId);
            if(!StringUtil.isEmpty(delayid)){
                id=delayid;
            }else{
                id = GuidUtils.getGuid();
            }
            service.sqyq(id,(String)list.get(0).get("TASKID"),jhyqjz_vue,csyy,yqwcsj,sprid,user.getUserid());
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("flag",flag);
        jo.put("id",id);
        return jo.toString();
    }
    @ApiOperation(value = "是否显示延期按钮")
    @RequestMapping(value = "yqbuttonshow",method = RequestMethod.GET)
    public boolean  yqbuttonshow(HttpServletRequest request,String xmid,String activityId){
        boolean flag = false;
        List<Map<String,Object>> list = new ArrayList<>();
        List<WfTaskEntity> tasklist = new ArrayList<WfTaskEntity>();
        List<Map<String,Object>> delaylist = new ArrayList<>();
        boolean isRed=false;
        boolean ispassdaley=true;
        String id = "";
        Date date = new Date();
        try {
            tasklist = service.initHhld(xmid,activityId);
            if(tasklist.size()>0){
                isRed = tasklist.get(0).getJssx_yl().before(date);
                delaylist = service.getDelay(tasklist.get(0).getTaskid());
            }
            list = service.getActivity(activityId);
            if(delaylist!=null&&delaylist.size()>0){
                for(int i = 0;i<delaylist.size();i++){
                    if(("0").equals(delaylist.get(i).get("SPZT").toString())){
                        ispassdaley=false;
                    }
                }
                if(!isRed&&("1").equals(list.get(0).get("DELAY").toString())&&ispassdaley){
                    flag = true;
                }
            }else{
                if(!isRed&&("1").equals(list.get(0).get("DELAY").toString())){
                    flag = true;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation(value = "获取审批项目列表")
    @RequestMapping(value = "getspXmlb",method = RequestMethod.GET)
    public String  getspXmlb(HttpServletRequest request){
        JSONObject jo = new JSONObject();
        int total = 0;
        int startRow = 0;
        int endRow = 0;
        List<Map<String,Object>> list = new ArrayList<>();
        UserEntity user = (UserEntity)request.getSession().getAttribute("user");
        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("size"));
        String xmbh = request.getParameter("xmbh");
        String spzt = request.getParameter("spzt");
        String kssj = request.getParameter("kssj");
        String jssj = request.getParameter("jssj");
       try {
           total=service.getspXmlbCount(xmbh,spzt,kssj,jssj,user.getUserid());
           if(total>0){
               startRow=(page - 1) * size + 1;
               endRow=page * size;
               list = service.getspXmlb(xmbh,spzt,kssj,jssj,startRow,endRow,user.getUserid());
               for (Map map:list
                    ) {
                   if(map.get("SPR").toString().equals(user.getUserid())&&Integer.parseInt(map.get("SPZT").toString())==0){
                        map.put("isreadonly",false);
                   }else{
                       map.put("isreadonly",true);
                   }
               }
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       jo.put("total",total);
       jo.put("row",list);
        return jo.toString();
    }

    @ApiOperation(value = "申请延期")
    @RequestMapping(value = "passdelay",method = RequestMethod.GET)
    public boolean  passdelay(HttpServletRequest request,String id,String spsfty,String spyj,String jhyqsj){
        boolean flag = false;
        try {
            service.passdelay(id,spsfty,spyj);
            if(("1").equals(spsfty)){
                service.updateTasksj(jhyqsj,id);
            }
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    //查询项目基本信息
    @RequestMapping(value = "getXmjbxxById",method = RequestMethod.GET)
    @ResponseBody
    public String getXmjbxxById(String xmid, HttpServletResponse response){
        JSONObject jo = new JSONObject();
        GetXqslEntity entity = new GetXqslEntity();
        List<Map<String,Object>> list = new ArrayList<>();
        list = service.getxmList(xmid);
        entity = service.getXmjbxxById(xmid);
        jo.put("row",entity);
        jo.put("xmbh",list.get(0).get("XMBH"));
        return jo.toString();
    }

    //查询项目基本信息
    @RequestMapping(value = "handleAdd",method = RequestMethod.GET)
    @ResponseBody
    public boolean handleAdd(HttpServletRequest request,ProjectModel model){
        boolean flag = false;
        UserEntity user = (UserEntity)request.getSession().getAttribute("user");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            Map gss = xkylxService.getGssByXzqybh(model.getXmdz_xzqybh());
            model.setBmbmQs(gss.get("BH").toString());
            service.handleAdd(model);
            if(("20").equals(model.getSqlx())&&("0020201").equals(model.getAzlx())&&("0020202").equals(model.getSlazlx())){
//                查询水表
                List<Map<String,Object>> list = new ArrayList<>();
                list = service.getSbxx(model.getId());
                if(list.size()>0){
                    for(int i = 0;i<list.size();i++){
                        service.updateSblhh(list.get(i).get("ID").toString(),getSblhh("DB"));
                    }
                }
                logger.info("安装类型：移改表改为新装");
            }
            logger.info("项目修改人id："+user.getUserid());
            logger.info("修改项目id："+model.getId());
            logger.info("项目修改时间："+sdf.format(date));
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    /**
     * 获取水表立户号
     * @param mc
     * @return
     */
    public String getSblhh(String mc){
        Map<String,Object> map = new HashMap<String,Object>();
        String key = "";
        map.put("mc",mc);
        service.getSblhh(map);
        key= (String) map.get("key");
        return key;
    }
    /**
     * 上传用户资料
     * @param multipartFiles
     * @param filename
     * @param request
     * @return
     */
    @ApiOperation(value = "上传用户资料")
    @RequestMapping(value ="importFile_delay",method = RequestMethod.POST, consumes = { "multipart/mixed", "multipart/form-data" })
    public String importFile_delay(
            @RequestParam(value = "multipartFiles", required = false ) MultipartFile multipartFiles,
            @RequestParam(value = "filename", required = false ) String filename,
            @RequestParam(value = "delayid", required = false ) String delayid,
            @RequestParam(value = "xmid", required = false ) String xmid,
            HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        JSONObject jo = new JSONObject();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateString = formatter.format(currentTime);
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            if (Objects.isNull(multipartFiles)) {
                logger.error("文件为空");
                jo.put("msg","文件为空，请重新上传");
                return jo.toString();
            }
            list = service.getxmList(xmid);
            String fileDir = StringUtil.getBasePath()+"upload"+File.separator+list.get(0).get("XMBH")+File.separator+"delay"+File.separator;
            System.out.println(fileDir);
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
            if(StringUtil.isEmpty(delayid)){
                delayid = GuidUtils.getGuid();
            }
            String id = GuidUtils.getGuid();
            pd.put("id",id);//主键标识
            pd.put("fjlj","upload"+File.separator+list.get(0).get("XMBH")+File.separator+"delay"+File.separator+filename.split("\\.")[0]+dateString+"."+filename.split("\\.")[filename.split("\\.").length-1]);//附件路径
            pd.put("fjhz",filenames[filenames.length-1]);//附件后缀
            pd.put("delayid",delayid);
            //根据附件标识查询附件类型名称
//            List<Map<String,Object>> fjlx = xqslService.findFjlxById(pd);
//            String fjlx_yj = String.valueOf(fjlx.get(0).get("PARENTID"));
//            String fjlx_ej = String.valueOf(fjlx.get(0).get("ID"));
//            pd.put("fjlx_yj",fjlx_yj);//一级附件类型
//            pd.put("fjlx_ej",fjlx_ej);//一级附件类型
            pd.put("fjmc",filename);//附件名称
            pd.put("scr",user.getUserid());//创建人
            service.insertImportFh_delay(pd);//保存到上传附件表

        } catch (IOException e) {
            e.printStackTrace();
            jo.put("returncode", "999");
            jo.put("msg", "程序异常，请联系管理员");
            return jo.toString();
        }
        jo.put("returncode", "000");
        jo.put("msg", "上传成功");
        jo.put("delayid", delayid);
        return jo.toString();
    }
    @ApiOperation(value = "延期资料详情")
    @RequestMapping(value = "toDetial_delay",method = RequestMethod.GET)
    public String  toDetial_delay(HttpServletRequest request,String delayid){
        JSONObject jo = new JSONObject();
        List<Map<String,Object>> list = new ArrayList<>();
        try {
                list = service.gettoDetial_delayList(delayid);
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "删除延期资料")
    @RequestMapping(value = "deleteDelayFj",method = RequestMethod.GET)
    public boolean  deleteDelayFj(HttpServletRequest request){
        boolean flag = false;
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        String id = request.getParameter("id");
        try{
            if(!StringUtil.isEmpty(id)){
                service.deleteDelayFj(id,user.getUserid());
                flag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    @ApiOperation(value = "删除延期资料")
    @RequestMapping(value = "getXmsqlx",method = RequestMethod.GET)
    public String  getXmsqlx(HttpServletRequest request,String xmid){
        JSONObject jo = new JSONObject();
        List<Map<String,Object>> list = new ArrayList<>();
        try{
            list = service.getxmList(xmid);
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        return jo.toString();
    }

}
