package com.ljx.hfgsjt.main.serviceImpl;

import com.ljx.hfgsjt.entity.dto.Activity.WfActivityRoleEntity;
import com.ljx.hfgsjt.main.service.WfActivityRoleService;
import com.ljx.hfgsjt.repository.activity.WfActivityRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class WfActivityRoleServiceImpl implements WfActivityRoleService {
    @Autowired
    private WfActivityRoleRepository repository;

    @Override
    public void save(WfActivityRoleEntity wf) {
        repository.save(wf);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Page<WfActivityRoleEntity> findByActivitynameLike(String activityid, PageRequest request) {
        return repository.findByActivitynameLike(activityid,request);
    }


}
