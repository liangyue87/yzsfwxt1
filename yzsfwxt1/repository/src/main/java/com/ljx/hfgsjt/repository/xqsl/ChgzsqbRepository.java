package com.ljx.hfgsjt.repository.xqsl;

import com.ljx.hfgsjt.entity.xqsl.YwslChgzsqbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ChgzsqbRepository extends JpaRepository<YwslChgzsqbEntity, String>, JpaSpecificationExecutor<YwslChgzsqbEntity>, Serializable {

}
