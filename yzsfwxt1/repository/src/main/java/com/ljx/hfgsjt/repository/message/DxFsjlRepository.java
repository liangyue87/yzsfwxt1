package com.ljx.hfgsjt.repository.message;



import com.ljx.hfgsjt.entity.dto.message.DxFsjlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface DxFsjlRepository extends JpaRepository<DxFsjlEntity, String>, JpaSpecificationExecutor<DxFsjlEntity>, Serializable {


}
