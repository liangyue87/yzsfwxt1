package com.ljx.hfgsjt.yj.controller;

import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.yj.YjDzjcb;
import com.ljx.hfgsjt.entity.yj.YjDzjzb;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "SbsxController")
@Api(description = "水表三箱管理")
public class SbsxController {

    @Autowired
    private XhsService service;
    @Autowired
    private SjstService sjstService;


    @ApiOperation(value = "获取添加水表三箱点志记的下拉框数据")
    @RequestMapping(value = "getOptions",method = RequestMethod.GET)
    public String  getOptions(){
        JSONObject jo = new JSONObject();
        List<Map<String,String>> jgmc = service.getJgmcOptions();
        List<Map<String,String>> jgkl = service.getJgkjOptions();
        List<Map<String,String>> bqgcz = service.getBqgczOptions();
        jo.put("jgmc",jgmc);
        jo.put("jgkl",jgkl);
        jo.put("bqgcz",bqgcz);
        return jo.toString();
    }

    @ApiOperation(value = "获取添加水表三箱点志记")
    @RequestMapping(value = "getDzjSbsxxx",method = RequestMethod.GET)
    public String  getDzjSbsxxx(String id){
        JSONObject jo = new JSONObject();
        List<YjDzjcb> list = service.findDzjcbByZbid(id);
        YjDzjzb yjDzjzb = service.findDzjbByid(id);
        for(YjDzjcb cb : list){
            if(cb.getDzjcblxbh().equals("03")){
                yjDzjzb.setSbid(cb.getSbid());
                yjDzjzb.setGdkjbh(cb.getGdkjbh());
                yjDzjzb.setGdczbh(cb.getGdczbh());
            }else if(cb.getDzjcblxbh().equals("01")){
                yjDzjzb.setBqfid(cb.getFmid());
                yjDzjzb.setFbjjqf(cb.getFbjj());
            }else {
                yjDzjzb.setBhfid(cb.getFmid());
                yjDzjzb.setFbjjhf(cb.getFbjj());
            }
        }
        Map<String,String> sb = service.getSbxx(yjDzjzb.getSbid());
        Map<String,String> qf = service.getFmxx(yjDzjzb.getBqfid());
        Map<String,String> hf= service.getFmxx(yjDzjzb.getBhfid());
        jo.put("model",yjDzjzb);
        jo.put("sbbzh",sb.get("HH"));
        jo.put("qfbh",qf.get("FMBH"));
        jo.put("hfbh",hf.get("FMBH"));
        try {
            Map<String,String> file= service.getFilexx(yjDzjzb.getId());
            jo.put("path",file.get("FJLJ"));
            jo.put("fileName",file.get("FJMC"));
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @ApiOperation(value = "保存点记志")
    @RequestMapping(value = "saveDjz",method = RequestMethod.GET)
    public boolean  saveDjz(HttpServletRequest request, YjDzjzb model){
        boolean flag = false;
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        Date date = new Date();
        try {
            if(StringUtil.isEmpty(model.getDzjbh())){

                String dzjbh = getKey("DZJ",0);
                model.setDzjbh(dzjbh);
                if(model.getId() == null || model.getId().equals("")){
                    model.setId(GuidUtils.getGuid());
                }
                model.setCzr(user.getUserid());
                model.setCzrq(date);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                model.setAzrq(sdf.parse(model.getAzrq_vue()));
                if(model.getHtr() == null || model.getHtr().equals("")){
                    model.setHtr(user.getUsername());
                }
                service.insertDzjzb(model);
                //水表
                YjDzjcb cbsb = new YjDzjcb();
                cbsb.setDzjcblxbh("03");
                cbsb.setId(GuidUtils.getGuid());
                cbsb.setDzjzbid(model.getId());
                cbsb.setSbid(model.getSbid());
                cbsb.setGdczbh(model.getGdczbh());
                cbsb.setGdkjbh(model.getGdkjbh());
                service.insertDzjcb(cbsb);
                //前阀
                YjDzjcb cbqf = new YjDzjcb();
                cbqf.setDzjcblxbh("01");
                cbqf.setId(GuidUtils.getGuid());
                cbqf.setDzjzbid(model.getId());
                cbqf.setFbjj(model.getFbjjqf());
                cbqf.setFmid(model.getBqfid());
                service.insertDzjcb(cbqf);
                //后阀
                YjDzjcb cbhf = new YjDzjcb();
                cbhf.setDzjcblxbh("02");
                cbhf.setId(GuidUtils.getGuid());
                cbhf.setDzjzbid(model.getId());
                cbhf.setFbjj(model.getFbjjhf());
                cbhf.setFmid(model.getBhfid());
                service.insertDzjcb(cbhf);
                flag=true;
            }else{
                YjDzjcb cbModel = new YjDzjcb();
                model.setCzr(user.getUserid());
                model.setCzrq(date);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                model.setAzrq(sdf.parse(model.getAzrq_vue()));
                if(model.getHtr() == null || model.getHtr().equals("")){
                    model.setHtr(user.getUsername());
                }
                service.insertDzjzb(model);
                List<YjDzjcb> list = service.findDzjcbByZbid(model.getId());
                String cbsbid;
                String cbqfid;
                String cbhfid;
                for(YjDzjcb cb : list){
                    if(cb.getDzjcblxbh().equals("03")){
                        cbsbid = cb.getId();
                    }else if(cb.getDzjcblxbh().equals("01")){
                        cbqfid = cb.getId();
                    }else {
                        cbhfid = cb.getId();
                    }
                }
                //水表
                YjDzjcb cbsb = new YjDzjcb();
                cbsb.setDzjcblxbh("03");
                cbsb.setId(GuidUtils.getGuid());
                cbsb.setDzjzbid(model.getId());
                cbsb.setSbid(model.getSbid());
                service.insertDzjcb(cbsb);
                //前阀
                YjDzjcb cbqf = new YjDzjcb();
                cbqf.setDzjcblxbh("01");
                cbqf.setId(GuidUtils.getGuid());
                cbqf.setDzjzbid(model.getId());
                cbqf.setFbjj(model.getFbjjqf());
                cbqf.setFmid(model.getBqfid());
                service.insertDzjcb(cbqf);
                //后阀
                YjDzjcb cbhf = new YjDzjcb();
                cbhf.setDzjcblxbh("02");
                cbhf.setId(GuidUtils.getGuid());
                cbhf.setDzjzbid(model.getId());
                cbhf.setFbjj(model.getFbjjhf());
                cbhf.setFmid(model.getBhfid());
                service.insertDzjcb(cbhf);
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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
    @RequestMapping(value ="saveDzjfile",method = RequestMethod.POST, consumes = { "multipart/mixed", "multipart/form-data" })
    @ResponseBody
    public Map importEnterPriseQuota(
            @RequestParam(value = "multipartFiles", required = false ) MultipartFile multipartFiles,
            @RequestParam(value = "filename", required = false ) String filename,
            @RequestParam(value = "dzjid", required = false ) String dzjid,
            @RequestParam(value = "xmid", required = false ) String xmid,
            HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        List<Map<String,Object>> list = new ArrayList<>();
        Map resMap = new HashMap<String,Object>();
        if (Objects.isNull(multipartFiles)) {
            resMap.put("msg","文件为空，请重新上传");
            return resMap;
        }
        if(dzjid == null || dzjid.equals("")){
            dzjid = GuidUtils.getGuid();
        }else {
            service.deleteDzjfj(dzjid,user.getUserid());
        }
        String newFilename = "";
        list = sjstService.getxmList(xmid);
        String fileDir = StringUtil.getBasePath()+"upload"+File.separator+list.get(0).get("XMBH")+"yj"+File.separator;
        File dir = new File(fileDir);
        YjDzjzbFj model = new YjDzjzbFj();
        File file = null;
        try {
            list = sjstService.getxmList(xmid);
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

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String str_date="_"+df.format(new Date());
             newFilename=filenames[0]+str_date+"."+filenames[filenames.length-1];//加时间后缀
            file.renameTo(new File(fileDir + newFilename));//重命名

            Date date = new Date();

            model.setId(GuidUtils.getGuid());//id
            model.setDzjid(dzjid);//点记志id
            model.setFjlj("upload"+File.separator+list.get(0).get("XMBH")+"yj"+File.separator+newFilename);//附件路径
            model.setFjmc(filename);//文件名称
            model.setFjhz(filenames[filenames.length-1]);//文件后缀
            model.setFjlx_yj("dzjzl");
            model.setFjlx_ej("sbsxdzj");
            model.setScr(user.getUserid());//上传人
            model.setScsj(date);//上传时间
            service.saveFj(model);//将文件信息保存到附件表
        } catch (IOException e) {
            e.printStackTrace();
            resMap.put("returncode", "500");
            resMap.put("msg", "程序异常，请联系管理员");
        }
        resMap.put("returncode", "200");
        resMap.put("msg", "上传成功");
        resMap.put("path",model.getFjlj());
        resMap.put("fileName",filename);
        resMap.put("dzjid",dzjid);
        return resMap;
    }
    /**
     * 下载用户资料
     * @param path
     * @param response
     * @return
     */
    @ApiOperation(value = "下载用户资料")
    @RequestMapping(value = "download",method = RequestMethod.POST)
    @ResponseBody
    public HttpServletResponse download(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            String basePath = StringUtil.getBasePath();
            // path是指欲下载的文件的路径。
            //path = basePath+path;
            File file = new File(basePath+path);
            // 取得文件名
            String filename = file.getName();
            // 取得文件的后缀名
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件
            InputStream fis = new BufferedInputStream(new FileInputStream(basePath+path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            //response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            //response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
            //response.setHeader("Access-Control-Allow-Credentials", "true");
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
}
