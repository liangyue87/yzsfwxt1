package com.ljx.hfgsjt.repository;

import com.ljx.hfgsjt.entity.dto.RoleMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMenuRepository extends JpaRepository<RoleMenuEntity, String>,JpaSpecificationExecutor<RoleMenuEntity> {

    @Modifying
    @Query("delete from RoleMenuEntity a where a.roleid = ?1")
    int deleteByRoleid(String roleid);

    List<RoleMenuEntity> findByRoleid(String roleid);

}
