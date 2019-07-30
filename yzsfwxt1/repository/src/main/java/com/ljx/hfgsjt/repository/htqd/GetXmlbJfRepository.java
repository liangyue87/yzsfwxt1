package com.ljx.hfgsjt.repository.htqd;

import com.ljx.hfgsjt.entity.dto.htqd.GetXmlbJfEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GetXmlbJfRepository extends JpaRepository<GetXmlbJfEntity,String> , JpaSpecificationExecutor<GetXmlbJfEntity> {

}
