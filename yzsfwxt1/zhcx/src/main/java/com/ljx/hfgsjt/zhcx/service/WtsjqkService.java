package com.ljx.hfgsjt.zhcx.service;

import com.ljx.hfgsjt.entity.zhcx.WtsjqkModel;

import java.util.List;

/**
 * Created by Ant on 2019/5/6.
 */
public interface WtsjqkService {
    public int getProjectCount(WtsjqkModel model);
    public List<WtsjqkModel> getProjectList(WtsjqkModel model);

/*   public  WtsjqkModel getotherList(String id);*/
}
