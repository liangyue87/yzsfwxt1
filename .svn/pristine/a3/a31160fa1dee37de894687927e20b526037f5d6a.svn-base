package com.ljx.hfgsjt.sfkp.serviceImpl;

import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.Sjst.XmSb;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.htqd.GetXmlbJfEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.sfkp.SfkpSftzdzbEntity;
import com.ljx.hfgsjt.entity.sfkp.SfkpldEntity;
import com.ljx.hfgsjt.repository.message.DxDfsRepository;
import com.ljx.hfgsjt.sfkp.dao.SfkpMapper;
import com.ljx.hfgsjt.sfkp.service.SfkpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SfkpServiceImpl implements SfkpService {

    @Autowired
    private SfkpMapper dao;
    @Autowired
    private DxDfsRepository dxDfsRepository;

    @Override
    public List<Map<String, Object>> getSftzd(Map<String,Object> pd) {
        return dao.getSftzd(pd);
    }

    @Override
    public List<Map<String, Object>> getSkzh() {
        return dao.getSkzh();
    }

    @Override
    public List<Map<String, Object>> getGcflm() {
        return dao.getGcflm();
    }

    @Override
    public void insertSftzdcb(Map<String, Object> cbpd) {
        dao.insertSftzdcb(cbpd);
    }

    @Override
    public void insertSftzdzb(Map<String, Object> pd) {
        dao.insertSftzdzb(pd);
    }

    @Override
    public void updateSftzd(Map<String, Object> pd) {
        dao.updateSftzd(pd);
    }

    @Override
    public List<GetXmlbJfEntity> getJfList(Map<String, Object> pd) {
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
        return dao.getJfList(pd);
    }

    @Override
    public List<GetXmlbJfEntity> getAllJfList(Map<String, Object> pd) {
        return dao.getAllJfList(pd);
    }

    @Override
    public List<Map<String, Object>> getZbkjList() {
        return dao.getZbkjList();
    }

    @Override
    public List<XmSb> queryGsbByXmid(Map<String, Object> pd) {
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
        return dao.queryGsbByXmid(pd);
    }

    @Override
    public List<XmSb> queryAllGsbByXmid(Map<String, Object> pd) {
        return dao.queryAllGsbByXmid(pd);
    }

    @Override
    public List<XmSb> queryGsbByXmids(Map<String, Object> pd) {
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
        return dao.queryGsbByXmids(pd);
    }

    @Override
    public List<XmSb> queryAllGsbByXmids(Map<String, Object> pd) {
        return dao.queryAllGsbByXmids(pd);
    }

    @Override
    public void updateSbgcfBySbid(Map<String, Object> pd) {
        dao.updateSbgcfBySbid(pd);
    }

    @Override
    public void gspldjxg(Map<String, Object> pd) {
        dao.gspldjxg(pd);
    }

    @Override
    public List<Map<String, Object>> gsjezj(Map<String, Object> pd) {
        return dao.gsjezj(pd);
    }

    @Override
    public List<Map<String, Object>> hbjezj(Map<String, Object> pd) {
        return dao.hbjezj(pd);
    }

    @Override
    public List<SfkpldEntity> queryLdByxmid(String xmid) {
        return dao.queryLdByxmid(xmid);
    }

    @Override
    public List<Map<String, Object>> querymxByxmid(String xmid) {
        return dao.querymxByxmid(xmid);
    }

    @Override
    public List<XmSb> querySbByldid(String xmid, String ldid) {
        return dao.querySbByldid(xmid,ldid);
    }

    @Override
    public void insertSftzdcbsb(Map<String, Object> pd) {
        dao.insertSftzdcbsb(pd);
    }

    @Override
    public void deleteSfsbByldid(Map<String, Object> pd) {
        dao.deleteSfsbByldid(pd);
    }

    @Override
    public List<XmSb> queryHb(Map<String, Object> pd) {
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
        return dao.queryHb(pd);
    }

    @Override
    public List<XmSb> queryallHb(Map<String, Object> pd) {
        return dao.queryallHb(pd);
    }

    @Override
    public List<XmSb> queryAllHb(Map<String, Object> pd) {
        return dao.queryAllHb(pd);
    }

    @Override
    public List<Map<String, Object>> queryLd(Map<String, Object> pd) {
        return dao.queryLd(pd);
    }

    @Override
    public void updateJfcbgcfBySbid(Map<String, Object> pd) {
        dao.updateJfcbgcfBySbid(pd);
    }

    @Override
    public void deleteJfhb() {
        dao.deleteJfhb();
    }

    @Override
    public List<Map<String, Object>> gssbje(Map<String, Object> pd) {
        return dao.gssbje(pd);
    }

    @Override
    public void saveJfgssb(Map<String, Object> pd) {
        dao.saveJfgssb(pd);
    }

    @Override
    public List<String> querybhBymc(Map<String, Object> cbpd) {
        return dao.querybhBymc(cbpd);
    }

    @Override
    public void updateJfcbgsb(Map<String, Object> cbpd) {
        dao.updateJfcbgsb(cbpd);
    }

    @Override
    public void updateJfcbhb(Map<String, Object> cbpd) {
        dao.updateJfcbhb(cbpd);
    }

    @Override
    public void deleteJfgsb() {
        dao.deleteJfgsb();
    }

    @Override
    public List<SfkpSftzdzbEntity> queryByTzdid(String tzdid) {
        return dao.queryByTzdid(tzdid);
    }

    @Override
    public List<Map<String, Object>> queryCbByTzdid(String tzdid) {
        return dao.queryCbByTzdid(tzdid);
    }

    @Override
    public void deleteSftzdcb(Map<String, Object> pd) {
        dao.deleteSftzdcb(pd);
    }

    @Override
    public void updateSftzdzb(Map<String, Object> pd) {
        dao.updateSftzdzb(pd);
    }

    @Override
    public List<Map<String, Object>> querylxByname(Map<String, Object> pd) {
        return dao.querylxByname(pd);
    }

    @Override
    public List<Map<String, Object>> querylxBynames(Map<String, Object> pd) {
        return dao.querylxBynames(pd);
    }

    @Override
    public List<Map<String, Object>> querypjlx() {
        return dao.querypjlx();
    }

    @Override
    public void importPz(Map<String, Object> pd) {
        dao.importPz(pd);
    }

    @Override
    public List<XmInstanceEntity> findXmByid(Map<String, Object> pd) {
        return dao.findXmByid(pd);
    }

    @Override
    public List<XmInstanceEntity> getjbxx(String xmid) {
        return dao.getjbxx(xmid);
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
    public UserEntity getUserByUserId(String userid) {
        return dao.getUserByUserId(userid);
    }

    @Override
    public void saveDxDfsEntity(DxDfsEntity dxDfsEntity) {
        dxDfsRepository.saveAndFlush(dxDfsEntity);
    }

    @Override
    public void ExecAfterJfqr(Map<String, Object> map) {
        dao.ExecAfterJfqr(map);
    }

    @Override
    public List<Map<String, Object>> querySfzbByid(String zbid) {
        return dao.querySfzbByid(zbid);
    }

    @Override
    public List<XmInstance> getxmdz(String xmid) {
        return dao.getxmdz(xmid);
    }

    @Override
    public List<String> queryGsCbid(String id) {
        return dao.queryGsCbid(id);
    }

    @Override
    public List<String> queryJmCbid(String id) {
        return dao.queryJmCbid(id);
    }

    @Override
    public void deleteSftzdcbsb(String cbid,String sblx) {
        dao.deleteSftzdcbsb(cbid,sblx);
    }

    @Override
    public List<String> querygssbid(String cbid) {
        return dao.querygssbid(cbid);
    }

    @Override
    public List<String> queryjmsbid(String cbid) {
        return dao.queryjmsbid(cbid);
    }

    @Override
    public void updategssbcbid(String sbid,String cbid) {
        dao.updategssbcbid(sbid,cbid);
    }

    @Override
    public void updatejmsbcbid(String sbid,String cbid) {
        dao.updatejmsbcbid(sbid,cbid);
    }

    @Override
    public List<XmInstance> findXmByxmid(String xmid) {
        return dao.findXmByxmid(xmid);
    }

    @Override
    public List<Map<String, Object>> findSftzd(String xmid) {
        return dao.findSftzd(xmid);
    }

    @Override
    public List<Map<String, Object>> getGsbTotalByXmid(String xmid, String id) {
        return dao.getGsbTotalByXmid(xmid,id);
    }

}
