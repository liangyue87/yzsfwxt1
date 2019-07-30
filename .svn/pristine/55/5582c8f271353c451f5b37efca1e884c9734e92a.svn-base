package com.ljx.hfgsjt.main.serviceImpl;


import com.ljx.hfgsjt.entity.dto.RoleMenuEntity;
import com.ljx.hfgsjt.main.service.RoleMenuService;
import com.ljx.hfgsjt.repository.RoleMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuRepository repository;


    @Override
    @Transactional
    public int deleteByRoleid(String menuid) {
        return repository.deleteByRoleid(menuid);
    }

    @Override
    public void saveRoleMenu(List<RoleMenuEntity> lists) {
        repository.saveAll(lists);
    }

    @Override
    public List<RoleMenuEntity> getMenuForRole(String roleid) {
        return repository.findByRoleid(roleid);
    }
}
