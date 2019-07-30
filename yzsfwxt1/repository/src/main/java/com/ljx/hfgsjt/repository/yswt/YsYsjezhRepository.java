package com.ljx.hfgsjt.repository.yswt;

import com.ljx.hfgsjt.entity.dto.yswt.YsYsjezh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface YsYsjezhRepository extends JpaRepository<YsYsjezh, String > , JpaSpecificationExecutor<YsYsjezh> {
}
