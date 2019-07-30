package com.ljx.hfgsjt.main.service;

import com.ljx.hfgsjt.entity.dto.Activity.WfDfActivityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface WfDfActivityService {

   void save(WfDfActivityEntity wf);

    void deleteById(String id);

    Page<WfDfActivityEntity> findByDfActivitynameLike(String activityname, PageRequest request);
}
