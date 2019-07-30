package com.ljx.hfgsjt.yj.service;

import com.ljx.hfgsjt.entity.yj.XmLd;

import java.util.List;

/**
 * Created by Ant on 2019/5/9.
 */
public interface LdService {
    int getLdCount(String xmid);

    List<XmLd> getLdData(String xmid, int startRow, int endRow);

    List<XmLd> LddzjData(String id);

    List<XmLd> LdhxData(String id);
}
