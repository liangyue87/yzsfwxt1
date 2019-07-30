package com.ljx.hfgsjt.xqsl.serviceImpl;

import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.xqsl.GetXqslEntity;
import com.ljx.hfgsjt.entity.xqsl.XmGsybxxEntity;
import com.ljx.hfgsjt.entity.xqsl.XmYzbEntity;
import com.ljx.hfgsjt.entity.xqsl.YwslXjxqsqLdxxEntity;
import com.ljx.hfgsjt.repository.message.DxDfsRepository;
import com.ljx.hfgsjt.repository.xqsl.XqslListRepository;
import com.ljx.hfgsjt.xqsl.dao.XqslMapper;
import com.ljx.hfgsjt.xqsl.service.XqslService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class XqslServiceImpl implements XqslService {

    @Autowired
    private XqslListRepository xqslListRepository;
    @Autowired
    private DxDfsRepository dxDfsRepository;

    @Autowired
    private XqslMapper dao;

    @Override
    public Page<GetXqslEntity> getAllXqsl(Pageable pageable) {
        return xqslListRepository.findAll(pageable);
    }

    @Override
    public void updateGsSlrById(Map<String, Object> pd) {
        dao.updateGsSlrById(pd);
    }

    @Override
    public void updateJmSlrById(Map<String, Object> pd) {
        dao.updateJmSlrById(pd);
    }

    @Override
    public void updateHgSlrById(Map<String, Object> pd) {
        dao.updateHgSlrById(pd);
    }

    @Override
    public void insertImportFh(Map<String, Object> pd) {
        dao.insertImportFh(pd);
    }

    @Override
    public void insertImportgsFh(Map<String, Object> pd) {
        dao.insertImportgsFh(pd);
    }

    @Override
    public void insertImporthgFh(Map<String, Object> pd) {
        dao.insertImporthgFh(pd);
    }

    @Override
    public void insertChgz(Map<String, Object> pd) {
        dao.insertChgz(pd);
    }

    @Override
    public void insertXjxq(Map<String, Object> pd) {
        dao.insertXjxq(pd);
    }

    @Override
    public void insertGshb(Map<String, Object> pd) {
        dao.insertGshb(pd);
    }

    @Override
    public void updateChgz(Map<String, Object> pd) {
        dao.updateChgz(pd);
    }

    @Override
    public void updateXjxq(Map<String, Object> pd) {
        dao.updateXjxq(pd);
    }

    @Override
    public void updateGshb(Map<String, Object> pd) {
        dao.updateGshb(pd);
    }

    @Override
    public void updateChgzFj(Map<String, Object> pd) {
        dao.updateChgzFj(pd);
    }

    @Override
    public void updateXjxqFj(Map<String, Object> pd) {
        dao.updateXjxqFj(pd);
    }

    @Override
    public void updateGshbFjs(Map<String, Object> pd) {
        dao.updateGshbFjs(pd);
    }

    @Override
    public void updateChgzFjs(Map<String, Object> pd) {
        dao.updateChgzFjs(pd);
    }

    @Override
    public void updateXjxqFjs(Map<String, Object> pd) {
        dao.updateXjxqFjs(pd);
    }

    @Override
    public void updateGshbFj(Map<String, Object> pd) {
        dao.updateGshbFj(pd);
    }

    @Override
    public void insertTask(Map<String, Object> pd) {
        dao.insertTask(pd);
    }

    @Override
    public List<GetXqslEntity> getXqqsListByXmjl(Integer page, Integer size, String czr) {
        Integer pagebefor = 1;
        Integer pageafter = 1;
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
        List<GetXqslEntity> list = dao.getXqqsListByXmjl(pagebefor, pageafter,czr);
        return list;
    }

    @Override
    public List<GetXqslEntity> getAllXqqsLists(String czr) {
        List<GetXqslEntity> list = dao.findAll(czr);
        return list;
    }

    @Override
    public void updateFpzt(Map<String, Object> pd) {
        dao.updateFpzt(pd);
    }

    @Override
    public List<Map<String, Object>> getZpry(Map<String, Object> pd) {
        return dao.getZpry(pd);
    }

    @Override
    public List<Map<String, Object>> getSclx(Map<String, Object> pd) {
        return dao.getSclx(pd);
    }

    @Override
    public List<Map<String,Object>> findFjlxById(Map<String, Object> pd) {
        return dao.findFjlxById(pd);
    }

    @Override
    public List<GetXqslEntity> queryXqsl(Map<String, Object> pd) {
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
        List<GetXqslEntity> list = dao.queryXqsl(pd);
        return list;
    }

    @Override
    public List<GetXqslEntity> queryAllXqqsLists(Map<String, Object> pd) {
        List<GetXqslEntity> list = dao.queryAllXqqsLists(pd);
        return list;
    }

    @Override
    public List<Map<String, Object>> getQyList(Map<String, Object> pd) {
        return dao.getQyList(pd);
    }

    @Override
    public List<Map<String, Object>> queryZlcount(Map<String, Object> pd) {
        return dao.queryZlcount(pd);
    }

    @Override
    public void deleteChgzFj(Map<String, Object> pd2) {
        dao.deleteChgzFj(pd2);
    }

    @Override
    public void deleteXjxqFj(Map<String, Object> pd) {
        dao.deleteXjxqFj(pd);
    }

    @Override
    public void deleteGshbFj(Map<String, Object> pd1) {
        dao.deleteGshbFj(pd1);
    }

    @Override
    public List<Map<String, Object>> getScwjJm(Map<String, Object> pd) {
        return dao.getScwjJm(pd);
    }

    @Override
    public List<Map<String, Object>> getScwjGs(Map<String, Object> pd) {
        return dao.getScwjGs(pd);
    }

    @Override
    public List<Map<String, Object>> getScwjHg(Map<String, Object> pd) {
        return dao.getScwjHg(pd);
    }

    @Override
    public List<Map<String, Object>> getAzList(Map<String, Object> pd) {
        return dao.getAzList(pd);
    }

    @Override
    public void addLdxx(YwslXjxqsqLdxxEntity entity) {
        dao.addLdxx(entity);
    }

    @Override
    public void addLdxxs(YwslXjxqsqLdxxEntity entity) {
        dao.addLdxxs(entity);
    }

    @Override
    public void editLdxx(YwslXjxqsqLdxxEntity entity) {
        dao.editLdxx(entity);
    }

    @Override
    public List<YwslXjxqsqLdxxEntity> getLdxxList() {
        return dao.getLdxxList();
    }

    @Override
    public List<YwslXjxqsqLdxxEntity> getLdxxList1(Map<String, Object> pd) {
        return dao.getLdxxList1(pd);
    }

    @Override
    public void deleteLdxxById(String id) {
        dao.deleteLdxxById(id);
    }

    @Override
    public void deleteXjxqSqld(Map<String, Object> pd) {
        dao.deleteXjxqSqld(pd);
    }

    @Override
    public void updateXjxqSqld(Map<String, Object> pd) {
        dao.updateXjxqSqld(pd);
    }

    @Override
    public void updateXjxqSqlds(Map<String, Object> pd) {
        dao.updateXjxqSqlds(pd);
    }

    @Override
    public List<Map<String, Object>> getXqslById(String rwid) {
        return dao.getXqslById(rwid);
    }

    @Override
    public List<Map<String, Object>> querySqList() {
        return dao.querySqList();
    }

    @Override
    public List<XmYzbEntity> getYzbList() {
        return dao.getYzbList();
    }

    @Override
    public List<XmYzbEntity> getYzbList1(Map<String, Object> pd) {
        return dao.getYzbList1(pd);
    }

    @Override
    public void deleteYzbById(String id) {
        dao.deleteYzbById(id);
    }

    @Override
    public void addYzb(XmYzbEntity entity) {
        dao.addYzb(entity);
    }

    @Override
    public void addYzbs(XmYzbEntity entity) {
        dao.addYzbs(entity);
    }

    @Override
    public void editYzb(XmYzbEntity entity) {
        dao.editYzb(entity);
    }

    @Override
    public List<Map<String, Object>> queryKjList() {
        return dao.queryKjList();
    }

    @Override
    public List<Map<String, Object>> queryYsxzList() {
        return dao.queryYsxzList();
    }

    @Override
    public void deleteYzb(Map<String, Object> pd) {
        dao.deleteYzb(pd);
    }

    @Override
    public void updateYzb(Map<String, Object> pd) {
        dao.updateYzb(pd);
    }

    @Override
    public void updateYzbs(Map<String, Object> pd) {
        dao.updateYzbs(pd);
    }

    @Override
    public List<Map<String, Object>> getZlrow(Map<String, Object> pd) {
        return dao.getZlrow(pd);
    }

    @Override
    public List<Map<String, Object>> getGsZlrow(Map<String, Object> pd) {
        return dao.getGsZlrow(pd);
    }

    @Override
    public List<Map<String, Object>> getHgZlrow(Map<String, Object> pd) {
        return dao.getHgZlrow(pd);
    }

    @Override
    public List<Map<String, Object>> toDetial(Map<String, Object> pd) {
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
        return dao.toDetial(pd);
    }

    @Override
    public List<Map<String, Object>> toAllDetial(Map<String, Object> pd) {
        return dao.toAllDetial(pd);
    }

    @Override
    public void deleteFj(Map<String, Object> pd) {
        dao.deleteFj(pd);
    }

    @Override
    public List<Map<String, Object>> getEditZlrow(Map<String, Object> pd) {
        return dao.getEditZlrow(pd);
    }

    @Override
    public List<Map<String, Object>> getGsEditZlrow(Map<String, Object> pd) {
        return dao.getGsEditZlrow(pd);
    }

    @Override
    public List<Map<String, Object>> getHgEditZlrow(Map<String, Object> pd) {
        return dao.getHgEditZlrow(pd);
    }

    @Override
    public void deleteLdxx() {
        dao.deleteLdxx();
    }

    @Override
    public void deleteYzbxx() {
        dao.deleteYzbxx();
    }

    @Override
    public void deleteJmFjxx() {
        dao.deleteJmFjxx();
    }

    @Override
    public void deleteGsFjxx() {
        dao.deleteGsFjxx();
    }

    @Override
    public void deleteHgFjxx() {
        dao.deleteHgFjxx();
    }

    @Override
    public List<Map<String, Object>> toDetialGs(Map<String, Object> pd) {
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
        return dao.toDetialGs(pd);
    }

    @Override
    public List<Map<String, Object>> toAllDetialGs(Map<String, Object> pd) {
        return dao.toAllDetialGs(pd);
    }

    @Override
    public List<Map<String, Object>> toDetialHg(Map<String, Object> pd) {
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
        return dao.toDetialHg(pd);
    }

    @Override
    public List<Map<String, Object>> toAllDetialHg(Map<String, Object> pd) {
        return dao.toAllDetialHg(pd);
    }

    @Override
    public List<Map<String, Object>> queryFwxzList(String type) {
        return dao.queryFwxzList(type);
    }

    @Override
    public Map<String, Object> getxmjl(Map<String, Object> pd) {
        return dao.getxmjl(pd);
    }

    @Override
    public List<GetXqslEntity> queryXqByid(String rwid) {
        return dao.queryXqByid(rwid);
    }

    @Override
    public void saveDxDfsEntity(DxDfsEntity dxDfsEntity) {
        dxDfsRepository.saveAndFlush(dxDfsEntity);
    }

    @Override
    public List<Map<String, Object>> querySqxxById(Map<String, Object> pd) {
        return dao.querySqxxById(pd);
    }

    @Override
    public String findAzlxBybh(String azlxbh) {
        return dao.findAzlxBybh(azlxbh);
    }

    @Override
    public List<XmGsybxxEntity> getGsbjxxList() {
        return dao.getGsbjxxList();
    }

    @Override
    public List<XmGsybxxEntity> getGsbjxxLists(Map<String, Object> pd) {
        return dao.getGsbjxxLists(pd);
    }

    @Override
    public void addGsbjxx(XmGsybxxEntity entity) {
        dao.addGsbjxx(entity);
    }

    @Override
    public void editGsbjxx(XmGsybxxEntity entity) {
        dao.editGsbjxx(entity);
    }

    @Override
    public void deleteGsbjxxById(String id) {
        dao.deleteGsbjxxById(id);
    }

    @Override
    public void deleteXq(Map<String, Object> pd) {
        dao.deleteXq(pd);
    }

    @Override
    public List<Map<String, Object>> queryzdry(String userid) {
        return dao.queryzdry(userid);
    }

    @Override
    public void updateRwblr(Map<String, Object> pd) {
        dao.updateRwblr(pd);
    }

    @Override
    public void updateXmjl(Map<String, Object> pd) {
        dao.updateXmjl(pd);
    }

    @Override
    public List<UserEntity> queryUserByid(String zdr) {
        return dao.queryUserByid(zdr);
    }

    @Override
    public Map<String, Object> defaultsqlx(String userid) {
        return dao.defaultsqlx(userid);
    }

    @Override
    public List<Map<String, Object>> getXmxxByid(String rwid) {
        return dao.getXmxxByid(rwid);
    }

    @Override
    public List<Map<String, Object>> getXqslByBh(String sqbh) {
        return dao.getXqslByBh(sqbh);
    }


}
