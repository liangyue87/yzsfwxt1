package com.ljx.hfgsjt.main.serviceImpl;

import com.ljx.hfgsjt.entity.dto.SbcjEntity;
import com.ljx.hfgsjt.main.service.SbcjService;
import com.ljx.hfgsjt.repository.SbcjRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SbcjServiceImpl implements SbcjService {

    @Autowired
    private SbcjRepository repository;
    private int pages;

    public int getPages() {
        return pages;
    }


    @Override
    public List<SbcjEntity> getPageSbcjList(String usernaem , Integer page, Integer size) {
        if (page == null) {
            page = 0;
        }
        if (size==null) {
            size = 2;
        }

        Sort sort = new Sort(Sort.Direction.ASC, "bh");
        PageRequest request = PageRequest.of(page, size,sort);

        Page<SbcjEntity>   sbcjpage = repository.findUserAll(usernaem,request);




        this.pages = (int)sbcjpage.getTotalElements();

        List<SbcjEntity> sbcjs= sbcjpage.getContent();
        return sbcjs;
    }

    @Override
    public List<SbcjEntity> getAllSbcjList() {
        List<SbcjEntity> list =repository.findAll();
        return list;
    }

    @Override
    public void saveSbcj(SbcjEntity sbcjEntity) {
        repository.save(sbcjEntity);
    }
   @Override

   //
   //
   @Transactional
   public void deleteById(String id) {
       repository.deleteById(id);
    }




    @Override
    public void updateSbcj(SbcjEntity sbcjEntity) {
        repository.save(sbcjEntity);
    }


}
