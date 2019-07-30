package com.ljx.hfgsjt.js.service.Impl;

import com.ljx.hfgsjt.entity.dto.Activity.WfActivityRoleEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.InstContentEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.InstEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.TaskEntity;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.js.*;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.dto.yswt.YsRwcb;
import com.ljx.hfgsjt.entity.dto.yswt.YsRwzb;
import com.ljx.hfgsjt.entity.dto.zhys.XmUserEntity;
import com.ljx.hfgsjt.js.mapper.JssqMapper;
import com.ljx.hfgsjt.js.service.JssqService;
import com.ljx.hfgsjt.repository.message.DxDfsRepository;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.ljx.hfgsjt.util.utils.StringUtil.isEmpty;


@Service
public class JssqServiceImpl implements JssqService {

    @Autowired
    private JssqMapper jssqMapper;
    @Autowired
    private DxDfsRepository dxDfsRepository;

   // @Autowired
  //  private JszbRepository repository;

    @Override
    public int selectJssqCount(Jssq jssq) {
        return jssqMapper.selectJssqCount(jssq);
    }

    @Override
    public List<Jssq> selectJssqList(Jssq jssq) {
        return jssqMapper.selectJssqList(jssq);
    }

    @Override
    public ys_rucb selectYshz( String flbh , String xmid) {
        return jssqMapper.selectYshz(flbh,xmid);
    }

    @Override
    public int selectBzyhjssCount(Jssq jssq) {
        return jssqMapper.selectBzyhjssCount(jssq);
    }

    @Override
    public List<Jssq> selectBzyhjssList(Jssq jssq) {
        return jssqMapper.selectBzyhjssList(jssq);
    }

    @Override
    public int selectJszlCount(String xmid) {
        return jssqMapper.selectJszlCount(xmid);
    }

    @Override
    public List<JsRwFj> selectJszlList(Map<String, Object> map) {
        return jssqMapper.selectJszlList(map);
    }

    @Override
    public void insertJszl(JsRwFj jsRwFj) {
        jssqMapper.insertJszl(jsRwFj);
    }

    @Override
    public void insertRwzb(JsRwZb jsRwZb) {
        jssqMapper.insertRwzb(jsRwZb);
    }

    @Override
    public JsRwZb selectRwzb(String xmid) {
        return jssqMapper.selectRwzb(xmid);
    }

    @Override
    public void updataRwzb(JsRwZb jsRwZb) {
        jssqMapper.updataRwzb(jsRwZb);
    }

    @Override
    public JsRwCb selectRwcb(String zbid, String jsfl) {
        return jssqMapper.selectRwcb(zbid,jsfl);
    }

    @Override
    public void updatRwcb(JsRwCb jsRwCb) {
        jssqMapper.updatRwcb(jsRwCb);
    }

    @Override
    public void delectRwfj(String id) {
        jssqMapper.delectRwfj(id);
    }

    @Override
    public void delectRwZb(String xmid) {
        jssqMapper.delectRwZb(xmid);
    }

//    @Override
//    public void saveJszb(JsRwZb jsRwZb) {
//        repository.save(jsRwZb);
//    }

    @Override
    public void insertJsRwcb(JsRwCb jsRwCb) {
        jssqMapper.insertJsRwcb(jsRwCb);
    }

    @Override
    public List<JsRwFj> selectJsFjList(String xmid) {
        return jssqMapper.selectJsFjList(xmid);
    }

    @Override
    public void updateJsFj(JsRwFj jsRwFj) {
        jssqMapper.updateJsFj(jsRwFj);
    }

    @Override
    public void updataInst(InstEntity instEntity) {
        jssqMapper.updataInst(instEntity);
    }
    @Override
    public void updataTask(TaskEntity taskEntity) {
        jssqMapper.updataTask(taskEntity);
    }

    @Override
    public InstContentEntity getRuInstContent(String pdef_id, String xmid) {
        return jssqMapper.getRuInstContent(pdef_id,xmid);
    }

    @Override
    public InstEntity getRuInst(String xmid) {
        return jssqMapper.getRuInst(xmid);
    }

    @Override
    public TaskEntity getTask(String xmid) {
        return jssqMapper.getTask(xmid);
    }

    @Override
    public void insertTask(TaskEntity taskEntity) {
        jssqMapper.insertTask(taskEntity);
    }

    @Override
    public void insertHiTask(TaskEntity taskEntity) {
        jssqMapper.insertHiTask(taskEntity);
    }

    @Override
    public void insetHiinstContent(InstContentEntity instContentEntity) {
        jssqMapper.insetHiinstContent(instContentEntity);
    }

    @Override
    public void insertHiInst(InstEntity instEntity) {
        jssqMapper.insertHiInst(instEntity);
    }

    @Override
    public void deleteRuInstContent(String prodid) {
        jssqMapper.deleteRuInstContent(prodid);
    }

    @Override
    public void deleteRuInst(String prodid) {
        jssqMapper.deleteRuInst(prodid);
    }

    @Override
    public void deleteTask(String prodid) {
        jssqMapper.deleteTask(prodid);
    }

    @Override
    public YsRwzb selectYszbid(String xmid) {
        return jssqMapper.selectYszbid(xmid);
    }
    @Override
    public List<YsRwcb> getYsRwcb(String zbid) {

        List<YsRwcb> all = jssqMapper.findAll(zbid);
        return all;
    }

    @Override
    public List<JsRwCb> GetJsmc(String zbid) {
        return jssqMapper.GetJsmc(zbid);
    }

    @Override
    public void setYsBz(Map map) {

    }

    @Override
    public void delectRwCb(String zbid) {
        jssqMapper.delectRwCb(zbid);
    }

    @Override
    public List<XmUserEntity> getJsmrr(String pdefid) {
        return jssqMapper.getJsmrr(pdefid);
    }

    @Override
    public XmInstanceEntity getXmInstanceByXmid(String xmid) {
        return jssqMapper.getXmInstanceByXmid(xmid);
    }

    @Override
    public void saveDxDfsEntity(DxDfsEntity dxDfsEntity) {
        dxDfsRepository.saveAndFlush(dxDfsEntity);
    }

    @Override
    public WfActivityRoleEntity getWfActivityRoleByActivity(String Activity) {
        return jssqMapper.getWfActivityRoleByActivity(Activity);
    }

    @Override
    public UserEntity getUserByActivityid(String Activityid) {
        return jssqMapper.getUserByActivityid(Activityid);
    }


}
