package com.ljx.hfgsjt.main.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljx.hfgsjt.entity.dto.CodeEntity;
import com.ljx.hfgsjt.main.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import com.ljx.hfgsjt.util.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Controller
@RequestMapping(value = "CodeController")
public class CodeController {
    @Autowired
    private CodeService service;
    /**
     * 获取所有的菜单列表
     * @param request
     * @return
     */
    @RequestMapping(value = "getCodeTree",method = RequestMethod.GET)
    @ResponseBody
    public String getCodeTree(HttpServletRequest request,HttpServletResponse response){
        CodeEntity xtglcode = new CodeEntity();
        List<CodeEntity> list=service.getAllCodeList();
        String str= "["+get_json(list,1,"")+"]";
        return str;
    }

    /***
     * 新增 or 保存
     * @param xtglcode
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "saveCode",method = RequestMethod.POST)
    @ResponseBody
    public String saveCode(CodeEntity xtglcode,
                           HttpServletRequest request, HttpServletResponse response){
        String flag = "ok";
        //UserEntity1 userEntity = (UserEntity1) request.getParameterMap();
        try{
            if(xtglcode.getId().equals("")||xtglcode.getId()==null){
                xtglcode.setId(GuidUtils.getGuid());
            }
            service.saveCode(xtglcode);
        }catch (Exception e){
            flag = "err";
            e.printStackTrace();
        }
        return flag;
    }
    /**
     *
     */
    @RequestMapping(value = "getCodeById",method = RequestMethod.GET)
    @ResponseBody
    public String getCodeById(String id,
                           HttpServletRequest request,HttpServletResponse response) {
        CodeEntity code = service.getCodeById(id);
        JSONObject jo = new JSONObject();
        jo.put("code",code);
        return jo.toString();
    }
    /**
     * 菜单重新格式化（层级关系）
     * @param list
     * @param k
     * @param pID
     * @return
     */
    private String get_json(List<CodeEntity> list, int k, String pID){
        if(k>5){
            return "";
        }
        StringBuilder note_s=new StringBuilder("");
        for(CodeEntity _menu :list)
        {
            if(StringUtil.isNull(_menu.getParentid()).equals(StringUtil.isNull(pID))){
                if(note_s.length()!=0){
                    note_s.append(",");
                }
                note_s.append("{\"id\":\""+_menu.getId()
                        +"\",\"text\":\""+_menu.getCodename()
                        +"\",\"children\":["+get_json(list,k+1,_menu.getId())
                        +"]"
                        +",\"attributes\":{\"VALID\":"+_menu.getSort()
//						+"\",\"ord\":"+_menu.getORDINAL()
//						+",\"url\":\""+_menu.getMENUURL()
//						+"\",\"tar\":\""+_menu.getTARGET()
//						+"\",\"big\":\""+_menu.getBIGICON()
//						+"\",\"ico\":\""+_menu.getMENUICON()
//						+"\",\"vid\":"+_menu.getVALID()
                        +"}"
                        +"}");
            }
        }
        return note_s.toString();
    }
    /***
     * 删除
     * @param xtglcode
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "deleteCode",method = RequestMethod.POST)
    @ResponseBody
    public String deleteCode(String id,HttpServletResponse response){
        String flag = "err";
        try {
            service.deleteById(id);
            flag = "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
