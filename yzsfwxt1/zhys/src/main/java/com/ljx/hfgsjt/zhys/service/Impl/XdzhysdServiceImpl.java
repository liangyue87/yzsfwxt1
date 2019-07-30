package com.ljx.hfgsjt.zhys.service.Impl;

import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.Sjst.XzqyEntity;
import com.ljx.hfgsjt.entity.dto.zhys.XmUserEntity;
import com.ljx.hfgsjt.entity.dto.zhys.ZhysRwcbEntity;
import com.ljx.hfgsjt.entity.dto.zhys.ZhysRwcbFjEntity;
import com.ljx.hfgsjt.entity.dto.zhys.ZhysRwzbEntity;
import com.ljx.hfgsjt.repository.zhys.ZhysRwcbFjRepository;
import com.ljx.hfgsjt.repository.zhys.ZhysRwcbRepository;
import com.ljx.hfgsjt.repository.zhys.ZhysRwzbRepository;
import com.ljx.hfgsjt.zhys.mapper.XdzhysdMapper;
import com.ljx.hfgsjt.zhys.service.XdzhysdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class XdzhysdServiceImpl implements XdzhysdService {

    @Autowired
    private XdzhysdMapper xdzhysdMapper;

    @Autowired
    private ZhysRwzbRepository zhysRwzbRepository;

    @Autowired
    private ZhysRwcbRepository zhysRwcbRepository;

    @Autowired
    private ZhysRwcbFjRepository zhysRwcbFjRepository;

    @Override
    public XzqyEntity getQsmc(String qsbh) {
        return xdzhysdMapper.getQsmc(qsbh);
    }

    @Override
    public int selectBmzl(String zhyscbid) {
        return xdzhysdMapper.selectBmzl(zhyscbid);
    }

    @Override
    public List<ZhysRwcbFjEntity> selectBmzllist(Map<String, Object> map) {
        return xdzhysdMapper.selectBmzllist(map);
    }

    @Override
    public XmInstance selectRwzbid(String xmid) {
        return xdzhysdMapper.selectRwzbid(xmid);
    }

    @Override
    public XmUserEntity getQs(String role) {
        return xdzhysdMapper.getQs(role);
    }

    @Override
    public XmUserEntity getUser(String role) {
        return xdzhysdMapper.getUser(role);
    }

    @Override
    public int selectXdzhysdCount(Map<String, Object> map) {
        return xdzhysdMapper.selectXdzhysdCount(map);
    }

    @Override
    public List<XmInstance> selectXdzhysdList(Map<String, Object> map) {
        return xdzhysdMapper.selectXdzhysdList(map);
    }

    @Override
    public void saveZhysRwzb(ZhysRwzbEntity zhysRwzbEntity) {
        zhysRwzbRepository.save(zhysRwzbEntity);
    }

    @Override
    public void saveZhysRwcb(ZhysRwcbEntity zhysRwcbEntity) {
        zhysRwcbRepository.save(zhysRwcbEntity);
    }

    @Override
    public List<Map<String, Object>> getZpry(Map<String, Object> pd) {
        return xdzhysdMapper.getZpry(pd);
    }

    @Override
    public void updateCbBmcyry(Map<String, Object> pd) {
        xdzhysdMapper.updateCbBmcyry(pd);
    }

    @Override
    public void upoadFile(Map<String, Object> pd) {
        xdzhysdMapper.upoadFile(pd);
    }

    @Override
    public List<Map<String, Object>> findZhyscbidByBmAndId(Map<String, Object> pd) {
        return xdzhysdMapper.findZhyscbidByBmAndId(pd);
    }

    @Override
    public void updateCbBmfkyj(Map<String, Object> pd) {
        xdzhysdMapper.updateCbBmfkyj(pd);
    }

    @Override
    public List<Map<String, Object>> queryRoleIdByUserId(Map<String, Object> pd) {
        return xdzhysdMapper.queryRoleIdByUserId(pd);
    }

    @Override
    public List<Map<String, Object>> getFkyj(Map<String, Object> pd) {
        return xdzhysdMapper.getFkyj(pd);
    }

    @Override
    public void updateZbXmjlfkyj(Map<String, Object> pd) {
        xdzhysdMapper.updateZbXmjlfkyj(pd);
    }

    @Override
    public void updateZbXmjlfkyjCh(Map<String, Object> pd) {
        xdzhysdMapper.updateZbXmjlfkyjCh(pd);
    }

    @Override
    public List<Map<String, Object>> getFkzl(Map<String, Object> pd) {
        return xdzhysdMapper.getFkzl(pd);
    }

    @Override
    public List<Map<String,Object>> queryRoleIdByActivityId(String flag) {
        return xdzhysdMapper.queryRoleIdByActivityId(flag);
    }

    @Override
    public List<ZhysRwcbFjEntity> getFkFjData(String cbid, String yj, String ej) {
        return xdzhysdMapper.getFkFjData(cbid,yj,ej);
    }

    @Override
    public void deleteFkFj(String fjid) {
        xdzhysdMapper.deleteFkFj(fjid);
    }

}
