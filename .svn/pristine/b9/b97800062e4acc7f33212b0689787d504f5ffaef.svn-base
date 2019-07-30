package com.ljx.hfgsjt.message.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.dto.message.DxFsjlEntity;
import com.ljx.hfgsjt.message.service.MessageService;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "MessageController")
@Api(description = "短信接口")
public class MessageController {
    @Autowired
    private MessageService service;

    @Value("${message.authcode}")
    private String authcode;
    @Value("${message.url_old}")
    private String url;
    @Value("${message.ywsysid}")
    private String ywsysid;

    /**
     * 短信发送
     * @param mstype 短信类型
     * @param dsflag 是否定时发送,1: 立即发送（必须删除dstime的设置） 2：定时发送；在定义为2时，dstime 必须设置时间，否则，立即发送；
     * @param dstime 定时时间，格式：yyyy-MM-dd hh24:mi:ss;
     * @param phone 短信发送号码
     * @param content 短信内容
     * @return
     */
    @RequestMapping(value = "sendMessage", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "短信发送")
    public String sendMessage(String mstype, String dsflag, String dstime, String phone, String content) {
        String result = "fail";
        DxFsjlEntity dxFsjlEntity = new DxFsjlEntity();//短信发送记录
        dxFsjlEntity.setId(GuidUtils.getGuid());
        dxFsjlEntity.setFssj(new Date());
        dxFsjlEntity.setFsnr(content);
        dxFsjlEntity.setJshm(phone);
        dxFsjlEntity.setFscs("1");
        // 1. 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2. 创建HttpPost对象
        HttpPost post = new HttpPost(url);
        //post.addHeader("Content-type", "application/json; charset=utf-8");
        post.setHeader("Content-type", "application/json; charset=utf-8");
        //3. 设置POST请求传递参数
        JSONObject params = new JSONObject();
        JSONObject head = new JSONObject();
        JSONObject body = new JSONObject();
        List<String> listphone = new ArrayList<>();
        listphone.add(phone);
        head.put("authcode", authcode);
        head.put("ywsysid", ywsysid);
        head.put("mstype", mstype);
        head.put("dsflag", "1");
        params.put("head", head);
        List<JSONObject> datas = new ArrayList();
        JSONObject data = new JSONObject();
        data.put("mobileno", listphone);
        data.put("content", content);
        datas.add(data);
        body.put("datas", datas);
        params.put("body", body);
        try {
            StringEntity entity = new StringEntity(params.toString(),"UTF-8");
            //entity.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            post.setEntity(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 4. 执行请求并处理响应
        try {
            CloseableHttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            String res = EntityUtils.toString(entity);
            if (entity != null) {
                System.out.println("1响应内容：" + res);
                JSONObject json = JSONObject.parseObject(res);
                if(json.get("state").toString().equals("ok")){
                    result = "success";
                    dxFsjlEntity.setSfcg("1");
                }else {
                    dxFsjlEntity.setSfcg("0");
                }
            }
            response.close();
        } catch (ClientProtocolException e) {
            dxFsjlEntity.setSfcg("0");
            e.printStackTrace();
        } catch (IOException e) {
            dxFsjlEntity.setSfcg("0");
            e.printStackTrace();
        } finally {
            // 释放资源
            try {
                httpClient.close();
            } catch (IOException e) {
                dxFsjlEntity.setSfcg("1");
                e.printStackTrace();
            }
        }
        service.saveDxFsjl(dxFsjlEntity);//保存到发送记录
        return result;
    }

    /***
     *
     * 代码调用短信发送方法
     * @param mstype 短信类型
     * @param dsflag 是否定时发送,1: 立即发送（必须删除dstime的设置） 2：定时发送；在定义为2时，dstime 必须设置时间，否则，立即发送；
     * @param dstime 定时时间，格式：yyyy-MM-dd hh24:mi:ss;
     * @param phone 短信发送号码
     * @param content 短信内容
     * @param fsr 发送人id
     * @param dxmbid 短信模板id
     * @param jsr 接收人id
     * @return
     */
    public String sendMsg(String mstype, String dsflag, String dstime, String phone, String content,String fsr, String dxmbid,String jsr) {
        String result = "fail";
        DxFsjlEntity dxFsjlEntity = new DxFsjlEntity();//短信发送记录
        dxFsjlEntity.setId(GuidUtils.getGuid());
        dxFsjlEntity.setFssj(new Date());
        dxFsjlEntity.setFsnr(content);
        dxFsjlEntity.setJshm(phone);
        dxFsjlEntity.setFscs("1");
        dxFsjlEntity.setFsr(fsr);
        dxFsjlEntity.setDxmbid(dxmbid);
        dxFsjlEntity.setJsr(jsr);
        dxFsjlEntity.setJshm(phone);
        // 1. 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2. 创建HttpPost对象
        HttpPost post = new HttpPost(url);
        //post.addHeader("Content-type", "application/json; charset=utf-8");
        post.setHeader("Content-type", "application/json; charset=utf-8");
        //3. 设置POST请求传递参数
        JSONObject params = new JSONObject();
        JSONObject head = new JSONObject();
        JSONObject body = new JSONObject();
        List<String> listphone = new ArrayList<>();
        listphone.add(phone);
        head.put("authcode", authcode);
        head.put("ywsysid", ywsysid);
        head.put("mstype", mstype);
        head.put("dsflag", "1");
        params.put("head", head);
        List<JSONObject> datas = new ArrayList();
        JSONObject data = new JSONObject();
        data.put("mobileno", listphone);
        data.put("content", content);
        datas.add(data);
        body.put("datas", datas);
        params.put("body", body);
        try {
            StringEntity entity = new StringEntity(params.toString(),"UTF-8");
            //entity.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            post.setEntity(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 4. 执行请求并处理响应
        try {
            CloseableHttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            String res = EntityUtils.toString(entity);
            if (entity != null) {
                System.out.println("1响应内容：" + res);
                JSONObject json = JSONObject.parseObject(res);
                if(json.get("state").toString().equals("ok")){
                    result = "success";
                    dxFsjlEntity.setSfcg("1");
                }else {
                    dxFsjlEntity.setSfcg("0");
                }
            }
            response.close();
        } catch (ClientProtocolException e) {
            dxFsjlEntity.setSfcg("0");
            e.printStackTrace();
        } catch (IOException e) {
            dxFsjlEntity.setSfcg("0");
            e.printStackTrace();
        } finally {
            // 释放资源
            try {
                httpClient.close();
            } catch (IOException e) {
                dxFsjlEntity.setSfcg("0");
                e.printStackTrace();
            }
        }
        service.saveDxFsjl(dxFsjlEntity);//保存到发送记录
        return result;
    }

    /**
     * 查询所有待发送短信并发送，记录发送记录
     * @return
     */
    public void getDxDfsListAndSend(){
        try {
            List<DxDfsEntity> list = service.getDxDfsList();
            if(list.size() != 0){
                for (DxDfsEntity df : list){
                    String fsnr = df.getFsnr();
                    //List<String> jshm = new ArrayList<String>();
                    //jshm.add(df.getJshm());
                    String result = "";
                    if(df.getJshm() != null && !df.getJshm().equals("")){
                        result = sendMessageTiming("002","1",null,df.getJshm(),fsnr);
                    }
                    DxFsjlEntity dxFsjlEntity = new DxFsjlEntity(df);
                    dxFsjlEntity.setFscs("1");
                    if(result.equals("success")){
                        dxFsjlEntity.setSfcg("1");
                    }else {
                        dxFsjlEntity.setSfcg("0");
                    }
                    service.saveDxFsjl(dxFsjlEntity);
                    service.deleteDxDfsById(df.getId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 定时器发送短信调用接口
     * @param mstype
     * @param dsflag
     * @param dstime
     * @param phone
     * @param content
     * @return
     */
    public String sendMessageTiming(String mstype, String dsflag, String dstime,String phone, String content) {
        String result = "fail";
        // 1. 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2. 创建HttpPost对象
        HttpPost post = new HttpPost(url);
        //post.addHeader("Content-type", "application/json; charset=utf-8");
        post.setHeader("Content-type", "application/json; charset=utf-8");
        //3. 设置POST请求传递参数
        JSONObject params = new JSONObject();
        JSONObject head = new JSONObject();
        JSONObject body = new JSONObject();
        List<String> listphone = new ArrayList<>();
        listphone.add(phone);
        head.put("authcode", authcode);
        head.put("ywsysid", ywsysid);
        head.put("mstype", mstype);
        head.put("dsflag", "1");
        params.put("head", head);
        List<JSONObject> datas = new ArrayList();
        JSONObject data = new JSONObject();
        data.put("mobileno", listphone);
        data.put("content", content);
        datas.add(data);
        body.put("datas", datas);
        params.put("body", body);
        try {
            StringEntity entity = new StringEntity(params.toString(),"UTF-8");
            //entity.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            post.setEntity(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 4. 执行请求并处理响应
        try {
            CloseableHttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                System.out.println("响应内容：");
                System.out.println(EntityUtils.toString(entity));
                JSONObject json = (JSONObject)JSONObject.parse(EntityUtils.toString(entity));
                if(json.get("state").toString().equals("ok")){
                    result = "success";
                }
            }
            response.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void dxTimer(){
        service.dxTimer(GuidUtils.getGuid());
    }

}
