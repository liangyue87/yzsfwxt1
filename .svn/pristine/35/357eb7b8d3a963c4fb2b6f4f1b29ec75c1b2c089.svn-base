package com.ljx.hfgsjt.message.serviceImpl;

import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.dto.message.DxFsjlEntity;
import com.ljx.hfgsjt.message.dao.MessageMapper;
import com.ljx.hfgsjt.message.service.MessageService;
import com.ljx.hfgsjt.repository.message.DxDfsRepository;
import com.ljx.hfgsjt.repository.message.DxFsjlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private DxDfsRepository dxDfsRepository;
    @Autowired
    private DxFsjlRepository dxFsjlRepository;
    @Autowired
    private MessageMapper mapper;

    @Override
    public List<DxDfsEntity> getDxDfsList() {
        return dxDfsRepository.findAll();
    }

    @Override
    public void deleteDxDfsById(String id) {
        dxDfsRepository.deleteById(id);
    }

    @Override
    public void saveDxFsjl(DxFsjlEntity dxFsjlEntity) {
        dxFsjlRepository.save(dxFsjlEntity);
    }

    @Override
    public Map<String, Object> getDxMb(String id) {
        return mapper.getDxMb(id);
    }

    @Override
    public Map<String, Object> getUserxxById(String userid) {
        return mapper.getUserxxById(userid);
    }

    @Override
    public void dxTimer(String id) {
        mapper.dxTimer(id);
    }
}
