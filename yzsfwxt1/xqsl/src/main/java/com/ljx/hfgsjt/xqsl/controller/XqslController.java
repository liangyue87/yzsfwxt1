package com.ljx.hfgsjt.xqsl.controller;

import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.xqsl.GetXqslEntity;
import com.ljx.hfgsjt.entity.xqsl.XmGsybxxEntity;
import com.ljx.hfgsjt.entity.xqsl.XmYzbEntity;
import com.ljx.hfgsjt.entity.xqsl.YwslXjxqsqLdxxEntity;
import com.ljx.hfgsjt.message.controller.MessageController;
import com.ljx.hfgsjt.message.service.MessageService;
import com.ljx.hfgsjt.repository.CalculateHours;
import com.ljx.hfgsjt.sgjg.service.SgxdService;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import com.ljx.hfgsjt.util.utils.StringUtil;
import com.ljx.hfgsjt.xqsl.service.XqslService;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.impl.util.json.JSONException;
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
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.ljx.hfgsjt.util.utils.DateUtils.getDateFormatByGMT;

@Controller
@RequestMapping(value = "XqslController")
@CrossOrigin
public class XqslController {
    @Autowired
    private XqslService xqslService;
    @Autowired
    private MessageService messageService;

    @Autowired
    private SgxdService sgxdService;
    @Autowired
    MessageController controller;

    private Logger logger = LoggerFactory.getLogger(XqslController.class);

    CalculateHours cal = new CalculateHours();

    @ApiOperation("根据当前登录人，显示申请列表")
    @RequestMapping(value = "defaultsqlx",method = RequestMethod.POST)
    @ResponseBody
    public String defaultsqlx(HttpServletRequest request){
        String flag = "false";
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");

        try {
            Map<String,Object> defaultsqlx = xqslService.defaultsqlx(user.getUserid());
            JSONObject jo = new JSONObject();
            jo.put("defaultsqlx",defaultsqlx);
            return jo.toString();
        } catch (JSONException e) {
            flag = "true";
        }

        return flag;
    }


    //查询所有数据
    @RequestMapping(value = "getAllXqsl",method = RequestMethod.GET)
    @ResponseBody
    public String getAllXqsl(Integer page, Integer size, HttpServletResponse response){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<GetXqslEntity> pagelist = xqslService.getAllXqsl(pageRequest);
        List<Map<String,Object>> sqList = xqslService.querySqList();//获得申请类型集合
        JSONObject jo = new JSONObject();
        jo.put("total",pagelist.getTotalElements());
        jo.put("row",pagelist.getContent());
        jo.put("sqList",sqList);
        return jo.toString();
    }

    /**
     * 条件查询需求受理列表
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value="queryXqsl",method = RequestMethod.GET)
    @ResponseBody
    public String queryXqsl(HttpServletResponse response,HttpServletRequest request) throws Exception {
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        pd.put("xmfzr",user.getUserid());
        pd.put("page",request.getParameter("page"));
        pd.put("size",request.getParameter("size"));
        pd.put("sqlx",request.getParameter("sqlx"));
        pd.put("status",request.getParameter("status"));
        pd.put("gcmc",request.getParameter("gcmc"));
        pd.put("lxr",request.getParameter("lxr"));
        pd.put("gcdz",request.getParameter("gcdz"));
        String date1 = request.getParameter("date1");
        if(!"".equals(date1) && date1 != null){
            String beforeDate = getDateFormatByGMT(date1);
            pd.put("beforeDate",beforeDate);
        }
        String date2 = request.getParameter("date2");
        if(!"".equals(date2) && date2 != null){
            String afterDate = getDateFormatByGMT(date2);
            pd.put("afterDate",afterDate);
        }
        List<GetXqslEntity> list = xqslService.queryXqsl(pd);
        List<Map<String,Object>> sqList = xqslService.querySqList();//获得申请类型集合
        int total = xqslService.queryAllXqqsLists(pd).size();
        jo.put("total",total);
        jo.put("row",list);
        jo.put("sqList",sqList);
        return jo.toString();
    }

    /**
     * 上传申请附件
     * @param multipartFiles
     * @param operation
     * @param filename
     * @param sqlx
     * @param request
     * @return
     */
    @RequestMapping(value ="importEnterPriseQuota",method = RequestMethod.POST, consumes = { "multipart/mixed", "multipart/form-data" })
    @ResponseBody
    public String importEnterPriseQuota(
            @RequestParam(value = "multipartFiles", required = false ) MultipartFile multipartFiles,
            @RequestParam(value = "operation", required = false ) String operation,
            @RequestParam(value = "filename", required = false ) String filename,
            @RequestParam(value = "sqlx", required = false ) String sqlx,
            @RequestParam(value = "sldid", required = false ) String sldid,
            @RequestParam(value = "sqbh", required = false ) String sqbh,
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
            String fileDir = StringUtil.getBasePath()+"upload"+File.separator+sqbh+File.separator;
            File dir = new File(fileDir);
            File file = null;

            file = new File(fileDir, filename.split("\\.")[0]+dateString+"."+filename.split("\\.")[filename.split("\\.").length-1]);
            System.out.println(dir);
            System.out.println(file);
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
            pd.put("fjlj","upload"+File.separator+sqbh+File.separator+filename.split("\\.")[0]+dateString+"."+filename.split("\\.")[filename.split("\\.").length-1]);//附件路径
            pd.put("fjhz",filenames[filenames.length-1]);//附件后缀
            pd.put("operation",operation);
            if(sldid != null && !"".equals(sldid)){
                pd.put("sldid",sldid);
            }else{
                pd.put("sldid","");
            }
            //根据附件标识查询附件类型名称
            List<Map<String,Object>> fjlx = xqslService.findFjlxById(pd);
            String fjlx_yj = String.valueOf(fjlx.get(0).get("PARENTID"));
            String fjlx_ej = String.valueOf(fjlx.get(0).get("ID"));
            pd.put("fjlx_yj",fjlx_yj);//一级附件类型
            pd.put("fjlx_ej",fjlx_ej);//一级附件类型
            pd.put("fjmc",filename);//附件名称
            pd.put("scr",user.getUserid());//创建人
            if("10".equals(sqlx)){
                pd.put("tablename","ywsl_xjxqsqb_fj");
                xqslService.insertImportFh(pd);//保存到上传附件表
            }else if("20".equals(sqlx)){
                pd.put("tablename","ywsl_gshbsqb_fj");
                xqslService.insertImportgsFh(pd);//保存到上传附件表
            }else if("30".equals(sqlx)){
                pd.put("tablename","ywsl_chgzsqb_fj");
                xqslService.insertImporthgFh(pd);//保存到上传附件表
            }else{
                jo.put("msg","上传失败");
                return jo.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            jo.put("returncode", "999");
            jo.put("msg", "程序异常，请联系管理员");
        }
        jo.put("msg", "上传成功");
        jo.put("fileId",id);
        return jo.toString();
    }

