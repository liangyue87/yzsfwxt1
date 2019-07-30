package com.ljx.hfgsjt.repository.yj;

import com.ljx.hfgsjt.entity.yj.YjDzjcb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DjzcbRepository extends JpaRepository<YjDzjcb,String> , JpaSpecificationExecutor<YjDzjcb> {

}
