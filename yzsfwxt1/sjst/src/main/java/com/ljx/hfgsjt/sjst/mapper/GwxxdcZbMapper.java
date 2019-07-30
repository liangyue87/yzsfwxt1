package com.ljx.hfgsjt.sjst.mapper;
import com.ljx.hfgsjt.entity.dto.Sjst.GwxxdcZb;
import org.springframework.stereotype.Service;

@Service
public interface GwxxdcZbMapper {
    int deleteByPrimaryKey(String id);

    int insert(GwxxdcZb record);

    int insertSelective(GwxxdcZb record);

    GwxxdcZb selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GwxxdcZb record);

    int updateByPrimaryKey(GwxxdcZb record);
}