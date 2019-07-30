package com.ljx.hfgsjt.main.serviceImpl;

import com.ljx.hfgsjt.entity.dto.BankEntity;
import com.ljx.hfgsjt.main.dao.BankMapper;
import com.ljx.hfgsjt.main.service.BankService;
import com.ljx.hfgsjt.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository repository;

    @Autowired
    BankMapper dao;

    @Override
    public List<BankEntity> getBankList(Integer page, Integer size,String search_yhmc) {
        Integer pagebefor = 1;
        Integer pageafter = 1;
        if (null == page) {
            page = 0;
            pagebefor = 0;
        }
        if (size==null) {
            size = 10;
            pageafter = 10;
        }
        pagebefor = page * size;
        pageafter = (page+1) * size;
        //PageRequest request = PageRequest.of(page, size);
        //PageRequest request  =new PageRequest(page,size);
        //List<BankEntity> bank = repository.findAll(request).getContent();
        /*传多个参数用@Param注解或将参数封装进map*/
        List<BankEntity> bank = dao.findBankByyhmc( pagebefor, pageafter,search_yhmc);
        return bank;
    }

    public List<BankEntity> getAllBankLists(String search_yhmc){
        List<BankEntity> bank = dao.findAll(search_yhmc);
        return bank;
    }

    public List<BankEntity> getAllBankList(){
        List<BankEntity> bank = repository.findAll();
        return bank;
    }

    @Transactional
    public void saveBank(BankEntity bankEntity) {
        dao.save(bankEntity);
    }

    @Override
    public void deleteById(String bh) {
        dao.deleteById(bh);
    }

    @Override
    public List<String> findBankByBh(String bh) {
        return dao.findBankByBh(bh);
    }

    @Override
    public void updateBank(BankEntity bankEntity) {
        dao.updateBank(bankEntity);
    }

    @Override
    public List<Map<String, String>> findOption() {
        return dao.findOption();
    }

    @Override
    public List<String> findMaxNoByYhmc(String yhmc) {
        return dao.findMaxNoByYhmc(yhmc);
    }

    @Override
    public void deleteByIds(List<String> list) {
        dao.deleteByIds(list);
    }
}
