package com.ljx.hfgsjt.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.ljx.hfgsjt.entity.dto.BankEntity;

import java.io.Serializable;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, String>,JpaSpecificationExecutor<BankEntity>, Serializable {

}
