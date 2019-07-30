package com.ljx.hfgsjt.main.service;

import com.ljx.hfgsjt.entity.dto.DepartmentEntity;
import com.ljx.hfgsjt.entity.dto.UserOrgEntity;

import java.util.List;

public interface DepartmentService {
    void saveDepartment(DepartmentEntity Department);

    List<DepartmentEntity> getAllDepartmentList();

    DepartmentEntity getDepartmentById(String id);

    void deleteById(String id);

    void saveUserOrg(UserOrgEntity userOrgEntity);
}
