package com.ljx.hfgsjt.main.service;

import com.ljx.hfgsjt.entity.dto.Activity.WfReNodeattrEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface WfReNodeattrService {

   void save(WfReNodeattrEntity wf);

    void deleteById(String id);

    Page<WfReNodeattrEntity> findAll(PageRequest request);

    //Page<WfReNodeattrEntity> findByNameLike(String name, PageRequest request);
}
