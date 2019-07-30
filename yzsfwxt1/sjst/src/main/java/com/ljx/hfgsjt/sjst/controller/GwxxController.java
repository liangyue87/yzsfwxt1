package com.ljx.hfgsjt.sjst.controller;

import com.ljx.hfgsjt.entity.dto.Activity.WfDfActivityEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfReNodeEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfRuInstContentEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfRuInstEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.*;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.message.controller.MessageController;
import com.ljx.hfgsjt.message.service.MessageService;
import com.ljx.hfgsjt.repository.CalculateHours;
import com.ljx.hfgsjt.sjst.service.GwxxService;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import com.ljx.hfgsjt.util.utils.StringUtil;
import com.ljx.hfgsjt.xkybz.service.XkylxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "GwxxController")
@Api(description = "官网信息数据列表")
public class GwxxController {

    @Autowired
    private GwxxService gwxxService;
    @Autowired
    private MessageService messageService;
    @Autowired
    MessageController controller;
    @Autowired
    private XkylxService service;
    CalculateHours cal = new CalculateHours();

    //会审结果
    @ApiOperation(value = "获取会审结果集")
    @RequestMapping(value = "getHsnr", method = RequestMethod.POST)
    public String getHsnr(String xmid, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        HsjglrZb hsjglrZb = gwxxService.getHsjglr(xmid);
        if (hsjglrZb != null) {
            jo.put("hysj", hsjglrZb.getCjsj_str());
            jo.put("hyjg", hsjglrZb.getXmjlyj());
            jo.put("hycyr", hsjglrZb.getCyry());
        }
        return jo.toString();
    }

