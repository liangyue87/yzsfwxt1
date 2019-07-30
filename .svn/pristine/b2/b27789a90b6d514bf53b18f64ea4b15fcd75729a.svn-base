package com.ljx.hfgsjt.repository;

import com.ljx.hfgsjt.entity.dto.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String>,JpaSpecificationExecutor<RoleEntity> {


    @Query(value = "SELECT * FROM XTGL_ROLE WHERE rolename like %?1%",
            countQuery = "SELECT count(*) FROM XTGL_ROLE WHERE rolename like %?1%",
            nativeQuery = true)
    Page<RoleEntity> findUserAll(String rolename, PageRequest request);

}
