package com.ljx.hfgsjt.main.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljx.hfgsjt.entity.dto.BankEntity;
import com.ljx.hfgsjt.main.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "BankController")
public class BankController {

    @Autowired
    private BankService service;

    //分页查询
    @RequestMapping(value = "getBankList",method = RequestMethod.GET)
    @ResponseBody
    public String getBankList(Integer page, Integer size,String search_yhmc, HttpServletResponse response){
        List<BankEntity> list = service.getBankList(page, size,search_yhmc);
        int total = service.getAllBankLists(search_yhmc).size();
        List<Map<String,String>> varList = service.findOption();//获得下拉框数据
        JSONObject jo = new JSONObject();
        jo.put("total",total);
        jo.put("banks",list);
        jo.put("opvalue",varList);
        return jo.toString();
    }

    //获取最大支行编号
    @RequestMapping(value = "getBankNo",method = RequestMethod.GET)
    @ResponseBody
    public String getBankNo(String yhmc,String yhbh, HttpServletResponse response){
        List<String> varList = service.findMaxNoByYhmc(yhmc);//根据银行名称获取最大支行编号
        int zhbh = 0;
        String bh = "";
        if(varList.isEmpty() == false){
            String no = varList.get(0).substring(4,varList.get(0).length());
            zhbh = Integer.parseInt(no) + 1;
            bh = yhbh + String.valueOf(zhbh);
        }
        JSONObject jo = new JSONObject();
        jo.put("bh",bh);
        return jo.toString();
    }

    //查询所有
    @RequestMapping(value = "getAllBankList",method = RequestMethod.GET)
    @ResponseBody
    public String getAllBankList(Integer page, Integer size,HttpServletResponse response){
        List<BankEntity> list = service.getAllBankList();
        JSONObject jo = new JSONObject();
        jo.put("total",list.size());
        jo.put("users",list);
        return jo.toString();
    }

    //删除
    @RequestMapping(value = "deleteBankByTid",method = RequestMethod.POST)
    @ResponseBody
    public String remove(String bh,HttpServletResponse response){
        String flag = "err";
        try {
            service.deleteById(bh);
        } catch (Exception e) {
            flag = "ok";
            e.printStackTrace();
        }
        return flag;
    }

    //批量删除
    @RequestMapping(value = "batchRemoveBank",method = RequestMethod.POST)
    @ResponseBody
    public String batchRemoveBank(String[] bhs,HttpServletResponse response){
        String flag = "err";
        List<String> list = Arrays.asList(bhs);
        try {
            service.deleteByIds(list);
        } catch (Exception e) {
            flag = "ok";
            e.printStackTrace();
        }
        return flag;
    }

    //新增
    @RequestMapping(value = "saveBank",method = RequestMethod.POST)
    @ResponseBody
    public String saveBank(BankEntity bankEntity,
                           HttpServletRequest request, HttpServletResponse response){
        String flag = "ok";
        //UserEntity userEntity = (UserEntity) request.getParameterMap();
        try{
            String bh = bankEntity.getBh();
            List<String> varList =  service.findBankByBh(bh);
            if(!varList.isEmpty()){
                flag = "err";
            }else{
                service.saveBank(bankEntity);
            }
        }catch (Exception e){
            flag = "err";
            e.printStackTrace();
        }
        return flag;
    }

    //修改
    @RequestMapping(value = "editBank",method = RequestMethod.POST)
    @ResponseBody
    public String editBank(BankEntity bankEntity,
                           HttpServletRequest request, HttpServletResponse response){
        String flag = "ok";
        //UserEntity userEntity = (UserEntity) request.getParameterMap();
        try{
           service.updateBank(bankEntity);
        }catch (Exception e){
            flag = "err";
            e.printStackTrace();
        }
        return flag;
    }
}
