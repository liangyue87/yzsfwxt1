package com.ljx.hfgsjt.yj.controller;

import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.Sjst.CodeModel;
import com.ljx.hfgsjt.entity.dto.Sjst.ProjectModel;
import com.ljx.hfgsjt.entity.dto.Sjst.XmSb;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.yj.*;
import com.ljx.hfgsjt.repository.CalculateHours;
import com.ljx.hfgsjt.sjst.service.SjstService;
import com.ljx.hfgsjt.util.utils.DateUtils;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import com.ljx.hfgsjt.util.utils.StringUtil;
import com.ljx.hfgsjt.xkybz.service.XkylxService;
import com.ljx.hfgsjt.yj.service.BwyjService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "BwyjController")
@Api(description = "表位移交")
public class BwyjController {

    @Autowired
    private BwyjService service;
    @Autowired
    private SjstService sjstService;
    @Autowired
    private XkylxService xkylxService;
    @ApiOperation(value = "获取项目集合")
    @RequestMapping(value = "getBwyjlb",method = RequestMethod.GET)
    public String  getBwyjlb(HttpServletRequest request, ProjectModel model){
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

    @ApiOperation(value = "获取阀门集合")
    @RequestMapping(value = "getFmData",method = RequestMethod.GET)
    public String  getFmData(String xmid,Integer page, Integer size){
        JSONObject jo = new JSONObject();
        List<XmFm> list = new ArrayList<XmFm>();
        int total = 0;
        int startRow=0;
        int endRow=0;
        try {
            startRow = (page - 1) * size + 1;
            endRow = page * size;
            total = service.getFmCount(xmid);
            if(total>0){
                list = service.getFmData(xmid,startRow,endRow);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("total",total);
        jo.put("row",list);
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
        sjstService.getKey(map);
        if(type==1){
            key= (String) map.get("key");
        }else {
            key = mc+map.get("key");
        }
        return key;
    }

    @ApiOperation(value = "阀门信息维护")
    @RequestMapping(value = "fmxxwh",method = RequestMethod.GET)
    public boolean  fmxxwh(HttpServletRequest request,XmFm model){
        boolean flag = false;
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        Date date = new Date();
        try {
            if(StringUtil.isEmpty(model.getFmbh())){
                String fmbh = getKey("FM",0);
                model.setFmbh(fmbh);
                model.setFmyxbh(fmbh);
                model.setId(GuidUtils.getGuid());
                model.setCzr(user.getUserid());
                model.setCzsj(date);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                model.setAzrq(sdf.parse(model.getAzrq_vue()));
                if(StringUtil.isEmpty(model.getBz())){
                    model.setBz("-");
                }
                model.setZt("1");
                service.insertFm(model);
                flag=true;
            }else{
                model.setCzr(user.getUserid());
                model.setCzsj(date);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                model.setAzrq(sdf.parse(model.getAzrq_vue()));
                service.insertFm(model);
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation(value = "删除阀门")
    @RequestMapping(value = "fmpzDelete",method = RequestMethod.GET)
    public boolean  fmpzDelete(HttpServletRequest request,String id){
        boolean flag = false;
        XmFm model = new XmFm();
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        Date date = new Date();
        try {
            model.setId(id);
            model.setCzr(user.getUserid());
            model.setCzsj(date);
            model.setZt("0");
            service.fmpzDelete(model);
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }


    @ApiOperation(value = "获取水表")
    @RequestMapping(value = "getSb",method = RequestMethod.GET)
    public String  getSb(HttpServletRequest request,String xmId,Integer page, Integer size){
        JSONObject jo = new JSONObject();
        List<XmSb> list = new ArrayList<XmSb>();
        int total = 0;
        int startRow=0;
        int endRow=0;
        try {
            startRow = (page - 1) * size + 1;
            endRow = page * size;
            total = service.getSbCount(xmId);
            if(total>0){
                list = service.getSbData(xmId,startRow,endRow);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("total",total);
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "获取阀门口径")
    @RequestMapping(value = "getFmkj",method = RequestMethod.GET)
    public String  getFmkj(HttpServletRequest request){
        JSONObject jo = new JSONObject();
        List<CodeModel> list = new ArrayList<CodeModel>();
        try {
            list = service.getFmkj();
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "获取阀门类型")
    @RequestMapping(value = "getFmlx",method = RequestMethod.GET)
    public String  getFmlx(HttpServletRequest request){
        JSONObject jo = new JSONObject();
        List<CodeModel> list = new ArrayList<CodeModel>();
        try {
            list = service.getFmlx();
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "获取阀杆材质")
    @RequestMapping(value = "getFgcz",method = RequestMethod.GET)
    public String  getFgcz(HttpServletRequest request){
        JSONObject jo = new JSONObject();
        List<CodeModel> list = new ArrayList<CodeModel>();
        try {
            list = service.getFgcz();
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "阀门作用")
    @RequestMapping(value = "getFmzy",method = RequestMethod.GET)
    public String  getFmzy(HttpServletRequest request){
        JSONObject jo = new JSONObject();
        List<CodeModel> list = new ArrayList<CodeModel>();
        try {
            list = service.getFmzy();
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "转动方式")
    @RequestMapping(value = "getZdfs",method = RequestMethod.GET)
    public String  getZdfs(HttpServletRequest request){
        JSONObject jo = new JSONObject();
        List<CodeModel> list = new ArrayList<CodeModel>();
        try {
            list = service.getZdfs();
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "转动方向")
    @RequestMapping(value = "getZdfx",method = RequestMethod.GET)
    public String  getZdfx(HttpServletRequest request){
        JSONObject jo = new JSONObject();
        List<CodeModel> list = new ArrayList<CodeModel>();
        try {
            list = service.getZdfx();
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "阀井盖材质")
    @RequestMapping(value = "getfjgcz",method = RequestMethod.GET)
    public String  getfjgcz(HttpServletRequest request){
        JSONObject jo = new JSONObject();
        List<CodeModel> list = new ArrayList<CodeModel>();
        try {
            list = service.getfjgcz();
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "阀井盖形式")
    @RequestMapping(value = "getFjgxs",method = RequestMethod.GET)
    public String  getFjgxs(HttpServletRequest request){
        JSONObject jo = new JSONObject();
        List<CodeModel> list = new ArrayList<CodeModel>();
        try {
            list = service.getFjgxs();
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "阀门数据回显")
    @RequestMapping(value = "fmpzHandle",method = RequestMethod.GET)
    public String  fmpzHandle(String id){
        JSONObject jo = new JSONObject();
        List<XmFm> list = new ArrayList<XmFm>();
        try {
            list = service.fmpzHandle(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "查询点志记")
    @RequestMapping(value = "getDzjlb",method = RequestMethod.GET)
    public String  getDzjlb(String xmid,Integer page, Integer size){
        JSONObject jo = new JSONObject();
        List<YjDzjzb> list = new ArrayList<YjDzjzb>();
        int total = 0;
        int startRow=0;
        int endRow=0;
        try {
            startRow = (page - 1) * size + 1;
            endRow = page * size;
            total = service.getDzjlbCount(xmid);
            if(total>0){
                list = service.getDzjlbData(xmid,startRow,endRow);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("total",total);
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "查询阀门点志记")
    @RequestMapping(value = "getfmdzjData",method = RequestMethod.GET)
    public String  getfmdzjData(String id){
        JSONObject jo = new JSONObject();
        List<YjDzjzb> list = new ArrayList<YjDzjzb>();
        try {
            list = service.getfmdzjData(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        return jo.toString();
    }

    @ApiOperation(value = "阀门点志记信息维护")
    @RequestMapping(value = "fmdzjxxwh",method = RequestMethod.GET)
    public boolean  fmdzjxxwh(HttpServletRequest request,YjDzjzb model){
        boolean flag = false;
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        Date date = new Date();
        YjDzjcb cbModel = new YjDzjcb();
        String xmyjCount="";
        try {
            if(StringUtil.isEmpty(model.getDzjbh())){
                String dzjbh = getKey("DZJ",0);
                model.setDzjbh(dzjbh);
                if(StringUtil.isEmpty(model.getId())){
                    model.setId(GuidUtils.getGuid());
                }
                if(StringUtil.isEmpty(model.getHtr())){
                    model.setHtr(user.getUsername());
                }
                xmyjCount = service.getxmyjCount(model.getXmid());
                if(StringUtil.isEmpty(xmyjCount)){
                    YjBwyjcb bwyjcbModel = new YjBwyjcb();
                    bwyjcbModel.setId(GuidUtils.getGuid());
                    bwyjcbModel.setBwyjdid(model.getId());
                    bwyjcbModel.setDzjid(xmyjCount);
                    bwyjcbModel.setShzt("0");
                    bwyjcbModel.setZt("1");
                    bwyjcbModel.setGfShzt("0");
                    service.saveBwyjcbModel(bwyjcbModel);
                }
                model.setCzr(user.getUserid());
                model.setCzrq(date);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                model.setAzrq(sdf.parse(model.getAzrq_vue()));
                cbModel.setDzjcblxbh(model.getDzjcbbh());
                cbModel.setId(GuidUtils.getGuid());
                cbModel.setDzjzbid(model.getId());
                cbModel.setFbjj(model.getFbjj());
                cbModel.setFmid(model.getFmid());
                cbModel.setXhsid(model.getXhsid());
                cbModel.setSbid(model.getSbid());
                cbModel.setLdid(model.getLdid());
                cbModel.setGdczbh(model.getGdczbh());
                cbModel.setGdkjbh(model.getGdkjbh());
                service.insertDzjcb(cbModel);
                service.insertDzj(model);
                flag=true;
            }else{
                List<YjDzjcb> list = service.getCbid(model.getId());
                model.setCzr(user.getUserid());
                if(StringUtil.isEmpty(model.getHtr())){
                    model.setHtr(user.getUsername());
                }
                model.setCzrq(date);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                model.setAzrq(sdf.parse(model.getAzrq_vue()));
                cbModel.setId(list.get(0).getId());
                cbModel.setDzjcblxbh(model.getDzjcbbh());
                cbModel.setDzjzbid(model.getId());
                cbModel.setFbjj(model.getFbjj());
                cbModel.setFmid(model.getFmid());
                cbModel.setXhsid(model.getXhsid());
                cbModel.setSbid(model.getSbid());
                cbModel.setLdid(model.getLdid());
                cbModel.setGdczbh(model.getGdczbh());
                cbModel.setGdkjbh(model.getGdkjbh());
                //service.insertDzjcb(cbModel);
                service.insertDzj(model);
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation(value = "删除点志记")
    @RequestMapping(value = "dzjHandleDelete",method = RequestMethod.GET)
    public boolean  dzjHandleDelete(HttpServletRequest request,String id,String zt){
        boolean flag = false;
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        try {
            service.dzjHandleDelete(user.getUserid(),id,zt);
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     *
     * @param request
     * @param xmid
     * @param activityId
     * @param bwyjdbh
     * @param yjfs
     * @param yysj_vue
     * @param type
     * @return 0:提交失败 -1：缺少任务办理人 -2：有多个任务办理人 1：提交成功
     */
    @ApiOperation(value = "提交")
    @RequestMapping(value = "bwyjdzjSubmit",method = RequestMethod.GET)
    public int  bwyjdzjSubmit(HttpServletRequest request,String xmid,String activityId,String bwyjdbh,String yjfs,String yysj_vue,String type){
        UserEntity user = (UserEntity)request.getSession().getAttribute("user");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //这里的格式可以自己设置
        int flag = 0;
        CalculateHours ch = new CalculateHours();
        int direction = 0;
        try{
            //            查询任务办理人及手机号
            List<Map<String,Object>> rwblrList = service.getrwblr(xmid);
            if(rwblrList==null||rwblrList.size()==0){
                return -1;
            }else if (rwblrList.size()>1){
                return -2;
            }
            Map<String,Object> rwblr = rwblrList.get(0);
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
            task.setRwblr(user.getUserid());
            xkylxService.saveWfTask(task);

            //创建表位移交主表
            YjBwyjzb bwyjzbModel = new YjBwyjzb();
            bwyjzbModel.setId(GuidUtils.getGuid());
            bwyjzbModel.setBwyjdbh(getKey("BWYJ",0));
            bwyjzbModel.setXmid(xmid);
            bwyjzbModel.setBwyjdzt("0");
            bwyjzbModel.setYjfsbh(yjfs);
            bwyjzbModel.setCjr(user.getUserid());
            bwyjzbModel.setCjsj(date);
            bwyjzbModel.setYysj_vue(yysj_vue);
            bwyjzbModel.setQsysry((String)rwblr.get("USERID"));
            bwyjzbModel.setQsrysj((String)rwblr.get("USERSJ"));
            service.saveBwyjzbModel(bwyjzbModel);
//            根据项目id查询所有点志记id
            List<String> dzjList = new ArrayList<String>();
            dzjList = service.getDzjid(xmid);

            for(int i = 0;i<dzjList.size();i++){
                //创建表位移交从表
                YjBwyjcb bwyjcbModel = new YjBwyjcb();
                bwyjcbModel.setId(GuidUtils.getGuid());
                bwyjcbModel.setBwyjdid(bwyjzbModel.getId());
                bwyjcbModel.setDzjid(dzjList.get(i));
                bwyjcbModel.setShzt("0");
                bwyjcbModel.setZt("1");
                bwyjcbModel.setGfShzt("0");
                service.saveBwyjcbModel(bwyjcbModel);
            }

            //查询当前任务
            WfTaskEntity nowTask = sjstService.getNowTask(xmid, activityId);
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
            WfTaskEntity nexttask = getNextTaskEntity(xmid,activityId,direction);

            //根据环节编号查询环节定义数据
            WfDfActivityEntity activityEntity = sjstService.getActivityEntity(nexttask.getCActivityid());
            nexttask.setTaskid(GuidUtils.getGuid());
            nexttask.setIscurrent("1");//未完成
            nexttask.setCzr(user.getUserid());//操作人
            nexttask.setQssj(date);
            nexttask.setCheckUser(user.getUserid());
            nexttask.setIscheck("0");//未签收
            nexttask.setRefkey("YJ_BWYJZB");
            nexttask.setRefid((String)rwblr.get("USERID"));
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
            nexttask.setRwblr((String)rwblr.get("USERID"));
            nexttask.setHasnew("2");//无子流程
            xkylxService.saveWfTask(nexttask);
            //修改实例中流程定义id
            sjstService.updateInst(xmid, activityId, direction,bwyjzbModel.getBwyjdbh());
            //删除之前任务
            sjstService.deleteTask(nowTask.getTaskid());
            flag = 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
        //return true;
    }
    /**
     * 查询下一环节编号，流程实例，前置任务id
     * @param activitiId
     * @return
     */
    public WfTaskEntity getNextTaskEntity(String xmid,String activitiId,int direction){
        List<WfTaskEntity> list = new ArrayList<WfTaskEntity>();
        list = sjstService.getNextTaskEntity(xmid,activitiId,direction);
        return list.get(0);
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

//区所查询项目列表
@ApiOperation(value = "获取项目集合")
@RequestMapping(value = "getQsBwyjlb",method = RequestMethod.GET)
public String  getQsBwyjlb(HttpServletRequest request, ProjectModel model){
    HttpSession session = request.getSession();
    UserEntity user =  (UserEntity)session.getAttribute("user");
    model.setXmfzr(user.getUserid());
    List<ProjectModel> list = new ArrayList<ProjectModel>();
    int total = 0;
    try {
        model.setStartRow();
        model.setEndRow();
        total = service.getQsBwyjlbCount(model);
        if(total>0){
            list = service.getQsBwyjlbList(model);
        }
    }catch (Exception e){
        e.printStackTrace();
    }
    JSONObject jo = new JSONObject();
    jo.put("total",total);
    jo.put("row",list);
    return jo.toString();
}
//
    /**
     * 区所确认
     * @param xmid
     * @param activityId
     * @return
     */
    @ApiOperation(value = "区所确认")
    @RequestMapping(value = "qsSubmit",method = RequestMethod.GET)
    public boolean qsSubmit(String xmid,String type, String activityId,HttpServletRequest request) {
        UserEntity user = (UserEntity)request.getSession().getAttribute("user");
        Date date = new Date();
        boolean flag = false;
        int direction=0;
        try{
            //修改实例中流程定义id
//            service.updateInst(xmid, activityId,direction);
//            查询当前实例
            List<WfRuInstEntity> instList = sjstService.getInstList(xmid,activityId,direction,type);
            List<WfRuInstContentEntity> listWfRuInstContentEntity = sjstService.queryWfRuInstContentEntity(instList.get(0).getProcInstId());
            //将当前实例存入历史
            //service.pushWfRuInstToHis(xmid,activityId);
            WfHiInstEntity instEntity = new WfHiInstEntity();
            instEntity.setProcInstId(instList.get(0).getProcInstId());
            instEntity.setPdefId(instList.get(0).getPdefId());
            instEntity.setEndtime(date);
            instEntity.setProcstatus(instList.get(0).getProcstatus().toString());
            instEntity.setParammap(instList.get(0).getParammap());
            instEntity.setStartuser(instList.get(0).getStartuser());
            instEntity.setStartdt(instList.get(0).getStartdt());
            instEntity.setStopdt(instList.get(0).getStopdt());
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
            WfTaskEntity nowTask = sjstService.getNowTask(xmid, activityId);
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
            historyTask.setRefkey(nowTask.getRefkey());
            historyTask.setRefid(nowTask.getRefid());
            historyTask.setRwblr(nowTask.getRwblr());
            xkylxService.saveWfTaskHistory(historyTask);
            //删除之前任务
            sjstService.deleteTask(nowTask.getTaskid());
//            删除运行实例
            sjstService.deleteInst(instList.get(0).getProcInstId());
            sjstService.deleteInstContent(instList.get(0).getProcInstId());
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
