package com.ljx.hfgsjt.yj.controller;

import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.BaseEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.Sjst.XmSb;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.yj.*;
import com.ljx.hfgsjt.message.service.MessageService;
import com.ljx.hfgsjt.repository.CalculateHours;
import com.ljx.hfgsjt.sgjg.service.SgxdService;
import com.ljx.hfgsjt.util.utils.DateUtils;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import com.ljx.hfgsjt.yj.service.SbyjService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 水表移交控制层
 */
@Controller
@RequestMapping(value = "SbyjController")
@Api(description = "水表移交")
public class SbyjController {

    @Autowired
    private SbyjService sbyjService;

    @Autowired
    private SgxdService sgxdService;

    @Autowired
    private MessageService messageService;

    private Logger logger = LoggerFactory.getLogger(SbyjController.class);

    CalculateHours cal = new CalculateHours();

    /**
     * 条件查询水表移交列表
     * @param xmbh
     * @param xmmc
     * @param
     * @param gcdz
     * @param azlx
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "条件查询水表移交列表")
    @RequestMapping(value = "getSbyjList",method = RequestMethod.GET)
    @ResponseBody
    public String getSbyjList(String xmbh,String xmmc,String gcdz,String azlx,
                              Integer page,Integer size,HttpServletRequest request){//String kssj,String jssj,
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        try{
//            if((!"".equals(kssj) && kssj != null) && (!"".equals(jssj) && jssj != null)){
//                pd.put("kssj",getDateFormatByGMT(kssj));
//                pd.put("jssj",getDateFormatByGMT(jssj));

                pd.put("xmfzr",user.getUserid());
                pd.put("xmbh",xmbh);
                pd.put("xmmc",xmmc);
                pd.put("gcdz",gcdz);
                pd.put("azlx",azlx);
                pd.put("page",page);
                pd.put("size",size);
                List<GetXmlbSbyjEntity> list = sbyjService.getSbyjListBysj(pd);
                jo.put("list",list);
                int total = sbyjService.queryAllSbyjLists(pd).size();
                jo.put("total", total);
//            }else{
//                List<GetXmlbSbyjEntity> list = sbyjService.getSbyjList(xmbh,xmmc,gcdz,azlx,page - 1, size);
//                jo.put("list",list);
//                jo.put("total", sbyjService.getTotal());
//            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 根据项目ID查询水表移交单列表
     * @param xmid
     * @return
     */
    @ApiOperation(value = "根据项目ID查询水表移交单列表")
    @RequestMapping(value = "querySbyjList",method = RequestMethod.GET)
    @ResponseBody
    public String querySbyjList(String xmid){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        try{
            List<YjSbyjzbEntity> list = sbyjService.findByXmid(xmid);
            jo.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 根据项目ID查询水表集合
     * @param xmid
     * @param wbsize
     * @param wbpage
     * @param jmbsize
     * @param jmbpage
     * @param gsbsize
     * @param gsbpage
     * @return
     */
    @ApiOperation(value = "根据项目ID查询水表集合")
    @RequestMapping(value = "showSbList",method = RequestMethod.GET)
    @ResponseBody
    public String showSbList(String xmid,String yjdbh,Integer wbsize,Integer wbpage,Integer jmbsize,Integer jmbpage,
                             Integer gsbsize,Integer gsbpage){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        try{
            pd.put("xmid",xmid);
            pd.put("wbsize",wbsize);
            pd.put("wbpage",wbpage);
            pd.put("jmbsize",jmbsize);
            pd.put("jmbpage",jmbpage);
            pd.put("gsbsize",gsbsize);
            pd.put("gsbpage",gsbpage);
            List<XmSb> wbList = sbyjService.queryWblist(pd);
            int wbtotal = sbyjService.queryAllWblists(pd).size();
            List<XmSb> gsbList = new ArrayList<>();
            int gsbtotal = 0;
            List<Map<String,Object>> jmbList = new ArrayList<>();
            int jmbtotal = 0;
//            if("".equals(yjdbh) || yjdbh == null){
                gsbList = sbyjService.queryGsblist(pd);
                gsbtotal = sbyjService.queryAllGsblists(pd).size();
                jmbList = sbyjService.queryJMblist(pd);
                jmbtotal = sbyjService.queryAllJmblists(pd).size();
//            }else{
//
//            }
            List<Map<String,Object>> sbcjList = sbyjService.getSbcjList(pd);
            List<Map<String,Object>> zbkjList = sbyjService.getZbkjList(pd);
            List<Map<String,Object>> qyList = sbyjService.getqyList(pd);
            List<Map<String,Object>> sblxList = sbyjService.getsblxList(pd);
            List<Map<String,Object>> varList = sbyjService.getXmxxByXmid(pd);
            List<Map<String,Object>> pqList = sbyjService.getpqList(pd);
            List<Map<String,Object>> zpry = sbyjService.getZpry(pd);
            jo.put("zpry",zpry);
            jo.put("sbcjList",sbcjList);
            jo.put("sblxList",sblxList);
            jo.put("zbkjList",zbkjList);
            jo.put("varList",varList);
            jo.put("pqList",pqList);
            jo.put("qyList",qyList);
            jo.put("wbList",wbList);
            jo.put("wbtotal",wbtotal);
            jo.put("gsbList",gsbList);
            jo.put("gsbtotal",gsbtotal);
            jo.put("jmbList",jmbList);
            jo.put("jmbtotal",jmbtotal);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation(value = "专人审核水表信息")
    @RequestMapping(value = "shSbList",method = RequestMethod.GET)
    @ResponseBody
    public String shSbList(String xmid,Integer wbsize,Integer wbpage,Integer jmbsize,Integer jmbpage,
                           Integer gsbsize,Integer gsbpage,String yjdbh,String yjzbid){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        try{
            pd.put("xmid",xmid);
            pd.put("wbsize",wbsize);
            pd.put("wbpage",wbpage);
            pd.put("jmbsize",jmbsize);
            pd.put("jmbpage",jmbpage);
            pd.put("gsbsize",gsbsize);
            pd.put("gsbpage",gsbpage);
            pd.put("yjdbh",yjdbh);
            pd.put("yjzbid",yjzbid);
            List<Map<String, Object>> wbList = sbyjService.shWblist(pd);
            int wbtotal = sbyjService.shAllWblists(pd).size();
            List<Map<String, Object>> gsbList = sbyjService.shGsblist(pd);
            int gsbtotal = sbyjService.shAllGsblists(pd).size();
            List<Map<String,Object>> jmbList = sbyjService.shJMblist(pd);
            int jmbtotal = sbyjService.shAllJmblists(pd).size();
            List<Map<String,Object>> varList = sbyjService.getXmxxByXmid(pd);
            List<Map<String,Object>> pqList = sbyjService.getpqList(pd);
            List<Map<String,Object>> zpcby = sbyjService.getZpcby(pd);
            List<Map<String,Object>> ysxzList = sbyjService.getYsxz(pd);
            jo.put("ysxzList",ysxzList);
            jo.put("zpcby",zpcby);
            jo.put("varList",varList);
            jo.put("pqList",pqList);
            jo.put("wbList",wbList);
            jo.put("wbtotal",wbtotal);
            jo.put("gsbList",gsbList);
            jo.put("gsbtotal",gsbtotal);
            jo.put("jmbList",jmbList);
            jo.put("jmbtotal",jmbtotal);

            //导出情况描述
            List<Map<String,Object>> dcList = sbyjService.queryAlldcsb(pd);
            if(dcList.isEmpty() == false){
                jo.put("dcck",1);
            }else{
                jo.put("dcck",0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 五级计量表编辑保存
     * @param entity
     * @return
     */
    @ApiOperation(value = "五级计量表编辑保存")
    @RequestMapping(value = "editWb",method = RequestMethod.GET)
    @ResponseBody
    public String editWb(WjjlbEntity entity){
        String flag = "false";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            if(entity.getAzrq() != null){
                entity.setNazrq(sdf.parse(DateUtils.getDateFormatByGMT(entity.getAzrq())));
            }
            if(entity.getTsrq() != null){
                entity.setNtsrq(sdf.parse(DateUtils.getDateFormatByGMT(entity.getTsrq())));
            }
            if(entity.getXcyjrq() != null){
                entity.setNxcyjrq(sdf.parse(DateUtils.getDateFormatByGMT(entity.getXcyjrq())));
            }
            if(entity.getSgrq() != null){
                entity.setNsgrq(sdf.parse(DateUtils.getDateFormatByGMT(entity.getSgrq())));
            }
            if(entity.getYhqrrq() != null){
                entity.setNyhqrrq(sdf.parse(DateUtils.getDateFormatByGMT(entity.getYhqrrq())));
            }
            sbyjService.editWb(entity);
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }


    @ApiOperation(value = "审核界面水表编辑")
    @RequestMapping(value = "sheditsb",method = RequestMethod.POST)
    @ResponseBody
    public String sheditsb(HttpServletRequest request){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            pd.put("pq",request.getParameter("pq"));
            pd.put("sbid",request.getParameter("sbid"));
            pd.put("ysxz",request.getParameter("ysxz"));
            pd.put("hyfl",request.getParameter("hyfl"));
            pd.put("dsljf",request.getParameter("dsljf"));
            String dxfw = request.getParameter("dxfw");
            pd.put("dxfw",dxfw);
            String xcyjrq = request.getParameter("xcyjrq");
            pd.put("xcyjrq",sdf.parse(xcyjrq));
            pd.put("sgdw",request.getParameter("sgdw"));
            pd.put("gss",request.getParameter("gss"));
            pd.put("yyzx",request.getParameter("yyzx"));
            pd.put("bz",request.getParameter("bz"));
            if("Y".equals(dxfw)){
                pd.put("jzmc",request.getParameter("jzmc") == null ? "" :request.getParameter("jzmc"));
                pd.put("sjhm",request.getParameter("sjhm") == null ? "" :request.getParameter("sjhm"));
                sbyjService.sheditsbs(pd);
            }else{
                sbyjService.sheditsb(pd);
            }
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }


    @ApiOperation(value = "抄表员界面水表编辑")
    @RequestMapping(value = "cbyeditsb",method = RequestMethod.POST)
    @ResponseBody
    public String cbyeditsb(HttpServletRequest request){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        try{
            pd.put("sbid",request.getParameter("sbid"));
            pd.put("bch",request.getParameter("bch"));
            sbyjService.cbyeditsb(pd);
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 根据楼栋ID/项目ID查询居民表数据
     * @param xmid
     * @param ldid
     * @return
     */
    @ApiOperation(value = "根据楼栋ID/项目ID查询居民表数据")
    @RequestMapping(value = "findJmbByLdid",method = RequestMethod.GET)
    @ResponseBody
    public String findJmbByLdid(String xmid,String ldid){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        try{
            pd.put("xmid",xmid);
            pd.put("ldid",ldid);
            List<XmSb> list = sbyjService.findJmbByLdid(pd);
            List<Map<String,Object>> sbcjList = sbyjService.getSbcjList(pd);
            List<Map<String,Object>> zbkjList = sbyjService.getZbkjList(pd);
            List<Map<String,Object>> qyList = sbyjService.getqyList(pd);
            List<Map<String,Object>> sblxList = sbyjService.getsblxList(pd);
            List<Map<String,Object>> varList = sbyjService.getXmxxByXmid(pd);
            List<Map<String,Object>> pqList = sbyjService.getpqList(pd);
            jo.put("sbcjList",sbcjList);
            jo.put("sblxList",sblxList);
            jo.put("zbkjList",zbkjList);
            jo.put("varList",varList);
            jo.put("pqList",pqList);
            jo.put("qyList",qyList);
            jo.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }


    @ApiOperation(value = "根据楼栋ID/项目ID查询居民表数据")
    @RequestMapping(value = "shJmbByLdid",method = RequestMethod.GET)
    @ResponseBody
    public String shJmbByLdid(String xmid,String ldid,String yjdbh){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        try{
            pd.put("xmid",xmid);
            pd.put("ldid",ldid);
            pd.put("yjdbh",yjdbh);
            List<Map<String, Object>> list = sbyjService.shJmbByLdid(pd);
            List<Map<String,Object>> varList = sbyjService.getXmxxByXmid(pd);
            List<Map<String,Object>> pqList = sbyjService.getpqList(pd);
            List<Map<String,Object>> zpry = sbyjService.getZpry(pd);
            List<Map<String,Object>> ysxzList = sbyjService.getYsxz(pd);
            List<Map<String,Object>> sblxList = sbyjService.getsblxList(pd);
            List<Map<String,Object>> sbcjList = sbyjService.getSbcjList(pd);
            jo.put("sbcjList",sbcjList);
            jo.put("sblxList",sblxList);
            jo.put("ysxzList",ysxzList);
            jo.put("zpry",zpry);
            jo.put("varList",varList);
            jo.put("pqList",pqList);
            jo.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * 表内编辑
     * @return
     */
    @ApiOperation(value = "表内编辑")
    @RequestMapping(value = "elxedit",method = RequestMethod.POST)
    @ResponseBody
    public String elxedit(String mc,HttpServletRequest request){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            if("hh".equals(mc)){
                pd.put("thh","1");
                pd.put("hh",request.getParameter("hh"));
            }else if("mph".equals(mc)){
                pd.put("tmph","1");
                pd.put("mph",request.getParameter("mph"));
            }else if("azwz".equals(mc)){
                pd.put("tazwz","1");
                pd.put("azwz",request.getParameter("azwz"));
            }else if("bsm".equals(mc)){
                pd.put("tbsm","1");
                pd.put("bsm",request.getParameter("bsm"));
            }else if("sgdw_sbkj".equals(mc)){
                pd.put("tsgdw_sbkj","1");
                pd.put("sgdw_sbkj",request.getParameter("sgdw_sbkj"));
            }else if("pqbh".equals(mc)){
                pd.put("tpqbh","1");
                pd.put("pqbh",request.getParameter("pqbh"));
            }else if("sgsj".equals(mc)){
                pd.put("tsgsj","1");
                pd.put("sgsj", sdf.parse(DateUtils.getDateFormatByGMT(request.getParameter("sgsj"))));
            }else if("tssj".equals(mc)){
                pd.put("ttssj","1");
                pd.put("tssj",sdf.parse(DateUtils.getDateFormatByGMT(request.getParameter("tssj"))));
            }else if("ds".equals(mc)){
                pd.put("tds","1");
                pd.put("ds",request.getParameter("ds"));
            }else if("sbcjbh".equals(mc)){
                pd.put("tsbcjbh","1");
                pd.put("sbcjbh",request.getParameter("sbcjbh"));
            }else if("sblxbh".equals(mc)){
                pd.put("tsblxbh","1");
                pd.put("sblxbh",request.getParameter("sblxbh"));
            }else if("sgry".equals(mc)){
                pd.put("tsgry","1");
                pd.put("sgry",request.getParameter("sgry"));
            }else if("sgryqzsj".equals(mc)){
                pd.put("tsgryqzsj","1");
                pd.put("sgryqzsj",sdf.parse(DateUtils.getDateFormatByGMT(request.getParameter("sgryqzsj"))));
            }else if("yhqr".equals(mc)){
                pd.put("tyhqr","1");
                pd.put("yhqr",request.getParameter("yhqr"));
            }else if("yhqrqzsj".equals(mc)){
                pd.put("tyhqrqzsj","1");
                pd.put("yhqrqzsj",sdf.parse(DateUtils.getDateFormatByGMT(request.getParameter("yhqrqzsj"))));
            }else{
                return flag;
            }
            pd.put("id",request.getParameter("id"));
            sbyjService.elxedit(pd);
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 公共字段批量编辑
     * @return
     */
    @ApiOperation(value = "公共字段批量编辑")
    @RequestMapping(value = "pledit",method = RequestMethod.POST)
    @ResponseBody
    public String pledit(YjSbplEntity entity){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        pd.put("sbids",entity.getIds());
        try{
            if(!"".equals(entity.getZbdz())){
                pd.put("tzbdz","1");
                pd.put("zbdz",entity.getZbdz());
            }
            if(!"".equals(entity.getGss())){
                pd.put("tgss","1");
                pd.put("gss",entity.getGss());
            }
            if(!"".equals(entity.getSbkj())){
                pd.put("tsbkj","1");
                pd.put("sbkj",entity.getSbkj());
            }
            if(!"".equals(entity.getAzrq())){
                pd.put("tazrq","1");
                pd.put("azrq",sdf.parse(DateUtils.getDateFormatByGMT(entity.getAzrq())));
            }
            if(!"".equals(entity.getTsrq())){
                pd.put("ttsrq","1");
                pd.put("tsrq",sdf.parse(DateUtils.getDateFormatByGMT(entity.getTsrq())));
            }
            if(!"".equals(entity.getSbcj())){
                pd.put("tsbcj","1");
                pd.put("sbcj",entity.getSbcj());
            }
            if(!"".equals(entity.getSblx())){
                pd.put("tsblx","1");
                pd.put("sblx",entity.getSblx());
            }
            if(!"".equals(entity.getSgry())){
                pd.put("tsgry","1");
                pd.put("sgry",entity.getSgry());
            }
            if(!"".equals(entity.getSgrq())){
                pd.put("tsgrq","1");
                pd.put("sgrq",sdf.parse(DateUtils.getDateFormatByGMT(entity.getSgrq())));
            }
            if(!"".equals(entity.getYhdb())){
                pd.put("tyhdb","1");
                pd.put("yhdb",entity.getYhdb());
            }
            if(!"".equals(entity.getYhqrrq())){
                pd.put("tyhqrrq","1");
                pd.put("yhqrrq",sdf.parse(DateUtils.getDateFormatByGMT(entity.getYhqrrq())));
            }else{
                return flag;
            }
            sbyjService.pledit(pd);
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation(value = "审核界面批量编辑水表")
    @RequestMapping(value = "shpledit",method = RequestMethod.POST)
    @ResponseBody
    public String shpledit(YjSbshplEntity entity){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        pd.put("sbids",entity.getIds());
        try{
            if(!"".equals(entity.getDsljf())){
                pd.put("tdsljf","1");
                pd.put("dsljf",entity.getDsljf());
            }
            if(!"".equals(entity.getGssry())){
                pd.put("tgssry","1");
                pd.put("gssry",entity.getGssry());
            }
            if(!"".equals(entity.getHyfl())){
                pd.put("thyfl","1");
                pd.put("hyfl",entity.getHyfl());
            }
            if(!"".equals(entity.getSgdwry())){
                pd.put("tsgdwry","1");
                pd.put("sgdwry",entity.getSgdwry());
            }
            if(!"".equals(entity.getXcyjrq())){
                pd.put("txcyjrq","1");
                pd.put("xcyjrq",sdf.parse(entity.getXcyjrq()));
            }
            if(!"".equals(entity.getYsxz())){
                pd.put("tysxz","1");
                pd.put("ysxz",entity.getYsxz());
            }
            if(!"".equals(entity.getYyzxry())){
                pd.put("tyyzxry","1");
                pd.put("yyzxry",entity.getYyzxry());
            }
            if(!"".equals(entity.getBz())){
                pd.put("tbz","1");
                pd.put("bz",entity.getBz());
            }
            if(!"".equals(entity.getDxfw())){
                pd.put("tdxfw","1");
                pd.put("dxfw",entity.getDxfw());
            }
            if(!"".equals(entity.getJzmc())){
                pd.put("tjzmc","1");
                pd.put("jzmc",entity.getJzmc());
            }
            if(!"".equals(entity.getSjhm())){
                pd.put("tsjhm","1");
                pd.put("sjhm",entity.getSjhm());
            }
            if(!"".equals(entity.getPq())){
                pd.put("tpq","1");
                pd.put("pq",entity.getPq());
            }
            sbyjService.shpledit(pd);
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation(value = "抄表员界面批量编辑水表")
    @RequestMapping(value = "cbypledit",method = RequestMethod.POST)
    @ResponseBody
    public String cbypledit(String bch, String ids){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        try{
            pd.put("bch",bch);
            pd.put("ids",ids);
            sbyjService.cbypledit(pd);
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 居民表Excel批量编辑
     * @param multipartFiles
     * @param comId
     * @param request
     * @return
     */
    @ApiOperation("居民表Excel批量编辑")
    @RequestMapping(value ="importExcel",method = RequestMethod.POST, consumes = { "multipart/mixed", "multipart/form-data" })
    @ResponseBody
    public Map importExcel(@RequestParam(value = "multipartFiles", required = false ) MultipartFile multipartFiles,
                              @RequestParam(value = "comId", required = false ) String comId,
                              HttpServletRequest request){
        Map resMap = new HashMap<String,Object>();
        if (Objects.isNull(multipartFiles)) {
            logger.error("文件为空");
            resMap.put("msg","文件为空，请重新上传");
            return resMap;
        }
        try{
            File excel = null;
            if(multipartFiles.equals("")||multipartFiles.getSize()<=0){
                multipartFiles = null;
            }else {
                InputStream ins = null;
                ins = multipartFiles.getInputStream();
                excel = new File(multipartFiles.getOriginalFilename());
                inputStreamToFile(ins, excel);
                ins.close();
            }
            String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
            Workbook wb;
            //根据文件后缀（xls/xlsx）进行判断
            if ( "xls".equals(split[1])){
                FileInputStream fis = new FileInputStream(excel);   //文件流对象
                wb = new HSSFWorkbook(fis);
            }else if ("xlsx".equals(split[1])){
                wb = new XSSFWorkbook(excel);
            }else {
                resMap.put("msg","文件类型错误");
                return resMap;
            }

            //开始解析
            Sheet sheet = wb.getSheetAt(0);     //读取sheet 0

            int firstRowIndex = sheet.getFirstRowNum()+1;   //第一行是列名，所以不读
            int lastRowIndex = sheet.getLastRowNum();

            for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                Row row = sheet.getRow(rIndex);
                if (row != null) {
                    int firstCellIndex = row.getFirstCellNum();
                    int lastCellIndex = row.getLastCellNum();
                    Map<String,Object> pd = new HashMap<>();
                    String bzh = "";
                    int bsm = 0;
                    String fq = "";
                    for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
                        Cell cell = row.getCell(cIndex);
                        if (cell != null) {
                            if(cIndex == 0){
                                bzh =  cell.toString();
                                pd.put("bzh",bzh);
                            }else if(cIndex == 3){
                                bsm = (int) cell.getNumericCellValue();
                                pd.put("bsm",bsm);
                            }else if(cIndex == 4){
                                fq = cell.toString();
                                pd.put("fq",fq);
                            }
                        }
                    }
                    sbyjService.updateBsmAndFqByBzh(pd);
                }
            }
            resMap.put("msg","上传成功");
        }catch (Exception e){
            e.printStackTrace();
            resMap.put("msg","上传失败");
        }
        return resMap;
    }

    /**
     * 水表勾选移交保存
     * @param sbids
     * @param ldids
     * @param xmid
     * @param yjdbh
     * @return
     */
    @ApiOperation("水表勾选移交保存")
    @RequestMapping(value = "sbyjSubmit",method = RequestMethod.GET)
    @ResponseBody
    public String sbyjSubmit(String type,String sbids,String ldids,String wbsbids,String xmid,String yjdbh,String name,
                             String isEdit,String isTh,String yjzbid,HttpServletRequest request){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity)session.getAttribute("user");//获取当前登录的用户信息
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String cjr = user.getUserid();
        pd.put("xmid",xmid);
        pd.put("yjdbh",yjdbh);
        pd.put("yjzbid",yjzbid);
        try{
            String id = yjzbid;
//            if("1".equals(isEdit)){
//                id = sbyjService.findZbidBybh(pd);
//            }else{
//               id  = GuidUtils.getGuid();//水表移交主表标识
//            }
            pd.put("id",id);
            pd.put("cjr",cjr);
            pd.put("name",name);
            if("1".equals(isEdit)){

            }else{
                sbyjService.saveSbyjzb(pd);//水表移交主表保存
            }
            if(!"(".equals(ldids) && !"".equals(ldids)){//楼栋勾选保存
//                sbyjService.deteleHbByYjdbh(pd);//根据移交单编号删除水表移交从表中垃圾数据
                String[] ldarr = ldids.split(",");
                for(int i=0;i<ldarr.length;i++){
                    pd.put("ldid",ldarr[i]);
                    sbyjService.deleteLdsb(pd);
//                    sbyjService.deleteHbByLdid(pd);//根据楼栋ID删除水表移交从表中垃圾数据
                    List<String> ldhbList = sbyjService.queryhbidByLdid(pd);//根据楼栋ID查询楼栋下居民表ID集合
                    for(int z=0;z<ldhbList.size();z++){
                        pd.put("ldhbid",ldhbList.get(z));
                        pd.put("ldhbbs",GuidUtils.getGuid());
                        sbyjService.ldhbSubmit(pd);//楼栋内居民表保存
                    }
                }
            }
//            sbyjService.updateCbhb(pd);//更新从表中居民表数据
            sbyjService.deleteDb(pd);//先删后加地表
            if(!"(".equals(sbids) && !"".equals(sbids)){//地表勾选保存
                String[] dbarr = sbids.split(",");
                for(int j=0;j<dbarr.length;j++){
                    pd.put("dbid",dbarr[j]);
                    pd.put("dbbs", GuidUtils.getGuid());
                    sbyjService.sbyjDbSubmit(pd);
                }
            }

            sbyjService.deleteWb(pd);//先删后加五级计量表
            if(!"(".equals(wbsbids) && !"".equals(wbsbids)){//五级计量表勾选保存
                String[] wbarr = wbsbids.split(",");
                for(int j=0;j<wbarr.length;j++){
                    pd.put("wbid",wbarr[j]);
                    pd.put("wbbs", GuidUtils.getGuid());
                    sbyjService.sbyjWbSubmit(pd);
                }
            }

            if(type.equals("2")){
                //流程
                WfTaskEntity task = new WfTaskEntity();
                String procInstId = "";
                String pdefId = "";
                String lcscId = "90001";
                WfRuInstEntity instEntity = new WfRuInstEntity();
                WfRuInstContentEntity wfRuInstContentEntity = new WfRuInstContentEntity();
                List<Map<String,Object>> varList = sgxdService.queryPdefId(id,lcscId);

                if("1".equals(isTh)){
                    procInstId = String.valueOf(varList.get(0).get("PROC_INST_ID"));
                    pdefId = String.valueOf(varList.get(0).get("C_ACTIVITYID"));

                    //将当前task表里数据存入历史任务表中
                    task = sgxdService.findBycActivityidAndProcInstId(pdefId,procInstId);
                    task.setIscurrent("0");//已完成
                    task.setIscheck("1");//已签收
                    task.setJssj(new Date());//结束时间
                    WfTaskHistoryEntity historyEntity = new WfTaskHistoryEntity(task);
                    sgxdService.saveWfTaskHistory(historyEntity);

                    //删除当前task表里数据
                    sgxdService.deleteTask(pdefId,procInstId);
                }else {
                    pdefId = "90001";
                    procInstId = GuidUtils.getGuid();

                    //启动流程实例
                    instEntity.setProcInstId(procInstId);
                    instEntity.setPdefId(pdefId);
                    instEntity.setParammap(yjdbh);
                    instEntity.setStartdt(new Date());
                    instEntity.setProcstatus(1);
                    instEntity.setStartuser(cjr);
                    sgxdService.saveWfRuInst(instEntity);

                    //将项目信息存入流程实例使用内容表(WF_RU_INST_CONTENT)
                    wfRuInstContentEntity.setContentid(GuidUtils.getGuid());
                    wfRuInstContentEntity.setProcInstId(procInstId);
                    wfRuInstContentEntity.setTableName("XM_INSTANCE");
                    wfRuInstContentEntity.setContentvalue(xmid);
                    wfRuInstContentEntity.setContenttype("YJ_SBYJZB");
                    wfRuInstContentEntity.setContentname(id);
                    sgxdService.saveWfRuInstContent(wfRuInstContentEntity);

                    //创建任务，存入历史任务表中
                    String taskId = GuidUtils.getGuid();
                    task.setTaskid(taskId);
                    task.setQssj(new Date());//起始时间
                    task.setJssj(new Date());//结束时间
                    task.setPtaskid("0");//前置任务为空
                    task.setProcInstId(procInstId);//流程实例ID
                    task.setCActivityid(pdefId);//环节编号
                    task.setIscurrent("0");//已完成
                    task.setCzr(cjr);//操作人
                    task.setIscheck("1");//已签收
                    task.setHasnew("2");//无子流程
                    task.setRwblr(cjr);//任务办理人
                    WfTaskHistoryEntity historyEntity = new WfTaskHistoryEntity(task);
                    sgxdService.saveWfTaskHistory(historyEntity);
                }

                //下一节点 创建任务
                String direction = "0";//流程方向 前进/回退
                WfTaskEntity newtask = new WfTaskEntity();
                List<WfReNodeEntity> list = sgxdService.findByActivityidAndDirection(pdefId,direction);
                for(WfReNodeEntity wf : list){
                    WfDfActivityEntity ac = sgxdService.findByActivityid(wf.getActivityid());
                    newtask.setTaskid(GuidUtils.getGuid());
                    newtask.setPtaskid("0");//前置任务为空
                    newtask.setProcInstId(procInstId);//流程实例ID
                    newtask.setCActivityid(wf.getNextnode());//环节编号
                    newtask.setIscurrent("1");//状态标志：0已做1待做2退回
                    Date qssj = new Date();
                    try {
                        if (ac.getCheckLimited() == null || ac.getCheckLimited().equals("")
                                || ac.getCheckLimited().equals("0")) {
                            newtask.setCheck_sx(sdft.parse("2099-12-31 23:59:59"));
                        } else {
                            newtask.setCheck_sx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getCheckMinute())));
                        }
                        if (ac.getFinishLimited() == null || ac.getFinishLimited().equals("")
                                || ac.getFinishLimited().equals("0")) {
                            newtask.setJssx(sdft.parse("2099-12-31 23:59:59"));
                            newtask.setJssx_yl(sdft.parse("2099-12-31 23:59:59"));
                        } else {
                            newtask.setJssx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getYellowFinish())));
                            newtask.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getFinishMinute()) + Float.parseFloat(ac.getYellowFinish())));
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    newtask.setQssj(qssj);
                    newtask.setCzr(cjr);//操作人
                    newtask.setIscheck("0");//已经签收：0未签收1已签收
                    newtask.setHasnew("2");//无子流程
                    newtask.setRefkey("YJ_SBYJZB");//关联实体Key
                    newtask.setRefid(id);//关联实体ID
                    newtask.setRwblr(name);//任务办理人
                    sgxdService.saveWfTask(newtask);

                    //修改流程实例表中的流程定义ID
                    sgxdService.updateWfruInst(procInstId,wf.getNextnode());
                    saveDxDfs(newtask.getCActivityid(),xmid,cjr,newtask.getRwblr(),yjdbh,newtask.getTaskid());
                }
            }
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation("审核移交水表")
    @RequestMapping(value = "shsbyjSubmit",method = RequestMethod.GET)
    @ResponseBody
    public String shsbyjSubmit(String type,String xmid,String yjdbh,String name,String refId,HttpServletRequest request){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserEntity user = (UserEntity)request.getSession().getAttribute("user");
        pd.put("xmid",xmid);
        pd.put("yjdbh",yjdbh);
        pd.put("name",name);
        pd.put("refId",refId);
        try{
            if("1".equals(type)){
                sbyjService.updateCby(pd);
            }

            //更新流程
            WfTaskEntity task = new WfTaskEntity();
            String procInstId = "";
            String pdefId = "";
            String rwblr = "";
            String lcscId = "90001";
            WfRuInstEntity instEntity = new WfRuInstEntity();
            WfRuInstContentEntity wfRuInstContentEntity = new WfRuInstContentEntity();
            List<Map<String,Object>> varList = sgxdService.queryPdefId(refId,lcscId);
            procInstId = String.valueOf(varList.get(0).get("PROC_INST_ID"));
            pdefId = String.valueOf(varList.get(0).get("C_ACTIVITYID"));

            //将当前task表里数据存入历史任务表中
            task = sgxdService.findBycActivityidAndProcInstId(pdefId,procInstId);
            task.setIscurrent("0");//已完成
            task.setIscheck("1");//已签收
            task.setJssj(new Date());//结束时间
            WfTaskHistoryEntity historyEntity = new WfTaskHistoryEntity(task);
            sgxdService.saveWfTaskHistory(historyEntity);

            //删除当前task表里数据
            sgxdService.deleteTask(pdefId,procInstId);

            //下一节点 创建任务
            String direction = "0";//流程方向 前进/回退
            if("2".equals(type)){
                direction = "1";//退回
            }
            WfTaskEntity newtask = new WfTaskEntity();
            String thprefId = "";
            List<WfReNodeEntity> list = sgxdService.findByActivityidAndDirection(pdefId,direction);
            for(WfReNodeEntity wf : list){
                WfDfActivityEntity ac = sgxdService.findByActivityid(wf.getActivityid());
                newtask.setTaskid(GuidUtils.getGuid());
                newtask.setPtaskid("0");//前置任务为空
                newtask.setProcInstId(procInstId);//流程实例ID
                newtask.setCActivityid(wf.getNextnode());//环节编号
                thprefId = wf.getNextnode();
                if("1".equals(direction)){
                    newtask.setIscurrent("2");//状态标志：0已做1待做2退回
                }else{
                    newtask.setIscurrent("1");//状态标志：0已做1待做2退回
                }
                Date qssj = new Date();
                try {
                    if (ac.getCheckLimited() == null || ac.getCheckLimited().equals("")
                            || ac.getCheckLimited().equals("0")) {
                        newtask.setCheck_sx(sdft.parse("2099-12-31 23:59:59"));
                    } else {
                        newtask.setCheck_sx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getCheckMinute())));
                    }
                    if (ac.getFinishLimited() == null || ac.getFinishLimited().equals("")
                            || ac.getFinishLimited().equals("0")) {
                        newtask.setJssx(sdft.parse("2099-12-31 23:59:59"));
                        newtask.setJssx_yl(sdft.parse("2099-12-31 23:59:59"));
                    } else {
                        newtask.setJssx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getYellowFinish())));
                        newtask.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getFinishMinute()) + Float.parseFloat(ac.getYellowFinish())));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                newtask.setQssj(qssj);
                newtask.setCzr(user.getUserid());//操作人
                newtask.setIscheck("0");//已经签收：0未签收1已签收
                newtask.setHasnew("2");//无子流程
                newtask.setRefkey("YJ_SBYJZB");//关联实体Key
                newtask.setRefid(refId);//关联实体ID
                if("1".equals(type)){
                    rwblr = name;
                }else{
                    rwblr = user.getUserid();
                    //sgxdService.getUserIdByActivityId(wf.getNextnode())
                }
                newtask.setRwblr(rwblr);//任务办理人
                sgxdService.saveWfTask(newtask);

                //修改流程实例表中的流程定义ID
                sgxdService.updateWfruInst(procInstId,wf.getNextnode());

                if("2".equals(type)){

                }else{
                    saveDxDfs(newtask.getCActivityid(),xmid,user.getUserid(),newtask.getRwblr(),yjdbh,newtask.getTaskid());
                }
            }

            if("2".equals(type)){

                //将当前task表里数据存入历史任务表中
                task = sgxdService.findBycActivityidAndProcInstId(thprefId,procInstId);
                task.setIscurrent("0");//已完成
                task.setIscheck("1");//已签收
                task.setJssj(new Date());//结束时间
                WfTaskHistoryEntity thistoryEntity = new WfTaskHistoryEntity(task);
                sgxdService.saveWfTaskHistory(thistoryEntity);

                //删除当前task表里数据
                sgxdService.deleteTask(thprefId,procInstId);

                String thdirection = "0";//流程方向 前进/回退
                WfTaskEntity thtask = new WfTaskEntity();
                List<WfReNodeEntity> thlist = sgxdService.findByActivityidAndDirection(thprefId,thdirection);
                for(WfReNodeEntity thwf : thlist){
                    WfDfActivityEntity ac = sgxdService.findByActivityid(thwf.getActivityid());
                    thtask.setTaskid(GuidUtils.getGuid());
                    thtask.setPtaskid("0");//前置任务为空
                    thtask.setProcInstId(procInstId);//流程实例ID
                    thtask.setCActivityid(thwf.getNextnode());//环节编号
                    if("1".equals(thdirection)){
                        thtask.setIscurrent("2");//状态标志：0已做1待做2退回
                    }else{
                        thtask.setIscurrent("1");//状态标志：0已做1待做2退回
                    }
                    Date qssj = new Date();
                    try {
                        if (ac.getCheckLimited() == null || ac.getCheckLimited().equals("")
                                || ac.getCheckLimited().equals("0")) {
                            thtask.setCheck_sx(sdft.parse("2099-12-31 23:59:59"));
                        } else {
                            thtask.setCheck_sx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getCheckMinute())));
                        }
                        if (ac.getFinishLimited() == null || ac.getFinishLimited().equals("")
                                || ac.getFinishLimited().equals("0")) {
                            thtask.setJssx(sdft.parse("2099-12-31 23:59:59"));
                            thtask.setJssx_yl(sdft.parse("2099-12-31 23:59:59"));
                        } else {
                            thtask.setJssx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getYellowFinish())));
                            thtask.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getFinishMinute()) + Float.parseFloat(ac.getYellowFinish())));
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    thtask.setQssj(qssj);
                    thtask.setCzr(user.getUserid());//操作人
                    thtask.setIscheck("0");//已经签收：0未签收1已签收
                    thtask.setHasnew("2");//无子流程
                    thtask.setRefkey("YJ_SBYJZB");//关联实体Key
                    thtask.setRefid(refId);//关联实体ID
                    String activityId = "90001";
                    List<String> rList = sbyjService.queryRwblr(activityId,refId,procInstId);
                    if(rList.isEmpty() == false){
                        thtask.setRwblr(rList.get(0));//任务办理人
                    }
                    sgxdService.saveWfTask(thtask);

                    //修改流程实例表中的流程定义ID
                    sgxdService.updateWfruInst(procInstId,thwf.getNextnode());

                    saveDxDfs(thtask.getCActivityid(),xmid,user.getUserid(),thtask.getRwblr(),yjdbh,thtask.getTaskid());
                }
            }

            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation("抄表员移交水表")
    @RequestMapping(value = "cbysbyjSubmit",method = RequestMethod.GET)
    @ResponseBody
    public String cbysbyjSubmit(String type,String xmid,String yjdbh,String name,String refId,HttpServletRequest request){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserEntity user = (UserEntity)request.getSession().getAttribute("user");
        try{
            pd.put("refId",refId);
            if("1".equals(type)){
                pd.put("zt",1);
                sbyjService.updateSbztByid(pd);
            }
//            else{
//                pd.put("zt",2);
//            }


            //更新流程
            WfTaskEntity task = new WfTaskEntity();
            String procInstId = "";
            String pdefId = "";
            String rwblr = "";
            String lcscId = "90001";
            WfRuInstEntity instEntity = new WfRuInstEntity();
            WfRuInstContentEntity wfRuInstContentEntity = new WfRuInstContentEntity();
            List<Map<String,Object>> varList = sgxdService.queryPdefId(refId,lcscId);
            procInstId = String.valueOf(varList.get(0).get("PROC_INST_ID"));
            pdefId = String.valueOf(varList.get(0).get("C_ACTIVITYID"));

            if("1".equals(type)){
                //结束流程实例
                instEntity = sgxdService.findByprocInstId(procInstId);
                WfHiInstEntity wfHiInstEntity = new WfHiInstEntity(instEntity);
                wfHiInstEntity.setEndtime(new Date());
                //将当前流程实例存入流程历史表
                sgxdService.savewfHiInst(wfHiInstEntity);
                //删除当前流程实例
                sgxdService.deleteInst(procInstId);

                //将当前流程实例内容表存入流程实例历史内容表
                wfRuInstContentEntity = sgxdService.findByProcInstIdAndContentname(procInstId,refId);
                WfHiInstContentEntity wfHiInstContentEntity = new WfHiInstContentEntity(wfRuInstContentEntity);
                sgxdService.saveWfHiInstContent(wfHiInstContentEntity);
                //删除当前流程实例内容
                sgxdService.deleteInstContent(procInstId,refId);
            }

            //将当前task表里数据存入历史任务表中
            task = sgxdService.findBycActivityidAndProcInstId(pdefId,procInstId);
            task.setIscurrent("0");//已完成
            task.setIscheck("1");//已签收
            task.setJssj(new Date());//结束时间
            WfTaskHistoryEntity historyEntity = new WfTaskHistoryEntity(task);
            sgxdService.saveWfTaskHistory(historyEntity);

            historyEntity.setTaskid(GuidUtils.getGuid());
            historyEntity.setCActivityid("90006");
            historyEntity.setQssj(new Date());
            historyEntity.setJssj(new Date());
            historyEntity.setRwblr(user.getUserid());

            sgxdService.saveWfTaskHistory(historyEntity);

            //删除当前task表里数据
            sgxdService.deleteTask(pdefId,procInstId);

            if("1".equals(type)){

            }else{

              //  Yjsbyjzb yjsbyjzb=sbyjService.yjsb_zb(yjdbh);
                pd.put("name","");
                pd.put("shr","");
                sbyjService.updateCby(pd);//抄表员
                sbyjService.updateSbztByid(pd);//审核员
                //下一节点 创建任务
                String direction = "0";//流程方向 前进/回退
                if("2".equals(type)){
                    direction = "1";//退回
                }
                WfTaskEntity newtask = new WfTaskEntity();
                String thprefId = "";
                List<WfReNodeEntity> list = sgxdService.findByActivityidAndDirection(pdefId,direction);
                for(WfReNodeEntity wf : list){
                    WfDfActivityEntity ac = sgxdService.findByActivityid(wf.getActivityid());
                    newtask.setTaskid(GuidUtils.getGuid());
                    newtask.setPtaskid("0");//前置任务为空
                    newtask.setProcInstId(procInstId);//流程实例ID
                    newtask.setCActivityid(wf.getNextnode());//环节编号
                    thprefId = wf.getNextnode();
                    if("1".equals(direction)){
                        newtask.setIscurrent("2");//状态标志：0已做1待做2退回
                    }else{
                        newtask.setIscurrent("1");//状态标志：0已做1待做2退回
                    }
                    Date qssj = new Date();
                    try {
                        if (ac.getCheckLimited() == null || ac.getCheckLimited().equals("")
                                || ac.getCheckLimited().equals("0")) {
                            newtask.setCheck_sx(sdft.parse("2099-12-31 23:59:59"));
                        } else {
                            newtask.setCheck_sx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getCheckMinute())));
                        }
                        if (ac.getFinishLimited() == null || ac.getFinishLimited().equals("")
                                || ac.getFinishLimited().equals("0")) {
                            newtask.setJssx(sdft.parse("2099-12-31 23:59:59"));
                            newtask.setJssx_yl(sdft.parse("2099-12-31 23:59:59"));
                        } else {
                            newtask.setJssx(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getYellowFinish())));
                            newtask.setJssx_yl(cal.getEndTime(sdft.format(qssj), Float.parseFloat(ac.getFinishMinute()) + Float.parseFloat(ac.getYellowFinish())));
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    newtask.setQssj(qssj);
                    newtask.setCzr(user.getUserid());//操作人
                    newtask.setIscheck("0");//已经签收：0未签收1已签收
                    newtask.setHasnew("2");//无子流程
                    newtask.setRefkey("YJ_SBYJZB");//关联实体Key
                    newtask.setRefid(refId);//关联实体ID
                    String activityId = "90003";

                    System.out.println(activityId);
                    System.out.println("refId++++"+refId);
                    System.out.println("procInstId+++++"+procInstId);
                    List<String> rList = sbyjService.querycbyThRwblr(activityId,refId,procInstId);

                    if(rList.isEmpty() == false){
                        newtask.setRwblr(rList.get(0));//任务办理人
                    }
                    sgxdService.saveWfTask(newtask);

                    //修改流程实例表中的流程定义ID
                    sgxdService.updateWfruInst(procInstId,wf.getNextnode());

                    saveDxDfs(newtask.getCActivityid(),xmid,user.getUserid(),newtask.getRwblr(),yjdbh,newtask.getTaskid());
                }
            }

            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 楼栋水表勾选移交保存
     * @param yjdbh
     * @param ldsbid
     * @param ldid
     * @return
     */
    @ApiOperation("楼栋水表勾选移交保存")
    @RequestMapping(value = "sbyjLdSubmit",method = RequestMethod.GET)
    @ResponseBody
    public String sbyjLdSubmit(String yjdbh,String ldsbid,String ldid,String yjzbid){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        try{
            pd.put("yjdbh",yjdbh);
            pd.put("ldid",ldid);
            pd.put("yjzbid",yjzbid);
            String[] arr=ldsbid.split(",");
            sbyjService.deleteLdsb(pd);
            for(int i=0;i<arr.length;i++){
                pd.put("sbid",arr[i]);
                pd.put("id", GuidUtils.getGuid());
                sbyjService.sbyjLdSubmit(pd);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }



    /**
     * 查询安装类型下拉框
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "查询安装类型")
    @RequestMapping(value = "findAzlx",method = RequestMethod.GET)
    @ResponseBody
    public String findAzlx(HttpServletRequest request, HttpServletResponse response){
        JSONObject jo = new JSONObject();
        List<String> varList = new ArrayList<>();
        varList.add("居民科");
        varList.add("工商科");
        varList.add("户改科");
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i=0;i<varList.size();i++){
            Map<String,Object> pd = new HashMap<>();
            pd.put("value",i);
            pd.put("label",varList.get(i));
            List<azlxEntity> aList = sbyjService.findAzlxBySllx(pd);
            pd.put("children",aList);
            list.add(pd);
        }
        jo.put("list",list);
        return jo.toString();
    }

    /**
     * 查询移交单编号
     * @return
     */
    @ApiOperation(value = "查询移交单编号")
    @RequestMapping(value = "queryYjdbh",method = RequestMethod.GET)
    @ResponseBody
    public String queryYjdbh(){
        JSONObject jo = new JSONObject();
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            String ss="SBYJ";
            map.put("v_stop_run",ss);
            sgxdService.texuChange(map);//获取任务编号
            String rwbh = "SBYJ"+map.get("p_result");
            jo.put("rwbh",rwbh);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("查询行业分类")
    @RequestMapping(value = "getHyflCodeData",method = RequestMethod.GET)
    @ResponseBody
    public String getHyflCodeData(String ysxz){
        JSONObject jo = new JSONObject();
        try{
            List<Map<String,Object>> list = sbyjService.getHyflCodeData(ysxz);
            jo.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @ApiOperation("区域抄表员导入")
    @RequestMapping(value = "cbysbdr",method = RequestMethod.GET)
    @ResponseBody
    public String cbysbdr(String xmid,String refId,String yjdbh){
        String flag = "false";
        try{
            List<String> sbList = sbyjService.querySbids(xmid,refId);
            for (int i=0;i<sbList.size();i++){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("v_sbid",sbList.get(i));
                map.put("yjdbh",yjdbh);
                sbyjService.cbysbdr(map);
            }
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @ApiOperation("查询已导出数据")
    @RequestMapping(value = "querydcsb",method = RequestMethod.GET)
    @ResponseBody
    public String querydcsb(String yjdbh,Integer dcpage,Integer dcsize){
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        try{
            pd.put("dcpage",dcpage);
            pd.put("dcsize",dcsize);
            pd.put("yjdbh",yjdbh);
            List<Map<String,Object>> list = sbyjService.querydcsb(pd);
            int total = sbyjService.queryAlldcsb(pd).size();
            jo.put("list",list);
            jo.put("total",total);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @RequestMapping(value = "querysbcjjcBybh",method = RequestMethod.POST)
    @ResponseBody
    public String querysbcjjcBybh(String sbcj){
        JSONObject jo = new JSONObject();
        try{
            String sbcjjc = sbyjService.querysbcjjcBybh(sbcj);
            jo.put("sbcjjc",sbcjjc);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    @RequestMapping(value = "querysbkjmcBybh",method = RequestMethod.POST)
    @ResponseBody
    public String querysbkjmcBybh(String kjbh){
        JSONObject jo = new JSONObject();
        try{
            String sbkjmc = sbyjService.querysbkjmcBybh(kjbh);
            jo.put("sbkjmc",sbkjmc);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo.toString();
    }

    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("获得移交单主表ID")
    @RequestMapping(value = "getyjzbid",method = RequestMethod.GET)
    @ResponseBody
    public String getyjzbid(){
        JSONObject jo = new JSONObject();
        jo.put("yjzbid",GuidUtils.getGuid());
        return jo.toString();
    }

    @ApiOperation("区域抄表员工商表批量编辑表册号")
    @RequestMapping(value = "gsplbj",method = RequestMethod.POST)
    @ResponseBody
    public String gsplbj(String sbids,String bch){
        String flag = "false";
        Map<String,Object> pd = new HashMap<>();
        pd.put("sbids",sbids);
        pd.put("bch",bch);
        try{
            sbyjService.gsplbj(pd);
            flag = "true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 任务提醒 保存到待发送表中
     * @param activityid
     * @param xmid
     * @param fsr
     * @param jsr
     * @param taskid
     * @return
     */
    public String saveDxDfs(String activityid,String xmid,String fsr,String jsr,String rwbh,String taskid) {
        WfDfActivityEntity wfDfActivityEntity = sgxdService.findByActivityid(activityid);
        List<XmInstance> xmInstances = sgxdService.getXmxxByid(xmid);
        String xmmc = xmInstances.get(0).getXmmc();
        UserEntity userEntity = sgxdService.getUserByUserId(jsr);
        DxDfsEntity dxDfsEntity = new DxDfsEntity();//初始化短信模版
        Map<String,Object> dxmb = messageService.getDxMb("4");
        String content = dxmb.get("DXMB").toString();
        content = content.replace("[环节名称类型]", wfDfActivityEntity.getActivityname());
        content =   content.replace("[任务单编号]", rwbh);
        content =  content.replace("[项目名称]", xmmc);
        dxDfsEntity.setId(GuidUtils.getGuid());
        dxDfsEntity.setDxmbid("4");
        dxDfsEntity.setFsr(fsr);//发送人
        dxDfsEntity.setFssj(new Date());//发送时间
        dxDfsEntity.setFscs("1");//发送次数
        dxDfsEntity.setFsnr(content);//发送内容
        dxDfsEntity.setJsr(userEntity.getUserid());//接收人
        dxDfsEntity.setJshm(userEntity.getMobilephone());//接收号码
        dxDfsEntity.setTaskid(taskid);
        sgxdService.saveDxDfsEntity(dxDfsEntity);//保存
        return "";
    }


    @ApiOperation("综合查询 收费开票查询")
    @RequestMapping(value = "querysfkp",method = RequestMethod.GET)
    @ResponseBody
    public String querysfkp(BaseEntity baseEntity,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        baseEntity.setStartRow();
        baseEntity.setEndRow();
        List<Map<String,Object>> list = sbyjService.querysfkp(baseEntity);
        int total = sbyjService.querysfkpTotal(baseEntity);
        jo.put("row", list);
        jo.put("total", total);
        return jo.toString();
    }

    @ApiOperation("综合查询 施工下单查询")
    @RequestMapping(value = "querysgxd",method = RequestMethod.GET)
    @ResponseBody
    public String querysgxd(BaseEntity baseEntity,HttpServletRequest request){
        JSONObject jo = new JSONObject();
        baseEntity.setStartRow();
        baseEntity.setEndRow();
        List<Map<String,Object>> list = sbyjService.querysgxd(baseEntity);
        int total = sbyjService.querysgxdTotal(baseEntity);
        jo.put("row", list);
        jo.put("total", total);
        return jo.toString();
    }

}
