package com.ljx.hfgsjt.main.serviceImpl;

import com.ljx.hfgsjt.entity.dto.CodeEntity;
import com.ljx.hfgsjt.main.service.CodeService;
import com.ljx.hfgsjt.repository.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {

//    @Resource
    @Autowired
    private CodeRepository repository;

    @Override
    public void saveCode(CodeEntity xtglcode) {
        repository.save(xtglcode);
    }

    @Override
    public List<CodeEntity> getAllCodeList() {
        Sort sort = new Sort(Sort.Direction.ASC, "sort");
        List<CodeEntity> codeList = repository.findAll(sort);
        return codeList;
    }
    @Override
    public CodeEntity getCodeById(String id) {
        return repository.getOne(id);

    }
    @Override
    public void deleteById(String id) {
       repository.deleteById(id);

    }


}
