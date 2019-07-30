package com.ljx.hfgsjt.xqsl.service;

import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.xqsl.GetXqslEntity;
import com.ljx.hfgsjt.entity.xqsl.XmGsybxxEntity;
import com.ljx.hfgsjt.entity.xqsl.XmYzbEntity;
import com.ljx.hfgsjt.entity.xqsl.YwslXjxqsqLdxxEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface XqslService {
    public Page<GetXqslEntity> getAllXqsl(Pageable pageable);

    void updateGsSlrById(Map<String, Object> pd);

    void updateJmSlrById(Map<String, Object> pd);

    void updateHgSlrById(Map<String, Object> pd);

    void insertImportFh(Map<String, Object> pd);

    void insertImportgsFh(Map<String, Object> pd);

    void insertImporthgFh(Map<String, Object> pd);

    void insertChgz(Map<String, Object> pd);

    void insertXjxq(Map<String, Object> pd);

    void insertGshb(Map<String, Object> pd);

    void updateChgz(Map<String, Object> pd);

    void updateXjxq(Map<String, Object> pd);

    void updateGshb(Map<String, Object> pd);

    void updateChgzFj(Map<String, Object> pd);

    void updateXjxqFj(Map<String, Object> pd);

    void updateGshbFj(Map<String, Object> pd);

    void updateChgzFjs(Map<String, Object> pd);

    void updateXjxqFjs(Map<String, Object> pd);

    void updateGshbFjs(Map<String, Object> pd);

    void insertTask(Map<String, Object> pd);

    List<GetXqslEntity> getXqqsListByXmjl(Integer page, Integer size, String czr);

    List<GetXqslEntity> getAllXqqsLists(String czr);

    void updateFpzt(Map<String, Object> pd);

    List<Map<String, Object>> getZpry(Map<String, Object> pd);

    List<Map<String, Object>> getSclx(Map<String, Object> pd);

    List<Map<String,Object>> findFjlxById(Map<String, Object> pd);

    List<GetXqslEntity> queryXqsl(Map<String, Object> pd);

    List<GetXqslEntity> queryAllXqqsLists(Map<String, Object> pd);

    List<Map<String, Object>> getQyList(Map<String, Object> pd);

    List<Map<String, Object>> queryZlcount(Map<String, Object> pd);

    void deleteChgzFj(Map<String, Object> pd2);

    void deleteXjxqFj(Map<String, Object> pd);

    void deleteGshbFj(Map<String, Object> pd1);

    List<Map<String, Object>> getScwjJm(Map<String, Object> pd);

    List<Map<String, Object>> getScwjGs(Map<String, Object> pd);

    List<Map<String, Object>> getScwjHg(Map<String, Object> pd);

    List<Map<String, Object>> getAzList(Map<String, Object> pd);

    void addLdxx(YwslXjxqsqLdxxEntity entity);

    void addLdxxs(YwslXjxqsqLdxxEntity entity);

    void editLdxx(YwslXjxqsqLdxxEntity entity);

    List<YwslXjxqsqLdxxEntity> getLdxxList();

    List<YwslXjxqsqLdxxEntity> getLdxxList1(Map<String, Object> pd);

    void deleteLdxxById(String id);

    void deleteXjxqSqld(Map<String, Object> pd);

    void updateXjxqSqld(Map<String, Object> pd);

    void updateXjxqSqlds(Map<String, Object> pd);

    List<Map<String, Object>> getXqslById(String rwid);

    List<Map<String, Object>> querySqList();

    List<XmYzbEntity> getYzbList();

    List<XmYzbEntity> getYzbList1(Map<String, Object> pd);

    void deleteYzbById(String id);

    void addYzb(XmYzbEntity entity);

    void addYzbs(XmYzbEntity entity);

    void editYzb(XmYzbEntity entity);

    List<Map<String, Object>> queryKjList();

    List<Map<String, Object>> queryYsxzList();

    void deleteYzb(Map<String, Object> pd);

    void updateYzb(Map<String, Object> pd);

    void updateYzbs(Map<String, Object> pd);

    List<Map<String, Object>> getZlrow(Map<String, Object> pd);

    List<Map<String, Object>> getGsZlrow(Map<String, Object> pd);

    List<Map<String, Object>> getHgZlrow(Map<String, Object> pd);

    List<Map<String, Object>> toDetial(Map<String, Object> pd);

    List<Map<String, Object>> toAllDetial(Map<String, Object> pd);

    void deleteFj(Map<String, Object> pd);

    List<Map<String, Object>> getEditZlrow(Map<String, Object> pd);

    List<Map<String, Object>> getGsEditZlrow(Map<String, Object> pd);

    List<Map<String, Object>> getHgEditZlrow(Map<String, Object> pd);

    void deleteLdxx();

    void deleteYzbxx();

    void deleteJmFjxx();

    void deleteGsFjxx();

    void deleteHgFjxx();

    List<Map<String, Object>> toDetialGs(Map<String, Object> pd);

    List<Map<String, Object>> toAllDetialGs(Map<String, Object> pd);

    List<Map<String, Object>> toDetialHg(Map<String, Object> pd);

    List<Map<String, Object>> toAllDetialHg(Map<String, Object> pd);

    List<Map<String,Object>> queryFwxzList(String type);

    Map<String, Object> getxmjl(Map<String, Object> pd);

    List<GetXqslEntity> queryXqByid(String rwid);

    void saveDxDfsEntity(DxDfsEntity dxDfsEntity);

    List<Map<String, Object>> querySqxxById(Map<String, Object> pd);

    String findAzlxBybh(String azlxbh);

    List<XmGsybxxEntity> getGsbjxxList();

    List<XmGsybxxEntity> getGsbjxxLists(Map<String, Object> pd);

    void addGsbjxx(XmGsybxxEntity entity);

    void editGsbjxx(XmGsybxxEntity entity);

    void deleteGsbjxxById(String id);

    void deleteXq(Map<String, Object> pd);

    List<Map<String, Object>> queryzdry(String userid);

    void updateRwblr(Map<String, Object> pd);

    void updateXmjl(Map<String, Object> pd);

    List<UserEntity> queryUserByid(String zdr);

    Map<String, Object> defaultsqlx(String userid);

    List<Map<String, Object>> getXmxxByid(String rwid);

    List<Map<String, Object>> getXqslByBh(String sqbh);
}
