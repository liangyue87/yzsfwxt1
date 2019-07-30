package com.ljx.hfgsjt.main.service;

import com.ljx.hfgsjt.entity.dto.SbcjEntity;

import java.util.List;


public interface SbcjService {

    public List<SbcjEntity> getPageSbcjList(String sbcjname,Integer page, Integer size);

    public List<SbcjEntity> getAllSbcjList();

    public void saveSbcj(SbcjEntity sbcjEntity);

    public void deleteById(String id);

    public void updateSbcj(SbcjEntity sbcjEntity);


    public int getPages();
}
