package com.ljx.hfgsjt.zhys.service.Impl;

import com.ljx.hfgsjt.entity.dto.zhys.*;
import com.ljx.hfgsjt.zhys.mapper.SqzhysMapper;
import com.ljx.hfgsjt.zhys.service.SqzhysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SqzhysServiceImpl implements SqzhysService {

    @Autowired
    private SqzhysMapper sqzhysMapper;

    @Override
    public int selectSqzhysCount(Sqzhys sqzhys) {
        return sqzhysMapper.selectSqzhysCount(sqzhys);
    }

    @Override
    public List<Sqzhys> selectSqzhysList(Sqzhys sqzhys) {
        return sqzhysMapper.selectSqzhysList(sqzhys);
    }

  /*  @Override
    public List<YwslXjxqsqbFjEntity> selectJmlist(String sqbh) {
        return sqzhysMapper.selectJmlist(sqbh);
    }*/
  @Override
  public int selectJmCount(String sqbh) {
      return sqzhysMapper.selectJmCount(sqbh);
  }
  @Override
  public List<YwslXjxqsqbFjEntity> selectJmlist(Map<String, Object> map) {
      return sqzhysMapper.selectJmlist(map);
  }

    @Override
    public int selectGsCount(String sqbh) {
        return sqzhysMapper.selectGsCount(sqbh);
    }
    @Override
    public List<YwslGshbsqbFjEntity> selectGslist(Map<String, Object> map) {return sqzhysMapper.selectGslist(map); }

    @Override
    public int selectHwCount(String sqbh) {
        return sqzhysMapper.selectHwCount(sqbh);
    }
    @Override
    public List<YwslChgzsqbFjEntity> selectHwlist(Map<String, Object> map) {return sqzhysMapper.selectHwlist(map); }

    @Override
    public int selectSjcgCount(String xmid) {
        return sqzhysMapper.selectSjcgCount(xmid);
    }
    @Override
    public List<SjstRwSjstcgEntity> selectSjcglist(Map<String, Object> map) {return sqzhysMapper.selectSjcglist(map); }

    @Override
    public int selectSgCount(String xmid) {
        return sqzhysMapper.selectSgCount(xmid);
    }
    @Override
    public List<SgxtBgxxFjEntity> selectSglist(Map<String, Object> map) {return sqzhysMapper.selectSglist(map); }

    @Override
    public int selectTysl(String  xmid) {return sqzhysMapper.selectTysl(xmid); }//庭院数量
    @Override
    public int selectLdsl(String  xmid) {return sqzhysMapper.selectLdsl(xmid); }//楼栋数量
    @Override
    public int selectDxssl(String  xmid) {return sqzhysMapper.selectDxssl(xmid); }//地下室数量
    @Override
    public int selectBfsl(String  xmid) {return sqzhysMapper.selectBfsl(xmid); }//泵房数量
    @Override
    public int selectSxsl(String  xmid) {return sqzhysMapper.selectSxsl(xmid); }//水箱数量

    @Override
    public List<XmTyEntity> selectTyList(Map<String, Object> map) {
        return sqzhysMapper.selectTyList(map);
    }

    @Override
    public List<XmLdEntity> selectLdList(Map<String, Object> map) {
        return sqzhysMapper.selectLdList(map);
    }

    @Override
    public List<XmDxsEntity> selectDxsList(Map<String, Object> map) {
        return sqzhysMapper.selectDxsList(map);
    }

    @Override
    public List<XmBfEntity> selectBfList(Map<String, Object> map) {
        return sqzhysMapper.selectBfList(map);
    }

    @Override
    public List<XmSxEntity> selectSxList(Map<String, Object> map) {
        return sqzhysMapper.selectSxList(map);
    }

    @Override
    public String texuChange(Map<String, Object> map) {
        return sqzhysMapper.texuChange(map);
    }
    @Override
    public int insertZhysSqzb(ZhysSqzb zhysSqzb) {
        return sqzhysMapper.insertZhysSqzb(zhysSqzb);
    }

    @Override
    public List<Map<String, Object>> getWbxxByXmid(String xmid) {
        return sqzhysMapper.getWbxxByXmid(xmid);
    }

    @Override
    public List<Map<String, Object>> getDbxxByXmid(String xmid) {
        return sqzhysMapper.getDbxxByXmid(xmid);
    }

    @Override
    public List<Map<String, Object>> getHbxxByXmid(String xmid) {
        return sqzhysMapper.getHbxxByXmid(xmid);
    }

    @Override
    public XmUserEntity getZhysmrr(String pdefid) {
        return sqzhysMapper.getZhysmrr(pdefid);
    }
}
