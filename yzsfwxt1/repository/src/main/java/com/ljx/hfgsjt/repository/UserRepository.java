package com.ljx.hfgsjt.repository;

import com.ljx.hfgsjt.entity.dto.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>,JpaSpecificationExecutor<UserEntity> {

    UserEntity findByAccountAndPassword(String account, String password);

    @Query(value = "SELECT * FROM XTGL_USER WHERE username like %?1% AND userid in( select t.userid from xtgl_userorg t where t.orgid= ?2)",
            countQuery = "SELECT count(*) FROM XTGL_USER WHERE username like %?1%",
            nativeQuery = true)
    Page<UserEntity> findByUsernameLike(String username, String deptid, PageRequest request);

    @Query(value = "SELECT * FROM XTGL_USER WHERE username like %?1% ",
            countQuery = "SELECT count(*) FROM XTGL_USER WHERE username like %?1%",
            nativeQuery = true)
    Page<UserEntity> findByUsernameLike(String username,PageRequest request);
}
