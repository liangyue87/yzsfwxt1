package com.ljx.hfgsjt.wtys.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.dto.yswt.*;
import com.ljx.hfgsjt.entity.yj.azlxEntity;
import com.ljx.hfgsjt.message.controller.MessageController;
import com.ljx.hfgsjt.message.service.MessageService;
import com.ljx.hfgsjt.repository.CalculateHours;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import com.ljx.hfgsjt.util.utils.StringUtil;
import com.ljx.hfgsjt.wtys.service.YSwtService;
import com.ljx.hfgsjt.xkybz.service.XkylxService;
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

;

/**
 * 四.預算委派
 */


@Controller
@RequestMapping(value = "YswtController")
public class YswtController {


    @Autowired
    private XkylxService service;
    @Autowired
    private YSwtService yswtservice;
    @Autowired
    private MessageService messageService;
    @Autowired
    MessageController controller;

    private String ysrid;

    CalculateHours cal = new CalculateHours();
    private String procinstidNew;


    /**
     * 模糊查询XM_YSZT视图
     * @param xmbh  项目id
     * @param lxr   联系人
     * @param xmdz  项目地址
     * @param azlx  安装类型
     * @param yszt  预算状态
     * @param page  页数
     * @param size  条数
     * @return
     */
    @ApiOperation(value = "模糊查询")
    @RequestMapping(value = "TestgetYszt", method = RequestMethod.GET)
    @ResponseBody
    public String TestgetYszt(String bjzt,String xmid,int bs,String xmmc,String date1,String date2,String xmbh,String lxr,String xmdz,String azlx,String yszt,Integer page,Integer size,String sfsh,HttpServletRequest request){
        if(!"".equals(date1) && date1 != null){
            try {
                date1 = getDateFormatByGMT(date1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //   pd.put("beforeDate",beforeDate);
        }
        if(!"".equals(date2) && date2 != null){
            try {
                date2 = getDateFormatByGMT(date2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // pd.put("afterDate",afterDate);
        }
        //获取当前登录人
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");
        switch (bs) {
            case 1: //预算分配
                JSONObject jo = new JSONObject();
                List<YsVzpNewEntity> yszp = yswtservice.geYszptall(xmid,date1,date2,xmbh, lxr, xmdz, azlx, page - 1, size);
                jo.put("yszp", yszp);
                jo.put("total", yswtservice.getTotal());
                return jo.toString();

            case 2://查询预算编制
                jo = new JSONObject();
                List<YsVbzNewEntity> ysbz = yswtservice.getYsBz(xmid,date1,date2,yszt,xmbh, lxr, xmdz, azlx, page - 1, size);
                jo.put("ysbz", ysbz);
                jo.put("total", yswtservice.getTotal());
                return jo.toString();
            case 3://查询领导审批
                jo = new JSONObject();
                List<YsLdshNewEntity> ysldsh = yswtservice.getYsLdsh(sfsh,xmbh, lxr, xmdz, azlx, page - 1, size);
                jo.put("ysldsh", ysldsh);
                jo.put("total", yswtservice.getTotal());
                return jo.toString();
            case 4://查询签收
                jo = new JSONObject();
                List<YsQsEntity> ysqs = yswtservice.getYsQs(sfsh,xmbh, lxr, xmdz, azlx, page - 1, size);
                jo.put("ysqs", ysqs);
                jo.put("total", yswtservice.getTotal());
                return jo.toString();

            default://查询预算委托
                jo = new JSONObject();
                List<XmYsztEntity> yswt = yswtservice.getall(bjzt,userEntity.getUserid(),date1,date2,xmbh, lxr, xmdz, azlx, yszt,xmmc, page - 1, size);
                jo.put("yswt", yswt);
                jo.put("total", yswtservice.getTotal());
                return jo.toString();



        }


    }

    /**
     * 初始化综合查询（红黄绿灯列表）
     * @return
     */
    @ApiOperation(value = "初始化综合查询（红黄绿灯列表）")
    @RequestMapping(value = "getHhldcx" ,method = RequestMethod.POST)
    @ResponseBody
    public String getHhldcx(ZhcxHhldcxEntity hhldcxEntity,HttpServletRequest request){
        org.activiti.engine.impl.util.json.JSONObject jo = new org.activiti.engine.impl.util.json.JSONObject();
        hhldcxEntity.setStartRow();
        hhldcxEntity.setEndRow();
        List<Map<String,Object>> hhldcx = yswtservice.getZhcxHhldcx(hhldcxEntity);
        int total = yswtservice.getZhcxHhldcxToTal(hhldcxEntity);
        jo.put("hhldcx", hhldcx);
        jo.put("total", total);
        return jo.toString();
    }

    /**
     * 初始化综合查询（预算编制列表）
     * @return
     */
    @ApiOperation(value = "初始化综合查询（预算编制列表）")
    @RequestMapping(value = "getYsbzcxList" ,method = RequestMethod.POST)
    @ResponseBody
    public String getYsbzcxList(ZhcxYsbzEntity zhcxYsbzEntity,HttpServletRequest request){
        org.activiti.engine.impl.util.json.JSONObject jo = new org.activiti.engine.impl.util.json.JSONObject();
        zhcxYsbzEntity.setStartRow();
        zhcxYsbzEntity.setEndRow();
        List<Map<String,Object>> YsbzcxList = yswtservice.getYsbzcxList(zhcxYsbzEntity);
        int total = yswtservice.getYsbzcxListToTal(zhcxYsbzEntity);
        jo.put("YsbzcxList", YsbzcxList);
        jo.put("total", total);
        return jo.toString();
    }

    /**
     * 获取设计审图主表id
     * @param xmid
     * @param activityId
     * @return
     */
    @ApiOperation(value = "获取设计审图主表id")
    @RequestMapping(value = "getsjstrzwid", method = RequestMethod.POST)
    @ResponseBody
    public String getsjstrzwid(String xmid,String activityId){
        String flag="ok";
        JSONObject jo = new JSONObject();

        try {
            List<WfTaskHistoryEntity>  sjstrzwid= yswtservice.getsjstrzwid(xmid,activityId);
            jo.put("sjstrzwid",sjstrzwid);
            return jo.toString();

        } catch (Exception e) {
            flag="error";
        }

        return flag;
    }


    /**
     * 预算金额总汇
     * @param id
     * @return
     */
    @ApiOperation(value = "预算金额总汇")
    @RequestMapping(value = "getYSJEZH",method = RequestMethod.GET)
    @ResponseBody
    public String getYSJEZH(String id){
        YsYsjezh ysjezh = new YsYsjezh();
        ysjezh.setYsrwzbid(id);
        List<YsYsjezh> list = yswtservice.getAllYsjezh(ysjezh);
        JSONObject jo = new JSONObject();
        jo.put("ysjezh",list);
        return jo.toString();
    }


    /**
     * 获取预算资料信息表
     * @return
     */
    @RequestMapping(value = "getYsZlxx" ,method = RequestMethod.GET)
    @ResponseBody
    public String getYsZlxx(String xmid,String zbid){
        List<YsZlxx> zlxx = yswtservice.getYsZlxx(xmid,zbid);

        JSONObject jo = new JSONObject();
        jo.put("YsZlxx",zlxx);


        return jo.toString();
    }

    /**
     * 删除预算编制附件
     * @param fjid
     * @return
     */
    @ApiOperation(value = "删除预算编制附件")
    @RequestMapping(value = "deleteFkFj", method = RequestMethod.POST)
    @ResponseBody
    public String deleteFkFj(String fjid){
        String flag="ok";

        try {
            yswtservice.deleteFkFj(fjid);
        } catch (Exception e) {
            flag="error";
        }

        return flag;
    }

    /**
     * 下载用户资料
     * @param path
     * @param response
     * @return
     */

    @ApiOperation(value = "下载预算资料")
    @RequestMapping(value = "download",method = RequestMethod.POST)
    public HttpServletResponse download(String path, HttpServletRequest request,HttpServletResponse response) {
        try {
            /*String basePath = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();
            // path是指欲下载的文件的路径。
            path = basePath+path;*/
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
     * 预算编制提交 40003 -> 40008
     * @param json
     * @return
     * zje   预算总金额
     * ysrwb    预算主表id
     * ysbzbz    预算编制备注
     */
    @ApiOperation(value = "预算编制提交")
    @RequestMapping(value = "setYsbz", method = RequestMethod.POST)
    @ResponseBody
    public String  setYsbz(String json,HttpServletRequest request){
        String direction="0";//0 前进  1 后退
        String activityid="";
        String flag="ok";
        String pdefid= "40008";
        DxDfsEntity dxDfsEntity = new DxDfsEntity();//初始化短信模版
        try {
            JSONObject jo = new JSONObject(JSON.parseObject(json));
            Map<String, Object> map = jo;
            for(Map.Entry<String, Object> entry : map.entrySet()){
                entry.getValue().toString();
            }
            yswtservice.setYsBz(map,"1");
            String xmid=map.get("xmid").toString();

            YsRwzb zb=yswtservice.getYsRwzbByXmid(xmid);

            //更新当前的实例的节点标识
            yswtservice.updateWfRuInstPdefId(pdefid,map.get("procinstid").toString());

            //1:通过xmid查找WfRuInst（xmid,tablename） 结果不唯一
            WfRuInstEntity wfRuInstEntity = yswtservice.getWfRuInstByXmidAndPdefid(pdefid,activityid,xmid);

            //2.通过InstId 查找Task  结果只有一个
            WfTaskEntity wfTaskEntity = null;
            wfTaskEntity = yswtservice.getTaskByInstId(wfRuInstEntity.getProcInstId());

            //3.把这个放到历史任务里  新建一个当前任务
            WfTaskHistoryEntity wfTaskHistoryEntity = new WfTaskHistoryEntity(wfTaskEntity);
            wfTaskHistoryEntity.setIscurrent("0");//状态标志：0已做
            wfTaskHistoryEntity.setJssj(new Date());
            service.saveWfTaskHistory(wfTaskHistoryEntity);//保存历史任务
            service.deleteTaskById(wfTaskEntity.getTaskid());//删除当前任务
            List<WfReNodeEntity> list = service.findByActivityidAndDirection(wfTaskEntity.getCActivityid(),direction);
            for(WfReNodeEntity wf : list){
                WfTaskEntity task = new WfTaskEntity();
                String taskId = GuidUtils.getGuid();
                //---------------短信taskid---------------------------------
                dxDfsEntity.setTaskid(taskId);
                //------------------------------------------------
                //WfDfActivityEntity ac = service.findByActivityid(wf.getActivityid());
                task.setTaskid(taskId);
                task.setRefkey("YS_RWZB");
                task.setRefid(zb.getId());
                task.setQssj(new Date());
                WfDfActivityEntity wfDfActivityEntity = service.findByActivityid(wf.getNextnode());
                Date qssj = new Date();
                SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                task.setCheck_sx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
                task.setJssx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                //task.setJssx_yl(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getFinishMinute())));
                task.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute())+Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                task.setPtaskid(wfTaskEntity.getTaskid());//前置任务
                task.setProcInstId(wfRuInstEntity.getProcInstId());//流程实例ID
                task.setCActivityid(wf.getNextnode());//环节编号
                task.setIscurrent("1");//待做
                UserEntity user = (UserEntity)request.getSession().getAttribute("user");//获取当前登录的用户信息
                task.setCzr(user.getUserid());//指派人
                task.setIscheck("0");//未签收
                task.setHasnew("2");//无子流程
                task.setRwblr(service.getUserIdByActivityId("40008"));
                service.saveWfTask(task);

                //预算编制提交时，发送短信通知办理人
                String content = "";
                Map<String,Object> userMap=null;


                dxDfsEntity.setId(GuidUtils.getGuid());//标识
                dxDfsEntity.setDxmbid("4");//短信模版标识
                dxDfsEntity.setFsr(user.getUserid());//发送人
                dxDfsEntity.setFssj(new Date());//发送时间
                dxDfsEntity.setFscs("1");//发送次数
                Map<String,Object> dxmb = messageService.getDxMb("4");//模版4：任务提醒，替换短信模版内容
                XmInstanceEntity xmInstanceEntity = yswtservice.getXmfzr(xmid);//获取项目名称
                content = dxmb.get("DXMB").toString();//获取短信模版
                content = content.replace("[环节名称类型]","预算审核");
                content = content.replace("[任务单编号]",zb.getRwbh());
                content = content.replace("[项目名称]",xmInstanceEntity.getXmmc());
                dxDfsEntity.setFsnr(content);//发送内容
                UserEntity userEntity = yswtservice.getUserIdAndUserMobilephone("40008");
                dxDfsEntity.setJsr(userEntity.getUserid());//接收人
                dxDfsEntity.setJshm(userEntity.getMobilephone());//接收号码
                yswtservice.saveDxDfsEntity(dxDfsEntity);//保存


            }
        } catch (Exception e) {
            e.printStackTrace();
            flag="error";
        }


        return flag;
    }

    /**
     * 用户保存预算编制的预算汇总表
     * @param json
     * @param request
     * @return
     */
    @ApiOperation(value = "预算编制保存")
    @RequestMapping(value = "saveYsbz", method = RequestMethod.POST)
    @ResponseBody
    public String  saveYsbz(String json,HttpServletRequest request){
        String flag="ok";
        try {
            JSONObject jo = new JSONObject(JSON.parseObject(json));
            Map<String, Object> map = jo;
            for(Map.Entry<String, Object> entry : map.entrySet()){
                entry.getValue().toString();
            }
            yswtservice.setYsBz(map,"2");

        } catch (Exception e) {
            e.printStackTrace();
            flag="error";
        }


        return flag;
    }

    /**
     * 保存预算补充
     * @param json
     * @param request
     * @return
     */
    @ApiOperation(value = "保存预算补充")
    @RequestMapping(value = "saveYsbc", method = RequestMethod.POST)
    @ResponseBody
    public String  saveYsbc(String json,HttpServletRequest request){
        String flag="ok";
        try {
            JSONObject jo = new JSONObject(JSON.parseObject(json));
            Map<String, Object> map = jo;
            for(Map.Entry<String, Object> entry : map.entrySet()){
                entry.getValue().toString();
            }
            yswtservice.setYsBz(map,"3");

        } catch (Exception e) {
            e.printStackTrace();
            flag="error";
        }


        return flag;
    }

    /**
     * 查询某任务的预算数据
     * @param zbid
     * @param request
     * @return
     */
    @ApiOperation(value = "查询某任务的预算数据")
    @RequestMapping(value = "ysbg", method = RequestMethod.POST)
    @ResponseBody
    public String  ysbg(String zbid,HttpServletRequest request){
       // yswtservice.getysbg(zbid);

        List<YsRwcb> ysRwcb = yswtservice.getYsRwcb(zbid);
        JSONObject jo = new JSONObject();
        jo.put("ysrwcb",ysRwcb);

        return jo.toString();
    }

    /**
     * 根据id查询预算专员
     * @param id
     * @return
     */
    @ApiOperation(value = " 根据id查询预算专员")
    @RequestMapping(value = "getYszyById", method = RequestMethod.GET)
    @ResponseBody
    public String getYszyById(String id){
        YsYszy ysYszy = new YsYszy();
        ArrayList<List<XmInstanceEntity>> list1 = new ArrayList<>();
        ysYszy.setUserid(id);
        List<YsYszy> yszy = yswtservice.getYszyById(ysYszy);

        List<YsRwzb> rwzbs = yswtservice.getYszyXmById(id);



        JSONObject jo = new JSONObject();
        jo.put("yszyxx",yszy);
        jo.put("yszyruxp",rwzbs);

        return jo.toString();
    }

    /**
     * 获取预算专员的信息
     * @return
     */
    @ApiOperation(value = " 获取预算专员的信息")
    @RequestMapping(value = "getYsZy", method = RequestMethod.GET)
    @ResponseBody
    public String getYsZy(){
        JSONObject jo = new JSONObject();
        List<YsYszy> yszy = yswtservice.getYSZY();
        int i = 0;
        HashMap map = null;
        ArrayList<Map> list = new ArrayList<>();
        for(YsYszy a:yszy){
            String Q = String.valueOf(yswtservice.getYSZY(a.getUserid()));
            map = new HashMap();
            map.put("id",a.getUserid());
            map.put("name",a.getUsername());
            map.put("sl",Q);
            list.add(map);
            i++;
        }

        jo.put("yszy",list);
        jo.put("ts",i);

        System.out.print("前台传值===>"+jo.toString());

        return jo.toString();

    }
    private static final Logger LOGGER = LoggerFactory.getLogger(YswtController.class);
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
            @RequestParam(value = "ruzuid", required = false ) String  ruzuid,
            @RequestParam(value = "xmid",required = false) String xmid,
            @RequestParam(value = "xmbh",required = false) String xmbh,
            HttpServletRequest request) {
        Map resMap = new HashMap<String,Object>();

        if (Objects.isNull(multipartFiles)) {
            resMap.put("msg","文件为空，请重新上传");
            return resMap;
        }
        String fileDir = StringUtil.getBasePath()+"upload"+File.separator+xmbh+File.separator+"YsRwfj"+File.separator;
        //String fileDir = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")+"upload/YsRwfj/")).replaceAll("file:/", "").replaceAll("%20", " ").trim();
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
            YsRwfj rwfj = new YsRwfj();

            rwfj.setId(GuidUtils.getGuid());//唯一uuid
            rwfj.setYsrwid(ruzuid);//预算主表表的id
            rwfj.setFjlxEj("Late");//二级附件（预算成果）
            rwfj.setFjlj("upload/"+xmbh+"/YsRwfj/"+filename);//附件路径
            rwfj.setFjhz(filenames[filenames.length-1]);//后缀
            rwfj.setScr(user.getUserid());//上传人
            rwfj.setFjmc(filename);//附件名称
            rwfj.setScsj(new java.sql.Date(new java.util.Date().getTime()));//上传时间
            rwfj.setScFlag("0");//不删除

            yswtservice.setYsRwfj(rwfj);

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
    /**
     *
     * 委派页面查询
     *
     *
     * @return
     */

    @ApiOperation(value = " 委派页面查询")
    @RequestMapping(value = "getTable", method = RequestMethod.POST     )
    @ResponseBody
    public String getTable(String  zt,String id) {
        List<XmVfw> xmfw = yswtservice.getXmVfw(id,zt);
        JSONObject jo = new JSONObject();
        jo.put("xmfw",xmfw);



        return jo.toString();
    }

    /**
     * 获取预算项
     * @return
     */
    @ApiOperation(value = " 获取预算项(费用类型)")
    @RequestMapping(value = "getYsx",method =  RequestMethod.GET)
    @ResponseBody
    public String getYsx(){

        List<XtglFylx> ysx = yswtservice.getYsx();

        JSONObject jo = new JSONObject();
        jo.put("ysxx",ysx);

        return jo.toString();
    }
/*================================================================================================================*/
    /**
     * 开始委派启动流程
     *
     * 40001 - 40002
     * @param xmid 项目id
     * @param xmjlbz 项目经理备注
     * @return
     */
    @ApiOperation(value = "开始委派启动流程")
    @RequestMapping(value = "startDelegate", method = RequestMethod.GET)
    @ResponseBody
    public String startDelegate(String xmid ,String  xmjlbz,String taskid,HttpServletRequest request) {
        String flag = "ok";
        String pdefId = "40001";
        UserEntity user= (UserEntity) request.getSession().getAttribute("user");//获取当前登录的用户信息
        String czr = user.getUserid();
        DxDfsEntity dxDfsEntity = new DxDfsEntity();//初始化短信模版

        //调用存储过程获取任务编号
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("v_stop_run", "YSWT");
        service.getRwbh(map);//获取任务编号
        String rwbh = "YSWT" + map.get("p_result");

        //不等于0时，是驳回的数据
        if (!taskid.equals("0")){
            //wf_task_history
            yswtservice.updatewfTaskHistoryEntityByTaskid(taskid);

            //获取流程实例id
            WfRuInstEntity wRi=yswtservice.getWfRuInstProcinstidByContentvalue(xmid,pdefId);
            String procInstId=wRi.getProcInstId();

            //1:通过xmid查找WfRuInst（xmid,tablename） 结果不唯一
            WfRuInstEntity wfRuInstEntity = yswtservice.getWfRuInstByXmidAndPdefid(pdefId,wRi.getPdefId(),xmid);

            //2.通过InstId 40001 查找Task  结果只有一个
            WfTaskEntity wfTaskEntity = null;
            wfTaskEntity = yswtservice.getTaskByInstId(wfRuInstEntity.getProcInstId());
            wfTaskEntity.setJssj(new Date());

            //3.把这个放到历史任务里  新建一个当前任务
            WfTaskHistoryEntity wfTaskHistoryEntity = new WfTaskHistoryEntity(wfTaskEntity);
            wfTaskHistoryEntity.setIscurrent("0");
            service.saveWfTaskHistory(wfTaskHistoryEntity);//保存历史任务
            service.deleteTaskById(wfTaskEntity.getTaskid());//删除当前任务

            YsRwzb rwzb = new YsRwzb();
            rwzb.setId(GuidUtils.getGuid());
            rwzb.setXmid(xmid);
            rwzb.setYszt("0");//预算表添加数据    0 待预算 1预算 2预算变更中 3完成 4作废
            rwzb.setYsrybz(xmjlbz);
            rwzb.setRwbh(rwbh);//任务编号
            yswtservice.setOny(rwzb);

            //下一节点 创建任务 40002
            WfTaskEntity task=new WfTaskEntity();
            String direction = "0";//流程方向 0前进/1回退
            List<WfReNodeEntity> list = service.findByActivityidAndDirection(pdefId, direction);
            for (WfReNodeEntity wf : list) {
                String taskId1 = GuidUtils.getGuid();
                //-----------------短信taskId----------------------------
                dxDfsEntity.setTaskid(taskId1);
                //---------------------------------------------
                task.setTaskid(taskId1);
                task.setRefkey("YS_RWZB");
                task.setRefid(rwzb.getId());
                task.setQssj(new Date());//当前任务开时间
                WfDfActivityEntity wfDfActivityEntity = service.findByActivityid(wf.getNextnode());
                Date qssj = new Date();
                SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                task.setCheck_sx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
                task.setJssx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                //task.setJssx_yl(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getFinishMinute())));
                task.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute())+Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                task.setPtaskid("0");//前置任务为空
                task.setProcInstId(procInstId);//流程实例ID
                task.setCActivityid(wf.getNextnode());//环节编号
                task.setIscurrent("1");//已完成
                task.setCzr(czr);//操作人
                task.setIscheck("0");//已签收
                task.setHasnew("2");//无子流程
                task.setRwblr(service.getUserIdByActivityId("40002"));
                service.saveWfTask(task);

                //更新当前的实例的节点标识
                yswtservice.updateWfRuInstPdefId(wf.getNextnode(),procInstId);
            }

        }else {
            //启动流程实例
            WfRuInstEntity instEntity = new WfRuInstEntity();
            String procInstId = GuidUtils.getGuid();
            instEntity.setProcInstId(procInstId);
            instEntity.setPdefId("40002");//启动40001 -> 40002
            instEntity.setParammap(rwbh);
            instEntity.setStartdt(new Date());
            instEntity.setStartuser(czr);
            instEntity.setProcstatus(1);//实例状态 1正常0终止
            service.saveWfRuInst(instEntity);

            //将项目信息存入流程实例使用内容表(WF_RU_INST_CONTENT)
            WfRuInstContentEntity wfRuInstContentEntity = new WfRuInstContentEntity();
            wfRuInstContentEntity.setContentid(GuidUtils.getGuid());
            wfRuInstContentEntity.setProcInstId(procInstId);
            wfRuInstContentEntity.setTableName("XM_INSTANCE");
            wfRuInstContentEntity.setContentname("id");
            wfRuInstContentEntity.setContenttype("yswt");
            wfRuInstContentEntity.setContentvalue(xmid);
            service.saveWfRuInstContent(wfRuInstContentEntity);

            //创建任务，存入历史任务表中 40001
            WfTaskEntity task = new WfTaskEntity();
            String taskId = GuidUtils.getGuid();
            task.setTaskid(taskId);
            task.setPtaskid("0");//前置任务为空
            task.setQssj(new Date());
            task.setJssj(new Date());
            task.setProcInstId(procInstId);//流程实例ID
            task.setCActivityid(pdefId);//环节编号
            task.setIscurrent("0");//已完成
            task.setCzr(czr);//操作人
            task.setIscheck("1");//已签收
            task.setHasnew("2");//无子流程
            task.setRwblr(user.getUserid());//任务办理人
            task.setCheck_time(new Date());//签收时间
            WfTaskHistoryEntity historyEntity = new WfTaskHistoryEntity(task);
            service.saveWfTaskHistory(historyEntity);

            YsRwzb rwzb = new YsRwzb();
            rwzb.setId(GuidUtils.getGuid());
            rwzb.setXmid(xmid);
            rwzb.setYszt("0");//预算表添加数据    0 带预算 1预算 2预算变更中 3完成 4作废
            rwzb.setYsrybz(xmjlbz);
            rwzb.setRwbh(rwbh);//任务编号
            yswtservice.setOny(rwzb);
            /*---------------------添加完任务主表后，修改任务附件的任务主表id-----------------*/
            List<YsZlxx> zlxx = yswtservice.getYszl(xmid);
            for (YsZlxx ys:zlxx){
                yswtservice.updateYszl(ys.getRwfjid(),rwzb.getId());

            }


            /*-----------------------------------------------------------------------------*/
            //下一节点 创建任务 40002
            String direction = "0";//流程方向 0前进/1回退
            List<WfReNodeEntity> list = service.findByActivityidAndDirection(pdefId, direction);
            for (WfReNodeEntity wf : list) {
                String taskId1 = GuidUtils.getGuid();
                //-----------------短信发送任务------------------------
                dxDfsEntity.setTaskid(taskId1);
                //-----------------------------------------
               // WfDfActivityEntity ac = service.findByActivityid(wf.getActivityid());
                task.setTaskid(taskId1);
                task.setRefkey("YS_RWZB");
                task.setRefid(rwzb.getId());
                task.setQssj(new Date());
                WfDfActivityEntity wfDfActivityEntity = service.findByActivityid(wf.getNextnode());
                Date qssj = new Date();
                SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                task.setCheck_sx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
                task.setJssx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                task.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute())+Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                task.setCheck_time(qssj);//签收时间
                task.setPtaskid("0");//前置任务为空
                task.setProcInstId(procInstId);//流程实例ID
                task.setCActivityid(wf.getNextnode());//环节编号
                task.setIscurrent("1");//已完成
                task.setCzr(czr);//操作人
                task.setIscheck("0");//已签收
                task.setHasnew("2");//子流程是否有提交，1有，0无
                task.setRwblr(service.getUserIdByActivityId("40002"));
                service.saveWfTask(task);
            }

        }
        //委托预算时，发送短信通知办理人
        String content = "";
        Map<String,Object> userMap=null;


        dxDfsEntity.setId(GuidUtils.getGuid());//标识
        dxDfsEntity.setDxmbid("4");//短信模版标识
        dxDfsEntity.setFsr(user.getUserid());//发送人
        dxDfsEntity.setFssj(new Date());//发送时间
        dxDfsEntity.setFscs("1");//发送次数
        Map<String,Object> dxmb = messageService.getDxMb("4");//模版4：任务提醒，替换短信模版内容
        XmInstanceEntity xmInstanceEntity = yswtservice.getXmfzr(xmid);//获取项目名称
        content = dxmb.get("DXMB").toString();//获取短信模版
        content = content.replace("[环节名称类型]","预算人员指派");
        content = content.replace("[任务单编号]",rwbh);
        content = content.replace("[项目名称]",xmInstanceEntity.getXmmc());
        dxDfsEntity.setFsnr(content);//发送内容
        UserEntity userEntity = yswtservice.getUserIdAndUserMobilephone("40002");
        dxDfsEntity.setJsr(userEntity.getUserid());//接收人
        dxDfsEntity.setJshm(userEntity.getMobilephone());//接收号码
        yswtservice.saveDxDfsEntity(dxDfsEntity);//保存


/*

        try {
            SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            userMap = messageService.getUserxxById(ryid);
            phone = userMap.get("MOBILEPHONE").toString();

            Map<String,Object> dxmb = messageService.getDxMb("9");
            content = dxmb.get("DXMB").toString();
            content = content.replace("[任务编号]",xckcRwzbEntity.getRwbh());
            content = content.replace("[勘查时间]",sdft.format(xckcRwzbEntity.getYykcrq()));
            GetXckcxxView getXckcxxView = service.getXckcxxByView(xmid);
            Map<String,Object> xmjl= messageService.getUserxxById(getXckcxxView.getXmjl());
            content = content.replace("[项目经理]",xmjl.get("USERNAME").toString());
            content = content.replace("[联系方式]",xmjl.get("MOBILEPHONE").toString());
            controller.sendMsg("002","1",null,phone,content,
                    ((UserEntity)session.getAttribute("user")).getUserid(),"9",user.get("USERID").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
*/


        return flag;
        }

    /**
     * 驳回
     * 40002-40001
     * @param xmid
     * @param htyy
     * @return
     */
    @ApiOperation(value = "委托预算退回")
    @RequestMapping(value = "ToBackward", method = RequestMethod.POST)
    @ResponseBody
    public String ToBackward(String xmid,String htyy,String activityid,HttpServletRequest request) {
        String flag = "ok";
        //退回
        String direction="1";//0前进 1后退
        String pdefid="40002";
       UserEntity user= (UserEntity) request.getSession().getAttribute("user");
        DxDfsEntity dxDfsEntity = new DxDfsEntity();//初始化短信模版

        //删除预算主表
        YsRwzb rwzb = new YsRwzb();
        rwzb.setXmid(xmid);
        try {
            //删除预算主表任务
            yswtservice.DeleById(rwzb);
            //1:通过xmid查找WfRuInst（xmid,tablename） 结果不唯一
            WfRuInstEntity wfRuInstEntity = yswtservice.getWfRuInstByXmidAndPdefid(pdefid,activityid,xmid);



            //2.通过InstId 查找Task  结果只有一个,是40002的在办任务
            WfTaskEntity wfTaskEntity = null;
            wfTaskEntity = yswtservice.getTaskByInstId(wfRuInstEntity.getProcInstId());
            //把前台用户传过来的回退原因加到流程任务表_办结 （WF_TASK_HISTORY）
            wfTaskEntity.setHtyy(htyy);
            wfTaskEntity.setJssj(new Date());
            //3.把这个放到历史任务里
            WfTaskHistoryEntity wfTaskHistoryEntity = new WfTaskHistoryEntity(wfTaskEntity);
            service.saveWfTaskHistory(wfTaskHistoryEntity);//保存历史任务
            service.deleteTaskById(wfTaskEntity.getTaskid());//删除当前任务

            //驳回上一节点 创建任务 40001
            WfRuInstEntity wRi=yswtservice.getWfRuInstProcinstidByContentvalue(xmid,"40002");
            String procInstId=wRi.getProcInstId();
            WfTaskEntity task = new WfTaskEntity();
            List<WfReNodeEntity> list = service.findByActivityidAndDirection(pdefid, direction);
            for (WfReNodeEntity wf : list) {
                String taskId1 = GuidUtils.getGuid();
                task.setTaskid(taskId1);
                //----------------短信taskid--------------------
                dxDfsEntity.setTaskid(taskId1);
                //------------------------------------
                /*task.setRefkey("YS_RWZB");
                task.setRefid(GuidUtils.getGuid());//生成随机主任务id*/
                task.setQssj(new Date());//当前任务开时间
                WfDfActivityEntity wfDfActivityEntity = service.findByActivityid(wf.getNextnode());
                Date qssj = new Date();
                SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                task.setCheck_sx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
                task.setJssx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                //task.setJssx_yl(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getFinishMinute())));
                task.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute())+Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                task.setPtaskid("0");//前置任务为空
                task.setProcInstId(procInstId);//流程实例ID
                task.setCActivityid(wf.getNextnode());//环节编号
                task.setIscurrent("1");//状态标志：0已做1待做2回退
                task.setHtyy(htyy);
                task.setCzr(user.getUserid());//操作人
                task.setIscheck("0");//已签收
                task.setHasnew("2");//无子流程
                XmInstanceEntity xmInstanceEntity = yswtservice.getXmfzr(xmid);//获取项目负责人
                task.setRwblr(xmInstanceEntity.getXmfzr());//驳回到预算委托，任务办理人是该项目的项目负责人
                service.saveWfTask(task);

                this.setProcinstidNew(procInstId);
                //更新当前的实例的节点标识
                yswtservice.updateWfRuInstPdefId(wf.getNextnode(),this.getProcinstidNew());


                //预算指派驳回时，发送短信通知办理人
                String content = "";
                Map<String,Object> userMap=null;


                dxDfsEntity.setId(GuidUtils.getGuid());//标识
                dxDfsEntity.setDxmbid("4");//短信模版标识
                dxDfsEntity.setFsr(user.getUserid());//发送人
                dxDfsEntity.setFssj(new Date());//发送时间
                dxDfsEntity.setFscs("1");//发送次数
                Map<String,Object> dxmb = messageService.getDxMb("4");//模版4：任务提醒，替换短信模版内容
                content = dxmb.get("DXMB").toString();//获取短信模版
                content = content.replace("[环节名称类型]","委托预算");
                content = content.replace("[任务单编号]","");
                content = content.replace("[项目名称]",xmInstanceEntity.getXmmc());
                dxDfsEntity.setFsnr(content);//发送内容
                dxDfsEntity.setJsr(xmInstanceEntity.getXmfzr());//接收人
                UserEntity userEntity = yswtservice.getUserByXmfzr(xmInstanceEntity.getXmfzr());
                dxDfsEntity.setJshm(userEntity.getMobilephone());//接收号码
                yswtservice.saveDxDfsEntity(dxDfsEntity);//保存


            }








        } catch (Exception e) {
             flag ="NO";
        }
            return flag;
    }

    /**
     * 发起指派
     *
     * @param procinstid 当前实例id
     * @param ysr  预算人
     * @param zprbz 指派人备注
     * @return
     */
    @ApiOperation(value = "发起指派")
    @RequestMapping(value = "ToYsZpzr", method = RequestMethod.GET)
    @ResponseBody
    public String ToYsZpzr(String xmid ,String procinstid ,String ysr,String  zprbz,String activityid,HttpServletRequest request){
        this.setYsrid(ysr);
        UserEntity user= (UserEntity) request.getSession().getAttribute("user");//获取用户信息
       String zpr=user.getUserid();
        String pdefid="40003";
        DxDfsEntity dxDfsEntity = new DxDfsEntity();//初始化短信模版

        String direction="0";//0前进 1后退
        YsRwzb rwzb = new YsRwzb();
        rwzb.setXmid(xmid);
        rwzb.setYsr(ysr);
        rwzb.setZpr(zpr);
        rwzb.setZprbz(zprbz);
        rwzb.setYskssj(new Date());
        rwzb.setYszt("1");//预算状态0待预算1预算中2预算变更中3完成4作废
        yswtservice.updataYszb(rwzb);//更新预算主表、项目实例表中的数据，

        YsRwzb zb=yswtservice.getYsRwzbByXmid(xmid);

        //更新当前的实例的节点标识
        yswtservice.updateWfRuInstPdefId(pdefid,procinstid);

        //1:通过xmid查找WfRuInst（xmid,tablename） 结果不唯一
        WfRuInstEntity wfRuInstEntity = yswtservice.getWfRuInstByXmidAndPdefid(pdefid,activityid,xmid);

        //2.通过InstId 40001 查找Task  结果只有一个
        WfTaskEntity wfTaskEntity = null;
        wfTaskEntity = yswtservice.getTaskByInstId(wfRuInstEntity.getProcInstId());
        wfTaskEntity.setJssj(new Date());

        //3.把这个放到历史任务里  新建一个当前任务
        WfTaskHistoryEntity wfTaskHistoryEntity = new WfTaskHistoryEntity(wfTaskEntity);
        wfTaskHistoryEntity.setIscurrent("0");
        service.saveWfTaskHistory(wfTaskHistoryEntity);//保存历史任务
        service.deleteTaskById(wfTaskEntity.getTaskid());//删除当前任务
        List<WfReNodeEntity> list = service.findByActivityidAndDirection(wfTaskEntity.getCActivityid(),direction);
            for(WfReNodeEntity wf : list){
                WfTaskEntity task = new WfTaskEntity();
                String taskId = GuidUtils.getGuid();
                //WfDfActivityEntity ac = service.findByActivityid(wf.getActivityid());
                //-----------------短信taskid---------------------------
                dxDfsEntity.setTaskid(taskId);
                //--------------------------------------------
                task.setTaskid(taskId);
                task.setRefkey("YS_RWZB");
                task.setRefid(zb.getId());
                task.setQssj(new Date());
                WfDfActivityEntity wfDfActivityEntity = service.findByActivityid(wf.getNextnode());
                Date qssj = new Date();
                SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                task.setCheck_sx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
                task.setJssx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                //task.setJssx_yl(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getFinishMinute())));
                task.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute())+Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                task.setPtaskid(wfTaskEntity.getTaskid());//前置任务
                task.setProcInstId(wfRuInstEntity.getProcInstId());//流程实例ID
                task.setCActivityid(wf.getNextnode());//环节编号
                task.setIscurrent("1");//待做
                task.setCzr(zpr);//指派人
                task.setIscheck("0");//未签收
                task.setHasnew("2");//无子流程
                task.setRwblr(ysr);
                service.saveWfTask(task);



                //预算指派驳回时，发送短信通知办理人
                String content = "";
                Map<String,Object> userMap=null;


                dxDfsEntity.setId(GuidUtils.getGuid());//标识
                dxDfsEntity.setDxmbid("4");//短信模版标识
                dxDfsEntity.setFsr(user.getUserid());//发送人
                dxDfsEntity.setFssj(new Date());//发送时间
                dxDfsEntity.setFscs("1");//发送次数
                Map<String,Object> dxmb = messageService.getDxMb("4");//模版4：任务提醒，替换短信模版内容
                XmInstanceEntity xmInstanceEntity = yswtservice.getXmfzr(xmid);//获取项目名称
                content = dxmb.get("DXMB").toString();//获取短信模版
                content = content.replace("[环节名称类型]","预算编制");
                content = content.replace("[任务单编号]",zb.getRwbh());
                content = content.replace("[项目名称]",xmInstanceEntity.getXmmc());
                dxDfsEntity.setFsnr(content);//发送内容
                dxDfsEntity.setJsr(ysr);//接收人
                UserEntity userEntity = yswtservice.getUserByXmfzr(ysr);
                dxDfsEntity.setJshm(userEntity.getMobilephone());//接收号码
                yswtservice.saveDxDfsEntity(dxDfsEntity);//保存


            }


        return "ok";
    }

    @ApiOperation(value = "预算审核通过")
    @RequestMapping(value = "ToYsZpzrOK", method = RequestMethod.POST)
    @ResponseBody
    public String ToYsZpzrOK(String json,String xmid,HttpServletRequest request){
        String flag="ok";
        String activityid="";
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity)session.getAttribute("user");//获取当前登录的用户信息
       // String pdefid="40009";
        String pdefid="40081";
        DxDfsEntity dxDfsEntity = new DxDfsEntity();//初始化短信模版

        JSONObject jo = new JSONObject(JSON.parseObject(json));
        Map<String, Object> map = jo;
        for(Map.Entry<String, Object> entry : map.entrySet()){
            entry.getValue().toString();
        }
        map.put("shr",user.getUserid());
        yswtservice.updataYsShtg(map,1);

        //更新当前的实例的节点标识
        yswtservice.updateWfRuInstPdefId(pdefid,map.get("procinstid").toString());

        try{
        //1:通过xmid查找WfRuInst（xmid,tablename） 结果不唯一
        WfRuInstEntity wfRuInstEntity = yswtservice.getWfRuInstByXmidAndPdefid(pdefid,activityid,map.get("xmid").toString());

        //2.通过InstId 40001 查找Task  结果只有一个
        WfTaskEntity wfTaskEntity = null;
        wfTaskEntity = yswtservice.getTaskByInstId(wfRuInstEntity.getProcInstId());

        YsRwzb zb=yswtservice.getYsRwzbByXmid(map.get("xmid").toString());

        //3.把这个放到历史任务里  新建一个当前任务
        WfTaskHistoryEntity wfTaskHistoryEntity = new WfTaskHistoryEntity(wfTaskEntity);
        wfTaskHistoryEntity.setIscurrent("0");
        wfTaskHistoryEntity.setJssj(new Date());
        service.saveWfTaskHistory(wfTaskHistoryEntity);//保存历史任务
        service.deleteTaskById(wfTaskEntity.getTaskid());//删除当前任务

            String direction="0";//0前进 1后退
        List<WfReNodeEntity> list = service.findByActivityidAndDirection(wfTaskEntity.getCActivityid(),direction);
        for(WfReNodeEntity wf : list){
            WfTaskEntity task = new WfTaskEntity();
            String taskId = GuidUtils.getGuid();
            //----------------短信taskid----------------------------
            dxDfsEntity.setTaskid(taskId);
            //--------------------------------------------
            //WfDfActivityEntity ac = service.findByActivityid(wf.getActivityid());
            task.setTaskid(taskId);
            task.setRefkey("YS_RWZB");
            task.setRefid(zb.getId());
            task.setQssj(new Date());
            WfDfActivityEntity wfDfActivityEntity = service.findByActivityid(wf.getNextnode());
            Date qssj = new Date();
            SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            task.setCheck_sx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
            task.setJssx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
           // task.setJssx_yl(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getFinishMinute())));
            task.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute())+Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
            task.setPtaskid(wfTaskEntity.getTaskid());//前置任务
            task.setProcInstId(wfRuInstEntity.getProcInstId());//流程实例ID
            task.setCActivityid(wf.getNextnode());//环节编号
            task.setIscurrent("1");//待做
            task.setCzr(user.getUserid());//操作人
            task.setIscheck("0");//未签收
            task.setHasnew("2");//无子流程
            XmInstanceEntity xmInstanceEntity = yswtservice.getXmfzr(xmid);//获取当前项目信息
            task.setRwblr(xmInstanceEntity.getXmfzr());//和发起委托一样由该项目的项目负责人签收
            service.saveWfTask(task);

            //预算审核通过时，发送短信通知办理人
            String content = "";
            Map<String,Object> userMap=null;


            dxDfsEntity.setId(GuidUtils.getGuid());//标识
            dxDfsEntity.setDxmbid("4");//短信模版标识
            dxDfsEntity.setFsr(user.getUserid());//发送人
            dxDfsEntity.setFssj(new Date());//发送时间
            dxDfsEntity.setFscs("1");//发送次数
            Map<String,Object> dxmb = messageService.getDxMb("4");//模版4：任务提醒，替换短信模版内容
            content = dxmb.get("DXMB").toString();//获取短信模版
            content = content.replace("[环节名称类型]","预算签收");
            content = content.replace("[任务单编号]",zb.getRwbh());
            content = content.replace("[项目名称]",xmInstanceEntity.getXmmc());
            dxDfsEntity.setFsnr(content);//发送内容
            UserEntity userEntity = yswtservice.getUserByXmfzr(xmInstanceEntity.getXmfzr());
            dxDfsEntity.setJsr(userEntity.getUserid());//接收人
            dxDfsEntity.setJshm(userEntity.getMobilephone());//接收号码
            yswtservice.saveDxDfsEntity(dxDfsEntity);//保存
        }
    } catch (Exception e) {
        e.printStackTrace();
        flag="error";
    }


