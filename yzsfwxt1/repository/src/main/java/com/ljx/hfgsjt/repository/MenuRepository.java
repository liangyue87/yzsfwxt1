package com.ljx.hfgsjt.repository;

import com.ljx.hfgsjt.entity.dto.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, String>,JpaSpecificationExecutor<MenuEntity> {
    //MenuEntity findByNameAndPassword(String name, String password);

    @Query(value = "select distinct t.* from xtgl_menu t where exists" +
            "(select menuid from xtgl_rolemenu a where t.menuid = a.menuid and exists" +
            "(select roleid from xtgl_userrole b where a.roleid = b.roleid and b.userid = ?1)" +
            ") and VALID = '1' order by t.ORDINAL", nativeQuery = true)
    List<MenuEntity> getMenuByUserid(String userid);
}
