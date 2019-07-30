package com.ljx.hfgsjt.xmlct.controller;

import com.ljx.hfgsjt.entity.dto.Sjst.TaskEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.sfkp.SfkpSftzdzbEntity;
import com.ljx.hfgsjt.util.utils.StringUtil;
import com.ljx.hfgsjt.xmlct.service.XmlctService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "XmlctController")
@Api(description = "流程图")
public class XmlctController {

    @Autowired
    private XmlctService xmlctService;

    @ApiOperation(value = "项目流程图当前和历史完成节点")
    @RequestMapping(value = "getXmlctDqjd", method = RequestMethod.GET)
    public String getXmlctDqjd(HttpServletRequest request, String xmid) {
        JSONObject jo = new JSONObject();
        System.out.println(xmid+"-------");
        List<TaskEntity> taskDq= xmlctService.getXmlctDqjd(xmid);
        List<TaskEntity> taskLs= xmlctService.getXmlctLsjd(xmid);
        List<SfkpSftzdzbEntity> sfkplixt= xmlctService.getSfkpList(xmid);
        jo.put("taskLs",taskLs);
        jo.put("taskDq",taskDq);
        jo.put("sfkplixt",sfkplixt);
        return jo.toString();
    }

    @ApiOperation(value = "项目列表")
    @RequestMapping(value = "getXmlb", method = RequestMethod.GET)
    public String getXmlb(HttpServletRequest request, XmInstance xmInstance) {
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");//获取当前登录人
        List<XmInstance> list = new ArrayList<XmInstance>();
        int total = 0;
        try {
            xmInstance.setStartRow();
            xmInstance.setEndRow();
                Map<String,Object> getjbxx = xmlctService.getRoleidAndOrgidAndBmfzridByUserid(userEntity.getUserid());
                if(getjbxx==null|| StringUtil.isEmpty(getjbxx.get("BMFZRID"))){
                    list = xmlctService.getXmlbList(xmInstance);
                    total = xmlctService.getXmlbCount(xmInstance);
                }else{
                    //是三个科室的部门负责人，加载该科室的所有项目
                    if (userEntity.getUserid().equals(getjbxx.get("BMFZRID").toString())){
                        xmInstance.setKs(getjbxx.get("ORGNAME").toString());
                        list = xmlctService.getXmlbList(xmInstance);
                        total = xmlctService.getXmlbCount(xmInstance);
                        //根据当前登录人id 判断所属角色 是项目经理角色则加载自己的项目
                    }else if (getjbxx.get("ROLEID").toString().equals("370bcad9650a44d99a0bc2e587963bfb") ||
                            getjbxx.get("ROLEID").toString().equals("231a28c9165844ecb695ae445d3ae8de") ||
                            getjbxx.get("ROLEID").toString().equals("ed212d777b304531b80bccf8c3a886d6")){
                        xmInstance.setXmjl(userEntity.getUserid());
                        list = xmlctService.getXmlbList(xmInstance);
                        total = xmlctService.getXmlbCount(xmInstance);

                    }
                }


        } catch (Exception e) {
            //加载所有
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("total", total);
        jo.put("list", list);
        // System.out.println("------" + jo.toString());
        return jo.toString();

    }



    @ApiOperation(value = "项目流程图根据id查询项目称")
    @RequestMapping(value = "getXmmcByid", method = RequestMethod.POST)
    public String getXmmcByid(HttpServletRequest request, String xmid) {
        JSONObject jo = new JSONObject();

      Map<String,Object> getXmmcByid = xmlctService.getXmmcByid(xmid);
      jo.put("getXmmcByid",getXmmcByid);
        return jo.toString();
    }


}
