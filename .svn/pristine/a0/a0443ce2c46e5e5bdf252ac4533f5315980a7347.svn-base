package com.ljx.hfgsjt.repository;

import com.ljx.hfgsjt.entity.dto.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface CodeRepository extends JpaRepository<CodeEntity, String>,JpaSpecificationExecutor<CodeEntity> {

    /*@Query("select codevalue, codename from XTGL_CODE where parentid = (select id from XTGL_CODE where codename= '需求受理单类型')")
    public List<CodeEntity> getByCodename();*/
}
