package com.ljx.hfgsjt.repository.yj;

import com.ljx.hfgsjt.entity.yj.YjDzjzbFj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DjzzbFjRepository extends JpaRepository<YjDzjzbFj,String> , JpaSpecificationExecutor<YjDzjzbFj> {

}
