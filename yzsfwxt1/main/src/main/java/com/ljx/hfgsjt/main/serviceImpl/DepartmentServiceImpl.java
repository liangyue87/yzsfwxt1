package com.ljx.hfgsjt.main.serviceImpl;

import com.ljx.hfgsjt.entity.dto.DepartmentEntity;
import com.ljx.hfgsjt.entity.dto.UserOrgEntity;
import com.ljx.hfgsjt.main.dao.DepartmentDao;
import com.ljx.hfgsjt.main.service.DepartmentService;
import com.ljx.hfgsjt.repository.DepartmentRepository;
import com.ljx.hfgsjt.repository.UserOrgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    @Autowired
    private DepartmentRepository repository;

    @Resource
    @Autowired
    private UserOrgRepository uoRepository;

    @Autowired
    private DepartmentDao departmentDao;


    /**
     * 添加部门
     * @param Department
     */
    @Override
    public void saveDepartment(DepartmentEntity Department) {
        repository.save(Department);
    }

    /**
     * 查询
     *
     * @return
     */
    @Override
    public List<DepartmentEntity> getAllDepartmentList() {

        Sort sort = new Sort(Sort.Direction.ASC, "ordinal");
        List<DepartmentEntity> DepartmentList = repository.findAll(sort);
        return DepartmentList;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public DepartmentEntity getDepartmentById(String id) {
        return departmentDao.getDepartmentById(id);
    }

    @Override
    public void deleteById(String id) {

            repository.deleteById(id);
    }

    @Override
    public void saveUserOrg(UserOrgEntity userOrgEntity) {
        uoRepository.save(userOrgEntity);
    }
}
