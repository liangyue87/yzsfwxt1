package com.ljx.hfgsjt.repository.xqsl;

import com.ljx.hfgsjt.entity.xqsl.YwslXjxqsqdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface XjxqsqdRepository extends JpaRepository<YwslXjxqsqdEntity, String>, JpaSpecificationExecutor<YwslXjxqsqdEntity>, Serializable {

}
