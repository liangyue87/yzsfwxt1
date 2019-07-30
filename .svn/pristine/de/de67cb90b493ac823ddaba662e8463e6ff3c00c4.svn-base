package com.ljx.hfgsjt.yj.controller;

import com.ljx.hfgsjt.entity.dto.Sjst.CodeModel;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.yj.XmXhs;
import com.ljx.hfgsjt.entity.yj.YjDzjzbFj;
import com.ljx.hfgsjt.sjst.service.SjstService;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import com.ljx.hfgsjt.util.utils.StringUtil;
import com.ljx.hfgsjt.yj.service.XhsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "XhsController")
@Api(description = "消火栓管理")
public class XhsController {

    @Autowired
    private XhsService service;
    @Autowired
    private SjstService sjstService;


    @ApiOperation(value = "获取消火栓列表集合")
    @RequestMapping(value = "getXhsData",method = RequestMethod.GET)
    public String  getXhsData(String xmid,Integer page, Integer size){
        JSONObject jo = new JSONObject();
        List<XmXhs> list = new ArrayList<XmXhs>();
        int total = 0;
        int startRow=0;
        int endRow=0;
        try {
            startRow = (page - 1) * size + 1;
            endRow = page * size;
            total = service.getXhsCount(xmid);
            if(total>0){
                list = service.getXhsData(xmid,startRow,endRow);
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
    @ApiOperation(value = "消火栓信息维护")
    @RequestMapping(value = "xhsxxwh",method = RequestMethod.GET)
    public boolean  xhsxxwh(HttpServletRequest request, XmXhs model){
        boolean flag = false;
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        Date date = new Date();
        try {
            if(StringUtil.isEmpty(model.getXhsbh())){
                String xhsbh = getKey("XHS",0);
                model.setXhsbh(xhsbh);
                model.setXhsyxbh(xhsbh);
                model.setId(GuidUtils.getGuid());
                model.setCzr(user.getUserid());
                model.setCzsj(date);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                model.setAzrq(sdf.parse(model.getAzrq_vue()));
                if(StringUtil.isEmpty(model.getBz())){
                    model.setBz("-");
                }
                model.setZt("1");
                service.insertXhs(model);
                flag=true;
            }else{
                model.setCzr(user.getUserid());
                model.setCzsj(date);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                model.setAzrq(sdf.parse(model.getAzrq_vue()));
                service.insertXhs(model);
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    @ApiOperation(value = "获取消火栓型号")
    @RequestMapping(value = "getXhsxh",method = RequestMethod.GET)
    public String  getXhsxh(HttpServletRequest request){
        JSONObject jo = new JSONObject();
        List<CodeModel> list = new ArrayList<CodeModel>();
        try {
            list = service.getXhsxh();
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "获取消火栓安装方式")
    @RequestMapping(value = "getXhsazfs",method = RequestMethod.GET)
    public String  getXhsazfs(HttpServletRequest request){
        JSONObject jo = new JSONObject();
        List<CodeModel> list = new ArrayList<CodeModel>();
        try {
            list = service.getXhsazfs();
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "消火栓数据回显")
    @RequestMapping(value = "xhspzHandle",method = RequestMethod.GET)
    public String  xhspzHandle(String id){
        JSONObject jo = new JSONObject();
        List<XmXhs> list = new ArrayList<XmXhs>();
        try {
            list = service.xhspzHandle(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "消火栓数据删除")
    @RequestMapping(value = "xhspzDelete",method = RequestMethod.GET)
    public boolean  xhspzDelete(String id){
        boolean flag = false;
        service.xhspzDelete(id);
        flag = true;
        return flag;
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
    /***
     * 文件上传
     * @param multipartFiles
     * @param filename
     * @param request
     * @return
     */
    @Transactional
    @RequestMapping(value ="saveDzjfile",method = RequestMethod.POST, consumes = { "multipart/mixed", "multipart/form-data" })
    @ResponseBody
    public Map importEnterPriseQuota(
            @RequestParam(value = "multipartFiles", required = false ) MultipartFile multipartFiles,
            @RequestParam(value = "filename", required = false ) String filename,
            @RequestParam(value = "dzjid", required = false ) String dzjid,
            @RequestParam(value = "fjlxej", required = false ) String fjlxej,
            @RequestParam(value = "xmid", required = false ) String xmid,
            @RequestParam(value = "fjnumber", required = false ) Integer  fjnumber,
            HttpServletRequest request) {

        Map resMap = new HashMap<String,Object>();
        if (Objects.isNull(multipartFiles)) {
            resMap.put("msg","文件为空，请重新上传");
            return resMap;
        }
        if(StringUtil.isEmpty(dzjid)){
            dzjid = GuidUtils.getGuid();
        }
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateString = formatter.format(currentTime);
        YjDzjzbFj model = new YjDzjzbFj();
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            if (Objects.isNull(multipartFiles)) {
                resMap.put("msg","文件为空，请重新上传");
                return resMap;
            }
            if(fjnumber>0){
                service.deleteDzjfj(dzjid,user.getUserid());
            }
            list = sjstService.getxmList(xmid);
            String fileDir = StringUtil.getBasePath()+"upload"+File.separator+list.get(0).get("XMBH")+File.separator+"yj"+File.separator;
            File dir = new File(fileDir);
            File file = null;

            file = new File(fileDir, filename.split("\\.")[0]+"_"+dateString+"."+filename.split("\\.")[filename.split("\\.").length-1]);
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

            model.setId(GuidUtils.getGuid());
            model.setDzjid(dzjid);
            model.setFjlj("upload"+File.separator+list.get(0).get("XMBH")+File.separator+"yj"+File.separator+filename.split("\\.")[0]+"_"+dateString+"."+filename.split("\\.")[filename.split("\\.").length-1]);
            model.setFjhz(filenames[filenames.length-1]);
            model.setFjmc(filename);
            model.setFjlx_yj("dzjzl");
            model.setFjlx_ej(fjlxej);
            model.setScsj(currentTime);
            model.setScr(user.getUserid());
            model.setSc_flag("0");
            service.saveFj(model);//将文件信息保存到附件表
        } catch (IOException e) {
            e.printStackTrace();
            resMap.put("returncode", "500");
            resMap.put("msg", "程序异常，请联系管理员");
        }
        resMap.put("returncode", "200");
        resMap.put("msg", "上传成功");
        resMap.put("path",model.getFjlj());
        resMap.put("dzjid",dzjid);
        return resMap;
    }

    @ApiOperation(value = "消火栓点志记数据")
    @RequestMapping(value = "xhsdzjData",method = RequestMethod.GET)
    public String  xhsdzjData(String id){
        List<XmXhs> list = new ArrayList<XmXhs>();
        JSONObject jo = new JSONObject();
        try {
            list = service.xhsdzjData(id);
        }catch (Exception e){

        }
        jo.put("row",list);
        return jo.toString();
    }

}
