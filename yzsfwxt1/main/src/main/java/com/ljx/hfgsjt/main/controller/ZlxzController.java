package com.ljx.hfgsjt.main.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljx.hfgsjt.util.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "ZlxzController")
public class ZlxzController {


    @RequestMapping(value = "getFileList",method = RequestMethod.GET)
    @ResponseBody
    public String getFileList(HttpServletResponse response){
        JSONObject jo = new JSONObject();
        List<Map<String,String>> list = getFileList();
        jo.put("row",list);
        return jo.toString();
    }

    @RequestMapping(value = "download",method = RequestMethod.POST)
    @ResponseBody
    public HttpServletResponse download(String path,HttpServletResponse response){
        try {
            String basePath = StringUtil.getBasePath();
            // path是指欲下载的文件的路径。
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


    public List<Map<String,String>> getFileList() {
        List<Map<String,String>> list = new ArrayList<>();
        try {
            String basePath = StringUtil.getBasePath()+ "download";
            File file = new File(basePath);
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                Map<String,String> map = new HashMap<>();
                if (fileList[i].isFile()) {
                    String fileName = fileList[i].getName();
                    System.out.println("文件：" + fileName);
                    String path = "download\\" + fileName;
                    map.put("filename",fileName);
                    map.put("path",path);
                    list.add(map);
                }
                if (fileList[i].isDirectory()) {
                    String fileName = fileList[i].getName();
                    System.out.println("目录：" + fileName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}
