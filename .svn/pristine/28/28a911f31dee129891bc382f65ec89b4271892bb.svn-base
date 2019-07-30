package com.ljx.hfgsjt.zlgd.controller;


import com.ljx.hfgsjt.entity.dto.Sjst.ProjectModel;
import com.ljx.hfgsjt.entity.dto.Sjst.SjstYhzl;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.sjst.service.SjstService;
import com.ljx.hfgsjt.util.utils.StringUtil;
import com.ljx.hfgsjt.zlgd.service.ZlgdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "zlgdController")
@Api(description = "资料归档")
public class ZlgdController {
    @Autowired
    private ZlgdService service;
    @Autowired
    private SjstService sjstService;


    @ApiOperation(value = "项目查询")
    @RequestMapping(value = "getProject", method = RequestMethod.GET)
    public String getProject(HttpServletRequest request, ProjectModel model) {
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");//获取当前登录人
        List<ProjectModel> list = new ArrayList<ProjectModel>();
        int total = 0;
        try {
            model.setStartRow();
            model.setEndRow();
            Map<String,Object> getjbxx = service.getRoleidAndOrgidAndBmfzridByUserid(userEntity.getUserid());
            //是三个科室的部门负责人，加载该科室的所有项目
            if (userEntity.getUserid().equals(getjbxx.get("BMFZRID").toString())){
                model.setKs(getjbxx.get("ORGNAME").toString());
                list = service.getProjectList(model);
                total = service.getProjectCount(model);
                //根据当前登录人id 判断所属角色 是项目经理角色则加载自己的项目
            }else if (getjbxx.get("ROLEID").toString().equals("370bcad9650a44d99a0bc2e587963bfb") ||
                    getjbxx.get("ROLEID").toString().equals("231a28c9165844ecb695ae445d3ae8de") ||
                    getjbxx.get("ROLEID").toString().equals("ed212d777b304531b80bccf8c3a886d6")){
                model.setXmfzr(userEntity.getUserid());
                list = service.getProjectList(model);
                total = service.getProjectCount(model);

            }
        }catch (Exception e){
           total = service.getProjectCount(model);
            if(total>0){
                list = service.getProjectList(model);
            }
        }
        JSONObject jo = new JSONObject();
        jo.put("total",total);
        jo.put("row",list);
        return jo.toString();
    }

    @ApiOperation(value = "资料总览")
    @RequestMapping(value = "getZlzl",method = RequestMethod.GET)
    public String  getZlzl(HttpServletRequest request,String xmid){
        Map<String, Object> params = new HashMap<String, Object>();
        List<Map<String, Object>> listfomat = new ArrayList<Map<String, Object>>();
        List<ProjectModel> projectList = new ArrayList<ProjectModel>();
        String yjfjlx="";
        if(!StringUtil.isEmpty(xmid)){
            projectList = sjstService.getProjectLists(xmid);
            switch (projectList.get(0).getSqlx()){
                case "10":
                    yjfjlx = "2a52373c3bad43bb9b5236f6ce95455d";
                    break;
                case "20":
                    yjfjlx = "86c0fe78eb204bb5a45cfd64cae13839";
                    break;
                case "30":
                    yjfjlx = "96fd81786e1146b6abfd095cd135c15b";
                    break;
                default:
                    break;
            }
            params.put("v_xmid",xmid);
            params.put("v_yjlx",yjfjlx);
            params.put("p_cur", new ArrayList<Map<String, Object>>());//传入一个jdbc游标，用于接收返回参数
            service.getZlzl(params);
        }
        List<Map<String, Object>> list = (List<Map<String, Object>>) params.get("p_cur");
        if(list!=null&&list.size()>0){
            for (Map<String, Object> map:list
                    ) {
                if(!StringUtil.isEmpty(map.get("parentid"))){
                    listfomat.add(map);
                }
            }
            for (Map<String, Object> fomatModel:listfomat
                    ) {
                for (Map<String, Object> codeModels:list
                        ) {
                        if(codeModels.get("id").equals(fomatModel.get("parentid"))){
                            fomatModel.put("parentname",codeModels.get("codename"));
                            //fomatModel.put("SCR",codeModels.get("SCR"));
                            //fomatModel.put("SCSJ",codeModels.get("SCSJ"));
                        }
                }
            }
        }
        JSONObject jo = new JSONObject();
        jo.put("row",listfomat);
        return jo.toString();
    }
    @ApiOperation(value = "资料详情")
    @RequestMapping(value = "toDetial",method = RequestMethod.GET)
    public String  toDetial(HttpServletRequest request,String id,String parentid,String xmid){
        List<SjstYhzl> list = new ArrayList<SjstYhzl>();
        if(!StringUtil.isEmpty(parentid)){
            if(("yhtgzl").equals(parentid)){
                list =  sjstService.getXmjlQtzl(xmid,id);
            }else if(("86c0fe78eb204bb5a45c86c0fe78eb55").equals(parentid)){
                list = service.getSjcgzl(xmid,id);
            }else if(("wtys").equals(parentid)){

                list = service.getYszl(xmid,id);
            }else if(("htqd").equals(parentid)){
                list = service.getHtzl(xmid,id);
            }else{
                list = sjstService.getYhzl(xmid,id);
            }
        }
        JSONObject jo = new JSONObject();
        jo.put("row",list);
        return jo.toString();
    }
}
