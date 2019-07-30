package com.ljx.hfgsjt.main.dao.Provider;

import java.util.List;
import java.util.Map;

public class BankProvider {
    public String deleteByIds(Map map){
        List<String> list = (List<String>) map.get("list");
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM XTGL_FZYH WHERE bh IN (");
        for (int i = 0; i < list.size(); i++) {
            sql.append("'").append(list.get(i)).append("'");
            if (i < list.size() - 1){
                sql.append(",");
            }
        }
        sql.append(")");
        return sql.toString();
    }
}
