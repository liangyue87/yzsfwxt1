package com.ljx.hfgsjt.htqd.controller;


import com.alibaba.fastjson.JSONObject;
import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.CodeEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.htqd.*;
import com.ljx.hfgsjt.htqd.service.HtbzService;
import com.ljx.hfgsjt.sgjg.service.SgxdService;
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
import java.io.BufferedReader;
import java.io.File;
import java.io.Reader;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 合同编制控制层
 */
@Controller
@RequestMapping(value = "HtbzController")
@Api(description = "合同编制")
public class HtbzController {

    @Autowired
    private HtbzService service;

    @Autowired
    private SgxdService sgxdService;

    @Autowired
    private XkylxService xkylxservice;

    private Logger logger = LoggerFactory.getLogger(HtbzController.class);


    /**
     * 保存合同
     * @return
     */
    @ApiOperation("保存合同")
    @RequestMapping(value ="add_htbz",method = RequestMethod.GET)
    @ResponseBody
    public String add_htbz(HttpServletRequest request, HttpServletResponse response){
        String flag = "ok";
        try {
            String xmbh = request.getParameter("xmbh");
            String aa = "1";
        }catch(Exception e){
            flag = "err";
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 根据项目编号查询项目信息
     * @return
     */
    @ApiOperation("项目编号查询项目信息")
    @RequestMapping(value ="getXmBybh",method = RequestMethod.GET)
    @ResponseBody
    public String getXmBybh(HttpServletRequest request, HttpServletResponse response){
        JSONObject jo = new JSONObject();
        try {
            String xmbh = request.getParameter("xmbh");
            //根据项目编号查询项目信息
//            List<XmInstanceEntity> varList = service.findByXmbh(xmbh);
            List<GetHtxmEntity> varList = service.findXmxxByXmbh(xmbh);
            if(varList.isEmpty() == false){
                /*String mbbm = varList.get(0).getSqlx();*/
                String mbbm ="1";
                //根据模板编号查询模板信息
                List<XtglHtmbEntity> mList = service.findByMbbm(mbbm);
                String mbnr = "";
                if(mList.isEmpty() == false){
                    Clob sc = mList.get(0).getMbnr();
                    Reader is = sc.getCharacterStream();// 得到流
                    BufferedReader br = new BufferedReader(is);
                    String s = br.readLine();
                    StringBuffer sb = new StringBuffer();
                    while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
                        sb.append(s);
                        s = br.readLine();
                    }
                    mbnr = sb.toString();
                }
                jo.put("mbnr",mbnr);
                jo.put("list",varList.get(0));
            }else{
                jo.put("list","");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 造价洽谈反馈意见上传
     * @param multipartFiles
     * @param filename
     * @return
     */
    @ApiOperation("合同附件上传")
    @RequestMapping(value ="upoadFile",method = RequestMethod.POST, consumes = { "multipart/mixed", "multipart/form-data" })
    @ResponseBody
    public String upoadFile(
            @RequestParam(value = "multipartFiles", required = false ) MultipartFile multipartFiles,
            @RequestParam(value = "htzbid", required = false ) String htzbid,
            @RequestParam(value = "filename", required = false ) String filename,
            @RequestParam(value = "xmid", required = false ) String xmid,HttpServletRequest request) {
        String id = "";
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        org.activiti.engine.impl.util.json.JSONObject jo = new org.activiti.engine.impl.util.json.JSONObject();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateString = formatter.format(currentTime);
        try {
            if (Objects.isNull(multipartFiles)) {
                logger.error("文件为空");
                jo.put("msg","文件为空，请重新上传");
                return jo.toString();
            }
            List<XmInstance> xmxxList = service.findXmxxByxmid(xmid);
            String xmbh = xmxxList.get(0).getXmbh();
            String fileDir = StringUtil.getBasePath()+"upload"+File.separator+xmbh+File.separator;
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
            pd.put("id", id);//主键标识
            pd.put("htqdid",htzbid);//合同主表ID
            pd.put("fjlx_yj","htqd");//一级附件类型
            pd.put("fjlx_ej","86c0fe78eb204bb5a45c86c0fe78eeee");//二级附件类型
            pd.put("fjlj","upload"+File.separator+xmbh+File.separator+filename.split("\\.")[0]+dateString+"."+filename.split("\\.")[filename.split("\\.").length-1]);//附件路径
            pd.put("fjhz",filenames[filenames.length-1]);//附件后缀
            pd.put("fjmc",filename);//附件名称
            pd.put("scr",user.getUserid());//上传人
            service.upoadFile(pd);
        }catch (Exception e){
            e.printStackTrace();
            jo.put("returncode", "999");
            jo.put("msg", "程序异常，请联系管理员");
        }
        jo.put("msg", "上传成功");
        jo.put("fileId",id);
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

    @ApiOperation(value = "条件查询合同编制列表")
    @RequestMapping(value = "getHtbzList",method = RequestMethod.POST)
    @ResponseBody
    public String getJfList(String xmbh,String xmmc,String lxr,String xmdz,String azlx,String beginTime,String endTime,
                            Integer page,Integer size,String jsdw,String jfdw,String sfbz,String azlxname,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        pd.put("xmfzr",user.getUserid());
        pd.put("xmbh",xmbh);
        pd.put("xmmc",xmmc);
        pd.put("lxr",lxr);
        pd.put("xmdz",xmdz);
        pd.put("azlx",azlx);
        pd.put("beginTime",beginTime);
        pd.put("endTime",endTime);
        pd.put("jsdw",jsdw);
        pd.put("jfdw",jfdw);
        pd.put("azlxname",azlxname);
        pd.put("page",page);
        pd.put("size",size);
        pd.put("sfbz",sfbz);
        try{
            List<GetXmlbHtEntity> list = service.getHtList(pd);
            int totol = service.getAllHtList(pd).size();
            jo.put("list",list);
            jo.put("total", totol);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("根据项目ID查询合同单")
    @RequestMapping(value = "queryHtbz",method = RequestMethod.POST)
    @ResponseBody
    public String queryHtbz(String xmid){
        JSONObject jo = new JSONObject();
        try{
            List<Map<String,Object>> list = service.queryHtbz(xmid);
            if(list.isEmpty() == false){
                String gcjsfs = String.valueOf(list.get(0).get("GCJSFS"));
                List<String> jsvalue = new ArrayList<>();
                String[] varList = gcjsfs.split("@#");
                for (int i=0;i<varList.length;i++){
                    String[] bList = varList[i].split("%%");
                    jsvalue.add(bList[0]);
                }
                jo.put("list",list);
                jo.put("isEdit",1);
                jo.put("jsvalue",jsvalue);
            }else{
                jo.put("isEdit",0);
            }
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
    public String queryZllist(String htzbid,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        try{
            pd.put("htzbid",htzbid);
            List<Map<String,Object>> fjList = service.queryZjqtfjById(pd);
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
            service.deleteFjByid(pd);
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation("根据项目ID查询项目基本信息")
    @RequestMapping(value = "queryxmxxByid",method = RequestMethod.GET)
    @ResponseBody
    public String queryxmxxByid(String xmid,String isEdit){
        JSONObject jo = new JSONObject();
        try{
            //项目基本信息
            List<XmInstanceEntity> list = service.queryxmxxByid(xmid);
            //合同编号
            Map<String, Object> map = new HashMap<String, Object>();
            String ss="HTBZ";
            map.put("v_stop_run",ss);
            service.texuChange(map);//获取任务编号
            //乙方开户行信息
            List<Map<String,Object>> gskhyhList = service.queryYjsfzh();
            jo.put("list",list);
            jo.put("htbh","KF"+map.get("p_result"));
            jo.put("gskhyhList",gskhyhList);
            jo.put("htzbid",GuidUtils.getGuid());
            //结算方式
            List<JsfsEntity> jsfsList = service.queryJsfs();
            List<Map<String,Object>> varlist = service.queryHtbz(xmid);
            if("1".equals(isEdit)) {
                if(varlist.isEmpty() == false){
                    String gcjsfs = String.valueOf(varlist.get(0).get("GCJSFS"));
                    String[] aList = gcjsfs.split("@#");
                    for (int i=0;i<aList.length;i++){
                        String[] bList = aList[i].split("%%");
                        String bz = "";
                        String bh = bList[0];
                        if(bList.length == 1){
                            bz = "";
                        }else{
                            if(bList[1].equals("undefined")){
                                bz = "";
                            }else{
                                bz = bList[1];
                            }
                        }
                        for (int j=0;j<jsfsList.size();j++){
                            if(bh.equals(jsfsList.get(j).getBh())){
                                jsfsList.get(j).setBz(bz);
                            }
                        }
                    }
                }
            }
            jo.put("jsfsList",jsfsList);
            //项目分类码
            if(list.isEmpty() == false){
                String type = list.get(0).getSqlx();
                List<CodeEntity> xmflm = xkylxservice.getXmCodeCheckbox("项目分类码",type);
                jo.put("xmflm",xmflm);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("查询预算项分类")
    @RequestMapping(value = "queryYslist",method = RequestMethod.GET)
    @ResponseBody
    public String queryYslist(String xmid){
        JSONObject jo = new JSONObject();
        try{
            List<Map<String,Object>> ysList = service.queryYslist(xmid);
            jo.put("ysList",ysList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("合同保存")
    @RequestMapping(value = "saveHt",method = RequestMethod.POST)
    @ResponseBody
    public String saveHt(String htzbid,String xmid,String htbh,String gczje,String zffs,String fqs,
                         String editzbid,String isEdit,String xmlb,String Subjsfs,HttpServletRequest request){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            pd.put("id",htzbid);
            pd.put("xmid",xmid);
            pd.put("htbh",htbh);
            String htqdsj = request.getParameter("htqdsj");
            if("".equals(htqdsj) || htqdsj == null){
                pd.put("htqdsj","");
            }else{
                pd.put("htqdsj",sdf.parse(request.getParameter("htqdsj")));
            }
            pd.put("gqzrlts",request.getParameter("zrlts"));
            pd.put("jfdw",request.getParameter("jfdw"));
            pd.put("gcmc",request.getParameter("gcmc") == null ? "" :request.getParameter("gcmc"));
            pd.put("gcdz",request.getParameter("gcdz") == null ? "" :request.getParameter("gcdz"));
            pd.put("jfkhh",request.getParameter("jfkhh") == null ? "" :request.getParameter("jfkhh"));
            pd.put("yhzh",request.getParameter("jfzh") == null ? "" :request.getParameter("jfzh"));
            pd.put("bzrbh",user.getUserid());
            pd.put("gczje",gczje);
            pd.put("gcjsfs",Subjsfs == null ? "" :Subjsfs);
            pd.put("zffs",zffs);//支付方式
            pd.put("fqs",fqs);//分期数
            String qrsj = request.getParameter("hqtgsj");
            if("".equals(qrsj) || qrsj == null){
                pd.put("qrsj","");
            }else{
                pd.put("qrsj",sdf.parse(request.getParameter("hqtgsj")));
            }

            //项目类别存入项目主表
            pd.put("xmlb",xmlb == null ? "" : xmlb);
            service.updateXmlbtoXMb(pd);
            //合同主表保存
            if("1".equals(isEdit)){
                pd.put("editzbid",editzbid);
                service.updateHt(pd);
                service.deleteHtfy(pd);
            }else{
                service.saveHt(pd);
            }

            //合同费用保存
            Integer count = Integer.valueOf(request.getParameter("counts"));
            Map<String,Object> cbpd = new HashMap<>();
            for(int i=0;i<count;i++){
                String ysje_cs = "ysje"+(i+1);
                String ysfl_cs = "ysfl"+(i+1);
                String bz_cs = "bz"+(i+1);
                cbpd.put("id",GuidUtils.getGuid());
                cbpd.put("htzbid",htzbid);
                cbpd.put("htje",request.getParameter(ysje_cs));
                cbpd.put("htfl",request.getParameter(ysfl_cs));
                String bz = request.getParameter(bz_cs);
                if(bz == null){
                    bz = "";
                }
                cbpd.put("bz",bz);
                service.savefycb(cbpd);
            }

            if("1".equals(isEdit)){

            }else{
                WfTaskEntity task = new WfTaskEntity();
                String procInstId = GuidUtils.getGuid();
                String pdefId = "50001";
                WfRuInstEntity instEntity = new WfRuInstEntity();
                WfRuInstContentEntity wfRuInstContentEntity = new WfRuInstContentEntity();

                //启动流程实例并放入历史
                instEntity.setProcInstId(procInstId);
                instEntity.setPdefId(pdefId);
                instEntity.setParammap(htbh);
                instEntity.setStartdt(new Date());
                instEntity.setProcstatus(1);
                instEntity.setStartuser(user.getUserid());
                WfHiInstEntity hiInstEntity = new WfHiInstEntity(instEntity);
                hiInstEntity.setEndtime(new Date());
                sgxdService.savewfHiInst(hiInstEntity);

                //将项目信息存入流程实例使用内容表(WF_RU_INST_CONTENT)并放入历史
                wfRuInstContentEntity.setContentid(GuidUtils.getGuid());
                wfRuInstContentEntity.setProcInstId(procInstId);
                wfRuInstContentEntity.setTableName("XM_INSTANCE");
                wfRuInstContentEntity.setContentvalue(xmid);
                wfRuInstContentEntity.setContenttype("HT_QDZB");
                wfRuInstContentEntity.setContentname(htzbid);
                WfHiInstContentEntity wfHiInstContentEntity = new WfHiInstContentEntity(wfRuInstContentEntity);
                sgxdService.saveWfHiInstContent(wfHiInstContentEntity);

                //创建任务，存入历史任务表中
                String taskId = GuidUtils.getGuid();
                task.setTaskid(taskId);
                task.setQssj(new Date());//起始时间
                task.setJssj(new Date());//结束时间
                task.setPtaskid("0");//前置任务为空
                task.setProcInstId(procInstId);//流程实例ID
                task.setCActivityid(pdefId);//环节编号
                task.setIscurrent("0");//已完成
                task.setCzr(user.getUserid());//操作人
                task.setIscheck("1");//已签收
                task.setHasnew("2");//无子流程
                task.setRwblr(user.getUserid());//任务办理人
                WfTaskHistoryEntity historyEntity = new WfTaskHistoryEntity(task);
                sgxdService.saveWfTaskHistory(historyEntity);
            }

            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation("查询分期支付金额设置")
    @RequestMapping(value = "findFqje",method = RequestMethod.GET)
    @ResponseBody
    public String findFqje(String htzbid, HttpServletRequest request){
        JSONObject jo = new JSONObject();
        List<FqbEntity> list = new ArrayList<>();
        list = service.findFqb(htzbid);
        for(FqbEntity fqb : list){
            FqmxbEntity fm = new FqmxbEntity();
            List<FqmxbEntity> lli = new ArrayList<>();
            lli.add(fm);
            List<FqmxbEntity> li = service.findFqmxb(fqb.getId());
            lli.addAll(li);
            fqb.setFqmx(lli);
        }
        Collections.sort(list);//排序
        jo.put("fqxx",list);
        return jo.toString();
    }

    @ApiOperation("保存分期支付金额设置")
    @RequestMapping(value = "saveFqje",method = RequestMethod.POST)
    @ResponseBody
    public String saveFqje(String datas, String htzbid, HttpServletRequest request){
        JSONObject jsonObject = JSONObject.parseObject(datas);
        List<FqbEntity> list = new ArrayList<>();
        for(int i = 1;;i++){//数据格式化
            if(jsonObject.get("zje"+i) != null){
                FqbEntity f = new FqbEntity();
                String fqzbid = GuidUtils.getGuid();
                f.setId(fqzbid);
                f.setHtzbid(htzbid);
                f.setZje(jsonObject.get("zje"+i).toString());
                f.setPerce(jsonObject.get("perce"+i).toString());
                f.setQs(""+i);
                JSONObject jo = (JSONObject)jsonObject.get("fqmx"+i);
                List<FqmxbEntity> li = new ArrayList<>();
                for(int j = 2;;j++){//从2开始
                    if(jo.get("fqje"+j) !=null){
                        FqmxbEntity fy = new FqmxbEntity();
                        fy.setId(GuidUtils.getGuid());
                        fy.setFqzbid(fqzbid);
                        fy.setFqfl(jo.get("ysfl"+j).toString());
                        fy.setMc(jo.get("mc"+j).toString());
                        fy.setFqje(jo.get("fqje"+j).toString());
                        fy.setFqperce(jo.get("fqperce"+j).toString());
                        li.add(fy);
                    }else {
                        break;
                    }
                }
                f.setFqmx(li);
                list.add(f);
            }else{
                break;
            }
        }

        //删除之前设置的分期支付金额数据
        List<FqbEntity> ls = service.getFqbByHtzbid(htzbid);
        if(ls != null){
            for(FqbEntity f : ls){
                service.deleteFqmxbByFqzbid(f.getId());
            }
            service.deleteFqbByHtzbid(htzbid);
        }
        //将新的分期支付金额设置保存到数据库
        for(FqbEntity f : list){
            service.saveFqb(f);
            for(FqmxbEntity b : f.getFqmx()){
                service.saveFqbmx(b);
            }
        }

        return "";
    }

}
