package com.ljx.hfgsjt.main.service;

import com.ljx.hfgsjt.entity.dto.MenuEntity;

import java.util.List;

public interface MenuService {

    void saveMenu(MenuEntity menuEntity);

    List<MenuEntity> getAllMenuList();

    MenuEntity getMenuById(String id);

    void deleteById(String id);

    List<MenuEntity> getMenuByUserid(String userid);
}