    @ApiOperation(value = "获取会审结果集")
    @RequestMapping(value = "getHsjgList", method = RequestMethod.GET)
    public String getHsjgList(HttpServletRequest request, NewsGwxx gwxx) {
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");//获取当前登录人
        List<NewsGwxx> list = new ArrayList<NewsGwxx>();
        int total = 0;
        try {
            gwxx.setStartRow();
            gwxx.setEndRow();
            gwxx.setXmfzr(userEntity.getUserid());
            total = gwxxService.getHsjglrCount(gwxx);
            if (total > 0) {
                list = gwxxService.getHsjglrList(gwxx);
                for (int i = 0; i < list.size(); i++) {
                    HsjglrZb hsjglrZb = gwxxService.getHsjglr(list.get(i).getId());
                    if (hsjglrZb != null) {
                        list.get(i).setDqxmzt("2");//完成
                    } else {
                        list.get(i).setDqxmzt("1");//真在进行
                    }
                }
            } else {
                list = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("total", total);
        jo.put("list", list);
        return jo.toString();
    }

    //会审结果
    @ApiOperation(value = "获取会审上传资料")
    @RequestMapping(value = "getHsjgLsit", method = RequestMethod.GET)
    public String getHsjgLsit(HttpServletRequest request, Integer page, Integer size, String xmid) {

        List<HsjglrFj> list = new ArrayList<HsjglrFj>();
        Map<String, Object> map = new HashedMap();
        map.put("page", (page - 1) * size);
        map.put("pageSize", (page - 1) * size + size);
        map.put("xmid", xmid);
        int total = 0;
        try {
            total = gwxxService.getHsjgCount(xmid);
            if (total > 0) {
                list = gwxxService.getHsjgLsit(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("total", total);
        jo.put("list", list);
        System.out.println("------" + jo.toString());
        return jo.toString();
    }

    //会审结果
    @ApiOperation(value = "删除会审资料")
    @RequestMapping(value = "deleteHszl", method = RequestMethod.GET)
    public String deleteHszl(HttpServletRequest request, String id) {
        JSONObject jo = new JSONObject();
        gwxxService.delectHsfj(id);
        jo.put("msg", "success");
        return jo.toString();
    }

    /***
     * 会审结果录入
     * @param request
     * @return
     */
    @RequestMapping(value = "saveHsjglr", method = RequestMethod.POST)
    @ResponseBody
    public String saveHsjglr(String xmid, String hysj, String info, String usercyr,String username, HttpServletRequest request) {
        HttpSession session = request.getSession();
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
        JSONObject jo = new JSONObject();
        String zbid = GuidUtils.getGuid();
        HsjglrFj hsjglrFj = new HsjglrFj();
        String fjlx = "1";
        List<HsjglrFj> list = gwxxService.selectHsFj(fjlx);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                hsjglrFj.setSjthsid(zbid);
                hsjglrFj.setFjlx_ej(list.get(i).getFjlx_yj());
                hsjglrFj.setId(list.get(i).getId());
                hsjglrFj.setFjlj(list.get(i).getFjlj());
                hsjglrFj.setFjhz(list.get(i).getFjhz());
                hsjglrFj.setFjmc(list.get(i).getFjmc());
                hsjglrFj.setScr(list.get(i).getScr());
                hsjglrFj.setScsj(list.get(i).getScsj());
                gwxxService.updataHsFj(hsjglrFj);
            }
        }
        try {
            Date dcsj = sdft.parse(hysj);
            HsjglrZb hsjglrZb = new HsjglrZb();
            hsjglrZb.setXmid(xmid);
            hsjglrZb.setCjsj(dcsj);
            hsjglrZb.setXmjlyj(info);
            usercyr = username;
            hsjglrZb.setCyry(usercyr);
            hsjglrZb.setId(zbid);
            hsjglrZb.setSjstrwid(GuidUtils.getGuid());
            hsjglrZb.setCjr(((UserEntity) session.getAttribute("user")).getUserid());
            gwxxService.insertHsiglr(hsjglrZb);
            //结束实例
            InstContentEntity incentity = new InstContentEntity();
            incentity.setContentid(GuidUtils.getGuid());
            incentity.setTableName("XM_INSTANCE");
            incentity.setProcInstId(zbid);
            incentity.setContentvalue(xmid);
            gwxxService.insetHiinstContent(incentity);//添加

            InstEntity instEntity = new InstEntity();
            instEntity.setPdefId("30020");
            instEntity.setProcInstId(zbid);
            instEntity.setProcstatus("1");//结束
            instEntity.setStartdt(new Date());
            instEntity.setStartuser(user.getUserid());
            gwxxService.insertHiInst(instEntity);//添加

            String pdefid_id = "30020";
            //XmUserEntity xmUserEntity = gwxxService.getGwmrr(pdefid_id);
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setTaskid(GuidUtils.getGuid());
            taskEntity.setPtaskid("0");//前置任务为空
            taskEntity.setC_activityid("30020");//环节编号
            taskEntity.setQssj(new Date());
            taskEntity.setJssj(new Date());
            taskEntity.setIscurrent("0");//已完成
            taskEntity.setRwblr(user.getUserid());//任务办理人id
            taskEntity.setCzr(user.getUserid());//操作人
            taskEntity.setIscheck("1");//已签收
            taskEntity.setHasnew("2");//无子流程
            taskEntity.setProc_inst_id(zbid);//流程实例ID
            gwxxService.insertHiTask(taskEntity);//添加
            jo.put("result", "success");

        } catch (Exception e) {
            jo.put("result", "fail");
            e.printStackTrace();
        }
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
    public String importFile(
            @RequestParam(value = "multipartFiles", required = false) MultipartFile multipartFiles,
            @RequestParam(value = "xmid", required = false) String xmid,
            @RequestParam(value = "operation", required = false) String operation,
            @RequestParam(value = "filename", required = false) String filename,
            HttpServletRequest request) {
        JSONObject jo = new JSONObject();

        String id = GuidUtils.getGuid();
        if (Objects.isNull(multipartFiles)) {
            jo.put("msg", "文件为空，请重新上传");
            return jo.toString();
        }
        XmInstance xmInstance = new XmInstance();
        xmInstance = gwxxService.getXzqyDz(xmid);
        String fileDir = StringUtil.getBasePath() + "upload" + File.separator + xmInstance.getXmbh()+ File.separator + "sjst" + File.separator;
        String  fiLj="upload" + File.separator + xmInstance.getXmbh()+ File.separator + "sjst" + File.separator;
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
            String str_date = "_" + df.format(new Date());
            file.renameTo(new File(fileDir + filenames[0] + str_date + "." + filenames[filenames.length - 1]));//重命名
            HttpSession session = request.getSession();
            UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
            HsjglrFj hsjglrFj = new HsjglrFj();
            hsjglrFj.setId(id);//id
            hsjglrFj.setFjlx_yj("1");//表示会审资料
            hsjglrFj.setFjlj(fiLj + filenames[0] + str_date + "." + filenames[filenames.length - 1]);//附件路径
            hsjglrFj.setFjhz(filenames[filenames.length - 1]);//附件后缀
            hsjglrFj.setFjmc(filename);//附件名称
            hsjglrFj.setScr(user.getUserid());//上传人
            hsjglrFj.setScsj(new Date());
            hsjglrFj.setXmid(xmid);
            gwxxService.insertHsjglrFj(hsjglrFj);
            jo.put("msg", "success");
            jo.put("id", id);
        } catch (Exception e) {
            jo.put("msg", "程序异常，请联系管理员");
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


    //发起管网
    @ApiOperation(value = "获取官网信息数据集合")
    @RequestMapping(value = "findXmxx", method = RequestMethod.GET)
    public String findXmxx(HttpServletRequest request, String xmid) {
        JSONObject jo = new JSONObject();
        XmInstance xmInstance = new XmInstance();
        xmInstance = gwxxService.getXzqyDz(xmid);
        jo.put("list", xmInstance);
        System.out.println("------" + jo.toString());
        return jo.toString();

    }


    @ApiOperation(value = "获取官网信息数据集合")
    @RequestMapping(value = "getGwxxList", method = RequestMethod.GET)
    public String getGwxxList(HttpServletRequest request, NewsGwxx gwxx) {
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");//获取当前登录人
        List<NewsGwxx> list = new ArrayList<NewsGwxx>();
        int total = 0;
        try {
            gwxx.setStartRow();
            gwxx.setEndRow();
            gwxx.setXmfzr(userEntity.getUserid());
            total = gwxxService.getGwxxCount(gwxx);
            if (total > 0) {
                list = gwxxService.getGwxxList(gwxx);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("total", total);
        jo.put("list", list);
        // System.out.println("------" + jo.toString());
        return jo.toString();

    }

    @ApiOperation(value = "保存官网信息")
    @RequestMapping(value = "saveFqgwxxTask", method = RequestMethod.POST)
    @ResponseBody
    public String saveFqgwxxTask(String xmid, String qs, HttpServletRequest request) {//xmid  项目id  qs 区所
        System.out.println(qs + "--------------qs");
        Map<String, Object> map = new HashMap<String, Object>();
        DxDfsEntity dxDfsEntity = new DxDfsEntity();
        String ss = "ZHYS";
        map.put("v_stop_run", ss);
        gwxxService.texuChange(map);
        String rwbh = "FQGW" + map.get("p_result");
        String id = GuidUtils.getGuid();
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");//获取当前登录的用户信息
        String pdefId = "30006";
        JSONObject jo = new JSONObject();
        String czr = user.getUserid();
        XzqyEntity xzqyEntity = new XzqyEntity();
        if (qs != null) {
            xzqyEntity = gwxxService.getGssName(qs);
        }
        GwxxdcZb gwxxdcZb = new GwxxdcZb();
        gwxxdcZb.setId(id);//id
        gwxxdcZb.setXmid(xmid);//实例id
        gwxxdcZb.setFqr(czr);//操作人
        gwxxdcZb.setFqsj(new Date());//当前时间
        gwxxdcZb.setGss(xzqyEntity.getMc());//供水所名称
        //   System.out.println(id+"---id---"+pdefId+"---pdefId------"+czr+"--czr---"+new Date()+"---new Date()--"+xzqyEntity.getMc()+"-------");
        gwxxService.insertGwxxZb(gwxxdcZb);
        XmInstance xmInstance = new XmInstance();
        xmInstance = gwxxService.selectXminstance(xmid);//查对象在跟新
        //   xmInstance.setId(xmid);
        xmInstance.setQsfzr(czr);
        //  System.out.println(xmInstance.getXzqybh()+"-------");
        xmInstance.setBmbmQs(qs);
        //   System.out.println(id+"---id---"+xmid+"---pdefId------"+czr+"--czr---"+qs+"------qs");
        gwxxService.updateXminstance(xmInstance);//供水所负责人跟新至主表对应位置

        InstEntity inst = new InstEntity();
        inst = gwxxService.selectRuInst("30005", xmid);//查
        if (inst != null) {  //退回有实例则跟新
            inst.setPdefId("30006");
            gwxxService.updataInst(inst);//跟新

            TaskEntity task1 = new TaskEntity();
            task1 = gwxxService.selectTask("30005", xmid);//查
            task1.setQssj(new Date());
            task1.setC_activityid("30006");
            gwxxService.updataTask(task1);//跟新
        } else {
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
            wfRuInstContentEntity.setContenttype("GwxxdcZb");
            wfRuInstContentEntity.setContentname(id);
            service.saveWfRuInstContent(wfRuInstContentEntity);

            //创建任务，存入历史任务表中
            String pdefid_id = "30005";
            //XmUserEntity xmUserEntity = gwxxService.getGwmrr(pdefid_id);
            TaskEntity task = new TaskEntity();
            String taskId = GuidUtils.getGuid();
            task.setTaskid(taskId);
            task.setPtaskid("0");//前置任务为空
            task.setProc_inst_id(procInstId);//流程实例ID
            task.setC_activityid("30005");//环节编号saveGwxxFQ
            task.setQssj(new Date());
            task.setJssj(new Date());
            task.setRwblr(user.getUserid());//任务办理人id为当前登录人id
            task.setIscurrent("0");//已完成
            task.setCzr(czr);//操作人
            task.setIscheck("1");//已签收
            task.setHasnew("2");//无子流程
            gwxxService.insertHiTask(task);

            //下一节点 创建任务  综合验收申请
            UserEntity userEntity = gwxxService.getUserByqs(qs);//根据区所查询操作人
            String direction = "0";//流程方向 前进/回退s
            List<WfReNodeEntity> list = service.findByActivityidAndDirection(pdefid_id, direction);
            WfDfActivityEntity wfDfActivityEntity = service.findByActivityid(list.get(0).getNextnode());
            taskId = GuidUtils.getGuid();
            task.setTaskid(taskId);
            task.setPtaskid("0");//前置任务为空
            task.setProc_inst_id(procInstId);//流程实例ID
            task.setC_activityid(list.get(0).getNextnode());//环节编号
            task.setIscurrent("1");//已完成
            task.setCzr(czr);//操作人
            task.setRwblr(userEntity.getUserid());//任务办理人id为区所分派人
            task.setIscheck("0");//已签收
            task.setHasnew("1");//有流程
            task.setRefkey("sjst_gwdczb");//关联实体key
            task.setRefid(id);//关联实体ID
            Date qssj = new Date();
            SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            task.setCheck_time(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
            task.setJssx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
            task.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute()) + Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
            gwxxService.insertTask(task);

            //发送任务提醒短信
            String content = "";
            Map<String, Object> userMap = null;

            dxDfsEntity.setId(GuidUtils.getGuid());//标识
            dxDfsEntity.setDxmbid("4");//短信模版标识
            dxDfsEntity.setFsr(user.getUserid());//发送人
            dxDfsEntity.setFssj(new Date());//发送时间
            dxDfsEntity.setFscs("1");//发送次数
            Map<String, Object> dxmb = messageService.getDxMb("4");//模版4：任务提醒，替换短信模版内容
            XmInstanceEntity xmInstanceEntity = gwxxService.getXmInstanceByXmid(xmid);//获取项目名称
            content = dxmb.get("DXMB").toString();//获取短信模版
            content = content.replace("[环节名称类型]", wfDfActivityEntity.getActivityname());
            content = content.replace("[任务单编号]", rwbh);
            content = content.replace("[项目名称]", xmInstanceEntity.getXmmc());
            dxDfsEntity.setFsnr(content);//发送内容
            // UserEntity userEntity = yswtservice.getUserIdAndUserMobilephone("40008");
            dxDfsEntity.setJsr(userEntity.getUserid());//接收人
            dxDfsEntity.setJshm(userEntity.getMobilephone());//接收号码
            dxDfsEntity.setTaskid(taskId);
            gwxxService.saveDxDfsEntity(dxDfsEntity);//保存

        }
        jo.put("result", "success");
        return jo.toString();
    }

    @ApiOperation(value = "查询对应的区域 根据项目主表id")
    @RequestMapping(value = "selectXzqy", method = RequestMethod.POST)
    @ResponseBody
    public String selectXzqy(String xmid, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        TaskEntity taskEntity = gwxxService.selectTask("30005", xmid);
        if (taskEntity != null) {
            jo.put("htyyStr", taskEntity.getHtyy());
        } else {
            jo.put("htyyStr", "err");
        }
        return jo.toString();
    }

    //管网反馈列
    @ApiOperation(value = "获取管网反馈列表")
    @RequestMapping(value = "GetfqlbList", method = RequestMethod.GET)
    public String GetfqlbList(HttpServletRequest request, NewsGwxx gwxx) {
        List<NewsGwxx> list = new ArrayList<NewsGwxx>();
        int total = 0;
        try {
            gwxx.setStartRow();
            gwxx.setEndRow();
            total = gwxxService.getGwxxFqCount(gwxx);
            if (total > 0) {
                list = gwxxService.getGwxxFqList(gwxx);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("total", total);
        jo.put("list", list);
        return jo.toString();

    }

    @ApiOperation(value = "获取官网信息从表数据")
    @RequestMapping(value = "getGwxxdcCB", method = RequestMethod.GET)
    @ResponseBody
    public String getGwxxdcCB(HttpServletRequest request, String xmid) {
        // System.out.println(xmid + "-----");
        JSONObject jo = new JSONObject();
        List<GwxxdcCb> list = new ArrayList<GwxxdcCb>();
        list = gwxxService.selectGwxxdcCBList(xmid);
        jo.put("row", list);
        return jo.toString();

    }

    @ApiOperation(value = "保存官网信息从表数据")
    @RequestMapping(value = "saveGwxxdcCB", method = RequestMethod.POST)
    @ResponseBody
    public String saveGwxxdcCB(GwxxdcCb gwxxdcCb, String xmid, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        try {
            if (gwxxdcCb.getId() == null || gwxxdcCb.getId().equals("")) {
                gwxxdcCb.setId(GuidUtils.getGuid());
            }
            //System.out.println(gwxxdcCb.getDcnr()+"---------");
            GwxxdcZb gwxxdc = gwxxService.selectGwxxdcZB(xmid);
            gwxxdcCb.setGwdczbid(gwxxdc.getId());
            //gwxxdcCb.setDcnr(gwxxdcCb.getDcnr());
            gwxxService.saveGwxxFq(gwxxdcCb);
            jo.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("result", "fail");
        }
        return jo.toString();

    }

    @ApiOperation(value = "删除官网反馈信息从表数据")
    @RequestMapping(value = "deleteGwxxFq", method = RequestMethod.POST)
    @ResponseBody
    public String deleteGwxxFq(String id, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        try {
            gwxxService.deleteGwxxFq(id);
            jo.put("result", "success");
        } catch (Exception e) {
            jo.put("result", "fail");
            e.printStackTrace();
        }
        return jo.toString();


    }

    @ApiOperation(value = "保存官网反馈数据，并结束流程")
    @RequestMapping(value = "saveGwxxFQ", method = RequestMethod.POST)
    @ResponseBody
    public String saveGwxxFQ(String xmid, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        //关闭流程实列类容
        try {
            System.out.print(xmid + "--------");
            String InstId = "";
            //  String procInstId = GuidUtils.getGuid();//proc_inst_id
            //WF_RU_INST_CONTENT    WF_HI_INST_CONTENT
            InstContentEntity incentity = new InstContentEntity();
            incentity = gwxxService.getRuInstContent(xmid);//查
            InstId = incentity.getProcInstId();//唯一ProcInstId
            incentity.setContentid(GuidUtils.getGuid());
            // incentity.setProcInstId(procInstId);
            gwxxService.insetHiinstContent(incentity);//添加

            //WF_RU_INST     WF_HI_INST
            InstEntity instEntity = new InstEntity();
            instEntity = gwxxService.getRuInst(xmid);//查
            //  instEntity.setProcInstId(procInstId);
            //   instEntity.setProcstatus("0");//结束
            gwxxService.insertHiInst(instEntity);//添加

            //WF_TASK   WF_TASK_HISTORY
            String pdefid_id = "30005";
//            XmUserEntity xmUserEntity = gwxxService.getGwmrr(pdefid_id);
            TaskEntity taskEntity = new TaskEntity();
            taskEntity = gwxxService.getTask(xmid);//查
            String taskId = GuidUtils.getGuid();
            taskEntity.setTaskid(taskId);
//            taskEntity.setRwblr(xmUserEntity.getUserid());//任务办理人id
            taskEntity.setJssj(new Date());
            //    taskEntity.setProc_inst_id(procInstId);//流程实例ID
//            System.out.print(xmUserEntity.getUserid()+"------------11---------");
            gwxxService.insertHiTask(taskEntity);//添加
            //删除WF_RU_INST_CONTENT    WF_RU_INST     WF_TASK
            //    System.out.print(InstId+"---------------------");
            gwxxService.deleteRuInst(InstId);
            gwxxService.deleteRuInstContent(InstId);
            gwxxService.deleteTask(InstId);

            jo.put("result", "success");
        } catch (Exception e) {
            jo.put("result", "fail");
            e.printStackTrace();
        }

        return jo.toString();


    }

    @ApiOperation(value = "官网反馈数据退回，保存退回原因")
    @RequestMapping(value = "saveReverSion", method = RequestMethod.POST)
    @ResponseBody
    public String saveReverSion(String xmid, String thyy, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        DxDfsEntity dxDfsEntity = new DxDfsEntity();
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");//获取当前登录人
        //关闭流程实列类容
        //WF_RU_INST     WF_HI_INST
        InstEntity instEntity = new InstEntity();
        instEntity = gwxxService.getRuInst(xmid);//查
        instEntity.setPdefId("30005");
        gwxxService.updataInst(instEntity);//跟新

        //WF_TASK   WF_TASK_HISTORY
        TaskEntity taskEntity = new TaskEntity();
        taskEntity = gwxxService.getTask(xmid);//查
        //----------------------------------------------
        taskEntity.setHtyy(thyy);//添加回退原因
        taskEntity.setIscheck("1");//签收
        gwxxService.insertHiTask(taskEntity);//保存到历史任务中

        String direction = "1";//流程方向 前进/回退s
        String pdefid_id = "30006";
        List<WfReNodeEntity> list = service.findByActivityidAndDirection(pdefid_id, direction);
        WfDfActivityEntity wfDfActivityEntity = service.findByActivityid(list.get(0).getNextnode());
        taskEntity.setC_activityid(list.get(0).getNextnode());//下一个节点30005
        taskEntity.setHtyy(thyy);
        //  taskEntity.setIscheck("1");//签收
        Date qssj = new Date();
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        taskEntity.setCheck_time(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
        taskEntity.setJssx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
        taskEntity.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute()) + Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
        XmInstanceEntity xmInstanceEntity = gwxxService.getXmInstanceByXmid(xmid);//获取项目负责人
        taskEntity.setRwblr(xmInstanceEntity.getXmfzr());
        gwxxService.updataTask(taskEntity);//跟新

        //发送任务提醒短信
        String content = "";
        Map<String, Object> userMap = null;

        dxDfsEntity.setId(GuidUtils.getGuid());//标识
        dxDfsEntity.setDxmbid("4");//短信模版标识
        dxDfsEntity.setFsr(user.getUserid());//发送人
        dxDfsEntity.setFssj(new Date());//发送时间
        dxDfsEntity.setFscs("1");//发送次数
        Map<String, Object> dxmb = messageService.getDxMb("4");//模版4：任务提醒，替换短信模版内容
        //XmInstanceEntity xmInstanceEntity = gwxxService.getXmInstanceByXmid(xmid);//获取项目名称
        content = dxmb.get("DXMB").toString();//获取短信模版
        content = content.replace("[环节名称类型]", wfDfActivityEntity.getActivityname());
        WfRuInstEntity wfRuInstEntity = gwxxService.getWfRuInstByXmid(xmid);//查找项目编号
        content = content.replace("[任务单编号]", wfRuInstEntity.getParammap());
        content = content.replace("[项目名称]", xmInstanceEntity.getXmmc());
        dxDfsEntity.setFsnr(content);//发送内容
        UserEntity userEntity = gwxxService.getUserByUserid(xmInstanceEntity.getXmfzr());
        dxDfsEntity.setJsr(userEntity.getUserid());//接收人
        dxDfsEntity.setJshm(userEntity.getMobilephone());//接收号码
        dxDfsEntity.setTaskid(taskEntity.getTaskid());
        gwxxService.saveDxDfsEntity(dxDfsEntity);//保存


        //----------------------------------------------


       /* String direction = "1";//流程方向 前进/回退s
        String pdefid_id = "30006";
        List<WfReNodeEntity> list = service.findByActivityidAndDirection(pdefid_id, direction);
        WfDfActivityEntity wfDfActivityEntity = service.findByActivityid(list.get(0).getNextnode());
        taskEntity.setC_activityid(list.get(0).getNextnode());//下一个节点30005
        taskEntity.setHtyy(thyy);
        taskEntity.setIscheck("1");//签收
        Date qssj = new Date();
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        taskEntity.setCheck_time(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getCheckMinute())));
        taskEntity.setJssx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
        taskEntity.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(wfDfActivityEntity.getFinishMinute()) + Float.parseFloat(wfDfActivityEntity.getYellowFinish())));
        XmInstanceEntity xmInstanceEntity = gwxxService.getXmInstanceByXmid(xmid);//获取项目负责人
        taskEntity.setRwblr(xmInstanceEntity.getXmfzr());
        gwxxService.updataTask(taskEntity);//跟新

        taskEntity.setTaskid(GuidUtils.getGuid());
        taskEntity.setC_activityid("30006");
        taskEntity.setHtyy(thyy);

        gwxxService.insertHiTask(taskEntity);*/
        GwxxdcZb gwxxdcZb = gwxxService.selectGwxxdcZB(xmid);
        gwxxService.deleteGwxxCb(gwxxdcZb.getId());//删除从表数据
        gwxxService.deleteGwxxZb(xmid);//删除主表数据

        jo.put("result", "success");
        return jo.toString();


    }



    /***
     * 项目统筹 设计成果上传
     * 获取管网反馈内容
     * @return
     */
    @RequestMapping(value = "getSjstGwfk", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取管网反馈内容")
    private String getSjstGwfk(String xmid, String kczt) {
        JSONObject jo = new JSONObject();

        try{
            List<GwxxdcCb> listGwcb=gwxxService.selectGwxxdcCBList(xmid);
            jo.put("listGwcb", listGwcb);
            jo.put("result", "success");
        }catch (Exception e){
            jo.put("result", "fail");
            e.printStackTrace();
        }

        return jo.toString();
    }


/********     短信接收   *************/
@RequestMapping(value = "getdxjslist", method = RequestMethod.GET)
@ResponseBody
@ApiOperation(value = "短信列表")
public String getdxjslist(HttpServletRequest request, Dx_dypz_lb dxDypzLb) {
    String userid= request.getParameter("userid");
    UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");//获取当前登录人
    UserEntity userEntity1=null;
    List<Dx_dypz_lb> list = new ArrayList<Dx_dypz_lb>();
    List<Dx_dypz_lb> dylist = new ArrayList<Dx_dypz_lb>();
    int total = 0;
    try {
        dxDypzLb.setStartRow();
        dxDypzLb.setEndRow();
        if(userid==null||userid==""){
            userid=userEntity.getUserid();
        }
        dylist=gwxxService.getUserDy(userid);
        userEntity1= gwxxService.getDquser(userid);
        total = gwxxService.getdxjsCount(dxDypzLb);
        if (total > 0) {
            list = gwxxService.getdxjsList(dxDypzLb);

        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    JSONObject jo = new JSONObject();
    jo.put("total", total);
    jo.put("list", list);
    jo.put("dylist", dylist);
    jo.put("username", userEntity1.getUsername());
    jo.put("mobilephone", userEntity1.getMobilephone());
    return jo.toString();

}


@RequestMapping(value = "saveDyjl", method = RequestMethod.POST)
@ResponseBody
@ApiOperation(value = "保存订阅短信")
public String saveDyjl(HttpServletRequest request, String json_gx, String json_qx,String userid) {
    JSONObject jo = new JSONObject();
    UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");//获取当前登录人
    JSONArray json_gx_1 = JSONArray.fromObject(json_gx);
    List<Dx_dypz_lb> persons = (List<Dx_dypz_lb>)JSONArray.toCollection(json_gx_1, Dx_dypz_lb.class);
    JSONArray json_qx_1 = JSONArray.fromObject(json_qx);
    List<Dx_dypz_lb> persons_1 = (List<Dx_dypz_lb>)JSONArray.toCollection(json_qx_1, Dx_dypz_lb.class);
    List<Dx_dypz_lb> dylist = new ArrayList<Dx_dypz_lb>();
    System.out.println();
    try{
        if(userid==null||userid==""){
            userid=userEntity.getUserid();
        }
        //查询当前订阅的短信数据
        dylist=gwxxService.getUserDy(userid);
        //删除取消勾选短信数据
        for(int i=0;i<persons_1.size();i++){//
            gwxxService.deleteUserDy(persons_1.get(i).getYwlx(),persons_1.get(i).getDxlx(),persons_1.get(i).getActivityid(),userid);
        }

       //  gwxxService.deleteUserDy(userid);
        for(int i=0;i<persons.size();i++){
            Dx_dypz_lb dxDypzLb=new Dx_dypz_lb();
            dxDypzLb.setId(GuidUtils.getGuid());
         //   dxDypzLb.setDxlbid(persons.get(i).getDxid());//短信列表id
            dxDypzLb.setYwlx(persons.get(i).getCodevalue());//业务类型
            dxDypzLb.setDxlx(persons.get(i).getId());//业务提醒
            dxDypzLb.setActivityid(persons.get(i).getActivityid());//环节编号
            dxDypzLb.setUserid(userid);//订阅人id
            dxDypzLb.setCjr(userid);//创建人获取最新操作人
            dxDypzLb.setCjsj(new Date());//当前操作时间
            dxDypzLb.setSfdy("1");//订阅
            gwxxService.insertDydx(dxDypzLb);
            //测试保存
        }
        jo.put("result", "success");
    }catch (Exception e){
        jo.put("result", "fail");
        e.printStackTrace();
    }
        return jo.toString();

    }


}
