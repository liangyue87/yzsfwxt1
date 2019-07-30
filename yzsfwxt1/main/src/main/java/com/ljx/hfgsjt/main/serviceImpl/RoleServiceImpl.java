package com.ljx.hfgsjt.main.serviceImpl;

import com.ljx.hfgsjt.entity.dto.RoleEntity;
import com.ljx.hfgsjt.main.dao.UserMapper;
import com.ljx.hfgsjt.main.service.RoleService;
import com.ljx.hfgsjt.repository.RoleRepository;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;
    @Autowired
    private UserMapper mapper;
    private int pages;

    public int getPages() {
        return pages;
    }

    @Override
    public List<Map<String, Object>> getOrgList() {
        return mapper.getOrgList();
    }

    @Override
    public List<Map<String, Object>> getUserList() {
        return mapper.getUserList();
    }

    @Override
    public int deleteByRoleid(String roleid) {
        return mapper.deleteByRoleid(roleid);
    }

    @Override
    public void saveRoleUser(Map<String, String> map) {
        mapper.saveRoleUser(map);
    }

    @Override
    public List<Map<String, Object>> getUserForRole(String roleid) {
        return mapper.getUserForRole(roleid);
    }


    @Override
    public List<RoleEntity> getPageRoleList(String usernaem ,Integer page, Integer size) {
        if (page == null) {
            page = 0;
        }
        if (size==null) {
            size = 2;
        }
        Sort sort = new Sort(Sort.Direction.ASC, "ordinal");
        PageRequest request = PageRequest.of(page, size,sort);
        Page<RoleEntity> rolepage = repository.findUserAll(usernaem,request);
        this.pages = (int)rolepage.getTotalElements();

        List<RoleEntity> roles= rolepage.getContent();
        return roles;
    }

    @Override
    public List<RoleEntity> getAllRoleList() {
        Sort sort = new Sort(Sort.Direction.ASC, "ordinal");
        List<RoleEntity> list =repository.findAll(sort);
        return list;
    }

    @Override
    public void saveRole(RoleEntity roleEntity) {
        //roleEntity.setRoleid(UUID.randomUUID().toString());
       // roleEntity.setRoleid(GuidUtils.getUUID());
        roleEntity.setRoleid(GuidUtils.getGuid());
        repository.save(roleEntity);
    }
    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void updateRole(RoleEntity roleEntity) {
        repository.save(roleEntity);
    }


}
