package com.ljx.hfgsjt.repository.xqsl;

import com.ljx.hfgsjt.entity.xqsl.YwslChgzsqbFjEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ChgzsqbFjRepository extends JpaRepository<YwslChgzsqbFjEntity, String>, JpaSpecificationExecutor<YwslChgzsqbFjEntity>, Serializable {

}