        return flag;
    }

    @ApiOperation(value = "预算审核不通过")
    @RequestMapping(value = "ToYsZpzrNO", method = RequestMethod.POST)
    @ResponseBody
    public String ToYsZpzrNO(String json,HttpServletRequest request,String activityid){
        String flag = "ok";
        //退回
        String direction="1";//0前进 1后退
        String pdefid="40003";
        DxDfsEntity dxDfsEntity = new DxDfsEntity();//初始化短信模版
        //删除预算主表
        /*YsRwzb rwzb = new YsRwzb();
        rwzb.setXmid(xmid);*/

        try {
            HttpSession session = request.getSession();
            UserEntity user = (UserEntity)session.getAttribute("user");//获取当前登录的用户信息
            JSONObject jo = new JSONObject(JSON.parseObject(json));
            Map<String, Object> map = jo;
            for(Map.Entry<String, Object> entry : map.entrySet()){
                entry.getValue().toString();
            }
            map.put("shr",user.getUserid());
            //审核不通过，更新预算状态  预算状态0待预算1预算中2预算变更中3完成4作废
            yswtservice.updataYsShtg(map,2);

            //更新当前的实例的节点标识
            yswtservice.updateWfRuInstPdefId(pdefid,map.get("procinstid").toString());

            //1:通过xmid查找WfRuInst（xmid,tablename） 结果不唯一
            WfRuInstEntity wfRuInstEntity = yswtservice.getWfRuInstByXmidAndPdefid(pdefid,activityid,map.get("xmid").toString());

            //2.通过InstId 查找Task  结果只有一个
            WfTaskEntity wfTaskEntity = null;
            wfTaskEntity = yswtservice.getTaskByInstId(wfRuInstEntity.getProcInstId());

            YsRwzb zb=yswtservice.getYsRwzbByXmid(map.get("xmid").toString());

            //3.把这个放到历史任务里  新建一个当前任务
            WfTaskHistoryEntity wfTaskHistoryEntity = new WfTaskHistoryEntity(wfTaskEntity);
            wfTaskHistoryEntity.setHtyy(map.get("shthyy").toString());//回退原因
            wfTaskHistoryEntity.setIscurrent("0");
            wfTaskHistoryEntity.setJssj(new Date());//结束时间
            service.saveWfTaskHistory(wfTaskHistoryEntity);//保存历史任务
            service.deleteTaskById(wfTaskEntity.getTaskid());//删除当前任务
            List<WfReNodeEntity> list = service.findByActivityidAndDirection(wfTaskEntity.getCActivityid(),direction);
            for(WfReNodeEntity wf : list){
                WfTaskEntity task = new WfTaskEntity();
                String taskId = GuidUtils.getGuid();
                //--------------短信taskid-----------------------------
                dxDfsEntity.setTaskid(taskId);
                //-------------------------------------------
                //WfDfActivityEntity ac = service.findByActivityid(wf.getActivityid());
                task.setTaskid(taskId);
                task.setRefkey("YS_RWZB");
                task.setRefid(zb.getId());
                task.setHtyy(map.get("shthyy").toString());//回退原因
                task.setQssj(new Date());
                WfDfActivityEntity wfDfActivityEntity = service.findByActivityid(wf.getNextnode());
                Date qssj = new Date();
                SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                task.setCheck_sx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
                task.setJssx(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                //task.setJssx_yl(cal.getEndTime(sdft.format(qssj),Float.parseFloat(wfDfActivityEntity.getFinishMinute())));
                task.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute())+Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
                task.setPtaskid(wfTaskEntity.getTaskid());//前置任务
                task.setProcInstId(wfRuInstEntity.getProcInstId());//流程实例ID
                task.setCActivityid(wf.getNextnode());//环节编号
                task.setIscurrent("1");//待做
                task.setCzr(user.getUserid());//指派人
                task.setIscheck("0");//未签收
                task.setHasnew("2");//无子流程
                XmInstanceEntity xmInstanceEntity = yswtservice.getXmfzr(map.get("xmid").toString());
                task.setRwblr(xmInstanceEntity.getYsfzr());//领导审核不通过时，任务办理人为指派人指派的人员
                service.saveWfTask(task);


                //预算审核不通过时，发送短信通知办理人
                String content = "";
                Map<String,Object> userMap=null;


                dxDfsEntity.setId(GuidUtils.getGuid());//标识
                dxDfsEntity.setDxmbid("4");//短信模版标识
                dxDfsEntity.setFsr(user.getUserid());//发送人
                dxDfsEntity.setFssj(new Date());//发送时间
                dxDfsEntity.setFscs("1");//发送次数
                Map<String,Object> dxmb = messageService.getDxMb("4");//模版4：任务提醒，替换短信模版内容
                content = dxmb.get("DXMB").toString();//获取短信模版
                content = content.replace("[环节名称类型]","预算编制");
                content = content.replace("[任务单编号]",zb.getRwbh());
                content = content.replace("[项目名称]",xmInstanceEntity.getXmmc());
                dxDfsEntity.setFsnr(content);//发送内容
                UserEntity userEntity = yswtservice.getUserByXmfzr(xmInstanceEntity.getYsfzr());
                dxDfsEntity.setJsr(userEntity.getUserid());//接收人
                dxDfsEntity.setJshm(userEntity.getMobilephone());//接收号码
                yswtservice.saveDxDfsEntity(dxDfsEntity);//保存
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag="error";
        }


        return flag;
    }

    /**
     *  签收（关闭流程）
     * @param xmid 项目id
     * @return
     */
    @ApiOperation(value = "签收关闭流程")
    @RequestMapping(value = "Qs", method = RequestMethod.GET)
    @ResponseBody
    public String Qs(String xmid,HttpServletRequest request) {
        String flag = "ok";
        String activityid="";
        String pdefid="40081";
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");//获取当前登录人

        try {
            //1:通过xmid查找WfRuInst（xmid,tablename)
            WfRuInstEntity wfRuInstEntity = yswtservice.getWfRuInstByXmidAndPdefid(pdefid,activityid,xmid);


            //2.通过InstId 查找Task  结果只有一个
            WfTaskEntity wfTaskEntity = null;
            wfTaskEntity = yswtservice.getTaskByInstId(wfRuInstEntity.getProcInstId());

            //3.把这个放到历史任务里
            WfTaskHistoryEntity wfTaskHistoryEntity = new WfTaskHistoryEntity(wfTaskEntity);
            wfTaskHistoryEntity.setIscurrent("0");//已做
            wfTaskHistoryEntity.setJssj(new Date());
            wfTaskHistoryEntity.setIscheck("1");//已经签收：0未签收1已签收
            wfTaskHistoryEntity.setRwblr(userEntity.getUserid());//任务办理人
            service.saveWfTaskHistory(wfTaskHistoryEntity);//保存历史任务
            service.deleteTaskById(wfTaskEntity.getTaskid());//删除当前任务

            //通过xmid查询当前开启的实例
           // wf_ru_inst_content
            WfRuInstContentEntity wfRuInstContentEntity1=yswtservice.getWfRuInstAndWfRuInstContentByXmid(xmid);
            wfRuInstEntity=yswtservice.getwfRuInstByid(wfRuInstContentEntity1.getProcInstId());
            //把这个实例放到历史任务里(wf_hi_inst)
            String userid="";
            UserEntity user = (UserEntity) request.getSession().getAttribute("user");//获取当前登录的用户信息
            if (user.getUserid()==null || user.getUserid().equals("")){
               userid="项目经理";
            }else{
                userid =user.getUserid();
            }
            wfRuInstEntity.setStopuser(userid);
            wfRuInstEntity.setStopdt(new Date());
            WfHiInstEntity wfHiInstEntity=new WfHiInstEntity(wfRuInstEntity);
            wfHiInstEntity.setProcstatus("1");//实例状态 1正常0终止
            service.saveWfHiRuInst(wfHiInstEntity);
            service.deleteInstById(wfRuInstEntity.getProcInstId());

            //将项目信息存入流程历史实例使用内容表(wf_hi_inst_content)
            WfRuInstContentEntity wfRuInstContentEntity=yswtservice.getWfRuInstContentEntityById(wfRuInstContentEntity1.getContentid());
            WfHiInstContentEntity wfHiInstContentEntity=new WfHiInstContentEntity(wfRuInstContentEntity);
            service.saveWfHiInstContent(wfHiInstContentEntity);
            service.deleteWfRuInstContentById(wfRuInstContentEntity.getContentid());

//            yswtservice.deleteRwzbByXmid(xmid);

        } catch (Exception e) {
            e.printStackTrace();

        }


        return flag;
    }

    /**
     * 获取预算说明（预算人员备注）
     * 预算编制
     * @return
     */
    @ApiOperation(value = " 获取预算说明（预算人员备注）")
    @RequestMapping(value = "getYssm",method =  RequestMethod.POST)
    @ResponseBody
    public String getYssm(String zbid,HttpServletRequest request){

        List<YsRwzb> ysRwzb = yswtservice.getYssm(zbid);

        JSONObject jo = new JSONObject();
        jo.put("ysRwzb",ysRwzb);

        return jo.toString();
    }

    /**
     * 查询安装类型下拉框
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "查询安装类型")
    @RequestMapping(value = "findAzlx",method = RequestMethod.GET)
    @ResponseBody
    public String findAzlx(HttpServletRequest request, HttpServletResponse response){
        JSONObject jo = new JSONObject();
        List<String> varList = new ArrayList<>();
        varList.add("居民科");
        varList.add("工商科");
        varList.add("户改科");
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i=0;i<varList.size();i++){
            Map<String,Object> pd = new HashMap<>();
            pd.put("value",varList.get(i));
            pd.put("label",varList.get(i));
            List<azlxEntity> aList = yswtservice.findAzlxBySllx(pd);
            pd.put("children",aList);
            list.add(pd);
        }
        jo.put("list",list);
        return jo.toString();
    }

    /**
     * 更新签收状态
     * @param xmid
     * @param activityId
     * @return
     */
    @ApiOperation(value = "更新签收状态")
    @RequestMapping(value = "updateQszt",method = RequestMethod.POST)
    @ResponseBody
    public String updateQszt (String xmid,String activityId){
        String flag = "ok";

        try {
            yswtservice.updateQszt(xmid,activityId);
        } catch (Exception e) {
            e.printStackTrace();
            flag = "error";
        }

        return flag;
    }

    /**
     * 上传用户签字盖章的电子文件
     * 供预算科预算参考
     * @param multipartFiles
     * @param filename
     * @param xmid
     * @param request
     * @return
     */
    @ApiOperation(value = "上传用户签字盖章的电子文件，供预算科预算参考")
    @RequestMapping(value ="importFileQzgz",method = RequestMethod.POST)
    @ResponseBody
    public Map importFileQzgz(
            @RequestParam(value = "multipartFiles", required = false ) MultipartFile multipartFiles,
            @RequestParam(value = "filename", required = false ) String  filename,
            @RequestParam(value = "xmid", required = false) String xmid,
            @RequestParam(value = "xmbh", required = false) String xmbh,
            HttpServletRequest request) {
        Map resMap = new HashMap<String,Object>();
        //判断是否是第一次上传，如果是第二次上传就不需要自动生成guid
       /* System.out.println("---------------------------:"+ysrwzbid);
        if ("123".equals(ysrwzbid)){
            ysrwzbid = GuidUtils.getGuid();
        }*/
        if (Objects.isNull(multipartFiles)) {
            resMap.put("msg","文件为空，请重新上传");
            return resMap;
        }
        String fileDir = StringUtil.getBasePath()+"upload"+File.separator+xmbh+File.separator+"YsRwfj"+File.separator;
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
            YsRwfj rwfj = new YsRwfj();
            rwfj.setId(GuidUtils.getGuid());//唯一uuid
            rwfj.setYsrwid("123");
            rwfj.setFjlxYj("Early_stage");
            rwfj.setFjlj("upload/"+xmbh+"/YsRwfj/"+filename);//附件路径
            rwfj.setFjhz(filenames[filenames.length-1]);//后缀
            rwfj.setScr(user.getUserid());//上传人
            rwfj.setFjmc(filename);//附件名称
            rwfj.setScsj(new java.sql.Date(new java.util.Date().getTime()));//上传时间
            rwfj.setScFlag("0");//不删除
            rwfj.setXmid(xmid);//项目id

            yswtservice.setYsRwfj(rwfj);

        }catch (IOException e) {
            e.printStackTrace();
            resMap.put("returncode", "500");
            resMap.put("msg", "程序异常，请联系管理员");
        }

        resMap.put("returncode", "200");
        resMap.put("msg", "上传成功");
      /*  resMap.put("ysrwzbid",ysrwzbid);*/
        return resMap;

    }

    /**
     * 预算委托
     * 预算资料上传
     * @param xmid
     * @return
     */
    @RequestMapping(value = "getYszl" ,method = RequestMethod.POST)
    @ResponseBody
    public String getYszl(String xmid){
        JSONObject jo = new JSONObject();
        List<YsZlxx> zlxx = yswtservice.getYszl(xmid);

        jo.put("YsZlxx",zlxx);


        return jo.toString();
    }

    /**
     * 预算结果用户确认附件
     * 用户确认凭证上传
     * @param multipartFiles
     * @param filename
     * @param ysrwzbid
     * @param request
     * @return
     */
    @ApiOperation(value = "用户确认凭证上传")
    @RequestMapping(value ="importYhpz",method = RequestMethod.POST)
    @ResponseBody
    public Map importYhpz(
            @RequestParam(value = "multipartFiles", required = false ) MultipartFile multipartFiles,
            @RequestParam(value = "filename", required = false ) String  filename,
            @RequestParam(value = "ysrwzbid", required = false ) String  ysrwzbid,
            @RequestParam(value = "xmid", required = false ) String  xmid,
            @RequestParam(value = "xmbh", required = false ) String  xmbh,
            HttpServletRequest request) {

        Map resMap = new HashMap<String,Object>();

        if (Objects.isNull(multipartFiles)) {
            resMap.put("msg","文件为空，请重新上传");
            return resMap;
        }
        String fileDir = StringUtil.getBasePath()+"upload"+File.separator+xmbh+File.separator+"YsRwYhqrfj"+File.separator;
        //String fileDir = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")+"upload/YsRwfj/")).replaceAll("file:/", "").replaceAll("%20", " ").trim();
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
            //预算结果用户确认附件
            YsRwyhqrfjEntity ysRwyhqrfjEntity = new YsRwyhqrfjEntity();

            ysRwyhqrfjEntity.setId(GuidUtils.getGuid());//唯一uuid
            ysRwyhqrfjEntity.setYsrwzbid(ysrwzbid);//预算主表表的id
            ysRwyhqrfjEntity.setFjlxYj("wtys");
            ysRwyhqrfjEntity.setFjlj("upload/"+xmbh+"/YsRwYhqrfj/"+filename);//附件路径
            ysRwyhqrfjEntity.setFjhz(filenames[filenames.length-1]);//后缀
            ysRwyhqrfjEntity.setScr(user.getUserid());//上传人
            ysRwyhqrfjEntity.setFjmc(filename);//附件名称
            ysRwyhqrfjEntity.setScsj(new java.sql.Date(new java.util.Date().getTime()));//上传时间
            ysRwyhqrfjEntity.setScFlag("0");//不删除
            yswtservice.saveysRwyhqrfjEntity(ysRwyhqrfjEntity);

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
     * 获取预算委托时上传附件
     * 用户确认上传凭证
     * @param ysrwzbid
     * @return
     */
    @RequestMapping(value = "getYszlYhpz" ,method = RequestMethod.POST)
    @ResponseBody
    public String getYszlYhpz(String ysrwzbid){
        List<YsZlxx> YszlYhpz = yswtservice.getYszlYhpz(ysrwzbid);

        JSONObject jo = new JSONObject();
        jo.put("YszlYhpz",YszlYhpz);


        return jo.toString();
    }

    /**
     * 删除附件
     * 用户确认凭证
     * @param yhqrfjid
     * @return
     */
    @ApiOperation(value = "删除附件（用户确认凭证）")
    @RequestMapping(value = "deleteYhqrfj", method = RequestMethod.POST)
    @ResponseBody
    public String deleteYhqrfj(String yhqrfjid){
        String flag="ok";
        try {
            yswtservice.deleteYhqrfj(yhqrfjid);
        } catch (Exception e) {
            flag="error";
        }
        return flag;
    }

    /**
     * 获取基本信息
     * @param xmid
     * @return
     */
    @ApiOperation(value = "获取基本信息")
    @RequestMapping(value = "getYsbc", method = RequestMethod.POST)
    @ResponseBody
    public String getYsbc(String xmid){
        String flag="ok";
        JSONObject jo = new JSONObject();
        List<XmInstanceEntity> ysbc = null;
        try {
            ysbc = yswtservice.getYsbc(xmid);
            jo.put("ysbc",ysbc);
            return jo.toString();
        } catch (Exception e) {
            flag="error";
        }

        return flag;
    }

    /**
     * 初始化前期预算资料
     * @return
     */
    @ApiOperation(value = "初始化前期预算资料")
    @RequestMapping(value = "getEarly_stage" ,method = RequestMethod.POST)
    @ResponseBody
    public String getEarly_stage(String xmid,String zbid){
        List<YsZlxx> getEarly_stage = yswtservice.getEarly_stage(xmid,zbid);

        JSONObject jo = new JSONObject();
        jo.put("getEarly_stage",getEarly_stage);
        return jo.toString();
    }

    /**
     * 初始化预算成果资料
     * @return
     */
    @ApiOperation(value = "初始化预算成果资料")
    @RequestMapping(value = "getLate" ,method = RequestMethod.POST)
    @ResponseBody
    public String getLate(String zbid){
        List<YsZlxx> getLate = yswtservice.getLate(zbid);

        JSONObject jo = new JSONObject();
        jo.put("getLate",getLate);
        return jo.toString();
    }

    /**
     * 会审结果基本信息
     * @return
     */
    @ApiOperation(value = "会审结果基本信息")
    @RequestMapping(value = "gethsjljbxx" ,method = RequestMethod.POST)
    @ResponseBody
    public String gethsjljbxx(String xmid){
        List<Map<Object,String>> gethsjljbxx = yswtservice.gethsjljbxx(xmid);
        JSONObject jo = new JSONObject();
        jo.put("gethsjljbxx",gethsjljbxx);
        return jo.toString();
    }

    /**
     * 查询项目是否创建预算任务主表
     * @return
     */
    @ApiOperation(value = "查询项目是否创建预算任务主表")
    @RequestMapping(value = "getYszbidOrGuid" ,method = RequestMethod.POST)
    @ResponseBody
    public String getYszbidOrGuid(String xmid,HttpServletRequest request){
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");//获取当前登录信息
        JSONObject jo = new JSONObject();
        String yszbid = "";
        String sfcz = "1";//1存在 0不存在

        //查询项目是否创建预算任务主表
        YsRwzb ysRwzb = yswtservice.getYsRwzbByXmid(xmid);
        try {
            //存在预算主表
            yszbid = ysRwzb.getId();
        } catch (Exception e) {
            sfcz = "0";//不存在
            //不存在预算主表
            /*YsRwzb rwzb = new YsRwzb();
            rwzb.setId(GuidUtils.getGuid());
            yszbid = rwzb.getId();
            rwzb.setXmid(xmid);
            rwzb.setYsr(userEntity.getUserid());
            rwzb.setYskssj(new Date());
            rwzb.setYszt("1");//预算状态0待预算1预算中2预算变更中3完成4作废
            rwzb.setRwbh(rwbh);
            yswtservice.saveYsRwzb(rwzb);*/
            yszbid = GuidUtils.getGuid();
        }



        jo.put("sfcz",sfcz);
        jo.put("yszbid",yszbid);
        return jo.toString();
    }

   //创建预算任务主表（YS_RWZB）
    @ApiOperation(value = "创建预算任务主表（YS_RWZB）")
    @RequestMapping(value = "newYsrwzb" ,method = RequestMethod.POST)
    @ResponseBody
    public String newYsrwzb(String xmid,String yszbid,HttpServletRequest request){
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");//获取当前登录信息
        JSONObject jo = new JSONObject();
        //调用存储过程获取任务编号
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("v_stop_run", "YSWT");
        service.getRwbh(map);//获取任务编号
        String rwbh = "YSWT" + map.get("p_result");
        String flag = "";

        try {
            //当前时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            //当前时间减10分钟
            Calendar nowTime2 = Calendar.getInstance();
            nowTime2.add(Calendar.MINUTE, -10);//30分钟前的时间

            YsRwzb rwzb = new YsRwzb();
            rwzb.setId(yszbid);
            rwzb.setXmid(xmid);
            rwzb.setYsr(userEntity.getUserid());
            rwzb.setYskssj(nowTime2.getTime());
            rwzb.setYsjssj(new Date());
            rwzb.setYszt("3");//预算状态0待预算1预算中2预算变更中3完成4作废
            rwzb.setRwbh(rwbh);
            yswtservice.saveYsRwzb(rwzb);
            flag = "ok";
        } catch (Exception e) {
            flag = "error";
        }

        jo.put("flag",flag);
        return jo.toString();
    }



    //预算编制跳转
    @ApiOperation(value = "预算编制跳转")
    @RequestMapping(value = "getXxzl" ,method = RequestMethod.POST)
    @ResponseBody
    public String getXxzl(String xmid,HttpServletRequest request){
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");//获取当前登录信息
        JSONObject jo = new JSONObject();

        YsVbzNewEntity YsVbzNewEntity = yswtservice.getXxzl(xmid);

        jo.put("getXxzl",YsVbzNewEntity);

        return jo.toString();
    }


    public String getProcinstidNew() {
        return procinstidNew;
    }

    public void setProcinstidNew(String procinstidNew) {
        this.procinstidNew = procinstidNew;
    }

    public String getYsrid() {
        return ysrid;
    }

    public void setYsrid(String ysrid) {
        this.ysrid = ysrid;
    }
}

