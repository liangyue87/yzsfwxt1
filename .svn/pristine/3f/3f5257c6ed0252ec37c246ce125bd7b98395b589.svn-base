package com.ljx.hfgsjt.xqsl.serviceImpl;

import com.ljx.hfgsjt.entity.xqsl.YwslChgzsqbEntity;
import com.ljx.hfgsjt.repository.xqsl.ChgzsqbRepository;
import com.ljx.hfgsjt.xqsl.dto.ChgzsqbDto;
import com.ljx.hfgsjt.xqsl.dto.XjxqsqbDto;
import com.ljx.hfgsjt.xqsl.mapper.ChgzsqbMapper;
import com.ljx.hfgsjt.xqsl.service.ChgzsqbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChgzsqbServiceImpl implements ChgzsqbService {

    @Autowired
    private ChgzsqbMapper chgzSqbMapper;

    @Autowired
    private ChgzsqbRepository chgzsqbRepository;

    @Override
    public List<ChgzsqbDto> getChzgsqbDtos() {
        List<YwslChgzsqbEntity> ywslChgzsqbEntities = chgzsqbRepository.findAll();
        return chgzSqbMapper.EOToDtoList(ywslChgzsqbEntities);
    }
}
