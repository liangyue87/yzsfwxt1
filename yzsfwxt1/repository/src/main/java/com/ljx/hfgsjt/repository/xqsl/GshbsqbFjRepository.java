package com.ljx.hfgsjt.repository.xqsl;

import com.ljx.hfgsjt.entity.xqsl.YwslGshbsqbFjEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface GshbsqbFjRepository extends JpaRepository<YwslGshbsqbFjEntity, String>, JpaSpecificationExecutor<YwslGshbsqbFjEntity>, Serializable {

}
