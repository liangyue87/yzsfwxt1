package com.ljx.hfgsjt.main.serviceImpl;


import com.ljx.hfgsjt.entity.dto.Activity.WfReNodeattrEntity;
import com.ljx.hfgsjt.main.service.WfReNodeattrService;
import com.ljx.hfgsjt.repository.activity.WfReNodeattrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class WfReNodeattrServiceImpl implements WfReNodeattrService {
    @Autowired
    private WfReNodeattrRepository repository;

    @Override
    public void save(WfReNodeattrEntity wf) {
        repository.save(wf);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Page<WfReNodeattrEntity> findAll(PageRequest request) {
        return repository.findAll(request);
    }

   /* @Override
    public Page<WfReNodeattrEntity> findByNameLike(String name, PageRequest request) {
        return repository.findByNodenameLike(name,request);
    }*/


}
