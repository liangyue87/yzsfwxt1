package com.ljx.hfgsjt.sgjg.serviceImpl;

import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.Sjst.CodeModel;
import com.ljx.hfgsjt.entity.dto.Sjst.GdModel;
import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.Sjst.XmSb;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.htqd.HtQdzbEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.sgjg.GetXmlbSgEntity;
import com.ljx.hfgsjt.entity.sgjg.GetsgxtzbEntity;
import com.ljx.hfgsjt.entity.sgjg.SgxtRwJlfwEntity;
import com.ljx.hfgsjt.entity.sgjg.SgxtRwbEntity;
import com.ljx.hfgsjt.repository.activity.*;
import com.ljx.hfgsjt.repository.message.DxDfsRepository;
import com.ljx.hfgsjt.repository.sgjg.GetSgxtzbRepository;
import com.ljx.hfgsjt.repository.sgjg.GetXmlbSgRepository;
import com.ljx.hfgsjt.repository.sgjg.SgxtRwJLFWRepository;
import com.ljx.hfgsjt.repository.sgjg.SgxtRwbRepository;
import com.ljx.hfgsjt.sgjg.dao.SgxdMapper;
import com.ljx.hfgsjt.sgjg.service.SgxdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SgxdServiceImpl implements SgxdService {

    @Autowired
    private SgxdMapper dao;

    @Autowired
    private GetXmlbSgRepository repository;

    @Autowired
    private GetSgxtzbRepository getSgxtzbRepository;

    @Autowired
    private SgxtRwbRepository sgxtRwbRepository;

    @Autowired
    private SgxtRwJLFWRepository sgxtRwJLFWRepository;

    @Autowired
    private WfRuInstRepository instRepository;

    @Autowired
    private WfRuInstContentRepository instContentRepository;

    @Autowired
    private WfTaskRepository taskRepository;
    @Autowired
    private WfTaskHistoryRepository taskHistoryRepository;
    @Autowired
    private WfDfActivityRepository activityRepository;
    @Autowired
    private WfReNodeRepository nodeRepository;

    @Autowired
    private WfHiInstRepository wfHiInstRepository;

    @Autowired
    private WfHiInstContentRepository wfHiInstContentRepository;

    @Autowired
    private DxDfsRepository dxDfsRepository;

    private int total;

    @Override
    public int getTotal() {
        return total;
    }

    @Override
    public List<GetsgxtzbEntity> getSggcdList(Map<String, Object> pd) {
        Integer pagebefore = 1;
        Integer pageafter = 1;
        Integer page = Integer.parseInt(String.valueOf(pd.get("page")));
        Integer size = Integer.parseInt(String.valueOf(pd.get("size")));
        if (null == page) {
            page = 0;
            pagebefore = 0;
        }
        if (size==null) {
            size = 10;
            pageafter = 10;
        }
        pagebefore = page * size;
        pageafter = (page+1) * size;
        pd.put("pagebefore",pagebefore);
        pd.put("pageafter",pageafter);
        return dao.getSggcdList(pd);
    }

    @Override
    public List<GetsgxtzbEntity> getAllSggcdList(Map<String, Object> pd) {
        return dao.getAllSggcdList(pd);
    }

//    @Override
//    public List<GetsgxtzbEntity> getSggcdList(String xmbh, String xmmc, Integer page, Integer size) {
//        PageRequest request = PageRequest.of(page, size);
//        GetsgxtzbEntity entity = new GetsgxtzbEntity();
//        entity.setXmbh(xmbh);
//        entity.setXmmc(xmmc);
//        ExampleMatcher matcher = EX(2);
//        Example<GetsgxtzbEntity> ex = Example.of(entity, matcher);
//        Page<GetsgxtzbEntity> all = getSgxtzbRepository.findAll(ex, request);
//        this.total = (int) all.getTotalElements();
//        List<GetsgxtzbEntity> list = all.getContent();
//        return list;
//    }

    @Override
    public List<Map<String, Object>> queryWjjlbByXmid(Map<String, Object> pd) {
        Integer pagebefore = 1;
        Integer pageafter = 1;
        Integer page = Integer.parseInt(String.valueOf(pd.get("wbpage")));
        Integer size = Integer.parseInt(String.valueOf(pd.get("wbsize")));
        if (null == page) {
            page = 0;
            pagebefore = 0;
        }
        if (size==null) {
            size = 10;
            pageafter = 10;
        }
        pagebefore = page * size;
        pageafter = (page+1) * size;
        pd.put("pagebefore",pagebefore);
        pd.put("pageafter",pageafter);
        return dao.queryWjjlbByXmid(pd);
    }

    @Override
    public List<Map<String, Object>> queryDbByXmid(Map<String, Object> pd) {
        Integer pagebefore = 1;
        Integer pageafter = 1;
        Integer page = Integer.parseInt(String.valueOf(pd.get("dbpage")));
        Integer size = Integer.parseInt(String.valueOf(pd.get("dbsize")));
        if (null == page) {
            page = 0;
            pagebefore = 0;
        }
        if (size==null) {
            size = 10;
            pageafter = 10;
        }
        pagebefore = page * size;
        pageafter = (page+1) * size;
        pd.put("pagebefore",pagebefore);
        pd.put("pageafter",pageafter);
        return dao.queryDbByXmid(pd);
    }

    @Override
    public List<Map<String, Object>> queryHbByXmid(Map<String, Object> pd) {
        Integer pagebefore = 1;
        Integer pageafter = 1;
        Integer page = Integer.parseInt(String.valueOf(pd.get("hbpage")));
        Integer size = Integer.parseInt(String.valueOf(pd.get("hbsize")));
        if (null == page) {
            page = 0;
            pagebefore = 0;
        }
        if (size==null) {
            size = 10;
            pageafter = 10;
        }
        pagebefore = page * size;
        pageafter = (page+1) * size;
        pd.put("pagebefore",pagebefore);
        pd.put("pageafter",pageafter);
        return dao.queryHbByXmid(pd);
    }

    @Override
    public List<Map<String, Object>> queryWlxxByXmid(Map<String, Object> pd) {
        Integer pagebefore = 1;
        Integer pageafter = 1;
        Integer page = Integer.parseInt(String.valueOf(pd.get("wlpage")));
        Integer size = Integer.parseInt(String.valueOf(pd.get("wlsize")));
        if (null == page) {
            page = 0;
            pagebefore = 0;
        }
        if (size==null) {
            size = 10;
            pageafter = 10;
        }
        pagebefore = page * size;
        pageafter = (page+1) * size;
        pd.put("pagebefore",pagebefore);
        pd.put("pageafter",pageafter);
        return dao.queryWlxxByXmid(pd);
    }

    @Override
    public List<Map<String, Object>> queryXdfwJtByXmid(Map<String, Object> pd) {
        return dao.queryXdfwJtByXmid(pd);
    }

    @Override
    public List<Map<String, Object>> queryLdJtByXmid(Map<String, Object> pd) {
        return dao.queryLdJtByXmid(pd);
    }

    @Override
    public void hbSubmit(Map<String, Object> pd) {
        dao.hbSubmit(pd);
    }

    @Override
    public void hbEditSubmit(Map<String, Object> pd) {
        dao.hbEditSubmit(pd);
    }

    @Override
    public void dbSubmit(Map<String, Object> pd) {
        dao.dbSubmit(pd);
    }

    @Override
    public void dbEditSubmit(Map<String, Object> pd) {
        dao.dbEditSubmit(pd);
    }

    @Override
    public void wbSubmit(Map<String, Object> pd) {
        dao.wbSubmit(pd);
    }

    @Override
    public void wbEditSubmit(Map<String, Object> pd) {
        dao.wbEditSubmit(pd);
    }

    @Override
    public List<Map<String,Object>> findXmfw(Map<String, Object> pd) {
       return dao.findXmfw(pd);
    }

    @Override
    public void dbdelete(Map<String, Object> pd) {
        dao.dbdelete(pd);
    }

    @Override
    public void wbdelete(Map<String, Object> pd) {
        dao.wbdelete(pd);
    }

    @Override
    public void saveSgxtRwb(SgxtRwbEntity sgxtRwbEntity) {
        sgxtRwbRepository.save(sgxtRwbEntity);
    }

    @Override
    public void saveSgxtRwjl(SgxtRwJlfwEntity sgxtRwJlfwEntity) {
        sgxtRwJLFWRepository.save(sgxtRwJlfwEntity);
    }

    @Override
    public void saveWfRuInst(WfRuInstEntity instEntity) {
        instRepository.save(instEntity);
    }

    @Override
    public void saveWfRuInstContent(WfRuInstContentEntity wfRuInstContentEntity) {
        instContentRepository.save(wfRuInstContentEntity);
    }

    @Override
    public void saveWfTaskHistory(WfTaskHistoryEntity historyEntity) {
        taskHistoryRepository.save(historyEntity);
    }

    @Override
    public List<WfReNodeEntity> findByActivityidAndDirection(String activityid, String direction) {
        return nodeRepository.findByActivityidAndDirection(activityid,direction);
    }

    @Override
    public WfDfActivityEntity findByActivityid(String activityid) {
        return activityRepository.findByActivityid(activityid);
    }

    @Override
    public void saveWfTask(WfTaskEntity task) {
        taskRepository.save(task);
    }

    @Override
    public List<Map<String, Object>> queryAllWjjlbByXmid(Map<String, Object> pd) {
        List<Map<String, Object>> list = dao.findWjjlbAll(pd);
        return list;
    }

    @Override
    public List<Map<String, Object>> queryAllDbByXmid(Map<String, Object> pd) {
        List<Map<String, Object>> list = dao.findDbAll(pd);
        return list;
    }

    @Override
    public List<Map<String, Object>> queryAllHbByXmid(Map<String, Object> pd) {
        List<Map<String, Object>> list = dao.findHbAll(pd);
        return list;
    }

    @Override
    public List<Map<String, Object>> queryAllWlxxByXmid(Map<String, Object> pd) {
        List<Map<String, Object>> list = dao.findWlxxAll(pd);
        return list;
    }

    @Override
    public List<Map<String, Object>> queryJlfwByXmid(Map<String, Object> pd) {
        return dao.queryJlfwByXmid(pd);
    }

    @Override
    public List<Map<String, Object>> querySgxdByXmid(Map<String, Object> pd) {
        return dao.querySgxdByXmid(pd);
    }

    @Override
    public List<Map<String, Object>> queryPdefId(String id,String lcscId) {
        return dao.queryPdefId(id,lcscId);
    }

    @Override
    public List<Map<String, Object>> queryPdefIdAndBmmc(String id) {
        return dao.queryPdefIdAndBmmc(id);
    }

    @Override
    public WfTaskEntity findBycActivityidAndProcInstId(String cActivityid, String procInstId) {
        return taskRepository.findBycActivityidAndProcInstId(cActivityid,procInstId);
    }

    @Override
    public List<WfTaskEntity> findByProcInstId(String procInstId) {
        return dao.findByProcInstId(procInstId);
    }

    @Override
    public void deleteTask(String pdefId,String procInstId) {
        dao.deleteTask(pdefId,procInstId);
    }

    @Override
    public void deleteTasks(String pdefId,String procInstId,String refid) {
        dao.deleteTasks(pdefId,procInstId,refid);
    }

    @Override
    public WfRuInstEntity findByprocInstId(String procInstId) {
        return instRepository.findByprocInstId(procInstId);
    }

    @Override
    public void savewfHiInst(WfHiInstEntity wfHiInstEntity) {
        wfHiInstRepository.save(wfHiInstEntity);
    }

    @Override
    public void deleteInst(String procInstId) {
        dao.deleteInst(procInstId);
    }

    @Override
    public WfRuInstContentEntity findByProcInstIdAndContentname(String procInstId, String contentvalue) {
        return instContentRepository.findByProcInstIdAndContentname(procInstId,contentvalue);
    }

    @Override
    public void saveWfHiInstContent(WfHiInstContentEntity wfHiInstContentEntity) {
        wfHiInstContentRepository.save(wfHiInstContentEntity);
    }

    @Override
    public void deleteInstContent(String procInstId, String id) {
        dao.deleteInstContent(procInstId,id);
    }

    @Override
    public List<Map<String, Object>> getZpry(Map<String, Object> pd) {
        return dao.getZpry(pd);
    }

    @Override
    public WfTaskEntity findBycActivityidAndRefid(String pdefId, String refid) {
        return taskRepository.findBycActivityidAndRefid(pdefId,refid);
    }

    @Override
    public void updateShzt(Map<String, Object> pd) {
        dao.updateShzt(pd);
    }

    @Override
    public void updateShth(Map<String, Object> pd) {
        dao.updateShth(pd);
    }

    @Override
    public void updateZmgzry(Map<String, Object> pd) {
        dao.updateZmgzry(pd);
    }

    @Override
    public void updateRwzt(Map<String, Object> pd) {
        dao.updateRwzt(pd);
    }

    @Override
    public String texuChange(Map<String, Object> map) {
        return dao.texuChange(map);
    }

    @Override
    public void updateWfruInst(String procInstId, String pdef_id) {
        dao.updateWfruInst(procInstId,pdef_id);
    }

    @Override
    public String getUserIdByActivityId(String activityId) {
        return dao.getUserIdByActivityId(activityId);
    }

    @Override
    public List<Map<String, Object>> getSgrwdById(String sgdid) {
        return dao.getSgrwdById(sgdid);
    }

    @Override
    public List<GetXmlbSgEntity> getSgxdsList(Map<String, Object> pd) {
        Integer pagebefor = 1;
        Integer pageafter = 1;
        Integer page = Integer.parseInt(String.valueOf(pd.get("page")));
        Integer size = Integer.parseInt(String.valueOf(pd.get("size")));
        if (null == page) {
            page = 0;
            pagebefor = 0;
        }
        if (size==null) {
            size = 10;
            pageafter = 10;
        }
        pagebefor = page * size;
        pageafter = (page+1) * size;
        pd.put("pagebefor",pagebefor);
        pd.put("pageafter",pageafter);
        return dao.getSgxdsList(pd);
    }

    @Override
    public List<GetXmlbSgEntity> getAllSgxdList(Map<String, Object> pd) {
        return dao.getAllSgxdList(pd);
    }

    @Override
    public List<WfReNodeEntity> findByActivityidAndNextnode(String activityid, String nextnode) {
        return nodeRepository.findByActivityidAndNextnode(activityid,nextnode);
    }

    @Override
    public List<String> queryGxsbidBySgdId(Map<String, Object> pd) {
        return dao.queryGxsbidBySgdId(pd);
    }

    @Override
    public List<Map<String,Object>> querySbByLdid(String ldid) {
        return dao.querySbByLdid(ldid);
    }

    @Override
    public List<Map<String, Object>> queryxmxxByid(String xmid) {
        return dao.queryxmxxByid(xmid);
    }

    @Override
    public List<Map<String, Object>> querySqlxByXmid(Map<String, Object> pd) {
        return dao.querySqlxByXmid(pd);
    }

    @Override
    public String getUserIdByroleId(String roleId) {
        return dao.getUserIdByroleId(roleId);
    }

    @Override
    public List<String> queryRwblr(String actId, String id, String procInstId) {
        return dao.queryRwblr(actId,id,procInstId);
    }

    @Override
    public List<XmInstance> getXmxxByid(String xmid) {
        return dao.getXmxxByid(xmid);
    }

    @Override
    public UserEntity getUserByUserId(String userid) {
        return dao.getUserByUserId(userid);
    }

    @Override
    public void saveDxDfsEntity(DxDfsEntity dxDfsEntity) {
        dxDfsRepository.saveAndFlush(dxDfsEntity);
    }

    @Override
    public void wbEditdelete(Map<String, Object> pd) {
        dao.wbEditdelete(pd);
    }

    @Override
    public void dbEditdelete(Map<String, Object> pd) {
        dao.dbEditdelete(pd);
    }

    @Override
    public void hbEditdelete(Map<String, Object> pd) {
        dao.hbEditdelete(pd);
    }

    @Override
    public List<Map<String, Object>> queryWjjlbByXmids(Map<String, Object> pd) {
        return dao.queryWjjlbByXmids(pd);
    }

    @Override
    public List<Map<String, Object>> queryDbByXmids(Map<String, Object> pd) {
        return dao.queryDbByXmids(pd);
    }

    @Override
    public List<Map<String, Object>> queryHbByXmids(Map<String, Object> pd) {
        return dao.queryHbByXmids(pd);
    }

    @Override
    public List<HtQdzbEntity> getHtxx(String xmid) {
        return dao.getHtxx(xmid);
    }

    @Override
    public List<UserEntity> getUserxx(String xmjl) {
        return dao.getUserxx(xmjl);
    }

    @Override
    public WfTaskEntity findByTaskid(String taskid) {
        return taskRepository.findByTaskid(taskid);
    }

    @Override
    public List<CodeModel> initLdhb(String xmid) {
        return dao.initLdhb(xmid);
    }

    @Override
    public List<XmSb> initDbpzTable(String xmId,String sgdid, String ldid) {
        return dao.initDbpzTable(xmId,sgdid,ldid);
    }

    @Override
    public List<XmSb> initDbpzTables(String xmId, String sgdid, String ldid) {
        return dao.initDbpzTables(xmId,sgdid,ldid);
    }

    @Override
    public List<String> querySgdidByxmid(String xmid) {
        return dao.querySgdidByxmid(xmid);
    }

    @Override
    public void deleteSgsb(String sgdid) {
        dao.deleteSgsb(sgdid);
    }

    @Override
    public List<GdModel> getGdList(String xmid,String sgdid) {
        return dao.getGdList(xmid,sgdid);
    }



    @Override
    public void gwdelete(Map<String, Object> pd) {
        dao.gwdelete(pd);
    }

    @Override
    public void insertGw(Map<String, Object> pd) {
        dao.insertGw(pd);
    }


    public ExampleMatcher EX(int  i){
        if (i == 1){//精确
            ExampleMatcher matching = ExampleMatcher.matching()
                    .withStringMatcher(ExampleMatcher.StringMatcher.EXACT)
                    .withIgnoreCase(true);
            return  matching;
        }else if (i == 2){ //模糊
            ExampleMatcher matching = ExampleMatcher.matching()
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                    .withIgnoreCase(true);
            return  matching;
        }
        return null;
    }
}
