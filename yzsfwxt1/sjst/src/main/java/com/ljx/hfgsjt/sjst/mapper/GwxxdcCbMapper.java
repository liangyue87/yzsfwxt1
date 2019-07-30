package com.ljx.hfgsjt.sjst.mapper;

import com.ljx.hfgsjt.entity.dto.Sjst.GwxxdcCb;
import com.ljx.hfgsjt.entity.dto.Sjst.GwxxdcZb;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GwxxdcCbMapper {

    int insert(GwxxdcCb record);

    int insertSelective(GwxxdcCb record);

    GwxxdcZb selectGwxxdcZB(String xmid);

    List<GwxxdcCb> selectGwxxdcCBList(String  xmid);

}