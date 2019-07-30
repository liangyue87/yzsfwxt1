package com.ljx.hfgsjt.main.service;

import com.ljx.hfgsjt.entity.dto.Activity.WfReNodeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface WfReNodeService {

   void save(WfReNodeEntity wf);

    void deleteById(String id);

    Page<WfReNodeEntity> findByNameLike(String name, PageRequest request);
}
