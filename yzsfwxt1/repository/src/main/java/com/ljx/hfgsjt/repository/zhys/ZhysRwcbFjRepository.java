package com.ljx.hfgsjt.repository.zhys;

import com.ljx.hfgsjt.entity.dto.zhys.ZhysRwcbFjEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ZhysRwcbFjRepository extends JpaRepository<ZhysRwcbFjEntity, String>,JpaSpecificationExecutor<ZhysRwcbFjEntity>, Serializable {

}
