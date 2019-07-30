package com.ljx.hfgsjt.zlgd.service.Impl;

import com.ljx.hfgsjt.entity.dto.Sjst.ProjectModel;
import com.ljx.hfgsjt.entity.dto.Sjst.SjstYhzl;
import com.ljx.hfgsjt.zlgd.mapper.ZlgdMapper;
import com.ljx.hfgsjt.zlgd.service.ZlgdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ZldgServiceImpl implements ZlgdService {
    @Autowired
    private ZlgdMapper mapper;
    @Override
    public int getProjectCount(ProjectModel model) {
        return mapper.getProjectCount(model);
    }

    @Override
    public List<ProjectModel> getProjectList(ProjectModel model) {
        return mapper.getProjectList(model);
    }

    @Override
    public void getZlzl(Map<String, Object> params) {
        mapper.getZlzl(params);
    }

    @Override
    public List<SjstYhzl> getSjcgzl(String xmid, String id) {
        return mapper.getSjcgzl(xmid,id);
    }

    @Override
    public List<SjstYhzl> getYszl(String xmid, String id) {
        return mapper.getYszl(xmid,id);
    }

    @Override
    public List<SjstYhzl> getHtzl(String xmid, String id) {
        return mapper.getHtzl(xmid,id);
    }

    @Override
    public Map<String, Object> getRoleidAndOrgidAndBmfzridByUserid(String userid) {
        return mapper.getRoleidAndOrgidAndBmfzridByUserid(userid);
    }
}
