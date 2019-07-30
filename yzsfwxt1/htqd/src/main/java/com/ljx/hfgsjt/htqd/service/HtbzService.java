package com.ljx.hfgsjt.htqd.service;

import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.htqd.*;

import java.util.List;
import java.util.Map;

public interface HtbzService {

    List<XmInstanceEntity> findByXmbh(String xmbh);

    List<XtglHtmbEntity> findByMbbm(String mbbm);

    void upoadFile(Map<String, Object> pd);

    List<GetXmlbHtEntity> getHtList(Map<String, Object> pd);

    List<GetXmlbHtEntity> getAllHtList(Map<String, Object> pd);

    List<GetHtxmEntity> findXmxxByXmbh(String xmbh);

    List<Map<String, Object>> queryZjqtfjById(Map<String, Object> pd);

    void deleteFjByid(Map<String, Object> pd);

    List<XmInstanceEntity> queryxmxxByid(String xmid);

    List<Map<String, Object>> queryYjsfzh();

    List<JsfsEntity> queryJsfs();

    List<Map<String, Object>> queryYslist(String xmid);

    void saveHt(Map<String, Object> pd);

    void savefycb(Map<String, Object> cbpd);

    String texuChange(Map<String, Object> map);

    void saveFqb(FqbEntity fqbEntity);

    void saveFqbmx(FqmxbEntity fqmxbEntity);

    List<FqbEntity> findFqb(String htzbid);

    List<FqmxbEntity> findFqmxb(String fqzbid);

    List<FqbEntity> getFqbByHtzbid(String htzbid);

    void deleteFqbByHtzbid(String htzbid);

    void deleteFqmxbByFqzbid(String fqzbid);

    List<Map<String, Object>> queryHtbz(String xmid);

    void updateHt(Map<String, Object> pd);

    void deleteHtfy(Map<String, Object> pd);

    void updateXmlbtoXMb(Map<String, Object> pd);

    List<XmInstance> findXmxxByxmid(String xmid);
}
