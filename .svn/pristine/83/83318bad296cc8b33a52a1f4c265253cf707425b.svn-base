package com.ljx.hfgsjt.message.controller;

import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.dto.message.DxFsjlEntity;
import com.ljx.hfgsjt.entity.message.SendReq;
import com.ljx.hfgsjt.entity.message.SendRes;
import com.ljx.hfgsjt.message.service.MessageService;
import com.ljx.hfgsjt.util.utils.JSONUtils;
import com.ljx.hfgsjt.util.utils.Md5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping(value = "SendMessageController")
@Api(description = "短信接口")
public class SendMessageController {
    @Autowired
    private MessageService service;

    @Value("${message.apId}")
    private String apId;
    @Value("${message.secretKey}")
    private String secretKey;
    @Value("${message.ecName}")
    private String ecName;
    @Value("${message.sign}")
    private String sign;
    @Value("${message.url}")
    private String url;

    private String addSerial = "";

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
                    boolean result = false;
                    if(df.getJshm() != null && !df.getJshm().equals("")){
                        result = sendMessageTiming(df.getJshm(),fsnr);
                    }
                    DxFsjlEntity dxFsjlEntity = new DxFsjlEntity(df);
                    dxFsjlEntity.setFscs("1");
                    if(result){
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
     * @param phone
     * @param content
     * @return
     */
    public boolean sendMessageTiming(String phone, String content) throws IOException{
        SendReq sendReq = new SendReq();
        sendReq.setApId(apId);
        sendReq.setEcName(ecName);
        sendReq.setSecretKey(secretKey);
        sendReq.setContent(content);
        sendReq.setMobiles(phone);
        sendReq.setAddSerial(addSerial);
        sendReq.setSign(sign);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sendReq.getEcName());
        stringBuffer.append(sendReq.getApId());
        stringBuffer.append(secretKey);
        stringBuffer.append(sendReq.getMobiles());
        stringBuffer.append(sendReq.getContent());
        stringBuffer.append(sendReq.getSign());
        stringBuffer.append(sendReq.getAddSerial());
        sendReq.setMac(Md5Util.MD5(stringBuffer.toString()).toLowerCase());
        String reqText = JSONUtils.obj2json(sendReq);
        String encode = Base64.encodeBase64String(reqText.getBytes("UTF-8"));
        String resStr = sendPost(url,encode);
        System.out.println("发送短信结果："+resStr);
        SendRes sendRes = JSONUtils.json2pojo(resStr,SendRes.class);
        if(sendRes.isSuccess() && !"".equals(sendRes.getMsgGroup()) && "success".equals(sendRes.getRspcod())){
            return true;
        }else{
            return false;
        }
    }





























    /**
     * 多用户发送短信信息
     * @param mobiles 手机号码逗号分隔
     * @param content 短信内容
     * @return 返回1表示成功，0表示失败
     * @throws IOException
     */
    public int sendMsg(String mobiles,String content) throws IOException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDatestr = sdf.format(calendar.getTimeInMillis());
        //content += nowDatestr; //短信内容后跟个日期时间（可有可无），需求要求

        SendReq sendReq = new SendReq();
        sendReq.setApId(apId);
        sendReq.setEcName(ecName);
        sendReq.setSecretKey(secretKey);
        sendReq.setContent(content);
        sendReq.setMobiles(mobiles);
        sendReq.setAddSerial(addSerial);
        sendReq.setSign(sign);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sendReq.getEcName());
        stringBuffer.append(sendReq.getApId());
        stringBuffer.append(secretKey);
        stringBuffer.append(sendReq.getMobiles());
        stringBuffer.append(sendReq.getContent());
        stringBuffer.append(sendReq.getSign());
        stringBuffer.append(sendReq.getAddSerial());

        System.out.println(stringBuffer.toString());
        sendReq.setMac(Md5Util.MD5(stringBuffer.toString()).toLowerCase());
        System.out.println(sendReq.getMac());

//        String reqText = JSON.toJSONString(sendReq);
        String reqText = JSONUtils.obj2json(sendReq);
        System.out.println("reqtext1:"+reqText);
        //reqText = "{\"ecName\":\"合肥供水集团有限公司\",\"apId\":\"yizhanshixitong\",\"mobiles\":\"18655813105\",content\":\"这是发送短信的内容！2019-07-09 18:45:25\",\"sign\":\"1BIWrSgAl\",\"addSerial\":\"\",\"mac\":\""+sendReq.getMac()+"\"}";
//        reqText = "{'ecName':'"+sendReq.getEcName()+ "','apId':'"+sendReq.getApId()+"','mobiles':'"+sendReq.getMobiles()+"',content':'"+sendReq.getContent()+"','sign':'"+sendReq.getSign()+"','addSerial':'"+sendReq.getAddSerial()+"','mac':'"+sendReq.getMac()+"'}";
        String encode = Base64.encodeBase64String(reqText.getBytes("UTF-8"));
        System.out.println(encode);
//        encode = "eyJlY05hbWUiOiLlkIjogqXkvpvmsLTpm4blm6LmnInpmZDlhazlj7giLCJhcElkIjoieWl6aGFuc2hpeGl0b25nIiwibW9iaWxlcyI6IjE4NjU1ODEzMTA1IiwiY29udGVudCI6Iui/meaYr+WPkemAgeefreS/oeeahOWGheWuue+8gTIwMTktMDctMDkgMTg6NTU6NDYiLCJzaWduIjoiMUJJV3JTZ0FsIiwiYWRkU2VyaWFsIjoiIiwibWFjIjoiYTc3YmM2NjkxNGQ0ZjE5MjJhMTI4NDU0MGVkMTlmNDgifQ==";
        String resStr = sendPost(url,encode);

        System.out.println("发送短信结果："+resStr);

//        SendRes sendRes = JSON.parseObject(resStr,SendRes.class);
        SendRes sendRes = JSONUtils.json2pojo(resStr,SendRes.class);

        if(sendRes.isSuccess() && !"".equals(sendRes.getMsgGroup()) && "success".equals(sendRes.getRspcod())){
            return 1;
        }else{
            return 0;
        }
    }


    @RequestMapping(value = "sendMessage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "发送短信测试")
    public void sendMessage() throws IOException{
        String msg = "测试短信发送（2019-07-11）！";
        int result = sendMsg("18856960648",msg);
        System.out.println("==="+result);
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数
     * @return 所代表远程资源的响应结果
     */
    private String sendPost(String url, String param) {
        OutputStreamWriter out = null;

        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("contentType","utf-8");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            out = new OutputStreamWriter(conn.getOutputStream());
            out.write(param);
            out.flush();


            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;

    }

}
