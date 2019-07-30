package com.ljx.hfgsjt.htqd.serviceImpl;

import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.htqd.*;
import com.ljx.hfgsjt.htqd.dao.HtbzMapper;
import com.ljx.hfgsjt.htqd.service.HtbzService;
import com.ljx.hfgsjt.repository.XmInstanceRepository;
import com.ljx.hfgsjt.repository.htqd.GetXmlbHtRepository;
import com.ljx.hfgsjt.repository.htqd.GetXmlbJfRepository;
import com.ljx.hfgsjt.repository.htqd.XtghHtmbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HtbzServiceImpl implements HtbzService {

    @Autowired
    private XmInstanceRepository repository;

    @Autowired
    private XtghHtmbRepository xtghHtmbRepository;

    @Autowired
    private GetXmlbHtRepository getXmlbHtRepository;

    @Autowired
    private GetXmlbJfRepository getXmlbJfRepository;

    @Autowired
    private HtbzMapper dao;


    @Override
    public List<GetXmlbHtEntity> getHtList(Map<String, Object> pd) {
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
        return dao.getHtList(pd);
    }

    @Override
    public List<GetXmlbHtEntity> getAllHtList(Map<String, Object> pd) {
        return dao.getAllHtList(pd);
    }

    @Override
    public List<GetHtxmEntity> findXmxxByXmbh(String xmbh) {
        return dao.findXmxxByXmbh(xmbh);
    }

    @Override
    public List<Map<String, Object>> queryZjqtfjById(Map<String, Object> pd) {
        return dao.queryZjqtfjById(pd);
    }

    @Override
    public void deleteFjByid(Map<String, Object> pd) {
        dao.deleteFjByid(pd);
    }

    @Override
    public List<XmInstanceEntity> queryxmxxByid(String xmid) {
        return dao.queryxmxxByid(xmid);
    }

    @Override
    public List<Map<String, Object>> queryYjsfzh() {
        return dao.queryYjsfzh();
    }

    @Override
    public List<JsfsEntity> queryJsfs() {
        return dao.queryJsfs();
    }

    @Override
    public List<Map<String, Object>> queryYslist(String xmid) {
        return dao.queryYslist(xmid);
    }

    @Override
    public void saveHt(Map<String, Object> pd) {
        dao.saveHt(pd);
    }

    @Override
    public void savefycb(Map<String, Object> cbpd) {
        dao.savefycb(cbpd);
    }

    @Override
    public String texuChange(Map<String, Object> map) {
        return dao.texuChange(map);
    }

    @Override
    public void saveFqb(FqbEntity fqbEntity) {
        dao.saveFqb(fqbEntity);
    }

    @Override
    public void saveFqbmx(FqmxbEntity fqmxbEntity) {
        dao.saveFqbmx(fqmxbEntity);
    }

    @Override
    public List<FqbEntity> findFqb(String htzbid) {
        return dao.findFqb(htzbid);
    }

    @Override
    public List<FqmxbEntity> findFqmxb(String fqzbid) {
        return dao.findFqmxb(fqzbid);
    }

    @Override
    public List<FqbEntity> getFqbByHtzbid(String htzbid) {
        return dao.getFqbByHtzbid(htzbid);
    }

    @Override
    public void deleteFqbByHtzbid(String htzbid) {
        dao.deleteFqbByHtzbid(htzbid);
    }

    @Override
    public void deleteFqmxbByFqzbid(String fqzbid) {
        dao.deleteFqmxbByFqzbid(fqzbid);
    }

    @Override
    public List<Map<String, Object>> queryHtbz(String xmid) {
        return dao.queryHtbz(xmid);
    }

    @Override
    public void updateHt(Map<String, Object> pd) {
        dao.updateHt(pd);
    }

    @Override
    public void deleteHtfy(Map<String, Object> pd) {
        dao.deleteHtfy(pd);
    }

    @Override
    public void updateXmlbtoXMb(Map<String, Object> pd) {
        dao.updateXmlbtoXMb(pd);
    }

    @Override
    public List<XmInstance> findXmxxByxmid(String xmid) {
        return dao.findXmxxByxmid(xmid);
    }

    @Override
    public List<XmInstanceEntity> findByXmbh(String xmbh) {
        return repository.findByXmbh(xmbh);
    }

    @Override
    public List<XtglHtmbEntity> findByMbbm(String mbbm) {
        return xtghHtmbRepository.findByMbbm(mbbm);
    }

    @Override
    public void upoadFile(Map<String, Object> pd) {
        dao.upoadFile(pd);
    }
}
