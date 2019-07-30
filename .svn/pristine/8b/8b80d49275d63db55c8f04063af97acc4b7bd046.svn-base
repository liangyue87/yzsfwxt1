package com.ljx.hfgsjt.main.serviceImpl;

import com.ljx.hfgsjt.entity.dto.Activity.WfDfActivityEntity;
import com.ljx.hfgsjt.main.service.WfDfActivityService;
import com.ljx.hfgsjt.repository.activity.WfDfActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class WfDfActivityServiceImpl implements WfDfActivityService {
    @Autowired
    private WfDfActivityRepository repository;

    @Override
    public void save(WfDfActivityEntity wf) {
        repository.save(wf);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Page<WfDfActivityEntity> findByDfActivitynameLike(String activityname, PageRequest request) {
        Sort sort = new Sort(Sort.Direction.ASC, "ACTIVITYID");
        return repository.findByDfActivitynameLike(activityname,request);
    }


}
