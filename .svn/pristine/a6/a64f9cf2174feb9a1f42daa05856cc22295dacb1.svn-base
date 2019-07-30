package com.ljx.hfgsjt.main.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljx.hfgsjt.entity.dto.DepartmentEntity;
import com.ljx.hfgsjt.main.service.DepartmentService;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import com.ljx.hfgsjt.util.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "DepartmentController")
public class DepartmentController {
    @Resource
    @Autowired
    private DepartmentService service;

/*
     * 获取所有的菜单列表
     * @param request
     * @return
*/

    @RequestMapping(value = "getDepartmentTree",method = RequestMethod.GET)
    @ResponseBody
    public String DepartmentTree(HttpServletRequest request,HttpServletResponse response){
        DepartmentEntity branch = new DepartmentEntity();
        List<DepartmentEntity> list = service.getAllDepartmentList();
        String str= "["+get_json(list,1,"")+"]";
        return str;
    }

    @RequestMapping(value = "showDepartmentTree",method = RequestMethod.GET)
    @ResponseBody
    public String showDepartmentTree(HttpServletRequest request,HttpServletResponse response){
        DepartmentEntity branch = new DepartmentEntity();
        List<DepartmentEntity> list = service.getAllDepartmentList();
        String str = "[" + get_json(list,1,"") + "]";
        return str;
    }

/**
     * 新增 or 保存
     * @param
     * @param request
     * @param response
     * @return
             */

    @RequestMapping(value = "saveDepartment",method = RequestMethod.POST)
    @ResponseBody
    public String saveDepartment(DepartmentEntity department, HttpServletRequest request,HttpServletResponse response){
        String flag = "ok";
        //UserEntity1 userEntity = (UserEntity1) request.getParameterMap();
        try{
            System.out.println("------------------------" + department.getOrgid());
            if(department.getOrgid().equals("")){
                department.setOrgid(GuidUtils.getGuid());
            }
            service.saveDepartment(department);
        }catch (Exception e){
            flag = "err";
            e.printStackTrace();
        }
        return flag;
    }



    @RequestMapping(value = "getDepartmentById",method = RequestMethod.GET)
    @ResponseBody
    public String saveDepartment(String orgid, HttpServletRequest request,HttpServletResponse response) {
        DepartmentEntity  department = service.getDepartmentById(orgid);

        JSONObject jo = new JSONObject();
        jo.put("department",department);
        return jo.toString();
    }



    /* * 菜单重新格式化（层级关系）
     * @param list
     * @param k
     * @param pID
     * @return
*/

    private String get_json(List<DepartmentEntity> list,int k,String pID){
        if(k>6){
            return "";
        }
        StringBuilder note_s=new StringBuilder("");
        for(DepartmentEntity _D :list)
        {
            if(StringUtil.isNull(_D.getParentid()).equals(StringUtil.isNull(pID))){
                if(note_s.length()!=0){
                    note_s.append(",");
                }
                note_s.append("{\"id\":\""+_D.getOrgid()
                        +"\",\"text\":\""+_D.getOrgname()
                        +"\",\"children\":["+get_json(list,k+1,_D.getOrgid())
                        +"]"
                        +",\"attributes\":{\"VALID\":"+_D.getIsused()
//						+"\",\"ord\":"+_D.getOrdinal()
//						+",\"url\":\""+_D.getOrgLevel()
//						+"\",\"tar\":\""+_D.getParentid()
//						+"\",\"big\":\""+_menu.getBIGICON()
//						+"\",\"ico\":\""+_menu.getMENUICON()
//						+"\",\"vid\":"+_menu.getVALID()
                        +"}"
                        +"}");
            }
        }
        return note_s.toString();
    }

    //删除
    @RequestMapping(value = "deleDepartment",method = RequestMethod.POST)
    @ResponseBody
    public String delrole(String id,HttpServletResponse response){
        String flag = "ok";
        try {
            service.deleteById(id);
        } catch (Exception e) {
            flag = "no";
            e.printStackTrace();
        }
        return flag;
    }
}
