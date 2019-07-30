package com.ljx.hfgsjt.repository;

import com.ljx.hfgsjt.entity.dto.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
    public interface DepartmentRepository extends JpaRepository<DepartmentEntity, String>, JpaSpecificationExecutor<DepartmentEntity> {

}


