package com.ljx.hfgsjt.main.serviceImpl;

import com.ljx.hfgsjt.entity.dto.MenuEntity;
import com.ljx.hfgsjt.main.service.MenuService;
import com.ljx.hfgsjt.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository repository;

    @Override
    public void saveMenu(MenuEntity menuEntity) {
        repository.save(menuEntity);
    }

    @Override
    public List<MenuEntity> getAllMenuList() {

        Sort sort = new Sort(Sort.Direction.ASC, "ordinal");
        List<MenuEntity> menuEntityList = repository.findAll(sort);
        return menuEntityList;
    }

    @Override
    public MenuEntity getMenuById(String id) {
        return repository.getOne(id);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<MenuEntity> getMenuByUserid(String userid) {
        return repository.getMenuByUserid(userid);
    }
}
