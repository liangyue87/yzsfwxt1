package com.ljx.hfgsjt.main.serviceImpl;

import com.ljx.hfgsjt.entity.dto.Activity.WfReNodeEntity;
import com.ljx.hfgsjt.main.service.WfReNodeService;
import com.ljx.hfgsjt.repository.activity.WfReNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class WfReNodeServiceImpl implements WfReNodeService {
    @Autowired
    private WfReNodeRepository repository;

    @Override
    public void save(WfReNodeEntity wf) {
        repository.save(wf);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Page<WfReNodeEntity> findByNameLike(String name, PageRequest request) {
        return repository.findByNodenameLike(name,request);
    }


}
