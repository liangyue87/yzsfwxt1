package com.ljx.hfgsjt.main.service;

import com.ljx.hfgsjt.entity.dto.RoleEntity;
import com.ljx.hfgsjt.entity.dto.RoleMenuEntity;

import java.util.List;
import java.util.Map;


public interface RoleService {

    public List<RoleEntity> getPageRoleList(String rolename,Integer page, Integer size);

    public List<RoleEntity> getAllRoleList();

    public void saveRole(RoleEntity roleEntity);

    public void deleteById(String id);

    public void updateRole(RoleEntity roleEntity);

    public int getPages();

    public List<Map<String,Object>> getOrgList();

    public List<Map<String,Object>> getUserList();

    int deleteByRoleid(String menuid);

    void saveRoleUser(Map<String,String> map);

    List<Map<String,Object>> getUserForRole(String roleid);



}
