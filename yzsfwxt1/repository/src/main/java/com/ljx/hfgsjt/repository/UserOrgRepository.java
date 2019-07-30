package com.ljx.hfgsjt.repository;

import com.ljx.hfgsjt.entity.dto.UserOrgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrgRepository extends JpaRepository<UserOrgEntity, String>,JpaSpecificationExecutor<UserOrgEntity> {

}
