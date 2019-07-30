package com.ljx.hfgsjt.repository;


import com.ljx.hfgsjt.entity.HelloTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

//@NoRepositoryBean
@Repository
public interface HelloTestRepository extends JpaRepository<HelloTest, Long>, JpaSpecificationExecutor<HelloTest>, Serializable {
    List<HelloTest> findAll();

//    @Override
    //void deleteById(Integer tid);
}
