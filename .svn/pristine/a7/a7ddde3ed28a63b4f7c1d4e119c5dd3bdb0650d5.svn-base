package com.ljx.hfgsjt.yj.serviceImpl;

import com.ljx.hfgsjt.entity.dto.BaseEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.XmSb;
import com.ljx.hfgsjt.entity.yj.*;
import com.ljx.hfgsjt.repository.yj.GetXmlbSbyjRepository;
import com.ljx.hfgsjt.repository.yj.YjSbyjzbRepository;
import com.ljx.hfgsjt.yj.dao.SbyjMapper;
import com.ljx.hfgsjt.yj.service.SbyjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SbyjServiceImpl implements SbyjService {

    @Autowired
    private SbyjMapper dao;

    @Autowired
    private GetXmlbSbyjRepository getXmlbSbyjRepository;

    @Autowired
    private YjSbyjzbRepository yjSbyjzbRepository;

    private int total;

    @Override
    public List<azlxEntity> findAzlxBySllx(Map<String, Object> pd) {
        return dao.findAzlxBySllx(pd);
    }

    @Override
    public int getTotal() {
        return total;
    }

    @Override
    public List<GetXmlbSbyjEntity> getSbyjList(String xmbh, String xmmc, String gcdz, String azlx,
                                               Integer page, Integer size) {
        PageRequest request = PageRequest.of(page, size);
        GetXmlbSbyjEntity entity = new GetXmlbSbyjEntity();
        entity.setXmbh(xmbh);
        entity.setXmmc(xmmc);
        entity.setAzlx(azlx);
        entity.setXmdz(gcdz);
        ExampleMatcher matcher = EX(2);
        Example<GetXmlbSbyjEntity> ex = Example.of(entity, matcher);
        Page<GetXmlbSbyjEntity> all = getXmlbSbyjRepository.findAll(ex, request);
        this.total = (int) all.getTotalElements();
        List<GetXmlbSbyjEntity> list = all.getContent();
        return list;
    }

    @Override
    public List<GetXmlbSbyjEntity> getSbyjListBysj(Map<String, Object> pd) {
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
        return dao.getSbyjListBysj(pd);
    }

    @Override
    public List<GetXmlbSbyjEntity> queryAllSbyjLists(Map<String, Object> pd) {
        return dao.queryAllSbyjLists(pd);
    }

    @Override
    public List<YjSbyjzbEntity> findByXmid(String xmid) {
        return yjSbyjzbRepository.findByXmid(xmid);
    }

    @Override
    public List<XmSb> queryWblist(Map<String, Object> pd) {
        Integer pagebefor = 1;
        Integer pageafter = 1;
        Integer page = Integer.parseInt(String.valueOf(pd.get("wbpage")));
        Integer size = Integer.parseInt(String.valueOf(pd.get("wbsize")));
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
        return dao.queryWblist(pd);
    }

    @Override
    public List<XmSb> queryGsblist(Map<String, Object> pd) {
        Integer pagebefor = 1;
        Integer pageafter = 1;
        Integer page = Integer.parseInt(String.valueOf(pd.get("gsbpage")));
        Integer size = Integer.parseInt(String.valueOf(pd.get("gsbsize")));
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
        return dao.queryGsblist(pd);
    }

    @Override
    public List<Map<String, Object>> queryJMblist(Map<String, Object> pd) {
        Integer pagebefor = 1;
        Integer pageafter = 1;
        Integer page = Integer.parseInt(String.valueOf(pd.get("jmbpage")));
        Integer size = Integer.parseInt(String.valueOf(pd.get("jmbsize")));
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
        return dao.queryJMblist(pd);
    }

    @Override
    public List<XmSb> queryAllWblists(Map<String, Object> pd) {
        return dao.queryAllWblists(pd);
    }

    @Override
    public List<XmSb> queryAllGsblists(Map<String, Object> pd) {
        return dao.queryAllGsblists(pd);
    }

    @Override
    public List<Map<String, Object>> queryAllJmblists(Map<String, Object> pd) {
        return dao.queryAllJmblists(pd);
    }

    @Override
    public List<Map<String, Object>> getpqList(Map<String, Object> pd) {
        return dao.getpqList(pd);
    }

    @Override
    public List<XmSb> findJmbByLdid(Map<String, Object> pd) {
        return dao.findJmbByLdid(pd);
    }

    @Override
    public void elxedit(Map<String, Object> pd) {
        dao.elxedit(pd);
    }

    @Override
    public void pledit(Map<String, Object> pd) {
        dao.pledit(pd);
    }

    @Override
    public void updateBsmAndFqByBzh(Map<String, Object> pd) {
        dao.updateBsmAndFqByBzh(pd);
    }

    @Override
    public void sbyjLdSubmit(Map<String, Object> pd) {
        dao.sbyjLdSubmit(pd);
    }

    @Override
    public void sbyjDbSubmit(Map<String, Object> pd) {
        dao.sbyjDbSubmit(pd);
    }

    @Override
    public void saveSbyjzb(Map<String, Object> pd) {
        dao.saveSbyjzb(pd);
    }

    @Override
    public void deteleHbByYjdbh(Map<String, Object> pd) {
        dao.deteleHbByYjdbh(pd);
    }

    @Override
    public void deleteHbByLdid(Map<String, Object> pd) {
        dao.deleteHbByLdid(pd);
    }

    @Override
    public List<String> queryhbidByLdid(Map<String, Object> pd) {
        return dao.queryhbidByLdid(pd);
    }

    @Override
    public void ldhbSubmit(Map<String, Object> pd) {
        dao.ldhbSubmit(pd);
    }

    @Override
    public void updateCbhb(Map<String, Object> pd) {
        dao.updateCbhb(pd);
    }

    @Override
    public List<Map<String, Object>> getZpry(Map<String, Object> pd) {
        return dao.getZpry(pd);
    }

    @Override
    public List<Map<String, Object>> shWblist(Map<String, Object> pd) {
        Integer pagebefor = 1;
        Integer pageafter = 1;
        Integer page = Integer.parseInt(String.valueOf(pd.get("wbpage")));
        Integer size = Integer.parseInt(String.valueOf(pd.get("wbsize")));
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
        return dao.shWblist(pd);
    }

    @Override
    public List<Map<String, Object>> shAllWblists(Map<String, Object> pd) {
        return dao.shAllWblists(pd);
    }

    @Override
    public List<Map<String, Object>> shGsblist(Map<String, Object> pd) {
        Integer pagebefor = 1;
        Integer pageafter = 1;
        Integer page = Integer.parseInt(String.valueOf(pd.get("gsbpage")));
        Integer size = Integer.parseInt(String.valueOf(pd.get("gsbsize")));
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
        return dao.shGsblist(pd);
    }

    @Override
    public List<Map<String, Object>> shAllGsblists(Map<String, Object> pd) {
        return dao.shAllGsblists(pd);
    }

    @Override
    public List<Map<String, Object>> shJMblist(Map<String, Object> pd) {
        Integer pagebefor = 1;
        Integer pageafter = 1;
        Integer page = Integer.parseInt(String.valueOf(pd.get("jmbpage")));
        Integer size = Integer.parseInt(String.valueOf(pd.get("jmbsize")));
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
        return dao.shJMblist(pd);
    }

    @Override
    public List<Map<String, Object>> shAllJmblists(Map<String, Object> pd) {
        return dao.shAllJmblists(pd);
    }

    @Override
    public List<Map<String, Object>> getYsxz(Map<String, Object> pd) {
        return dao.getYsxz(pd);
    }

    @Override
    public void sheditsb(Map<String, Object> pd) {
        dao.sheditsb(pd);
    }

    @Override
    public List<Map<String, Object>> getHyflCodeData(String ysxz) {
        return dao.getHyflCodeData(ysxz);
    }

    @Override
    public List<Map<String, Object>> shJmbByLdid(Map<String, Object> pd) {
        return dao.shJmbByLdid(pd);
    }

    @Override
    public void shpledit(Map<String, Object> pd) {
        dao.shpledit(pd);
    }

    @Override
    public List<Map<String, Object>> getZpcby(Map<String, Object> pd) {
        return dao.getZpcby(pd);
    }

    @Override
    public void updateCby(Map<String, Object> pd) {
        dao.updateCby(pd);
    }

    @Override
    public void cbyeditsb(Map<String, Object> pd) {
        dao.cbyeditsb(pd);
    }

    @Override
    public void cbypledit(Map<String, Object> pd) {
        dao.cbypledit(pd);
    }

    @Override
    public void updateSbztByid(Map<String, Object> pd) {
        dao.updateSbztByid(pd);
    }

    @Override
    public void deleteDb(Map<String, Object> pd) {
        dao.deleteDb(pd);
    }

    @Override
    public void deleteLdsb(Map<String, Object> pd) {
        dao.deleteLdsb(pd);
    }

    @Override
    public String findZbidBybh(Map<String, Object> pd) {
        return dao.findZbidBybh(pd);
    }

    @Override
    public void sheditsbs(Map<String, Object> pd) {
        dao.sheditsbs(pd);
    }

    @Override
    public List<String> queryRwblr(String activityId, String refId,String procInstId) {
        return dao.queryRwblr(activityId,refId,procInstId);
    }

    @Override
    public List<String> querycbyThRwblr(String activityId, String refId,String procInstId) {
        return dao.querycbyThRwblr(activityId,refId,procInstId);
    }

    @Override
    public List<String> querySbids(String xmid, String refId) {
        return dao.querySbids(xmid,refId);
    }

    @Override
    public void cbysbdr(Map<String, Object> map) {
        dao.cbysbdr(map);
    }

    @Override
    public List<Map<String, Object>> queryAlldcsb(Map<String, Object> pd) {
        return dao.queryAlldcsb(pd);
    }

    @Override
    public String querysbcjjcBybh(String sbcj) {
        return dao.querysbcjjcBybh(sbcj);
    }

    @Override
    public String querysbkjmcBybh(String kjbh) {
        return dao.querysbkjmcBybh(kjbh);
    }

    @Override
    public List<Map<String, Object>> querysfkp(BaseEntity e) {
        return dao.querysfkp(e);
    }

    @Override
    public int querysfkpTotal(BaseEntity e) {
        return dao.querysfkpTotal(e);
    }

    @Override
    public List<Map<String, Object>> querysgxd(BaseEntity e) {
        return dao.querysgxd(e);
    }

    @Override
    public int querysgxdTotal(BaseEntity e) {
        return dao.querysgxdTotal(e);
    }

    @Override
    public void gsplbj(Map<String, Object> pd) {
        dao.gsplbj(pd);
    }

    @Override
    public void deleteWb(Map<String, Object> pd) {
        dao.deleteWb(pd);
    }

    @Override
    public void sbyjWbSubmit(Map<String, Object> pd) {
        dao.sbyjWbSubmit(pd);
    }


    @Override
    public List<Map<String, Object>> querydcsb(Map<String, Object> pd) {
        Integer pagebefor = 1;
        Integer pageafter = 1;
        Integer page = Integer.parseInt(String.valueOf(pd.get("dcpage")));
        Integer size = Integer.parseInt(String.valueOf(pd.get("dcsize")));
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
        return dao.querydcsb(pd);
    }

    @Override
    public List<Map<String, Object>> getSbcjList(Map<String, Object> pd) {
        return dao.getSbcjList(pd);
    }

    @Override
    public List<Map<String, Object>> getZbkjList(Map<String, Object> pd) {
        return dao.getZbkjList(pd);
    }

    @Override
    public List<Map<String, Object>> getqyList(Map<String, Object> pd) {
        return dao.getqyList(pd);
    }

    @Override
    public List<Map<String, Object>> getsblxList(Map<String, Object> pd) {
        return dao.getsblxList(pd);
    }

    @Override
    public List<Map<String, Object>> getXmxxByXmid(Map<String, Object> pd) {
        return dao.getXmxxByXmid(pd);
    }

    @Override
    public void editWb(WjjlbEntity entity) {
        dao.editWb(entity);
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