    @ApiOperation(value = " 上传")
    @RequestMapping(value ="saveImageToFj",method = RequestMethod.POST)
    @ResponseBody
    public String saveImageToFj(String dirName,String fileName,String sqlx,String operation,String sldid,
                                HttpServletRequest request){
        String id = "";
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        JSONObject jo = new JSONObject();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateString = formatter.format(currentTime);
        Map<String,Object> pd = new HashMap<>();
        String fileDir = StringUtil.getBasePath()+"upload"+File.separator+"xqsl"+File.separator;
        try{
            id = GuidUtils.getGuid();
            pd.put("id",id);//主键标识
            pd.put("operation",operation);
            if(sldid != null && !"".equals(sldid)){
                pd.put("sldid",sldid);
            }else{
                pd.put("sldid","");
            }
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
            if("10".equals(sqlx)){
                pd.put("tablename","ywsl_xjxqsqb_fj");
                xqslService.insertImportFh(pd);//保存到上传附件表
            }else if("20".equals(sqlx)){
                pd.put("tablename","ywsl_gshbsqb_fj");
                xqslService.insertImportgsFh(pd);//保存到上传附件表
            }else if("30".equals(sqlx)){
                pd.put("tablename","ywsl_chgzsqb_fj");
                xqslService.insertImporthgFh(pd);//保存到上传附件表
            }else{
                jo.put("msg","上传失败");
                return jo.toString();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("msg", "上传成功");
        jo.put("fileId",id);
        jo.put("path",fileDir+dirName+"/"+fileName);
        return jo.toString();
    }

    /**
     * 指派人员提交
     * @param name
     * @param rwid
     * @param type
     * @return
     */
    @RequestMapping(value="toZpry",method = RequestMethod.GET)
    @ResponseBody
    public String toZpry(String name,String rwid,String type,HttpServletRequest request, HttpServletResponse response){
        String flag = "false";
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            Map<String,Object> pd = new HashMap<>();
            pd.put("name",name);
            pd.put("rwid",rwid);
            WfTaskEntity task = new WfTaskEntity();
            String lcscId = "";
            WfRuInstEntity instEntity = new WfRuInstEntity();
            WfRuInstContentEntity wfRuInstContentEntity = new WfRuInstContentEntity();
            HttpSession session = request.getSession();
            UserEntity user = (UserEntity)session.getAttribute("user");//获取当前登录的用户信息
            String czr = user.getUserid();
            if("20".equals(type)){
                lcscId="10005";
                xqslService.updateGsSlrById(pd);//更新申请表项目经理
            }else if("10".equals(type) || "10".equals(type)){
                xqslService.updateJmSlrById(pd);//更新申请表项目经理
                lcscId="10004";
            }else{
                xqslService.updateHgSlrById(pd);//更新申请表项目经理
                lcscId="10006";
            }
            List<Map<String,Object>> varList = sgxdService.queryPdefId(rwid,lcscId);
            String procInstId = String.valueOf(varList.get(0).get("PROC_INST_ID"));
            String pdefId = String.valueOf(varList.get(0).get("C_ACTIVITYID"));

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
            WfTaskEntity newtask = new WfTaskEntity();
            List<WfReNodeEntity> Wflist = sgxdService.findByActivityidAndDirection(pdefId,direction);
            for(WfReNodeEntity wf : Wflist){
                WfDfActivityEntity ac = sgxdService.findByActivityid(wf.getActivityid());
                newtask.setTaskid(GuidUtils.getGuid());
                newtask.setPtaskid("0");//前置任务为空
                newtask.setProcInstId(procInstId);//流程实例ID
                newtask.setCActivityid(wf.getNextnode());//环节编号
                Date qssj = new Date();
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
                newtask.setIscurrent("1");//已完成
                newtask.setCzr(czr);//操作人
                newtask.setIscheck("0");//已签收
                newtask.setHasnew("2");//无子流程
                newtask.setRefkey("GET_XQSL");//关联实体Key
                newtask.setRefid(rwid);//关联实体ID
                newtask.setRwblr(name);//任务办理人
                sgxdService.saveWfTask(newtask);

                //修改流程实例表中的流程定义ID
                sgxdService.updateWfruInst(procInstId,wf.getNextnode());

                List<Map<String,Object>> sList = xqslService.querySqxxById(pd);
                String xmmc = String.valueOf(sList.get(0).get("XMMC"));
                String sqbh = String.valueOf(sList.get(0).get("SQBH"));
                saveDxDfs(newtask.getCActivityid(),xmmc,czr,newtask.getRwblr(),sqbh,newtask.getTaskid());
            }
            flag = "true";
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 新增受理单
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="saveSld",method = RequestMethod.POST)
    @ResponseBody
    public String saveSld(String sqzbid,HttpServletRequest request, HttpServletResponse response){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();//居民科
        Map<String,Object> pd1 = new HashMap<>();//工商科
        Map<String,Object> pd2 = new HashMap<>();//户改科
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity)session.getAttribute("user");//获取当前登录的用户信息
        String czr = user.getUserid();
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
        String update_id = request.getParameter("updateId");

        WfTaskEntity task = new WfTaskEntity();
        String procInstId = GuidUtils.getGuid();
        String pdefId = "";
        String rwblr = "";
        String tablename = "";
        WfRuInstEntity instEntity = new WfRuInstEntity();
        WfRuInstContentEntity wfRuInstContentEntity = new WfRuInstContentEntity();
//        Map<String, Object> map = new HashMap<String, Object>();
//        String ss="XQSL";
//        map.put("v_stop_run",ss);
//        sgxdService.texuChange(map);//获取任务编号
//        String qRwbh = "XQSL"+map.get("p_result");
        String id = sqzbid;
        try{
            String sqlx = request.getParameter("sqlx");
            String sqbh = request.getParameter("sqbh");//申请编号
            String azlx = request.getParameter("azlx");//安装类型
            String slrq = request.getParameter("slrq");//受理日期
            Date date = sdfd.parse(slrq);
            String ssqy = request.getParameter("ssqy");//所属区域
            String lxr = request.getParameter("lxr");//联系人
            String lxdh = request.getParameter("lxdh");//联系电话
            String zjlxdh = request.getParameter("zjlxdh");//座机号码
            try {
                if (zjlxdh.equals("null") || zjlxdh == null){
                    zjlxdh = "";
                }
            } catch (Exception e) {
                zjlxdh = "";
            }
            String gcmc = request.getParameter("gcmc");//工程名称
            String bjdw = request.getParameter("bjdw");//报建单位
            String yhxz = request.getParameter("yhxz");//用户性质
            String gcdz = request.getParameter("gcdz");//工程地址
            String fwxz = request.getParameter("fwxz");//工程地址
            pd.put("id",id);
            pd.put("sqlx",sqlx);
            pd.put("sqbh",sqbh);
            pd.put("azlx",azlx);
            pd.put("slr",czr);//受理人
            pd.put("slrq",date);
            pd.put("xmdz_xzqybh",ssqy);
            pd.put("lxr",lxr);
            pd.put("lxrsjh",lxdh);
            try {//前台不添写固定电话时，初始化为空
                pd.put("zjlxdh",zjlxdh);
            } catch (Exception e) {
                pd.put("zjlxdh","");
            }
            pd.put("xmmc",gcmc);
            pd.put("dwmc",bjdw);
            pd.put("yhxz",yhxz);
            pd.put("xmdz",gcdz);
            pd.put("fwxz",fwxz == null ? "" :fwxz);
            pd.put("zjzmj",request.getParameter("zjzmj") == null ? "" : request.getParameter("zjzmj"));//总建筑面积
            pd.put("zzjzmj",request.getParameter("zzmj") == null ? "" : request.getParameter("zzmj"));//住宅建筑面积
            pd.put("dkjc",request.getParameter("dkjc") == null ? "" : request.getParameter("dkjc"));//地块简称
            pd.put("hs",request.getParameter("hs") == null ? "" : request.getParameter("hs"));//户数
            pd.put("sjfw",request.getParameter("sjfw") == null ? "" : request.getParameter("sjfw"));//设计范围
            pd.put("dylh",request.getParameter("dylh") == null ? "" : request.getParameter("dylh"));//单元楼号
            pd.put("ghbfs",request.getParameter("ghbfs") == null ? "" : request.getParameter("ghbfs"));//规划泵房数
            if("".equals(request.getParameter("jfsj"))){
                pd.put("jfsj","");//交房时间
            }else{
                pd.put("jfsj",sdf.parse(request.getParameter("jfsj")));//DateUtils.getDateFormatByGMT()
            }
            pd.put("fqqk",request.getParameter("fqqk"));//分期情况
            //======================**========================//
            String jhkgsj = request.getParameter("jhkgsj");//计划开工时间
            if(!"".equals(jhkgsj) && jhkgsj != null){
                try {
                    Date jhkgsjs = sdfd.parse(jhkgsj);
                    pd1.put("jhkgsj",jhkgsjs);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else{
                pd1.put("jhkgsj","");
            }

            pd1.put("id",id);
            pd1.put("sqlx",sqlx);
            pd1.put("sqbh",sqbh);
            pd1.put("azlx",azlx);
            pd1.put("slr",czr);//受理人
            pd1.put("slrq",date);
            pd1.put("xmdz_xzqybh",ssqy);
            pd1.put("lxr",lxr);
            pd1.put("lxrsjh",lxdh);
            try {
                pd1.put("zjlxdh",zjlxdh);
            } catch (Exception e) {
               pd1.put("zjlxdh","");
            }
            pd1.put("xmmc",gcmc);
            pd1.put("dwmc",bjdw);
            pd1.put("yhxz",yhxz);
            pd1.put("xmdz",gcdz);
            pd1.put("yxazsl",request.getParameter("yxjbsl"));
            pd1.put("jsyt",request.getParameter("jsyt"));
            pd1.put("yhh",request.getParameter("yhh"));
            pd1.put("ybkj",request.getParameter("ybkj"));
            //======================**========================//
            pd2.put("id",id);
            pd2.put("sqlx",sqlx);
            pd2.put("sqbh",sqbh);
            pd2.put("azlx",azlx);
            pd2.put("slr",czr);//受理人
            pd2.put("slrq",date);
            pd2.put("xmdz_xzqybh",ssqy);
            pd2.put("lxr",lxr);
            pd2.put("lxrsjh",lxdh);
            try {
                pd2.put("zjlxdh",zjlxdh);
            } catch (Exception e) {
                pd2.put("zjlxdh","");
            }
            pd2.put("xmmc",gcmc);
            pd2.put("dwmc",bjdw);
            pd2.put("xqmc",request.getParameter("xqmc") == null ? "" :request.getParameter("xqmc"));
            pd2.put("xmdz",gcdz);
            pd2.put("xysfs",request.getParameter("ysfs") == null ? "" : request.getParameter("ysfs"));
            pd2.put("xyszk",request.getParameter("yszk") == null ? "" : request.getParameter("yszk"));
//            pd2.put("xhxzk",request.getParameter("hxzk") == null ? "" : request.getParameter("hxzk"));
            pd2.put("ldsx",request.getParameter("ldsx") == null ? "" :request.getParameter("ldsx"));
            pd2.put("lfds",request.getParameter("lfds") == null ? "" : request.getParameter("lfds"));
            pd2.put("lfhs",request.getParameter("lfhs") == null ? "" : request.getParameter("lfhs"));
            pd2.put("pfhs",request.getParameter("pfhs") == null ? "" : request.getParameter("pfhs"));
            pd2.put("zgcs",request.getParameter("zgcs") == null ? "" : request.getParameter("zgcs"));
            pd2.put("nbxf",request.getParameter("nbxf") == null ? "" : request.getParameter("nbxf"));
            pd2.put("ckyss",request.getParameter("ckys") == null ? "" : request.getParameter("ckys"));
            pd2.put("cpyss",request.getParameter("cpys") == null ? "" : request.getParameter("cpys"));
            pd2.put("mmyss",request.getParameter("mmys") == null ? "" : request.getParameter("mmys"));
            pd2.put("gcyss",request.getParameter("gcys") == null ? "" : request.getParameter("gcys"));
            pd2.put("wyyss",request.getParameter("wgys") == null ? "" : request.getParameter("wgys"));
            pd2.put("qtys",request.getParameter("qtys") == null ? "" : request.getParameter("qtys"));
            //======================**========================//
            //上传文件ID集合
            int count = Integer.parseInt(request.getParameter("count"));
            List<String> list = new ArrayList<>();
            String ids = "";
            if(count>0){
                for(int i=0;i<count;i++){
                    String value_cs = "value"+(i+1);
                    list.add(request.getParameter(value_cs));
                }
                ids += "(";
                for(int j=0;j<list.size();j++){
                    if(j==0){
                        ids += "'" + list.get(j) + "'";
                    }else{
                        ids += ",'" + list.get(j) + "'";
                    }
                }
                ids += ")";
            }

            if("30".equals(sqlx) || "户改科".equals(sqlx)){
                pdefId = "10003";
                tablename="YWSL_CHGZSQB";

                //新建小区申请楼栋ID集合
                int ldxxcount = Integer.parseInt(request.getParameter("ldxxcount"));
                List<String> ldxxList = new ArrayList<>();
                String ldxxids = "";
                if(ldxxcount>0){
                    for(int m=0;m<ldxxcount;m++){
                        String ldxxvalue_cs = "ldxxvalue"+(m+1);
                        ldxxList.add(request.getParameter(ldxxvalue_cs));
                    }
                    ldxxids += "(";
                    for(int n=0;n<ldxxList.size();n++){
                        if(n==0){
                            ldxxids += "'" + ldxxList.get(n) + "'";
                        }else{
                            ldxxids += ",'" + ldxxList.get(n) + "'";
                        }
                    }
                    ldxxids += ")";
                }

                //原总表信息表ID集合
                int yzbcount = Integer.parseInt(request.getParameter("yzbcount"));
                List<String> yzbList = new ArrayList<>();
                String yzbids = "";
                if(yzbcount>0){
                    for(int m=0;m<yzbcount;m++){
                        String yzbvalue_cs = "yzbvalue"+(m+1);
                        yzbList.add(request.getParameter(yzbvalue_cs));
                    }
                    yzbids += "(";
                    for(int n=0;n<yzbList.size();n++){
                        if(n==0){
                            yzbids += "'" + yzbList.get(n) + "'";
                        }else{
                            yzbids += ",'" + yzbList.get(n) + "'";
                        }
                    }
                    yzbids += ")";
                }

                pd2.put("tablename","ywsl_chgzsqb");//出户改造申请表
                pd2.put("tnamefj","ywsl_chgzsqb_fj");//出户改造附件表
                pd2.put("ids",ids);
                pd2.put("ldxxids",ldxxids);
                pd2.put("yzbids",yzbids);
                if(!"".equals(ids) && ids != null){
                    xqslService.deleteChgzFj(pd2);//删除出户改造附件表垃圾数据
                }
                if(!"".equals(ldxxids) && ldxxids != null){
                    xqslService.deleteXjxqSqld(pd2);//删除新建小区申请楼栋垃圾数据
                }
                if(!"".equals(yzbids) && yzbids != null){
                    xqslService.deleteYzb(pd2);//删除原总表垃圾数据
                }
                if(!"".equals(update_id) && update_id != null){
                    pd2.put("update_id",update_id);
                    xqslService.updateChgz(pd2);//出户改造更新
                    xqslService.updateChgzFjs(pd2);//更新出户改造附件表（修改时）
                    xqslService.updateXjxqSqlds(pd2);//更新新建小区申请楼栋表（修改时）
                    xqslService.updateYzbs(pd2);//更新原总表（修改时）
                }else{
                    xqslService.insertChgz(pd2);//出户改造提交
                    xqslService.updateChgzFj(pd2);//更新出户改造附件表（新增时）
                    xqslService.updateXjxqSqld(pd2);//更新新建小区申请楼栋表（新增时）
                    xqslService.updateYzb(pd2);//更新原总表（新增时）
                }
            }else if("10".equals(sqlx) || "居民科".equals(sqlx)){
                //新建小区申请楼栋ID集合
                int ldxxcount = Integer.parseInt(request.getParameter("ldxxcount"));
                List<String> ldxxList = new ArrayList<>();
                pdefId = "10001";
                tablename="YWSL_XJXQSQB";

                String ldxxids = "";
                if(ldxxcount>0){
                    for(int m=0;m<ldxxcount;m++){
                        String ldxxvalue_cs = "ldxxvalue"+(m+1);
                        ldxxList.add(request.getParameter(ldxxvalue_cs));
                    }
                    ldxxids += "(";
                    for(int n=0;n<ldxxList.size();n++){
                        if(n==0){
                            ldxxids += "'" + ldxxList.get(n) + "'";
                        }else{
                            ldxxids += ",'" + ldxxList.get(n) + "'";
                        }
                    }
                    ldxxids += ")";
                }
                pd.put("tablename","ywsl_xjxqsqb");//新建小区申请表
                pd.put("tnamefj","ywsl_xjxqsqb_fj");//新建小区附件表
                pd.put("ids",ids);
                pd.put("ldxxids",ldxxids);
                if(!"".equals(ids) && ids != null){
                    xqslService.deleteXjxqFj(pd);//删除出户改造附件表垃圾数据
                }
                if(!"".equals(ldxxids) && ldxxids != null){
                    xqslService.deleteXjxqSqld(pd);//删除新建小区申请楼栋垃圾数据
                }
                if(!"".equals(update_id) && update_id != null){
                    pd.put("update_id",update_id);
                    xqslService.updateXjxq(pd);
                    xqslService.updateXjxqFjs(pd);//更新新建小区附件表（修改时）
                    xqslService.updateXjxqSqlds(pd);//更新新建小区申请楼栋表（修改时）
                }else{
                    xqslService.insertXjxq(pd);//新建小区提交
                    xqslService.updateXjxqFj(pd);//更新新建小区附件表（新增时）
                    xqslService.updateXjxqSqld(pd);//更新新建小区申请楼栋表（新增时）
                }
            }else{
                pdefId = "10002";
                tablename="YWSL_GSHBSQB";
                pd1.put("tablename","ywsl_gshbsqb");//工商户表申请表
                pd1.put("tnamefj","ywsl_gshbsqb_fj");//工商户表附件表
                pd1.put("ids",ids);
                if(!"".equals(ids) && ids != null){
                    xqslService.deleteGshbFj(pd1);//删除出户改造附件表垃圾数据
                }
                if(!"".equals(update_id) && update_id != null){
                    pd1.put("update_id",update_id);
                    xqslService.updateGshb(pd1);
                    xqslService.updateGshbFjs(pd1);//更新工商户表附件表（修改时）
                }else{
                    xqslService.insertGshb(pd1);//工商户表提交
                    xqslService.updateGshbFj(pd1);//更新工商户表附件表（新增时）
                }
            }

            if(!"".equals(update_id) && update_id != null){

            }else{
                //启动流程实例
                instEntity.setProcInstId(procInstId);
                instEntity.setPdefId(pdefId);
                instEntity.setParammap(sqbh);
                instEntity.setStartdt(new Date());
                instEntity.setProcstatus(1);
                instEntity.setStartuser(czr);
                sgxdService.saveWfRuInst(instEntity);

                //将项目信息存入流程实例使用内容表(WF_RU_INST_CONTENT)
                wfRuInstContentEntity.setContentid(GuidUtils.getGuid());
                wfRuInstContentEntity.setProcInstId(procInstId);
                wfRuInstContentEntity.setTableName(tablename);
                wfRuInstContentEntity.setContentvalue(id);
                wfRuInstContentEntity.setContenttype("GET_XQSL");
                wfRuInstContentEntity.setContentname(id);
                sgxdService.saveWfRuInstContent(wfRuInstContentEntity);

                //创建任务，存入历史任务表中
                String taskId = GuidUtils.getGuid();
                task.setTaskid(taskId);
                task.setPtaskid("0");//前置任务为空
                task.setQssj(new Date());//起始时间
                task.setJssj(new Date());//结束时间
                task.setProcInstId(procInstId);//流程实例ID
                task.setCActivityid(pdefId);//环节编号
                task.setIscurrent("0");//已完成
                task.setCzr(czr);//操作人
                task.setIscheck("1");//已签收
                task.setHasnew("2");//无子流程
                task.setRwblr(czr);//任务办理人
                WfTaskHistoryEntity historyEntity = new WfTaskHistoryEntity(task);
                sgxdService.saveWfTaskHistory(historyEntity);

                //下一节点 创建任务
                String direction = "0";//流程方向 前进/回退
                WfTaskEntity newtask = new WfTaskEntity();
                List<WfReNodeEntity> Wflist = sgxdService.findByActivityidAndDirection(pdefId,direction);
                for(WfReNodeEntity wf : Wflist){
                    WfDfActivityEntity ac = sgxdService.findByActivityid(wf.getActivityid());
                    newtask.setTaskid(GuidUtils.getGuid());
                    newtask.setPtaskid("0");//前置任务为空
                    newtask.setProcInstId(procInstId);//流程实例ID
                    newtask.setCActivityid(wf.getNextnode());//环节编号
                    Date qssj = new Date();
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
                    newtask.setIscurrent("1");//已完成
                    newtask.setCzr(czr);//操作人
                    newtask.setIscheck("0");//已签收
                    newtask.setHasnew("2");//无子流程
                    newtask.setRefkey("GET_XQSL");//关联实体Key
                    newtask.setRefid(id);//关联实体ID
//                    rwblr = sgxdService.getUserIdByActivityId(wf.getNextnode());
                    newtask.setRwblr(czr);//任务办理人
                    sgxdService.saveWfTask(newtask);

                    //修改流程实例表中的流程定义ID
                    sgxdService.updateWfruInst(procInstId,wf.getNextnode());

//                    saveDxDfs(newtask.getCActivityid(),gcmc,czr,newtask.getRwblr(),sqbh,newtask.getTaskid());
                }
            }
            if("1".equals(request.getParameter("sfzp"))){
                if(!"".equals(update_id) && update_id != null){
                    flag = update_id;
                }else{
                    flag = id;
                }
            }else{
                flag = "true";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 按用户名分页查询待签收列表
     * @param page
     * @param size
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "getXqqs",method = RequestMethod.GET)
    @ResponseBody
    public String getXqqs(Integer page, Integer size,HttpServletResponse response,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity)session.getAttribute("user");//获取当前登录的用户信息
        String czr = user.getUserid();
        List<GetXqslEntity> list = xqslService.getXqqsListByXmjl(page, size,czr);
        int total = xqslService.getAllXqqsLists(czr).size();
        jo.put("total",total);
        jo.put("row",list);
        return jo.toString();
    }

    /**
     * 业务签收
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "toXqqs",method = RequestMethod.POST)
    @ResponseBody
    public String toXqqs(String rwid,String type,HttpServletResponse response,HttpServletRequest request){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        String lcscId = "";
        WfTaskEntity task = new WfTaskEntity();
        WfRuInstEntity instEntity = new WfRuInstEntity();
        WfRuInstContentEntity wfRuInstContentEntity = new WfRuInstContentEntity();
        try{
            pd.put("id",rwid);
            if("10".equals(type)){
                pd.put("tablename","ywsl_xjxqsqb");
            }else if("20".equals(type)){
                pd.put("tablename","ywsl_gshbsqb");
            }else if("30".equals(type)){
                pd.put("tablename","ywsl_chgzsqb");
            }
            xqslService.updateFpzt(pd);//签收

            List<Map<String,Object>> varList = sgxdService.queryPdefId(rwid,lcscId);
            String procInstId = String.valueOf(varList.get(0).get("PROC_INST_ID"));
            String pdefId = String.valueOf(varList.get(0).get("C_ACTIVITYID"));

            //结束流程实例
            instEntity = sgxdService.findByprocInstId(procInstId);
            WfHiInstEntity wfHiInstEntity = new WfHiInstEntity(instEntity);
            wfHiInstEntity.setEndtime(new Date());
            //将当前流程实例存入流程历史表
            sgxdService.savewfHiInst(wfHiInstEntity);
            //删除当前流程实例
            sgxdService.deleteInst(procInstId);

            //将当前流程实例内容表存入流程实例历史内容表
            wfRuInstContentEntity = sgxdService.findByProcInstIdAndContentname(procInstId,rwid);
            WfHiInstContentEntity wfHiInstContentEntity = new WfHiInstContentEntity(wfRuInstContentEntity);
            sgxdService.saveWfHiInstContent(wfHiInstContentEntity);
            //删除当前流程实例内容
            sgxdService.deleteInstContent(procInstId,rwid);

            //将当前task表里数据存入历史任务表中
            task = sgxdService.findBycActivityidAndProcInstId(pdefId,procInstId);
            task.setIscurrent("0");//已完成
            task.setIscheck("1");//已签收
            task.setJssj(new Date());//结束时间
            WfTaskHistoryEntity historyEntity = new WfTaskHistoryEntity(task);
            sgxdService.saveWfTaskHistory(historyEntity);

            //删除当前task表里数据
            sgxdService.deleteTask(pdefId,procInstId);
            flag = "true";
        }catch(Exception e){
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
    private String getZpry(HttpServletResponse response,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        String type = request.getParameter("type");
        if("10".equals(type)){
            pd.put("roleid","370bcad9650a44d99a0bc2e587963bfb");
        }else if("20".equals(type)){
            pd.put("roleid","231a28c9165844ecb695ae445d3ae8de");
        }else if("30".equals(type)){
            pd.put("roleid","ed212d777b304531b80bccf8c3a886d6");
        }
        List<Map<String,Object>> zpry = xqslService.getZpry(pd);
        jo.put("zpry",zpry);
        return jo.toString();
    }

    /**
     * 按申请类型获得上传类型
     * 获得所属区域集合
     * 获得安装类型集合
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "getSclx",method = RequestMethod.GET)
    @ResponseBody
    private String getSclx(String sqlx,HttpServletResponse response,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        List<Map<String,Object>> zlrow = new ArrayList<>();
        if("10".equals(sqlx)){
            pd.put("type","jmk");
            pd.put("fjlxid","2a52373c3bad43bb9b5236f6ce95455d");
            pd.put("roleid","370bcad9650a44d99a0bc2e587963bfb");
            pd.put("tablename","ywsl_xjxqsqb_fj");
            zlrow = xqslService.getZlrow(pd);
        }else if("20".equals(sqlx)){
            pd.put("type","gsk");
            pd.put("fjlxid","86c0fe78eb204bb5a45cfd64cae13839");
            pd.put("roleid","231a28c9165844ecb695ae445d3ae8de");
            pd.put("tablename","ywsl_gshbsqb_fj");
            zlrow = xqslService.getGsZlrow(pd);
        }else if("30".equals(sqlx)){
            pd.put("type","hgk");
            pd.put("fjlxid","96fd81786e1146b6abfd095cd135c15b");
            pd.put("roleid","ed212d777b304531b80bccf8c3a886d6");
            pd.put("tablename","ywsl_chgzsqb_fj");
            zlrow = xqslService.getHgZlrow(pd);
        }else {
            return jo.toString();
        }
        List<Map<String,Object>> sclx = xqslService.getSclx(pd);
        List<Map<String,Object>> qyList = xqslService.getQyList(pd);
        List<Map<String,Object>> azList = xqslService.getAzList(pd);
        List<Map<String,Object>> zpry = xqslService.getZpry(pd);

        jo.put("zlrow",zlrow);
        jo.put("zpry",zpry);
        jo.put("sclx",sclx);
        jo.put("qyList",qyList);
        jo.put("azList",azList);
        jo.put("sqzbid",GuidUtils.getGuid());
        return jo.toString();
    }

    @RequestMapping(value = "getZlrow",method = RequestMethod.GET)
    @ResponseBody
    private String getZlrow(String sqlx,String id,HttpServletResponse response,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        pd.put("id",id);
        List<Map<String,Object>> zlrow = new ArrayList<>();
        if("10".equals(sqlx)){
            pd.put("type","jmk");
            pd.put("fjlxid","2a52373c3bad43bb9b5236f6ce95455d");
            pd.put("roleid","370bcad9650a44d99a0bc2e587963bfb");
            pd.put("tablename","ywsl_xjxqsqb_fj");
            zlrow = xqslService.getEditZlrow(pd);
        }else if("20".equals(sqlx)){
            pd.put("type","gsk");
            pd.put("fjlxid","86c0fe78eb204bb5a45cfd64cae13839");
            pd.put("roleid","231a28c9165844ecb695ae445d3ae8de");
            pd.put("tablename","ywsl_gshbsqb_fj");
            zlrow = xqslService.getGsEditZlrow(pd);
        }else if("30".equals(sqlx)){
            pd.put("type","hgk");
            pd.put("fjlxid","96fd81786e1146b6abfd095cd135c15b");
            pd.put("roleid","ed212d777b304531b80bccf8c3a886d6");
            pd.put("tablename","ywsl_chgzsqb_fj");
            zlrow = xqslService.getHgEditZlrow(pd);
        }else {
            return jo.toString();
        }
        jo.put("zlrow",zlrow);
        return jo.toString();
    }

    /**
     * 根据已上传资料标识查询资料类型及数量
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "queryZlcount",method = RequestMethod.GET)
    @ResponseBody
    public String queryZlcount(String azlxbh,HttpServletResponse response,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        try{
            String sqlx = request.getParameter("sqlx");
            int count = Integer.parseInt(request.getParameter("count"));
            List<String> list = new ArrayList<>();
            for(int i=0;i<count;i++){
                String value_cs = "value"+(i+1);
                list.add(request.getParameter(value_cs));
            }
            if(list.isEmpty() == false){
                String ids = "(";
                for(int j=0;j<list.size();j++){
                    if(j==0){
                        ids += "'" + list.get(j) + "'";
                    }else{
                        ids += ",'" + list.get(j) + "'";
                    }
                }
                ids += ")";
                pd.put("ids",ids);
                if("1".equals(sqlx)){
                    pd.put("tablename","ywsl_xjxqsqb_fj");
                }else if("2".equals(sqlx)){
                    pd.put("tablename","ywsl_gshbsqb_fj");
                }else if("3".equals(sqlx)){
                    pd.put("tablename","ywsl_chgzsqb_fj");
                }else{
                    return jo.toString();
                }
                List<Map<String,Object>> varList = xqslService.queryZlcount(pd);
                jo.put("list",varList);
            }
            String azlx = xqslService.findAzlxBybh(azlxbh);
            jo.put("azlx",azlx);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 获取当前用户
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "queryUser",method = RequestMethod.GET)
    @ResponseBody
    public String queryUser(HttpServletResponse response,HttpServletRequest request,String type){
        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity)session.getAttribute("user");//获取当前登录的用户信息
        try{
            List<Map<String,Object>> sqList = xqslService.querySqList();//获得申请类型集合
            Map<String, Object> map = new HashMap<String, Object>();
            String ss="XQSL";
            map.put("v_stop_run",ss);
            sgxdService.texuChange(map);//获取任务编号
            List<Map<String,Object>> kjList = xqslService.queryKjList();//获得口径集合
            List<Map<String,Object>> ysxzList = xqslService.queryYsxzList();//获得用水性质集合
//            List<Map<String,Object>> hyflList = xqslService.queryHyflList();//获得行业分类集合
            List<Map<String,Object>> fwxzList = xqslService.queryFwxzList(type);//获得房屋性质集合
             xqslService.deleteLdxx();
            xqslService.deleteYzbxx();
            xqslService.deleteJmFjxx();
            xqslService.deleteGsFjxx();
            xqslService.deleteHgFjxx();
            jo.put("kjList",kjList);
            jo.put("ysxzList",ysxzList);
//            jo.put("hyflList",hyflList);
            jo.put("user",user.getUsername());
            jo.put("sqList",sqList);
            jo.put("fwxzList",fwxzList);
            jo.put("sqbh",map.get("p_result"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 根据申请标识获取已上传文件
     * 根据申请类型获得上传类型
     * 获得所属区域集合
     * 根据申请标识获得安装类型集合
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "getScwj",method = RequestMethod.GET)
    @ResponseBody
    public String getScwj(HttpServletResponse response,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        List<Map<String,Object>> zlrow = new ArrayList<>();
        try{
            pd.put("id",request.getParameter("id"));
            String sqlx = request.getParameter("sqlx");
            List<Map<String,Object>> varList = new ArrayList<>();
            List<Map<String,Object>> qyList = xqslService.getQyList(pd);
            if("10".equals(sqlx)){
                pd.put("tablename","ywsl_xjxqsqb_fj");
                pd.put("fjlxid","2a52373c3bad43bb9b5236f6ce95455d");
                pd.put("roleid","370bcad9650a44d99a0bc2e587963bfb");
                pd.put("type","jmk");
                varList = xqslService.getScwjJm(pd);
                zlrow = xqslService.getEditZlrow(pd);
            }else if("20".equals(sqlx)){
                pd.put("tablename","ywsl_gshbsqb_fj");
                pd.put("fjlxid","86c0fe78eb204bb5a45cfd64cae13839");
                pd.put("roleid","231a28c9165844ecb695ae445d3ae8de");
                pd.put("type","gsk");
                varList = xqslService.getScwjGs(pd);
                zlrow = xqslService.getGsEditZlrow(pd);
            }else if("30".equals(sqlx)){
                pd.put("tablename","ywsl_chgzsqb_fj");
                pd.put("type","hgk");
                pd.put("fjlxid","96fd81786e1146b6abfd095cd135c15b");
                pd.put("roleid","ed212d777b304531b80bccf8c3a886d6");
                varList = xqslService.getScwjHg(pd);
                zlrow = xqslService.getHgEditZlrow(pd);
            }else{
                return jo.toString();
            }
            List<Map<String,Object>> list = new ArrayList<>();
            List<Map<String,Object>> azList = xqslService.getAzList(pd);
            List<Map<String,Object>> sclx = xqslService.getSclx(pd);
            List<Map<String,Object>> zpry = xqslService.getZpry(pd);
            jo.put("zlrow",zlrow);
            jo.put("zpry",zpry);
            for (int i=0;i<varList.size();i++){
                Map<String,Object> map = new HashMap<>();
//                String basePath = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();
                // path是指欲下载的文件的路径。
                String path = String.valueOf(varList.get(i).get("FJMC"));
//                path = basePath+path;
                File file = new File(path);
                map.put("value",file);
                map.put("type",varList.get(i).get("TYPE"));
                map.put("id",varList.get(i).get("ID"));
                list.add(map);
            }
            jo.put("sclx",sclx);
            jo.put("qyList",qyList);
            jo.put("azList",azList);
            jo.put("files",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 新建小区申请楼栋表新增
     * @param entity
     * @param response
     * @param request
     * @return
     */
    @ApiOperation("新建小区申请楼栋表新增")
    @RequestMapping(value = "addLdxx",method = RequestMethod.POST)
    @ResponseBody
    public String addLdxx(YwslXjxqsqLdxxEntity entity,HttpServletResponse response,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        try{
            entity.setId(GuidUtils.getGuid());
            if(entity.getSqid() != null && !"".equals(entity.getSqid())){
                xqslService.addLdxx(entity);
            }else{
                xqslService.addLdxxs(entity);
            }
            jo.put("id",entity.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 原总表新增
     * @param entity
     * @param response
     * @param request
     * @return
     */
    @ApiOperation("原总表新增")
    @RequestMapping(value = "addYzb",method = RequestMethod.POST)
    @ResponseBody
    public String addYzb(XmYzbEntity entity,HttpServletResponse response,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        try{
            entity.setId(GuidUtils.getGuid());
            if(entity.getBz() == null){
                entity.setBz("");
            }
            if(entity.getKchs() == null){
                entity.setKchs("");
            }
            if(entity.getYsxzbh() == null){
                entity.setYsxzbh("");
            }
            if(entity.getHyflbh() == null){
                entity.setHyflbh("");
            }
            if(entity.getSqid() != null && !"".equals(entity.getSqid())){
                xqslService.addYzb(entity);
            }else{
                xqslService.addYzbs(entity);
            }
            jo.put("id",entity.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 新建小区申请楼栋表修改
     * @param entity
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "editLdxx",method = RequestMethod.POST)
    @ResponseBody
    public String editLdxx(YwslXjxqsqLdxxEntity entity,HttpServletResponse response,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        try{
            if(entity.getBz() == null){
                entity.setBz("");
            }
            xqslService.editLdxx(entity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 原总表修改
     * @param entity
     * @param response
     * @param request
     * @return
     */
    @ApiOperation("原总表修改")
    @RequestMapping(value = "editYzb",method = RequestMethod.POST)
    @ResponseBody
    public String editYzb(XmYzbEntity entity,HttpServletResponse response,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        try{
            if(entity.getKchs() == null){
                entity.setKchs("");
            }
            if(entity.getYsxzbh() == null){
                entity.setYsxzbh("");
            }
            if(entity.getHyflbh() == null){
                entity.setHyflbh("");
            }
            if(entity.getBz() == null){
                entity.setBz("");
            }
            xqslService.editYzb(entity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("原总表列表删除")
    @RequestMapping(value = "deleteGsbjxxById",method = RequestMethod.POST)
    @ResponseBody
    public String deleteGsbjxxById(String id,HttpServletResponse response){
        String flag = "false";
        try{
            xqslService.deleteGsbjxxById(id);
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 新建小区申请楼栋表列表
     * @return
     */
    @ApiOperation("新建小区申请楼栋表列表")
    @RequestMapping(value = "getLdxxList",method = RequestMethod.GET)
    @ResponseBody
    public String getLdxxList(HttpServletResponse response,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        List<YwslXjxqsqLdxxEntity> list = new ArrayList<>();
        try{
            String id = request.getParameter("id");
            if("".equals(id) || id == null){
                list = xqslService.getLdxxList();
            }else {
                pd.put("id",id);
                list = xqslService.getLdxxList1(pd);
            }
            jo.put("ldxxs",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 原总表信息表列表
     * @return
     */
    @ApiOperation("原总表信息表列表")
    @RequestMapping(value = "getYzbList",method = RequestMethod.GET)
    @ResponseBody
    public String getYzbList(HttpServletResponse response,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        List<XmYzbEntity> list = new ArrayList<>();
        try{
            String id = request.getParameter("id");
            if("".equals(id) || id == null){
                list = xqslService.getYzbList();
            }else {
                pd.put("id",id);
                list = xqslService.getYzbList1(pd);
            }
            jo.put("yzbxx",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("工商报建信息列表")
    @RequestMapping(value = "getGsbjxxList",method = RequestMethod.GET)
    @ResponseBody
    public String getGsbjxxList(HttpServletResponse response,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        List<XmGsybxxEntity> list = new ArrayList<>();
        try{
            String id = request.getParameter("id");
            if("".equals(id) || id == null){
                list = xqslService.getGsbjxxList();
            }else{
                pd.put("id",id);
                list = xqslService.getGsbjxxLists(pd);
            }
            jo.put("gsbjxx",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("工商报建信息新增")
    @RequestMapping(value = "addGsbjxx",method = RequestMethod.POST)
    @ResponseBody
    public String addGsbjxx(XmGsybxxEntity entity,HttpServletRequest request){
        String flag = "false";
        try{
            entity.setId(GuidUtils.getGuid());
            if(entity.getYbkj() == null){
                entity.setYbkj("");
            }
            if(entity.getYxkj() == null){
                entity.setYxkj("");
            }
            if(entity.getYhh() == null){
                entity.setYhh("");
            }
            if(entity.getBz() == null){
                entity.setBz("");
            }
            xqslService.addGsbjxx(entity);
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation("工商报建信息编辑")
    @RequestMapping(value = "editGsbjxx",method = RequestMethod.POST)
    @ResponseBody
    public String editGsbjxx(XmGsybxxEntity entity,HttpServletRequest request){
        String flag = "false";
        try{
            if(entity.getYbkj() == null){
                entity.setYbkj("");
            }
            if(entity.getYxkj() == null){
                entity.setYxkj("");
            }
            if(entity.getYhh() == null){
                entity.setYhh("");
            }
            if(entity.getBz() == null){
                entity.setBz("");
            }
            xqslService.editGsbjxx(entity);
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 新建小区申请楼栋表列表删除
     * @param id
     * @param response
     * @return
     */
    @ApiOperation("新建小区申请楼栋表列表删除")
    @RequestMapping(value = "deleteLdxxById",method = RequestMethod.POST)
    @ResponseBody
    public String deleteLdxxById(String id,HttpServletResponse response){
        String flag = "err";
        try {
            xqslService.deleteLdxxById(id);
        } catch (Exception e) {
            flag = "ok";
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 原总表列表删除
     * @param id
     * @param response
     * @return
     */
    @ApiOperation("原总表列表删除")
    @RequestMapping(value = "deleteYzbById",method = RequestMethod.POST)
    @ResponseBody
    public String deleteYzbById(String id,HttpServletResponse response){
        String flag = "err";
        try {
            xqslService.deleteYzbById(id);
        } catch (Exception e) {
            flag = "ok";
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation("根据ID查询需求受理单信息")
    @RequestMapping(value = "getXqslById",method = RequestMethod.GET)
    @ResponseBody
    public String getXqslById(String rwid){
        JSONObject jo = new JSONObject();
        try{
            List<Map<String,Object>> list = xqslService.getXqslById(rwid);
            jo.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("根据项目ID查询需求受理单信息")
    @RequestMapping(value = "getXqslByXmid",method = RequestMethod.GET)
    @ResponseBody
    public String getXqslByXmid(String rwid){
        JSONObject jo = new JSONObject();
        List<Map<String,Object>> list = new ArrayList<>();
        try{
            List<Map<String,Object>> xmList = xqslService.getXmxxByid(rwid);
            if(xmList.isEmpty() == false){
                String sqbh = String.valueOf(xmList.get(0).get("SQBH"));
                list = xqslService.getXqslByBh(sqbh);
            }else{
                list = xqslService.getXqslById(rwid);
            }
            jo.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation(value = "资料详情")
    @RequestMapping(value = "toDetial",method = RequestMethod.GET)
    @ResponseBody
    public String  toDetial(HttpServletRequest request,String yjfjlx,String ejfjlx,String sqlx,String sqdid,
                            Integer page,Integer size){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        pd.put("yjfjlx",yjfjlx);
        pd.put("ejfjlx",ejfjlx);
        pd.put("page",page);
        pd.put("size",size);
        List<Map<String,Object>> list = new ArrayList<>();
        int total = 0;
        try{
            if("10".equals(sqlx)){
                pd.put("tablename","ywsl_xjxqsqb_fj");
                pd.put("sqdid",sqdid);
                list = xqslService.toDetial(pd);
                total = xqslService.toAllDetial(pd).size();
            }else if("20".equals(sqlx)){
                pd.put("tablename","ywsl_gshbsqb_fj");
                pd.put("sqdid",sqdid);
                list = xqslService.toDetialGs(pd);
                total = xqslService.toAllDetialGs(pd).size();
            }else if("30".equals(sqlx)){
                pd.put("tablename","ywsl_chgzsqb_fj");
                pd.put("sqdid",sqdid);
                list = xqslService.toDetialHg(pd);
                total = xqslService.toAllDetialHg(pd).size();
            }
            jo.put("row",list);
            jo.put("total",total);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation(value = "删除附件")
    @RequestMapping(value = "deleteFj",method = RequestMethod.GET)
    @ResponseBody
    public String  deleteFj(String id,String sqlx,String size,String yjfjlx,String ejfjlx,String sqdid,
                            HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        pd.put("id",id);
        pd.put("yjfjlx",yjfjlx);
        pd.put("ejfjlx",ejfjlx);
        try{
            if(!StringUtil.isEmpty(id)){
                if("10".equals(sqlx)){
                    pd.put("tablename","ywsl_xjxqsqb_fj");
                    xqslService.deleteFj(pd);
                }else if("20".equals(sqlx)){
                    pd.put("tablename","ywsl_gshbsqb_fj");
                    xqslService.deleteFj(pd);
                }else if("30".equals(sqlx)){
                    pd.put("tablename","ywsl_chgzsqb_fj");
                    xqslService.deleteFj(pd);
                }
                jo.put("flag","true");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 下载用户资料
     * @param path
     * @param response
     * @return
     */
    @ApiOperation(value = "查看图片")
    @RequestMapping(value = "download",method = RequestMethod.POST)
    @ResponseBody
    public HttpServletResponse download(String path,String type, HttpServletRequest request,HttpServletResponse response) {
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
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
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
     * 业务签收
     * 发送短信
     * @param lxr
     * @param lxrsjh
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "Qsmessages",method = RequestMethod.POST)
    @ResponseBody
    public String Qsmessages(String rwid,String type,String lxr,String lxrsjh,HttpServletResponse response,HttpServletRequest request) {
        JSONObject jo = new JSONObject();

        //发送短信给业务办理人员
        String content = "";
        String gddh = "";//项目经理的固定电话
        Map<String, Object> user = null;
        Map<String, Object> pd = new HashMap<>();
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");
        DxDfsEntity dxDfsEntity = new DxDfsEntity();

        pd.put("id", rwid);
        if ("10".equals(type)) {
            pd.put("tablename", "ywsl_xjxqsqb");
        } else if ("20".equals(type)) {
            pd.put("tablename", "ywsl_gshbsqb");
        } else if ("30".equals(type)) {
            pd.put("tablename", "ywsl_chgzsqb");
        }
        Map<String, Object> list = xqslService.getxmjl(pd);//获取该业务项目经理（项目负责人）

        try {
            gddh = list.get("GDDH").toString();//固定电话为空时，赋值会抛出异常
        } catch (Exception e) {
            gddh = "";
        }


        try {
            //业务办理签收时，发送短信通知办理人员
            dxDfsEntity.setId(GuidUtils.getGuid());//标识
            dxDfsEntity.setDxmbid("10");//短信模版标识
            dxDfsEntity.setFsr(userEntity.getUserid());//发送人
            dxDfsEntity.setFssj(new Date());//发送时间
            dxDfsEntity.setFscs("1");//发送次数
            Map<String,Object> dxmb = messageService.getDxMb("10");//模版4：任务提醒，替换短信模版内容
            content = dxmb.get("DXMB").toString();//获取短信模版
            content = content.replace("[项目经理]", list.get("USERNAME").toString());
            content =   content.replace("[项目经理联系方式]", gddh);
            content =  content.replace("[业务编号]", list.get("SQBH").toString());
            content =  content.replace("[业务名称]", list.get("XMMC").toString());
            dxDfsEntity.setFsnr(content);//发送内容
            dxDfsEntity.setJsr(lxr);//接收人
            dxDfsEntity.setJshm(lxrsjh);//接收号码
            xqslService.saveDxDfsEntity(dxDfsEntity);//保存
            jo.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("result","error");
        }
      /*  try {
            SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //user = messageService.getUserxxById(userEntity.getUserid());
            usrename = lxr;
            phone = lxrsjh;
            //MessageController controller = new MessageController();
            Map<String, Object> dxmb = messageService.getDxMb("10");
            content = dxmb.get("DXMB").toString();
           *//* content.replaceAll("[任务编号]",xckcRwzbEntity.getRwbh());
            content.replaceAll("[勘查时间]",sdft.format(xckcRwzbEntity.getYykcrq()));
            GetXckcxxView getXckcxxView = service.getXckcxxByView(xmid);
            Map<String,Object> xmjl= messageService.getUserxxById(getXckcxxView.getXmjl());
            content.replaceAll("[项目经理]",xmjl.get("USERNAME").toString());
            content.replaceAll("[联系方式]",xmjl.get("MOBILPHONE").toString());*//*
            try {
                pd.put("id", rwid);
                if ("10".equals(type)) {
                    pd.put("tablename", "ywsl_xjxqsqb");
                } else if ("20".equals(type)) {
                    pd.put("tablename", "ywsl_gshbsqb");
                } else if ("30".equals(type)) {
                    pd.put("tablename", "ywsl_chgzsqb");
                }
                Map<String, Object> list = xqslService.getxmjl(pd);//获取该业务项目经理（项目负责人）

                try {
                    gddh = list.get("GDDH").toString();//固定电话为空时，赋值会抛出异常
                } catch (Exception e) {
                   gddh = "";
                }

                content = content.replace("[项目经理]", list.get("USERNAME").toString());
                content =   content.replace("[项目经理联系方式]", gddh);
                content =  content.replace("[业务编号]", list.get("SQBH").toString());
                content =  content.replace("[业务名称]", list.get("XMMC").toString());
                controller.sendMsg("002","1",null,phone,content,
                        ((UserEntity)request.getSession().getAttribute("user")).getUserid(),"10",lxr);
               *//* controller.sendMessage("002", "1", null, phone, content);
                DxFsjlEntity dxFsjlEntity = new DxFsjlEntity(GuidUtils.getGuid(), "9",
                        ((UserEntity) request.getSession().getAttribute("user")).getUserid(), new Date(), "1", "1", content,
                        user.get("username").toString(), phone, "");*//*
               *//* messageService.saveDxFsjl(dxFsjlEntity);*//*
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/



        return jo.toString();
    }

    @RequestMapping(value = "queryXqByid",method = RequestMethod.GET)
    @ResponseBody
    public String queryXqByid(String rwid){
        JSONObject jo = new JSONObject();
        try{
            List<GetXqslEntity> list = xqslService.queryXqByid(rwid);
            jo.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("删除需求")
    @RequestMapping(value = "deleteXq",method = RequestMethod.POST)
    @ResponseBody
    public String deleteXq(String id,String type){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        pd.put("id",id);
        try{
            if("10".equals(type)){
                pd.put("tablename","ywsl_xjxqsqb");
            }else if("20".equals(type)){
                pd.put("tablename","ywsl_gshbsqb");
            }else{
                pd.put("tablename","ywsl_chgzsqb");
            }
            xqslService.deleteXq(pd);
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation("查询转单人员")
    @RequestMapping(value = "queryzdry",method = RequestMethod.POST)
    @ResponseBody
    public String queryzdry(HttpServletRequest request){
        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        try{
            List<Map<String,Object>> list = xqslService.queryzdry(user.getUserid());
            jo.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("转单")
    @RequestMapping(value = "saveZd",method = RequestMethod.POST)
    @ResponseBody
    public String saveZd(String taskId,String zdr,String rwid,String type,String xmmc,HttpServletRequest request){
        String flag = "false";
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        Map<String,Object> pd = new HashMap<>();
        pd.put("taskId",taskId);
        pd.put("zdr",zdr);
        pd.put("rwid",rwid);
        try{
            if("10".equals(type)){
                pd.put("tablename","ywsl_xjxqsqb");
            }else if("20".equals(type)){
                pd.put("tablename","ywsl_gshbsqb");
            }else{
                pd.put("tablename","ywsl_chgzsqb");
            }
            //更改任务办理人
            xqslService.updateRwblr(pd);
            //更改申请表项目经理
            xqslService.updateXmjl(pd);
            //日志记录
            List<UserEntity> userList = xqslService.queryUserByid(zdr);
            logger.info(user.getUsername()+"于"+ new Date() + "将[" + xmmc + "]项目转单给"+userList.get(0).getUsername());
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 任务提醒 保存到待发送表中
     * @param activityid
     * @param gcmc
     * @param fsr
     * @param jsr
     * @param taskid
     * @return
     */
    public String saveDxDfs(String activityid,String gcmc,String fsr,String jsr,String rwbh,String taskid) {
        WfDfActivityEntity wfDfActivityEntity = sgxdService.findByActivityid(activityid);
//        List<XmInstance> xmInstances = sgxdService.getXmxxByid(xmid);
//        String xmmc = xmInstances.get(0).getXmmc();
        UserEntity userEntity = sgxdService.getUserByUserId(jsr);
        DxDfsEntity dxDfsEntity = new DxDfsEntity();//初始化短信模版
        Map<String,Object> dxmb = messageService.getDxMb("4");
        String content = dxmb.get("DXMB").toString();
        content = content.replace("[环节名称类型]", wfDfActivityEntity.getActivityname());
        content =   content.replace("[任务单编号]", rwbh);
        content =  content.replace("[项目名称]", gcmc);
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





}
