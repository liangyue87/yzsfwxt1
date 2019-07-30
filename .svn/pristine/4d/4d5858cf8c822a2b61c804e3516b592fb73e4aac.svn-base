package com.ljx.hfgsjt.sjst.service.Impl;

import com.ljx.hfgsjt.entity.dto.Activity.WfRuInstEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.*;

import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.dto.zhys.XmUserEntity;
import com.ljx.hfgsjt.repository.message.DxDfsRepository;
import com.ljx.hfgsjt.repository.sjst.GwxxFkRepository;
import com.ljx.hfgsjt.sjst.mapper.GwxxMapper;
import com.ljx.hfgsjt.sjst.service.GwxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GwxxServiceImpl implements GwxxService {
    @Autowired
    private GwxxMapper gwxxmapper;

    @Autowired
    private GwxxFkRepository gwxxFkRepository;
    @Autowired
    private DxDfsRepository dxDfsRepository;

    @Override
    public int getGwxxCount(NewsGwxx gwxx){
        return gwxxmapper.getGwxxCount(gwxx);
    }
    @Override
    public List<NewsGwxx> getGwxxList(NewsGwxx gwxx){
        return gwxxmapper.getGwxxList(gwxx);
    }

    @Override
    public XmInstance getXzqyDz(String  xmid){
        return gwxxmapper.getXzqyDz(xmid);
    }

    @Override
    public XzqyEntity getGssName(String  qybh){
        return gwxxmapper.getGssName(qybh);
    }

    @Override
    public XmInstance selectXminstance(String xmid) {
        return gwxxmapper.selectXminstance(xmid);
    }

    @Override
    public int updateXminstance(XmInstance xmInstance){
        return gwxxmapper.updateXminstance(xmInstance);
    }

    @Override
    public void insertHsjglrFj(HsjglrFj hsjglrFj) {
        gwxxmapper.insertHsjglrFj(hsjglrFj);
    }

    @Override
    public int getHsjgCount(String xmid ) {
        return gwxxmapper.getHsjgCount(xmid);
    }

    @Override
    public List<HsjglrFj> getHsjgLsit(Map<String, Object> map) {
        return gwxxmapper.getHsjgLsit(map);
    }

    @Override
    public void delectHsfj(String id) {
        gwxxmapper.delectHsfj(id);
    }

    @Override
    public List<HsjglrFj> selectHsFj(String fjlx) {
        return gwxxmapper.selectHsFj(fjlx);
    }

    @Override
    public void updataHsFj(HsjglrFj hsjglrFj) {
        gwxxmapper.updataHsFj(hsjglrFj);
    }

    @Override
    public void insertHsiglr(HsjglrZb hsjglrZb) {
        gwxxmapper.insertHsiglr(hsjglrZb);
    }

    @Override
    public HsjglrZb getHsjglr(String xmid) {
        return gwxxmapper.getHsjglr(xmid);
    }

    @Override
    public int getGwxxFqCount(NewsGwxx gwxx) {
        return gwxxmapper.getGwxxFqCount(gwxx);
    }

    @Override
    public List<NewsGwxx> getGwxxFqList(NewsGwxx gwxx) {
        return gwxxmapper.getGwxxFqList(gwxx);
    }

    @Override
    public List<GwxxdcCb> selectGwxxdcCBList(String xmid) {
        return gwxxmapper.selectGwxxdcCBList(xmid);
    }

    @Override
    public void insertGwxxZb(GwxxdcZb gwxxdcZb) {
        gwxxmapper.insertGwxxZb(gwxxdcZb);
    }

    @Override
    public int getHsjglrCount(NewsGwxx gwxx) {
        return gwxxmapper.getHsjglrCount(gwxx);
    }

    @Override
    public List<NewsGwxx> getHsjglrList(NewsGwxx gwxx) {
        return gwxxmapper.getHsjglrList(gwxx);
    }


    @Override
    public InstContentEntity getRuInstContent(String xmid) {
        return gwxxmapper.getRuInstContent(xmid);
    }
    @Override
    public void insetHiinstContent(InstContentEntity instContentEntity) {
         gwxxmapper.insetHiinstContent(instContentEntity);
    }
    @Override
    public InstEntity getRuInst(String xmid) {
        return gwxxmapper.getRuInst(xmid);
    }
    @Override
    public void insertHiInst(InstEntity instEntity) {
        gwxxmapper.insertHiInst(instEntity);
    }
    @Override
    public TaskEntity getTask(String xmid) {
        return gwxxmapper.getTask(xmid);
    }
    @Override
    public void insertTask(TaskEntity taskEntity) {
        gwxxmapper.insertTask(taskEntity);
    }
    @Override
    public void insertHiTask(TaskEntity taskEntity) {
        gwxxmapper.insertHiTask(taskEntity);
    }
    @Override
    public void updataInst(InstEntity instEntity) {
        gwxxmapper.updataInst(instEntity);
    }
    @Override
    public void updataTask(TaskEntity taskEntity) {
        gwxxmapper.updataTask(taskEntity);
    }

    @Override
    public void deleteRuInstContent(String prodid) {
        gwxxmapper.deleteRuInstContent(prodid);
    }
    @Override
    public void deleteRuInst(String prodid) {
        gwxxmapper.deleteRuInst(prodid);
    }
    @Override
    public void deleteTask(String prodid) {
        gwxxmapper.deleteTask(prodid);
    }
    @Override
    public void deleteGwxxZb(String xmid) {
        gwxxmapper.deleteGwxxZb(xmid);
    }
    @Override
    public void deleteGwxxCb(String zbid) {
        gwxxmapper.deleteGwxxCb(zbid);
    }

    @Override
    public InstEntity selectRuInst(String pdef_id, String xmid) {
        return gwxxmapper.selectRuInst(pdef_id,xmid);
    }
    @Override
    public TaskEntity selectTask(String pdef_id, String xmid) {
        return gwxxmapper.selectTask(pdef_id,xmid);
    }

    @Override
    public String texuChange(Map<String, Object> map) {
        return gwxxmapper.texuChange(map);
    }

    @Override
    public XmUserEntity getGwmrr(String pdefid) {
        return gwxxmapper.getGwmrr(pdefid);
    }

    @Override
    public XmInstanceEntity getXmInstanceByXmid(String xmid) {
        return gwxxmapper.getXmInstanceByXmid(xmid);
    }

    @Override
    public UserEntity getUserByqs(String qs) {
        return gwxxmapper.getUserByqs(qs);
    }

    @Override
    public void saveDxDfsEntity(DxDfsEntity dxDfsEntity) {
        dxDfsRepository.saveAndFlush(dxDfsEntity);
    }

    @Override
    public WfRuInstEntity getWfRuInstByXmid(String xmid) {
        return gwxxmapper.getWfRuInstByXmid(xmid);
    }

    @Override
    public UserEntity getUserByUserid(String xmfzr) {
        return gwxxmapper.getUserByUserid(xmfzr);
    }

    @Override
    public int getdxjsCount(Dx_dypz_lb dxDypzLb) {
        return gwxxmapper.getdxjsCount(dxDypzLb);
    }

    @Override
    public List<Dx_dypz_lb> getdxjsList(Dx_dypz_lb dxDypzLb) {
        return gwxxmapper.getdxjsList(dxDypzLb);
    }

    @Override
    public List<Dx_dypz_lb> getUserDy(String userid) {
        return gwxxmapper.getUserDy(userid);
    }

    @Override
    public void deleteUserDy(String ywlx, String dxlx, String activityid, String userid) {
        gwxxmapper.deleteUserDy(ywlx,dxlx,activityid,userid);
    }

    @Override
    public UserEntity getDquser(String userid) {
        return gwxxmapper.getDquser(userid);
    }

    @Override
    public void insertDydx(Dx_dypz_lb dxDypzLb) {
         gwxxmapper.insertDydx(dxDypzLb);
    }

    @Override
    public GwxxdcZb selectGwxxdcZB(String  xmid) {
        return gwxxmapper.selectGwxxdcZB(xmid);
    }

    @Override
    public void saveGwxxFq(GwxxdcCb gwxxdcCb) {
        gwxxFkRepository.save(gwxxdcCb);
    }

    @Override
    public void deleteGwxxFq(String id) {
        gwxxFkRepository.deleteById(id);
    }
}
