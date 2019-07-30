package com.ljx.hfgsjt.main.service;

import com.ljx.hfgsjt.entity.dto.BankEntity;

import java.util.List;
import java.util.Map;

public interface BankService {
    public List<BankEntity> getBankList(Integer page, Integer size,String search_yhmc);

    public List<BankEntity> getAllBankList();

    public List<BankEntity> getAllBankLists(String search_yhmc);

    public void saveBank(BankEntity bankEntity);

    void deleteById(String bh);

    List<String> findBankByBh(String bh);

    void updateBank(BankEntity bankEntity);

    /**
     * 获得下拉框数据
     * @return
     */
    List<Map<String, String>> findOption();

    /**
     * 根据银行名称获取最大支行编号
     * @return
     */
    List<String> findMaxNoByYhmc(String yhmc);

    /**
     * 批量删除
     * @param list
     */
    void deleteByIds(List<String> list);
}
