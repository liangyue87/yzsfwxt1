package com.ljx.hfgsjt.repository;

import com.ljx.hfgsjt.entity.GoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodTypeRepository extends JpaRepository<GoodType,Long> {

}
