package com.ljx.hfgsjt.xqsl.serviceImpl;

import com.ljx.hfgsjt.entity.xqsl.YwslXjxqsqbEntity;
import com.ljx.hfgsjt.repository.xqsl.XjxqsqbRepository;
import com.ljx.hfgsjt.xqsl.dto.XjxqsqbDto;
import com.ljx.hfgsjt.xqsl.mapper.XjxqsqbMapper;
import com.ljx.hfgsjt.xqsl.service.XjxqsqbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XjxqsqbServiceImpl implements XjxqsqbService {

    @Autowired
    private XjxqsqbMapper xjxqsqbMapper;

    @Autowired
    private XjxqsqbRepository xjxqsqbRepository;

    @Override
    public List<XjxqsqbDto> getXjxqsqbDtos() {
        List<YwslXjxqsqbEntity> ywslXjxqsqbEntities = xjxqsqbRepository.findAll();
        return xjxqsqbMapper.EOToDtoList(ywslXjxqsqbEntities);
    }
}
